package cn.jiguang.d.g;

import cn.jiguang.e.d;
import cn.jiguang.g.g;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public final class c {
    public static File a(List<File> list) {
        Comparator dVar = new d();
        Object obj = null;
        for (Object next : list) {
            if (obj == null) {
                obj = next;
            } else if (dVar.compare(next, obj) < 0) {
                obj = next;
            }
        }
        return (File) obj;
    }

    public static void a(File file) {
        if (file != null && file.exists()) {
            file.delete();
            d.c("FileUtils", "delete File:" + file.getPath());
        }
    }

    public static void a(File file, String str) {
        Exception e;
        Throwable th;
        String str2 = null;
        if (str != null) {
            try {
                byte[] bytes = str.getBytes("UTF-8");
                Closeable fileOutputStream;
                try {
                    file = c(file);
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(bytes);
                        g.a(fileOutputStream);
                    } catch (Exception e2) {
                        e = e2;
                        if (file != null) {
                            try {
                                str2 = file.getAbsolutePath();
                            } catch (Throwable th2) {
                                th = th2;
                                g.a(fileOutputStream);
                                throw th;
                            }
                        }
                        d.g("FileUtils", "save to file exception:" + e.getMessage() + " path = " + str2);
                        g.a(fileOutputStream);
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = null;
                    if (file != null) {
                        str2 = file.getAbsolutePath();
                    }
                    d.g("FileUtils", "save to file exception:" + e.getMessage() + " path = " + str2);
                    g.a(fileOutputStream);
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    g.a(fileOutputStream);
                    throw th;
                }
            } catch (Exception e4) {
                d.g("FileUtils", "getBytes exception:" + e4.getMessage());
            }
        }
    }

    public static void a(List<File> list, File file, FileFilter fileFilter) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            if (fileFilter == null || fileFilter.accept(file)) {
                list.add(file);
            }
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a : listFiles) {
                    a(list, a, fileFilter);
                }
            }
        }
    }

    public static void a(AtomicLong atomicLong, File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            atomicLong.addAndGet(file.length());
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a : listFiles) {
                    a(atomicLong, a);
                }
            }
        }
    }

    public static String b(File file) {
        byte[] d = d(file);
        if (d == null) {
            return null;
        }
        try {
            return new String(d, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            d.c("FileUtils", "can't encoding, give up read :" + e.getMessage());
            return null;
        }
    }

    private static File c(File file) {
        if (!(file == null || file.exists())) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (Exception e) {
            }
        }
        return file;
    }

    private static byte[] d(File file) {
        try {
            return g.a(new FileInputStream(file));
        } catch (Exception e) {
            d.c("FileUtils", "can't read, give up read :" + e.getMessage());
            return null;
        }
    }
}
