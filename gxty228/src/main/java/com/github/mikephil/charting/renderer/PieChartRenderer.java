package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet.ValuePosition;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.List;

public class PieChartRenderer extends DataRenderer {
    protected Canvas mBitmapCanvas;
    private RectF mCenterTextLastBounds = new RectF();
    private CharSequence mCenterTextLastValue;
    private StaticLayout mCenterTextLayout;
    private TextPaint mCenterTextPaint;
    protected PieChart mChart;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mDrawCenterTextPathBuffer = new Path();
    protected RectF mDrawHighlightedRectF = new RectF();
    private Paint mEntryLabelsPaint;
    private Path mHoleCirclePath = new Path();
    protected Paint mHolePaint;
    private RectF mInnerRectBuffer = new RectF();
    private Path mPathBuffer = new Path();
    private RectF[] mRectBuffer = new RectF[]{new RectF(), new RectF(), new RectF()};
    protected Paint mTransparentCirclePaint;
    protected Paint mValueLinePaint;

    public PieChartRenderer(PieChart pieChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = pieChart;
        this.mHolePaint = new Paint(1);
        this.mHolePaint.setColor(-1);
        this.mHolePaint.setStyle(Style.FILL);
        this.mTransparentCirclePaint = new Paint(1);
        this.mTransparentCirclePaint.setColor(-1);
        this.mTransparentCirclePaint.setStyle(Style.FILL);
        this.mTransparentCirclePaint.setAlpha(105);
        this.mCenterTextPaint = new TextPaint(1);
        this.mCenterTextPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mCenterTextPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        this.mValuePaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValuePaint.setColor(-1);
        this.mValuePaint.setTextAlign(Align.CENTER);
        this.mEntryLabelsPaint = new Paint(1);
        this.mEntryLabelsPaint.setColor(-1);
        this.mEntryLabelsPaint.setTextAlign(Align.CENTER);
        this.mEntryLabelsPaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValueLinePaint = new Paint(1);
        this.mValueLinePaint.setStyle(Style.STROKE);
    }

    public Paint getPaintHole() {
        return this.mHolePaint;
    }

    public Paint getPaintTransparentCircle() {
        return this.mTransparentCirclePaint;
    }

    public TextPaint getPaintCenterText() {
        return this.mCenterTextPaint;
    }

    public Paint getPaintEntryLabels() {
        return this.mEntryLabelsPaint;
    }

    public void initBuffers() {
    }

    public void drawData(Canvas canvas) {
        int chartWidth = (int) this.mViewPortHandler.getChartWidth();
        int chartHeight = (int) this.mViewPortHandler.getChartHeight();
        if (!(this.mDrawBitmap != null && ((Bitmap) this.mDrawBitmap.get()).getWidth() == chartWidth && ((Bitmap) this.mDrawBitmap.get()).getHeight() == chartHeight)) {
            if (chartWidth > 0 && chartHeight > 0) {
                this.mDrawBitmap = new WeakReference(Bitmap.createBitmap(chartWidth, chartHeight, Config.ARGB_4444));
                this.mBitmapCanvas = new Canvas((Bitmap) this.mDrawBitmap.get());
            } else {
                return;
            }
        }
        ((Bitmap) this.mDrawBitmap.get()).eraseColor(0);
        for (IPieDataSet iPieDataSet : ((PieData) this.mChart.getData()).getDataSets()) {
            if (iPieDataSet.isVisible() && iPieDataSet.getEntryCount() > 0) {
                drawDataSet(canvas, iPieDataSet);
            }
        }
    }

    protected float calculateMinimumRadiusForSpacedSlice(MPPointF mPPointF, float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = (f6 / 2.0f) + f5;
        float cos = mPPointF.x + (((float) Math.cos((double) ((f5 + f6) * 0.017453292f))) * f);
        float sin = mPPointF.y + (((float) Math.sin((double) ((f5 + f6) * 0.017453292f))) * f);
        float cos2 = mPPointF.x + (((float) Math.cos((double) (0.017453292f * f7))) * f);
        f7 = (((float) Math.sin((double) (f7 * 0.017453292f))) * f) + mPPointF.y;
        return (float) (((double) (f - ((float) ((Math.sqrt(Math.pow((double) (cos - f3), 2.0d) + Math.pow((double) (sin - f4), 2.0d)) / 2.0d) * Math.tan(((180.0d - ((double) f2)) / 2.0d) * 0.017453292519943295d))))) - Math.sqrt(Math.pow((double) (f7 - ((sin + f4) / 2.0f)), 2.0d) + Math.pow((double) (cos2 - ((cos + f3) / 2.0f)), 2.0d)));
    }

    protected float getSliceSpace(IPieDataSet iPieDataSet) {
        if (iPieDataSet.isAutomaticallyDisableSliceSpacingEnabled()) {
            return iPieDataSet.getSliceSpace() / this.mViewPortHandler.getSmallestContentExtension() > (iPieDataSet.getYMin() / ((PieData) this.mChart.getData()).getYValueSum()) * 2.0f ? 0.0f : iPieDataSet.getSliceSpace();
        } else {
            return iPieDataSet.getSliceSpace();
        }
    }

    protected void drawDataSet(Canvas canvas, IPieDataSet iPieDataSet) {
        float holeRadius;
        float f;
        float rotationAngle = this.mChart.getRotationAngle();
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        RectF circleBox = this.mChart.getCircleBox();
        int entryCount = iPieDataSet.getEntryCount();
        float[] drawAngles = this.mChart.getDrawAngles();
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        Object obj = (!this.mChart.isDrawHoleEnabled() || this.mChart.isDrawSlicesUnderHoleEnabled()) ? null : 1;
        if (obj != null) {
            holeRadius = (this.mChart.getHoleRadius() / 100.0f) * radius;
        } else {
            holeRadius = 0.0f;
        }
        int i = 0;
        int i2 = 0;
        while (i2 < entryCount) {
            int i3;
            if (Math.abs(((PieEntry) iPieDataSet.getEntryForIndex(i2)).getY()) > Utils.FLOAT_EPSILON) {
                i3 = i + 1;
            } else {
                i3 = i;
            }
            i2++;
            i = i3;
        }
        if (i <= 1) {
            f = 0.0f;
        } else {
            f = getSliceSpace(iPieDataSet);
        }
        int i4 = 0;
        float f2 = 0.0f;
        while (i4 < entryCount) {
            float f3 = drawAngles[i4];
            if (Math.abs(iPieDataSet.getEntryForIndex(i4).getY()) > Utils.FLOAT_EPSILON && !this.mChart.needsHighlight(i4)) {
                float f4;
                Object obj2 = (f <= 0.0f || f3 > 180.0f) ? null : 1;
                this.mRenderPaint.setColor(iPieDataSet.getColor(i4));
                if (i == 1) {
                    f4 = 0.0f;
                } else {
                    f4 = f / (0.017453292f * radius);
                }
                float f5 = rotationAngle + (((f4 / 2.0f) + f2) * phaseY);
                float f6 = (f3 - f4) * phaseY;
                if (f6 < 0.0f) {
                    f6 = 0.0f;
                }
                this.mPathBuffer.reset();
                float cos = (((float) Math.cos((double) (0.017453292f * f5))) * radius) + centerCircleBox.x;
                float sin = (((float) Math.sin((double) (0.017453292f * f5))) * radius) + centerCircleBox.y;
                if (f6 < 360.0f || f6 % 360.0f > Utils.FLOAT_EPSILON) {
                    this.mPathBuffer.moveTo(cos, sin);
                    this.mPathBuffer.arcTo(circleBox, f5, f6);
                } else {
                    this.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, radius, Direction.CW);
                }
                this.mInnerRectBuffer.set(centerCircleBox.x - holeRadius, centerCircleBox.y - holeRadius, centerCircleBox.x + holeRadius, centerCircleBox.y + holeRadius);
                float calculateMinimumRadiusForSpacedSlice;
                if (obj != null && (holeRadius > 0.0f || obj2 != null)) {
                    if (obj2 != null) {
                        calculateMinimumRadiusForSpacedSlice = calculateMinimumRadiusForSpacedSlice(centerCircleBox, radius, f3 * phaseY, cos, sin, f5, f6);
                        if (calculateMinimumRadiusForSpacedSlice < 0.0f) {
                            calculateMinimumRadiusForSpacedSlice = -calculateMinimumRadiusForSpacedSlice;
                        }
                        f4 = Math.max(holeRadius, calculateMinimumRadiusForSpacedSlice);
                    } else {
                        f4 = holeRadius;
                    }
                    if (i == 1 || f4 == 0.0f) {
                        calculateMinimumRadiusForSpacedSlice = 0.0f;
                    } else {
                        calculateMinimumRadiusForSpacedSlice = f / (0.017453292f * f4);
                    }
                    cos = (((calculateMinimumRadiusForSpacedSlice / 2.0f) + f2) * phaseY) + rotationAngle;
                    calculateMinimumRadiusForSpacedSlice = (f3 - calculateMinimumRadiusForSpacedSlice) * phaseY;
                    if (calculateMinimumRadiusForSpacedSlice < 0.0f) {
                        calculateMinimumRadiusForSpacedSlice = 0.0f;
                    }
                    cos += calculateMinimumRadiusForSpacedSlice;
                    if (f6 < 360.0f || f6 % 360.0f > Utils.FLOAT_EPSILON) {
                        this.mPathBuffer.lineTo(centerCircleBox.x + (((float) Math.cos((double) (0.017453292f * cos))) * f4), (f4 * ((float) Math.sin((double) (0.017453292f * cos)))) + centerCircleBox.y);
                        this.mPathBuffer.arcTo(this.mInnerRectBuffer, cos, -calculateMinimumRadiusForSpacedSlice);
                    } else {
                        this.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, f4, Direction.CCW);
                    }
                } else if (f6 % 360.0f > Utils.FLOAT_EPSILON) {
                    if (obj2 != null) {
                        float f7 = f5 + (f6 / 2.0f);
                        calculateMinimumRadiusForSpacedSlice = calculateMinimumRadiusForSpacedSlice(centerCircleBox, radius, f3 * phaseY, cos, sin, f5, f6);
                        this.mPathBuffer.lineTo(centerCircleBox.x + (((float) Math.cos((double) (0.017453292f * f7))) * calculateMinimumRadiusForSpacedSlice), (calculateMinimumRadiusForSpacedSlice * ((float) Math.sin((double) (0.017453292f * f7)))) + centerCircleBox.y);
                    } else {
                        this.mPathBuffer.lineTo(centerCircleBox.x, centerCircleBox.y);
                    }
                }
                this.mPathBuffer.close();
                this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
            }
            i4++;
            f2 += f3 * phaseX;
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    public void drawValues(Canvas canvas) {
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        float rotationAngle = this.mChart.getRotationAngle();
        float[] drawAngles = this.mChart.getDrawAngles();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles();
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float holeRadius = this.mChart.getHoleRadius() / 100.0f;
        float f = (radius / 10.0f) * 3.6f;
        if (this.mChart.isDrawHoleEnabled()) {
            f = (radius - (radius * holeRadius)) / 2.0f;
        }
        float f2 = radius - f;
        PieData pieData = (PieData) this.mChart.getData();
        List dataSets = pieData.getDataSets();
        float yValueSum = pieData.getYValueSum();
        boolean isDrawEntryLabelsEnabled = this.mChart.isDrawEntryLabelsEnabled();
        int i = 0;
        canvas.save();
        float convertDpToPixel = Utils.convertDpToPixel(5.0f);
        for (int i2 = 0; i2 < dataSets.size(); i2++) {
            IPieDataSet iPieDataSet = (IPieDataSet) dataSets.get(i2);
            boolean isDrawValuesEnabled = iPieDataSet.isDrawValuesEnabled();
            if (isDrawValuesEnabled || isDrawEntryLabelsEnabled) {
                ValuePosition xValuePosition = iPieDataSet.getXValuePosition();
                ValuePosition yValuePosition = iPieDataSet.getYValuePosition();
                applyValueTextStyle(iPieDataSet);
                float calcTextHeight = ((float) Utils.calcTextHeight(this.mValuePaint, "Q")) + Utils.convertDpToPixel(4.0f);
                IValueFormatter valueFormatter = iPieDataSet.getValueFormatter();
                int entryCount = iPieDataSet.getEntryCount();
                this.mValueLinePaint.setColor(iPieDataSet.getValueLineColor());
                this.mValueLinePaint.setStrokeWidth(Utils.convertDpToPixel(iPieDataSet.getValueLineWidth()));
                float sliceSpace = getSliceSpace(iPieDataSet);
                MPPointF instance = MPPointF.getInstance(iPieDataSet.getIconsOffset());
                instance.x = Utils.convertDpToPixel(instance.x);
                instance.y = Utils.convertDpToPixel(instance.y);
                int i3 = i;
                for (int i4 = 0; i4 < entryCount; i4++) {
                    float y;
                    Object obj;
                    Entry entry = (PieEntry) iPieDataSet.getEntryForIndex(i4);
                    if (i3 == 0) {
                        f = 0.0f;
                    } else {
                        f = absoluteAngles[i3 - 1] * phaseX;
                    }
                    float f3 = rotationAngle + ((f + ((drawAngles[i3] - ((sliceSpace / (0.017453292f * f2)) / 2.0f)) / 2.0f)) * phaseY);
                    if (this.mChart.isUsePercentValuesEnabled()) {
                        y = (entry.getY() / yValueSum) * 100.0f;
                    } else {
                        y = entry.getY();
                    }
                    float cos = (float) Math.cos((double) (0.017453292f * f3));
                    float sin = (float) Math.sin((double) (0.017453292f * f3));
                    Object obj2 = (isDrawEntryLabelsEnabled && xValuePosition == ValuePosition.OUTSIDE_SLICE) ? 1 : null;
                    Object obj3 = (isDrawValuesEnabled && yValuePosition == ValuePosition.OUTSIDE_SLICE) ? 1 : null;
                    Object obj4 = (isDrawEntryLabelsEnabled && xValuePosition == ValuePosition.INSIDE_SLICE) ? 1 : null;
                    if (isDrawValuesEnabled && yValuePosition == ValuePosition.INSIDE_SLICE) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (!(obj2 == null && obj3 == null)) {
                        float f4;
                        float f5;
                        float f6;
                        float valueLinePart1Length = iPieDataSet.getValueLinePart1Length();
                        float valueLinePart2Length = iPieDataSet.getValueLinePart2Length();
                        f = iPieDataSet.getValueLinePart1OffsetPercentage() / 100.0f;
                        if (this.mChart.isDrawHoleEnabled()) {
                            f = (f * (radius - (radius * holeRadius))) + (radius * holeRadius);
                        } else {
                            f *= radius;
                        }
                        float abs = iPieDataSet.isValueLineVariableLength() ? (valueLinePart2Length * f2) * ((float) Math.abs(Math.sin((double) (0.017453292f * f3)))) : valueLinePart2Length * f2;
                        valueLinePart2Length = (f * cos) + centerCircleBox.x;
                        float f7 = centerCircleBox.y + (f * sin);
                        float f8 = centerCircleBox.x + (((1.0f + valueLinePart1Length) * f2) * cos);
                        valueLinePart1Length = centerCircleBox.y + (((1.0f + valueLinePart1Length) * f2) * sin);
                        if (((double) f3) % 360.0d < 90.0d || ((double) f3) % 360.0d > 270.0d) {
                            abs += f8;
                            this.mValuePaint.setTextAlign(Align.LEFT);
                            if (obj2 != null) {
                                this.mEntryLabelsPaint.setTextAlign(Align.LEFT);
                            }
                            f4 = valueLinePart1Length;
                            f5 = abs + convertDpToPixel;
                            f3 = valueLinePart1Length;
                            f6 = abs;
                        } else {
                            abs = f8 - abs;
                            this.mValuePaint.setTextAlign(Align.RIGHT);
                            if (obj2 != null) {
                                this.mEntryLabelsPaint.setTextAlign(Align.RIGHT);
                            }
                            f4 = valueLinePart1Length;
                            f5 = abs - convertDpToPixel;
                            f3 = valueLinePart1Length;
                            f6 = abs;
                        }
                        if (iPieDataSet.getValueLineColor() != ColorTemplate.COLOR_NONE) {
                            canvas.drawLine(valueLinePart2Length, f7, f8, valueLinePart1Length, this.mValueLinePaint);
                            canvas.drawLine(f8, valueLinePart1Length, f6, f3, this.mValueLinePaint);
                        }
                        if (obj2 != null && obj3 != null) {
                            drawValue(canvas, valueFormatter, y, entry, 0, f5, f4, iPieDataSet.getValueTextColor(i4));
                            if (i4 < pieData.getEntryCount() && entry.getLabel() != null) {
                                drawEntryLabel(canvas, entry.getLabel(), f5, f4 + calcTextHeight);
                            }
                        } else if (obj2 != null) {
                            if (i4 < pieData.getEntryCount() && entry.getLabel() != null) {
                                drawEntryLabel(canvas, entry.getLabel(), f5, (calcTextHeight / 2.0f) + f4);
                            }
                        } else if (obj3 != null) {
                            drawValue(canvas, valueFormatter, y, entry, 0, f5, f4 + (calcTextHeight / 2.0f), iPieDataSet.getValueTextColor(i4));
                        }
                    }
                    if (!(obj4 == null && obj == null)) {
                        f3 = (f2 * cos) + centerCircleBox.x;
                        float f9 = (f2 * sin) + centerCircleBox.y;
                        this.mValuePaint.setTextAlign(Align.CENTER);
                        if (obj4 != null && obj != null) {
                            drawValue(canvas, valueFormatter, y, entry, 0, f3, f9, iPieDataSet.getValueTextColor(i4));
                            if (i4 < pieData.getEntryCount() && entry.getLabel() != null) {
                                drawEntryLabel(canvas, entry.getLabel(), f3, f9 + calcTextHeight);
                            }
                        } else if (obj4 != null) {
                            if (i4 < pieData.getEntryCount() && entry.getLabel() != null) {
                                drawEntryLabel(canvas, entry.getLabel(), f3, (calcTextHeight / 2.0f) + f9);
                            }
                        } else if (obj != null) {
                            drawValue(canvas, valueFormatter, y, entry, 0, f3, f9 + (calcTextHeight / 2.0f), iPieDataSet.getValueTextColor(i4));
                        }
                    }
                    if (entry.getIcon() != null && iPieDataSet.isDrawIconsEnabled()) {
                        Drawable icon = entry.getIcon();
                        Utils.drawImage(canvas, icon, (int) (((instance.y + f2) * cos) + centerCircleBox.x), (int) (instance.x + (((instance.y + f2) * sin) + centerCircleBox.y)), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                    i3++;
                }
                MPPointF.recycleInstance(instance);
                i = i3;
            }
        }
        MPPointF.recycleInstance(centerCircleBox);
        canvas.restore();
    }

    protected void drawEntryLabel(Canvas canvas, String str, float f, float f2) {
        canvas.drawText(str, f, f2, this.mEntryLabelsPaint);
    }

    public void drawExtras(Canvas canvas) {
        drawHole(canvas);
        canvas.drawBitmap((Bitmap) this.mDrawBitmap.get(), 0.0f, 0.0f, null);
        drawCenterText(canvas);
    }

    protected void drawHole(Canvas canvas) {
        if (this.mChart.isDrawHoleEnabled() && this.mBitmapCanvas != null) {
            float radius = this.mChart.getRadius();
            float holeRadius = (this.mChart.getHoleRadius() / 100.0f) * radius;
            MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
            if (Color.alpha(this.mHolePaint.getColor()) > 0) {
                this.mBitmapCanvas.drawCircle(centerCircleBox.x, centerCircleBox.y, holeRadius, this.mHolePaint);
            }
            if (Color.alpha(this.mTransparentCirclePaint.getColor()) > 0 && this.mChart.getTransparentCircleRadius() > this.mChart.getHoleRadius()) {
                int alpha = this.mTransparentCirclePaint.getAlpha();
                radius *= this.mChart.getTransparentCircleRadius() / 100.0f;
                this.mTransparentCirclePaint.setAlpha((int) ((((float) alpha) * this.mAnimator.getPhaseX()) * this.mAnimator.getPhaseY()));
                this.mHoleCirclePath.reset();
                this.mHoleCirclePath.addCircle(centerCircleBox.x, centerCircleBox.y, radius, Direction.CW);
                this.mHoleCirclePath.addCircle(centerCircleBox.x, centerCircleBox.y, holeRadius, Direction.CCW);
                this.mBitmapCanvas.drawPath(this.mHoleCirclePath, this.mTransparentCirclePaint);
                this.mTransparentCirclePaint.setAlpha(alpha);
            }
            MPPointF.recycleInstance(centerCircleBox);
        }
    }

    protected void drawCenterText(Canvas canvas) {
        CharSequence centerText = this.mChart.getCenterText();
        if (this.mChart.isDrawCenterTextEnabled() && centerText != null) {
            float radius;
            MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
            MPPointF centerTextOffset = this.mChart.getCenterTextOffset();
            float f = centerTextOffset.x + centerCircleBox.x;
            float f2 = centerTextOffset.y + centerCircleBox.y;
            if (!this.mChart.isDrawHoleEnabled() || this.mChart.isDrawSlicesUnderHoleEnabled()) {
                radius = this.mChart.getRadius();
            } else {
                radius = this.mChart.getRadius() * (this.mChart.getHoleRadius() / 100.0f);
            }
            RectF rectF = this.mRectBuffer[0];
            rectF.left = f - radius;
            rectF.top = f2 - radius;
            rectF.right = f + radius;
            rectF.bottom = radius + f2;
            RectF rectF2 = this.mRectBuffer[1];
            rectF2.set(rectF);
            radius = this.mChart.getCenterTextRadiusPercent() / 100.0f;
            if (((double) radius) > Utils.DOUBLE_EPSILON) {
                rectF2.inset((rectF2.width() - (rectF2.width() * radius)) / 2.0f, (rectF2.height() - (radius * rectF2.height())) / 2.0f);
            }
            if (!(centerText.equals(this.mCenterTextLastValue) && rectF2.equals(this.mCenterTextLastBounds))) {
                this.mCenterTextLastBounds.set(rectF2);
                this.mCenterTextLastValue = centerText;
                this.mCenterTextLayout = new StaticLayout(centerText, 0, centerText.length(), this.mCenterTextPaint, (int) Math.max(Math.ceil((double) this.mCenterTextLastBounds.width()), 1.0d), Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            }
            radius = (float) this.mCenterTextLayout.getHeight();
            canvas.save();
            if (VERSION.SDK_INT >= 18) {
                Path path = this.mDrawCenterTextPathBuffer;
                path.reset();
                path.addOval(rectF, Direction.CW);
                canvas.clipPath(path);
            }
            canvas.translate(rectF2.left, ((rectF2.height() - radius) / 2.0f) + rectF2.top);
            this.mCenterTextLayout.draw(canvas);
            canvas.restore();
            MPPointF.recycleInstance(centerCircleBox);
            MPPointF.recycleInstance(centerTextOffset);
        }
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        float holeRadius;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float rotationAngle = this.mChart.getRotationAngle();
        float[] drawAngles = this.mChart.getDrawAngles();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles();
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        Object obj = (!this.mChart.isDrawHoleEnabled() || this.mChart.isDrawSlicesUnderHoleEnabled()) ? null : 1;
        if (obj != null) {
            holeRadius = (this.mChart.getHoleRadius() / 100.0f) * radius;
        } else {
            holeRadius = 0.0f;
        }
        RectF rectF = this.mDrawHighlightedRectF;
        rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int i = 0; i < highlightArr.length; i++) {
            int x = (int) highlightArr[i].getX();
            if (x < drawAngles.length) {
                IPieDataSet dataSetByIndex = ((PieData) this.mChart.getData()).getDataSetByIndex(highlightArr[i].getDataSetIndex());
                if (dataSetByIndex != null && dataSetByIndex.isHighlightEnabled()) {
                    float f;
                    int entryCount = dataSetByIndex.getEntryCount();
                    int i2 = 0;
                    int i3 = 0;
                    while (i3 < entryCount) {
                        int i4;
                        if (Math.abs(((PieEntry) dataSetByIndex.getEntryForIndex(i3)).getY()) > Utils.FLOAT_EPSILON) {
                            i4 = i2 + 1;
                        } else {
                            i4 = i2;
                        }
                        i3++;
                        i2 = i4;
                    }
                    if (x == 0) {
                        f = 0.0f;
                    } else {
                        f = absoluteAngles[x - 1] * phaseX;
                    }
                    float sliceSpace = i2 <= 1 ? 0.0f : dataSetByIndex.getSliceSpace();
                    float f2 = drawAngles[x];
                    float selectionShift = dataSetByIndex.getSelectionShift();
                    float f3 = radius + selectionShift;
                    rectF.set(this.mChart.getCircleBox());
                    rectF.inset(-selectionShift, -selectionShift);
                    Object obj2 = (sliceSpace <= 0.0f || f2 > 180.0f) ? null : 1;
                    this.mRenderPaint.setColor(dataSetByIndex.getColor(x));
                    float f4 = i2 == 1 ? 0.0f : sliceSpace / (0.017453292f * radius);
                    if (i2 == 1) {
                        selectionShift = 0.0f;
                    } else {
                        selectionShift = sliceSpace / (0.017453292f * f3);
                    }
                    float f5 = rotationAngle + (((f4 / 2.0f) + f) * phaseY);
                    float f6 = (f2 - f4) * phaseY;
                    if (f6 < 0.0f) {
                        f6 = 0.0f;
                    }
                    f4 = (((selectionShift / 2.0f) + f) * phaseY) + rotationAngle;
                    selectionShift = (f2 - selectionShift) * phaseY;
                    if (selectionShift < 0.0f) {
                        selectionShift = 0.0f;
                    }
                    this.mPathBuffer.reset();
                    if (f6 < 360.0f || f6 % 360.0f > Utils.FLOAT_EPSILON) {
                        this.mPathBuffer.moveTo(centerCircleBox.x + (((float) Math.cos((double) (0.017453292f * f4))) * f3), (f3 * ((float) Math.sin((double) (0.017453292f * f4)))) + centerCircleBox.y);
                        this.mPathBuffer.arcTo(rectF, f4, selectionShift);
                    } else {
                        this.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, f3, Direction.CW);
                    }
                    selectionShift = 0.0f;
                    if (obj2 != null) {
                        selectionShift = calculateMinimumRadiusForSpacedSlice(centerCircleBox, radius, f2 * phaseY, (((float) Math.cos((double) (0.017453292f * f5))) * radius) + centerCircleBox.x, (((float) Math.sin((double) (0.017453292f * f5))) * radius) + centerCircleBox.y, f5, f6);
                    }
                    this.mInnerRectBuffer.set(centerCircleBox.x - holeRadius, centerCircleBox.y - holeRadius, centerCircleBox.x + holeRadius, centerCircleBox.y + holeRadius);
                    if (obj != null && (holeRadius > 0.0f || obj2 != null)) {
                        if (obj2 != null) {
                            if (selectionShift < 0.0f) {
                                selectionShift = -selectionShift;
                            }
                            f4 = Math.max(holeRadius, selectionShift);
                        } else {
                            f4 = holeRadius;
                        }
                        if (i2 == 1 || f4 == 0.0f) {
                            selectionShift = 0.0f;
                        } else {
                            selectionShift = sliceSpace / (0.017453292f * f4);
                        }
                        float f7 = (((selectionShift / 2.0f) + f) * phaseY) + rotationAngle;
                        selectionShift = (f2 - selectionShift) * phaseY;
                        if (selectionShift < 0.0f) {
                            selectionShift = 0.0f;
                        }
                        f7 += selectionShift;
                        if (f6 < 360.0f || f6 % 360.0f > Utils.FLOAT_EPSILON) {
                            this.mPathBuffer.lineTo(centerCircleBox.x + (((float) Math.cos((double) (0.017453292f * f7))) * f4), (f4 * ((float) Math.sin((double) (0.017453292f * f7)))) + centerCircleBox.y);
                            this.mPathBuffer.arcTo(this.mInnerRectBuffer, f7, -selectionShift);
                        } else {
                            this.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, f4, Direction.CCW);
                        }
                    } else if (f6 % 360.0f > Utils.FLOAT_EPSILON) {
                        if (obj2 != null) {
                            f4 = (f6 / 2.0f) + f5;
                            this.mPathBuffer.lineTo(centerCircleBox.x + (((float) Math.cos((double) (0.017453292f * f4))) * selectionShift), (selectionShift * ((float) Math.sin((double) (f4 * 0.017453292f)))) + centerCircleBox.y);
                        } else {
                            this.mPathBuffer.lineTo(centerCircleBox.x, centerCircleBox.y);
                        }
                    }
                    this.mPathBuffer.close();
                    this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
                }
            }
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    protected void drawRoundedSlices(Canvas canvas) {
        if (this.mChart.isDrawRoundedSlicesEnabled()) {
            IPieDataSet dataSet = ((PieData) this.mChart.getData()).getDataSet();
            if (dataSet.isVisible()) {
                float phaseX = this.mAnimator.getPhaseX();
                float phaseY = this.mAnimator.getPhaseY();
                MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
                float radius = this.mChart.getRadius();
                float holeRadius = (radius - ((this.mChart.getHoleRadius() * radius) / 100.0f)) / 2.0f;
                float[] drawAngles = this.mChart.getDrawAngles();
                float rotationAngle = this.mChart.getRotationAngle();
                for (int i = 0; i < dataSet.getEntryCount(); i++) {
                    float f = drawAngles[i];
                    if (Math.abs(dataSet.getEntryForIndex(i).getY()) > Utils.FLOAT_EPSILON) {
                        float cos = (float) ((((double) (radius - holeRadius)) * Math.cos(Math.toRadians((double) ((rotationAngle + f) * phaseY)))) + ((double) centerCircleBox.x));
                        float sin = (float) ((((double) (radius - holeRadius)) * Math.sin(Math.toRadians((double) ((rotationAngle + f) * phaseY)))) + ((double) centerCircleBox.y));
                        this.mRenderPaint.setColor(dataSet.getColor(i));
                        this.mBitmapCanvas.drawCircle(cos, sin, holeRadius, this.mRenderPaint);
                    }
                    rotationAngle += f * phaseX;
                }
                MPPointF.recycleInstance(centerCircleBox);
            }
        }
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
