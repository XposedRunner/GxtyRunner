package com.github.ybq.android.spinkit.c;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.github.ybq.android.spinkit.a.d;
import com.github.ybq.android.spinkit.b.e;
import com.github.ybq.android.spinkit.b.f;

/* compiled from: CubeGrid */
public class c extends f {

    /* compiled from: CubeGrid */
    class a extends com.github.ybq.android.spinkit.b.c {
        final /* synthetic */ c m;

        a(c cVar) {
            this.m = cVar;
        }

        public ValueAnimator a() {
            float[] fArr = new float[]{0.0f, 0.35f, 0.7f, 1.0f};
            return new d(this).a(fArr, 1.0f, 0.0f, 1.0f, 1.0f).a(1300).a(fArr).a();
        }
    }

    public e[] r() {
        int[] iArr = new int[]{200, 300, 400, 100, 200, 300, 0, 100, 200};
        e[] eVarArr = new a[9];
        for (int i = 0; i < eVarArr.length; i++) {
            eVarArr[i] = new a(this);
            eVarArr[i].g(iArr[i]);
        }
        return eVarArr;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Rect b = b(rect);
        int width = (int) (((float) b.width()) * 0.33f);
        int height = (int) (((float) b.height()) * 0.33f);
        for (int i = 0; i < q(); i++) {
            int i2 = ((i % 3) * width) + b.left;
            int i3 = ((i / 3) * height) + b.top;
            h(i).a(i2, i3, i2 + width, i3 + height);
        }
    }
}
