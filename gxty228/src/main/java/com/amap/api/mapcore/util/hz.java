package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.NativeConfigInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* compiled from: FileProvider */
public class hz {
    private Context a;
    private String b;

    public hz(Context context) {
        this.a = context;
        if (TextUtils.isEmpty(this.b)) {
            this.b = "d";
        }
    }

    private String c() {
        if (this.a == null) {
            return "";
        }
        return this.a.getFilesDir().getAbsolutePath();
    }

    public final String a(String str) {
        if (this.a == null || TextUtils.isEmpty(this.b)) {
            return "";
        }
        return c() + File.separator + this.b + File.separator + gf.b(str) + File.separator + "i";
    }

    public final String b(String str) {
        if (this.a == null) {
            return "";
        }
        return a(str) + File.separator + d();
    }

    private String d() {
        if (this.a == null) {
            return "";
        }
        return b(this.a, "");
    }

    public static String a(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            str = "emptyfilename";
        }
        return b(context, str);
    }

    public final String a() {
        if (this.a == null || TextUtils.isEmpty(this.b)) {
            return "";
        }
        return c() + File.separator + this.b + File.separator + "j";
    }

    public final String b() {
        if (this.a == null || TextUtils.isEmpty(this.b)) {
            return "";
        }
        return (c() + File.separator + this.b) + File.separator + File.separator + "m";
    }

    public final String c(String str) {
        if (this.a == null || TextUtils.isEmpty(this.b)) {
            return "";
        }
        return b() + File.separator + d();
    }

    public static void d(String str) {
        if (!TextUtils.isEmpty(str)) {
        }
    }

    public static void a(Context context, List<String> list) {
        if (context != null) {
            String a = gx.a(context);
            if (!TextUtils.isEmpty(a)) {
                Object obj;
                if (list == null || list.size() == 0) {
                    obj = "";
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < list.size(); i++) {
                        if (!TextUtils.isEmpty((CharSequence) list.get(i))) {
                            String str = (String) list.get(i);
                            if (TextUtils.isEmpty(str)) {
                                str = "";
                            } else {
                                if (!str.startsWith("lib")) {
                                    str = "lib" + str;
                                }
                                if (!str.endsWith(".so")) {
                                    str = str + ".so";
                                }
                            }
                            stringBuffer.append(str);
                            stringBuffer.append(",");
                            stringBuffer.append(a(context, str));
                            if (i < list.size() - 1) {
                                stringBuffer.append(",");
                            }
                        }
                    }
                    obj = stringBuffer.toString();
                }
                if (!TextUtils.isEmpty(obj)) {
                    File file = new File(a);
                    if (!(file.isDirectory() && file.exists())) {
                        if (file.isFile() && file.exists()) {
                            file.delete();
                        }
                        file.mkdirs();
                    }
                    NativeConfigInfo.nativeInit(a, "defconfig", obj);
                }
            }
        }
    }

    public static void e(String str) {
        File file = new File(str);
        if (file.exists()) {
            a(file);
        }
    }

    private static void a(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int i = 0; i < listFiles.length; i++) {
                    if (listFiles[i].isDirectory()) {
                        a(listFiles[i]);
                    } else {
                        listFiles[i].delete();
                    }
                }
                return;
            }
            return;
        }
        file.delete();
    }

    public static void f(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void h(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (!file.exists() || !file.isDirectory()) {
                if (!file.exists()) {
                    file.mkdirs();
                } else if (file.exists() && !file.isDirectory()) {
                    file.delete();
                    file.mkdirs();
                }
            }
        }
    }

    private static void b(File file) throws IOException {
        if (!file.exists() || !file.isFile()) {
            if (!file.exists()) {
                file.createNewFile();
            } else if (file.exists() && !file.isFile()) {
                file.delete();
                file.createNewFile();
            }
        }
    }

    private static void i(String str) throws IOException {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            h(file.getParentFile().getAbsolutePath());
            b(file);
        }
    }

    public static void a(String str, String str2) {
        InputStream fileInputStream;
        InputStream inputStream;
        Throwable th;
        OutputStream outputStream = null;
        if (g(str)) {
            OutputStream fileOutputStream;
            try {
                File file = new File(str);
                File file2 = new File(str2);
                i(file2.getAbsolutePath());
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        fileInputStream.close();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.flush();
                            try {
                                fileInputStream.close();
                                fileOutputStream.close();
                                return;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    outputStream = fileOutputStream;
                    th = th4;
                    fileInputStream.close();
                    outputStream.close();
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                fileInputStream = null;
                fileInputStream.close();
                outputStream.close();
                throw th;
            }
        }
    }

    public static void b(String str, String str2) {
        InputStream fileInputStream;
        InputStream inputStream;
        Throwable th;
        OutputStream outputStream = null;
        if (g(str)) {
            OutputStream fileOutputStream;
            try {
                File file = new File(str);
                File file2 = new File(str2 + File.separator + file.getName());
                h(str2);
                i(file2.getAbsolutePath());
                fileInputStream = new FileInputStream(file);
                try {
                    fileInputStream.read(new byte[32]);
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        fileInputStream.close();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.flush();
                            try {
                                fileInputStream.close();
                                fileOutputStream.close();
                                return;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    outputStream = fileOutputStream;
                    th = th4;
                    fileInputStream.close();
                    outputStream.close();
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                fileInputStream = null;
                fileInputStream.close();
                outputStream.close();
                throw th;
            }
        }
    }

    public static void a(Context context, String str, String str2) {
        if (c(new File(str))) {
            byte[] bArr = new byte[1024];
            try {
                InputStream fileInputStream = new FileInputStream(str);
                ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
                for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
                    Object obj;
                    String name = nextEntry.getName();
                    if (name == null || !name.endsWith(".so")) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj != null) {
                        File file = new File(str2 + File.separator + a(context, name));
                        b(file);
                        new File(file.getParent()).mkdirs();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        while (true) {
                            int read = zipInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.close();
                    }
                    zipInputStream.closeEntry();
                }
                zipInputStream.closeEntry();
                zipInputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean g(String str) {
        return c(new File(str));
    }

    private static boolean c(File file) {
        return file.exists();
    }

    public static boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str.equals(gf.a(str2));
    }

    private static String b(Context context, String str) {
        if (context == null) {
            return gf.b(str);
        }
        return gf.b(gd.u(context) + str);
    }
}
