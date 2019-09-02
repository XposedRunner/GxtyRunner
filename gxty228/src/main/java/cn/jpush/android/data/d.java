package cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.jpush.android.d.e;
import org.altbeacon.beacon.BeaconManager;

public final class d extends f {
    private static final String[] a = new String[]{"_id", "ln_id", "ln_count", "ln_remove", "ln_type", "ln_extra", "ln_trigger_time", "ln_add_time"};
    private static volatile d b;
    private static final Object c = new Object();

    private d(Context context) {
        super(context, "jpush_local_notification.db", null, 1);
    }

    public static d a(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new d(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE t_localnotification (_id INTEGER PRIMARY KEY AUTOINCREMENT ,ln_id long not null,ln_count integer not null,ln_remove integer not null,ln_type integer not null,ln_extra text ,ln_trigger_time long ,ln_add_time long );");
        } catch (Exception e) {
            e.a("LocalNotificationDb", "表已经存在");
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS t_localnotification");
        onCreate(sQLiteDatabase);
    }

    public static e a(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            e.c("LocalNotificationDb", "cursor is null");
            return null;
        }
        try {
            e eVar = new e();
            eVar.a(cursor.getLong(1));
            eVar.a(cursor.getInt(2));
            eVar.b(cursor.getInt(3));
            eVar.c(cursor.getInt(4));
            eVar.a(cursor.getString(5));
            eVar.c(cursor.getLong(6));
            eVar.b(cursor.getLong(7));
            e.e("LocalNotificationDb", eVar.toString());
            return eVar;
        } catch (Exception e) {
            e.getStackTrace();
            e.g("LocalNotificationDb", "initLocalNotificationDBData exception:" + e.getMessage());
            return null;
        }
    }

    public final long a(long j, int i, int i2, int i3, String str, long j2, long j3) {
        long j4 = 0;
        if (a(true)) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("ln_id", Long.valueOf(j));
                contentValues.put("ln_count", Integer.valueOf(1));
                contentValues.put("ln_remove", Integer.valueOf(0));
                contentValues.put("ln_type", Integer.valueOf(0));
                contentValues.put("ln_extra", str);
                contentValues.put("ln_trigger_time", Long.valueOf(j2));
                contentValues.put("ln_add_time", Long.valueOf(j3));
                j4 = getWritableDatabase().insert("t_localnotification", null, contentValues);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                b(true);
            }
        }
        return j4;
    }

    public final long b(long j, int i, int i2, int i3, String str, long j2, long j3) {
        long j4 = 0;
        if (!a(true)) {
            return j4;
        }
        try {
            String str2 = "ln_id=" + j;
            ContentValues contentValues = new ContentValues();
            contentValues.put("ln_id", Long.valueOf(j));
            contentValues.put("ln_count", Integer.valueOf(i));
            contentValues.put("ln_remove", Integer.valueOf(i2));
            contentValues.put("ln_type", Integer.valueOf(0));
            contentValues.put("ln_extra", str);
            contentValues.put("ln_trigger_time", Long.valueOf(j2));
            contentValues.put("ln_add_time", Long.valueOf(j3));
            j4 = (long) getWritableDatabase().update("t_localnotification", contentValues, str2, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            b(true);
        }
        return j4;
    }

    public final Cursor a(long j, long j2) {
        try {
            return getReadableDatabase().query(true, "t_localnotification", a, "ln_count>0 and ln_trigger_time<" + (BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD + j) + " and ln_trigger_time" + ">" + j, null, null, null, null, null);
        } catch (Exception e) {
            e.g("LocalNotificationDb", "getRtcDatas exception:" + e.getMessage());
            return null;
        }
    }

    public final Cursor a(int i, long j) {
        try {
            return getReadableDatabase().query(true, "t_localnotification", a, "ln_count=" + 1 + " and ln_trigger_time" + "<" + j, null, null, null, null, null);
        } catch (Exception e) {
            e.g("LocalNotificationDb", "getOutDatas exception:" + e.getMessage());
            return null;
        }
    }

    public final e a(long j, int i) throws Exception {
        Cursor query;
        Throwable th;
        if (a(false)) {
            try {
                query = getReadableDatabase().query(true, "t_localnotification", a, "ln_id=" + j + " and ln_type" + "=0", null, null, null, null, null);
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
                e a = a(query);
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
        throw new Exception("open database failed");
    }

    public final int a(long j) {
        int delete;
        if (a(true)) {
            try {
                delete = getWritableDatabase().delete("t_localnotification", "ln_id=" + j, null);
                return delete;
            } catch (Exception e) {
                delete = e;
                delete.printStackTrace();
            } finally {
                b(true);
            }
        }
        return 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int[] a() {
        /*
        r13 = this;
        r10 = 0;
        r12 = 1;
        r11 = 0;
        r4 = "1";
        r0 = r13.a(r11);
        if (r0 == 0) goto L_0x00ac;
    L_0x000b:
        r0 = 1;
        r3 = new java.lang.String[r0];	 Catch:{ Exception -> 0x0064, all -> 0x0079 }
        r0 = 0;
        r1 = "ln_id";
        r3[r0] = r1;	 Catch:{ Exception -> 0x0064, all -> 0x0079 }
        r0 = r13.getReadableDatabase();	 Catch:{ Exception -> 0x0064, all -> 0x0079 }
        r1 = 1;
        r2 = "t_localnotification";
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0064, all -> 0x0079 }
        if (r2 == 0) goto L_0x00aa;
    L_0x0025:
        r0 = r2.getCount();	 Catch:{ Exception -> 0x0094, all -> 0x008a }
        if (r0 <= 0) goto L_0x00aa;
    L_0x002b:
        r0 = r2.getCount();	 Catch:{ Exception -> 0x0094, all -> 0x008a }
        r10 = new int[r0];	 Catch:{ Exception -> 0x0094, all -> 0x008a }
        r2.moveToFirst();	 Catch:{ Exception -> 0x009a, all -> 0x008a }
        r0 = r11;
    L_0x0035:
        r1 = 0;
        r1 = r2.getInt(r1);	 Catch:{ Exception -> 0x009a, all -> 0x008a }
        r10[r0] = r1;	 Catch:{ Exception -> 0x009a, all -> 0x008a }
        r0 = r0 + 1;
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x009a, all -> 0x008a }
        if (r1 != 0) goto L_0x0035;
    L_0x0044:
        r0 = r10;
    L_0x0045:
        r1 = 1;
        r1 = r13.a(r1);	 Catch:{ Exception -> 0x00a0, all -> 0x008a }
        if (r1 == 0) goto L_0x00a8;
    L_0x004c:
        r1 = "delete from t_localnotification";
        r3 = r13.getWritableDatabase();	 Catch:{ Exception -> 0x00a4, all -> 0x008d }
        r3.execSQL(r1);	 Catch:{ Exception -> 0x00a4, all -> 0x008d }
        r1 = r12;
    L_0x0056:
        if (r2 == 0) goto L_0x005b;
    L_0x0058:
        r2.close();
    L_0x005b:
        r13.b(r11);
        if (r1 == 0) goto L_0x0063;
    L_0x0060:
        r13.b(r12);
    L_0x0063:
        return r0;
    L_0x0064:
        r0 = move-exception;
        r1 = r0;
        r2 = r11;
        r0 = r10;
    L_0x0068:
        r1.printStackTrace();	 Catch:{ all -> 0x0090 }
        if (r10 == 0) goto L_0x0070;
    L_0x006d:
        r10.close();
    L_0x0070:
        r13.b(r11);
        if (r2 == 0) goto L_0x0063;
    L_0x0075:
        r13.b(r12);
        goto L_0x0063;
    L_0x0079:
        r0 = move-exception;
        r2 = r10;
        r1 = r11;
    L_0x007c:
        if (r2 == 0) goto L_0x0081;
    L_0x007e:
        r2.close();
    L_0x0081:
        r13.b(r11);
        if (r1 == 0) goto L_0x0089;
    L_0x0086:
        r13.b(r12);
    L_0x0089:
        throw r0;
    L_0x008a:
        r0 = move-exception;
        r1 = r11;
        goto L_0x007c;
    L_0x008d:
        r0 = move-exception;
        r1 = r12;
        goto L_0x007c;
    L_0x0090:
        r0 = move-exception;
        r1 = r2;
        r2 = r10;
        goto L_0x007c;
    L_0x0094:
        r0 = move-exception;
        r1 = r0;
        r0 = r10;
        r10 = r2;
        r2 = r11;
        goto L_0x0068;
    L_0x009a:
        r0 = move-exception;
        r1 = r0;
        r0 = r10;
        r10 = r2;
        r2 = r11;
        goto L_0x0068;
    L_0x00a0:
        r1 = move-exception;
        r10 = r2;
        r2 = r11;
        goto L_0x0068;
    L_0x00a4:
        r1 = move-exception;
        r10 = r2;
        r2 = r12;
        goto L_0x0068;
    L_0x00a8:
        r1 = r11;
        goto L_0x0056;
    L_0x00aa:
        r0 = r10;
        goto L_0x0045;
    L_0x00ac:
        r0 = r10;
        goto L_0x0063;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.d.a():int[]");
    }
}
