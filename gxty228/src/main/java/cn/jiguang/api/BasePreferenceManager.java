package cn.jiguang.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.jiguang.g.b.a;

public abstract class BasePreferenceManager {
    public static final String JPUSH_PREF = "cn.jpush.preferences.v2";
    private static SharedPreferences mSharedPreferences = null;

    protected static void applyBuffer(Context context, a aVar) {
        init(context);
        aVar.a(mSharedPreferences, true);
    }

    protected static void applyBuffer(a aVar) {
        if (mSharedPreferences != null) {
            aVar.a(mSharedPreferences, true);
        }
    }

    protected static void commitBoolean(Context context, String str, boolean z) {
        init(context);
        Editor edit = mSharedPreferences.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    protected static void commitBoolean(String str, boolean z) {
        if (mSharedPreferences != null) {
            Editor edit = mSharedPreferences.edit();
            edit.putBoolean(str, z);
            edit.apply();
        }
    }

    protected static void commitInt(Context context, String str, int i) {
        init(context);
        Editor edit = mSharedPreferences.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    protected static void commitInt(String str, int i) {
        if (mSharedPreferences != null) {
            Editor edit = mSharedPreferences.edit();
            edit.putInt(str, i);
            edit.apply();
        }
    }

    protected static void commitLong(Context context, String str, long j) {
        init(context);
        Editor edit = mSharedPreferences.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    protected static void commitLong(String str, long j) {
        if (mSharedPreferences != null) {
            Editor edit = mSharedPreferences.edit();
            edit.putLong(str, j);
            edit.apply();
        }
    }

    protected static void commitString(Context context, String str, String str2) {
        init(context);
        Editor edit = mSharedPreferences.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    protected static void commitString(String str, String str2) {
        if (mSharedPreferences != null) {
            Editor edit = mSharedPreferences.edit();
            edit.putString(str, str2);
            edit.apply();
        }
    }

    protected static void commitStringWithEncryption(String str, String str2) {
        Editor edit = mSharedPreferences.edit();
        edit.putString(str, cn.jiguang.d.g.a.a.a(str2));
        edit.apply();
    }

    protected static Boolean getBoolean(Context context, String str, boolean z) {
        init(context);
        return Boolean.valueOf(mSharedPreferences.getBoolean(str, z));
    }

    protected static Boolean getBoolean(String str, boolean z) {
        return mSharedPreferences != null ? Boolean.valueOf(mSharedPreferences.getBoolean(str, z)) : Boolean.valueOf(z);
    }

    protected static int getInt(Context context, String str, int i) {
        init(context);
        return mSharedPreferences.getInt(str, i);
    }

    protected static int getInt(String str, int i) {
        return mSharedPreferences != null ? mSharedPreferences.getInt(str, i) : i;
    }

    protected static long getLong(Context context, String str, long j) {
        init(context);
        return mSharedPreferences.getLong(str, j);
    }

    protected static long getLong(String str, long j) {
        return mSharedPreferences != null ? mSharedPreferences.getLong(str, j) : j;
    }

    protected static String getString(Context context, String str, String str2) {
        init(context);
        return mSharedPreferences.getString(str, str2);
    }

    protected static String getString(String str, String str2) {
        return mSharedPreferences != null ? mSharedPreferences.getString(str, str2) : str2;
    }

    protected static String getStringUnencrypted(String str, String str2) {
        return cn.jiguang.d.g.a.a.b(mSharedPreferences.getString(str, str2), str2);
    }

    public static void init(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(JPUSH_PREF, 0);
        }
    }

    public static void removeAll() {
        Editor edit = mSharedPreferences.edit();
        edit.clear();
        edit.commit();
    }

    public static void removeKey(String str) {
        Editor edit = mSharedPreferences.edit();
        edit.remove(str);
        edit.commit();
    }
}
