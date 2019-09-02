package cn.jiguang.analytics.android;

public final class d {
    public static boolean a = false;
    public static final String b;
    public static final String c;
    public static boolean d = false;
    public static long e = StatisticConfig.MIN_UPLOAD_INTERVAL;
    public static boolean f = true;
    public static boolean g = true;
    public static int h = 0;

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
        r10 = 1;
        r1 = 0;
        a = r1;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = android.os.Environment.getExternalStorageDirectory();
        r2 = r2.getAbsolutePath();
        r0 = r0.append(r2);
        r2 = java.io.File.separator;
        r3 = r0.append(r2);
        r2 = "\u001e=v#S\\.c$QC";
        r0 = -1;
    L_0x001e:
        r2 = r2.toCharArray();
        r4 = r2.length;
        if (r4 > r10) goto L_0x0094;
    L_0x0025:
        r5 = r1;
    L_0x0026:
        r6 = r2;
        r7 = r5;
        r11 = r4;
        r4 = r2;
        r2 = r11;
    L_0x002b:
        r9 = r4[r5];
        r8 = r7 % 5;
        switch(r8) {
            case 0: goto L_0x0040;
            case 1: goto L_0x0043;
            case 2: goto L_0x0046;
            case 3: goto L_0x0049;
            default: goto L_0x0032;
        };
    L_0x0032:
        r8 = 50;
    L_0x0034:
        r8 = r8 ^ r9;
        r8 = (char) r8;
        r4[r5] = r8;
        r5 = r7 + 1;
        if (r2 != 0) goto L_0x004c;
    L_0x003c:
        r4 = r6;
        r7 = r5;
        r5 = r2;
        goto L_0x002b;
    L_0x0040:
        r8 = 48;
        goto L_0x0034;
    L_0x0043:
        r8 = 87;
        goto L_0x0034;
    L_0x0046:
        r8 = 23;
        goto L_0x0034;
    L_0x0049:
        r8 = 77;
        goto L_0x0034;
    L_0x004c:
        r4 = r2;
        r2 = r6;
    L_0x004e:
        if (r4 > r5) goto L_0x0026;
    L_0x0050:
        r4 = new java.lang.String;
        r4.<init>(r2);
        r2 = r4.intern();
        switch(r0) {
            case 0: goto L_0x007d;
            default: goto L_0x005c;
        };
    L_0x005c:
        r0 = r3.append(r2);
        r2 = java.io.File.separator;
        r0 = r0.append(r2);
        r0 = r0.toString();
        b = r0;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = b;
        r2 = r0.append(r2);
        r0 = "\u001e=v#S\\.c$QC";
        r3 = r2;
        r2 = r0;
        r0 = r1;
        goto L_0x001e;
    L_0x007d:
        r0 = r3.append(r2);
        r0 = r0.toString();
        c = r0;
        d = r1;
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        e = r2;
        f = r10;
        g = r10;
        h = r1;
        return;
    L_0x0094:
        r5 = r1;
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.d.<clinit>():void");
    }
}
