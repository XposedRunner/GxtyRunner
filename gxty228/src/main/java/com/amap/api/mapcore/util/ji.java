package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import java.lang.ref.WeakReference;

/* compiled from: OfflineLocManager */
public class ji {
    static int a = 1000;
    static boolean b = false;
    static int c = 20;
    private static WeakReference<je> d;
    private static int e = 10;

    @Deprecated
    public static synchronized void a(int i, boolean z) {
        synchronized (ji.class) {
            a = i;
            b = z;
        }
    }

    public static synchronized void a(final jh jhVar, final Context context) {
        synchronized (ji.class) {
            gz.d().submit(new Runnable() {
                public void run() {
                    try {
                        synchronized (ji.class) {
                            String l = Long.toString(System.currentTimeMillis());
                            je a = jl.a(ji.d);
                            jl.a(context, a, gx.i, ji.a, 2097152, Constants.VIA_SHARE_TYPE_INFO);
                            if (a.e == null) {
                                a.e = new go(new gr(new gt(new gr())));
                            }
                            jf.a(l, jhVar.a(), a);
                        }
                    } catch (Throwable th) {
                        gz.c(th, "ofm", "aple");
                    }
                }
            });
        }
    }

    public static void a(final Context context) {
        gz.d().submit(new Runnable() {
            public void run() {
                try {
                    je a = jl.a(ji.d);
                    jl.a(context, a, gx.i, ji.a, 2097152, Constants.VIA_SHARE_TYPE_INFO);
                    a.h = 14400000;
                    if (a.g == null) {
                        gq goVar = new go(new gr(new gt()));
                        a.g = new jq(new jp(context, new ju(), goVar, new String(gu.a(10)), fx.f(context), gd.u(context), gd.l(context), gd.h(context), gd.a(), Build.MANUFACTURER, Build.DEVICE, gd.v(context), fx.c(context), Build.MODEL, fx.d(context), fx.b(context)));
                    }
                    if (TextUtils.isEmpty(a.i)) {
                        a.i = "fKey";
                    }
                    a.f = new jy(context, a.h, a.i, new jw(context, ji.b, ji.e * 1024, ji.c * 1024));
                    jf.a(a);
                } catch (Throwable th) {
                    gz.c(th, "ofm", "uold");
                }
            }
        });
    }
}
