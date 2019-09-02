package com.amap.api.mapcore.util;

import android.content.Context;

/* compiled from: OfflineLocEntity */
public class jh {
    private Context a;
    private gk b;
    private String c;

    public jh(Context context, gk gkVar, String str) {
        this.a = context.getApplicationContext();
        this.b = gkVar;
        this.c = str;
    }

    byte[] a() {
        return gl.a(a(this.a, this.b, this.c));
    }

    private static String a(Context context, gk gkVar, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sdkversion\":\"").append(gkVar.c()).append("\",\"product\":\"").append(gkVar.a()).append("\",\"nt\":\"").append(gd.e(context)).append("\",\"details\":").append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
