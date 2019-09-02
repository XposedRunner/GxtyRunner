package cn.jiguang.a.a.d;

import android.content.Context;

final class h implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ c b;

    h(c cVar, Context context) {
        this.b = cVar;
        this.a = context;
    }

    public final void run() {
        c.b(this.b, this.a);
    }
}
