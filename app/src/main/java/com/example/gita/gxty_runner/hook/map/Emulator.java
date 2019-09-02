package com.example.gita.gxty_runner.hook.map;

import java.util.ArrayList;
import java.util.List;

import static com.example.gita.gxty_runner.hook.map.MapUtils.getDistance;
import static com.example.gita.gxty_runner.hook.map.MapUtils.nextLatLng;

public class Emulator {
    private EmulatorThread emulatorThread;

    public static final int EXTRA_START = 0;
    public static final int EXTRA_EMULATING = 1;
    public static final int EXTRA_STOP = 2;


    private Emulator() {
        emulatorThread = new EmulatorThread(this);
    }

    public void setRoute(List<LatLng> route) {
        this.emulatorThread.route = route;
    }

    public void setSpeed(float speed) {
        this.emulatorThread.speed = speed;
    }

    public float getSpeed() {
        return this.emulatorThread.speed;
    }


    public void setListener(Listener listener) {
        this.emulatorThread.listener = listener;
    }

    public void start() {
        this.emulatorThread.start();
    }

    public void stop() {
        this.emulatorThread.running = false;
        this.emulatorThread.interrupt();
    }

    public void pause() {
        this.emulatorThread.waiting = true;
    }

    public void resume() {
        this.emulatorThread.waiting = false;
    }


    public static Emulator create() {
        return new Emulator();
    }


    public static Emulator create(List<LatLng> route, float speed, Listener listener) {
        Emulator emulator = new Emulator();
        emulator.setRoute(route);
        emulator.setSpeed(speed);
        emulator.setListener(listener);
        return emulator;
    }

    public interface Listener {
        void onEmulating(Emulator emulator, LatLng latLng, Angle angle, int extra);
    }

    private class EmulatorThread extends Thread {
        long lastUpdateTimeMillis = System.currentTimeMillis();
        List<LatLng> route = new ArrayList<>();
        boolean running = true;
        boolean waiting = false;
        float speed = 0.0f;
        int nextNode = 1;
        LatLng lastLatLng = null;
        Angle angle;
        Listener listener;
        Emulator emulator;


        EmulatorThread(Emulator emulator) {
            super("EmulatorThread");
            this.emulator = emulator;
        }

        @Override
        public void run() {

            lastLatLng = route.get(0);
            nextNode = 1;
            angle = Angle.elevate(route.get(0), route.get(1));
            listener.onEmulating(emulator, route.get(0), angle, EXTRA_START);

            mainLoop:
            while (running) {
                sleep();
                long currentTime = System.currentTimeMillis(), realIntervalTime = currentTime - lastUpdateTimeMillis;
                lastUpdateTimeMillis = currentTime;
                if (!waiting) {
                    double distance = speed * realIntervalTime / 1000.0;
                    while (distance > getDistance(lastLatLng, route.get(nextNode))) {
                        distance -= getDistance(lastLatLng, route.get(nextNode));
                        lastLatLng = route.get(nextNode++);
                        if (nextNode == route.size()) {
                            angle = Angle.elevate(route.get(route.size() - 2), route.get(route.size() - 1));
                            listener.onEmulating(emulator, route.get(route.size() - 1), angle, EXTRA_STOP);
                            break mainLoop;
                        }
                    }
                    angle = Angle.elevate(lastLatLng, route.get(nextNode));
                    lastLatLng = nextLatLng(lastLatLng, angle, distance);
                    listener.onEmulating(emulator, lastLatLng, angle, EXTRA_EMULATING);
                }
            }
        }

        private void sleep() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ignored) {
            }
        }
    }

}
