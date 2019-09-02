package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.PieRadarChartBase;
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;

public class PieRadarChartTouchListener extends ChartTouchListener<PieRadarChartBase<?>> {
    private ArrayList<AngularVelocitySample> _velocitySamples = new ArrayList();
    private float mDecelerationAngularVelocity = 0.0f;
    private long mDecelerationLastTime = 0;
    private float mStartAngle = 0.0f;
    private MPPointF mTouchStartPoint = MPPointF.getInstance(0.0f, 0.0f);

    private class AngularVelocitySample {
        public float angle;
        public long time;

        public AngularVelocitySample(long j, float f) {
            this.time = j;
            this.angle = f;
        }
    }

    public PieRadarChartTouchListener(PieRadarChartBase<?> pieRadarChartBase) {
        super(pieRadarChartBase);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.mGestureDetector.onTouchEvent(motionEvent) && ((PieRadarChartBase) this.mChart).isRotationEnabled()) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            switch (motionEvent.getAction()) {
                case 0:
                    startAction(motionEvent);
                    stopDeceleration();
                    resetVelocity();
                    if (((PieRadarChartBase) this.mChart).isDragDecelerationEnabled()) {
                        sampleVelocity(x, y);
                    }
                    setGestureStartAngle(x, y);
                    this.mTouchStartPoint.x = x;
                    this.mTouchStartPoint.y = y;
                    break;
                case 1:
                    if (((PieRadarChartBase) this.mChart).isDragDecelerationEnabled()) {
                        stopDeceleration();
                        sampleVelocity(x, y);
                        this.mDecelerationAngularVelocity = calculateVelocity();
                        if (this.mDecelerationAngularVelocity != 0.0f) {
                            this.mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
                            Utils.postInvalidateOnAnimation(this.mChart);
                        }
                    }
                    ((PieRadarChartBase) this.mChart).enableScroll();
                    this.mTouchMode = 0;
                    endAction(motionEvent);
                    break;
                case 2:
                    if (((PieRadarChartBase) this.mChart).isDragDecelerationEnabled()) {
                        sampleVelocity(x, y);
                    }
                    if (this.mTouchMode == 0 && ChartTouchListener.distance(x, this.mTouchStartPoint.x, y, this.mTouchStartPoint.y) > Utils.convertDpToPixel(8.0f)) {
                        this.mLastGesture = ChartGesture.ROTATE;
                        this.mTouchMode = 6;
                        ((PieRadarChartBase) this.mChart).disableScroll();
                    } else if (this.mTouchMode == 6) {
                        updateGestureRotation(x, y);
                        ((PieRadarChartBase) this.mChart).invalidate();
                    }
                    endAction(motionEvent);
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.mLastGesture = ChartGesture.LONG_PRESS;
        OnChartGestureListener onChartGestureListener = ((PieRadarChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartLongPressed(motionEvent);
        }
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.mLastGesture = ChartGesture.SINGLE_TAP;
        OnChartGestureListener onChartGestureListener = ((PieRadarChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartSingleTapped(motionEvent);
        }
        if (!((PieRadarChartBase) this.mChart).isHighlightPerTapEnabled()) {
            return false;
        }
        performHighlight(((PieRadarChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY()), motionEvent);
        return true;
    }

    private void resetVelocity() {
        this._velocitySamples.clear();
    }

    private void sampleVelocity(float f, float f2) {
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this._velocitySamples.add(new AngularVelocitySample(currentAnimationTimeMillis, ((PieRadarChartBase) this.mChart).getAngleForPoint(f, f2)));
        for (int size = this._velocitySamples.size(); size - 2 > 0 && currentAnimationTimeMillis - ((AngularVelocitySample) this._velocitySamples.get(0)).time > 1000; size--) {
            this._velocitySamples.remove(0);
        }
    }

    private float calculateVelocity() {
        if (this._velocitySamples.isEmpty()) {
            return 0.0f;
        }
        AngularVelocitySample angularVelocitySample = (AngularVelocitySample) this._velocitySamples.get(0);
        AngularVelocitySample angularVelocitySample2 = (AngularVelocitySample) this._velocitySamples.get(this._velocitySamples.size() - 1);
        AngularVelocitySample angularVelocitySample3 = angularVelocitySample;
        for (int size = this._velocitySamples.size() - 1; size >= 0; size--) {
            angularVelocitySample3 = (AngularVelocitySample) this._velocitySamples.get(size);
            if (angularVelocitySample3.angle != angularVelocitySample2.angle) {
                break;
            }
        }
        float f = ((float) (angularVelocitySample2.time - angularVelocitySample.time)) / 1000.0f;
        if (f == 0.0f) {
            f = 0.1f;
        }
        Object obj = angularVelocitySample2.angle >= angularVelocitySample3.angle ? 1 : null;
        Object obj2 = ((double) Math.abs(angularVelocitySample2.angle - angularVelocitySample3.angle)) > 270.0d ? obj == null ? 1 : null : obj;
        if (((double) (angularVelocitySample2.angle - angularVelocitySample.angle)) > 180.0d) {
            angularVelocitySample.angle = (float) (((double) angularVelocitySample.angle) + 360.0d);
        } else if (((double) (angularVelocitySample.angle - angularVelocitySample2.angle)) > 180.0d) {
            angularVelocitySample2.angle = (float) (((double) angularVelocitySample2.angle) + 360.0d);
        }
        float abs = Math.abs((angularVelocitySample2.angle - angularVelocitySample.angle) / f);
        if (obj2 == null) {
            return -abs;
        }
        return abs;
    }

    public void setGestureStartAngle(float f, float f2) {
        this.mStartAngle = ((PieRadarChartBase) this.mChart).getAngleForPoint(f, f2) - ((PieRadarChartBase) this.mChart).getRawRotationAngle();
    }

    public void updateGestureRotation(float f, float f2) {
        ((PieRadarChartBase) this.mChart).setRotationAngle(((PieRadarChartBase) this.mChart).getAngleForPoint(f, f2) - this.mStartAngle);
    }

    public void stopDeceleration() {
        this.mDecelerationAngularVelocity = 0.0f;
    }

    public void computeScroll() {
        if (this.mDecelerationAngularVelocity != 0.0f) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.mDecelerationAngularVelocity = ((PieRadarChartBase) this.mChart).getDragDecelerationFrictionCoef() * this.mDecelerationAngularVelocity;
            ((PieRadarChartBase) this.mChart).setRotationAngle(((PieRadarChartBase) this.mChart).getRotationAngle() + ((((float) (currentAnimationTimeMillis - this.mDecelerationLastTime)) / 1000.0f) * this.mDecelerationAngularVelocity));
            this.mDecelerationLastTime = currentAnimationTimeMillis;
            if (((double) Math.abs(this.mDecelerationAngularVelocity)) >= 0.001d) {
                Utils.postInvalidateOnAnimation(this.mChart);
            } else {
                stopDeceleration();
            }
        }
    }
}
