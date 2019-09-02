package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.engine.i;

/* compiled from: SimpleResource */
public class c<T> implements i<T> {
    protected final T a;

    public c(T t) {
        if (t == null) {
            throw new NullPointerException("Data must not be null");
        }
        this.a = t;
    }

    public final T b() {
        return this.a;
    }

    public final int c() {
        return 1;
    }

    public void d() {
    }
}
