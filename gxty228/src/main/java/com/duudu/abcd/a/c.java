package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import java.util.Arrays;
import java.util.List;

/* compiled from: HuaweiNavigateStrategy */
public class c extends f {
    private static final List<Pair> a = Arrays.asList(new Pair[]{Pair.create("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"), Pair.create("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.bootstart.BootStartActivity"), Pair.create("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupAwakedAppListActivity"), Pair.create("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")});
    private static final List<Pair> b = Arrays.asList(new Pair[]{Pair.create("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")});

    public c(Context context) {
        super(context);
    }

    protected List<Pair> a() {
        return a;
    }

    protected List<Pair> b() {
        return b;
    }

    public String c() {
        return "设置方法：手机管家 -> 自启动管理 -> 打开 App 的开关 。";
    }

    public String d() {
        return "设置方法：手机管家 -> 电池管理 -> 受保护应用 -> 打开 App 的开关 。";
    }
}
