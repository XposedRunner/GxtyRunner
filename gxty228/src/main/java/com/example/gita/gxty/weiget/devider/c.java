package com.example.gita.gxty.weiget.devider;

import android.support.annotation.ColorInt;

/* compiled from: Y_DividerBuilder */
public class c {
    private d a;
    private d b;
    private d c;
    private d d;

    public c a(boolean z, @ColorInt int i, float f, float f2, float f3) {
        this.d = new d(z, i, f, f2, f3);
        return this;
    }

    public b a() {
        d dVar;
        d dVar2 = new d(false, -10066330, 0.0f, 0.0f, 0.0f);
        this.a = this.a != null ? this.a : dVar2;
        if (this.b != null) {
            dVar = this.b;
        } else {
            dVar = dVar2;
        }
        this.b = dVar;
        if (this.c != null) {
            dVar = this.c;
        } else {
            dVar = dVar2;
        }
        this.c = dVar;
        if (this.d != null) {
            dVar2 = this.d;
        }
        this.d = dVar2;
        return new b(this.a, this.b, this.c, this.d);
    }
}
