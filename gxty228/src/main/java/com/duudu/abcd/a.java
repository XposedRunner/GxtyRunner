package com.duudu.abcd;

import android.content.Context;
import android.util.Log;
import com.duudu.abcd.a.b;
import com.duudu.abcd.a.c;
import com.duudu.abcd.a.d;
import com.duudu.abcd.a.e;
import com.duudu.abcd.a.f;
import com.duudu.abcd.a.g;
import com.duudu.abcd.a.h;
import com.duudu.abcd.a.i;
import com.duudu.abcd.a.j;
import com.duudu.abcd.a.k;
import com.duudu.abcd.a.l;
import com.duudu.abcd.manufacturer.ManufacturerType;

/* compiled from: PermissionHelper */
public class a {
    private static a a;
    private f b;

    /* compiled from: PermissionHelper */
    private static class a {
        static final int[] a = new int[ManufacturerType.values().length];

        static {
            try {
                a[ManufacturerType.HUAWEI.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
                Log.e("TAG", e.getLocalizedMessage());
            }
            try {
                a[ManufacturerType.VIVO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
                Log.e("TAG", e2.getLocalizedMessage());
            }
            try {
                a[ManufacturerType.OPPO.ordinal()] = 3;
            } catch (NoSuchFieldError e22) {
                Log.e("TAG", e22.getLocalizedMessage());
            }
            try {
                a[ManufacturerType.XIAOMI.ordinal()] = 4;
            } catch (NoSuchFieldError e222) {
                Log.e("TAG", e222.getLocalizedMessage());
            }
            try {
                a[ManufacturerType.MEIZU.ordinal()] = 5;
            } catch (NoSuchFieldError e2222) {
                Log.e("TAG", e2222.getLocalizedMessage());
            }
            try {
                a[ManufacturerType.SAMSUNG.ordinal()] = 6;
            } catch (NoSuchFieldError e22222) {
                Log.e("TAG", e22222.getLocalizedMessage());
            }
            try {
                a[ManufacturerType.LETV.ordinal()] = 7;
            } catch (NoSuchFieldError e222222) {
                Log.e("TAG", e222222.getLocalizedMessage());
            }
            try {
                a[ManufacturerType.SMARTISAN.ordinal()] = 8;
            } catch (NoSuchFieldError e2222222) {
                Log.e("TAG", e2222222.getLocalizedMessage());
            }
            try {
                a[ManufacturerType.LENOVO.ordinal()] = 9;
            } catch (NoSuchFieldError e22222222) {
                Log.e("TAG", e22222222.getLocalizedMessage());
            }
            try {
                a[ManufacturerType.COOLPAD.ordinal()] = 10;
            } catch (NoSuchFieldError e222222222) {
                Log.e("TAG", e222222222.getLocalizedMessage());
            }
            try {
                a[ManufacturerType.ZTE.ordinal()] = 11;
            } catch (NoSuchFieldError e2222222222) {
                Log.e("TAG", e2222222222.getLocalizedMessage());
            }
        }
    }

    public static a a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    private a(Context context) {
        this.b = a(com.duudu.abcd.manufacturer.a.a(), context);
    }

    public boolean a() {
        return this.b.e();
    }

    public boolean b() {
        return this.b.f();
    }

    public boolean c() {
        return this.b.g();
    }

    public boolean d() {
        return this.b.h();
    }

    public String e() {
        return this.b.c();
    }

    public String f() {
        return this.b.d();
    }

    private f a(ManufacturerType manufacturerType, Context context) {
        switch (a.a[manufacturerType.ordinal()]) {
            case 1:
                return new c(context);
            case 2:
                return new j(context);
            case 3:
                return new g(context);
            case 4:
                return new k(context);
            case 5:
                return new e(context);
            case 6:
                return new h(context);
            case 7:
                return new d(context);
            case 8:
                return new i(context);
            case 9:
                return new d(context);
            case 10:
                return new com.duudu.abcd.a.a(context);
            case 11:
                return new l(context);
            default:
                return new b(context);
        }
    }
}
