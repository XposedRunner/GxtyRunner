package cn.jiguang.a.a.a;

import cn.jiguang.g.n;

final class j implements g {
    final /* synthetic */ n a;
    final /* synthetic */ int b;
    final /* synthetic */ h c;

    j(h hVar, n nVar, int i) {
        this.c = hVar;
        this.a = nVar;
        this.b = i;
    }

    public final void a() {
        synchronized (h.i) {
            this.a.a("ArpUtil", "thread" + this.b);
            this.c.h[this.b] = true;
            h.i.notifyAll();
        }
    }
}
