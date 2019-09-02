package com.blankj.utilcode.util;

import android.os.Build.VERSION;
import android.os.Environment;

public class PathUtils {
    private PathUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String getRootPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    public static String getDataPath() {
        return Environment.getDataDirectory().getAbsolutePath();
    }

    public static String getDownloadCachePath() {
        return Environment.getDownloadCacheDirectory().getAbsolutePath();
    }

    public static String getInternalAppDataPath() {
        if (VERSION.SDK_INT < 24) {
            return Utils.getApp().getApplicationInfo().dataDir;
        }
        return Utils.getApp().getDataDir().getAbsolutePath();
    }

    public static String getInternalAppCodeCacheDir() {
        if (VERSION.SDK_INT < 21) {
            return Utils.getApp().getApplicationInfo().dataDir + "/code_cache";
        }
        return Utils.getApp().getCodeCacheDir().getAbsolutePath();
    }

    public static String getInternalAppCachePath() {
        return Utils.getApp().getCacheDir().getAbsolutePath();
    }

    public static String getInternalAppDbsPath() {
        return Utils.getApp().getApplicationInfo().dataDir + "/databases";
    }

    public static String getInternalAppDbPath(String str) {
        return Utils.getApp().getDatabasePath(str).getAbsolutePath();
    }

    public static String getInternalAppFilesPath() {
        return Utils.getApp().getFilesDir().getAbsolutePath();
    }

    public static String getInternalAppSpPath() {
        return Utils.getApp().getApplicationInfo().dataDir + "shared_prefs";
    }

    public static String getInternalAppNoBackupFilesPath() {
        if (VERSION.SDK_INT < 21) {
            return Utils.getApp().getApplicationInfo().dataDir + "no_backup";
        }
        return Utils.getApp().getNoBackupFilesDir().getAbsolutePath();
    }

    public static String getExternalStoragePath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String getExternalMusicPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath();
    }

    public static String getExternalPodcastsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).getAbsolutePath();
    }

    public static String getExternalRingtonesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES).getAbsolutePath();
    }

    public static String getExternalAlarmsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getAbsolutePath();
    }

    public static String getExternalNotificationsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath();
    }

    public static String getExternalPicturesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }

    public static String getExternalMoviesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getAbsolutePath();
    }

    public static String getExternalDownloadsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    public static String getExternalDcimPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
    }

    public static String getExternalDocumentsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        if (VERSION.SDK_INT < 19) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents";
        }
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
    }

    public static String getExternalAppDataPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalCacheDir().getParentFile().getAbsolutePath();
    }

    public static String getExternalAppCachePath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalCacheDir().getAbsolutePath();
    }

    public static String getExternalAppFilesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalFilesDir(null).getAbsolutePath();
    }

    public static String getExternalAppMusicPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath();
    }

    public static String getExternalAppPodcastsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PODCASTS).getAbsolutePath();
    }

    public static String getExternalAppRingtonesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_RINGTONES).getAbsolutePath();
    }

    public static String getExternalAppAlarmsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_ALARMS).getAbsolutePath();
    }

    public static String getExternalAppNotificationsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath();
    }

    public static String getExternalAppPicturesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }

    public static String getExternalAppMoviesPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath();
    }

    public static String getExternalAppDownloadPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    public static String getExternalAppDcimPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath();
    }

    public static String getExternalAppDocumentsPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        if (VERSION.SDK_INT < 19) {
            return Utils.getApp().getExternalFilesDir(null).getAbsolutePath() + "/Documents";
        }
        return Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
    }

    public static String getExternalAppObbPath() {
        if (isExternalStorageDisable()) {
            return "";
        }
        return Utils.getApp().getObbDir().getAbsolutePath();
    }

    private static boolean isExternalStorageDisable() {
        return !"mounted".equals(Environment.getExternalStorageState());
    }
}
