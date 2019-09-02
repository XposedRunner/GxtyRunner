package com.github.mikephil.charting.matrix;

public final class Vector3 {
    public static final Vector3 UNIT_X = new Vector3(1.0f, 0.0f, 0.0f);
    public static final Vector3 UNIT_Y = new Vector3(0.0f, 1.0f, 0.0f);
    public static final Vector3 UNIT_Z = new Vector3(0.0f, 0.0f, 1.0f);
    public static final Vector3 ZERO = new Vector3(0.0f, 0.0f, 0.0f);
    public float x;
    public float y;
    public float z;

    public Vector3(float[] fArr) {
        set(fArr[0], fArr[1], fArr[2]);
    }

    public Vector3(float f, float f2, float f3) {
        set(f, f2, f3);
    }

    public Vector3(Vector3 vector3) {
        set(vector3);
    }

    public final void add(Vector3 vector3) {
        this.x += vector3.x;
        this.y += vector3.y;
        this.z += vector3.z;
    }

    public final void add(float f, float f2, float f3) {
        this.x += f;
        this.y += f2;
        this.z += f3;
    }

    public final void subtract(Vector3 vector3) {
        this.x -= vector3.x;
        this.y -= vector3.y;
        this.z -= vector3.z;
    }

    public final void subtractMultiple(Vector3 vector3, float f) {
        this.x -= vector3.x * f;
        this.y -= vector3.y * f;
        this.z -= vector3.z * f;
    }

    public final void multiply(float f) {
        this.x *= f;
        this.y *= f;
        this.z *= f;
    }

    public final void multiply(Vector3 vector3) {
        this.x *= vector3.x;
        this.y *= vector3.y;
        this.z *= vector3.z;
    }

    public final void divide(float f) {
        if (f != 0.0f) {
            this.x /= f;
            this.y /= f;
            this.z /= f;
        }
    }

    public final void set(Vector3 vector3) {
        this.x = vector3.x;
        this.y = vector3.y;
        this.z = vector3.z;
    }

    public final void set(float f, float f2, float f3) {
        this.x = f;
        this.y = f2;
        this.z = f3;
    }

    public final float dot(Vector3 vector3) {
        return ((this.x * vector3.x) + (this.y * vector3.y)) + (this.z * vector3.z);
    }

    public final Vector3 cross(Vector3 vector3) {
        return new Vector3((this.y * vector3.z) - (this.z * vector3.y), (this.z * vector3.x) - (this.x * vector3.z), (this.x * vector3.y) - (this.y * vector3.x));
    }

    public final float length() {
        return (float) Math.sqrt((double) length2());
    }

    public final float length2() {
        return ((this.x * this.x) + (this.y * this.y)) + (this.z * this.z);
    }

    public final float distance2(Vector3 vector3) {
        float f = this.x - vector3.x;
        float f2 = this.y - vector3.y;
        float f3 = this.z - vector3.z;
        return ((f * f) + (f2 * f2)) + (f3 * f3);
    }

    public final float normalize() {
        float length = length();
        if (length != 0.0f) {
            this.x /= length;
            this.y /= length;
            this.z /= length;
        }
        return length;
    }

    public final void zero() {
        set(0.0f, 0.0f, 0.0f);
    }

    public final boolean pointsInSameDirection(Vector3 vector3) {
        return dot(vector3) > 0.0f;
    }
}
