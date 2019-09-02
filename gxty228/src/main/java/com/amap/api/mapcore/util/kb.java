package com.amap.api.mapcore.util;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: ThreadPool */
public final class kb {
    private static kb a = null;
    private ExecutorService b;
    private ConcurrentHashMap<kc, Future<?>> c = new ConcurrentHashMap();
    private a d = new a(this) {
        final /* synthetic */ kb a;

        {
            this.a = r1;
        }

        public void a(kc kcVar) {
        }

        public void b(kc kcVar) {
            this.a.a(kcVar, false);
        }

        public void c(kc kcVar) {
            this.a.a(kcVar, true);
        }
    };

    public static synchronized kb a(int i) {
        kb kbVar;
        synchronized (kb.class) {
            if (a == null) {
                a = new kb(i);
            }
            kbVar = a;
        }
        return kbVar;
    }

    private kb(int i) {
        try {
            this.b = new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(256));
        } catch (Throwable th) {
            gz.c(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    public void a(kc kcVar) throws gp {
        try {
            if (!b(kcVar) && this.b != null && !this.b.isShutdown()) {
                kcVar.d = this.d;
                Future submit = this.b.submit(kcVar);
                if (submit != null) {
                    a(kcVar, submit);
                }
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
            gz.c(th, "TPool", "addTask");
            gp gpVar = new gp("thread pool has exception");
        }
    }

    public static synchronized void a() {
        synchronized (kb.class) {
            try {
                if (a != null) {
                    a.b();
                    a = null;
                }
            } catch (Throwable th) {
                gz.c(th, "TPool", "onDestroy");
                th.printStackTrace();
            }
        }
    }

    private void b() {
        try {
            for (Entry key : this.c.entrySet()) {
                Future future = (Future) this.c.get((kc) key.getKey());
                if (future != null) {
                    future.cancel(true);
                }
            }
            this.c.clear();
            this.b.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            gz.c(th, "TPool", "destroy");
            th.printStackTrace();
        }
    }

    private synchronized boolean b(kc kcVar) {
        boolean z;
        z = false;
        try {
            z = this.c.containsKey(kcVar);
        } catch (Throwable th) {
            gz.c(th, "TPool", "contain");
            th.printStackTrace();
        }
        return z;
    }

    private synchronized void a(kc kcVar, Future<?> future) {
        try {
            this.c.put(kcVar, future);
        } catch (Throwable th) {
            gz.c(th, "TPool", "addQueue");
            th.printStackTrace();
        }
    }

    private synchronized void a(kc kcVar, boolean z) {
        try {
            Future future = (Future) this.c.remove(kcVar);
            if (z && future != null) {
                future.cancel(true);
            }
        } catch (Throwable th) {
            gz.c(th, "TPool", "removeQueue");
            th.printStackTrace();
        }
    }
}
