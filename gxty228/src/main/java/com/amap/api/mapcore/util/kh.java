package com.amap.api.mapcore.util;

import android.text.TextUtils;

/* compiled from: MapLocFilter */
public final class kh {
    private static kh b = null;
    long a = 0;
    private kx c = null;
    private long d = 0;
    private long e = 0;

    private kh() {
    }

    public static synchronized kh a() {
        kh khVar;
        synchronized (kh.class) {
            if (b == null) {
                b = new kh();
            }
            khVar = b;
        }
        return khVar;
    }

    public static kx b(kx kxVar) {
        return kxVar;
    }

    public final kx a(kx kxVar) {
        if (lc.b() - this.a > StatisticConfig.MIN_UPLOAD_INTERVAL) {
            this.c = kxVar;
            this.a = lc.b();
            return this.c;
        }
        this.a = lc.b();
        if (!kp.a(this.c) || !kp.a(kxVar)) {
            this.d = lc.b();
            this.c = kxVar;
            return this.c;
        } else if (kxVar.getTime() == this.c.getTime() && kxVar.getAccuracy() < 300.0f) {
            return kxVar;
        } else {
            if (kxVar.getProvider().equalsIgnoreCase("GPS")) {
                this.d = lc.b();
                this.c = kxVar;
                return this.c;
            } else if (kxVar.c() != this.c.c()) {
                this.d = lc.b();
                this.c = kxVar;
                return this.c;
            } else if (kxVar.getBuildingId().equals(this.c.getBuildingId()) || TextUtils.isEmpty(kxVar.getBuildingId())) {
                float a = lc.a(new double[]{kxVar.getLatitude(), kxVar.getLongitude(), this.c.getLatitude(), this.c.getLongitude()});
                float accuracy = this.c.getAccuracy();
                float accuracy2 = kxVar.getAccuracy();
                float f = accuracy2 - accuracy;
                long b = lc.b();
                long j = b - this.d;
                if ((accuracy < 101.0f && accuracy2 > 299.0f) || (accuracy > 299.0f && accuracy2 > 299.0f)) {
                    if (this.e == 0) {
                        this.e = b;
                    } else if (b - this.e > StatisticConfig.MIN_UPLOAD_INTERVAL) {
                        this.d = b;
                        this.c = kxVar;
                        this.e = 0;
                        return this.c;
                    }
                    return this.c;
                } else if (accuracy2 >= 101.0f || accuracy <= 299.0f) {
                    if (accuracy2 <= 299.0f) {
                        this.e = 0;
                    }
                    if (a >= 10.0f || ((double) a) <= 0.1d || accuracy2 <= 5.0f) {
                        if (f < 300.0f) {
                            this.d = lc.b();
                            this.c = kxVar;
                            return this.c;
                        } else if (j < StatisticConfig.MIN_UPLOAD_INTERVAL) {
                            return this.c;
                        } else {
                            this.d = lc.b();
                            this.c = kxVar;
                            return this.c;
                        }
                    } else if (f >= -300.0f) {
                        return this.c;
                    } else {
                        if (accuracy / accuracy2 < 2.0f) {
                            return this.c;
                        }
                        this.d = b;
                        this.c = kxVar;
                        return this.c;
                    }
                } else {
                    this.d = b;
                    this.c = kxVar;
                    this.e = 0;
                    return this.c;
                }
            } else {
                this.d = lc.b();
                this.c = kxVar;
                return this.c;
            }
        }
    }
}
