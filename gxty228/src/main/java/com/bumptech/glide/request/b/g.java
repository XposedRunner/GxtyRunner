package com.bumptech.glide.request.b;

import com.bumptech.glide.g.h;

/* compiled from: SimpleTarget */
public abstract class g<Z> extends a<Z> {
    private final int a;
    private final int b;

    public g() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public g(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final void a(h hVar) {
        if (h.a(this.a, this.b)) {
            hVar.a(this.a, this.b);
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + this.a + " and height: " + this.b + ", either provide dimensions in the constructor" + " or call override()");
    }
}
