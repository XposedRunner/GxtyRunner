package cn.jiguang.a.a.d;

import android.content.Context;

final class f implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ c b;

    f(c cVar, Context context) {
        this.b = cVar;
        this.a = context;
    }

    public final void run() {
        c.a(this.b, this.a);
    }
}
