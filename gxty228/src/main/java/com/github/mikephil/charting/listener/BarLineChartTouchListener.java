package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class BarLineChartTouchListener extends ChartTouchListener<BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>> {
    private IDataSet mClosestDataSetToTouch;
    private MPPointF mDecelerationCurrentPoint = MPPointF.getInstance(0.0f, 0.0f);
    private long mDecelerationLastTime = 0;
    private MPPointF mDecelerationVelocity = MPPointF.getInstance(0.0f, 0.0f);
    private float mDragTriggerDist;
    private Matrix mMatrix = new Matrix();
    private float mMinScalePointerDistance;
    private float mSavedDist = 1.0f;
    private Matrix mSavedMatrix = new Matrix();
    private float mSavedXDist = 1.0f;
    private float mSavedYDist = 1.0f;
    private MPPointF mTouchPointCenter = MPPointF.getInstance(0.0f, 0.0f);
    private MPPointF mTouchStartPoint = MPPointF.getInstance(0.0f, 0.0f);
    private VelocityTracker mVelocityTracker;

    public BarLineChartTouchListener(BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> barLineChartBase, Matrix matrix, float f) {
        super(barLineChartBase);
        this.mMatrix = matrix;
        this.mDragTriggerDist = Utils.convertDpToPixel(f);
        this.mMinScalePointerDistance = Utils.convertDpToPixel(3.5f);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int i = 2;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (motionEvent.getActionMasked() == 3 && this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        if (this.mTouchMode == 0) {
            this.mGestureDetector.onTouchEvent(motionEvent);
        }
        if (((BarLineChartBase) this.mChart).isDragEnabled() || ((BarLineChartBase) this.mChart).isScaleXEnabled() || ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
            switch (motionEvent.getAction() & 255) {
                case 0:
                    startAction(motionEvent);
                    stopDeceleration();
                    saveTouchStart(motionEvent);
                    break;
                case 1:
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    int pointerId = motionEvent.getPointerId(0);
                    velocityTracker.computeCurrentVelocity(1000, (float) Utils.getMaximumFlingVelocity());
                    float yVelocity = velocityTracker.getYVelocity(pointerId);
                    float xVelocity = velocityTracker.getXVelocity(pointerId);
                    if ((Math.abs(xVelocity) > ((float) Utils.getMinimumFlingVelocity()) || Math.abs(yVelocity) > ((float) Utils.getMinimumFlingVelocity())) && this.mTouchMode == 1 && ((BarLineChartBase) this.mChart).isDragDecelerationEnabled()) {
                        stopDeceleration();
                        this.mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
                        this.mDecelerationCurrentPoint.x = motionEvent.getX();
                        this.mDecelerationCurrentPoint.y = motionEvent.getY();
                        this.mDecelerationVelocity.x = xVelocity;
                        this.mDecelerationVelocity.y = yVelocity;
                        Utils.postInvalidateOnAnimation(this.mChart);
                    }
                    if (this.mTouchMode == 2 || this.mTouchMode == 3 || this.mTouchMode == 4 || this.mTouchMode == 5) {
                        ((BarLineChartBase) this.mChart).calculateOffsets();
                        ((BarLineChartBase) this.mChart).postInvalidate();
                    }
                    this.mTouchMode = 0;
                    ((BarLineChartBase) this.mChart).enableScroll();
                    if (this.mVelocityTracker != null) {
                        this.mVelocityTracker.recycle();
                        this.mVelocityTracker = null;
                    }
                    endAction(motionEvent);
                    break;
                case 2:
                    if (this.mTouchMode != 1) {
                        if (this.mTouchMode != 2 && this.mTouchMode != 3 && this.mTouchMode != 4) {
                            if (this.mTouchMode == 0 && Math.abs(ChartTouchListener.distance(motionEvent.getX(), this.mTouchStartPoint.x, motionEvent.getY(), this.mTouchStartPoint.y)) > this.mDragTriggerDist) {
                                if (!((BarLineChartBase) this.mChart).hasNoDragOffset()) {
                                    if (((BarLineChartBase) this.mChart).isDragEnabled()) {
                                        this.mLastGesture = ChartGesture.DRAG;
                                        this.mTouchMode = 1;
                                        break;
                                    }
                                } else if (!((BarLineChartBase) this.mChart).isFullyZoomedOut() && ((BarLineChartBase) this.mChart).isDragEnabled()) {
                                    this.mTouchMode = 1;
                                    break;
                                } else {
                                    this.mLastGesture = ChartGesture.DRAG;
                                    if (((BarLineChartBase) this.mChart).isHighlightPerDragEnabled()) {
                                        performHighlightDrag(motionEvent);
                                        break;
                                    }
                                }
                            }
                        }
                        ((BarLineChartBase) this.mChart).disableScroll();
                        if (((BarLineChartBase) this.mChart).isScaleXEnabled() || ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                            performZoom(motionEvent);
                            break;
                        }
                    }
                    ((BarLineChartBase) this.mChart).disableScroll();
                    performDrag(motionEvent);
                    break;
                    break;
                case 3:
                    this.mTouchMode = 0;
                    endAction(motionEvent);
                    break;
                case 5:
                    if (motionEvent.getPointerCount() >= 2) {
                        ((BarLineChartBase) this.mChart).disableScroll();
                        saveTouchStart(motionEvent);
                        this.mSavedXDist = getXDist(motionEvent);
                        this.mSavedYDist = getYDist(motionEvent);
                        this.mSavedDist = spacing(motionEvent);
                        if (this.mSavedDist > 10.0f) {
                            if (((BarLineChartBase) this.mChart).isPinchZoomEnabled()) {
                                this.mTouchMode = 4;
                            } else if (((BarLineChartBase) this.mChart).isScaleXEnabled() != ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                                this.mTouchMode = ((BarLineChartBase) this.mChart).isScaleXEnabled() ? 2 : 3;
                            } else {
                                if (this.mSavedXDist <= this.mSavedYDist) {
                                    i = 3;
                                }
                                this.mTouchMode = i;
                            }
                        }
                        midPoint(this.mTouchPointCenter, motionEvent);
                        break;
                    }
                    break;
                case 6:
                    Utils.velocityTrackerPointerUpCleanUpIfNecessary(motionEvent, this.mVelocityTracker);
                    this.mTouchMode = 5;
                    break;
            }
            this.mMatrix = ((BarLineChartBase) this.mChart).getViewPortHandler().refresh(this.mMatrix, this.mChart, true);
        }
        return true;
    }

    private void saveTouchStart(MotionEvent motionEvent) {
        this.mSavedMatrix.set(this.mMatrix);
        this.mTouchStartPoint.x = motionEvent.getX();
        this.mTouchStartPoint.y = motionEvent.getY();
        this.mClosestDataSetToTouch = ((BarLineChartBase) this.mChart).getDataSetByTouchPoint(motionEvent.getX(), motionEvent.getY());
    }

    private void performDrag(MotionEvent motionEvent) {
        float x;
        float y;
        this.mLastGesture = ChartGesture.DRAG;
        this.mMatrix.set(this.mSavedMatrix);
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (!inverted()) {
            x = motionEvent.getX() - this.mTouchStartPoint.x;
            y = motionEvent.getY() - this.mTouchStartPoint.y;
        } else if (this.mChart instanceof HorizontalBarChart) {
            x = -(motionEvent.getX() - this.mTouchStartPoint.x);
            y = motionEvent.getY() - this.mTouchStartPoint.y;
        } else {
            x = motionEvent.getX() - this.mTouchStartPoint.x;
            y = -(motionEvent.getY() - this.mTouchStartPoint.y);
        }
        this.mMatrix.postTranslate(x, y);
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartTranslate(motionEvent, x, y);
        }
    }

    private void performZoom(MotionEvent motionEvent) {
        Object obj = 1;
        if (motionEvent.getPointerCount() >= 2) {
            OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
            float spacing = spacing(motionEvent);
            if (spacing > this.mMinScalePointerDistance) {
                MPPointF trans = getTrans(this.mTouchPointCenter.x, this.mTouchPointCenter.y);
                ViewPortHandler viewPortHandler = ((BarLineChartBase) this.mChart).getViewPortHandler();
                if (this.mTouchMode == 4) {
                    boolean canZoomOutMoreY;
                    float f;
                    this.mLastGesture = ChartGesture.PINCH_ZOOM;
                    spacing /= this.mSavedDist;
                    if (spacing >= 1.0f) {
                        obj = null;
                    }
                    boolean canZoomOutMoreX;
                    if (obj != null) {
                        canZoomOutMoreX = viewPortHandler.canZoomOutMoreX();
                    } else {
                        canZoomOutMoreX = viewPortHandler.canZoomInMoreX();
                    }
                    if (obj != null) {
                        canZoomOutMoreY = viewPortHandler.canZoomOutMoreY();
                    } else {
                        canZoomOutMoreY = viewPortHandler.canZoomInMoreY();
                    }
                    if (((BarLineChartBase) this.mChart).isScaleXEnabled()) {
                        f = spacing;
                    } else {
                        f = 1.0f;
                    }
                    if (!((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                        spacing = 1.0f;
                    }
                    if (canZoomOutMoreY || r5) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(f, spacing, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, f, spacing);
                        }
                    }
                } else if (this.mTouchMode == 2 && ((BarLineChartBase) this.mChart).isScaleXEnabled()) {
                    this.mLastGesture = ChartGesture.X_ZOOM;
                    spacing = getXDist(motionEvent) / this.mSavedXDist;
                    if (spacing >= 1.0f) {
                        obj = null;
                    }
                    if (obj != null) {
                        r0 = viewPortHandler.canZoomOutMoreX();
                    } else {
                        r0 = viewPortHandler.canZoomInMoreX();
                    }
                    if (r0) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(spacing, 1.0f, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, spacing, 1.0f);
                        }
                    }
                } else if (this.mTouchMode == 3 && ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                    this.mLastGesture = ChartGesture.Y_ZOOM;
                    spacing = getYDist(motionEvent) / this.mSavedYDist;
                    if ((spacing < 1.0f ? 1 : null) != null) {
                        r0 = viewPortHandler.canZoomOutMoreY();
                    } else {
                        r0 = viewPortHandler.canZoomInMoreY();
                    }
                    if (r0) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(1.0f, spacing, trans.x, trans.y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, 1.0f, spacing);
                        }
                    }
                }
                MPPointF.recycleInstance(trans);
            }
        }
    }

    private void performHighlightDrag(MotionEvent motionEvent) {
        Highlight highlightByTouchPoint = ((BarLineChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY());
        if (highlightByTouchPoint != null && !highlightByTouchPoint.equalTo(this.mLastHighlighted)) {
            this.mLastHighlighted = highlightByTouchPoint;
            ((BarLineChartBase) this.mChart).highlightValue(highlightByTouchPoint, true);
        }
    }

    private static void midPoint(MPPointF mPPointF, MotionEvent motionEvent) {
        float y = motionEvent.getY(0) + motionEvent.getY(1);
        mPPointF.x = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
        mPPointF.y = y / 2.0f;
    }

    private static float spacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    private static float getXDist(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getX(0) - motionEvent.getX(1));
    }

    private static float getYDist(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getY(0) - motionEvent.getY(1));
    }

    public MPPointF getTrans(float f, float f2) {
        float f3;
        ViewPortHandler viewPortHandler = ((BarLineChartBase) this.mChart).getViewPortHandler();
        float offsetLeft = f - viewPortHandler.offsetLeft();
        if (inverted()) {
            f3 = -(f2 - viewPortHandler.offsetTop());
        } else {
            f3 = -((((float) ((BarLineChartBase) this.mChart).getMeasuredHeight()) - f2) - viewPortHandler.offsetBottom());
        }
        return MPPointF.getInstance(offsetLeft, f3);
    }

    private boolean inverted() {
        return (this.mClosestDataSetToTouch == null && ((BarLineChartBase) this.mChart).isAnyAxisInverted()) || (this.mClosestDataSetToTouch != null && ((BarLineChartBase) this.mChart).isInverted(this.mClosestDataSetToTouch.getAxisDependency()));
    }

    public Matrix getMatrix() {
        return this.mMatrix;
    }

    public void setDragTriggerDist(float f) {
        this.mDragTriggerDist = Utils.convertDpToPixel(f);
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        float f = 1.4f;
        this.mLastGesture = ChartGesture.DOUBLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartDoubleTapped(motionEvent);
        }
        if (((BarLineChartBase) this.mChart).isDoubleTapToZoomEnabled() && ((BarLineScatterCandleBubbleData) ((BarLineChartBase) this.mChart).getData()).getEntryCount() > 0) {
            MPPointF trans = getTrans(motionEvent.getX(), motionEvent.getY());
            BarLineChartBase barLineChartBase = (BarLineChartBase) this.mChart;
            float f2 = ((BarLineChartBase) this.mChart).isScaleXEnabled() ? 1.4f : 1.0f;
            if (!((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                f = 1.0f;
            }
            barLineChartBase.zoom(f2, f, trans.x, trans.y);
            if (((BarLineChartBase) this.mChart).isLogEnabled()) {
                Log.i("BarlineChartTouch", "Double-Tap, Zooming In, x: " + trans.x + ", y: " + trans.y);
            }
            MPPointF.recycleInstance(trans);
        }
        return super.onDoubleTap(motionEvent);
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.mLastGesture = ChartGesture.LONG_PRESS;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartLongPressed(motionEvent);
        }
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.mLastGesture = ChartGesture.SINGLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartSingleTapped(motionEvent);
        }
        if (!((BarLineChartBase) this.mChart).isHighlightPerTapEnabled()) {
            return false;
        }
        performHighlight(((BarLineChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY()), motionEvent);
        return super.onSingleTapUp(motionEvent);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.mLastGesture = ChartGesture.FLING;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartFling(motionEvent, motionEvent2, f, f2);
        }
        return super.onFling(motionEvent, motionEvent2, f, f2);
    }

    public void stopDeceleration() {
        this.mDecelerationVelocity.x = 0.0f;
        this.mDecelerationVelocity.y = 0.0f;
    }

    public void computeScroll() {
        if (this.mDecelerationVelocity.x != 0.0f || this.mDecelerationVelocity.y != 0.0f) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            MPPointF mPPointF = this.mDecelerationVelocity;
            mPPointF.x = ((BarLineChartBase) this.mChart).getDragDecelerationFrictionCoef() * mPPointF.x;
            mPPointF = this.mDecelerationVelocity;
            mPPointF.y = ((BarLineChartBase) this.mChart).getDragDecelerationFrictionCoef() * mPPointF.y;
            float f = ((float) (currentAnimationTimeMillis - this.mDecelerationLastTime)) / 1000.0f;
            float f2 = this.mDecelerationVelocity.x * f;
            f *= this.mDecelerationVelocity.y;
            MPPointF mPPointF2 = this.mDecelerationCurrentPoint;
            mPPointF2.x = f2 + mPPointF2.x;
            mPPointF = this.mDecelerationCurrentPoint;
            mPPointF.y = f + mPPointF.y;
            MotionEvent obtain = MotionEvent.obtain(currentAnimationTimeMillis, currentAnimationTimeMillis, 2, this.mDecelerationCurrentPoint.x, this.mDecelerationCurrentPoint.y, 0);
            performDrag(obtain);
            obtain.recycle();
            this.mMatrix = ((BarLineChartBase) this.mChart).getViewPortHandler().refresh(this.mMatrix, this.mChart, false);
            this.mDecelerationLastTime = currentAnimationTimeMillis;
            if (((double) Math.abs(this.mDecelerationVelocity.x)) >= 0.01d || ((double) Math.abs(this.mDecelerationVelocity.y)) >= 0.01d) {
                Utils.postInvalidateOnAnimation(this.mChart);
                return;
            }
            ((BarLineChartBase) this.mChart).calculateOffsets();
            ((BarLineChartBase) this.mChart).postInvalidate();
            stopDeceleration();
        }
    }
}
