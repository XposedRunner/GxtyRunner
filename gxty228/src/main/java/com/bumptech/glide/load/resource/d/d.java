package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.resource.c.b;
import java.io.OutputStream;

/* compiled from: GifBitmapWrapperResourceEncoder */
public class d implements e<a> {
    private final e<Bitmap> a;
    private final e<b> b;
    private String c;

    public d(e<Bitmap> eVar, e<b> eVar2) {
        this.a = eVar;
        this.b = eVar2;
    }

    public boolean a(i<a> iVar, OutputStream outputStream) {
        a aVar = (a) iVar.b();
        i b = aVar.b();
        if (b != null) {
            return this.a.a(b, outputStream);
        }
        return this.b.a(aVar.c(), outputStream);
    }

    public String a() {
        if (this.c == null) {
            this.c = this.a.a() + this.b.a();
        }
        return this.c;
    }
}
