package com.bumptech.glide.request.a;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import com.bumptech.glide.request.a.c.a;

/* compiled from: DrawableCrossFadeViewAnimation */
public class b<T extends Drawable> implements c<T> {
    private final c<T> a;
    private final int b;

    public b(c<T> cVar, int i) {
        this.a = cVar;
        this.b = i;
    }

    public boolean a(T t, a aVar) {
        if (aVar.b() != null) {
            Drawable transitionDrawable = new TransitionDrawable(new Drawable[]{aVar.b(), t});
            transitionDrawable.setCrossFadeEnabled(true);
            transitionDrawable.startTransition(this.b);
            aVar.a(transitionDrawable);
            return true;
        }
        this.a.a(t, aVar);
        return false;
    }
}
