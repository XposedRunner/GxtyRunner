package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import java.util.Arrays;
import java.util.List;

/* compiled from: OppoNavigateStrategy */
public class g extends f {
    private static final List<Pair> a = Arrays.asList(new Pair[]{Pair.create("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity"), Pair.create("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"), Pair.create("com.coloros.safecenter", "com.coloros.safecenter.permission.startupapp.StartupAppListActivity"), Pair.create("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity"), Pair.create("com.coloros.safecenter", "com.coloros.privacypermissionsentry.PermissionTopActivity"), Pair.create("com.coloros.safecenter", "com.coloros.safecenter.newrequest.SecurityScanActivity")});
    private static final List<Pair> b = Arrays.asList(new Pair[]{Pair.create("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity"), Pair.create("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity"), Pair.create("com.coloros.oppoguardelf", "com.coloros.oppoguardelf.MonitoredPkgActivity")});

    public g(Context context) {
        super(context);
    }

    protected List<Pair> a() {
        return a;
    }

    protected List<Pair> b() {
        return b;
    }

    public String c() {
        return "设置方法：手机管家 -> 权限隐私 -> 管理自启动应用 -> 允许 App 自启动 。<";
    }

    public String d() {
        return "设置方法：设置 -> 电池 -> App -> 取消后台冻结 和 异常自动优化 。";
    }
}
