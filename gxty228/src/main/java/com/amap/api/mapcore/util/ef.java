package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: AbstractAsyncTask */
public abstract class ef<Params, Progress, Result> {
    public static final Executor a = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, e, d, new DiscardOldestPolicy());
    public static final Executor b;
    public static final Executor c = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(), new dy("AMapDUAL_THREAD_EXECUTOR"), new AbortPolicy());
    private static final ThreadFactory d = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AbstractAsyncTask #" + this.a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> e = new LinkedBlockingQueue(10);
    private static final c f = new c(Looper.getMainLooper());
    private static volatile Executor g = b;
    private final a<Params, Result> h = new a<Params, Result>(this) {
        final /* synthetic */ ef a;

        {
            this.a = r2;
        }

        public Result call() throws Exception {
            this.a.l.set(true);
            Process.setThreadPriority(10);
            return this.a.d(this.a.a(this.b));
        }
    };
    private final FutureTask<Result> i = new FutureTask<Result>(this, this.h) {
        final /* synthetic */ ef a;

        protected void done() {
            try {
                this.a.c(this.a.i.get());
            } catch (Throwable e) {
                Log.w("AbstractAsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                this.a.c(null);
            }
        }
    };
    private volatile e j = e.PENDING;
    private final AtomicBoolean k = new AtomicBoolean();
    private final AtomicBoolean l = new AtomicBoolean();

    /* compiled from: AbstractAsyncTask */
    private static abstract class a<Params, Result> implements Callable<Result> {
        Params[] b;

        private a() {
        }
    }

    /* compiled from: AbstractAsyncTask */
    private static class b<Data> {
        final ef a;
        final Data[] b;

        b(ef efVar, Data... dataArr) {
            this.a = efVar;
            this.b = dataArr;
        }
    }

    /* compiled from: AbstractAsyncTask */
    private static class c extends Handler {
        public c(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.obj != null && (message.obj instanceof b)) {
                b bVar = (b) message.obj;
                switch (message.what) {
                    case 1:
                        bVar.a.e(bVar.b[0]);
                        return;
                    case 2:
                        bVar.a.b(bVar.b);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* compiled from: AbstractAsyncTask */
    private static class d implements Executor {
        final ArrayDeque<Runnable> a;
        Runnable b;

        private d() {
            this.a = new ArrayDeque();
        }

        public synchronized void execute(final Runnable runnable) {
            this.a.offer(new Runnable(this) {
                final /* synthetic */ d b;

                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        this.b.a();
                    }
                }
            });
            if (this.b == null) {
                a();
            }
        }

        protected synchronized void a() {
            Runnable runnable = (Runnable) this.a.poll();
            this.b = runnable;
            if (runnable != null) {
                ef.a.execute(this.b);
            }
        }
    }

    /* compiled from: AbstractAsyncTask */
    public enum e {
        PENDING,
        RUNNING,
        FINISHED
    }

    protected abstract Result a(Params... paramsArr);

    static {
        Executor dVar;
        if (en.c()) {
            dVar = new d();
        } else {
            dVar = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(), new dy("AMapSERIAL_EXECUTOR"), new AbortPolicy());
        }
        b = dVar;
    }

    private void c(Result result) {
        if (!this.l.get()) {
            d(result);
        }
    }

    private Result d(Result result) {
        f.obtainMessage(1, new b(this, result)).sendToTarget();
        return result;
    }

    public final e a() {
        return this.j;
    }

    protected void b() {
    }

    protected void a(Result result) {
    }

    protected void b(Progress... progressArr) {
    }

    protected void b(Result result) {
        c();
    }

    protected void c() {
    }

    public final boolean d() {
        return this.k.get();
    }

    public final boolean a(boolean z) {
        this.k.set(true);
        return this.i.cancel(z);
    }

    public final ef<Params, Progress, Result> c(Params... paramsArr) {
        return a(g, (Object[]) paramsArr);
    }

    public final ef<Params, Progress, Result> a(Executor executor, Params... paramsArr) {
        if (this.j != e.PENDING) {
            switch (this.j) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.j = e.RUNNING;
        b();
        this.h.b = paramsArr;
        executor.execute(this.i);
        return this;
    }

    private void e(Result result) {
        if (d()) {
            b((Object) result);
        } else {
            a((Object) result);
        }
        this.j = e.FINISHED;
    }
}
