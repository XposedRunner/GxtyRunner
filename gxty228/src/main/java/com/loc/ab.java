package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: DynamicExceptionHandler */
public final class ab implements UncaughtExceptionHandler {
    private static ab a;
    private UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
    private Context c;
    private dk d;

    private ab(Context context, dk dkVar) {
        this.c = context.getApplicationContext();
        this.d = dkVar;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    static synchronized ab a(Context context, dk dkVar) {
        ab abVar;
        synchronized (ab.class) {
            if (a == null) {
                a = new ab(context, dkVar);
            }
            abVar = a;
        }
        return abVar;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        String a = dl.a(th);
        try {
            if (!TextUtils.isEmpty(a)) {
                if ((a.contains("amapdynamic") || a.contains("admic")) && a.contains("com.amap.api")) {
                    p pVar = new p(this.c, ad.b());
                    if (a.contains("loc")) {
                        aa.a(pVar, this.c, "loc");
                    }
                    if (a.contains("navi")) {
                        aa.a(pVar, this.c, "navi");
                    }
                    if (a.contains("sea")) {
                        aa.a(pVar, this.c, "sea");
                    }
                    if (a.contains("2dmap")) {
                        aa.a(pVar, this.c, "2dmap");
                    }
                    if (a.contains("3dmap")) {
                        aa.a(pVar, this.c, "3dmap");
                    }
                } else if (a.contains("com.autonavi.aps.amapapi.offline")) {
                    aa.a(new p(this.c, ad.b()), this.c, "OfflineLocation");
                } else if (a.contains("com.data.carrier_v4")) {
                    aa.a(new p(this.c, ad.b()), this.c, "Collection");
                } else if (a.contains("com.autonavi.aps.amapapi.httpdns") || a.contains("com.autonavi.httpdns")) {
                    aa.a(new p(this.c, ad.b()), this.c, "HttpDNS");
                } else if (a.contains("com.amap.api.aiunet")) {
                    aa.a(new p(this.c, ad.b()), this.c, "aiu");
                }
            }
        } catch (Throwable th2) {
            g.a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }
}
