package cn.jiguang.a.b;

import android.content.Context;
import android.content.SharedPreferences;

public class c {
    private static volatile c b;
    private SharedPreferences a = null;

    private SharedPreferences a(Context context) {
        if (this.a == null) {
            this.a = context.getSharedPreferences("JPushSA_Config", 0);
        }
        return this.a;
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public final long a(Context context, String str, long j) {
        return a(context).getLong(str, j);
    }

    public final String a(Context context, String str, String str2) {
        return a(context).getString(str, null);
    }

    public final void b(Context context, String str, long j) {
        a(context).edit().putLong(str, j).commit();
    }

    public final void b(Context context, String str, String str2) {
        a(context).edit().putString(str, str2).commit();
    }
}
