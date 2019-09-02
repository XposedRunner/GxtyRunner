package cn.jiguang.d.h;

import android.content.Context;
import cn.jiguang.d.d.o;
import cn.jiguang.e.d;
import java.util.ArrayList;
import org.json.JSONObject;

public abstract class a<T> {
    protected long a;
    protected String b;
    protected long c;
    protected String d = "";

    public final void a(long j) {
        this.a = 3600;
    }

    protected void a(Context context, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            d.c("AbsWakeUpManager", "cannot report null data.");
        } else {
            o.a(context, o.b(context, jSONObject, str));
        }
    }

    public final void a(Context context, boolean z) {
        d.c("AbsWakeUpManager", "wakeUp, force:" + z);
        if (context == null) {
            d.g("AbsWakeUpManager", "context is null");
        } else if (!a()) {
            d.c("AbsWakeUpManager", "startOtherAppService failed,canLaunchedStoppedService is false");
        } else if (z || a(context)) {
            b(context);
            d(context);
        }
    }

    public final void a(String str) {
        this.b = str;
    }

    protected boolean a() {
        return true;
    }

    protected abstract boolean a(Context context);

    public final void b(long j) {
        this.c = j;
    }

    protected abstract void b(Context context);

    public final void b(String str) {
        this.d = str;
    }

    protected abstract ArrayList<cn.jiguang.d.d.a> c(Context context);

    public abstract void d(Context context);
}
