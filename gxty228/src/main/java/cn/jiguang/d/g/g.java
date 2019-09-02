package cn.jiguang.d.g;

import cn.jiguang.e.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class g {
    private static g e = new g();
    private ArrayList<WeakReference<ScheduledFuture<?>>> a = new ArrayList();
    private ExecutorService b = Executors.newSingleThreadExecutor();
    private ScheduledExecutorService c = Executors.newSingleThreadScheduledExecutor();
    private long d = 5;

    private g() {
    }

    public static g a() {
        return e;
    }

    public static void a(ExecutorService executorService) {
        d.a("ThreadUtil", "Action - shutdownExcutorService");
        if (executorService == null) {
            d.h("ThreadUtil", "executor was null");
            return;
        }
        try {
            executorService.shutdown();
            if (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                    d.a("ThreadUtil", "Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            d.a("ThreadUtil", "current thread is interrupted by self");
            Thread.currentThread().interrupt();
        }
        d.a("ThreadUtil", "Action - shutdownExcutorService - end");
    }

    public final void a(Runnable runnable) {
        if (this.b.isShutdown()) {
            this.b = Executors.newSingleThreadExecutor();
        }
        try {
            this.b.execute(runnable);
        } catch (RejectedExecutionException e) {
            d.i("ThreadUtil", "execute error:" + e);
        }
    }
}
