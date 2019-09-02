package com.amap.api.mapcore.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Looper;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: Log */
public class gx {
    public static final String a = "/a/";
    static final String b = "b";
    static final String c = "c";
    static final String d = "d";
    static final String e = "i";
    public static final String f = IXAdRequestInfo.GPS;
    public static final String g = IXAdRequestInfo.HEIGHT;
    public static final String h = "e";
    public static final String i = "f";
    public static final String j = "j";

    public static String a(Context context, String str) {
        return context.getSharedPreferences("AMSKLG_CFG", 0).getString(str, "");
    }

    @TargetApi(9)
    public static void a(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("AMSKLG_CFG", 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static void b(Context context, String str) {
        Editor edit = context.getSharedPreferences("AMSKLG_CFG", 0).edit();
        edit.remove(str);
        edit.apply();
    }

    public static String a(Context context) {
        return c(context, e);
    }

    public static String c(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getFilesDir().getAbsolutePath());
        stringBuilder.append(a);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static void b(final Context context) {
        try {
            ExecutorService d = gz.d();
            if (d != null && !d.isShutdown()) {
                d.submit(new Runnable() {
                    public void run() {
                        try {
                            jg.a(context);
                            ha.b(context);
                            ha.d(context);
                            ha.c(context);
                            jk.a(context);
                            ji.a(context);
                        } catch (RejectedExecutionException e) {
                        } catch (Throwable th) {
                            gz.c(th, "Lg", "proL");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            gz.c(th, "Lg", "proL");
        }
    }

    static List<gk> c(Context context) {
        List<gk> a;
        Throwable th;
        Throwable th2;
        Throwable th3;
        List<gk> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    a = new hj(context, false).a();
                    try {
                    } catch (Throwable th22) {
                        th = th22;
                        list = a;
                        th3 = th;
                        try {
                            throw th3;
                        } catch (Throwable th32) {
                            th = th32;
                            a = list;
                            th22 = th;
                        }
                    }
                } catch (Throwable th4) {
                    th32 = th4;
                    throw th32;
                }
            }
        } catch (Throwable th322) {
            th = th322;
            a = null;
            th22 = th;
            th22.printStackTrace();
            return a;
        }
    }

    static boolean a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String trim : str.split("\n")) {
                if (b(strArr, trim.trim())) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    static boolean b(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String str2 : strArr) {
                str = str.trim();
                if (str.startsWith("at ") && str.contains(str2 + ".") && str.endsWith(")") && !str.contains("uncaughtException")) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
