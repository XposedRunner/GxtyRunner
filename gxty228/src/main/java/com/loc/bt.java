package com.loc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.github.mikephil.charting.utils.Utils;

/* compiled from: AmapSensorManager */
public final class bt implements SensorEventListener {
    SensorManager a = null;
    Sensor b = null;
    Sensor c = null;
    Sensor d = null;
    public boolean e = false;
    public double f = Utils.DOUBLE_EPSILON;
    public float g = 0.0f;
    Handler h = new Handler(this) {
        final /* synthetic */ bt a;

        {
            this.a = r1;
        }
    };
    double i = Utils.DOUBLE_EPSILON;
    double j = Utils.DOUBLE_EPSILON;
    double k = Utils.DOUBLE_EPSILON;
    double l = Utils.DOUBLE_EPSILON;
    double[] m = new double[3];
    volatile double n = Utils.DOUBLE_EPSILON;
    long o = 0;
    long p = 0;
    final int q = 100;
    final int r = 30;
    private Context s = null;
    private float t = 1013.25f;
    private float u = 0.0f;

    public bt(Context context) {
        try {
            this.s = context;
            if (this.a == null) {
                this.a = (SensorManager) this.s.getSystemService("sensor");
            }
            try {
                this.b = this.a.getDefaultSensor(6);
            } catch (Throwable th) {
            }
            try {
                this.c = this.a.getDefaultSensor(11);
            } catch (Throwable th2) {
            }
            try {
                this.d = this.a.getDefaultSensor(1);
            } catch (Throwable th3) {
            }
        } catch (Throwable th4) {
            cl.a(th4, "AMapSensorManager", "<init>");
        }
    }

    public final void a() {
        if (this.a != null && !this.e) {
            this.e = true;
            try {
                if (this.b != null) {
                    this.a.registerListener(this, this.b, 3, this.h);
                }
            } catch (Throwable th) {
                cl.a(th, "AMapSensorManager", "registerListener mPressure");
            }
            try {
                if (this.c != null) {
                    this.a.registerListener(this, this.c, 3, this.h);
                }
            } catch (Throwable th2) {
                cl.a(th2, "AMapSensorManager", "registerListener mRotationVector");
            }
            try {
                if (this.d != null) {
                    this.a.registerListener(this, this.d, 3, this.h);
                }
            } catch (Throwable th22) {
                cl.a(th22, "AMapSensorManager", "registerListener mAcceleroMeterVector");
            }
        }
    }

    public final void b() {
        if (this.a != null && this.e) {
            this.e = false;
            try {
                if (this.b != null) {
                    this.a.unregisterListener(this, this.b);
                }
            } catch (Throwable th) {
            }
            try {
                if (this.c != null) {
                    this.a.unregisterListener(this, this.c);
                }
            } catch (Throwable th2) {
            }
            try {
                if (this.d != null) {
                    this.a.unregisterListener(this, this.d);
                }
            } catch (Throwable th3) {
            }
        }
    }

    public final double c() {
        return this.f;
    }

    public final float d() {
        return this.u;
    }

    public final double e() {
        return this.l;
    }

    public final void f() {
        try {
            b();
            this.b = null;
            this.c = null;
            this.a = null;
            this.d = null;
            this.e = false;
        } catch (Throwable th) {
            cl.a(th, "AMapSensorManager", "destroy");
        }
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null) {
            try {
                float[] fArr;
                switch (sensorEvent.sensor.getType()) {
                    case 1:
                        try {
                            if (this.d != null) {
                                fArr = (float[]) sensorEvent.values.clone();
                                this.m[0] = (this.m[0] * 0.800000011920929d) + ((double) (fArr[0] * 0.19999999f));
                                this.m[1] = (this.m[1] * 0.800000011920929d) + ((double) (fArr[1] * 0.19999999f));
                                this.m[2] = (this.m[2] * 0.800000011920929d) + ((double) (fArr[2] * 0.19999999f));
                                this.i = ((double) fArr[0]) - this.m[0];
                                this.j = ((double) fArr[1]) - this.m[1];
                                this.k = ((double) fArr[2]) - this.m[2];
                                long currentTimeMillis = System.currentTimeMillis();
                                if (currentTimeMillis - this.o >= 100) {
                                    double sqrt = Math.sqrt(((this.i * this.i) + (this.j * this.j)) + (this.k * this.k));
                                    this.p++;
                                    this.o = currentTimeMillis;
                                    this.n += sqrt;
                                    if (this.p >= 30) {
                                        this.l = this.n / ((double) this.p);
                                        this.n = Utils.DOUBLE_EPSILON;
                                        this.p = 0;
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            return;
                        }
                    case 6:
                        try {
                            if (this.b != null) {
                                fArr = (float[]) sensorEvent.values.clone();
                                if (fArr != null) {
                                    this.g = fArr[0];
                                }
                                if (fArr != null) {
                                    this.f = (double) ct.a(SensorManager.getAltitude(this.t, fArr[0]));
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (Throwable th2) {
                            return;
                        }
                    case 11:
                        try {
                            if (this.c != null) {
                                fArr = (float[]) sensorEvent.values.clone();
                                if (fArr != null) {
                                    float[] fArr2 = new float[9];
                                    SensorManager.getRotationMatrixFromVector(fArr2, fArr);
                                    fArr = new float[3];
                                    SensorManager.getOrientation(fArr2, fArr);
                                    this.u = (float) Math.toDegrees((double) fArr[0]);
                                    this.u = (float) Math.floor(this.u > 0.0f ? (double) this.u : (double) (this.u + 360.0f));
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (Throwable th3) {
                            return;
                        }
                    default:
                        return;
                }
            } catch (Throwable th4) {
            }
        }
    }
}
