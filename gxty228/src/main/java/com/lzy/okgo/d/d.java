package com.lzy.okgo.d;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.lzy.okgo.a;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cookie.SerializableCookie;
import com.lzy.okgo.model.Progress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: DBHelper */
class d extends SQLiteOpenHelper {
    static final Lock a = new ReentrantLock();
    private f b;
    private f c;
    private f d;
    private f e;

    d() {
        this(a.a().b());
    }

    d(Context context) {
        super(context, "okgo.db", null, 1);
        this.b = new f("cache");
        this.c = new f(SerializableCookie.COOKIE);
        this.d = new f("download");
        this.e = new f("upload");
        this.b.a(new c(CacheEntity.KEY, "VARCHAR", true, true)).a(new c(CacheEntity.LOCAL_EXPIRE, "INTEGER")).a(new c(CacheEntity.HEAD, "BLOB")).a(new c("data", "BLOB"));
        this.c.a(new c(SerializableCookie.HOST, "VARCHAR")).a(new c("name", "VARCHAR")).a(new c(SerializableCookie.DOMAIN, "VARCHAR")).a(new c(SerializableCookie.COOKIE, "BLOB")).a(new c(SerializableCookie.HOST, "name", SerializableCookie.DOMAIN));
        this.d.a(new c(Progress.TAG, "VARCHAR", true, true)).a(new c("url", "VARCHAR")).a(new c(Progress.FOLDER, "VARCHAR")).a(new c(Progress.FILE_PATH, "VARCHAR")).a(new c(Progress.FILE_NAME, "VARCHAR")).a(new c(Progress.FRACTION, "VARCHAR")).a(new c(Progress.TOTAL_SIZE, "INTEGER")).a(new c(Progress.CURRENT_SIZE, "INTEGER")).a(new c("status", "INTEGER")).a(new c(Progress.PRIORITY, "INTEGER")).a(new c(Progress.DATE, "INTEGER")).a(new c("request", "BLOB")).a(new c(Progress.EXTRA1, "BLOB")).a(new c(Progress.EXTRA2, "BLOB")).a(new c(Progress.EXTRA3, "BLOB"));
        this.e.a(new c(Progress.TAG, "VARCHAR", true, true)).a(new c("url", "VARCHAR")).a(new c(Progress.FOLDER, "VARCHAR")).a(new c(Progress.FILE_PATH, "VARCHAR")).a(new c(Progress.FILE_NAME, "VARCHAR")).a(new c(Progress.FRACTION, "VARCHAR")).a(new c(Progress.TOTAL_SIZE, "INTEGER")).a(new c(Progress.CURRENT_SIZE, "INTEGER")).a(new c("status", "INTEGER")).a(new c(Progress.PRIORITY, "INTEGER")).a(new c(Progress.DATE, "INTEGER")).a(new c("request", "BLOB")).a(new c(Progress.EXTRA1, "BLOB")).a(new c(Progress.EXTRA2, "BLOB")).a(new c(Progress.EXTRA3, "BLOB"));
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(this.b.a());
        sQLiteDatabase.execSQL(this.c.a());
        sQLiteDatabase.execSQL(this.d.a());
        sQLiteDatabase.execSQL(this.e.a());
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (e.a(sQLiteDatabase, this.b)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cache");
        }
        if (e.a(sQLiteDatabase, this.c)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cookie");
        }
        if (e.a(sQLiteDatabase, this.d)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS download");
        }
        if (e.a(sQLiteDatabase, this.e)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS upload");
        }
        onCreate(sQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(sQLiteDatabase, i, i2);
    }
}
