package com.example.gita.gxty_runner;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Keep;

import com.example.gita.gxty_runner.hook.app.XAppHook;
import com.example.gita.gxty_runner.hook.autorun.XAutoRunHook;
import com.example.gita.gxty_runner.hook.bluetooth.XBluetoothHook;
import com.example.gita.gxty_runner.hook.map.XMapHook;
import com.example.gita.gxty_runner.hook.sensor.XSensorHook;
import com.example.gita.gxty_runner.hook.settings.XSettingsHook;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

@Keep
public class GxtyRunner implements IXposedHookZygoteInit, IXposedHookLoadPackage {

    public static final String GXTY_PACKAGE_NAME = "com.example.gita.gxty";
    public static final String MODULE_PACKAGE_NAME = "com.example.gita.gxty_runner";
    public static final String MODULE_LOADED_PATH = null;
    public static final String MODULE_PREFS_NAME = MODULE_PACKAGE_NAME + "_prefs";

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        Field field = GxtyRunner.class.getDeclaredField("MODULE_LOADED_PATH");
        field.setAccessible(!field.isAccessible());
        field.set(null, startupParam.modulePath);
        field.setAccessible(!field.isAccessible());
        GxtyRunner.log("Module init done.");
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        try {
            try {
                XposedHelpers.callStaticMethod(
                        XposedHelpers.findClass("net.androidwing.hotxposed.HotXposed",
                                this.getClass().getClassLoader()),
                        "hook",
                        new Class[]{Class.class, XC_LoadPackage.LoadPackageParam.class},
                        XposedHelpers.findClass("com.example.gita.gxty_runner.DebugHookerDispatcher",
                                this.getClass().getClassLoader()),
                        loadPackageParam);
            } catch (XposedHelpers.InvocationTargetError ignored) {
            }
        } catch (XposedHelpers.ClassNotFoundError e) {
            if (GxtyRunner.GXTY_PACKAGE_NAME.equals(loadPackageParam.packageName)) {
                GxtyRunner.log("package found, hooking...");
                XposedHelpers.findAndHookMethod(Application.class,
                        "attach",
                        Context.class,
                        new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) {
                                Context context = (Context) param.args[0];
                                ClassLoader classLoader = context.getClassLoader();
                                XAppHook.hook(classLoader);
                                XMapHook.hook(classLoader);
                                XBluetoothHook.hook(classLoader);
                                XSensorHook.hook(classLoader);
                                XAutoRunHook.hook(classLoader);
                                XSettingsHook.hook(classLoader);
                            }
                        });

            }

        }
    }

    public static void log(String log) {
        try {
            XposedHelpers.findClass("net.androidwing.hotxposed.HotXposed",
                    GxtyRunner.class.getClassLoader());
            XposedBridge.log("[gxty_runner(Debug)] " + log);
        } catch (XposedHelpers.ClassNotFoundError ignored) {
            XposedBridge.log("[gxty_runner] " + log);
        }
    }


}
