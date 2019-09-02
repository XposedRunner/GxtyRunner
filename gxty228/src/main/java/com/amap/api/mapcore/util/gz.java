package com.amap.api.mapcore.util;

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
public class gz extends gw implements UncaughtExceptionHandler {
    private static ExecutorService e;
    private static Set<Integer> f = Collections.synchronizedSet(new HashSet());
    private static WeakReference<Context> g;
    private static final ThreadFactory h = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "pama#" + this.a.getAndIncrement());
        }
    };
    private Context d;

    public static void a(Context context) {
        if (context != null) {
            try {
                g = new WeakReference(context.getApplicationContext());
            } catch (Throwable th) {
            }
        }
    }

    public static synchronized gz a(Context context, gk gkVar) throws gp {
        gz gzVar;
        synchronized (gz.class) {
            if (gkVar == null) {
                throw new gp("sdk info is null");
            } else if (gkVar.a() == null || "".equals(gkVar.a())) {
                throw new gp("sdk name is invalid");
            } else {
                try {
                    new hb().a(context);
                    if (f.add(Integer.valueOf(gkVar.hashCode()))) {
                        if (gw.a == null) {
                            gw.a = new gz(context, gkVar);
                        } else {
                            gw.a.c = false;
                        }
                        gw.a.a(context, gkVar, gw.a.c);
                        gzVar = (gz) gw.a;
                    } else {
                        gzVar = (gz) gw.a;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        return gzVar;
    }

    public static synchronized void b() {
        synchronized (gz.class) {
            try {
                if (e != null) {
                    e.shutdown();
                }
                io.a();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (!(gw.a == null || Thread.getDefaultUncaughtExceptionHandler() != gw.a || gw.a.b == null)) {
                    Thread.setDefaultUncaughtExceptionHandler(gw.a.b);
                }
                gw.a = null;
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    protected void a(gk gkVar, String str, String str2) {
        ha.b(gkVar, this.d, str2, str);
    }

    protected void a(Throwable th, int i, String str, String str2) {
        ha.a(this.d, th, i, str, str2);
    }

    protected void a() {
        gx.b(this.d);
    }

    public void uncaughtException(Thread thread, Throwable th) {
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

    protected void a(final Context context, final gk gkVar, final boolean z) {
        try {
            ExecutorService d = d();
            if (d != null && !d.isShutdown()) {
                d.submit(new Runnable(this) {
                    final /* synthetic */ gz d;

                    public void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new hj(context, true).a(gkVar);
                            }
                            if (z) {
                                ha.a(this.d.d);
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

    public void b(Throwable th, String str, String str2) {
        if (th != null) {
            try {
                a(th, 1, str, str2);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    private gz(Context context, gk gkVar) {
        this.d = context;
        f();
    }

    private void f() {
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

    public static void c() {
        if (g != null && g.get() != null) {
            gx.b((Context) g.get());
        } else if (gw.a != null) {
            gw.a.a();
        }
    }

    public static void c(Throwable th, String str, String str2) {
        try {
            if (gw.a != null) {
                gw.a.a(th, 1, str, str2);
            }
        } catch (Throwable th2) {
        }
    }

    public static void a(gk gkVar, String str, String str2, String str3, String str4) {
        try {
            if (gw.a != null) {
                StringBuilder stringBuilder = new StringBuilder("path:");
                stringBuilder.append(str).append(",type:").append(str2).append(",gsid:").append(str3).append(",code:").append(str4);
                gw.a.a(gkVar, stringBuilder.toString(), "networkError");
            }
        } catch (Throwable th) {
        }
    }

    public static void b(gk gkVar, String str, String str2) {
        try {
            if (gw.a != null) {
                gw.a.a(gkVar, str, str2);
            }
        } catch (Throwable th) {
        }
    }

    public static void a(gk gkVar, String str, gp gpVar) {
        if (gpVar != null) {
            a(gkVar, str, gpVar.c(), gpVar.d(), gpVar.b());
        }
    }

    public static synchronized ExecutorService d() {
        ExecutorService executorService;
        synchronized (gz.class) {
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

    public static synchronized gz e() {
        gz gzVar;
        synchronized (gz.class) {
            gzVar = (gz) gw.a;
        }
        return gzVar;
    }
}
