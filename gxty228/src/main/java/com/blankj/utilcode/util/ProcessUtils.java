package com.blankj.utilcode.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ProcessUtils {
    private ProcessUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String getForegroundProcessName() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        if (VERSION.SDK_INT > 21) {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
            List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            Log.i("ProcessUtils", queryIntentActivities.toString());
            if (queryIntentActivities.size() <= 0) {
                Log.i("ProcessUtils", "getForegroundProcessName: noun of access to usage information.");
                return "";
            }
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(Utils.getApp().getPackageName(), 0);
                AppOpsManager appOpsManager = (AppOpsManager) Utils.getApp().getSystemService("appops");
                if (appOpsManager.checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) != 0) {
                    intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
                    Utils.getApp().startActivity(intent);
                }
                if (appOpsManager.checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) != 0) {
                    Log.i("ProcessUtils", "getForegroundProcessName: refuse to device usage stats.");
                    return "";
                }
                UsageStatsManager usageStatsManager = (UsageStatsManager) Utils.getApp().getSystemService("usagestats");
                List queryUsageStats;
                if (usageStatsManager != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    queryUsageStats = usageStatsManager.queryUsageStats(4, currentTimeMillis - 604800000, currentTimeMillis);
                } else {
                    queryUsageStats = null;
                }
                if (r0 == null || r0.isEmpty()) {
                    return null;
                }
                UsageStats usageStats = null;
                for (UsageStats usageStats2 : r0) {
                    UsageStats usageStats3;
                    if (usageStats != null && usageStats2.getLastTimeUsed() <= usageStats.getLastTimeUsed()) {
                        usageStats3 = usageStats;
                    }
                    usageStats = usageStats3;
                }
                return usageStats == null ? null : usageStats.getPackageName();
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @RequiresPermission("android.permission.KILL_BACKGROUND_PROCESSES")
    public static Set<String> getAllBackgroundProcesses() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningAppProcesses();
        Object hashSet = new HashSet();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                Collections.addAll(hashSet, runningAppProcessInfo.pkgList);
            }
        }
        return hashSet;
    }

    @RequiresPermission("android.permission.KILL_BACKGROUND_PROCESSES")
    public static Set<String> killAllBackgroundProcesses() {
        ActivityManager activityManager = (ActivityManager) Utils.getApp().getSystemService("activity");
        List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        Set<String> hashSet = new HashSet();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            for (String str : runningAppProcessInfo.pkgList) {
                activityManager.killBackgroundProcesses(str);
                hashSet.add(str);
            }
        }
        for (RunningAppProcessInfo runningAppProcessInfo2 : activityManager.getRunningAppProcesses()) {
            for (Object remove : runningAppProcessInfo2.pkgList) {
                hashSet.remove(remove);
            }
        }
        return hashSet;
    }

    @RequiresPermission("android.permission.KILL_BACKGROUND_PROCESSES")
    public static boolean killBackgroundProcesses(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'packageName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        ActivityManager activityManager = (ActivityManager) Utils.getApp().getSystemService("activity");
        List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
            return true;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (Arrays.asList(runningAppProcessInfo.pkgList).contains(str)) {
                activityManager.killBackgroundProcesses(str);
            }
        }
        List<RunningAppProcessInfo> runningAppProcesses2 = activityManager.getRunningAppProcesses();
        if (runningAppProcesses2 == null || runningAppProcesses2.size() == 0) {
            return true;
        }
        for (RunningAppProcessInfo runningAppProcessInfo2 : runningAppProcesses2) {
            if (Arrays.asList(runningAppProcessInfo2.pkgList).contains(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMainProcess() {
        return Utils.getApp().getPackageName().equals(getCurrentProcessName());
    }

    public static String getCurrentProcessName() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
            return null;
        }
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && runningAppProcessInfo.processName != null) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }
}
