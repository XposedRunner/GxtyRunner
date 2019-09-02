package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
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

public class YAxisRenderer extends AxisRenderer {
    protected Path mDrawZeroLinePath = new Path();
    protected float[] mGetTransformedPositionsBuffer = new float[2];
    protected RectF mGridClippingRect = new RectF();
    protected RectF mLimitLineClippingRect = new RectF();
    protected Path mRenderGridLinesPath = new Path();
    protected Path mRenderLimitLines = new Path();
    protected float[] mRenderLimitLinesBuffer = new float[2];
    protected YAxis mYAxis;
    protected RectF mZeroLineClippingRect = new RectF();
    protected Paint mZeroLinePaint;

    public YAxisRenderer(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer) {
        super(viewPortHandler, transformer, yAxis);
        this.mYAxis = yAxis;
        if (this.mViewPortHandler != null) {
            this.mAxisLabelPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10.0f));
            this.mZeroLinePaint = new Paint(1);
            this.mZeroLinePaint.setColor(-7829368);
            this.mZeroLinePaint.setStrokeWidth(1.0f);
            this.mZeroLinePaint.setStyle(Style.STROKE);
        }
    }

    public void renderAxisLabels(Canvas canvas) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled()) {
            float[] transformedPositions = getTransformedPositions();
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            float xOffset = this.mYAxis.getXOffset();
            float calcTextHeight = (((float) Utils.calcTextHeight(this.mAxisLabelPaint, "A")) / 2.5f) + this.mYAxis.getYOffset();
            AxisDependency axisDependency = this.mYAxis.getAxisDependency();
            YAxisLabelPosition labelPosition = this.mYAxis.getLabelPosition();
            if (axisDependency == AxisDependency.LEFT) {
                if (labelPosition == YAxisLabelPosition.OUTSIDE_CHART) {
                    this.mAxisLabelPaint.setTextAlign(Align.RIGHT);
                    xOffset = this.mViewPortHandler.offsetLeft() - xOffset;
                } else {
                    this.mAxisLabelPaint.setTextAlign(Align.LEFT);
                    xOffset += this.mViewPortHandler.offsetLeft();
                }
            } else if (labelPosition == YAxisLabelPosition.OUTSIDE_CHART) {
                this.mAxisLabelPaint.setTextAlign(Align.LEFT);
                xOffset += this.mViewPortHandler.contentRight();
            } else {
                this.mAxisLabelPaint.setTextAlign(Align.RIGHT);
                xOffset = this.mViewPortHandler.contentRight() - xOffset;
            }
            drawYLabels(canvas, xOffset, transformedPositions, calcTextHeight);
        }
    }

    public void renderAxisLine(Canvas canvas) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawAxisLineEnabled()) {
            this.mAxisLinePaint.setColor(this.mYAxis.getAxisLineColor());
            this.mAxisLinePaint.setStrokeWidth(this.mYAxis.getAxisLineWidth());
            if (this.mYAxis.getAxisDependency() == AxisDependency.LEFT) {
                canvas.drawLine(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
                return;
            }
            canvas.drawLine(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.mAxisLinePaint);
        }
    }

    protected void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        int i;
        int i2 = this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 0 : 1;
        if (this.mYAxis.isDrawTopYLabelEntryEnabled()) {
            i = this.mYAxis.mEntryCount;
        } else {
            i = this.mYAxis.mEntryCount - 1;
        }
        while (i2 < i) {
            canvas.drawText(this.mYAxis.getFormattedLabel(i2), f, fArr[(i2 * 2) + 1] + f2, this.mAxisLabelPaint);
            i2++;
        }
    }

    public void renderGridLines(Canvas canvas) {
        if (this.mYAxis.isEnabled()) {
            if (this.mYAxis.isDrawGridLinesEnabled()) {
                int save = canvas.save();
                canvas.clipRect(getGridClippingRect());
                float[] transformedPositions = getTransformedPositions();
                this.mGridPaint.setColor(this.mYAxis.getGridColor());
                this.mGridPaint.setStrokeWidth(this.mYAxis.getGridLineWidth());
                this.mGridPaint.setPathEffect(this.mYAxis.getGridDashPathEffect());
                Path path = this.mRenderGridLinesPath;
                path.reset();
                for (int i = 0; i < transformedPositions.length; i += 2) {
                    canvas.drawPath(linePath(path, i, transformedPositions), this.mGridPaint);
                    path.reset();
                }
                canvas.restoreToCount(save);
            }
            if (this.mYAxis.isDrawZeroLineEnabled()) {
                drawZeroLine(canvas);
            }
        }
    }

    public RectF getGridClippingRect() {
        this.mGridClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mGridClippingRect.inset(0.0f, -this.mAxis.getGridLineWidth());
        return this.mGridClippingRect;
    }

    protected Path linePath(Path path, int i, float[] fArr) {
        path.moveTo(this.mViewPortHandler.offsetLeft(), fArr[i + 1]);
        path.lineTo(this.mViewPortHandler.contentRight(), fArr[i + 1]);
        return path;
    }

    protected float[] getTransformedPositions() {
        if (this.mGetTransformedPositionsBuffer.length != this.mYAxis.mEntryCount * 2) {
            this.mGetTransformedPositionsBuffer = new float[(this.mYAxis.mEntryCount * 2)];
        }
        float[] fArr = this.mGetTransformedPositionsBuffer;
        for (int i = 0; i < fArr.length; i += 2) {
            fArr[i + 1] = this.mYAxis.mEntries[i / 2];
        }
        this.mTrans.pointValuesToPixel(fArr);
        return fArr;
    }

    protected void drawZeroLine(Canvas canvas) {
        int save = canvas.save();
        this.mZeroLineClippingRect.set(this.mViewPortHandler.getContentRect());
        this.mZeroLineClippingRect.inset(0.0f, -this.mYAxis.getZeroLineWidth());
        canvas.clipRect(this.mZeroLineClippingRect);
        MPPointD pixelForValues = this.mTrans.getPixelForValues(0.0f, 0.0f);
        this.mZeroLinePaint.setColor(this.mYAxis.getZeroLineColor());
        this.mZeroLinePaint.setStrokeWidth(this.mYAxis.getZeroLineWidth());
        Path path = this.mDrawZeroLinePath;
        path.reset();
        path.moveTo(this.mViewPortHandler.contentLeft(), (float) pixelForValues.y);
        path.lineTo(this.mViewPortHandler.contentRight(), (float) pixelForValues.y);
        canvas.drawPath(path, this.mZeroLinePaint);
        canvas.restoreToCount(save);
    }

    public void renderLimitLines(Canvas canvas) {
        List limitLines = this.mYAxis.getLimitLines();
        if (limitLines != null && limitLines.size() > 0) {
            float[] fArr = this.mRenderLimitLinesBuffer;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            Path path = this.mRenderLimitLines;
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
                        this.mLimitLinePaint.setTypeface(limitLine.getTypeface());
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
