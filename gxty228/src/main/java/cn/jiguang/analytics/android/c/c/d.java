package cn.jiguang.analytics.android.c.c;

import java.io.Serializable;

public abstract class d {
    private String a;

    public d(String str) {
        this.a = str;
    }

    public final String a() {
        return this.a;
    }

    public abstract boolean a(Serializable serializable);
}
