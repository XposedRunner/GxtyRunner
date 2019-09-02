package cn.jiguang.d.d;

import android.content.Context;
import org.json.JSONArray;

final class r implements Runnable {
    Context a;
    JSONArray b;
    String c;

    public r(Context context, JSONArray jSONArray, String str) {
        this.a = context;
        this.b = jSONArray;
        this.c = str;
    }

    public final void run() {
        o.b(this.a, this.b, this.c);
    }
}
