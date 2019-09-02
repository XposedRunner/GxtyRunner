package com.github.ybq.android.spinkit.c;

import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.a.d;
import com.github.ybq.android.spinkit.b.e;

/* compiled from: Circle */
public class b extends com.github.ybq.android.spinkit.b.b {

    /* compiled from: Circle */
    class a extends com.github.ybq.android.spinkit.b.a {
        final /* synthetic */ b m;

        a(b bVar) {
            this.m = bVar;
        }

        public ValueAnimator a() {
            float[] fArr = new float[]{0.0f, 0.5f, 1.0f};
            return new d(this).a(fArr, 0.0f, 1.0f, 0.0f).a(1200).a(fArr).a();
        }
    }

    public e[] r() {
        e[] eVarArr = new a[12];
        for (int i = 0; i < eVarArr.length; i++) {
            eVarArr[i] = new a(this);
            eVarArr[i].g((i * 100) - 1200);
        }
        return eVarArr;
    }
}
