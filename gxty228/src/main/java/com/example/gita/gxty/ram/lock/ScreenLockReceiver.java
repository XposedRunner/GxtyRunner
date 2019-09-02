package com.example.gita.gxty.ram.lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.example.gita.gxty.utils.h;

public class ScreenLockReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.intent.action.SCREEN_OFF")) {
            h.b("屏幕已锁定");
            Intent intent2 = new Intent(context, KeepAliveActivity.class);
            intent2.setFlags(AMapEngineUtils.MAX_P20_WIDTH);
            context.startActivity(intent2);
        } else if (action.equals("android.intent.action.USER_PRESENT") || action.equals("android.intent.action.SCREEN_ON")) {
            h.b("屏幕已解锁");
            context.sendBroadcast(new Intent("com.example.gita.gxty.ram.keepalive"));
        }
    }
}
