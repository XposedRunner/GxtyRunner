package cn.jiguang.analytics.android.b;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import cn.jiguang.analytics.android.c.a.b;
import java.io.Serializable;

public final class c implements Serializable {
    private static final String[] z;
    private long a;
    private long b;
    private boolean c;
    private long d;
    private long e;
    private long f;
    private long g;
    private long h;
    private long i;
    private long j;
    private long k;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 14;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0003Ld]n\fCdE+\u000e\u0002#J!\u0004VhQ:JK~\t \u001fNa";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x00b8;
            case 1: goto L_0x00bc;
            case 2: goto L_0x00c0;
            case 3: goto L_0x00c4;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 78;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = ":CjL\u000b\u0012VH";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u000bAy@8\u0003Vt";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "F\u0002l_/\u0003N@L#W";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "F\u0002n\\<\u0018Gc]\u0003\u0005@dE+8ZOP:\u000fQ0";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "F\u0002yA<\u000fQeF\"\u000e\u001f";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "F\u0002yF:\u000bNYQ\f\u0013VhZs";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "F\u0002yF:\u000bN@L#W";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "F\u0002lY>>ZOP:\u000fQ0";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = ":CjL\u000b\u0012VH\u000b\u0004Vd]7\u0011Ax[<\u000fLyd+\u0007MPs";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "F\u0002aF9'G`F<\u0013\u001f";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "F\u0002lY>8ZOP:\u000fQ0";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "F\u0002yF:\u000bN_Q\f\u0013VhZs";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "F\u0002n\\<\u0018Gc]\u0003\u0005@dE+>ZOP:\u000fQ0";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x00b8:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x0020;
    L_0x00bc:
        r9 = 34;
        goto L_0x0020;
    L_0x00c0:
        r9 = 13;
        goto L_0x0020;
    L_0x00c4:
        r9 = 41;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.b.c.<clinit>():void");
    }

    public static long a() {
        return TrafficStats.getTotalRxBytes() == -1 ? -1 : TrafficStats.getTotalRxBytes();
    }

    public static long b() {
        return TrafficStats.getTotalTxBytes() == -1 ? -1 : TrafficStats.getTotalTxBytes();
    }

    public static long c() {
        return TrafficStats.getMobileRxBytes() == -1 ? -1 : TrafficStats.getMobileRxBytes();
    }

    public static long d() {
        return TrafficStats.getMobileTxBytes() == -1 ? -1 : TrafficStats.getMobileTxBytes();
    }

    public final void a(Context context) {
        this.f = a();
        this.g = b();
        this.h = c();
        this.i = d();
        if (context == null) {
            b.g(z[1], z[0]);
            return;
        }
        int i = context.getApplicationInfo().uid;
        this.j = TrafficStats.getUidTxBytes(i) == -1 ? -1 : TrafficStats.getUidTxBytes(i);
        this.k = TrafficStats.getUidRxBytes(i) == -1 ? -1 : TrafficStats.getUidRxBytes(i);
        ActivityManager activityManager = (ActivityManager) context.getSystemService(z[2]);
        activityManager.getProcessMemoryInfo(new int[]{0});
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        this.b = memoryInfo.availMem;
        this.d = -1;
        if (VERSION.SDK_INT >= 16) {
            this.d = memoryInfo.totalMem;
        }
        this.e = memoryInfo.threshold;
        this.c = memoryInfo.lowMemory;
        this.a = (long) activityManager.getProcessMemoryInfo(new int[]{Process.myPid()})[0].getTotalPrivateDirty();
    }

    public final long e() {
        return this.b;
    }

    public final long f() {
        return this.d;
    }

    public final long g() {
        return this.j;
    }

    public final long h() {
        return this.k;
    }

    public final String toString() {
        return new StringBuilder(z[9]).append(this.a).append(z[3]).append(this.b).append(z[10]).append(this.c).append(z[7]).append(this.d).append(z[5]).append(this.e).append(z[12]).append(this.f).append(z[6]).append(this.g).append(z[4]).append(this.h).append(z[13]).append(this.i).append(z[8]).append(this.j).append(z[11]).append(this.k).append('}').toString();
    }
}
