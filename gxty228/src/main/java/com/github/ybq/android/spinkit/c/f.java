package com.github.ybq.android.spinkit.c;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.animation.LinearInterpolator;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.github.ybq.android.spinkit.a.d;
import com.github.ybq.android.spinkit.b.c;
import com.github.ybq.android.spinkit.b.e;

/* compiled from: FoldingCube */
public class f extends com.github.ybq.android.spinkit.b.f {
    private boolean m = false;

    /* compiled from: FoldingCube */
    class a extends c {
        final /* synthetic */ f m;

        a(f fVar) {
            this.m = fVar;
        }

        public ValueAnimator a() {
            float[] fArr = new float[]{0.0f, 0.1f, 0.25f, 0.75f, 0.9f, 1.0f};
            return new d(this).a(fArr, 0, 0, 255, 255, 0, 0).b(fArr, AMapEngineUtils.MIN_LONGITUDE_DEGREE, AMapEngineUtils.MIN_LONGITUDE_DEGREE, 0, 0, 0, 0).c(fArr, 0, 0, 0, 0, 180, 180).a(2400).a(new LinearInterpolator()).a();
        }
    }

    public e[] r() {
        e[] eVarArr = new a[4];
        for (int i = 0; i < eVarArr.length; i++) {
            eVarArr[i] = new a(this);
            eVarArr[i].g((i * 300) - 1200);
        }
        return eVarArr;
    }

    protected void onBoundsChange(Rect rect) {
        int width;
        Rect rect2;
        super.onBoundsChange(rect);
        Rect b = b(rect);
        int min = Math.min(b.width(), b.height());
        if (this.m) {
            min = (int) Math.sqrt((double) ((min * min) / 2));
            width = (b.width() - min) / 2;
            int height = (b.height() - min) / 2;
            rect2 = new Rect(b.left + width, b.top + height, b.right - width, b.bottom - height);
        } else {
            rect2 = b;
        }
        int i = (rect2.left + (min / 2)) + 1;
        width = ((min / 2) + rect2.top) + 1;
        for (min = 0; min < q(); min++) {
            e h = h(min);
            h.a(rect2.left, rect2.top, i, width);
            h.f((float) h.p().right);
            h.g((float) h.p().bottom);
        }
    }

    public void a(Canvas canvas) {
        Rect b = b(getBounds());
        for (int i = 0; i < q(); i++) {
            int save = canvas.save();
            canvas.rotate((float) ((i * 90) + 45), (float) b.centerX(), (float) b.centerY());
            h(i).draw(canvas);
            canvas.restoreToCount(save);
        }
    }
}
