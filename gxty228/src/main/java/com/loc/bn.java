package com.loc;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: TimeUpdateStrategy */
public final class bn extends bo {
    private int b;
    private long c;
    private String d;
    private Context e;

    public bn(Context context, int i, String str, bo boVar) {
        super(boVar);
        this.b = i;
        this.d = str;
        this.e = context;
    }

    public final void a(boolean z) {
        super.a(z);
        if (z) {
            String str = this.d;
            long currentTimeMillis = System.currentTimeMillis();
            this.c = currentTimeMillis;
            h.a(this.e, str, String.valueOf(currentTimeMillis));
        }
    }

    protected final boolean a() {
        long j = 0;
        if (this.c == 0) {
            Object a = h.a(this.e, this.d);
            if (!TextUtils.isEmpty(a)) {
                j = Long.parseLong(a);
            }
            this.c = j;
        }
        return System.currentTimeMillis() - this.c >= ((long) this.b);
    }
}
