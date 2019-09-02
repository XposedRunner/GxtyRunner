package com.amap.api.mapcore.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.jiguang.net.HttpUtils;

/* compiled from: OfflineDBCreator */
public class az implements hd {
    private static volatile az a;

    public static az a() {
        if (a == null) {
            synchronized (az.class) {
                if (a == null) {
                    a = new az();
                }
            }
        }
        return a;
    }

    private az() {
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS update_item (_id integer primary key autoincrement, title  TEXT, url TEXT,mAdcode TEXT,fileName TEXT,version TEXT,lLocalLength INTEGER,lRemoteLength INTEGER,localPath TEXT,mIndex INTEGER,isProvince INTEGER NOT NULL,mCompleteCode INTEGER,mCityCode TEXT,mState INTEGER,mPinyin TEXT, UNIQUE(mAdcode));");
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS update_item_file (_id integer primary key autoincrement,mAdcode TTEXT, file TEXT);");
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS update_item_download_info (_id integer primary key autoincrement,mAdcode TEXT,fileLength integer,splitter integer,startPos integer,endPos integer, UNIQUE(mAdcode));");
            } catch (Throwable th) {
                gz.c(th, "DB", "onCreate");
                th.printStackTrace();
            }
        }
    }

    public String b() {
        return "offlineDbV4.db";
    }

    public int c() {
        return 2;
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        SQLiteDatabase sQLiteDatabase2 = null;
        if (sQLiteDatabase != null) {
            switch (i) {
                case 1:
                    sQLiteDatabase.execSQL("ALTER TABLE update_item ADD COLUMN mPinyin TEXT;");
                    Cursor query = sQLiteDatabase.query("update_item", null, null, null, null, null, null);
                    if (query == null) {
                        sQLiteDatabase.close();
                    } else {
                        sQLiteDatabase2 = sQLiteDatabase;
                    }
                    if (query != null) {
                        while (query.moveToNext()) {
                            String string = query.getString(query.getColumnIndex("url"));
                            String substring = string.substring(string.lastIndexOf(HttpUtils.PATHS_SEPARATOR) + 1);
                            substring = substring.substring(0, substring.lastIndexOf("."));
                            sQLiteDatabase2.execSQL("update update_item set mPinyin=? where url =?", new String[]{substring, string});
                        }
                        query.close();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
