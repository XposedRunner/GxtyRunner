package com.github.mikephil.charting.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Build.VERSION;
import com.github.mikephil.charting.animation.Easing.EasingOption;

public class ChartAnimator {
    private AnimatorUpdateListener mListener;
    protected float mPhaseX = 1.0f;
    protected float mPhaseY = 1.0f;

    public ChartAnimator(AnimatorUpdateListener animatorUpdateListener) {
        this.mListener = animatorUpdateListener;
    }

    public void animateXY(int i, int i2, EasingFunction easingFunction, EasingFunction easingFunction2) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", new float[]{0.0f, 1.0f});
            ofFloat.setInterpolator(easingFunction2);
            ofFloat.setDuration((long) i2);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "phaseX", new float[]{0.0f, 1.0f});
            ofFloat2.setInterpolator(easingFunction);
            ofFloat2.setDuration((long) i);
            if (i > i2) {
                ofFloat2.addUpdateListener(this.mListener);
            } else {
                ofFloat.addUpdateListener(this.mListener);
            }
            ofFloat2.start();
            ofFloat.start();
        }
    }

    public void animateX(int i, EasingFunction easingFunction) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseX", new float[]{0.0f, 1.0f});
            ofFloat.setInterpolator(easingFunction);
            ofFloat.setDuration((long) i);
            ofFloat.addUpdateListener(this.mListener);
            ofFloat.start();
        }
    }

    public void animateY(int i, EasingFunction easingFunction) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", new float[]{0.0f, 1.0f});
            ofFloat.setInterpolator(easingFunction);
            ofFloat.setDuration((long) i);
            ofFloat.addUpdateListener(this.mListener);
            ofFloat.start();
        }
    }

    public void animateXY(int i, int i2, EasingOption easingOption, EasingOption easingOption2) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", new float[]{0.0f, 1.0f});
            ofFloat.setInterpolator(Easing.getEasingFunctionFromOption(easingOption2));
            ofFloat.setDuration((long) i2);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "phaseX", new float[]{0.0f, 1.0f});
            ofFloat2.setInterpolator(Easing.getEasingFunctionFromOption(easingOption));
            ofFloat2.setDuration((long) i);
            if (i > i2) {
                ofFloat2.addUpdateListener(this.mListener);
            } else {
                ofFloat.addUpdateListener(this.mListener);
            }
            ofFloat2.start();
            ofFloat.start();
        }
    }

    public void animateX(int i, EasingOption easingOption) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseX", new float[]{0.0f, 1.0f});
            ofFloat.setInterpolator(Easing.getEasingFunctionFromOption(easingOption));
            ofFloat.setDuration((long) i);
            ofFloat.addUpdateListener(this.mListener);
            ofFloat.start();
        }
    }

    public void animateY(int i, EasingOption easingOption) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", new float[]{0.0f, 1.0f});
            ofFloat.setInterpolator(Easing.getEasingFunctionFromOption(easingOption));
            ofFloat.setDuration((long) i);
            ofFloat.addUpdateListener(this.mListener);
            ofFloat.start();
        }
    }

    public void animateXY(int i, int i2) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", new float[]{0.0f, 1.0f});
            ofFloat.setDuration((long) i2);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "phaseX", new float[]{0.0f, 1.0f});
            ofFloat2.setDuration((long) i);
            if (i > i2) {
                ofFloat2.addUpdateListener(this.mListener);
            } else {
                ofFloat.addUpdateListener(this.mListener);
            }
            ofFloat2.start();
            ofFloat.start();
        }
    }

    public void animateX(int i) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseX", new float[]{0.0f, 1.0f});
            ofFloat.setDuration((long) i);
            ofFloat.addUpdateListener(this.mListener);
            ofFloat.start();
        }
    }

    public void animateY(int i) {
        if (VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", new float[]{0.0f, 1.0f});
            ofFloat.setDuration((long) i);
            ofFloat.addUpdateListener(this.mListener);
            ofFloat.start();
        }
    }

    public float getPhaseY() {
        return this.mPhaseY;
    }

    public void setPhaseY(float f) {
        this.mPhaseY = f;
    }

    public float getPhaseX() {
        return this.mPhaseX;
    }

    public void setPhaseX(float f) {
        this.mPhaseX = f;
    }
}
