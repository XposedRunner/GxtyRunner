package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import java.util.List;

public class Transformer {
    private Matrix mMBuffer1 = new Matrix();
    private Matrix mMBuffer2 = new Matrix();
    protected Matrix mMatrixOffset = new Matrix();
    protected Matrix mMatrixValueToPx = new Matrix();
    protected Matrix mPixelToValueMatrixBuffer = new Matrix();
    protected ViewPortHandler mViewPortHandler;
    float[] ptsBuffer = new float[2];
    protected float[] valuePointsForGenerateTransformedValuesBubble = new float[1];
    protected float[] valuePointsForGenerateTransformedValuesCandle = new float[1];
    protected float[] valuePointsForGenerateTransformedValuesLine = new float[1];
    protected float[] valuePointsForGenerateTransformedValuesScatter = new float[1];

    public Transformer(ViewPortHandler viewPortHandler) {
        this.mViewPortHandler = viewPortHandler;
    }

    public void prepareMatrixValuePx(float f, float f2, float f3, float f4) {
        float f5 = 0.0f;
        float contentWidth = this.mViewPortHandler.contentWidth() / f2;
        float contentHeight = this.mViewPortHandler.contentHeight() / f3;
        if (Float.isInfinite(contentWidth)) {
            contentWidth = 0.0f;
        }
        if (!Float.isInfinite(contentHeight)) {
            f5 = contentHeight;
        }
        this.mMatrixValueToPx.reset();
        this.mMatrixValueToPx.postTranslate(-f, -f4);
        this.mMatrixValueToPx.postScale(contentWidth, -f5);
    }

    public void prepareMatrixOffset(boolean z) {
        this.mMatrixOffset.reset();
        if (z) {
            this.mMatrixOffset.setTranslate(this.mViewPortHandler.offsetLeft(), -this.mViewPortHandler.offsetTop());
            this.mMatrixOffset.postScale(1.0f, -1.0f);
            return;
        }
        this.mMatrixOffset.postTranslate(this.mViewPortHandler.offsetLeft(), this.mViewPortHandler.getChartHeight() - this.mViewPortHandler.offsetBottom());
    }

    public float[] generateTransformedValuesScatter(IScatterDataSet iScatterDataSet, float f, float f2, int i, int i2) {
        int i3 = ((int) ((((float) (i2 - i)) * f) + 1.0f)) * 2;
        if (this.valuePointsForGenerateTransformedValuesScatter.length != i3) {
            this.valuePointsForGenerateTransformedValuesScatter = new float[i3];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesScatter;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            Entry entryForIndex = iScatterDataSet.getEntryForIndex((i4 / 2) + i);
            if (entryForIndex != null) {
                fArr[i4] = entryForIndex.getX();
                fArr[i4 + 1] = entryForIndex.getY() * f2;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public float[] generateTransformedValuesBubble(IBubbleDataSet iBubbleDataSet, float f, int i, int i2) {
        int i3 = ((i2 - i) + 1) * 2;
        if (this.valuePointsForGenerateTransformedValuesBubble.length != i3) {
            this.valuePointsForGenerateTransformedValuesBubble = new float[i3];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesBubble;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            Entry entryForIndex = iBubbleDataSet.getEntryForIndex((i4 / 2) + i);
            if (entryForIndex != null) {
                fArr[i4] = entryForIndex.getX();
                fArr[i4 + 1] = entryForIndex.getY() * f;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public float[] generateTransformedValuesLine(ILineDataSet iLineDataSet, float f, float f2, int i, int i2) {
        int i3 = (((int) (((float) (i2 - i)) * f)) + 1) * 2;
        if (this.valuePointsForGenerateTransformedValuesLine.length != i3) {
            this.valuePointsForGenerateTransformedValuesLine = new float[i3];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesLine;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            Entry entryForIndex = iLineDataSet.getEntryForIndex((i4 / 2) + i);
            if (entryForIndex != null) {
                fArr[i4] = entryForIndex.getX();
                fArr[i4 + 1] = entryForIndex.getY() * f2;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public float[] generateTransformedValuesCandle(ICandleDataSet iCandleDataSet, float f, float f2, int i, int i2) {
        int i3 = ((int) ((((float) (i2 - i)) * f) + 1.0f)) * 2;
        if (this.valuePointsForGenerateTransformedValuesCandle.length != i3) {
            this.valuePointsForGenerateTransformedValuesCandle = new float[i3];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesCandle;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForIndex((i4 / 2) + i);
            if (candleEntry != null) {
                fArr[i4] = candleEntry.getX();
                fArr[i4 + 1] = candleEntry.getHigh() * f2;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public void pathValueToPixel(Path path) {
        path.transform(this.mMatrixValueToPx);
        path.transform(this.mViewPortHandler.getMatrixTouch());
        path.transform(this.mMatrixOffset);
    }

    public void pathValuesToPixel(List<Path> list) {
        for (int i = 0; i < list.size(); i++) {
            pathValueToPixel((Path) list.get(i));
        }
    }

    public void pointValuesToPixel(float[] fArr) {
        this.mMatrixValueToPx.mapPoints(fArr);
        this.mViewPortHandler.getMatrixTouch().mapPoints(fArr);
        this.mMatrixOffset.mapPoints(fArr);
    }

    public void rectValueToPixel(RectF rectF) {
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectToPixelPhase(RectF rectF, float f) {
        rectF.top *= f;
        rectF.bottom *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectToPixelPhaseHorizontal(RectF rectF, float f) {
        rectF.left *= f;
        rectF.right *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectValueToPixelHorizontal(RectF rectF) {
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectValueToPixelHorizontal(RectF rectF, float f) {
        rectF.left *= f;
        rectF.right *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectValuesToPixel(List<RectF> list) {
        Matrix valueToPixelMatrix = getValueToPixelMatrix();
        for (int i = 0; i < list.size(); i++) {
            valueToPixelMatrix.mapRect((RectF) list.get(i));
        }
    }

    public void pixelsToValue(float[] fArr) {
        Matrix matrix = this.mPixelToValueMatrixBuffer;
        matrix.reset();
        this.mMatrixOffset.invert(matrix);
        matrix.mapPoints(fArr);
        this.mViewPortHandler.getMatrixTouch().invert(matrix);
        matrix.mapPoints(fArr);
        this.mMatrixValueToPx.invert(matrix);
        matrix.mapPoints(fArr);
    }

    public MPPointD getValuesByTouchPoint(float f, float f2) {
        MPPointD instance = MPPointD.getInstance(Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON);
        getValuesByTouchPoint(f, f2, instance);
        return instance;
    }

    public void getValuesByTouchPoint(float f, float f2, MPPointD mPPointD) {
        this.ptsBuffer[0] = f;
        this.ptsBuffer[1] = f2;
        pixelsToValue(this.ptsBuffer);
        mPPointD.x = (double) this.ptsBuffer[0];
        mPPointD.y = (double) this.ptsBuffer[1];
    }

    public MPPointD getPixelForValues(float f, float f2) {
        this.ptsBuffer[0] = f;
        this.ptsBuffer[1] = f2;
        pointValuesToPixel(this.ptsBuffer);
        return MPPointD.getInstance((double) this.ptsBuffer[0], (double) this.ptsBuffer[1]);
    }

    public Matrix getValueMatrix() {
        return this.mMatrixValueToPx;
    }

    public Matrix getOffsetMatrix() {
        return this.mMatrixOffset;
    }

    public Matrix getValueToPixelMatrix() {
        this.mMBuffer1.set(this.mMatrixValueToPx);
        this.mMBuffer1.postConcat(this.mViewPortHandler.mMatrixTouch);
        this.mMBuffer1.postConcat(this.mMatrixOffset);
        return this.mMBuffer1;
    }

    public Matrix getPixelToValueMatrix() {
        getValueToPixelMatrix().invert(this.mMBuffer2);
        return this.mMBuffer2;
    }
}
