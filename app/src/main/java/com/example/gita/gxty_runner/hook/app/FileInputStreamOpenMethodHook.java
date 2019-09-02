package com.example.gita.gxty_runner.hook.app;

import de.robv.android.xposed.XC_MethodHook;

public   class FileInputStreamOpenMethodHook extends XC_MethodHook {
        

        @Override
        protected void beforeHookedMethod(MethodHookParam param) {

            if (((String) param.args[0]).matches("/proc/[0-9]+/maps") || Common.isTarget((String) param.args[0])) {
                param.args[0] = "/system/build.prop";
            }
        }
    }

