package com.loc;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SDKLogHandler */
public final class j extends g implements UncaughtExceptionHandler {
    private static ExecutorService e;
    private static Set<Integer> f = Collections.synchronizedSet(new HashSet());
    private static WeakReference<Context> g;
    private static final ThreadFactory h = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public final Thread newThread(Runnable runnable) {
            return new Thread(runnable, "pama#" + this.a.getAndIncrement());
        }
    };
    private Context d;

    private j(Context context) {
        this.d = context;
        try {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
                return;
            }
            String obj = this.b.toString();
            if (obj.indexOf("com.amap.api") == -1 && obj.indexOf("com.loc") == -1) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = true;
                return;
            }
            this.c = false;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static synchronized j a(Context context, dk dkVar) throws k {
        j jVar;
        synchronized (j.class) {
            if (dkVar == null) {
                throw new k("sdk info is null");
            } else if (dkVar.a() == null || "".equals(dkVar.a())) {
                throw new k("sdk name is invalid");
            } else {
                try {
                    new m().a(context);
                    if (f.add(Integer.valueOf(dkVar.hashCode()))) {
                        if (g.a == null) {
                            g.a = new j(context);
                        } else {
                            g.a.c = false;
                        }
                        g.a.a(context, dkVar, g.a.c);
                        jVar = (j) g.a;
                    } else {
                        jVar = (j) g.a;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        return jVar;
    }

    public static void a(dk dkVar, String str, k kVar) {
        if (kVar != null) {
            a(dkVar, str, kVar.c(), kVar.d(), kVar.b());
        }
    }

    public static void a(dk dkVar, String str, String str2, String str3, String str4) {
        try {
            if (g.a != null) {
                StringBuilder stringBuilder = new StringBuilder("path:");
                stringBuilder.append(str).append(",type:").append(str2).append(",gsid:").append(str3).append(",code:").append(str4);
                g.a.a(dkVar, stringBuilder.toString(), "networkError");
            }
        } catch (Throwable th) {
        }
    }

    public static synchronized void b() {
        synchronized (j.class) {
            try {
                if (e != null) {
                    e.shutdown();
                }
                ai.a();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (!(g.a == null || Thread.getDefaultUncaughtExceptionHandler() != g.a || g.a.b == null)) {
                    Thread.setDefaultUncaughtExceptionHandler(g.a.b);
                }
                g.a = null;
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    public static void b(dk dkVar, String str, String str2) {
        try {
            if (g.a != null) {
                g.a.a(dkVar, str, str2);
            }
        } catch (Throwable th) {
        }
    }

    public static void b(Throwable th, String str, String str2) {
        try {
            if (g.a != null) {
                g.a.a(th, 1, str, str2);
            }
        } catch (Throwable th2) {
        }
    }

    public static void c() {
        if (g != null && g.get() != null) {
            h.b((Context) g.get());
        } else if (g.a != null) {
            g.a.a();
        }
    }

    public static synchronized ExecutorService d() {
        ExecutorService executorService;
        synchronized (j.class) {
            try {
                if (e == null || e.isShutdown()) {
                    e = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(256), h);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            executorService = e;
        }
        return executorService;
    }

    protected final void a() {
        h.b(this.d);
    }

    protected final void a(final Context context, final dk dkVar, final boolean z) {
        try {
            ExecutorService d = d();
            if (d != null && !d.isShutdown()) {
                d.submit(new Runnable(this) {
                    final /* synthetic */ j d;

                    public final void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new u(context, true).a(dkVar);
                            }
                            if (z) {
                                l.a(this.d.d);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected final void a(dk dkVar, String str, String str2) {
        l.b(dkVar, this.d, str2, str);
    }

    protected final void a(Throwable th, int i, String str, String str2) {
        l.a(this.d, th, i, str, str2);
    }

    public final void uncaughtException(Thread thread, Throwable th) {
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
}
