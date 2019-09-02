package com.example.gita.gxty_runner.hook.autorun;

import de.robv.android.xposed.XC_MethodHook;

public class RuningServiceOnDestroyMethodHook extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) {
            Common.runType = -1;
            Common.target = 0;
            Common.dOk = null;
            Common.task = false;
        }
    }
