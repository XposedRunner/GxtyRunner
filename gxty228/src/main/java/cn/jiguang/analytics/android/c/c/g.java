package cn.jiguang.analytics.android.c.c;

import java.io.Serializable;

public final class g extends d {
    private static final String[] z;
    private final int a;
    private final int b;

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
        r4 = 1;
        r1 = 0;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = ";b";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0056;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r2;
        r10 = r8;
        r13 = r7;
        r7 = r2;
        r2 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x004a;
            case 1: goto L_0x004d;
            case 2: goto L_0x0050;
            case 3: goto L_0x0053;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = r4;
    L_0x0020:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002c;
    L_0x0028:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x0018;
    L_0x002c:
        r7 = r2;
        r2 = r9;
    L_0x002e:
        if (r7 > r8) goto L_0x0013;
    L_0x0030:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0045;
            default: goto L_0x003c;
        };
    L_0x003c:
        r5[r3] = r2;
        r0 = "d8\u0016omsp\u001b!~>YA";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0045:
        r5[r3] = r2;
        z = r6;
        return;
    L_0x004a:
        r11 = 23;
        goto L_0x0020;
    L_0x004d:
        r11 = 80;
        goto L_0x0020;
    L_0x0050:
        r11 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x0020;
    L_0x0053:
        r11 = 26;
        goto L_0x0020;
    L_0x0056:
        r8 = r1;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.c.c.g.<clinit>():void");
    }

    public g() {
        super("");
        this.a = Integer.MAX_VALUE;
        this.b = Integer.MIN_VALUE;
    }

    public g(int i, int i2) {
        super(new StringBuilder(z[1]).append(0).append(z[0]).append("]").toString());
        this.b = 0;
        this.a = 2;
    }

    public final boolean a(Serializable serializable) {
        if (!(serializable instanceof Integer)) {
            return false;
        }
        Integer num = (Integer) serializable;
        return num.intValue() >= this.b && num.intValue() <= this.a;
    }
}
