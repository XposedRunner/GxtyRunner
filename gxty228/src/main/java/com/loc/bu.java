package com.loc;

import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: CellAgeEstimator */
public final class bu {
    private HashMap<Long, bv> a = new HashMap();
    private long b = 0;

    private static long a(int i, int i2) {
        return ((((long) i) & 65535) << 32) | (((long) i2) & 65535);
    }

    public final long a(bv bvVar) {
        if (bvVar == null || !bvVar.o) {
            return 0;
        }
        long a;
        HashMap hashMap = this.a;
        switch (bvVar.k) {
            case 1:
            case 3:
            case 4:
                a = a(bvVar.c, bvVar.d);
                break;
            case 2:
                a = a(bvVar.h, bvVar.i);
                break;
            default:
                a = 0;
                break;
        }
        bv bvVar2 = (bv) hashMap.get(Long.valueOf(a));
        if (bvVar2 == null) {
            bvVar.m = ct.b();
            hashMap.put(Long.valueOf(a), bvVar);
            return 0;
        } else if (bvVar2.j != bvVar.j) {
            bvVar.m = ct.b();
            hashMap.put(Long.valueOf(a), bvVar);
            return 0;
        } else {
            bvVar.m = bvVar2.m;
            hashMap.put(Long.valueOf(a), bvVar);
            return (ct.b() - bvVar2.m) / 1000;
        }
    }

    public final void a() {
        this.a.clear();
        this.b = 0;
    }

    public final void a(ArrayList<? extends bv> arrayList) {
        long j = 0;
        if (arrayList != null) {
            long b = ct.b();
            if (this.b <= 0 || b - this.b >= 60000) {
                int i;
                bv bvVar;
                HashMap hashMap = this.a;
                int size = arrayList.size();
                for (i = 0; i < size; i++) {
                    bvVar = (bv) arrayList.get(i);
                    if (bvVar.o) {
                        switch (bvVar.k) {
                            case 1:
                            case 3:
                            case 4:
                                j = a(bvVar.c, bvVar.d);
                                break;
                            case 2:
                                j = a(bvVar.h, bvVar.i);
                                break;
                        }
                        bv bvVar2 = (bv) hashMap.get(Long.valueOf(j));
                        if (bvVar2 != null) {
                            if (bvVar2.j == bvVar.j) {
                                bvVar.m = bvVar2.m;
                            } else {
                                bvVar.m = b;
                            }
                        }
                    }
                }
                hashMap.clear();
                i = arrayList.size();
                for (int i2 = 0; i2 < i; i2++) {
                    bvVar = (bv) arrayList.get(i2);
                    if (bvVar.o) {
                        switch (bvVar.k) {
                            case 1:
                            case 3:
                            case 4:
                                j = a(bvVar.c, bvVar.d);
                                break;
                            case 2:
                                j = a(bvVar.h, bvVar.i);
                                break;
                        }
                        hashMap.put(Long.valueOf(j), bvVar);
                    }
                }
                this.b = b;
            }
        }
    }
}
