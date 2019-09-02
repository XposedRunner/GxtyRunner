package com.example.gita.gxty_runner.hook.app;

import java.io.File;

import de.robv.android.xposed.XC_MethodHook;

public class FileExistsMethodHook extends XC_MethodHook {
    private String[] fileList = new String[]{"/su", "/su/bin/su", "/sbin/su",
            "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su",
            "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su",
            "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck",
            "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd",
            "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};

    @Override
    protected void afterHookedMethod(MethodHookParam param) {
        File file = (File) param.thisObject;
        for (String filename : fileList) {
            if (new File(filename).getPath().equals(file.getPath())) {
                param.setResult(false);
                break;
            }
        }
    }
}
