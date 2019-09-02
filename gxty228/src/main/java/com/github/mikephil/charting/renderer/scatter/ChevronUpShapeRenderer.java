package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class ChevronUpShapeRenderer implements IShapeRenderer {
    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, float f, float f2, Paint paint) {
        float scatterShapeSize = iScatterDataSet.getScatterShapeSize() / 2.0f;
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(Utils.convertDpToPixel(1.0f));
        canvas.drawLine(f, f2 - (2.0f * scatterShapeSize), f + (2.0f * scatterShapeSize), f2, paint);
        canvas.drawLine(f, f2 - (2.0f * scatterShapeSize), f - (2.0f * scatterShapeSize), f2, paint);
    }
}
