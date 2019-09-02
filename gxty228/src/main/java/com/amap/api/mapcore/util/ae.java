package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.amap.api.mapcore.util.db.a;
import com.amap.api.maps.model.BitmapDescriptor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* compiled from: FakeInstanceMultiPoint */
public class ae {
    public static int a = 200;
    float[] b = null;
    int c = 0;
    int d = 0;
    private boolean e = false;
    private boolean f = false;
    private BitmapDescriptor g;
    private FloatBuffer h;
    private ShortBuffer i;
    private int j = 0;
    private a k;
    private db l;

    public ae(float[] fArr, ai aiVar) {
        this.b = fArr;
    }

    private void a(float[] fArr) {
        int i = 0;
        if (fArr != null) {
            ByteBuffer allocateDirect;
            if (this.h == null) {
                allocateDirect = ByteBuffer.allocateDirect((fArr.length * a) * 4);
                allocateDirect.order(ByteOrder.nativeOrder());
                this.h = allocateDirect.asFloatBuffer();
            }
            this.h.clear();
            for (int i2 = 0; i2 < a; i2++) {
                int i3 = 0;
                for (float f : fArr) {
                    if (i3 % 6 == 3) {
                        this.h.put((float) i2);
                    } else {
                        this.h.put(f);
                    }
                    i3++;
                }
            }
            this.h.position(0);
            if (this.i == null) {
                allocateDirect = ByteBuffer.allocateDirect((a * 6) * 2);
                allocateDirect.order(ByteOrder.nativeOrder());
                this.i = allocateDirect.asShortBuffer();
                short[] sArr = new short[(a * 6)];
                while (i < a) {
                    sArr[(i * 6) + 0] = (short) ((i * 4) + 0);
                    sArr[(i * 6) + 1] = (short) ((i * 4) + 1);
                    sArr[(i * 6) + 2] = (short) ((i * 4) + 2);
                    sArr[(i * 6) + 3] = (short) ((i * 4) + 0);
                    sArr[(i * 6) + 4] = (short) ((i * 4) + 2);
                    sArr[(i * 6) + 5] = (short) ((i * 4) + 3);
                    i++;
                }
                this.i.put(sArr);
                this.i.flip();
            }
            this.e = true;
        }
    }

    public void a() {
        if (this.b != null && !this.e) {
            a(this.b);
        }
    }

    public void a(BitmapDescriptor bitmapDescriptor) {
        this.g = bitmapDescriptor;
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null) {
            if (this.j == 0) {
                int[] iArr = new int[1];
                GLES20.glGenTextures(1, iArr, 0);
                this.j = iArr[0];
            }
            if (this.j != 0) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.j);
                GLES20.glTexParameterf(3553, 10241, 9728.0f);
                GLES20.glTexParameterf(3553, 10240, 9729.0f);
                GLES20.glTexParameterf(3553, 10242, 33071.0f);
                GLES20.glTexParameterf(3553, 10243, 33071.0f);
                GLUtils.texImage2D(3553, 0, bitmap, 0);
                if (this.j != 0) {
                    this.f = true;
                }
            }
        }
    }

    public boolean b() {
        return this.e;
    }

    public void a(float[] fArr, float[] fArr2, float[] fArr3, float f, float f2, float f3, float f4, int i) {
        if (!(this.f || this.g == null)) {
            a(this.g.getBitmap());
        }
        if (this.j != 0) {
            if (this.k == null || this.k.c()) {
                f();
            }
            e();
            GLES20.glUseProgram(this.k.d);
            GLES20.glUniform4f(this.k.j, f, f2, f3, f4);
            GLES20.glUniform3fv(this.k.i, i, fArr3, 0);
            GLES20.glDisable(2929);
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(770, 771);
            GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, this.j);
            GLES20.glEnableVertexAttribArray(this.k.c);
            GLES20.glBindBuffer(34962, this.c);
            GLES20.glVertexAttribPointer(this.k.c, 4, 5126, false, 24, 0);
            GLES20.glEnableVertexAttribArray(this.k.h);
            GLES20.glVertexAttribPointer(this.k.h, 2, 5126, false, 24, 16);
            GLES20.glUniformMatrix4fv(this.k.g, 1, false, fArr, 0);
            GLES20.glUniformMatrix4fv(this.k.k, 1, false, fArr2, 0);
            GLES20.glBindBuffer(34963, this.d);
            GLES20.glDrawElements(4, i * 6, 5123, 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glBindBuffer(34962, 0);
            GLES20.glDisableVertexAttribArray(this.k.c);
            GLES20.glDisableVertexAttribArray(this.k.h);
            GLES20.glUseProgram(0);
        }
    }

    private void e() {
        if (this.c == 0) {
            int[] iArr = new int[2];
            GLES20.glGenBuffers(2, iArr, 0);
            this.c = iArr[0];
            this.d = iArr[1];
            GLES20.glBindBuffer(34962, this.c);
            GLES20.glBufferData(34962, this.h.limit() * 4, this.h, 35044);
            GLES20.glBindBuffer(34963, this.d);
            GLES20.glBufferData(34963, (a * 6) * 2, this.i, 35044);
            a("bindVbo");
            this.h.clear();
            this.h = null;
        }
    }

    public void c() {
        if (this.h != null) {
            this.h.clear();
        }
        if (this.i != null) {
            this.i.clear();
        }
        if (this.g != null) {
            this.g = null;
        }
        GLES20.glDeleteBuffers(2, new int[]{this.c, this.d}, 0);
        if (this.j != 0) {
            GLES20.glDeleteTextures(1, new int[]{this.j}, 0);
        }
        this.c = 0;
        this.d = 0;
        this.b = null;
        this.e = false;
        this.f = false;
        this.c = 0;
        this.d = 0;
        this.l = null;
    }

    public void a(db dbVar) {
        this.l = dbVar;
    }

    private void f() {
        try {
            if (this.l != null) {
                this.k = (a) this.l.a(4);
            }
        } catch (Throwable th) {
            a = 1;
            if (this.l != null) {
                this.k = (a) this.l.a(4);
            }
        }
    }

    public static synchronized void a(String str) {
        synchronized (ae.class) {
            int glGetError = GLES20.glGetError();
            if (glGetError != 0) {
                Log.e("amap", str + ": glError " + glGetError);
                throw new RuntimeException(str + ": glError " + glGetError);
            }
        }
    }

    public boolean d() {
        if (this.l == null) {
            return false;
        }
        return true;
    }
}
