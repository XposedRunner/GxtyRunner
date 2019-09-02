package cn.jiguang.analytics.android.a;

import cn.jiguang.analytics.android.b.b;
import cn.jiguang.analytics.android.c.b.a;
import java.util.ArrayList;

final class d implements Runnable {
    private static final String[] z;
    final /* synthetic */ b a;
    final /* synthetic */ c b;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 4;
        r5 = new java.lang.String[r0];
        r4 = "\u001bHHi@~LHd[,Z\rtA=]HtG~4";
        r0 = -1;
        r6 = r5;
        r7 = r5;
        r5 = r1;
    L_0x000c:
        r4 = r4.toCharArray();
        r8 = r4.length;
        if (r8 > r2) goto L_0x006a;
    L_0x0013:
        r9 = r1;
    L_0x0014:
        r10 = r4;
        r11 = r9;
        r14 = r8;
        r8 = r4;
        r4 = r14;
    L_0x0019:
        r13 = r8[r9];
        r12 = r11 % 5;
        switch(r12) {
            case 0: goto L_0x005f;
            case 1: goto L_0x0062;
            case 2: goto L_0x0065;
            case 3: goto L_0x0068;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 52;
    L_0x0022:
        r12 = r12 ^ r13;
        r12 = (char) r12;
        r8[r9] = r12;
        r9 = r11 + 1;
        if (r4 != 0) goto L_0x002e;
    L_0x002a:
        r8 = r10;
        r11 = r9;
        r9 = r4;
        goto L_0x0019;
    L_0x002e:
        r8 = r4;
        r4 = r10;
    L_0x0030:
        if (r8 > r9) goto L_0x0014;
    L_0x0032:
        r8 = new java.lang.String;
        r8.<init>(r4);
        r4 = r8.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0050;
            case 2: goto L_0x005a;
            default: goto L_0x003e;
        };
    L_0x003e:
        r6[r5] = r4;
        r0 = "\u001bHHi@\u0013_CfS;L";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = "0[Z'Q([Cs\u00144MBi\u00147PKh\u000e";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "5[TbB;PY";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        z = r7;
        return;
    L_0x005f:
        r12 = 94;
        goto L_0x0022;
    L_0x0062:
        r12 = 62;
        goto L_0x0022;
    L_0x0065:
        r12 = 45;
        goto L_0x0022;
    L_0x0068:
        r12 = 7;
        goto L_0x0022;
    L_0x006a:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.a.d.<clinit>():void");
    }

    d(c cVar, b bVar) {
        this.b = cVar;
        this.a = bVar;
    }

    public final void run() {
        if (this.a != null && this.b.a != null) {
            a.a(this.b.a);
            ArrayList c = a.c(this.b.a, z[3]);
            if (c == null) {
                c = new ArrayList();
            }
            c.add(this.a);
            cn.jiguang.analytics.android.c.a.b.a(z[1], new StringBuilder(z[2]).append(this.a.b().toString()).toString());
            a.a(this.b.a);
            a.a(this.b.a, z[3], c);
            if (this.a.a() != null) {
                cn.jiguang.analytics.android.c.a.b.c(z[1], new StringBuilder(z[0]).append(this.a.a().toString()).toString());
            }
        }
    }
}
