package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class SlideInRightAnimation implements BaseAnimation {
    public Animator[] getAnimators(View view) {
        Animator[] animatorArr = new Animator[1];
        animatorArr[0] = ObjectAnimator.ofFloat(view, "translationX", new float[]{(float) view.getRootView().getWidth(), 0.0f});
        return animatorArr;
    }
}
