package com.example.gita.gxty_runner.hook.map;

import android.content.Context;

import java.lang.ref.WeakReference;

import de.robv.android.xposed.XC_MethodHook;

public class ApplicationAttachMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {
           Common. context = new WeakReference<>((Context) param.args[0]);


        }
    }
