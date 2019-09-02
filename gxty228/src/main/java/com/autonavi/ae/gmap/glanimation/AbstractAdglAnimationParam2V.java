package com.autonavi.ae.gmap.glanimation;

public class AbstractAdglAnimationParam2V extends AbstractAdglAnimationParam {
    public float fromXValue;
    public float fromYValue;
    public float toXValue;
    public float toYValue;

    public AbstractAdglAnimationParam2V() {
        reset();
    }

    public void reset() {
        super.reset();
        this.fromXValue = 0.0f;
        this.toXValue = 0.0f;
        this.fromYValue = 0.0f;
        this.toYValue = 0.0f;
    }

    public void setFromValue(float f, float f2) {
        this.fromXValue = f;
        this.fromYValue = f2;
        this.hasFromValue = true;
        this.hasCheckedParam = false;
    }

    public void setToValue(float f, float f2) {
        this.toXValue = f;
        this.toYValue = f2;
        this.hasToValue = true;
        this.hasCheckedParam = false;
    }

    public float getFromXValue() {
        return this.fromXValue;
    }

    public float getFromYValue() {
        return this.fromYValue;
    }

    public float getToXValue() {
        return this.toXValue;
    }

    public float getToYValue() {
        return this.toYValue;
    }

    public float getCurXValue() {
        return this.fromXValue + ((this.toXValue - this.fromXValue) * this.mult);
    }

    public float getCurYValue() {
        return this.fromYValue + ((this.toYValue - this.fromYValue) * this.mult);
    }

    public void checkParam() {
        this.needToCaculate = false;
        if (this.hasFromValue && this.hasToValue) {
            float f = this.toYValue - this.fromYValue;
            if (((double) Math.abs(this.toXValue - this.fromXValue)) > 1.0E-4d || ((double) Math.abs(f)) > 1.0E-4d) {
                this.needToCaculate = true;
            }
        }
        this.hasCheckedParam = true;
    }
}
