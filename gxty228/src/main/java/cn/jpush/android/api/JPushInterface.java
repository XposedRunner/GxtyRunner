package cn.jpush.android.api;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.MultiSpHelper;
import cn.jpush.android.a.b;
import cn.jpush.android.a.c;
import cn.jpush.android.a.d;
import cn.jpush.android.a.f;
import cn.jpush.android.a.k;
import cn.jpush.android.c.g;
import cn.jpush.android.d.e;
import cn.jpush.android.data.JPushLocalNotification;
import cn.jpush.android.service.ServiceInterface;
import com.tencent.tauth.AuthActivity;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class JPushInterface {
    public static final String ACTION_CONNECTION_CHANGE = "cn.jpush.android.intent.CONNECTION";
    public static final String ACTION_MESSAGE_RECEIVED = "cn.jpush.android.intent.MESSAGE_RECEIVED";
    public static final String ACTION_NOTIFICATION_CLICK_ACTION = "cn.jpush.android.intent.NOTIFICATION_CLICK_ACTION";
    public static final String ACTION_NOTIFICATION_OPENED = "cn.jpush.android.intent.NOTIFICATION_OPENED";
    public static final String ACTION_NOTIFICATION_RECEIVED = "cn.jpush.android.intent.NOTIFICATION_RECEIVED";
    public static final String ACTION_NOTIFICATION_RECEIVED_PROXY = "cn.jpush.android.intent.NOTIFICATION_RECEIVED_PROXY";
    public static final String ACTION_REGISTRATION_ID = "cn.jpush.android.intent.REGISTRATION";
    public static final String ACTION_RICHPUSH_CALLBACK = "cn.jpush.android.intent.ACTION_RICHPUSH_CALLBACK";
    public static final String EXTRA_ACTIVITY_PARAM = "cn.jpush.android.ACTIVITY_PARAM";
    public static final String EXTRA_ALERT = "cn.jpush.android.ALERT";
    public static final String EXTRA_ALERT_TYPE = "cn.jpush.android.ALERT_TYPE";
    public static final String EXTRA_APP_KEY = "cn.jpush.android.APPKEY";
    public static final String EXTRA_BIG_PIC_PATH = "cn.jpush.android.BIG_PIC_PATH";
    public static final String EXTRA_BIG_TEXT = "cn.jpush.android.BIG_TEXT";
    public static final String EXTRA_CONNECTION_CHANGE = "cn.jpush.android.CONNECTION_CHANGE";
    public static final String EXTRA_CONTENT_TYPE = "cn.jpush.android.CONTENT_TYPE";
    public static final String EXTRA_EXTRA = "cn.jpush.android.EXTRA";
    public static final String EXTRA_INBOX = "cn.jpush.android.INBOX";
    public static final String EXTRA_MESSAGE = "cn.jpush.android.MESSAGE";
    public static final String EXTRA_MSG_ID = "cn.jpush.android.MSG_ID";
    public static final String EXTRA_NOTIFICATION_ACTION_EXTRA = "cn.jpush.android.NOTIFIACATION_ACTION_EXTRA";
    public static final String EXTRA_NOTIFICATION_DEVELOPER_ARG0 = "cn.jpush.android.NOTIFICATION_DEVELOPER_ARG0";
    public static final String EXTRA_NOTIFICATION_ID = "cn.jpush.android.NOTIFICATION_ID";
    public static final String EXTRA_NOTIFICATION_TITLE = "cn.jpush.android.NOTIFICATION_CONTENT_TITLE";
    public static final String EXTRA_NOTI_CATEGORY = "cn.jpush.android.NOTI_CATEGORY";
    public static final String EXTRA_NOTI_PRIORITY = "cn.jpush.android.NOTI_PRIORITY";
    public static final String EXTRA_NOTI_TYPE = "cn.jpush.android.NOTIFICATION_TYPE";
    public static final String EXTRA_PUSH_ID = "cn.jpush.android.PUSH_ID";
    public static final String EXTRA_REGISTRATION_ID = "cn.jpush.android.REGISTRATION_ID";
    public static final String EXTRA_RICHPUSH_FILE_PATH = "cn.jpush.android.FILE_PATH";
    public static final String EXTRA_RICHPUSH_FILE_TYPE = "cn.jpush.android.FILE_TYPE";
    public static final String EXTRA_RICHPUSH_HTML_PATH = "cn.jpush.android.HTML_PATH";
    public static final String EXTRA_RICHPUSH_HTML_RES = "cn.jpush.android.HTML_RES";
    public static final String EXTRA_STATUS = "cn.jpush.android.STATUS";
    public static final String EXTRA_TITLE = "cn.jpush.android.TITLE";
    public static int a = 5;
    private static final Integer b = Integer.valueOf(0);

    public static class a {
        public static int a = 0;
        public static int b = 6001;
        public static int c = 6002;
        public static int d = 6003;
        public static int e = 6004;
        public static int f = 6005;
        public static int g = 6006;
        public static int h = 6007;
        public static int i = 6008;
        public static int j = 6009;
        public static int k = 6010;
        public static int l = 6011;
        public static int m = 6012;
        public static int n = 6013;
        public static int o = 6014;
        public static int p = 6015;
        public static int q = 6016;
        public static int r = 6017;
        public static int s = 6018;
        public static int t = 6019;
        public static int u = 6020;
        public static int v = 6021;
        public static int w = 6022;
        public static int x = 6023;
        public static int y = 6024;
        public static int z = 6025;
    }

    static {
        JCoreInterface.initActionExtra(cn.jpush.android.a.a, c.class);
        JCoreInterface.initAction(cn.jpush.android.a.a, b.class);
    }

    public static void init(Context context) {
        e.d("JPushInterface", "action:init - sdkVersion:" + ServiceInterface.a() + ", buildId:396");
        a(context);
        if (JCoreInterface.init(context, false) && cn.jpush.android.a.a(context)) {
            if (JCoreInterface.getDebugMode() && !cn.jpush.android.d.a.a(context)) {
                e.d("JPushInterface", "检测到当前没有网络。长连接将在有网络时自动继续建立。");
            }
            if (cn.jpush.android.b.a(context) == -1) {
                setLatestNotificationNumber(context, a);
            }
            ServiceInterface.a(context);
        }
    }

    public static void resumePush(Context context) {
        e.d("JPushInterface", "action:resumePush");
        a(context);
        ServiceInterface.b(context, 1);
        g.a().b(context);
    }

    public static void stopPush(Context context) {
        e.d("JPushInterface", "action:stopPush");
        a(context);
        ServiceInterface.a(context, 1);
        g.a().c(context);
    }

    public static boolean isPushStopped(Context context) {
        a(context);
        return ServiceInterface.c(context);
    }

    public static void setDebugMode(boolean z) {
        JCoreInterface.setDebugMode(z);
    }

    public static void setPushTime(Context context, Set<Integer> set, int i, int i2) {
        a(context);
        if (JCoreInterface.getDebugMode() && !cn.jpush.android.d.a.a(context)) {
            e.d("JPushInterface", "检测到当前没有网络。此动作将在有网络时自动继续执行。");
        }
        if (set == null) {
            a(context, true, "0123456_0^23");
        } else if (set.size() == 0 || set.isEmpty()) {
            a(context, false, "0123456_0^23");
        } else if (i > i2) {
            e.j("JPushInterface", "Invalid time format - startHour should less than endHour");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer num : set) {
                if (num.intValue() > 6 || num.intValue() < 0) {
                    e.j("JPushInterface", "Invalid day format - " + num);
                    return;
                }
                stringBuilder.append(num);
            }
            stringBuilder.append("_");
            stringBuilder.append(i);
            stringBuilder.append("^");
            stringBuilder.append(i2);
            a(context, true, stringBuilder.toString());
        }
    }

    public static void setSilenceTime(Context context, int i, int i2, int i3, int i4) {
        a(context);
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0 || i2 > 59 || i4 > 59 || i3 > 23 || i > 23) {
            e.j("JPushInterface", "Invalid parameter format, startHour and endHour should between 0 ~ 23, startMins and endMins should between 0 ~ 59. ");
        } else if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            ServiceInterface.a(context, "");
            e.d("JPushInterface", "Remove the silence time!");
        } else if (ServiceInterface.a(context, i, i2, i3, i4)) {
            e.d("JPushInterface", "Set Silence PushTime - " + i + " : " + i2 + " -- " + i3 + " : " + i4);
        } else {
            e.j("JPushInterface", "Set Silence PushTime Failed");
        }
    }

    public static String getRegistrationID(Context context) {
        a(context);
        return JCoreInterface.getRegistrationID(context);
    }

    private static void a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
    }

    public static String getUdid(Context context) {
        a(context);
        return JCoreInterface.getDeviceId(context);
    }

    public static void setLatestNotificationNumber(Context context, int i) {
        a(context);
        e.d("JPushInterface", "action:setLatestNotificationNumber : " + i);
        if (i <= 0) {
            e.j("JPushInterface", "maxNum should > 0, Give up action..");
        } else {
            ServiceInterface.c(context, i);
        }
    }

    public static void setDefaultPushNotificationBuilder(DefaultPushNotificationBuilder defaultPushNotificationBuilder) {
        if (defaultPushNotificationBuilder == null) {
            throw new IllegalArgumentException("NULL notification");
        }
        ServiceInterface.a(cn.jpush.android.a.e, b, defaultPushNotificationBuilder);
    }

    public static void clearAllNotifications(Context context) {
        a(context);
        ServiceInterface.b(context);
    }

    public static void clearNotificationById(Context context, int i) {
        a(context);
        ((NotificationManager) context.getSystemService("notification")).cancel(i);
    }

    public static void setPushNotificationBuilder(Integer num, DefaultPushNotificationBuilder defaultPushNotificationBuilder) {
        e.b("JPushInterface", "action:setPushNotificationBuilder - id:" + num);
        if (defaultPushNotificationBuilder == null) {
            throw new IllegalArgumentException("NULL pushNotificationBuilder");
        } else if (num.intValue() <= 0) {
            e.j("JPushInterface", "id should be larger than 0");
        } else {
            ServiceInterface.a(cn.jpush.android.a.e, num, defaultPushNotificationBuilder);
        }
    }

    static boolean a(int i) {
        if (i <= 0) {
            return false;
        }
        if (a(i) != null) {
            return true;
        }
        e.h("JPushInterface", "The builder with id:" + i + " has not been set in your app, use default builder!");
        return false;
    }

    static PushNotificationBuilder b(int i) {
        PushNotificationBuilder a;
        e.b("JPushInterface", "action:getPushNotificationBuilder : " + i);
        if (i <= 0) {
            i = b.intValue();
        }
        try {
            a = a(i);
        } catch (Throwable e) {
            e.a("JPushInterface", "获取记录builder出错!", e);
            a = null;
        }
        if (a != null) {
            return a;
        }
        e.c("JPushInterface", "No developer customized builder. Use default one.");
        return new DefaultPushNotificationBuilder();
    }

    @Deprecated
    public void setAliasAndTags(Context context, String str, Set<String> set) {
        a(context);
        k.a(context, str, set, null, 0, 0);
    }

    @Deprecated
    public static void setAliasAndTags(Context context, String str, Set<String> set, TagAliasCallback tagAliasCallback) {
        a(context);
        k.a(context, str, set, tagAliasCallback, 0, 0);
    }

    @Deprecated
    public static void setTags(Context context, Set<String> set, TagAliasCallback tagAliasCallback) {
        a(context);
        setAliasAndTags(context, null, set, tagAliasCallback);
    }

    @Deprecated
    public static void setAlias(Context context, String str, TagAliasCallback tagAliasCallback) {
        a(context);
        setAliasAndTags(context, str, null, tagAliasCallback);
    }

    public static Set<String> filterValidTags(Set<String> set) {
        return k.a((Set) set);
    }

    public static void setTags(Context context, int i, Set<String> set) {
        a(context);
        k.a(context, i, (Set) set, 1, 2);
    }

    public static void addTags(Context context, int i, Set<String> set) {
        a(context);
        k.a(context, i, (Set) set, 1, 1);
    }

    public static void deleteTags(Context context, int i, Set<String> set) {
        a(context);
        k.a(context, i, (Set) set, 1, 3);
    }

    public static void cleanTags(Context context, int i) {
        a(context);
        k.a(context, i, new HashSet(), 1, 4);
    }

    public static void getAllTags(Context context, int i) {
        a(context);
        k.a(context, i, new HashSet(), 1, 5);
    }

    public static void checkTagBindState(Context context, int i, String str) {
        a(context);
        Set set = null;
        if (!TextUtils.isEmpty(str)) {
            set = new HashSet();
            set.add(str);
        }
        k.a(context, i, set, 1, 6);
    }

    public static void setAlias(Context context, int i, String str) {
        a(context);
        k.a(context, i, str, 2, 2);
    }

    public static void deleteAlias(Context context, int i) {
        a(context);
        k.a(context, i, null, 2, 3);
    }

    public static void getAlias(Context context, int i) {
        a(context);
        k.a(context, i, null, 2, 5);
    }

    public static void setMobileNumber(Context context, int i, String str) {
        a(context);
        f.a();
        e.d("MobileNumberHelper", "action - setMobileNubmer, sequence:" + i + ",mobileNumber:" + str);
        if (ServiceInterface.d(context)) {
            f.a(context, i, a.m, str);
            return;
        }
        if (!(context instanceof Application)) {
            context = context.getApplicationContext();
        }
        if (cn.jpush.android.a.a(context)) {
            Bundle bundle = new Bundle();
            bundle.putString(AuthActivity.ACTION_KEY, "intent.MOBILE_NUMBER");
            bundle.putInt("sequence", i);
            bundle.putString("mobile_number", str);
            JCoreInterface.sendAction(context, cn.jpush.android.a.a, bundle);
            return;
        }
        f.a(context, i, a.j, str);
    }

    public static void reportNotificationOpened(Context context, String str) {
        a(context);
        if (TextUtils.isEmpty(str)) {
            e.j("JPushInterface", "The msgId is not valid - " + str);
        }
        d.a(str, 1028, null, context);
    }

    public static void reportNotificationOpened(Context context, String str, byte b) {
        a(context);
        if (TextUtils.isEmpty(str)) {
            e.j("JPushInterface", "The msgId is not valid - " + str);
        }
        d.a(str, "", b, 1000, context);
    }

    public static boolean getConnectionState(Context context) {
        a(context);
        return JCoreInterface.getConnectionState(context);
    }

    public static void onResume(Context context) {
        a(context);
        JCoreInterface.onResume(context);
    }

    public static void onPause(Context context) {
        a(context);
        JCoreInterface.onPause(context);
    }

    public static void onFragmentResume(Context context, String str) {
        a(context);
        JCoreInterface.onFragmentResume(context, str);
    }

    public static void onFragmentPause(Context context, String str) {
        a(context);
        JCoreInterface.onFragmentPause(context, str);
    }

    public static void onKillProcess(Context context) {
        JCoreInterface.onKillProcess(context);
    }

    public static void setStatisticsSessionTimeout(long j) {
        if (j < 10) {
            e.h("JPushInterface", "sesseion timeout less than 10s");
        } else if (j > 86400) {
            e.h("JPushInterface", "sesseion timeout larger than 1day");
        }
    }

    public static void setStatisticsEnable(boolean z) {
    }

    public static void initCrashHandler(Context context) {
        a(context);
        JCoreInterface.initCrashHandler(context);
    }

    public static void stopCrashHandler(Context context) {
        a(context);
        JCoreInterface.stopCrashHandler(context);
    }

    public static void addLocalNotification(Context context, JPushLocalNotification jPushLocalNotification) {
        a(context);
        cn.jpush.android.service.a.a(context).a(context, jPushLocalNotification, false);
    }

    public static void removeLocalNotification(Context context, long j) {
        a(context);
        Bundle bundle = new Bundle();
        cn.jpush.android.service.c.a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 7);
        bundle.putLong("local_notification_id", j);
        JCoreInterface.sendAction(context, cn.jpush.android.a.a, bundle);
    }

    public static void clearLocalNotifications(Context context) {
        a(context);
        Bundle bundle = new Bundle();
        cn.jpush.android.service.c.a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 8);
        JCoreInterface.sendAction(context, cn.jpush.android.a.a, bundle);
    }

    private static void a(Context context, boolean z, String str) {
        e.c("JPushInterface", "Action:enableNotification");
        MultiSpHelper.commitBoolean(context, "notification_enabled", z);
        if (z) {
            String str2 = "([0-9]|1[0-9]|2[0-3])\\^([0-9]|1[0-9]|2[0-3])";
            if (Pattern.compile("([0-6]{0,7})_((" + str2 + ")|(" + str2 + "-)+(" + str2 + "))").matcher(str).matches()) {
                str2 = cn.jpush.android.b.b(context);
                if (str.equals(str2)) {
                    e.d("JPushInterface", "Already SetPushTime, give up - " + str2);
                    return;
                }
                e.d("JPushInterface", "action:setPushTime - enabled:" + z + ", pushTime:" + str);
                cn.jpush.android.b.b(context, str, false);
                return;
            }
            e.j("JPushInterface", "Invalid time format - " + str);
            return;
        }
        e.d("JPushInterface", "action:setPushTime - closed");
    }

    public static String getStringTags(Set<String> set) {
        return k.b(set);
    }

    private static PushNotificationBuilder a(String str) throws NumberFormatException {
        e.a("JPushInterface", "getRecordPushNotificationBuilder - " + str);
        String string = MultiSpHelper.getString(cn.jpush.android.a.e, "jpush_save_custom_builder" + str, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        e.a("JPushInterface", "Customized builder from saved preference - " + string);
        if (string.startsWith("basic") || string.startsWith("custom")) {
            return BasicPushNotificationBuilder.a(string);
        }
        return MultiActionsNotificationBuilder.parseFromPreference(string);
    }

    public static void requestPermission(Context context) {
        if (context == null) {
            e.h("JPushInterface", "[requestPermission] unexcepted - context was null");
        } else {
            JCoreInterface.requestPermission(context);
        }
    }

    public static void setDaemonAction(String str) {
        JCoreInterface.setDaemonAction(str);
    }

    public static void setPowerSaveMode(Context context, boolean z) {
        JCoreInterface.setPowerSaveMode(context, z);
    }
}
