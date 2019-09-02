package com.example.gita.gxty_runner.hook.app;

import android.view.View;

import de.robv.android.xposed.XC_MethodHook;

public class ViewSetVisibility extends XC_MethodHook {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            View view = (View) param.thisObject;
            if (view.getId() == 0) {/*TODO: replace 0 with real adLayoutId*/
                param.args[0] = View.GONE;
            }
        }
    }
