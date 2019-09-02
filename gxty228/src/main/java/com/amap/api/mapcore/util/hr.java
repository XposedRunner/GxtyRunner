package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: DynamicExceptionHandler */
public class hr implements UncaughtExceptionHandler {
    private static hr a;
    private UncaughtExceptionHandler b = Thread.getDefaultUncaughtExceptionHandler();
    private Context c;
    private gk d;

    private hr(Context context, gk gkVar) {
        this.c = context.getApplicationContext();
        this.d = gkVar;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    static synchronized hr a(Context context, gk gkVar) {
        hr hrVar;
        synchronized (hr.class) {
            if (a == null) {
                a = new hr(context, gkVar);
            }
            hrVar = a;
        }
        return hrVar;
    }

    void a(Throwable th) {
        String a = gl.a(th);
        try {
            if (!TextUtils.isEmpty(a)) {
                if ((a.contains("amapdynamic") || a.contains("admic")) && a.contains("com.amap.api")) {
                    he heVar = new he(this.c, hs.a());
                    if (a.contains("loc")) {
                        hq.a(heVar, this.c, "loc");
                    }
                    if (a.contains("navi")) {
                        hq.a(heVar, this.c, "navi");
                    }
                    if (a.contains("sea")) {
                        hq.a(heVar, this.c, "sea");
                    }
                    if (a.contains("2dmap")) {
                        hq.a(heVar, this.c, "2dmap");
                    }
                    if (a.contains("3dmap")) {
                        hq.a(heVar, this.c, "3dmap");
                    }
                } else if (a.contains("com.autonavi.aps.amapapi.offline")) {
                    hq.a(new he(this.c, hs.a()), this.c, "OfflineLocation");
                } else if (a.contains("com.data.carrier_v4")) {
                    hq.a(new he(this.c, hs.a()), this.c, "Collection");
                } else if (a.contains("com.autonavi.aps.amapapi.httpdns") || a.contains("com.autonavi.httpdns")) {
                    hq.a(new he(this.c, hs.a()), this.c, "HttpDNS");
                } else if (a.contains("com.amap.api.aiunet")) {
                    hq.a(new he(this.c, hs.a()), this.c, "aiu");
                }
            }
        } catch (Throwable th2) {
            gw.a(th2, "DynamicExceptionHandler", "uncaughtException");
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        a(th);
        if (this.b != null) {
            this.b.uncaughtException(thread, th);
        }
    }
}
