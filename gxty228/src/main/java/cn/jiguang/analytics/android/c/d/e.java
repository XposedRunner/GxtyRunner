package cn.jiguang.analytics.android.c.d;

import android.content.Context;
import cn.jiguang.api.MultiSpHelper;
import org.json.JSONException;

public final class e {
    private static final String z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "b:~[rb>ikgu1cAhb";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0026;
    L_0x000b:
        r3 = r0;
        r4 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x0010:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0034;
            case 1: goto L_0x0037;
            case 2: goto L_0x003a;
            case 3: goto L_0x003d;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 6;
    L_0x0018:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0024;
    L_0x0020:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0010;
    L_0x0024:
        r1 = r0;
        r0 = r3;
    L_0x0026:
        if (r1 > r2) goto L_0x000b;
    L_0x0028:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        z = r0;
        return;
    L_0x0034:
        r5 = 22;
        goto L_0x0018;
    L_0x0037:
        r5 = 82;
        goto L_0x0018;
    L_0x003a:
        r5 = 12;
        goto L_0x0018;
    L_0x003d:
        r5 = 52;
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.c.d.e.<clinit>():void");
    }

    public static boolean a(Context context) {
        return a(context, z, 10000, 30);
    }

    private static boolean a(Context context, String str, long j, int i) {
        f fVar;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            fVar = new f(MultiSpHelper.getString(context, str, ""));
        } catch (JSONException e) {
            fVar = new f(10000, 30);
        }
        boolean a = fVar.a(currentTimeMillis);
        if (!a) {
            MultiSpHelper.commitString(context, str, fVar.a());
        }
        return a;
    }
}
