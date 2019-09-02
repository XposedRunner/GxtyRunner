package cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.jpush.android.a.d;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.d.a;
import cn.jpush.android.d.e;

public final class b {
    private static b a;

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public static void a(Context context, Intent intent) {
        Object stringExtra = intent.getStringExtra(JPushInterface.EXTRA_MSG_ID);
        if (!TextUtils.isEmpty(stringExtra)) {
            String stringExtra2 = intent.getStringExtra(JPushInterface.EXTRA_NOTI_TYPE);
            e.c("PushReceiverCore", "strNType = " + stringExtra2);
            boolean z = false;
            if (stringExtra2 != null && "1".equals(stringExtra2)) {
                z = true;
            }
            if (true != z) {
                d.a(stringExtra, 1000, null, context);
            }
        }
        if (a.a(context, JPushInterface.ACTION_NOTIFICATION_OPENED, true)) {
            Intent intent2 = new Intent(JPushInterface.ACTION_NOTIFICATION_OPENED);
            String str = "";
            try {
                intent2.putExtras(intent.getExtras());
                str = intent.getStringExtra("app");
                if (TextUtils.isEmpty(str)) {
                    str = context.getPackageName();
                }
                intent2.addCategory(str);
                intent2.setPackage(context.getPackageName());
                e.e("PushReceiverCore", "Send broadcast to app: " + str);
                context.sendBroadcast(intent2, str + ".permission.JPUSH_MESSAGE");
                return;
            } catch (Throwable th) {
                e.h("PushReceiverCore", "onNotificationOpen sendBrocat error:" + th.getMessage());
                a.b(context, intent2, str + ".permission.JPUSH_MESSAGE");
                return;
            }
        }
        e.d("PushReceiverCore", "No ACTION_NOTIFICATION_OPENED defined in manifest, open the default main activity");
        a.b(context);
    }
}
