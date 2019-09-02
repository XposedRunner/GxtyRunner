package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.b.c;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;

/* compiled from: ProcessAndDisplayImageTask */
final class h implements Runnable {
    private final f a;
    private final Bitmap b;
    private final g c;
    private final Handler d;

    public h(f fVar, Bitmap bitmap, g gVar, Handler handler) {
        this.a = fVar;
        this.b = bitmap;
        this.c = gVar;
        this.d = handler;
    }

    public void run() {
        c.a("PostProcess image before displaying [%s]", this.c.b);
        LoadAndDisplayImageTask.a(new b(this.c.e.p().a(this.b), this.c, this.a, LoadedFrom.MEMORY_CACHE), this.c.e.s(), this.d, this.a);
    }
}
