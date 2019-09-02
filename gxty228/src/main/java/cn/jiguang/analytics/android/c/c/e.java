package cn.jiguang.analytics.android.c.c;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class e extends d {
    private static final DateFormat a = new SimpleDateFormat(z[1], Locale.CHINA);
    private static final String[] z;

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
        r2 = "+Y\u0010 \u000b<\u0011\u001d0G<P\u000b0G9B_,\u001e!H2\u0018\u0003<";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0064;
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
            case 0: goto L_0x0058;
            case 1: goto L_0x005b;
            case 2: goto L_0x005e;
            case 3: goto L_0x0061;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
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
        r0 = "!H\u0006,*\u0015U\u001b";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        z = r6;
        r0 = new java.text.SimpleDateFormat;
        r1 = z;
        r1 = r1[r4];
        r2 = java.util.Locale.CHINA;
        r0.<init>(r1, r2);
        a = r0;
        return;
    L_0x0058:
        r11 = 88;
        goto L_0x0021;
    L_0x005b:
        r11 = 49;
        goto L_0x0021;
    L_0x005e:
        r11 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        goto L_0x0021;
    L_0x0061:
        r11 = 85;
        goto L_0x0021;
    L_0x0064:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.c.c.e.<clinit>():void");
    }

    public e() {
        super(z[0]);
    }

    public final boolean a(Serializable serializable) {
        if (!(serializable instanceof String)) {
            return false;
        }
        try {
            a.parse((String) serializable);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
