package cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.jpush.android.a.a;
import cn.jpush.android.api.JPushMessage;

public abstract class JPushMessageReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        a.a().a(context.getApplicationContext(), this, intent);
    }

    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
    }

    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
    }

    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
    }

    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
    }
}
