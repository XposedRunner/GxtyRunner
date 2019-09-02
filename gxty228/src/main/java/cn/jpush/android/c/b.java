package cn.jpush.android.c;

import android.content.Context;
import cn.jpush.android.d.a;
import cn.jpush.android.d.e;

public final class b extends e {
    private a c;

    protected b(Context context) {
        if (context == null) {
            e.h("PluginHuaweiPlatformAction", "context was null");
        }
        this.c = new a(context, null);
        this.a = "huawei_appid";
        Object e = a.e(context, "com.huawei.hms.client.appid");
        if (e != null) {
            this.b = e.toString();
        }
        e.e("PluginHuaweiPlatformAction", "huawei appId is " + this.b);
    }

    protected final void a(Context context) {
        try {
            this.c.a.connect();
        } catch (Throwable th) {
            e.j("PluginHuaweiPlatformAction", "register e:" + th);
        }
    }

    protected final void b(Context context) {
        try {
            this.c.a.connect();
        } catch (Throwable th) {
            e.c("PluginHuaweiPlatformAction", "resumePush e:" + th);
        }
    }
}
