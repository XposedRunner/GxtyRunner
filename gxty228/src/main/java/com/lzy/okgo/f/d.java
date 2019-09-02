package com.lzy.okgo.f;

import android.util.Log;

/* compiled from: OkLogger */
public class d {
    private static boolean a = true;
    private static String b = "OkGo";

    public static void a(String str, String str2) {
        if (a) {
            Log.v(str, str2);
        }
    }

    public static void a(Throwable th) {
        if (a && th != null) {
            th.printStackTrace();
        }
    }
}
