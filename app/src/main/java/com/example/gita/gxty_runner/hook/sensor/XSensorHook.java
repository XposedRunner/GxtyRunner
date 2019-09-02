package com.example.gita.gxty_runner.hook.sensor;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class XSensorHook {


    private static long lastUpdateTimeMillis = 0;


    public static void hook(final ClassLoader classLoader) {

        XposedHelpers.findAndHookMethod("android.hardware.SensorManager",
                classLoader, "registerListener",
                SensorEventListener.class, Sensor.class, int.class,
                Common.instantiate(SensorManagerRegisterListenerMethodHook.class));

        XposedHelpers.findAndHookMethod("android.hardware.SensorManager",
                classLoader,
                "unregisterListener",
                SensorEventListener.class,
                Common.instantiate(SensorManagerUnregisterListenerMethodHook.class));


        Class sensorEventQueueClazz =
                XposedHelpers.findClass("android.hardware.SystemSensorManager$SensorEventQueue", classLoader);
        XposedBridge.hookAllMethods(sensorEventQueueClazz,
                "dispatchSensorEvent",
                Common.instantiate(SensorEventQueueDispatchSensorEventMethodHook.class));

    }


}
