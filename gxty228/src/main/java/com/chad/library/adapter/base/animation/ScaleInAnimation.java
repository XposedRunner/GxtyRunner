package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class ScaleInAnimation implements BaseAnimation {
    private static final float DEFAULT_SCALE_FROM = 0.5f;
    private final float mFrom;

    public ScaleInAnimation() {
        this(0.5f);
    }

    public ScaleInAnimation(float f) {
        this.mFrom = f;
    }

    public Animator[] getAnimators(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", new float[]{this.mFrom, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", new float[]{this.mFrom, 1.0f});
        return new ObjectAnimator[]{ofFloat, ofFloat2};
    }
}
