package com.amap.api.mapcore.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/* compiled from: NativeBufferAbstractPool */
public class dz extends eu<a> {
    private a b;

    /* compiled from: NativeBufferAbstractPool */
    static final class a extends ev<a> {
        ByteBuffer a;
        ShortBuffer b;
        FloatBuffer c;
        IntBuffer d;
        int e;

        a() {
        }

        void a(int i) {
            if (i < 32768) {
                i = 32768;
            }
            this.a = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
            this.e = i;
            this.b = null;
            this.d = null;
            this.c = null;
        }
    }

    public a a(int i) {
        ev aVar;
        ev evVar = (a) this.a;
        if (evVar == null) {
            aVar = new a();
        } else {
            this.a = evVar.f;
            evVar.f = null;
            aVar = evVar;
        }
        if (aVar.e < i) {
            aVar.a(i);
        }
        this.b = (a) ev.a(this.b, aVar);
        return aVar;
    }

    public void a() {
        this.b = (a) b(this.b);
    }

    public ShortBuffer b(int i) {
        a a = a(i * 2);
        if (a.b == null) {
            a.a.clear();
            a.b = a.a.asShortBuffer();
        } else {
            a.b.clear();
        }
        return a.b;
    }

    public FloatBuffer c(int i) {
        a a = a(i * 4);
        if (a.c == null) {
            a.a.clear();
            a.c = a.a.asFloatBuffer();
        } else {
            a.c.clear();
        }
        a.c.clear();
        return a.c;
    }
}
