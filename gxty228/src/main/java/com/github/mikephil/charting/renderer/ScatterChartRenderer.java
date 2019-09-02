package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class ScatterChartRenderer extends LineScatterCandleRadarRenderer {
    protected ScatterDataProvider mChart;
    float[] mPixelBuffer = new float[2];

    public ScatterChartRenderer(ScatterDataProvider scatterDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = scatterDataProvider;
    }

    public void initBuffers() {
    }

    public void drawData(Canvas canvas) {
        for (IScatterDataSet iScatterDataSet : this.mChart.getScatterData().getDataSets()) {
            if (iScatterDataSet.isVisible()) {
                drawDataSet(canvas, iScatterDataSet);
            }
        }
    }

    protected void drawDataSet(Canvas canvas, IScatterDataSet iScatterDataSet) {
        ViewPortHandler viewPortHandler = this.mViewPortHandler;
        Transformer transformer = this.mChart.getTransformer(iScatterDataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        IShapeRenderer shapeRenderer = iScatterDataSet.getShapeRenderer();
        if (shapeRenderer == null) {
            Log.i("MISSING", "There's no IShapeRenderer specified for ScatterDataSet");
            return;
        }
        int min = (int) Math.min(Math.ceil((double) (((float) iScatterDataSet.getEntryCount()) * this.mAnimator.getPhaseX())), (double) ((float) iScatterDataSet.getEntryCount()));
        int i = 0;
        while (i < min) {
            Entry entryForIndex = iScatterDataSet.getEntryForIndex(i);
            this.mPixelBuffer[0] = entryForIndex.getX();
            this.mPixelBuffer[1] = entryForIndex.getY() * phaseY;
            transformer.pointValuesToPixel(this.mPixelBuffer);
            if (viewPortHandler.isInBoundsRight(this.mPixelBuffer[0])) {
                if (viewPortHandler.isInBoundsLeft(this.mPixelBuffer[0]) && viewPortHandler.isInBoundsY(this.mPixelBuffer[1])) {
                    this.mRenderPaint.setColor(iScatterDataSet.getColor(i / 2));
                    shapeRenderer.renderShape(canvas, iScatterDataSet, this.mViewPortHandler, this.mPixelBuffer[0], this.mPixelBuffer[1], this.mRenderPaint);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public void drawValues(Canvas canvas) {
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getScatterData().getDataSets();
            for (int i = 0; i < this.mChart.getScatterData().getDataSetCount(); i++) {
                IScatterDataSet iScatterDataSet = (IScatterDataSet) dataSets.get(i);
                if (shouldDrawValues(iScatterDataSet)) {
                    applyValueTextStyle(iScatterDataSet);
                    this.mXBounds.set(this.mChart, iScatterDataSet);
                    float[] generateTransformedValuesScatter = this.mChart.getTransformer(iScatterDataSet.getAxisDependency()).generateTransformedValuesScatter(iScatterDataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                    float convertDpToPixel = Utils.convertDpToPixel(iScatterDataSet.getScatterShapeSize());
                    MPPointF instance = MPPointF.getInstance(iScatterDataSet.getIconsOffset());
                    instance.x = Utils.convertDpToPixel(instance.x);
                    instance.y = Utils.convertDpToPixel(instance.y);
                    int i2 = 0;
                    while (i2 < generateTransformedValuesScatter.length && this.mViewPortHandler.isInBoundsRight(generateTransformedValuesScatter[i2])) {
                        if (this.mViewPortHandler.isInBoundsLeft(generateTransformedValuesScatter[i2]) && this.mViewPortHandler.isInBoundsY(generateTransformedValuesScatter[i2 + 1])) {
                            Entry entryForIndex = iScatterDataSet.getEntryForIndex((i2 / 2) + this.mXBounds.min);
                            if (iScatterDataSet.isDrawValuesEnabled()) {
                                drawValue(canvas, iScatterDataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i, generateTransformedValuesScatter[i2], generateTransformedValuesScatter[i2 + 1] - convertDpToPixel, iScatterDataSet.getValueTextColor((i2 / 2) + this.mXBounds.min));
                            }
                            if (entryForIndex.getIcon() != null && iScatterDataSet.isDrawIconsEnabled()) {
                                Drawable icon = entryForIndex.getIcon();
                                Utils.drawImage(canvas, icon, (int) (generateTransformedValuesScatter[i2] + instance.x), (int) (generateTransformedValuesScatter[i2 + 1] + instance.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                            }
                        }
                        i2 += 2;
                    }
                    MPPointF.recycleInstance(instance);
                }
            }
        }
    }

    public void drawExtras(Canvas canvas) {
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        ScatterData scatterData = this.mChart.getScatterData();
        for (Highlight highlight : highlightArr) {
            IScatterDataSet iScatterDataSet = (IScatterDataSet) scatterData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iScatterDataSet != null && iScatterDataSet.isHighlightEnabled()) {
                Entry entryForXValue = iScatterDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(entryForXValue, iScatterDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iScatterDataSet.getAxisDependency()).getPixelForValues(entryForXValue.getX(), entryForXValue.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                    drawHighlightLines(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iScatterDataSet);
                }
            }
        }
    }
}
