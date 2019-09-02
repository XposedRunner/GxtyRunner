package com.example.gita.gxty_runner.hook.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class SensorManagerRegisterListenerMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {
            Sensor sensor = (Sensor) param.args[1];
            switch (sensor.getType()) {
                case Sensor.TYPE_STEP_DETECTOR:
                    Common.detectorSensorEventListener = (SensorEventListener) param.args[0];
                    Common.detector = (Sensor) param.args[1];
                    XposedHelpers.callMethod(param.thisObject, "unregisterListener", new Class[]{SensorEventListener.class, Sensor.class}, param.args[0], param.args[1]);
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    Common.counterSensorEventListener = (SensorEventListener) param.args[0];
                    Common. counter = (Sensor) param.args[1];
                    XposedHelpers.callMethod(param.thisObject, "unregisterListener", new Class[]{SensorEventListener.class, Sensor.class}, param.args[0], param.args[1]);
                    break;
            }
            if (Common.counterSensorEventListener != null && Common.detectorSensorEventListener != null) {
                Common.sensorThread.startThread(param.thisObject.getClass().getClassLoader());
            }
        }
    }
