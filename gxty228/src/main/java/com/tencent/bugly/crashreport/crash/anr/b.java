package com.tencent.bugly.crashreport.crash.anr;

import android.app.ActivityManager;
import android.app.ActivityManager.ProcessErrorStateInfo;
import android.content.Context;
import android.os.FileObserver;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.ae;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ao;
import com.tencent.bugly.proguard.ap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: BUGLY */
public class b {
    private AtomicInteger a = new AtomicInteger(0);
    private long b = -1;
    private final Context c;
    private final a d;
    private final am e;
    private final com.tencent.bugly.crashreport.common.strategy.a f;
    private final String g;
    private final com.tencent.bugly.crashreport.crash.b h;
    private FileObserver i;
    private boolean j = true;

    public b(Context context, com.tencent.bugly.crashreport.common.strategy.a aVar, a aVar2, am amVar, ae aeVar, com.tencent.bugly.crashreport.crash.b bVar, BuglyStrategy.a aVar3) {
        this.c = ap.a(context);
        this.g = context.getDir("bugly", 0).getAbsolutePath();
        this.d = aVar2;
        this.e = amVar;
        this.f = aVar;
        this.h = bVar;
    }

    protected ProcessErrorStateInfo a(Context context, long j) {
        if (j < 0) {
            j = 0;
        }
        an.c("to find!", new Object[0]);
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        long j2 = j / 500;
        int i = 0;
        while (true) {
            an.c("waiting!", new Object[0]);
            List<ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState != null) {
                for (ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                    if (processErrorStateInfo.condition == 2) {
                        an.c("found!", new Object[0]);
                        return processErrorStateInfo;
                    }
                }
            }
            ap.b(500);
            int i2 = i + 1;
            if (((long) i) >= j2) {
                an.c("end!", new Object[0]);
                return null;
            }
            i = i2;
        }
    }

    protected a a(Context context, ProcessErrorStateInfo processErrorStateInfo, long j, Map<String, String> map) {
        int i;
        File file = new File(context.getFilesDir(), "bugly/bugly_trace_" + j + ".txt");
        a aVar = new a();
        aVar.c = j;
        aVar.d = file.getAbsolutePath();
        aVar.a = processErrorStateInfo.processName;
        aVar.f = processErrorStateInfo.shortMsg;
        aVar.e = processErrorStateInfo.longMsg;
        aVar.b = map;
        if (map != null) {
            for (String str : map.keySet()) {
                if (str.startsWith("main(")) {
                    aVar.g = (String) map.get(str);
                }
            }
        }
        String str2 = "anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d";
        Object[] objArr = new Object[6];
        objArr[0] = Long.valueOf(aVar.c);
        objArr[1] = aVar.d;
        objArr[2] = aVar.a;
        objArr[3] = aVar.f;
        objArr[4] = aVar.e;
        if (aVar.b == null) {
            i = 0;
        } else {
            i = aVar.b.size();
        }
        objArr[5] = Integer.valueOf(i);
        an.c(str2, objArr);
        return aVar;
    }

    protected CrashDetailBean a(a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.k();
            crashDetailBean.F = this.d.p();
            crashDetailBean.G = this.d.o();
            crashDetailBean.H = this.d.q();
            crashDetailBean.w = ap.a(this.c, c.e, c.h);
            crashDetailBean.b = 3;
            crashDetailBean.e = this.d.h();
            crashDetailBean.f = this.d.o;
            crashDetailBean.g = this.d.w();
            crashDetailBean.m = this.d.g();
            crashDetailBean.n = "ANR_EXCEPTION";
            crashDetailBean.o = aVar.f;
            crashDetailBean.q = aVar.g;
            crashDetailBean.P = new HashMap();
            crashDetailBean.P.put("BUGLY_CR_01", aVar.e);
            int i = -1;
            if (crashDetailBean.q != null) {
                i = crashDetailBean.q.indexOf("\n");
            }
            crashDetailBean.p = i > 0 ? crashDetailBean.q.substring(0, i) : "GET_FAIL";
            crashDetailBean.r = aVar.c;
            if (crashDetailBean.q != null) {
                crashDetailBean.u = ap.b(crashDetailBean.q.getBytes());
            }
            crashDetailBean.z = aVar.b;
            crashDetailBean.A = this.d.e;
            crashDetailBean.B = "main(1)";
            crashDetailBean.I = this.d.y();
            crashDetailBean.h = this.d.v();
            crashDetailBean.i = this.d.J();
            crashDetailBean.v = aVar.d;
            crashDetailBean.M = this.d.s;
            crashDetailBean.N = this.d.a;
            crashDetailBean.O = this.d.a();
            crashDetailBean.Q = this.d.H();
            crashDetailBean.R = this.d.I();
            crashDetailBean.S = this.d.B();
            crashDetailBean.T = this.d.G();
            this.h.c(crashDetailBean);
            crashDetailBean.y = ao.a();
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    protected boolean a(String str, String str2, String str3) {
        BufferedWriter bufferedWriter;
        Throwable e;
        TraceFileHelper.a readTargetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (readTargetDumpInfo == null || readTargetDumpInfo.d == null || readTargetDumpInfo.d.size() <= 0) {
            an.e("not found trace dump for %s", str3);
            return false;
        }
        File file = new File(str2);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            if (file.exists() && file.canWrite()) {
                BufferedWriter bufferedWriter2 = null;
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                    try {
                        String[] strArr = (String[]) readTargetDumpInfo.d.get("main");
                        if (strArr != null && strArr.length >= 3) {
                            String str4 = strArr[0];
                            bufferedWriter.write("\"main\" tid=" + strArr[2] + " :\n" + str4 + "\n" + strArr[1] + "\n\n");
                            bufferedWriter.flush();
                        }
                        for (Entry entry : readTargetDumpInfo.d.entrySet()) {
                            if (!(((String) entry.getKey()).equals("main") || entry.getValue() == null || ((String[]) entry.getValue()).length < 3)) {
                                String str5 = ((String[]) entry.getValue())[0];
                                bufferedWriter.write("\"" + ((String) entry.getKey()) + "\" tid=" + ((String[]) entry.getValue())[2] + " :\n" + str5 + "\n" + ((String[]) entry.getValue())[1] + "\n\n");
                                bufferedWriter.flush();
                            }
                        }
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (Throwable e2) {
                                if (!an.a(e2)) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        return true;
                    } catch (IOException e3) {
                        e2 = e3;
                        bufferedWriter2 = bufferedWriter;
                        try {
                            if (!an.a(e2)) {
                                e2.printStackTrace();
                            }
                            an.e("dump trace fail %s", e2.getClass().getName() + ":" + e2.getMessage());
                            if (bufferedWriter2 != null) {
                                try {
                                    bufferedWriter2.close();
                                } catch (Throwable e22) {
                                    if (!an.a(e22)) {
                                        e22.printStackTrace();
                                    }
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            e22 = th;
                            bufferedWriter = bufferedWriter2;
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (Throwable e4) {
                                    if (!an.a(e4)) {
                                        e4.printStackTrace();
                                    }
                                }
                            }
                            throw e22;
                        }
                    } catch (Throwable th2) {
                        e22 = th2;
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        throw e22;
                    }
                } catch (IOException e5) {
                    e22 = e5;
                    if (an.a(e22)) {
                        e22.printStackTrace();
                    }
                    an.e("dump trace fail %s", e22.getClass().getName() + ":" + e22.getMessage());
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    return false;
                } catch (Throwable th3) {
                    e22 = th3;
                    bufferedWriter = null;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    throw e22;
                }
            }
            an.e("backup file create fail %s", str2);
            return false;
        } catch (Throwable e222) {
            if (!an.a(e222)) {
                e222.printStackTrace();
            }
            an.e("backup file create error! %s  %s", e222.getClass().getName() + ":" + e222.getMessage(), str2);
            return false;
        }
    }

    public boolean a() {
        return this.a.get() != 0;
    }

    protected boolean a(Context context, String str, ProcessErrorStateInfo processErrorStateInfo, long j, Map<String, String> map) {
        a a = a(context, processErrorStateInfo, j, map);
        if (!this.f.b()) {
            an.e("crash report sync remote fail, will not upload to Bugly , print local for helpful!", new Object[0]);
            com.tencent.bugly.crashreport.crash.b.a("ANR", ap.a(), a.a, "main", a.e, null);
            return false;
        } else if (this.f.c().j) {
            an.a("found visiable anr , start to upload!", new Object[0]);
            CrashDetailBean a2 = a(a);
            if (a2 == null) {
                an.e("pack anr fail!", new Object[0]);
                return false;
            }
            c.a().a(a2);
            if (a2.a >= 0) {
                an.a("backup anr record success!", new Object[0]);
            } else {
                an.d("backup anr record fail!", new Object[0]);
            }
            if (str != null && new File(str).exists()) {
                this.a.set(3);
                if (a(str, a.d, a.a)) {
                    an.a("backup trace success", new Object[0]);
                }
            }
            com.tencent.bugly.crashreport.crash.b.a("ANR", ap.a(), a.a, "main", a.e, a2);
            if (!this.h.a(a2)) {
                this.h.a(a2, 3000, true);
            }
            this.h.b(a2);
            return true;
        } else {
            an.d("ANR Report is closed!", new Object[0]);
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r11) {
        /*
        r10 = this;
        r8 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = -1;
        r7 = 0;
        monitor-enter(r10);
        r2 = r10.a;	 Catch:{ all -> 0x0063 }
        r2 = r2.get();	 Catch:{ all -> 0x0063 }
        if (r2 == 0) goto L_0x0018;
    L_0x000e:
        r0 = "trace started return ";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0063 }
        com.tencent.bugly.proguard.an.c(r0, r1);	 Catch:{ all -> 0x0063 }
        monitor-exit(r10);	 Catch:{ all -> 0x0063 }
    L_0x0017:
        return;
    L_0x0018:
        r2 = r10.a;	 Catch:{ all -> 0x0063 }
        r3 = 1;
        r2.set(r3);	 Catch:{ all -> 0x0063 }
        monitor-exit(r10);	 Catch:{ all -> 0x0063 }
        r2 = "read trace first dump for create time!";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x00e9 }
        com.tencent.bugly.proguard.an.c(r2, r3);	 Catch:{ Throwable -> 0x00e9 }
        r2 = 0;
        r2 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r11, r2);	 Catch:{ Throwable -> 0x00e9 }
        if (r2 == 0) goto L_0x0114;
    L_0x002e:
        r4 = r2.c;	 Catch:{ Throwable -> 0x00e9 }
    L_0x0030:
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 != 0) goto L_0x0040;
    L_0x0034:
        r0 = "trace dump fail could not get time!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00e9 }
        com.tencent.bugly.proguard.an.d(r0, r1);	 Catch:{ Throwable -> 0x00e9 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x00e9 }
    L_0x0040:
        r0 = r10.b;	 Catch:{ Throwable -> 0x00e9 }
        r0 = r4 - r0;
        r0 = java.lang.Math.abs(r0);	 Catch:{ Throwable -> 0x00e9 }
        r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r0 >= 0) goto L_0x0066;
    L_0x004c:
        r0 = "should not process ANR too Fre in %d";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00e9 }
        r2 = 0;
        r3 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x00e9 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x00e9 }
        com.tencent.bugly.proguard.an.d(r0, r1);	 Catch:{ Throwable -> 0x00e9 }
        r0 = r10.a;
        r0.set(r7);
        goto L_0x0017;
    L_0x0063:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0063 }
        throw r0;
    L_0x0066:
        r10.b = r4;	 Catch:{ Throwable -> 0x00e9 }
        r0 = r10.a;	 Catch:{ Throwable -> 0x00e9 }
        r1 = 1;
        r0.set(r1);	 Catch:{ Throwable -> 0x00e9 }
        r0 = com.tencent.bugly.crashreport.crash.c.f;	 Catch:{ Throwable -> 0x008b }
        r1 = 0;
        r6 = com.tencent.bugly.proguard.ap.a(r0, r1);	 Catch:{ Throwable -> 0x008b }
        if (r6 == 0) goto L_0x007d;
    L_0x0077:
        r0 = r6.size();	 Catch:{ Throwable -> 0x00e9 }
        if (r0 > 0) goto L_0x009e;
    L_0x007d:
        r0 = "can't get all thread skip this anr";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00e9 }
        com.tencent.bugly.proguard.an.d(r0, r1);	 Catch:{ Throwable -> 0x00e9 }
        r0 = r10.a;
        r0.set(r7);
        goto L_0x0017;
    L_0x008b:
        r0 = move-exception;
        com.tencent.bugly.proguard.an.a(r0);	 Catch:{ Throwable -> 0x00e9 }
        r0 = "get all thread stack fail!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00e9 }
        com.tencent.bugly.proguard.an.e(r0, r1);	 Catch:{ Throwable -> 0x00e9 }
        r0 = r10.a;
        r0.set(r7);
        goto L_0x0017;
    L_0x009e:
        r0 = r10.c;	 Catch:{ Throwable -> 0x00e9 }
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r3 = r10.a(r0, r2);	 Catch:{ Throwable -> 0x00e9 }
        if (r3 != 0) goto L_0x00b7;
    L_0x00a8:
        r0 = "proc state is unvisiable!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00e9 }
        com.tencent.bugly.proguard.an.c(r0, r1);	 Catch:{ Throwable -> 0x00e9 }
        r0 = r10.a;
        r0.set(r7);
        goto L_0x0017;
    L_0x00b7:
        r0 = r3.pid;	 Catch:{ Throwable -> 0x00e9 }
        r1 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x00e9 }
        if (r0 == r1) goto L_0x00d3;
    L_0x00bf:
        r0 = "not mind proc!";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00e9 }
        r2 = 0;
        r3 = r3.processName;	 Catch:{ Throwable -> 0x00e9 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x00e9 }
        com.tencent.bugly.proguard.an.c(r0, r1);	 Catch:{ Throwable -> 0x00e9 }
        r0 = r10.a;
        r0.set(r7);
        goto L_0x0017;
    L_0x00d3:
        r0 = "found visiable anr , start to process!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00e9 }
        com.tencent.bugly.proguard.an.a(r0, r1);	 Catch:{ Throwable -> 0x00e9 }
        r1 = r10.c;	 Catch:{ Throwable -> 0x00e9 }
        r0 = r10;
        r2 = r11;
        r0.a(r1, r2, r3, r4, r6);	 Catch:{ Throwable -> 0x00e9 }
        r0 = r10.a;
        r0.set(r7);
        goto L_0x0017;
    L_0x00e9:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.an.a(r0);	 Catch:{ all -> 0x010d }
        if (r1 != 0) goto L_0x00f3;
    L_0x00f0:
        r0.printStackTrace();	 Catch:{ all -> 0x010d }
    L_0x00f3:
        r1 = "handle anr error %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x010d }
        r3 = 0;
        r0 = r0.getClass();	 Catch:{ all -> 0x010d }
        r0 = r0.toString();	 Catch:{ all -> 0x010d }
        r2[r3] = r0;	 Catch:{ all -> 0x010d }
        com.tencent.bugly.proguard.an.e(r1, r2);	 Catch:{ all -> 0x010d }
        r0 = r10.a;
        r0.set(r7);
        goto L_0x0017;
    L_0x010d:
        r0 = move-exception;
        r1 = r10.a;
        r1.set(r7);
        throw r0;
    L_0x0114:
        r4 = r0;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(java.lang.String):void");
    }

    protected synchronized void b() {
        if (d()) {
            an.d("start when started!", new Object[0]);
        } else {
            this.i = new FileObserver(this, "/data/anr/", 8) {
                final /* synthetic */ b a;

                public void onEvent(int i, String str) {
                    if (str != null) {
                        String str2 = "/data/anr/" + str;
                        if (str2.contains("trace")) {
                            this.a.a(str2);
                            return;
                        }
                        an.d("not anr file %s", str2);
                    }
                }
            };
            try {
                this.i.startWatching();
                an.a("start anr monitor!", new Object[0]);
                this.e.a(new Runnable(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.f();
                    }
                });
            } catch (Throwable th) {
                this.i = null;
                an.d("start anr monitor failed!", new Object[0]);
                if (!an.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    protected synchronized void c() {
        if (d()) {
            try {
                this.i.stopWatching();
                this.i = null;
                an.d("close anr monitor!", new Object[0]);
            } catch (Throwable th) {
                an.d("stop anr monitor failed!", new Object[0]);
                if (!an.a(th)) {
                    th.printStackTrace();
                }
            }
        } else {
            an.d("close when closed!", new Object[0]);
        }
    }

    protected synchronized boolean d() {
        return this.i != null;
    }

    protected synchronized void a(boolean z) {
        if (z) {
            b();
        } else {
            c();
        }
    }

    public synchronized boolean e() {
        return this.j;
    }

    private synchronized void c(boolean z) {
        if (this.j != z) {
            an.a("user change anr %b", Boolean.valueOf(z));
            this.j = z;
        }
    }

    public void b(boolean z) {
        c(z);
        boolean e = e();
        com.tencent.bugly.crashreport.common.strategy.a a = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (a != null) {
            e = e && a.c().g;
        }
        if (e != d()) {
            an.a("anr changed to %b", Boolean.valueOf(e));
            a(e);
        }
    }

    protected void f() {
        long b = ap.b() - c.g;
        File file = new File(this.g);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "bugly_trace_";
                String str2 = ".txt";
                int length = str.length();
                int i = 0;
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (name.startsWith(str)) {
                        try {
                            int indexOf = name.indexOf(str2);
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b) {
                            }
                        } catch (Throwable th) {
                            an.e("tomb format error delete %s", name);
                        }
                        if (file2.delete()) {
                            i++;
                        }
                    }
                }
                an.c("clean tombs %d", Integer.valueOf(i));
            }
        }
    }

    public synchronized void a(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.j != d()) {
                    an.d("server anr changed to %b", Boolean.valueOf(strategyBean.j));
                }
                if (!(strategyBean.j && e())) {
                    z = false;
                }
                if (z != d()) {
                    an.a("anr changed to %b", Boolean.valueOf(z));
                    a(z);
                }
            }
        }
    }

    public void g() {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i < 30) {
                try {
                    an.a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i2));
                    ap.b(5000);
                    i = i2;
                } catch (Throwable th) {
                    if (!an.a(th)) {
                        th.printStackTrace();
                        return;
                    }
                    return;
                }
            }
            return;
        }
    }
}
