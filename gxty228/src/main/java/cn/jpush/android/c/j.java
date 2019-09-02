package cn.jpush.android.c;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.d.e;
import com.xiaomi.mipush.sdk.MiPushClient;

public final class j extends e {
    public j(Context context) {
        this.a = i.a(context, "XIAOMI_APPKEY");
        this.b = i.a(context, "XIAOMI_APPID");
    }

    protected final void a(Context context) {
        if (context == null) {
            e.i("PluginXiaomiPlatformAction", "Register - context was null");
            return;
        }
        try {
            e.a("PluginXiaomiPlatformAction", "xiaomiAppKey:" + this.a + ",xiaomiAppid:" + this.b);
            if (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.b)) {
                e.j("PluginXiaomiPlatformAction", "xiaomi sdk appkey or appid was empty,please check your manifest config");
            } else {
                MiPushClient.registerPush(context, this.b, this.a);
            }
        } catch (Throwable th) {
            e.e("PluginXiaomiPlatformAction", "#unexpected - register error:", th);
        }
    }

    protected final void c(Context context) {
        e.c("PluginXiaomiPlatformAction", "stopPush");
        if (context == null) {
            e.i("PluginXiaomiPlatformAction", "context was null");
        } else {
            MiPushClient.disablePush(context);
        }
    }

    protected final void b(Context context) {
        e.c("PluginXiaomiPlatformAction", "resumePush");
        if (context == null) {
            e.i("PluginXiaomiPlatformAction", "context was null");
        } else {
            MiPushClient.enablePush(context);
        }
    }
}
