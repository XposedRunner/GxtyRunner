package com.example.gita.gxty_runner.hook.app;

import de.robv.android.xposed.XC_MethodHook;

public class SharedPreferencesImplGetBooleanMethodHook extends XC_MethodHook {
    @Override
    protected void beforeHookedMethod(MethodHookParam param) {

        if ("Notice_Flag1".equalsIgnoreCase((String) param.args[0])) {
            param.setResult(true);
        }
    }
}