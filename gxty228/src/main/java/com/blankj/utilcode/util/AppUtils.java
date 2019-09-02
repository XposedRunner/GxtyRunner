package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.blankj.utilcode.util.ShellUtils.CommandResult;
import com.blankj.utilcode.util.Utils.OnAppStatusChangedListener;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public final class AppUtils {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static class AppInfo {
        private Drawable icon;
        private boolean isSystem;
        private String name;
        private String packageName;
        private String packagePath;
        private int versionCode;
        private String versionName;

        public Drawable getIcon() {
            return this.icon;
        }

        public void setIcon(Drawable drawable) {
            this.icon = drawable;
        }

        public boolean isSystem() {
            return this.isSystem;
        }

        public void setSystem(boolean z) {
            this.isSystem = z;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public void setPackageName(String str) {
            this.packageName = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getPackagePath() {
            return this.packagePath;
        }

        public void setPackagePath(String str) {
            this.packagePath = str;
        }

        public int getVersionCode() {
            return this.versionCode;
        }

        public void setVersionCode(int i) {
            this.versionCode = i;
        }

        public String getVersionName() {
            return this.versionName;
        }

        public void setVersionName(String str) {
            this.versionName = str;
        }

        public AppInfo(String str, String str2, Drawable drawable, String str3, String str4, int i, boolean z) {
            setName(str2);
            setIcon(drawable);
            setPackageName(str);
            setPackagePath(str3);
            setVersionName(str4);
            setVersionCode(i);
            setSystem(z);
        }

        public String toString() {
            return "pkg name: " + getPackageName() + "\napp icon: " + getIcon() + "\napp name: " + getName() + "\napp path: " + getPackagePath() + "\napp v name: " + getVersionName() + "\napp v code: " + getVersionCode() + "\nis system: " + isSystem();
        }
    }

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void registerAppStatusChangedListener(@NonNull Object obj, @NonNull OnAppStatusChangedListener onAppStatusChangedListener) {
        if (obj == null) {
            throw new NullPointerException("Argument 'obj' of type Object (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (onAppStatusChangedListener == null) {
            throw new NullPointerException("Argument 'listener' of type Utils.OnAppStatusChangedListener (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            Utils.getActivityLifecycle().addListener(obj, onAppStatusChangedListener);
        }
    }

    public static void unregisterAppStatusChangedListener(@NonNull Object obj) {
        if (obj == null) {
            throw new NullPointerException("Argument 'obj' of type Object (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Utils.getActivityLifecycle().removeListener(obj);
    }

    public static void installApp(String str) {
        installApp(getFileByPath(str));
    }

    public static void installApp(File file) {
        if (isFileExists(file)) {
            Utils.getApp().startActivity(getInstallAppIntent(file, true));
        }
    }

    public static void installApp(Activity activity, String str, int i) {
        installApp(activity, getFileByPath(str), i);
    }

    public static void installApp(Activity activity, File file, int i) {
        if (isFileExists(file)) {
            activity.startActivityForResult(getInstallAppIntent(file), i);
        }
    }

    public static boolean installAppSilent(String str) {
        return installAppSilent(getFileByPath(str), null);
    }

    public static boolean installAppSilent(File file) {
        return installAppSilent(file, null);
    }

    public static boolean installAppSilent(String str, String str2) {
        return installAppSilent(getFileByPath(str), str2);
    }

    public static boolean installAppSilent(File file, String str) {
        return installAppSilent(file, str, isDeviceRooted());
    }

    public static boolean installAppSilent(File file, String str, boolean z) {
        if (!isFileExists(file)) {
            return false;
        }
        CommandResult execCmd = ShellUtils.execCmd("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm install " + (str == null ? "" : str + " ") + ('\"' + file.getAbsolutePath() + '\"'), z);
        if (execCmd.successMsg != null && execCmd.successMsg.toLowerCase().contains("success")) {
            return true;
        }
        Log.e("AppUtils", "installAppSilent successMsg: " + execCmd.successMsg + ", errorMsg: " + execCmd.errorMsg);
        return false;
    }

    public static void uninstallApp(String str) {
        if (!isSpace(str)) {
            Utils.getApp().startActivity(getUninstallAppIntent(str, true));
        }
    }

    public static void uninstallApp(Activity activity, String str, int i) {
        if (!isSpace(str)) {
            activity.startActivityForResult(getUninstallAppIntent(str), i);
        }
    }

    public static boolean uninstallAppSilent(String str) {
        return uninstallAppSilent(str, false);
    }

    public static boolean uninstallAppSilent(String str, boolean z) {
        return uninstallAppSilent(str, z, isDeviceRooted());
    }

    public static boolean uninstallAppSilent(String str, boolean z, boolean z2) {
        if (isSpace(str)) {
            return false;
        }
        CommandResult execCmd = ShellUtils.execCmd("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm uninstall " + (z ? "-k " : "") + str, z2);
        if (execCmd.successMsg != null && execCmd.successMsg.toLowerCase().contains("success")) {
            return true;
        }
        Log.e("AppUtils", "uninstallAppSilent successMsg: " + execCmd.successMsg + ", errorMsg: " + execCmd.errorMsg);
        return false;
    }

    public static boolean isAppInstalled(@NonNull String str) {
        if (str != null) {
            return (isSpace(str) || Utils.getApp().getPackageManager().getLaunchIntentForPackage(str) == null) ? false : true;
        } else {
            throw new NullPointerException("Argument 'packageName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    public static boolean isAppInstalled(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'action' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (str2 == null) {
            throw new NullPointerException("Argument 'category' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            Intent intent = new Intent(str);
            intent.addCategory(str2);
            if (Utils.getApp().getPackageManager().resolveActivity(intent, 0) != null) {
                return true;
            }
            return false;
        }
    }

    public static boolean isAppRoot() {
        CommandResult execCmd = ShellUtils.execCmd("echo root", true);
        if (execCmd.result == 0) {
            return true;
        }
        if (execCmd.errorMsg != null) {
            Log.d("AppUtils", "isAppRoot() called" + execCmd.errorMsg);
        }
        return false;
    }

    public static boolean isAppDebug() {
        return isAppDebug(Utils.getApp().getPackageName());
    }

    public static boolean isAppDebug(String str) {
        if (isSpace(str)) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = Utils.getApp().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo == null || (applicationInfo.flags & 2) == 0) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isAppSystem() {
        return isAppSystem(Utils.getApp().getPackageName());
    }

    public static boolean isAppSystem(String str) {
        if (isSpace(str)) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = Utils.getApp().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo == null || (applicationInfo.flags & 1) == 0) {
                return false;
            }
            return true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isAppForeground() {
        return Utils.isAppForeground();
    }

    public static boolean isAppForeground(@NonNull String str) {
        if (str != null) {
            return !isSpace(str) && str.equals(getForegroundProcessName());
        } else {
            throw new NullPointerException("Argument 'packageName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    public static void launchApp(String str) {
        if (!isSpace(str)) {
            Utils.getApp().startActivity(getLaunchAppIntent(str, true));
        }
    }

    public static void launchApp(Activity activity, String str, int i) {
        if (!isSpace(str)) {
            activity.startActivityForResult(getLaunchAppIntent(str), i);
        }
    }

    public static void relaunchApp() {
        Intent launchIntentForPackage = Utils.getApp().getPackageManager().getLaunchIntentForPackage(Utils.getApp().getPackageName());
        if (launchIntentForPackage != null) {
            Utils.getApp().startActivity(Intent.makeRestartActivityTask(launchIntentForPackage.getComponent()));
            System.exit(0);
        }
    }

    public static void launchAppDetailsSettings() {
        launchAppDetailsSettings(Utils.getApp().getPackageName());
    }

    public static void launchAppDetailsSettings(String str) {
        if (!isSpace(str)) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + str));
            Utils.getApp().startActivity(intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH));
        }
    }

    public static void exitApp() {
        List activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            ((Activity) activityList.get(size)).finish();
        }
        System.exit(0);
    }

    public static Drawable getAppIcon() {
        return getAppIcon(Utils.getApp().getPackageName());
    }

    public static Drawable getAppIcon(String str) {
        Drawable drawable = null;
        if (!isSpace(str)) {
            try {
                PackageManager packageManager = Utils.getApp().getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                if (packageInfo != null) {
                    drawable = packageInfo.applicationInfo.loadIcon(packageManager);
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return drawable;
    }

    public static String getAppPackageName() {
        return Utils.getApp().getPackageName();
    }

    public static String getAppName() {
        return getAppName(Utils.getApp().getPackageName());
    }

    public static String getAppName(String str) {
        if (isSpace(str)) {
            return "";
        }
        try {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            return packageInfo == null ? null : packageInfo.applicationInfo.loadLabel(packageManager).toString();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAppPath() {
        return getAppPath(Utils.getApp().getPackageName());
    }

    public static String getAppPath(String str) {
        if (isSpace(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 0);
            return packageInfo == null ? null : packageInfo.applicationInfo.sourceDir;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAppVersionName() {
        return getAppVersionName(Utils.getApp().getPackageName());
    }

    public static String getAppVersionName(String str) {
        if (isSpace(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 0);
            return packageInfo == null ? null : packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getAppVersionCode() {
        return getAppVersionCode(Utils.getApp().getPackageName());
    }

    public static int getAppVersionCode(String str) {
        int i = -1;
        if (!isSpace(str)) {
            try {
                PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 0);
                if (packageInfo != null) {
                    i = packageInfo.versionCode;
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static Signature[] getAppSignature() {
        return getAppSignature(Utils.getApp().getPackageName());
    }

    public static Signature[] getAppSignature(String str) {
        Signature[] signatureArr = null;
        if (!isSpace(str)) {
            try {
                PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 64);
                if (packageInfo != null) {
                    signatureArr = packageInfo.signatures;
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return signatureArr;
    }

    public static String getAppSignatureSHA1() {
        return getAppSignatureSHA1(Utils.getApp().getPackageName());
    }

    public static String getAppSignatureSHA1(String str) {
        return getAppSignatureHash(str, "SHA1");
    }

    public static String getAppSignatureSHA256() {
        return getAppSignatureSHA256(Utils.getApp().getPackageName());
    }

    public static String getAppSignatureSHA256(String str) {
        return getAppSignatureHash(str, "SHA256");
    }

    public static String getAppSignatureMD5() {
        return getAppSignatureMD5(Utils.getApp().getPackageName());
    }

    public static String getAppSignatureMD5(String str) {
        return getAppSignatureHash(str, "MD5");
    }

    private static String getAppSignatureHash(String str, String str2) {
        if (isSpace(str)) {
            return "";
        }
        Signature[] appSignature = getAppSignature(str);
        if (appSignature == null || appSignature.length <= 0) {
            return "";
        }
        return bytes2HexString(hashTemplate(appSignature[0].toByteArray(), str2)).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }

    public static AppInfo getAppInfo() {
        return getAppInfo(Utils.getApp().getPackageName());
    }

    public static AppInfo getAppInfo(String str) {
        try {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            return getBean(packageManager, packageManager.getPackageInfo(str, 0));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<AppInfo> getAppsInfo() {
        List<AppInfo> arrayList = new ArrayList();
        PackageManager packageManager = Utils.getApp().getPackageManager();
        for (PackageInfo bean : packageManager.getInstalledPackages(0)) {
            AppInfo bean2 = getBean(packageManager, bean);
            if (bean2 != null) {
                arrayList.add(bean2);
            }
        }
        return arrayList;
    }

    private static AppInfo getBean(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageManager == null || packageInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        return new AppInfo(packageInfo.packageName, applicationInfo.loadLabel(packageManager).toString(), applicationInfo.loadIcon(packageManager), applicationInfo.sourceDir, packageInfo.versionName, packageInfo.versionCode, (applicationInfo.flags & 1) != 0);
    }

    private static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    private static File getFileByPath(String str) {
        return isSpace(str) ? null : new File(str);
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDeviceRooted() {
        String str = "su";
        for (String str2 : new String[]{"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/"}) {
            if (new File(str2 + str).exists()) {
                return true;
            }
        }
        return false;
    }

    private static byte[] hashTemplate(byte[] bArr, String str) {
        byte[] bArr2 = null;
        if (bArr != null && bArr.length > 0) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                instance.update(bArr);
                bArr2 = instance.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return bArr2;
    }

    private static String bytes2HexString(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return "";
        }
        int length = bArr.length;
        if (length <= 0) {
            return "";
        }
        char[] cArr = new char[(length << 1)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = HEX_DIGITS[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = HEX_DIGITS[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    private static Intent getInstallAppIntent(File file) {
        return getInstallAppIntent(file, false);
    }

    private static Intent getInstallAppIntent(File file, boolean z) {
        Uri fromFile;
        Intent intent = new Intent("android.intent.action.VIEW");
        String str = "application/vnd.android.package-archive";
        if (VERSION.SDK_INT < 24) {
            fromFile = Uri.fromFile(file);
        } else {
            intent.setFlags(1);
            fromFile = FileProvider.getUriForFile(Utils.getApp(), Utils.getApp().getPackageName() + ".utilcode.provider", file);
        }
        intent.setDataAndType(fromFile, str);
        if (z) {
            return intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH);
        }
        return intent;
    }

    private static Intent getUninstallAppIntent(String str) {
        return getUninstallAppIntent(str, false);
    }

    private static Intent getUninstallAppIntent(String str, boolean z) {
        Intent intent = new Intent("android.intent.action.DELETE");
        intent.setData(Uri.parse("package:" + str));
        return z ? intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH) : intent;
    }

    private static Intent getLaunchAppIntent(String str) {
        return getLaunchAppIntent(str, false);
    }

    private static Intent getLaunchAppIntent(String str, boolean z) {
        Intent launchIntentForPackage = Utils.getApp().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return null;
        }
        return z ? launchIntentForPackage.addFlags(AMapEngineUtils.MAX_P20_WIDTH) : launchIntentForPackage;
    }

    private static String getForegroundProcessName() {
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
}
