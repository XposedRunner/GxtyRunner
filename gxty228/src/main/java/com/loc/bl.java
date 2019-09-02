package com.loc;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: MobileUpdateStrategy */
public final class bl extends bo {
    private Context b;
    private boolean c;
    private int d;
    private int e;

    public bl(Context context, boolean z, int i, int i2) {
        this.b = context;
        this.c = z;
        this.d = i;
        this.e = i2;
    }

    public final void a(int i) {
        if (df.q(this.b) != 1) {
            String a = dl.a(System.currentTimeMillis(), "yyyyMMdd");
            Object a2 = h.a(this.b, "iKey");
            if (!TextUtils.isEmpty(a2)) {
                String[] split = a2.split("\\|");
                if (split == null || split.length < 2) {
                    h.b(this.b, "iKey");
                } else if (a.equals(split[0])) {
                    i += Integer.parseInt(split[1]);
                }
            }
            h.a(this.b, "iKey", a + "|" + i);
        }
    }

    protected final boolean a() {
        if (df.q(this.b) == 1) {
            return true;
        }
        if (!this.c) {
            return false;
        }
        Object a = h.a(this.b, "iKey");
        if (TextUtils.isEmpty(a)) {
            return true;
        }
        String[] split = a.split("\\|");
        if (split == null || split.length < 2) {
            h.b(this.b, "iKey");
            return true;
        }
        return !dl.a(System.currentTimeMillis(), "yyyyMMdd").equals(split[0]) || Integer.parseInt(split[1]) < this.e;
    }

    public final int b() {
        int i = (df.q(this.b) == 1 || this.d <= 0) ? Integer.MAX_VALUE : this.d;
        return this.a != null ? Math.max(i, this.a.b()) : i;
    }
}
