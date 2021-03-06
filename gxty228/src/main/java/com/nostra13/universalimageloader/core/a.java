package com.nostra13.universalimageloader.core;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import com.nostra13.universalimageloader.a.a.b.b;
import com.nostra13.universalimageloader.b.c;
import com.nostra13.universalimageloader.b.e;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.deque.LIFOLinkedBlockingDeque;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: DefaultConfigurationFactory */
public class a {

    /* compiled from: DefaultConfigurationFactory */
    private static class a implements ThreadFactory {
        private static final AtomicInteger a = new AtomicInteger(1);
        private final ThreadGroup b;
        private final AtomicInteger c = new AtomicInteger(1);
        private final String d;
        private final int e;

        a(int i, String str) {
            this.e = i;
            this.b = Thread.currentThread().getThreadGroup();
            this.d = str + a.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.b, runnable, this.d + this.c.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.e);
            return thread;
        }
    }

    public static Executor a(int i, int i2, QueueProcessingType queueProcessingType) {
        return new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, (queueProcessingType == QueueProcessingType.LIFO ? 1 : null) != null ? new LIFOLinkedBlockingDeque() : new LinkedBlockingQueue(), a(i2, "uil-pool-"));
    }

    public static Executor a() {
        return Executors.newCachedThreadPool(a(5, "uil-pool-d-"));
    }

    public static com.nostra13.universalimageloader.a.a.b.a b() {
        return new b();
    }

    public static com.nostra13.universalimageloader.a.a.a a(Context context, com.nostra13.universalimageloader.a.a.b.a aVar, long j, int i) {
        File b = b(context);
        if (j > 0 || i > 0) {
            try {
                return new com.nostra13.universalimageloader.a.a.a.a.b(e.b(context), b, aVar, j, i);
            } catch (Throwable e) {
                c.a(e);
            }
        }
        return new com.nostra13.universalimageloader.a.a.a.b(e.a(context), b, aVar);
    }

    private static File b(Context context) {
        File a = e.a(context, false);
        File file = new File(a, "uil-images");
        if (file.exists() || file.mkdir()) {
            return file;
        }
        return a;
    }

    public static com.nostra13.universalimageloader.a.b.a a(Context context, int i) {
        if (i == 0) {
            int a;
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            int memoryClass = activityManager.getMemoryClass();
            if (d() && c(context)) {
                a = a(activityManager);
            } else {
                a = memoryClass;
            }
            i = (a * 1048576) / 8;
        }
        return new com.nostra13.universalimageloader.a.b.a.b(i);
    }

    private static boolean d() {
        return VERSION.SDK_INT >= 11;
    }

    @TargetApi(11)
    private static boolean c(Context context) {
        return (context.getApplicationInfo().flags & 1048576) != 0;
    }

    @TargetApi(11)
    private static int a(ActivityManager activityManager) {
        return activityManager.getLargeMemoryClass();
    }

    public static ImageDownloader a(Context context) {
        return new com.nostra13.universalimageloader.core.download.a(context);
    }

    public static com.nostra13.universalimageloader.core.a.b a(boolean z) {
        return new com.nostra13.universalimageloader.core.a.a(z);
    }

    public static com.nostra13.universalimageloader.core.b.a c() {
        return new com.nostra13.universalimageloader.core.b.b();
    }

    private static ThreadFactory a(int i, String str) {
        return new a(i, str);
    }
}
