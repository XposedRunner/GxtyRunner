package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import java.lang.ref.WeakReference;

/* compiled from: MarkInfoManager */
public class jg {
    static WeakReference<je> a;

    public static void a(final String str, final Context context) {
        gz.d().submit(new Runnable() {
            public void run() {
                synchronized (jg.class) {
                    try {
                        String a = gf.a(gl.a(str));
                        je a2 = jl.a(jg.a);
                        jl.a(context, a2, gx.j, 50, 102400, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
                        if (a2.e == null) {
                            a2.e = new go(new gt(new gr()));
                        }
                        jf.a(a, gl.a(" \"timestamp\":\"" + gl.a(System.currentTimeMillis(), "yyyyMMdd HH:mm:ss") + "\",\"details\":" + str), a2);
                    } catch (Throwable th) {
                        gz.c(th, "mam", "ap");
                    }
                }
            }
        });
    }

    public static void a(final Context context) {
        gz.d().submit(new Runnable() {
            public void run() {
                synchronized (jg.class) {
                    je a = jl.a(jg.a);
                    jl.a(context, a, gx.j, 50, 102400, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
                    if (a.g == null) {
                        String b = jg.c(context);
                        a.g = new jq(new jp(context, new ju(), new go(new gt(new gr())), "WImFwcG5hbWUiOiIlcyIsInBrZyI6IiVzIiwiZGl1IjoiJXMi", fx.b(context), fx.c(context), b));
                    }
                    a.h = 14400000;
                    if (TextUtils.isEmpty(a.i)) {
                        a.i = "eKey";
                    }
                    if (a.f == null) {
                        a.f = new jy(context, a.h, a.i, new jv(5, a.a, new ka(context, false)));
                    }
                    jf.a(a);
                }
            }
        });
    }

    private static String c(Context context) {
        String str = "";
        Object u = gd.u(context);
        if (!TextUtils.isEmpty(u)) {
            return u;
        }
        str = gd.h(context);
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        str = gd.l(context);
        if (TextUtils.isEmpty(str)) {
            return gd.b(context);
        }
        return str;
    }
}
