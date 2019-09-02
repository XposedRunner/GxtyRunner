package com.bumptech.glide.request.a;

import android.view.View;
import android.view.animation.Animation;

/* compiled from: ViewAnimation */
public class f<R> implements c<R> {
    private final a a;

    /* compiled from: ViewAnimation */
    interface a {
        Animation a();
    }

    f(a aVar) {
        this.a = aVar;
    }

    public boolean a(R r, com.bumptech.glide.request.a.c.a aVar) {
        View a = aVar.a();
        if (a != null) {
            a.clearAnimation();
            a.startAnimation(this.a.a());
        }
        return false;
    }
}
