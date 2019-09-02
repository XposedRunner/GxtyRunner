package com.amap.api.services.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: DeviceInfo */
public class b {
    static String a = "";
    static String b = "";
    static String c = "";
    static boolean d = false;
    static int e = -1;
    static String f = "";
    static String g = "";
    private static String h = null;
    private static boolean i = false;
    private static String j = "";
    private static String k = "";
    private static String l = "";
    private static String m = "";
    private static String n = "";
    private static boolean o = false;
    private static String p = "";

    public static String a(Context context) {
        try {
            return h(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String b(Context context) {
        String str = "";
        try {
            str = k(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str;
    }

    public static int c(Context context) {
        int i = -1;
        try {
            i = l(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    public static int d(Context context) {
        int i = -1;
        try {
            i = i(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    public static String e(Context context) {
        try {
            return g(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static boolean a(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String f(Context context) {
        String str = "";
        try {
            str = g(context);
        } catch (Throwable th) {
            n.a(th, "dI", "gSd");
        }
        return str;
    }

    private static String g(Context context) throws InvocationTargetException, IllegalAccessException {
        if (p != null && !"".equals(p)) {
            return p;
        }
        if (!a(context, f.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return p;
        }
        TelephonyManager m = m(context);
        if (m == null) {
            return "";
        }
        Method a = f.a(m.getClass(), "UZ2V0U3Vic2NyaWJlcklk", new Class[0]);
        if (a != null) {
            p = (String) a.invoke(m, new Object[0]);
        }
        if (p == null) {
            p = "";
        }
        return p;
    }

    private static String h(Context context) {
        if (!a(context, f.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return null;
        }
        TelephonyManager m = m(context);
        if (m == null) {
            return "";
        }
        String simOperatorName = m.getSimOperatorName();
        if (TextUtils.isEmpty(simOperatorName)) {
            return m.getNetworkOperatorName();
        }
        return simOperatorName;
    }

    private static int i(Context context) {
        if (context == null || !a(context, f.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF"))) {
            return -1;
        }
        ConnectivityManager j = j(context);
        if (j == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = j.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }

    private static ConnectivityManager j(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static String k(Context context) {
        String str = "";
        String f = f(context);
        if (f == null || f.length() < 5) {
            return str;
        }
        return f.substring(3, 5);
    }

    private static int l(Context context) {
        if (!a(context, f.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return -1;
        }
        TelephonyManager m = m(context);
        if (m != null) {
            return m.getNetworkType();
        }
        return -1;
    }

    private static TelephonyManager m(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }
}
