package com.example.gita.gxty_runner.hook.autorun;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class RuningServiceOnCreateMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {

            if (!Common.autoRun) return;

            Common.target = Float.parseFloat((String) XposedHelpers.getObjectField(Common.run, "length")) * 1000.0f;
//            target = 200;
        }
    }
