package cn.jiguang.d.d;

import android.content.Context;
import cn.jiguang.a.a.c.b;
import cn.jiguang.d.a.a;
import cn.jiguang.e.d;
import cn.jiguang.g.k;

public final class t {
    public static void a(Context context) {
        d.c("VersionHelper", "action - handleUpgrade");
        String str = "1.1.9";
        String b = b.b(context);
        if (k.a(b)) {
            b = a.s();
        }
        d.a("VersionHelper", "sdk version - curVersion:" + str + ",oldVersion:" + b);
        if (k.a(b)) {
            d.c("VersionHelper", "It's a new app, first installed");
        } else if (!(str.equals(b) || str.startsWith("1.") || !b.startsWith("1."))) {
            b.a(context, str);
            d.c("VersionHelper", "action - onUpgrade");
            b(context);
            a.d(context);
        }
        a.i(str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized boolean b(android.content.Context r13) {
        /*
        r4 = 0;
        r12 = 8;
        r0 = 0;
        r7 = cn.jiguang.d.d.t.class;
        monitor-enter(r7);
        r1 = "VersionHelper";
        r2 = "Action - copyRegisterUserInfo";
        cn.jiguang.e.d.c(r1, r2);	 Catch:{ all -> 0x007a }
        r6 = "";
        r1 = cn.jiguang.d.a.d.b(r13);	 Catch:{ all -> 0x007a }
        r1 = cn.jiguang.g.k.a(r1);	 Catch:{ all -> 0x007a }
        if (r1 == 0) goto L_0x0024;
    L_0x001b:
        r1 = "VersionHelper";
        r2 = "No RegistrationID.";
        cn.jiguang.e.d.c(r1, r2);	 Catch:{ all -> 0x007a }
    L_0x0022:
        monitor-exit(r7);
        return r0;
    L_0x0024:
        r1 = 8;
        r8 = new byte[r1];	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x006f }
        r1 = "PrefsFile";
        r9 = r13.openFileInput(r1);	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x006f }
        r1 = 0;
        r2 = 8;
        r9.read(r8, r1, r2);	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x006f }
        r1 = r0;
        r2 = r4;
    L_0x0036:
        if (r1 >= r12) goto L_0x0043;
    L_0x0038:
        r10 = r2 << r12;
        r2 = r8[r1];	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x007d }
        r2 = r2 & 255;
        r2 = (long) r2;	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x007d }
        r2 = r2 + r10;
        r1 = r1 + 1;
        goto L_0x0036;
    L_0x0043:
        r1 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x007d }
        r1.<init>();	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x007d }
    L_0x0048:
        r8 = r9.read();	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x007d }
        r10 = -1;
        if (r8 == r10) goto L_0x0067;
    L_0x004f:
        r8 = (char) r8;	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x007d }
        r1.append(r8);	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x007d }
        goto L_0x0048;
    L_0x0054:
        r1 = move-exception;
        r1 = "VersionHelper";
        r2 = "No saved user info.";
        cn.jiguang.e.d.c(r1, r2);	 Catch:{ all -> 0x007a }
        r1 = r6;
        r2 = r4;
    L_0x005e:
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 == 0) goto L_0x0022;
    L_0x0062:
        cn.jiguang.d.a.d.a(r13, r2, r1);	 Catch:{ all -> 0x007a }
        r0 = 1;
        goto L_0x0022;
    L_0x0067:
        r9.close();	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x007d }
        r1 = r1.toString();	 Catch:{ FileNotFoundException -> 0x0054, IOException -> 0x007d }
        goto L_0x005e;
    L_0x006f:
        r1 = move-exception;
        r2 = r4;
    L_0x0071:
        r8 = "VersionHelper";
        r9 = "";
        cn.jiguang.e.d.b(r8, r9, r1);	 Catch:{ all -> 0x007a }
        r1 = r6;
        goto L_0x005e;
    L_0x007a:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x007d:
        r1 = move-exception;
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.d.d.t.b(android.content.Context):boolean");
    }
}
