package cn.jpush.android.service;

import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.MultiSpHelper;
import cn.jpush.android.a;
import cn.jpush.android.api.DefaultPushNotificationBuilder;
import cn.jpush.android.b;
import cn.jpush.android.c.g;
import cn.jpush.android.d.e;
import cn.jpush.client.android.BuildConfig;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceInterface {
    private static boolean a = false;

    public static void a(Context context) {
        if (!d(context)) {
            JCoreInterface.restart(context, a.a, new Bundle(), false);
            g.a().a(context);
        }
    }

    public static void a(Context context, int i) {
        e(context);
        MultiSpHelper.commitInt(context, "service_stoped", 1);
        Bundle bundle = new Bundle();
        c.a(context, bundle, "intent.STOPPUSH");
        bundle.putString("app", context.getPackageName());
        JCoreInterface.stop(context, a.a, bundle);
    }

    public static void b(Context context, int i) {
        e(context);
        MultiSpHelper.commitInt(context, "service_stoped", 0);
        Bundle bundle = new Bundle();
        c.a(context, bundle, "intent.RESTOREPUSH");
        bundle.putString("app", context.getPackageName());
        JCoreInterface.restart(context, a.a, bundle, true);
    }

    public static void a(Context context, String str, Set<String> set, long j, cn.jpush.android.api.a aVar) {
        int i;
        int i2 = 0;
        Bundle bundle = new Bundle();
        c.a(context, bundle, "intent.ALIAS_TAGS");
        bundle.putString("alias", str);
        ArrayList arrayList = null;
        if (set != null) {
            arrayList = new ArrayList(set);
        }
        bundle.putStringArrayList("tags", arrayList);
        bundle.putLong("seq_id", j);
        String str2 = "proto_type";
        StringBuilder stringBuilder = new StringBuilder();
        if (aVar != null) {
            i = aVar.e;
        } else {
            i = 0;
        }
        bundle.putString(str2, stringBuilder.append(i).toString());
        String str3 = "protoaction_type";
        StringBuilder stringBuilder2 = new StringBuilder();
        if (aVar != null) {
            i2 = aVar.f;
        }
        bundle.putString(str3, stringBuilder2.append(i2).toString());
        JCoreInterface.sendAction(context, a.a, bundle);
    }

    public static void a(Context context, String str) {
        if (context != null && !d(context)) {
            b.a(context, str, false);
        }
    }

    public static void a(Context context, Integer num, DefaultPushNotificationBuilder defaultPushNotificationBuilder) {
        if (context == null) {
            e.j("ServiceInterface", "Null context, please init JPush!");
            return;
        }
        e.e("ServiceInterface", "- setNotificationBuilder - " + defaultPushNotificationBuilder.toString());
        b.a(context, num, defaultPushNotificationBuilder.toString(), false);
    }

    public static void c(Context context, int i) {
        if (context == null) {
            e.h("ServiceInterface", "setNotificationNumber - context is null!");
            return;
        }
        e.a("ServiceInterface", "set notification max num : " + i);
        b.a(context, i, false);
    }

    public static void b(Context context) {
        if (context == null) {
            e.h("ServiceInterface", "clearAllNotification - context is null!");
        } else {
            cn.jpush.android.api.b.a(context.getApplicationContext(), false);
        }
    }

    public static String a() {
        return BuildConfig.VERSION_NAME;
    }

    public static boolean a(Context context, int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("startHour", i);
            jSONObject.put("startMins", i2);
            jSONObject.put("endHour", i3);
            jSONObject.put("endtMins", i4);
            a(context, jSONObject.toString());
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static boolean c(Context context) {
        return e(context) > 0;
    }

    public static boolean d(Context context) {
        boolean c = c(context);
        if (c) {
            e.d("ServiceInterface", "The service is stopped, it will give up all the actions until you call resumePush method to resume the service.");
        }
        return c;
    }

    private static int e(Context context) {
        int i = MultiSpHelper.getInt(context, "service_stoped", 0);
        e.c("ServiceInterface", "pid:" + Process.myPid() + ", stopType:" + i);
        return i;
    }
}
