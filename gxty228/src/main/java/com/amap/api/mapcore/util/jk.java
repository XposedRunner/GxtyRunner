package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.blankj.utilcode.constant.TimeConstants;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Random;

/* compiled from: StatisticsManager */
public class jk {
    private static WeakReference<je> a;

    public static synchronized void a(final jj jjVar, final Context context) {
        synchronized (jk.class) {
            gz.d().submit(new Runnable() {
                public void run() {
                    try {
                        synchronized (jk.class) {
                            jk.b(context, jjVar.a());
                        }
                    } catch (Throwable th) {
                        gz.c(th, "stm", "as");
                    }
                }
            });
        }
    }

    private static void b(Context context, byte[] bArr) throws IOException {
        je a = jl.a(a);
        jl.a(context, a, gx.h, 1000, 307200, "2");
        if (a.e == null) {
            a.e = new gs();
        }
        try {
            jf.a(Integer.toString(new Random().nextInt(100)) + Long.toString(System.nanoTime()), bArr, a);
        } catch (Throwable th) {
            gz.c(th, "stm", "wts");
        }
    }

    public static void a(final Context context) {
        gz.d().submit(new Runnable() {
            public void run() {
                try {
                    je a = jl.a(jk.a);
                    jl.a(context, a, gx.h, 1000, 307200, "2");
                    if (a.g == null) {
                        a.g = new jm(new jr(context, new jo(new js(new ju()))));
                    }
                    a.h = TimeConstants.HOUR;
                    if (TextUtils.isEmpty(a.i)) {
                        a.i = "cKey";
                    }
                    if (a.f == null) {
                        a.f = new jy(context, a.h, a.i, new jv(30, a.a, new ka(context, false)));
                    }
                    jf.a(a);
                } catch (Throwable th) {
                    gz.c(th, "stm", "usd");
                }
            }
        });
    }
}
