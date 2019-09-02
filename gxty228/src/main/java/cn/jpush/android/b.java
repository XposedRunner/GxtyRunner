package cn.jpush.android;

import android.content.Context;
import android.os.Bundle;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.MultiSpHelper;
import cn.jpush.android.a.i;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.d.e;
import cn.jpush.android.service.c;

public final class b {
    public static void a(Context context, int i, boolean z) {
        if (z) {
            int b = i.b();
            e.a("JPushCommon", "number in queue: " + b);
            if (i < b) {
                b -= i;
                e.a("JPushCommon", "decreaseNotification:" + b);
                cn.jpush.android.api.b.a(context, b);
            }
            MultiSpHelper.commitInt(context, "notification_num", i);
            return;
        }
        Bundle bundle = new Bundle();
        c.a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 2);
        bundle.putInt("notification_maxnum", i);
        JCoreInterface.sendAction(context, a.a, bundle);
    }

    public static int a(Context context) {
        int i = MultiSpHelper.getInt(context, "notification_num", JPushInterface.a);
        e.a("JPushCommon", "max notification:" + i);
        return i;
    }

    public static void a(Context context, String str, boolean z) {
        if (z || JCoreInterface.canCallDirect()) {
            MultiSpHelper.commitString(context, "setting_silence_push_time", str);
            return;
        }
        Bundle bundle = new Bundle();
        c.a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 4);
        bundle.putString("silence_push_time", str);
        JCoreInterface.sendAction(context, a.a, bundle);
    }

    public static String b(Context context) {
        return MultiSpHelper.getString(context, "setting_push_time", "");
    }

    public static void b(Context context, String str, boolean z) {
        if (z || JCoreInterface.canCallDirect()) {
            MultiSpHelper.commitString(context, "setting_push_time", str);
            return;
        }
        Bundle bundle = new Bundle();
        c.a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 3);
        bundle.putString("enable_push_time", str);
        JCoreInterface.sendAction(context, a.a, bundle);
    }

    public static void a(Context context, String str, String str2, boolean z) {
        if (z || JCoreInterface.canCallDirect()) {
            MultiSpHelper.commitString(context, "jpush_save_custom_builder" + str, str2);
            return;
        }
        Bundle bundle = new Bundle();
        c.a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 1);
        bundle.putString("notification_buidler_id", str);
        bundle.putString("notification_buidler", str2);
        JCoreInterface.sendAction(context, a.a, bundle);
    }

    public static void a(Context context, int i, String str) {
        MultiSpHelper.commitString(context, "pluginPlatformRegid" + i, str);
    }

    public static String a(Context context, int i) {
        return MultiSpHelper.getString(context, "pluginPlatformRegid" + i, null);
    }

    public static void b(Context context, int i, boolean z) {
        MultiSpHelper.commitBoolean(context, "pluginPlatformRegidupload" + i, z);
    }

    public static boolean b(Context context, int i) {
        return MultiSpHelper.getBoolean(context, "pluginPlatformRegidupload" + i, false);
    }
}
