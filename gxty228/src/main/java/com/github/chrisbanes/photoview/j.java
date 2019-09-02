package com.github.chrisbanes.photoview;

import android.support.v4.view.MotionEventCompat;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/* compiled from: Util */
class j {

    /* compiled from: Util */
    /* renamed from: com.github.chrisbanes.photoview.j$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    static void a(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
        } else if (f2 >= f3) {
            throw new IllegalArgumentException("Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
        }
    }

    static boolean a(ImageView imageView) {
        return imageView.getDrawable() != null;
    }

    static boolean a(ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (AnonymousClass1.a[scaleType.ordinal()]) {
            case 1:
                throw new IllegalStateException("Matrix scale type is not supported");
            default:
                return true;
        }
    }

    static int a(int i) {
        return (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8;
    }
}
