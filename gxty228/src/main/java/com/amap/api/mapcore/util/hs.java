package com.amap.api.mapcore.util;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: DynamicFileDBCreator */
public class hs implements hd {
    private static hs a;

    public static synchronized hs a() {
        hs hsVar;
        synchronized (hs.class) {
            if (a == null) {
                a = new hs();
            }
            hsVar = a;
        }
        return hsVar;
    }

    private hs() {
    }

    public String b() {
        return "dafile.db";
    }

    public int c() {
        return 1;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sname  varchar(20), fname varchar(100),md varchar(20),version varchar(20),dversion varchar(20),status varchar(20),reservedfield varchar(20));");
        } catch (Throwable th) {
            hw.a(th, "DynamicFileDBCreator", "onCreate");
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
