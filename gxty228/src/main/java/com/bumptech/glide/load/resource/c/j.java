package com.bumptech.glide.load.resource.c;

import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.b.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.a.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;
import java.io.OutputStream;

/* compiled from: GifResourceEncoder */
public class j implements e<b> {
    private static final a a = new a();
    private final com.bumptech.glide.b.a.a b;
    private final c c;
    private final a d;

    /* compiled from: GifResourceEncoder */
    static class a {
        a() {
        }

        public com.bumptech.glide.b.a a(com.bumptech.glide.b.a.a aVar) {
            return new com.bumptech.glide.b.a(aVar);
        }

        public d a() {
            return new d();
        }

        public com.bumptech.glide.c.a b() {
            return new com.bumptech.glide.c.a();
        }

        public i<Bitmap> a(Bitmap bitmap, c cVar) {
            return new com.bumptech.glide.load.resource.bitmap.c(bitmap, cVar);
        }
    }

    public j(c cVar) {
        this(cVar, a);
    }

    j(c cVar, a aVar) {
        this.c = cVar;
        this.b = new a(cVar);
        this.d = aVar;
    }

    public boolean a(i<b> iVar, OutputStream outputStream) {
        long a = com.bumptech.glide.g.d.a();
        b bVar = (b) iVar.b();
        f c = bVar.c();
        if (c instanceof com.bumptech.glide.load.resource.d) {
            return a(bVar.d(), outputStream);
        }
        com.bumptech.glide.b.a a2 = a(bVar.d());
        com.bumptech.glide.c.a b = this.d.b();
        if (!b.a(outputStream)) {
            return false;
        }
        int i = 0;
        while (i < a2.c()) {
            i a3 = a(a2.f(), c, bVar);
            if (b.a((Bitmap) a3.b())) {
                try {
                    b.a(a2.a(a2.d()));
                    a2.a();
                    a3.d();
                    i++;
                } catch (Throwable th) {
                    a3.d();
                    throw th;
                }
            }
            a3.d();
            return false;
        }
        boolean a4 = b.a();
        if (!Log.isLoggable("GifEncoder", 2)) {
            return a4;
        }
        Log.v("GifEncoder", "Encoded gif with " + a2.c() + " frames and " + bVar.d().length + " bytes in " + com.bumptech.glide.g.d.a(a) + " ms");
        return a4;
    }

    private boolean a(byte[] bArr, OutputStream outputStream) {
        try {
            outputStream.write(bArr);
            return true;
        } catch (Throwable e) {
            if (Log.isLoggable("GifEncoder", 3)) {
                Log.d("GifEncoder", "Failed to write data to output stream in GifResourceEncoder", e);
            }
            return false;
        }
    }

    private com.bumptech.glide.b.a a(byte[] bArr) {
        d a = this.d.a();
        a.a(bArr);
        com.bumptech.glide.b.c b = a.b();
        com.bumptech.glide.b.a a2 = this.d.a(this.b);
        a2.a(b, bArr);
        a2.a();
        return a2;
    }

    private i<Bitmap> a(Bitmap bitmap, f<Bitmap> fVar, b bVar) {
        i a = this.d.a(bitmap, this.c);
        i<Bitmap> a2 = fVar.a(a, bVar.getIntrinsicWidth(), bVar.getIntrinsicHeight());
        if (!a.equals(a2)) {
            a.d();
        }
        return a2;
    }

    public String a() {
        return "";
    }
}
