package com.github.ybq.android.spinkit.c;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.github.ybq.android.spinkit.a.d;
import com.github.ybq.android.spinkit.b.e;
import com.github.ybq.android.spinkit.b.f;

/* compiled from: ThreeBounce */
public class i extends f {

    /* compiled from: ThreeBounce */
    class a extends com.github.ybq.android.spinkit.b.a {
        final /* synthetic */ i m;

        a(i iVar) {
            this.m = iVar;
        }

        public ValueAnimator a() {
            float[] fArr = new float[]{0.0f, 0.4f, 0.8f, 1.0f};
            return new d(this).a(fArr, 0.0f, 1.0f, 0.0f, 0.0f).a(1400).a(fArr).a();
        }
    }

    public e[] r() {
        return new e[]{new a(this), new a(this), new a(this)};
    }

    public void a(e... eVarArr) {
        super.a(eVarArr);
        eVarArr[1].g((int) GlMapUtil.DEVICE_DISPLAY_DPI_NORMAL);
        eVarArr[2].g((int) GlMapUtil.DEVICE_DISPLAY_DPI_HIGH);
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Rect b = b(rect);
        int width = b.width() / 8;
        int centerY = b.centerY() - width;
        int centerY2 = b.centerY() + width;
        for (int i = 0; i < q(); i++) {
            int width2 = ((b.width() * i) / 3) + b.left;
            h(i).a(width2, centerY, (width * 2) + width2, centerY2);
        }
    }
}
