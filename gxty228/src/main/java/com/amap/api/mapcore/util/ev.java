package com.amap.api.mapcore.util;

/* compiled from: Inlist */
public class ev<T extends ev<T>> {
    public T f;

    public static <T extends ev<?>> T a(T t, T t2) {
        if (t2.f != null) {
            throw new IllegalArgumentException("'item' is a list");
        }
        t2.f = t;
        return t2;
    }
}
