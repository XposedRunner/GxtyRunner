package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.c.b;

/* compiled from: GifBitmapWrapper */
public class a {
    private final i<b> a;
    private final i<Bitmap> b;

    public a(i<Bitmap> iVar, i<b> iVar2) {
        if (iVar != null && iVar2 != null) {
            throw new IllegalArgumentException("Can only contain either a bitmap resource or a gif resource, not both");
        } else if (iVar == null && iVar2 == null) {
            throw new IllegalArgumentException("Must contain either a bitmap resource or a gif resource");
        } else {
            this.b = iVar;
            this.a = iVar2;
        }
    }

    public int a() {
        if (this.b != null) {
            return this.b.c();
        }
        return this.a.c();
    }

    public i<Bitmap> b() {
        return this.b;
    }

    public i<b> c() {
        return this.a;
    }
}
