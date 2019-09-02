package cn.jiguang.d.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.jiguang.e.d;

public final class g extends e {
    private static final String[] a = new String[]{"st_sort_key", "_id", "st_net", "st_conn_ip", "st_local_dns", "st_source", "st_failed", "st_total", "st_count_1", "st_count_1_3", "st_count_3_10", "st_count_10"};
    private static volatile g b;
    private static final Object c = new Object();

    private g(Context context) {
        super(context, "jpush_statistics.db", null, 1);
    }

    public static g a(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new g(context);
                }
            }
        }
        return b;
    }

    public static h a(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            d.c("StatisticsDB", "cursor is null");
            return null;
        }
        try {
            h hVar = new h();
            hVar.a(cursor.getString(1));
            hVar.b(cursor.getString(2));
            hVar.c(cursor.getString(3));
            hVar.d(cursor.getString(4));
            hVar.e(cursor.getString(5));
            hVar.a(cursor.getInt(6));
            hVar.b(cursor.getInt(7));
            hVar.c(cursor.getInt(8));
            hVar.d(cursor.getInt(9));
            hVar.e(cursor.getInt(10));
            hVar.f(cursor.getInt(11));
            d.e("StatisticsDB", hVar.toString());
            return hVar;
        } catch (Exception e) {
            e.printStackTrace();
            d.i("StatisticsDB", "getStatisticsData exception:" + e.getMessage());
            return null;
        }
    }

    public final long a(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, int i5, int i6) {
        long j = 0;
        if (a(true)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("st_sort_key", str);
            contentValues.put("st_net", str2);
            contentValues.put("st_conn_ip", str3);
            contentValues.put("st_local_dns", str4);
            contentValues.put("st_source", str5);
            contentValues.put("st_failed", Integer.valueOf(i));
            contentValues.put("st_total", Integer.valueOf(1));
            contentValues.put("st_count_1", Integer.valueOf(i3));
            contentValues.put("st_count_1_3", Integer.valueOf(i4));
            contentValues.put("st_count_3_10", Integer.valueOf(i5));
            contentValues.put("st_count_10", Integer.valueOf(0));
            try {
                j = getWritableDatabase().insert("jpush_statistics", null, contentValues);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                b(true);
            }
        }
        return j;
    }

    public final Cursor a() {
        try {
            Cursor rawQuery = getReadableDatabase().rawQuery("select * from jpush_statistics where st_failed > 0  order by st_failed desc limit 3", null);
            if (rawQuery == null) {
                return rawQuery;
            }
            rawQuery.moveToFirst();
            return rawQuery;
        } catch (Exception e) {
            d.g("StatisticsDB", "getFailedTop3Data exception:" + e.getMessage());
            return null;
        }
    }

    public final h a(String str) {
        Cursor query;
        Throwable th;
        if (a(false)) {
            try {
                query = getReadableDatabase().query(true, "jpush_statistics", a, "st_sort_key='" + str + "'", null, null, null, null, null);
                if (query != null) {
                    try {
                        query.moveToFirst();
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        b(false);
                        throw th;
                    }
                }
                h a = a(query);
                if (query != null) {
                    query.close();
                }
                b(false);
                return a;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                b(false);
                throw th;
            }
        }
        throw new Exception("database open failed");
    }

    public final long b(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, int i5, int i6) {
        long j = 0;
        if (!a(true)) {
            return j;
        }
        String str6 = "st_sort_key='" + str + "'";
        ContentValues contentValues = new ContentValues();
        contentValues.put("st_sort_key", str);
        contentValues.put("st_net", str2);
        contentValues.put("st_conn_ip", str3);
        contentValues.put("st_local_dns", str4);
        contentValues.put("st_source", str5);
        contentValues.put("st_failed", Integer.valueOf(i));
        contentValues.put("st_total", Integer.valueOf(i2));
        contentValues.put("st_count_1", Integer.valueOf(i3));
        contentValues.put("st_count_1_3", Integer.valueOf(i4));
        contentValues.put("st_count_3_10", Integer.valueOf(i5));
        contentValues.put("st_count_10", Integer.valueOf(i6));
        try {
            j = (long) getWritableDatabase().update("jpush_statistics", contentValues, str6, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            b(true);
        }
        return j;
    }

    public final Cursor b() {
        try {
            Cursor rawQuery = getReadableDatabase().rawQuery("select * from jpush_statistics where st_total > 0 and st_failed = 0  order by st_total desc limit 3", null);
            if (rawQuery == null) {
                return rawQuery;
            }
            rawQuery.moveToFirst();
            return rawQuery;
        } catch (Exception e) {
            d.g("StatisticsDB", "getSucceedTop3Data exception:" + e.getMessage());
            return null;
        }
    }

    public final int c(boolean z) {
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().rawQuery("select SUM(" + (z ? "st_total" : "st_failed") + ") from jpush_statistics", null);
            if (cursor != null) {
                cursor.moveToFirst();
                int i = cursor.getInt(0);
                if (cursor == null) {
                    return i;
                }
                cursor.close();
                return i;
            }
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        } catch (Exception e) {
            d.g("StatisticsDB", "getCountTotalOrFailed exception:" + e.getMessage());
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE jpush_statistics (_id INTEGER PRIMARY KEY AUTOINCREMENT ,st_sort_key text not null,st_net text not null,st_conn_ip text not null,st_local_dns text,st_source integer not null,st_failed integer not null,st_total integer not null,st_count_1 integer,st_count_1_3 integer,st_count_3_10 integer,st_count_10 integer);");
        } catch (Exception e) {
            d.a("StatisticsDB", "table jpush_statistics is already");
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS jpush_statistics");
        onCreate(sQLiteDatabase);
    }
}
