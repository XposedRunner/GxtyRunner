package com.qq.e.comm.constants;

import com.qq.e.ads.ADActivity;
import com.qq.e.comm.DownloadService;

public class CustomPkgConstants {
    private static final String a = DownloadService.class.getName();
    private static final String b = ADActivity.class.getName();

    public static String getADActivityName() {
        return b;
    }

    public static String getAssetPluginDir() {
        return "gdt_plugin";
    }

    public static String getAssetPluginName() {
        return "gdtadv2.jar";
    }

    public static String getAssetPluginXorKey() {
        return "";
    }

    public static String getDownLoadServiceName() {
        return a;
    }
}
