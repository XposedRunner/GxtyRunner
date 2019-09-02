package cn.jiguang.g.b;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import cn.jiguang.e.d;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map.Entry;

public final class c {
    public static java.io.Serializable a(android.content.SharedPreferences r8, java.lang.String r9, int r10) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:cn.jiguang.g.b.c.a(android.content.SharedPreferences, java.lang.String, int):java.io.Serializable. bs: [B:3:0x000b, B:9:0x0038, B:17:0x0044]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r0 = 0;
        r1 = r8.contains(r9);
        if (r1 != 0) goto L_0x0008;
    L_0x0007:
        return r0;
    L_0x0008:
        switch(r10) {
            case 0: goto L_0x0037;
            case 1: goto L_0x0077;
            case 2: goto L_0x0043;
            case 3: goto L_0x007d;
            case 4: goto L_0x0060;
            case 5: goto L_0x0087;
            case 6: goto L_0x0092;
            default: goto L_0x000b;
        };
    L_0x000b:
        r1 = new java.lang.IllegalArgumentException;	 Catch:{ Throwable -> 0x0013 }
        r2 = "[SpHelper], action - readInternal , unsupport type";	 Catch:{ Throwable -> 0x0013 }
        r1.<init>(r2);	 Catch:{ Throwable -> 0x0013 }
        throw r1;	 Catch:{ Throwable -> 0x0013 }
    L_0x0013:
        r1 = move-exception;
        r2 = "SpHelper";
        r3 = new java.lang.StringBuilder;
        r4 = "exception when get [";
        r3.<init>(r4);
        r3 = r3.append(r9);
        r4 = "]";
        r3 = r3.append(r4);
        r4 = r1.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        cn.jiguang.e.d.g(r2, r3, r1);
        goto L_0x0007;
    L_0x0037:
        r1 = 0;
        r0 = r8.getString(r9, r1);	 Catch:{ ClassCastException -> 0x003d }
        goto L_0x0007;
    L_0x003d:
        r1 = move-exception;
        r0 = cn.jiguang.g.b.a.a(r8, r9);	 Catch:{ Throwable -> 0x0013 }
        goto L_0x0007;
    L_0x0043:
        r1 = 0;
        r1 = r8.getInt(r9, r1);	 Catch:{ ClassCastException -> 0x004d }
        r0 = java.lang.Integer.valueOf(r1);	 Catch:{ ClassCastException -> 0x004d }
        goto L_0x0007;
    L_0x004d:
        r1 = move-exception;
        r2 = 0;
        r2 = r8.getLong(r9, r2);	 Catch:{ Throwable -> 0x0013 }
        r4 = (int) r2;	 Catch:{ Throwable -> 0x0013 }
        r6 = (long) r4;	 Catch:{ Throwable -> 0x0013 }
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));	 Catch:{ Throwable -> 0x0013 }
        if (r2 != 0) goto L_0x005f;	 Catch:{ Throwable -> 0x0013 }
    L_0x005a:
        r0 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0013 }
        goto L_0x0007;	 Catch:{ Throwable -> 0x0013 }
    L_0x005f:
        throw r1;	 Catch:{ Throwable -> 0x0013 }
    L_0x0060:
        r2 = 0;
        r2 = r8.getLong(r9, r2);	 Catch:{ ClassCastException -> 0x006b }
        r0 = java.lang.Long.valueOf(r2);	 Catch:{ ClassCastException -> 0x006b }
        goto L_0x0007;
    L_0x006b:
        r1 = move-exception;
        r1 = 0;
        r1 = r8.getInt(r9, r1);	 Catch:{ Throwable -> 0x0013 }
        r2 = (long) r1;	 Catch:{ Throwable -> 0x0013 }
        r0 = java.lang.Long.valueOf(r2);	 Catch:{ Throwable -> 0x0013 }
        goto L_0x0007;	 Catch:{ Throwable -> 0x0013 }
    L_0x0077:
        r1 = 0;	 Catch:{ Throwable -> 0x0013 }
        r0 = r8.getString(r9, r1);	 Catch:{ Throwable -> 0x0013 }
        goto L_0x0007;	 Catch:{ Throwable -> 0x0013 }
    L_0x007d:
        r1 = 0;	 Catch:{ Throwable -> 0x0013 }
        r1 = r8.getBoolean(r9, r1);	 Catch:{ Throwable -> 0x0013 }
        r0 = java.lang.Boolean.valueOf(r1);	 Catch:{ Throwable -> 0x0013 }
        goto L_0x0007;	 Catch:{ Throwable -> 0x0013 }
    L_0x0087:
        r1 = 0;	 Catch:{ Throwable -> 0x0013 }
        r1 = r8.getFloat(r9, r1);	 Catch:{ Throwable -> 0x0013 }
        r0 = java.lang.Float.valueOf(r1);	 Catch:{ Throwable -> 0x0013 }
        goto L_0x0007;	 Catch:{ Throwable -> 0x0013 }
    L_0x0092:
        r0 = cn.jiguang.g.b.a.a(r8, r9);	 Catch:{ Throwable -> 0x0013 }
        goto L_0x0007;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.g.b.c.a(android.content.SharedPreferences, java.lang.String, int):java.io.Serializable");
    }

    public static <T extends Serializable> T a(SharedPreferences sharedPreferences, String str, T t) {
        try {
            T a = a(sharedPreferences, str, a.a(t));
            return a == null ? t : a;
        } catch (Throwable th) {
            d.g("SpHelper", th.getMessage(), th);
            return t;
        }
    }

    private static <T extends Serializable> void a(Editor editor, String str, T t) {
        if (t == null) {
            editor.remove(str);
        } else if (t instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) t).booleanValue());
        } else if (t instanceof Integer) {
            editor.putInt(str, ((Integer) t).intValue());
        } else if (t instanceof Long) {
            editor.putLong(str, ((Long) t).longValue());
        } else if (t instanceof Float) {
            editor.putFloat(str, ((Float) t).floatValue());
        } else if (t instanceof String) {
            editor.putString(str, (String) t);
        } else if ((t instanceof HashSet) && VERSION.SDK_INT >= 11) {
            try {
                editor.putStringSet(str, (HashSet) t);
            } catch (ClassCastException e) {
            }
        }
    }

    public static boolean a(SharedPreferences sharedPreferences, ContentValues contentValues) {
        if (contentValues == null) {
            return true;
        }
        Editor edit = sharedPreferences.edit();
        for (Entry entry : contentValues.valueSet()) {
            a(edit, (String) entry.getKey(), (Serializable) entry.getValue());
        }
        return edit.commit();
    }

    public static boolean a(SharedPreferences sharedPreferences, a aVar) {
        if (aVar == null) {
            return true;
        }
        Editor edit = sharedPreferences.edit();
        for (Entry entry : aVar.a()) {
            a(edit, (String) entry.getKey(), (Serializable) entry.getValue());
        }
        return edit.commit();
    }

    public static <T extends Serializable> boolean b(SharedPreferences sharedPreferences, String str, T t) {
        Editor edit = sharedPreferences.edit();
        a(edit, str, (Serializable) t);
        return edit.commit();
    }
}
