package com.example.gita.gxty_runner.hook.map;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class MyRuningActivityOnDestroyMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {

            if ( Common.runType != 1) return;

            if ( Common.mapView != null) {
                XposedHelpers.callMethod( Common.mapView, "onDestroy");
            }
            Common.mapView = null;
            Common.aMap = null;
        }
    }
