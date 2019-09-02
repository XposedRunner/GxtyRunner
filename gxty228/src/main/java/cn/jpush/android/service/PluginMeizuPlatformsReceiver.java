package cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import cn.jpush.android.a;
import cn.jpush.android.c.g;
import cn.jpush.android.c.h;
import cn.jpush.android.d.e;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import org.json.JSONObject;

public class PluginMeizuPlatformsReceiver extends MzPushMessageReceiver {
    private Context a;

    public void onReceive(Context context, Intent intent) {
        try {
            this.a = context.getApplicationContext();
        } catch (Throwable th) {
        }
        super.onReceive(context, intent);
    }

    public void onRegister(Context context, String str) {
        e.d("PluginMeizuPlatformsReceiver", "onRegister is called");
    }

    public void onMessage(Context context, String str) {
        e.d("PluginMeizuPlatformsReceiver", "onMessage is called");
    }

    public void onUnRegister(Context context, boolean z) {
        e.f("PluginMeizuPlatformsReceiver", "onUnRegister is called");
    }

    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
        e.f("PluginMeizuPlatformsReceiver", "onPushStatus is called");
    }

    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        e.f("PluginMeizuPlatformsReceiver", "onRegisterStatus:" + String.valueOf(registerStatus));
        String str = null;
        if (registerStatus != null) {
            str = registerStatus.getPushId();
            e.h("PluginMeizuPlatformsReceiver", "PushId is " + String.valueOf(str));
        }
        try {
            g.a().a(context, str);
        } catch (Throwable th) {
            e.h("PluginMeizuPlatformsReceiver", "Update pushId unexpected error:" + th.getMessage());
        }
    }

    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        e.d("PluginMeizuPlatformsReceiver", "onUnRegisterStatus:" + String.valueOf(unRegisterStatus));
    }

    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
        e.d("PluginMeizuPlatformsReceiver", "onSubTagsStatus:" + String.valueOf(subTagsStatus));
    }

    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
        e.f("PluginMeizuPlatformsReceiver", "onSubAliasStatus:" + String.valueOf(subAliasStatus));
    }

    public void onNotificationArrived(Context context, String str, String str2, String str3) {
        e.f("PluginMeizuPlatformsReceiver", "onNotificationArrived:title:" + String.valueOf(str) + ",content:" + String.valueOf(str2) + ",extra:" + String.valueOf(str3));
        h.a(context, a(str3), "", 0, (byte) 3, false);
    }

    public void onNotificationClicked(Context context, String str, String str2, String str3) {
        e.f("PluginMeizuPlatformsReceiver", "onNotificationClicked:title:" + String.valueOf(str) + ",content:" + String.valueOf(str2) + ",extra:" + String.valueOf(str3));
        h.a(context, a(str3), "", 0, (byte) 3, true);
    }

    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        try {
            if (this.a == null) {
                this.a = a.e;
            }
            int identifier = this.a.getResources().getIdentifier("mz_push_notification_small_icon", "drawable", this.a.getPackageName());
            if (identifier != 0) {
                pushNotificationBuilder.setmStatusbarIcon(identifier);
            }
        } catch (Throwable th) {
            e.h("PluginMeizuPlatformsReceiver", "set meizu statusbar icon error:" + th.toString());
        }
    }

    private static String a(String str) {
        try {
            return new JSONObject(str).optString("JMessageExtra");
        } catch (Throwable th) {
            e.h("PluginMeizuPlatformsReceiver", "parse extra error " + th);
            return null;
        }
    }
}
