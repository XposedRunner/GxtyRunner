package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class YAxisRendererHorizontalBarChart extends YAxisRenderer {
    protected Path mDrawZeroLinePathBuffer = new Path();
    protected float[] mRenderLimitLinesBuffer = new float[4];
    protected Path mRenderLimitLinesPathBuffer = new Path();

    public YAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer) {
        super(viewPortHandler, yAxis, transformer);
        this.mLimitLinePaint.setTextAlign(Align.LEFT);
    }

    public void computeAxis(float f, float f2, boolean z) {
        if (this.mViewPortHandler.contentHeight() > 10.0f && !this.mViewPortHandler.isFullyZoomedOutX()) {
            float f3;
            float f4;
            MPPointD valuesByTouchPoint = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            MPPointD valuesByTouchPoint2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop());
            if (z) {
                f3 = (float) valuesByTouchPoint2.x;
                f4 = (float) valuesByTouchPoint.x;
            } else {
                f3 = (float) valuesByTouchPoint.x;
                f4 = (float) valuesByTouchPoint2.x;
            }
            MPPointD.recycleInstance(valuesByTouchPoint);
            MPPointD.recycleInstance(valuesByTouchPoint2);
            f2 = f4;
            f = f3;
        }
        computeAxisValues(f, f2);
    }

    public void renderAxisLabels(Canvas canvas) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled()) {
            float[] transformedPositions = getTransformedPositions();
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            this.mAxisLabelPaint.setTextAlign(Align.CENTER);
            float convertDpToPixel = Utils.convertDpToPixel(2.5f);
            float calcTextHeight = (float) Utils.calcTextHeight(this.mAxisLabelPaint, "Q");
            AxisDependency axisDependency = this.mYAxis.getAxisDependency();
            YAxisLabelPosition labelPosition = this.mYAxis.getLabelPosition();
            if (axisDependency == AxisDependency.LEFT) {
                if (labelPosition == YAxisLabelPosition.OUTSIDE_CHART) {
                    convertDpToPixel = this.mViewPortHandler.contentTop() - convertDpToPixel;
                } else {
                    convertDpToPixel = this.mViewPortHandler.contentTop() - convertDpToPixel;
                }
            } else if (labelPosition == YAxisLabelPosition.OUTSIDE_CHART) {
                convertDpToPixel += calcTextHeight + this.mViewPortHandler.contentBottom();
            } else {
                convertDpToPixel += calcTextHeight + this.mViewPortHandler.contentBottom();
            }
            drawYLabels(canvas, convertDpToPixel, transformedPositions, this.mYAxis.getYOffset());
        }
    }

    public void renderAxisLine(Canvas canvas) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawAxisLineEnabled()) {
            this.mAxisLinePaint.setColor(this.mYAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mYAxis.getAxisLineWidth());
            if (this.mYAxis.getAxisDependency() == AxisDependency.LEFT) {
                canvas.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mAxisLinePaint);
                return;
            }
            canvas.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
        }
    }

    protected void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
        this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
        int i = this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 0 : 1;
        int i2 = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
        while (i < i2) {
            canvas.drawText(this.mYAxis.getFormattedLabel(i), fArr[i * 2], f - f2, this.mAxisLabelPaint);
            i++;
        }
    }

    protected float[] getTransformedPositions() {
        if (this.mGetTransformedPositionsBuffer.length != this.mYAxis.mEntryCount * 2) {
            this.mGetTransformedPositionsBuffer = new float[(this.mYAxis.mEntryCount * 2)];
        }
        float[] fArr = this.mGetTransformedPositionsBuffer;
        for (int i = 0; i < fArr.length; i += 2) {
            fArr[i] = this.mYAxis.mEntries[i / 2];
        }
        this.mTrans.pointValuesToPixel(fArr);
        return fArr;
    }

    public RectF getGridClippingRect() {
        this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mGridClippingRect.inset(-this.mAxis.getGridLineWidth(), 0.0f);
        return this.mGridClippingRect;
    }

    protected Path linePath(Path path, int i, float[] fArr) {
        path.moveTo(fArr[i], this.mViewPortHandler.contentTop());
        path.lineTo(fArr[i], this.mViewPortHandler.contentBottom());
        return path;
    }

    protected void drawZeroLine(Canvas canvas) {
        int save = canvas.save();
        this.mZeroLineClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mZeroLineClippingRect.inset(-this.mYAxis.getZeroLineWidth(), 0.0f);
        canvas.clipRect(this.mLimitLineClippingRect);
        MPPointD pixelForValues = this.mTrans.getPixelForValues(0.0f, 0.0f);
        this.mZeroLinePaint.setColor(this.mYAxis.getZeroLineColor());
        this.mZeroLinePaint.setStrokeWidth(this.mYAxis.getZeroLineWidth());
        Path path = this.mDrawZeroLinePathBuffer;
        path.reset();
        path.moveTo(((float) pixelForValues.x) - 1.0f, this.mViewPortHandler.contentTop());
        path.lineTo(((float) pixelForValues.x) - 1.0f, this.mViewPortHandler.contentBottom());
        canvas.drawPath(path, this.mZeroLinePaint);
        canvas.restoreToCount(save);
    }

    public void renderLimitLines(Canvas canvas) {
        List limitLines = this.mYAxis.getLimitLines();
        if (limitLines != null && limitLines.size() > 0) {
            float[] fArr = this.mRenderLimitLinesBuffer;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            Path path = this.mRenderLimitLinesPathBuffer;
            path.reset();
            for (int i = 0; i < limitLines.size(); i++) {
                LimitLine limitLine = (LimitLine) limitLines.get(i);
                if (limitLine.isEnabled()) {
                    int save = canvas.save();
                    this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
                    this.mLimitLineClippingRect.inset(-limitLine.getLineWidth(), 0.0f);
                    canvas.clipRect(this.mLimitLineClippingRect);
                    fArr[0] = limitLine.getLimit();
                    fArr[2] = limitLine.getLimit();
                    this.mTrans.pointValuesToPixel(fArr);
                    fArr[1] = this.mViewPortHandler.contentTop();
                    fArr[3] = this.mViewPortHandler.contentBottom();
                    path.moveTo(fArr[0], fArr[1]);
                    path.lineTo(fArr[2], fArr[3]);
                    this.mLimitLinePaint.setStyle(Style.STROKE);
                    this.mLimitLinePaint.setColor(limitLine.getLineColor());
                    this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
                    this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
                    canvas.drawPath(path, this.mLimitLinePaint);
                    path.reset();
                    String label = limitLine.getLabel();
                    if (!(label == null || label.equals(""))) {
                        this.mLimitLinePaint.setStyle(limitLine.getTextStyle());
                        this.mLimitLinePaint.setPathEffect(null);
                        this.mLimitLinePaint.setColor(limitLine.getTextColor());
                        this.mLimitLinePaint.setTypeface(limitLine.getTypeface());
                        this.mLimitLinePaint.setStrokeWidth(0.5f);
                        this.mLimitLinePaint.setTextSize(limitLine.getTextSize());
                        float lineWidth = limitLine.getLineWidth() + limitLine.getXOffset();
                        float convertDpToPixel = Utils.convertDpToPixel(2.0f) + limitLine.getYOffset();
                        LimitLabelPosition labelPosition = limitLine.getLabelPosition();
                        if (labelPosition == LimitLabelPosition.RIGHT_TOP) {
                            float calcTextHeight = (float) Utils.calcTextHeight(this.mLimitLinePaint, label);
                            this.mLimitLinePaint.setTextAlign(Align.LEFT);
                            canvas.drawText(label, lineWidth + fArr[0], calcTextHeight + (convertDpToPixel + this.mViewPortHandler.contentTop()), this.mLimitLinePaint);
                        } else if (labelPosition == LimitLabelPosition.RIGHT_BOTTOM) {
                            this.mLimitLinePaint.setTextAlign(Align.LEFT);
                            canvas.drawText(label, fArr[0] + lineWidth, this.mViewPortHandler.contentBottom() - convertDpToPixel, this.mLimitLinePaint);
                        } else if (labelPosition == LimitLabelPosition.LEFT_TOP) {
                            this.mLimitLinePaint.setTextAlign(Align.RIGHT);
                            canvas.drawText(label, fArr[0] - lineWidth, ((float) Utils.calcTextHeight(this.mLimitLinePaint, label)) + (convertDpToPixel + this.mViewPortHandler.contentTop()), this.mLimitLinePaint);
                        } else {
                            this.mLimitLinePaint.setTextAlign(Align.RIGHT);
                            canvas.drawText(label, fArr[0] - lineWidth, this.mViewPortHandler.contentBottom() - convertDpToPixel, this.mLimitLinePaint);
                        }
                    }
                    canvas.restoreToCount(save);
                }
            }
        }
    }
}
