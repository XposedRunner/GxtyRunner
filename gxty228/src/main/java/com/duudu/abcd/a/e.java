package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import java.util.Arrays;
import java.util.List;

/* compiled from: MeizuNavigateStrategy */
public class e extends f {
    private static final List<Pair> a = Arrays.asList(new Pair[]{Pair.create("com.meizu.safe", "com.meizu.safe.permission.PermissionMainActivity"), Pair.create("com.meizu.safe", "com.meizu.safe.permission.SmartBGActivity"), Pair.create("com.meizu.safe", "com.meizu.safe.security.HomeActivity")});
    private static final List<Pair> b = Arrays.asList(new Pair[]{Pair.create("com.meizu.safe", "com.meizu.safe.powerui.PowerAppPermissionActivity"), Pair.create("com.meizu.safe", "")});

    public e(Context context) {
        super(context);
    }

    protected List<Pair> a() {
        return a;
    }

    protected List<Pair> b() {
        return b;
    }

    public String c() {
        return "设置方法：手机管家 -> 权限管理 -> 后台管理 -> 找到 App -> 保持后台运行 。";
    }

    public String d() {
        return "设置方法：手机管家 -> 省电模式 -> 待机耗电管理 -> 打开 App 的开关 。";
    }
}
