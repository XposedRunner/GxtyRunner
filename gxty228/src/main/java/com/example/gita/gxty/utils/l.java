package com.example.gita.gxty.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* compiled from: PreferEntryUtils */
public class l {
    private static l a = null;
    private SharedPreferences b;

    private l(Context context) {
        this.b = context.getSharedPreferences("gxty_prefer_entry", 0);
    }

    public static l a(Context context) {
        if (a == null) {
            a = new l(context);
        }
        return a;
    }

    public void a() {
        Editor edit = this.b.edit();
        edit.clear();
        edit.commit();
    }

    public void a(String str) {
        Editor edit = this.b.edit();
        edit.putString("entry_url", str);
        edit.commit();
    }

    public String b() {
        return this.b.getString("entry_url", "http://gxhttp.chinacloudapp.cn/api/");
    }
}
