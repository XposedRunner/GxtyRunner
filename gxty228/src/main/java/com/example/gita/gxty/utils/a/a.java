package com.example.gita.gxty.utils.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Process;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: CheckHook */
public class a {
    public static boolean a(Context context) {
        return b(context) || c(context) || a();
    }

    public static boolean b(Context context) {
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(128);
        if (installedApplications == null) {
            return false;
        }
        boolean z = false;
        for (ApplicationInfo applicationInfo : installedApplications) {
            boolean z2;
            if (applicationInfo.packageName.equals("de.robv.android.xposed.installer")) {
                Log.wtf("HookDetection", "Xposed found on the system.");
                z = true;
            }
            if (applicationInfo.packageName.equals("com.saurik.substrate")) {
                Log.wtf("HookDetection", "Substrate found on the system.");
                z2 = true;
            } else {
                z2 = z;
            }
            z = z2;
        }
        return z;
    }

    public static boolean c(Context context) {
        boolean z = false;
        try {
            throw new Exception("blah");
        } catch (Exception e) {
            int i = 0;
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (stackTraceElement.getClassName().equals("com.android.internal.os.ZygoteInit")) {
                    i++;
                    if (i == 2) {
                        Log.wtf("HookDetection", "Substrate is active on the device.");
                        z = true;
                    }
                }
                if (stackTraceElement.getClassName().equals("com.saurik.substrate.MS$2") && stackTraceElement.getMethodName().equals("invoked")) {
                    Log.wtf("HookDetection", "A method on the stack trace has been hooked using Substrate.");
                    z = true;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("main")) {
                    Log.wtf("HookDetection", "Xposed is active on the device.");
                    z = true;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("handleHookedMethod")) {
                    Log.wtf("HookDetection", "A method on the stack trace has been hooked using Xposed.");
                    z = true;
                }
            }
            return z;
        }
    }

    public static boolean a() {
        boolean z = false;
        boolean z2;
        try {
            Set<String> hashSet = new HashSet();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/maps"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.endsWith(".so") || readLine.endsWith(".jar")) {
                    hashSet.add(readLine.substring(readLine.lastIndexOf(" ") + 1));
                }
            }
            for (String str : hashSet) {
                if (str.contains("com.saurik.substrate")) {
                    Log.wtf("HookDetection", "Substrate shared object found: " + str);
                    z = true;
                }
                if (str.contains("XposedBridge.jar")) {
                    Log.wtf("HookDetection", "Xposed JAR found: " + str);
                    z2 = true;
                } else {
                    z2 = z;
                }
                z = z2;
            }
            bufferedReader.close();
            return z;
        } catch (Exception e) {
            Exception exception = e;
            z2 = false;
            Log.wtf("HookDetection", exception.toString());
            return z2;
        }
    }
}
