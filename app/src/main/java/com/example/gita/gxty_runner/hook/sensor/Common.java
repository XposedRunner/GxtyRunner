package com.example.gita.gxty_runner.hook.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XCallback;

class Common {

    public static SensorEventListener detectorSensorEventListener;
    public static SensorEventListener counterSensorEventListener;
    public static Sensor detector;
    public static Sensor counter;
    public static SensorThread sensorThread = new SensorThread();

    private Common() {
    }


    @SuppressWarnings("unchecked")
    public static <T extends XCallback> T instantiate(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            return (T) XC_MethodReplacement.DO_NOTHING;
        }
    }

}
