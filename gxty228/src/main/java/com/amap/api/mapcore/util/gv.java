package com.amap.api.mapcore.util;

/* compiled from: HashCodeBuilder */
public class gv {
    private final int a;
    private int b;

    public gv() {
        this.b = 0;
        this.a = 37;
        this.b = 17;
    }

    public gv a(boolean z) {
        this.b = (z ? 0 : 1) + (this.a * this.b);
        return this;
    }

    public gv a(boolean[] zArr) {
        if (zArr == null) {
            this.b *= this.a;
        } else {
            for (boolean a : zArr) {
                a(a);
            }
        }
        return this;
    }

    public gv a(byte b) {
        this.b = (this.b * this.a) + b;
        return this;
    }

    public gv a(byte[] bArr) {
        if (bArr == null) {
            this.b *= this.a;
        } else {
            for (byte a : bArr) {
                a(a);
            }
        }
        return this;
    }

    public gv a(char c) {
        this.b = (this.b * this.a) + c;
        return this;
    }

    public gv a(char[] cArr) {
        if (cArr == null) {
            this.b *= this.a;
        } else {
            for (char a : cArr) {
                a(a);
            }
        }
        return this;
    }

    public gv a(double d) {
        return a(Double.doubleToLongBits(d));
    }

    public gv a(double[] dArr) {
        if (dArr == null) {
            this.b *= this.a;
        } else {
            for (double a : dArr) {
                a(a);
            }
        }
        return this;
    }

    public gv a(float f) {
        this.b = (this.b * this.a) + Float.floatToIntBits(f);
        return this;
    }

    public gv a(float[] fArr) {
        if (fArr == null) {
            this.b *= this.a;
        } else {
            for (float a : fArr) {
                a(a);
            }
        }
        return this;
    }

    public gv a(int i) {
        this.b = (this.b * this.a) + i;
        return this;
    }

    public gv a(int[] iArr) {
        if (iArr == null) {
            this.b *= this.a;
        } else {
            for (int a : iArr) {
                a(a);
            }
        }
        return this;
    }

    public gv a(long j) {
        this.b = (this.b * this.a) + ((int) ((j >> 32) ^ j));
        return this;
    }

    public gv a(long[] jArr) {
        if (jArr == null) {
            this.b *= this.a;
        } else {
            for (long a : jArr) {
                a(a);
            }
        }
        return this;
    }

    public gv a(Object obj) {
        if (obj == null) {
            this.b *= this.a;
        } else if (!obj.getClass().isArray()) {
            this.b = (this.b * this.a) + obj.hashCode();
        } else if (obj instanceof long[]) {
            a((long[]) obj);
        } else if (obj instanceof int[]) {
            a((int[]) obj);
        } else if (obj instanceof short[]) {
            a((short[]) obj);
        } else if (obj instanceof char[]) {
            a((char[]) obj);
        } else if (obj instanceof byte[]) {
            a((byte[]) obj);
        } else if (obj instanceof double[]) {
            a((double[]) obj);
        } else if (obj instanceof float[]) {
            a((float[]) obj);
        } else if (obj instanceof boolean[]) {
            a((boolean[]) obj);
        } else {
            a((Object[]) obj);
        }
        return this;
    }

    public gv a(Object[] objArr) {
        if (objArr == null) {
            this.b *= this.a;
        } else {
            for (Object a : objArr) {
                a(a);
            }
        }
        return this;
    }

    public gv a(short s) {
        this.b = (this.b * this.a) + s;
        return this;
    }

    public gv a(short[] sArr) {
        if (sArr == null) {
            this.b *= this.a;
        } else {
            for (short a : sArr) {
                a(a);
            }
        }
        return this;
    }

    public int a() {
        return this.b;
    }

    public int hashCode() {
        return a();
    }
}
