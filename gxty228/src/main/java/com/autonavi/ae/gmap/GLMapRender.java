package com.autonavi.ae.gmap;

import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.autonavi.amap.mapcore.interfaces.IAMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLMapRender implements Renderer {
    public static final int ANIMATION_TICK_COUNT = 10;
    public static final int LONG_LONG_TICK_COUNT = 30;
    public static final int LONG_TICK_COUNT = 6;
    private static final int MAP_RENDER_MSG_RUNNABLE_ONGLTHREAD = 100;
    private static final int MAP_RENDER_MSG_SURFACE_RENDER = 10;
    public static final int NORMAL_TICK_COUNT = 2;
    public static final int RENDER_FPS_ANIMATION = 30;
    public static final int RENDER_FPS_GESTURE_ACTION = 40;
    static final int RENDER_FPS_MAX = 60;
    public static final int RENDER_FPS_NAVI = 10;
    public static final int RENDER_FPS_NORMAL = 15;
    static final long RENDER_TIMMER_DIFF_MIN = 16;
    static final String TAG = "render";
    private volatile AtomicLong mDrawFrameTickCount = new AtomicLong(6);
    public IAMap mGLMapView;
    private Handler mGLRenderHandler = null;
    private HandlerThread mGLRenderThread = null;
    private volatile boolean mIsRendPause = false;
    private boolean mIsTrafficMode = false;
    private long mLastFrameTime = System.currentTimeMillis();
    public volatile boolean mSurfacedestoryed = false;
    private int mTargetFrameDurationMillis = 66;
    private float mTargetRenderFPS = 15.0f;

    public GLMapRender(IAMap iAMap) {
        this.mGLMapView = iAMap;
    }

    public void setTrafficMode(boolean z) {
        this.mIsTrafficMode = z;
    }

    public void resetTickCount(int i) {
        long j = this.mDrawFrameTickCount.get();
        if (this.mIsRendPause || this.mGLRenderThread == null || this.mGLRenderHandler == null || !this.mGLRenderThread.isAlive()) {
            if (j < ((long) i)) {
                this.mDrawFrameTickCount.set((long) i);
            }
        } else if (j <= 0) {
            this.mDrawFrameTickCount.set((long) i);
            this.mGLRenderHandler.removeMessages(10);
            this.mGLRenderHandler.sendEmptyMessage(10);
        } else if (j < ((long) i)) {
            this.mDrawFrameTickCount.set((long) i);
        }
    }

    public boolean isRenderPause() {
        return this.mIsRendPause;
    }

    public void setRenderFps(float f) {
        if (this.mTargetRenderFPS != f && f > 0.0f) {
            this.mTargetFrameDurationMillis = (int) ((1.0f / f) * 1000.0f);
            this.mTargetRenderFPS = f;
        }
    }

    public void sendToRenderEvent(Runnable runnable) {
        if (this.mGLRenderHandler != null && this.mGLRenderThread != null && this.mGLRenderThread.isAlive()) {
            this.mGLRenderHandler.post(runnable);
        }
    }

    public void renderPause() {
        if (!(this.mGLRenderHandler == null || this.mGLRenderThread == null || !this.mGLRenderThread.isAlive())) {
            this.mGLRenderHandler.removeMessages(10);
        }
        this.mIsRendPause = true;
    }

    public void renderResume() {
        if (!(this.mGLRenderHandler == null || this.mGLRenderThread == null || !this.mGLRenderThread.isAlive())) {
            this.mGLRenderHandler.removeMessages(10);
        }
        this.mIsRendPause = false;
        this.mDrawFrameTickCount.set(-1);
        resetTickCount(30);
    }

    public void onAttachedToWindow() {
        this.mGLRenderThread = new HandlerThread(" AMapGlRenderThread");
        this.mGLRenderThread.start();
        this.mGLRenderHandler = new Handler(this.mGLRenderThread.getLooper()) {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 10:
                        if (!GLMapRender.this.mIsRendPause && GLMapRender.this.mGLMapView != null && GLMapRender.this.mGLMapView.getRenderMode() == 0) {
                            GLMapRender.this.mGLMapView.requestRender();
                            return;
                        }
                        return;
                    case 100:
                        ((Runnable) message.obj).run();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void onDetachedFromWindow() {
        if (this.mGLRenderThread != null) {
            this.mGLRenderThread.quit();
            this.mGLRenderThread = null;
            this.mGLRenderHandler = null;
        }
    }

    private void drawSingleFrame(GL10 gl10) {
        try {
            this.mGLMapView.drawFrame(gl10);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onDrawFrame(GL10 gl10) {
        this.mLastFrameTime = System.currentTimeMillis();
        drawSingleFrame(gl10);
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - this.mLastFrameTime;
        this.mLastFrameTime = currentTimeMillis;
        currentTimeMillis = this.mDrawFrameTickCount.get();
        if (this.mGLMapView.getRenderMode() == 0 && this.mGLRenderHandler != null && this.mGLRenderThread != null && this.mGLRenderThread.isAlive()) {
            currentTimeMillis--;
            this.mDrawFrameTickCount.set(currentTimeMillis);
            if (currentTimeMillis > 0) {
                currentTimeMillis = Math.max(16, ((long) this.mTargetFrameDurationMillis) - j);
            } else if (currentTimeMillis > ((long) -5)) {
                currentTimeMillis = 60;
            } else if (currentTimeMillis > ((long) -7)) {
                currentTimeMillis = 100;
            } else if (currentTimeMillis > ((long) -9)) {
                currentTimeMillis = 250;
            } else {
                if (this.mIsTrafficMode) {
                    currentTimeMillis = 10000;
                } else {
                    currentTimeMillis = 500;
                }
                this.mDrawFrameTickCount.set(-9);
            }
            if (currentTimeMillis > 0) {
                this.mGLRenderHandler.removeMessages(10);
                this.mGLRenderHandler.sendEmptyMessageDelayed(10, currentTimeMillis);
            }
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        if (this.mSurfacedestoryed) {
            onSurfaceCreated(gl10, null);
        }
        this.mGLMapView.changeSurface(gl10, i, i2);
        resetTickCount(30);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.mIsRendPause = false;
        this.mSurfacedestoryed = false;
        this.mGLMapView.createSurface(gl10, eGLConfig);
    }

    public void onSurfaceDestory() {
        this.mIsRendPause = true;
        if (!(this.mGLRenderHandler == null || this.mGLRenderThread == null)) {
            this.mGLRenderHandler.removeCallbacksAndMessages(null);
        }
        this.mGLMapView.destroySurface(1);
        this.mSurfacedestoryed = true;
    }
}
