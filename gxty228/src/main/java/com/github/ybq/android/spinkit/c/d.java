package com.github.ybq.android.spinkit.c;

import android.animation.ValueAnimator;
import android.support.v4.app.NotificationManagerCompat;
import com.github.ybq.android.spinkit.b.e;
import com.github.ybq.android.spinkit.b.f;
import com.tencent.bugly.beta.tinker.TinkerReport;

/* compiled from: DoubleBounce */
public class d extends f {

    /* compiled from: DoubleBounce */
    class a extends com.github.ybq.android.spinkit.b.a {
        final /* synthetic */ d m;

        public a(d dVar) {
            this.m = dVar;
            setAlpha(TinkerReport.KEY_APPLIED_PACKAGE_CHECK_APK_TINKER_ID_NOT_FOUND);
        }

        public ValueAnimator a() {
            float[] fArr = new float[]{0.0f, 0.5f, 1.0f};
            return new com.github.ybq.android.spinkit.a.d(this).a(fArr, 0.0f, 1.0f, 0.0f).a(2000).a(fArr).a();
        }
    }

    public e[] r() {
        return new e[]{new a(this), new a(this)};
    }

    public void a(e... eVarArr) {
        super.a(eVarArr);
        eVarArr[1].g((int) NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
    }
}
