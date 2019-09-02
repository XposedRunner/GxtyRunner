package cn.jiguang.a.a.c;

import android.content.Context;
import cn.jiguang.d.a.d;
import cn.jiguang.d.d.q;

final class c implements q {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    c(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public final void a(int i) {
        if (i == 0) {
            b.b(this.a, this.b);
            d.a(this.a, "last_report_device_info", Long.valueOf(System.currentTimeMillis()));
        }
    }
}
