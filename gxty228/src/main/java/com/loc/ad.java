package com.loc;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: DynamicFileDBCreator */
public final class ad implements o {
    private static ad a;

    private ad() {
    }

    public static synchronized ad b() {
        ad adVar;
        synchronized (ad.class) {
            if (a == null) {
                a = new ad();
            }
            adVar = a;
        }
        return adVar;
    }

    public final String a() {
        return "dafile.db";
    }

    public final void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sname  varchar(20), fname varchar(100),md varchar(20),version varchar(20),dversion varchar(20),status varchar(20),reservedfield varchar(20));");
        } catch (Throwable th) {
            g.a(th, "DynamicFileDBCreator", "onCreate");
        }
    }
}
