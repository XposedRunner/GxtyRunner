package cn.jiguang.analytics.android;

import android.content.Context;
import cn.jiguang.analytics.android.a.c;
import cn.jiguang.analytics.android.a.h;
import cn.jiguang.analytics.android.a.m;
import cn.jiguang.analytics.android.c.a.b;
import cn.jiguang.analytics.android.c.d.d;
import cn.jiguang.api.JCoreInterface;
import java.util.concurrent.atomic.AtomicBoolean;

public final class a {
    private static AtomicBoolean a = new AtomicBoolean(false);
    private static Context b;
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r4 = 1;
        r1 = 0;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = "\fb8od\u001c|)rcY!}th\u0017x8osY{<d'\u0017y1{";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x005d;
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
            case 0: goto L_0x0051;
            case 1: goto L_0x0054;
            case 2: goto L_0x0057;
            case 3: goto L_0x005a;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 7;
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
        r0 = "3M3vk\u0000x4tt";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0045:
        r5[r3] = r2;
        z = r6;
        r0 = new java.util.concurrent.atomic.AtomicBoolean;
        r0.<init>(r1);
        a = r0;
        return;
    L_0x0051:
        r11 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x0020;
    L_0x0054:
        r11 = 12;
        goto L_0x0020;
    L_0x0057:
        r11 = 93;
        goto L_0x0020;
    L_0x005a:
        r11 = 23;
        goto L_0x0020;
    L_0x005d:
        r8 = r1;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.a.<clinit>():void");
    }

    public static boolean a(Context context) {
        if (a.get()) {
            return true;
        }
        if (context == null) {
            b.f(z[1], z[0]);
            return false;
        }
        b = context.getApplicationContext();
        h.b().c(b);
        m.b().a(b);
        c.b().a(b);
        JCoreInterface.register(b);
        d.a().a(new b());
        a.set(true);
        return true;
    }
}
