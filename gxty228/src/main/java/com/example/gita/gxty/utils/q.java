package com.example.gita.gxty.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.example.gita.gxty.MyApplication;

/* compiled from: SPUtils */
public class q {
    private static q a = null;
    private SharedPreferences b;

    public void a(String str, Object obj) {
        Editor edit = this.b.edit();
        if (obj instanceof String) {
            edit.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Long) obj).longValue());
        } else {
            edit.putString(str, "");
        }
        edit.commit();
    }

    public Object b(String str, Object obj) {
        h.b("SPUtils" + obj);
        if (obj instanceof String) {
            return this.b.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(this.b.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(this.b.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(this.b.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(this.b.getLong(str, ((Long) obj).longValue()));
        }
        return null;
    }

    public void a(String str) {
        Editor edit = this.b.edit();
        edit.remove(str);
        edit.commit();
    }

    public void a() {
        Editor edit = this.b.edit();
        edit.clear();
        edit.commit();
    }

    public String b() {
        return (String) b("userid", "0");
    }

    public String c() {
        return (String) b("utoken", "");
    }

    private q(Context context) {
        this.b = context.getSharedPreferences("gxty_sputils2", 32768);
    }

    public static q a(Context context) {
        if (a == null) {
            a = new q(MyApplication.e());
        }
        return a;
    }
}
