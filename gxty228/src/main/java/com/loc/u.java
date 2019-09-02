package com.loc;

import android.content.Context;
import java.util.List;

/* compiled from: SDKDBOperation */
public final class u {
    private p a;
    private Context b;

    public u(Context context, boolean z) {
        this.b = context;
        this.a = a(this.b, z);
    }

    private static p a(Context context, boolean z) {
        try {
            return new p(context, p.a(t.class));
        } catch (Throwable th) {
            if (!z) {
                j.b(th, "sd", "gdb");
            }
            return null;
        }
    }

    public final List<dk> a() {
        List<dk> list = null;
        try {
            list = this.a.a(dk.g(), dk.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return list;
    }

    public final void a(dk dkVar) {
        if (dkVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b, false);
                }
                String a = dk.a(dkVar.a());
                List<dk> b = this.a.b(a, dk.class);
                if (b == null || b.size() == 0) {
                    this.a.a((Object) dkVar);
                    return;
                }
                Object obj;
                for (dk equals : b) {
                    if (equals.equals(dkVar)) {
                        obj = null;
                        break;
                    }
                }
                obj = 1;
                if (obj != null) {
                    this.a.a(a, (Object) dkVar);
                }
            } catch (Throwable th) {
                j.b(th, "sd", "it");
            }
        }
    }
}
