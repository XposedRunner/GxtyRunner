package com.amap.api.mapcore.util;

import android.content.Context;

/* compiled from: WiFiUplateStrategy */
public class ka extends jz {
    private Context b;
    private boolean c = false;

    public ka(Context context, boolean z) {
        this.b = context;
        this.c = z;
    }

    protected boolean a() {
        return gd.q(this.b) == 1 || this.c;
    }
}
