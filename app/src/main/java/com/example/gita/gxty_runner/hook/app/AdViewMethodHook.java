package com.example.gita.gxty_runner.hook.app;

import de.robv.android.xposed.XC_MethodHook;

public class AdViewMethodHook extends XC_MethodHook {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        param.setResult(null);
    }
}
