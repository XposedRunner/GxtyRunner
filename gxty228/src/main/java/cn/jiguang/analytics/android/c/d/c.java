package cn.jiguang.analytics.android.c.d;

import cn.jiguang.analytics.android.c.a.b;
import java.util.Locale;

public final class c {
    private static String a;
    private static final String[] z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 3;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "o\nsbQY\u001dkChM\u0013rC\u001eJ\u001enJ\u0004\f\u001a=";
        r0 = -1;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0074;
            case 1: goto L_0x0077;
            case 2: goto L_0x007a;
            case 3: goto L_0x007c;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 62;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0011;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u000buOPK*sOR_";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\tQ";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        z = r4;
        r0 = "\u001cN5\u0015\n\u0019I0\u001e\u0007m=Db{j";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x008c;
    L_0x005a:
        r3 = r0;
        r4 = r2;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x005f:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x007f;
            case 1: goto L_0x0082;
            case 2: goto L_0x0085;
            case 3: goto L_0x0087;
            default: goto L_0x0066;
        };
    L_0x0066:
        r5 = 62;
    L_0x0068:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x008a;
    L_0x0070:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x005f;
    L_0x0074:
        r9 = 44;
        goto L_0x001f;
    L_0x0077:
        r9 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        goto L_0x001f;
    L_0x007a:
        r9 = 7;
        goto L_0x001f;
    L_0x007c:
        r9 = 38;
        goto L_0x001f;
    L_0x007f:
        r5 = 44;
        goto L_0x0068;
    L_0x0082:
        r5 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        goto L_0x0068;
    L_0x0085:
        r5 = 7;
        goto L_0x0068;
    L_0x0087:
        r5 = 38;
        goto L_0x0068;
    L_0x008a:
        r1 = r0;
        r0 = r3;
    L_0x008c:
        if (r1 > r2) goto L_0x005a;
    L_0x008e:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        a = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.c.d.c.<clinit>():void");
    }

    public static double a(double d, int i) {
        try {
            String stringBuilder = new StringBuilder(z[2]).append(2).append("f").toString();
            d = Double.parseDouble(String.format(Locale.ENGLISH, stringBuilder, new Object[]{Double.valueOf(d)}));
        } catch (Throwable th) {
            b.a(z[1], new StringBuilder(z[0]).append(th).toString());
        }
        return d;
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }
}
