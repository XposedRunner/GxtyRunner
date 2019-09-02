package cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.d.b.a;
import cn.jiguang.d.d.g;
import cn.jiguang.e.d;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmReceiver";

    public void onReceive(Context context, Intent intent) {
        d.c(TAG, "onReceive");
        if (!JCoreInterface.init(context.getApplicationContext(), false)) {
            return;
        }
        if (cn.jiguang.d.a.d.j(context)) {
            a.a(context, false);
            return;
        }
        if (VERSION.SDK_INT >= 19) {
            cn.jiguang.g.a.j(context.getApplicationContext());
        }
        g.a();
        g.b(context);
    }
}
