package com.example.gita.gxty.utils.a;

import android.os.Build;
import com.example.gita.gxty.utils.h;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/* compiled from: CheckRoot */
public class b {
    public static boolean a() {
        if (b() || c() || d() || e() || f()) {
            return true;
        }
        return false;
    }

    public static boolean b() {
        String str = Build.TAGS;
        if (str == null || !str.contains("test-keys")) {
            return false;
        }
        h.b("buildTags=" + str);
        return true;
    }

    public static boolean c() {
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                h.b("/system/app/Superuser.apk exist");
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean d() {
        String[] strArr = new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < strArr.length) {
            try {
                File file = new File(strArr[i] + "su");
                if (file == null || !file.exists()) {
                    i++;
                } else {
                    h.b("find su in : " + strArr[i]);
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean e() {
        ArrayList a = a(new String[]{"/system/xbin/which", "su"});
        if (a != null) {
            h.b("execResult=" + a.toString());
            return true;
        }
        h.b("execResult=null");
        return false;
    }

    public static ArrayList<String> a(String[] strArr) {
        ArrayList<String> arrayList = new ArrayList();
        try {
            h.b("to shell exec which for find su :");
            Process exec = Runtime.getRuntime().exec(strArr);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(exec.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    h.b("–> Line received: " + readLine);
                    arrayList.add(readLine);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            h.b("–> Full response was: " + arrayList);
            return arrayList;
        } catch (Exception e2) {
            return null;
        }
    }

    public static synchronized boolean f() {
        boolean z = false;
        synchronized (b.class) {
            try {
                h.b("to write /data");
                String str = "test_ok";
                if (a("/data/su_test", str).booleanValue()) {
                    h.b("write ok");
                } else {
                    h.b("write failed");
                }
                h.b("to read /data");
                String a = a("/data/su_test");
                h.b("strRead=" + a);
                if (str.equals(a)) {
                    z = true;
                }
            } catch (Exception e) {
                h.b("Unexpected error - Here is what I know: " + e.getMessage());
            }
        }
        return z;
    }

    public static Boolean a(String str, String str2) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(str2.getBytes());
            fileOutputStream.close();
            return Boolean.valueOf(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    public static String a(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    String str2 = new String(byteArrayOutputStream.toByteArray());
                    h.b(str2);
                    return str2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
