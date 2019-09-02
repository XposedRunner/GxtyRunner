package com.nostra13.universalimageloader.b;

import android.opengl.GLES10;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.assist.c;

/* compiled from: ImageSizeUtils */
public final class a {
    private static c a;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], 2048);
        a = new c(max, max);
    }

    public static c a(com.nostra13.universalimageloader.core.c.a aVar, c cVar) {
        int a = aVar.a();
        if (a <= 0) {
            a = cVar.a();
        }
        int b = aVar.b();
        if (b <= 0) {
            b = cVar.b();
        }
        return new c(a, b);
    }

    public static int a(c cVar, c cVar2, ViewScaleType viewScaleType, boolean z) {
        int max;
        int i = 1;
        int a = cVar.a();
        int b = cVar.b();
        int a2 = cVar2.a();
        int b2 = cVar2.b();
        int i2;
        int i3;
        switch (viewScaleType) {
            case FIT_INSIDE:
                if (!z) {
                    max = Math.max(a / a2, b / b2);
                    break;
                }
                i2 = a / 2;
                i3 = b / 2;
                max = 1;
                while (true) {
                    if (i2 / max <= a2 && i3 / max <= b2) {
                        break;
                    }
                    max *= 2;
                }
                break;
            case CROP:
                if (!z) {
                    max = Math.min(a / a2, b / b2);
                    break;
                }
                i2 = a / 2;
                i3 = b / 2;
                max = 1;
                while (i2 / max > a2 && i3 / max > b2) {
                    max *= 2;
                }
                break;
            default:
                max = 1;
                break;
        }
        if (max >= 1) {
            i = max;
        }
        return a(a, b, i, z);
    }

    private static int a(int i, int i2, int i3, boolean z) {
        int a = a.a();
        int b = a.b();
        while (true) {
            if (i / i3 <= a && i2 / i3 <= b) {
                return i3;
            }
            if (z) {
                i3 *= 2;
            } else {
                i3++;
            }
        }
    }

    public static int a(c cVar) {
        int a = cVar.a();
        int b = cVar.b();
        return Math.max((int) Math.ceil((double) (((float) a) / ((float) a.a()))), (int) Math.ceil((double) (((float) b) / ((float) a.b()))));
    }

    public static float b(c cVar, c cVar2, ViewScaleType viewScaleType, boolean z) {
        int i;
        int a = cVar.a();
        int b = cVar.b();
        int a2 = cVar2.a();
        int b2 = cVar2.b();
        float f = ((float) a) / ((float) a2);
        float f2 = ((float) b) / ((float) b2);
        if ((viewScaleType != ViewScaleType.FIT_INSIDE || f < f2) && (viewScaleType != ViewScaleType.CROP || f >= f2)) {
            i = (int) (((float) a) / f2);
            a2 = b2;
        } else {
            i = a2;
            a2 = (int) (((float) b) / f);
        }
        if ((z || i >= a || r1 >= b) && (!z || i == a || r1 == b)) {
            return 1.0f;
        }
        return ((float) i) / ((float) a);
    }
}
