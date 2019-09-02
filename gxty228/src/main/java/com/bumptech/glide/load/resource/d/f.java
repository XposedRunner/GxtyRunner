package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.c.b;
import com.bumptech.glide.load.resource.c.e;

/* compiled from: GifBitmapWrapperTransformation */
public class f implements com.bumptech.glide.load.f<a> {
    private final com.bumptech.glide.load.f<Bitmap> a;
    private final com.bumptech.glide.load.f<b> b;

    public f(c cVar, com.bumptech.glide.load.f<Bitmap> fVar) {
        this((com.bumptech.glide.load.f) fVar, new e(fVar, cVar));
    }

    f(com.bumptech.glide.load.f<Bitmap> fVar, com.bumptech.glide.load.f<b> fVar2) {
        this.a = fVar;
        this.b = fVar2;
    }

    public i<a> a(i<a> iVar, int i, int i2) {
        i b = ((a) iVar.b()).b();
        i c = ((a) iVar.b()).c();
        if (b != null && this.a != null) {
            i a = this.a.a(b, i, i2);
            if (b.equals(a)) {
                return iVar;
            }
            return new b(new a(a, ((a) iVar.b()).c()));
        } else if (c == null || this.b == null) {
            return iVar;
        } else {
            b = this.b.a(c, i, i2);
            if (c.equals(b)) {
                return iVar;
            }
            return new b(new a(((a) iVar.b()).b(), b));
        }
    }

    public String a() {
        return this.a.a();
    }
}
