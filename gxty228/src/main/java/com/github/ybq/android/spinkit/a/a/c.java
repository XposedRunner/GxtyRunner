package com.github.ybq.android.spinkit.a.a;

import android.os.Build.VERSION;
import android.view.animation.Interpolator;

/* compiled from: PathInterpolatorCompat */
public class c {
    public static Interpolator a(float f, float f2, float f3, float f4) {
        if (VERSION.SDK_INT >= 21) {
            return d.a(f, f2, f3, f4);
        }
        return e.a(f, f2, f3, f4);
    }
}
