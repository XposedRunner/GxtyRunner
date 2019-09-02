package cn.jiguang.a.a.d;

import android.content.Context;

final class d implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ c b;

    d(c cVar, Context context) {
        this.b = cVar;
        this.a = context;
    }

    public final void run() {
        c.a(this.b, this.a);
    }
}
