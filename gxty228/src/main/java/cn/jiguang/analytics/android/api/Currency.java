package cn.jiguang.analytics.android.api;

public enum Currency {
    ;
    
    private static final String[] z = null;

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
        r13 = 2;
        r4 = 1;
        r1 = 0;
        r3 = new java.lang.String[r13];
        r2 = "DsI";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0079;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r2;
        r10 = r8;
        r14 = r7;
        r7 = r2;
        r2 = r14;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x006d;
            case 1: goto L_0x0070;
            case 2: goto L_0x0073;
            case 3: goto L_0x0076;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 53;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x0018;
    L_0x002d:
        r7 = r2;
        r2 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r3] = r2;
        r0 = "RnT";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        z = r6;
        r0 = new cn.jiguang.analytics.android.api.Currency;
        r2 = z;
        r2 = r2[r4];
        r0.<init>(r2, r1);
        CNY = r0;
        r0 = new cn.jiguang.analytics.android.api.Currency;
        r2 = z;
        r2 = r2[r1];
        r0.<init>(r2, r4);
        USD = r0;
        r0 = new cn.jiguang.analytics.android.api.Currency[r13];
        r2 = CNY;
        r0[r1] = r2;
        r1 = USD;
        r0[r4] = r1;
        $VALUES = r0;
        return;
    L_0x006d:
        r11 = 17;
        goto L_0x0021;
    L_0x0070:
        r11 = 32;
        goto L_0x0021;
    L_0x0073:
        r11 = 13;
        goto L_0x0021;
    L_0x0076:
        r11 = 95;
        goto L_0x0021;
    L_0x0079:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.api.Currency.<clinit>():void");
    }
}
