package cn.jiguang.analytics.android.a;

import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.c.a.b;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.MultiSpHelper;
import com.blankj.utilcode.constant.CacheConstants;

final class g extends l {
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
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "v!;*0E\u0014<(/u\u000e!..Z\u00016.";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000b:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r3;
        r10 = r8;
        r13 = r7;
        r7 = r3;
        r3 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0055;
            case 1: goto L_0x0058;
            case 2: goto L_0x005b;
            case 3: goto L_0x005e;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 92;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x0018;
    L_0x002d:
        r7 = r3;
        r3 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0050;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r4] = r3;
        r0 = "]\u000e4'%H\t68\u0003N\u0005%$.H?%..U\u000f1";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "L\u0005'\"3X@&#3I\f1k>Y@<%|gQegd\nTe{\u0001";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        z = r6;
        return;
    L_0x0055:
        r11 = 60;
        goto L_0x0021;
    L_0x0058:
        r11 = 96;
        goto L_0x0021;
    L_0x005b:
        r11 = 85;
        goto L_0x0021;
    L_0x005e:
        r11 = 75;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.a.g.<clinit>():void");
    }

    g() {
    }

    final int a() {
        int i = MultiSpHelper.getInt(JAnalyticsInterface.mContext, z[1], 60);
        return i < 10 ? 10 : i > CacheConstants.DAY ? CacheConstants.DAY : i;
    }

    final boolean a(int i) {
        if (i < 10 || i > CacheConstants.DAY) {
            b.g(z[0], z[2]);
            return false;
        } else if (i == a()) {
            return false;
        } else {
            MultiSpHelper.commitInt(JAnalyticsInterface.mContext, z[1], i);
            return true;
        }
    }

    public final void run() {
        m.b().d();
        JCoreInterface.report(JAnalyticsInterface.mContext, null, true);
    }
}
