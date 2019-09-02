package cn.jiguang.d.b.a.a;

import cn.jiguang.d.a.a;
import cn.jiguang.d.b.a.d;
import cn.jiguang.d.d.c;
import cn.jiguang.g.m;

public final class i extends j {
    private final boolean b;

    public i(d dVar, boolean z) {
        super(dVar);
        this.b = z;
    }

    public final int a() {
        if (this.b) {
            if (a.a(m.c(this.a.d())) || a.f()) {
                return 1;
            }
            cn.jiguang.e.d.e("LastGoodSisPolicy", "Do not need SIS again within 3 mins. Use last good sis response. ");
        }
        cn.jiguang.d.b.i a = c.a(a.m());
        if (a == null) {
            return -1;
        }
        a.h();
        return b(cn.jiguang.d.b.a.a.b(a));
    }
}
