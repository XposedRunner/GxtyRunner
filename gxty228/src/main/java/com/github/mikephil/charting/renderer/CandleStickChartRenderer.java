package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class CandleStickChartRenderer extends LineScatterCandleRadarRenderer {
    private float[] mBodyBuffers = new float[4];
    protected CandleDataProvider mChart;
    private float[] mCloseBuffers = new float[4];
    private float[] mOpenBuffers = new float[4];
    private float[] mRangeBuffers = new float[4];
    private float[] mShadowBuffers = new float[8];

    public CandleStickChartRenderer(CandleDataProvider candleDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = candleDataProvider;
    }

    public void initBuffers() {
    }

    public void drawData(Canvas canvas) {
        for (ICandleDataSet iCandleDataSet : this.mChart.getCandleData().getDataSets()) {
            if (iCandleDataSet.isVisible()) {
                drawDataSet(canvas, iCandleDataSet);
            }
        }
    }

    protected void drawDataSet(Canvas canvas, ICandleDataSet iCandleDataSet) {
        Transformer transformer = this.mChart.getTransformer(iCandleDataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        float barSpace = iCandleDataSet.getBarSpace();
        boolean showCandleBar = iCandleDataSet.getShowCandleBar();
        this.mXBounds.set(this.mChart, iCandleDataSet);
        this.mRenderPaint.setStrokeWidth(iCandleDataSet.getShadowWidth());
        for (int i = this.mXBounds.min; i <= this.mXBounds.range + this.mXBounds.min; i++) {
            CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForIndex(i);
            if (candleEntry != null) {
                float x = candleEntry.getX();
                float open = candleEntry.getOpen();
                float close = candleEntry.getClose();
                float high = candleEntry.getHigh();
                float low = candleEntry.getLow();
                int color;
                if (showCandleBar) {
                    this.mShadowBuffers[0] = x;
                    this.mShadowBuffers[2] = x;
                    this.mShadowBuffers[4] = x;
                    this.mShadowBuffers[6] = x;
                    if (open > close) {
                        this.mShadowBuffers[1] = high * phaseY;
                        this.mShadowBuffers[3] = open * phaseY;
                        this.mShadowBuffers[5] = low * phaseY;
                        this.mShadowBuffers[7] = close * phaseY;
                    } else if (open < close) {
                        this.mShadowBuffers[1] = high * phaseY;
                        this.mShadowBuffers[3] = close * phaseY;
                        this.mShadowBuffers[5] = low * phaseY;
                        this.mShadowBuffers[7] = open * phaseY;
                    } else {
                        this.mShadowBuffers[1] = high * phaseY;
                        this.mShadowBuffers[3] = open * phaseY;
                        this.mShadowBuffers[5] = low * phaseY;
                        this.mShadowBuffers[7] = this.mShadowBuffers[3];
                    }
                    transformer.pointValuesToPixel(this.mShadowBuffers);
                    Paint paint;
                    if (!iCandleDataSet.getShadowColorSameAsCandle()) {
                        paint = this.mRenderPaint;
                        if (iCandleDataSet.getShadowColor() == ColorTemplate.COLOR_NONE) {
                            color = iCandleDataSet.getColor(i);
                        } else {
                            color = iCandleDataSet.getShadowColor();
                        }
                        paint.setColor(color);
                    } else if (open > close) {
                        paint = this.mRenderPaint;
                        if (iCandleDataSet.getDecreasingColor() == ColorTemplate.COLOR_NONE) {
                            color = iCandleDataSet.getColor(i);
                        } else {
                            color = iCandleDataSet.getDecreasingColor();
                        }
                        paint.setColor(color);
                    } else if (open < close) {
                        paint = this.mRenderPaint;
                        if (iCandleDataSet.getIncreasingColor() == ColorTemplate.COLOR_NONE) {
                            color = iCandleDataSet.getColor(i);
                        } else {
                            color = iCandleDataSet.getIncreasingColor();
                        }
                        paint.setColor(color);
                    } else {
                        paint = this.mRenderPaint;
                        if (iCandleDataSet.getNeutralColor() == ColorTemplate.COLOR_NONE) {
                            color = iCandleDataSet.getColor(i);
                        } else {
                            color = iCandleDataSet.getNeutralColor();
                        }
                        paint.setColor(color);
                    }
                    this.mRenderPaint.setStyle(Style.STROKE);
                    canvas.drawLines(this.mShadowBuffers, this.mRenderPaint);
                    this.mBodyBuffers[0] = (x - 0.5f) + barSpace;
                    this.mBodyBuffers[1] = close * phaseY;
                    this.mBodyBuffers[2] = (x + 0.5f) - barSpace;
                    this.mBodyBuffers[3] = open * phaseY;
                    transformer.pointValuesToPixel(this.mBodyBuffers);
                    if (open > close) {
                        if (iCandleDataSet.getDecreasingColor() == ColorTemplate.COLOR_NONE) {
                            this.mRenderPaint.setColor(iCandleDataSet.getColor(i));
                        } else {
                            this.mRenderPaint.setColor(iCandleDataSet.getDecreasingColor());
                        }
                        this.mRenderPaint.setStyle(iCandleDataSet.getDecreasingPaintStyle());
                        canvas.drawRect(this.mBodyBuffers[0], this.mBodyBuffers[3], this.mBodyBuffers[2], this.mBodyBuffers[1], this.mRenderPaint);
                    } else if (open < close) {
                        if (iCandleDataSet.getIncreasingColor() == ColorTemplate.COLOR_NONE) {
                            this.mRenderPaint.setColor(iCandleDataSet.getColor(i));
                        } else {
                            this.mRenderPaint.setColor(iCandleDataSet.getIncreasingColor());
                        }
                        this.mRenderPaint.setStyle(iCandleDataSet.getIncreasingPaintStyle());
                        canvas.drawRect(this.mBodyBuffers[0], this.mBodyBuffers[1], this.mBodyBuffers[2], this.mBodyBuffers[3], this.mRenderPaint);
                    } else {
                        if (iCandleDataSet.getNeutralColor() == ColorTemplate.COLOR_NONE) {
                            this.mRenderPaint.setColor(iCandleDataSet.getColor(i));
                        } else {
                            this.mRenderPaint.setColor(iCandleDataSet.getNeutralColor());
                        }
                        canvas.drawLine(this.mBodyBuffers[0], this.mBodyBuffers[1], this.mBodyBuffers[2], this.mBodyBuffers[3], this.mRenderPaint);
                    }
                } else {
                    this.mRangeBuffers[0] = x;
                    this.mRangeBuffers[1] = high * phaseY;
                    this.mRangeBuffers[2] = x;
                    this.mRangeBuffers[3] = low * phaseY;
                    this.mOpenBuffers[0] = (x - 0.5f) + barSpace;
                    this.mOpenBuffers[1] = open * phaseY;
                    this.mOpenBuffers[2] = x;
                    this.mOpenBuffers[3] = open * phaseY;
                    this.mCloseBuffers[0] = (0.5f + x) - barSpace;
                    this.mCloseBuffers[1] = close * phaseY;
                    this.mCloseBuffers[2] = x;
                    this.mCloseBuffers[3] = close * phaseY;
                    transformer.pointValuesToPixel(this.mRangeBuffers);
                    transformer.pointValuesToPixel(this.mOpenBuffers);
                    transformer.pointValuesToPixel(this.mCloseBuffers);
                    if (open > close) {
                        if (iCandleDataSet.getDecreasingColor() == ColorTemplate.COLOR_NONE) {
                            color = iCandleDataSet.getColor(i);
                        } else {
                            color = iCandleDataSet.getDecreasingColor();
                        }
                    } else if (open < close) {
                        if (iCandleDataSet.getIncreasingColor() == ColorTemplate.COLOR_NONE) {
                            color = iCandleDataSet.getColor(i);
                        } else {
                            color = iCandleDataSet.getIncreasingColor();
                        }
                    } else if (iCandleDataSet.getNeutralColor() == ColorTemplate.COLOR_NONE) {
                        color = iCandleDataSet.getColor(i);
                    } else {
                        color = iCandleDataSet.getNeutralColor();
                    }
                    this.mRenderPaint.setColor(color);
                    canvas.drawLine(this.mRangeBuffers[0], this.mRangeBuffers[1], this.mRangeBuffers[2], this.mRangeBuffers[3], this.mRenderPaint);
                    canvas.drawLine(this.mOpenBuffers[0], this.mOpenBuffers[1], this.mOpenBuffers[2], this.mOpenBuffers[3], this.mRenderPaint);
                    canvas.drawLine(this.mCloseBuffers[0], this.mCloseBuffers[1], this.mCloseBuffers[2], this.mCloseBuffers[3], this.mRenderPaint);
                }
            }
        }
    }

    public void drawValues(Canvas canvas) {
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getCandleData().getDataSets();
            for (int i = 0; i < dataSets.size(); i++) {
                ICandleDataSet iCandleDataSet = (ICandleDataSet) dataSets.get(i);
                if (shouldDrawValues(iCandleDataSet)) {
                    applyValueTextStyle(iCandleDataSet);
                    Transformer transformer = this.mChart.getTransformer(iCandleDataSet.getAxisDependency());
                    this.mXBounds.set(this.mChart, iCandleDataSet);
                    float[] generateTransformedValuesCandle = transformer.generateTransformedValuesCandle(iCandleDataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                    float convertDpToPixel = Utils.convertDpToPixel(5.0f);
                    MPPointF instance = MPPointF.getInstance(iCandleDataSet.getIconsOffset());
                    instance.x = Utils.convertDpToPixel(instance.x);
                    instance.y = Utils.convertDpToPixel(instance.y);
                    for (int i2 = 0; i2 < generateTransformedValuesCandle.length; i2 += 2) {
                        float f = generateTransformedValuesCandle[i2];
                        float f2 = generateTransformedValuesCandle[i2 + 1];
                        if (!this.mViewPortHandler.isInBoundsRight(f)) {
                            break;
                        }
                        if (this.mViewPortHandler.isInBoundsLeft(f) && this.mViewPortHandler.isInBoundsY(f2)) {
                            CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForIndex((i2 / 2) + this.mXBounds.min);
                            if (iCandleDataSet.isDrawValuesEnabled()) {
                                drawValue(canvas, iCandleDataSet.getValueFormatter(), candleEntry.getHigh(), candleEntry, i, f, f2 - convertDpToPixel, iCandleDataSet.getValueTextColor(i2 / 2));
                            }
                            if (candleEntry.getIcon() != null && iCandleDataSet.isDrawIconsEnabled()) {
                                Drawable icon = candleEntry.getIcon();
                                Utils.drawImage(canvas, icon, (int) (instance.x + f), (int) (instance.y + f2), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                            }
                        }
                    }
                    MPPointF.recycleInstance(instance);
                }
            }
        }
    }

    public void drawExtras(Canvas canvas) {
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        CandleData candleData = this.mChart.getCandleData();
        for (Highlight highlight : highlightArr) {
            ICandleDataSet iCandleDataSet = (ICandleDataSet) candleData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iCandleDataSet != null && iCandleDataSet.isHighlightEnabled()) {
                CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(candleEntry, iCandleDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iCandleDataSet.getAxisDependency()).getPixelForValues(candleEntry.getX(), ((candleEntry.getLow() * this.mAnimator.getPhaseY()) + (candleEntry.getHigh() * this.mAnimator.getPhaseY())) / 2.0f);
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                    drawHighlightLines(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iCandleDataSet);
                }
            }
        }
    }
}
