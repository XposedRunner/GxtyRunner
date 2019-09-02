package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.List;

/* compiled from: SDKDBOperation */
public class hj {
    private he a;
    private Context b;

    public hj(Context context, boolean z) {
        this.b = context;
        this.a = a(this.b, z);
    }

    private he a(Context context, boolean z) {
        try {
            return new he(context, he.a(hi.class));
        } catch (Throwable th) {
            if (!z) {
                gz.c(th, "sd", "gdb");
            }
            return null;
        }
    }

    public void a(gk gkVar) {
        if (gkVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b, false);
                }
                String a = gk.a(gkVar.a());
                List b = this.a.b(a, gk.class);
                if (b == null || b.size() == 0) {
                    this.a.a((Object) gkVar);
                } else if (a(b, gkVar)) {
                    this.a.a(a, (Object) gkVar);
                }
            } catch (Throwable th) {
                gz.c(th, "sd", "it");
            }
        }
    }

    private boolean a(List<gk> list, gk gkVar) {
        for (gk equals : list) {
            if (equals.equals(gkVar)) {
                return false;
            }
        }
        return true;
    }

    public List<gk> a() {
        List<gk> list = null;
        try {
            list = this.a.a(gk.h(), gk.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return list;
    }
}
