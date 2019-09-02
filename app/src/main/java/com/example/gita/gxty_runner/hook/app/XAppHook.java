package com.example.gita.gxty_runner.hook.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.gita.gxty_runner.GxtyRunner;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class XAppHook {

    public static void hook(final ClassLoader classLoader) {
        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager",
                classLoader,
                "getInstalledApplications",
                int.class,
                Common.instantiate(GetInstalledApplicationsMethodHook.class));

        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager",
                classLoader,
                "getInstalledPackages",
                int.class,
                Common.instantiate(GetInstalledPackagesMethodHook.class));

        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager",
                classLoader,
                "getPackageInfo",
                String.class, int.class,
                Common.instantiate(GetPackageInfoMethodHook.class));

        XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager",
                classLoader,
                "getApplicationInfo",
                String.class, int.class,
                Common.instantiate(GetApplicationInfoMethodHook.class));

        XposedHelpers.findAndHookMethod("android.app.ActivityManager",
                classLoader,
                "getRunningServices",
                int.class,
                Common.instantiate(GetRunningServicesMethodHook.class));

        XposedHelpers.findAndHookMethod("android.app.ActivityManager",
                classLoader,
                "getRunningTasks",
                int.class,
                Common.instantiate(GetRunningTasksMethodHook.class));

        XposedHelpers.findAndHookMethod("android.app.ActivityManager",
                classLoader,
                "getRunningAppProcesses",
                Common.instantiate(GetRunningAppProcessesMethodHook.class));

        XposedHelpers.findAndHookMethod("com.blankj.utilcode.util.AppUtils",
                classLoader,
                "isAppRoot",
                Common.instantiate(IsAppRootMethodHook.class));

        XposedHelpers.findAndHookMethod("java.io.File",
                classLoader,
                "exists",
                Common.instantiate(FileExistsMethodHook.class));

        XposedHelpers.findAndHookMethod("android.os.Build",
                classLoader,
                "getString",
                String.class,
                Common.instantiate(BuildGetStringMethodHook.class));

        XposedHelpers.findAndHookMethod("java.lang.Throwable",
                classLoader,
                "getOurStackTrace",
                Common.instantiate(GetOurStackTraceMethodHook.class));

        XposedHelpers.findAndHookMethod("java.io.FileInputStream",
                classLoader,
                "open",
                String.class,
                Common.instantiate(FileInputStreamOpenMethodHook.class));

        XposedHelpers.findAndHookMethod("android.provider.Settings.Secure",
                classLoader,
                "getString",
                ContentResolver.class,
                String.class,
                Common.instantiate(SecureGetStringMethodHook.class));


        XposedHelpers.findAndHookMethod("android.app.SharedPreferencesImpl",
                classLoader,
                "getBoolean",
                String.class, boolean.class,
                Common.instantiate(SharedPreferencesImplGetBooleanMethodHook.class));


        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.AdsActivity",
                classLoader,
                "onCreate",
                Bundle.class,
                Common.instantiate(AdsActivityOnCreateMethodHook.class));


        XposedHelpers.findAndHookMethod("android.view.View",
                classLoader,
                "setVisibility",
                int.class,
                Common.instantiate(ViewSetVisibility.class));


        try {
            Class clazzBannerView =
                    XposedHelpers.findClass("com.qq.e.ads.banner.BannerView", classLoader);
            Class clazzADSize =
                    XposedHelpers.findClass("com.qq.e.ads.banner.ADSize", classLoader);
            XposedHelpers.findAndHookConstructor(clazzBannerView,
                    Activity.class, clazzADSize, String.class, String.class,
                    Common.instantiate(BannerViewConstructorMethodHook.class));

            for (Method method : clazzBannerView.getDeclaredMethods()) {
                if (Modifier.isPublic(method.getModifiers())
                        && !Modifier.isAbstract(method.getModifiers())) {
                    XposedBridge.hookMethod(method,
                            Common.instantiate(BannerViewMethodHook.class));
                }
            }
        } catch (XposedHelpers.ClassNotFoundError ignored) {
        }

        try {
            Class clazzSplashAD =
                    XposedHelpers.findClass("com.qq.e.ads.splash.SplashAD", classLoader);
            Class clazzSplashADListener =
                    XposedHelpers.findClass("com.qq.e.ads.splash.SplashADListener", classLoader);
            XposedHelpers.findAndHookConstructor(clazzSplashAD,
                    Activity.class, ViewGroup.class, View.class, String.class, String.class, clazzSplashADListener, int.class,
                    Common.instantiate(SplashADConstructorMethodHook.class));
            for (Method method : clazzSplashAD.getDeclaredMethods()) {
                if (Modifier.isPublic(method.getModifiers())
                        && !Modifier.isAbstract(method.getModifiers())) {
                    XposedBridge.hookMethod(method,
                            Common.instantiate(SplashADMethodHook.class));
                }
            }
        } catch (XposedHelpers.ClassNotFoundError ignored) {
        }

        try {
            Class clazzNativeExpressAD =
                    XposedHelpers.findClass("com.qq.e.ads.nativ.NativeExpressAD.NativeExpressAD", classLoader);
            Class clazzADSize =
                    XposedHelpers.findClass("com.qq.e.ads.nativ.ADSize", classLoader);
            Class clazzNativeExpressADListener =
                    XposedHelpers.findClass("com.qq.e.ads.nativ.NativeExpressAD.NativeExpressADListener", classLoader);

            XposedHelpers.findAndHookConstructor(clazzNativeExpressAD,
                    Context.class, clazzADSize, String.class, String.class, clazzNativeExpressADListener,
                    Common.instantiate(NativeExpressADConstructorMethodHook.class));

            for (Method method : clazzNativeExpressAD.getDeclaredMethods()) {
                if (Modifier.isPublic(method.getModifiers())
                        && !Modifier.isAbstract(method.getModifiers())) {
                    XposedBridge.hookMethod(method,
                            Common.instantiate(NativeExpressADMethodHook.class));
                }
            }

        } catch (XposedHelpers.ClassNotFoundError ignored) {
        }

        try {
            Class clazzGDTLogger =
                    XposedHelpers.findClass("com.qq.e.comm.util.GDTLogger", classLoader);
            for (Method method : clazzGDTLogger.getDeclaredMethods()) {
                if (Modifier.isPublic(method.getModifiers())
                        && !Modifier.isAbstract(method.getModifiers())) {
                    XposedBridge.hookMethod(method,
                            Common.instantiate(GDTLoggerMethodHook.class));
                }
            }

        } catch (XposedHelpers.ClassNotFoundError ignored) {
        }

        try {
            Class clazzAdView = XposedHelpers.findClass("com.baidu.mobads.AdView", classLoader);
            Class clazzAdSize = XposedHelpers.findClass("com.baidu.mobads.AdSize", classLoader);
            XposedHelpers.findAndHookConstructor(clazzAdView,
                    Context.class, AttributeSet.class, boolean.class, clazzAdSize, String.class,
                    Common.instantiate(AdViewConstructorMethodHook.class));

            mainLoop:
            for (Method method : clazzAdView.getDeclaredMethods()) {
                for (Method viewMethod : View.class.getDeclaredMethods()) {
                    if (viewMethod.getName().equals(method.getName())) {
                        continue mainLoop;
                    }
                }
                if (Modifier.isPublic(method.getModifiers())
                        && !Modifier.isAbstract(method.getModifiers())) {
                    XposedBridge.hookMethod(method,
                            Common.instantiate(AdViewMethodHook.class));
                }
            }

        } catch (XposedHelpers.ClassNotFoundError ignored) {
        }


        for (int i = 'a'; i < 'z'; i++) {
            try {
                Class clazz = XposedHelpers.findClass(
                        "com.example.gita.gxty.utils.a." + ((char) i), classLoader);
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.getReturnType() == boolean.class
                            && Modifier.isPublic(method.getModifiers())
                            && !Modifier.isAbstract(method.getModifiers())
                            && Modifier.isStatic(method.getModifiers())) {
                        XposedBridge.hookMethod(method, XC_MethodReplacement.returnConstant(false));
                    }
                }
            } catch (XposedHelpers.ClassNotFoundError ignored) {
                break;
            }
        }
    }


}
