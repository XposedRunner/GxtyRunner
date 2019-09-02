package com.lzy.okgo.d;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.lzy.okgo.f.d;

/* compiled from: DBUtils */
public class e {
    public static boolean a(SQLiteDatabase sQLiteDatabase, f fVar) {
        if (!a(sQLiteDatabase, fVar.a)) {
            return true;
        }
        Cursor rawQuery = sQLiteDatabase.rawQuery("select * from " + fVar.a, null);
        if (rawQuery == null) {
            return false;
        }
        try {
            int b = fVar.b();
            if (b == rawQuery.getColumnCount()) {
                for (int i = 0; i < b; i++) {
                    if (fVar.a(rawQuery.getColumnName(i)) == -1) {
                        return true;
                    }
                }
                rawQuery.close();
                return false;
            }
            rawQuery.close();
            return true;
        } finally {
            rawQuery.close();
        }
    }

    public static boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        boolean z = true;
        if (str == null || sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return false;
        }
        Cursor cursor = null;
        int i;
        try {
            cursor = sQLiteDatabase.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", str});
            if (cursor.moveToFirst()) {
                i = cursor.getInt(0);
                if (cursor != null) {
                    cursor.close();
                }
                if (i <= 0) {
                    z = false;
                }
                return z;
            } else if (cursor == null) {
                return false;
            } else {
                cursor.close();
                return false;
            }
        } catch (Throwable e) {
            d.a(e);
            if (cursor != null) {
                cursor.close();
                i = 0;
            } else {
                i = 0;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
