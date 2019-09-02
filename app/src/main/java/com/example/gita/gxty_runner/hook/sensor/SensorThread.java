package com.example.gita.gxty_runner.hook.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Build;

import java.util.Random;

import de.robv.android.xposed.XposedHelpers;

class SensorThread extends Thread {
    private boolean isClose = false;
    private long lastUpdateTimeMillis = 0;
    private float step = (float) Math.round(1000 * Math.random());
    private Class sensorEventClazz;
    private Random random = new Random();


    @Override
    public void run() {
        while (!isClose) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - lastUpdateTimeMillis > ((long) 1500)) {
                lastUpdateTimeMillis = currentTimeMillis;

                int plus = (int) Math.round((50 + random.nextInt(300)) / 100.0);

                for (int i = 0; i < plus && !isClose; i++) {

                    try {
                        sleep((long) (1500 / plus));
                    } catch (InterruptedException ignored) {
                        break;
                    }

                    if (Common.detector != null && Common.detectorSensorEventListener != null) {
                        int length = (int) XposedHelpers.callStaticMethod(Sensor.class, "getMaxLengthValuesArray", new Class[]{Sensor.class, int.class}, Common.detector, Build.VERSION.SDK_INT);
                        SensorEvent detectorSensorEvent = (SensorEvent) XposedHelpers.newInstance(sensorEventClazz, new Class[]{int.class}, length);
                        detectorSensorEvent.sensor = Common.detector;
                        detectorSensorEvent.timestamp = System.nanoTime();
                        detectorSensorEvent.accuracy = 3;
                        detectorSensorEvent.values[0] = Float.parseFloat("1.0");
                        for (int index = 1; index < length; index++) {
                            detectorSensorEvent.values[index] = 0.0f;
                        }
                        Common.detectorSensorEventListener.onSensorChanged(detectorSensorEvent);
                    }

                    if (isClose) continue;

                    if (Common.counter != null && Common.counterSensorEventListener != null) {
                        int length = (int) XposedHelpers.callStaticMethod(Sensor.class, "getMaxLengthValuesArray", new Class[]{Sensor.class, int.class}, Common.counter, Build.VERSION.SDK_INT);
                        SensorEvent counterSensorEvent = (SensorEvent) XposedHelpers.newInstance(sensorEventClazz, new Class[]{int.class}, length);
                        counterSensorEvent.sensor = Common.counter;
                        counterSensorEvent.timestamp = System.nanoTime();
                        counterSensorEvent.accuracy = 3;
                        counterSensorEvent.values[0] = Float.parseFloat(String.valueOf(++step));
                        for (int index = 1; index < length; index++) {
                            counterSensorEvent.values[index] = 0.0f;
                        }
                        Common.counterSensorEventListener.onSensorChanged(counterSensorEvent);
                    }

                }

            }
        }
    }

    public void startThread(ClassLoader classLoader) {
        sensorEventClazz = XposedHelpers.findClass("android.hardware.SensorEvent", classLoader);
        isClose = false;
        start();
    }

    public void stopThread() {
        interrupt();
        isClose = true;
    }
}