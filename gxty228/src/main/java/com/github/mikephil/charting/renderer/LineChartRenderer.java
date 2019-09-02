package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet.Mode;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

public class LineChartRenderer extends LineRadarRenderer {
    protected Path cubicFillPath = new Path();
    protected Path cubicPath = new Path();
    protected Canvas mBitmapCanvas;
    protected Config mBitmapConfig = Config.ARGB_8888;
    protected LineDataProvider mChart;
    protected Paint mCirclePaintInner;
    private float[] mCirclesBuffer = new float[2];
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mGenerateFilledPathBuffer = new Path();
    private HashMap<IDataSet, DataSetImageCache> mImageCaches = new HashMap();
    private float[] mLineBuffer = new float[4];

    private class DataSetImageCache {
        private Bitmap[] circleBitmaps;
        private Path mCirclePathBuffer;

        private DataSetImageCache() {
            this.mCirclePathBuffer = new Path();
        }

        protected boolean init(ILineDataSet iLineDataSet) {
            int circleColorCount = iLineDataSet.getCircleColorCount();
            if (this.circleBitmaps == null) {
                this.circleBitmaps = new Bitmap[circleColorCount];
                return true;
            } else if (this.circleBitmaps.length == circleColorCount) {
                return false;
            } else {
                this.circleBitmaps = new Bitmap[circleColorCount];
                return true;
            }
        }

        protected void fill(ILineDataSet iLineDataSet, boolean z, boolean z2) {
            int circleColorCount = iLineDataSet.getCircleColorCount();
            float circleRadius = iLineDataSet.getCircleRadius();
            float circleHoleRadius = iLineDataSet.getCircleHoleRadius();
            for (int i = 0; i < circleColorCount; i++) {
                Bitmap createBitmap = Bitmap.createBitmap((int) (((double) circleRadius) * 2.1d), (int) (((double) circleRadius) * 2.1d), Config.ARGB_4444);
                Canvas canvas = new Canvas(createBitmap);
                this.circleBitmaps[i] = createBitmap;
                LineChartRenderer.this.mRenderPaint.setColor(iLineDataSet.getCircleColor(i));
                if (z2) {
                    this.mCirclePathBuffer.reset();
                    this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleRadius, Direction.CW);
                    this.mCirclePathBuffer.addCircle(circleRadius, circleRadius, circleHoleRadius, Direction.CCW);
                    canvas.drawPath(this.mCirclePathBuffer, LineChartRenderer.this.mRenderPaint);
                } else {
                    canvas.drawCircle(circleRadius, circleRadius, circleRadius, LineChartRenderer.this.mRenderPaint);
                    if (z) {
                        canvas.drawCircle(circleRadius, circleRadius, circleHoleRadius, LineChartRenderer.this.mCirclePaintInner);
                    }
                }
            }
        }

        protected Bitmap getBitmap(int i) {
            return this.circleBitmaps[i % this.circleBitmaps.length];
        }
    }

    public LineChartRenderer(LineDataProvider lineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = lineDataProvider;
        this.mCirclePaintInner = new Paint(1);
        this.mCirclePaintInner.setStyle(Style.FILL);
        this.mCirclePaintInner.setColor(-1);
    }

    public void initBuffers() {
    }

    public void drawData(Canvas canvas) {
        int chartWidth = (int) this.mViewPortHandler.getChartWidth();
        int chartHeight = (int) this.mViewPortHandler.getChartHeight();
        if (!(this.mDrawBitmap != null && ((Bitmap) this.mDrawBitmap.get()).getWidth() == chartWidth && ((Bitmap) this.mDrawBitmap.get()).getHeight() == chartHeight)) {
            if (chartWidth > 0 && chartHeight > 0) {
                this.mDrawBitmap = new WeakReference(Bitmap.createBitmap(chartWidth, chartHeight, this.mBitmapConfig));
                this.mBitmapCanvas = new Canvas((Bitmap) this.mDrawBitmap.get());
            } else {
                return;
            }
        }
        ((Bitmap) this.mDrawBitmap.get()).eraseColor(0);
        for (ILineDataSet iLineDataSet : this.mChart.getLineData().getDataSets()) {
            if (iLineDataSet.isVisible()) {
                drawDataSet(canvas, iLineDataSet);
            }
        }
        canvas.drawBitmap((Bitmap) this.mDrawBitmap.get(), 0.0f, 0.0f, this.mRenderPaint);
    }

    protected void drawDataSet(Canvas canvas, ILineDataSet iLineDataSet) {
        if (iLineDataSet.getEntryCount() >= 1) {
            this.mRenderPaint.setStrokeWidth(iLineDataSet.getLineWidth());
            this.mRenderPaint.setPathEffect(iLineDataSet.getDashPathEffect());
            switch (iLineDataSet.getMode()) {
                case CUBIC_BEZIER:
                    drawCubicBezier(iLineDataSet);
                    break;
                case HORIZONTAL_BEZIER:
                    drawHorizontalBezier(iLineDataSet);
                    break;
                default:
                    drawLinear(canvas, iLineDataSet);
                    break;
            }
            this.mRenderPaint.setPathEffect(null);
        }
    }

    protected void drawHorizontalBezier(ILineDataSet iLineDataSet) {
        float phaseY = this.mAnimator.getPhaseY();
        Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
        this.mXBounds.set(this.mChart, iLineDataSet);
        this.cubicPath.reset();
        if (this.mXBounds.range >= 1) {
            Entry entryForIndex = iLineDataSet.getEntryForIndex(this.mXBounds.min);
            this.cubicPath.moveTo(entryForIndex.getX(), entryForIndex.getY() * phaseY);
            int i = this.mXBounds.min + 1;
            Entry entry = entryForIndex;
            while (i <= this.mXBounds.range + this.mXBounds.min) {
                Entry entryForIndex2 = iLineDataSet.getEntryForIndex(i);
                float x = ((entryForIndex2.getX() - entry.getX()) / 2.0f) + entry.getX();
                this.cubicPath.cubicTo(x, entry.getY() * phaseY, x, entryForIndex2.getY() * phaseY, entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
                i++;
                entry = entryForIndex2;
            }
        }
        if (iLineDataSet.isDrawFilledEnabled()) {
            this.cubicFillPath.reset();
            this.cubicFillPath.addPath(this.cubicPath);
            drawCubicFill(this.mBitmapCanvas, iLineDataSet, this.cubicFillPath, transformer, this.mXBounds);
        }
        this.mRenderPaint.setColor(iLineDataSet.getColor());
        this.mRenderPaint.setStyle(Style.STROKE);
        transformer.pathValueToPixel(this.cubicPath);
        this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    protected void drawCubicBezier(ILineDataSet iLineDataSet) {
        Math.max(0.0f, Math.min(1.0f, this.mAnimator.getPhaseX()));
        float phaseY = this.mAnimator.getPhaseY();
        Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
        this.mXBounds.set(this.mChart, iLineDataSet);
        float cubicIntensity = iLineDataSet.getCubicIntensity();
        this.cubicPath.reset();
        if (this.mXBounds.range >= 1) {
            int i = this.mXBounds.min + 1;
            int i2 = this.mXBounds.min + this.mXBounds.range;
            Entry entryForIndex = iLineDataSet.getEntryForIndex(Math.max(i - 2, 0));
            Entry entryForIndex2 = iLineDataSet.getEntryForIndex(Math.max(i - 1, 0));
            if (entryForIndex2 != null) {
                this.cubicPath.moveTo(entryForIndex2.getX(), entryForIndex2.getY() * phaseY);
                int i3 = this.mXBounds.min + 1;
                i = -1;
                Entry entry = entryForIndex2;
                Entry entry2 = entryForIndex2;
                entryForIndex2 = entryForIndex;
                entryForIndex = entry2;
                while (i3 <= this.mXBounds.range + this.mXBounds.min) {
                    int i4;
                    Entry entryForIndex3 = i == i3 ? entry : iLineDataSet.getEntryForIndex(i3);
                    if (i3 + 1 < iLineDataSet.getEntryCount()) {
                        i4 = i3 + 1;
                    } else {
                        i4 = i3;
                    }
                    Entry entryForIndex4 = iLineDataSet.getEntryForIndex(i4);
                    this.cubicPath.cubicTo(((entryForIndex3.getX() - entryForIndex2.getX()) * cubicIntensity) + entryForIndex.getX(), (((entryForIndex3.getY() - entryForIndex2.getY()) * cubicIntensity) + entryForIndex.getY()) * phaseY, entryForIndex3.getX() - ((entryForIndex4.getX() - entryForIndex.getX()) * cubicIntensity), (entryForIndex3.getY() - ((entryForIndex4.getY() - entryForIndex.getY()) * cubicIntensity)) * phaseY, entryForIndex3.getX(), entryForIndex3.getY() * phaseY);
                    i3++;
                    entry = entryForIndex4;
                    entryForIndex2 = entryForIndex;
                    entryForIndex = entryForIndex3;
                    i = i4;
                }
            } else {
                return;
            }
        }
        if (iLineDataSet.isDrawFilledEnabled()) {
            this.cubicFillPath.reset();
            this.cubicFillPath.addPath(this.cubicPath);
            drawCubicFill(this.mBitmapCanvas, iLineDataSet, this.cubicFillPath, transformer, this.mXBounds);
        }
        this.mRenderPaint.setColor(iLineDataSet.getColor());
        this.mRenderPaint.setStyle(Style.STROKE);
        transformer.pathValueToPixel(this.cubicPath);
        this.mBitmapCanvas.drawPath(this.cubicPath, this.mRenderPaint);
        this.mRenderPaint.setPathEffect(null);
    }

    protected void drawCubicFill(Canvas canvas, ILineDataSet iLineDataSet, Path path, Transformer transformer, XBounds xBounds) {
        float fillLinePosition = iLineDataSet.getFillFormatter().getFillLinePosition(iLineDataSet, this.mChart);
        path.lineTo(iLineDataSet.getEntryForIndex(xBounds.min + xBounds.range).getX(), fillLinePosition);
        path.lineTo(iLineDataSet.getEntryForIndex(xBounds.min).getX(), fillLinePosition);
        path.close();
        transformer.pathValueToPixel(path);
        Drawable fillDrawable = iLineDataSet.getFillDrawable();
        if (fillDrawable != null) {
            drawFilledPath(canvas, path, fillDrawable);
        } else {
            drawFilledPath(canvas, path, iLineDataSet.getFillColor(), iLineDataSet.getFillAlpha());
        }
    }

    protected void drawLinear(Canvas canvas, ILineDataSet iLineDataSet) {
        Canvas canvas2;
        int entryCount = iLineDataSet.getEntryCount();
        boolean isDrawSteppedEnabled = iLineDataSet.isDrawSteppedEnabled();
        int i = isDrawSteppedEnabled ? 4 : 2;
        Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        this.mRenderPaint.setStyle(Style.STROKE);
        if (iLineDataSet.isDashedLineEnabled()) {
            canvas2 = this.mBitmapCanvas;
        } else {
            canvas2 = canvas;
        }
        this.mXBounds.set(this.mChart, iLineDataSet);
        if (iLineDataSet.isDrawFilledEnabled() && entryCount > 0) {
            drawLinearFill(canvas, iLineDataSet, transformer, this.mXBounds);
        }
        if (iLineDataSet.getColors().size() > 1) {
            if (this.mLineBuffer.length <= i * 2) {
                this.mLineBuffer = new float[(i * 4)];
            }
            for (entryCount = this.mXBounds.min; entryCount <= this.mXBounds.range + this.mXBounds.min; entryCount++) {
                Entry entryForIndex = iLineDataSet.getEntryForIndex(entryCount);
                if (entryForIndex != null) {
                    this.mLineBuffer[0] = entryForIndex.getX();
                    this.mLineBuffer[1] = entryForIndex.getY() * phaseY;
                    if (entryCount < this.mXBounds.max) {
                        entryForIndex = iLineDataSet.getEntryForIndex(entryCount + 1);
                        if (entryForIndex == null) {
                            break;
                        } else if (isDrawSteppedEnabled) {
                            this.mLineBuffer[2] = entryForIndex.getX();
                            this.mLineBuffer[3] = this.mLineBuffer[1];
                            this.mLineBuffer[4] = this.mLineBuffer[2];
                            this.mLineBuffer[5] = this.mLineBuffer[3];
                            this.mLineBuffer[6] = entryForIndex.getX();
                            this.mLineBuffer[7] = entryForIndex.getY() * phaseY;
                        } else {
                            this.mLineBuffer[2] = entryForIndex.getX();
                            this.mLineBuffer[3] = entryForIndex.getY() * phaseY;
                        }
                    } else {
                        this.mLineBuffer[2] = this.mLineBuffer[0];
                        this.mLineBuffer[3] = this.mLineBuffer[1];
                    }
                    transformer.pointValuesToPixel(this.mLineBuffer);
                    if (!this.mViewPortHandler.isInBoundsRight(this.mLineBuffer[0])) {
                        break;
                    } else if (this.mViewPortHandler.isInBoundsLeft(this.mLineBuffer[2]) && (this.mViewPortHandler.isInBoundsTop(this.mLineBuffer[1]) || this.mViewPortHandler.isInBoundsBottom(this.mLineBuffer[3]))) {
                        this.mRenderPaint.setColor(iLineDataSet.getColor(entryCount));
                        canvas2.drawLines(this.mLineBuffer, 0, i * 2, this.mRenderPaint);
                    }
                }
            }
        } else {
            if (this.mLineBuffer.length < Math.max(entryCount * i, i) * 2) {
                this.mLineBuffer = new float[(Math.max(entryCount * i, i) * 4)];
            }
            if (iLineDataSet.getEntryForIndex(this.mXBounds.min) != null) {
                int i2 = 0;
                entryCount = this.mXBounds.min;
                while (entryCount <= this.mXBounds.range + this.mXBounds.min) {
                    Entry entryForIndex2 = iLineDataSet.getEntryForIndex(entryCount == 0 ? 0 : entryCount - 1);
                    Entry entryForIndex3 = iLineDataSet.getEntryForIndex(entryCount);
                    if (!(entryForIndex2 == null || entryForIndex3 == null)) {
                        int i3;
                        int i4 = i2 + 1;
                        this.mLineBuffer[i2] = entryForIndex2.getX();
                        i2 = i4 + 1;
                        this.mLineBuffer[i4] = entryForIndex2.getY() * phaseY;
                        if (isDrawSteppedEnabled) {
                            i4 = i2 + 1;
                            this.mLineBuffer[i2] = entryForIndex3.getX();
                            i3 = i4 + 1;
                            this.mLineBuffer[i4] = entryForIndex2.getY() * phaseY;
                            i4 = i3 + 1;
                            this.mLineBuffer[i3] = entryForIndex3.getX();
                            i2 = i4 + 1;
                            this.mLineBuffer[i4] = entryForIndex2.getY() * phaseY;
                        }
                        i3 = i2 + 1;
                        this.mLineBuffer[i2] = entryForIndex3.getX();
                        i2 = i3 + 1;
                        this.mLineBuffer[i3] = entryForIndex3.getY() * phaseY;
                    }
                    entryCount++;
                }
                if (i2 > 0) {
                    transformer.pointValuesToPixel(this.mLineBuffer);
                    i = Math.max((this.mXBounds.range + 1) * i, i) * 2;
                    this.mRenderPaint.setColor(iLineDataSet.getColor());
                    canvas2.drawLines(this.mLineBuffer, 0, i, this.mRenderPaint);
                }
            }
        }
        this.mRenderPaint.setPathEffect(null);
    }

    protected void drawLinearFill(Canvas canvas, ILineDataSet iLineDataSet, Transformer transformer, XBounds xBounds) {
        Path path = this.mGenerateFilledPathBuffer;
        int i = xBounds.min;
        int i2 = xBounds.min + xBounds.range;
        int i3 = 0;
        int i4;
        int i5;
        do {
            i4 = i + (i3 * 128);
            i5 = i4 + 128;
            if (i5 > i2) {
                i5 = i2;
            }
            if (i4 <= i5) {
                generateFilledPath(iLineDataSet, i4, i5, path);
                transformer.pathValueToPixel(path);
                Drawable fillDrawable = iLineDataSet.getFillDrawable();
                if (fillDrawable != null) {
                    drawFilledPath(canvas, path, fillDrawable);
                } else {
                    drawFilledPath(canvas, path, iLineDataSet.getFillColor(), iLineDataSet.getFillAlpha());
                }
            }
            i3++;
        } while (i4 <= i5);
    }

    private void generateFilledPath(ILineDataSet iLineDataSet, int i, int i2, Path path) {
        Entry entry = null;
        float fillLinePosition = iLineDataSet.getFillFormatter().getFillLinePosition(iLineDataSet, this.mChart);
        float phaseY = this.mAnimator.getPhaseY();
        Object obj = iLineDataSet.getMode() == Mode.STEPPED ? 1 : null;
        path.reset();
        Entry entryForIndex = iLineDataSet.getEntryForIndex(i);
        path.moveTo(entryForIndex.getX(), fillLinePosition);
        path.lineTo(entryForIndex.getX(), entryForIndex.getY() * phaseY);
        int i3 = i + 1;
        Entry entry2 = null;
        while (i3 <= i2) {
            entry2 = iLineDataSet.getEntryForIndex(i3);
            if (!(obj == null || entry == null)) {
                path.lineTo(entry2.getX(), entry.getY() * phaseY);
            }
            path.lineTo(entry2.getX(), entry2.getY() * phaseY);
            i3++;
            entry = entry2;
        }
        if (entry2 != null) {
            path.lineTo(entry2.getX(), fillLinePosition);
        }
        path.close();
    }

    public void drawValues(Canvas canvas) {
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getLineData().getDataSets();
            for (int i = 0; i < dataSets.size(); i++) {
                ILineDataSet iLineDataSet = (ILineDataSet) dataSets.get(i);
                if (shouldDrawValues(iLineDataSet)) {
                    int i2;
                    applyValueTextStyle(iLineDataSet);
                    Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
                    int circleRadius = (int) (iLineDataSet.getCircleRadius() * 1.75f);
                    if (iLineDataSet.isDrawCirclesEnabled()) {
                        i2 = circleRadius;
                    } else {
                        i2 = circleRadius / 2;
                    }
                    this.mXBounds.set(this.mChart, iLineDataSet);
                    float[] generateTransformedValuesLine = transformer.generateTransformedValuesLine(iLineDataSet, this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY(), this.mXBounds.min, this.mXBounds.max);
                    MPPointF instance = MPPointF.getInstance(iLineDataSet.getIconsOffset());
                    instance.x = Utils.convertDpToPixel(instance.x);
                    instance.y = Utils.convertDpToPixel(instance.y);
                    for (int i3 = 0; i3 < generateTransformedValuesLine.length; i3 += 2) {
                        float f = generateTransformedValuesLine[i3];
                        float f2 = generateTransformedValuesLine[i3 + 1];
                        if (!this.mViewPortHandler.isInBoundsRight(f)) {
                            break;
                        }
                        if (this.mViewPortHandler.isInBoundsLeft(f) && this.mViewPortHandler.isInBoundsY(f2)) {
                            Entry entryForIndex = iLineDataSet.getEntryForIndex((i3 / 2) + this.mXBounds.min);
                            if (iLineDataSet.isDrawValuesEnabled()) {
                                drawValue(canvas, iLineDataSet.getValueFormatter(), entryForIndex.getY(), entryForIndex, i, f, f2 - ((float) i2), iLineDataSet.getValueTextColor(i3 / 2));
                            }
                            if (entryForIndex.getIcon() != null && iLineDataSet.isDrawIconsEnabled()) {
                                Drawable icon = entryForIndex.getIcon();
                                Utils.drawImage(canvas, icon, (int) (instance.x + f), (int) (instance.y + f2), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                            }
                        }
                    }
                    MPPointF.recycleInstance(instance);
                }
            }
        }
    }

    public void drawExtras(Canvas canvas) {
        drawCircles(canvas);
    }

    protected void drawCircles(Canvas canvas) {
        this.mRenderPaint.setStyle(Style.FILL);
        float phaseY = this.mAnimator.getPhaseY();
        this.mCirclesBuffer[0] = 0.0f;
        this.mCirclesBuffer[1] = 0.0f;
        List dataSets = this.mChart.getLineData().getDataSets();
        for (int i = 0; i < dataSets.size(); i++) {
            ILineDataSet iLineDataSet = (ILineDataSet) dataSets.get(i);
            if (iLineDataSet.isVisible() && iLineDataSet.isDrawCirclesEnabled() && iLineDataSet.getEntryCount() != 0) {
                DataSetImageCache dataSetImageCache;
                this.mCirclePaintInner.setColor(iLineDataSet.getCircleHoleColor());
                Transformer transformer = this.mChart.getTransformer(iLineDataSet.getAxisDependency());
                this.mXBounds.set(this.mChart, iLineDataSet);
                float circleRadius = iLineDataSet.getCircleRadius();
                float circleHoleRadius = iLineDataSet.getCircleHoleRadius();
                boolean z = iLineDataSet.isDrawCircleHoleEnabled() && circleHoleRadius < circleRadius && circleHoleRadius > 0.0f;
                boolean z2 = z && iLineDataSet.getCircleHoleColor() == ColorTemplate.COLOR_NONE;
                if (this.mImageCaches.containsKey(iLineDataSet)) {
                    dataSetImageCache = (DataSetImageCache) this.mImageCaches.get(iLineDataSet);
                } else {
                    dataSetImageCache = new DataSetImageCache();
                    this.mImageCaches.put(iLineDataSet, dataSetImageCache);
                }
                if (dataSetImageCache.init(iLineDataSet)) {
                    dataSetImageCache.fill(iLineDataSet, z, z2);
                }
                int i2 = this.mXBounds.min + this.mXBounds.range;
                for (int i3 = this.mXBounds.min; i3 <= i2; i3++) {
                    Entry entryForIndex = iLineDataSet.getEntryForIndex(i3);
                    if (entryForIndex == null) {
                        break;
                    }
                    this.mCirclesBuffer[0] = entryForIndex.getX();
                    this.mCirclesBuffer[1] = entryForIndex.getY() * phaseY;
                    transformer.pointValuesToPixel(this.mCirclesBuffer);
                    if (!this.mViewPortHandler.isInBoundsRight(this.mCirclesBuffer[0])) {
                        break;
                    }
                    if (this.mViewPortHandler.isInBoundsLeft(this.mCirclesBuffer[0]) && this.mViewPortHandler.isInBoundsY(this.mCirclesBuffer[1])) {
                        Bitmap bitmap = dataSetImageCache.getBitmap(i3);
                        if (bitmap != null) {
                            canvas.drawBitmap(bitmap, this.mCirclesBuffer[0] - circleRadius, this.mCirclesBuffer[1] - circleRadius, null);
                        }
                    }
                }
            }
        }
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        LineData lineData = this.mChart.getLineData();
        for (Highlight highlight : highlightArr) {
            ILineDataSet iLineDataSet = (ILineDataSet) lineData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iLineDataSet != null && iLineDataSet.isHighlightEnabled()) {
                Entry entryForXValue = iLineDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(entryForXValue, iLineDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iLineDataSet.getAxisDependency()).getPixelForValues(entryForXValue.getX(), entryForXValue.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                    drawHighlightLines(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iLineDataSet);
                }
            }
        }
    }

    public void setBitmapConfig(Config config) {
        this.mBitmapConfig = config;
        releaseBitmap();
    }

    public Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public void releaseBitmap() {
        if (this.mBitmapCanvas != null) {
            this.mBitmapCanvas.setBitmap(null);
            this.mBitmapCanvas = null;
        }
        if (this.mDrawBitmap != null) {
            ((Bitmap) this.mDrawBitmap.get()).recycle();
            this.mDrawBitmap.clear();
            this.mDrawBitmap = null;
        }
    }
}
