package com.example.gita.gxty_runner.hook.app;

import com.example.gita.gxty_runner.GxtyRunner;

import de.robv.android.xposed.XC_MethodHook;

public class NativeExpressADConstructorMethodHook extends XC_MethodHook {


        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

            param.args[2] = "";
            param.args[3] = "";
            param.args[1] = null;
        }
    }
