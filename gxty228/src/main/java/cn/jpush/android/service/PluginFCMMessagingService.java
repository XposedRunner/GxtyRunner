package cn.jpush.android.service;

import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.a;
import cn.jpush.android.api.b;
import cn.jpush.android.c.h;
import cn.jpush.android.d.e;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.messaging.RemoteMessage.Notification;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class PluginFCMMessagingService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        e.d("PluginFCMMessagingService", "onMessageReceived is called:" + remoteMessage);
        Notification notification = remoteMessage.getNotification();
        Map data = remoteMessage.getData();
        if (data == null || data.isEmpty()) {
            e.h("PluginFCMMessagingService", "data is null");
            return;
        }
        Bundle bundle = new Bundle();
        for (Entry entry : data.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        if (notification != null) {
            try {
                String string = bundle.getString("JMessageExtra");
                int a = b.a(new JSONObject(string).optString("msg_id"), 0);
                ((NotificationManager) getSystemService("notification")).notify(a, a(bundle, notification, a));
                h.a(this, string, "", a, (byte) 8, false);
                return;
            } catch (Throwable th) {
                e.g("PluginFCMMessagingService", "#unexcepted - action handleNotification error:" + th);
                return;
            }
        }
        c.a(this, bundle, "intent.plugin.platform.ON_MESSAGING");
        JCoreInterface.sendAction(this, a.a, bundle);
    }

    private android.app.Notification a(Bundle bundle, Notification notification, int i) {
        int i2;
        Builder smallIcon;
        android.app.Notification build;
        Intent intent;
        CharSequence title = notification.getTitle();
        CharSequence body = notification.getBody();
        Object clickAction = notification.getClickAction();
        e.d("PluginFCMMessagingService", "收到通知 title:" + String.valueOf(title) + ",content:" + String.valueOf(body) + ",clickAction:" + String.valueOf(clickAction));
        if (TextUtils.isEmpty(title)) {
            title = a.d;
        }
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                i2 = applicationInfo.metaData.getInt("com.google.firebase.messaging.default_notification_icon");
                if (i2 == 0) {
                    e.b("PluginFCMMessagingService", "did not config fcm icon meta");
                    if (a.b == 0) {
                        i2 = a.b;
                    } else {
                        try {
                            i2 = a.e.getPackageManager().getApplicationInfo(a.e.getPackageName(), 0).icon;
                            e.e("PluginFCMMessagingService", "JPush.mPackageInconId == 0 ?, get icon from application info.");
                        } catch (Throwable th) {
                            e.f("PluginFCMMessagingService", "failed to get application info and icon.", th);
                        }
                    }
                }
                if (VERSION.SDK_INT < 11) {
                    smallIcon = new Builder(a.e).setContentTitle(title).setContentText(body).setTicker(body).setSmallIcon(i2);
                    b.a(smallIcon, "JPush", (CharSequence) "Notification", 0, -1);
                    if (VERSION.SDK_INT >= 16) {
                        build = smallIcon.build();
                    } else if (VERSION.SDK_INT < 11) {
                        build = smallIcon.getNotification();
                    } else {
                        build = null;
                    }
                } else {
                    build = new android.app.Notification(i2, body, System.currentTimeMillis());
                }
                if (TextUtils.isEmpty(clickAction)) {
                    intent = new Intent(clickAction);
                    intent.setPackage(getPackageName());
                    intent.putExtras(bundle);
                } else {
                    intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                    if (intent == null) {
                        intent = new Intent();
                    }
                    intent.putExtras(bundle);
                }
                intent.setFlags(335544320);
                build.contentIntent = PendingIntent.getActivity(this, i, intent, AMapEngineUtils.HALF_MAX_P20_WIDTH);
                build.flags |= 16;
                build.flags |= 1;
                build.defaults = -1;
                return build;
            }
        } catch (Throwable th2) {
            e.h("PluginFCMMessagingService", "get fcm icon res error" + th2);
        }
        i2 = 0;
        if (i2 == 0) {
            e.b("PluginFCMMessagingService", "did not config fcm icon meta");
            if (a.b == 0) {
                i2 = a.e.getPackageManager().getApplicationInfo(a.e.getPackageName(), 0).icon;
                e.e("PluginFCMMessagingService", "JPush.mPackageInconId == 0 ?, get icon from application info.");
            } else {
                i2 = a.b;
            }
        }
        if (VERSION.SDK_INT < 11) {
            build = new android.app.Notification(i2, body, System.currentTimeMillis());
        } else {
            smallIcon = new Builder(a.e).setContentTitle(title).setContentText(body).setTicker(body).setSmallIcon(i2);
            b.a(smallIcon, "JPush", (CharSequence) "Notification", 0, -1);
            if (VERSION.SDK_INT >= 16) {
                build = smallIcon.build();
            } else if (VERSION.SDK_INT < 11) {
                build = null;
            } else {
                build = smallIcon.getNotification();
            }
        }
        if (TextUtils.isEmpty(clickAction)) {
            intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if (intent == null) {
                intent = new Intent();
            }
            intent.putExtras(bundle);
        } else {
            intent = new Intent(clickAction);
            intent.setPackage(getPackageName());
            intent.putExtras(bundle);
        }
        intent.setFlags(335544320);
        build.contentIntent = PendingIntent.getActivity(this, i, intent, AMapEngineUtils.HALF_MAX_P20_WIDTH);
        build.flags |= 16;
        build.flags |= 1;
        build.defaults = -1;
        return build;
    }

    public void onDeletedMessages() {
        e.d("PluginFCMMessagingService", "onDeletedMessages is called");
        super.onDeletedMessages();
    }

    public void onMessageSent(String str) {
        e.d("PluginFCMMessagingService", "onMessageSent is called " + String.valueOf(str));
        super.onMessageSent(str);
    }

    public void onSendError(String str, Exception exception) {
        e.b("PluginFCMMessagingService", "onSendError is called", exception);
        super.onSendError(str, exception);
    }
}
