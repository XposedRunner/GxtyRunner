package com.tencent.bugly.beta.upgrade;

import android.os.Parcelable;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.text.TextUtils;
import com.tencent.bugly.beta.download.BetaReceiver;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.download.a;
import com.tencent.bugly.beta.global.d;
import com.tencent.bugly.beta.global.e;
import com.tencent.bugly.beta.global.f;
import com.tencent.bugly.beta.ui.h;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.bg;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.y;
import java.io.File;

/* compiled from: BUGLY */
public class c {
    public static c a = new c();
    public BetaGrayStrategy b;
    public DownloadTask c;
    public DownloadListener d;
    public UpgradeListener e;
    public UpgradeStateListener f;
    public boolean g;
    public d h;
    public d i;
    public int j;
    private final Object k = new Object();
    private final Object l = new Object();
    private DownloadListener m = new a(3, this, Integer.valueOf(0));
    private a n = null;
    private d o;
    private boolean p;

    public BetaGrayStrategy a(y yVar) {
        BetaGrayStrategy betaGrayStrategy;
        synchronized (this.k) {
            y yVar2;
            Parcelable parcelable;
            y yVar3;
            Parcelable betaGrayStrategy2;
            int i;
            betaGrayStrategy = (BetaGrayStrategy) com.tencent.bugly.beta.global.a.a("st.bch", BetaGrayStrategy.CREATOR);
            if (betaGrayStrategy != null && betaGrayStrategy.a == null) {
                com.tencent.bugly.beta.global.a.a("st.bch");
                betaGrayStrategy = null;
            }
            if (!(betaGrayStrategy == null || betaGrayStrategy.a == null || (betaGrayStrategy.a.e.c > e.E.w && betaGrayStrategy.a.n == 1 && ((betaGrayStrategy.a.f == null || !TextUtils.equals(e.E.v, betaGrayStrategy.a.f.a)) && betaGrayStrategy.a.p != 3)))) {
                com.tencent.bugly.beta.global.a.a("st.bch");
                this.c = null;
                betaGrayStrategy = null;
            }
            if (yVar == null || yVar.e.c >= e.E.w) {
                yVar2 = yVar;
            } else {
                an.a("versionCode is too small, discard remote strategy: [new: %d] [current: %d]", Integer.valueOf(yVar.e.c), Integer.valueOf(e.E.w));
                yVar2 = null;
            }
            if (yVar2 != null) {
                if (!(yVar2.n != 2 || betaGrayStrategy == null || betaGrayStrategy.a == null || TextUtils.isEmpty(yVar2.m) || TextUtils.isEmpty(betaGrayStrategy.a.m) || !TextUtils.equals(yVar2.m, betaGrayStrategy.a.m))) {
                    an.a("callback strategy: %s", yVar2.m);
                    com.tencent.bugly.beta.global.a.a("st.bch");
                    e.E.p.a(betaGrayStrategy.a.f.b, e.E.t.getAbsolutePath(), null, null).delete(true);
                    betaGrayStrategy = null;
                }
                if (yVar2.n != 1) {
                    an.a("invalid strategy: %s", yVar2.m);
                    parcelable = betaGrayStrategy;
                    yVar3 = null;
                    if (yVar3 == null) {
                        if (parcelable != null || parcelable.a == null || TextUtils.isEmpty(yVar3.m) || TextUtils.isEmpty(parcelable.a.m) || !TextUtils.equals(yVar3.m, parcelable.a.m)) {
                            betaGrayStrategy2 = new BetaGrayStrategy();
                        } else {
                            betaGrayStrategy2 = new BetaGrayStrategy(ap.d(ap.a(parcelable)));
                            an.a("same strategyId:[new: %s] [current: %s] keep has popup times: %d, interval: %d", yVar3.m, parcelable.a.m, Integer.valueOf(betaGrayStrategy2.b), Long.valueOf(yVar3.i));
                        }
                        betaGrayStrategy2.a = yVar3;
                        betaGrayStrategy2.e = System.currentTimeMillis();
                        if (parcelable != null) {
                            if (!parcelable.a.f.b.equals(yVar3.f.b)) {
                                if (this.c != null) {
                                    e.E.p.a(parcelable.a.f.b, e.E.t.getAbsolutePath(), null, null).delete(true);
                                    for (File delete : e.E.t.listFiles()) {
                                        if (!delete.delete()) {
                                            an.e("cannot deleteCache file:%s", r1[i].getAbsolutePath());
                                        }
                                    }
                                } else {
                                    BetaReceiver.netListeners.remove(this.c.getDownloadUrl());
                                    this.c.delete(true);
                                    this.c = null;
                                }
                            }
                            if (parcelable.a.p == 3) {
                                File file = e.E.H;
                                if (file != null && file.exists() && file.delete()) {
                                    an.a("delete tmpPatchFile", new Object[0]);
                                }
                                file = e.E.G;
                                if (file != null && file.exists() && file.delete()) {
                                    e.E.L = "";
                                    an.a("delete patchFile", new Object[0]);
                                }
                            }
                        }
                        com.tencent.bugly.beta.global.a.a("st.bch", betaGrayStrategy2);
                        an.a("onUpgradeReceived: %s [type: %d]", yVar3, Integer.valueOf(yVar3.g));
                        p.a.a(new w("rcv", System.currentTimeMillis(), (byte) 0, 0, yVar3.e, yVar3.m, yVar3.p, null));
                        betaGrayStrategy = betaGrayStrategy2;
                    } else if (parcelable != null || parcelable.a == null || parcelable.a.p == 3) {
                        betaGrayStrategy = null;
                    } else {
                        com.tencent.bugly.beta.global.a.a("st.bch");
                        betaGrayStrategy = null;
                    }
                }
            }
            yVar3 = yVar2;
            Object obj = betaGrayStrategy;
            if (yVar3 == null) {
                if (parcelable != null) {
                }
                betaGrayStrategy = null;
            } else {
                if (parcelable != null) {
                }
                betaGrayStrategy2 = new BetaGrayStrategy();
                betaGrayStrategy2.a = yVar3;
                betaGrayStrategy2.e = System.currentTimeMillis();
                if (parcelable != null) {
                    if (parcelable.a.f.b.equals(yVar3.f.b)) {
                        if (this.c != null) {
                            BetaReceiver.netListeners.remove(this.c.getDownloadUrl());
                            this.c.delete(true);
                            this.c = null;
                        } else {
                            e.E.p.a(parcelable.a.f.b, e.E.t.getAbsolutePath(), null, null).delete(true);
                            for (i = 0; i < r4; i++) {
                                if (!delete.delete()) {
                                    an.e("cannot deleteCache file:%s", r1[i].getAbsolutePath());
                                }
                            }
                        }
                    }
                    if (parcelable.a.p == 3) {
                        File file2 = e.E.H;
                        an.a("delete tmpPatchFile", new Object[0]);
                        file2 = e.E.G;
                        e.E.L = "";
                        an.a("delete patchFile", new Object[0]);
                    }
                }
                com.tencent.bugly.beta.global.a.a("st.bch", betaGrayStrategy2);
                an.a("onUpgradeReceived: %s [type: %d]", yVar3, Integer.valueOf(yVar3.g));
                p.a.a(new w("rcv", System.currentTimeMillis(), (byte) 0, 0, yVar3.e, yVar3.m, yVar3.p, null));
                betaGrayStrategy = betaGrayStrategy2;
            }
        }
        return betaGrayStrategy;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r11, boolean r12, int r13, com.tencent.bugly.proguard.y r14, java.lang.String r15) {
        /*
        r10 = this;
        r9 = 4;
        r8 = 2;
        r7 = 3;
        r6 = 1;
        r1 = r10.k;
        monitor-enter(r1);
        r0 = r10.a(r14);	 Catch:{ all -> 0x00d3 }
        r10.b = r0;	 Catch:{ all -> 0x00d3 }
        r10.g = r11;	 Catch:{ all -> 0x00d3 }
        r0 = r10.e;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x009a;
    L_0x0013:
        r0 = "你已放弃让SDK来处理策略";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00d3 }
        com.tencent.bugly.proguard.an.a(r0, r2);	 Catch:{ all -> 0x00d3 }
        r0 = 3;
        r10.j = r0;	 Catch:{ all -> 0x00d3 }
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        if (r0 != 0) goto L_0x002a;
    L_0x0022:
        r0 = "betaStrategy is null";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00d3 }
        com.tencent.bugly.proguard.an.a(r0, r2);	 Catch:{ all -> 0x00d3 }
    L_0x002a:
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x0055;
    L_0x002e:
        r0 = r10.c;	 Catch:{ all -> 0x00d3 }
        if (r0 != 0) goto L_0x0055;
    L_0x0032:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r0 = r0.p;	 Catch:{ all -> 0x00d3 }
        r2 = r10.b;	 Catch:{ all -> 0x00d3 }
        r2 = r2.a;	 Catch:{ all -> 0x00d3 }
        r2 = r2.f;	 Catch:{ all -> 0x00d3 }
        r2 = r2.b;	 Catch:{ all -> 0x00d3 }
        r3 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r3 = r3.t;	 Catch:{ all -> 0x00d3 }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x00d3 }
        r4 = 0;
        r5 = r10.b;	 Catch:{ all -> 0x00d3 }
        r5 = r5.a;	 Catch:{ all -> 0x00d3 }
        r5 = r5.f;	 Catch:{ all -> 0x00d3 }
        r5 = r5.a;	 Catch:{ all -> 0x00d3 }
        r0 = r0.a(r2, r3, r4, r5);	 Catch:{ all -> 0x00d3 }
        r10.c = r0;	 Catch:{ all -> 0x00d3 }
    L_0x0055:
        r0 = r10.c;	 Catch:{ all -> 0x00d3 }
        if (r0 != 0) goto L_0x00cb;
    L_0x0059:
        r0 = "用户自定义activity，创建task失败 [strategy:%s]";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00d3 }
        r3 = 0;
        r4 = r10.b;	 Catch:{ all -> 0x00d3 }
        r2[r3] = r4;	 Catch:{ all -> 0x00d3 }
        com.tencent.bugly.proguard.an.a(r0, r2);	 Catch:{ all -> 0x00d3 }
        r0 = 0;
        r10.b = r0;	 Catch:{ all -> 0x00d3 }
        r0 = "st.bch";
        com.tencent.bugly.beta.global.a.a(r0);	 Catch:{ all -> 0x00d3 }
    L_0x006e:
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ all -> 0x00d3 }
        r2 = 16;
        r3 = 5;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00d3 }
        r4 = 0;
        r5 = r10.e;	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r4 = 1;
        r5 = java.lang.Integer.valueOf(r13);	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r4 = 2;
        r5 = r10.b;	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r4 = 3;
        r5 = java.lang.Boolean.valueOf(r11);	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r4 = 4;
        r5 = java.lang.Boolean.valueOf(r12);	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r0.<init>(r2, r3);	 Catch:{ all -> 0x00d3 }
        com.tencent.bugly.beta.utils.e.a(r0);	 Catch:{ all -> 0x00d3 }
    L_0x009a:
        if (r13 == 0) goto L_0x00e8;
    L_0x009c:
        if (r11 == 0) goto L_0x00e8;
    L_0x009e:
        if (r12 != 0) goto L_0x00e8;
    L_0x00a0:
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        if (r0 != 0) goto L_0x00e8;
    L_0x00a4:
        r0 = r10.f;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x00d6;
    L_0x00a8:
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ all -> 0x00d3 }
        r2 = 18;
        r3 = 3;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00d3 }
        r4 = 0;
        r5 = r10.f;	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r4 = 1;
        r5 = -1;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r4 = 2;
        r5 = java.lang.Boolean.valueOf(r11);	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r0.<init>(r2, r3);	 Catch:{ all -> 0x00d3 }
        com.tencent.bugly.beta.utils.e.a(r0);	 Catch:{ all -> 0x00d3 }
    L_0x00c9:
        monitor-exit(r1);	 Catch:{ all -> 0x00d3 }
    L_0x00ca:
        return;
    L_0x00cb:
        r0 = r10.c;	 Catch:{ all -> 0x00d3 }
        r2 = r10.m;	 Catch:{ all -> 0x00d3 }
        r0.addListener(r2);	 Catch:{ all -> 0x00d3 }
        goto L_0x006e;
    L_0x00d3:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00d3 }
        throw r0;
    L_0x00d6:
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ all -> 0x00d3 }
        r2 = 5;
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00d3 }
        r4 = 0;
        r5 = com.tencent.bugly.beta.Beta.strToastCheckUpgradeError;	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r0.<init>(r2, r3);	 Catch:{ all -> 0x00d3 }
        com.tencent.bugly.beta.utils.e.a(r0);	 Catch:{ all -> 0x00d3 }
        goto L_0x00c9;
    L_0x00e8:
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x01bc;
    L_0x00ec:
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        r0 = r0.a;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x01bc;
    L_0x00f2:
        r0 = r10.f;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x0117;
    L_0x00f6:
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ all -> 0x00d3 }
        r2 = 18;
        r3 = 3;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00d3 }
        r4 = 0;
        r5 = r10.f;	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r4 = 1;
        r5 = 0;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r4 = 2;
        r5 = java.lang.Boolean.valueOf(r11);	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r0.<init>(r2, r3);	 Catch:{ all -> 0x00d3 }
        com.tencent.bugly.beta.utils.e.a(r0);	 Catch:{ all -> 0x00d3 }
    L_0x0117:
        r0 = r10.e;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x011d;
    L_0x011b:
        monitor-exit(r1);	 Catch:{ all -> 0x00d3 }
        goto L_0x00ca;
    L_0x011d:
        if (r11 != 0) goto L_0x018c;
    L_0x011f:
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        r0 = r0.a;	 Catch:{ all -> 0x00d3 }
        r0 = r0.g;	 Catch:{ all -> 0x00d3 }
        if (r0 == r8) goto L_0x018c;
    L_0x0127:
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        r0 = r0.d;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x0161;
    L_0x012d:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r0 = r0.e;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x0161;
    L_0x0133:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r0 = r0.s;	 Catch:{ all -> 0x00d3 }
        r0 = com.tencent.bugly.beta.global.a.a(r0);	 Catch:{ all -> 0x00d3 }
        if (r0 != r6) goto L_0x0143;
    L_0x013d:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r0 = r0.S;	 Catch:{ all -> 0x00d3 }
        if (r0 != 0) goto L_0x0153;
    L_0x0143:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r0 = r0.s;	 Catch:{ all -> 0x00d3 }
        r0 = com.tencent.bugly.beta.global.a.a(r0);	 Catch:{ all -> 0x00d3 }
        if (r0 != r9) goto L_0x0159;
    L_0x014d:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r0 = r0.T;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x0159;
    L_0x0153:
        r10.c();	 Catch:{ all -> 0x00d3 }
        monitor-exit(r1);	 Catch:{ all -> 0x00d3 }
        goto L_0x00ca;
    L_0x0159:
        if (r12 != 0) goto L_0x015e;
    L_0x015b:
        r10.a(r11);	 Catch:{ all -> 0x00d3 }
    L_0x015e:
        monitor-exit(r1);	 Catch:{ all -> 0x00d3 }
        goto L_0x00ca;
    L_0x0161:
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        r2 = r0.c;	 Catch:{ all -> 0x00d3 }
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        r0 = r0.a;	 Catch:{ all -> 0x00d3 }
        r4 = r0.i;	 Catch:{ all -> 0x00d3 }
        r2 = r2 + r4;
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00d3 }
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 > 0) goto L_0x0189;
    L_0x0174:
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        r0 = r0.a;	 Catch:{ all -> 0x00d3 }
        r0 = r0.h;	 Catch:{ all -> 0x00d3 }
        r2 = r10.b;	 Catch:{ all -> 0x00d3 }
        r2 = r2.b;	 Catch:{ all -> 0x00d3 }
        r0 = r0 - r2;
        if (r0 <= 0) goto L_0x0189;
    L_0x0181:
        r0 = r10.b;	 Catch:{ all -> 0x00d3 }
        r0 = r0.a;	 Catch:{ all -> 0x00d3 }
        r0 = r0.g;	 Catch:{ all -> 0x00d3 }
        if (r0 != r7) goto L_0x018c;
    L_0x0189:
        monitor-exit(r1);	 Catch:{ all -> 0x00d3 }
        goto L_0x00ca;
    L_0x018c:
        if (r12 != 0) goto L_0x01b9;
    L_0x018e:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r0 = r0.s;	 Catch:{ all -> 0x00d3 }
        r0 = com.tencent.bugly.beta.global.a.a(r0);	 Catch:{ all -> 0x00d3 }
        if (r0 != r6) goto L_0x019e;
    L_0x0198:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r0 = r0.S;	 Catch:{ all -> 0x00d3 }
        if (r0 != 0) goto L_0x01ae;
    L_0x019e:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r0 = r0.s;	 Catch:{ all -> 0x00d3 }
        r0 = com.tencent.bugly.beta.global.a.a(r0);	 Catch:{ all -> 0x00d3 }
        if (r0 != r9) goto L_0x01b6;
    L_0x01a8:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ all -> 0x00d3 }
        r0 = r0.T;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x01b6;
    L_0x01ae:
        if (r11 != 0) goto L_0x01b6;
    L_0x01b0:
        r10.c();	 Catch:{ all -> 0x00d3 }
        monitor-exit(r1);	 Catch:{ all -> 0x00d3 }
        goto L_0x00ca;
    L_0x01b6:
        r10.a(r11);	 Catch:{ all -> 0x00d3 }
    L_0x01b9:
        monitor-exit(r1);	 Catch:{ all -> 0x00d3 }
        goto L_0x00ca;
    L_0x01bc:
        r0 = r10.f;	 Catch:{ all -> 0x00d3 }
        if (r0 == 0) goto L_0x01e2;
    L_0x01c0:
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ all -> 0x00d3 }
        r2 = 18;
        r3 = 3;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00d3 }
        r4 = 0;
        r5 = r10.f;	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r4 = 1;
        r5 = 1;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r4 = 2;
        r5 = java.lang.Boolean.valueOf(r11);	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r0.<init>(r2, r3);	 Catch:{ all -> 0x00d3 }
        com.tencent.bugly.beta.utils.e.a(r0);	 Catch:{ all -> 0x00d3 }
        goto L_0x01b9;
    L_0x01e2:
        if (r11 == 0) goto L_0x01b9;
    L_0x01e4:
        if (r12 != 0) goto L_0x01b9;
    L_0x01e6:
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ all -> 0x00d3 }
        r2 = 5;
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00d3 }
        r4 = 0;
        r5 = com.tencent.bugly.beta.Beta.strToastYourAreTheLatestVersion;	 Catch:{ all -> 0x00d3 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d3 }
        r0.<init>(r2, r3);	 Catch:{ all -> 0x00d3 }
        com.tencent.bugly.beta.utils.e.a(r0);	 Catch:{ all -> 0x00d3 }
        goto L_0x01b9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.beta.upgrade.c.a(boolean, boolean, int, com.tencent.bugly.proguard.y, java.lang.String):void");
    }

    private y a() {
        return this.b == null ? null : this.b.a;
    }

    private DownloadTask b() {
        y a = a();
        if (a == null) {
            return null;
        }
        if (this.c == null) {
            this.c = e.E.p.a(a.f.b, e.E.t.getAbsolutePath(), null, this.b.a.f.a);
        }
        return this.c;
    }

    private void c() {
        y a = a();
        if (a != null) {
            if (this.c == null) {
                this.c = b();
            }
            if (this.c != null) {
                com.tencent.bugly.beta.global.a.a("st.bch", this.b);
                BetaReceiver.addTask(this.c);
                if (this.c.getStatus() != 1) {
                    this.c.download();
                } else if (this.g && com.tencent.bugly.beta.global.a.a(e.E.s, this.c.getSaveFile(), a.f.a)) {
                    p.a.a(new w("install", System.currentTimeMillis(), (byte) 0, 0, a.e, a.m, a.p, null));
                } else if (e.E.d) {
                    a(this.g);
                }
            }
        }
    }

    private void a(boolean z) {
        boolean z2 = false;
        y a = a();
        if (a != null) {
            if (System.currentTimeMillis() <= a.a() - LogBuilder.MAX_INTERVAL) {
                an.e(System.currentTimeMillis() + "ms", new Object[0]);
                return;
            }
            f.a.a(e.E.p, a.l);
            if (this.c == null) {
                this.c = b();
            }
            if (this.c == null) {
                return;
            }
            if (z || this.c.getStatus() != 2) {
                this.c.addListener(this.m);
                if (this.d != null) {
                    this.c.addListener(this.d);
                }
                h hVar = h.v;
                hVar.a(a, this.c);
                hVar.r = new d(3, this.b, this.c);
                hVar.s = new d(4, this.b, this.c, Boolean.valueOf(z));
                this.b.c = System.currentTimeMillis();
                com.tencent.bugly.beta.global.a.a("st.bch", this.b);
                if (z) {
                    f.a.a(new d(2, hVar, Boolean.valueOf(z)), (int) PathInterpolatorCompat.MAX_NUM_POINTS);
                    return;
                }
                f fVar = f.a;
                Object[] objArr = new Object[2];
                objArr[0] = hVar;
                if (z || a.g == (byte) 2) {
                    z2 = true;
                }
                objArr[1] = Boolean.valueOf(z2);
                fVar.a(new d(2, objArr));
                return;
            }
            an.a("Task is downloading %s %s", a.m, this.c.getDownloadUrl());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r12, boolean r13, int r14) {
        /*
        r11 = this;
        r3 = 3;
        r10 = 2;
        r2 = 1;
        r7 = 0;
        r9 = r11.l;
        monitor-enter(r9);
        r0 = "st.bch";
        r1 = com.tencent.bugly.beta.upgrade.BetaGrayStrategy.CREATOR;	 Catch:{ all -> 0x00d9 }
        r0 = com.tencent.bugly.beta.global.a.a(r0, r1);	 Catch:{ all -> 0x00d9 }
        r0 = (com.tencent.bugly.beta.upgrade.BetaGrayStrategy) r0;	 Catch:{ all -> 0x00d9 }
        if (r12 == 0) goto L_0x0106;
    L_0x0013:
        if (r0 == 0) goto L_0x0106;
    L_0x0015:
        r1 = r0.a;	 Catch:{ all -> 0x00d9 }
        if (r1 == 0) goto L_0x0106;
    L_0x0019:
        r1 = r0.a;	 Catch:{ all -> 0x00d9 }
        r1 = r1.p;	 Catch:{ all -> 0x00d9 }
        if (r1 != r3) goto L_0x0106;
    L_0x001f:
        r0 = 0;
        r8 = r0;
    L_0x0021:
        r0 = r11.n;	 Catch:{ all -> 0x00d9 }
        if (r0 == 0) goto L_0x002f;
    L_0x0025:
        r0 = r11.n;	 Catch:{ all -> 0x00d9 }
        r0 = r0.d;	 Catch:{ all -> 0x00d9 }
        if (r0 != 0) goto L_0x002f;
    L_0x002b:
        r0 = r11.p;	 Catch:{ all -> 0x00d9 }
        if (r0 == r12) goto L_0x00dc;
    L_0x002f:
        r11.p = r12;	 Catch:{ all -> 0x00d9 }
        r0 = r11.n;	 Catch:{ all -> 0x00d9 }
        if (r0 == 0) goto L_0x003a;
    L_0x0035:
        r0 = r11.n;	 Catch:{ all -> 0x00d9 }
        r1 = 1;
        r0.d = r1;	 Catch:{ all -> 0x00d9 }
    L_0x003a:
        r0 = new com.tencent.bugly.beta.upgrade.a;	 Catch:{ all -> 0x00d9 }
        r1 = 1;
        r3 = 804; // 0x324 float:1.127E-42 double:3.97E-321;
        r4 = 3;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x00d9 }
        r5 = 0;
        r6 = java.lang.Boolean.valueOf(r12);	 Catch:{ all -> 0x00d9 }
        r4[r5] = r6;	 Catch:{ all -> 0x00d9 }
        r5 = 1;
        r6 = java.lang.Boolean.valueOf(r13);	 Catch:{ all -> 0x00d9 }
        r4[r5] = r6;	 Catch:{ all -> 0x00d9 }
        r5 = 2;
        r4[r5] = r8;	 Catch:{ all -> 0x00d9 }
        r0.<init>(r1, r3, r4);	 Catch:{ all -> 0x00d9 }
        r11.n = r0;	 Catch:{ all -> 0x00d9 }
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ all -> 0x00d9 }
        r1 = 12;
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00d9 }
        r4 = 0;
        r5 = 0;
        r5 = java.lang.Boolean.valueOf(r5);	 Catch:{ all -> 0x00d9 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d9 }
        r4 = 1;
        r5 = r11.n;	 Catch:{ all -> 0x00d9 }
        r3[r4] = r5;	 Catch:{ all -> 0x00d9 }
        r0.<init>(r1, r3);	 Catch:{ all -> 0x00d9 }
        r11.o = r0;	 Catch:{ all -> 0x00d9 }
        r3 = "";
        r4 = 0;
        if (r8 == 0) goto L_0x0083;
    L_0x0077:
        r0 = r8.a;	 Catch:{ Throwable -> 0x00ce }
        if (r0 == 0) goto L_0x0083;
    L_0x007b:
        r0 = r8.a;	 Catch:{ Throwable -> 0x00ce }
        r3 = r0.m;	 Catch:{ Throwable -> 0x00ce }
        r0 = r8.a;	 Catch:{ Throwable -> 0x00ce }
        r4 = r0.o;	 Catch:{ Throwable -> 0x00ce }
    L_0x0083:
        r6 = new java.util.HashMap;	 Catch:{ Throwable -> 0x00ce }
        r6.<init>();	 Catch:{ Throwable -> 0x00ce }
        r0 = "G16";
        r1 = com.tencent.bugly.beta.global.e.E;	 Catch:{ Throwable -> 0x00ce }
        r1 = r1.L;	 Catch:{ Throwable -> 0x00ce }
        r6.put(r0, r1);	 Catch:{ Throwable -> 0x00ce }
        r1 = new com.tencent.bugly.proguard.z;	 Catch:{ Throwable -> 0x00ce }
        if (r12 == 0) goto L_0x00cc;
    L_0x0095:
        r1.<init>(r2, r3, r4, r6);	 Catch:{ Throwable -> 0x00ce }
        r2 = com.tencent.bugly.proguard.ah.a(r1);	 Catch:{ Throwable -> 0x00ce }
        r0 = com.tencent.bugly.beta.upgrade.b.a;	 Catch:{ Throwable -> 0x00ce }
        r1 = 804; // 0x324 float:1.127E-42 double:3.97E-321;
        r3 = r11.n;	 Catch:{ Throwable -> 0x00ce }
        r4 = com.tencent.bugly.beta.global.e.E;	 Catch:{ Throwable -> 0x00ce }
        r4 = r4.F;	 Catch:{ Throwable -> 0x00ce }
        r4 = r4.a;	 Catch:{ Throwable -> 0x00ce }
        r5 = r4.e;	 Catch:{ Throwable -> 0x00ce }
        r4 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00ce }
    L_0x00ae:
        if (r12 == 0) goto L_0x00b2;
    L_0x00b0:
        if (r13 == 0) goto L_0x00be;
    L_0x00b2:
        if (r8 == 0) goto L_0x00ca;
    L_0x00b4:
        r0 = r8.a;	 Catch:{ all -> 0x00d9 }
        if (r0 == 0) goto L_0x00ca;
    L_0x00b8:
        r0 = r8.a;	 Catch:{ all -> 0x00d9 }
        r0 = r0.p;	 Catch:{ all -> 0x00d9 }
        if (r0 != r10) goto L_0x00ca;
    L_0x00be:
        r0 = r11.o;	 Catch:{ all -> 0x00d9 }
        com.tencent.bugly.beta.utils.e.b(r0);	 Catch:{ all -> 0x00d9 }
        r0 = r11.o;	 Catch:{ all -> 0x00d9 }
        r2 = 6000; // 0x1770 float:8.408E-42 double:2.9644E-320;
        com.tencent.bugly.beta.utils.e.a(r0, r2);	 Catch:{ all -> 0x00d9 }
    L_0x00ca:
        monitor-exit(r9);	 Catch:{ all -> 0x00d9 }
        return;
    L_0x00cc:
        r2 = r7;
        goto L_0x0095;
    L_0x00ce:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.an.b(r0);	 Catch:{ all -> 0x00d9 }
        if (r1 != 0) goto L_0x00ae;
    L_0x00d5:
        r0.printStackTrace();	 Catch:{ all -> 0x00d9 }
        goto L_0x00ae;
    L_0x00d9:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x00d9 }
        throw r0;
    L_0x00dc:
        r1 = r11.n;	 Catch:{ all -> 0x00d9 }
        monitor-enter(r1);	 Catch:{ all -> 0x00d9 }
        r0 = r11.n;	 Catch:{ all -> 0x0103 }
        r0 = r0.c;	 Catch:{ all -> 0x0103 }
        r2 = 0;
        r3 = java.lang.Boolean.valueOf(r12);	 Catch:{ all -> 0x0103 }
        r0[r2] = r3;	 Catch:{ all -> 0x0103 }
        r0 = r11.n;	 Catch:{ all -> 0x0103 }
        r0 = r0.c;	 Catch:{ all -> 0x0103 }
        r2 = 1;
        r3 = java.lang.Boolean.valueOf(r13);	 Catch:{ all -> 0x0103 }
        r0[r2] = r3;	 Catch:{ all -> 0x0103 }
        monitor-exit(r1);	 Catch:{ all -> 0x0103 }
        r0 = r11.o;	 Catch:{ all -> 0x00d9 }
        r0 = r0.b;	 Catch:{ all -> 0x00d9 }
        r1 = 0;
        r2 = 0;
        r2 = java.lang.Boolean.valueOf(r2);	 Catch:{ all -> 0x00d9 }
        r0[r1] = r2;	 Catch:{ all -> 0x00d9 }
        goto L_0x00ae;
    L_0x0103:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0103 }
        throw r0;	 Catch:{ all -> 0x00d9 }
    L_0x0106:
        r8 = r0;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.beta.upgrade.c.a(boolean, boolean, int):void");
    }

    public void a(bg bgVar) {
        if (e.E.F == null) {
            e.E.F = new BetaUploadStrategy();
        }
        if (bgVar != null && e.E.F.b != bgVar.h) {
            e.E.F.b = bgVar.h;
            e.E.F.a.b = bgVar.b;
            e.E.F.a.c = bgVar.c;
            e.E.F.a.h = bgVar.h;
            if (ap.c(bgVar.d)) {
                e.E.F.a.d = bgVar.d;
            }
            if (ap.c(bgVar.e)) {
                e.E.F.a.e = bgVar.e;
            }
            if (!(bgVar.f == null || TextUtils.isEmpty(bgVar.f.a))) {
                e.E.F.a.f.a = bgVar.f.a;
            }
            if (bgVar.g != null && bgVar.g.size() > 0) {
                e.E.F.a.g = bgVar.g;
            }
            if (ap.c(bgVar.i)) {
                e.E.F.a.i = bgVar.i;
            }
            if (ap.c(bgVar.j)) {
                e.E.F.a.j = bgVar.j;
            }
            com.tencent.bugly.beta.global.a.a("us.bch", e.E.F);
        }
    }
}
