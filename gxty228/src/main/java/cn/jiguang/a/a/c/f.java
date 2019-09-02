package cn.jiguang.a.a.c;

import cn.jiguang.d.d.o;
import cn.jiguang.d.d.q;
import cn.jiguang.e.d;
import org.json.JSONArray;
import org.json.JSONObject;

final class f extends Thread implements q {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void a(int i) {
        d.g("JPushCrashHandler", "ReportDirect errorCode:" + i);
        if (i == 0) {
            e.d(this.a.e);
        }
    }

    public final void run() {
        if (this.a.e == null) {
            d.g("JPushCrashHandler", "ReportDirect context is null");
            return;
        }
        JSONObject e = e.e(this.a.e);
        if (e != null) {
            o.a(this.a.e, new JSONArray().put(e), (q) this);
        }
    }
}
