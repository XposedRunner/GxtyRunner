package com.autonavi.amap.mapcore.tools;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class GLFileUtil {
    public static void copy(Context context, String str, File file) throws Exception {
        file.delete();
        Closeable open = context.getAssets().open(str);
        byte[] bArr = new byte[open.available()];
        try {
            open.read(bArr);
            open = new FileOutputStream(file);
            try {
                open.write(bArr);
            } finally {
                closeQuietly(open);
            }
        } finally {
            closeQuietly(open);
        }
    }

    public static void deleteFile(File file) {
        if (file != null) {
            File[] listFiles = file.listFiles();
            if (file.isDirectory() && listFiles != null) {
                for (File deleteFile : listFiles) {
                    deleteFile(deleteFile);
                }
            }
            file.delete();
        }
    }

    public static void writeDatasToFile(String str, byte[] bArr) {
        Closeable fileOutputStream;
        Throwable th;
        WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        Closeable closeable = null;
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    File file = new File(str);
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(bArr);
                        fileOutputStream.flush();
                        writeLock.unlock();
                        closeQuietly(fileOutputStream);
                        return;
                    } catch (Exception e) {
                        closeable = fileOutputStream;
                        writeLock.unlock();
                        closeQuietly(closeable);
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        writeLock.unlock();
                        closeQuietly(fileOutputStream);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                writeLock.unlock();
                closeQuietly(closeable);
                return;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileOutputStream = null;
                th = th4;
                writeLock.unlock();
                closeQuietly(fileOutputStream);
                throw th;
            }
        }
        writeLock.unlock();
        closeQuietly(null);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static byte[] readFileContents(String str) {
        Throwable th;
        byte[] bArr = null;
        Closeable fileInputStream;
        try {
            File file = new File(str);
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr2 = new byte[1024];
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        int read = fileInputStream.read(bArr2);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    byteArrayOutputStream.close();
                    bArr = byteArrayOutputStream.toByteArray();
                    closeQuietly(fileInputStream);
                } catch (Exception e) {
                    closeQuietly(fileInputStream);
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    closeQuietly(fileInputStream);
                    throw th;
                }
            }
            closeQuietly(bArr);
        } catch (Exception e2) {
            fileInputStream = bArr;
            closeQuietly(fileInputStream);
            return bArr;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileInputStream = bArr;
            th = th4;
            closeQuietly(fileInputStream);
            throw th;
        }
        return bArr;
    }

    public static File getCacheDir(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            cacheDir = context.getDir("cache", 0);
        }
        if (cacheDir == null) {
            cacheDir = new File("/data/data/" + context.getPackageName() + "/app_cache");
        }
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    public static File getFilesDir(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            filesDir = context.getDir("files", 0);
        }
        if (filesDir == null) {
            filesDir = new File("/data/data/" + context.getPackageName() + "/app_files");
        }
        if (!filesDir.exists()) {
            filesDir.mkdirs();
        }
        return filesDir;
    }
}
