package com.example.gita.gxty_runner.hook.bluetooth;

import android.content.Intent;
import android.content.SharedPreferences;

import com.example.gita.gxty_runner.hook.map.DPoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.robv.android.xposed.XposedHelpers;

public class XBluetoothHook {
//    private static Object run;
//    private static int runType = -1;
//    private static List<DPoint> dPointList = new ArrayList<>();
//    private static List<Beacon> beaconList = new  ArrayList<>();
//    private static float distance;

    public static void hook(final ClassLoader classLoader) {

        XposedHelpers.findAndHookMethod("android.app.Activity",
                classLoader, 
                "startActivity",
                Intent.class,
                Common.instantiate( ActivityStartActivityMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.service.RuningService", 
                classLoader, 
                "didRangeBeaconsInRegion", 
                Collection.class, "org.altbeacon.beacon.Region",
                Common.instantiate(DidRangeBeaconsInRegionMethodHook.class));

        XposedHelpers.findAndHookConstructor("com.example.gita.gxty.model.Run",
                classLoader, 
                Common.instantiate(RunConstructorMethodHook.class));


        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.service.RuningService",
                classLoader, 
                "onCreate", 
                Common.instantiate(RuningServiceOnCreateMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.service.RuningService",
                classLoader, 
                "onDestroy", 
                Common.instantiate(RuningServiceOnDestroyMethodHook.class));

        XposedHelpers.findAndHookMethod("com.amap.api.location.AMapLocationClient",
                classLoader, "setLocationListener",
                "com.amap.api.location.AMapLocationListener",
                Common.instantiate(AMapSetLocationListenerMethodHook.class));

    }


    


    


}
