package com.example.gita.gxty_runner.hook.map;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XCallback;

class Common {

    public static int runType = -1;
    public static Object run;
    public static List<DPoint> dPointList = new ArrayList<>();
    public static Object mapView, aMap;
    public static Emulator emulator = null;
    public static boolean isBackground = false;
    public static WeakReference<Context> context;
//    public static LatLng latLng;
    public static RouteProcessor routeProcessor = null;
    public static int gpsCount, beaconCount;
    public static float target;

    public static LatLng currentLatLng = null;
    public static LatLng lastLatLng = null;
    public static LatLng currentVirtualLatLng = null;
    public static LatLng lastVirtualLatLng = null;
    public static RouteRandom routeRandom;

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
