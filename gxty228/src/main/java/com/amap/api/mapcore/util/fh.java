package com.amap.api.mapcore.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.jiguang.net.HttpUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

/* compiled from: ServiceUtils */
public class fh {
    public static int a = -1;
    private static AssetManager b = null;
    private static Resources c = null;
    private static Resources d = null;
    private static boolean e = true;
    private static Context f;
    private static String g = "amap_resource";
    private static String h = "1_0_0";
    private static String i = ".png";
    private static String j = ".jar";
    private static String k = (g + h + j);
    private static String l = (g + h + i);
    private static String m = "";
    private static String n = (m + k);
    private static Theme o = null;
    private static Theme p = null;
    private static Field q = null;
    private static Field r = null;
    private static Activity s = null;

    /* compiled from: ServiceUtils */
    static class a implements FilenameFilter {
        a() {
        }

        public boolean accept(File file, String str) {
            return str.startsWith(fh.g) && !str.endsWith(fh.h + fh.j);
        }
    }

    public static boolean a(Context context) {
        try {
            f = context;
            File b = b(f);
            if (b != null) {
                m = b.getAbsolutePath() + HttpUtils.PATHS_SEPARATOR;
            }
            n = m + k;
            if (!e) {
                return true;
            }
            if (!c(context)) {
                return false;
            }
            b = a(n);
            c = a(context, b);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    private static File b(Context context) {
        File externalStorageDirectory;
        Exception exception;
        Throwable th;
        Throwable th2;
        File file = null;
        if (context != null) {
            try {
                if (Environment.getExternalStorageState().equals("mounted")) {
                    externalStorageDirectory = Environment.getExternalStorageDirectory();
                    try {
                        if (externalStorageDirectory.canWrite()) {
                            file = context.getExternalFilesDir("LBS");
                        } else {
                            file = context.getFilesDir();
                        }
                    } catch (Exception e) {
                        Exception exception2 = e;
                        file = externalStorageDirectory;
                        exception = exception2;
                        try {
                            exception.printStackTrace();
                            if (file != null && context != null) {
                                return context.getFilesDir();
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            externalStorageDirectory = file;
                            th2 = th;
                            if (externalStorageDirectory == null && context != null) {
                                context.getFilesDir();
                            }
                            throw th2;
                        }
                    } catch (Throwable th4) {
                        th2 = th4;
                        context.getFilesDir();
                        throw th2;
                    }
                }
                file = context.getFilesDir();
                if (file != null || context == null) {
                    return file;
                }
                context.getFilesDir();
                return file;
            } catch (Exception e2) {
                exception = e2;
                exception.printStackTrace();
                return file != null ? file : file;
            } catch (Throwable th32) {
                th = th32;
                externalStorageDirectory = file;
                th2 = th;
                context.getFilesDir();
                throw th2;
            }
        } else if (file != null || context == null) {
            return file;
        } else {
            context.getFilesDir();
            return file;
        }
    }

    public static Resources a() {
        if (c == null) {
            return f.getResources();
        }
        return c;
    }

    private static Resources a(Context context, AssetManager assetManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics.setToDefaults();
        return new Resources(assetManager, displayMetrics, context.getResources().getConfiguration());
    }

    private static AssetManager a(String str) {
        AssetManager assetManager;
        Throwable th;
        try {
            Class cls = Class.forName("android.content.res.AssetManager");
            assetManager = (AssetManager) cls.getConstructor((Class[]) null).newInstance((Object[]) null);
            try {
                cls.getDeclaredMethod("addAssetPath", new Class[]{String.class}).invoke(assetManager, new Object[]{str});
            } catch (Throwable th2) {
                th = th2;
                gz.c(th, "ResourcesUtil", "getAssetManager(String apkPath)");
                return assetManager;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            assetManager = null;
            th = th4;
            gz.c(th, "ResourcesUtil", "getAssetManager(String apkPath)");
            return assetManager;
        }
        return assetManager;
    }

    private static boolean c(Context context) {
        InputStream open;
        Throwable th;
        InputStream inputStream = null;
        boolean z = true;
        d(context);
        OutputStream outputStream = null;
        try {
            open = context.getResources().getAssets().open(l);
            try {
                if (b(open)) {
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable e) {
                            e.printStackTrace();
                            gz.c(e, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                        }
                    }
                    if (inputStream != null) {
                        outputStream.close();
                    }
                } else {
                    e();
                    OutputStream a = a(open);
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable e2) {
                            e2.printStackTrace();
                            gz.c(e2, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                        }
                    }
                    if (a != null) {
                        a.close();
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    th.printStackTrace();
                    gz.c(th, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                    z = false;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable e22) {
                            e22.printStackTrace();
                            gz.c(e22, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                        }
                    }
                    if (inputStream != null) {
                        outputStream.close();
                    }
                    return z;
                } catch (Throwable th3) {
                    th = th3;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable e222) {
                            e222.printStackTrace();
                            gz.c(e222, "ResourcesUtil", "copyResourceJarToAppFilesDir(Context ctx)");
                            throw th;
                        }
                    }
                    if (inputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            open = inputStream;
            if (open != null) {
                open.close();
            }
            if (inputStream != null) {
                outputStream.close();
            }
            throw th;
        }
        return z;
    }

    private static OutputStream a(InputStream inputStream) throws IOException {
        OutputStream fileOutputStream = new FileOutputStream(new File(m, k));
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                return fileOutputStream;
            }
            fileOutputStream.write(bArr, 0, read);
        }
    }

    private static boolean b(InputStream inputStream) throws IOException {
        File file = new File(n);
        long length = file.length();
        int available = inputStream.available();
        if (!file.exists() || length != ((long) available)) {
            return false;
        }
        inputStream.close();
        return true;
    }

    private static void e() {
        File[] listFiles = new File(m).listFiles(new a());
        if (listFiles != null && listFiles.length > 0) {
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                i = listFiles[i].delete() ? i + 1 : i + 1;
            }
        }
    }

    private static void d(Context context) {
        m = context.getFilesDir().getAbsolutePath();
        n = m + HttpUtils.PATHS_SEPARATOR + k;
    }

    public static View a(Context context, int i, ViewGroup viewGroup) {
        View view = null;
        Object xml = a().getXml(i);
        if (!e) {
            return LayoutInflater.from(context).inflate(xml, viewGroup);
        }
        try {
            view = LayoutInflater.from(new fg(context, a == -1 ? 0 : a, fh.class.getClassLoader())).inflate(xml, viewGroup);
            return view;
        } catch (Throwable th) {
            th.printStackTrace();
            gz.c(th, "ResourcesUtil", "selfInflate(Activity activity, int resource, ViewGroup root)");
            return view;
        } finally {
            xml.close();
        }
    }
}
