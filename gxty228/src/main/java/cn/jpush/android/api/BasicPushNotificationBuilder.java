package cn.jpush.android.api;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.content.Context;
import android.os.Build.VERSION;
import cn.jpush.android.a;

public class BasicPushNotificationBuilder extends DefaultPushNotificationBuilder {
    protected Context a;
    public String developerArg0 = "developerArg0";
    public int notificationDefaults = -2;
    public int notificationFlags = 16;
    public int statusBarDrawable = a.b;

    public BasicPushNotificationBuilder(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        this.a = context;
    }

    public String getDeveloperArg0() {
        return this.developerArg0;
    }

    @TargetApi(11)
    Notification getNotification(Builder builder) {
        Notification build;
        if (this.notificationDefaults != -2) {
            builder.setDefaults(this.notificationDefaults);
        }
        builder.setSmallIcon(this.statusBarDrawable);
        if (VERSION.SDK_INT >= 16) {
            build = builder.build();
        } else {
            build = builder.getNotification();
        }
        build.flags = this.notificationFlags | 1;
        return build;
    }

    void resetNotificationParams(Notification notification) {
        notification.defaults = this.notificationDefaults;
        notification.flags = this.notificationFlags;
        notification.icon = this.statusBarDrawable;
    }

    public String toString() {
        return "basic_____" + a();
    }

    String a() {
        return this.notificationDefaults + "_____" + this.notificationFlags + "_____" + this.statusBarDrawable + "_____" + this.developerArg0;
    }

    void a(String[] strArr) throws NumberFormatException {
        this.notificationDefaults = Integer.parseInt(strArr[1]);
        this.notificationFlags = Integer.parseInt(strArr[2]);
        this.statusBarDrawable = Integer.parseInt(strArr[3]);
        if (5 == strArr.length) {
            this.developerArg0 = strArr[4];
        }
    }

    static PushNotificationBuilder a(String str) {
        PushNotificationBuilder basicPushNotificationBuilder;
        String[] split = str.split("_____");
        Object obj = split[0];
        if ("basic".equals(obj)) {
            basicPushNotificationBuilder = new BasicPushNotificationBuilder(a.e);
        } else if ("custom".equals(obj)) {
            basicPushNotificationBuilder = new CustomPushNotificationBuilder(a.e);
        } else {
            basicPushNotificationBuilder = new BasicPushNotificationBuilder(a.e);
        }
        basicPushNotificationBuilder.a(split);
        return basicPushNotificationBuilder;
    }
}
