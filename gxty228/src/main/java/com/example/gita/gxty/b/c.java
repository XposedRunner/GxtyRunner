package com.example.gita.gxty.b;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/* compiled from: StepDetector */
public class c implements SensorEventListener {
    float[] a = new float[3];
    final int b = 4;
    float[] c = new float[4];
    int d = 0;
    boolean e = false;
    int f = 0;
    int g = 0;
    boolean h = false;
    float i = 0.0f;
    float j = 0.0f;
    long k = 0;
    long l = 0;
    long m = 0;
    float n = 0.0f;
    float o = 0.0f;
    final float p = 1.3f;
    float q = 2.0f;
    int r = 250;
    private b s;

    public void onSensorChanged(SensorEvent sensorEvent) {
        for (int i = 0; i < 3; i++) {
            this.a[i] = sensorEvent.values[i];
        }
        this.n = (float) Math.sqrt((double) (((this.a[0] * this.a[0]) + (this.a[1] * this.a[1])) + (this.a[2] * this.a[2])));
        a(this.n);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void a(b bVar) {
        this.s = bVar;
    }

    public void a(float f) {
        if (this.o == 0.0f) {
            this.o = f;
        } else if (a(f, this.o)) {
            this.l = this.k;
            this.m = System.currentTimeMillis();
            if (this.m - this.l >= ((long) this.r) && this.i - this.j >= this.q) {
                this.k = this.m;
                this.s.b();
            }
            if (this.m - this.l >= ((long) this.r) && this.i - this.j >= 1.3f) {
                this.k = this.m;
                this.q = b(this.i - this.j);
            }
        }
        this.o = f;
    }

    public boolean a(float f, float f2) {
        this.h = this.e;
        if (f >= f2) {
            this.e = true;
            this.f++;
        } else {
            this.g = this.f;
            this.f = 0;
            this.e = false;
        }
        if (!this.e && this.h && (this.g >= 2 || f2 >= 20.0f)) {
            this.i = f2;
            return true;
        } else if (this.h || !this.e) {
            return false;
        } else {
            this.j = f2;
            return false;
        }
    }

    public float b(float f) {
        float f2 = this.q;
        if (this.d < 4) {
            this.c[this.d] = f;
            this.d++;
        } else {
            f2 = a(this.c, 4);
            for (int i = 1; i < 4; i++) {
                this.c[i - 1] = this.c[i];
            }
            this.c[3] = f;
        }
        return f2;
    }

    public float a(float[] fArr, int i) {
        float f = 0.0f;
        for (int i2 = 0; i2 < i; i2++) {
            f += fArr[i2];
        }
        float f2 = f / 4.0f;
        if (f2 >= 8.0f) {
            return 4.3f;
        }
        if (f2 >= 7.0f && f2 < 8.0f) {
            return 3.3f;
        }
        if (f2 >= 4.0f && f2 < 7.0f) {
            return 2.3f;
        }
        if (f2 < 3.0f || f2 >= 4.0f) {
            return 1.3f;
        }
        return 2.0f;
    }
}
