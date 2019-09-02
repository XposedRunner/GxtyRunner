package com.github.ybq.android.spinkit.a;

import android.animation.Animator;
import android.animation.ValueAnimator;
import com.github.ybq.android.spinkit.b.e;

/* compiled from: AnimationUtils */
public class a {
    public static void a(Animator animator) {
        if (animator != null && !animator.isStarted()) {
            animator.start();
        }
    }

    public static void a(e... eVarArr) {
        for (e start : eVarArr) {
            start.start();
        }
    }

    public static void b(e... eVarArr) {
        for (e stop : eVarArr) {
            stop.stop();
        }
    }

    public static boolean c(e... eVarArr) {
        for (e isRunning : eVarArr) {
            if (isRunning.isRunning()) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(ValueAnimator valueAnimator) {
        return valueAnimator != null && valueAnimator.isRunning();
    }
}
