package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: CoolpadNavigateStrategy */
public class a extends f {
    private static final List<Pair> a = Arrays.asList(new Pair[]{Pair.create("com.yulong.android.security", "com.yulong.android.seccenter.tabbarmain")});
    private static final List<Pair> b = Collections.emptyList();

    public a(Context context) {
        super(context);
    }

    protected List<Pair> a() {
        return a;
    }

    protected List<Pair> b() {
        return b;
    }

    public String c() {
        return "设置方法：酷管家 -> 软件管理 -> 自启动管理 -> 打开 App 的开关 。";
    }
}
