package com.amap.api.maps.model;

public class AMapCameraInfo {
    private float a = 0.0f;
    private float b = 1.0f;
    private float c = 0.0f;
    private float d = 0.0f;
    private float e = 0.0f;
    private float f = 0.0f;

    public AMapCameraInfo(float f, float f2, float f3, float f4, float f5, float f6) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = f5;
        this.f = f6;
    }

    public float getFov() {
        return this.a;
    }

    public float getAspectRatio() {
        return this.b;
    }

    public float getRotate() {
        return this.c;
    }

    public float getX() {
        return this.d;
    }

    public float getY() {
        return this.e;
    }

    public float getZ() {
        return this.f;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append("fov:").append(this.a).append(" ");
        stringBuilder.append("aspectRatio:").append(this.b).append(" ");
        stringBuilder.append("rotate:").append(this.c).append(" ");
        stringBuilder.append("pos_x:").append(this.d).append(" ");
        stringBuilder.append("pos_y:").append(this.e).append(" ");
        stringBuilder.append("pos_z:").append(this.f).append("]");
        return stringBuilder.toString();
    }
}
