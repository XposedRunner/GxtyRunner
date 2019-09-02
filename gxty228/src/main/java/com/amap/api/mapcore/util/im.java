package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SoManager */
public final class im {
    public static volatile boolean a = false;
    public static volatile gk b = null;
    public static List<String> c;
    private in d;

    private in a(Context context) {
        if (this.d == null) {
            this.d = new in(context);
        }
        return this.d;
    }

    public final void a(gk gkVar, List<String> list) {
        if (c == null) {
            c = new ArrayList();
        }
        a = true;
        if (list != null) {
            c.addAll(list);
        }
        if (gkVar != null) {
            b = gkVar;
        }
        if (this.d != null) {
            this.d.a();
        }
    }

    public final boolean a(Context context, hy hyVar, String str, boolean z) {
        return b(context, hyVar, str, z);
    }

    private boolean b(Context context, hy hyVar, String str, boolean z) {
        boolean z2 = false;
        if (hyVar != null) {
            try {
                if (hyVar.a() != null) {
                    in a = a(context);
                    if (a != null) {
                        z2 = a.a(context, hyVar.a(), str, z);
                    }
                }
            } catch (Throwable th) {
                hz.d("SoManagerCore ex " + th);
            }
        }
        return z2;
    }

    public final void a(Context context, hy hyVar, boolean z, boolean z2, String str, String str2, String str3, boolean z3) {
        try {
            in a = a(context);
            if (a != null) {
                a.a(context, hyVar.a(), str3, z, z2, str, str2, z3);
            }
        } catch (Throwable th) {
            hz.d("SoManagerCore ex " + th);
        }
    }
}
