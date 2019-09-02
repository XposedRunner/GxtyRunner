package com.bumptech.glide.request.b;

import android.widget.ImageView;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.a.c;

/* compiled from: GlideDrawableImageViewTarget */
public class d extends e<b> {
    private int b;
    private b c;

    public d(ImageView imageView) {
        this(imageView, -1);
    }

    public d(ImageView imageView, int i) {
        super(imageView);
        this.b = i;
    }

    public void a(b bVar, c<? super b> cVar) {
        Object iVar;
        if (!bVar.a()) {
            float intrinsicWidth = ((float) bVar.getIntrinsicWidth()) / ((float) bVar.getIntrinsicHeight());
            if (Math.abs((((float) ((ImageView) this.a).getWidth()) / ((float) ((ImageView) this.a).getHeight())) - 1.0f) <= 0.05f && Math.abs(intrinsicWidth - 1.0f) <= 0.05f) {
                iVar = new i(bVar, ((ImageView) this.a).getWidth());
            }
        }
        super.a(iVar, (c) cVar);
        this.c = iVar;
        iVar.a(this.b);
        iVar.start();
    }

    protected void a(b bVar) {
        ((ImageView) this.a).setImageDrawable(bVar);
    }

    public void d() {
        if (this.c != null) {
            this.c.start();
        }
    }

    public void e() {
        if (this.c != null) {
            this.c.stop();
        }
    }
}
