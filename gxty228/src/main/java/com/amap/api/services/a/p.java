package com.amap.api.services.a;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SDKLogHandler */
public class p extends n implements UncaughtExceptionHandler {
    private static Set<Integer> d = Collections.synchronizedSet(new HashSet());
    private static final ThreadFactory e = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "pama#" + this.a.getAndIncrement());
        }
    };
    private Context c;

    protected void a(Throwable th, int i, String str, String str2) {
        q.a(this.c, th, i, str, str2);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            a(th, 0, null, null);
            if (this.b != null) {
                try {
                    Thread.setDefaultUncaughtExceptionHandler(this.b);
                } catch (Throwable th2) {
                }
                this.b.uncaughtException(thread, th);
            }
        }
    }

    public void b(Throwable th, String str, String str2) {
        if (th != null) {
            try {
                a(th, 1, str, str2);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    public static void c(Throwable th, String str, String str2) {
        try {
            if (n.a != null) {
                n.a.a(th, 1, str, str2);
            }
        } catch (Throwable th2) {
        }
    }

    public static synchronized p a() {
        p pVar;
        synchronized (p.class) {
            pVar = (p) n.a;
        }
        return pVar;
    }
}
