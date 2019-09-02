package com.bumptech.glide.load.resource.e;

import android.content.res.Resources;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.load.resource.bitmap.k;

/* compiled from: GlideBitmapDrawableTranscoder */
public class b implements c<Bitmap, j> {
    private final Resources a;
    private final c b;

    public b(Resources resources, c cVar) {
        this.a = resources;
        this.b = cVar;
    }

    public i<j> a(i<Bitmap> iVar) {
        return new k(new j(this.a, (Bitmap) iVar.b()), this.b);
    }

    public String a() {
        return "GlideBitmapDrawableTranscoder.com.bumptech.glide.load.resource.transcode";
    }
}
