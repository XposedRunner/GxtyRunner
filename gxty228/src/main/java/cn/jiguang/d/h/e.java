package cn.jiguang.d.h;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.jiguang.api.JCoreInterface;
import cn.jiguang.api.SdkType;
import cn.jiguang.d.d.a;
import cn.jiguang.d.d.o;
import cn.jiguang.e.d;
import cn.jiguang.g.k;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class e extends a {
    private boolean e = false;
    private String f = null;
    private Class<?> g = null;
    private String h = null;

    private a a(Context context, PackageManager packageManager, String str, String str2) {
        Object obj = null;
        if (packageManager == null || TextUtils.isEmpty(str)) {
            d.c("WakeUpJiGuangSdkManager", "pkgManager is null or packageName was empty");
            return null;
        }
        try {
            int checkPermission = packageManager.checkPermission(str + ".permission.JPUSH_MESSAGE", str);
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object string = applicationInfo.metaData.getString("JPUSH_APPKEY");
                Intent intent = new Intent();
                intent.setClassName(str, this.h);
                List queryIntentServices = packageManager.queryIntentServices(intent, 0);
                d.e("WakeUpJiGuangSdkManager", "[checkWhetherToStart] - permission:" + checkPermission + ". appkey:" + string + ". PushService:" + (queryIntentServices == null ? "null" : Integer.valueOf(queryIntentServices.size())));
                if (!(queryIntentServices == null || queryIntentServices.size() == 0)) {
                    obj = 1;
                }
                if (checkPermission == 0 && r0 != null && !TextUtils.isEmpty(string) && string.length() == 24) {
                    a aVar = new a(str, str2, applicationInfo.targetSdkVersion);
                    ComponentInfo a = cn.jiguang.g.a.a(context, str, this.g);
                    if (a != null && (a instanceof ProviderInfo)) {
                        ProviderInfo providerInfo = (ProviderInfo) a;
                        if (providerInfo.exported && providerInfo.enabled && providerInfo.authority != null && TextUtils.equals(str + ".DownloadProvider", providerInfo.authority)) {
                            aVar.d = providerInfo.authority;
                        }
                    }
                    return aVar;
                }
            }
            return null;
        } catch (NameNotFoundException e) {
            d.g("WakeUpJiGuangSdkManager", "Can not get application info with package name : " + str);
            return null;
        } catch (Throwable th) {
            d.g("WakeUpJiGuangSdkManager", "checkWhetherToStart error:" + th);
            return null;
        }
    }

    private static String a(String str, String str2) {
        return k.b(System.currentTimeMillis() + str + str2);
    }

    private static JSONObject a(int i, String str, String str2, String str3, boolean z) {
        Object obj;
        Object obj2;
        Object obj3;
        if (str == null) {
            try {
                obj = "";
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        if (str2 == null) {
            obj2 = "";
        }
        if (str3 == null) {
            obj3 = "";
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("awake_type", i);
        jSONObject.put("from_package", obj);
        jSONObject.put("from_uid", obj2);
        jSONObject.put("awake_sequence", obj3);
        jSONObject.put("app_alive", z);
        return jSONObject;
    }

    public static void b(Context context, String str, JSONObject jSONObject) {
        JSONObject b = o.b(context, jSONObject, str);
        JSONObject a = o.a(context, "wakeup_cache.json");
        if (a == null) {
            a = new JSONObject();
        }
        JSONArray optJSONArray = a.optJSONArray("content");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        try {
            optJSONArray.put(b);
            a.put("content", optJSONArray);
            o.a(context, "wakeup_cache.json", a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public final JSONObject a(String str, ArrayList<d> arrayList) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            try {
                JSONObject jSONObject = new JSONObject();
                String packageName = dVar.a().getPackageName();
                String a = a(this.b, packageName);
                jSONObject.put("target_package", packageName);
                jSONObject.put("awake_sequence", a);
                JSONArray jSONArray2 = new JSONArray();
                HashMap b = dVar.b();
                for (Integer intValue : b.keySet()) {
                    int intValue2 = intValue.intValue();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("awake_type", intValue2);
                    jSONObject2.put("success", b.get(Integer.valueOf(intValue2)));
                    jSONArray2.put(jSONObject2);
                }
                jSONObject.put("awake", jSONArray2);
                jSONArray.put(jSONObject);
            } catch (Throwable th) {
                d.i("WakeUpJiGuangSdkManager", "formatReportData:" + th);
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("package", str);
            jSONObject3.put("target", jSONArray);
            jSONObject3.put("device", Build.MODEL);
            jSONObject3.put(IXAdRequestInfo.OS, VERSION.RELEASE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject3;
    }

    public final void a(Context context, int i, boolean z, String str, String str2, String str3) {
        try {
            JSONObject a = a(i, str, str2, str3, z);
            d.c("WakeUpJiGuangSdkManager", "reportData:" + a);
            b(context, "android_awake_target", a);
        } catch (Throwable th) {
            d.i("WakeUpJiGuangSdkManager", "report awake reslut error:" + th);
        }
    }

    protected final void a(Context context, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            d.i("WakeUpJiGuangSdkManager", "cannot report null data.");
            return;
        }
        JSONObject b = o.b(context, jSONObject, str);
        JSONObject a = o.a(context, "wakeup_cache.json");
        if (a == null) {
            a = new JSONObject();
        }
        JSONArray optJSONArray = a.optJSONArray("content");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        optJSONArray.put(b);
        JCoreInterface.reportHttpData(context, optJSONArray, SdkType.JCORE.name());
        o.a(context, "wakeup_cache.json", null);
    }

    public final void a(Class<?> cls) {
        this.g = cls;
    }

    public final void a(boolean z) {
        this.e = z;
    }

    protected final boolean a() {
        if (!TextUtils.isEmpty(this.h) || this.g != null) {
            return this.e;
        }
        d.i("WakeUpJiGuangSdkManager", "please set wakeUpServiceName and wakeUpContentProviderClass");
        return false;
    }

    protected final boolean a(Context context) {
        if (context == null) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long k = cn.jiguang.d.a.d.k(context);
        d.c("WakeUpJiGuangSdkManager", "time now:" + currentTimeMillis + ", last launched time:" + k);
        if (-1 == k || Math.abs(currentTimeMillis - k) > this.a) {
            return true;
        }
        d.a("WakeUpJiGuangSdkManager", "localTime - lastLaunchTime = " + (currentTimeMillis - k));
        return false;
    }

    protected final void b(Context context) {
        cn.jiguang.d.a.d.a(context, System.currentTimeMillis() / 1000);
    }

    protected final ArrayList<a> c(Context context) {
        d.c("WakeUpJiGuangSdkManager", "action - filter all DaemonService");
        if (context == null) {
            d.g("WakeUpJiGuangSdkManager", "filterAllDaemonService - context was null");
            return null;
        }
        ArrayList<a> arrayList = new ArrayList();
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent();
            intent.setAction(this.f);
            List queryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices == null || queryIntentServices.size() == 0) {
                return null;
            }
            for (int i = 0; i < queryIntentServices.size(); i++) {
                ServiceInfo serviceInfo = ((ResolveInfo) queryIntentServices.get(i)).serviceInfo;
                Object obj = serviceInfo.name;
                Object obj2 = serviceInfo.packageName;
                if (!(obj == null || obj2 == null || TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2) || !serviceInfo.exported || !serviceInfo.enabled || context.getPackageName().equals(obj2))) {
                    a a = a(context, packageManager, obj2, obj);
                    d.c("WakeUpJiGuangSdkManager", "To start Component: " + a);
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            d.d("WakeUpJiGuangSdkManager", "filterAllDaemonService error:" + th.getMessage());
        }
    }

    public final void c(String str) {
        this.f = str;
    }

    public final void d(Context context) {
        HashMap hashMap = new HashMap();
        String packageName = context.getPackageName();
        long j = this.c;
        hashMap.put("from_package", packageName);
        hashMap.put("from_uid", String.valueOf(j));
        hashMap.put("need_report", "true");
        List c = c(context);
        ArrayList arrayList = new ArrayList();
        int size = c.size();
        for (int i = 0; i < size; i++) {
            a aVar = (a) c.get(i);
            hashMap.put("awake_sequence", a(this.b, aVar.a));
            int i2 = 2;
            if (VERSION.SDK_INT < 26 || aVar.c < 26) {
                i2 = 3;
            }
            if (!TextUtils.isEmpty(aVar.d)) {
                i2 |= 4;
            }
            d a = c.a(context, 1, i2, aVar, hashMap);
            if (a != null) {
                arrayList.add(a);
            }
        }
        if (c.a(context)) {
            JSONObject a2 = a(packageName, arrayList);
            d.c("WakeUpJiGuangSdkManager", "report:" + a2);
            a(context, "android_awake", a2);
            return;
        }
        d.c("WakeUpJiGuangSdkManager", "Not need report wakeup!");
    }

    public final void d(String str) {
        this.h = str;
    }
}
