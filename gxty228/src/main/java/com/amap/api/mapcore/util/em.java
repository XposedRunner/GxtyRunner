package com.amap.api.mapcore.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

/* compiled from: ThreadUtil */
public class em {
    private static volatile em c;
    private BlockingQueue<Runnable> a = new LinkedBlockingQueue();
    private ExecutorService b = null;

    public static em a() {
        if (c == null) {
            synchronized (em.class) {
                if (c == null) {
                    c = new em();
                }
            }
        }
        return c;
    }

    public static void b() {
        if (c != null) {
            try {
                c.b.shutdownNow();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            c.b = null;
            c = null;
        }
    }

    private em() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        this.b = new ThreadPoolExecutor(availableProcessors, availableProcessors * 2, (long) 1, TimeUnit.SECONDS, this.a, new dy("AMapThreadUtil"), new AbortPolicy());
    }

    public void a(Runnable runnable) {
        if (this.b != null) {
            try {
                this.b.execute(runnable);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
