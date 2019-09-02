package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: ZteNavigateStrategy */
public class l extends f {
    private static final List<Pair> a = Arrays.asList(new Pair[]{Pair.create("com.zte.powersavemode", "com.zte.powersavemode.autorun.AppAutoRunManager")});
    private static final List<Pair> b = Collections.emptyList();

    public l(Context context) {
        super(context);
    }

    protected List<Pair> a() {
        return a;
    }

    protected List<Pair> b() {
        return b;
    }

    public String c() {
        return "设置方法：掌心管家 -> 自启动管理 -> 打开 App 的开关 。";
    }

    public String d() {
        return "设置方法：手机管家 -> 省电模式 -> 待机耗电管理 -> 打开 App 的开关 。";
    }
}
