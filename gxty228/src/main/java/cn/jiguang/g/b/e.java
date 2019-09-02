package cn.jiguang.g.b;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import cn.jiguang.e.d;
import cn.jiguang.g.a;
import cn.jiguang.net.HttpUtils;
import com.lzy.okgo.cache.CacheEntity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public final class e {
    public static Boolean a;
    private static Map<String, e> f = new HashMap();
    private static final Object g = new Object();
    private final String b;
    private String c;
    private SharedPreferences d;
    private ContentResolver e;

    private e(Context context, String str) {
        this.b = str;
        if (a == null) {
            a = Boolean.valueOf(a.b(context));
        }
        Context applicationContext = context != null ? context.getApplicationContext() : cn.jiguang.d.a.e;
        if (applicationContext != null) {
            this.c = "content://" + applicationContext.getPackageName() + ".DataProvider/" + str;
            this.d = applicationContext.getSharedPreferences(str, 0);
            this.e = applicationContext.getContentResolver();
            return;
        }
        d.i("SpResolver", "create SpResolver error,context is null");
    }

    public static e a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            d.i("SpResolver", "content provider name is empty");
            return null;
        }
        e eVar = (e) f.get(str);
        if (eVar != null) {
            return eVar;
        }
        synchronized (g) {
            eVar = (e) f.get(str);
            if (eVar == null) {
                eVar = new e(context, str);
                f.put(str, eVar);
            }
        }
        return eVar;
    }

    private String b() {
        if (this.c == null && cn.jiguang.d.a.e != null) {
            this.c = "content://" + cn.jiguang.d.a.e.getPackageName() + ".DataProvider/" + this.b;
        }
        return this.c;
    }

    private SharedPreferences c() {
        if (this.d == null && cn.jiguang.d.a.e != null) {
            this.d = cn.jiguang.d.a.e.getSharedPreferences(this.b, 0);
        }
        return this.d;
    }

    private a c(a aVar) {
        if (aVar == null || aVar.b() == 0) {
            return new a();
        }
        SharedPreferences c = c();
        if (c != null) {
            a aVar2 = new a();
            if (aVar != null) {
                for (Entry entry : aVar.a()) {
                    String str = (String) entry.getKey();
                    Serializable a = c.a(c, str, a.a((Serializable) entry.getValue()));
                    if (a != null) {
                        aVar2.a(str, a);
                    }
                }
            }
            return aVar2;
        }
        d.g("SpResolver", "can't get SharedPref when read " + aVar);
        return aVar;
    }

    private <T extends Serializable> T c(String str, T t) {
        SharedPreferences c = c();
        if (c != null) {
            return c.a(c, str, (Serializable) t);
        }
        d.g("SpResolver", "can't get SharedPref when read " + str);
        return t;
    }

    private ContentResolver d() {
        if (this.e == null && cn.jiguang.d.a.e != null) {
            this.e = cn.jiguang.d.a.e.getContentResolver();
        }
        return this.e;
    }

    private boolean d(a aVar) {
        SharedPreferences c = c();
        if (c != null) {
            return c.a(c, aVar);
        }
        d.g("SpResolver", "can't get SharedPref when write " + aVar);
        return false;
    }

    private <T extends Serializable> boolean d(String str, T t) {
        SharedPreferences c = c();
        if (c != null) {
            return c.b(c, str, t);
        }
        d.g("SpResolver", "can't get SharedPref when write " + str + HttpUtils.EQUAL_SIGN + t);
        return false;
    }

    public final a a(a aVar) {
        if (aVar == null || aVar.b() == 0) {
            return new a();
        }
        if (!a.booleanValue()) {
            return c(aVar);
        }
        ContentResolver d = d();
        String b = b();
        if (d == null || b == null) {
            d.g("SpResolver", "can't get resolver or path when get " + aVar);
            return c(aVar);
        }
        try {
            return d.a(d, b, aVar);
        } catch (Throwable th) {
            d.i("SpResolver", "content provider error" + th + " when read " + aVar);
            return c(aVar);
        }
    }

    public final Serializable a(String str, int i) {
        SharedPreferences c = c();
        if (c != null) {
            return c.a(c, str, i);
        }
        d.g("SpResolver", "can't get SharedPref when read " + str);
        return null;
    }

    public final <T extends Serializable> T a(String str, T t) {
        if (!a.booleanValue()) {
            return c(str, t);
        }
        ContentResolver d = d();
        String b = b();
        if (d == null || b == null) {
            d.i("SpResolver", "can't get resolver or path when get " + str);
            return c(str, t);
        }
        try {
            T type = d.getType(Uri.parse(b).buildUpon().appendQueryParameter(CacheEntity.KEY, str).appendQueryParameter("type", String.valueOf(a.a(t))).build());
            return type != null ? t == null ? type : t instanceof Integer ? Integer.valueOf(type) : t instanceof Boolean ? Boolean.valueOf(type) : t instanceof Long ? Long.valueOf(type) : t instanceof Float ? Float.valueOf(type) : t instanceof String ? type : t instanceof HashSet ? null : null : t;
        } catch (Throwable th) {
            d.g("SpResolver", "content provider error" + th + " when get " + str);
            return c(str, t);
        }
    }

    public final boolean a() {
        SharedPreferences c = c();
        if (c != null) {
            return c.edit().clear().commit();
        }
        d.g("SpResolver", "can't get SharedPref when clear");
        return false;
    }

    public final boolean a(ContentValues contentValues) {
        SharedPreferences c = c();
        if (c != null) {
            return c.a(c, contentValues);
        }
        d.g("SpResolver", "can't get SharedPref when write " + contentValues);
        return false;
    }

    public final boolean b(a aVar) {
        if (!a.booleanValue()) {
            return d(aVar);
        }
        ContentResolver d = d();
        String b = b();
        if (d == null || b == null) {
            d.g("SpResolver", "can't get resolver or path when write " + aVar);
            return d(aVar);
        }
        try {
            return d.b(d, b, aVar);
        } catch (Throwable th) {
            d.i("SpResolver", "content provider error" + th + " when write " + aVar);
            return d(aVar);
        }
    }

    public final <T extends Serializable> boolean b(String str, T t) {
        if (!a.booleanValue()) {
            return d(str, t);
        }
        ContentResolver d = d();
        String b = b();
        if (d == null || b == null) {
            d.g("SpResolver", "can't get resolver or path when write " + str + HttpUtils.EQUAL_SIGN + t);
            return d(str, t);
        }
        try {
            return d.a(d, b, str, t);
        } catch (Throwable th) {
            d.i("SpResolver", "content provider error" + th + " when write " + str + HttpUtils.EQUAL_SIGN + t);
            return d(str, t);
        }
    }
}
