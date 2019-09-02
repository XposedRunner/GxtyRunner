package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import java.util.Arrays;
import java.util.List;

/* compiled from: SamsungNavigateStrategy */
public class h extends f {
    private static final List<Pair> a = Arrays.asList(new Pair[]{Pair.create("com.samsung.android.sm", "com.samsung.android.sm.app.dashboard.SmartManagerDashBoardActivity"), Pair.create("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.ram.AutoRunActivity"), Pair.create("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.appmanagement.AppManagementActivity"), Pair.create("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.cstyleboard.SmartManagerDashBoardActivity")});
    private static final List<Pair> b = Arrays.asList(new Pair[]{Pair.create("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.battery.AppSleepListActivity_dim"), Pair.create("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.battery.BatteryActivity"), Pair.create("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.AppSleepListActivity"), Pair.create("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")});

    public h(Context context) {
        super(context);
    }

    protected List<Pair> a() {
        return a;
    }

    protected List<Pair> b() {
        return b;
    }

    public String c() {
        return "设置方法：智能管理器 -> 应用程序管理 -> 管理自动运行 -> 打开 App 的开关 。";
    }
}
