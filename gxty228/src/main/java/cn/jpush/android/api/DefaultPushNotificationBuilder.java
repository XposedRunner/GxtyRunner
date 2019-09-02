package cn.jpush.android.api;

import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.InboxStyle;
import android.app.Notification.Style;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.widget.RemoteViews;
import cn.jpush.android.a;
import cn.jpush.android.d.e;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class DefaultPushNotificationBuilder implements PushNotificationBuilder {
    private static final String DEFAULT_NOTIFICATION_CHANNEL_ID = "JPush";
    private static final String DEFAULT_NOTIFICATION_CHANNEL_NAME = "Notification";
    private static final String TAG = "DefaultPushNotificationBuilder";
    private static boolean hasCreateNotificationChannel = false;

    public String getDeveloperArg0() {
        return null;
    }

    RemoteViews buildContentView(String str, String str2) {
        return null;
    }

    void resetNotificationParams(Notification notification) {
    }

    Notification getNotification(Builder builder) {
        try {
            if (VERSION.SDK_INT >= 16) {
                return builder.build();
            }
            return builder.getNotification();
        } catch (Throwable th) {
            e.d(TAG, "Build notification error:", th);
            return null;
        }
    }

    public Notification buildNotification(Map<String, String> map) {
        String str;
        String str2 = a.d;
        String str3 = "";
        CharSequence charSequence = "";
        Object obj = "";
        int i = 0;
        CharSequence charSequence2 = "";
        String str4 = "";
        if (map.containsKey(JPushInterface.EXTRA_ALERT)) {
            str = (String) map.get(JPushInterface.EXTRA_ALERT);
        } else {
            str = str3;
        }
        if (TextUtils.isEmpty(str)) {
            e.h(TAG, "No notification content to show. Give up.");
            return null;
        }
        int parseInt;
        int i2;
        if (map.containsKey(JPushInterface.EXTRA_NOTIFICATION_TITLE)) {
            str2 = (String) map.get(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        }
        if (map.containsKey(JPushInterface.EXTRA_BIG_TEXT)) {
            charSequence = (String) map.get(JPushInterface.EXTRA_BIG_TEXT);
        }
        if (map.containsKey(JPushInterface.EXTRA_INBOX)) {
            obj = (String) map.get(JPushInterface.EXTRA_INBOX);
        }
        if (map.containsKey(JPushInterface.EXTRA_NOTI_PRIORITY)) {
            i = Integer.parseInt((String) map.get(JPushInterface.EXTRA_NOTI_PRIORITY));
        }
        if (map.containsKey(JPushInterface.EXTRA_NOTI_CATEGORY)) {
            charSequence2 = (String) map.get(JPushInterface.EXTRA_NOTI_CATEGORY);
        }
        if (map.containsKey(JPushInterface.EXTRA_BIG_PIC_PATH)) {
            str4 = (String) map.get(JPushInterface.EXTRA_BIG_PIC_PATH);
        }
        if (map.containsKey(JPushInterface.EXTRA_ALERT_TYPE)) {
            parseInt = Integer.parseInt((String) map.get(JPushInterface.EXTRA_ALERT_TYPE));
        } else {
            parseInt = -1;
        }
        if (parseInt < -1 || parseInt > 7) {
            i2 = -1;
        } else {
            i2 = parseInt;
        }
        if (a.e != null) {
            parseInt = a.e.getResources().getIdentifier("jpush_notification_icon", "drawable", a.e.getPackageName());
            if (parseInt == 0) {
                if (a.b != 0) {
                    parseInt = a.b;
                } else {
                    try {
                        parseInt = a.e.getPackageManager().getApplicationInfo(a.e.getPackageName(), 0).icon;
                        e.e(TAG, "JPush.mPackageInconId == 0 ?, get icon from application info.");
                    } catch (Throwable th) {
                        e.f(TAG, "failed to get application info and icon.", th);
                        return null;
                    }
                }
            }
            RemoteViews buildContentView = buildContentView(str, str2);
            if (VERSION.SDK_INT >= 11) {
                Builder builder = new Builder(a.e);
                builder.setContentTitle(str2).setContentText(str).setTicker(str).setSmallIcon(parseInt);
                b.a(builder, DEFAULT_NOTIFICATION_CHANNEL_ID, DEFAULT_NOTIFICATION_CHANNEL_NAME, i, i2);
                if (VERSION.SDK_INT >= 16) {
                    Style bigTextStyle;
                    if (!TextUtils.isEmpty(charSequence)) {
                        bigTextStyle = new BigTextStyle();
                        bigTextStyle.bigText(charSequence);
                        builder.setStyle(bigTextStyle);
                    }
                    if (!TextUtils.isEmpty(obj)) {
                        Style inboxStyle = new InboxStyle();
                        try {
                            TreeMap treeMap = new TreeMap();
                            JSONObject jSONObject = new JSONObject(obj);
                            Iterator keys = jSONObject.keys();
                            while (keys.hasNext()) {
                                str3 = (String) keys.next();
                                treeMap.put(str3, jSONObject.optString(str3));
                            }
                            for (String str32 : treeMap.values()) {
                                inboxStyle.addLine(str32);
                            }
                            inboxStyle.setSummaryText(" + " + jSONObject.length() + " new messages");
                        } catch (Throwable th2) {
                            e.j(TAG, "Set inbox style error: " + th2.getMessage());
                        }
                        builder.setStyle(inboxStyle);
                    }
                    if (!TextUtils.isEmpty(str4)) {
                        e.e(TAG, "Set notification BPS with picture path:" + str4);
                        try {
                            bigTextStyle = new BigPictureStyle();
                            bigTextStyle.bigPicture(BitmapFactory.decodeFile(str4));
                            builder.setStyle(bigTextStyle);
                        } catch (OutOfMemoryError e) {
                            e.h(TAG, "Create bitmap failed caused by OutOfMemoryError.");
                            e.printStackTrace();
                        } catch (Exception e2) {
                            e.h(TAG, "Create big picture style failed.");
                            e2.printStackTrace();
                        }
                    }
                    if (i != 0) {
                        if (i == 1) {
                            builder.setPriority(1);
                        } else if (i == 2) {
                            builder.setPriority(2);
                        } else if (i == -1) {
                            builder.setPriority(-1);
                        } else if (i == -2) {
                            builder.setPriority(-2);
                        } else {
                            builder.setPriority(0);
                        }
                    }
                    if (!TextUtils.isEmpty(charSequence2)) {
                        if (VERSION.SDK_INT >= 21) {
                            try {
                                Class.forName("android.app.Notification$Builder").getDeclaredMethod("setCategory", new Class[]{String.class}).invoke(builder, new Object[]{charSequence2});
                            } catch (ClassNotFoundException e3) {
                                e3.printStackTrace();
                            } catch (NoSuchMethodException e4) {
                                e4.printStackTrace();
                            } catch (Exception e22) {
                                e22.printStackTrace();
                            }
                        } else {
                            e.h(TAG, "Device rom SDK < 21, can not set notification category!");
                        }
                    }
                }
                if (buildContentView != null) {
                    builder.setContent(buildContentView);
                } else {
                    e.e(TAG, " Use default notification view! ");
                }
                builder.setDefaults(i2);
                return getNotification(builder);
            }
            Notification notification = new Notification(parseInt, str, System.currentTimeMillis());
            resetNotificationParams(notification);
            notification.defaults = i2;
            if (str2 == null) {
                str2 = a.d;
            }
            if (buildContentView != null) {
                notification.contentView = buildContentView;
            } else {
                b.a(notification, a.e, str2, str, null);
            }
            return notification;
        }
        e.g(TAG, "Can't find valid context when build notification.");
        return null;
    }

    public String toString() {
        return "";
    }
}
