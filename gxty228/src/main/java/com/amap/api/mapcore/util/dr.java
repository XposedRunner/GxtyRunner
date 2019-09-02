package com.amap.api.mapcore.util;

/* compiled from: EarClippingTriangulator */
public class dr {
    private final ej a = new ej();
    private short[] b;
    private double[] c;
    private int d;
    private final dw e = new dw();
    private final ej f = new ej();

    public ej a(double[] dArr) {
        return a(dArr, 0, dArr.length);
    }

    public ej a(double[] dArr, int i, int i2) {
        int i3;
        this.c = dArr;
        int i4 = i2 / 2;
        this.d = i4;
        int i5 = i / 2;
        ej ejVar = this.a;
        ejVar.a();
        ejVar.c(i4);
        ejVar.b = i4;
        short[] sArr = ejVar.a;
        this.b = sArr;
        int i6 = i4 - 1;
        for (i3 = 0; i3 < i4; i3++) {
            sArr[i3] = (short) ((i5 + i6) - i3);
        }
        dw dwVar = this.e;
        dwVar.a();
        dwVar.c(i4);
        for (i3 = 0; i3 < i4; i3++) {
            dwVar.a(a(i3));
        }
        ejVar = this.f;
        ejVar.a();
        ejVar.c(Math.max(0, i4 - 2) * 3);
        a();
        return ejVar;
    }

    private void a() {
        int[] iArr = this.e.a;
        while (this.d > 3) {
            int b = b();
            c(b);
            int d = d(b);
            if (b == this.d) {
                b = 0;
            }
            iArr[d] = a(d);
            iArr[b] = a(b);
        }
        if (this.d == 3) {
            ej ejVar = this.f;
            short[] sArr = this.b;
            ejVar.a(sArr[0]);
            ejVar.a(sArr[1]);
            ejVar.a(sArr[2]);
        }
    }

    private int a(int i) {
        short[] sArr = this.b;
        int i2 = sArr[d(i)] * 2;
        int i3 = sArr[i] * 2;
        int i4 = sArr[e(i)] * 2;
        double[] dArr = this.c;
        return a(dArr[i2], dArr[i2 + 1], dArr[i3], dArr[i3 + 1], dArr[i4], dArr[i4 + 1]);
    }

    private int b() {
        int i;
        int i2 = this.d;
        for (i = 0; i < i2; i++) {
            if (b(i)) {
                return i;
            }
        }
        int[] iArr = this.e.a;
        for (i = 0; i < i2; i++) {
            if (iArr[i] != -1) {
                return i;
            }
        }
        return 0;
    }

    private boolean b(int i) {
        int[] iArr = this.e.a;
        if (iArr[i] == -1) {
            return false;
        }
        int d = d(i);
        int e = e(i);
        short[] sArr = this.b;
        int i2 = sArr[d] * 2;
        int i3 = sArr[i] * 2;
        int i4 = sArr[e] * 2;
        double[] dArr = this.c;
        double d2 = dArr[i2];
        double d3 = dArr[i2 + 1];
        double d4 = dArr[i3];
        double d5 = dArr[i3 + 1];
        double d6 = dArr[i4];
        double d7 = dArr[i4 + 1];
        int e2 = e(e);
        while (e2 != d) {
            if (iArr[e2] != 1) {
                int i5 = sArr[e2] * 2;
                double d8 = dArr[i5];
                double d9 = dArr[i5 + 1];
                if (a(d6, d7, d2, d3, d8, d9) >= 0 && a(d2, d3, d4, d5, d8, d9) >= 0 && a(d4, d5, d6, d7, d8, d9) >= 0) {
                    return false;
                }
            }
            e2 = e(e2);
        }
        return true;
    }

    private void c(int i) {
        short[] sArr = this.b;
        ej ejVar = this.f;
        ejVar.a(sArr[d(i)]);
        ejVar.a(sArr[i]);
        ejVar.a(sArr[e(i)]);
        this.a.b(i);
        this.e.b(i);
        this.d--;
    }

    private int d(int i) {
        if (i == 0) {
            i = this.d;
        }
        return i - 1;
    }

    private int e(int i) {
        return (i + 1) % this.d;
    }

    private static int a(double d, double d2, double d3, double d4, double d5, double d6) {
        return (int) Math.signum((((d6 - d4) * d) + ((d2 - d6) * d3)) + ((d4 - d2) * d5));
    }
}
