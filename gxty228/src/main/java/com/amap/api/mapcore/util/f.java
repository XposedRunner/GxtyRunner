package com.amap.api.mapcore.util;

import com.amap.api.maps.model.BitmapDescriptor;

/* compiled from: OverlayTextureItem */
public class f {
    private String a;
    private BitmapDescriptor b;
    private int c;
    private float d = 0.0f;
    private float e = 0.0f;
    private float f = 1.0f;
    private float g = 1.0f;
    private int h = 0;

    public float a() {
        return this.e;
    }

    public void a(float f) {
        this.e = f;
    }

    public float b() {
        return this.d;
    }

    public void b(float f) {
        this.d = f;
    }

    public float c() {
        return this.f;
    }

    public void c(float f) {
        this.f = f;
    }

    public float d() {
        return this.g;
    }

    public void d(float f) {
        this.g = f;
    }

    public f(BitmapDescriptor bitmapDescriptor, int i) {
        this.b = bitmapDescriptor;
        this.c = i;
        this.a = ed.a();
    }

    public BitmapDescriptor e() {
        return this.b;
    }

    public int f() {
        return this.c;
    }

    public void g() {
        this.h++;
    }

    public void h() {
        this.h--;
    }

    public int i() {
        return this.h;
    }

    public String j() {
        return this.a;
    }

    public void a(int i) {
        this.c = i;
    }
}
