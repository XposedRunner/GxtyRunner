package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class SlideInBottomAnimation implements BaseAnimation {
    public Animator[] getAnimators(View view) {
        Animator[] animatorArr = new Animator[1];
        animatorArr[0] = ObjectAnimator.ofFloat(view, "translationY", new float[]{(float) view.getMeasuredHeight(), 0.0f});
        return animatorArr;
    }
}
