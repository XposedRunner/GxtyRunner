package com.bumptech.glide.request.b;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* compiled from: DrawableImageViewTarget */
public class c extends e<Drawable> {
    protected /* synthetic */ void a(Object obj) {
        d((Drawable) obj);
    }

    public c(ImageView imageView) {
        super(imageView);
    }

    protected void d(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }
}
