package com.example.gita.gxty_runner;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.gita.gxty_runner.hook.app.XAppHook;
import com.example.gita.gxty_runner.hook.autorun.XAutoRunHook;
import com.example.gita.gxty_runner.hook.bluetooth.XBluetoothHook;
import com.example.gita.gxty_runner.hook.map.XMapHook;
import com.example.gita.gxty_runner.hook.sensor.XSensorHook;
import com.example.gita.gxty_runner.hook.settings.XSettingsHook;

import net.androidwing.hotxposed.IHookerDispatcher;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class DebugHookerDispatcher implements IHookerDispatcher {
    @Override
    public void dispatch(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        if (GxtyRunner.GXTY_PACKAGE_NAME.equals(loadPackageParam.packageName)) {
            GxtyRunner.log(loadPackageParam.packageName + " found, hooking...");

            XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    Context context = (Context) param.args[0];

                    ClassLoader classLoader = context.getClassLoader();
                    SharedPreferences sharedPreferences = context.getSharedPreferences(GxtyRunner.MODULE_PREFS_NAME, Context.MODE_PRIVATE);

                    XposedHelpers.findAndHookMethod("com.example.gita.gxty.utils.l", classLoader, "b", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) {
                            param.setResult("http://gxtest.wizarcan.com/api/");
                        }
                    });

                    XposedHelpers.findAndHookConstructor("com.example.gita.gxty.utils.h", classLoader, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) {
                            XposedHelpers.setBooleanField(param.thisObject, "a", true);
                        }
                    });

                    XposedHelpers.findAndHookMethod("com.blankj.utilcode.util.AppUtils", classLoader, "getAppSignatureSHA1", XC_MethodReplacement.returnConstant("DD:BB:8E:EF:99:3D:58:C2:2B:A8:31:BC:AA:13:58:C7:A1:E5:6D:68"));

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
