package com.duudu.abcd.a;

import android.content.Context;
import android.util.Pair;
import com.duudu.abcd.b.a;
import java.util.List;

/* compiled from: NavigateStrategy */
public abstract class f {
    private Context a;

    protected abstract List<Pair> a();

    protected abstract List<Pair> b();

    public f(Context context) {
        this.a = context;
    }

    public boolean e() {
        return a.a(a(), this.a);
    }

    public boolean f() {
        return a.a(b(), this.a);
    }

    public boolean g() {
        try {
            a.b(a(), this.a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean h() {
        try {
            a.b(b(), this.a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String c() {
        return "设置方法：请到 安全中心 - 权限管理 的 自启动管理 中，勾选 App。";
    }

    public String d() {
        return "设置方法：请到 安全中心 - 应用智能省电 中，将 App 设置为 无限制 。";
    }
}
