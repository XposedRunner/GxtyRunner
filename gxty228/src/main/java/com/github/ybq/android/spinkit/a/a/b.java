package com.github.ybq.android.spinkit.a.a;

import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;

/* compiled from: KeyFrameInterpolator */
public class b implements Interpolator {
    private TimeInterpolator a;
    private float[] b;

    public static b a(float... fArr) {
        b bVar = new b(a.a());
        bVar.b(fArr);
        return bVar;
    }

    public b(TimeInterpolator timeInterpolator) {
        this.a = timeInterpolator;
    }

    public void b(float... fArr) {
        this.b = fArr;
    }

    public synchronized float getInterpolation(float f) {
        float interpolation;
        if (this.b.length > 1) {
            for (int i = 0; i < this.b.length - 1; i++) {
                float f2 = this.b[i];
                float f3 = this.b[i + 1];
                float f4 = f3 - f2;
                if (f >= f2 && f <= f3) {
                    interpolation = (this.a.getInterpolation((f - f2) / f4) * f4) + f2;
                    break;
                }
            }
        }
        interpolation = this.a.getInterpolation(f);
        return interpolation;
    }
}
