package com.example.gita.gxty_runner.hook.sensor;

import de.robv.android.xposed.XC_MethodHook;


public class SensorManagerUnregisterListenerMethodHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) {
        if (param.args[0] == Common.detectorSensorEventListener) {
            Common.detectorSensorEventListener = null;
            Common. detector = null;
        }
        if (param.args[0] == Common.counterSensorEventListener) {
            Common.counterSensorEventListener = null;
            Common.counter = null;
        }
        if (Common.detectorSensorEventListener == null && Common.counterSensorEventListener == null) {
            Common.sensorThread.stopThread();
        }
    }
}
