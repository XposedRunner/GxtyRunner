package com.amap.api.mapcore.util;

import com.autonavi.amap.mapcore.animation.GLAlphaAnimation;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* compiled from: MaskLayer */
public class b {
    public FloatBuffer a;
    public ShortBuffer b;
    float[] c = new float[]{-1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
    short[] d = new short[]{(short) 0, (short) 1, (short) 3, (short) 0, (short) 3, (short) 2};
    private float e = 0.0f;
    private float f = 0.0f;
    private float g = 0.0f;
    private float h = 0.7f;
    private GLAlphaAnimation i;

    public b() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.d.length * 2);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.b = allocateDirect.asShortBuffer();
        this.b.put(this.d);
        this.b.position(0);
        allocateDirect = ByteBuffer.allocateDirect(this.c.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.a = allocateDirect.asFloatBuffer();
        this.a.put(this.c);
        this.a.position(0);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.e = ((float) i) / 255.0f;
        this.f = ((float) i2) / 255.0f;
        this.g = ((float) i3) / 255.0f;
        this.h = ((float) i4) / 255.0f;
    }

    public void a(GLAlphaAnimation gLAlphaAnimation) {
        if (!(this.i == null || this.i.hasEnded())) {
            this.i.cancel();
        }
        if (gLAlphaAnimation != null) {
            this.i = gLAlphaAnimation;
            this.i.start();
        }
    }
}
