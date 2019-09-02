package cn.jpush.android.c;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import cn.jpush.android.a;
import cn.jpush.android.a.d;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.d.e;
import cn.jpush.android.data.b;
import cn.jpush.android.data.g;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import org.json.JSONObject;

public final class h {
    public static void a(Context context, String str, String str2, int i, byte b, boolean z) {
        if (context == null) {
            e.h("PluginPlatformsNotificationHelper", "context was null");
        } else if (TextUtils.isEmpty(str)) {
            e.h("PluginPlatformsNotificationHelper", "content was null");
        } else {
            e.a("PluginPlatformsNotificationHelper", "message content:" + str);
            b a = a(context, str, str2);
            e.a("PluginPlatformsNotificationHelper", "entity:" + a);
            if (a == null) {
                e.h("PluginPlatformsNotificationHelper", "entity was null");
            } else if (TextUtils.isEmpty(a.c)) {
                e.h("PluginPlatformsNotificationHelper", "message id was empty");
            } else {
                a.e = b;
                if (z) {
                    e.a("PluginPlatformsNotificationHelper", "Action - onNotificationMessageClick");
                    if (!(a instanceof g)) {
                        return;
                    }
                    if (((g) a).L == -1) {
                        a(context, a, str2, i);
                        return;
                    }
                    Intent c = cn.jpush.android.api.b.c(context, a);
                    if (c != null) {
                        c.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
                        context.getApplicationContext().startActivity(c);
                        return;
                    }
                    return;
                }
                e.a("PluginPlatformsNotificationHelper", "Action - onNotificationMessageArrived");
                if (a instanceof g) {
                    cn.jpush.android.api.b.a(context, cn.jpush.android.api.b.a(a), i, null, context.getPackageName(), a);
                    d.a(a.c, str2, a.e, PointerIconCompat.TYPE_ZOOM_IN, context);
                }
            }
        }
    }

    public static b a(Context context, String str, String str2) {
        byte b = (byte) 0;
        b gVar = new g();
        try {
            JSONObject jSONObject = new JSONObject(str);
            gVar.c = jSONObject.optString("_jmsgid_");
            if (gVar.c.isEmpty()) {
                gVar.c = jSONObject.optString("msg_id");
            }
            gVar.e = (byte) jSONObject.optInt("rom_type");
            int optInt = jSONObject.optInt("show_type", -1);
            JSONObject optJSONObject = jSONObject.optJSONObject("m_content");
            if (optJSONObject != null) {
                gVar.v = optJSONObject.optString("n_content");
                gVar.u = optJSONObject.optString("n_title");
                gVar.n = optJSONObject.optString("n_extras");
                jSONObject = optJSONObject.optJSONObject("rich_content");
                if (jSONObject != null) {
                    gVar.a(jSONObject);
                    gVar.b = 3;
                } else {
                    gVar.b = 4;
                    gVar.L = -1;
                }
            } else {
                gVar.v = jSONObject.optString("n_content");
                gVar.u = jSONObject.optString("n_title");
                gVar.n = jSONObject.optString("n_extras");
                gVar.e = (byte) jSONObject.optInt("rom_type");
            }
            if (optInt != -1) {
                gVar.b = optInt;
            }
            gVar.q = 0;
            gVar.r = true;
            return gVar;
        } catch (Throwable th) {
            e.i("PluginPlatformsNotificationHelper", "parseContent error:" + th);
            String str3 = "NO MSGID";
            if (!TextUtils.isEmpty(gVar.c)) {
                str3 = gVar.c;
                b = gVar.e;
            }
            d.a(str3, str2, b, 996, context);
            return null;
        }
    }

    private static void a(Context context, b bVar, String str, int i) {
        if (!TextUtils.isEmpty(bVar.c)) {
            Intent intent = new Intent(JPushInterface.ACTION_NOTIFICATION_OPENED);
            try {
                cn.jpush.android.api.b.a(intent, cn.jpush.android.api.b.a(bVar), i);
                intent.putExtra("sdktype", a.a);
                String packageName = TextUtils.isEmpty(bVar.o) ? context.getPackageName() : bVar.o;
                intent.addCategory(packageName);
                e.e("PluginPlatformsNotificationHelper", "Send broadcast to app: " + packageName);
                intent.setPackage(context.getPackageName());
                context.sendBroadcast(intent, packageName + ".permission.JPUSH_MESSAGE");
                d.a(bVar.c, str, bVar.e, 1000, context);
            } catch (Throwable th) {
                e.h("PluginPlatformsNotificationHelper", "onNotificationOpen sendBrocat error:" + th.getMessage());
                cn.jpush.android.d.a.b(context, intent, context.getPackageName() + ".permission.JPUSH_MESSAGE");
            }
        }
    }
}
