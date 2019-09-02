package com.autonavi.ae.gmap.glanimation;

import android.os.SystemClock;

public class AdglAnimation2V extends AbstractAdglAnimation {
    private float curValue1;
    private float curValue2;
    private AbstractAdglAnimationParam2V v2Param = null;

    public AdglAnimation2V(int i) {
        reset();
        this.duration = i;
        this.curValue1 = 0.0f;
        this.curValue2 = 0.0f;
    }

    public void reset() {
        this.isOver = false;
        this.duration = 0;
        this.curValue1 = 0.0f;
        this.curValue2 = 0.0f;
        if (this.v2Param != null) {
            this.v2Param.reset();
        }
    }

    public void setAnimationValue(float f, float f2, float f3, float f4, int i) {
        if (this.v2Param == null) {
            this.v2Param = new AbstractAdglAnimationParam2V();
        }
        this.v2Param.reset();
        this.v2Param.setInterpolatorType(i, 1.0f);
        this.v2Param.setFromValue(f, f3);
        this.v2Param.setToValue(f2, f4);
        this.startTime = SystemClock.uptimeMillis();
        this.isOver = false;
    }

    public float getCurValue(int i) {
        if (i == 0) {
            return this.curValue1;
        }
        return this.curValue2;
    }

    public float getStartValue(int i) {
        if (i == 0) {
            if (this.v2Param != null) {
                return this.v2Param.getFromXValue();
            }
            return 0.0f;
        } else if (this.v2Param != null) {
            return this.v2Param.getFromYValue();
        } else {
            return 0.0f;
        }
    }

    public float getEndValue(int i) {
        if (i == 0) {
            if (this.v2Param != null) {
                return this.v2Param.getToXValue();
            }
            return 0.0f;
        } else if (this.v2Param != null) {
            return this.v2Param.getToYValue();
        } else {
            return 0.0f;
        }
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
            if (this.v2Param != null) {
                this.v2Param.setNormalizedTime(f);
                this.curValue1 = this.v2Param.getCurXValue();
                this.curValue2 = this.v2Param.getCurYValue();
            }
        }
    }
}
