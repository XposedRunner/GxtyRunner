package com.amap.api.mapcore.util;

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
public class hv {
    private static final hv a = new hv();
    private static final ThreadFactory d = new b();
    private final Map<String, ho> b = new HashMap();
    private final Map<String, a> c = new HashMap();
    private ExecutorService e = null;

    /* compiled from: LoaderFactory */
    class a {
        volatile boolean a = false;
        volatile boolean b = false;
        final /* synthetic */ hv c;

        a(hv hvVar) {
            this.c = hvVar;
        }
    }

    /* compiled from: LoaderFactory */
    static class b implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);

        b() {
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "amapD#" + this.a.getAndIncrement());
        }
    }

    private hv() {
    }

    ExecutorService a() {
        try {
            if (this.e == null || this.e.isShutdown()) {
                this.e = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(128), d);
            }
        } catch (Throwable th) {
        }
        return this.e;
    }

    public static hv b() {
        return a;
    }

    a a(gk gkVar) {
        a aVar;
        synchronized (this.c) {
            if (b(gkVar)) {
                String a = gkVar.a();
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

    ho a(Context context, gk gkVar) throws Exception {
        if (!b(gkVar) || context == null) {
            return null;
        }
        ho hoVar;
        String a = gkVar.a();
        synchronized (this.b) {
            hoVar = (ho) this.b.get(a);
            if (hoVar == null) {
                try {
                    ho htVar = new ht(context.getApplicationContext(), gkVar, true);
                    try {
                        this.b.put(a, htVar);
                        hr.a(context, gkVar);
                        hoVar = htVar;
                    } catch (Throwable th) {
                        hoVar = htVar;
                    }
                } catch (Throwable th2) {
                }
            }
        }
        return hoVar;
    }

    private boolean b(gk gkVar) {
        if (gkVar == null || TextUtils.isEmpty(gkVar.b()) || TextUtils.isEmpty(gkVar.a())) {
            return false;
        }
        return true;
    }
}
