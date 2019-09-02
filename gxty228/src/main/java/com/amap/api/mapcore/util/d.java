package com.amap.api.mapcore.util;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.autonavi.ae.gmap.GLMapRender;

/* compiled from: AMapGLSurfaceView */
public class d extends GLSurfaceView implements lk {
    protected boolean a;
    private lj b;
    private GLMapRender c;

    public d(Context context) {
        this(context, null);
    }

    public d(Context context, AttributeSet attributeSet) {
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

    public void setRenderer(Renderer renderer) {
        this.c = (GLMapRender) renderer;
        super.setRenderer(renderer);
    }

    public void a(ds dsVar) {
        super.setEGLConfigChooser(dsVar);
    }

    public void a(dt dtVar) {
        super.setEGLContextFactory(dtVar);
    }

    public void b() {
        onPause();
        try {
            if (this.c != null) {
                this.c.onDetachedFromWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onDetachedFromWindow();
    }

    public void onPause() {
        if (!this.c.mSurfacedestoryed) {
            queueEvent(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.c != null) {
                        try {
                            this.a.c.onSurfaceDestory();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
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
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 8 || i == 4) {
            try {
                if (this.c != null) {
                    this.c.renderPause();
                    this.a = false;
                }
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
        onResume();
    }

    protected void onDetachedFromWindow() {
    }
}
