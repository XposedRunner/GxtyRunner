package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class BubbleChartRenderer extends BarLineScatterCandleBubbleRenderer {
    private float[] _hsvBuffer = new float[3];
    protected BubbleDataProvider mChart;
    private float[] pointBuffer = new float[2];
    private float[] sizeBuffer = new float[4];

    public BubbleChartRenderer(BubbleDataProvider bubbleDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = bubbleDataProvider;
        this.mRenderPaint.setStyle(Style.FILL);
        this.mHighlightPaint.setStyle(Style.STROKE);
        this.mHighlightPaint.setStrokeWidth(Utils.convertDpToPixel(1.5f));
    }

    public void initBuffers() {
    }

    public void drawData(Canvas canvas) {
        for (IBubbleDataSet iBubbleDataSet : this.mChart.getBubbleData().getDataSets()) {
            if (iBubbleDataSet.isVisible()) {
                drawDataSet(canvas, iBubbleDataSet);
            }
        }
    }

    protected float getShapeSize(float f, float f2, float f3, boolean z) {
        if (z) {
            f = f2 == 0.0f ? 1.0f : (float) Math.sqrt((double) (f / f2));
        }
        return f3 * f;
    }

    protected void drawDataSet(Canvas canvas, IBubbleDataSet iBubbleDataSet) {
        Transformer transformer = this.mChart.getTransformer(iBubbleDataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        this.mXBounds.set(this.mChart, iBubbleDataSet);
        this.sizeBuffer[0] = 0.0f;
        this.sizeBuffer[2] = 1.0f;
        transformer.pointValuesToPixel(this.sizeBuffer);
        boolean isNormalizeSizeEnabled = iBubbleDataSet.isNormalizeSizeEnabled();
        float min = Math.min(Math.abs(this.mViewPortHandler.contentBottom() - this.mViewPortHandler.contentTop()), Math.abs(this.sizeBuffer[2] - this.sizeBuffer[0]));
        for (int i = this.mXBounds.min; i <= this.mXBounds.range + this.mXBounds.min; i++) {
            BubbleEntry bubbleEntry = (BubbleEntry) iBubbleDataSet.getEntryForIndex(i);
            this.pointBuffer[0] = bubbleEntry.getX();
            this.pointBuffer[1] = bubbleEntry.getY() * phaseY;
            transformer.pointValuesToPixel(this.pointBuffer);
            float shapeSize = getShapeSize(bubbleEntry.getSize(), iBubbleDataSet.getMaxSize(), min, isNormalizeSizeEnabled) / 2.0f;
            if (this.mViewPortHandler.isInBoundsTop(this.pointBuffer[1] + shapeSize) && this.mViewPortHandler.isInBoundsBottom(this.pointBuffer[1] - shapeSize) && this.mViewPortHandler.isInBoundsLeft(this.pointBuffer[0] + shapeSize)) {
                if (this.mViewPortHandler.isInBoundsRight(this.pointBuffer[0] - shapeSize)) {
                    this.mRenderPaint.setColor(iBubbleDataSet.getColor((int) bubbleEntry.getX()));
                    canvas.drawCircle(this.pointBuffer[0], this.pointBuffer[1], shapeSize, this.mRenderPaint);
                } else {
                    return;
                }
            }
        }
    }

    public void drawValues(Canvas canvas) {
        BubbleData bubbleData = this.mChart.getBubbleData();
        if (bubbleData != null) {
            if (isDrawingValuesAllowed(this.mChart)) {
                List dataSets = bubbleData.getDataSets();
                float calcTextHeight = (float) Utils.calcTextHeight(this.mValuePaint, "1");
                for (int i = 0; i < dataSets.size(); i++) {
                    IBubbleDataSet iBubbleDataSet = (IBubbleDataSet) dataSets.get(i);
                    if (shouldDrawValues(iBubbleDataSet)) {
                        applyValueTextStyle(iBubbleDataSet);
                        float max = Math.max(0.0f, Math.min(1.0f, this.mAnimator.getPhaseX()));
                        float phaseY = this.mAnimator.getPhaseY();
                        this.mXBounds.set(this.mChart, iBubbleDataSet);
                        float[] generateTransformedValuesBubble = this.mChart.getTransformer(iBubbleDataSet.getAxisDependency()).generateTransformedValuesBubble(iBubbleDataSet, phaseY, this.mXBounds.min, this.mXBounds.max);
                        float f = max == 1.0f ? phaseY : max;
                        MPPointF instance = MPPointF.getInstance(iBubbleDataSet.getIconsOffset());
                        instance.x = Utils.convertDpToPixel(instance.x);
                        instance.y = Utils.convertDpToPixel(instance.y);
                        for (int i2 = 0; i2 < generateTransformedValuesBubble.length; i2 += 2) {
                            int valueTextColor = iBubbleDataSet.getValueTextColor((i2 / 2) + this.mXBounds.min);
                            int argb = Color.argb(Math.round(255.0f * f), Color.red(valueTextColor), Color.green(valueTextColor), Color.blue(valueTextColor));
                            float f2 = generateTransformedValuesBubble[i2];
                            float f3 = generateTransformedValuesBubble[i2 + 1];
                            if (!this.mViewPortHandler.isInBoundsRight(f2)) {
                                break;
                            }
                            if (this.mViewPortHandler.isInBoundsLeft(f2) && this.mViewPortHandler.isInBoundsY(f3)) {
                                BubbleEntry bubbleEntry = (BubbleEntry) iBubbleDataSet.getEntryForIndex((i2 / 2) + this.mXBounds.min);
                                if (iBubbleDataSet.isDrawValuesEnabled()) {
                                    drawValue(canvas, iBubbleDataSet.getValueFormatter(), bubbleEntry.getSize(), bubbleEntry, i, f2, f3 + (0.5f * calcTextHeight), argb);
                                }
                                if (bubbleEntry.getIcon() != null && iBubbleDataSet.isDrawIconsEnabled()) {
                                    Drawable icon = bubbleEntry.getIcon();
                                    Utils.drawImage(canvas, icon, (int) (instance.x + f2), (int) (instance.y + f3), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                            }
                        }
                        MPPointF.recycleInstance(instance);
                    }
                }
            }
        }
    }

    public void drawExtras(Canvas canvas) {
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        BubbleData bubbleData = this.mChart.getBubbleData();
        float phaseY = this.mAnimator.getPhaseY();
        for (Highlight highlight : highlightArr) {
            IBubbleDataSet iBubbleDataSet = (IBubbleDataSet) bubbleData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iBubbleDataSet != null && iBubbleDataSet.isHighlightEnabled()) {
                BubbleEntry bubbleEntry = (BubbleEntry) iBubbleDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (bubbleEntry.getY() == highlight.getY() && isInBoundsX(bubbleEntry, iBubbleDataSet)) {
                    Transformer transformer = this.mChart.getTransformer(iBubbleDataSet.getAxisDependency());
                    this.sizeBuffer[0] = 0.0f;
                    this.sizeBuffer[2] = 1.0f;
                    transformer.pointValuesToPixel(this.sizeBuffer);
                    boolean isNormalizeSizeEnabled = iBubbleDataSet.isNormalizeSizeEnabled();
                    float min = Math.min(Math.abs(this.mViewPortHandler.contentBottom() - this.mViewPortHandler.contentTop()), Math.abs(this.sizeBuffer[2] - this.sizeBuffer[0]));
                    this.pointBuffer[0] = bubbleEntry.getX();
                    this.pointBuffer[1] = bubbleEntry.getY() * phaseY;
                    transformer.pointValuesToPixel(this.pointBuffer);
                    highlight.setDraw(this.pointBuffer[0], this.pointBuffer[1]);
                    float shapeSize = getShapeSize(bubbleEntry.getSize(), iBubbleDataSet.getMaxSize(), min, isNormalizeSizeEnabled) / 2.0f;
                    if (this.mViewPortHandler.isInBoundsTop(this.pointBuffer[1] + shapeSize) && this.mViewPortHandler.isInBoundsBottom(this.pointBuffer[1] - shapeSize) && this.mViewPortHandler.isInBoundsLeft(this.pointBuffer[0] + shapeSize)) {
                        if (this.mViewPortHandler.isInBoundsRight(this.pointBuffer[0] - shapeSize)) {
                            int color = iBubbleDataSet.getColor((int) bubbleEntry.getX());
                            Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color), this._hsvBuffer);
                            float[] fArr = this._hsvBuffer;
                            fArr[2] = fArr[2] * 0.5f;
                            this.mHighlightPaint.setColor(Color.HSVToColor(Color.alpha(color), this._hsvBuffer));
                            this.mHighlightPaint.setStrokeWidth(iBubbleDataSet.getHighlightCircleWidth());
                            canvas.drawCircle(this.pointBuffer[0], this.pointBuffer[1], shapeSize, this.mHighlightPaint);
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }
}
