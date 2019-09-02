package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class RadarChartRenderer extends LineRadarRenderer {
    protected RadarChart mChart;
    protected Path mDrawDataSetSurfacePathBuffer = new Path();
    protected Path mDrawHighlightCirclePathBuffer = new Path();
    protected Paint mHighlightCirclePaint;
    protected Paint mWebPaint;

    public RadarChartRenderer(RadarChart radarChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = radarChart;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(2.0f);
        this.mHighlightPaint.setColor(Color.rgb(255, 187, 115));
        this.mWebPaint = new Paint(1);
        this.mWebPaint.setStyle(Style.STROKE);
        this.mHighlightCirclePaint = new Paint(1);
    }

    public Paint getWebPaint() {
        return this.mWebPaint;
    }

    public void initBuffers() {
    }

    public void drawData(Canvas canvas) {
        RadarData radarData = (RadarData) this.mChart.getData();
        int entryCount = ((IRadarDataSet) radarData.getMaxEntryCountSet()).getEntryCount();
        for (IRadarDataSet iRadarDataSet : radarData.getDataSets()) {
            if (iRadarDataSet.isVisible()) {
                drawDataSet(canvas, iRadarDataSet, entryCount);
            }
        }
    }

    protected void drawDataSet(Canvas canvas, IRadarDataSet iRadarDataSet, int i) {
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        Path path = this.mDrawDataSetSurfacePathBuffer;
        path.reset();
        Object obj = null;
        for (int i2 = 0; i2 < iRadarDataSet.getEntryCount(); i2++) {
            this.mRenderPaint.setColor(iRadarDataSet.getColor(i2));
            Utils.getPosition(centerOffsets, ((((RadarEntry) iRadarDataSet.getEntryForIndex(i2)).getY() - this.mChart.getYChartMin()) * factor) * phaseY, ((((float) i2) * sliceAngle) * phaseX) + this.mChart.getRotationAngle(), instance);
            if (!Float.isNaN(instance.x)) {
                if (obj == null) {
                    path.moveTo(instance.x, instance.y);
                    obj = 1;
                } else {
                    path.lineTo(instance.x, instance.y);
                }
            }
        }
        if (iRadarDataSet.getEntryCount() > i) {
            path.lineTo(centerOffsets.x, centerOffsets.y);
        }
        path.close();
        if (iRadarDataSet.isDrawFilledEnabled()) {
            Drawable fillDrawable = iRadarDataSet.getFillDrawable();
            if (fillDrawable != null) {
                drawFilledPath(canvas, path, fillDrawable);
            } else {
                drawFilledPath(canvas, path, iRadarDataSet.getFillColor(), iRadarDataSet.getFillAlpha());
            }
        }
        this.mRenderPaint.setStrokeWidth(iRadarDataSet.getLineWidth());
        this.mRenderPaint.setStyle(Style.STROKE);
        if (!iRadarDataSet.isDrawFilledEnabled() || iRadarDataSet.getFillAlpha() < 255) {
            canvas.drawPath(path, this.mRenderPaint);
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(instance);
    }

    public void drawValues(Canvas canvas) {
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF instance2 = MPPointF.getInstance(0.0f, 0.0f);
        float convertDpToPixel = Utils.convertDpToPixel(5.0f);
        for (int i = 0; i < ((RadarData) this.mChart.getData()).getDataSetCount(); i++) {
            IRadarDataSet iRadarDataSet = (IRadarDataSet) ((RadarData) this.mChart.getData()).getDataSetByIndex(i);
            if (shouldDrawValues(iRadarDataSet)) {
                applyValueTextStyle(iRadarDataSet);
                MPPointF instance3 = MPPointF.getInstance(iRadarDataSet.getIconsOffset());
                instance3.x = Utils.convertDpToPixel(instance3.x);
                instance3.y = Utils.convertDpToPixel(instance3.y);
                for (int i2 = 0; i2 < iRadarDataSet.getEntryCount(); i2++) {
                    RadarEntry radarEntry = (RadarEntry) iRadarDataSet.getEntryForIndex(i2);
                    Utils.getPosition(centerOffsets, ((radarEntry.getY() - this.mChart.getYChartMin()) * factor) * phaseY, ((((float) i2) * sliceAngle) * phaseX) + this.mChart.getRotationAngle(), instance);
                    if (iRadarDataSet.isDrawValuesEnabled()) {
                        drawValue(canvas, iRadarDataSet.getValueFormatter(), radarEntry.getY(), radarEntry, i, instance.x, instance.y - convertDpToPixel, iRadarDataSet.getValueTextColor(i2));
                    }
                    if (radarEntry.getIcon() != null && iRadarDataSet.isDrawIconsEnabled()) {
                        Drawable icon = radarEntry.getIcon();
                        Utils.getPosition(centerOffsets, ((radarEntry.getY() * factor) * phaseY) + instance3.y, ((((float) i2) * sliceAngle) * phaseX) + this.mChart.getRotationAngle(), instance2);
                        instance2.y += instance3.x;
                        Utils.drawImage(canvas, icon, (int) instance2.x, (int) instance2.y, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                }
                MPPointF.recycleInstance(instance3);
            }
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(instance);
        MPPointF.recycleInstance(instance2);
    }

    public void drawExtras(Canvas canvas) {
        drawWeb(canvas);
    }

    protected void drawWeb(Canvas canvas) {
        int i;
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        float rotationAngle = this.mChart.getRotationAngle();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidth());
        this.mWebPaint.setColor(this.mChart.getWebColor());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int skipWebLineCount = this.mChart.getSkipWebLineCount() + 1;
        int entryCount = ((IRadarDataSet) ((RadarData) this.mChart.getData()).getMaxEntryCountSet()).getEntryCount();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        for (i = 0; i < entryCount; i += skipWebLineCount) {
            Utils.getPosition(centerOffsets, this.mChart.getYRange() * factor, (((float) i) * sliceAngle) + rotationAngle, instance);
            canvas.drawLine(centerOffsets.x, centerOffsets.y, instance.x, instance.y, this.mWebPaint);
        }
        MPPointF.recycleInstance(instance);
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidthInner());
        this.mWebPaint.setColor(this.mChart.getWebColorInner());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        entryCount = this.mChart.getYAxis().mEntryCount;
        instance = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF instance2 = MPPointF.getInstance(0.0f, 0.0f);
        for (skipWebLineCount = 0; skipWebLineCount < entryCount; skipWebLineCount++) {
            for (i = 0; i < ((RadarData) this.mChart.getData()).getEntryCount(); i++) {
                float yChartMin = (this.mChart.getYAxis().mEntries[skipWebLineCount] - this.mChart.getYChartMin()) * factor;
                Utils.getPosition(centerOffsets, yChartMin, (((float) i) * sliceAngle) + rotationAngle, instance);
                Utils.getPosition(centerOffsets, yChartMin, (((float) (i + 1)) * sliceAngle) + rotationAngle, instance2);
                canvas.drawLine(instance.x, instance.y, instance2.x, instance2.y, this.mWebPaint);
            }
        }
        MPPointF.recycleInstance(instance);
        MPPointF.recycleInstance(instance2);
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        RadarData radarData = (RadarData) this.mChart.getData();
        for (Highlight highlight : highlightArr) {
            IRadarDataSet iRadarDataSet = (IRadarDataSet) radarData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iRadarDataSet != null && iRadarDataSet.isHighlightEnabled()) {
                RadarEntry radarEntry = (RadarEntry) iRadarDataSet.getEntryForIndex((int) highlight.getX());
                if (isInBoundsX(radarEntry, iRadarDataSet)) {
                    Utils.getPosition(centerOffsets, ((radarEntry.getY() - this.mChart.getYChartMin()) * factor) * this.mAnimator.getPhaseY(), ((highlight.getX() * sliceAngle) * this.mAnimator.getPhaseX()) + this.mChart.getRotationAngle(), instance);
                    highlight.setDraw(instance.x, instance.y);
                    drawHighlightLines(canvas, instance.x, instance.y, iRadarDataSet);
                    if (!(!iRadarDataSet.isDrawHighlightCircleEnabled() || Float.isNaN(instance.x) || Float.isNaN(instance.y))) {
                        int colorWithAlpha;
                        int highlightCircleStrokeColor = iRadarDataSet.getHighlightCircleStrokeColor();
                        if (highlightCircleStrokeColor == ColorTemplate.COLOR_NONE) {
                            highlightCircleStrokeColor = iRadarDataSet.getColor(0);
                        }
                        if (iRadarDataSet.getHighlightCircleStrokeAlpha() < 255) {
                            colorWithAlpha = ColorTemplate.colorWithAlpha(highlightCircleStrokeColor, iRadarDataSet.getHighlightCircleStrokeAlpha());
                        } else {
                            colorWithAlpha = highlightCircleStrokeColor;
                        }
                        drawHighlightCircle(canvas, instance, iRadarDataSet.getHighlightCircleInnerRadius(), iRadarDataSet.getHighlightCircleOuterRadius(), iRadarDataSet.getHighlightCircleFillColor(), colorWithAlpha, iRadarDataSet.getHighlightCircleStrokeWidth());
                    }
                }
            }
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(instance);
    }

    public void drawHighlightCircle(Canvas canvas, MPPointF mPPointF, float f, float f2, int i, int i2, float f3) {
        canvas.save();
        float convertDpToPixel = Utils.convertDpToPixel(f2);
        float convertDpToPixel2 = Utils.convertDpToPixel(f);
        if (i != ColorTemplate.COLOR_NONE) {
            Path path = this.mDrawHighlightCirclePathBuffer;
            path.reset();
            path.addCircle(mPPointF.x, mPPointF.y, convertDpToPixel, Direction.CW);
            if (convertDpToPixel2 > 0.0f) {
                path.addCircle(mPPointF.x, mPPointF.y, convertDpToPixel2, Direction.CCW);
            }
            this.mHighlightCirclePaint.setColor(i);
            this.mHighlightCirclePaint.setStyle(Style.FILL);
            canvas.drawPath(path, this.mHighlightCirclePaint);
        }
        if (i2 != ColorTemplate.COLOR_NONE) {
            this.mHighlightCirclePaint.setColor(i2);
            this.mHighlightCirclePaint.setStyle(Style.STROKE);
            this.mHighlightCirclePaint.setStrokeWidth(Utils.convertDpToPixel(f3));
            canvas.drawCircle(mPPointF.x, mPPointF.y, convertDpToPixel, this.mHighlightCirclePaint);
        }
        canvas.restore();
    }
}
