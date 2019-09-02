package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.crashreport.crash.anr.b;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.ae;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: BUGLY */
public class c {
    public static int a = 0;
    public static boolean b = false;
    public static int c = 2;
    public static boolean d = true;
    public static int e = 20480;
    public static int f = 20480;
    public static long g = 604800000;
    public static String h = null;
    public static boolean i = false;
    public static String j = null;
    public static int k = GLMapStaticValue.TMC_REFRESH_TIMELIMIT;
    public static boolean l = true;
    public static boolean m = false;
    public static String n = null;
    public static String o = null;
    private static c v;
    public final b p;
    public final a q = a.a();
    public final am r;
    public BuglyStrategy.a s;
    public f t;
    private final Context u;
    private final e w;
    private final NativeCrashHandler x;
    private final b y;
    private Boolean z;

    protected c(int i, Context context, am amVar, boolean z, BuglyStrategy.a aVar, f fVar, String str) {
        a = i;
        Context a = ap.a(context);
        this.u = a;
        this.r = amVar;
        this.s = aVar;
        this.t = fVar;
        ak a2 = ak.a();
        ae a3 = ae.a();
        this.p = new b(i, a, a2, a3, this.q, aVar, fVar);
        com.tencent.bugly.crashreport.common.info.a a4 = com.tencent.bugly.crashreport.common.info.a.a(a);
        this.w = new e(a, this.p, this.q, a4);
        this.x = NativeCrashHandler.getInstance(a, a4, this.p, this.q, amVar, z, str);
        a4.L = this.x;
        this.y = new b(a, this.q, a4, amVar, a3, this.p, aVar);
    }

    public static synchronized c a(int i, Context context, boolean z, BuglyStrategy.a aVar, f fVar, String str) {
        c cVar;
        synchronized (c.class) {
            if (v == null) {
                v = new c(i, context, am.a(), z, aVar, fVar, str);
            }
            cVar = v;
        }
        return cVar;
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            cVar = v;
        }
        return cVar;
    }

    public void a(StrategyBean strategyBean) {
        this.w.a(strategyBean);
        this.x.onStrategyChanged(strategyBean);
        this.y.a(strategyBean);
        a(3000);
    }

    public boolean b() {
        Boolean bool = this.z;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = com.tencent.bugly.crashreport.common.info.a.b().e;
        List<ag> a = ae.a().a(1);
        List arrayList = new ArrayList();
        if (a == null || a.size() <= 0) {
            this.z = Boolean.valueOf(false);
            return false;
        }
        for (ag agVar : a) {
            if (str.equals(agVar.c)) {
                this.z = Boolean.valueOf(true);
                arrayList.add(agVar);
            }
        }
        if (arrayList.size() > 0) {
            ae.a().a(arrayList);
        }
        return true;
    }

    public synchronized void c() {
        f();
        h();
        i();
    }

    public synchronized void d() {
        e();
        g();
        j();
    }

    public void e() {
        this.w.b();
    }

    public void f() {
        this.w.a();
    }

    public void g() {
        this.x.setUserOpened(false);
    }

    public void h() {
        this.x.setUserOpened(true);
    }

    public void i() {
        this.y.b(true);
    }

    public void j() {
        this.y.b(false);
    }

    public synchronized void a(boolean z, boolean z2, boolean z3) {
        this.x.testNativeCrash(z, z2, z3);
    }

    public synchronized void k() {
        this.y.g();
    }

    public boolean l() {
        return this.y.a();
    }

    public void a(Thread thread, Throwable th, boolean z, String str, byte[] bArr, boolean z2) {
        final boolean z3 = z;
        final Thread thread2 = thread;
        final Throwable th2 = th;
        final String str2 = str;
        final byte[] bArr2 = bArr;
        final boolean z4 = z2;
        this.r.a(new Runnable(this) {
            final /* synthetic */ c g;

            public void run() {
                try {
                    an.c("post a throwable %b", Boolean.valueOf(z3));
                    this.g.w.b(thread2, th2, false, str2, bArr2);
                    if (z4) {
                        an.a("clear user datas", new Object[0]);
                        com.tencent.bugly.crashreport.common.info.a.a(this.g.u).C();
                    }
                } catch (Throwable th) {
                    if (!an.b(th)) {
                        th.printStackTrace();
                    }
                    an.e("java catch error: %s", th2.toString());
                }
            }
        });
    }

    public void a(CrashDetailBean crashDetailBean) {
        this.p.e(crashDetailBean);
    }

    public void a(long j) {
        am.a().a(new Thread(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                if (ap.a(this.a.u, "local_crash_lock", 10000)) {
                    List a = this.a.p.a();
                    if (a != null && a.size() > 0) {
                        List arrayList;
                        int size = a.size();
                        if (((long) size) > 100) {
                            arrayList = new ArrayList();
                            Collections.sort(a);
                            for (int i = 0; ((long) i) < 100; i++) {
                                arrayList.add(a.get((size - 1) - i));
                            }
                        } else {
                            arrayList = a;
                        }
                        this.a.p.a(arrayList, 0, false, false, false);
                    }
                    ap.c(this.a.u, "local_crash_lock");
                }
            }
        }, j);
    }

    public void b(long j) {
        this.x.checkUploadRecordCrash(j);
    }
}
