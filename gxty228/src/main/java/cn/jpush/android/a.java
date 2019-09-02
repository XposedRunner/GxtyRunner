package cn.jpush.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import cn.jiguang.api.MultiSpHelper;
import cn.jiguang.api.SdkType;
import cn.jpush.android.d.e;
import cn.jpush.client.android.BuildConfig;
import java.util.concurrent.atomic.AtomicBoolean;

public final class a {
    public static final String a = SdkType.JPUSH.name();
    public static int b;
    public static String c;
    public static String d;
    public static Context e;
    public static boolean f = false;
    private static AtomicBoolean g = new AtomicBoolean(false);

    public static synchronized boolean a(Context context) {
        boolean z = true;
        synchronized (a.class) {
            if (!g.get()) {
                if (cn.jpush.android.d.a.e(context)) {
                    e.c("JPushGlobal", "action:init - Service");
                    c = context.getPackageName();
                    e = context.getApplicationContext();
                    try {
                        e.c("JPushGlobal", "action - handleUpgrade");
                        String str = BuildConfig.VERSION_NAME;
                        CharSequence string = MultiSpHelper.getString(context, "jpush_sdk_version", "");
                        e.c("JPushGlobal", "jpush sdk version - curVersion:" + str + ",oldVersion:" + string);
                        if (str.equals(string)) {
                            e.c("JPushGlobal", "It's same push version");
                        } else if (TextUtils.isEmpty(string)) {
                            e.c("JPushGlobal", "It's a new push version");
                        }
                        MultiSpHelper.commitString(context, "jpush_sdk_version", str);
                    } catch (Throwable th) {
                        e.i("JPushGlobal", "handleUpgrade failed:" + th);
                    }
                    ApplicationInfo b = b(context);
                    if (b == null) {
                        e.j("JPushGlobal", "JPush cannot be initialized completely due to NULL appInfo.");
                        z = false;
                    } else {
                        b = b.icon;
                        if (b == 0) {
                            e.j("JPushGlobal", "metadata: Can not get Application icon, you will be not able to show notification due to the application icon is null.");
                        }
                        try {
                            d = context.getPackageManager().getApplicationLabel(b).toString();
                        } catch (Throwable th2) {
                            e.d("JPushGlobal", "get mApplicationName error:" + th2.getMessage());
                        }
                        g.set(true);
                    }
                } else {
                    e.g("JPushGlobal", "invalide running, please check your manifest configs");
                    z = false;
                }
            }
        }
        return z;
    }

    private static ApplicationInfo b(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (Throwable th) {
            e.f("JPushGlobal", "Unexpected: failed to get current application info", th);
            return null;
        }
    }
}
