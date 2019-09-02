package cn.jiguang.a;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import cn.jiguang.a.a.d.b;
import cn.jiguang.api.JAnalyticsAction;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.e.d;
import com.tencent.tauth.AuthActivity;

public final class a {
    public static JAnalyticsAction a;
    public static boolean b = true;
    private static boolean c;

    public static void a(Context context) {
        d.c("JCoreAnalytics", "JCoreAnalytics ActivityLifecycle init");
        if (!c) {
            if (context == null) {
                d.g("JCoreAnalytics", "JAnalytics init context is null");
                return;
            }
            try {
                Context applicationContext = context.getApplicationContext();
                if ((applicationContext instanceof Application) && VERSION.SDK_INT >= 14) {
                    String c = cn.jiguang.d.b.a.c(context);
                    String packageName = context.getPackageName();
                    d.c("JCoreAnalytics", "registerActivityLifecycleCallbacks in main process,packageName:" + packageName + ",currentProcessName:" + c);
                    if (c == null || packageName == null || !context.getPackageName().equals(c)) {
                        d.c("JCoreAnalytics", "need not registerActivityLifecycleCallbacks in other process :" + c);
                    } else {
                        b = false;
                        ((Application) applicationContext).registerActivityLifecycleCallbacks(new b());
                    }
                }
            } catch (Throwable th) {
                b = true;
            }
            c = true;
        }
    }

    public static void a(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(AuthActivity.ACTION_KEY, "cn.jpush.android.intent.REPORT");
        bundle.putString("report", str);
        bundle.putString("report.extra.info", str2);
        JCoreInterface.sendAction(context, cn.jiguang.d.a.a, bundle);
    }
}
