package com.autonavi.ae.gmap.glanimation;

import android.os.SystemClock;

public class AdglAnimation1V extends AbstractAdglAnimation {
    private float curValue;
    private AbstractAdglAnimationParam1V v1Param = null;

    public void reset() {
        this.isOver = false;
        this.duration = 0;
        if (this.v1Param != null) {
            this.v1Param.reset();
        }
    }

    public AdglAnimation1V(int i) {
        reset();
        this.duration = i;
        this.curValue = 0.0f;
    }

    public void setAnimationValue(float f, float f2, int i) {
        if (this.v1Param == null) {
            this.v1Param = new AbstractAdglAnimationParam1V();
        }
        this.v1Param.reset();
        this.v1Param.setInterpolatorType(i, 1.0f);
        this.v1Param.setFromValue(f);
        this.v1Param.setToValue(f2);
        this.startTime = SystemClock.uptimeMillis();
        this.isOver = false;
    }

    public float getCurValue() {
        return this.curValue;
    }

    public float getStartValue() {
        if (this.v1Param != null) {
            return this.v1Param.getFromValue();
        }
        return 0.0f;
    }

    public float getEndValue() {
        if (this.v1Param != null) {
            return this.v1Param.getToValue();
        }
        return 0.0f;
    }

    public void doAnimation(Object obj) {
        float f = 1.0f;
        if (!this.isOver) {
            this.offsetTime = SystemClock.uptimeMillis() - this.startTime;
            float f2 = ((float) this.offsetTime) / ((float) this.duration);
            if (f2 > 1.0f) {
                this.isOver = true;
            } else if (f2 < 0.0f) {
                this.isOver = true;
                return;
            } else {
                f = f2;
            }
            if (this.v1Param != null) {
                this.v1Param.setNormalizedTime(f);
                this.curValue = this.v1Param.getCurValue();
            }
        }
    }
}
