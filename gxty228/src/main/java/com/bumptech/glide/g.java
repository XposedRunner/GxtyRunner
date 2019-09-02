package com.bumptech.glide;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.manager.h;
import com.bumptech.glide.manager.l;
import com.bumptech.glide.manager.m;
import java.io.File;

/* compiled from: RequestManager */
public class g implements h {
    private final Context a;
    private final com.bumptech.glide.manager.g b;
    private final l c;
    private final m d;
    private final e e;
    private final c f;
    private a g;

    /* compiled from: RequestManager */
    public interface a {
        <T> void a(c<T, ?, ?, ?> cVar);
    }

    /* compiled from: RequestManager */
    public final class b<A, T> {
        final /* synthetic */ g a;
        private final com.bumptech.glide.load.b.l<A, T> b;
        private final Class<T> c;

        /* compiled from: RequestManager */
        public final class a {
            final /* synthetic */ b a;
            private final A b;
            private final Class<A> c;
            private final boolean d = true;

            a(b bVar, A a) {
                this.a = bVar;
                this.b = a;
                this.c = g.c((Object) a);
            }

            public <Z> d<A, T, Z> a(Class<Z> cls) {
                d<A, T, Z> dVar = (d) this.a.a.f.a(new d(this.a.a.a, this.a.a.e, this.c, this.a.b, this.a.c, cls, this.a.a.d, this.a.a.b, this.a.a.f));
                if (this.d) {
                    dVar.b(this.b);
                }
                return dVar;
            }
        }

        b(g gVar, com.bumptech.glide.load.b.l<A, T> lVar, Class<T> cls) {
            this.a = gVar;
            this.b = lVar;
            this.c = cls;
        }

        public a a(A a) {
            return new a(this, a);
        }
    }

    /* compiled from: RequestManager */
    class c {
        final /* synthetic */ g a;

        c(g gVar) {
            this.a = gVar;
        }

        public <A, X extends c<A, ?, ?, ?>> X a(X x) {
            if (this.a.g != null) {
                this.a.g.a(x);
            }
            return x;
        }
    }

    /* compiled from: RequestManager */
    private static class d implements com.bumptech.glide.manager.c.a {
        private final m a;

        public d(m mVar) {
            this.a = mVar;
        }

        public void a(boolean z) {
            if (z) {
                this.a.d();
            }
        }
    }

    public g(Context context, com.bumptech.glide.manager.g gVar, l lVar) {
        this(context, gVar, lVar, new m(), new com.bumptech.glide.manager.d());
    }

    g(Context context, final com.bumptech.glide.manager.g gVar, l lVar, m mVar, com.bumptech.glide.manager.d dVar) {
        this.a = context.getApplicationContext();
        this.b = gVar;
        this.c = lVar;
        this.d = mVar;
        this.e = e.a(context);
        this.f = new c(this);
        h a = dVar.a(context, new d(mVar));
        if (com.bumptech.glide.g.h.c()) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ g b;

                public void run() {
                    gVar.a(this.b);
                }
            });
        } else {
            gVar.a(this);
        }
        gVar.a(a);
    }

    public void a(int i) {
        this.e.a(i);
    }

    public void a() {
        this.e.e();
    }

    public void b() {
        com.bumptech.glide.g.h.a();
        this.d.a();
    }

    public void c() {
        com.bumptech.glide.g.h.a();
        this.d.b();
    }

    public void d() {
        c();
    }

    public void e() {
        b();
    }

    public void f() {
        this.d.c();
    }

    public <A, T> b<A, T> a(com.bumptech.glide.load.b.l<A, T> lVar, Class<T> cls) {
        return new b(this, lVar, cls);
    }

    public b<String> a(String str) {
        return (b) g().a((Object) str);
    }

    public b<String> g() {
        return a(String.class);
    }

    public b<File> a(File file) {
        return (b) h().a((Object) file);
    }

    public b<File> h() {
        return a(File.class);
    }

    public <T> b<T> a(T t) {
        return (b) a(c((Object) t)).a((Object) t);
    }

    private <T> b<T> a(Class<T> cls) {
        com.bumptech.glide.load.b.l a = e.a((Class) cls, this.a);
        com.bumptech.glide.load.b.l b = e.b((Class) cls, this.a);
        if (cls != null && a == null && b == null) {
            throw new IllegalArgumentException("Unknown type " + cls + ". You must provide a Model of a type for" + " which there is a registered ModelLoader, if you are using a custom model, you must first call" + " Glide#register with a ModelLoaderFactory for your custom model class");
        }
        return (b) this.f.a(new b(cls, a, b, this.a, this.e, this.d, this.b, this.f));
    }

    private static <T> Class<T> c(T t) {
        return t != null ? t.getClass() : null;
    }
}
