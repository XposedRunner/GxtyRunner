package com.amap.api.services.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: DB */
public class v extends SQLiteOpenHelper {
    private static boolean b = true;
    private static boolean c = false;
    private r a;

    public v(Context context, String str, CursorFactory cursorFactory, int i, r rVar) {
        super(context, str, cursorFactory, i);
        this.a = rVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.a.a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.a.a(sQLiteDatabase, i, i2);
    }
}
