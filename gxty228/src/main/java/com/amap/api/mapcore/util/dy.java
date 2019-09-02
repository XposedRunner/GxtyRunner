package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: NamedThreadFactory */
public class dy implements ThreadFactory {
    private static final AtomicInteger a = new AtomicInteger(1);
    private final AtomicInteger b;
    private final String c;
    private final boolean d;
    private final ThreadGroup e;

    public dy() {
        this("amap-threadpool-" + a.getAndIncrement(), false);
    }

    public dy(String str) {
        this(str, false);
    }

    public dy(String str, boolean z) {
        this.b = new AtomicInteger(1);
        this.c = TextUtils.isEmpty(str) ? "" : str + "-thread-";
        this.d = z;
        SecurityManager securityManager = System.getSecurityManager();
        this.e = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.e, runnable, this.c + this.b.getAndIncrement(), 0);
        thread.setDaemon(this.d);
        return thread;
    }
}
