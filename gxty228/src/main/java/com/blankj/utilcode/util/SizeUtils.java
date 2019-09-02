package com.blankj.utilcode.util;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.blankj.utilcode.constant.MemoryConstants;

public final class SizeUtils {

    public interface onGetSizeListener {
        void onGetSize(View view);
    }

    private SizeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int dp2px(float f) {
        return (int) ((Utils.getApp().getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int px2dp(float f) {
        return (int) ((f / Utils.getApp().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(float f) {
        return (int) ((Utils.getApp().getResources().getDisplayMetrics().scaledDensity * f) + 0.5f);
    }

    public static int px2sp(float f) {
        return (int) ((f / Utils.getApp().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static float applyDimension(float f, int i) {
        DisplayMetrics displayMetrics = Utils.getApp().getResources().getDisplayMetrics();
        switch (i) {
            case 0:
                return f;
            case 1:
                return f * displayMetrics.density;
            case 2:
                return f * displayMetrics.scaledDensity;
            case 3:
                return (displayMetrics.xdpi * f) * 0.013888889f;
            case 4:
                return f * displayMetrics.xdpi;
            case 5:
                return (displayMetrics.xdpi * f) * 0.03937008f;
            default:
                return 0.0f;
        }
    }

    public static void forceGetViewSize(final View view, final onGetSizeListener ongetsizelistener) {
        view.post(new Runnable() {
            public void run() {
                if (ongetsizelistener != null) {
                    ongetsizelistener.onGetSize(view);
                }
            }
        });
    }

    public static int getMeasuredWidth(View view) {
        return measureView(view)[0];
    }

    public static int getMeasuredHeight(View view) {
        return measureView(view)[1];
    }

    public static int[] measureView(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            i = MeasureSpec.makeMeasureSpec(i, MemoryConstants.GB);
        } else {
            i = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }
}
