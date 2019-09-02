package com.github.ybq.android.spinkit.b;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.amap.api.maps.utils.SpatialRelationUtil;

/* compiled from: CircleSpriteGroup */
public abstract class b extends f {
    public void a(Canvas canvas) {
        for (int i = 0; i < q(); i++) {
            e h = h(i);
            int save = canvas.save();
            canvas.rotate((float) ((i * SpatialRelationUtil.A_CIRCLE_DEGREE) / q()), (float) getBounds().centerX(), (float) getBounds().centerY());
            h.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Rect b = b(rect);
        int width = (int) (((((double) b.width()) * 3.141592653589793d) / 3.5999999046325684d) / ((double) q()));
        int centerX = b.centerX() - width;
        int centerX2 = b.centerX() + width;
        for (int i = 0; i < q(); i++) {
            h(i).a(centerX, b.top, centerX2, b.top + (width * 2));
        }
    }
}
