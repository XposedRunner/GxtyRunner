package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy.a;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.proguard.ac;
import com.tencent.bugly.proguard.an;

/* compiled from: BUGLY */
public class CrashModule extends a {
    public static final int MODULE_ID = 1004;
    private static int c = 0;
    private static boolean d = false;
    private static CrashModule e = new CrashModule();
    private long a;
    private a b;

    public static CrashModule getInstance() {
        e.id = 1004;
        return e;
    }

    public static boolean hasInitialized() {
        return d;
    }

    public synchronized void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
        if (context != null) {
            if (!d) {
                an.a("Initializing crash module.", new Object[0]);
                ac a = ac.a();
                int i = c + 1;
                c = i;
                a.a(1004, i);
                d = true;
                CrashReport.setContext(context);
                a(context, buglyStrategy);
                c a2 = c.a(1004, context, z, this.b, null, null);
                a2.f();
                if (buglyStrategy == null || buglyStrategy.isEnableNativeCrashMonitor()) {
                    a2.h();
                } else {
                    an.a("[crash] Closed native crash monitor!", new Object[0]);
                    a2.g();
                }
                if (buglyStrategy == null || buglyStrategy.isEnableANRCrashMonitor()) {
                    a2.i();
                } else {
                    an.a("[crash] Closed ANR monitor!", new Object[0]);
                    a2.j();
                }
                long appReportDelay = buglyStrategy != null ? buglyStrategy.getAppReportDelay() : 0;
                a2.a(appReportDelay);
                a2.b(appReportDelay);
                d.a(context);
                BuglyBroadcastReceiver instance = BuglyBroadcastReceiver.getInstance();
                instance.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
                instance.register(context);
                a = ac.a();
                i = c - 1;
                c = i;
                a.a(1004, i);
            }
        }
    }

    private synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy != null) {
            Object libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
            if (!TextUtils.isEmpty(libBuglySOFilePath)) {
                com.tencent.bugly.crashreport.common.info.a.a(context).r = libBuglySOFilePath;
                an.a("setted libBugly.so file path :%s", libBuglySOFilePath);
            }
            if (buglyStrategy.getCrashHandleCallback() != null) {
                this.b = buglyStrategy.getCrashHandleCallback();
                an.a("setted CrashHanldeCallback", new Object[0]);
            }
            if (buglyStrategy.getAppReportDelay() > 0) {
                this.a = buglyStrategy.getAppReportDelay();
                an.a("setted delay: %d", Long.valueOf(this.a));
            }
        }
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            c a = c.a();
            if (a != null) {
                a.a(strategyBean);
            }
        }
    }

    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}
