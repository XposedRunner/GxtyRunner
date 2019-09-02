package com.github.mikephil.charting.charts;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.jobs.AnimatedMoveViewJob;
import com.github.mikephil.charting.jobs.AnimatedZoomJob;
import com.github.mikephil.charting.jobs.MoveViewJob;
import com.github.mikephil.charting.jobs.ZoomJob;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.listener.OnDrawListener;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;

@SuppressLint({"RtlHardcoded"})
public abstract class BarLineChartBase<T extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> extends Chart<T> implements BarLineScatterCandleBubbleDataProvider {
    private long drawCycles;
    protected boolean mAutoScaleMinMaxEnabled;
    protected YAxis mAxisLeft;
    protected YAxisRenderer mAxisRendererLeft;
    protected YAxisRenderer mAxisRendererRight;
    protected YAxis mAxisRight;
    protected Paint mBorderPaint;
    protected boolean mClipValuesToContent;
    private boolean mCustomViewPortEnabled;
    protected boolean mDoubleTapToZoomEnabled;
    private boolean mDragEnabled;
    protected boolean mDrawBorders;
    protected boolean mDrawGridBackground;
    protected OnDrawListener mDrawListener;
    protected Matrix mFitScreenMatrixBuffer;
    protected float[] mGetPositionBuffer;
    protected Paint mGridBackgroundPaint;
    protected boolean mHighlightPerDragEnabled;
    protected boolean mKeepPositionOnRotation;
    protected Transformer mLeftAxisTransformer;
    protected int mMaxVisibleCount;
    protected float mMinOffset;
    private RectF mOffsetsBuffer;
    protected float[] mOnSizeChangedBuffer;
    protected boolean mPinchZoomEnabled;
    protected Transformer mRightAxisTransformer;
    private boolean mScaleXEnabled;
    private boolean mScaleYEnabled;
    protected XAxisRenderer mXAxisRenderer;
    protected Matrix mZoomMatrixBuffer;
    protected MPPointD posForGetHighestVisibleX;
    protected MPPointD posForGetLowestVisibleX;
    private long totalTime;

    public /* bridge */ /* synthetic */ BarLineScatterCandleBubbleData getData() {
        return (BarLineScatterCandleBubbleData) super.getData();
    }

    public BarLineChartBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxVisibleCount = 100;
        this.mAutoScaleMinMaxEnabled = false;
        this.mPinchZoomEnabled = false;
        this.mDoubleTapToZoomEnabled = true;
        this.mHighlightPerDragEnabled = true;
        this.mDragEnabled = true;
        this.mScaleXEnabled = true;
        this.mScaleYEnabled = true;
        this.mDrawGridBackground = false;
        this.mDrawBorders = false;
        this.mClipValuesToContent = false;
        this.mMinOffset = 15.0f;
        this.mKeepPositionOnRotation = false;
        this.totalTime = 0;
        this.drawCycles = 0;
        this.mOffsetsBuffer = new RectF();
        this.mZoomMatrixBuffer = new Matrix();
        this.mFitScreenMatrixBuffer = new Matrix();
        this.mCustomViewPortEnabled = false;
        this.mGetPositionBuffer = new float[2];
        this.posForGetLowestVisibleX = MPPointD.getInstance(Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON);
        this.posForGetHighestVisibleX = MPPointD.getInstance(Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON);
        this.mOnSizeChangedBuffer = new float[2];
    }

    public BarLineChartBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxVisibleCount = 100;
        this.mAutoScaleMinMaxEnabled = false;
        this.mPinchZoomEnabled = false;
        this.mDoubleTapToZoomEnabled = true;
        this.mHighlightPerDragEnabled = true;
        this.mDragEnabled = true;
        this.mScaleXEnabled = true;
        this.mScaleYEnabled = true;
        this.mDrawGridBackground = false;
        this.mDrawBorders = false;
        this.mClipValuesToContent = false;
        this.mMinOffset = 15.0f;
        this.mKeepPositionOnRotation = false;
        this.totalTime = 0;
        this.drawCycles = 0;
        this.mOffsetsBuffer = new RectF();
        this.mZoomMatrixBuffer = new Matrix();
        this.mFitScreenMatrixBuffer = new Matrix();
        this.mCustomViewPortEnabled = false;
        this.mGetPositionBuffer = new float[2];
        this.posForGetLowestVisibleX = MPPointD.getInstance(Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON);
        this.posForGetHighestVisibleX = MPPointD.getInstance(Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON);
        this.mOnSizeChangedBuffer = new float[2];
    }

    public BarLineChartBase(Context context) {
        super(context);
        this.mMaxVisibleCount = 100;
        this.mAutoScaleMinMaxEnabled = false;
        this.mPinchZoomEnabled = false;
        this.mDoubleTapToZoomEnabled = true;
        this.mHighlightPerDragEnabled = true;
        this.mDragEnabled = true;
        this.mScaleXEnabled = true;
        this.mScaleYEnabled = true;
        this.mDrawGridBackground = false;
        this.mDrawBorders = false;
        this.mClipValuesToContent = false;
        this.mMinOffset = 15.0f;
        this.mKeepPositionOnRotation = false;
        this.totalTime = 0;
        this.drawCycles = 0;
        this.mOffsetsBuffer = new RectF();
        this.mZoomMatrixBuffer = new Matrix();
        this.mFitScreenMatrixBuffer = new Matrix();
        this.mCustomViewPortEnabled = false;
        this.mGetPositionBuffer = new float[2];
        this.posForGetLowestVisibleX = MPPointD.getInstance(Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON);
        this.posForGetHighestVisibleX = MPPointD.getInstance(Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON);
        this.mOnSizeChangedBuffer = new float[2];
    }

    protected void init() {
        super.init();
        this.mAxisLeft = new YAxis(AxisDependency.LEFT);
        this.mAxisRight = new YAxis(AxisDependency.RIGHT);
        this.mLeftAxisTransformer = new Transformer(this.mViewPortHandler);
        this.mRightAxisTransformer = new Transformer(this.mViewPortHandler);
        this.mAxisRendererLeft = new YAxisRenderer(this.mViewPortHandler, this.mAxisLeft, this.mLeftAxisTransformer);
        this.mAxisRendererRight = new YAxisRenderer(this.mViewPortHandler, this.mAxisRight, this.mRightAxisTransformer);
        this.mXAxisRenderer = new XAxisRenderer(this.mViewPortHandler, this.mXAxis, this.mLeftAxisTransformer);
        setHighlighter(new ChartHighlighter(this));
        this.mChartTouchListener = new BarLineChartTouchListener(this, this.mViewPortHandler.getMatrixTouch(), 3.0f);
        this.mGridBackgroundPaint = new Paint();
        this.mGridBackgroundPaint.setStyle(Style.FILL);
        this.mGridBackgroundPaint.setColor(Color.rgb(GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN, GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN, GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN));
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setStyle(Style.STROKE);
        this.mBorderPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(1.0f));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mData != null) {
            long currentTimeMillis = System.currentTimeMillis();
            drawGridBackground(canvas);
            if (this.mAutoScaleMinMaxEnabled) {
                autoScale();
            }
            if (this.mAxisLeft.isEnabled()) {
                this.mAxisRendererLeft.computeAxis(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisMaximum, this.mAxisLeft.isInverted());
            }
            if (this.mAxisRight.isEnabled()) {
                this.mAxisRendererRight.computeAxis(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisMaximum, this.mAxisRight.isInverted());
            }
            if (this.mXAxis.isEnabled()) {
                this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false);
            }
            this.mXAxisRenderer.renderAxisLine(canvas);
            this.mAxisRendererLeft.renderAxisLine(canvas);
            this.mAxisRendererRight.renderAxisLine(canvas);
            this.mXAxisRenderer.renderGridLines(canvas);
            this.mAxisRendererLeft.renderGridLines(canvas);
            this.mAxisRendererRight.renderGridLines(canvas);
            if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mXAxisRenderer.renderLimitLines(canvas);
            }
            if (this.mAxisLeft.isEnabled() && this.mAxisLeft.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererLeft.renderLimitLines(canvas);
            }
            if (this.mAxisRight.isEnabled() && this.mAxisRight.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererRight.renderLimitLines(canvas);
            }
            int save = canvas.save();
            canvas.clipRect(this.mViewPortHandler.getContentRect());
            this.mRenderer.drawData(canvas);
            if (valuesToHighlight()) {
                this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
            }
            canvas.restoreToCount(save);
            this.mRenderer.drawExtras(canvas);
            if (this.mXAxis.isEnabled() && !this.mXAxis.isDrawLimitLinesBehindDataEnabled()) {
                this.mXAxisRenderer.renderLimitLines(canvas);
            }
            if (this.mAxisLeft.isEnabled() && !this.mAxisLeft.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererLeft.renderLimitLines(canvas);
            }
            if (this.mAxisRight.isEnabled() && !this.mAxisRight.isDrawLimitLinesBehindDataEnabled()) {
                this.mAxisRendererRight.renderLimitLines(canvas);
            }
            this.mXAxisRenderer.renderAxisLabels(canvas);
            this.mAxisRendererLeft.renderAxisLabels(canvas);
            this.mAxisRendererRight.renderAxisLabels(canvas);
            if (isClipValuesToContentEnabled()) {
                save = canvas.save();
                canvas.clipRect(this.mViewPortHandler.getContentRect());
                this.mRenderer.drawValues(canvas);
                canvas.restoreToCount(save);
            } else {
                this.mRenderer.drawValues(canvas);
            }
            this.mLegendRenderer.renderLegend(canvas);
            drawDescription(canvas);
            drawMarkers(canvas);
            if (this.mLogEnabled) {
                currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                this.totalTime += currentTimeMillis;
                this.drawCycles++;
                Log.i(Chart.LOG_TAG, "Drawtime: " + currentTimeMillis + " ms, average: " + (this.totalTime / this.drawCycles) + " ms, cycles: " + this.drawCycles);
            }
        }
    }

    public void resetTracking() {
        this.totalTime = 0;
        this.drawCycles = 0;
    }

    protected void prepareValuePxMatrix() {
        if (this.mLogEnabled) {
            Log.i(Chart.LOG_TAG, "Preparing Value-Px Matrix, xmin: " + this.mXAxis.mAxisMinimum + ", xmax: " + this.mXAxis.mAxisMaximum + ", xdelta: " + this.mXAxis.mAxisRange);
        }
        this.mRightAxisTransformer.prepareMatrixValuePx(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisRange, this.mAxisRight.mAxisRange, this.mAxisRight.mAxisMinimum);
        this.mLeftAxisTransformer.prepareMatrixValuePx(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisRange, this.mAxisLeft.mAxisRange, this.mAxisLeft.mAxisMinimum);
    }

    protected void prepareOffsetMatrix() {
        this.mRightAxisTransformer.prepareMatrixOffset(this.mAxisRight.isInverted());
        this.mLeftAxisTransformer.prepareMatrixOffset(this.mAxisLeft.isInverted());
    }

    public void notifyDataSetChanged() {
        if (this.mData != null) {
            if (this.mLogEnabled) {
                Log.i(Chart.LOG_TAG, "Preparing...");
            }
            if (this.mRenderer != null) {
                this.mRenderer.initBuffers();
            }
            calcMinMax();
            this.mAxisRendererLeft.computeAxis(this.mAxisLeft.mAxisMinimum, this.mAxisLeft.mAxisMaximum, this.mAxisLeft.isInverted());
            this.mAxisRendererRight.computeAxis(this.mAxisRight.mAxisMinimum, this.mAxisRight.mAxisMaximum, this.mAxisRight.isInverted());
            this.mXAxisRenderer.computeAxis(this.mXAxis.mAxisMinimum, this.mXAxis.mAxisMaximum, false);
            if (this.mLegend != null) {
                this.mLegendRenderer.computeLegend(this.mData);
            }
            calculateOffsets();
        } else if (this.mLogEnabled) {
            Log.i(Chart.LOG_TAG, "Preparing... DATA NOT SET.");
        }
    }

    protected void autoScale() {
        ((BarLineScatterCandleBubbleData) this.mData).calcMinMaxY(getLowestVisibleX(), getHighestVisibleX());
        this.mXAxis.calculate(((BarLineScatterCandleBubbleData) this.mData).getXMin(), ((BarLineScatterCandleBubbleData) this.mData).getXMax());
        if (this.mAxisLeft.isEnabled()) {
            this.mAxisLeft.calculate(((BarLineScatterCandleBubbleData) this.mData).getYMin(AxisDependency.LEFT), ((BarLineScatterCandleBubbleData) this.mData).getYMax(AxisDependency.LEFT));
        }
        if (this.mAxisRight.isEnabled()) {
            this.mAxisRight.calculate(((BarLineScatterCandleBubbleData) this.mData).getYMin(AxisDependency.RIGHT), ((BarLineScatterCandleBubbleData) this.mData).getYMax(AxisDependency.RIGHT));
        }
        calculateOffsets();
    }

    protected void calcMinMax() {
        this.mXAxis.calculate(((BarLineScatterCandleBubbleData) this.mData).getXMin(), ((BarLineScatterCandleBubbleData) this.mData).getXMax());
        this.mAxisLeft.calculate(((BarLineScatterCandleBubbleData) this.mData).getYMin(AxisDependency.LEFT), ((BarLineScatterCandleBubbleData) this.mData).getYMax(AxisDependency.LEFT));
        this.mAxisRight.calculate(((BarLineScatterCandleBubbleData) this.mData).getYMin(AxisDependency.RIGHT), ((BarLineScatterCandleBubbleData) this.mData).getYMax(AxisDependency.RIGHT));
    }

    protected void calculateLegendOffsets(RectF rectF) {
        rectF.left = 0.0f;
        rectF.right = 0.0f;
        rectF.top = 0.0f;
        rectF.bottom = 0.0f;
        if (this.mLegend != null && this.mLegend.isEnabled() && !this.mLegend.isDrawInsideEnabled()) {
            switch (this.mLegend.getOrientation()) {
                case VERTICAL:
                    switch (this.mLegend.getHorizontalAlignment()) {
                        case LEFT:
                            rectF.left += Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + this.mLegend.getXOffset();
                            return;
                        case RIGHT:
                            rectF.right += Math.min(this.mLegend.mNeededWidth, this.mViewPortHandler.getChartWidth() * this.mLegend.getMaxSizePercent()) + this.mLegend.getXOffset();
                            return;
                        case CENTER:
                            switch (this.mLegend.getVerticalAlignment()) {
                                case TOP:
                                    rectF.top += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
                                    return;
                                case BOTTOM:
                                    rectF.bottom += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
                                    return;
                                default:
                                    return;
                            }
                        default:
                            return;
                    }
                case HORIZONTAL:
                    switch (this.mLegend.getVerticalAlignment()) {
                        case TOP:
                            rectF.top += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
                            if (getXAxis().isEnabled() && getXAxis().isDrawLabelsEnabled()) {
                                rectF.top += (float) getXAxis().mLabelRotatedHeight;
                                return;
                            }
                            return;
                        case BOTTOM:
                            rectF.bottom += Math.min(this.mLegend.mNeededHeight, this.mViewPortHandler.getChartHeight() * this.mLegend.getMaxSizePercent()) + this.mLegend.getYOffset();
                            if (getXAxis().isEnabled() && getXAxis().isDrawLabelsEnabled()) {
                                rectF.bottom += (float) getXAxis().mLabelRotatedHeight;
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    public void calculateOffsets() {
        if (!this.mCustomViewPortEnabled) {
            float yOffset;
            calculateLegendOffsets(this.mOffsetsBuffer);
            float f = this.mOffsetsBuffer.left + 0.0f;
            float f2 = 0.0f + this.mOffsetsBuffer.top;
            float f3 = this.mOffsetsBuffer.right + 0.0f;
            float f4 = this.mOffsetsBuffer.bottom + 0.0f;
            if (this.mAxisLeft.needsOffset()) {
                f += this.mAxisLeft.getRequiredWidthSpace(this.mAxisRendererLeft.getPaintAxisLabels());
            }
            if (this.mAxisRight.needsOffset()) {
                f3 += this.mAxisRight.getRequiredWidthSpace(this.mAxisRendererRight.getPaintAxisLabels());
            }
            if (this.mXAxis.isEnabled() && this.mXAxis.isDrawLabelsEnabled()) {
                yOffset = ((float) this.mXAxis.mLabelRotatedHeight) + this.mXAxis.getYOffset();
                if (this.mXAxis.getPosition() == XAxisPosition.BOTTOM) {
                    f4 += yOffset;
                } else if (this.mXAxis.getPosition() == XAxisPosition.TOP) {
                    f2 += yOffset;
                } else if (this.mXAxis.getPosition() == XAxisPosition.BOTH_SIDED) {
                    f4 += yOffset;
                    f2 += yOffset;
                }
            }
            f2 += getExtraTopOffset();
            f3 += getExtraRightOffset();
            f4 += getExtraBottomOffset();
            f += getExtraLeftOffset();
            yOffset = Utils.convertDpToPixel(this.mMinOffset);
            this.mViewPortHandler.restrainViewPort(Math.max(yOffset, f), Math.max(yOffset, f2), Math.max(yOffset, f3), Math.max(yOffset, f4));
            if (this.mLogEnabled) {
                Log.i(Chart.LOG_TAG, "offsetLeft: " + f + ", offsetTop: " + f2 + ", offsetRight: " + f3 + ", offsetBottom: " + f4);
                Log.i(Chart.LOG_TAG, "Content: " + this.mViewPortHandler.getContentRect().toString());
            }
        }
        prepareOffsetMatrix();
        prepareValuePxMatrix();
    }

    protected void drawGridBackground(Canvas canvas) {
        if (this.mDrawGridBackground) {
            canvas.drawRect(this.mViewPortHandler.getContentRect(), this.mGridBackgroundPaint);
        }
        if (this.mDrawBorders) {
            canvas.drawRect(this.mViewPortHandler.getContentRect(), this.mBorderPaint);
        }
    }

    public Transformer getTransformer(AxisDependency axisDependency) {
        if (axisDependency == AxisDependency.LEFT) {
            return this.mLeftAxisTransformer;
        }
        return this.mRightAxisTransformer;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        if (this.mChartTouchListener == null || this.mData == null || !this.mTouchEnabled) {
            return false;
        }
        return this.mChartTouchListener.onTouch(this, motionEvent);
    }

    public void computeScroll() {
        if (this.mChartTouchListener instanceof BarLineChartTouchListener) {
            ((BarLineChartTouchListener) this.mChartTouchListener).computeScroll();
        }
    }

    public void zoomIn() {
        MPPointF contentCenter = this.mViewPortHandler.getContentCenter();
        this.mViewPortHandler.zoomIn(contentCenter.x, -contentCenter.y, this.mZoomMatrixBuffer);
        this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
        MPPointF.recycleInstance(contentCenter);
        calculateOffsets();
        postInvalidate();
    }

    public void zoomOut() {
        MPPointF contentCenter = this.mViewPortHandler.getContentCenter();
        this.mViewPortHandler.zoomOut(contentCenter.x, -contentCenter.y, this.mZoomMatrixBuffer);
        this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
        MPPointF.recycleInstance(contentCenter);
        calculateOffsets();
        postInvalidate();
    }

    public void resetZoom() {
        this.mViewPortHandler.resetZoom(this.mZoomMatrixBuffer);
        this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
        calculateOffsets();
        postInvalidate();
    }

    public void zoom(float f, float f2, float f3, float f4) {
        this.mViewPortHandler.zoom(f, f2, f3, -f4, this.mZoomMatrixBuffer);
        this.mViewPortHandler.refresh(this.mZoomMatrixBuffer, this, false);
        calculateOffsets();
        postInvalidate();
    }

    public void zoom(float f, float f2, float f3, float f4, AxisDependency axisDependency) {
        addViewportJob(ZoomJob.getInstance(this.mViewPortHandler, f, f2, f3, f4, getTransformer(axisDependency), axisDependency, this));
    }

    public void zoomToCenter(float f, float f2) {
        MPPointF centerOffsets = getCenterOffsets();
        Matrix matrix = this.mZoomMatrixBuffer;
        this.mViewPortHandler.zoom(f, f2, centerOffsets.x, -centerOffsets.y, matrix);
        this.mViewPortHandler.refresh(matrix, this, false);
    }

    @TargetApi(11)
    public void zoomAndCenterAnimated(float f, float f2, float f3, float f4, AxisDependency axisDependency, long j) {
        if (VERSION.SDK_INT >= 11) {
            MPPointD valuesByTouchPoint = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axisDependency);
            addViewportJob(AnimatedZoomJob.getInstance(this.mViewPortHandler, this, getTransformer(axisDependency), getAxis(axisDependency), this.mXAxis.mAxisRange, f, f2, this.mViewPortHandler.getScaleX(), this.mViewPortHandler.getScaleY(), f3, f4, (float) valuesByTouchPoint.x, (float) valuesByTouchPoint.y, j));
            MPPointD.recycleInstance(valuesByTouchPoint);
            return;
        }
        Log.e(Chart.LOG_TAG, "Unable to execute zoomAndCenterAnimated(...) on API level < 11");
    }

    public void fitScreen() {
        Matrix matrix = this.mFitScreenMatrixBuffer;
        this.mViewPortHandler.fitScreen(matrix);
        this.mViewPortHandler.refresh(matrix, this, false);
        calculateOffsets();
        postInvalidate();
    }

    public void setScaleMinima(float f, float f2) {
        this.mViewPortHandler.setMinimumScaleX(f);
        this.mViewPortHandler.setMinimumScaleY(f2);
    }

    public void setVisibleXRangeMaximum(float f) {
        this.mViewPortHandler.setMinimumScaleX(this.mXAxis.mAxisRange / f);
    }

    public void setVisibleXRangeMinimum(float f) {
        this.mViewPortHandler.setMaximumScaleX(this.mXAxis.mAxisRange / f);
    }

    public void setVisibleXRange(float f, float f2) {
        this.mViewPortHandler.setMinMaxScaleX(this.mXAxis.mAxisRange / f, this.mXAxis.mAxisRange / f2);
    }

    public void setVisibleYRangeMaximum(float f, AxisDependency axisDependency) {
        this.mViewPortHandler.setMinimumScaleY(getAxisRange(axisDependency) / f);
    }

    public void setVisibleYRangeMinimum(float f, AxisDependency axisDependency) {
        this.mViewPortHandler.setMaximumScaleY(getAxisRange(axisDependency) / f);
    }

    public void setVisibleYRange(float f, float f2, AxisDependency axisDependency) {
        this.mViewPortHandler.setMinMaxScaleY(getAxisRange(axisDependency) / f, getAxisRange(axisDependency) / f2);
    }

    public void moveViewToX(float f) {
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, f, 0.0f, getTransformer(AxisDependency.LEFT), this));
    }

    public void moveViewTo(float f, float f2, AxisDependency axisDependency) {
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, f, ((getAxisRange(axisDependency) / this.mViewPortHandler.getScaleY()) / 2.0f) + f2, getTransformer(axisDependency), this));
    }

    @TargetApi(11)
    public void moveViewToAnimated(float f, float f2, AxisDependency axisDependency, long j) {
        if (VERSION.SDK_INT >= 11) {
            MPPointD valuesByTouchPoint = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axisDependency);
            float f3 = f;
            addViewportJob(AnimatedMoveViewJob.getInstance(this.mViewPortHandler, f3, f2 + ((getAxisRange(axisDependency) / this.mViewPortHandler.getScaleY()) / 2.0f), getTransformer(axisDependency), this, (float) valuesByTouchPoint.x, (float) valuesByTouchPoint.y, j));
            MPPointD.recycleInstance(valuesByTouchPoint);
            return;
        }
        Log.e(Chart.LOG_TAG, "Unable to execute moveViewToAnimated(...) on API level < 11");
    }

    public void centerViewToY(float f, AxisDependency axisDependency) {
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, 0.0f, ((getAxisRange(axisDependency) / this.mViewPortHandler.getScaleY()) / 2.0f) + f, getTransformer(axisDependency), this));
    }

    public void centerViewTo(float f, float f2, AxisDependency axisDependency) {
        float scaleX = getXAxis().mAxisRange / this.mViewPortHandler.getScaleX();
        addViewportJob(MoveViewJob.getInstance(this.mViewPortHandler, f - (scaleX / 2.0f), ((getAxisRange(axisDependency) / this.mViewPortHandler.getScaleY()) / 2.0f) + f2, getTransformer(axisDependency), this));
    }

    @TargetApi(11)
    public void centerViewToAnimated(float f, float f2, AxisDependency axisDependency, long j) {
        if (VERSION.SDK_INT >= 11) {
            MPPointD valuesByTouchPoint = getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), axisDependency);
            float scaleX = getXAxis().mAxisRange / this.mViewPortHandler.getScaleX();
            addViewportJob(AnimatedMoveViewJob.getInstance(this.mViewPortHandler, f - (scaleX / 2.0f), ((getAxisRange(axisDependency) / this.mViewPortHandler.getScaleY()) / 2.0f) + f2, getTransformer(axisDependency), this, (float) valuesByTouchPoint.x, (float) valuesByTouchPoint.y, j));
            MPPointD.recycleInstance(valuesByTouchPoint);
            return;
        }
        Log.e(Chart.LOG_TAG, "Unable to execute centerViewToAnimated(...) on API level < 11");
    }

    public void setViewPortOffsets(float f, float f2, float f3, float f4) {
        this.mCustomViewPortEnabled = true;
        final float f5 = f;
        final float f6 = f2;
        final float f7 = f3;
        final float f8 = f4;
        post(new Runnable() {
            public void run() {
                BarLineChartBase.this.mViewPortHandler.restrainViewPort(f5, f6, f7, f8);
                BarLineChartBase.this.prepareOffsetMatrix();
                BarLineChartBase.this.prepareValuePxMatrix();
            }
        });
    }

    public void resetViewPortOffsets() {
        this.mCustomViewPortEnabled = false;
        calculateOffsets();
    }

    protected float getAxisRange(AxisDependency axisDependency) {
        if (axisDependency == AxisDependency.LEFT) {
            return this.mAxisLeft.mAxisRange;
        }
        return this.mAxisRight.mAxisRange;
    }

    public void setOnDrawListener(OnDrawListener onDrawListener) {
        this.mDrawListener = onDrawListener;
    }

    public OnDrawListener getDrawListener() {
        return this.mDrawListener;
    }

    public MPPointF getPosition(Entry entry, AxisDependency axisDependency) {
        if (entry == null) {
            return null;
        }
        this.mGetPositionBuffer[0] = entry.getX();
        this.mGetPositionBuffer[1] = entry.getY();
        getTransformer(axisDependency).pointValuesToPixel(this.mGetPositionBuffer);
        return MPPointF.getInstance(this.mGetPositionBuffer[0], this.mGetPositionBuffer[1]);
    }

    public void setMaxVisibleValueCount(int i) {
        this.mMaxVisibleCount = i;
    }

    public int getMaxVisibleCount() {
        return this.mMaxVisibleCount;
    }

    public void setHighlightPerDragEnabled(boolean z) {
        this.mHighlightPerDragEnabled = z;
    }

    public boolean isHighlightPerDragEnabled() {
        return this.mHighlightPerDragEnabled;
    }

    public void setGridBackgroundColor(int i) {
        this.mGridBackgroundPaint.setColor(i);
    }

    public void setDragEnabled(boolean z) {
        this.mDragEnabled = z;
    }

    public boolean isDragEnabled() {
        return this.mDragEnabled;
    }

    public void setScaleEnabled(boolean z) {
        this.mScaleXEnabled = z;
        this.mScaleYEnabled = z;
    }

    public void setScaleXEnabled(boolean z) {
        this.mScaleXEnabled = z;
    }

    public void setScaleYEnabled(boolean z) {
        this.mScaleYEnabled = z;
    }

    public boolean isScaleXEnabled() {
        return this.mScaleXEnabled;
    }

    public boolean isScaleYEnabled() {
        return this.mScaleYEnabled;
    }

    public void setDoubleTapToZoomEnabled(boolean z) {
        this.mDoubleTapToZoomEnabled = z;
    }

    public boolean isDoubleTapToZoomEnabled() {
        return this.mDoubleTapToZoomEnabled;
    }

    public void setDrawGridBackground(boolean z) {
        this.mDrawGridBackground = z;
    }

    public void setDrawBorders(boolean z) {
        this.mDrawBorders = z;
    }

    public boolean isDrawBordersEnabled() {
        return this.mDrawBorders;
    }

    public void setClipValuesToContent(boolean z) {
        this.mClipValuesToContent = z;
    }

    public boolean isClipValuesToContentEnabled() {
        return this.mClipValuesToContent;
    }

    public void setBorderWidth(float f) {
        this.mBorderPaint.setStrokeWidth(Utils.convertDpToPixel(f));
    }

    public void setBorderColor(int i) {
        this.mBorderPaint.setColor(i);
    }

    public float getMinOffset() {
        return this.mMinOffset;
    }

    public void setMinOffset(float f) {
        this.mMinOffset = f;
    }

    public boolean isKeepPositionOnRotation() {
        return this.mKeepPositionOnRotation;
    }

    public void setKeepPositionOnRotation(boolean z) {
        this.mKeepPositionOnRotation = z;
    }

    public MPPointD getValuesByTouchPoint(float f, float f2, AxisDependency axisDependency) {
        MPPointD instance = MPPointD.getInstance(Utils.DOUBLE_EPSILON, Utils.DOUBLE_EPSILON);
        getValuesByTouchPoint(f, f2, axisDependency, instance);
        return instance;
    }

    public void getValuesByTouchPoint(float f, float f2, AxisDependency axisDependency, MPPointD mPPointD) {
        getTransformer(axisDependency).getValuesByTouchPoint(f, f2, mPPointD);
    }

    public MPPointD getPixelForValues(float f, float f2, AxisDependency axisDependency) {
        return getTransformer(axisDependency).getPixelForValues(f, f2);
    }

    public Entry getEntryByTouchPoint(float f, float f2) {
        Highlight highlightByTouchPoint = getHighlightByTouchPoint(f, f2);
        if (highlightByTouchPoint != null) {
            return ((BarLineScatterCandleBubbleData) this.mData).getEntryForHighlight(highlightByTouchPoint);
        }
        return null;
    }

    public IBarLineScatterCandleBubbleDataSet getDataSetByTouchPoint(float f, float f2) {
        Highlight highlightByTouchPoint = getHighlightByTouchPoint(f, f2);
        if (highlightByTouchPoint != null) {
            return (IBarLineScatterCandleBubbleDataSet) ((BarLineScatterCandleBubbleData) this.mData).getDataSetByIndex(highlightByTouchPoint.getDataSetIndex());
        }
        return null;
    }

    public float getLowestVisibleX() {
        getTransformer(AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentBottom(), this.posForGetLowestVisibleX);
        return (float) Math.max((double) this.mXAxis.mAxisMinimum, this.posForGetLowestVisibleX.x);
    }

    public float getHighestVisibleX() {
        getTransformer(AxisDependency.LEFT).getValuesByTouchPoint(this.mViewPortHandler.contentRight(), this.mViewPortHandler.contentBottom(), this.posForGetHighestVisibleX);
        return (float) Math.min((double) this.mXAxis.mAxisMaximum, this.posForGetHighestVisibleX.x);
    }

    public float getVisibleXRange() {
        return Math.abs(getHighestVisibleX() - getLowestVisibleX());
    }

    public float getScaleX() {
        if (this.mViewPortHandler == null) {
            return 1.0f;
        }
        return this.mViewPortHandler.getScaleX();
    }

    public float getScaleY() {
        if (this.mViewPortHandler == null) {
            return 1.0f;
        }
        return this.mViewPortHandler.getScaleY();
    }

    public boolean isFullyZoomedOut() {
        return this.mViewPortHandler.isFullyZoomedOut();
    }

    public YAxis getAxisLeft() {
        return this.mAxisLeft;
    }

    public YAxis getAxisRight() {
        return this.mAxisRight;
    }

    public YAxis getAxis(AxisDependency axisDependency) {
        if (axisDependency == AxisDependency.LEFT) {
            return this.mAxisLeft;
        }
        return this.mAxisRight;
    }

    public boolean isInverted(AxisDependency axisDependency) {
        return getAxis(axisDependency).isInverted();
    }

    public void setPinchZoom(boolean z) {
        this.mPinchZoomEnabled = z;
    }

    public boolean isPinchZoomEnabled() {
        return this.mPinchZoomEnabled;
    }

    public void setDragOffsetX(float f) {
        this.mViewPortHandler.setDragOffsetX(f);
    }

    public void setDragOffsetY(float f) {
        this.mViewPortHandler.setDragOffsetY(f);
    }

    public boolean hasNoDragOffset() {
        return this.mViewPortHandler.hasNoDragOffset();
    }

    public XAxisRenderer getRendererXAxis() {
        return this.mXAxisRenderer;
    }

    public void setXAxisRenderer(XAxisRenderer xAxisRenderer) {
        this.mXAxisRenderer = xAxisRenderer;
    }

    public YAxisRenderer getRendererLeftYAxis() {
        return this.mAxisRendererLeft;
    }

    public void setRendererLeftYAxis(YAxisRenderer yAxisRenderer) {
        this.mAxisRendererLeft = yAxisRenderer;
    }

    public YAxisRenderer getRendererRightYAxis() {
        return this.mAxisRendererRight;
    }

    public void setRendererRightYAxis(YAxisRenderer yAxisRenderer) {
        this.mAxisRendererRight = yAxisRenderer;
    }

    public float getYChartMax() {
        return Math.max(this.mAxisLeft.mAxisMaximum, this.mAxisRight.mAxisMaximum);
    }

    public float getYChartMin() {
        return Math.min(this.mAxisLeft.mAxisMinimum, this.mAxisRight.mAxisMinimum);
    }

    public boolean isAnyAxisInverted() {
        if (this.mAxisLeft.isInverted() || this.mAxisRight.isInverted()) {
            return true;
        }
        return false;
    }

    public void setAutoScaleMinMaxEnabled(boolean z) {
        this.mAutoScaleMinMaxEnabled = z;
    }

    public boolean isAutoScaleMinMaxEnabled() {
        return this.mAutoScaleMinMaxEnabled;
    }

    public void setPaint(Paint paint, int i) {
        super.setPaint(paint, i);
        switch (i) {
            case 4:
                this.mGridBackgroundPaint = paint;
                return;
            default:
                return;
        }
    }

    public Paint getPaint(int i) {
        Paint paint = super.getPaint(i);
        if (paint != null) {
            return paint;
        }
        switch (i) {
            case 4:
                return this.mGridBackgroundPaint;
            default:
                return null;
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        float[] fArr = this.mOnSizeChangedBuffer;
        this.mOnSizeChangedBuffer[1] = 0.0f;
        fArr[0] = 0.0f;
        if (this.mKeepPositionOnRotation) {
            this.mOnSizeChangedBuffer[0] = this.mViewPortHandler.contentLeft();
            this.mOnSizeChangedBuffer[1] = this.mViewPortHandler.contentTop();
            getTransformer(AxisDependency.LEFT).pixelsToValue(this.mOnSizeChangedBuffer);
        }
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mKeepPositionOnRotation) {
            getTransformer(AxisDependency.LEFT).pointValuesToPixel(this.mOnSizeChangedBuffer);
            this.mViewPortHandler.centerViewPort(this.mOnSizeChangedBuffer, this);
            return;
        }
        this.mViewPortHandler.refresh(this.mViewPortHandler.getMatrixTouch(), this, true);
    }
}
