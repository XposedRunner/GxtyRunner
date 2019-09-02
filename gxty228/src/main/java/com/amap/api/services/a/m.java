package com.amap.api.services.a;

/* compiled from: HashCodeBuilder */
public class m {
    private final int a;
    private int b;

    public m() {
        this.b = 0;
        this.a = 37;
        this.b = 17;
    }

    public m a(boolean z) {
        this.b = (z ? 0 : 1) + (this.a * this.b);
        return this;
    }

    public m a(boolean[] zArr) {
        if (zArr == null) {
            this.b *= this.a;
        } else {
            for (boolean a : zArr) {
                a(a);
            }
        }
        return this;
    }

    public m a(byte b) {
        this.b = (this.b * this.a) + b;
        return this;
    }

    public m a(byte[] bArr) {
        if (bArr == null) {
            this.b *= this.a;
        } else {
            for (byte a : bArr) {
                a(a);
            }
        }
        return this;
    }

    public m a(char c) {
        this.b = (this.b * this.a) + c;
        return this;
    }

    public m a(char[] cArr) {
        if (cArr == null) {
            this.b *= this.a;
        } else {
            for (char a : cArr) {
                a(a);
            }
        }
        return this;
    }

    public m a(double d) {
        return a(Double.doubleToLongBits(d));
    }

    public m a(double[] dArr) {
        if (dArr == null) {
            this.b *= this.a;
        } else {
            for (double a : dArr) {
                a(a);
            }
        }
        return this;
    }

    public m a(float f) {
        this.b = (this.b * this.a) + Float.floatToIntBits(f);
        return this;
    }

    public m a(float[] fArr) {
        if (fArr == null) {
            this.b *= this.a;
        } else {
            for (float a : fArr) {
                a(a);
            }
        }
        return this;
    }

    public m a(int i) {
        this.b = (this.b * this.a) + i;
        return this;
    }

    public m a(int[] iArr) {
        if (iArr == null) {
            this.b *= this.a;
        } else {
            for (int a : iArr) {
                a(a);
            }
        }
        return this;
    }

    public m a(long j) {
        this.b = (this.b * this.a) + ((int) ((j >> 32) ^ j));
        return this;
    }

    public m a(long[] jArr) {
        if (jArr == null) {
            this.b *= this.a;
        } else {
            for (long a : jArr) {
                a(a);
            }
        }
        return this;
    }

    public m a(Object obj) {
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

    public m a(Object[] objArr) {
        if (objArr == null) {
            this.b *= this.a;
        } else {
            for (Object a : objArr) {
                a(a);
            }
        }
        return this;
    }

    public m a(short s) {
        this.b = (this.b * this.a) + s;
        return this;
    }

    public m a(short[] sArr) {
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
