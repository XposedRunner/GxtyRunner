package com.amap.api.mapcore.util;

/* compiled from: AbstractPool */
public abstract class eu<T extends ev<?>> {
    protected T a;

    protected boolean a(T t) {
        return true;
    }

    public T b(T t) {
        if (t != null) {
            while (t != null) {
                T t2 = t.f;
                a(t);
                t.f = this.a;
                this.a = t;
                t = t2;
            }
        }
        return null;
    }
}
