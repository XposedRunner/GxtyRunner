package com.lzy.okgo.d;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.lzy.okgo.f.d;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

/* compiled from: BaseDao */
public abstract class a<T> {
    protected static String a;
    protected Lock b = d.a;
    protected SQLiteOpenHelper c;
    protected SQLiteDatabase d;

    public abstract T a(Cursor cursor);

    public abstract ContentValues b(T t);

    public abstract String b();

    public a(SQLiteOpenHelper sQLiteOpenHelper) {
        a = getClass().getSimpleName();
        this.c = sQLiteOpenHelper;
        this.d = a();
    }

    public SQLiteDatabase a() {
        return this.c.getWritableDatabase();
    }

    protected final void a(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        if (!(cursor == null || cursor.isClosed())) {
            cursor.close();
        }
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            sQLiteDatabase.close();
        }
    }

    public boolean a(String str, String[] strArr) {
        long currentTimeMillis = System.currentTimeMillis();
        this.b.lock();
        try {
            this.d.beginTransaction();
            this.d.delete(b(), str, strArr);
            this.d.setTransactionSuccessful();
            this.d.endTransaction();
            this.b.unlock();
            d.a(a, (System.currentTimeMillis() - currentTimeMillis) + " delete");
            return true;
        } catch (Throwable e) {
            d.a(e);
            this.d.endTransaction();
            this.b.unlock();
            d.a(a, (System.currentTimeMillis() - currentTimeMillis) + " delete");
            return false;
        } catch (Throwable th) {
            this.d.endTransaction();
            this.b.unlock();
            d.a(a, (System.currentTimeMillis() - currentTimeMillis) + " delete");
        }
    }

    public boolean a(T t) {
        boolean z = false;
        if (t == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.b.lock();
        try {
            this.d.beginTransaction();
            this.d.replace(b(), null, b(t));
            this.d.setTransactionSuccessful();
            z = true;
            return z;
        } catch (Throwable e) {
            d.a(e);
            return z;
        } finally {
            this.d.endTransaction();
            this.b.unlock();
            d.a(a, (System.currentTimeMillis() - currentTimeMillis) + " replaceT");
        }
    }

    public List<T> b(String str, String[] strArr) {
        return a(null, str, strArr, null, null, null, null);
    }

    public List<T> a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        Cursor query;
        Throwable e;
        long currentTimeMillis = System.currentTimeMillis();
        this.b.lock();
        List<T> arrayList = new ArrayList();
        try {
            this.d.beginTransaction();
            query = this.d.query(b(), strArr, str, strArr2, str2, str3, str4, str5);
            while (!query.isClosed() && query.moveToNext()) {
                try {
                    arrayList.add(a(query));
                } catch (Exception e2) {
                    e = e2;
                }
            }
            this.d.setTransactionSuccessful();
            a(null, query);
            this.d.endTransaction();
            this.b.unlock();
            d.a(a, (System.currentTimeMillis() - currentTimeMillis) + " query");
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                d.a(e);
                a(null, query);
                this.d.endTransaction();
                this.b.unlock();
                d.a(a, (System.currentTimeMillis() - currentTimeMillis) + " query");
                return arrayList;
            } catch (Throwable th) {
                e = th;
                a(null, query);
                this.d.endTransaction();
                this.b.unlock();
                d.a(a, (System.currentTimeMillis() - currentTimeMillis) + " query");
                throw e;
            }
        } catch (Throwable th2) {
            e = th2;
            query = null;
            a(null, query);
            this.d.endTransaction();
            this.b.unlock();
            d.a(a, (System.currentTimeMillis() - currentTimeMillis) + " query");
            throw e;
        }
        return arrayList;
    }
}
