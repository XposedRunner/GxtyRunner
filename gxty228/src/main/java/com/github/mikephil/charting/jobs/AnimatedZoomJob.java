package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.ObjectPool;
import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint({"NewApi"})
public class AnimatedZoomJob extends AnimatedViewPortJob implements AnimatorListener {
    private static ObjectPool<AnimatedZoomJob> pool = ObjectPool.create(8, new AnimatedZoomJob(null, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0));
    protected Matrix mOnAnimationUpdateMatrixBuffer = new Matrix();
    protected float xAxisRange;
    protected YAxis yAxis;
    protected float zoomCenterX;
    protected float zoomCenterY;
    protected float zoomOriginX;
    protected float zoomOriginY;

    public static AnimatedZoomJob getInstance(ViewPortHandler viewPortHandler, View view, Transformer transformer, YAxis yAxis, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, long j) {
        AnimatedZoomJob animatedZoomJob = (AnimatedZoomJob) pool.get();
        animatedZoomJob.mViewPortHandler = viewPortHandler;
        animatedZoomJob.xValue = f2;
        animatedZoomJob.yValue = f3;
        animatedZoomJob.mTrans = transformer;
        animatedZoomJob.view = view;
        animatedZoomJob.xOrigin = f4;
        animatedZoomJob.yOrigin = f5;
        animatedZoomJob.resetAnimator();
        animatedZoomJob.animator.setDuration(j);
        return animatedZoomJob;
    }

    @SuppressLint({"NewApi"})
    public AnimatedZoomJob(ViewPortHandler viewPortHandler, View view, Transformer transformer, YAxis yAxis, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, long j) {
        super(viewPortHandler, f2, f3, transformer, view, f4, f5, j);
        this.zoomCenterX = f6;
        this.zoomCenterY = f7;
        this.zoomOriginX = f8;
        this.zoomOriginY = f9;
        this.animator.addListener(this);
        this.yAxis = yAxis;
        this.xAxisRange = f;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f = this.xOrigin + ((this.xValue - this.xOrigin) * this.phase);
        float f2 = this.yOrigin + ((this.yValue - this.yOrigin) * this.phase);
        Matrix matrix = this.mOnAnimationUpdateMatrixBuffer;
        this.mViewPortHandler.setZoom(f, f2, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, false);
        f = this.yAxis.mAxisRange / this.mViewPortHandler.getScaleY();
        f2 = this.xAxisRange / this.mViewPortHandler.getScaleX();
        this.pts[0] = (((this.zoomCenterX - (f2 / 2.0f)) - this.zoomOriginX) * this.phase) + this.zoomOriginX;
        this.pts[1] = ((((f / 2.0f) + this.zoomCenterY) - this.zoomOriginY) * this.phase) + this.zoomOriginY;
        this.mTrans.pointValuesToPixel(this.pts);
        this.mViewPortHandler.translate(this.pts, matrix);
        this.mViewPortHandler.refresh(matrix, this.view, true);
    }

    public void onAnimationEnd(Animator animator) {
        ((BarLineChartBase) this.view).calculateOffsets();
        this.view.postInvalidate();
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void recycleSelf() {
    }

    public void onAnimationStart(Animator animator) {
    }

    protected Poolable instantiate() {
        return new AnimatedZoomJob(null, null, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
    }
}
