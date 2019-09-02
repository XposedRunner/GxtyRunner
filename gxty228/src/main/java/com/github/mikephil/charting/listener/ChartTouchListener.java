package com.github.mikephil.charting.listener;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;

public abstract class ChartTouchListener<T extends Chart<?>> extends SimpleOnGestureListener implements OnTouchListener {
    protected static final int DRAG = 1;
    protected static final int NONE = 0;
    protected static final int PINCH_ZOOM = 4;
    protected static final int POST_ZOOM = 5;
    protected static final int ROTATE = 6;
    protected static final int X_ZOOM = 2;
    protected static final int Y_ZOOM = 3;
    protected T mChart;
    protected GestureDetector mGestureDetector;
    protected ChartGesture mLastGesture = ChartGesture.NONE;
    protected Highlight mLastHighlighted;
    protected int mTouchMode = 0;

    public enum ChartGesture {
        NONE,
        DRAG,
        X_ZOOM,
        Y_ZOOM,
        PINCH_ZOOM,
        ROTATE,
        SINGLE_TAP,
        DOUBLE_TAP,
        LONG_PRESS,
        FLING
    }

    public ChartTouchListener(T t) {
        this.mChart = t;
        this.mGestureDetector = new GestureDetector(t.getContext(), this);
    }

    public void startAction(MotionEvent motionEvent) {
        OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartGestureStart(motionEvent, this.mLastGesture);
        }
    }

    public void endAction(MotionEvent motionEvent) {
        OnChartGestureListener onChartGestureListener = this.mChart.getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartGestureEnd(motionEvent, this.mLastGesture);
        }
    }

    public void setLastHighlighted(Highlight highlight) {
        this.mLastHighlighted = highlight;
    }

    public int getTouchMode() {
        return this.mTouchMode;
    }

    public ChartGesture getLastGesture() {
        return this.mLastGesture;
    }

    protected void performHighlight(Highlight highlight, MotionEvent motionEvent) {
        if (highlight == null || highlight.equalTo(this.mLastHighlighted)) {
            this.mChart.highlightValue(null, true);
            this.mLastHighlighted = null;
            return;
        }
        this.mChart.highlightValue(highlight, true);
        this.mLastHighlighted = highlight;
    }

    protected static float distance(float f, float f2, float f3, float f4) {
        float f5 = f - f2;
        float f6 = f3 - f4;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }
}
