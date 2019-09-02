package com.bigkoo.pickerview.lib;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

/* compiled from: LoopViewGestureListener */
final class b extends SimpleOnGestureListener {
    final WheelView a;

    b(WheelView wheelView) {
        this.a = wheelView;
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.a.a(f2);
        return true;
    }
}
