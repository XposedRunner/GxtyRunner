package com.tencent.bugly.proguard;

import com.tencent.bugly.b;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: BUGLY */
public class am {
    private static final AtomicInteger a = new AtomicInteger(1);
    private static am b;
    private ScheduledExecutorService c;

    protected am() {
        this.c = null;
        this.c = Executors.newScheduledThreadPool(3, new ThreadFactory(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("BuglyThread-" + am.a.getAndIncrement());
                return thread;
            }
        });
        if (this.c == null || this.c.isShutdown()) {
            an.d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }

    public static synchronized am a() {
        am amVar;
        synchronized (am.class) {
            if (b == null) {
                b = new am();
            }
            amVar = b;
        }
        return amVar;
    }

    public synchronized boolean a(Runnable runnable, long j) {
        boolean z = false;
        synchronized (this) {
            if (!c()) {
                an.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            } else if (runnable == null) {
                an.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            } else {
                if (j <= 0) {
                    j = 0;
                }
                an.c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", Long.valueOf(j), runnable.getClass().getName());
                try {
                    this.c.schedule(runnable, j, TimeUnit.MILLISECONDS);
                    z = true;
                } catch (Throwable th) {
                    if (b.c) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public synchronized boolean a(Runnable runnable) {
        boolean z = false;
        synchronized (this) {
            if (!c()) {
                an.d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            } else if (runnable == null) {
                an.d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            } else {
                an.c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
                try {
                    this.c.execute(runnable);
                    z = true;
                } catch (Throwable th) {
                    if (b.c) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public synchronized void b() {
        if (!(this.c == null || this.c.isShutdown())) {
            an.c("[AsyncTaskHandler] Close async handler.", new Object[0]);
            this.c.shutdownNow();
        }
    }

    public synchronized boolean c() {
        boolean z;
        z = (this.c == null || this.c.isShutdown()) ? false : true;
        return z;
    }
}
