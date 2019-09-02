package cn.jpush.android.c;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.d.e;

public final class c {
    protected Activity a;
    a b;

    public c(Context context) {
        if (context == null) {
            e.h("PluginHuaweiPushInterface", "context was null");
        } else if (i.a(context) == (byte) 2) {
            this.b = new a(context, this);
        }
    }

    public final void a(Activity activity) {
        e.e("PluginHuaweiPushInterface", "onStart mActivity:" + this.a + ",activity:" + activity);
        if (activity == null) {
            e.h("PluginHuaweiPushInterface", "activity was null");
            return;
        }
        try {
            if (this.b.a != null) {
                this.a = activity;
                if (!JPushInterface.isPushStopped(activity.getApplicationContext())) {
                    this.b.a.connect();
                }
            }
        } catch (Throwable th) {
            e.i("PluginHuaweiPushInterface", "onStart - error:" + th);
        }
    }

    public final void b(Activity activity) {
        e.e("PluginHuaweiPushInterface", "onStop mActivity:" + this.a + ",activity:" + activity);
        if (activity == null) {
            e.h("PluginHuaweiPushInterface", "activity was null");
        }
        this.a = null;
    }

    public final void a(Context context, int i, int i2, Intent intent) {
        e.e("PluginHuaweiPushInterface", "onActivityResult:" + i + ",resultCode:" + i2);
        if (i != 10001) {
            return;
        }
        if (context != null) {
            int intExtra;
            if (intent != null) {
                try {
                    intExtra = intent.getIntExtra("intent.extra.RESULT", 0);
                } catch (Throwable th) {
                    e.h("PluginHuaweiPushInterface", "onActivityResult error:" + th);
                    return;
                }
            }
            intExtra = -1;
            e.f("PluginHuaweiPushInterface", "onActivityResult,intent.extra.RESULT value" + intExtra);
            if (intExtra != 0) {
                g.a().a(context, null);
                if (intExtra == 13) {
                    e.f("PluginHuaweiPushInterface", "user cancled");
                    return;
                } else if (intExtra == 8) {
                    e.f("PluginHuaweiPushInterface", "huawei sdk internal error");
                    return;
                } else {
                    e.f("PluginHuaweiPushInterface", "unknow error:" + intExtra);
                    return;
                }
            } else if (this.b.a == null || this.b.a.isConnecting() || this.b.a.isConnected()) {
                e.f("PluginHuaweiPushInterface", "onActivityResult call connect failed huaweiApiClient:" + this.b.a);
                return;
            } else {
                this.b.a.connect();
                return;
            }
        }
        e.h("PluginHuaweiPushInterface", "onActivityResult activity was null");
    }
}
