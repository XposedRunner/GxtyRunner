package cn.jiguang.d.h;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.d.d.a;
import cn.jiguang.e.d;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

public final class c {
    private static Boolean a;

    private static Bundle a(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (String str : hashMap.keySet()) {
            bundle.putString(str, (String) hashMap.get(str));
        }
        return bundle;
    }

    public static d a(Context context, int i, int i2, a aVar, HashMap<String, String> hashMap) {
        if (aVar == null) {
            d.i("WakeUpHelper", "wakeInfo is null");
            return null;
        } else if (context == null) {
            d.i("WakeUpHelper", "context is null");
            return null;
        } else {
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName(aVar.a, aVar.b);
            intent.setComponent(componentName);
            if (VERSION.SDK_INT >= 12) {
                intent.setFlags(32);
            }
            Bundle a = a((HashMap) hashMap);
            if (a != null) {
                intent.putExtras(a);
            }
            d dVar = new d();
            dVar.a(componentName);
            if ((i2 & 2) != 0) {
                try {
                    ServiceConnection bVar = new b(context, i == 1);
                    if (context.getApplicationContext().bindService(intent, bVar, 1)) {
                        d.a("WakeUpHelper", "binder success");
                        b.a.put(aVar.a + aVar.b, new WeakReference(bVar));
                        if (i == 2) {
                            dVar.a(2, true);
                        }
                    } else {
                        dVar.a(2, false);
                        d.g("WakeUpHelper", "binder failed");
                    }
                } catch (Throwable th) {
                    dVar.a(2, false);
                    d.c("WakeUpHelper", "Fail to bind  Service caused by:" + th);
                }
            }
            if ((i2 & 1) != 0) {
                try {
                    componentName = context.startService(intent);
                    d.c("WakeUpHelper", "start service ComponentName：：" + componentName);
                    if (componentName != null) {
                        dVar.a(1, true);
                    } else {
                        dVar.a(1, false);
                    }
                } catch (Throwable th2) {
                    d.c("WakeUpHelper", "Fail to start Service caused by:" + th2);
                    dVar.a(1, false);
                }
            }
            if ((i2 & 4) != 0) {
                try {
                    if (TextUtils.isEmpty(aVar.d)) {
                        d.g("WakeUpHelper", "do not support provider wakeup, packageName:" + aVar.a);
                    } else {
                        ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
                        String str = aVar.d;
                        if (!str.startsWith("content://")) {
                            str = "content://" + str;
                        }
                        d.a("WakeUpHelper", "path:" + str);
                        Object b = b(hashMap);
                        d.a("WakeUpHelper", "extraUrl:" + b);
                        if (!TextUtils.isEmpty(b)) {
                            str = str + b;
                        }
                        Cursor query = contentResolver.query(Uri.parse(str), null, null, null, null);
                        dVar.a(4, true);
                        d.g("WakeUpHelper", "ContentResolver getType result:" + query);
                    }
                } catch (Throwable th22) {
                    d.c("WakeUpHelper", "Fail to start other app DataProvider caused by:" + th22);
                    dVar.a(4, false);
                }
            }
            return dVar;
        }
    }

    public static boolean a(Context context) {
        if (a != null) {
            return a.booleanValue();
        }
        if (context == null) {
            d.g("WakeUpHelper", "context is null");
            return true;
        }
        try {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction("cn.jpush.android.WAKED_NOT_REPORT");
            List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                a = Boolean.valueOf(true);
            } else {
                a = Boolean.valueOf(false);
            }
            return a.booleanValue();
        } catch (Throwable th) {
            d.b("WakeUpHelper", "Get isWakedNeedReport error#", th);
            return true;
        }
    }

    private static String b(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        Builder builder = new Builder();
        for (String str : hashMap.keySet()) {
            builder.appendQueryParameter(str, (String) hashMap.get(str));
        }
        return builder.toString();
    }
}
