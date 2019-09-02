package com.amap.api.services.a;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: ErrorLogManager */
public class q {
    private static WeakReference<ac> a;
    private static boolean b = true;
    private static String[] c = new String[10];
    private static int d = 0;
    private static boolean e = false;
    private static int f = 0;

    private static boolean a(e eVar) {
        return eVar != null && eVar.d();
    }

    private static void a(Context context, e eVar, int i, String str, String str2) {
        String a = ae.a();
        String a2 = ae.a(a.a(context), ae.a(context, eVar), a, i, str, str2);
        if (a2 != null && !"".equals(a2)) {
            a = d.a(str2);
            String str3 = "";
            if (i == 1) {
                str3 = o.b;
            } else if (i == 2) {
                str3 = o.d;
            } else if (i == 0) {
                str3 = o.c;
            } else {
                return;
            }
            a(context, a, str3, a2);
        }
    }

    public static void a(Context context, Throwable th, int i, String str, String str2) {
        String a = f.a(th);
        e a2 = a(context, a);
        if (a(a2)) {
            a = a.replaceAll("\n", "<br/>");
            String a3 = a(th);
            if (a3 != null && !"".equals(a3)) {
                StringBuilder stringBuilder = new StringBuilder();
                if (str != null) {
                    stringBuilder.append("class:").append(str);
                }
                if (str2 != null) {
                    stringBuilder.append(" method:").append(str2).append("$").append("<br/>");
                }
                stringBuilder.append(a);
                a(context, a2, i, a3, stringBuilder.toString());
            }
        }
    }

    private static void a(Context context, String str, String str2, String str3) {
        ac a = ae.a(a);
        ae.a(context, a, str2, 1000, 20480, "1");
        if (a.e == null) {
            a.e = new g(new h(new j(new k())));
        }
        try {
            ad.a(str, f.a(str3), a);
        } catch (Throwable th) {
        }
    }

    static e a(Context context, String str) {
        List<e> a = o.a(context);
        if (str == null || "".equals(str)) {
            return null;
        }
        for (e eVar : a) {
            if (o.a(eVar.e(), str)) {
                return eVar;
            }
        }
        if (str.contains("com.amap.api.col")) {
            try {
                return f.a();
            } catch (be e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String a(Throwable th) {
        return th.toString();
    }
}
