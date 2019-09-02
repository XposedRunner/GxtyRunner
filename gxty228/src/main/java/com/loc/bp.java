package com.loc;

import android.content.Context;

/* compiled from: WiFiUplateStrategy */
public final class bp extends bo {
    private Context b;
    private boolean c = false;

    public bp(Context context) {
        this.b = context;
        this.c = false;
    }

    protected final boolean a() {
        return df.q(this.b) == 1 || this.c;
    }
}
