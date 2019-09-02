package com.example.gita.gxty_runner.hook.autorun;

import de.robv.android.xposed.XC_MethodHook;

public class RunConstructorMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {
            Common.run = param.thisObject;

        }
    }
