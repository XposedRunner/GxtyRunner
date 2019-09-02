package com.example.gita.gxty.weiget.devider;

import android.content.Context;
import android.util.TypedValue;

/* compiled from: Dp2Px */
public class a {
    public static int a(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }
}
