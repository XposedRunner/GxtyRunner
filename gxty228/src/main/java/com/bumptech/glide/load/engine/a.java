package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.g.d;
import com.bumptech.glide.load.f;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: DecodeJob */
class a<A, T, Z> {
    private static final b a = new b();
    private final e b;
    private final int c;
    private final int d;
    private final com.bumptech.glide.load.a.c<A> e;
    private final com.bumptech.glide.e.b<A, T> f;
    private final f<T> g;
    private final com.bumptech.glide.load.resource.e.c<T, Z> h;
    private final a i;
    private final DiskCacheStrategy j;
    private final Priority k;
    private final b l;
    private volatile boolean m;

    /* compiled from: DecodeJob */
    interface a {
        com.bumptech.glide.load.engine.b.a a();
    }

    /* compiled from: DecodeJob */
    static class b {
        b() {
        }

        public OutputStream a(File file) throws FileNotFoundException {
            return new BufferedOutputStream(new FileOutputStream(file));
        }
    }

    /* compiled from: DecodeJob */
    class c<DataType> implements com.bumptech.glide.load.engine.b.a.b {
        final /* synthetic */ a a;
        private final com.bumptech.glide.load.a<DataType> b;
        private final DataType c;

        public c(a aVar, com.bumptech.glide.load.a<DataType> aVar2, DataType dataType) {
            this.a = aVar;
            this.b = aVar2;
            this.c = dataType;
        }

        public boolean a(File file) {
            boolean z = false;
            OutputStream outputStream = null;
            try {
                outputStream = this.a.l.a(file);
                z = this.b.a(this.c, outputStream);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Throwable e2) {
                if (Log.isLoggable("DecodeJob", 3)) {
                    Log.d("DecodeJob", "Failed to find file to write to disk cache", e2);
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (Throwable th) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e4) {
                    }
                }
            }
            return z;
        }
    }

    public a(e eVar, int i, int i2, com.bumptech.glide.load.a.c<A> cVar, com.bumptech.glide.e.b<A, T> bVar, f<T> fVar, com.bumptech.glide.load.resource.e.c<T, Z> cVar2, a aVar, DiskCacheStrategy diskCacheStrategy, Priority priority) {
        this(eVar, i, i2, cVar, bVar, fVar, cVar2, aVar, diskCacheStrategy, priority, a);
    }

    a(e eVar, int i, int i2, com.bumptech.glide.load.a.c<A> cVar, com.bumptech.glide.e.b<A, T> bVar, f<T> fVar, com.bumptech.glide.load.resource.e.c<T, Z> cVar2, a aVar, DiskCacheStrategy diskCacheStrategy, Priority priority, b bVar2) {
        this.b = eVar;
        this.c = i;
        this.d = i2;
        this.e = cVar;
        this.f = bVar;
        this.g = fVar;
        this.h = cVar2;
        this.i = aVar;
        this.j = diskCacheStrategy;
        this.k = priority;
        this.l = bVar2;
    }

    public i<Z> a() throws Exception {
        if (!this.j.cacheResult()) {
            return null;
        }
        long a = d.a();
        i a2 = a(this.b);
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Decoded transformed from cache", a);
        }
        long a3 = d.a();
        i<Z> d = d(a2);
        if (!Log.isLoggable("DecodeJob", 2)) {
            return d;
        }
        a("Transcoded transformed from cache", a3);
        return d;
    }

    public i<Z> b() throws Exception {
        if (!this.j.cacheSource()) {
            return null;
        }
        long a = d.a();
        i a2 = a(this.b.a());
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Decoded source from cache", a);
        }
        return a(a2);
    }

    public i<Z> c() throws Exception {
        return a(e());
    }

    public void d() {
        this.m = true;
        this.e.c();
    }

    private i<Z> a(i<T> iVar) {
        long a = d.a();
        i c = c(iVar);
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Transformed resource from source", a);
        }
        b(c);
        a = d.a();
        i<Z> d = d(c);
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Transcoded transformed from source", a);
        }
        return d;
    }

    private void b(i<T> iVar) {
        if (iVar != null && this.j.cacheResult()) {
            long a = d.a();
            this.i.a().a(this.b, new c(this, this.f.d(), iVar));
            if (Log.isLoggable("DecodeJob", 2)) {
                a("Wrote transformed from source to cache", a);
            }
        }
    }

    private i<T> e() throws Exception {
        try {
            long a = d.a();
            Object a2 = this.e.a(this.k);
            if (Log.isLoggable("DecodeJob", 2)) {
                a("Fetched data", a);
            }
            if (this.m) {
                return null;
            }
            i<T> a3 = a(a2);
            this.e.a();
            return a3;
        } finally {
            this.e.a();
        }
    }

    private i<T> a(A a) throws IOException {
        if (this.j.cacheSource()) {
            return b((Object) a);
        }
        long a2 = d.a();
        i<T> a3 = this.f.b().a(a, this.c, this.d);
        if (!Log.isLoggable("DecodeJob", 2)) {
            return a3;
        }
        a("Decoded from source", a2);
        return a3;
    }

    private i<T> b(A a) throws IOException {
        long a2 = d.a();
        this.i.a().a(this.b.a(), new c(this, this.f.c(), a));
        if (Log.isLoggable("DecodeJob", 2)) {
            a("Wrote source to cache", a2);
        }
        a2 = d.a();
        i<T> a3 = a(this.b.a());
        if (Log.isLoggable("DecodeJob", 2) && a3 != null) {
            a("Decoded source from cache", a2);
        }
        return a3;
    }

    private i<T> a(com.bumptech.glide.load.b bVar) throws IOException {
        i<T> iVar = null;
        File a = this.i.a().a(bVar);
        if (a != null) {
            try {
                iVar = this.f.a().a(a, this.c, this.d);
                if (iVar == null) {
                    this.i.a().b(bVar);
                }
            } catch (Throwable th) {
                if (iVar == null) {
                    this.i.a().b(bVar);
                }
            }
        }
        return iVar;
    }

    private i<T> c(i<T> iVar) {
        if (iVar == null) {
            return null;
        }
        i<T> a = this.g.a(iVar, this.c, this.d);
        if (iVar.equals(a)) {
            return a;
        }
        iVar.d();
        return a;
    }

    private i<Z> d(i<T> iVar) {
        if (iVar == null) {
            return null;
        }
        return this.h.a(iVar);
    }

    private void a(String str, long j) {
        Log.v("DecodeJob", str + " in " + d.a(j) + ", key: " + this.b);
    }
}
