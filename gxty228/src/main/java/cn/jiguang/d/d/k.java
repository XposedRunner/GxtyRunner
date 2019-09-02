package cn.jiguang.d.d;

import android.content.Context;
import android.os.Bundle;
import cn.jiguang.d.b.a;
import cn.jiguang.e.d;

final class k implements Runnable {
    final /* synthetic */ j a;
    private String b;
    private Bundle c;
    private int d;
    private Context e;

    public k(j jVar, Context context, String str, Bundle bundle, int i) {
        this.a = jVar;
        this.b = str;
        this.c = bundle;
        this.e = context;
        this.d = i;
    }

    public final void run() {
        if (this.d == 1) {
            j.a(this.a, this.b, this.c);
        } else if (a.c()) {
            this.a.c(this.e, this.b, this.c);
        } else if (this.a.e) {
            j.a(this.a, this.b, this.c);
        } else {
            d.i("JServiceCommandHelper", "is not main process - ");
        }
    }
}
