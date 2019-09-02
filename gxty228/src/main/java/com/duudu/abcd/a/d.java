package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import java.util.Arrays;
import java.util.List;

/* compiled from: LenovoNavigateStrategy */
public class d extends f {
    private static final List<Pair> a = Arrays.asList(new Pair[]{Pair.create("com.lenovo.security", "com.lenovo.security.homepage.HomePageActivity")});
    private static final List<Pair> b = Arrays.asList(new Pair[]{Pair.create("com.lenovo.powersetting", "com.lenovo.powersetting.PowerSettingActivity")});

    public d(Context context) {
        super(context);
    }

    protected List<Pair> a() {
        return a;
    }

    protected List<Pair> b() {
        return b;
    }

    public String c() {
        return "设置方法：安全中心 -> 权限管理 -> 自启动 -> 打开 App 的开关 。";
    }

    public String d() {
        return "设置方法：设置 -> 省电管理 -> 待机省电 -> 打开 App 的开关 。";
    }
}
