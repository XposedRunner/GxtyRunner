package com.tencent.bugly.beta.global;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.util.DisplayMetrics;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.b;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.bugly.beta.ui.UILifecycleListener;
import com.tencent.bugly.beta.upgrade.BetaUploadStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BUGLY */
public class e {
    public static e E = new e();
    public static int a = 0;
    public SharedPreferences A;
    public DisplayMetrics B;
    public boolean C = true;
    public boolean D = false;
    public BetaUploadStrategy F;
    public File G;
    public File H;
    public File I;
    public String J = "";
    public String K = "";
    public String L = "";
    public String M = "";
    public boolean N = false;
    public int O = 0;
    public String P = "";
    public boolean Q = false;
    public boolean R = true;
    public boolean S = false;
    public boolean T = false;
    public boolean U = true;
    public boolean V = true;
    public BetaPatchListener W;
    public boolean X = false;
    public boolean Y = true;
    public boolean Z = false;
    public final List<String> aa = new ArrayList();
    public boolean ab = false;
    public boolean ac = false;
    public boolean ad;
    public int ae = 1;
    public long b = 3000;
    public long c = 0;
    public boolean d = true;
    public boolean e = true;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public UILifecycleListener<UpgradeInfo> k;
    public File l;
    public final List<Class<? extends Activity>> m = new ArrayList();
    public final List<Class<? extends Activity>> n = new ArrayList();
    public int o;
    public b p;
    public DownloadListener q;
    public File r;
    public Context s;
    public File t;
    public String u;
    public String v;
    public int w = Integer.MIN_VALUE;
    public String x = "";
    public String y = "";
    public PackageInfo z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r11) {
        /*
        r10 = this;
        r1 = 1;
        r2 = 0;
        monitor-enter(r10);
        r0 = E;	 Catch:{ all -> 0x01bb }
        r3 = r11.getApplicationContext();	 Catch:{ all -> 0x01bb }
        r0.s = r3;	 Catch:{ all -> 0x01bb }
        r0 = r10.s;	 Catch:{ all -> 0x01bb }
        r3 = r0.getPackageManager();	 Catch:{ all -> 0x01bb }
        r0 = r10.s;	 Catch:{ Exception -> 0x01af }
        r0 = r0.getPackageName();	 Catch:{ Exception -> 0x01af }
        r4 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        r0 = r3.getPackageInfo(r0, r4);	 Catch:{ Exception -> 0x01af }
        r10.z = r0;	 Catch:{ Exception -> 0x01af }
        r0 = r10.w;	 Catch:{ Exception -> 0x01af }
        r4 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r0 != r4) goto L_0x002b;
    L_0x0025:
        r0 = r10.z;	 Catch:{ Exception -> 0x01af }
        r0 = r0.versionCode;	 Catch:{ Exception -> 0x01af }
        r10.w = r0;	 Catch:{ Exception -> 0x01af }
    L_0x002b:
        r0 = r10.x;	 Catch:{ Exception -> 0x01af }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01af }
        if (r0 == 0) goto L_0x0039;
    L_0x0033:
        r0 = r10.z;	 Catch:{ Exception -> 0x01af }
        r0 = r0.versionName;	 Catch:{ Exception -> 0x01af }
        r10.x = r0;	 Catch:{ Exception -> 0x01af }
    L_0x0039:
        r0 = r10.z;	 Catch:{ Exception -> 0x01af }
        r0 = r0.applicationInfo;	 Catch:{ Exception -> 0x01af }
        r0 = r0.loadLabel(r3);	 Catch:{ Exception -> 0x01af }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x01af }
        r10.y = r0;	 Catch:{ Exception -> 0x01af }
        r0 = r10.P;	 Catch:{ Exception -> 0x01af }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x01af }
        if (r0 == 0) goto L_0x0057;
    L_0x004d:
        r0 = r10.s;	 Catch:{ Exception -> 0x01af }
        r4 = "BUGLY_CHANNEL";
        r0 = com.tencent.bugly.beta.global.a.a(r0, r4);	 Catch:{ Exception -> 0x01af }
        r10.P = r0;	 Catch:{ Exception -> 0x01af }
    L_0x0057:
        r0 = "window";
        r0 = r11.getSystemService(r0);	 Catch:{ all -> 0x01bb }
        r0 = (android.view.WindowManager) r0;	 Catch:{ all -> 0x01bb }
        r4 = new android.util.DisplayMetrics;	 Catch:{ all -> 0x01bb }
        r4.<init>();	 Catch:{ all -> 0x01bb }
        r10.B = r4;	 Catch:{ all -> 0x01bb }
        if (r0 == 0) goto L_0x0077;
    L_0x0068:
        r4 = r0.getDefaultDisplay();	 Catch:{ all -> 0x01bb }
        if (r4 == 0) goto L_0x0077;
    L_0x006e:
        r0 = r0.getDefaultDisplay();	 Catch:{ all -> 0x01bb }
        r4 = r10.B;	 Catch:{ all -> 0x01bb }
        r0.getMetrics(r4);	 Catch:{ all -> 0x01bb }
    L_0x0077:
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bb }
        r0.<init>();	 Catch:{ all -> 0x01bb }
        r4 = r10.s;	 Catch:{ all -> 0x01bb }
        r4 = r4.getPackageName();	 Catch:{ all -> 0x01bb }
        r0 = r0.append(r4);	 Catch:{ all -> 0x01bb }
        r4 = "/.beta/";
        r0 = r0.append(r4);	 Catch:{ all -> 0x01bb }
        r4 = "/apk/";
        r5 = "/res/";
        r6 = r10.l;	 Catch:{ all -> 0x01bb }
        if (r6 == 0) goto L_0x009c;
    L_0x0094:
        r6 = r10.l;	 Catch:{ all -> 0x01bb }
        r6 = r6.exists();	 Catch:{ all -> 0x01bb }
        if (r6 != 0) goto L_0x01be;
    L_0x009c:
        r6 = new java.io.File;	 Catch:{ all -> 0x01bb }
        r7 = android.os.Environment.DIRECTORY_DOWNLOADS;	 Catch:{ all -> 0x01bb }
        r7 = android.os.Environment.getExternalStoragePublicDirectory(r7);	 Catch:{ all -> 0x01bb }
        r0 = r0.toString();	 Catch:{ all -> 0x01bb }
        r6.<init>(r7, r0);	 Catch:{ all -> 0x01bb }
        r10.l = r6;	 Catch:{ all -> 0x01bb }
        r0 = new java.io.File;	 Catch:{ all -> 0x01bb }
        r6 = r10.l;	 Catch:{ all -> 0x01bb }
        r0.<init>(r6, r4);	 Catch:{ all -> 0x01bb }
        r10.t = r0;	 Catch:{ all -> 0x01bb }
        r0 = new java.io.File;	 Catch:{ all -> 0x01bb }
        r4 = r10.l;	 Catch:{ all -> 0x01bb }
        r0.<init>(r4, r5);	 Catch:{ all -> 0x01bb }
        r10.r = r0;	 Catch:{ all -> 0x01bb }
    L_0x00bf:
        r0 = "android.permission.WRITE_EXTERNAL_STORAGE";
        r4 = r10.z;	 Catch:{ all -> 0x01bb }
        r4 = r4.packageName;	 Catch:{ all -> 0x01bb }
        r0 = r3.checkPermission(r0, r4);	 Catch:{ all -> 0x01bb }
        if (r0 != 0) goto L_0x01fc;
    L_0x00cb:
        r0 = r1;
    L_0x00cc:
        if (r0 == 0) goto L_0x00fa;
    L_0x00ce:
        r0 = "mounted";
        r1 = android.os.Environment.getExternalStorageState();	 Catch:{ Exception -> 0x01ff }
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x01ff }
        if (r0 == 0) goto L_0x00fa;
    L_0x00da:
        r0 = r10.t;	 Catch:{ Exception -> 0x01ff }
        r0 = r0.exists();	 Catch:{ Exception -> 0x01ff }
        if (r0 != 0) goto L_0x00ea;
    L_0x00e2:
        r0 = r10.t;	 Catch:{ Exception -> 0x01ff }
        r0 = r0.mkdirs();	 Catch:{ Exception -> 0x01ff }
        if (r0 == 0) goto L_0x00fa;
    L_0x00ea:
        r0 = r10.r;	 Catch:{ Exception -> 0x01ff }
        r0 = r0.exists();	 Catch:{ Exception -> 0x01ff }
        if (r0 != 0) goto L_0x013c;
    L_0x00f2:
        r0 = r10.r;	 Catch:{ Exception -> 0x01ff }
        r0 = r0.mkdirs();	 Catch:{ Exception -> 0x01ff }
        if (r0 != 0) goto L_0x013c;
    L_0x00fa:
        r0 = r10.s;	 Catch:{ Exception -> 0x01ff }
        r1 = "apk";
        r0 = r0.getExternalFilesDir(r1);	 Catch:{ Exception -> 0x01ff }
        r10.t = r0;	 Catch:{ Exception -> 0x01ff }
        r0 = r10.s;	 Catch:{ Exception -> 0x01ff }
        r1 = "res";
        r0 = r0.getExternalFilesDir(r1);	 Catch:{ Exception -> 0x01ff }
        r10.r = r0;	 Catch:{ Exception -> 0x01ff }
        r0 = r10.t;	 Catch:{ Exception -> 0x01ff }
        if (r0 == 0) goto L_0x0126;
    L_0x0112:
        r0 = r10.t;	 Catch:{ Exception -> 0x01ff }
        r0 = r0.exists();	 Catch:{ Exception -> 0x01ff }
        if (r0 == 0) goto L_0x0126;
    L_0x011a:
        r0 = r10.r;	 Catch:{ Exception -> 0x01ff }
        if (r0 == 0) goto L_0x0126;
    L_0x011e:
        r0 = r10.r;	 Catch:{ Exception -> 0x01ff }
        r0 = r0.exists();	 Catch:{ Exception -> 0x01ff }
        if (r0 != 0) goto L_0x013c;
    L_0x0126:
        r0 = r10.s;	 Catch:{ Exception -> 0x01ff }
        r1 = "apk";
        r2 = 2;
        r0 = r0.getDir(r1, r2);	 Catch:{ Exception -> 0x01ff }
        r10.t = r0;	 Catch:{ Exception -> 0x01ff }
        r0 = r10.s;	 Catch:{ Exception -> 0x01ff }
        r1 = "res";
        r2 = 0;
        r0 = r0.getDir(r1, r2);	 Catch:{ Exception -> 0x01ff }
        r10.r = r0;	 Catch:{ Exception -> 0x01ff }
    L_0x013c:
        r0 = "apkSaveDir: %s, resSaveDir: %s";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x01bb }
        r2 = 0;
        r3 = r10.t;	 Catch:{ all -> 0x01bb }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x01bb }
        r1[r2] = r3;	 Catch:{ all -> 0x01bb }
        r2 = 1;
        r3 = r10.r;	 Catch:{ all -> 0x01bb }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x01bb }
        r1[r2] = r3;	 Catch:{ all -> 0x01bb }
        com.tencent.bugly.proguard.an.a(r0, r1);	 Catch:{ all -> 0x01bb }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bb }
        r0.<init>();	 Catch:{ all -> 0x01bb }
        r1 = r11.getPackageName();	 Catch:{ all -> 0x01bb }
        r0 = r0.append(r1);	 Catch:{ all -> 0x01bb }
        r1 = ".BETA_VALUES";
        r0 = r0.append(r1);	 Catch:{ all -> 0x01bb }
        r0 = r0.toString();	 Catch:{ all -> 0x01bb }
        r1 = r10.s;	 Catch:{ all -> 0x01bb }
        r0 = com.tencent.bugly.proguard.ap.a(r0, r1);	 Catch:{ all -> 0x01bb }
        r10.A = r0;	 Catch:{ all -> 0x01bb }
        r0 = "isFirstRun";
        r1 = 1;
        r0 = com.tencent.bugly.beta.global.a.b(r0, r1);	 Catch:{ all -> 0x01bb }
        r10.C = r0;	 Catch:{ all -> 0x01bb }
        r0 = r10.C;	 Catch:{ all -> 0x01bb }
        if (r0 == 0) goto L_0x0188;
    L_0x0182:
        r0 = "isFirstRun";
        r1 = 0;
        com.tencent.bugly.beta.global.a.a(r0, r1);	 Catch:{ all -> 0x01bb }
    L_0x0188:
        r0 = com.tencent.bugly.crashreport.common.info.a.b();	 Catch:{ all -> 0x01bb }
        if (r0 == 0) goto L_0x0196;
    L_0x018e:
        r0 = com.tencent.bugly.crashreport.common.info.a.b();	 Catch:{ all -> 0x01bb }
        r0 = r0.K;	 Catch:{ all -> 0x01bb }
        r10.Q = r0;	 Catch:{ all -> 0x01bb }
    L_0x0196:
        r0 = "us.bch";
        r1 = com.tencent.bugly.beta.upgrade.BetaUploadStrategy.CREATOR;	 Catch:{ all -> 0x01bb }
        r0 = com.tencent.bugly.beta.global.a.a(r0, r1);	 Catch:{ all -> 0x01bb }
        r0 = (com.tencent.bugly.beta.upgrade.BetaUploadStrategy) r0;	 Catch:{ all -> 0x01bb }
        r10.F = r0;	 Catch:{ all -> 0x01bb }
        r0 = r10.F;	 Catch:{ all -> 0x01bb }
        if (r0 != 0) goto L_0x01ad;
    L_0x01a6:
        r0 = new com.tencent.bugly.beta.upgrade.BetaUploadStrategy;	 Catch:{ all -> 0x01bb }
        r0.<init>();	 Catch:{ all -> 0x01bb }
        r10.F = r0;	 Catch:{ all -> 0x01bb }
    L_0x01ad:
        monitor-exit(r10);
        return;
    L_0x01af:
        r0 = move-exception;
        r4 = com.tencent.bugly.proguard.an.b(r0);	 Catch:{ all -> 0x01bb }
        if (r4 != 0) goto L_0x0057;
    L_0x01b6:
        r0.printStackTrace();	 Catch:{ all -> 0x01bb }
        goto L_0x0057;
    L_0x01bb:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x01be:
        r6 = new java.io.File;	 Catch:{ all -> 0x01bb }
        r7 = r10.l;	 Catch:{ all -> 0x01bb }
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bb }
        r8.<init>();	 Catch:{ all -> 0x01bb }
        r9 = r0.toString();	 Catch:{ all -> 0x01bb }
        r8 = r8.append(r9);	 Catch:{ all -> 0x01bb }
        r4 = r8.append(r4);	 Catch:{ all -> 0x01bb }
        r4 = r4.toString();	 Catch:{ all -> 0x01bb }
        r6.<init>(r7, r4);	 Catch:{ all -> 0x01bb }
        r10.t = r6;	 Catch:{ all -> 0x01bb }
        r4 = new java.io.File;	 Catch:{ all -> 0x01bb }
        r6 = r10.l;	 Catch:{ all -> 0x01bb }
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bb }
        r7.<init>();	 Catch:{ all -> 0x01bb }
        r0 = r0.toString();	 Catch:{ all -> 0x01bb }
        r0 = r7.append(r0);	 Catch:{ all -> 0x01bb }
        r0 = r0.append(r5);	 Catch:{ all -> 0x01bb }
        r0 = r0.toString();	 Catch:{ all -> 0x01bb }
        r4.<init>(r6, r0);	 Catch:{ all -> 0x01bb }
        r10.r = r4;	 Catch:{ all -> 0x01bb }
        goto L_0x00bf;
    L_0x01fc:
        r0 = r2;
        goto L_0x00cc;
    L_0x01ff:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.an.b(r0);	 Catch:{ all -> 0x01bb }
        if (r1 != 0) goto L_0x013c;
    L_0x0206:
        r0.printStackTrace();	 Catch:{ all -> 0x01bb }
        goto L_0x013c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.beta.global.e.a(android.content.Context):void");
    }
}
