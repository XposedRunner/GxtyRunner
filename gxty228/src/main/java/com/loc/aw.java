package com.loc;

import android.content.Context;

/* compiled from: OfflineLocEntity */
public final class aw {
    private Context a;
    private dk b;
    private String c;

    public aw(Context context, dk dkVar, String str) {
        this.a = context.getApplicationContext();
        this.b = dkVar;
        this.c = str;
    }

    private static String a(Context context, dk dkVar, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sdkversion\":\"").append(dkVar.c()).append("\",\"product\":\"").append(dkVar.a()).append("\",\"nt\":\"").append(df.e(context)).append("\",\"details\":").append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    final byte[] a() {
        return dl.a(a(this.a, this.b, this.c));
    }
}
