package com.autonavi.amap.mapcore.animation;

public class GLRotateAnimation extends GLAnimation {
    private float mFromDegrees = 0.0f;
    private float mToDegrees = 1.0f;

    public GLRotateAnimation(float f, float f2, float f3, float f4, float f5) {
        this.mFromDegrees = f;
        this.mToDegrees = f2;
    }

    protected void applyTransformation(float f, GLTransformation gLTransformation) {
        gLTransformation.rotate = (double) (this.mFromDegrees + ((this.mToDegrees - this.mFromDegrees) * f));
    }
}
