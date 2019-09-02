package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.g.h;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;

/* compiled from: BitmapTransformation */
public abstract class d implements f<Bitmap> {
    private c a;

    protected abstract Bitmap a(c cVar, Bitmap bitmap, int i, int i2);

    public d(c cVar) {
        this.a = cVar;
    }

    public final i<Bitmap> a(i<Bitmap> iVar, int i, int i2) {
        if (h.a(i, i2)) {
            Bitmap bitmap = (Bitmap) iVar.b();
            if (i == Integer.MIN_VALUE) {
                i = bitmap.getWidth();
            }
            if (i2 == Integer.MIN_VALUE) {
                i2 = bitmap.getHeight();
            }
            Bitmap a = a(this.a, bitmap, i, i2);
            if (bitmap.equals(a)) {
                return iVar;
            }
            return c.a(a, this.a);
        }
        throw new IllegalArgumentException("Cannot apply transformation on width: " + i + " or height: " + i2 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
    }
}
