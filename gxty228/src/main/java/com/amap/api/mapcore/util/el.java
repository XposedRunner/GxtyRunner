package com.amap.api.mapcore.util;

import android.content.Context;

/* compiled from: StatisticsUtil */
public class el {
    public static void a(Context context, boolean z) {
        try {
            String a = a(z);
            jj jjVar = new jj(context, "3dmap", "6.4.1", "O001");
            jjVar.a(a);
            jk.a(jjVar, context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static String a(boolean z) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{\"Quest\":").append(z).append("}");
            return stringBuilder.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
