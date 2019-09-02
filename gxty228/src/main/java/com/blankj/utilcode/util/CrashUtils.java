package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public final class CrashUtils {
    private static final UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();
    private static final String FILE_SEP = System.getProperty("file.separator");
    @SuppressLint({"SimpleDateFormat"})
    private static final Format FORMAT = new SimpleDateFormat("MM-dd HH-mm-ss");
    private static final UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER = new UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable th) {
            if (th != null) {
                String format = CrashUtils.FORMAT.format(new Date(System.currentTimeMillis()));
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("************* Log Head ****************\nTime Of Crash      : " + format + "\nDevice Manufacturer: " + Build.MANUFACTURER + "\nDevice Model       : " + Build.MODEL + "\nAndroid Version    : " + VERSION.RELEASE + "\nAndroid SDK        : " + VERSION.SDK_INT + "\nApp VersionName    : " + CrashUtils.versionName + "\nApp VersionCode    : " + CrashUtils.versionCode + "\n************* Log Head ****************\n\n");
                Writer stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                th.printStackTrace(printWriter);
                for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                    cause.printStackTrace(printWriter);
                }
                printWriter.flush();
                stringBuilder.append(stringWriter.toString());
                String stringBuilder2 = stringBuilder.toString();
                String str = (CrashUtils.dir == null ? CrashUtils.defaultDir : CrashUtils.dir) + format + ".txt";
                if (CrashUtils.createOrExistsFile(str)) {
                    CrashUtils.input2File(stringBuilder2, str);
                } else {
                    Log.e("CrashUtils", "create " + str + " failed!");
                }
                if (CrashUtils.sOnCrashListener != null) {
                    CrashUtils.sOnCrashListener.onCrash(stringBuilder2, th);
                }
                if (CrashUtils.DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    CrashUtils.DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(thread, th);
                }
            } else if (CrashUtils.DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                CrashUtils.DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(thread, null);
            } else {
                Process.killProcess(Process.myPid());
                System.exit(1);
            }
        }
    };
    private static String defaultDir;
    private static String dir;
    private static OnCrashListener sOnCrashListener;
    private static int versionCode;
    private static String versionName;

    public interface OnCrashListener {
        void onCrash(String str, Throwable th);
    }

    static {
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(Utils.getApp().getPackageName(), 0);
            if (packageInfo != null) {
                versionName = packageInfo.versionName;
                versionCode = packageInfo.versionCode;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public static void init() {
        init("");
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public static void init(@NonNull File file) {
        if (file == null) {
            throw new NullPointerException("Argument 'crashDir' of type File (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        init(file.getAbsolutePath(), null);
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public static void init(String str) {
        init(str, null);
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public static void init(OnCrashListener onCrashListener) {
        init("", onCrashListener);
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public static void init(@NonNull File file, OnCrashListener onCrashListener) {
        if (file == null) {
            throw new NullPointerException("Argument 'crashDir' of type File (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        init(file.getAbsolutePath(), onCrashListener);
    }

    @RequiresPermission("android.permission.WRITE_EXTERNAL_STORAGE")
    public static void init(String str, OnCrashListener onCrashListener) {
        if (isSpace(str)) {
            dir = null;
        } else {
            if (!str.endsWith(FILE_SEP)) {
                str = str + FILE_SEP;
            }
            dir = str;
        }
        if (!"mounted".equals(Environment.getExternalStorageState()) || Utils.getApp().getExternalCacheDir() == null) {
            defaultDir = Utils.getApp().getCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        } else {
            defaultDir = Utils.getApp().getExternalCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        }
        sOnCrashListener = onCrashListener;
        Thread.setDefaultUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
    }

    private static void input2File(final String str, final String str2) {
        try {
            if (((Boolean) Executors.newSingleThreadExecutor().submit(new Callable<Boolean>() {
                public Boolean call() {
                    Boolean valueOf;
                    IOException e;
                    Throwable th;
                    BufferedWriter bufferedWriter;
                    try {
                        bufferedWriter = new BufferedWriter(new FileWriter(str2, true));
                        try {
                            bufferedWriter.write(str);
                            valueOf = Boolean.valueOf(true);
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } catch (IOException e3) {
                            e = e3;
                            try {
                                e.printStackTrace();
                                valueOf = Boolean.valueOf(false);
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (IOException e22) {
                                        e22.printStackTrace();
                                    }
                                }
                                return valueOf;
                            } catch (Throwable th2) {
                                th = th2;
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (IOException e222) {
                                        e222.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (IOException e4) {
                        e = e4;
                        bufferedWriter = null;
                        e.printStackTrace();
                        valueOf = Boolean.valueOf(false);
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        return valueOf;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedWriter = null;
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        throw th;
                    }
                    return valueOf;
                }
            }).get()).booleanValue()) {
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
        Log.e("CrashUtils", "write crash info to " + str2 + " failed!");
    }

    private static boolean createOrExistsFile(String str) {
        boolean z = false;
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return z;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return z;
        }
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
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
}
