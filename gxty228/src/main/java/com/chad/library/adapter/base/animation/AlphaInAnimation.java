package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class AlphaInAnimation implements BaseAnimation {
    private static final float DEFAULT_ALPHA_FROM = 0.0f;
    private final float mFrom;

    public AlphaInAnimation() {
        this(0.0f);
    }

    public AlphaInAnimation(float f) {
        this.mFrom = f;
    }

    public Animator[] getAnimators(View view) {
        Animator[] animatorArr = new Animator[1];
        animatorArr[0] = ObjectAnimator.ofFloat(view, "alpha", new float[]{this.mFrom, 1.0f});
        return animatorArr;
    }
}
