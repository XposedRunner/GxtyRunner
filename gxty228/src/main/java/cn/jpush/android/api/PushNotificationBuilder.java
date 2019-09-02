package cn.jpush.android.api;

import android.app.Notification;
import java.util.Map;

public interface PushNotificationBuilder {
    Notification buildNotification(Map<String, String> map);

    String getDeveloperArg0();
}
