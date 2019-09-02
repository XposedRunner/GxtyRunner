package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.tencent.connect.common.Constants;
import java.util.List;

public class BarChartRenderer extends BarLineScatterCandleBubbleRenderer {
    protected Paint mBarBorderPaint;
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect = new RectF();
    private RectF mBarShadowRectBuffer = new RectF();
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    public BarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = barDataProvider;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        this.mShadowPaint = new Paint(1);
        this.mShadowPaint.setStyle(Style.FILL);
        this.mBarBorderPaint = new Paint(1);
        this.mBarBorderPaint.setStyle(Style.STROKE);
    }

    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new BarBuffer((iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1) * (iBarDataSet.getEntryCount() * 4), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    public void drawData(Canvas canvas) {
        BarData barData = this.mChart.getBarData();
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            if (iBarDataSet.isVisible()) {
                drawDataSet(canvas, iBarDataSet, i);
            }
        }
    }

    protected void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        Object obj = iBarDataSet.getBarBorderWidth() > 0.0f ? 1 : null;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(iBarDataSet.getBarShadowColor());
            float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
            int min = Math.min((int) Math.ceil((double) (((float) iBarDataSet.getEntryCount()) * phaseX)), iBarDataSet.getEntryCount());
            for (int i2 = 0; i2 < min; i2++) {
                float x = ((BarEntry) iBarDataSet.getEntryForIndex(i2)).getX();
                this.mBarShadowRectBuffer.left = x - barWidth;
                this.mBarShadowRectBuffer.right = x + barWidth;
                transformer.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsLeft(this.mBarShadowRectBuffer.right)) {
                    if (!this.mViewPortHandler.isInBoundsRight(this.mBarShadowRectBuffer.left)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.top = this.mViewPortHandler.contentTop();
                    this.mBarShadowRectBuffer.bottom = this.mViewPortHandler.contentBottom();
                    canvas.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
                }
            }
        }
        BarBuffer barBuffer = this.mBarBuffers[i];
        barBuffer.setPhases(phaseX, phaseY);
        barBuffer.setDataSet(i);
        barBuffer.setInverted(this.mChart.isInverted(iBarDataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(iBarDataSet);
        transformer.pointValuesToPixel(barBuffer.buffer);
        Object obj2 = iBarDataSet.getColors().size() == 1 ? 1 : null;
        if (obj2 != null) {
            this.mRenderPaint.setColor(iBarDataSet.getColor());
        }
        for (int i3 = 0; i3 < barBuffer.size(); i3 += 4) {
            if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i3 + 2])) {
                if (this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i3])) {
                    if (obj2 == null) {
                        this.mRenderPaint.setColor(iBarDataSet.getColor(i3 / 4));
                    }
                    canvas.drawRect(barBuffer.buffer[i3], barBuffer.buffer[i3 + 1], barBuffer.buffer[i3 + 2], barBuffer.buffer[i3 + 3], this.mRenderPaint);
                    if (obj != null) {
                        canvas.drawRect(barBuffer.buffer[i3], barBuffer.buffer[i3 + 1], barBuffer.buffer[i3 + 2], barBuffer.buffer[i3 + 3], this.mBarBorderPaint);
                    }
                } else {
                    return;
                }
            }
        }
    }

    protected void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set(f - f4, f2, f + f4, f3);
        transformer.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }

    public void drawValues(Canvas canvas) {
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(4.5f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            for (int i = 0; i < this.mChart.getBarData().getDataSetCount(); i++) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i);
                if (shouldDrawValues(iBarDataSet)) {
                    float f;
                    float f2;
                    float f3;
                    applyValueTextStyle(iBarDataSet);
                    boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    float calcTextHeight = (float) Utils.calcTextHeight(this.mValuePaint, Constants.VIA_SHARE_TYPE_PUBLISHVIDEO);
                    float f4 = isDrawValueAboveBarEnabled ? -convertDpToPixel : calcTextHeight + convertDpToPixel;
                    if (isDrawValueAboveBarEnabled) {
                        f = calcTextHeight + convertDpToPixel;
                    } else {
                        f = -convertDpToPixel;
                    }
                    if (isInverted) {
                        f2 = (-f) - calcTextHeight;
                        f3 = (-f4) - calcTextHeight;
                    } else {
                        f2 = f;
                        f3 = f4;
                    }
                    BarBuffer barBuffer = this.mBarBuffers[i];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF instance = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    instance.x = Utils.convertDpToPixel(instance.x);
                    instance.y = Utils.convertDpToPixel(instance.y);
                    BarEntry barEntry;
                    float f5;
                    Drawable icon;
                    float f6;
                    if (iBarDataSet.isStacked()) {
                        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i2 = 0;
                        int i3 = 0;
                        while (((float) i2) < ((float) iBarDataSet.getEntryCount()) * this.mAnimator.getPhaseX()) {
                            barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i2);
                            float[] yVals = barEntry.getYVals();
                            f5 = (barBuffer.buffer[i3] + barBuffer.buffer[i3 + 2]) / 2.0f;
                            int valueTextColor = iBarDataSet.getValueTextColor(i2);
                            if (yVals == null) {
                                if (!this.mViewPortHandler.isInBoundsRight(f5)) {
                                    break;
                                } else if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i3 + 1]) && this.mViewPortHandler.isInBoundsLeft(f5)) {
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        drawValue(canvas, iBarDataSet.getValueFormatter(), barEntry.getY(), barEntry, i, f5, barBuffer.buffer[i3 + 1] + (barEntry.getY() >= 0.0f ? f3 : f2), valueTextColor);
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        icon = barEntry.getIcon();
                                        f4 = barBuffer.buffer[i3 + 1];
                                        if (barEntry.getY() >= 0.0f) {
                                            f = f3;
                                        } else {
                                            f = f2;
                                        }
                                        Utils.drawImage(canvas, icon, (int) (instance.x + f5), (int) ((f + f4) + instance.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                    }
                                }
                            } else {
                                float[] fArr = new float[(yVals.length * 2)];
                                int i4 = 0;
                                int i5 = 0;
                                float f7 = -barEntry.getNegativeSum();
                                float f8 = 0.0f;
                                while (i4 < fArr.length) {
                                    float f9 = yVals[i5];
                                    float f10;
                                    if (f9 == 0.0f && (f8 == 0.0f || f7 == 0.0f)) {
                                        calcTextHeight = f8;
                                        f10 = f7;
                                        f7 = f9;
                                        f9 = f10;
                                    } else if (f9 >= 0.0f) {
                                        f9 += f8;
                                        calcTextHeight = f9;
                                        f10 = f7;
                                        f7 = f9;
                                        f9 = f10;
                                    } else {
                                        f9 = f7 - f9;
                                        calcTextHeight = f8;
                                    }
                                    fArr[i4 + 1] = f7 * phaseY;
                                    i4 += 2;
                                    i5++;
                                    f7 = f9;
                                    f8 = calcTextHeight;
                                }
                                transformer.pointValuesToPixel(fArr);
                                for (int i6 = 0; i6 < fArr.length; i6 += 2) {
                                    f = yVals[i6 / 2];
                                    Object obj = (!(f == 0.0f && f7 == 0.0f && f8 > 0.0f) && f >= 0.0f) ? null : 1;
                                    f4 = fArr[i6 + 1];
                                    if (obj != null) {
                                        f = f2;
                                    } else {
                                        f = f3;
                                    }
                                    f6 = f4 + f;
                                    if (!this.mViewPortHandler.isInBoundsRight(f5)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsY(f6) && this.mViewPortHandler.isInBoundsLeft(f5)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            drawValue(canvas, iBarDataSet.getValueFormatter(), yVals[i6 / 2], barEntry, i, f5, f6, valueTextColor);
                                        }
                                        if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon2 = barEntry.getIcon();
                                            Utils.drawImage(canvas, icon2, (int) (instance.x + f5), (int) (instance.y + f6), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                        }
                                    }
                                }
                            }
                            i2++;
                            i3 = yVals == null ? i3 + 4 : i3 + (yVals.length * 4);
                        }
                    } else {
                        for (int i7 = 0; ((float) i7) < ((float) barBuffer.buffer.length) * this.mAnimator.getPhaseX(); i7 += 4) {
                            f5 = (barBuffer.buffer[i7] + barBuffer.buffer[i7 + 2]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsRight(f5)) {
                                break;
                            }
                            if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i7 + 1]) && this.mViewPortHandler.isInBoundsLeft(f5)) {
                                barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i7 / 4);
                                calcTextHeight = barEntry.getY();
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    IValueFormatter valueFormatter = iBarDataSet.getValueFormatter();
                                    if (calcTextHeight >= 0.0f) {
                                        f6 = barBuffer.buffer[i7 + 1] + f3;
                                    } else {
                                        f6 = barBuffer.buffer[i7 + 3] + f2;
                                    }
                                    drawValue(canvas, valueFormatter, calcTextHeight, barEntry, i, f5, f6, iBarDataSet.getValueTextColor(i7 / 4));
                                }
                                if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    icon = barEntry.getIcon();
                                    if (calcTextHeight >= 0.0f) {
                                        f = barBuffer.buffer[i7 + 1] + f3;
                                    } else {
                                        f = barBuffer.buffer[i7 + 3] + f2;
                                    }
                                    Utils.drawImage(canvas, icon, (int) (instance.x + f5), (int) (f + instance.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                            }
                        }
                    }
                    MPPointF.recycleInstance(instance);
                }
            }
        }
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        BarData barData = this.mChart.getBarData();
        for (Highlight highlight : highlightArr) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iBarDataSet != null && iBarDataSet.isHighlightEnabled()) {
                BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(barEntry, iBarDataSet)) {
                    float y;
                    float f;
                    Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                    this.mHighlightPaint.setColor(iBarDataSet.getHighLightColor());
                    this.mHighlightPaint.setAlpha(iBarDataSet.getHighLightAlpha());
                    Object obj = (highlight.getStackIndex() < 0 || !barEntry.isStacked()) ? null : 1;
                    if (obj == null) {
                        y = barEntry.getY();
                        f = 0.0f;
                    } else if (this.mChart.isHighlightFullBarEnabled()) {
                        y = barEntry.getPositiveSum();
                        f = -barEntry.getNegativeSum();
                    } else {
                        Range range = barEntry.getRanges()[highlight.getStackIndex()];
                        y = range.from;
                        f = range.to;
                    }
                    prepareBarHighlight(barEntry.getX(), y, f, barData.getBarWidth() / 2.0f, transformer);
                    setHighlightDrawPos(highlight, this.mBarRect);
                    canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                }
            }
        }
    }

    protected void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerX(), rectF.top);
    }

    public void drawExtras(Canvas canvas) {
    }
}
