package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.b.c;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.c.a;

/* compiled from: DisplayBitmapTask */
final class b implements Runnable {
    private final Bitmap a;
    private final String b;
    private final a c;
    private final String d;
    private final com.nostra13.universalimageloader.core.b.a e;
    private final com.nostra13.universalimageloader.core.d.a f;
    private final f g;
    private final LoadedFrom h;

    public b(Bitmap bitmap, g gVar, f fVar, LoadedFrom loadedFrom) {
        this.a = bitmap;
        this.b = gVar.a;
        this.c = gVar.c;
        this.d = gVar.b;
        this.e = gVar.e.q();
        this.f = gVar.f;
        this.g = fVar;
        this.h = loadedFrom;
    }

    public void run() {
        if (this.c.e()) {
            c.a("ImageAware was collected by GC. Task is cancelled. [%s]", this.d);
            this.f.b(this.b, this.c.d());
        } else if (a()) {
            c.a("ImageAware is reused for another image. Task is cancelled. [%s]", this.d);
            this.f.b(this.b, this.c.d());
        } else {
            c.a("Display image in ImageAware (loaded from %1$s) [%2$s]", this.h, this.d);
            this.e.a(this.a, this.c, this.h);
            this.g.b(this.c);
            this.f.a(this.b, this.c.d(), this.a);
        }
    }

    private boolean a() {
        return !this.d.equals(this.g.a(this.c));
    }
}
