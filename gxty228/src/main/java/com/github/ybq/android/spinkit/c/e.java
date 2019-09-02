package com.github.ybq.android.spinkit.c;

import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.a.d;
import com.github.ybq.android.spinkit.b.b;

/* compiled from: FadingCircle */
public class e extends b {

    /* compiled from: FadingCircle */
    class a extends com.github.ybq.android.spinkit.b.a {
        final /* synthetic */ e m;

        a(e eVar) {
            this.m = eVar;
        }

        public ValueAnimator a() {
            float[] fArr = new float[]{0.0f, 0.4f, 1.0f};
            return new d(this).a(fArr, 0, 255, 0).a(1200).a(fArr).a();
        }
    }

    public com.github.ybq.android.spinkit.b.e[] r() {
        com.github.ybq.android.spinkit.b.e[] eVarArr = new a[12];
        for (int i = 0; i < eVarArr.length; i++) {
            eVarArr[i] = new a(this);
            eVarArr[i].g(i * 100);
        }
        return eVarArr;
    }
}
