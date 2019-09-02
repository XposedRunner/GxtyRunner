package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import java.util.Arrays;
import java.util.List;

/* compiled from: VivoNavigateStrategy */
public class j extends f {
    private static final List<Pair> a = Arrays.asList(new Pair[]{Pair.create("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager"), Pair.create("com.iqoo.secure", ""), Pair.create("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewTabActivity")});
    private static final List<Pair> b = Arrays.asList(new Pair[]{Pair.create("com.vivo.abe", "com.vivo.applicationbehaviorengine.ui.ExcessivePowerManagerActivity")});

    public j(Context context) {
        super(context);
    }

    protected List<Pair> a() {
        return a;
    }

    protected List<Pair> b() {
        return b;
    }

    public String c() {
        return "设置方法：i 管家 -> 软件管理 -> 自启动管理 ，然后勾选 App 。";
    }

    public String d() {
        return "设置方法：i 管家 -> 省电管理 -> 后台高耗电 ，让 App 可以后台运行。";
    }
}
