package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import java.lang.ref.WeakReference;

/* compiled from: OfflineLocManager */
public class ax {
    static int a = 1000;
    static boolean b = false;
    static int c = 20;
    private static WeakReference<at> d;
    private static int e = 10;

    public static synchronized void a(int i, boolean z, int i2) {
        synchronized (ax.class) {
            a = i;
            b = z;
            if (i2 < 10 || i2 > 100) {
                i2 = 20;
            }
            c = i2;
            if (i2 / 5 > e) {
                e = c / 5;
            }
        }
    }

    public static void a(final Context context) {
        j.d().submit(new Runnable() {
            public final void run() {
                try {
                    at a = ba.a(ax.d);
                    ba.a(context, a, h.i, ax.a, 2097152, Constants.VIA_SHARE_TYPE_INFO);
                    a.h = 14400000;
                    if (a.g == null) {
                        dp doVar = new do(new b(new d()));
                        a.g = new bf(new be(context, new bj(), doVar, new String(e.a(10)), db.f(context), df.u(context), df.l(context), df.h(context), df.a(), Build.MANUFACTURER, Build.DEVICE, df.v(context), db.c(context), Build.MODEL, db.d(context), db.b(context)));
                    }
                    if (TextUtils.isEmpty(a.i)) {
                        a.i = "fKey";
                    }
                    a.f = new bn(context, a.h, a.i, new bl(context, ax.b, ax.e * 1024, ax.c * 1024));
                    au.a(a);
                } catch (Throwable th) {
                    j.b(th, "ofm", "uold");
                }
            }
        });
    }

    public static synchronized void a(final aw awVar, final Context context) {
        synchronized (ax.class) {
            j.d().submit(new Runnable() {
                public final void run() {
                    try {
                        synchronized (ax.class) {
                            String l = Long.toString(System.currentTimeMillis());
                            at a = ba.a(ax.d);
                            ba.a(context, a, h.i, ax.a, 2097152, Constants.VIA_SHARE_TYPE_INFO);
                            if (a.e == null) {
                                a.e = new do(new b(new d(new b())));
                            }
                            au.a(l, awVar.a(), a);
                        }
                    } catch (Throwable th) {
                        j.b(th, "ofm", "aple");
                    }
                }
            });
        }
    }
}
