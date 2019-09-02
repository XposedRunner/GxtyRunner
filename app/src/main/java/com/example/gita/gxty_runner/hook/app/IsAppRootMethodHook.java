package com.example.gita.gxty_runner.hook.app;

import de.robv.android.xposed.XC_MethodHook;

public class IsAppRootMethodHook extends XC_MethodHook {

    @Override
    protected void beforeHookedMethod(MethodHookParam param) {

        param.setResult(false);
    }

}
