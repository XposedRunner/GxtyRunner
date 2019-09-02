package cn.jiguang.g.c.a;

import android.content.Context;
import android.telephony.TelephonyManager;
import cn.jiguang.e.d;
import cn.jiguang.g.c.a;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class e extends a {
    public final ArrayList<a> a(Context context) {
        ArrayList<a> arrayList = new ArrayList();
        Class cls = Class.forName("com.android.internal.telephony.PhoneFactory");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService((String) cls.getMethod("getServiceName", new Class[]{String.class, Integer.TYPE}).invoke(cls, new Object[]{"phone", Integer.valueOf(1)}));
        TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService("phone");
        a aVar = new a();
        try {
            aVar.c = telephonyManager2.getSubscriberId();
            aVar.b = telephonyManager2.getDeviceId();
            aVar.e = telephonyManager2.getSimSerialNumber();
            aVar.d = telephonyManager2.getPhoneType();
        } catch (Throwable th) {
            d.g("SpreadTelImpl", "load failed, error:" + th);
            return null;
        }
        arrayList.add(aVar);
        a aVar2 = new a();
        try {
            aVar2.c = telephonyManager.getSubscriberId();
            aVar2.b = telephonyManager.getDeviceId();
            aVar2.e = telephonyManager.getSimSerialNumber();
            aVar2.d = telephonyManager.getPhoneType();
        } catch (Throwable th2) {
            d.g("SpreadTelImpl", "load sim2 info failed:" + th2);
        }
        arrayList.add(aVar2);
        return arrayList;
    }

    public final boolean b(Context context) {
        try {
            Class cls = Class.forName("com.android.internal.telephony.PhoneFactory");
            Method method = cls.getMethod("getServiceName", new Class[]{String.class, Integer.TYPE});
            String str = (String) method.invoke(cls, new Object[]{"phone", Integer.valueOf(1)});
            return (method == null || str == null || ((TelephonyManager) context.getSystemService(str)) == null) ? false : true;
        } catch (Throwable th) {
            d.g("SpreadTelImpl", "check device failed, error:" + th);
            return false;
        }
    }
}
