package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: LoaderFactory */
public final class ag {
    private static final ag a = new ag();
    private static final ThreadFactory d = new b();
    private final Map<String, y> b = new HashMap();
    private final Map<String, a> c = new HashMap();
    private ExecutorService e = null;

    /* compiled from: LoaderFactory */
    class a {
        volatile boolean a = false;
        volatile boolean b = false;
        final /* synthetic */ ag c;

        a(ag agVar) {
            this.c = agVar;
        }
    }

    /* compiled from: LoaderFactory */
    static class b implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);

        b() {
        }

        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "amapD#" + this.a.getAndIncrement());
        }
    }

    private ag() {
    }

    public static ag b() {
        return a;
    }

    private static boolean b(dk dkVar) {
        return (dkVar == null || TextUtils.isEmpty(dkVar.b()) || TextUtils.isEmpty(dkVar.a())) ? false : true;
    }

    final a a(dk dkVar) {
        a aVar;
        synchronized (this.c) {
            if (b(dkVar)) {
                String a = dkVar.a();
                aVar = (a) this.c.get(a);
                if (aVar == null) {
                    try {
                        a aVar2 = new a(this);
                        try {
                            this.c.put(a, aVar2);
                            aVar = aVar2;
                        } catch (Throwable th) {
                            aVar = aVar2;
                        }
                    } catch (Throwable th2) {
                    }
                }
            } else {
                aVar = null;
            }
        }
        return aVar;
    }

    final y a(Context context, dk dkVar) throws Exception {
        if (!b(dkVar) || context == null) {
            return null;
        }
        y yVar;
        String a = dkVar.a();
        synchronized (this.b) {
            yVar = (y) this.b.get(a);
            if (yVar == null) {
                try {
                    y aeVar = new ae(context.getApplicationContext(), dkVar);
                    try {
                        this.b.put(a, aeVar);
                        ab.a(context, dkVar);
                        yVar = aeVar;
                    } catch (Throwable th) {
                        yVar = aeVar;
                    }
                } catch (Throwable th2) {
                }
            }
        }
        return yVar;
    }

    final ExecutorService a() {
        try {
            if (this.e == null || this.e.isShutdown()) {
                this.e = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(128), d);
            }
        } catch (Throwable th) {
        }
        return this.e;
    }
}
