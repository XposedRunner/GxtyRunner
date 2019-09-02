package com.bumptech.glide.request.b;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.request.a.c;
import com.bumptech.glide.request.a.c.a;

/* compiled from: ImageViewTarget */
public abstract class e<Z> extends k<ImageView, Z> implements a {
    protected abstract void a(Z z);

    public e(ImageView imageView) {
        super(imageView);
    }

    public Drawable b() {
        return ((ImageView) this.a).getDrawable();
    }

    public void a(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public void c(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public void a(Exception exception, Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public void b(Drawable drawable) {
        ((ImageView) this.a).setImageDrawable(drawable);
    }

    public void a(Z z, c<? super Z> cVar) {
        if (cVar == null || !cVar.a(z, this)) {
            a((Object) z);
        }
    }
}
