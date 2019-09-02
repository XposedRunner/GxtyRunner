package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class CircleShapeRenderer implements IShapeRenderer {
    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, float f, float f2, Paint paint) {
        float scatterShapeSize = iScatterDataSet.getScatterShapeSize();
        float f3 = scatterShapeSize / 2.0f;
        float convertDpToPixel = Utils.convertDpToPixel(iScatterDataSet.getScatterShapeHoleRadius());
        float f4 = (scatterShapeSize - (2.0f * convertDpToPixel)) / 2.0f;
        float f5 = f4 / 2.0f;
        int scatterShapeHoleColor = iScatterDataSet.getScatterShapeHoleColor();
        if (((double) scatterShapeSize) > Utils.DOUBLE_EPSILON) {
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth(f4);
            canvas.drawCircle(f, f2, convertDpToPixel + f5, paint);
            if (scatterShapeHoleColor != ColorTemplate.COLOR_NONE) {
                paint.setStyle(Style.FILL);
                paint.setColor(scatterShapeHoleColor);
                canvas.drawCircle(f, f2, convertDpToPixel, paint);
                return;
            }
            return;
        }
        paint.setStyle(Style.FILL);
        canvas.drawCircle(f, f2, f3, paint);
    }
}
