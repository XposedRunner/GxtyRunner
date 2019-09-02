package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;

/* compiled from: AsyncExecutor */
final class ib implements ThreadFactory {
    private static String a = "";
    private static UncaughtExceptionHandler c = new UncaughtExceptionHandler() {
        public final void uncaughtException(Thread thread, Throwable th) {
        }
    };
    private String b;

    public ib(String str) {
        if (TextUtils.isEmpty(a)) {
            a = gf.b("DYNAMIC_SO_THREAD");
        }
        this.b = "DYNAMIC_SO_THREAD_" + null;
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setUncaughtExceptionHandler(c);
        thread.setName(this.b);
        return thread;
    }
}
