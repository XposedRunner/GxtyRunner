package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import java.util.Arrays;
import java.util.List;

/* compiled from: XiaomiNavigateStrategy */
public class k extends f {
    private static final List<Pair> a = Arrays.asList(new Pair[]{Pair.create("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")});
    private static final List<Pair> b = Arrays.asList(new Pair[]{Pair.create("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsContainerManagementActivity"), Pair.create("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity")});

    public k(Context context) {
        super(context);
    }

    protected List<Pair> a() {
        return a;
    }

    protected List<Pair> b() {
        return b;
    }

    public String c() {
        return "设置方法：安全中心 -> 授权管理 -> 自启动管理 -> 打开 App 的开关 。";
    }

    public String d() {
        return "设置方法：安全中心 -> 应用智能省电 -> App -> 无限制 。";
    }
}
