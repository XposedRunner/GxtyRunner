package com.adam.gpsstatus;

import android.location.GpsSatellite;

/* compiled from: Satellite */
public class c implements Comparable {
    private int a;
    private float b;
    private boolean c;
    private GpsSatellite d;

    public c(GpsSatellite gpsSatellite) {
        this.d = gpsSatellite;
        this.a = gpsSatellite.getPrn();
        this.b = gpsSatellite.getSnr();
        this.c = gpsSatellite.usedInFix();
    }

    public int a() {
        return this.a;
    }

    public int compareTo(Object obj) {
        if (this.a < ((c) obj).a()) {
            return 1;
        }
        return this.a == ((c) obj).a() ? 0 : -1;
    }
}
