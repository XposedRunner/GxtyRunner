package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.tencent.connect.common.Constants;
import java.util.List;

public class HorizontalBarChartRenderer extends BarChartRenderer {
    private RectF mBarShadowRectBuffer = new RectF();

    public HorizontalBarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.mValuePaint.setTextAlign(Align.LEFT);
    }

    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new HorizontalBarBuffer((iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1) * (iBarDataSet.getEntryCount() * 4), barData.getDataSetCount(), iBarDataSet.isStacked());
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
                this.mBarShadowRectBuffer.top = x - barWidth;
                this.mBarShadowRectBuffer.bottom = x + barWidth;
                transformer.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsTop(this.mBarShadowRectBuffer.bottom)) {
                    if (!this.mViewPortHandler.isInBoundsBottom(this.mBarShadowRectBuffer.top)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.left = this.mViewPortHandler.contentLeft();
                    this.mBarShadowRectBuffer.right = this.mViewPortHandler.contentRight();
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
        int i3 = 0;
        while (i3 < barBuffer.size() && this.mViewPortHandler.isInBoundsTop(barBuffer.buffer[i3 + 3])) {
            if (this.mViewPortHandler.isInBoundsBottom(barBuffer.buffer[i3 + 1])) {
                if (obj2 == null) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i3 / 4));
                }
                canvas.drawRect(barBuffer.buffer[i3], barBuffer.buffer[i3 + 1], barBuffer.buffer[i3 + 2], barBuffer.buffer[i3 + 3], this.mRenderPaint);
                if (obj != null) {
                    canvas.drawRect(barBuffer.buffer[i3], barBuffer.buffer[i3 + 1], barBuffer.buffer[i3 + 2], barBuffer.buffer[i3 + 3], this.mBarBorderPaint);
                }
            }
            i3 += 4;
        }
    }

    public void drawValues(Canvas canvas) {
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(5.0f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            for (int i = 0; i < this.mChart.getBarData().getDataSetCount(); i++) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i);
                if (shouldDrawValues(iBarDataSet)) {
                    boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    applyValueTextStyle(iBarDataSet);
                    float calcTextHeight = ((float) Utils.calcTextHeight(this.mValuePaint, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) / 2.0f;
                    IValueFormatter valueFormatter = iBarDataSet.getValueFormatter();
                    BarBuffer barBuffer = this.mBarBuffers[i];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF instance = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    instance.x = Utils.convertDpToPixel(instance.x);
                    instance.y = Utils.convertDpToPixel(instance.y);
                    String formattedValue;
                    float calcTextWidth;
                    float f;
                    float f2;
                    Drawable icon;
                    if (iBarDataSet.isStacked()) {
                        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i2 = 0;
                        int i3 = 0;
                        while (((float) i2) < ((float) iBarDataSet.getEntryCount()) * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i2);
                            int valueTextColor = iBarDataSet.getValueTextColor(i2);
                            float[] yVals = barEntry.getYVals();
                            float f3;
                            if (yVals == null) {
                                if (!this.mViewPortHandler.isInBoundsTop(barBuffer.buffer[i3 + 1])) {
                                    break;
                                } else if (this.mViewPortHandler.isInBoundsX(barBuffer.buffer[i3]) && this.mViewPortHandler.isInBoundsBottom(barBuffer.buffer[i3 + 1])) {
                                    float f4;
                                    formattedValue = valueFormatter.getFormattedValue(barEntry.getY(), barEntry, i, this.mViewPortHandler);
                                    calcTextWidth = (float) Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                    f = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth + convertDpToPixel);
                                    if (isDrawValueAboveBarEnabled) {
                                        f2 = -(calcTextWidth + convertDpToPixel);
                                    } else {
                                        f2 = convertDpToPixel;
                                    }
                                    if (isInverted) {
                                        f3 = (-f2) - calcTextWidth;
                                        f4 = (-f) - calcTextWidth;
                                    } else {
                                        f3 = f2;
                                        f4 = f;
                                    }
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        f = barBuffer.buffer[i3 + 2];
                                        if (barEntry.getY() >= 0.0f) {
                                            f2 = f4;
                                        } else {
                                            f2 = f3;
                                        }
                                        drawValue(canvas, formattedValue, f + f2, barBuffer.buffer[i3 + 1] + calcTextHeight, valueTextColor);
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        icon = barEntry.getIcon();
                                        f2 = barBuffer.buffer[i3 + 2];
                                        if (barEntry.getY() < 0.0f) {
                                            f4 = f3;
                                        }
                                        Utils.drawImage(canvas, icon, (int) ((f2 + f4) + instance.x), (int) (instance.y + barBuffer.buffer[i3 + 1]), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                    }
                                }
                            } else {
                                float[] fArr = new float[(yVals.length * 2)];
                                int i4 = 0;
                                int i5 = 0;
                                float f5 = -barEntry.getNegativeSum();
                                float f6 = 0.0f;
                                while (i4 < fArr.length) {
                                    float f7 = yVals[i5];
                                    float f8;
                                    if (f7 == 0.0f && (f6 == 0.0f || f5 == 0.0f)) {
                                        calcTextWidth = f6;
                                        f8 = f5;
                                        f5 = f7;
                                        f7 = f8;
                                    } else if (f7 >= 0.0f) {
                                        f7 += f6;
                                        calcTextWidth = f7;
                                        f8 = f5;
                                        f5 = f7;
                                        f7 = f8;
                                    } else {
                                        f7 = f5 - f7;
                                        calcTextWidth = f6;
                                    }
                                    fArr[i4] = f5 * phaseY;
                                    i4 += 2;
                                    i5++;
                                    f5 = f7;
                                    f6 = calcTextWidth;
                                }
                                transformer.pointValuesToPixel(fArr);
                                for (int i6 = 0; i6 < fArr.length; i6 += 2) {
                                    f2 = yVals[i6 / 2];
                                    formattedValue = valueFormatter.getFormattedValue(f2, barEntry, i, this.mViewPortHandler);
                                    float calcTextWidth2 = (float) Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                    calcTextWidth = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth2 + convertDpToPixel);
                                    if (isDrawValueAboveBarEnabled) {
                                        f = -(calcTextWidth2 + convertDpToPixel);
                                    } else {
                                        f = convertDpToPixel;
                                    }
                                    if (isInverted) {
                                        calcTextWidth = (-calcTextWidth) - calcTextWidth2;
                                        f = (-f) - calcTextWidth2;
                                    }
                                    Object obj = (!(f2 == 0.0f && f5 == 0.0f && f6 > 0.0f) && f2 >= 0.0f) ? null : 1;
                                    calcTextWidth2 = fArr[i6];
                                    if (obj != null) {
                                        f2 = f;
                                    } else {
                                        f2 = calcTextWidth;
                                    }
                                    calcTextWidth = calcTextWidth2 + f2;
                                    f3 = (barBuffer.buffer[i3 + 1] + barBuffer.buffer[i3 + 3]) / 2.0f;
                                    if (!this.mViewPortHandler.isInBoundsTop(f3)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsX(calcTextWidth) && this.mViewPortHandler.isInBoundsBottom(f3)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            drawValue(canvas, formattedValue, calcTextWidth, f3 + calcTextHeight, valueTextColor);
                                        }
                                        if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon2 = barEntry.getIcon();
                                            Utils.drawImage(canvas, icon2, (int) (instance.x + calcTextWidth), (int) (instance.y + f3), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                        }
                                    }
                                }
                            }
                            i2++;
                            i3 = yVals == null ? i3 + 4 : i3 + (yVals.length * 4);
                        }
                    } else {
                        int i7 = 0;
                        while (((float) i7) < ((float) barBuffer.buffer.length) * this.mAnimator.getPhaseX()) {
                            float f9 = (barBuffer.buffer[i7 + 1] + barBuffer.buffer[i7 + 3]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsTop(barBuffer.buffer[i7 + 1])) {
                                break;
                            }
                            if (this.mViewPortHandler.isInBoundsX(barBuffer.buffer[i7]) && this.mViewPortHandler.isInBoundsBottom(barBuffer.buffer[i7 + 1])) {
                                float f10;
                                float f11;
                                BarEntry barEntry2 = (BarEntry) iBarDataSet.getEntryForIndex(i7 / 4);
                                float y = barEntry2.getY();
                                formattedValue = valueFormatter.getFormattedValue(y, barEntry2, i, this.mViewPortHandler);
                                calcTextWidth = (float) Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                f = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth + convertDpToPixel);
                                if (isDrawValueAboveBarEnabled) {
                                    f2 = -(calcTextWidth + convertDpToPixel);
                                } else {
                                    f2 = convertDpToPixel;
                                }
                                if (isInverted) {
                                    f10 = (-f2) - calcTextWidth;
                                    f11 = (-f) - calcTextWidth;
                                } else {
                                    f10 = f2;
                                    f11 = f;
                                }
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    f = barBuffer.buffer[i7 + 2];
                                    if (y >= 0.0f) {
                                        f2 = f11;
                                    } else {
                                        f2 = f10;
                                    }
                                    drawValue(canvas, formattedValue, f + f2, f9 + calcTextHeight, iBarDataSet.getValueTextColor(i7 / 2));
                                }
                                if (barEntry2.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    icon = barEntry2.getIcon();
                                    f2 = barBuffer.buffer[i7 + 2];
                                    if (y < 0.0f) {
                                        f11 = f10;
                                    }
                                    Utils.drawImage(canvas, icon, (int) ((f2 + f11) + instance.x), (int) (f9 + instance.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                            }
                            i7 += 4;
                        }
                    }
                    MPPointF.recycleInstance(instance);
                }
            }
        }
    }

    protected void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }

    protected void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set(f2, f - f4, f3, f + f4);
        transformer.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    protected void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerY(), rectF.right);
    }

    protected boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return ((float) chartInterface.getData().getEntryCount()) < ((float) chartInterface.getMaxVisibleCount()) * this.mViewPortHandler.getScaleY();
    }
}
