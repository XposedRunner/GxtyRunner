package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.d.b;
import com.nostra13.universalimageloader.core.d.c;

/* compiled from: ImageLoader */
public class d {
    public static final String a = d.class.getSimpleName();
    private static volatile d e;
    private e b;
    private f c;
    private com.nostra13.universalimageloader.core.d.a d = new c();

    /* compiled from: ImageLoader */
    private static class a extends c {
        private Bitmap a;

        private a() {
        }

        public void a(String str, View view, Bitmap bitmap) {
            this.a = bitmap;
        }

        public Bitmap a() {
            return this.a;
        }
    }

    public static d a() {
        if (e == null) {
            synchronized (d.class) {
                if (e == null) {
                    e = new d();
                }
            }
        }
        return e;
    }

    protected d() {
    }

    public synchronized void a(e eVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        } else if (this.b == null) {
            com.nostra13.universalimageloader.b.c.a("Initialize ImageLoader with configuration", new Object[0]);
            this.c = new f(eVar);
            this.b = eVar;
        } else {
            com.nostra13.universalimageloader.b.c.c("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
        }
    }

    public void a(String str, com.nostra13.universalimageloader.core.c.a aVar, c cVar, com.nostra13.universalimageloader.core.d.a aVar2, b bVar) {
        b();
        if (aVar == null) {
            throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
        }
        com.nostra13.universalimageloader.core.d.a aVar3;
        c cVar2;
        if (aVar2 == null) {
            aVar3 = this.d;
        } else {
            aVar3 = aVar2;
        }
        if (cVar == null) {
            cVar2 = this.b.r;
        } else {
            cVar2 = cVar;
        }
        if (TextUtils.isEmpty(str)) {
            this.c.b(aVar);
            aVar3.a(str, aVar.d());
            if (cVar2.b()) {
                aVar.a(cVar2.b(this.b.a));
            } else {
                aVar.a(null);
            }
            aVar3.a(str, aVar.d(), null);
            return;
        }
        com.nostra13.universalimageloader.core.assist.c a = com.nostra13.universalimageloader.b.a.a(aVar, this.b.a());
        String a2 = com.nostra13.universalimageloader.b.d.a(str, a);
        this.c.a(aVar, a2);
        aVar3.a(str, aVar.d());
        Bitmap a3 = this.b.n.a(a2);
        if (a3 == null || a3.isRecycled()) {
            if (cVar2.a()) {
                aVar.a(cVar2.a(this.b.a));
            } else if (cVar2.g()) {
                aVar.a(null);
            }
            LoadAndDisplayImageTask loadAndDisplayImageTask = new LoadAndDisplayImageTask(this.c, new g(str, aVar, a, a2, cVar2, aVar3, bVar, this.c.a(str)), a(cVar2));
            if (cVar2.s()) {
                loadAndDisplayImageTask.run();
                return;
            } else {
                this.c.a(loadAndDisplayImageTask);
                return;
            }
        }
        com.nostra13.universalimageloader.b.c.a("Load image from memory cache [%s]", a2);
        if (cVar2.e()) {
            h hVar = new h(this.c, a3, new g(str, aVar, a, a2, cVar2, aVar3, bVar, this.c.a(str)), a(cVar2));
            if (cVar2.s()) {
                hVar.run();
                return;
            } else {
                this.c.a(hVar);
                return;
            }
        }
        cVar2.q().a(a3, aVar, LoadedFrom.MEMORY_CACHE);
        aVar3.a(str, aVar.d(), a3);
    }

    public void a(String str, ImageView imageView, c cVar) {
        a(str, new com.nostra13.universalimageloader.core.c.b(imageView), cVar, null, null);
    }

    public void a(String str, ImageView imageView, c cVar, com.nostra13.universalimageloader.core.d.a aVar) {
        a(str, imageView, cVar, aVar, null);
    }

    public void a(String str, ImageView imageView, c cVar, com.nostra13.universalimageloader.core.d.a aVar, b bVar) {
        a(str, new com.nostra13.universalimageloader.core.c.b(imageView), cVar, aVar, bVar);
    }

    public void a(String str, com.nostra13.universalimageloader.core.assist.c cVar, c cVar2, com.nostra13.universalimageloader.core.d.a aVar) {
        a(str, cVar, cVar2, aVar, null);
    }

    public void a(String str, com.nostra13.universalimageloader.core.assist.c cVar, c cVar2, com.nostra13.universalimageloader.core.d.a aVar, b bVar) {
        c cVar3;
        b();
        if (cVar == null) {
            cVar = this.b.a();
        }
        if (cVar2 == null) {
            cVar3 = this.b.r;
        } else {
            cVar3 = cVar2;
        }
        a(str, new com.nostra13.universalimageloader.core.c.c(str, cVar, ViewScaleType.CROP), cVar3, aVar, bVar);
    }

    public Bitmap a(String str) {
        return a(str, null, null);
    }

    public Bitmap a(String str, com.nostra13.universalimageloader.core.assist.c cVar, c cVar2) {
        if (cVar2 == null) {
            cVar2 = this.b.r;
        }
        c a = new com.nostra13.universalimageloader.core.c.a().a(cVar2).d(true).a();
        com.nostra13.universalimageloader.core.d.a aVar = new a();
        a(str, cVar, a, aVar);
        return aVar.a();
    }

    private void b() {
        if (this.b == null) {
            throw new IllegalStateException("ImageLoader must be init with configuration before using");
        }
    }

    private static Handler a(c cVar) {
        Handler r = cVar.r();
        if (cVar.s()) {
            return null;
        }
        if (r == null && Looper.myLooper() == Looper.getMainLooper()) {
            return new Handler();
        }
        return r;
    }
}
