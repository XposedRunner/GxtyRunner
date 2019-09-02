package com.amap.api.maps.model;

import java.util.ArrayList;
import java.util.List;

public class GL3DModelOptions {
    private float a;
    private LatLng b;
    private List<Float> c = new ArrayList();
    private List<Float> d = new ArrayList();
    private BitmapDescriptor e;
    private int f;

    public GL3DModelOptions textureDrawable(BitmapDescriptor bitmapDescriptor) {
        this.e = bitmapDescriptor;
        return this;
    }

    public GL3DModelOptions vertexData(List<Float> list, List<Float> list2) {
        this.c = list;
        this.d = list2;
        return this;
    }

    public GL3DModelOptions position(LatLng latLng) {
        this.b = latLng;
        return this;
    }

    public GL3DModelOptions angle(float f) {
        this.a = f;
        return this;
    }

    public List<Float> getVertext() {
        return this.c;
    }

    public List<Float> getTextrue() {
        return this.d;
    }

    public float getAngle() {
        return this.a;
    }

    public LatLng getLatLng() {
        return this.b;
    }

    public BitmapDescriptor getBitmapDescriptor() {
        return this.e;
    }

    public GL3DModelOptions setModelFixedLength(int i) {
        this.f = i;
        return this;
    }

    public int getModelFixedLength() {
        return this.f;
    }
}
