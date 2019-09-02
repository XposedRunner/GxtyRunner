package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.autonavi.amap.mapcore.MapConfig;
import java.lang.ref.WeakReference;

/* compiled from: AuthProTask */
public class gj extends Thread {
    private static int c = 0;
    private static int d = 3;
    private static long e = StatisticConfig.MIN_UPLOAD_INTERVAL;
    private static boolean g = false;
    private WeakReference<Context> a = null;
    private lj b;
    private a f = null;
    private Handler h = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ gj a;

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (!gj.g) {
                if (this.a.f == null) {
                    this.a.f = new a(this.a.b, this.a.a == null ? null : (Context) this.a.a.get());
                }
                em.a().a(this.a.f);
            }
        }
    };

    /* compiled from: AuthProTask */
    static class a implements Runnable {
        private WeakReference<lj> a = null;
        private WeakReference<Context> b = null;
        private hl c;

        public a(lj ljVar, Context context) {
            this.a = new WeakReference(ljVar);
            if (context != null) {
                this.b = new WeakReference(context);
            }
        }

        public void run() {
            try {
                if (!gj.g) {
                    if (!(this.c != null || this.b == null || this.b.get() == null)) {
                        this.c = new hl((Context) this.b.get(), "");
                    }
                    gj.c();
                    if (gj.c > gj.d) {
                        gj.g = true;
                        a();
                    } else if (this.c != null) {
                        com.amap.api.mapcore.util.hl.a aVar = (com.amap.api.mapcore.util.hl.a) this.c.e();
                        if (aVar != null) {
                            if (!aVar.d) {
                                a();
                            }
                            gj.g = true;
                        }
                    }
                }
            } catch (Throwable th) {
                gz.c(th, "authForPro", "loadConfigData_uploadException");
            }
        }

        private void a() {
            if (this.a != null && this.a.get() != null) {
                final lj ljVar = (lj) this.a.get();
                if (ljVar != null && ljVar.getMapConfig() != null) {
                    ljVar.queueEvent(new Runnable(this) {
                        final /* synthetic */ a b;

                        public void run() {
                            if (ljVar != null && ljVar.getMapConfig() != null) {
                                MapConfig mapConfig = ljVar.getMapConfig();
                                mapConfig.setProFunctionAuthEnable(false);
                                if (mapConfig.isUseProFunction()) {
                                    ljVar.a(mapConfig.isCustomStyleEnable(), true);
                                    dn.a(this.b.b == null ? null : (Context) this.b.b.get());
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    static /* synthetic */ int c() {
        int i = c;
        c = i + 1;
        return i;
    }

    public gj(Context context, lj ljVar) {
        if (context != null) {
            this.a = new WeakReference(context);
        }
        this.b = ljVar;
        a();
    }

    public static void a() {
        c = 0;
        g = false;
    }

    public void run() {
        try {
            f();
        } catch (Throwable th) {
            gz.c(th, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th.printStackTrace();
        }
    }

    private void f() {
        if (!g) {
            for (int i = 0; i <= d; i++) {
                this.h.sendEmptyMessageDelayed(0, ((long) (i + 1)) * e);
            }
        }
    }

    public void interrupt() {
        this.b = null;
        this.a = null;
        if (this.h != null) {
            this.h.removeCallbacksAndMessages(null);
        }
        this.h = null;
        this.f = null;
        a();
        super.interrupt();
    }
}
