package com.baidu.mobads.command.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

class b extends BroadcastReceiver {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        CharSequence action = intent.getAction();
        if (!TextUtils.isEmpty(action) && "AppActivity_onDestroy".equals(action)) {
            a.h.set(false);
            this.a.c();
        }
    }
}
