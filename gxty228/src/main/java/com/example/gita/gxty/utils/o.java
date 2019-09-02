package com.example.gita.gxty.utils;

import android.content.Context;
import android.content.res.Resources;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ResUtils */
public class o {
    private static Map<String, o> d = new HashMap();
    private Context a;
    private String b;
    private Resources c;

    public int a(String str) {
        return this.c.getIdentifier(str, "id", this.b);
    }

    private o(Context context, String str) {
        this.a = context.getApplicationContext();
        this.b = str;
        this.c = context.getResources();
    }

    public static o a(Context context, String str) {
        o oVar = (o) d.get(str);
        if (oVar != null) {
            return oVar;
        }
        oVar = new o(context, str);
        d.put(str, oVar);
        return oVar;
    }

    public static o a(Context context) {
        return a(context, context.getPackageName());
    }
}
