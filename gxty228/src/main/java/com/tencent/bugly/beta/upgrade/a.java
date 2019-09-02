package com.tencent.bugly.beta.upgrade;

import com.tencent.bugly.proguard.aj;

/* compiled from: BUGLY */
public class a implements aj {
    public final int a;
    public final int b;
    public final Object[] c;
    public boolean d = false;

    public a(int i, int i2, Object... objArr) {
        this.a = i;
        this.b = i2;
        this.c = objArr;
    }

    public void a(int i) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(int r8, com.tencent.bugly.proguard.be r9, long r10, long r12, boolean r14, java.lang.String r15) {
        /*
        r7 = this;
        monitor-enter(r7);
        r0 = r7.d;	 Catch:{ Exception -> 0x008c }
        if (r0 != 0) goto L_0x0035;
    L_0x0005:
        r0 = r7.b;	 Catch:{ Exception -> 0x008c }
        if (r8 != r0) goto L_0x0035;
    L_0x0009:
        r1 = "upload %s:[%d] [sended %d] [recevied %d]";
        r0 = 4;
        r2 = new java.lang.Object[r0];	 Catch:{ Exception -> 0x008c }
        r3 = 0;
        if (r14 == 0) goto L_0x0037;
    L_0x0011:
        r0 = "succ";
    L_0x0013:
        r2[r3] = r0;	 Catch:{ Exception -> 0x008c }
        r0 = 1;
        r3 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x008c }
        r2[r0] = r3;	 Catch:{ Exception -> 0x008c }
        r0 = 2;
        r3 = java.lang.Long.valueOf(r10);	 Catch:{ Exception -> 0x008c }
        r2[r0] = r3;	 Catch:{ Exception -> 0x008c }
        r0 = 3;
        r3 = java.lang.Long.valueOf(r12);	 Catch:{ Exception -> 0x008c }
        r2[r0] = r3;	 Catch:{ Exception -> 0x008c }
        com.tencent.bugly.proguard.an.a(r1, r2);	 Catch:{ Exception -> 0x008c }
        r0 = r7.a;	 Catch:{ Exception -> 0x008c }
        switch(r0) {
            case 1: goto L_0x003a;
            case 2: goto L_0x0112;
            default: goto L_0x0032;
        };	 Catch:{ Exception -> 0x008c }
    L_0x0032:
        r0 = 1;
        r7.d = r0;	 Catch:{ Exception -> 0x008c }
    L_0x0035:
        monitor-exit(r7);
        return;
    L_0x0037:
        r0 = "err";
        goto L_0x0013;
    L_0x003a:
        r0 = r7.c;	 Catch:{ Exception -> 0x008c }
        r1 = 0;
        r0 = r0[r1];	 Catch:{ Exception -> 0x008c }
        r0 = (java.lang.Boolean) r0;	 Catch:{ Exception -> 0x008c }
        r1 = r0.booleanValue();	 Catch:{ Exception -> 0x008c }
        r0 = r7.c;	 Catch:{ Exception -> 0x008c }
        r2 = 1;
        r0 = r0[r2];	 Catch:{ Exception -> 0x008c }
        r0 = (java.lang.Boolean) r0;	 Catch:{ Exception -> 0x008c }
        r2 = r0.booleanValue();	 Catch:{ Exception -> 0x008c }
        r0 = r7.b;	 Catch:{ Exception -> 0x008c }
        r3 = 804; // 0x324 float:1.127E-42 double:3.97E-321;
        if (r0 != r3) goto L_0x0035;
    L_0x0056:
        r3 = 0;
        r4 = 0;
        if (r14 == 0) goto L_0x013d;
    L_0x005a:
        if (r9 == 0) goto L_0x013d;
    L_0x005c:
        r0 = r9.c;	 Catch:{ Exception -> 0x008c }
        r5 = com.tencent.bugly.proguard.aa.class;
        r0 = com.tencent.bugly.proguard.ah.a(r0, r5);	 Catch:{ Exception -> 0x008c }
        r0 = (com.tencent.bugly.proguard.aa) r0;	 Catch:{ Exception -> 0x008c }
        if (r0 == 0) goto L_0x013d;
    L_0x0068:
        r3 = r0.a;	 Catch:{ Exception -> 0x008c }
        r4 = r0.b;	 Catch:{ Exception -> 0x008c }
        r0 = r3;
    L_0x006d:
        r3 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x008c }
        r3.a(r0);	 Catch:{ Exception -> 0x008c }
        r0 = r7.c;	 Catch:{ Exception -> 0x008c }
        r3 = 2;
        r0 = r0[r3];	 Catch:{ Exception -> 0x008c }
        r0 = (com.tencent.bugly.beta.upgrade.BetaGrayStrategy) r0;	 Catch:{ Exception -> 0x008c }
        if (r4 == 0) goto L_0x009a;
    L_0x007b:
        r0 = r4;
    L_0x007c:
        if (r0 == 0) goto L_0x0104;
    L_0x007e:
        r0 = r0.p;	 Catch:{ Exception -> 0x008c }
        switch(r0) {
            case 1: goto L_0x00a1;
            case 2: goto L_0x0032;
            case 3: goto L_0x00b2;
            default: goto L_0x0083;
        };	 Catch:{ Exception -> 0x008c }
    L_0x0083:
        r0 = "unexpected updatetype";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x008c }
        com.tencent.bugly.proguard.an.a(r0, r1);	 Catch:{ Exception -> 0x008c }
        goto L_0x0032;
    L_0x008c:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.an.b(r0);	 Catch:{ all -> 0x0097 }
        if (r1 != 0) goto L_0x0035;
    L_0x0093:
        r0.printStackTrace();	 Catch:{ all -> 0x0097 }
        goto L_0x0035;
    L_0x0097:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x009a:
        if (r0 == 0) goto L_0x009f;
    L_0x009c:
        r0 = r0.a;	 Catch:{ Exception -> 0x008c }
        goto L_0x007c;
    L_0x009f:
        r0 = 0;
        goto L_0x007c;
    L_0x00a1:
        r0 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x008c }
        if (r14 == 0) goto L_0x00b0;
    L_0x00a5:
        r3 = 0;
    L_0x00a6:
        r5 = r15;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x008c }
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ Exception -> 0x008c }
        r1 = 1;
        r0.ae = r1;	 Catch:{ Exception -> 0x008c }
        goto L_0x0032;
    L_0x00b0:
        r3 = -1;
        goto L_0x00a6;
    L_0x00b2:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ Exception -> 0x008c }
        r3 = 3;
        r0.ae = r3;	 Catch:{ Exception -> 0x008c }
        r3 = com.tencent.bugly.proguard.q.a;	 Catch:{ Exception -> 0x008c }
        if (r14 == 0) goto L_0x00eb;
    L_0x00bb:
        r0 = 0;
    L_0x00bc:
        r5 = 0;
        r3.a(r0, r4, r5);	 Catch:{ Exception -> 0x008c }
        r0 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x008c }
        r0 = r0.f;	 Catch:{ Exception -> 0x008c }
        if (r0 == 0) goto L_0x00ed;
    L_0x00c6:
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ Exception -> 0x008c }
        r2 = 18;
        r3 = 3;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x008c }
        r4 = 0;
        r5 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x008c }
        r5 = r5.f;	 Catch:{ Exception -> 0x008c }
        r3[r4] = r5;	 Catch:{ Exception -> 0x008c }
        r4 = 1;
        r5 = 1;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x008c }
        r3[r4] = r5;	 Catch:{ Exception -> 0x008c }
        r4 = 2;
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x008c }
        r3[r4] = r1;	 Catch:{ Exception -> 0x008c }
        r0.<init>(r2, r3);	 Catch:{ Exception -> 0x008c }
        com.tencent.bugly.beta.utils.e.a(r0);	 Catch:{ Exception -> 0x008c }
        goto L_0x0032;
    L_0x00eb:
        r0 = -1;
        goto L_0x00bc;
    L_0x00ed:
        if (r1 == 0) goto L_0x0032;
    L_0x00ef:
        if (r2 != 0) goto L_0x0032;
    L_0x00f1:
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ Exception -> 0x008c }
        r1 = 5;
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x008c }
        r3 = 0;
        r4 = com.tencent.bugly.beta.Beta.strToastYourAreTheLatestVersion;	 Catch:{ Exception -> 0x008c }
        r2[r3] = r4;	 Catch:{ Exception -> 0x008c }
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x008c }
        com.tencent.bugly.beta.utils.e.a(r0);	 Catch:{ Exception -> 0x008c }
        goto L_0x0032;
    L_0x0104:
        r0 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x008c }
        if (r14 == 0) goto L_0x0110;
    L_0x0108:
        r3 = 0;
    L_0x0109:
        r4 = 0;
        r5 = r15;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x008c }
        goto L_0x0032;
    L_0x0110:
        r3 = -1;
        goto L_0x0109;
    L_0x0112:
        r0 = r7.c;	 Catch:{ Exception -> 0x008c }
        r1 = 0;
        r0 = r0[r1];	 Catch:{ Exception -> 0x008c }
        r0 = (com.tencent.bugly.proguard.x) r0;	 Catch:{ Exception -> 0x008c }
        r0 = r7.b;	 Catch:{ Exception -> 0x008c }
        r1 = 803; // 0x323 float:1.125E-42 double:3.967E-321;
        if (r0 != r1) goto L_0x0035;
    L_0x011f:
        if (r14 == 0) goto L_0x0032;
    L_0x0121:
        if (r9 == 0) goto L_0x0136;
    L_0x0123:
        r0 = r9.c;	 Catch:{ Exception -> 0x008c }
        r1 = com.tencent.bugly.proguard.aa.class;
        r0 = com.tencent.bugly.proguard.ah.a(r0, r1);	 Catch:{ Exception -> 0x008c }
        r0 = (com.tencent.bugly.proguard.aa) r0;	 Catch:{ Exception -> 0x008c }
        if (r0 == 0) goto L_0x0136;
    L_0x012f:
        r1 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x008c }
        r0 = r0.a;	 Catch:{ Exception -> 0x008c }
        r1.a(r0);	 Catch:{ Exception -> 0x008c }
    L_0x0136:
        r0 = com.tencent.bugly.proguard.p.a;	 Catch:{ Exception -> 0x008c }
        r0.b();	 Catch:{ Exception -> 0x008c }
        goto L_0x0032;
    L_0x013d:
        r0 = r3;
        goto L_0x006d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.beta.upgrade.a.a(int, com.tencent.bugly.proguard.be, long, long, boolean, java.lang.String):void");
    }
}
