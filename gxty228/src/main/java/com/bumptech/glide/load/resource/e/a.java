package com.bumptech.glide.load.resource.e;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.load.resource.bitmap.j;

/* compiled from: GifBitmapWrapperDrawableTranscoder */
public class a implements c<com.bumptech.glide.load.resource.d.a, b> {
    private final c<Bitmap, j> a;

    public a(c<Bitmap, j> cVar) {
        this.a = cVar;
    }

    public i<b> a(i<com.bumptech.glide.load.resource.d.a> iVar) {
        com.bumptech.glide.load.resource.d.a aVar = (com.bumptech.glide.load.resource.d.a) iVar.b();
        i b = aVar.b();
        if (b != null) {
            return this.a.a(b);
        }
        return aVar.c();
    }

    public String a() {
        return "GifBitmapWrapperDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
