package cn.jpush.android.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import cn.jpush.android.c.c;
import cn.jpush.android.c.g;
import cn.jpush.android.d.e;

public class JPluginPlatformInterface {
    public static final int JPLUGIN_REQUEST_CODE = 10001;
    private c a;

    public JPluginPlatformInterface(Context context) {
        try {
            if (g.a().f(context) == (byte) 2) {
                this.a = new c(context);
            }
        } catch (Throwable th) {
            e.h("JPluginPlatformInterface", "new JPluginPlatformInterface failed:" + th);
        }
    }

    public void onStart(Activity activity) {
        if (this.a != null) {
            this.a.a(activity);
        }
    }

    public void onStop(Activity activity) {
        if (this.a != null) {
            this.a.b(activity);
        }
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (this.a != null) {
            this.a.a(activity, i, i2, intent);
        }
    }
}
