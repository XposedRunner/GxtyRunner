package cn.jiguang.d.h;

import android.content.Context;

final class g implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;
    final /* synthetic */ f c;

    g(f fVar, Context context, boolean z) {
        this.c = fVar;
        this.a = context;
        this.b = z;
    }

    public final void run() {
        this.c.a.a(this.a, this.b);
        this.c.b.a(this.a, this.b);
    }
}
