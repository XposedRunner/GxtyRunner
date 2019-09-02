package com.amap.api.services.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: DBOperation */
public class s {
    private static Map<Class<? extends r>, r> d = new HashMap();
    private v a;
    private SQLiteDatabase b;
    private r c;

    public static synchronized r a(Class<? extends r> cls) throws IllegalAccessException, InstantiationException {
        r rVar;
        synchronized (s.class) {
            if (d.get(cls) == null) {
                d.put(cls, cls.newInstance());
            }
            rVar = (r) d.get(cls);
        }
        return rVar;
    }

    public s(Context context, r rVar) {
        try {
            this.a = new v(context.getApplicationContext(), rVar.a(), null, rVar.b(), rVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.c = rVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> java.util.List<T> a(java.lang.String r13, java.lang.Class<T> r14, boolean r15) {
        /*
        r12 = this;
        r9 = 0;
        r10 = r12.c;
        monitor-enter(r10);
        r8 = new java.util.ArrayList;	 Catch:{ all -> 0x0094 }
        r8.<init>();	 Catch:{ all -> 0x0094 }
        r11 = r12.b(r14);	 Catch:{ all -> 0x0094 }
        r1 = r12.a(r11);	 Catch:{ all -> 0x0094 }
        r0 = r12.b;	 Catch:{ all -> 0x0094 }
        if (r0 != 0) goto L_0x001b;
    L_0x0015:
        r0 = r12.a(r15);	 Catch:{ all -> 0x0094 }
        r12.b = r0;	 Catch:{ all -> 0x0094 }
    L_0x001b:
        r0 = r12.b;	 Catch:{ all -> 0x0094 }
        if (r0 == 0) goto L_0x0027;
    L_0x001f:
        r0 = android.text.TextUtils.isEmpty(r1);	 Catch:{ all -> 0x0094 }
        if (r0 != 0) goto L_0x0027;
    L_0x0025:
        if (r13 != 0) goto L_0x002a;
    L_0x0027:
        monitor-exit(r10);	 Catch:{ all -> 0x0094 }
        r0 = r8;
    L_0x0029:
        return r0;
    L_0x002a:
        r0 = r12.b;	 Catch:{ Throwable -> 0x0106, all -> 0x0080 }
        r2 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r3 = r13;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Throwable -> 0x0106, all -> 0x0080 }
        if (r1 != 0) goto L_0x0054;
    L_0x0038:
        r0 = r12.b;	 Catch:{ Throwable -> 0x0062 }
        r0.close();	 Catch:{ Throwable -> 0x0062 }
        r0 = 0;
        r12.b = r0;	 Catch:{ Throwable -> 0x0062 }
        if (r1 == 0) goto L_0x0045;
    L_0x0042:
        r1.close();	 Catch:{ Throwable -> 0x00c3 }
    L_0x0045:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00cf }
        if (r0 == 0) goto L_0x0051;
    L_0x0049:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00cf }
        r0.close();	 Catch:{ Throwable -> 0x00cf }
        r0 = 0;
        r12.b = r0;	 Catch:{ Throwable -> 0x00cf }
    L_0x0051:
        monitor-exit(r10);	 Catch:{ all -> 0x0094 }
        r0 = r8;
        goto L_0x0029;
    L_0x0054:
        r0 = r1.moveToNext();	 Catch:{ Throwable -> 0x0062 }
        if (r0 == 0) goto L_0x00db;
    L_0x005a:
        r0 = r12.a(r1, r14, r11);	 Catch:{ Throwable -> 0x0062 }
        r8.add(r0);	 Catch:{ Throwable -> 0x0062 }
        goto L_0x0054;
    L_0x0062:
        r0 = move-exception;
    L_0x0063:
        if (r15 != 0) goto L_0x006c;
    L_0x0065:
        r2 = "dbs";
        r3 = "sld";
        com.amap.api.services.a.n.a(r0, r2, r3);	 Catch:{ all -> 0x0103 }
    L_0x006c:
        if (r1 == 0) goto L_0x0071;
    L_0x006e:
        r1.close();	 Catch:{ Throwable -> 0x00ad }
    L_0x0071:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00b8 }
        if (r0 == 0) goto L_0x007d;
    L_0x0075:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00b8 }
        r0.close();	 Catch:{ Throwable -> 0x00b8 }
        r0 = 0;
        r12.b = r0;	 Catch:{ Throwable -> 0x00b8 }
    L_0x007d:
        monitor-exit(r10);	 Catch:{ all -> 0x0094 }
        r0 = r8;
        goto L_0x0029;
    L_0x0080:
        r0 = move-exception;
        r1 = r9;
    L_0x0082:
        if (r1 == 0) goto L_0x0087;
    L_0x0084:
        r1.close();	 Catch:{ Throwable -> 0x0097 }
    L_0x0087:
        r1 = r12.b;	 Catch:{ Throwable -> 0x00a2 }
        if (r1 == 0) goto L_0x0093;
    L_0x008b:
        r1 = r12.b;	 Catch:{ Throwable -> 0x00a2 }
        r1.close();	 Catch:{ Throwable -> 0x00a2 }
        r1 = 0;
        r12.b = r1;	 Catch:{ Throwable -> 0x00a2 }
    L_0x0093:
        throw r0;	 Catch:{ all -> 0x0094 }
    L_0x0094:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0094 }
        throw r0;
    L_0x0097:
        r1 = move-exception;
        if (r15 != 0) goto L_0x0087;
    L_0x009a:
        r2 = "dbs";
        r3 = "sld";
        com.amap.api.services.a.n.a(r1, r2, r3);	 Catch:{ all -> 0x0094 }
        goto L_0x0087;
    L_0x00a2:
        r1 = move-exception;
        if (r15 != 0) goto L_0x0093;
    L_0x00a5:
        r2 = "dbs";
        r3 = "sld";
        com.amap.api.services.a.n.a(r1, r2, r3);	 Catch:{ all -> 0x0094 }
        goto L_0x0093;
    L_0x00ad:
        r0 = move-exception;
        if (r15 != 0) goto L_0x0071;
    L_0x00b0:
        r1 = "dbs";
        r2 = "sld";
        com.amap.api.services.a.n.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x0071;
    L_0x00b8:
        r0 = move-exception;
        if (r15 != 0) goto L_0x007d;
    L_0x00bb:
        r1 = "dbs";
        r2 = "sld";
        com.amap.api.services.a.n.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x007d;
    L_0x00c3:
        r0 = move-exception;
        if (r15 != 0) goto L_0x0045;
    L_0x00c6:
        r1 = "dbs";
        r2 = "sld";
        com.amap.api.services.a.n.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x0045;
    L_0x00cf:
        r0 = move-exception;
        if (r15 != 0) goto L_0x0051;
    L_0x00d2:
        r1 = "dbs";
        r2 = "sld";
        com.amap.api.services.a.n.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x0051;
    L_0x00db:
        if (r1 == 0) goto L_0x00e0;
    L_0x00dd:
        r1.close();	 Catch:{ Throwable -> 0x00f8 }
    L_0x00e0:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00ed }
        if (r0 == 0) goto L_0x007d;
    L_0x00e4:
        r0 = r12.b;	 Catch:{ Throwable -> 0x00ed }
        r0.close();	 Catch:{ Throwable -> 0x00ed }
        r0 = 0;
        r12.b = r0;	 Catch:{ Throwable -> 0x00ed }
        goto L_0x007d;
    L_0x00ed:
        r0 = move-exception;
        if (r15 != 0) goto L_0x007d;
    L_0x00f0:
        r1 = "dbs";
        r2 = "sld";
        com.amap.api.services.a.n.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x007d;
    L_0x00f8:
        r0 = move-exception;
        if (r15 != 0) goto L_0x00e0;
    L_0x00fb:
        r1 = "dbs";
        r2 = "sld";
        com.amap.api.services.a.n.a(r0, r1, r2);	 Catch:{ all -> 0x0094 }
        goto L_0x00e0;
    L_0x0103:
        r0 = move-exception;
        goto L_0x0082;
    L_0x0106:
        r0 = move-exception;
        r1 = r9;
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.s.a(java.lang.String, java.lang.Class, boolean):java.util.List<T>");
    }

    private <T> T a(Cursor cursor, Class<T> cls, t tVar) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] a = a(cls, tVar.b());
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : a) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(u.class);
            if (annotation != null) {
                u uVar = (u) annotation;
                int b = uVar.b();
                int columnIndex = cursor.getColumnIndex(uVar.a());
                switch (b) {
                    case 1:
                        field.set(newInstance, Short.valueOf(cursor.getShort(columnIndex)));
                        break;
                    case 2:
                        field.set(newInstance, Integer.valueOf(cursor.getInt(columnIndex)));
                        break;
                    case 3:
                        field.set(newInstance, Float.valueOf(cursor.getFloat(columnIndex)));
                        break;
                    case 4:
                        field.set(newInstance, Double.valueOf(cursor.getDouble(columnIndex)));
                        break;
                    case 5:
                        field.set(newInstance, Long.valueOf(cursor.getLong(columnIndex)));
                        break;
                    case 6:
                        field.set(newInstance, cursor.getString(columnIndex));
                        break;
                    case 7:
                        field.set(newInstance, cursor.getBlob(columnIndex));
                        break;
                    default:
                        break;
                }
            }
        }
        return newInstance;
    }

    private Field[] a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        if (z) {
            return cls.getSuperclass().getDeclaredFields();
        }
        return cls.getDeclaredFields();
    }

    private SQLiteDatabase a(boolean z) {
        try {
            if (this.b == null) {
                this.b = this.a.getReadableDatabase();
            }
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
            } else {
                n.a(th, "dbs", "grd");
            }
        }
        return this.b;
    }

    private boolean a(Annotation annotation) {
        return annotation != null;
    }

    private <T> String a(t tVar) {
        if (tVar == null) {
            return null;
        }
        return tVar.a();
    }

    private <T> t b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(t.class);
        if (a(annotation)) {
            return (t) annotation;
        }
        return null;
    }
}
