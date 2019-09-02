package com.example.gita.gxty.weiget.hlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private GestureDetector a = new GestureDetector(new a(this));

    class a extends SimpleOnGestureListener {
        final /* synthetic */ MyScrollView a;

        a(MyScrollView myScrollView) {
            this.a = myScrollView;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (Math.abs(f2) > Math.abs(f)) {
                return true;
            }
            return false;
        }
    }

    public MyScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFadingEdgeLength(0);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent) && this.a.onTouchEvent(motionEvent);
    }
}
