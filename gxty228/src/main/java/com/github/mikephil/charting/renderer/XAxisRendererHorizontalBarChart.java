package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class XAxisRendererHorizontalBarChart extends XAxisRenderer {
    protected BarChart mChart;
    protected Path mRenderLimitLinesPathBuffer = new Path();

    public XAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer, BarChart barChart) {
        super(viewPortHandler, xAxis, transformer);
        this.mChart = barChart;
    }

    public void computeAxis(float f, float f2, boolean z) {
        if (this.mViewPortHandler.contentWidth() > 10.0f && !this.mViewPortHandler.isFullyZoomedOutY()) {
            float f3;
            float f4;
            MPPointD valuesByTouchPoint = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom());
            MPPointD valuesByTouchPoint2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            if (z) {
                f3 = (float) valuesByTouchPoint2.y;
                f4 = (float) valuesByTouchPoint.y;
            } else {
                f3 = (float) valuesByTouchPoint.y;
                f4 = (float) valuesByTouchPoint2.y;
            }
            MPPointD.recycleInstance(valuesByTouchPoint);
            MPPointD.recycleInstance(valuesByTouchPoint2);
            f2 = f4;
            f = f3;
        }
        computeAxisValues(f, f2);
    }

    protected void computeSize() {
        this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
        FSize calcTextSize = Utils.calcTextSize(this.mAxisLabelPaint, this.mXAxis.getLongestLabel());
        float xOffset = (float) ((int) (calcTextSize.width + (this.mXAxis.getXOffset() * 3.5f)));
        float f = calcTextSize.height;
        calcTextSize = Utils.getSizeOfRotatedRectangleByDegrees(calcTextSize.width, f, this.mXAxis.getLabelRotationAngle());
        this.mXAxis.mLabelWidth = Math.round(xOffset);
        this.mXAxis.mLabelHeight = Math.round(f);
        this.mXAxis.mLabelRotatedWidth = (int) (calcTextSize.width + (this.mXAxis.getXOffset() * 3.5f));
        this.mXAxis.mLabelRotatedHeight = Math.round(calcTextSize.height);
        FSize.recycleInstance(calcTextSize);
    }

    public void renderAxisLabels(Canvas canvas) {
        if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
            float xOffset = this.mXAxis.getXOffset();
            this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
            MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
            if (this.mXAxis.getPosition() == XAxisPosition.TOP) {
                instance.x = 0.0f;
                instance.y = 0.5f;
                drawLabels(canvas, xOffset + this.mViewPortHandler.contentRight(), instance);
            } else if (this.mXAxis.getPosition() == XAxisPosition.TOP_INSIDE) {
                instance.x = 1.0f;
                instance.y = 0.5f;
                drawLabels(canvas, this.mViewPortHandler.contentRight() - xOffset, instance);
            } else if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM) {
                instance.x = 1.0f;
                instance.y = 0.5f;
                drawLabels(canvas, this.mViewPortHandler.contentLeft() - xOffset, instance);
            } else if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM_INSIDE) {
                instance.x = 1.0f;
                instance.y = 0.5f;
                drawLabels(canvas, xOffset + this.mViewPortHandler.contentLeft(), instance);
            } else {
                instance.x = 0.0f;
                instance.y = 0.5f;
                drawLabels(canvas, this.mViewPortHandler.contentRight() + xOffset, instance);
                instance.x = 1.0f;
                instance.y = 0.5f;
                drawLabels(canvas, this.mViewPortHandler.contentLeft() - xOffset, instance);
            }
            MPPointF.recycleInstance(instance);
        }
    }

    protected void drawLabels(Canvas canvas, float f, MPPointF mPPointF) {
        float labelRotationAngle = this.mXAxis.getLabelRotationAngle();
        boolean isCenterAxisLabelsEnabled = this.mXAxis.isCenterAxisLabelsEnabled();
        float[] fArr = new float[(this.mXAxis.mEntryCount * 2)];
        for (int i = 0; i < fArr.length; i += 2) {
            if (isCenterAxisLabelsEnabled) {
                fArr[i + 1] = this.mXAxis.mCenteredEntries[i / 2];
            } else {
                fArr[i + 1] = this.mXAxis.mEntries[i / 2];
            }
        }
        this.mTrans.pointValuesToPixel(fArr);
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            float f2 = fArr[i2 + 1];
            if (this.mViewPortHandler.isInBoundsY(f2)) {
                drawLabel(canvas, this.mXAxis.getValueFormatter().getFormattedValue(this.mXAxis.mEntries[i2 / 2], this.mXAxis), f, f2, mPPointF, labelRotationAngle);
            }
        }
    }

    public RectF getGridClippingRect() {
        this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mGridClippingRect.inset(0.0f, -this.mAxis.getGridLineWidth());
        return this.mGridClippingRect;
    }

    protected void drawGridLine(Canvas canvas, float f, float f2, Path path) {
        path.moveTo(this.mViewPortHandler.contentRight(), f2);
        path.lineTo(this.mViewPortHandler.contentLeft(), f2);
        canvas.drawPath(path, this.mGridPaint);
        path.reset();
    }

    public void renderAxisLine(Canvas canvas) {
        if (this.mXAxis.isDrawAxisLineEnabled() && this.mXAxis.isEnabled()) {
            this.mAxisLinePaint.setColor(this.mXAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mXAxis.getAxisLineWidth());
            if (this.mXAxis.getPosition() == XAxisPosition.TOP || this.mXAxis.getPosition() == XAxisPosition.TOP_INSIDE || this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            }
            if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM || this.mXAxis.getPosition() == XAxisPosition.BOTTOM_INSIDE || this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            }
        }
    }

    public void renderLimitLines(Canvas canvas) {
        List limitLines = this.mXAxis.getLimitLines();
        if (limitLines != null && limitLines.size() > 0) {
            float[] fArr = this.mRenderLimitLinesBuffer;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            Path path = this.mRenderLimitLinesPathBuffer;
            path.reset();
            for (int i = 0; i < limitLines.size(); i++) {
                LimitLine limitLine = (LimitLine) limitLines.get(i);
                if (limitLine.isEnabled()) {
                    int save = canvas.save();
                    this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
                    this.mLimitLineClippingRect.inset(0.0f, -limitLine.getLineWidth());
                    canvas.clipRect(this.mLimitLineClippingRect);
                    this.mLimitLinePaint.setStyle(Style.STROKE);
                    this.mLimitLinePaint.setColor(limitLine.getLineColor());
                    this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
                    this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
                    fArr[1] = limitLine.getLimit();
                    this.mTrans.pointValuesToPixel(fArr);
                    path.moveTo(this.mViewPortHandler.contentLeft(), fArr[1]);
                    path.lineTo(this.mViewPortHandler.contentRight(), fArr[1]);
                    canvas.drawPath(path, this.mLimitLinePaint);
                    path.reset();
                    String label = limitLine.getLabel();
                    if (!(label == null || label.equals(""))) {
                        this.mLimitLinePaint.setStyle(limitLine.getTextStyle());
                        this.mLimitLinePaint.setPathEffect(null);
                        this.mLimitLinePaint.setColor(limitLine.getTextColor());
                        this.mLimitLinePaint.setStrokeWidth(0.5f);
                        this.mLimitLinePaint.setTextSize(limitLine.getTextSize());
                        float calcTextHeight = (float) Utils.calcTextHeight(this.mLimitLinePaint, label);
                        float convertDpToPixel = Utils.convertDpToPixel(4.0f) + limitLine.getXOffset();
                        float lineWidth = (limitLine.getLineWidth() + calcTextHeight) + limitLine.getYOffset();
                        LimitLabelPosition labelPosition = limitLine.getLabelPosition();
                        if (labelPosition == LimitLabelPosition.RIGHT_TOP) {
                            this.mLimitLinePaint.setTextAlign(Align.RIGHT);
                            canvas.drawText(label, this.mViewPortHandler.contentRight() - convertDpToPixel, calcTextHeight + (fArr[1] - lineWidth), this.mLimitLinePaint);
                        } else if (labelPosition == LimitLabelPosition.RIGHT_BOTTOM) {
                            this.mLimitLinePaint.setTextAlign(Align.RIGHT);
                            canvas.drawText(label, this.mViewPortHandler.contentRight() - convertDpToPixel, fArr[1] + lineWidth, this.mLimitLinePaint);
                        } else if (labelPosition == LimitLabelPosition.LEFT_TOP) {
                            this.mLimitLinePaint.setTextAlign(Align.LEFT);
                            canvas.drawText(label, this.mViewPortHandler.contentLeft() + convertDpToPixel, calcTextHeight + (fArr[1] - lineWidth), this.mLimitLinePaint);
                        } else {
                            this.mLimitLinePaint.setTextAlign(Align.LEFT);
                            canvas.drawText(label, this.mViewPortHandler.offsetLeft() + convertDpToPixel, fArr[1] + lineWidth, this.mLimitLinePaint);
                        }
                    }
                    canvas.restoreToCount(save);
                }
            }
        }
    }
}
