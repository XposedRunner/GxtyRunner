package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Locale;

/* compiled from: AppInfo */
public class fx {
    static String a = null;
    static boolean b = false;
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static String f = "";

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str.toCharArray();
        for (char c : str.toCharArray()) {
            if (('A' > c || c > 'z') && (('0' > c || c > ':') && c != '.')) {
                try {
                    gz.b(gl.a(), str, "errorPackage");
                    return false;
                } catch (Throwable th) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean a() {
        try {
            if (b) {
                return true;
            }
            if (a(a)) {
                b = true;
                return true;
            } else if (!TextUtils.isEmpty(a)) {
                b = false;
                a = null;
                return false;
            } else if (a(d)) {
                b = true;
                return true;
            } else if (TextUtils.isEmpty(d)) {
                return true;
            } else {
                b = false;
                d = null;
                return false;
            }
        } catch (Throwable th) {
            return true;
        }
    }

    public static String a(Context context) {
        try {
            return h(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return f;
        }
    }

    public static String b(Context context) {
        try {
            if (!"".equals(c)) {
                return c;
            }
            PackageManager packageManager = context.getPackageManager();
            c = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            return c;
        } catch (Throwable th) {
            gw.a(th, "AI", "gAN");
        }
    }

    public static String c(Context context) {
        try {
            if (d != null && !"".equals(d)) {
                return d;
            }
            d = context.getPackageName();
            if (!a(d)) {
                d = context.getPackageName();
            }
            return d;
        } catch (Throwable th) {
            gw.a(th, "AI", "gpck");
        }
    }

    public static String d(Context context) {
        try {
            if (!"".equals(e)) {
                return e;
            }
            e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (e == null) {
                return "";
            }
            return e;
        } catch (Throwable th) {
            gw.a(th, "AI", "gAV");
        }
    }

    public static String e(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            byte[] digest = MessageDigest.getInstance("SHA1").digest(packageInfo.signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String toUpperCase = Integer.toHexString(b & 255).toUpperCase(Locale.US);
                if (toUpperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(toUpperCase);
                stringBuffer.append(":");
            }
            String str = packageInfo.packageName;
            if (a(str)) {
                str = packageInfo.packageName;
            }
            if (!TextUtils.isEmpty(d)) {
                str = c(context);
            }
            stringBuffer.append(str);
            a = stringBuffer.toString();
            return a;
        } catch (Throwable th) {
            gw.a(th, "AI", "gsp");
            return a;
        }
    }

    static void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            f = str;
            if (context != null) {
                b(context, str);
            }
        }
    }

    public static String f(Context context) {
        try {
            return h(context);
        } catch (Throwable th) {
            gw.a(th, "AI", "gKy");
            return f;
        }
    }

    private static void b(final Context context, final String str) {
        gz.d().submit(new Runnable() {
            public void run() {
                Throwable th;
                FileOutputStream fileOutputStream = null;
                try {
                    File file = new File(gx.c(context, "k.store"));
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        fileOutputStream2.write(gl.a(str));
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    gw.a(th, "AI", "stf");
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
            }
        });
    }

    private static String g(Context context) {
        Throwable th;
        File file = new File(gx.c(context, "k.store"));
        if (!file.exists()) {
            return "";
        }
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                String a = gl.a(bArr);
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
                    gw.a(th, "AI", "gKe");
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

    private static String h(Context context) throws NameNotFoundException {
        if (f == null || f.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return f;
            }
            f = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
            if (f == null) {
                f = g(context);
            }
        }
        return f;
    }
}
