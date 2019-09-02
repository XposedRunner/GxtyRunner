package com.blankj.utilcode.util;

import android.support.v4.media.session.PlaybackStateCompat;
import com.blankj.utilcode.constant.RegexConstants;
import com.lzy.okgo.model.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class FileUtils {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String LINE_SEP = System.getProperty("line.separator");

    public interface OnReplaceListener {
        boolean onReplace();
    }

    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static File getFileByPath(String str) {
        return isSpace(str) ? null : new File(str);
    }

    public static boolean isFileExists(String str) {
        return isFileExists(getFileByPath(str));
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static boolean rename(String str, String str2) {
        return rename(getFileByPath(str), str2);
    }

    public static boolean rename(File file, String str) {
        boolean z = true;
        if (file == null || !file.exists() || isSpace(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        if (file2.exists() || !file.renameTo(file2)) {
            z = false;
        }
        return z;
    }

    public static boolean isDir(String str) {
        return isDir(getFileByPath(str));
    }

    public static boolean isDir(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static boolean isFile(String str) {
        return isFile(getFileByPath(str));
    }

    public static boolean isFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean createOrExistsDir(String str) {
        return createOrExistsDir(getFileByPath(str));
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    public static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    public static boolean createOrExistsFile(File file) {
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

    public static boolean createFileByDeleteOldFile(String str) {
        return createFileByDeleteOldFile(getFileByPath(str));
    }

    public static boolean createFileByDeleteOldFile(File file) {
        boolean z = false;
        if (file != null && ((!file.exists() || file.delete()) && createOrExistsDir(file.getParentFile()))) {
            try {
                z = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    public static boolean copyDir(String str, String str2) {
        return copyDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean copyDir(String str, String str2, OnReplaceListener onReplaceListener) {
        return copyDir(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean copyDir(File file, File file2) {
        return copyOrMoveDir(file, file2, false);
    }

    public static boolean copyDir(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveDir(file, file2, onReplaceListener, false);
    }

    public static boolean copyFile(String str, String str2) {
        return copyFile(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean copyFile(String str, String str2, OnReplaceListener onReplaceListener) {
        return copyFile(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean copyFile(File file, File file2) {
        return copyOrMoveFile(file, file2, false);
    }

    public static boolean copyFile(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveFile(file, file2, onReplaceListener, false);
    }

    public static boolean moveDir(String str, String str2) {
        return moveDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean moveDir(String str, String str2, OnReplaceListener onReplaceListener) {
        return moveDir(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean moveDir(File file, File file2) {
        return copyOrMoveDir(file, file2, true);
    }

    public static boolean moveDir(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveDir(file, file2, onReplaceListener, true);
    }

    public static boolean moveFile(String str, String str2) {
        return moveFile(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean moveFile(String str, String str2, OnReplaceListener onReplaceListener) {
        return moveFile(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean moveFile(File file, File file2) {
        return copyOrMoveFile(file, file2, true);
    }

    public static boolean moveFile(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveFile(file, file2, onReplaceListener, true);
    }

    private static boolean copyOrMoveDir(File file, File file2, boolean z) {
        return copyOrMoveDir(file, file2, new OnReplaceListener() {
            public boolean onReplace() {
                return true;
            }
        }, z);
    }

    private static boolean copyOrMoveDir(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        if (file == null || file2 == null) {
            return false;
        }
        String str = file2.getPath() + File.separator;
        if (str.contains(file.getPath() + File.separator) || !file.exists() || !file.isDirectory()) {
            return false;
        }
        if (file2.exists()) {
            if (onReplaceListener != null && !onReplaceListener.onReplace()) {
                return true;
            }
            if (!deleteAllInDir(file2)) {
                return false;
            }
        }
        if (!createOrExistsDir(file2)) {
            return false;
        }
        for (File file3 : file.listFiles()) {
            File file4 = new File(str + file3.getName());
            if (file3.isFile()) {
                if (!copyOrMoveFile(file3, file4, onReplaceListener, z)) {
                    return false;
                }
            } else if (file3.isDirectory() && !copyOrMoveDir(file3, file4, onReplaceListener, z)) {
                return false;
            }
        }
        if (!z || deleteDir(file)) {
            return true;
        }
        return false;
    }

    private static boolean copyOrMoveFile(File file, File file2, boolean z) {
        return copyOrMoveFile(file, file2, new OnReplaceListener() {
            public boolean onReplace() {
                return true;
            }
        }, z);
    }

    private static boolean copyOrMoveFile(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        boolean z2 = true;
        if (file == null || file2 == null || file.equals(file2) || !file.exists() || !file.isFile()) {
            return false;
        }
        if (file2.exists()) {
            if (onReplaceListener != null && !onReplaceListener.onReplace()) {
                return true;
            }
            if (!file2.delete()) {
                return false;
            }
        }
        if (!createOrExistsDir(file2.getParentFile())) {
            return false;
        }
        try {
            if (!writeFileFromIS(file2, new FileInputStream(file)) || (z && !deleteFile(file))) {
                z2 = false;
            }
            return z2;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteDir(String str) {
        return deleteDir(getFileByPath(str));
    }

    public static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (!file2.delete()) {
                        return false;
                    }
                } else if (file2.isDirectory() && !deleteDir(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean deleteFile(String str) {
        return deleteFile(getFileByPath(str));
    }

    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static boolean deleteAllInDir(String str) {
        return deleteAllInDir(getFileByPath(str));
    }

    public static boolean deleteAllInDir(File file) {
        return deleteFilesInDirWithFilter(file, new FileFilter() {
            public boolean accept(File file) {
                return true;
            }
        });
    }

    public static boolean deleteFilesInDir(String str) {
        return deleteFilesInDir(getFileByPath(str));
    }

    public static boolean deleteFilesInDir(File file) {
        return deleteFilesInDirWithFilter(file, new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        });
    }

    public static boolean deleteFilesInDirWithFilter(String str, FileFilter fileFilter) {
        return deleteFilesInDirWithFilter(getFileByPath(str), fileFilter);
    }

    public static boolean deleteFilesInDirWithFilter(File file, FileFilter fileFilter) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (fileFilter.accept(file2)) {
                    if (file2.isFile()) {
                        if (!file2.delete()) {
                            return false;
                        }
                    } else if (file2.isDirectory() && !deleteDir(file2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static List<File> listFilesInDir(String str) {
        return listFilesInDir(str, false);
    }

    public static List<File> listFilesInDir(File file) {
        return listFilesInDir(file, false);
    }

    public static List<File> listFilesInDir(String str, boolean z) {
        return listFilesInDir(getFileByPath(str), z);
    }

    public static List<File> listFilesInDir(File file, boolean z) {
        return listFilesInDirWithFilter(file, new FileFilter() {
            public boolean accept(File file) {
                return true;
            }
        }, z);
    }

    public static List<File> listFilesInDirWithFilter(String str, FileFilter fileFilter) {
        return listFilesInDirWithFilter(getFileByPath(str), fileFilter, false);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter) {
        return listFilesInDirWithFilter(file, fileFilter, false);
    }

    public static List<File> listFilesInDirWithFilter(String str, FileFilter fileFilter, boolean z) {
        return listFilesInDirWithFilter(getFileByPath(str), fileFilter, z);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter, boolean z) {
        if (!isDir(file)) {
            return null;
        }
        List<File> arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return arrayList;
        }
        for (File file2 : listFiles) {
            if (fileFilter.accept(file2)) {
                arrayList.add(file2);
            }
            if (z && file2.isDirectory()) {
                arrayList.addAll(listFilesInDirWithFilter(file2, fileFilter, true));
            }
        }
        return arrayList;
    }

    public static long getFileLastModified(String str) {
        return getFileLastModified(getFileByPath(str));
    }

    public static long getFileLastModified(File file) {
        if (file == null) {
            return -1;
        }
        return file.lastModified();
    }

    public static String getFileCharsetSimple(String str) {
        return getFileCharsetSimple(getFileByPath(str));
    }

    public static String getFileCharsetSimple(File file) {
        int read;
        IOException e;
        Throwable th;
        InputStream inputStream = null;
        InputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                read = (bufferedInputStream.read() << 8) + bufferedInputStream.read();
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            read = 0;
                        }
                    }
                    read = 0;
                    switch (read) {
                        case 61371:
                            return "UTF-8";
                        case 65279:
                            return "UTF-16BE";
                        case 65534:
                            return "Unicode";
                        default:
                            return "GBK";
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = bufferedInputStream;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e4 = e5;
            bufferedInputStream = null;
            e4.printStackTrace();
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            read = 0;
            switch (read) {
                case 61371:
                    return "UTF-8";
                case 65279:
                    return "UTF-16BE";
                case 65534:
                    return "Unicode";
                default:
                    return "GBK";
            }
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
        switch (read) {
            case 61371:
                return "UTF-8";
            case 65279:
                return "UTF-16BE";
            case 65534:
                return "Unicode";
            default:
                return "GBK";
        }
    }

    public static int getFileLines(String str) {
        return getFileLines(getFileByPath(str));
    }

    public static int getFileLines(File file) {
        int i;
        int i2;
        IOException e;
        Throwable th;
        InputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                byte[] bArr = new byte[1024];
                int read;
                if (!LINE_SEP.endsWith("\n")) {
                    i = 1;
                    while (true) {
                        read = bufferedInputStream.read(bArr, 0, 1024);
                        if (read == -1) {
                            break;
                        }
                        i2 = i;
                        for (i = 0; i < read; i++) {
                            if (bArr[i] == (byte) 13) {
                                i2++;
                            }
                        }
                        i = i2;
                    }
                } else {
                    i = 1;
                    while (true) {
                        try {
                            read = bufferedInputStream.read(bArr, 0, 1024);
                            if (read == -1) {
                                break;
                            }
                            i2 = i;
                            i = 0;
                            while (i < read) {
                                try {
                                    if (bArr[i] == (byte) 10) {
                                        i2++;
                                    }
                                    i++;
                                } catch (IOException e2) {
                                    e = e2;
                                }
                            }
                            i = i2;
                        } catch (IOException e3) {
                            IOException iOException = e3;
                            i2 = i;
                            e = iOException;
                        }
                    }
                }
                i2 = i;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (IOException e32) {
                e4 = e32;
                i2 = 1;
                try {
                    e4.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                    return i2;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e422) {
                            e422.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e322) {
            bufferedInputStream = null;
            e422 = e322;
            i2 = 1;
            e422.printStackTrace();
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            return i2;
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
        return i2;
    }

    public static String getDirSize(String str) {
        return getDirSize(getFileByPath(str));
    }

    public static String getDirSize(File file) {
        long dirLength = getDirLength(file);
        return dirLength == -1 ? "" : byte2FitMemorySize(dirLength);
    }

    public static String getFileSize(String str) {
        long fileLength = getFileLength(str);
        return fileLength == -1 ? "" : byte2FitMemorySize(fileLength);
    }

    public static String getFileSize(File file) {
        long fileLength = getFileLength(file);
        return fileLength == -1 ? "" : byte2FitMemorySize(fileLength);
    }

    public static long getDirLength(String str) {
        return getDirLength(getFileByPath(str));
    }

    public static long getDirLength(File file) {
        if (!isDir(file)) {
            return -1;
        }
        long j = 0;
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return 0;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                j += getDirLength(file2);
            } else {
                j += file2.length();
            }
        }
        return j;
    }

    public static long getFileLength(String str) {
        if (str.matches(RegexConstants.REGEX_URL)) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "identity");
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    return (long) httpURLConnection.getContentLength();
                }
                return -1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getFileLength(getFileByPath(str));
    }

    public static long getFileLength(File file) {
        if (isFile(file)) {
            return file.length();
        }
        return -1;
    }

    public static String getFileMD5ToString(String str) {
        return getFileMD5ToString(isSpace(str) ? null : new File(str));
    }

    public static String getFileMD5ToString(File file) {
        return bytes2HexString(getFileMD5(file));
    }

    public static byte[] getFileMD5(String str) {
        return getFileMD5(getFileByPath(str));
    }

    public static byte[] getFileMD5(File file) {
        Exception e;
        Throwable th;
        byte[] bArr = null;
        if (file != null) {
            DigestInputStream digestInputStream;
            try {
                digestInputStream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
                try {
                    do {
                    } while (digestInputStream.read(new byte[262144]) > 0);
                    bArr = digestInputStream.getMessageDigest().digest();
                    if (digestInputStream != null) {
                        try {
                            digestInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (NoSuchAlgorithmException e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (digestInputStream != null) {
                            try {
                                digestInputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        if (digestInputStream != null) {
                            try {
                                digestInputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    if (digestInputStream != null) {
                        digestInputStream.close();
                    }
                    return bArr;
                }
            } catch (NoSuchAlgorithmException e5) {
                e = e5;
                digestInputStream = null;
                e.printStackTrace();
                if (digestInputStream != null) {
                    digestInputStream.close();
                }
                return bArr;
            } catch (IOException e6) {
                e = e6;
                digestInputStream = null;
                e.printStackTrace();
                if (digestInputStream != null) {
                    digestInputStream.close();
                }
                return bArr;
            } catch (Throwable th3) {
                digestInputStream = null;
                th = th3;
                if (digestInputStream != null) {
                    digestInputStream.close();
                }
                throw th;
            }
        }
        return bArr;
    }

    public static String getDirName(File file) {
        if (file == null) {
            return "";
        }
        return getDirName(file.getAbsolutePath());
    }

    public static String getDirName(String str) {
        if (isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf + 1);
    }

    public static String getFileName(File file) {
        if (file == null) {
            return "";
        }
        return getFileName(file.getAbsolutePath());
    }

    public static String getFileName(String str) {
        if (isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf != -1 ? str.substring(lastIndexOf + 1) : str;
    }

    public static String getFileNameNoExtension(File file) {
        if (file == null) {
            return "";
        }
        return getFileNameNoExtension(file.getPath());
    }

    public static String getFileNameNoExtension(String str) {
        if (isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf2 == -1) {
            if (lastIndexOf != -1) {
                return str.substring(0, lastIndexOf);
            }
            return str;
        } else if (lastIndexOf == -1 || lastIndexOf2 > lastIndexOf) {
            return str.substring(lastIndexOf2 + 1);
        } else {
            return str.substring(lastIndexOf2 + 1, lastIndexOf);
        }
    }

    public static String getFileExtension(File file) {
        if (file == null) {
            return "";
        }
        return getFileExtension(file.getPath());
    }

    public static String getFileExtension(String str) {
        if (isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf == -1 || lastIndexOf2 >= lastIndexOf) {
            return "";
        }
        return str.substring(lastIndexOf + 1);
    }

    private static String bytes2HexString(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return "";
        }
        int length = bArr.length;
        if (length <= 0) {
            return "";
        }
        char[] cArr = new char[(length << 1)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = HEX_DIGITS[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr[i3] = HEX_DIGITS[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    private static String byte2FitMemorySize(long j) {
        if (j < 0) {
            return "shouldn't be less than zero!";
        }
        if (j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return String.format(Locale.getDefault(), "%.3fB", new Object[]{Double.valueOf((double) j)});
        } else if (j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            return String.format(Locale.getDefault(), "%.3fKB", new Object[]{Double.valueOf(((double) j) / 1024.0d)});
        } else if (j < 1073741824) {
            return String.format(Locale.getDefault(), "%.3fMB", new Object[]{Double.valueOf(((double) j) / 1048576.0d)});
        } else {
            return String.format(Locale.getDefault(), "%.3fGB", new Object[]{Double.valueOf(((double) j) / 1.073741824E9d)});
        }
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

    private static boolean writeFileFromIS(File file, InputStream inputStream) {
        OutputStream bufferedOutputStream;
        IOException e;
        Throwable th;
        boolean z = false;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr, 0, 8192);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                z = true;
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e22 = e3;
                try {
                    e22.printStackTrace();
                    try {
                        inputStream.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                    }
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        inputStream.close();
                    } catch (IOException e22222) {
                        e22222.printStackTrace();
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e222222) {
                            e222222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e4) {
            e222222 = e4;
            bufferedOutputStream = null;
            e222222.printStackTrace();
            inputStream.close();
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
            inputStream.close();
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            throw th;
        }
        return z;
    }
}
