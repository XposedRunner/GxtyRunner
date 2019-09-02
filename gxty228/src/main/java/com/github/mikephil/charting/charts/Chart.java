package com.github.mikephil.charting.charts;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import cn.jiguang.api.utils.ByteBufferUtils;
import cn.jiguang.net.HttpUtils;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.animation.EasingFunction;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.IHighlighter;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"NewApi"})
public abstract class Chart<T extends ChartData<? extends IDataSet<? extends Entry>>> extends ViewGroup implements ChartInterface {
    public static final String LOG_TAG = "MPAndroidChart";
    public static final int PAINT_CENTER_TEXT = 14;
    public static final int PAINT_DESCRIPTION = 11;
    public static final int PAINT_GRID_BACKGROUND = 4;
    public static final int PAINT_HOLE = 13;
    public static final int PAINT_INFO = 7;
    public static final int PAINT_LEGEND_LABEL = 18;
    protected ChartAnimator mAnimator;
    protected ChartTouchListener mChartTouchListener;
    protected T mData = null;
    protected DefaultValueFormatter mDefaultValueFormatter = new DefaultValueFormatter(0);
    protected Paint mDescPaint;
    protected Description mDescription;
    private boolean mDragDecelerationEnabled = true;
    private float mDragDecelerationFrictionCoef = 0.9f;
    protected boolean mDrawMarkers = true;
    private float mExtraBottomOffset = 0.0f;
    private float mExtraLeftOffset = 0.0f;
    private float mExtraRightOffset = 0.0f;
    private float mExtraTopOffset = 0.0f;
    private OnChartGestureListener mGestureListener;
    protected boolean mHighLightPerTapEnabled = true;
    protected IHighlighter mHighlighter;
    protected Highlight[] mIndicesToHighlight;
    protected Paint mInfoPaint;
    protected ArrayList<Runnable> mJobs = new ArrayList();
    protected Legend mLegend;
    protected LegendRenderer mLegendRenderer;
    protected boolean mLogEnabled = false;
    protected IMarker mMarker;
    protected float mMaxHighlightDistance = 0.0f;
    private String mNoDataText = "No chart data available.";
    private boolean mOffsetsCalculated = false;
    protected DataRenderer mRenderer;
    protected OnChartValueSelectedListener mSelectionListener;
    protected boolean mTouchEnabled = true;
    private boolean mUnbind = false;
    protected ViewPortHandler mViewPortHandler = new ViewPortHandler();
    protected XAxis mXAxis;

    /* renamed from: com.github.mikephil.charting.charts.Chart$2 */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$CompressFormat = new int[CompressFormat.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[CompressFormat.PNG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[CompressFormat.WEBP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[CompressFormat.JPEG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    protected abstract void calcMinMax();

    protected abstract void calculateOffsets();

    public abstract void notifyDataSetChanged();

    public Chart(Context context) {
        super(context);
        init();
    }

    public Chart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public Chart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    protected void init() {
        setWillNotDraw(false);
        if (VERSION.SDK_INT < 11) {
            this.mAnimator = new ChartAnimator();
        } else {
            this.mAnimator = new ChartAnimator(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Chart.this.postInvalidate();
                }
            });
        }
        Utils.init(getContext());
        this.mMaxHighlightDistance = Utils.convertDpToPixel(500.0f);
        this.mDescription = new Description();
        this.mLegend = new Legend();
        this.mLegendRenderer = new LegendRenderer(this.mViewPortHandler, this.mLegend);
        this.mXAxis = new XAxis();
        this.mDescPaint = new Paint(1);
        this.mInfoPaint = new Paint(1);
        this.mInfoPaint.setColor(Color.rgb(247, 189, 51));
        this.mInfoPaint.setTextAlign(Align.CENTER);
        this.mInfoPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        if (this.mLogEnabled) {
            Log.i("", "Chart.init()");
        }
    }

    public void setData(T t) {
        this.mData = t;
        this.mOffsetsCalculated = false;
        if (t != null) {
            setupDefaultFormatter(t.getYMin(), t.getYMax());
            for (IDataSet iDataSet : this.mData.getDataSets()) {
                if (iDataSet.needsFormatter() || iDataSet.getValueFormatter() == this.mDefaultValueFormatter) {
                    iDataSet.setValueFormatter(this.mDefaultValueFormatter);
                }
            }
            notifyDataSetChanged();
            if (this.mLogEnabled) {
                Log.i(LOG_TAG, "Data is set.");
            }
        }
    }

    public void clear() {
        this.mData = null;
        this.mOffsetsCalculated = false;
        this.mIndicesToHighlight = null;
        invalidate();
    }

    public void clearValues() {
        this.mData.clearValues();
        invalidate();
    }

    public boolean isEmpty() {
        if (this.mData != null && this.mData.getEntryCount() > 0) {
            return false;
        }
        return true;
    }

    protected void setupDefaultFormatter(float f, float f2) {
        float max;
        if (this.mData == null || this.mData.getEntryCount() < 2) {
            max = Math.max(Math.abs(f), Math.abs(f2));
        } else {
            max = Math.abs(f2 - f);
        }
        this.mDefaultValueFormatter.setup(Utils.getDecimals(max));
    }

    protected void onDraw(Canvas canvas) {
        boolean z = true;
        if (this.mData == null) {
            if (TextUtils.isEmpty(this.mNoDataText)) {
                z = false;
            }
            if (z) {
                MPPointF center = getCenter();
                canvas.drawText(this.mNoDataText, center.x, center.y, this.mInfoPaint);
            }
        } else if (!this.mOffsetsCalculated) {
            calculateOffsets();
            this.mOffsetsCalculated = true;
        }
    }

    protected void drawDescription(Canvas canvas) {
        if (this.mDescription != null && this.mDescription.isEnabled()) {
            float width;
            float height;
            MPPointF position = this.mDescription.getPosition();
            this.mDescPaint.setTypeface(this.mDescription.getTypeface());
            this.mDescPaint.setTextSize(this.mDescription.getTextSize());
            this.mDescPaint.setColor(this.mDescription.getTextColor());
            this.mDescPaint.setTextAlign(this.mDescription.getTextAlign());
            if (position == null) {
                width = (((float) getWidth()) - this.mViewPortHandler.offsetRight()) - this.mDescription.getXOffset();
                height = (((float) getHeight()) - this.mViewPortHandler.offsetBottom()) - this.mDescription.getYOffset();
            } else {
                width = position.x;
                height = position.y;
            }
            canvas.drawText(this.mDescription.getText(), width, height, this.mDescPaint);
        }
    }

    public float getMaxHighlightDistance() {
        return this.mMaxHighlightDistance;
    }

    public void setMaxHighlightDistance(float f) {
        this.mMaxHighlightDistance = Utils.convertDpToPixel(f);
    }

    public Highlight[] getHighlighted() {
        return this.mIndicesToHighlight;
    }

    public boolean isHighlightPerTapEnabled() {
        return this.mHighLightPerTapEnabled;
    }

    public void setHighlightPerTapEnabled(boolean z) {
        this.mHighLightPerTapEnabled = z;
    }

    public boolean valuesToHighlight() {
        return (this.mIndicesToHighlight == null || this.mIndicesToHighlight.length <= 0 || this.mIndicesToHighlight[0] == null) ? false : true;
    }

    protected void setLastHighlighted(Highlight[] highlightArr) {
        if (highlightArr == null || highlightArr.length <= 0 || highlightArr[0] == null) {
            this.mChartTouchListener.setLastHighlighted(null);
        } else {
            this.mChartTouchListener.setLastHighlighted(highlightArr[0]);
        }
    }

    public void highlightValues(Highlight[] highlightArr) {
        this.mIndicesToHighlight = highlightArr;
        setLastHighlighted(highlightArr);
        invalidate();
    }

    public void highlightValue(float f, int i) {
        highlightValue(f, i, true);
    }

    public void highlightValue(float f, float f2, int i) {
        highlightValue(f, f2, i, true);
    }

    public void highlightValue(float f, int i, boolean z) {
        highlightValue(f, Float.NaN, i, z);
    }

    public void highlightValue(float f, float f2, int i, boolean z) {
        if (i < 0 || i >= this.mData.getDataSetCount()) {
            highlightValue(null, z);
        } else {
            highlightValue(new Highlight(f, f2, i), z);
        }
    }

    public void highlightValue(Highlight highlight) {
        highlightValue(highlight, false);
    }

    public void highlightValue(Highlight highlight, boolean z) {
        Entry entry = null;
        if (highlight == null) {
            this.mIndicesToHighlight = null;
        } else {
            if (this.mLogEnabled) {
                Log.i(LOG_TAG, "Highlighted: " + highlight.toString());
            }
            Entry entryForHighlight = this.mData.getEntryForHighlight(highlight);
            if (entryForHighlight == null) {
                this.mIndicesToHighlight = null;
                highlight = null;
                entry = entryForHighlight;
            } else {
                this.mIndicesToHighlight = new Highlight[]{highlight};
                entry = entryForHighlight;
            }
        }
        setLastHighlighted(this.mIndicesToHighlight);
        if (z && this.mSelectionListener != null) {
            if (valuesToHighlight()) {
                this.mSelectionListener.onValueSelected(entry, highlight);
            } else {
                this.mSelectionListener.onNothingSelected();
            }
        }
        invalidate();
    }

    public Highlight getHighlightByTouchPoint(float f, float f2) {
        if (this.mData != null) {
            return getHighlighter().getHighlight(f, f2);
        }
        Log.e(LOG_TAG, "Can't select by touch. No data set.");
        return null;
    }

    public void setOnTouchListener(ChartTouchListener chartTouchListener) {
        this.mChartTouchListener = chartTouchListener;
    }

    public ChartTouchListener getOnTouchListener() {
        return this.mChartTouchListener;
    }

    protected void drawMarkers(Canvas canvas) {
        if (this.mMarker != null && isDrawMarkersEnabled() && valuesToHighlight()) {
            for (int i = 0; i < this.mIndicesToHighlight.length; i++) {
                Highlight highlight = this.mIndicesToHighlight[i];
                IDataSet dataSetByIndex = this.mData.getDataSetByIndex(highlight.getDataSetIndex());
                Entry entryForHighlight = this.mData.getEntryForHighlight(this.mIndicesToHighlight[i]);
                int entryIndex = dataSetByIndex.getEntryIndex(entryForHighlight);
                if (entryForHighlight != null && ((float) entryIndex) <= ((float) dataSetByIndex.getEntryCount()) * this.mAnimator.getPhaseX()) {
                    float[] markerPosition = getMarkerPosition(highlight);
                    if (this.mViewPortHandler.isInBounds(markerPosition[0], markerPosition[1])) {
                        this.mMarker.refreshContent(entryForHighlight, highlight);
                        this.mMarker.draw(canvas, markerPosition[0], markerPosition[1]);
                    }
                }
            }
        }
    }

    protected float[] getMarkerPosition(Highlight highlight) {
        return new float[]{highlight.getDrawX(), highlight.getDrawY()};
    }

    public ChartAnimator getAnimator() {
        return this.mAnimator;
    }

    public boolean isDragDecelerationEnabled() {
        return this.mDragDecelerationEnabled;
    }

    public void setDragDecelerationEnabled(boolean z) {
        this.mDragDecelerationEnabled = z;
    }

    public float getDragDecelerationFrictionCoef() {
        return this.mDragDecelerationFrictionCoef;
    }

    public void setDragDecelerationFrictionCoef(float f) {
        float f2 = 0.0f;
        if (f >= 0.0f) {
            f2 = f;
        }
        if (f2 >= 1.0f) {
            f2 = 0.999f;
        }
        this.mDragDecelerationFrictionCoef = f2;
    }

    public void animateXY(int i, int i2, EasingFunction easingFunction, EasingFunction easingFunction2) {
        this.mAnimator.animateXY(i, i2, easingFunction, easingFunction2);
    }

    public void animateX(int i, EasingFunction easingFunction) {
        this.mAnimator.animateX(i, easingFunction);
    }

    public void animateY(int i, EasingFunction easingFunction) {
        this.mAnimator.animateY(i, easingFunction);
    }

    public void animateXY(int i, int i2, EasingOption easingOption, EasingOption easingOption2) {
        this.mAnimator.animateXY(i, i2, easingOption, easingOption2);
    }

    public void animateX(int i, EasingOption easingOption) {
        this.mAnimator.animateX(i, easingOption);
    }

    public void animateY(int i, EasingOption easingOption) {
        this.mAnimator.animateY(i, easingOption);
    }

    public void animateX(int i) {
        this.mAnimator.animateX(i);
    }

    public void animateY(int i) {
        this.mAnimator.animateY(i);
    }

    public void animateXY(int i, int i2) {
        this.mAnimator.animateXY(i, i2);
    }

    public XAxis getXAxis() {
        return this.mXAxis;
    }

    public IValueFormatter getDefaultValueFormatter() {
        return this.mDefaultValueFormatter;
    }

    public void setOnChartValueSelectedListener(OnChartValueSelectedListener onChartValueSelectedListener) {
        this.mSelectionListener = onChartValueSelectedListener;
    }

    public void setOnChartGestureListener(OnChartGestureListener onChartGestureListener) {
        this.mGestureListener = onChartGestureListener;
    }

    public OnChartGestureListener getOnChartGestureListener() {
        return this.mGestureListener;
    }

    public float getYMax() {
        return this.mData.getYMax();
    }

    public float getYMin() {
        return this.mData.getYMin();
    }

    public float getXChartMax() {
        return this.mXAxis.mAxisMaximum;
    }

    public float getXChartMin() {
        return this.mXAxis.mAxisMinimum;
    }

    public float getXRange() {
        return this.mXAxis.mAxisRange;
    }

    public MPPointF getCenter() {
        return MPPointF.getInstance(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
    }

    public MPPointF getCenterOffsets() {
        return this.mViewPortHandler.getContentCenter();
    }

    public void setExtraOffsets(float f, float f2, float f3, float f4) {
        setExtraLeftOffset(f);
        setExtraTopOffset(f2);
        setExtraRightOffset(f3);
        setExtraBottomOffset(f4);
    }

    public void setExtraTopOffset(float f) {
        this.mExtraTopOffset = Utils.convertDpToPixel(f);
    }

    public float getExtraTopOffset() {
        return this.mExtraTopOffset;
    }

    public void setExtraRightOffset(float f) {
        this.mExtraRightOffset = Utils.convertDpToPixel(f);
    }

    public float getExtraRightOffset() {
        return this.mExtraRightOffset;
    }

    public void setExtraBottomOffset(float f) {
        this.mExtraBottomOffset = Utils.convertDpToPixel(f);
    }

    public float getExtraBottomOffset() {
        return this.mExtraBottomOffset;
    }

    public void setExtraLeftOffset(float f) {
        this.mExtraLeftOffset = Utils.convertDpToPixel(f);
    }

    public float getExtraLeftOffset() {
        return this.mExtraLeftOffset;
    }

    public void setLogEnabled(boolean z) {
        this.mLogEnabled = z;
    }

    public boolean isLogEnabled() {
        return this.mLogEnabled;
    }

    public void setNoDataText(String str) {
        this.mNoDataText = str;
    }

    public void setNoDataTextColor(int i) {
        this.mInfoPaint.setColor(i);
    }

    public void setNoDataTextTypeface(Typeface typeface) {
        this.mInfoPaint.setTypeface(typeface);
    }

    public void setTouchEnabled(boolean z) {
        this.mTouchEnabled = z;
    }

    public void setMarker(IMarker iMarker) {
        this.mMarker = iMarker;
    }

    public IMarker getMarker() {
        return this.mMarker;
    }

    @Deprecated
    public void setMarkerView(IMarker iMarker) {
        setMarker(iMarker);
    }

    @Deprecated
    public IMarker getMarkerView() {
        return getMarker();
    }

    public void setDescription(Description description) {
        this.mDescription = description;
    }

    public Description getDescription() {
        return this.mDescription;
    }

    public Legend getLegend() {
        return this.mLegend;
    }

    public LegendRenderer getLegendRenderer() {
        return this.mLegendRenderer;
    }

    public RectF getContentRect() {
        return this.mViewPortHandler.getContentRect();
    }

    public void disableScroll() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    public void enableScroll() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
    }

    public void setPaint(Paint paint, int i) {
        switch (i) {
            case 7:
                this.mInfoPaint = paint;
                return;
            case 11:
                this.mDescPaint = paint;
                return;
            default:
                return;
        }
    }

    public Paint getPaint(int i) {
        switch (i) {
            case 7:
                return this.mInfoPaint;
            case 11:
                return this.mDescPaint;
            default:
                return null;
        }
    }

    @Deprecated
    public boolean isDrawMarkerViewsEnabled() {
        return isDrawMarkersEnabled();
    }

    @Deprecated
    public void setDrawMarkerViews(boolean z) {
        setDrawMarkers(z);
    }

    public boolean isDrawMarkersEnabled() {
        return this.mDrawMarkers;
    }

    public void setDrawMarkers(boolean z) {
        this.mDrawMarkers = z;
    }

    public T getData() {
        return this.mData;
    }

    public ViewPortHandler getViewPortHandler() {
        return this.mViewPortHandler;
    }

    public DataRenderer getRenderer() {
        return this.mRenderer;
    }

    public void setRenderer(DataRenderer dataRenderer) {
        if (dataRenderer != null) {
            this.mRenderer = dataRenderer;
        }
    }

    public IHighlighter getHighlighter() {
        return this.mHighlighter;
    }

    public void setHighlighter(ChartHighlighter chartHighlighter) {
        this.mHighlighter = chartHighlighter;
    }

    public MPPointF getCenterOfView() {
        return getCenter();
    }

    public Bitmap getChartBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        draw(canvas);
        return createBitmap;
    }

    public boolean saveToPath(String str, String str2) {
        Bitmap chartBitmap = getChartBitmap();
        try {
            OutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + str2 + HttpUtils.PATHS_SEPARATOR + str + ".png");
            chartBitmap.compress(CompressFormat.PNG, 40, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveToGallery(String str, String str2, String str3, CompressFormat compressFormat, int i) {
        if (i < 0 || i > 100) {
            i = 50;
        }
        long currentTimeMillis = System.currentTimeMillis();
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/" + str2);
        if (!file.exists() && !file.mkdirs()) {
            return false;
        }
        String str4 = "";
        switch (AnonymousClass2.$SwitchMap$android$graphics$Bitmap$CompressFormat[compressFormat.ordinal()]) {
            case 1:
                str4 = "image/png";
                if (!str.endsWith(".png")) {
                    str = str + ".png";
                    break;
                }
                break;
            case 2:
                str4 = "image/webp";
                if (!str.endsWith(".webp")) {
                    str = str + ".webp";
                    break;
                }
                break;
            default:
                str4 = "image/jpeg";
                if (!(str.endsWith(".jpg") || str.endsWith(".jpeg"))) {
                    str = str + ".jpg";
                    break;
                }
        }
        String str5 = file.getAbsolutePath() + HttpUtils.PATHS_SEPARATOR + str;
        try {
            boolean z;
            OutputStream fileOutputStream = new FileOutputStream(str5);
            getChartBitmap().compress(compressFormat, i, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            long length = new File(str5).length();
            ContentValues contentValues = new ContentValues(8);
            contentValues.put("title", str);
            contentValues.put("_display_name", str);
            contentValues.put("date_added", Long.valueOf(currentTimeMillis));
            contentValues.put("mime_type", str4);
            contentValues.put("description", str3);
            contentValues.put("orientation", Integer.valueOf(0));
            contentValues.put("_data", str5);
            contentValues.put("_size", Long.valueOf(length));
            if (getContext().getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, contentValues) != null) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveToGallery(String str, int i) {
        return saveToGallery(str, "", "MPAndroidChart-Library Save", CompressFormat.JPEG, i);
    }

    public void removeViewportJob(Runnable runnable) {
        this.mJobs.remove(runnable);
    }

    public void clearAllViewportJobs() {
        this.mJobs.clear();
    }

    public void addViewportJob(Runnable runnable) {
        if (this.mViewPortHandler.hasChartDimens()) {
            post(runnable);
        } else {
            this.mJobs.add(runnable);
        }
    }

    public ArrayList<Runnable> getJobs() {
        return this.mJobs;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            getChildAt(i5).layout(i, i2, i3, i4);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int convertDpToPixel = (int) Utils.convertDpToPixel(50.0f);
        setMeasuredDimension(Math.max(getSuggestedMinimumWidth(), resolveSize(convertDpToPixel, i)), Math.max(getSuggestedMinimumHeight(), resolveSize(convertDpToPixel, i2)));
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.mLogEnabled) {
            Log.i(LOG_TAG, "OnSizeChanged()");
        }
        if (i > 0 && i2 > 0 && i < ByteBufferUtils.ERROR_CODE && i2 < ByteBufferUtils.ERROR_CODE) {
            this.mViewPortHandler.setChartDimens((float) i, (float) i2);
            if (this.mLogEnabled) {
                Log.i(LOG_TAG, "Setting chart dimens, width: " + i + ", height: " + i2);
            }
            Iterator it = this.mJobs.iterator();
            while (it.hasNext()) {
                post((Runnable) it.next());
            }
            this.mJobs.clear();
        }
        notifyDataSetChanged();
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setHardwareAccelerationEnabled(boolean z) {
        if (VERSION.SDK_INT < 11) {
            Log.e(LOG_TAG, "Cannot enable/disable hardware acceleration for devices below API level 11.");
        } else if (z) {
            setLayerType(2, null);
        } else {
            setLayerType(1, null);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mUnbind) {
            unbindDrawables(this);
        }
    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    public void setUnbindEnabled(boolean z) {
        this.mUnbind = z;
    }
}
