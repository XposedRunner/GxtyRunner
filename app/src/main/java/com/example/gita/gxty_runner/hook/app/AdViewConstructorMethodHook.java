package com.example.gita.gxty_runner.hook.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import de.robv.android.xposed.XC_MethodHook;

public class AdViewConstructorMethodHook extends XC_MethodHook {
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        ViewGroup viewGroup = (ViewGroup) param.thisObject;
        viewGroup.removeAllViews();
        viewGroup.setVisibility(View.GONE);
//        view.setVisibility(View.GONE);
    }
}
