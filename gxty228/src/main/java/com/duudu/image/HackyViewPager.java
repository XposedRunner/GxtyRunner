package com.duudu.image;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class HackyViewPager extends ViewPager {
    private boolean a = false;

    public HackyViewPager(Context context) {
        super(context);
    }

    public HackyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!this.a) {
            try {
                z = super.onInterceptTouchEvent(motionEvent);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.a && super.onTouchEvent(motionEvent);
    }

    public void setLocked(boolean z) {
        this.a = z;
    }
}
