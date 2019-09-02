package com.github.ybq.android.spinkit.c;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.github.ybq.android.spinkit.a.d;
import com.github.ybq.android.spinkit.b.c;

/* compiled from: RotatingPlane */
public class h extends c {
    protected void onBoundsChange(Rect rect) {
        a(b(rect));
    }

    public ValueAnimator a() {
        float[] fArr = new float[]{0.0f, 0.5f, 1.0f};
        return new d(this).b(fArr, 0, AMapEngineUtils.MIN_LONGITUDE_DEGREE, AMapEngineUtils.MIN_LONGITUDE_DEGREE).c(fArr, 0, 0, AMapEngineUtils.MIN_LONGITUDE_DEGREE).a(1200).a(fArr).a();
    }

    public void a(Canvas canvas, Paint paint) {
        super.a(canvas, paint);
    }
}
