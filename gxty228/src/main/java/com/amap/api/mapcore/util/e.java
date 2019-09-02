package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView.Renderer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.amap.api.mapcore.util.n.f;
import com.autonavi.ae.gmap.GLMapRender;

/* compiled from: AMapGLTextureView */
public class e extends n implements lk {
    protected boolean a;
    private lj b;
    private GLMapRender c;

    public e(Context context) {
        this(context, null);
    }

    public e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = null;
        this.c = null;
        this.a = false;
        du.a((lk) this, 5, 6, 5, 0, 16, 8);
        this.b = new af(this, context, attributeSet);
    }

    public lj a() {
        return this.b;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        try {
            return this.b.onTouchEvent(motionEvent);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public SurfaceHolder getHolder() {
        return null;
    }

    public void a(ds dsVar) {
        super.a((com.amap.api.mapcore.util.n.e) dsVar);
    }

    public void a(dt dtVar) {
        super.a((f) dtVar);
    }

    public void b() {
        c();
        try {
            if (this.c != null) {
                this.c.onDetachedFromWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onDetachedFromWindow();
    }

    public void setRenderer(Renderer renderer) {
        this.c = (GLMapRender) renderer;
        super.setRenderer(renderer);
    }

    public void c() {
        if (!this.c.mSurfacedestoryed) {
            queueEvent(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        if (this.a.c != null) {
                            this.a.c.onSurfaceDestory();
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            int i = 0;
            while (!this.c.mSurfacedestoryed) {
                int i2 = i + 1;
                if (i >= 20) {
                    break;
                }
                try {
                    Thread.sleep(50);
                    i = i2;
                } catch (InterruptedException e) {
                    i = i2;
                }
            }
        }
        super.c();
    }

    public void d() {
        super.d();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        requestRender();
        try {
            Thread.sleep(100);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return super.onSurfaceTextureDestroyed(surfaceTexture);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 8 || i == 4) {
            try {
                if (this.c != null) {
                    this.c.renderPause();
                    this.a = false;
                }
                requestRender();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else if (i == 0 && this.c != null) {
            this.c.renderResume();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            if (this.c != null) {
                this.c.onAttachedToWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        d();
    }

    protected void onDetachedFromWindow() {
    }
}
