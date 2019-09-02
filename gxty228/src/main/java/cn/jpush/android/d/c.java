package cn.jpush.android.d;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public final class c {
    public static final String a = (File.separator + "rich" + File.separator);

    private static String d(Context context, String str) {
        String str2 = context.getFilesDir() + a + str;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2 + HttpUtils.PATHS_SEPARATOR;
    }

    public static String a(Context context, String str) {
        String str2 = context.getFilesDir() + HttpUtils.PATHS_SEPARATOR + str;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2 + HttpUtils.PATHS_SEPARATOR;
    }

    public static String b(Context context, String str) {
        try {
            File file;
            if (a.a()) {
                String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getPackageName() + File.separator + str + File.separator;
                file = new File(str2);
                if (file.exists()) {
                    return str2;
                }
                file.mkdirs();
                return str2;
            }
            file = new File(context.getFilesDir() + a);
            if (file.exists() && file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 10) {
                    Arrays.sort(listFiles, new Comparator<File>() {
                        public final /* synthetic */ int compare(Object obj, Object obj2) {
                            File file = (File) obj;
                            File file2 = (File) obj2;
                            if (file.lastModified() > file2.lastModified()) {
                                return -1;
                            }
                            if (file.lastModified() < file2.lastModified()) {
                                return 1;
                            }
                            return 0;
                        }
                    });
                    a(listFiles[listFiles.length - 1]);
                }
            }
            return d(context, str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static boolean a(File file) {
        boolean z = false;
        try {
            if (!file.exists()) {
                return z;
            }
            if (file.isFile()) {
                return file.delete();
            }
            String[] list = file.list();
            if (list != null) {
                int length = list.length;
                for (int i = z; i < length; i++) {
                    File file2 = new File(file, list[i]);
                    if (file2.isDirectory()) {
                        a(file2);
                    } else {
                        file2.delete();
                    }
                }
            }
            return file.delete();
        } catch (Exception e) {
            e.i("DirectoryUtils", "Delete dir error");
            return z;
        }
    }

    public static String c(Context context, String str) {
        try {
            if (a.a()) {
                String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getPackageName() + File.separator + str;
                if (new File(str2).exists()) {
                    return str2;
                }
                e.h("DirectoryUtils", "Can't find developer picture resource in SDCard.");
                return "";
            }
            e.h("DirectoryUtils", "No SDCard found.");
            return "";
        } catch (Exception e) {
            e.h("DirectoryUtils", "Get developer picture resource failed.");
            e.printStackTrace();
            return "";
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.substring(str.lastIndexOf(HttpUtils.PATHS_SEPARATOR) + 1, str.length());
    }

    public static boolean a(String str, String str2, Context context) {
        Throwable th;
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        e.a("DirectoryUtils", "action:createHtmlFile - filePath:" + str + ", content:" + str2);
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(str2.getBytes("UTF-8"));
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        return true;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                e.a("DirectoryUtils", "", th4);
            }
        }
        return false;
    }

    public static boolean a(String str, byte[] bArr) throws IOException {
        Throwable th;
        if (TextUtils.isEmpty(str) || bArr.length <= 0) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }
}
