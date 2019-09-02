package com.example.gita.gxty.receiver;

import android.content.Context;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;
import com.example.gita.gxty.utils.h;

public class MyJPushMessageReceiver extends JPushMessageReceiver {
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        h.b(jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }

    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        h.b(jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        h.b(jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }

    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        h.b(jPushMessage);
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }
}
