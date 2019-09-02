package com.bumptech.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.engine.b;
import com.bumptech.glide.load.engine.b.h;
import com.bumptech.glide.load.engine.c.a;
import com.bumptech.glide.load.resource.bitmap.g;
import com.bumptech.glide.load.resource.bitmap.i;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.load.resource.bitmap.m;
import com.bumptech.glide.load.resource.bitmap.n;
import com.bumptech.glide.load.resource.e.d;
import com.bumptech.glide.manager.k;
import com.bumptech.glide.request.b.f;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/* compiled from: Glide */
public class e {
    private static volatile e a;
    private static boolean b = true;
    private final c c;
    private final b d;
    private final com.bumptech.glide.load.engine.a.c e;
    private final h f;
    private final DecodeFormat g;
    private final f h = new f();
    private final d i = new d();
    private final com.bumptech.glide.e.c j;
    private final com.bumptech.glide.load.resource.bitmap.e k;
    private final com.bumptech.glide.load.resource.d.f l;
    private final i m;
    private final com.bumptech.glide.load.resource.d.f n;
    private final Handler o;
    private final a p;

    public static e a(Context context) {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    Context applicationContext = context.getApplicationContext();
                    f fVar = new f(applicationContext);
                    List<com.bumptech.glide.d.a> c = c(applicationContext);
                    for (com.bumptech.glide.d.a a : c) {
                        a.a(applicationContext, fVar);
                    }
                    a = fVar.a();
                    for (com.bumptech.glide.d.a a2 : c) {
                        a2.a(applicationContext, a);
                    }
                }
            }
        }
        return a;
    }

    private static List<com.bumptech.glide.d.a> c(Context context) {
        if (b) {
            return new com.bumptech.glide.d.b(context).a();
        }
        return Collections.emptyList();
    }

    e(b bVar, h hVar, com.bumptech.glide.load.engine.a.c cVar, Context context, DecodeFormat decodeFormat) {
        this.d = bVar;
        this.e = cVar;
        this.f = hVar;
        this.g = decodeFormat;
        this.c = new c(context);
        this.o = new Handler(Looper.getMainLooper());
        this.p = new a(hVar, cVar, decodeFormat);
        this.j = new com.bumptech.glide.e.c();
        com.bumptech.glide.e.b nVar = new n(cVar, decodeFormat);
        this.j.a(InputStream.class, Bitmap.class, nVar);
        com.bumptech.glide.e.b gVar = new g(cVar, decodeFormat);
        this.j.a(ParcelFileDescriptor.class, Bitmap.class, gVar);
        com.bumptech.glide.e.b mVar = new m(nVar, gVar);
        this.j.a(com.bumptech.glide.load.b.g.class, Bitmap.class, mVar);
        nVar = new com.bumptech.glide.load.resource.c.c(context, cVar);
        this.j.a(InputStream.class, com.bumptech.glide.load.resource.c.b.class, nVar);
        this.j.a(com.bumptech.glide.load.b.g.class, com.bumptech.glide.load.resource.d.a.class, new com.bumptech.glide.load.resource.d.g(mVar, nVar, cVar));
        this.j.a(InputStream.class, File.class, new com.bumptech.glide.load.resource.b.d());
        a(File.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.a.a());
        a(File.class, InputStream.class, new com.bumptech.glide.load.b.b.c.a());
        a(Integer.TYPE, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.c.a());
        a(Integer.TYPE, InputStream.class, new com.bumptech.glide.load.b.b.e.a());
        a(Integer.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.c.a());
        a(Integer.class, InputStream.class, new com.bumptech.glide.load.b.b.e.a());
        a(String.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.d.a());
        a(String.class, InputStream.class, new com.bumptech.glide.load.b.b.f.a());
        a(Uri.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.e.a());
        a(Uri.class, InputStream.class, new com.bumptech.glide.load.b.b.g.a());
        a(URL.class, InputStream.class, new com.bumptech.glide.load.b.b.h.a());
        a(com.bumptech.glide.load.b.d.class, InputStream.class, new com.bumptech.glide.load.b.b.a.a());
        a(byte[].class, InputStream.class, new com.bumptech.glide.load.b.b.b.a());
        this.i.a(Bitmap.class, j.class, new com.bumptech.glide.load.resource.e.b(context.getResources(), cVar));
        this.i.a(com.bumptech.glide.load.resource.d.a.class, com.bumptech.glide.load.resource.a.b.class, new com.bumptech.glide.load.resource.e.a(new com.bumptech.glide.load.resource.e.b(context.getResources(), cVar)));
        this.k = new com.bumptech.glide.load.resource.bitmap.e(cVar);
        this.l = new com.bumptech.glide.load.resource.d.f(cVar, this.k);
        this.m = new i(cVar);
        this.n = new com.bumptech.glide.load.resource.d.f(cVar, this.m);
    }

    public com.bumptech.glide.load.engine.a.c a() {
        return this.e;
    }

    <Z, R> com.bumptech.glide.load.resource.e.c<Z, R> a(Class<Z> cls, Class<R> cls2) {
        return this.i.a(cls, cls2);
    }

    <T, Z> com.bumptech.glide.e.b<T, Z> b(Class<T> cls, Class<Z> cls2) {
        return this.j.a(cls, cls2);
    }

    <R> com.bumptech.glide.request.b.j<R> a(ImageView imageView, Class<R> cls) {
        return this.h.a(imageView, cls);
    }

    b b() {
        return this.d;
    }

    com.bumptech.glide.load.resource.d.f c() {
        return this.l;
    }

    com.bumptech.glide.load.resource.d.f d() {
        return this.n;
    }

    private c f() {
        return this.c;
    }

    public void e() {
        com.bumptech.glide.g.h.a();
        this.f.a();
        this.e.a();
    }

    public void a(int i) {
        com.bumptech.glide.g.h.a();
        this.f.a(i);
        this.e.a(i);
    }

    public static void a(com.bumptech.glide.request.b.j<?> jVar) {
        com.bumptech.glide.g.h.a();
        com.bumptech.glide.request.a c = jVar.c();
        if (c != null) {
            c.d();
            jVar.a(null);
        }
    }

    public <T, Y> void a(Class<T> cls, Class<Y> cls2, com.bumptech.glide.load.b.m<T, Y> mVar) {
        com.bumptech.glide.load.b.m a = this.c.a((Class) cls, (Class) cls2, (com.bumptech.glide.load.b.m) mVar);
        if (a != null) {
            a.a();
        }
    }

    public static <T, Y> l<T, Y> a(Class<T> cls, Class<Y> cls2, Context context) {
        if (cls != null) {
            return a(context).f().a(cls, cls2);
        }
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Unable to load null model, setting placeholder only");
        }
        return null;
    }

    public static <T> l<T, InputStream> a(Class<T> cls, Context context) {
        return a((Class) cls, InputStream.class, context);
    }

    public static <T> l<T, ParcelFileDescriptor> b(Class<T> cls, Context context) {
        return a((Class) cls, ParcelFileDescriptor.class, context);
    }

    public static g b(Context context) {
        return k.a().a(context);
    }

    public static g a(Activity activity) {
        return k.a().a(activity);
    }

    public static g a(FragmentActivity fragmentActivity) {
        return k.a().a(fragmentActivity);
    }
}
