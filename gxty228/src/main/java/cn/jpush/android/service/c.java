package cn.jpush.android.service;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import cn.jiguang.api.MultiSpHelper;
import cn.jpush.android.a;
import cn.jpush.android.a.h;
import cn.jpush.android.a.k;
import cn.jpush.android.b;
import cn.jpush.android.c.f;
import cn.jpush.android.d.e;
import cn.jpush.android.data.JPushLocalNotification;
import com.tencent.tauth.AuthActivity;

public final class c {
    private static c b;
    private Context a;

    public static c a(Context context) {
        if (b == null) {
            b = new c(context);
        }
        return b;
    }

    private c(Context context) {
        this.a = context;
    }

    public final void a(Bundle bundle, Handler handler) {
        e.d("PushServiceCore", "bundle:" + (bundle != null ? bundle.toString() : ""));
        if (bundle == null) {
            e.g("PushServiceCore", "onActionRun bundle is null");
            return;
        }
        e.a("PushServiceCore", "Service bundle - " + bundle.toString());
        String string = bundle.getString(AuthActivity.ACTION_KEY);
        if (string != null) {
            e.d("PushServiceCore", "Action - handleServiceAction - action:" + string);
            String str = b(this.a) + ".";
            if (string.startsWith(str)) {
                string = string.substring(str.length());
            }
            if ("intent.MULTI_PROCESS".equals(string)) {
                int i = bundle.getInt("multi_type");
                e.a("PushServiceCore", "Handle action for multi type : " + i);
                switch (i) {
                    case 1:
                        b.a(this.a, bundle.getString("notification_buidler_id"), bundle.getString("notification_buidler"), true);
                        return;
                    case 2:
                        b.a(this.a, bundle.getInt("notification_maxnum"), true);
                        return;
                    case 3:
                        b.b(this.a, bundle.getString("enable_push_time"), true);
                        return;
                    case 4:
                        b.a(this.a, bundle.getString("silence_push_time"), true);
                        return;
                    case 6:
                        a.a(this.a).a(this.a, (JPushLocalNotification) bundle.getSerializable("local_notification"), true);
                        return;
                    case 7:
                        a.a(this.a).a(this.a, bundle.getLong("local_notification_id"));
                        return;
                    case 8:
                        a.a(this.a).b(this.a);
                        return;
                    case 9:
                        cn.jpush.android.api.b.a(this.a, bundle.getInt("notification_id"), true);
                        return;
                    case 10:
                        cn.jpush.android.api.b.a(this.a, true);
                        return;
                    default:
                        return;
                }
            } else if ("intent.STOPPUSH".equals(string)) {
                MultiSpHelper.commitInt(this.a, "service_stoped", 1);
            } else if (string.equals("intent.RESTOREPUSH")) {
                MultiSpHelper.commitInt(this.a, "service_stoped", 0);
            } else if ("intent.ALIAS_TAGS".equals(string)) {
                k.a(this.a, bundle);
            } else if ("intent.plugin.platform.REFRESSH_REGID".equals(string)) {
                f.a().a(this.a, bundle);
            } else if ("intent.plugin.platform.ON_MESSAGING".equals(string)) {
                Object string2 = bundle.getString("appId");
                Object string3 = bundle.getString("senderId");
                Object string4 = bundle.getString("JMessageExtra");
                Log.d("PushServiceCore", "appId:" + String.valueOf(string2) + ",senderId:" + String.valueOf(string3) + ",JMessageExtra:" + String.valueOf(string4));
                if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3) && !TextUtils.isEmpty(string4)) {
                    h.a(this.a, string2, string3, string4, 0, (byte) 8);
                }
            } else if ("intent.MOBILE_NUMBER".equals(string)) {
                cn.jpush.android.a.f.a().a(this.a, bundle);
            } else {
                e.g("PushServiceCore", "Unhandled service action - " + string);
            }
        }
    }

    private static String b(Context context) {
        String str = a.c;
        if (TextUtils.isEmpty(str) && context != null) {
            str = context.getPackageName();
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    public static void a(Context context, Bundle bundle, String str) {
        bundle.putString(AuthActivity.ACTION_KEY, b(context) + "." + str);
    }

    public final void a() {
        a.a(this.a).d(this.a);
    }
}
