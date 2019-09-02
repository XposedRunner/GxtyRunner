package com.baidu.mobads.production;

import android.content.Context;
import cn.jiguang.net.HttpUtils;
import dalvik.system.DexClassLoader;
import java.io.File;

public class a {
    private static DexClassLoader a = null;

    public static DexClassLoader a(Context context) {
        if (a == null) {
            try {
                String str = context.getDir("baidu_ad_sdk", 0).getAbsolutePath() + HttpUtils.PATHS_SEPARATOR;
                String absolutePath = context.getFilesDir().getAbsolutePath();
                File file = new File(str + "local_vr_imageview.jar");
                synchronized (a.class) {
                    String absolutePath2 = file.getAbsolutePath();
                    ClassLoader classLoader = context.getClass().getClassLoader();
                    System.currentTimeMillis();
                    DexClassLoader dexClassLoader = new DexClassLoader(absolutePath2, absolutePath, null, classLoader);
                    System.currentTimeMillis();
                    a = dexClassLoader;
                }
            } catch (Exception e) {
                a = null;
            }
        }
        return a;
    }
}
