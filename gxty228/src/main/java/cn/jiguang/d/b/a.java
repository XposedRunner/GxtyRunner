package cn.jiguang.d.b;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.e.d;
import cn.jpush.android.service.AlarmReceiver;
import cn.jpush.android.service.PushReceiver;
import cn.jpush.android.service.PushService;
import java.io.File;

public final class a {
    public static int a = -1;

    public static void a(Context context) {
        try {
            new c(context).start();
        } catch (Throwable th) {
            d.i("JCoreServiceUtils", "create check file failed, error:" + th);
        }
    }

    public static void a(Context context, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ComponentName componentName = new ComponentName(context.getApplicationContext(), PushReceiver.class);
            ComponentName componentName2 = new ComponentName(context.getApplicationContext(), AlarmReceiver.class);
            if (z) {
                d.d("JCoreServiceUtils", "set Push/Alarm Receiver enabled");
                packageManager.setComponentEnabledSetting(componentName, 1, 1);
                packageManager.setComponentEnabledSetting(componentName2, 1, 1);
                cn.jiguang.g.a.j(context.getApplicationContext());
                return;
            }
            d.d("JCoreServiceUtils", "set Push/Alarm Receiver disabled");
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
            packageManager.setComponentEnabledSetting(componentName2, 2, 1);
            cn.jiguang.g.a.k(context.getApplicationContext());
            cn.jiguang.g.a.r(context);
        } catch (Throwable th) {
            d.g("JCoreServiceUtils", "setPushReceiverEnable error:" + th.getMessage());
        }
    }

    public static boolean a() {
        return a == 0;
    }

    public static void b() {
        if (!cn.jiguang.g.a.p(cn.jiguang.d.a.e)) {
            a = 0;
        } else if (b(cn.jiguang.d.a.e)) {
            try {
                new b().start();
            } catch (Throwable th) {
                d.h("JCoreServiceUtils", "create checkCommonService thread error:" + th);
                a = 1;
            }
        } else {
            try {
                Object c = c(cn.jiguang.d.a.e);
                CharSequence h = cn.jiguang.g.a.h(cn.jiguang.d.a.e, PushService.class.getCanonicalName());
                if (!(TextUtils.isEmpty(c) || TextUtils.isEmpty(h) || !c.equals(h))) {
                    d.a("JCoreServiceUtils", "is service process ,save service file");
                    a(cn.jiguang.d.a.e);
                }
            } catch (Throwable th2) {
            }
            d.c("JCoreServiceUtils", "need not checkCommonService in not main process");
        }
    }

    public static boolean b(Context context) {
        String c = c(context);
        String packageName = context.getPackageName();
        return (c == null || packageName == null || !packageName.equals(c)) ? false : true;
    }

    public static String c(Context context) {
        try {
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        } catch (Throwable th) {
            d.h("JCoreServiceUtils", "#unexcepted - getCurProcessName failed:" + th.getMessage());
        }
        return "";
    }

    public static boolean c() {
        return a == 1 || a == -1;
    }

    static /* synthetic */ void d(Context context) {
        int i = 20;
        while (i > 0) {
            try {
                File file = new File(context.getFilesDir(), ".servicesaveFile");
                d.a("JCoreServiceUtils", "checkServiceFile timeout = " + i);
                if (file.exists()) {
                    a = 1;
                    break;
                } else {
                    i--;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                a = 1;
            } catch (Throwable th) {
                d.c("JCoreServiceUtils", "checkServiceFile exception...");
                a = 1;
            }
        }
        if (a == -1) {
            a = 0;
            JCoreInterface.register(cn.jiguang.d.a.e);
        }
        d.c("JCoreServiceUtils", "JPush init canRunPushService :" + a);
    }
}
