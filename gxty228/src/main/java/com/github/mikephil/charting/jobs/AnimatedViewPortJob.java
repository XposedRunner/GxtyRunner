package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.view.View;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

@SuppressLint({"NewApi"})
public abstract class AnimatedViewPortJob extends ViewPortJob implements AnimatorListener, AnimatorUpdateListener {
    protected ObjectAnimator animator = ObjectAnimator.ofFloat(this, "phase", new float[]{0.0f, 1.0f});
    protected float phase;
    protected float xOrigin;
    protected float yOrigin;

    public abstract void recycleSelf();

    public AnimatedViewPortJob(ViewPortHandler viewPortHandler, float f, float f2, Transformer transformer, View view, float f3, float f4, long j) {
        super(viewPortHandler, f, f2, transformer, view);
        this.xOrigin = f3;
        this.yOrigin = f4;
        this.animator.setDuration(j);
        this.animator.addUpdateListener(this);
        this.animator.addListener(this);
    }

    @SuppressLint({"NewApi"})
    public void run() {
        this.animator.start();
    }

    public float getPhase() {
        return this.phase;
    }

    public void setPhase(float f) {
        this.phase = f;
    }

    public float getXOrigin() {
        return this.xOrigin;
    }

    public float getYOrigin() {
        return this.yOrigin;
    }

    protected void resetAnimator() {
        this.animator.removeAllListeners();
        this.animator.removeAllUpdateListeners();
        this.animator.reverse();
        this.animator.addUpdateListener(this);
        this.animator.addListener(this);
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        try {
            recycleSelf();
        } catch (IllegalArgumentException e) {
        }
    }

    public void onAnimationCancel(Animator animator) {
        try {
            recycleSelf();
        } catch (IllegalArgumentException e) {
        }
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
    }
}
