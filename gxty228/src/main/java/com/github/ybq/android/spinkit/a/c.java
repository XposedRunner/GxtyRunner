package com.github.ybq.android.spinkit.a;

import android.util.Property;

/* compiled from: IntProperty */
public abstract class c<T> extends Property<T, Integer> {
    public abstract void a(T t, int i);

    public /* synthetic */ void set(Object obj, Object obj2) {
        a(obj, (Integer) obj2);
    }

    public c(String str) {
        super(Integer.class, str);
    }

    public final void a(T t, Integer num) {
        a((Object) t, num.intValue());
    }
}
