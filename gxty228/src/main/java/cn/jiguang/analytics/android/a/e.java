package cn.jiguang.analytics.android.a;

import android.os.Handler;
import android.os.HandlerThread;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.api.JCoreInterface;
import org.json.JSONException;
import org.json.JSONObject;

public final class e {
    private static Handler a;
    private static final Object b = new Object();
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
        r3 = "Bh^ ~V";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000b:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0068;
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
            case 0: goto L_0x005c;
            case 1: goto L_0x005f;
            case 2: goto L_0x0062;
            case 3: goto L_0x0065;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 17;
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
        r0 = "ScM%hFdO:N@h\\&cFR\\,c[bH";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "bh^ ~VYM:z";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        z = r6;
        r0 = new java.lang.Object;
        r0.<init>();
        b = r0;
        return;
    L_0x005c:
        r11 = 50;
        goto L_0x0021;
    L_0x005f:
        r11 = 13;
        goto L_0x0021;
    L_0x0062:
        r11 = 44;
        goto L_0x0021;
    L_0x0065:
        r11 = 73;
        goto L_0x0021;
    L_0x0068:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.a.e.<clinit>():void");
    }

    public static void a(int i, int i2) {
        l b = b(4369);
        if (b != null) {
            boolean a = b.a(i2);
            a(4369, a);
            if (a) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(z[0], i2);
                    JCoreInterface.report(JAnalyticsInterface.mContext, JCoreInterface.fillBaseReport(jSONObject, z[1]), false);
                } catch (JSONException e) {
                }
            }
        }
    }

    public static void a(int i, boolean z) {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    HandlerThread handlerThread = new HandlerThread(z[2]);
                    handlerThread.start();
                    a = new f(handlerThread.getLooper());
                }
            }
        }
        l b = b(i);
        if (b != null) {
            if (z) {
                if (a.hasMessages(i)) {
                    a.removeMessages(i);
                }
                a.sendEmptyMessageDelayed(i, (long) (b.a() * 1000));
            } else if (!a.hasMessages(i)) {
                a.sendEmptyMessageDelayed(i, (long) (b.a() * 1000));
            }
        }
    }

    private static l b(int i) {
        switch (i) {
            case 4369:
                return new g();
            default:
                return null;
        }
    }
}
