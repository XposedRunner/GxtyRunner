package com.amap.api.mapcore.util;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.amap.api.mapcore.util.db.b;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: GlModelCore */
public class lh {
    List<Float> a = new ArrayList();
    List<Float> b = new ArrayList();
    private FloatBuffer c;
    private FloatBuffer d;
    private int e = 0;
    private float f;
    private float g = 0.0f;
    private float h = 0.0f;

    public lh(List<Float> list, List<Float> list2) {
        this.a = list;
        this.b = list2;
        if (this.c == null) {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(list.size() * 4);
            allocateDirect.order(ByteOrder.nativeOrder());
            this.c = allocateDirect.asFloatBuffer();
        }
        this.c.clear();
        int i = 1;
        float f = 0.0f;
        float f2 = 1000000.0f;
        float f3 = 1000000.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        for (int i2 = 1; i2 < list.size() + 1; i2++) {
            Float f6 = (Float) list.get(i2 - 1);
            this.c.put(f6.floatValue());
            if (i == 1) {
                f5 = Math.max(f6.floatValue(), f5);
                f3 = Math.min(f6.floatValue(), f3);
            }
            if (i == 2) {
                f4 = Math.max(f6.floatValue(), f4);
                f2 = Math.min(f6.floatValue(), f2);
            }
            if (i == 3) {
                f = Math.max(f, f6.floatValue());
                i = 0;
            }
            i++;
        }
        float abs = Math.abs(f5 - f3);
        float abs2 = Math.abs(f4 - f2);
        this.g = abs > abs2 ? abs : abs2;
        if (abs <= abs2) {
            abs2 = abs;
        }
        this.h = abs2;
        this.c.position(0);
        if (this.d == null) {
            ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(list2.size() * 4);
            allocateDirect2.order(ByteOrder.nativeOrder());
            this.d = allocateDirect2.asFloatBuffer();
        }
        this.d.clear();
        for (Float f62 : list2) {
            this.d.put(f62.floatValue());
        }
        this.d.position(0);
    }

    public float a() {
        return this.g;
    }

    public float b() {
        return this.h;
    }

    public void a(int i) {
        this.e = i;
    }

    public void a(float f) {
        this.f = -f;
    }

    public void a(b bVar, float[] fArr) {
        Matrix.rotateM(fArr, 0, this.f, 0.0f, 0.0f, 1.0f);
        GLES20.glUseProgram(bVar.d);
        GLES20.glClear(256);
        GLES20.glEnable(2929);
        GLES20.glDepthMask(true);
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(770, 771);
        GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glBindTexture(3553, this.e);
        GLES20.glEnableVertexAttribArray(bVar.h);
        GLES20.glVertexAttribPointer(bVar.h, 2, 5126, false, 8, this.d);
        GLES20.glEnableVertexAttribArray(bVar.c);
        GLES20.glVertexAttribPointer(bVar.c, 3, 5126, false, 12, this.c);
        GLES20.glUniformMatrix4fv(bVar.g, 1, false, fArr, 0);
        GLES20.glDrawArrays(4, 0, this.a.size() / 3);
        GLES20.glBindTexture(3553, 0);
        GLES20.glDisable(2929);
        GLES20.glDisableVertexAttribArray(bVar.c);
        GLES20.glDisableVertexAttribArray(bVar.h);
        GLES20.glUseProgram(0);
    }

    public void c() {
        this.a.clear();
        this.d.clear();
    }
}
