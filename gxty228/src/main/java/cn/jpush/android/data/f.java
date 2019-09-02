package cn.jpush.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;

public abstract class f extends SQLiteOpenHelper {
    private volatile int a = 0;
    private volatile int b = 0;
    private volatile SQLiteDatabase c;
    private volatile SQLiteDatabase d;
    private final Object e = new Object();
    private final Object f = new Object();
    private final Context g;
    private final String h;
    private final int i;
    private final CursorFactory j;

    public f(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, null, 1);
        this.g = context;
        this.h = str;
        this.i = 1;
        this.j = null;
    }

    public final boolean a(boolean z) {
        if (z) {
            try {
                synchronized (this.e) {
                    getWritableDatabase();
                    this.b++;
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        synchronized (this.f) {
            getReadableDatabase();
            this.a++;
        }
        return true;
    }

    public SQLiteDatabase getReadableDatabase() {
        if (this.c == null || !this.c.isOpen()) {
            synchronized (this.f) {
                if (this.c == null || !this.c.isOpen()) {
                    try {
                        getWritableDatabase();
                    } catch (SQLiteException e) {
                    }
                    String path = this.g.getDatabasePath(this.h).getPath();
                    this.c = SQLiteDatabase.openDatabase(path, this.j, 1);
                    if (this.c.getVersion() != this.i) {
                        throw new SQLiteException("Can't upgrade read-only database from version " + this.c.getVersion() + " to " + this.i + ": " + path);
                    }
                    this.a = 0;
                    onOpen(this.c);
                }
            }
        }
        return this.c;
    }

    public SQLiteDatabase getWritableDatabase() {
        if (this.d == null || !this.d.isOpen()) {
            synchronized (this.e) {
                if (this.d == null || !this.d.isOpen()) {
                    this.b = 0;
                    this.d = super.getWritableDatabase();
                    if (VERSION.SDK_INT >= 11) {
                        this.d.enableWriteAheadLogging();
                    }
                }
            }
        }
        return this.d;
    }

    @Deprecated
    public void close() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(boolean r5) {
        /*
        r4 = this;
        r1 = 1;
        r0 = 0;
        if (r5 == 0) goto L_0x0032;
    L_0x0004:
        r2 = r4.e;
        monitor-enter(r2);
        r3 = r4.d;	 Catch:{ all -> 0x002f }
        if (r3 == 0) goto L_0x001b;
    L_0x000b:
        r3 = r4.d;	 Catch:{ all -> 0x002f }
        r3 = r3.isOpen();	 Catch:{ all -> 0x002f }
        if (r3 == 0) goto L_0x001b;
    L_0x0013:
        r3 = r4.b;	 Catch:{ all -> 0x002f }
        r3 = r3 + -1;
        r4.b = r3;	 Catch:{ all -> 0x002f }
        if (r3 > 0) goto L_0x001c;
    L_0x001b:
        r0 = r1;
    L_0x001c:
        if (r0 == 0) goto L_0x002d;
    L_0x001e:
        r0 = 0;
        r4.b = r0;	 Catch:{ all -> 0x002f }
        r0 = r4.d;	 Catch:{ all -> 0x002f }
        if (r0 == 0) goto L_0x002a;
    L_0x0025:
        r0 = r4.d;	 Catch:{ all -> 0x002f }
        r0.close();	 Catch:{ all -> 0x002f }
    L_0x002a:
        r0 = 0;
        r4.d = r0;	 Catch:{ all -> 0x002f }
    L_0x002d:
        monitor-exit(r2);	 Catch:{ all -> 0x002f }
    L_0x002e:
        return;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x002f }
        throw r0;
    L_0x0032:
        r2 = r4.f;
        monitor-enter(r2);
        r3 = r4.c;	 Catch:{ all -> 0x005d }
        if (r3 == 0) goto L_0x0049;
    L_0x0039:
        r3 = r4.c;	 Catch:{ all -> 0x005d }
        r3 = r3.isOpen();	 Catch:{ all -> 0x005d }
        if (r3 == 0) goto L_0x0049;
    L_0x0041:
        r3 = r4.a;	 Catch:{ all -> 0x005d }
        r3 = r3 + -1;
        r4.a = r3;	 Catch:{ all -> 0x005d }
        if (r3 > 0) goto L_0x004a;
    L_0x0049:
        r0 = r1;
    L_0x004a:
        if (r0 == 0) goto L_0x005b;
    L_0x004c:
        r0 = 0;
        r4.a = r0;	 Catch:{ all -> 0x005d }
        r0 = r4.c;	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0058;
    L_0x0053:
        r0 = r4.c;	 Catch:{ all -> 0x005d }
        r0.close();	 Catch:{ all -> 0x005d }
    L_0x0058:
        r0 = 0;
        r4.c = r0;	 Catch:{ all -> 0x005d }
    L_0x005b:
        monitor-exit(r2);	 Catch:{ all -> 0x005d }
        goto L_0x002e;
    L_0x005d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x005d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.f.b(boolean):void");
    }
}
