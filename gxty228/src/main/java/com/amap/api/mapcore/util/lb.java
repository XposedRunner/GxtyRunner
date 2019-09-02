package com.amap.api.mapcore.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;

/* compiled from: SpUtil */
public final class lb {
    public static int a(Context context, String str, String str2) {
        int i = 200;
        try {
            i = context.getSharedPreferences(str, 0).getInt(str2, 200);
        } catch (Throwable th) {
            kz.a(th, "SpUtil", "getPrefsInt");
        }
        return i;
    }

    public static String a(Context context) {
        String str = "00:00:00:00:00:00";
        return context == null ? str : b(context, "pref", "smac", str);
    }

    public static void a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            a(context, "pref", "smac", str);
        }
    }

    private static void a(Context context, String str, String str2, String str3) {
        try {
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putString(str2, str3);
            a(edit);
        } catch (Throwable th) {
            kz.a(th, "SpUtil", "setPrefsStr");
        }
    }

    @SuppressLint({"NewApi"})
    private static void a(Editor editor) {
        if (editor != null) {
            if (VERSION.SDK_INT >= 9) {
                editor.apply();
            } else {
                b(editor);
            }
        }
    }

    private static String b(Context context, String str, String str2, String str3) {
        try {
            str3 = context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
            kz.a(th, "SpUtil", "getPrefsInt");
        }
        return str3;
    }

    private static void b(final Editor editor) {
        try {
            new AsyncTask<Void, Void, Void>() {
                private Void a() {
                    try {
                        if (editor != null) {
                            editor.commit();
                        }
                    } catch (Throwable th) {
                        kz.a(th, "SpUtil", "commit");
                    }
                    return null;
                }

                protected final /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a();
                }
            }.execute(new Void[]{null, null, null});
        } catch (Throwable th) {
            kz.a(th, "SpUtil", "commit1");
        }
    }

    public static boolean b(Context context, String str, String str2) {
        boolean z = true;
        try {
            z = context.getSharedPreferences(str, 0).getBoolean(str2, true);
        } catch (Throwable th) {
            kz.a(th, "SpUtil", "getPrefsBoolean");
        }
        return z;
    }
}
