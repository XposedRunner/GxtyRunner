package cn.jiguang.d.d;

import android.content.Context;
import cn.jiguang.e.d;

public final class s {
    public static String a(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str.length() <= 30) {
                return str;
            }
            d.j("ServiceHelper", "The versionName is not valid, Please check your AndroidManifest.xml");
            return str.substring(0, 30);
        } catch (Throwable th) {
            return "Unknown";
        }
    }
}
