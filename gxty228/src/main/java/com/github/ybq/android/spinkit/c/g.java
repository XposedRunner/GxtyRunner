package com.github.ybq.android.spinkit.c;

import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.a.d;
import com.github.ybq.android.spinkit.b.a;

/* compiled from: Pulse */
public class g extends a {
    public ValueAnimator a() {
        float[] fArr = new float[]{0.0f, 1.0f};
        return new d(this).a(fArr, 0.0f, 1.0f).a(fArr, 255, 0).a(1000).a(fArr).a();
    }
}
