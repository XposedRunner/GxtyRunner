package com.autonavi.amap.mapcore;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.mapcore.util.gz;
import com.amap.api.maps.MapsInitializer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class FileUtil {
    private static final String TAG = "FileUtil";
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    public static void copy(Context context, String str, File file) throws Exception {
        file.delete();
        InputStream open = context.getAssets().open(str);
        byte[] bArr = new byte[open.available()];
        open.read(bArr);
        open.close();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bArr);
        fileOutputStream.close();
    }

    public static boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int i = 0; i < listFiles.length; i++) {
                    if (listFiles[i].isFile()) {
                        if (!listFiles[i].delete()) {
                            return false;
                        }
                    } else if (!deleteFile(listFiles[i])) {
                        return false;
                    } else {
                        listFiles[i].delete();
                    }
                }
            }
        }
        file.delete();
        return true;
    }

    public static String getMapBaseStorage(Context context) {
        if (context == null) {
            return null;
        }
        File file;
        String str = "map_base_path";
        if (VERSION.SDK_INT > 18) {
            str = "map_base_path_v44";
        }
        String str2 = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("base_path", 0);
        if (MapsInitializer.sdcardDir == null || MapsInitializer.sdcardDir.trim().length() <= 0) {
            str2 = sharedPreferences.getString(str, "");
        } else {
            str2 = MapsInitializer.sdcardDir + File.separatorChar;
        }
        if (str2 != null && str2.length() > 2) {
            file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            if (file.isDirectory()) {
                if (checkCanWrite(file)) {
                    return str2;
                }
                str2 = context.getCacheDir().toString() + AeUtil.ROOTPATH;
                if (str2 != null && str2.length() > 2) {
                    file = new File(str2);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    if (file.isDirectory()) {
                        return str2;
                    }
                }
            }
        }
        str2 = getExternalStroragePath(context) + AeUtil.ROOTPATH;
        if (str2 != null && str2.length() > 2) {
            file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            if (file.isDirectory() && file.canWrite()) {
                Editor edit = sharedPreferences.edit();
                edit.putString(str, str2);
                edit.commit();
                createNoMediaFileIfNotExist(str2);
                return str2;
            }
        }
        str = context.getCacheDir().toString() + AeUtil.ROOTPATH;
        if (str == null || str.length() <= 2) {
            return str;
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file2.isDirectory() ? str : str;
    }

    public static boolean checkCanWrite(File file) {
        if (file == null) {
            return false;
        }
        if (file.canWrite()) {
            File file2 = new File(file, "amap.tmp");
            try {
                file2.createNewFile();
                if (file2 == null || !file2.exists()) {
                    return false;
                }
                try {
                    file2.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static String getExternalStroragePath(Context context) {
        int i = VERSION.SDK_INT;
        if (i >= 12) {
            try {
                String str;
                StorageManager storageManager = (StorageManager) context.getSystemService("storage");
                Method method = StorageManager.class.getMethod("getVolumeList", new Class[0]);
                Method method2 = StorageManager.class.getMethod("getVolumeState", new Class[]{String.class});
                Object[] objArr = (Object[]) method.invoke(storageManager, new Object[0]);
                Boolean.valueOf(false);
                String str2 = "";
                String str3 = "";
                str2 = "";
                String str4 = "";
                int length = objArr.length;
                int i2 = 0;
                while (i2 < length) {
                    Object obj = objArr[i2];
                    str2 = (String) obj.getClass().getMethod("getPath", new Class[0]).invoke(obj, new Object[0]);
                    String str5 = (String) method2.invoke(storageManager, new Object[]{obj.getClass().getMethod("getPath", new Class[0]).invoke(obj, new Object[0])});
                    Boolean bool = (Boolean) obj.getClass().getMethod("isRemovable", new Class[0]).invoke(obj, new Object[0]);
                    if (!TextUtils.isEmpty(str2) && str2.toLowerCase().contains("private")) {
                        str5 = str4;
                        str2 = str3;
                    } else if (!bool.booleanValue()) {
                        continue;
                    } else if (str2 == null || str5 == null || !str5.equals("mounted")) {
                        str5 = str4;
                        str2 = str3;
                    } else {
                        if (i <= 18) {
                            str = str2;
                        } else {
                            try {
                                File[] externalFilesDirs = context.getExternalFilesDirs(null);
                                if (externalFilesDirs == null) {
                                    str2 = null;
                                } else if (externalFilesDirs.length > 1) {
                                    str2 = externalFilesDirs[1].getAbsolutePath();
                                }
                                str = str2;
                            } catch (Exception e) {
                                str = str2;
                            }
                        }
                        if (i > 18) {
                            return (str == null || str3 == null || str4 == null || !str4.equals("mounted")) ? str : str3;
                        } else {
                            if (!(str3 == null || str4 == null)) {
                                if (str4.equals("mounted")) {
                                    str = str3;
                                }
                            }
                            return str;
                        }
                    }
                    i2++;
                    str4 = str5;
                    str3 = str2;
                }
                str = null;
                if (i > 18) {
                    if (str4.equals("mounted")) {
                        str = str3;
                    }
                    return str;
                }
                if (str == null) {
                }
            } catch (Throwable th) {
            }
        }
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    public static void writeDatasToFile(String str, byte[] bArr) {
        WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    File file = new File(str);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    OutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(bArr);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            } finally {
                writeLock.unlock();
            }
        }
        writeLock.unlock();
    }

    public static byte[] readFileContents(String str) {
        byte[] bArr = null;
        try {
            File file = new File(str);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
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
                fileInputStream.close();
                bArr = byteArrayOutputStream.toByteArray();
            }
        } catch (Throwable th) {
            gz.c(th, TAG, "readFileContents");
        }
        return bArr;
    }

    public static void createNoMediaFileIfNotExist(String str) {
    }

    public static void saveFile(String str, String str2, boolean z) {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + HttpUtils.PATHS_SEPARATOR + str2);
            if (!file.exists()) {
                new File(file.getParent()).mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, z);
            fileOutputStream.write(str.getBytes("utf-8"));
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static byte[] readFileContentsFromAssets(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            int available = open.available();
            if (available == 0) {
                return null;
            }
            byte[] bArr = new byte[available];
            for (int i = 0; i < available; i += open.read(bArr, i, available - i)) {
            }
            open.close();
            return bArr;
        } catch (IOException e) {
            return null;
        } catch (OutOfMemoryError e2) {
            return null;
        }
    }

    public static String getName(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(indexOfLastSeparator(str) + 1);
    }

    public static int indexOfLastSeparator(String str) {
        if (str == null) {
            return -1;
        }
        return Math.max(str.lastIndexOf(47), str.lastIndexOf(92));
    }
}
