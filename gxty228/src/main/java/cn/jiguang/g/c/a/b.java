package cn.jiguang.g.c.a;

import android.content.Context;
import android.telephony.TelephonyManager;
import cn.jiguang.e.d;
import cn.jiguang.g.c.a;
import java.lang.reflect.Method;
import java.util.ArrayList;

public final class b extends a {
    private static int a(int i) {
        try {
            Method declaredMethod = Class.forName("android.telephony.SubscriptionManager").getDeclaredMethod("getSubId", new Class[]{Integer.TYPE});
            declaredMethod.setAccessible(true);
            int[] iArr = (int[]) declaredMethod.invoke(null, new Object[]{Integer.valueOf(i)});
            if (iArr.length <= 0) {
                return i;
            }
            d.e("AndroidTelImpl", "subid:" + iArr[0]);
            return iArr[0];
        } catch (Throwable th) {
            d.g("AndroidTelImpl", "get subid failed,error:" + th);
            return i;
        }
    }

    private static int a(int i, int i2) {
        try {
            Method declaredMethod = Class.forName("android.telephony.SubscriptionManager").getDeclaredMethod("getPhoneId", new Class[]{Integer.TYPE});
            declaredMethod.setAccessible(true);
            int intValue = ((Integer) declaredMethod.invoke(null, new Object[]{Integer.valueOf(i2)})).intValue();
            d.e("AndroidTelImpl", "phoneId:" + intValue);
            return intValue;
        } catch (Throwable th) {
            d.g("AndroidTelImpl", "get subid failed,error:" + th);
            return i;
        }
    }

    private static a a(TelephonyManager telephonyManager) {
        if (telephonyManager == null) {
            d.g("AndroidTelImpl", "tm was null");
            return null;
        }
        a aVar = new a();
        try {
            aVar.b = telephonyManager.getDeviceId();
            aVar.c = telephonyManager.getSubscriberId();
            aVar.g = telephonyManager.getNetworkOperatorName();
            aVar.f = telephonyManager.getSimOperatorName();
            aVar.d = telephonyManager.getPhoneType();
            aVar.e = telephonyManager.getSimSerialNumber();
            return aVar;
        } catch (Throwable th) {
            d.g("AndroidTelImpl", "get default sim info failed, error:" + th);
            return aVar;
        }
    }

    private static int b(TelephonyManager telephonyManager) {
        try {
            return ((Integer) telephonyManager.getClass().getMethod("getSimCount", new Class[0]).invoke(telephonyManager, new Object[0])).intValue();
        } catch (Throwable th) {
            d.g("AndroidTelImpl", "get sim count failed, error:" + th);
            return -1;
        }
    }

    public final ArrayList<a> a(Context context) {
        ArrayList<a> arrayList = new ArrayList();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            int b = b(telephonyManager);
            d.e("AndroidTelImpl", "simCount:" + b);
            if (b > 0) {
                Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDeviceId", new Class[]{Integer.TYPE});
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = telephonyManager.getClass().getDeclaredMethod("getImei", new Class[]{Integer.TYPE});
                declaredMethod2.setAccessible(true);
                Method declaredMethod3 = telephonyManager.getClass().getDeclaredMethod("getNetworkOperatorName", new Class[]{Integer.TYPE});
                declaredMethod3.setAccessible(true);
                Method declaredMethod4 = telephonyManager.getClass().getDeclaredMethod("getSimOperatorNameForPhone", new Class[]{Integer.TYPE});
                declaredMethod4.setAccessible(true);
                Method declaredMethod5 = telephonyManager.getClass().getDeclaredMethod("getSimSerialNumber", new Class[]{Integer.TYPE});
                declaredMethod5.setAccessible(true);
                Method declaredMethod6 = telephonyManager.getClass().getDeclaredMethod("getSubscriberId", new Class[]{Integer.TYPE});
                declaredMethod6.setAccessible(true);
                Method declaredMethod7 = telephonyManager.getClass().getDeclaredMethod("getCurrentPhoneType", new Class[]{Integer.TYPE});
                declaredMethod7.setAccessible(true);
                for (int i = 0; i < b; i++) {
                    int a = a(i, a(i));
                    a aVar = new a();
                    try {
                        aVar.a = (String) declaredMethod.invoke(telephonyManager, new Object[]{Integer.valueOf(i)});
                        aVar.b = (String) declaredMethod2.invoke(telephonyManager, new Object[]{Integer.valueOf(i)});
                        aVar.e = (String) declaredMethod5.invoke(telephonyManager, new Object[]{Integer.valueOf(r13)});
                        aVar.g = (String) declaredMethod3.invoke(telephonyManager, new Object[]{Integer.valueOf(r13)});
                        aVar.f = (String) declaredMethod4.invoke(telephonyManager, new Object[]{Integer.valueOf(a)});
                        aVar.d = ((Integer) declaredMethod7.invoke(telephonyManager, new Object[]{Integer.valueOf(r13)})).intValue();
                        aVar.c = (String) declaredMethod6.invoke(telephonyManager, new Object[]{Integer.valueOf(r13)});
                    } catch (Throwable th) {
                        d.g("AndroidTelImpl", "load sim info error:" + th);
                        arrayList.clear();
                        arrayList.add(a(telephonyManager));
                    }
                    d.e("AndroidTelImpl", "sim" + i + " info:" + aVar);
                    arrayList.add(aVar);
                }
                d.e("AndroidTelImpl", "simInfos:" + arrayList);
                return arrayList;
            }
            arrayList.add(a(telephonyManager));
            d.e("AndroidTelImpl", "simInfos:" + arrayList);
            return arrayList;
        } catch (Throwable th2) {
            d.g("AndroidTelImpl", "check device failed,error:" + th2);
            arrayList.clear();
        }
    }

    public final boolean b(Context context) {
        try {
            return TelephonyManager.class.getMethod("getSimCount", new Class[0]) != null;
        } catch (Throwable th) {
            d.g("AndroidTelImpl", "check device failed, error:" + th);
            return false;
        }
    }
}
