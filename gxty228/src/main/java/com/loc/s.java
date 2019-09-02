package com.loc;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import cn.jiguang.net.HttpUtils;
import java.io.File;
import java.io.IOException;

/* compiled from: DB */
public final class s extends SQLiteOpenHelper {
    private static boolean b = true;
    private static boolean c = false;
    private o a;

    /* compiled from: DB */
    public static class a extends ContextWrapper {
        private String a;
        private Context b;

        public a(Context context, String str) {
            super(context);
            this.a = str;
            this.b = context;
        }

        public final File getDatabasePath(String str) {
            try {
                if (!s.b || !dl.a(this.b, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    return super.getDatabasePath(str);
                }
                String str2 = this.a + HttpUtils.PATHS_SEPARATOR + str;
                File file = new File(this.a);
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (!(s.c && s.b)) {
                    s.c = true;
                    File file2 = new File(this.a + File.separator + System.currentTimeMillis() + ".db");
                    if (file2.createNewFile()) {
                        file2.delete();
                    } else {
                        s.b = false;
                        return super.getDatabasePath(str);
                    }
                }
                boolean z = false;
                file = new File(str2);
                z = !file.exists() ? file.createNewFile() : true;
                return !z ? super.getDatabasePath(str) : file;
            } catch (IOException e) {
                s.b = false;
            } catch (Throwable th) {
                s.b = false;
                return super.getDatabasePath(str);
            }
        }

        public final SQLiteDatabase openOrCreateDatabase(String str, int i, CursorFactory cursorFactory) {
            if (getDatabasePath(str) != null) {
                SQLiteDatabase openOrCreateDatabase;
                try {
                    openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), null);
                } catch (Throwable th) {
                    openOrCreateDatabase = null;
                }
                if (openOrCreateDatabase != null) {
                    return openOrCreateDatabase;
                }
            }
            return SQLiteDatabase.openOrCreateDatabase(this.b.getApplicationContext().getDatabasePath(str), null);
        }

        public final SQLiteDatabase openOrCreateDatabase(String str, int i, CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
            if (getDatabasePath(str) != null) {
                SQLiteDatabase openOrCreateDatabase;
                try {
                    openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), null);
                } catch (Throwable th) {
                    openOrCreateDatabase = null;
                }
                if (openOrCreateDatabase != null) {
                    return openOrCreateDatabase;
                }
            }
            return SQLiteDatabase.openOrCreateDatabase(this.b.getApplicationContext().getDatabasePath(str), null);
        }
    }

    public s(Context context, String str, o oVar) {
        super(context, str, null, 1);
        this.a = oVar;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.a.a(sQLiteDatabase);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        o oVar = this.a;
    }
}
