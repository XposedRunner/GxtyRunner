package com.example.gita.gxty_runner.hook.app;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;



public  class GetApplicationInfoMethodHook extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) {
            String packageName = (String) param.args[0];

            if (Common.isTarget(packageName)) {
                param.args[0] = "com.android.stk";
                XposedBridge.log("Fake package: " + packageName + " as " + "com.android.stk");
            }

        }

    }
