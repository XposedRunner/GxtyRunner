package com.blankj.utilcode.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.tencent.connect.common.Constants;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class ThreadUtils {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final Map<Task, ScheduledExecutorService> TASK_SCHEDULED = new ConcurrentHashMap();
    private static final byte TYPE_CACHED = (byte) -2;
    private static final byte TYPE_CPU = (byte) -8;
    private static final byte TYPE_IO = (byte) -4;
    private static final Map<Integer, Map<Integer, ExecutorService>> TYPE_PRIORITY_POOLS = new ConcurrentHashMap();
    private static final byte TYPE_SINGLE = (byte) -1;

    private static class Deliver {
        private static final Handler MAIN_HANDLER;

        private Deliver() {
        }

        static {
            Looper mainLooper;
            try {
                mainLooper = Looper.getMainLooper();
            } catch (Exception e) {
                mainLooper = null;
            }
            if (mainLooper != null) {
                MAIN_HANDLER = new Handler(mainLooper);
            } else {
                MAIN_HANDLER = null;
            }
        }

        static void post(Runnable runnable) {
            if (MAIN_HANDLER != null) {
                MAIN_HANDLER.post(runnable);
            } else {
                runnable.run();
            }
        }
    }

    public static abstract class Task<T> implements Runnable {
        private static final int CANCELLED = 2;
        private static final int COMPLETING = 1;
        private static final int EXCEPTIONAL = 3;
        private static final int NEW = 0;
        private boolean isSchedule;
        private volatile int state = 0;

        @Nullable
        public abstract T doInBackground() throws Throwable;

        public abstract void onCancel();

        public abstract void onFail(Throwable th);

        public abstract void onSuccess(@Nullable T t);

        public void run() {
            try {
                final Object doInBackground = doInBackground();
                if (this.state == 0) {
                    if (this.isSchedule) {
                        Deliver.post(new Runnable() {
                            public void run() {
                                Task.this.onSuccess(doInBackground);
                            }
                        });
                        return;
                    }
                    this.state = 1;
                    Deliver.post(new Runnable() {
                        public void run() {
                            Task.this.onSuccess(doInBackground);
                            ThreadUtils.removeScheduleByTask(Task.this);
                        }
                    });
                }
            } catch (final Throwable th) {
                if (this.state == 0) {
                    this.state = 3;
                    Deliver.post(new Runnable() {
                        public void run() {
                            Task.this.onFail(th);
                            ThreadUtils.removeScheduleByTask(Task.this);
                        }
                    });
                }
            }
        }

        public void cancel() {
            if (this.state == 0) {
                this.state = 2;
                Deliver.post(new Runnable() {
                    public void run() {
                        Task.this.onCancel();
                        ThreadUtils.removeScheduleByTask(Task.this);
                    }
                });
            }
        }
    }

    public static abstract class SimpleTask<T> extends Task<T> {
        public void onCancel() {
            Log.e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        public void onFail(Throwable th) {
            Log.e("ThreadUtils", "onFail: ", th);
        }
    }

    private static final class UtilsThreadFactory extends AtomicLong implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final int priority;

        UtilsThreadFactory(String str, int i) {
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = str + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
            this.priority = i;
        }

        public Thread newThread(@NonNull Runnable runnable) {
            if (runnable == null) {
                throw new NullPointerException("Argument 'r' of type Runnable (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            Thread anonymousClass1 = new Thread(this.group, runnable, this.namePrefix + getAndIncrement(), 0) {
                public void run() {
                    try {
                        super.run();
                    } catch (Throwable th) {
                        Log.e("ThreadUtils", "Request threw uncaught throwable", th);
                    }
                }
            };
            if (anonymousClass1.isDaemon()) {
                anonymousClass1.setDaemon(false);
            }
            anonymousClass1.setPriority(this.priority);
            return anonymousClass1;
        }
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static ExecutorService getFixedPool(@IntRange(from = 1) int i) {
        return getPoolByTypeAndPriority(i);
    }

    public static ExecutorService getFixedPool(@IntRange(from = 1) int i, @IntRange(from = 1, to = 10) int i2) {
        return getPoolByTypeAndPriority(i, i2);
    }

    public static ExecutorService getSinglePool() {
        return getPoolByTypeAndPriority(-1);
    }

    public static ExecutorService getSinglePool(@IntRange(from = 1, to = 10) int i) {
        return getPoolByTypeAndPriority(-1, i);
    }

    public static ExecutorService getCachedPool() {
        return getPoolByTypeAndPriority(-2);
    }

    public static ExecutorService getCachedPool(@IntRange(from = 1, to = 10) int i) {
        return getPoolByTypeAndPriority(-2, i);
    }

    public static ExecutorService getIoPool() {
        return getPoolByTypeAndPriority(-2);
    }

    public static ExecutorService getIoPool(@IntRange(from = 1, to = 10) int i) {
        return getPoolByTypeAndPriority(-2, i);
    }

    public static ExecutorService getCpuPool() {
        return getPoolByTypeAndPriority(-8);
    }

    public static ExecutorService getCpuPool(@IntRange(from = 1, to = 10) int i) {
        return getPoolByTypeAndPriority(-8, i);
    }

    public static <T> void executeByFixed(@IntRange(from = 1) int i, Task<T> task) {
        execute(getPoolByTypeAndPriority(i), task);
    }

    public static <T> void executeByFixed(@IntRange(from = 1) int i, Task<T> task, @IntRange(from = 1, to = 10) int i2) {
        execute(getPoolByTypeAndPriority(i, i2), task);
    }

    public static <T> void executeByFixedWithDelay(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(i), task, j, timeUnit);
    }

    public static <T> void executeByFixedWithDelay(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i2) {
        executeWithDelay(getPoolByTypeAndPriority(i, i2), task, j, timeUnit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(i), task, 0, j, timeUnit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i2) {
        executeAtFixedRate(getPoolByTypeAndPriority(i, i2), task, 0, j, timeUnit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(i), task, j, j2, timeUnit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i2) {
        executeAtFixedRate(getPoolByTypeAndPriority(i, i2), task, j, j2, timeUnit);
    }

    public static <T> void executeBySingle(Task<T> task) {
        execute(getPoolByTypeAndPriority(-1), task);
    }

    public static <T> void executeBySingle(Task<T> task, @IntRange(from = 1, to = 10) int i) {
        execute(getPoolByTypeAndPriority(-1, i), task);
    }

    public static <T> void executeBySingleWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-1), task, j, timeUnit);
    }

    public static <T> void executeBySingleWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeWithDelay(getPoolByTypeAndPriority(-1, i), task, j, timeUnit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1), task, 0, j, timeUnit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1, i), task, 0, j, timeUnit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1), task, j, j2, timeUnit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-1, i), task, j, j2, timeUnit);
    }

    public static <T> void executeByCached(Task<T> task) {
        execute(getPoolByTypeAndPriority(-2), task);
    }

    public static <T> void executeByCached(Task<T> task, @IntRange(from = 1, to = 10) int i) {
        execute(getPoolByTypeAndPriority(-2, i), task);
    }

    public static <T> void executeByCachedWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-2), task, j, timeUnit);
    }

    public static <T> void executeByCachedWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeWithDelay(getPoolByTypeAndPriority(-2, i), task, j, timeUnit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2), task, 0, j, timeUnit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2, i), task, 0, j, timeUnit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2), task, j, j2, timeUnit);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-2, i), task, j, j2, timeUnit);
    }

    public static <T> void executeByIo(Task<T> task) {
        execute(getPoolByTypeAndPriority(-4), task);
    }

    public static <T> void executeByIo(Task<T> task, @IntRange(from = 1, to = 10) int i) {
        execute(getPoolByTypeAndPriority(-4, i), task);
    }

    public static <T> void executeByIoWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-4), task, j, timeUnit);
    }

    public static <T> void executeByIoWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeWithDelay(getPoolByTypeAndPriority(-4, i), task, j, timeUnit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4), task, 0, j, timeUnit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4, i), task, 0, j, timeUnit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4), task, j, j2, timeUnit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-4, i), task, j, j2, timeUnit);
    }

    public static <T> void executeByCpu(Task<T> task) {
        execute(getPoolByTypeAndPriority(-8), task);
    }

    public static <T> void executeByCpu(Task<T> task, @IntRange(from = 1, to = 10) int i) {
        execute(getPoolByTypeAndPriority(-8, i), task);
    }

    public static <T> void executeByCpuWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(getPoolByTypeAndPriority(-8), task, j, timeUnit);
    }

    public static <T> void executeByCpuWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeWithDelay(getPoolByTypeAndPriority(-8, i), task, j, timeUnit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8), task, 0, j, timeUnit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8, i), task, 0, j, timeUnit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8), task, j, j2, timeUnit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        executeAtFixedRate(getPoolByTypeAndPriority(-8, i), task, j, j2, timeUnit);
    }

    public static <T> void executeByCustom(ExecutorService executorService, Task<T> task) {
        execute(executorService, task);
    }

    public static <T> void executeByCustomWithDelay(ExecutorService executorService, Task<T> task, long j, TimeUnit timeUnit) {
        executeWithDelay(executorService, task, j, timeUnit);
    }

    public static <T> void executeByCustomAtFixRate(ExecutorService executorService, Task<T> task, long j, TimeUnit timeUnit) {
        executeAtFixedRate(executorService, task, 0, j, timeUnit);
    }

    public static <T> void executeByCustomAtFixRate(ExecutorService executorService, Task<T> task, long j, long j2, TimeUnit timeUnit) {
        executeAtFixedRate(executorService, task, j, j2, timeUnit);
    }

    public static void cancel(Task task) {
        task.cancel();
    }

    private static <T> void execute(ExecutorService executorService, Task<T> task) {
        executeWithDelay(executorService, task, 0, TimeUnit.MILLISECONDS);
    }

    private static <T> void executeWithDelay(final ExecutorService executorService, final Task<T> task, long j, TimeUnit timeUnit) {
        if (j <= 0) {
            getScheduledByTask(task).execute(new Runnable() {
                public void run() {
                    executorService.execute(task);
                }
            });
        } else {
            getScheduledByTask(task).schedule(new Runnable() {
                public void run() {
                    executorService.execute(task);
                }
            }, j, timeUnit);
        }
    }

    private static <T> void executeAtFixedRate(final ExecutorService executorService, final Task<T> task, long j, long j2, TimeUnit timeUnit) {
        task.isSchedule = true;
        getScheduledByTask(task).scheduleAtFixedRate(new Runnable() {
            public void run() {
                executorService.execute(task);
            }
        }, j, j2, timeUnit);
    }

    private static ScheduledExecutorService getScheduledByTask(Task task) {
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) TASK_SCHEDULED.get(task);
        if (scheduledExecutorService != null) {
            return scheduledExecutorService;
        }
        scheduledExecutorService = Executors.newScheduledThreadPool(1, new UtilsThreadFactory("scheduled", 10));
        TASK_SCHEDULED.put(task, scheduledExecutorService);
        return scheduledExecutorService;
    }

    private static void removeScheduleByTask(Task task) {
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) TASK_SCHEDULED.get(task);
        if (scheduledExecutorService != null) {
            TASK_SCHEDULED.remove(task);
            shutdownAndAwaitTermination(scheduledExecutorService);
        }
    }

    private static ExecutorService getPoolByTypeAndPriority(int i) {
        return getPoolByTypeAndPriority(i, 5);
    }

    private static ExecutorService getPoolByTypeAndPriority(int i, int i2) {
        Map map = (Map) TYPE_PRIORITY_POOLS.get(Integer.valueOf(i));
        if (map == null) {
            map = new ConcurrentHashMap();
            ExecutorService createPoolByTypeAndPriority = createPoolByTypeAndPriority(i, i2);
            map.put(Integer.valueOf(i2), createPoolByTypeAndPriority);
            TYPE_PRIORITY_POOLS.put(Integer.valueOf(i), map);
            return createPoolByTypeAndPriority;
        }
        createPoolByTypeAndPriority = (ExecutorService) map.get(Integer.valueOf(i2));
        if (createPoolByTypeAndPriority != null) {
            return createPoolByTypeAndPriority;
        }
        createPoolByTypeAndPriority = createPoolByTypeAndPriority(i, i2);
        map.put(Integer.valueOf(i2), createPoolByTypeAndPriority);
        return createPoolByTypeAndPriority;
    }

    private static ExecutorService createPoolByTypeAndPriority(int i, int i2) {
        switch (i) {
            case Constants.ERROR_SOCKETTIMEOUT /*-8*/:
                return new ThreadPoolExecutor(CPU_COUNT + 1, (CPU_COUNT * 2) + 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new UtilsThreadFactory("cpu", i2));
            case -4:
                return new ThreadPoolExecutor((CPU_COUNT * 2) + 1, (CPU_COUNT * 2) + 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new UtilsThreadFactory("io", i2));
            case -2:
                return Executors.newCachedThreadPool(new UtilsThreadFactory("cached", i2));
            case -1:
                return Executors.newSingleThreadExecutor(new UtilsThreadFactory("single", i2));
            default:
                return Executors.newFixedThreadPool(i, new UtilsThreadFactory("fixed(" + i + ")", i2));
        }
    }

    private static void shutdownAndAwaitTermination(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
