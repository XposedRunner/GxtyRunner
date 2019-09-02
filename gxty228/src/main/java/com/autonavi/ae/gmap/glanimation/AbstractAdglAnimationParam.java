package com.autonavi.ae.gmap.glanimation;

public abstract class AbstractAdglAnimationParam {
    protected float factor;
    protected boolean hasCheckedParam;
    protected boolean hasFromValue;
    protected boolean hasToValue;
    protected int interpolationType;
    protected float mult;
    protected boolean needToCaculate;
    protected float normalizedTime;

    public abstract void checkParam();

    static float bounce(float f) {
        return (f * f) * 8.0f;
    }

    public void reset() {
        this.hasCheckedParam = false;
        this.needToCaculate = false;
        this.interpolationType = 0;
        this.factor = 1.0f;
        this.hasCheckedParam = false;
        this.needToCaculate = false;
        this.hasFromValue = false;
        this.hasToValue = false;
    }

    public boolean needToCaculate() {
        if (!this.hasCheckedParam) {
            checkParam();
        }
        if (this.hasCheckedParam && this.needToCaculate) {
            return true;
        }
        return false;
    }

    public float getCurMult() {
        return this.mult;
    }

    public void setNormalizedTime(float f) {
        this.normalizedTime = f;
        float f2;
        switch (this.interpolationType) {
            case 0:
                break;
            case 1:
                f = (float) Math.pow((double) f, (double) (this.factor * 2.0f));
                break;
            case 2:
                if (this.factor != 1.0f) {
                    f = (float) (1.0d - Math.pow((double) (1.0f - f), (double) (this.factor * 2.0f)));
                    break;
                } else {
                    f = 1.0f - ((1.0f - f) * (1.0f - f));
                    break;
                }
            case 3:
                f = (float) ((Math.cos(((double) (f + 1.0f)) * 3.141592653589793d) / 2.0d) + 0.5d);
                break;
            case 4:
                f2 = 1.1226f * f;
                if (f2 >= 0.3535f) {
                    if (f2 >= 0.7408f) {
                        if (f2 >= 0.9644f) {
                            f = bounce(f2 - 1.0435f) + 0.95f;
                            break;
                        } else {
                            f = bounce(f2 - 0.8526f) + 0.9f;
                            break;
                        }
                    }
                    f = bounce(f2 - 0.54719f) + 0.7f;
                    break;
                }
                f = bounce(f2);
                break;
            case 5:
                f2 = f - 1.0f;
                f = (((f2 * (2.0f + 1.0f)) + 2.0f) * (f2 * f2)) + 1.0f;
                break;
            case 6:
                if (f >= 0.0f) {
                    if (f >= 0.25f) {
                        if (f >= 0.5f) {
                            if (f >= 0.75f) {
                                if (f > 1.0f) {
                                    f = 0.0f;
                                    break;
                                } else {
                                    f = 4.0f - (4.0f * f);
                                    break;
                                }
                            }
                            f = (4.0f * f) - 2.0f;
                            break;
                        }
                        f = 2.0f - (4.0f * f);
                        break;
                    }
                    f *= 4.0f;
                    break;
                }
                f = 0.0f;
                break;
            default:
                f = 0.0f;
                break;
        }
        this.mult = f;
    }

    public void setInterpolatorType(int i, float f) {
        this.interpolationType = i;
        this.factor = f;
    }

    public int getInterpolatorType() {
        return this.interpolationType;
    }

    public AbstractAdglAnimationParam() {
        this.hasCheckedParam = false;
        this.needToCaculate = false;
        this.interpolationType = 0;
        this.factor = 1.0f;
        this.hasCheckedParam = false;
        this.needToCaculate = false;
        this.hasFromValue = false;
        this.hasToValue = false;
    }
}
