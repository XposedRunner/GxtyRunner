package com.amap.api.services.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;
import java.io.FileInputStream;

/* compiled from: AppInfo */
public class a {
    static String a = null;
    static boolean b = false;
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static String f = "";

    public static String a(Context context) {
        try {
            return c(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return f;
        }
    }

    private static String b(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        File file = new File(o.a(context, "k.store"));
        if (!file.exists()) {
            return "";
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                String a = f.a(bArr);
                if (a.length() != 32) {
                    a = "";
                }
                if (fileInputStream == null) {
                    return a;
                }
                try {
                    fileInputStream.close();
                    return a;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    return a;
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    n.a(th, "AI", "gKe");
                    if (file != null) {
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th22) {
                            th22.printStackTrace();
                        }
                    }
                    throw th;
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
                return "";
            }
        } catch (Throwable th6) {
            th5 = th6;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th5;
        }
    }

    private static String c(Context context) throws NameNotFoundException {
        if (f == null || f.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return f;
            }
            f = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
            if (f == null) {
                f = b(context);
            }
        }
        return f;
    }
}
