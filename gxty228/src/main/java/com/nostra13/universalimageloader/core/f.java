package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.c.a;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: ImageLoaderEngine */
class f {
    final e a;
    private Executor b;
    private Executor c;
    private Executor d;
    private final Map<Integer, String> e = Collections.synchronizedMap(new HashMap());
    private final Map<String, ReentrantLock> f = new WeakHashMap();
    private final AtomicBoolean g = new AtomicBoolean(false);
    private final AtomicBoolean h = new AtomicBoolean(false);
    private final AtomicBoolean i = new AtomicBoolean(false);
    private final Object j = new Object();

    f(e eVar) {
        this.a = eVar;
        this.b = eVar.g;
        this.c = eVar.h;
        this.d = a.a();
    }

    void a(final LoadAndDisplayImageTask loadAndDisplayImageTask) {
        this.d.execute(new Runnable(this) {
            final /* synthetic */ f b;

            public void run() {
                File a = this.b.a.o.a(loadAndDisplayImageTask.a());
                Object obj = (a == null || !a.exists()) ? null : 1;
                this.b.e();
                if (obj != null) {
                    this.b.c.execute(loadAndDisplayImageTask);
                } else {
                    this.b.b.execute(loadAndDisplayImageTask);
                }
            }
        });
    }

    void a(h hVar) {
        e();
        this.c.execute(hVar);
    }

    private void e() {
        if (!this.a.i && ((ExecutorService) this.b).isShutdown()) {
            this.b = f();
        }
        if (!this.a.j && ((ExecutorService) this.c).isShutdown()) {
            this.c = f();
        }
    }

    private Executor f() {
        return a.a(this.a.k, this.a.l, this.a.m);
    }

    String a(a aVar) {
        return (String) this.e.get(Integer.valueOf(aVar.f()));
    }

    void a(a aVar, String str) {
        this.e.put(Integer.valueOf(aVar.f()), str);
    }

    void b(a aVar) {
        this.e.remove(Integer.valueOf(aVar.f()));
    }

    void a(Runnable runnable) {
        this.d.execute(runnable);
    }

    ReentrantLock a(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.f.get(str);
        if (reentrantLock != null) {
            return reentrantLock;
        }
        reentrantLock = new ReentrantLock();
        this.f.put(str, reentrantLock);
        return reentrantLock;
    }

    AtomicBoolean a() {
        return this.g;
    }

    Object b() {
        return this.j;
    }

    boolean c() {
        return this.h.get();
    }

    boolean d() {
        return this.i.get();
    }
}
