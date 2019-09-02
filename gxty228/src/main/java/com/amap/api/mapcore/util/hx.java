package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.mapcore.util.fy.a.f;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: SoLoader */
public class hx {
    private WeakReference<Context> a;
    private im b = new im();

    /* compiled from: SoLoader */
    static class a {
        public static hx a = new hx();
    }

    public static hx a() {
        return a.a;
    }

    public boolean a(Context context, hy hyVar, String str) {
        if (hyVar == null || TextUtils.isEmpty(str) || context == null) {
            return false;
        }
        boolean b = hyVar.b();
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (context == null || hyVar == null) {
            a(str);
            return false;
        }
        a(context);
        return this.b.a(context, hyVar, str, b);
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            System.loadLibrary(str);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public void a(Context context, hy hyVar, f fVar) {
        if (fVar != null && hyVar != null && context != null) {
            a(context);
            this.b.a(context, hyVar, fVar.a, fVar.b, fVar.e, fVar.f, fVar.d, fVar.c);
        }
    }

    public static void a(Context context, gk gkVar, String str, List<String> list) {
        hz.a(context, (List) list);
        a().b.a(gkVar, list);
    }

    private void a(Context context) {
        if (context != null && context.getApplicationContext() != null) {
            this.a = null;
            this.a = new WeakReference(context.getApplicationContext());
        }
    }
}
