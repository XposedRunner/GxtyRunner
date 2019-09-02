package cn.jiguang.c;

import android.content.Context;
import android.text.TextUtils;
import cn.jiguang.e.d;
import java.lang.reflect.Method;

public final class c {
    private static String a = "";

    public static String a(Context context) {
        Object obj = "";
        return !TextUtils.isEmpty(obj) ? obj : b(context);
    }

    private static String b(Context context) {
        Object systemService;
        String str = null;
        int i = -1;
        if (context != null) {
            try {
                systemService = context.getApplicationContext().getSystemService("country_detector");
                if (systemService != null) {
                    int intValue;
                    Method declaredMethod = systemService.getClass().getDeclaredMethod("detectCountry", new Class[0]);
                    if (declaredMethod != null) {
                        Object invoke = declaredMethod.invoke(systemService, new Object[0]);
                        if (invoke != null) {
                            String str2 = (String) invoke.getClass().getDeclaredMethod("getCountryIso", new Class[0]).invoke(invoke, new Object[0]);
                            try {
                                str = str2;
                                intValue = ((Integer) invoke.getClass().getDeclaredMethod("getSource", new Class[0]).invoke(invoke, new Object[0])).intValue();
                                i = intValue;
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                str = str2;
                                Throwable th3 = th2;
                                d.i("HostConfig_RegionUtils", "getCountryCode failed, error :" + systemService);
                                d.c("HostConfig_RegionUtils", "get CountCode = " + str + " source = " + i);
                                return i != 0 ? str : str;
                            }
                        }
                    }
                    intValue = -1;
                    i = intValue;
                } else {
                    d.c("HostConfig_RegionUtils", "country_detector is null");
                }
            } catch (Throwable th4) {
                systemService = th4;
                d.i("HostConfig_RegionUtils", "getCountryCode failed, error :" + systemService);
                d.c("HostConfig_RegionUtils", "get CountCode = " + str + " source = " + i);
                if (i != 0) {
                }
            }
        }
        d.c("HostConfig_RegionUtils", "get CountCode = " + str + " source = " + i);
        if (i != 0 && i != 1) {
            return "";
        }
    }
}
