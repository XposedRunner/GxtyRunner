package cn.jiguang.analytics.android.c.c;

import java.io.Serializable;
import java.util.regex.Pattern;

public final class f extends d {
    private static Pattern a;
    private static final String z;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r1 = 0;
        r2 = "\u0018\u000e\u0011Jz%]TiT\u0018\u0011\u0011eQ\u0015\u000fTwF";
        r0 = -1;
    L_0x0004:
        r2 = r2.toCharArray();
        r3 = r2.length;
        r4 = 1;
        if (r3 > r4) goto L_0x0050;
    L_0x000c:
        r4 = r1;
    L_0x000d:
        r5 = r2;
        r6 = r4;
        r9 = r3;
        r3 = r2;
        r2 = r9;
    L_0x0012:
        r8 = r3[r4];
        r7 = r6 % 5;
        switch(r7) {
            case 0: goto L_0x003e;
            case 1: goto L_0x0041;
            case 2: goto L_0x0044;
            case 3: goto L_0x0047;
            default: goto L_0x0019;
        };
    L_0x0019:
        r7 = 53;
    L_0x001b:
        r7 = r7 ^ r8;
        r7 = (char) r7;
        r3[r4] = r7;
        r4 = r6 + 1;
        if (r2 != 0) goto L_0x0027;
    L_0x0023:
        r3 = r5;
        r6 = r4;
        r4 = r2;
        goto L_0x0012;
    L_0x0027:
        r3 = r2;
        r2 = r5;
    L_0x0029:
        if (r3 > r4) goto L_0x000d;
    L_0x002b:
        r3 = new java.lang.String;
        r3.<init>(r2);
        r2 = r3.intern();
        switch(r0) {
            case 0: goto L_0x0049;
            default: goto L_0x0037;
        };
    L_0x0037:
        z = r2;
        r0 = "/S\u001aD\u001bZ!\u001f*\u001eU";
        r2 = r0;
        r0 = r1;
        goto L_0x0004;
    L_0x003e:
        r7 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x001b;
    L_0x0041:
        r7 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        goto L_0x001b;
    L_0x0044:
        r7 = 49;
        goto L_0x001b;
    L_0x0047:
        r7 = 4;
        goto L_0x001b;
    L_0x0049:
        r0 = java.util.regex.Pattern.compile(r2);
        a = r0;
        return;
    L_0x0050:
        r4 = r1;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.c.c.f.<clinit>():void");
    }

    public f() {
        super(z);
    }

    public final boolean a(Serializable serializable) {
        return serializable instanceof String ? a.matcher((String) serializable).matches() && ((String) serializable).length() <= 100 : false;
    }
}
