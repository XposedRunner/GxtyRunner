package cn.jiguang.d.d;

import android.content.Context;
import org.json.JSONObject;

final class p implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ JSONObject b;
    final /* synthetic */ boolean c;

    p(Context context, JSONObject jSONObject, boolean z) {
        this.a = context;
        this.b = jSONObject;
        this.c = z;
    }

    public final void run() {
        o.b(this.a, this.b, this.c);
    }
}
