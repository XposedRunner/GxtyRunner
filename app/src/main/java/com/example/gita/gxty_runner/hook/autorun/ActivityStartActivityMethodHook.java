package com.example.gita.gxty_runner.hook.autorun;

import android.content.Intent;

import de.robv.android.xposed.XC_MethodHook;

public class ActivityStartActivityMethodHook extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) {
//                XposedBridge.log(param.thisObject.getClass().getName() + "." + param.method.getName() + "(" + Arrays.toString(param.args) + ")");

            if (!Common.autoRun) return;

            Intent intent = (Intent) param.args[0];
            if (param.thisObject.getClass().getName().equalsIgnoreCase("com.example.gita.gxty.ram.SportActivity")
                    && intent.hasExtra("runType"))
                Common.runType = intent.getIntExtra("runType", 1);

//                if(param.thisObject.getClass().getName().equalsIgnoreCase("com.example.gita.gxty.ram.MyRuningActivity")
//                        && intent.getClass().getName().equalsIgnoreCase("com.example.gita.gxty.activity.ResultActivity"))
//                    intent.putExtra("exit",true);
        }
    }