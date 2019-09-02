package com.bumptech.glide.b;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* compiled from: GifDecoder */
public class a {
    private static final String a = a.class.getSimpleName();
    private static final Config b = Config.ARGB_8888;
    private int[] c;
    private final int[] d = new int[256];
    private ByteBuffer e;
    private final byte[] f = new byte[256];
    private short[] g;
    private byte[] h;
    private byte[] i;
    private byte[] j;
    private int[] k;
    private int l;
    private byte[] m;
    private c n;
    private a o;
    private Bitmap p;
    private boolean q;
    private int r;

    /* compiled from: GifDecoder */
    public interface a {
        Bitmap a(int i, int i2, Config config);

        void a(Bitmap bitmap);
    }

    public a(a aVar) {
        this.o = aVar;
        this.n = new c();
    }

    public void a() {
        this.l = (this.l + 1) % this.n.c;
    }

    public int a(int i) {
        if (i < 0 || i >= this.n.c) {
            return -1;
        }
        return ((b) this.n.e.get(i)).i;
    }

    public int b() {
        if (this.n.c <= 0 || this.l < 0) {
            return -1;
        }
        return a(this.l);
    }

    public int c() {
        return this.n.c;
    }

    public int d() {
        return this.l;
    }

    public int e() {
        if (this.n.m == -1) {
            return 1;
        }
        if (this.n.m == 0) {
            return 0;
        }
        return this.n.m + 1;
    }

    public synchronized Bitmap f() {
        Bitmap bitmap;
        if (this.n.c <= 0 || this.l < 0) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "unable to decode frame, frameCount=" + this.n.c + " framePointer=" + this.l);
            }
            this.r = 1;
        }
        if (this.r == 1 || this.r == 2) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Unable to decode frame, status=" + this.r);
            }
            bitmap = null;
        } else {
            b bVar;
            this.r = 0;
            b bVar2 = (b) this.n.e.get(this.l);
            int i = this.l - 1;
            if (i >= 0) {
                bVar = (b) this.n.e.get(i);
            } else {
                bVar = null;
            }
            this.c = bVar2.k != null ? bVar2.k : this.n.a;
            if (this.c == null) {
                if (Log.isLoggable(a, 3)) {
                    Log.d(a, "No Valid Color Table");
                }
                this.r = 1;
                bitmap = null;
            } else {
                if (bVar2.f) {
                    System.arraycopy(this.c, 0, this.d, 0, this.c.length);
                    this.c = this.d;
                    this.c[bVar2.h] = 0;
                }
                bitmap = a(bVar2, bVar);
            }
        }
        return bitmap;
    }

    public void g() {
        this.n = null;
        this.m = null;
        this.j = null;
        this.k = null;
        if (this.p != null) {
            this.o.a(this.p);
        }
        this.p = null;
        this.e = null;
    }

    public void a(c cVar, byte[] bArr) {
        this.n = cVar;
        this.m = bArr;
        this.r = 0;
        this.l = -1;
        this.e = ByteBuffer.wrap(bArr);
        this.e.rewind();
        this.e.order(ByteOrder.LITTLE_ENDIAN);
        this.q = false;
        for (b bVar : cVar.e) {
            if (bVar.g == 3) {
                this.q = true;
                break;
            }
        }
        this.j = new byte[(cVar.f * cVar.g)];
        this.k = new int[(cVar.f * cVar.g)];
    }

    private Bitmap a(b bVar, b bVar2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = this.n.f;
        int i7 = this.n.g;
        int[] iArr = this.k;
        if (bVar2 == null) {
            Arrays.fill(iArr, 0);
        }
        if (bVar2 != null && bVar2.g > 0) {
            if (bVar2.g == 2) {
                i = 0;
                if (!bVar.f) {
                    i = this.n.l;
                    if (bVar.k != null && this.n.j == bVar.h) {
                        i = 0;
                    }
                }
                i2 = bVar2.a + (bVar2.b * i6);
                i3 = i2 + (bVar2.d * i6);
                while (i2 < i3) {
                    i4 = i2 + bVar2.c;
                    for (i5 = i2; i5 < i4; i5++) {
                        iArr[i5] = i;
                    }
                    i2 += i6;
                }
            } else if (bVar2.g == 3 && this.p != null) {
                this.p.getPixels(iArr, 0, i6, 0, 0, i6, i7);
            }
        }
        a(bVar);
        i3 = 1;
        i2 = 8;
        i5 = 0;
        for (i = 0; i < bVar.d; i++) {
            if (bVar.e) {
                if (i5 >= bVar.d) {
                    i3++;
                    switch (i3) {
                        case 2:
                            i5 = 4;
                            break;
                        case 3:
                            i5 = 2;
                            i2 = 4;
                            break;
                        case 4:
                            i5 = 1;
                            i2 = 2;
                            break;
                    }
                }
                int i8 = i5;
                i5 += i2;
                i4 = i8;
            } else {
                i4 = i;
            }
            i4 += bVar.b;
            if (i4 < this.n.g) {
                int i9 = this.n.f * i4;
                int i10 = i9 + bVar.a;
                i4 = bVar.c + i10;
                if (this.n.f + i9 < i4) {
                    i4 = this.n.f + i9;
                }
                i9 = bVar.c * i;
                int i11 = i10;
                while (i11 < i4) {
                    i10 = i9 + 1;
                    i9 = this.c[this.j[i9] & 255];
                    if (i9 != 0) {
                        iArr[i11] = i9;
                    }
                    i11++;
                    i9 = i10;
                }
            }
        }
        if (this.q && (bVar.g == 0 || bVar.g == 1)) {
            if (this.p == null) {
                this.p = j();
            }
            this.p.setPixels(iArr, 0, i6, 0, 0, i6, i7);
        }
        Bitmap j = j();
        j.setPixels(iArr, 0, i6, 0, 0, i6, i7);
        return j;
    }

    private void a(b bVar) {
        int i;
        int i2;
        if (bVar != null) {
            this.e.position(bVar.j);
        }
        if (bVar == null) {
            i = this.n.f * this.n.g;
        } else {
            i = bVar.c * bVar.d;
        }
        if (this.j == null || this.j.length < i) {
            this.j = new byte[i];
        }
        if (this.g == null) {
            this.g = new short[4096];
        }
        if (this.h == null) {
            this.h = new byte[4096];
        }
        if (this.i == null) {
            this.i = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        int h = h();
        int i3 = 1 << h;
        int i4 = i3 + 1;
        int i5 = i3 + 2;
        int i6 = -1;
        int i7 = h + 1;
        int i8 = (1 << i7) - 1;
        for (i2 = 0; i2 < i3; i2++) {
            this.g[i2] = (short) 0;
            this.h[i2] = (byte) i2;
        }
        i2 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = i7;
        int i14 = i8;
        int i15 = i5;
        i7 = 0;
        i8 = 0;
        i5 = 0;
        while (i9 < i) {
            if (i8 == 0) {
                i8 = i();
                if (i8 <= 0) {
                    this.r = 3;
                    break;
                }
                i7 = 0;
            }
            i2 += (this.f[i7] & 255) << i12;
            int i16 = i7 + 1;
            int i17 = i8 - 1;
            i7 = i13;
            i8 = i14;
            i13 = i11;
            int i18 = i12 + 8;
            i12 = i2;
            i2 = i5;
            i5 = i15;
            i15 = i18;
            while (i15 >= i7) {
                i11 = i12 & i8;
                i14 = i12 >> i7;
                i15 -= i7;
                if (i11 == i3) {
                    i7 = h + 1;
                    i8 = (1 << i7) - 1;
                    i5 = i3 + 2;
                    i12 = i14;
                    i6 = -1;
                } else if (i11 > i5) {
                    this.r = 3;
                    i11 = i13;
                    i12 = i15;
                    i13 = i7;
                    i15 = i5;
                    i7 = i16;
                    i5 = i2;
                    i2 = i14;
                    i14 = i8;
                    i8 = i17;
                    break;
                } else if (i11 == i4) {
                    i11 = i13;
                    i12 = i15;
                    i13 = i7;
                    i15 = i5;
                    i7 = i16;
                    i5 = i2;
                    i2 = i14;
                    i14 = i8;
                    i8 = i17;
                    break;
                } else if (i6 == -1) {
                    i12 = i10 + 1;
                    this.i[i10] = this.h[i11];
                    i10 = i12;
                    i13 = i11;
                    i6 = i11;
                    i12 = i14;
                } else {
                    if (i11 >= i5) {
                        i12 = i10 + 1;
                        this.i[i10] = (byte) i13;
                        i10 = i12;
                        i13 = i6;
                    } else {
                        i13 = i11;
                    }
                    while (i13 >= i3) {
                        i12 = i10 + 1;
                        this.i[i10] = this.h[i13];
                        short s = this.g[i13];
                        i10 = i12;
                    }
                    i13 = this.h[i13] & 255;
                    i12 = i10 + 1;
                    this.i[i10] = (byte) i13;
                    if (i5 < 4096) {
                        this.g[i5] = (short) i6;
                        this.h[i5] = (byte) i13;
                        i5++;
                        if ((i5 & i8) == 0 && i5 < 4096) {
                            i7++;
                            i8 += i5;
                        }
                    }
                    i10 = i9;
                    while (i12 > 0) {
                        i9 = i12 - 1;
                        i12 = i2 + 1;
                        this.j[i2] = this.i[i9];
                        i10++;
                        i2 = i12;
                        i12 = i9;
                    }
                    i9 = i10;
                    i6 = i11;
                    i10 = i12;
                    i12 = i14;
                }
            }
            i11 = i13;
            i14 = i8;
            i8 = i17;
            i13 = i7;
            i7 = i16;
            i18 = i15;
            i15 = i5;
            i5 = i2;
            i2 = i12;
            i12 = i18;
        }
        for (i7 = i5; i7 < i; i7++) {
            this.j[i7] = (byte) 0;
        }
    }

    private int h() {
        int i = 0;
        try {
            return this.e.get() & 255;
        } catch (Exception e) {
            this.r = 1;
            return i;
        }
    }

    private int i() {
        int h = h();
        int i = 0;
        if (h > 0) {
            while (i < h) {
                int i2 = h - i;
                try {
                    this.e.get(this.f, i, i2);
                    i += i2;
                } catch (Throwable e) {
                    Log.w(a, "Error Reading Block", e);
                    this.r = 1;
                }
            }
        }
        return i;
    }

    private Bitmap j() {
        Bitmap a = this.o.a(this.n.f, this.n.g, b);
        if (a == null) {
            a = Bitmap.createBitmap(this.n.f, this.n.g, b);
        }
        a(a);
        return a;
    }

    @TargetApi(12)
    private static void a(Bitmap bitmap) {
        if (VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }
}
