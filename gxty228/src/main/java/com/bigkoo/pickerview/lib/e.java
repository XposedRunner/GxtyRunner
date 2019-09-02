package com.bigkoo.pickerview.lib;

import android.support.graphics.drawable.PathInterpolatorCompat;
import java.util.TimerTask;

/* compiled from: SmoothScrollTimerTask */
final class e extends TimerTask {
    int a = Integer.MAX_VALUE;
    int b = 0;
    int c;
    final WheelView d;

    e(WheelView wheelView, int i) {
        this.d = wheelView;
        this.c = i;
    }

    public final void run() {
        if (this.a == Integer.MAX_VALUE) {
            this.a = this.c;
        }
        this.b = (int) (((float) this.a) * 0.1f);
        if (this.b == 0) {
            if (this.a < 0) {
                this.b = -1;
            } else {
                this.b = 1;
            }
        }
        if (Math.abs(this.a) <= 1) {
            this.d.a();
            this.d.b.sendEmptyMessage(PathInterpolatorCompat.MAX_NUM_POINTS);
            return;
        }
        this.d.t += this.b;
        if (!this.d.p) {
            float f = this.d.l;
            float f2 = ((float) (-this.d.u)) * f;
            f *= (float) ((this.d.getItemsCount() - 1) - this.d.u);
            if (((float) this.d.t) <= f2 || ((float) this.d.t) >= f) {
                this.d.t -= this.b;
                this.d.a();
                this.d.b.sendEmptyMessage(PathInterpolatorCompat.MAX_NUM_POINTS);
                return;
            }
        }
        this.d.b.sendEmptyMessage(1000);
        this.a -= this.b;
    }
}
