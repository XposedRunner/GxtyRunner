package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: MobileUpdateStrategy */
public class jw extends jz {
    private Context b;
    private boolean c;
    private int d;
    private int e;

    public jw(Context context, boolean z, int i, int i2) {
        this.b = context;
        this.c = z;
        this.d = i;
        this.e = i2;
    }

    protected boolean a() {
        if (gd.q(this.b) == 1) {
            return true;
        }
        if (!this.c) {
            return false;
        }
        Object a = gx.a(this.b, "iKey");
        if (TextUtils.isEmpty(a)) {
            return true;
        }
        String[] split = a.split("\\|");
        if (split == null || split.length < 2) {
            gx.b(this.b, "iKey");
            return true;
        }
        if (!gl.a(System.currentTimeMillis(), "yyyyMMdd").equals(split[0]) || Integer.parseInt(split[1]) < this.e) {
            return true;
        }
        return false;
    }

    public int b() {
        int i;
        if (gd.q(this.b) == 1 || this.d <= 0) {
            i = Integer.MAX_VALUE;
        } else {
            i = this.d;
        }
        if (this.a != null) {
            return Math.max(i, this.a.b());
        }
        return i;
    }

    public void a(int i) {
        if (gd.q(this.b) != 1) {
            String a = gl.a(System.currentTimeMillis(), "yyyyMMdd");
            Object a2 = gx.a(this.b, "iKey");
            if (!TextUtils.isEmpty(a2)) {
                String[] split = a2.split("\\|");
                if (split == null || split.length < 2) {
                    gx.b(this.b, "iKey");
                } else if (a.equals(split[0])) {
                    i += Integer.parseInt(split[1]);
                }
            }
            gx.a(this.b, "iKey", a + "|" + i);
        }
    }
}
