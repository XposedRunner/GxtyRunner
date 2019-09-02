package com.github.ybq.android.spinkit.a;

import android.util.Property;

/* compiled from: FloatProperty */
public abstract class b<T> extends Property<T, Float> {
    public abstract void a(T t, float f);

    public /* synthetic */ void set(Object obj, Object obj2) {
        a(obj, (Float) obj2);
    }

    public b(String str) {
        super(Float.class, str);
    }

    public final void a(T t, Float f) {
        a((Object) t, f.floatValue());
    }
}
