package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: TimeUpdateStrategy */
public class jy extends jz {
    private int b;
    private long c;
    private String d;
    private Context e;

    public jy(Context context, int i, String str, jz jzVar) {
        super(jzVar);
        this.b = i;
        this.d = str;
        this.e = context;
    }

    protected boolean a() {
        if (this.c == 0) {
            this.c = a(this.d);
        }
        if (System.currentTimeMillis() - this.c < ((long) this.b)) {
            return false;
        }
        return true;
    }

    public void a(boolean z) {
        super.a(z);
        if (z) {
            a(this.d, System.currentTimeMillis());
        }
    }

    private void a(String str, long j) {
        this.c = j;
        gx.a(this.e, str, String.valueOf(j));
    }

    private long a(String str) {
        Object a = gx.a(this.e, str);
        if (TextUtils.isEmpty(a)) {
            return 0;
        }
        return Long.parseLong(a);
    }
}
