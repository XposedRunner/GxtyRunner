package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.d;

class EngineRunnable implements com.bumptech.glide.load.engine.executor.a, Runnable {
    private final Priority a;
    private final a b;
    private final a<?, ?, ?> c;
    private Stage d = Stage.CACHE;
    private volatile boolean e;

    private enum Stage {
        CACHE,
        SOURCE
    }

    interface a extends d {
        void b(EngineRunnable engineRunnable);
    }

    public EngineRunnable(a aVar, a<?, ?, ?> aVar2, Priority priority) {
        this.b = aVar;
        this.c = aVar2;
        this.a = priority;
    }

    public void a() {
        this.e = true;
        this.c.d();
    }

    public void run() {
        Object obj;
        Exception exception = null;
        if (!this.e) {
            i d;
            try {
                d = d();
            } catch (Throwable e) {
                if (Log.isLoggable("EngineRunnable", 2)) {
                    Log.v("EngineRunnable", "Out Of Memory Error decoding", e);
                }
                exception = new ErrorWrappingGlideException(e);
                obj = null;
            } catch (Throwable e2) {
                if (Log.isLoggable("EngineRunnable", 2)) {
                    Log.v("EngineRunnable", "Exception decoding", e2);
                }
                Throwable th = e2;
                obj = null;
            }
            if (this.e) {
                if (d != null) {
                    d.d();
                }
            } else if (d == null) {
                a(exception);
            } else {
                a(d);
            }
        }
    }

    private boolean c() {
        return this.d == Stage.CACHE;
    }

    private void a(i iVar) {
        this.b.a(iVar);
    }

    private void a(Exception exception) {
        if (c()) {
            this.d = Stage.SOURCE;
            this.b.b(this);
            return;
        }
        this.b.a(exception);
    }

    private i<?> d() throws Exception {
        if (c()) {
            return e();
        }
        return f();
    }

    private i<?> e() throws Exception {
        i<?> a;
        try {
            a = this.c.a();
        } catch (Exception e) {
            if (Log.isLoggable("EngineRunnable", 3)) {
                Log.d("EngineRunnable", "Exception decoding result from cache: " + e);
            }
            a = null;
        }
        if (a == null) {
            return this.c.b();
        }
        return a;
    }

    private i<?> f() throws Exception {
        return this.c.c();
    }

    public int b() {
        return this.a.ordinal();
    }
}
