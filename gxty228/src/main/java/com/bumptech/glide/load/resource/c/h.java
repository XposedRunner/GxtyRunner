package com.bumptech.glide.load.resource.c;

import android.graphics.Bitmap;
import com.bumptech.glide.b.a;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.i;

/* compiled from: GifFrameResourceDecoder */
class h implements d<a, Bitmap> {
    private final c a;

    public h(c cVar) {
        this.a = cVar;
    }

    public i<Bitmap> a(a aVar, int i, int i2) {
        return com.bumptech.glide.load.resource.bitmap.c.a(aVar.f(), this.a);
    }

    public String a() {
        return "GifFrameResourceDecoder.com.bumptech.glide.load.resource.gif";
    }
}
