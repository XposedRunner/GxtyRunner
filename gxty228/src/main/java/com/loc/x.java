package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.lang.reflect.Constructor;

/* compiled from: InstanceFactory */
public final class x {
    public static Class a(Context context, dk dkVar, String str) {
        Class cls = null;
        y c = c(context, dkVar);
        try {
            if (a(c)) {
                cls = c.loadClass(str);
            }
        } catch (Throwable th) {
            g.a(th, "InstanceFactory", "loadpn");
        }
        return cls;
    }

    public static <T> T a(Context context, dk dkVar, String str, Class cls, Class[] clsArr, Object[] objArr) throws k {
        T a = a(c(context, dkVar), str, clsArr, objArr);
        if (a == null) {
            a = a(cls, clsArr, objArr);
            if (a == null) {
                throw new k("获取对象错误");
            }
        }
        return a;
    }

    private static <T> T a(y yVar, String str, Class[] clsArr, Object[] objArr) {
        try {
            if (a(yVar)) {
                Class loadClass = yVar.loadClass(str);
                if (loadClass != null) {
                    Constructor declaredConstructor = loadClass.getDeclaredConstructor(clsArr);
                    declaredConstructor.setAccessible(true);
                    return declaredConstructor.newInstance(objArr);
                }
            }
        } catch (Throwable th) {
            g.a(th, "IFactory", "getWrap");
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
                g.a(th, "IFactory", "gIns2()");
            }
        }
        return t;
    }

    public static String a(Context context, dk dkVar) {
        try {
            if (!new File(aa.a(context)).exists()) {
                return null;
            }
            File file = new File(aa.b(context, dkVar.a(), dkVar.b()));
            if (file.exists()) {
                return file.getAbsolutePath();
            }
            aa.a(context, file, dkVar);
            return null;
        } catch (Throwable th) {
            g.a(th, "IFactory", "isdowned");
            return null;
        }
    }

    public static void a(final Context context, final String str) {
        try {
            ag.b().a().submit(new Runnable() {
                public final void run() {
                    try {
                        aa.a(new p(context, ad.b()), context, str);
                    } catch (Throwable th) {
                        g.a(th, "InstanceFactory", "rollBack");
                    }
                }
            });
        } catch (Throwable th) {
            g.a(th, "InstanceFactory", "rollBack");
        }
    }

    public static boolean a(Context context, w wVar, dk dkVar) {
        boolean z = wVar != null && wVar.c();
        try {
            if (!ah.a(dkVar, wVar) || !ah.a(wVar) || !ah.a(context, z) || ah.a(context, wVar, dkVar)) {
                return false;
            }
            aa.b(context, dkVar.a());
            return true;
        } catch (Throwable th) {
            g.a(th, "dDownLoad", "isNeedDownload()");
            return false;
        }
    }

    private static boolean a(y yVar) {
        return yVar != null && yVar.a() && yVar.d;
    }

    public static void b(Context context, w wVar, dk dkVar) {
        if (wVar != null) {
            try {
                if (!TextUtils.isEmpty(wVar.a()) && !TextUtils.isEmpty(wVar.b()) && !TextUtils.isEmpty(wVar.c)) {
                    new v(context, wVar, dkVar).a();
                }
            } catch (Throwable th) {
                g.a(th, "IFactory", "dDownload()");
            }
        }
    }

    public static boolean b(Context context, dk dkVar) {
        try {
            if (!new File(aa.a(context)).exists()) {
                return false;
            }
            File file = new File(aa.b(context, dkVar.a(), dkVar.b()));
            if (file.exists()) {
                return true;
            }
            aa.a(context, file, dkVar);
            return false;
        } catch (Throwable th) {
            g.a(th, "IFactory", "isdowned");
            return false;
        }
    }

    private static y c(Context context, dk dkVar) {
        y yVar = null;
        if (context != null) {
            try {
                if (b(context, dkVar)) {
                    yVar = ag.b().a(context, dkVar);
                }
            } catch (Throwable th) {
                g.a(th, "IFactory", "gIns1");
            }
        }
        return yVar;
    }
}
