package cn.jiguang.g.c.a;

import android.content.Context;
import android.telephony.TelephonyManager;
import cn.jiguang.g.c.a;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class d extends a {
    public final ArrayList<a> a(Context context) {
        ArrayList<a> arrayList = new ArrayList();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        Class cls = Class.forName("android.telephony.MSimTelephonyManager");
        Object systemService = context.getSystemService("phone_msim");
        Method method = cls.getMethod("getDataState", new Class[0]);
        Method method2 = cls.getMethod("getDeviceId", new Class[]{Integer.TYPE});
        Method method3 = cls.getMethod("getSubscriberId", new Class[]{Integer.TYPE});
        a aVar = new a();
        try {
            aVar.b = (String) method2.invoke(systemService, new Object[]{Integer.valueOf(0)});
            aVar.c = (String) method3.invoke(systemService, new Object[]{Integer.valueOf(0)});
            aVar.d = telephonyManager.getDataState();
        } catch (Throwable th) {
            cn.jiguang.e.d.g("QualcommTelImpl", "load failed, error:" + th);
            return null;
        }
        arrayList.add(aVar);
        a aVar2 = new a();
        try {
            aVar2.b = (String) method2.invoke(systemService, new Object[]{Integer.valueOf(1)});
            aVar2.c = (String) method3.invoke(systemService, new Object[]{Integer.valueOf(1)});
            aVar2.d = ((Integer) method.invoke(systemService, new Object[0])).intValue();
        } catch (Throwable th2) {
            cn.jiguang.e.d.g("QualcommTelImpl", "load sim2 info failed:" + th2);
        }
        arrayList.add(aVar2);
        cn.jiguang.e.d.e("QualcommTelImpl", "simInfos:" + arrayList);
        return arrayList;
    }

    public final boolean b(Context context) {
        try {
            Class cls = Class.forName("android.telephony.MSimTelephonyManager");
            Object systemService = context.getSystemService("phone_msim");
            cls.getMethod("getDataState", new Class[0]);
            return (systemService == null || cls.getMethod("getDeviceId", new Class[]{Integer.TYPE}) == null || cls.getMethod("getSubscriberId", new Class[]{Integer.TYPE}) == null) ? false : true;
        } catch (Throwable th) {
            cn.jiguang.e.d.g("QualcommTelImpl", "check device failed, error:" + th);
            return false;
        }
    }
}
