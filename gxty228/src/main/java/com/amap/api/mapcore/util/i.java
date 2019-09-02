package com.amap.api.mapcore.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.WindowManager;
import com.amap.api.maps.model.Marker;

/* compiled from: SensorEventHelper */
public class i implements SensorEventListener {
    private SensorManager a;
    private Sensor b;
    private long c = 0;
    private float d;
    private Context e;
    private lj f;
    private Marker g;
    private boolean h = true;

    public i(Context context, lj ljVar) {
        this.e = context.getApplicationContext();
        this.f = ljVar;
        try {
            this.a = (SensorManager) context.getSystemService("sensor");
            if (this.a != null) {
                this.b = this.a.getDefaultSensor(3);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a() {
        if (this.a != null && this.b != null) {
            this.a.registerListener(this, this.b, 3);
        }
    }

    public void b() {
        if (this.a != null && this.b != null) {
            this.a.unregisterListener(this, this.b);
        }
    }

    public void a(Marker marker) {
        this.g = marker;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        try {
            if (System.currentTimeMillis() - this.c >= 100) {
                if (this.f.a() == null || this.f.a().getAnimateionsCount() <= 0) {
                    switch (sensorEvent.sensor.getType()) {
                        case 3:
                            float a = (sensorEvent.values[0] + ((float) a(this.e))) % 360.0f;
                            if (a > 180.0f) {
                                a -= 360.0f;
                            } else if (a < -180.0f) {
                                a += 360.0f;
                            }
                            if (Math.abs(this.d - a) >= 3.0f) {
                                if (Float.isNaN(a)) {
                                    a = 0.0f;
                                }
                                this.d = a;
                                if (this.g != null) {
                                    if (this.h) {
                                        this.f.a(p.d(this.d));
                                        this.g.setRotateAngle(-this.d);
                                    } else {
                                        this.g.setRotateAngle(360.0f - this.d);
                                    }
                                }
                                this.c = System.currentTimeMillis();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                    th.printStackTrace();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static int a(Context context) {
        if (context != null) {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                switch (windowManager.getDefaultDisplay().getRotation()) {
                    case 0:
                        return 0;
                    case 1:
                        return 90;
                    case 2:
                        return 180;
                    case 3:
                        return -90;
                }
            }
        }
        return 0;
    }
}
