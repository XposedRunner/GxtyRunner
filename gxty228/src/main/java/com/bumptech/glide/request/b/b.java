package com.bumptech.glide.request.b;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* compiled from: BitmapImageViewTarget */
public class b extends e<Bitmap> {
    public b(ImageView imageView) {
        super(imageView);
    }

    protected void a(Bitmap bitmap) {
        ((ImageView) this.a).setImageBitmap(bitmap);
    }
}
