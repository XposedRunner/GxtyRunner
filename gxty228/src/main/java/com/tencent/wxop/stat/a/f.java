package com.tencent.wxop.stat.a;

import android.support.v4.view.PointerIconCompat;

public enum f {
    PAGE_VIEW(1),
    SESSION_ENV(2),
    ERROR(3),
    CUSTOM(1000),
    ADDITION(1001),
    MONITOR_STAT(1002),
    MTA_GAME_USER(PointerIconCompat.TYPE_HELP),
    NETWORK_MONITOR(1004),
    NETWORK_DETECTOR(1005);
    
    private int j;

    private f(int i) {
        this.j = i;
    }

    public final int a() {
        return this.j;
    }
}
