package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import java.util.Arrays;
import java.util.List;

/* compiled from: SmartisanNavigateStrategy */
public class i extends f {
    private static final List<Pair> a = Arrays.asList(new Pair[]{Pair.create("com.smartisanos.security", "com.smartisanos.security.MainActivity"), Pair.create("com.smartisanos.security", "com.smartisanos.security.PackagesOverview")});
    private static final List<Pair> b = Arrays.asList(new Pair[]{Pair.create("com.android.settings", "com.android.settings.fuelgauge.appBatteryUseOptimization.AppBatteryUseOptimizationActivity"), Pair.create("com.android.settings", "com.android.settings.fuelgauge.appBatteryUseOptimization.BatteryUseOptimizationActivity"), Pair.create("com.android.settings", "com.android.settings.fuelgauge.PowerUsageSummaryActivity")});

    public i(Context context) {
        super(context);
    }

    protected List<Pair> a() {
        return a;
    }

    protected List<Pair> b() {
        return b;
    }

    public String c() {
        return "设置方法：设置 -> 安全中心 -> 应用程序权限管理 -> 自启动权限管理 -> App -> 允许被系统启动 。";
    }
}
