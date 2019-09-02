package cn.jiguang.a.a.d;

import android.content.Context;

final class e implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ c b;

    e(c cVar, Context context) {
        this.b = cVar;
        this.a = context;
    }

    public final void run() {
        c.b(this.b, this.a);
    }
}
