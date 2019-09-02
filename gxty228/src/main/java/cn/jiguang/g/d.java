package cn.jiguang.g;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.jiguang.a.a.c.h;
import cn.jiguang.a.a.c.k;
import cn.jiguang.d.d.o;
import com.qq.e.comm.constants.Constants.KEYS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class d {
    public static int a(ApplicationInfo applicationInfo) {
        Object obj = null;
        if (applicationInfo == null) {
            return -1;
        }
        try {
            if (((applicationInfo.flags & 1) != 0 ? 1 : null) != null) {
                if ((applicationInfo.flags & 128) != 0) {
                    obj = 1;
                }
                return obj != null ? 2 : 1;
            } else {
                Object obj2 = applicationInfo.sourceDir;
                return !TextUtils.isEmpty(obj2) ? obj2.startsWith("/system/") ? 3 : !applicationInfo.sourceDir.contains(applicationInfo.packageName) ? 3 : 0 : -1;
            }
        } catch (Throwable th) {
            cn.jiguang.e.d.a("AppStatUtils", "getInstallType error:" + th.getMessage());
            return -1;
        }
    }

    public static ApplicationInfo a(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(str, 0);
        } catch (Throwable th) {
            return null;
        }
    }

    private static List<e> a(int i) {
        List<String> a = a.a(new String[]{KEYS.PLACEMENTS});
        List<e> arrayList = new ArrayList();
        if (a == null || a.isEmpty()) {
            return arrayList;
        }
        HashSet hashSet = new HashSet();
        Map a2 = e.a((String) a.remove(0));
        for (String a3 : a) {
            e a4 = e.a(a3, a2);
            if (a4 != null) {
                if (i == 3 || !a4.a()) {
                    if (!KEYS.PLACEMENTS.equals(a4.a)) {
                        arrayList.add(a4);
                    }
                } else if (a4.b()) {
                    hashSet.add(a4.c);
                }
            }
        }
        if (i != 1 || hashSet.isEmpty()) {
            return arrayList;
        }
        List<e> arrayList2 = new ArrayList();
        int i2 = 0;
        while (i2 == 0) {
            Iterator it = arrayList.iterator();
            i2 = 1;
            while (it.hasNext()) {
                int i3;
                a4 = (e) it.next();
                if (hashSet.contains(a4.d)) {
                    arrayList2.add(a4);
                    hashSet.add(a4.c);
                    it.remove();
                    i3 = 0;
                } else {
                    i3 = i2;
                }
                i2 = i3;
            }
        }
        return arrayList2;
    }

    private static List<h> a(Context context) {
        Map hashMap = new HashMap();
        for (e eVar : a(1)) {
            h b = b(context, eVar.a);
            if (b != null) {
                hashMap.put(b.b, b);
            }
        }
        return new ArrayList(hashMap.values());
    }

    private static JSONArray a(List<h> list) {
        JSONArray jSONArray = new JSONArray();
        for (h hVar : list) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", hVar.a);
                jSONObject.put("pkg", k.a(hVar.b, 128));
                jSONObject.put("ver_name", hVar.c);
                jSONObject.put("ver_code", hVar.d);
                jSONObject.put("install_type", hVar.e);
                jSONArray.put(jSONObject);
            } catch (Throwable th) {
                cn.jiguang.e.d.e("AppStatUtils", "put App JSON Error:" + th.getMessage());
            }
        }
        return jSONArray;
    }

    public static void a(Context context, int i, int i2) {
        JSONArray jSONArray;
        JSONArray a;
        if (i2 > 0) {
            try {
                List<e> a2 = a(i2);
                jSONArray = new JSONArray();
                for (e a3 : a2) {
                    JSONObject a4 = a3.a(128);
                    if (a4 != null) {
                        jSONArray.put(a4);
                    }
                }
            } catch (Throwable th) {
                cn.jiguang.e.d.e("AppStatUtils", "report JSON Error : " + th.getMessage());
                return;
            }
        }
        jSONArray = null;
        if (i > 0) {
            a = a(VERSION.SDK_INT < 21 ? b(context) : a(context));
        } else {
            a = null;
        }
        JSONObject jSONObject = new JSONObject();
        if (a != null && a.length() > 0) {
            jSONObject.put("app", a);
        }
        if (jSONArray != null && jSONArray.length() > 0) {
            jSONObject.put("process", jSONArray);
        }
        if (jSONObject.length() > 0) {
            o.b(context, jSONObject, "app_running");
            o.a(context, jSONObject);
        }
    }

    private static h b(Context context, String str) {
        try {
            PackageInfo c = c(context, str);
            if (c == null) {
                return null;
            }
            String charSequence = c.applicationInfo.loadLabel(context.getPackageManager()).toString();
            h hVar = new h();
            hVar.a = k.a(charSequence, 30);
            hVar.b = c.packageName;
            hVar.d = c.versionCode;
            hVar.c = c.versionName;
            hVar.e = a(c.applicationInfo);
            return hVar;
        } catch (Throwable th) {
            cn.jiguang.e.d.e("AppStatUtils", "getPackageInfo error : " + th.getMessage());
            return null;
        }
    }

    private static List<h> b(Context context) {
        List<h> arrayList = new ArrayList();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        Object hashSet = new HashSet();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            String[] strArr = runningAppProcessInfo.pkgList;
            if (strArr != null && strArr.length > 0) {
                Collections.addAll(hashSet, strArr);
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            h b = b(context, (String) it.next());
            if (b != null) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    private static PackageInfo c(Context context, String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (Throwable th) {
        }
        return packageInfo;
    }
}
