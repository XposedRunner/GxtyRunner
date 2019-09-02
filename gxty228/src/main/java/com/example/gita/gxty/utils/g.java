package com.example.gita.gxty.utils;

import android.app.Application;
import android.content.Context;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jpush.android.api.JPushInterface;

/* compiled from: JpushUtils */
public class g {
    public static void a(Application application) {
        try {
            JAnalyticsInterface.setDebugMode(h.a);
            JAnalyticsInterface.init(application);
            JPushInterface.setDebugMode(h.a);
            JPushInterface.init(application);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(final Context context, final String str) {
        try {
            a(context);
            new Thread() {
                public void run() {
                    try {
                        AnonymousClass1.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    JPushInterface.setAlias(context, 1, str);
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context) {
        try {
            JPushInterface.deleteAlias(context, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
