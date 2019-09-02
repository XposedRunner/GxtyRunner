package com.github.ybq.android.spinkit.b;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

/* compiled from: CircleSprite */
public class a extends d {
    public ValueAnimator a() {
        return null;
    }

    public void a(Canvas canvas, Paint paint) {
        if (p() != null) {
            canvas.drawCircle((float) p().centerX(), (float) p().centerY(), (float) (Math.min(p().width(), p().height()) / 2), paint);
        }
    }
}
