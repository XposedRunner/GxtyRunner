package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewCompat;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class AxisRenderer extends Renderer {
    protected AxisBase mAxis;
    protected Paint mAxisLabelPaint;
    protected Paint mAxisLinePaint;
    protected Paint mGridPaint;
    protected Paint mLimitLinePaint;
    protected Transformer mTrans;

    public abstract void renderAxisLabels(Canvas canvas);

    public abstract void renderAxisLine(Canvas canvas);

    public abstract void renderGridLines(Canvas canvas);

    public abstract void renderLimitLines(Canvas canvas);

    public AxisRenderer(ViewPortHandler viewPortHandler, Transformer transformer, AxisBase axisBase) {
        super(viewPortHandler);
        this.mTrans = transformer;
        this.mAxis = axisBase;
        if (this.mViewPortHandler != null) {
            this.mAxisLabelPaint = new Paint(1);
            this.mGridPaint = new Paint();
            this.mGridPaint.setColor(-7829368);
            this.mGridPaint.setStrokeWidth(1.0f);
            this.mGridPaint.setStyle(Style.STROKE);
            this.mGridPaint.setAlpha(90);
            this.mAxisLinePaint = new Paint();
            this.mAxisLinePaint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mAxisLinePaint.setStrokeWidth(1.0f);
            this.mAxisLinePaint.setStyle(Style.STROKE);
            this.mLimitLinePaint = new Paint(1);
            this.mLimitLinePaint.setStyle(Style.STROKE);
        }
    }

    public Paint getPaintAxisLabels() {
        return this.mAxisLabelPaint;
    }

    public Paint getPaintGrid() {
        return this.mGridPaint;
    }

    public Paint getPaintAxisLine() {
        return this.mAxisLinePaint;
    }

    public Transformer getTransformer() {
        return this.mTrans;
    }

    public void computeAxis(float f, float f2, boolean z) {
        if (!(this.mViewPortHandler == null || this.mViewPortHandler.contentWidth() <= 10.0f || this.mViewPortHandler.isFullyZoomedOutY())) {
            float f3;
            float f4;
            MPPointD valuesByTouchPoint = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop());
            MPPointD valuesByTouchPoint2 = this.mTrans.getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom());
            if (z) {
                f3 = (float) valuesByTouchPoint.y;
                f4 = (float) valuesByTouchPoint2.y;
            } else {
                f3 = (float) valuesByTouchPoint2.y;
                f4 = (float) valuesByTouchPoint.y;
            }
            MPPointD.recycleInstance(valuesByTouchPoint);
            MPPointD.recycleInstance(valuesByTouchPoint2);
            f2 = f4;
            f = f3;
        }
        computeAxisValues(f, f2);
    }

    protected void computeAxisValues(float f, float f2) {
        int labelCount = this.mAxis.getLabelCount();
        double abs = (double) Math.abs(f2 - f);
        if (labelCount == 0 || abs <= Utils.DOUBLE_EPSILON || Double.isInfinite(abs)) {
            this.mAxis.mEntries = new float[0];
            this.mAxis.mCenteredEntries = new float[0];
            this.mAxis.mEntryCount = 0;
            return;
        }
        double roundToNextSignificant = (double) Utils.roundToNextSignificant(abs / ((double) labelCount));
        if (this.mAxis.isGranularityEnabled() && roundToNextSignificant < ((double) this.mAxis.getGranularity())) {
            roundToNextSignificant = (double) this.mAxis.getGranularity();
        }
        double roundToNextSignificant2 = (double) Utils.roundToNextSignificant(Math.pow(10.0d, (double) ((int) Math.log10(roundToNextSignificant))));
        if (((int) (roundToNextSignificant / roundToNextSignificant2)) > 5) {
            roundToNextSignificant = Math.floor(10.0d * roundToNextSignificant2);
        }
        int i = this.mAxis.isCenterAxisLabelsEnabled() ? 1 : 0;
        if (this.mAxis.isForceLabelsEnabled()) {
            roundToNextSignificant = (double) (((float) abs) / ((float) (labelCount - 1)));
            this.mAxis.mEntryCount = labelCount;
            if (this.mAxis.mEntries.length < labelCount) {
                this.mAxis.mEntries = new float[labelCount];
            }
            for (i = 0; i < labelCount; i++) {
                this.mAxis.mEntries[i] = f;
                f = (float) (((double) f) + roundToNextSignificant);
            }
            i = labelCount;
        } else {
            abs = roundToNextSignificant == Utils.DOUBLE_EPSILON ? Utils.DOUBLE_EPSILON : Math.ceil(((double) f) / roundToNextSignificant) * roundToNextSignificant;
            if (this.mAxis.isCenterAxisLabelsEnabled()) {
                abs -= roundToNextSignificant;
            }
            double nextUp = roundToNextSignificant == Utils.DOUBLE_EPSILON ? Utils.DOUBLE_EPSILON : Utils.nextUp(Math.floor(((double) f2) / roundToNextSignificant) * roundToNextSignificant);
            if (roundToNextSignificant != Utils.DOUBLE_EPSILON) {
                roundToNextSignificant2 = abs;
                while (roundToNextSignificant2 <= nextUp) {
                    roundToNextSignificant2 += roundToNextSignificant;
                    i++;
                }
            }
            this.mAxis.mEntryCount = i;
            if (this.mAxis.mEntries.length < i) {
                this.mAxis.mEntries = new float[i];
            }
            for (labelCount = 0; labelCount < i; labelCount++) {
                if (abs == Utils.DOUBLE_EPSILON) {
                    abs = Utils.DOUBLE_EPSILON;
                }
                this.mAxis.mEntries[labelCount] = (float) abs;
                abs += roundToNextSignificant;
            }
        }
        if (roundToNextSignificant < 1.0d) {
            this.mAxis.mDecimals = (int) Math.ceil(-Math.log10(roundToNextSignificant));
        } else {
            this.mAxis.mDecimals = 0;
        }
        if (this.mAxis.isCenterAxisLabelsEnabled()) {
            if (this.mAxis.mCenteredEntries.length < i) {
                this.mAxis.mCenteredEntries = new float[i];
            }
            float f3 = ((float) roundToNextSignificant) / 2.0f;
            for (int i2 = 0; i2 < i; i2++) {
                this.mAxis.mCenteredEntries[i2] = this.mAxis.mEntries[i2] + f3;
            }
        }
    }
}
