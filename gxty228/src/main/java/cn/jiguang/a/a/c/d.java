package cn.jiguang.a.a.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class d {
    public static ArrayList<h> a(Context context, boolean z) {
        ArrayList<h> arrayList = new ArrayList();
        try {
            List installedPackages = context.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < installedPackages.size(); i++) {
                try {
                    PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                    h hVar = new h();
                    hVar.a = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
                    hVar.b = packageInfo.packageName;
                    hVar.c = packageInfo.versionName;
                    hVar.d = packageInfo.versionCode;
                    hVar.e = cn.jiguang.g.d.a(packageInfo.applicationInfo);
                    arrayList.add(hVar);
                } catch (IndexOutOfBoundsException e) {
                    cn.jiguang.e.d.c("InstalledApplications", "getInstalledPackages IndexOutOfBoundsException errno");
                } catch (Throwable th) {
                    cn.jiguang.e.d.c("InstalledApplications", "get app info error:" + th.getMessage());
                }
            }
        } catch (IndexOutOfBoundsException e2) {
            cn.jiguang.e.d.c("InstalledApplications", "getInstalledPackages IndexOutOfBoundsException errno");
        } catch (Throwable th2) {
            cn.jiguang.e.d.c("InstalledApplications", "getInstalledPackages errno");
        }
        return arrayList;
    }

    public static String[] a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
        } catch (Throwable th) {
            cn.jiguang.e.d.e("InstalledApplications", "", th);
            return null;
        }
    }

    public static JSONArray b(Context context) {
        ArrayList a = a(context, true);
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                h hVar = (h) it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", hVar.a);
                jSONObject.put("pkg", hVar.b);
                jSONObject.put("ver_name", hVar.c);
                jSONObject.put("ver_code", hVar.d);
                jSONObject.put("install_type", hVar.e);
                jSONArray.put(jSONObject);
            }
        } catch (Throwable th) {
        }
        return jSONArray;
    }
}
