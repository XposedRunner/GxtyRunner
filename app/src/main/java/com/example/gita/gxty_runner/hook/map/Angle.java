package com.example.gita.gxty_runner.hook.map;

public class Angle {

    private double angle;

    public Angle(double rad) {
        this.angle = rad % (2 * Math.PI);
    }

    public Angle(float deg) {
        this.angle = Math.toRadians(450 - deg % 360);
    }

    public static Angle elevate(LatLng latLng1, LatLng latLng2) {
        return elevate(latLng1.longitude, latLng1.latitude, latLng2.longitude, latLng2.latitude);
    }

    public static Angle elevate(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1, dy = y2 - y1;
        if (dx == 0) return new Angle(dy > 0 ? Math.PI / 2 : Math.PI / -2);
        if (dy == 0) return new Angle(dx > 0 ? 0 : Math.PI);
        double atan2 = Math.atan2(dy, dx);
        return new Angle(atan2 > 0 ? atan2 : Math.PI * 2 + atan2);

    }

    public double deg() {
        return 450 - Math.toDegrees(this.angle);
    }

    public double rad() {
        return this.angle;
    }
}
