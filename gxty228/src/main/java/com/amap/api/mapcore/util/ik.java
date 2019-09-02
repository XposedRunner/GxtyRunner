package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.util.List;

/* compiled from: SoFileManager */
public final class ik {
    private static iz a = null;
    private static List<ij> b = null;

    public static iz a(Context context) {
        if (context == null) {
            return null;
        }
        if (a == null || !iz.a(a)) {
            a = a(context, "SO_INFO_ENTITY_KEY");
        }
        return new iz(a);
    }

    public static void a(Context context, iz izVar) {
        if (a != null) {
            a = null;
        }
        a(context, "SO_INFO_ENTITY_KEY", izVar);
    }

    public static void b(Context context, iz izVar) {
        a(context, "SO_TEMP_INFO_ENTITY_KEY", izVar);
    }

    public static iz b(Context context) {
        return a(context, "SO_TEMP_INFO_ENTITY_KEY");
    }

    public static void c(Context context) {
        a = null;
        if (context != null) {
            b(context, "SO_INFO_ENTITY_KEY");
        }
    }

    public static void d(Context context) {
        b(context, "SO_TEMP_INFO_ENTITY_KEY");
    }

    private static void a(Context context, String str, iz izVar) {
        if (context != null && !TextUtils.isEmpty(str) && izVar != null) {
            String e = izVar.e();
            if (!TextUtils.isEmpty(e)) {
                e = gl.g(fw.a(gl.a(e)));
                Editor edit = context.getSharedPreferences(gf.b("SO_DYNAMIC_FILE_KEY"), 0).edit();
                edit.putString(str, e);
                edit.commit();
            }
        }
    }

    private static iz a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return new iz();
        }
        return iz.b(gl.a(fw.b(gl.e(context.getSharedPreferences(gf.b("SO_DYNAMIC_FILE_KEY"), 0).getString(str, "")))));
    }

    private static void b(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            Editor edit = context.getSharedPreferences(gf.b("SO_DYNAMIC_FILE_KEY"), 0).edit();
            edit.putString(str, "");
            edit.commit();
        }
    }

    public static void a(Context context, ij ijVar) {
        if (context != null && ijVar != null && ijVar.g()) {
            if (b != null) {
                b.clear();
                b = null;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(gf.b("SO_DYNAMIC_FILE_KEY"), 0);
            List c = ij.c(gl.a(fw.b(gl.e(sharedPreferences.getString("SO_ERROR_KEY", "")))));
            int i = 0;
            while (i < c.size()) {
                if (!ij.a((ij) c.get(i), ijVar)) {
                    i++;
                } else {
                    return;
                }
            }
            c.add(ijVar);
            Editor edit = sharedPreferences.edit();
            edit.putString("SO_ERROR_KEY", gl.g(fw.a(gl.a(ij.a(c)))));
            edit.commit();
        }
    }

    public static boolean b(Context context, ij ijVar) {
        if (context == null || ijVar == null || !ijVar.g()) {
            return false;
        }
        if (b == null || b.isEmpty()) {
            b = ij.c(gl.a(fw.b(gl.e(context.getSharedPreferences(gf.b("SO_DYNAMIC_FILE_KEY"), 0).getString("SO_ERROR_KEY", "")))));
        }
        if (b != null) {
            for (ij ijVar2 : b) {
                if (ijVar2 != null && ij.a(ijVar2, ijVar)) {
                    return true;
                }
            }
        }
        return false;
    }
}
