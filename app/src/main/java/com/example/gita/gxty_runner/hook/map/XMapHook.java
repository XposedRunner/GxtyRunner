package com.example.gita.gxty_runner.hook.map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class XMapHook {

//    private static int runType = -1;
//    private static Object run;
//    private static List<DPoint> dPointList = new ArrayList<>();
//    private static Object mapView, aMap;
//    private static Emulator emulator = new Emulator();
//    private static boolean isBackground = false;
//    private static WeakReference<Context> context;
//    private static LatLng latLng;
//    private static RouteProcessor routeProcessor = null;
//    private static int gpsCount, beaconCount;
//    private static float target;
//    private static WeakReference<View> sport_actionBtnLeft, sport_pauseBtn, sport_stopBtn, dOk, dCancel;
//    private static Handler uiHandler;

    public static void hook(final ClassLoader classLoader) {


        XposedHelpers.findAndHookMethod("android.app.Activity",
                classLoader,
                "startActivity",
                Intent.class,
                Common.instantiate(ActivityStartActivityMethodHook.class));

        XposedHelpers.findAndHookMethod("android.app.Application",
                classLoader,
                "attach",
                Context.class,
                Common.instantiate(ApplicationAttachMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.MyRuningActivity",
                classLoader,
                "onCreate",
                Bundle.class,
                Common.instantiate(MyRuningActivityOnCreateMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.MyRuningActivity",
                classLoader,
                "onDestroy",
                Common.instantiate(MyRuningActivityOnDestroyMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.MyRuningActivity",
                classLoader,
                "onResume",
                Common.instantiate(MyRuningActivityOnResumeMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.MyRuningActivity",
                classLoader,
                "onPause",
                Common.instantiate(MyRuningActivityOnPauseMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.MyRuningActivity",
                classLoader,
                "onSaveInstanceState",
                Bundle.class,
                Common.instantiate(MyRuningActivityOnSavedInstanceStateMethodHook.class));


        XposedHelpers.findAndHookConstructor("com.example.gita.gxty.model.Run",
                classLoader,
                Common.instantiate(RunConstructorMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.service.RuningService",
                classLoader,
                "onCreate",
                Common.instantiate(RuningServiceOnCreateMethodHook.class));


        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.service.RuningService",
                classLoader,
                "onStartCommand",
                Intent.class, int.class, int.class,
                Common.instantiate(RuningServiceOnStartCommandMethodHook.class));

        XposedHelpers.findAndHookMethod("com.example.gita.gxty.ram.service.RuningService",
                classLoader,
                "onDestroy",
                Common.instantiate(RuningServiceOnDestroyMethodHook.class));

        XposedHelpers.findAndHookMethod("com.amap.api.location.AMapLocationClient",
                classLoader,
                "setLocationListener",
                "com.amap.api.location.AMapLocationListener",
                Common.instantiate(AMapSetLocationListenerMethodHook.class));


    }


}
