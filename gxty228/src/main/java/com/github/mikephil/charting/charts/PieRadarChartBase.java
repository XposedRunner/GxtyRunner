package com.github.mikephil.charting.charts;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.PieRadarChartTouchListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

public abstract class PieRadarChartBase<T extends ChartData<? extends IDataSet<? extends Entry>>> extends Chart<T> {
    protected float mMinOffset = 0.0f;
    private float mRawRotationAngle = 270.0f;
    protected boolean mRotateEnabled = true;
    private float mRotationAngle = 270.0f;

    public abstract int getIndexForAngle(float f);

    public abstract float getRadius();

    protected abstract float getRequiredBaseOffset();

    protected abstract float getRequiredLegendOffset();

    public PieRadarChartBase(Context context) {
        super(context);
    }

    public PieRadarChartBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PieRadarChartBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void init() {
        super.init();
        this.mChartTouchListener = new PieRadarChartTouchListener(this);
    }

    protected void calcMinMax() {
    }

    public int getMaxVisibleCount() {
        return this.mData.getEntryCount();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mTouchEnabled || this.mChartTouchListener == null) {
            return super.onTouchEvent(motionEvent);
        }
        return this.mChartTouchListener.onTouch(this, motionEvent);
    }

    public void computeScroll() {
        if (this.mChartTouchListener instanceof PieRadarChartTouchListener) {
            ((PieRadarChartTouchListener) this.mChartTouchListener).computeScroll();
        }
    }

    public void notifyDataSetChanged() {
        if (this.mData != null) {
            calcMinMax();
            if (this.mLegend != null) {
                this.mLegendRenderer.computeLegend(this.mData);
            }
            calculateOffsets();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void calculateOffsets() {
        /*
        r10 = this;
        r6 = 1097859072; // 0x41700000 float:15.0 double:5.424144515E-315;
        r2 = 0;
        r0 = r10.mLegend;
        if (r0 == 0) goto L_0x0228;
    L_0x0007:
        r0 = r10.mLegend;
        r0 = r0.isEnabled();
        if (r0 == 0) goto L_0x0228;
    L_0x000f:
        r0 = r10.mLegend;
        r0 = r0.isDrawInsideEnabled();
        if (r0 != 0) goto L_0x0228;
    L_0x0017:
        r0 = r10.mLegend;
        r0 = r0.mNeededWidth;
        r1 = r10.mViewPortHandler;
        r1 = r1.getChartWidth();
        r3 = r10.mLegend;
        r3 = r3.getMaxSizePercent();
        r1 = r1 * r3;
        r0 = java.lang.Math.min(r0, r1);
        r1 = com.github.mikephil.charting.charts.PieRadarChartBase.AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation;
        r3 = r10.mLegend;
        r3 = r3.getOrientation();
        r3 = r3.ordinal();
        r1 = r1[r3];
        switch(r1) {
            case 1: goto L_0x00e1;
            case 2: goto L_0x01d6;
            default: goto L_0x003d;
        };
    L_0x003d:
        r0 = r2;
        r1 = r2;
        r3 = r2;
    L_0x0040:
        r4 = r10.getRequiredBaseOffset();
        r3 = r3 + r4;
        r4 = r10.getRequiredBaseOffset();
        r1 = r1 + r4;
        r4 = r10.getRequiredBaseOffset();
        r2 = r2 + r4;
        r4 = r10.getRequiredBaseOffset();
        r0 = r0 + r4;
    L_0x0054:
        r4 = r10.mMinOffset;
        r4 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r4);
        r5 = r10 instanceof com.github.mikephil.charting.charts.RadarChart;
        if (r5 == 0) goto L_0x0075;
    L_0x005e:
        r5 = r10.getXAxis();
        r6 = r5.isEnabled();
        if (r6 == 0) goto L_0x0075;
    L_0x0068:
        r6 = r5.isDrawLabelsEnabled();
        if (r6 == 0) goto L_0x0075;
    L_0x006e:
        r5 = r5.mLabelRotatedWidth;
        r5 = (float) r5;
        r4 = java.lang.Math.max(r4, r5);
    L_0x0075:
        r5 = r10.getExtraTopOffset();
        r2 = r2 + r5;
        r5 = r10.getExtraRightOffset();
        r1 = r1 + r5;
        r5 = r10.getExtraBottomOffset();
        r0 = r0 + r5;
        r5 = r10.getExtraLeftOffset();
        r3 = r3 + r5;
        r3 = java.lang.Math.max(r4, r3);
        r2 = java.lang.Math.max(r4, r2);
        r1 = java.lang.Math.max(r4, r1);
        r5 = r10.getRequiredBaseOffset();
        r0 = java.lang.Math.max(r5, r0);
        r0 = java.lang.Math.max(r4, r0);
        r4 = r10.mViewPortHandler;
        r4.restrainViewPort(r3, r2, r1, r0);
        r4 = r10.mLogEnabled;
        if (r4 == 0) goto L_0x00e0;
    L_0x00aa:
        r4 = "MPAndroidChart";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "offsetLeft: ";
        r5 = r5.append(r6);
        r3 = r5.append(r3);
        r5 = ", offsetTop: ";
        r3 = r3.append(r5);
        r2 = r3.append(r2);
        r3 = ", offsetRight: ";
        r2 = r2.append(r3);
        r1 = r2.append(r1);
        r2 = ", offsetBottom: ";
        r1 = r1.append(r2);
        r0 = r1.append(r0);
        r0 = r0.toString();
        android.util.Log.i(r4, r0);
    L_0x00e0:
        return;
    L_0x00e1:
        r1 = r10.mLegend;
        r1 = r1.getHorizontalAlignment();
        r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.LEFT;
        if (r1 == r3) goto L_0x00f5;
    L_0x00eb:
        r1 = r10.mLegend;
        r1 = r1.getHorizontalAlignment();
        r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.RIGHT;
        if (r1 != r3) goto L_0x0225;
    L_0x00f5:
        r1 = r10.mLegend;
        r1 = r1.getVerticalAlignment();
        r3 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.CENTER;
        if (r1 != r3) goto L_0x011e;
    L_0x00ff:
        r1 = 1095761920; // 0x41500000 float:13.0 double:5.413783207E-315;
        r1 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r1);
        r0 = r0 + r1;
    L_0x0106:
        r1 = com.github.mikephil.charting.charts.PieRadarChartBase.AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendHorizontalAlignment;
        r3 = r10.mLegend;
        r3 = r3.getHorizontalAlignment();
        r3 = r3.ordinal();
        r1 = r1[r3];
        switch(r1) {
            case 1: goto L_0x0119;
            case 2: goto L_0x0189;
            case 3: goto L_0x018e;
            default: goto L_0x0117;
        };
    L_0x0117:
        goto L_0x003d;
    L_0x0119:
        r1 = r2;
        r3 = r0;
        r0 = r2;
        goto L_0x0040;
    L_0x011e:
        r1 = 1090519040; // 0x41000000 float:8.0 double:5.38787994E-315;
        r1 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r1);
        r1 = r1 + r0;
        r0 = r10.mLegend;
        r0 = r0.mNeededHeight;
        r3 = r10.mLegend;
        r3 = r3.mTextHeightMax;
        r3 = r3 + r0;
        r4 = r10.getCenter();
        r0 = r10.mLegend;
        r0 = r0.getHorizontalAlignment();
        r5 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.RIGHT;
        if (r0 != r5) goto L_0x017f;
    L_0x013c:
        r0 = r10.getWidth();
        r0 = (float) r0;
        r0 = r0 - r1;
        r0 = r0 + r6;
    L_0x0143:
        r3 = r3 + r6;
        r5 = r10.distanceToCenter(r0, r3);
        r6 = r10.getRadius();
        r0 = r10.getAngleForPoint(r0, r3);
        r6 = r10.getPosition(r4, r6, r0);
        r0 = r6.x;
        r7 = r6.y;
        r0 = r10.distanceToCenter(r0, r7);
        r7 = 1084227584; // 0x40a00000 float:5.0 double:5.356796015E-315;
        r7 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r7);
        r8 = r4.y;
        r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1));
        if (r3 < 0) goto L_0x0182;
    L_0x0168:
        r3 = r10.getHeight();
        r3 = (float) r3;
        r3 = r3 - r1;
        r8 = r10.getWidth();
        r8 = (float) r8;
        r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1));
        if (r3 <= 0) goto L_0x0182;
    L_0x0177:
        r0 = r1;
    L_0x0178:
        com.github.mikephil.charting.utils.MPPointF.recycleInstance(r4);
        com.github.mikephil.charting.utils.MPPointF.recycleInstance(r6);
        goto L_0x0106;
    L_0x017f:
        r0 = r1 - r6;
        goto L_0x0143;
    L_0x0182:
        r1 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1));
        if (r1 >= 0) goto L_0x0222;
    L_0x0186:
        r0 = r0 - r5;
        r0 = r0 + r7;
        goto L_0x0178;
    L_0x0189:
        r1 = r0;
        r3 = r2;
        r0 = r2;
        goto L_0x0040;
    L_0x018e:
        r0 = com.github.mikephil.charting.charts.PieRadarChartBase.AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment;
        r1 = r10.mLegend;
        r1 = r1.getVerticalAlignment();
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x01a1;
            case 2: goto L_0x01bd;
            default: goto L_0x019f;
        };
    L_0x019f:
        goto L_0x003d;
    L_0x01a1:
        r0 = r10.mLegend;
        r0 = r0.mNeededHeight;
        r1 = r10.mViewPortHandler;
        r1 = r1.getChartHeight();
        r3 = r10.mLegend;
        r3 = r3.getMaxSizePercent();
        r1 = r1 * r3;
        r0 = java.lang.Math.min(r0, r1);
        r1 = r2;
        r3 = r2;
        r9 = r0;
        r0 = r2;
        r2 = r9;
        goto L_0x0040;
    L_0x01bd:
        r0 = r10.mLegend;
        r0 = r0.mNeededHeight;
        r1 = r10.mViewPortHandler;
        r1 = r1.getChartHeight();
        r3 = r10.mLegend;
        r3 = r3.getMaxSizePercent();
        r1 = r1 * r3;
        r0 = java.lang.Math.min(r0, r1);
        r1 = r2;
        r3 = r2;
        goto L_0x0040;
    L_0x01d6:
        r0 = r10.mLegend;
        r0 = r0.getVerticalAlignment();
        r1 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.TOP;
        if (r0 == r1) goto L_0x01ea;
    L_0x01e0:
        r0 = r10.mLegend;
        r0 = r0.getVerticalAlignment();
        r1 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.BOTTOM;
        if (r0 != r1) goto L_0x003d;
    L_0x01ea:
        r0 = r10.getRequiredLegendOffset();
        r1 = r10.mLegend;
        r1 = r1.mNeededHeight;
        r0 = r0 + r1;
        r1 = r10.mViewPortHandler;
        r1 = r1.getChartHeight();
        r3 = r10.mLegend;
        r3 = r3.getMaxSizePercent();
        r1 = r1 * r3;
        r0 = java.lang.Math.min(r0, r1);
        r1 = com.github.mikephil.charting.charts.PieRadarChartBase.AnonymousClass2.$SwitchMap$com$github$mikephil$charting$components$Legend$LegendVerticalAlignment;
        r3 = r10.mLegend;
        r3 = r3.getVerticalAlignment();
        r3 = r3.ordinal();
        r1 = r1[r3];
        switch(r1) {
            case 1: goto L_0x0217;
            case 2: goto L_0x021e;
            default: goto L_0x0215;
        };
    L_0x0215:
        goto L_0x003d;
    L_0x0217:
        r1 = r2;
        r3 = r2;
        r9 = r0;
        r0 = r2;
        r2 = r9;
        goto L_0x0040;
    L_0x021e:
        r1 = r2;
        r3 = r2;
        goto L_0x0040;
    L_0x0222:
        r0 = r2;
        goto L_0x0178;
    L_0x0225:
        r0 = r2;
        goto L_0x0106;
    L_0x0228:
        r0 = r2;
        r1 = r2;
        r3 = r2;
        goto L_0x0054;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.charts.PieRadarChartBase.calculateOffsets():void");
    }

    public float getAngleForPoint(float f, float f2) {
        MPPointF centerOffsets = getCenterOffsets();
        double d = (double) (f - centerOffsets.x);
        double d2 = (double) (f2 - centerOffsets.y);
        float toDegrees = (float) Math.toDegrees(Math.acos(d2 / Math.sqrt((d * d) + (d2 * d2))));
        if (f > centerOffsets.x) {
            toDegrees = 360.0f - toDegrees;
        }
        toDegrees += 90.0f;
        if (toDegrees > 360.0f) {
            toDegrees -= 360.0f;
        }
        MPPointF.recycleInstance(centerOffsets);
        return toDegrees;
    }

    public MPPointF getPosition(MPPointF mPPointF, float f, float f2) {
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        getPosition(mPPointF, f, f2, instance);
        return instance;
    }

    public void getPosition(MPPointF mPPointF, float f, float f2, MPPointF mPPointF2) {
        mPPointF2.x = (float) (((double) mPPointF.x) + (((double) f) * Math.cos(Math.toRadians((double) f2))));
        mPPointF2.y = (float) (((double) mPPointF.y) + (((double) f) * Math.sin(Math.toRadians((double) f2))));
    }

    public float distanceToCenter(float f, float f2) {
        float f3;
        float f4;
        MPPointF centerOffsets = getCenterOffsets();
        if (f > centerOffsets.x) {
            f3 = f - centerOffsets.x;
        } else {
            f3 = centerOffsets.x - f;
        }
        if (f2 > centerOffsets.y) {
            f4 = f2 - centerOffsets.y;
        } else {
            f4 = centerOffsets.y - f2;
        }
        f3 = (float) Math.sqrt(Math.pow((double) f4, 2.0d) + Math.pow((double) f3, 2.0d));
        MPPointF.recycleInstance(centerOffsets);
        return f3;
    }

    public void setRotationAngle(float f) {
        this.mRawRotationAngle = f;
        this.mRotationAngle = Utils.getNormalizedAngle(this.mRawRotationAngle);
    }

    public float getRawRotationAngle() {
        return this.mRawRotationAngle;
    }

    public float getRotationAngle() {
        return this.mRotationAngle;
    }

    public void setRotationEnabled(boolean z) {
        this.mRotateEnabled = z;
    }

    public boolean isRotationEnabled() {
        return this.mRotateEnabled;
    }

    public float getMinOffset() {
        return this.mMinOffset;
    }

    public void setMinOffset(float f) {
        this.mMinOffset = f;
    }

    public float getDiameter() {
        RectF contentRect = this.mViewPortHandler.getContentRect();
        contentRect.left += getExtraLeftOffset();
        contentRect.top += getExtraTopOffset();
        contentRect.right -= getExtraRightOffset();
        contentRect.bottom -= getExtraBottomOffset();
        return Math.min(contentRect.width(), contentRect.height());
    }

    public float getYChartMax() {
        return 0.0f;
    }

    public float getYChartMin() {
        return 0.0f;
    }

    @SuppressLint({"NewApi"})
    public void spin(int i, float f, float f2, EasingOption easingOption) {
        if (VERSION.SDK_INT >= 11) {
            setRotationAngle(f);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rotationAngle", new float[]{f, f2});
            ofFloat.setDuration((long) i);
            ofFloat.setInterpolator(Easing.getEasingFunctionFromOption(easingOption));
            ofFloat.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PieRadarChartBase.this.postInvalidate();
                }
            });
            ofFloat.start();
        }
    }
}
