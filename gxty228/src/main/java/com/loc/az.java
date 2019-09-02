package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.blankj.utilcode.constant.TimeConstants;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Random;

/* compiled from: StatisticsManager */
public class az {
    private static WeakReference<at> a;

    public static void a(final Context context) {
        j.d().submit(new Runnable() {
            public final void run() {
                try {
                    at a = ba.a(az.a);
                    ba.a(context, a, h.h, 1000, 307200, "2");
                    if (a.g == null) {
                        a.g = new bb(new bg(context, new bc(new bh(new bj()))));
                    }
                    a.h = TimeConstants.HOUR;
                    if (TextUtils.isEmpty(a.i)) {
                        a.i = "cKey";
                    }
                    if (a.f == null) {
                        a.f = new bn(context, a.h, a.i, new bk(30, a.a, new bp(context)));
                    }
                    au.a(a);
                } catch (Throwable th) {
                    j.b(th, "stm", "usd");
                }
            }
        });
    }

    static /* synthetic */ void a(Context context, byte[] bArr) throws IOException {
        at a = ba.a(a);
        ba.a(context, a, h.h, 1000, 307200, "2");
        if (a.e == null) {
            a.e = new c();
        }
        try {
            au.a(Integer.toString(new Random().nextInt(100)) + Long.toString(System.nanoTime()), bArr, a);
        } catch (Throwable th) {
            j.b(th, "stm", "wts");
        }
    }

    public static synchronized void a(final ay ayVar, final Context context) {
        synchronized (az.class) {
            j.d().submit(new Runnable() {
                public final void run() {
                    try {
                        synchronized (az.class) {
                            az.a(context, ayVar.a());
                        }
                    } catch (Throwable th) {
                        j.b(th, "stm", "as");
                    }
                }
            });
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(final java.util.List<com.loc.ay> r3, final android.content.Context r4) {
        /*
        r1 = com.loc.az.class;
        monitor-enter(r1);
        if (r3 == 0) goto L_0x000b;
    L_0x0005:
        r0 = r3.size();	 Catch:{ Throwable -> 0x000d }
        if (r0 != 0) goto L_0x000e;
    L_0x000b:
        monitor-exit(r1);
        return;
    L_0x000d:
        r0 = move-exception;
    L_0x000e:
        r0 = com.loc.j.d();	 Catch:{ all -> 0x001b }
        r2 = new com.loc.az$2;	 Catch:{ all -> 0x001b }
        r2.<init>(r3, r4);	 Catch:{ all -> 0x001b }
        r0.submit(r2);	 Catch:{ all -> 0x001b }
        goto L_0x000b;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.az.a(java.util.List, android.content.Context):void");
    }
}
