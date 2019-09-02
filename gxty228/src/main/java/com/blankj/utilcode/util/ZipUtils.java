package com.blankj.utilcode.util;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public final class ZipUtils {
    private static final int BUFFER_LEN = 8192;

    private ZipUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean zipFiles(Collection<String> collection, String str) throws IOException {
        return zipFiles((Collection) collection, str, null);
    }

    public static boolean zipFiles(Collection<String> collection, String str, String str2) throws IOException {
        Throwable th;
        ZipOutputStream zipOutputStream;
        if (collection == null || str == null) {
            return false;
        }
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(str));
            try {
                for (String fileByPath : collection) {
                    if (!zipFile(getFileByPath(fileByPath), "", zipOutputStream2, str2)) {
                        if (zipOutputStream2 != null) {
                            zipOutputStream2.finish();
                            zipOutputStream2.close();
                        }
                        return false;
                    }
                }
                if (zipOutputStream2 == null) {
                    return true;
                }
                zipOutputStream2.finish();
                zipOutputStream2.close();
                return true;
            } catch (Throwable th2) {
                th = th2;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            zipOutputStream = null;
            if (zipOutputStream != null) {
                zipOutputStream.finish();
                zipOutputStream.close();
            }
            throw th;
        }
    }

    public static boolean zipFiles(Collection<File> collection, File file) throws IOException {
        return zipFiles((Collection) collection, file, null);
    }

    public static boolean zipFiles(Collection<File> collection, File file, String str) throws IOException {
        Throwable th;
        ZipOutputStream zipOutputStream;
        if (collection == null || file == null) {
            return false;
        }
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(file));
            try {
                for (File zipFile : collection) {
                    if (!zipFile(zipFile, "", zipOutputStream2, str)) {
                        if (zipOutputStream2 != null) {
                            zipOutputStream2.finish();
                            zipOutputStream2.close();
                        }
                        return false;
                    }
                }
                if (zipOutputStream2 == null) {
                    return true;
                }
                zipOutputStream2.finish();
                zipOutputStream2.close();
                return true;
            } catch (Throwable th2) {
                th = th2;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            zipOutputStream = null;
            if (zipOutputStream != null) {
                zipOutputStream.finish();
                zipOutputStream.close();
            }
            throw th;
        }
    }

    public static boolean zipFile(String str, String str2) throws IOException {
        return zipFile(getFileByPath(str), getFileByPath(str2), null);
    }

    public static boolean zipFile(String str, String str2, String str3) throws IOException {
        return zipFile(getFileByPath(str), getFileByPath(str2), str3);
    }

    public static boolean zipFile(File file, File file2) throws IOException {
        return zipFile(file, file2, null);
    }

    public static boolean zipFile(File file, File file2, String str) throws IOException {
        ZipOutputStream zipOutputStream;
        Throwable th;
        if (file == null || file2 == null) {
            return false;
        }
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
            try {
                boolean zipFile = zipFile(file, "", zipOutputStream, str);
                if (zipOutputStream == null) {
                    return zipFile;
                }
                zipOutputStream.close();
                return zipFile;
            } catch (Throwable th2) {
                th = th2;
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            zipOutputStream = null;
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            throw th;
        }
    }

    private static boolean zipFile(File file, String str, ZipOutputStream zipOutputStream, String str2) throws IOException {
        Throwable th;
        String str3 = str + (isSpace(str) ? "" : File.separator) + file.getName();
        ZipEntry zipEntry;
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                zipEntry = new ZipEntry(str3 + '/');
                zipEntry.setComment(str2);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.closeEntry();
            } else {
                for (File zipFile : listFiles) {
                    if (!zipFile(zipFile, str3, zipOutputStream, str2)) {
                        return false;
                    }
                }
            }
        } else {
            InputStream bufferedInputStream;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    zipEntry = new ZipEntry(str3);
                    zipEntry.setComment(str2);
                    zipOutputStream.putNextEntry(zipEntry);
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = bufferedInputStream.read(bArr, 0, 8192);
                        if (read == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                    zipOutputStream.closeEntry();
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        }
        return true;
    }

    public static List<File> unzipFile(String str, String str2) throws IOException {
        return unzipFileByKeyword(str, str2, null);
    }

    public static List<File> unzipFile(File file, File file2) throws IOException {
        return unzipFileByKeyword(file, file2, null);
    }

    public static List<File> unzipFileByKeyword(String str, String str2, String str3) throws IOException {
        return unzipFileByKeyword(getFileByPath(str), getFileByPath(str2), str3);
    }

    public static List<File> unzipFileByKeyword(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return null;
        }
        List<File> arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration entries = zipFile.entries();
        ZipEntry zipEntry;
        String name;
        if (isSpace(str)) {
            while (entries.hasMoreElements()) {
                zipEntry = (ZipEntry) entries.nextElement();
                name = zipEntry.getName();
                if (name.contains("../")) {
                    Log.e("ZipUtils", "it's dangerous!");
                    return arrayList;
                } else if (!unzipChildFile(file2, arrayList, zipFile, zipEntry, name)) {
                    return arrayList;
                }
            }
        }
        while (entries.hasMoreElements()) {
            zipEntry = (ZipEntry) entries.nextElement();
            name = zipEntry.getName();
            if (name.contains("../")) {
                Log.e("ZipUtils", "it's dangerous!");
                return arrayList;
            } else if (name.contains(str) && !unzipChildFile(file2, arrayList, zipFile, zipEntry, name)) {
                return arrayList;
            }
        }
        return arrayList;
    }

    private static boolean unzipChildFile(File file, List<File> list, ZipFile zipFile, ZipEntry zipEntry, String str) throws IOException {
        OutputStream bufferedOutputStream;
        Throwable th;
        InputStream inputStream = null;
        File file2 = new File(file + File.separator + str);
        list.add(file2);
        if (zipEntry.isDirectory()) {
            if (!createOrExistsDir(file2)) {
                return false;
            }
        } else if (!createOrExistsFile(file2)) {
            return false;
        } else {
            try {
                InputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                    try {
                        byte[] bArr = new byte[8192];
                        while (true) {
                            int read = bufferedInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                        }
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = bufferedInputStream;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream = null;
                    inputStream = bufferedInputStream;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        }
        return true;
    }

    public static List<String> getFilesPath(String str) throws IOException {
        return getFilesPath(getFileByPath(str));
    }

    public static List<String> getFilesPath(File file) throws IOException {
        if (file == null) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        Enumeration entries = new ZipFile(file).entries();
        while (entries.hasMoreElements()) {
            arrayList.add(((ZipEntry) entries.nextElement()).getName());
        }
        return arrayList;
    }

    public static List<String> getComments(String str) throws IOException {
        return getComments(getFileByPath(str));
    }

    public static List<String> getComments(File file) throws IOException {
        if (file == null) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        Enumeration entries = new ZipFile(file).entries();
        while (entries.hasMoreElements()) {
            arrayList.add(((ZipEntry) entries.nextElement()).getComment());
        }
        return arrayList;
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean createOrExistsFile(File file) {
        boolean z = false;
        if (file == null) {
            return z;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return z;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return z;
        }
    }

    private static File getFileByPath(String str) {
        return isSpace(str) ? null : new File(str);
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
