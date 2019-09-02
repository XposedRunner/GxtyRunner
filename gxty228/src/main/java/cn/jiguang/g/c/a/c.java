package cn.jiguang.g.c.a;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.jiguang.e.d;
import cn.jiguang.g.c.a;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class c extends a {
    private static ArrayList<Integer> a(TelephonyManager telephonyManager) {
        ArrayList<Integer> arrayList = new ArrayList();
        try {
            int i = 0;
            for (Field field : TelephonyManager.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (TextUtils.equals(field.getType().getName(), "com.android.internal.telephony.ITelephonyRegistry")) {
                    Object obj = field.get(telephonyManager);
                    d.g("MTKTelImpl", field.getName() + " value:" + obj);
                    if (obj != null) {
                        arrayList.add(Integer.valueOf(i));
                        i++;
                    }
                }
            }
        } catch (Throwable th) {
            d.g("MTKTelImpl", "get sim slots failed, error:" + th);
            arrayList.clear();
            arrayList.add(Integer.valueOf(0));
            arrayList.add(Integer.valueOf(1));
        }
        return arrayList;
    }

    public final ArrayList<a> a(Context context) {
        ArrayList<a> arrayList = new ArrayList();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getSubscriberIdGemini", new Class[]{Integer.TYPE});
        Method declaredMethod2 = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", new Class[]{Integer.TYPE});
        Method declaredMethod3 = TelephonyManager.class.getDeclaredMethod("getPhoneTypeGemini", new Class[]{Integer.TYPE});
        Method declaredMethod4 = TelephonyManager.class.getDeclaredMethod("getNetworkOperatorNameGemini", new Class[]{Integer.TYPE});
        Method declaredMethod5 = TelephonyManager.class.getDeclaredMethod("getSimSerialNumberGemini", new Class[]{Integer.TYPE});
        Method declaredMethod6 = TelephonyManager.class.getDeclaredMethod("getSimOperatorNameGemini", new Class[]{Integer.TYPE});
        Method declaredMethod7 = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", new Class[]{Integer.TYPE});
        Method declaredMethod8 = TelephonyManager.class.getDeclaredMethod("getLine1NumberGemini", new Class[]{Integer.TYPE});
        ArrayList a = a(telephonyManager);
        for (int i = 0; i < a.size(); i++) {
            a aVar = new a();
            try {
                int intValue = ((Integer) a.get(i)).intValue();
                aVar.c = (String) declaredMethod.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                aVar.b = (String) declaredMethod2.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                aVar.d = ((Integer) declaredMethod3.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)})).intValue();
                aVar.g = (String) declaredMethod4.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                aVar.f = (String) declaredMethod6.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                aVar.e = (String) declaredMethod5.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                aVar.a = (String) declaredMethod7.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                d.e("MTKTelImpl", "number:" + declaredMethod8.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)}));
            } catch (Throwable th) {
                d.g("MTKTelImpl", "load failed, error:" + th);
                return null;
            }
            arrayList.add(aVar);
            d.e("MTKTelImpl", "sim" + i + ":" + aVar);
        }
        d.e("MTKTelImpl", "simInfos:" + arrayList);
        return arrayList;
    }

    public final boolean b(Context context) {
        try {
            a((TelephonyManager) context.getSystemService("phone"));
            Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getSubscriberIdGemini", new Class[]{Integer.TYPE});
            Method declaredMethod2 = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", new Class[]{Integer.TYPE});
            Method declaredMethod3 = TelephonyManager.class.getDeclaredMethod("getPhoneTypeGemini", new Class[]{Integer.TYPE});
            Field declaredField = TelephonyManager.class.getDeclaredField("mtkGeminiSupport");
            if (!(declaredMethod == null || declaredMethod2 == null || declaredMethod3 == null || declaredField == null)) {
                declaredField.setAccessible(true);
                boolean booleanValue = ((Boolean) declaredField.get(null)).booleanValue();
                d.e("MTKTelImpl", "mtkGeminiSupportValue:" + booleanValue);
                if (booleanValue) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            d.g("MTKTelImpl", "check failed:" + th);
            return false;
        }
    }
}
