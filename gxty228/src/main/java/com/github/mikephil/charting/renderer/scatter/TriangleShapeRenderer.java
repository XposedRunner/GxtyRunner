package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class TriangleShapeRenderer implements IShapeRenderer {
    protected Path mTrianglePathBuffer = new Path();

    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, float f, float f2, Paint paint) {
        float scatterShapeSize = iScatterDataSet.getScatterShapeSize();
        float f3 = scatterShapeSize / 2.0f;
        float convertDpToPixel = (scatterShapeSize - (Utils.convertDpToPixel(iScatterDataSet.getScatterShapeHoleRadius()) * 2.0f)) / 2.0f;
        int scatterShapeHoleColor = iScatterDataSet.getScatterShapeHoleColor();
        paint.setStyle(Style.FILL);
        Path path = this.mTrianglePathBuffer;
        path.reset();
        path.moveTo(f, f2 - f3);
        path.lineTo(f + f3, f2 + f3);
        path.lineTo(f - f3, f2 + f3);
        if (((double) scatterShapeSize) > Utils.DOUBLE_EPSILON) {
            path.lineTo(f, f2 - f3);
            path.moveTo((f - f3) + convertDpToPixel, (f2 + f3) - convertDpToPixel);
            path.lineTo((f + f3) - convertDpToPixel, (f2 + f3) - convertDpToPixel);
            path.lineTo(f, (f2 - f3) + convertDpToPixel);
            path.lineTo((f - f3) + convertDpToPixel, (f2 + f3) - convertDpToPixel);
        }
        path.close();
        canvas.drawPath(path, paint);
        path.reset();
        if (((double) scatterShapeSize) > Utils.DOUBLE_EPSILON && scatterShapeHoleColor != ColorTemplate.COLOR_NONE) {
            paint.setColor(scatterShapeHoleColor);
            path.moveTo(f, (f2 - f3) + convertDpToPixel);
            path.lineTo((f + f3) - convertDpToPixel, (f2 + f3) - convertDpToPixel);
            path.lineTo((f - f3) + convertDpToPixel, (f3 + f2) - convertDpToPixel);
            path.close();
            canvas.drawPath(path, paint);
            path.reset();
        }
    }
}
