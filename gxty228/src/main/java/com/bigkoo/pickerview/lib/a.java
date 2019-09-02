package com.bigkoo.pickerview.lib;

import java.util.TimerTask;

/* compiled from: InertiaTimerTask */
final class a extends TimerTask {
    float a = 2.14748365E9f;
    final float b;
    final WheelView c;

    a(WheelView wheelView, float f) {
        this.c = wheelView;
        this.b = f;
    }

    public final void run() {
        if (this.a == 2.14748365E9f) {
            if (Math.abs(this.b) <= 2000.0f) {
                this.a = this.b;
            } else if (this.b > 0.0f) {
                this.a = 2000.0f;
            } else {
                this.a = -2000.0f;
            }
        }
        if (Math.abs(this.a) < 0.0f || Math.abs(this.a) > 20.0f) {
            int i = (int) ((this.a * 10.0f) / 1000.0f);
            this.c.t -= i;
            if (!this.c.p) {
                float f = this.c.l;
                float f2 = ((float) (-this.c.u)) * f;
                float itemsCount = ((float) ((this.c.getItemsCount() - 1) - this.c.u)) * f;
                if (((double) this.c.t) - (((double) f) * 0.3d) < ((double) f2)) {
                    f2 = (float) (this.c.t + i);
                } else if (((double) this.c.t) + (((double) f) * 0.3d) > ((double) itemsCount)) {
                    itemsCount = (float) (this.c.t + i);
                }
                if (((float) this.c.t) <= f2) {
                    this.a = 40.0f;
                    this.c.t = (int) f2;
                } else if (((float) this.c.t) >= itemsCount) {
                    this.c.t = (int) itemsCount;
                    this.a = -40.0f;
                }
            }
            if (this.a < 0.0f) {
                this.a += 20.0f;
            } else {
                this.a -= 20.0f;
            }
            this.c.b.sendEmptyMessage(1000);
            return;
        }
        this.c.a();
        this.c.b.sendEmptyMessage(2000);
    }
}
