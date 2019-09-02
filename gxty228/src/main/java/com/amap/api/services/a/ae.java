package com.amap.api.services.a;

import android.content.Context;
import java.lang.ref.WeakReference;

/* compiled from: Utils */
public class ae {
    public static void a(Context context, ac acVar, String str, int i, int i2, String str2) {
        acVar.a = o.a(context, str);
        acVar.d = i;
        acVar.b = (long) i2;
        acVar.c = str2;
    }

    public static ac a(WeakReference<ac> weakReference) {
        if (weakReference == null || weakReference.get() == null) {
            weakReference = new WeakReference(new ac());
        }
        return (ac) weakReference.get();
    }

    public static String a() {
        return f.a(System.currentTimeMillis());
    }

    public static String a(Context context, e eVar) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(b.e(context)).append("\",\"sdkversion\":\"").append(eVar.b()).append("\",\"product\":\"").append(eVar.a()).append("\",\"ed\":\"").append(eVar.c()).append("\",\"nt\":\"").append(b.c(context)).append("\",\"np\":\"").append(b.a(context)).append("\",\"mnc\":\"").append(b.b(context)).append("\",\"ant\":\"").append(b.d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String a(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2).append(",").append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }
}
