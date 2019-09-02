package cn.jiguang.a.a.c;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import cn.jiguang.d.d.o;
import cn.jiguang.e.d;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class k {
    private static final String a = k.class.getSimpleName();

    public static String a(String str, int i) {
        if (str == null) {
            return str;
        }
        String replaceAll = Pattern.compile("\n|\r|\r\n|\n\r|\t").matcher(str).replaceAll("");
        try {
            byte[] bytes = replaceAll.getBytes();
            return bytes.length > 30 ? replaceAll.substring(0, new String(bytes, 0, 30, "UTF-8").length()) : replaceAll;
        } catch (UnsupportedEncodingException e) {
            d.i(a, e.getMessage());
            return replaceAll;
        }
    }

    private static Set<String> a(ActivityManager activityManager) {
        Object hashSet = new HashSet();
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            Collections.addAll(hashSet, runningAppProcessInfo.pkgList);
        }
        return hashSet;
    }

    private static JSONArray a(ActivityManager activityManager, PackageManager packageManager) {
        JSONArray jSONArray = new JSONArray();
        Set a = a(activityManager);
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(8192);
        List<RunningServiceInfo> runningServices = activityManager.getRunningServices(200);
        Collections.sort(installedApplications, new DisplayNameComparator(packageManager));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        for (ApplicationInfo applicationInfo : installedApplications) {
            String a2 = a(applicationInfo.loadLabel(packageManager).toString(), 30);
            if (a.contains(applicationInfo.packageName)) {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                for (RunningServiceInfo runningServiceInfo : runningServices) {
                    if (runningServiceInfo.service.getPackageName().equals(applicationInfo.packageName)) {
                        JSONObject jSONObject2 = new JSONObject();
                        long round = (long) Math.round((float) ((elapsedRealtime - runningServiceInfo.activeSince) / 1000));
                        try {
                            jSONObject2.put("class_name", runningServiceInfo.service.getShortClassName());
                            jSONObject2.put("live_seconds", round);
                            jSONArray2.put(jSONObject2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Throwable th) {
                            d.d(a, "getRunningApps error:" + th.getMessage());
                        }
                    }
                }
                try {
                    jSONObject.put("app_name", a2);
                    jSONObject.put("pkg_name", applicationInfo.packageName);
                    jSONObject.put("service_list", jSONArray2);
                    jSONArray.put(jSONObject);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    public static void a(Context context) {
        d.c(a, "action:reportRunningInfo");
        JSONArray a = a((ActivityManager) context.getSystemService("activity"), context.getPackageManager());
        if (a != null && a.length() != 0) {
            o.a(context, a);
        }
    }
}
