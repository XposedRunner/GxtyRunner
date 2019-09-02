package com.example.gita.gxty_runner.hook.app;

import de.robv.android.xposed.XC_MethodHook;

public class BuildGetStringMethodHook extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) {

            if ("ro.build.tags".equalsIgnoreCase((String) param.args[0])) {
                String tags = (String) param.getResult();
                param.setResult(tags.replace("test-keys", ""));
            }
        }
    }
