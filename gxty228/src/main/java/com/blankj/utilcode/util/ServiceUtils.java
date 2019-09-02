package com.blankj.utilcode.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.content.ServiceConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ServiceUtils {
    private ServiceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Set getAllRunningServices() {
        List<RunningServiceInfo> runningServices = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        Set hashSet = new HashSet();
        if (runningServices == null || runningServices.size() == 0) {
            return null;
        }
        for (RunningServiceInfo runningServiceInfo : runningServices) {
            hashSet.add(runningServiceInfo.service.getClassName());
        }
        return hashSet;
    }

    public static void startService(String str) {
        try {
            startService(Class.forName(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startService(Class<?> cls) {
        Utils.getApp().startService(new Intent(Utils.getApp(), cls));
    }

    public static boolean stopService(String str) {
        try {
            return stopService(Class.forName(str));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean stopService(Class<?> cls) {
        return Utils.getApp().stopService(new Intent(Utils.getApp(), cls));
    }

    public static void bindService(String str, ServiceConnection serviceConnection, int i) {
        try {
            bindService(Class.forName(str), serviceConnection, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bindService(Class<?> cls, ServiceConnection serviceConnection, int i) {
        Utils.getApp().bindService(new Intent(Utils.getApp(), cls), serviceConnection, i);
    }

    public static void unbindService(ServiceConnection serviceConnection) {
        Utils.getApp().unbindService(serviceConnection);
    }

    public static boolean isServiceRunning(Class<?> cls) {
        return isServiceRunning(cls.getName());
    }

    public static boolean isServiceRunning(String str) {
        List<RunningServiceInfo> runningServices = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (runningServices == null || runningServices.size() == 0) {
            return false;
        }
        for (RunningServiceInfo runningServiceInfo : runningServices) {
            if (str.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
