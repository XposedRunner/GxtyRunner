package com.example.gita.gxty_runner.hook.map;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class MyRuningActivityOnPauseMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {

            if ( Common.runType != 1) return;

            XposedHelpers.callMethod( Common.mapView, "onPause");
        }
    }
