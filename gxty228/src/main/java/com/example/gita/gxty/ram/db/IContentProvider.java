package com.example.gita.gxty.ram.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import com.example.gita.gxty.utils.h;
import java.util.HashMap;
import java.util.Map;

public abstract class IContentProvider extends ContentProvider {
    private static UriMatcher b = new UriMatcher(-1);
    private static HashMap<String, String> c = new HashMap();
    private static HashMap<Integer, String> d = new HashMap();
    private a a;

    private class a extends SQLiteOpenHelper {
        final /* synthetic */ IContentProvider a;
        private Map<String, String> b;

        public a(IContentProvider iContentProvider, Context context, String str, CursorFactory cursorFactory, int i, Map<String, String> map) {
            this.a = iContentProvider;
            super(context, str, null, i);
            this.b = map;
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            a(sQLiteDatabase, true);
        }

        private void a(SQLiteDatabase sQLiteDatabase, boolean z) {
            h.a((Object) "------database---------onCreate----------");
            if (this.b != null && this.b.size() > 0) {
                for (String str : this.b.values()) {
                    if (z) {
                        sQLiteDatabase.execSQL(str);
                    } else if (this.a.a(sQLiteDatabase, str)) {
                        sQLiteDatabase.execSQL(str);
                    }
                }
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            h.a("------database---------onUpgrade----------" + i + " / " + i2);
            if (i2 > i) {
                if (this.b != null && this.b.size() > 0) {
                    for (String str : this.b.keySet()) {
                        if (this.a.a(sQLiteDatabase, str)) {
                            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + str);
                        }
                    }
                }
                a(sQLiteDatabase, false);
                this.a.a(sQLiteDatabase);
            }
        }
    }

    public boolean onCreate() {
        a a = a.a();
        if (a == null) {
            h.b("application 没有配置DBModel");
            return false;
        }
        Map e = a.e();
        for (Integer num : e.keySet()) {
            c.put(((c) e.get(num)).a(), ((c) e.get(num)).b());
            d.put(num, ((c) e.get(num)).a());
            b.addURI(a.c(), ((c) e.get(num)).a(), num.intValue());
        }
        this.a = new a(this, getContext(), a.b(), null, a.d(), c);
        return this.a != null;
    }

    public String getType(Uri uri) {
        String str = (String) d.get(Integer.valueOf(b.match(uri)));
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        String str4;
        SQLiteDatabase readableDatabase = this.a.getReadableDatabase();
        if (strArr2 != null) {
            int length = strArr2.length;
            if (length > 0) {
                str3 = strArr2[0];
                str4 = length > 1 ? strArr2[1] : null;
                return readableDatabase.query(getType(uri), strArr, str, null, str3, str4, str2);
            }
        }
        str4 = null;
        str3 = null;
        return readableDatabase.query(getType(uri), strArr, str, null, str3, str4, str2);
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        try {
            long insertWithOnConflict;
            try {
                insertWithOnConflict = this.a.getWritableDatabase().insertWithOnConflict(getType(uri), null, contentValues, 5);
            } catch (Exception e) {
                h.a(e);
                insertWithOnConflict = -1;
            }
            if (insertWithOnConflict == -1) {
                return uri;
            }
            Uri withAppendedId = ContentUris.withAppendedId(uri, insertWithOnConflict);
            getContext().getContentResolver().notifyChange(withAppendedId, null);
            return withAppendedId;
        } catch (Exception e2) {
            h.a(e2);
            return uri;
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        try {
            int delete = this.a.getWritableDatabase().delete(getType(uri), str, strArr);
            if (delete == -1) {
                return delete;
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return delete;
        } catch (Exception e) {
            h.a(e);
            return 0;
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        try {
            int update = this.a.getWritableDatabase().update(getType(uri), contentValues, str, strArr);
            if (update == -1) {
                return update;
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return update;
        } catch (Exception e) {
            h.a(e);
            return 0;
        }
    }

    protected void a(SQLiteDatabase sQLiteDatabase) {
    }

    protected boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        return true;
    }
}
