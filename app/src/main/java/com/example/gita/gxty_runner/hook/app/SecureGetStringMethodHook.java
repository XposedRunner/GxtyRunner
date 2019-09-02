package com.example.gita.gxty_runner.hook.app;

import de.robv.android.xposed.XC_MethodHook;

public class SecureGetStringMethodHook extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(MethodHookParam param) {

        if ("mock_location".equalsIgnoreCase((String) param.args[1])) {
            param.setResult("0");
        }
    }
}
