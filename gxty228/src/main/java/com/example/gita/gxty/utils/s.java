package com.example.gita.gxty.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Html;
import android.widget.Toast;
import com.example.gita.gxty.MyApplication;
import java.util.Arrays;
import java.util.List;

/* compiled from: TUtils */
public class s {
    public static boolean a = true;

    public static void a(CharSequence charSequence) {
        if (a) {
            try {
                Toast.makeText(MyApplication.e(), charSequence, 0).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void b(CharSequence charSequence) {
        if (a) {
            try {
                Toast.makeText(MyApplication.e(), charSequence, 1).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static CharSequence a(String str) {
        if (str == null) {
            return "";
        }
        try {
            return Html.fromHtml(str);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String b(String str) {
        return str + "/w/400/h/400";
    }

    public static void a(Context context, @NonNull String str) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (Arrays.asList(runningAppProcessInfo.pkgList).contains(str)) {
                        h.a("[killBackgroundProcesses]：" + runningAppProcessInfo.processName);
                        activityManager.killBackgroundProcesses(str);
                    }
                }
            }
        } catch (Exception e) {
            h.a(e);
        }
    }

    public static void a(Context context) {
        try {
            List<RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
            if (runningServices != null) {
                for (RunningServiceInfo runningServiceInfo : runningServices) {
                    if (context.getPackageName().equals(runningServiceInfo.service.getPackageName())) {
                        h.a("[stopService]:" + runningServiceInfo.service.getClassName());
                        context.stopService(new Intent(context, Class.forName(runningServiceInfo.service.getClassName())));
                    }
                }
            }
        } catch (Exception e) {
            h.a(e);
        }
    }
}
