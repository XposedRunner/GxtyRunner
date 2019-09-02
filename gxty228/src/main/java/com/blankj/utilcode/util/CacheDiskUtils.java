package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;
import com.blankj.utilcode.constant.CacheConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

public final class CacheDiskUtils implements CacheConstants {
    private static final SimpleArrayMap<String, CacheDiskUtils> CACHE_MAP = new SimpleArrayMap();
    private static final int DEFAULT_MAX_COUNT = Integer.MAX_VALUE;
    private static final long DEFAULT_MAX_SIZE = Long.MAX_VALUE;
    private final String mCacheKey;
    private final DiskCacheManager mDiskCacheManager;

    private static final class DiskCacheHelper {
        static final int TIME_INFO_LEN = 14;

        private DiskCacheHelper() {
        }

        private static byte[] newByteArrayWithTime(int i, byte[] bArr) {
            Object bytes = createDueTime(i).getBytes();
            Object obj = new byte[(bytes.length + bArr.length)];
            System.arraycopy(bytes, 0, obj, 0, bytes.length);
            System.arraycopy(bArr, 0, obj, bytes.length, bArr.length);
            return obj;
        }

        private static String createDueTime(int i) {
            return String.format(Locale.getDefault(), "_$%010d$_", new Object[]{Long.valueOf((System.currentTimeMillis() / 1000) + ((long) i))});
        }

        private static boolean isDue(byte[] bArr) {
            long dueTime = getDueTime(bArr);
            return dueTime != -1 && System.currentTimeMillis() > dueTime;
        }

        private static long getDueTime(byte[] bArr) {
            long j = -1;
            if (!hasTimeInfo(bArr)) {
                return j;
            }
            try {
                return Long.parseLong(new String(copyOfRange(bArr, 2, 12))) * 1000;
            } catch (NumberFormatException e) {
                return j;
            }
        }

        private static byte[] getDataWithoutDueTime(byte[] bArr) {
            if (hasTimeInfo(bArr)) {
                return copyOfRange(bArr, 14, bArr.length);
            }
            return bArr;
        }

        private static byte[] copyOfRange(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException(i + " > " + i2);
            }
            Object obj = new byte[i3];
            System.arraycopy(bArr, i, obj, 0, Math.min(bArr.length - i, i3));
            return obj;
        }

        private static boolean hasTimeInfo(byte[] bArr) {
            return bArr != null && bArr.length >= 14 && bArr[0] == (byte) 95 && bArr[1] == (byte) 36 && bArr[12] == (byte) 36 && bArr[13] == (byte) 95;
        }
    }

    private static final class DiskCacheManager {
        private final AtomicInteger cacheCount;
        private final File cacheDir;
        private final AtomicLong cacheSize;
        private final int countLimit;
        private final Map<File, Long> lastUsageDates;
        private final Thread mThread;
        private final long sizeLimit;

        private DiskCacheManager(final File file, long j, int i) {
            this.lastUsageDates = Collections.synchronizedMap(new HashMap());
            this.cacheDir = file;
            this.sizeLimit = j;
            this.countLimit = i;
            this.cacheSize = new AtomicLong();
            this.cacheCount = new AtomicInteger();
            this.mThread = new Thread(new Runnable() {
                public void run() {
                    int i = 0;
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        int length = listFiles.length;
                        int i2 = 0;
                        int i3 = 0;
                        while (i < length) {
                            File file = listFiles[i];
                            i3 = (int) (((long) i3) + file.length());
                            i2++;
                            DiskCacheManager.this.lastUsageDates.put(file, Long.valueOf(file.lastModified()));
                            i++;
                        }
                        DiskCacheManager.this.cacheSize.getAndAdd((long) i3);
                        DiskCacheManager.this.cacheCount.getAndAdd(i2);
                    }
                }
            });
            this.mThread.start();
        }

        private long getCacheSize() {
            try {
                this.mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.cacheSize.get();
        }

        private int getCacheCount() {
            try {
                this.mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.cacheCount.get();
        }

        private File getFileBeforePut(String str) {
            File file = new File(this.cacheDir, String.valueOf(str.hashCode()));
            if (file.exists()) {
                this.cacheCount.addAndGet(-1);
                this.cacheSize.addAndGet(-file.length());
            }
            return file;
        }

        private File getFileIfExists(String str) {
            File file = new File(this.cacheDir, String.valueOf(str.hashCode()));
            if (file.exists()) {
                return file;
            }
            return null;
        }

        private void put(File file) {
            this.cacheCount.addAndGet(1);
            this.cacheSize.addAndGet(file.length());
            while (true) {
                if (this.cacheCount.get() > this.countLimit || this.cacheSize.get() > this.sizeLimit) {
                    this.cacheSize.addAndGet(-removeOldest());
                    this.cacheCount.addAndGet(-1);
                } else {
                    return;
                }
            }
        }

        private void updateModify(File file) {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.lastUsageDates.put(file, valueOf);
        }

        private boolean removeByKey(String str) {
            File fileIfExists = getFileIfExists(str);
            if (fileIfExists == null) {
                return true;
            }
            if (!fileIfExists.delete()) {
                return false;
            }
            this.cacheSize.addAndGet(-fileIfExists.length());
            this.cacheCount.addAndGet(-1);
            this.lastUsageDates.remove(fileIfExists);
            return true;
        }

        private boolean clear() {
            boolean z = true;
            File[] listFiles = this.cacheDir.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.delete()) {
                        this.cacheSize.addAndGet(-file.length());
                        this.cacheCount.addAndGet(-1);
                        this.lastUsageDates.remove(file);
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    this.lastUsageDates.clear();
                    this.cacheSize.set(0);
                    this.cacheCount.set(0);
                }
            }
            return z;
        }

        private long removeOldest() {
            if (this.lastUsageDates.isEmpty()) {
                return 0;
            }
            Long valueOf = Long.valueOf(CacheDiskUtils.DEFAULT_MAX_SIZE);
            File file = null;
            Set<Entry> entrySet = this.lastUsageDates.entrySet();
            synchronized (this.lastUsageDates) {
                for (Entry entry : entrySet) {
                    File file2;
                    Long l = (Long) entry.getValue();
                    if (l.longValue() < valueOf.longValue()) {
                        file2 = (File) entry.getKey();
                    } else {
                        file2 = file;
                        l = valueOf;
                    }
                    file = file2;
                    valueOf = l;
                }
            }
            if (file == null) {
                return 0;
            }
            long length = file.length();
            if (!file.delete()) {
                return 0;
            }
            this.lastUsageDates.remove(file);
            return length;
        }
    }

    public static CacheDiskUtils getInstance() {
        return getInstance("", (long) DEFAULT_MAX_SIZE, Integer.MAX_VALUE);
    }

    public static CacheDiskUtils getInstance(String str) {
        return getInstance(str, (long) DEFAULT_MAX_SIZE, Integer.MAX_VALUE);
    }

    public static CacheDiskUtils getInstance(long j, int i) {
        return getInstance("", j, i);
    }

    public static CacheDiskUtils getInstance(String str, long j, int i) {
        if (isSpace(str)) {
            str = "cacheUtils";
        }
        return getInstance(new File(Utils.getApp().getCacheDir(), str), j, i);
    }

    public static CacheDiskUtils getInstance(@NonNull File file) {
        if (file != null) {
            return getInstance(file, (long) DEFAULT_MAX_SIZE, Integer.MAX_VALUE);
        }
        throw new NullPointerException("Argument 'cacheDir' of type File (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static CacheDiskUtils getInstance(@NonNull File file, long j, int i) {
        if (file == null) {
            throw new NullPointerException("Argument 'cacheDir' of type File (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        String str = file.getAbsoluteFile() + "_" + j + "_" + i;
        CacheDiskUtils cacheDiskUtils;
        if (file.exists()) {
            CacheDiskUtils cacheDiskUtils2 = (CacheDiskUtils) CACHE_MAP.get(str);
            if (cacheDiskUtils2 != null) {
                return cacheDiskUtils2;
            }
            cacheDiskUtils = new CacheDiskUtils(str, new DiskCacheManager(file, j, i));
            CACHE_MAP.put(str, cacheDiskUtils);
            return cacheDiskUtils;
        } else if (file.mkdirs()) {
            cacheDiskUtils = new CacheDiskUtils(str, new DiskCacheManager(file, j, i));
            CACHE_MAP.put(str, cacheDiskUtils);
            return cacheDiskUtils;
        } else {
            throw new RuntimeException("can't make dirs in " + file.getAbsolutePath());
        }
    }

    private CacheDiskUtils(String str, DiskCacheManager diskCacheManager) {
        this.mCacheKey = str;
        this.mDiskCacheManager = diskCacheManager;
    }

    public String toString() {
        return this.mCacheKey + "@" + Integer.toHexString(hashCode());
    }

    public void put(@NonNull String str, byte[] bArr) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, bArr, -1);
    }

    public void put(@NonNull String str, byte[] bArr, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (bArr != null) {
            if (i >= 0) {
                bArr = DiskCacheHelper.newByteArrayWithTime(i, bArr);
            }
            File access$200 = this.mDiskCacheManager.getFileBeforePut(str);
            writeFileFromBytes(access$200, bArr);
            this.mDiskCacheManager.updateModify(access$200);
            this.mDiskCacheManager.put(access$200);
        }
    }

    public byte[] getBytes(@NonNull String str) {
        if (str != null) {
            return getBytes(str, null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public byte[] getBytes(@NonNull String str, byte[] bArr) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        File access$500 = this.mDiskCacheManager.getFileIfExists(str);
        if (access$500 == null) {
            return bArr;
        }
        byte[] readFile2Bytes = readFile2Bytes(access$500);
        if (DiskCacheHelper.isDue(readFile2Bytes)) {
            this.mDiskCacheManager.removeByKey(str);
            return bArr;
        }
        this.mDiskCacheManager.updateModify(access$500);
        return DiskCacheHelper.getDataWithoutDueTime(readFile2Bytes);
    }

    public void put(@NonNull String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, str2, -1);
    }

    public void put(@NonNull String str, String str2, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, string2Bytes(str2), i);
    }

    public String getString(@NonNull String str) {
        if (str != null) {
            return getString(str, null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public String getString(@NonNull String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        byte[] bytes = getBytes(str);
        return bytes == null ? str2 : bytes2String(bytes);
    }

    public void put(@NonNull String str, JSONObject jSONObject) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, jSONObject, -1);
    }

    public void put(@NonNull String str, JSONObject jSONObject, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, jsonObject2Bytes(jSONObject), i);
    }

    public JSONObject getJSONObject(@NonNull String str) {
        if (str != null) {
            return getJSONObject(str, null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public JSONObject getJSONObject(@NonNull String str, JSONObject jSONObject) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        byte[] bytes = getBytes(str);
        return bytes == null ? jSONObject : bytes2JSONObject(bytes);
    }

    public void put(@NonNull String str, JSONArray jSONArray) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, jSONArray, -1);
    }

    public void put(@NonNull String str, JSONArray jSONArray, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, jsonArray2Bytes(jSONArray), i);
    }

    public JSONArray getJSONArray(@NonNull String str) {
        if (str != null) {
            return getJSONArray(str, null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public JSONArray getJSONArray(@NonNull String str, JSONArray jSONArray) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        byte[] bytes = getBytes(str);
        return bytes == null ? jSONArray : bytes2JSONArray(bytes);
    }

    public void put(@NonNull String str, Bitmap bitmap) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, bitmap, -1);
    }

    public void put(@NonNull String str, Bitmap bitmap, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, bitmap2Bytes(bitmap), i);
    }

    public Bitmap getBitmap(@NonNull String str) {
        if (str != null) {
            return getBitmap(str, null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public Bitmap getBitmap(@NonNull String str, Bitmap bitmap) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        byte[] bytes = getBytes(str);
        return bytes == null ? bitmap : bytes2Bitmap(bytes);
    }

    public void put(@NonNull String str, Drawable drawable) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, drawable, -1);
    }

    public void put(@NonNull String str, Drawable drawable, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, drawable2Bytes(drawable), i);
    }

    public Drawable getDrawable(@NonNull String str) {
        if (str != null) {
            return getDrawable(str, null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public Drawable getDrawable(@NonNull String str, Drawable drawable) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        byte[] bytes = getBytes(str);
        return bytes == null ? drawable : bytes2Drawable(bytes);
    }

    public void put(@NonNull String str, Parcelable parcelable) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, parcelable, -1);
    }

    public void put(@NonNull String str, Parcelable parcelable, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, parcelable2Bytes(parcelable), i);
    }

    public <T> T getParcelable(@NonNull String str, @NonNull Creator<T> creator) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator != null) {
            return getParcelable(str, creator, null);
        } else {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    public <T> T getParcelable(@NonNull String str, @NonNull Creator<T> creator, T t) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else if (creator == null) {
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        } else {
            byte[] bytes = getBytes(str);
            return bytes == null ? t : bytes2Parcelable(bytes, creator);
        }
    }

    public void put(@NonNull String str, Serializable serializable) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, serializable, -1);
    }

    public void put(@NonNull String str, Serializable serializable, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, serializable2Bytes(serializable), i);
    }

    public Object getSerializable(@NonNull String str) {
        if (str != null) {
            return getSerializable(str, null);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public Object getSerializable(@NonNull String str, Object obj) {
        if (str != null) {
            return getBytes(str) == null ? obj : bytes2Object(getBytes(str));
        } else {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
    }

    public long getCacheSize() {
        return this.mDiskCacheManager.getCacheSize();
    }

    public int getCacheCount() {
        return this.mDiskCacheManager.getCacheCount();
    }

    public boolean remove(@NonNull String str) {
        if (str != null) {
            return this.mDiskCacheManager.removeByKey(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public boolean clear() {
        return this.mDiskCacheManager.clear();
    }

    private static byte[] string2Bytes(String str) {
        if (str == null) {
            return null;
        }
        return str.getBytes();
    }

    private static String bytes2String(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr);
    }

    private static byte[] jsonObject2Bytes(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString().getBytes();
    }

    private static JSONObject bytes2JSONObject(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new JSONObject(new String(bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] jsonArray2Bytes(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        return jSONArray.toString().getBytes();
    }

    private static JSONArray bytes2JSONArray(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new JSONArray(new String(bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] parcelable2Bytes(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    private static <T> T bytes2Parcelable(byte[] bArr, Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        T createFromParcel = creator.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    private static byte[] serializable2Bytes(Serializable serializable) {
        ObjectOutputStream objectOutputStream;
        Exception e;
        Throwable th;
        byte[] bArr = null;
        if (serializable != null) {
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream.writeObject(serializable);
                    bArr = byteArrayOutputStream.toByteArray();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return bArr;
                    } catch (Throwable th2) {
                        th = th2;
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e = e4;
                objectOutputStream = bArr;
                e.printStackTrace();
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                return bArr;
            } catch (Throwable th3) {
                objectOutputStream = bArr;
                th = th3;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                throw th;
            }
        }
        return bArr;
    }

    private static Object bytes2Object(byte[] bArr) {
        ObjectInputStream objectInputStream;
        Object readObject;
        Exception e;
        Throwable th;
        ObjectInputStream objectInputStream2 = null;
        if (bArr != null) {
            try {
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
                try {
                    readObject = objectInputStream.readObject();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return readObject;
                    } catch (Throwable th2) {
                        th = th2;
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e = e4;
                objectInputStream = objectInputStream2;
                e.printStackTrace();
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                return readObject;
            } catch (Throwable th3) {
                objectInputStream = objectInputStream2;
                th = th3;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                throw th;
            }
        }
        return readObject;
    }

    private static byte[] bitmap2Bytes(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static Bitmap bytes2Bitmap(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    private static byte[] drawable2Bytes(Drawable drawable) {
        return drawable == null ? null : bitmap2Bytes(drawable2Bitmap(drawable));
    }

    private static Drawable bytes2Drawable(byte[] bArr) {
        return bArr == null ? null : bitmap2Drawable(bytes2Bitmap(bArr));
    }

    private static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap createBitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            createBitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        } else {
            createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        }
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    private static Drawable bitmap2Drawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.getApp().getResources(), bitmap);
    }

    private static void writeFileFromBytes(File file, byte[] bArr) {
        FileChannel fileChannel = null;
        try {
            fileChannel = new FileOutputStream(file, false).getChannel();
            fileChannel.write(ByteBuffer.wrap(bArr));
            fileChannel.force(true);
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    private static byte[] readFile2Bytes(File file) {
        IOException e;
        FileChannel fileChannel;
        Throwable th;
        FileChannel fileChannel2 = null;
        try {
            FileChannel channel = new RandomAccessFile(file, "r").getChannel();
            try {
                int size = (int) channel.size();
                byte[] bArr = new byte[size];
                channel.map(MapMode.READ_ONLY, 0, (long) size).load().get(bArr, 0, size);
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return bArr;
            } catch (IOException e3) {
                IOException iOException = e3;
                fileChannel = channel;
                e2 = iOException;
                try {
                    e2.printStackTrace();
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel2 = fileChannel;
                    if (fileChannel2 != null) {
                        try {
                            fileChannel2.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                fileChannel2 = channel;
                th = th3;
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                throw th;
            }
        } catch (IOException e4) {
            e22 = e4;
            fileChannel = null;
            e22.printStackTrace();
            if (fileChannel != null) {
                fileChannel.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            if (fileChannel2 != null) {
                fileChannel2.close();
            }
            throw th;
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
}
