package cn.jpush.android.service;

import android.content.Context;
import cn.jpush.android.c.g;
import cn.jpush.android.c.h;
import cn.jpush.android.d.e;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

public class PluginXiaomiPlatformsReceiver extends PushMessageReceiver {
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        e.a("PluginXiaomiPlatformsReceiver", "onReceivePassThroughMessage is called. " + miPushMessage);
    }

    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        e.a("PluginXiaomiPlatformsReceiver", "onNotificationMessageClicked is called. " + miPushMessage);
        if (miPushMessage == null) {
            e.a("PluginXiaomiPlatformsReceiver", "message was null");
            return;
        }
        h.a(context, miPushMessage.getContent(), miPushMessage.getMessageId(), miPushMessage.getNotifyId(), (byte) 1, true);
    }

    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        e.a("PluginXiaomiPlatformsReceiver", "onNotificationMessageArrived is called. " + miPushMessage);
        if (miPushMessage == null) {
            e.a("PluginXiaomiPlatformsReceiver", "message was null");
            return;
        }
        try {
            h.a(context, miPushMessage.getContent(), miPushMessage.getMessageId(), miPushMessage.getNotifyId(), (byte) 1, false);
        } catch (Throwable th) {
            e.g("PluginXiaomiPlatformsReceiver", "#unexcepted - action onNotificationMessageArrived error:" + th);
        }
    }

    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        e.a("PluginXiaomiPlatformsReceiver", "onCommandResult is called. " + miPushCommandMessage);
        if (miPushCommandMessage == null) {
            e.a("PluginXiaomiPlatformsReceiver", "message was null");
            return;
        }
        try {
            if ("register".equals(miPushCommandMessage.getCommand())) {
                String str = null;
                if (miPushCommandMessage.getResultCode() == 0) {
                    str = MiPushClient.getRegId(context);
                }
                g.a().a(context.getApplicationContext(), str);
            }
        } catch (Throwable th) {
            e.g("PluginXiaomiPlatformsReceiver", "#unexcepted - action onCommandResult error:" + th);
        }
    }

    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        if (miPushCommandMessage == null) {
            return;
        }
        if (miPushCommandMessage.getResultCode() == 0) {
            e.d("PluginXiaomiPlatformsReceiver", "xiao mi push register success");
        } else {
            e.j("PluginXiaomiPlatformsReceiver", "xiao mi push register failed - errorCode:" + miPushCommandMessage.getResultCode() + ",reason:" + miPushCommandMessage.getReason());
        }
    }
}
