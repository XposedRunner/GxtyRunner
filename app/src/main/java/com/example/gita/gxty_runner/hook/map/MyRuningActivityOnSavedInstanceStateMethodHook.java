package com.example.gita.gxty_runner.hook.map;

import android.os.Bundle;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class MyRuningActivityOnSavedInstanceStateMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {

            if ( Common.runType != 1) return;

            XposedHelpers.callMethod( Common.mapView, "onSaveInstanceState", new Class[]{Bundle.class}, param.args[0]);
        }
    }
