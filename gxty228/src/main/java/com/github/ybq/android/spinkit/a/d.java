package com.github.ybq.android.spinkit.a;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Property;
import android.view.animation.Interpolator;
import com.github.ybq.android.spinkit.a.a.b;
import com.github.ybq.android.spinkit.b.e;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: SpriteAnimatorBuilder */
public class d {
    private e a;
    private List<PropertyValuesHolder> b = new ArrayList();
    private Interpolator c;
    private int d = -1;
    private long e = 2000;

    public d(e eVar) {
        this.a = eVar;
    }

    public d a(float[] fArr, float... fArr2) {
        a(fArr, e.k, fArr2);
        return this;
    }

    public d a(float[] fArr, int... iArr) {
        a(fArr, e.l, iArr);
        return this;
    }

    public d b(float[] fArr, int... iArr) {
        a(fArr, e.b, iArr);
        return this;
    }

    public d c(float[] fArr, int... iArr) {
        a(fArr, e.d, iArr);
        return this;
    }

    public d d(float[] fArr, int... iArr) {
        a(fArr, e.c, iArr);
        return this;
    }

    public d b(float[] fArr, float... fArr2) {
        a(fArr, e.g, fArr2);
        return this;
    }

    public d c(float[] fArr, float... fArr2) {
        a(fArr, e.h, fArr2);
        return this;
    }

    public PropertyValuesHolder a(float[] fArr, Property property, float[] fArr2) {
        a(fArr.length, fArr2.length);
        Keyframe[] keyframeArr = new Keyframe[fArr.length];
        for (int i = 0; i < fArr2.length; i++) {
            keyframeArr[i] = Keyframe.ofFloat(fArr[i], fArr2[i]);
        }
        PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe(property, keyframeArr);
        this.b.add(ofKeyframe);
        return ofKeyframe;
    }

    public PropertyValuesHolder a(float[] fArr, Property property, int[] iArr) {
        a(fArr.length, iArr.length);
        Keyframe[] keyframeArr = new Keyframe[fArr.length];
        for (int i = 0; i < iArr.length; i++) {
            keyframeArr[i] = Keyframe.ofInt(fArr[i], iArr[i]);
        }
        PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe(property, keyframeArr);
        this.b.add(ofKeyframe);
        return ofKeyframe;
    }

    private void a(int i, int i2) {
        if (i != i2) {
            throw new IllegalStateException(String.format(Locale.getDefault(), "The fractions.length must equal values.length, fraction.length[%d], values.length[%d]", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        }
    }

    public d a(Interpolator interpolator) {
        this.c = interpolator;
        return this;
    }

    public d a(float... fArr) {
        a(b.a(fArr));
        return this;
    }

    public d a(long j) {
        this.e = j;
        return this;
    }

    public ObjectAnimator a() {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.a, (PropertyValuesHolder[]) this.b.toArray(new PropertyValuesHolder[this.b.size()]));
        ofPropertyValuesHolder.setDuration(this.e);
        ofPropertyValuesHolder.setRepeatCount(this.d);
        ofPropertyValuesHolder.setInterpolator(this.c);
        return ofPropertyValuesHolder;
    }
}
