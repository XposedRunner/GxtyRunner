package com.blankj.utilcode.util;

import android.os.Environment;
import android.os.storage.StorageManager;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SDCardUtils {
    private SDCardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isSDCardEnableByEnvironment() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String getSDCardPathByEnvironment() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "";
    }

    public static boolean isSDCardEnable() {
        return !getSDCardPaths().isEmpty();
    }

    public static List<String> getSDCardPaths(boolean z) {
        List<String> arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) Utils.getApp().getSystemService("storage");
        try {
            Class cls = Class.forName("android.os.storage.StorageVolume");
            Method method = StorageManager.class.getMethod("getVolumeList", new Class[0]);
            Method method2 = cls.getMethod("getPath", new Class[0]);
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Object invoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(invoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(invoke, i);
                String str = (String) method2.invoke(obj, new Object[0]);
                if (z == ((Boolean) method3.invoke(obj, new Object[0])).booleanValue()) {
                    arrayList.add(str);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
        }
        return arrayList;
    }

    public static List<String> getSDCardPaths() {
        StorageManager storageManager = (StorageManager) Utils.getApp().getSystemService("storage");
        ArrayList arrayList = new ArrayList();
        try {
            Method method = StorageManager.class.getMethod("getVolumePaths", new Class[0]);
            method.setAccessible(true);
            return Arrays.asList((String[]) method.invoke(storageManager, new Object[0]));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return arrayList;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return arrayList;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return arrayList;
        }
    }
}
