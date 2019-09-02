package com.amap.api.maps.model;

import com.amap.api.mapcore.util.en;
import com.autonavi.amap.mapcore.DPoint;
import com.github.mikephil.charting.utils.Utils;

public class WeightedLatLng {
    public static final double DEFAULT_INTENSITY = 1.0d;
    private DPoint a;
    public final double intensity;
    public final LatLng latLng;

    public WeightedLatLng(LatLng latLng, double d) {
        if (latLng == null) {
            throw new IllegalArgumentException("latLng can not null");
        }
        this.latLng = latLng;
        this.a = en.a(latLng);
        if (d >= Utils.DOUBLE_EPSILON) {
            this.intensity = d;
        } else {
            this.intensity = 1.0d;
        }
    }

    public WeightedLatLng(LatLng latLng) {
        this(latLng, 1.0d);
    }

    protected DPoint getPoint() {
        return this.a;
    }
}
