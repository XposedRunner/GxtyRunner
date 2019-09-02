package com.github.ybq.android.spinkit.c;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.support.v4.app.NotificationManagerCompat;
import android.view.animation.LinearInterpolator;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.github.ybq.android.spinkit.a.d;
import com.github.ybq.android.spinkit.b.e;
import com.github.ybq.android.spinkit.b.f;

/* compiled from: ChasingDots */
public class a extends f {

    /* compiled from: ChasingDots */
    class a extends com.github.ybq.android.spinkit.b.a {
        final /* synthetic */ a m;

        a(a aVar) {
            this.m = aVar;
        }

        public ValueAnimator a() {
            float[] fArr = new float[]{0.0f, 0.5f, 1.0f};
            return new d(this).a(fArr, 0.0f, 1.0f, 0.0f).a(2000).a(fArr).a();
        }
    }

    public e[] r() {
        return new e[]{new a(this), new a(this)};
    }

    public void a(e... eVarArr) {
        super.a(eVarArr);
        eVarArr[1].g((int) NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
    }

    public ValueAnimator a() {
        return new d(this).d(new float[]{0.0f, 1.0f}, 0, SpatialRelationUtil.A_CIRCLE_DEGREE).a(2000).a(new LinearInterpolator()).a();
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Rect b = b(rect);
        int width = (int) (((float) b.width()) * 0.6f);
        h(0).a(b.left, b.top, b.right, b.top + width);
        h(1).a(b.left, b.bottom - width, b.right, b.bottom);
    }
}
