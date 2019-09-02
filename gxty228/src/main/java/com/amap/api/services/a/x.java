package com.amap.api.services.a;

import android.content.Context;
import java.util.List;

/* compiled from: SDKDBOperation */
public class x {
    private s a;
    private Context b;

    public x(Context context, boolean z) {
        this.b = context;
        this.a = a(this.b, z);
    }

    private s a(Context context, boolean z) {
        try {
            return new s(context, s.a(w.class));
        } catch (Throwable th) {
            if (!z) {
                p.c(th, "sd", "gdb");
            }
            return null;
        }
    }

    public List<e> a() {
        List<e> list = null;
        try {
            list = this.a.a(e.f(), e.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return list;
    }
}
