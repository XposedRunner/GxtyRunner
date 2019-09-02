package com.autonavi.amap.mapcore.animation;

import com.amap.api.maps.model.LatLng;
import com.github.mikephil.charting.utils.Utils;

public class GLTranslateAnimation extends GLAnimation {
    public double mCurXDelta = Utils.DOUBLE_EPSILON;
    public double mCurYDelta = Utils.DOUBLE_EPSILON;
    public double mFromXDelta = Utils.DOUBLE_EPSILON;
    public double mFromYDelta = Utils.DOUBLE_EPSILON;
    public double mToXDelta = Utils.DOUBLE_EPSILON;
    public double mToYDelta = Utils.DOUBLE_EPSILON;

    public GLTranslateAnimation(LatLng latLng) {
        this.mToXDelta = latLng.longitude;
        this.mToYDelta = latLng.latitude;
    }

    public void setFromPoint(LatLng latLng) {
        this.mFromXDelta = latLng.longitude;
        this.mFromYDelta = latLng.latitude;
    }

    protected void applyTransformation(float f, GLTransformation gLTransformation) {
        this.mCurXDelta = this.mFromXDelta;
        this.mCurYDelta = this.mFromYDelta;
        if (this.mFromXDelta != this.mToXDelta) {
            this.mCurXDelta = this.mFromXDelta + ((this.mToXDelta - this.mFromXDelta) * ((double) f));
        }
        if (this.mFromYDelta != this.mToYDelta) {
            this.mCurYDelta = this.mFromYDelta + ((this.mToYDelta - this.mFromYDelta) * ((double) f));
        }
        gLTransformation.x = this.mCurXDelta;
        gLTransformation.y = this.mCurYDelta;
    }
}
