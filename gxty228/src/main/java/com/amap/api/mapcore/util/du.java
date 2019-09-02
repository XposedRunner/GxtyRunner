package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.opengl.GLES20;
import android.util.Log;
import android.view.SurfaceHolder;
import com.amap.api.mapcore.util.db.e;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* compiled from: GlesUtility */
public class du {

    /* compiled from: GlesUtility */
    public static class a extends ds {
        private static int g = 4;
        protected int a;
        protected int b;
        protected int c;
        protected int d;
        protected int e;
        protected int f;
        private int[] h = new int[1];

        public a(int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
        }

        private int[] a(int i, boolean z) {
            int i2 = 1;
            if (i == 2) {
                int[] iArr = new int[17];
                iArr[0] = 12324;
                iArr[1] = this.a;
                iArr[2] = 12323;
                iArr[3] = this.b;
                iArr[4] = 12322;
                iArr[5] = this.c;
                iArr[6] = 12321;
                iArr[7] = this.d;
                iArr[8] = 12325;
                iArr[9] = this.e;
                iArr[10] = 12326;
                iArr[11] = this.f;
                iArr[12] = 12338;
                if (!z) {
                    i2 = 0;
                }
                iArr[13] = i2;
                iArr[14] = 12352;
                iArr[15] = g;
                iArr[16] = 12344;
                return iArr;
            } else if (z) {
                return new int[]{12324, this.a, 12323, this.b, 12322, this.c, 12338, 1, 12344};
            } else {
                return new int[]{12324, this.a, 12323, this.b, 12322, this.c, 12344};
            }
        }

        private c a(EGL10 egl10, EGLDisplay eGLDisplay) {
            c cVar = new c();
            cVar.a = a(2, true);
            egl10.eglChooseConfig(eGLDisplay, cVar.a, null, 0, cVar.b);
            if (cVar.b[0] <= 0) {
                cVar.a = a(2, false);
                egl10.eglChooseConfig(eGLDisplay, cVar.a, null, 0, cVar.b);
                if (cVar.b[0] <= 0) {
                    return null;
                }
            }
            return cVar;
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            c a = a(egl10, eGLDisplay);
            if (a == null || a.a == null) {
                return null;
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[a.b[0]];
            egl10.eglChooseConfig(eGLDisplay, a.a, eGLConfigArr, a.b[0], a.b);
            EGLConfig a2 = a(egl10, eGLDisplay, eGLConfigArr);
            if (a2 != null) {
                return a2;
            }
            this.a = 8;
            this.b = 8;
            this.c = 8;
            c a3 = a(egl10, eGLDisplay);
            if (a3 == null || a3.a == null) {
                return a2;
            }
            eGLConfigArr = new EGLConfig[a3.b[0]];
            egl10.eglChooseConfig(eGLDisplay, a3.a, eGLConfigArr, a3.b[0], a3.b);
            return a(egl10, eGLDisplay, eGLConfigArr);
        }

        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a2 = a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (a >= this.e && a2 >= this.f) {
                    a = a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int a3 = a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int a4 = a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    a2 = a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (a == this.a && a3 == this.b && a4 == this.c && a2 == this.d) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.h)) {
                return this.h[0];
            }
            return i2;
        }
    }

    /* compiled from: GlesUtility */
    public static class b extends dt {
        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            try {
                return egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            egl10.eglDestroyContext(eGLDisplay, eGLContext);
        }
    }

    /* compiled from: GlesUtility */
    private static class c {
        public int[] a;
        public int[] b;

        private c() {
            this.a = null;
            this.b = new int[1];
        }
    }

    public static void a(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("amap", str + ": glError " + glGetError);
            throw new RuntimeException(str + ": glError " + glGetError);
        }
    }

    public static void a(e eVar, int i, int i2, FloatBuffer floatBuffer, float f, FloatBuffer floatBuffer2, int i3, int i4, float[] fArr) {
        b(eVar, 4, i, floatBuffer2, 1.0f, i4, fArr);
        b(eVar, 2, i2, floatBuffer, f, i3, fArr);
    }

    public static void a(e eVar, int i, int i2, FloatBuffer floatBuffer, float f, int i3, float[] fArr, float f2, int i4) {
        b(eVar, 6, i, floatBuffer, 1.0f, i3, fArr);
        b(eVar, i, i2, floatBuffer, f, i3, fArr, f2, i4);
    }

    public static void b(e eVar, int i, int i2, FloatBuffer floatBuffer, float f, int i3, float[] fArr, float f2, int i4) {
        if (i4 != -1) {
            a(eVar, i, i2, floatBuffer, f * f2, i3, fArr, i4);
            return;
        }
        a(eVar, i, i2, floatBuffer, f, i3, fArr);
    }

    public static void b(e eVar, int i, int i2, FloatBuffer floatBuffer, float f, FloatBuffer floatBuffer2, int i3, int i4, float[] fArr) {
        b(eVar, 2, i2, floatBuffer, f, i3, fArr);
    }

    private static void a(e eVar, int i, int i2, FloatBuffer floatBuffer, float f, int i3, float[] fArr, int i4) {
        try {
            float alpha = ((float) Color.alpha(i2)) / 255.0f;
            float red = ((float) Color.red(i2)) / 255.0f;
            float green = ((float) Color.green(i2)) / 255.0f;
            float blue = ((float) Color.blue(i2)) / 255.0f;
            if (i3 >= 3) {
                int i5 = (i3 - 2) * 3;
                if (floatBuffer != null && floatBuffer.limit() >= i5 + 3) {
                    float[] fArr2 = new float[i5];
                    for (int i6 = 0; i6 < i5; i6++) {
                        fArr2[i6] = floatBuffer.get(i6 + 3);
                    }
                    AMapNativeRenderer.nativeDrawLineByTextureID(fArr2, fArr2.length, f, i4, red, green, blue, alpha, 0.0f, true, true, false, fArr, 3, 0);
                }
            }
        } catch (Throwable th) {
            gz.c(th, "GlesUtility", "drawCircleLine");
        }
    }

    private static void a(e eVar, int i, int i2, FloatBuffer floatBuffer, float f, int i3, float[] fArr) {
        a(eVar, 2, i2, floatBuffer, f, 1, i3 - 1, fArr);
    }

    private static void b(e eVar, int i, int i2, FloatBuffer floatBuffer, float f, int i3, float[] fArr) {
        a(eVar, i, i2, floatBuffer, f, 0, i3, fArr);
    }

    private static void a(e eVar, int i, int i2, FloatBuffer floatBuffer, float f, int i3, int i4, float[] fArr) {
        if (f != 0.0f && eVar != null) {
            eVar.a();
            GLES20.glEnable(3042);
            GLES20.glDisable(2929);
            GLES20.glBlendFunc(770, 771);
            float alpha = ((float) Color.alpha(i2)) / 255.0f;
            float red = ((float) Color.red(i2)) / 255.0f;
            float green = ((float) Color.green(i2)) / 255.0f;
            float blue = ((float) Color.blue(i2)) / 255.0f;
            float[] fArr2 = new float[]{red, green, blue, alpha};
            GLES20.glLineWidth(f);
            GLES20.glEnableVertexAttribArray(eVar.b);
            GLES20.glVertexAttribPointer(eVar.b, 3, 5126, false, 0, floatBuffer);
            GLES20.glUniform4fv(eVar.c, 1, fArr2, 0);
            GLES20.glUniformMatrix4fv(eVar.a, 1, false, fArr, 0);
            GLES20.glDrawArrays(i, i3, i4);
            GLES20.glDisableVertexAttribArray(eVar.b);
            GLES20.glDisable(3042);
            GLES20.glUseProgram(0);
        }
    }

    public static void a(lk lkVar, int i, int i2, int i3, int i4, int i5, int i6) {
        if (i4 > 0) {
            SurfaceHolder holder = lkVar.getHolder();
            if (holder != null) {
                holder.setFormat(-3);
            }
        }
        lkVar.a(new b());
        lkVar.a(new a(i, i2, i3, i4, i5, i6));
    }
}
