package com.example.gita.gxty_runner.hook.autorun;

import de.robv.android.xposed.XC_MethodHook;

public class SharedPreferencesImplGetFloatMethodHook extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) {

            if (!Common.autoRun) return;

            if ("8totalLength".equalsIgnoreCase((String) param.args[0]) && !Common.task) {
                param.setResult(param.args[1]);
                Common.task = !Common.task;
            }
        }
    }
