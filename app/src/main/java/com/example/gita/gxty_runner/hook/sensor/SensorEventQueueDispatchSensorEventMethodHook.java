package com.example.gita.gxty_runner.hook.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class SensorEventQueueDispatchSensorEventMethodHook extends XC_MethodHook {
    private long lastUpdateTimeMillis = System.currentTimeMillis();

    @Override
    protected void beforeHookedMethod(MethodHookParam param) {
        Object sensorEvents = XposedHelpers.getObjectField(param.thisObject, "mSensorsEvents");
        Sensor sensor = ((SensorEvent) XposedHelpers.callMethod(sensorEvents, "get", new Class[]{int.class}, param.args[0])).sensor;
        if (sensor != null && sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastUpdateTimeMillis >= ((long) 480)) {
                lastUpdateTimeMillis = currentTimeMillis;
                ((float[]) param.args[1])[0] = (float) (10 + 25 * Math.random());
                ((float[]) param.args[1])[1] = (float) (10 + 25 * Math.random());
                ((float[]) param.args[1])[2] = (float) (10 + 25 * Math.random());

            }
        }
    }
}
