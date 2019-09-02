package cn.jpush.android.c;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.d.e;
import com.meizu.cloud.pushsdk.PushManager;

public final class d extends e {
    public d(Context context) {
        if (context == null) {
            e.h("PluginMeizuPlateformAction", "context was null");
        }
        this.a = i.a(context, "MEIZU_APPKEY");
        this.b = i.a(context, "MEIZU_APPID");
        e.c("PluginMeizuPlateformAction", "meizuAppKey:" + String.valueOf(this.a) + ",meizuAppId:" + String.valueOf(this.b));
    }

    protected final void a(Context context) {
        if (context == null) {
            e.i("PluginMeizuPlateformAction", "Register - context was null");
            return;
        }
        try {
            if (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.b)) {
                e.j("PluginMeizuPlateformAction", "meizu sdk appkey or appid was empty,please check your manifest config");
            } else {
                PushManager.register(context, this.b, this.a);
            }
        } catch (Throwable th) {
            e.e("PluginMeizuPlateformAction", "#unexpected - register error:", th);
        }
    }

    protected final void b(Context context) {
    }

    protected final void c(Context context) {
    }
}
