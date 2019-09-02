package cn.jiguang.analytics.android.c.d;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public final class d {
    private static d e = new d();
    private ArrayList<WeakReference<ScheduledFuture<?>>> a = new ArrayList();
    private ExecutorService b = Executors.newSingleThreadExecutor();
    private ScheduledExecutorService c = Executors.newSingleThreadScheduledExecutor();
    private long d = 5;

    private d() {
    }

    public static d a() {
        return e;
    }

    public final void a(Runnable runnable) {
        if (this.b.isShutdown()) {
            this.b = Executors.newSingleThreadExecutor();
        }
        this.b.execute(runnable);
    }
}
