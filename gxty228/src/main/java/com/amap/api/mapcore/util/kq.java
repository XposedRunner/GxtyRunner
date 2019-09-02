package com.amap.api.mapcore.util;

import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: CellAgeEstimator */
public final class kq {
    private HashMap<Long, kr> a = new HashMap();
    private long b = 0;

    private static long a(int i, int i2) {
        return ((((long) i) & 65535) << 32) | (((long) i2) & 65535);
    }

    public final long a(kr krVar) {
        if (krVar == null || !krVar.o) {
            return 0;
        }
        long a;
        HashMap hashMap = this.a;
        switch (krVar.k) {
            case 1:
            case 3:
            case 4:
                a = a(krVar.a(), krVar.b());
                break;
            case 2:
                a = a(krVar.c(), krVar.d());
                break;
            default:
                a = 0;
                break;
        }
        kr krVar2 = (kr) hashMap.get(Long.valueOf(a));
        if (krVar2 == null) {
            krVar.m = lc.b();
            hashMap.put(Long.valueOf(a), krVar);
            return 0;
        } else if (krVar2.e() != krVar.e()) {
            krVar.m = lc.b();
            hashMap.put(Long.valueOf(a), krVar);
            return 0;
        } else {
            krVar.m = krVar2.m;
            hashMap.put(Long.valueOf(a), krVar);
            return (lc.b() - krVar2.m) / 1000;
        }
    }

    public final void a() {
        this.a.clear();
        this.b = 0;
    }

    public final void a(ArrayList<? extends kr> arrayList) {
        long j = 0;
        if (arrayList != null) {
            long b = lc.b();
            if (this.b <= 0 || b - this.b >= 60000) {
                int i;
                kr krVar;
                HashMap hashMap = this.a;
                int size = arrayList.size();
                for (i = 0; i < size; i++) {
                    krVar = (kr) arrayList.get(i);
                    if (krVar.o) {
                        switch (krVar.k) {
                            case 1:
                            case 3:
                            case 4:
                                j = a(krVar.c, krVar.d);
                                break;
                            case 2:
                                j = a(krVar.h, krVar.i);
                                break;
                        }
                        kr krVar2 = (kr) hashMap.get(Long.valueOf(j));
                        if (krVar2 != null) {
                            if (krVar2.e() == krVar.e()) {
                                krVar.m = krVar2.m;
                            } else {
                                krVar.m = b;
                            }
                        }
                    }
                }
                hashMap.clear();
                i = arrayList.size();
                for (int i2 = 0; i2 < i; i2++) {
                    krVar = (kr) arrayList.get(i2);
                    if (krVar.o) {
                        switch (krVar.k) {
                            case 1:
                            case 3:
                            case 4:
                                j = a(krVar.a(), krVar.b());
                                break;
                            case 2:
                                j = a(krVar.c(), krVar.d());
                                break;
                        }
                        hashMap.put(Long.valueOf(j), krVar);
                    }
                }
                this.b = b;
            }
        }
    }
}
