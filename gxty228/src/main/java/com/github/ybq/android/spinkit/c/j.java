package com.github.ybq.android.spinkit.c;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.github.ybq.android.spinkit.a.d;
import com.github.ybq.android.spinkit.b.c;
import com.github.ybq.android.spinkit.b.e;
import com.github.ybq.android.spinkit.b.f;

/* compiled from: WanderingCubes */
public class j extends f {

    /* compiled from: WanderingCubes */
    class a extends c {
        final /* synthetic */ j m;

        a(j jVar) {
            this.m = jVar;
        }

        public ValueAnimator a() {
            float[] fArr = new float[]{0.0f, 0.25f, 0.5f, 0.51f, 0.75f, 1.0f};
            return new d(this).d(fArr, 0, -90, -179, AMapEngineUtils.MIN_LONGITUDE_DEGREE, -270, -360).b(fArr, 0.0f, 0.75f, 0.75f, 0.75f, 0.0f, 0.0f).c(fArr, 0.0f, 0.0f, 0.75f, 0.75f, 0.75f, 0.0f).a(fArr, 1.0f, 0.5f, 1.0f, 1.0f, 0.5f, 1.0f).a(1800).a(fArr).a();
        }
    }

    public e[] r() {
        return new e[]{new a(this), new a(this)};
    }

    public void a(e... eVarArr) {
        super.a(eVarArr);
        eVarArr[1].g(-900);
    }

    protected void onBoundsChange(Rect rect) {
        Rect b = b(rect);
        super.onBoundsChange(b);
        for (int i = 0; i < q(); i++) {
            h(i).a(b.left, b.top, b.left + (b.width() / 4), b.top + (b.height() / 4));
        }
    }
}
