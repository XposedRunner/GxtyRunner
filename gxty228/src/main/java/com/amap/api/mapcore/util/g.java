package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.amap.api.mapcore.util.db.f;
import com.amap.api.maps.model.CrossOverlay.GenerateCrossImageListener;
import java.nio.FloatBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/* compiled from: PboPluginTexture */
public class g {
    float[] a = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    float[] b = new float[]{-1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f};
    private final lj c;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private BlockingQueue<Runnable> g = new LinkedBlockingQueue();
    private ExecutorService h = null;
    private boolean i = false;
    private boolean j = false;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private volatile EGLContext n;
    private volatile EGLConfig o;
    private EGLDisplay p;
    private EGLContext q;
    private EGLSurface r;
    private f s;
    private FloatBuffer t;
    private FloatBuffer u;
    private a v;
    private GenerateCrossImageListener w;

    /* compiled from: PboPluginTexture */
    public interface a {
        int getTextureID();
    }

    public g(lj ljVar) {
        this.c = ljVar;
        this.j = false;
        this.h = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors() * 2, (long) 1, TimeUnit.SECONDS, this.g, new dy("AMapPboRenderThread"), new AbortPolicy());
    }

    private void a(String str) {
    }

    private void d() {
        this.p = EGL14.eglGetDisplay(0);
        if (this.p == EGL14.EGL_NO_DISPLAY) {
            a("eglGetDisplay failed");
            return;
        }
        int[] iArr = new int[2];
        if (EGL14.eglInitialize(this.p, iArr, 0, iArr, 1)) {
            this.q = EGL14.eglCreateContext(this.p, this.o, this.n, new int[]{12440, 2, 12344}, 0);
            if (this.q == EGL14.EGL_NO_CONTEXT) {
                a("eglCreateContext failed");
                return;
            }
            this.r = EGL14.eglCreatePbufferSurface(this.p, this.o, new int[]{12375, this.e, 12374, this.f, 12344}, 0);
            if (this.r == EGL14.EGL_NO_SURFACE) {
                a("eglCreatePbufferSurface failed");
                return;
            } else if (EGL14.eglMakeCurrent(this.p, this.r, this.r, this.q)) {
                GLES20.glFlush();
                a("initOpenGL complete");
                this.i = true;
                return;
            } else {
                a("eglMakeCurrent failed");
                return;
            }
        }
        this.p = null;
        a("eglInitialize failed");
    }

    public void a(int i, int i2) {
        this.e = i;
        this.f = i2;
        this.n = EGL14.eglGetCurrentContext();
        if (this.n == EGL14.EGL_NO_CONTEXT) {
            a("eglGetCurrentContext failed");
            return;
        }
        EGLDisplay eglGetCurrentDisplay = EGL14.eglGetCurrentDisplay();
        if (eglGetCurrentDisplay == EGL14.EGL_NO_DISPLAY) {
            a("sharedEglDisplay failed");
            return;
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (EGL14.eglGetConfigs(eglGetCurrentDisplay, eGLConfigArr, 0, eGLConfigArr.length, new int[1], 0)) {
            this.o = eGLConfigArr[0];
            this.h.execute(new Runnable(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.d();
                }
            });
            return;
        }
        a("eglGetConfigs failed");
    }

    private void e() {
        if (this.c != null) {
            this.s = (f) this.c.u(0);
        }
    }

    public void a() {
        this.h.execute(new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.k = 0;
                while (!this.a.j && this.a.k < 5) {
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (this.a.i) {
                        GLES20.glViewport(0, 0, this.a.e, this.a.f);
                        GLES20.glClear(16640);
                        this.a.f();
                    } else if (this.a.w != null) {
                        this.a.w.onGenerateComplete(null, -1);
                        return;
                    } else {
                        return;
                    }
                }
            }
        });
    }

    private void f() {
        try {
            if (!this.j) {
                if (this.v == null) {
                    a("renderTextureAndReadPixel failed textureHelper is null");
                    return;
                }
                if (this.v != null) {
                    this.d = this.v.getTextureID();
                }
                if (this.d <= 0) {
                    a("renderTextureAndReadPixel failed mTextureID is <= 0 mTextureID " + this.d);
                    return;
                }
                if (this.s == null || this.s.c()) {
                    e();
                }
                if (this.t == null) {
                    this.t = en.a(this.b);
                }
                if (this.u == null) {
                    this.u = en.a(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f});
                }
                this.s.a();
                GLES20.glDisable(3042);
                GLES20.glBlendFunc(1, 771);
                GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.d);
                GLES20.glEnableVertexAttribArray(this.s.b);
                GLES20.glVertexAttribPointer(this.s.b, 3, 5126, false, 12, this.t);
                GLES20.glEnableVertexAttribArray(this.s.c);
                GLES20.glVertexAttribPointer(this.s.c, 2, 5126, false, 8, this.u);
                Matrix.setIdentityM(this.a, 0);
                Matrix.scaleM(this.a, 0, 1.0f, 1.0f, 1.0f);
                GLES20.glUniformMatrix4fv(this.s.a, 1, false, this.a, 0);
                GLES20.glDrawArrays(6, 0, 4);
                GLES20.glDisableVertexAttribArray(this.s.b);
                GLES20.glDisableVertexAttribArray(this.s.c);
                GLES20.glBindTexture(3553, 0);
                GLES20.glUseProgram(0);
                GLES20.glDisable(3042);
                du.a("drawTexure");
                GLES20.glFinish();
                this.k++;
                if (this.k == 5) {
                    g();
                }
            }
        } catch (Throwable th) {
            if (this.w != null) {
                this.w.onGenerateComplete(null, -1);
            }
        }
    }

    private void g() {
        int i = 0;
        if (this.w != null) {
            if (this.l == 0) {
                this.l = this.e;
            }
            if (this.m == 0) {
                this.m = this.f;
            }
            Bitmap a = en.a(0, this.f - this.m, this.l, this.m);
            GenerateCrossImageListener generateCrossImageListener = this.w;
            if (!this.i) {
                i = -1;
            }
            generateCrossImageListener.onGenerateComplete(a, i);
        }
    }

    public void b() {
        this.j = true;
        if (this.u != null) {
            this.u.clear();
            this.u = null;
        }
        if (this.t != null) {
            this.t.clear();
            this.t = null;
        }
        this.v = null;
        this.h.shutdownNow();
    }

    public boolean c() {
        return this.j;
    }

    public void a(GenerateCrossImageListener generateCrossImageListener) {
        this.w = generateCrossImageListener;
    }

    public void a(a aVar) {
        this.v = aVar;
    }

    public void b(int i, int i2) {
        this.l = i;
        this.m = i2;
    }
}
