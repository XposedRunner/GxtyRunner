package com.amap.api.mapcore.util;

import android.content.Context;
import java.io.File;
import java.lang.reflect.Constructor;

/* compiled from: InstanceFactory */
public class hn {
    public static <T> T a(Context context, gk gkVar, String str, Class cls, Class[] clsArr, Object[] objArr) throws gp {
        T a = a(b(context, gkVar), str, clsArr, objArr);
        if (a == null) {
            a = a(cls, clsArr, objArr);
            if (a == null) {
                throw new gp("获取对象错误");
            }
        }
        return a;
    }

    public static void a(final Context context, final String str) {
        try {
            hv.b().a().submit(new Runnable() {
                public void run() {
                    try {
                        hq.a(new he(context, hs.a()), context, str);
                    } catch (Throwable th) {
                        hw.a(th, "InstanceFactory", "rollBack");
                    }
                }
            });
        } catch (Throwable th) {
            hw.a(th, "InstanceFactory", "rollBack");
        }
    }

    public static boolean a(Context context, gk gkVar) {
        try {
            if (!new File(hq.a(context)).exists()) {
                return false;
            }
            File file = new File(hq.b(context, gkVar.a(), gkVar.b()));
            if (file.exists()) {
                return true;
            }
            hq.a(context, file, gkVar);
            return false;
        } catch (Throwable th) {
            hw.a(th, "IFactory", "isdowned");
            return false;
        }
    }

    public static Class a(Context context, gk gkVar, String str) {
        Class cls = null;
        ho b = b(context, gkVar);
        try {
            if (a(b)) {
                cls = b.loadClass(str);
            }
        } catch (Throwable th) {
            hw.a(th, "InstanceFactory", "loadpn");
        }
        return cls;
    }

    private static ho b(Context context, gk gkVar) {
        ho hoVar = null;
        if (context != null) {
            try {
                if (a(context, gkVar)) {
                    hoVar = hv.b().a(context, gkVar);
                }
            } catch (Throwable th) {
                hw.a(th, "IFactory", "gIns1");
            }
        }
        return hoVar;
    }

    private static boolean a(ho hoVar) {
        if (hoVar != null && hoVar.a() && hoVar.d) {
            return true;
        }
        return false;
    }

    private static <T> T a(ho hoVar, String str, Class[] clsArr, Object[] objArr) {
        try {
            if (a(hoVar)) {
                Class loadClass = hoVar.loadClass(str);
                if (loadClass != null) {
                    Constructor declaredConstructor = loadClass.getDeclaredConstructor(clsArr);
                    declaredConstructor.setAccessible(true);
                    return declaredConstructor.newInstance(objArr);
                }
            }
        } catch (Throwable th) {
            hw.a(th, "IFactory", "getWrap");
        }
        return null;
    }

    private static <T> T a(Class cls, Class[] clsArr, Object[] objArr) {
        T t = null;
        if (cls != null) {
            try {
                Constructor declaredConstructor = cls.getDeclaredConstructor(clsArr);
                declaredConstructor.setAccessible(true);
                t = declaredConstructor.newInstance(objArr);
            } catch (Throwable th) {
                hw.a(th, "IFactory", "gIns2()");
            }
        }
        return t;
    }
}
