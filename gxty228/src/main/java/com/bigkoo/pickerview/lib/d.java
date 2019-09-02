package com.bigkoo.pickerview.lib;

/* compiled from: OnItemSelectedRunnable */
final class d implements Runnable {
    final WheelView a;

    d(WheelView wheelView) {
        this.a = wheelView;
    }

    public final void run() {
        this.a.c.a(this.a.getCurrentItem());
    }
}
