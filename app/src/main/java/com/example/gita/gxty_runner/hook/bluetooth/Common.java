package com.example.gita.gxty_runner.hook.bluetooth;

import com.example.gita.gxty_runner.hook.map.DPoint;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XCallback;

class Common {

    public static Object run;
    public static int runType = -1;
    public static List<DPoint> dPointList = new ArrayList<>();
    public static List<Beacon> beaconList = new ArrayList<>();
    public static float distance;


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
