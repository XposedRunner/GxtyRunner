package cn.jpush.android.service;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jpush.android.c.g;
import cn.jpush.android.c.h;
import cn.jpush.android.d.e;
import com.huawei.hms.support.api.push.PushReceiver;
import com.huawei.hms.support.api.push.PushReceiver.Event;

public class PluginHuaweiPlatformsReceiver extends PushReceiver {
    public void onToken(Context context, String str, Bundle bundle) {
        super.onToken(context, str, bundle);
        String str2 = null;
        if (bundle != null) {
            str2 = bundle.getString("belongId");
        }
        e.f("PluginHuaweiPlatformsReceiver", "token:" + str + ",belongId:" + str2);
        try {
            g.a().a(context.getApplicationContext(), str);
        } catch (Throwable th) {
            e.g("PluginHuaweiPlatformsReceiver", "uploadRegID failed - error:" + th);
        }
    }

    public void onEvent(Context context, Event event, Bundle bundle) {
        String str = null;
        int i = 0;
        e.e("PluginHuaweiPlatformsReceiver", "onEvent:" + (event != null ? event.name() : null));
        if (event != null && Event.NOTIFICATION_OPENED.equals(event)) {
            if (bundle != null) {
                i = bundle.getInt("pushNotifyId", 0);
            }
            if (i != 0) {
                ((NotificationManager) context.getSystemService("notification")).cancel(i);
            }
            if (bundle != null) {
                try {
                    str = bundle.getString("pushMsg");
                } catch (Throwable th) {
                    e.c("PluginHuaweiPlatformsReceiver", "parse message error:" + th);
                }
            }
            if (!TextUtils.isEmpty(str)) {
                str = str.substring(1, str.length() - 1);
            }
            e.e("PluginHuaweiPlatformsReceiver", "receive extented notification message: " + str);
            h.a(context.getApplicationContext(), str, "", i, (byte) 2, true);
        }
        super.onEvent(context, event, bundle);
    }

    public void onPushState(Context context, boolean z) {
        e.d("PluginHuaweiPlatformsReceiver", "onPushState:" + z);
        super.onPushState(context, z);
    }
}
