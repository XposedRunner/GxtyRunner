package com.example.gita.gxty.utils;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ActivityCollector */
public class a {
    public static List<Activity> a = new ArrayList();

    public static void a(Activity activity) {
        a.add(activity);
    }

    public static void b(Activity activity) {
        a.remove(activity);
    }

    public static void a() {
        for (Activity activity : a) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static void c(Activity activity) {
        if (activity != null) {
            if (a.contains(activity)) {
                a.remove(activity);
            }
            activity.finish();
        }
    }

    public static void a(Class<?> cls) {
        Activity activity = null;
        for (Activity activity2 : a) {
            Activity activity22;
            if (!activity22.getClass().equals(cls)) {
                activity22 = activity;
            }
            activity = activity22;
        }
        c(activity);
    }
}
