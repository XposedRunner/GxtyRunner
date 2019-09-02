package com.example.gita.gxty_runner.hook.bluetooth;


import android.content.Intent;

import de.robv.android.xposed.XC_MethodHook;

public class ActivityStartActivityMethodHook extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) {
//                XposedBridge.log(param.thisObject.getClass().getName() + "." + param.method.getName() + "(" + Arrays.toString(param.args) + ")");
            Intent intent = (Intent) param.args[0];
            if (param.thisObject.getClass().getName().equalsIgnoreCase("com.example.gita.gxty.ram.SportActivity")
                    && intent.hasExtra("runType"))
                Common.runType = intent.getIntExtra("runType", 1);
        }
    }
