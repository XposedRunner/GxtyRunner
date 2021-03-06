package com.bumptech.glide.load.resource.a;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.i;

/* compiled from: DrawableResource */
public abstract class a<T extends Drawable> implements i<T> {
    protected final T a;

    public /* synthetic */ Object b() {
        return a();
    }

    public a(T t) {
        if (t == null) {
            throw new NullPointerException("Drawable must not be null!");
        }
        this.a = t;
    }

    public final T a() {
        return this.a.getConstantState().newDrawable();
    }
}
