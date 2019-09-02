package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
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

public class XAxisRenderer extends AxisRenderer {
    protected RectF mGridClippingRect = new RectF();
    protected RectF mLimitLineClippingRect = new RectF();
    private Path mLimitLinePath = new Path();
    float[] mLimitLineSegmentsBuffer = new float[4];
    protected float[] mRenderGridLinesBuffer = new float[2];
    protected Path mRenderGridLinesPath = new Path();
    protected float[] mRenderLimitLinesBuffer = new float[2];
    protected XAxis mXAxis;

    public XAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer) {
        super(viewPortHandler, transformer, xAxis);
        this.mXAxis = xAxis;
        this.mAxisLabelPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mAxisLabelPaint.setTextAlign(Align.CENTER);
        this.mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10.0f));
    }

    protected void setupGridPaint() {
        this.mGridPaint.setColor(this.mXAxis.getGridColor());
        this.mGridPaint.setStrokeWidth(this.mXAxis.getGridLineWidth());
        this.mGridPaint.setPathEffect(this.mXAxis.getGridDashPathEffect());
    }

    public void computeAxis(float f, float f2, boolean z) {
        if (this.mViewPortHandler.contentWidth() > 10.0f && !this.mViewPortHandler.isFullyZoomedOutX()) {
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

    protected void computeAxisValues(float f, float f2) {
        super.computeAxisValues(f, f2);
        computeSize();
    }

    protected void computeSize() {
        String longestLabel = this.mXAxis.getLongestLabel();
        this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
        this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
        FSize calcTextSize = Utils.calcTextSize(this.mAxisLabelPaint, longestLabel);
        float f = calcTextSize.width;
        float calcTextHeight = (float) Utils.calcTextHeight(this.mAxisLabelPaint, "Q");
        FSize sizeOfRotatedRectangleByDegrees = Utils.getSizeOfRotatedRectangleByDegrees(f, calcTextHeight, this.mXAxis.getLabelRotationAngle());
        this.mXAxis.mLabelWidth = Math.round(f);
        this.mXAxis.mLabelHeight = Math.round(calcTextHeight);
        this.mXAxis.mLabelRotatedWidth = Math.round(sizeOfRotatedRectangleByDegrees.width);
        this.mXAxis.mLabelRotatedHeight = Math.round(sizeOfRotatedRectangleByDegrees.height);
        FSize.recycleInstance(sizeOfRotatedRectangleByDegrees);
        FSize.recycleInstance(calcTextSize);
    }

    public void renderAxisLabels(Canvas canvas) {
        if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
            float yOffset = this.mXAxis.getYOffset();
            this.mAxisLabelPaint.setTypeface(this.mXAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mXAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mXAxis.getTextColor());
            MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
            if (this.mXAxis.getPosition() == XAxisPosition.TOP) {
                instance.x = 0.5f;
                instance.y = 1.0f;
                drawLabels(canvas, this.mViewPortHandler.contentTop() - yOffset, instance);
            } else if (this.mXAxis.getPosition() == XAxisPosition.TOP_INSIDE) {
                instance.x = 0.5f;
                instance.y = 1.0f;
                drawLabels(canvas, (yOffset + this.mViewPortHandler.contentTop()) + ((float) this.mXAxis.mLabelRotatedHeight), instance);
            } else if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM) {
                instance.x = 0.5f;
                instance.y = 0.0f;
                drawLabels(canvas, yOffset + this.mViewPortHandler.contentBottom(), instance);
            } else if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM_INSIDE) {
                instance.x = 0.5f;
                instance.y = 0.0f;
                drawLabels(canvas, (this.mViewPortHandler.contentBottom() - yOffset) - ((float) this.mXAxis.mLabelRotatedHeight), instance);
            } else {
                instance.x = 0.5f;
                instance.y = 1.0f;
                drawLabels(canvas, this.mViewPortHandler.contentTop() - yOffset, instance);
                instance.x = 0.5f;
                instance.y = 0.0f;
                drawLabels(canvas, yOffset + this.mViewPortHandler.contentBottom(), instance);
            }
            MPPointF.recycleInstance(instance);
        }
    }

    public void renderAxisLine(Canvas canvas) {
        if (this.mXAxis.isDrawAxisLineEnabled() && this.mXAxis.isEnabled()) {
            this.mAxisLinePaint.setColor(this.mXAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mXAxis.getAxisLineWidth());
            this.mAxisLinePaint.setPathEffect(this.mXAxis.getAxisLineDashPathEffect());
            if (this.mXAxis.getPosition() == XAxisPosition.TOP || this.mXAxis.getPosition() == XAxisPosition.TOP_INSIDE || this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mAxisLinePaint);
            }
            if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM || this.mXAxis.getPosition() == XAxisPosition.BOTTOM_INSIDE || this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
            }
        }
    }

    protected void drawLabels(Canvas canvas, float f, MPPointF mPPointF) {
        float labelRotationAngle = this.mXAxis.getLabelRotationAngle();
        boolean isCenterAxisLabelsEnabled = this.mXAxis.isCenterAxisLabelsEnabled();
        float[] fArr = new float[(this.mXAxis.mEntryCount * 2)];
        for (int i = 0; i < fArr.length; i += 2) {
            if (isCenterAxisLabelsEnabled) {
                fArr[i] = this.mXAxis.mCenteredEntries[i / 2];
            } else {
                fArr[i] = this.mXAxis.mEntries[i / 2];
            }
        }
        this.mTrans.pointValuesToPixel(fArr);
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            float f2 = fArr[i2];
            if (this.mViewPortHandler.isInBoundsX(f2)) {
                float f3;
                String formattedValue = this.mXAxis.getValueFormatter().getFormattedValue(this.mXAxis.mEntries[i2 / 2], this.mXAxis);
                if (this.mXAxis.isAvoidFirstLastClippingEnabled()) {
                    if (i2 == this.mXAxis.mEntryCount - 1 && this.mXAxis.mEntryCount > 1) {
                        float calcTextWidth = (float) Utils.calcTextWidth(this.mAxisLabelPaint, formattedValue);
                        if (calcTextWidth > this.mViewPortHandler.offsetRight() * 2.0f && f2 + calcTextWidth > this.mViewPortHandler.getChartWidth()) {
                            f2 -= calcTextWidth / 2.0f;
                        }
                        f3 = f2;
                        drawLabel(canvas, formattedValue, f3, f, mPPointF, labelRotationAngle);
                    } else if (i2 == 0) {
                        f3 = f2 + (((float) Utils.calcTextWidth(this.mAxisLabelPaint, formattedValue)) / 2.0f);
                        drawLabel(canvas, formattedValue, f3, f, mPPointF, labelRotationAngle);
                    }
                }
                f3 = f2;
                drawLabel(canvas, formattedValue, f3, f, mPPointF, labelRotationAngle);
            }
        }
    }

    protected void drawLabel(Canvas canvas, String str, float f, float f2, MPPointF mPPointF, float f3) {
        Utils.drawXAxisValue(canvas, str, f, f2, this.mAxisLabelPaint, mPPointF, f3);
    }

    public void renderGridLines(Canvas canvas) {
        int i = 0;
        if (this.mXAxis.isDrawGridLinesEnabled() && this.mXAxis.isEnabled()) {
            int save = canvas.save();
            canvas.clipRect(getGridClippingRect());
            if (this.mRenderGridLinesBuffer.length != this.mAxis.mEntryCount * 2) {
                this.mRenderGridLinesBuffer = new float[(this.mXAxis.mEntryCount * 2)];
            }
            float[] fArr = this.mRenderGridLinesBuffer;
            for (int i2 = 0; i2 < fArr.length; i2 += 2) {
                fArr[i2] = this.mXAxis.mEntries[i2 / 2];
                fArr[i2 + 1] = this.mXAxis.mEntries[i2 / 2];
            }
            this.mTrans.pointValuesToPixel(fArr);
            setupGridPaint();
            Path path = this.mRenderGridLinesPath;
            path.reset();
            while (i < fArr.length) {
                drawGridLine(canvas, fArr[i], fArr[i + 1], path);
                i += 2;
            }
            canvas.restoreToCount(save);
        }
    }

    public RectF getGridClippingRect() {
        this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mGridClippingRect.inset(-this.mAxis.getGridLineWidth(), 0.0f);
        return this.mGridClippingRect;
    }

    protected void drawGridLine(Canvas canvas, float f, float f2, Path path) {
        path.moveTo(f, this.mViewPortHandler.contentBottom());
        path.lineTo(f, this.mViewPortHandler.contentTop());
        canvas.drawPath(path, this.mGridPaint);
        path.reset();
    }

    public void renderLimitLines(Canvas canvas) {
        List limitLines = this.mXAxis.getLimitLines();
        if (limitLines != null && limitLines.size() > 0) {
            float[] fArr = this.mRenderLimitLinesBuffer;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            for (int i = 0; i < limitLines.size(); i++) {
                LimitLine limitLine = (LimitLine) limitLines.get(i);
                if (limitLine.isEnabled()) {
                    int save = canvas.save();
                    this.mLimitLineClippingRect.set(this.mViewPortHandler.getContentRect());
                    this.mLimitLineClippingRect.inset(-limitLine.getLineWidth(), 0.0f);
                    canvas.clipRect(this.mLimitLineClippingRect);
                    fArr[0] = limitLine.getLimit();
                    fArr[1] = 0.0f;
                    this.mTrans.pointValuesToPixel(fArr);
                    renderLimitLineLine(canvas, limitLine, fArr);
                    renderLimitLineLabel(canvas, limitLine, fArr, 2.0f + limitLine.getYOffset());
                    canvas.restoreToCount(save);
                }
            }
        }
    }

    public void renderLimitLineLine(Canvas canvas, LimitLine limitLine, float[] fArr) {
        this.mLimitLineSegmentsBuffer[0] = fArr[0];
        this.mLimitLineSegmentsBuffer[1] = this.mViewPortHandler.contentTop();
        this.mLimitLineSegmentsBuffer[2] = fArr[0];
        this.mLimitLineSegmentsBuffer[3] = this.mViewPortHandler.contentBottom();
        this.mLimitLinePath.reset();
        this.mLimitLinePath.moveTo(this.mLimitLineSegmentsBuffer[0], this.mLimitLineSegmentsBuffer[1]);
        this.mLimitLinePath.lineTo(this.mLimitLineSegmentsBuffer[2], this.mLimitLineSegmentsBuffer[3]);
        this.mLimitLinePaint.setStyle(Style.STROKE);
        this.mLimitLinePaint.setColor(limitLine.getLineColor());
        this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
        this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
        canvas.drawPath(this.mLimitLinePath, this.mLimitLinePaint);
    }

    public void renderLimitLineLabel(Canvas canvas, LimitLine limitLine, float[] fArr, float f) {
        String label = limitLine.getLabel();
        if (label != null && !label.equals("")) {
            this.mLimitLinePaint.setStyle(limitLine.getTextStyle());
            this.mLimitLinePaint.setPathEffect(null);
            this.mLimitLinePaint.setColor(limitLine.getTextColor());
            this.mLimitLinePaint.setStrokeWidth(0.5f);
            this.mLimitLinePaint.setTextSize(limitLine.getTextSize());
            float lineWidth = limitLine.getLineWidth() + limitLine.getXOffset();
            LimitLabelPosition labelPosition = limitLine.getLabelPosition();
            if (labelPosition == LimitLabelPosition.RIGHT_TOP) {
                float calcTextHeight = (float) Utils.calcTextHeight(this.mLimitLinePaint, label);
                this.mLimitLinePaint.setTextAlign(Align.LEFT);
                canvas.drawText(label, lineWidth + fArr[0], calcTextHeight + (this.mViewPortHandler.contentTop() + f), this.mLimitLinePaint);
            } else if (labelPosition == LimitLabelPosition.RIGHT_BOTTOM) {
                this.mLimitLinePaint.setTextAlign(Align.LEFT);
                canvas.drawText(label, lineWidth + fArr[0], this.mViewPortHandler.contentBottom() - f, this.mLimitLinePaint);
            } else if (labelPosition == LimitLabelPosition.LEFT_TOP) {
                this.mLimitLinePaint.setTextAlign(Align.RIGHT);
                canvas.drawText(label, fArr[0] - lineWidth, ((float) Utils.calcTextHeight(this.mLimitLinePaint, label)) + (this.mViewPortHandler.contentTop() + f), this.mLimitLinePaint);
            } else {
                this.mLimitLinePaint.setTextAlign(Align.RIGHT);
                canvas.drawText(label, fArr[0] - lineWidth, this.mViewPortHandler.contentBottom() - f, this.mLimitLinePaint);
            }
        }
    }
}
