package com.amap.api.mapcore.util;

import com.autonavi.amap.mapcore.DPoint;

/* compiled from: Bounds */
public class dp {
    public final double a;
    public final double b;
    public final double c;
    public final double d;
    public final double e;
    public final double f;

    public dp(double d, double d2, double d3, double d4) {
        this.a = d;
        this.b = d3;
        this.c = d2;
        this.d = d4;
        this.e = (d + d2) / 2.0d;
        this.f = (d3 + d4) / 2.0d;
    }

    public boolean a(double d, double d2) {
        return this.a <= d && d <= this.c && this.b <= d2 && d2 <= this.d;
    }

    public boolean a(DPoint dPoint) {
        return a(dPoint.x, dPoint.y);
    }

    public boolean a(double d, double d2, double d3, double d4) {
        return d < this.c && this.a < d2 && d3 < this.d && this.b < d4;
    }

    public boolean a(dp dpVar) {
        return a(dpVar.a, dpVar.c, dpVar.b, dpVar.d);
    }

    public boolean b(dp dpVar) {
        return dpVar.a >= this.a && dpVar.c <= this.c && dpVar.b >= this.b && dpVar.d <= this.d;
    }
}
