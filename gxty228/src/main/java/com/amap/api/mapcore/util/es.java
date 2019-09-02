package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.amap.api.mapcore.util.io.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.util.HashMap;

/* compiled from: ImageCache */
public class es {
    private static final CompressFormat a = CompressFormat.PNG;
    private io b;
    private dx<String, Bitmap> c;
    private a d;
    private final Object e = new Object();
    private boolean f = true;
    private HashMap<String, WeakReference<Bitmap>> g;

    /* compiled from: ImageCache */
    public static class a {
        public int a = 5242880;
        public long b = 10485760;
        public File c;
        public CompressFormat d = es.a;
        public int e = 100;
        public boolean f = true;
        public boolean g = true;
        public boolean h = false;
        public boolean i = true;
        public String j = null;

        public a(Context context, String str) {
            this.j = str;
            this.c = es.a(context, str, null);
        }

        public a(Context context, String str, String str2) {
            this.j = str;
            this.c = es.a(context, str, str2);
        }

        public void a(int i) {
            this.a = i;
        }

        public void a(long j) {
            if (j <= 0) {
                this.g = false;
            }
            this.b = j;
        }

        public void a(String str) {
            this.c = new File(str);
        }

        public void a(boolean z) {
            this.f = z;
        }

        public void b(boolean z) {
            this.g = z;
        }

        public void b(String str) {
            this.c = es.a(lo.a, this.j, str);
        }
    }

    private es(a aVar) {
        b(aVar);
    }

    public static es a(a aVar) {
        return new es(aVar);
    }

    private void b(a aVar) {
        this.d = aVar;
        if (this.d.f) {
            if (et.a()) {
                this.g = new HashMap(64);
            }
            this.c = new dx<String, Bitmap>(this, this.d.a) {
                final /* synthetic */ es a;

                protected /* synthetic */ int b(Object obj, Object obj2) {
                    return a((String) obj, (Bitmap) obj2);
                }

                protected void a(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
                    if (en.c() && this.a.g != null && bitmap != null && !bitmap.isRecycled()) {
                        this.a.g.put(str, new WeakReference(bitmap));
                    }
                }

                protected int a(String str, Bitmap bitmap) {
                    int a = es.a(bitmap);
                    return a == 0 ? 1 : a;
                }
            };
        }
        if (aVar.h) {
            a();
        }
    }

    public void a() {
        synchronized (this.e) {
            if (this.b == null || this.b.d()) {
                File file = this.d.c;
                if (this.d.g && file != null) {
                    try {
                        if (!file.exists()) {
                            file.mkdirs();
                        } else if (this.d.i) {
                            b(file);
                            file.mkdir();
                        }
                    } catch (Throwable th) {
                    }
                    if (a(file) > this.d.b) {
                        try {
                            this.b = io.a(file, 1, 1, this.d.b);
                        } catch (Throwable th2) {
                            this.d.c = null;
                        }
                    }
                }
            }
            this.f = false;
            this.e.notifyAll();
        }
    }

    private void b(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2.isDirectory()) {
                b(file2);
            }
            if (file2.delete()) {
                i++;
            } else {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r7, android.graphics.Bitmap r8) {
        /*
        r6 = this;
        if (r7 == 0) goto L_0x000a;
    L_0x0002:
        if (r8 == 0) goto L_0x000a;
    L_0x0004:
        r0 = r8.isRecycled();
        if (r0 == 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = r6.c;
        if (r0 == 0) goto L_0x0014;
    L_0x000f:
        r0 = r6.c;
        r0.a(r7, r8);
    L_0x0014:
        r2 = r6.e;
        monitor-enter(r2);
        r0 = r6.b;	 Catch:{ all -> 0x004d }
        if (r0 == 0) goto L_0x004b;
    L_0x001b:
        r1 = c(r7);	 Catch:{ all -> 0x004d }
        r0 = 0;
        r3 = r6.b;	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r3 = r3.a(r1);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        if (r3 != 0) goto L_0x0050;
    L_0x0028:
        r3 = r6.b;	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r1 = r3.b(r1);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        if (r1 == 0) goto L_0x0046;
    L_0x0030:
        r3 = 0;
        r0 = r1.a(r3);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r3 = r6.d;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r3 = r3.d;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r4 = r6.d;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r4 = r4.e;	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r8.compress(r3, r4, r0);	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r1.a();	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
        r0.close();	 Catch:{ Throwable -> 0x0059, all -> 0x0070 }
    L_0x0046:
        if (r0 == 0) goto L_0x004b;
    L_0x0048:
        r0.close();	 Catch:{ Throwable -> 0x006c }
    L_0x004b:
        monitor-exit(r2);	 Catch:{ all -> 0x004d }
        goto L_0x000a;
    L_0x004d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x004d }
        throw r0;
    L_0x0050:
        r1 = 0;
        r1 = r3.a(r1);	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        r1.close();	 Catch:{ Throwable -> 0x0059, all -> 0x0062 }
        goto L_0x0046;
    L_0x0059:
        r1 = move-exception;
        if (r0 == 0) goto L_0x004b;
    L_0x005c:
        r0.close();	 Catch:{ Throwable -> 0x0060 }
        goto L_0x004b;
    L_0x0060:
        r0 = move-exception;
        goto L_0x004b;
    L_0x0062:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0066:
        if (r1 == 0) goto L_0x006b;
    L_0x0068:
        r1.close();	 Catch:{ Throwable -> 0x006e }
    L_0x006b:
        throw r0;	 Catch:{ all -> 0x004d }
    L_0x006c:
        r0 = move-exception;
        goto L_0x004b;
    L_0x006e:
        r1 = move-exception;
        goto L_0x006b;
    L_0x0070:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x0066;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.es.a(java.lang.String, android.graphics.Bitmap):void");
    }

    public Bitmap a(String str) {
        Bitmap bitmap;
        if (en.c() && this.g != null) {
            WeakReference weakReference = (WeakReference) this.g.get(str);
            if (weakReference != null) {
                bitmap = (Bitmap) weakReference.get();
                if (bitmap == null || bitmap.isRecycled()) {
                    bitmap = null;
                }
                this.g.remove(str);
                if (bitmap == null && this.c != null) {
                    bitmap = (Bitmap) this.c.a((Object) str);
                }
                if (bitmap == null && !bitmap.isRecycled()) {
                    return bitmap;
                }
            }
        }
        bitmap = null;
        bitmap = (Bitmap) this.c.a((Object) str);
        return bitmap == null ? null : null;
    }

    public Bitmap b(String str) {
        Throwable th;
        Bitmap bitmap = null;
        String c = c(str);
        synchronized (this.e) {
            while (this.f) {
                try {
                    this.e.wait();
                } catch (Throwable th2) {
                }
            }
            if (this.b != null) {
                InputStream a;
                try {
                    b a2 = this.b.a(c);
                    if (a2 != null) {
                        a = a2.a(0);
                        if (a != null) {
                            try {
                                bitmap = eq.a(((FileInputStream) a).getFD(), Integer.MAX_VALUE, Integer.MAX_VALUE, this);
                            } catch (Throwable th3) {
                                th = th3;
                                if (a != null) {
                                    try {
                                        a.close();
                                    } catch (Throwable th4) {
                                    }
                                }
                                throw th;
                            }
                        }
                    }
                    Object obj = bitmap;
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th5) {
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    a = bitmap;
                    if (a != null) {
                        a.close();
                    }
                    throw th;
                }
            }
        }
        return bitmap;
    }

    public void b() {
        if (en.c() && this.g != null) {
            this.g.clear();
        }
        if (this.c != null) {
            this.c.a();
        }
        synchronized (this.e) {
            this.f = true;
            if (!(this.b == null || this.b.d())) {
                try {
                    this.b.close();
                    b(a(lo.a, this.d.j, null));
                } catch (Throwable th) {
                }
                this.b = null;
                a();
            }
        }
    }

    public void c() {
        synchronized (this.e) {
            if (this.b != null) {
                try {
                    this.b.e();
                } catch (Throwable th) {
                }
            }
        }
    }

    public void a(boolean z) {
        if (en.c() && this.g != null) {
            this.g.clear();
        }
        if (this.c != null) {
            this.c.a();
        }
        synchronized (this.e) {
            if (this.b != null) {
                try {
                    if (!this.b.d()) {
                        if (z) {
                            this.b.f();
                        } else {
                            this.b.close();
                        }
                        this.b = null;
                    }
                } catch (Throwable th) {
                }
            }
        }
    }

    public static File a(Context context, String str, String str2) {
        String path;
        File a = a(context);
        if (("mounted".equals(Environment.getExternalStorageState()) || !d()) && a != null) {
            path = a.getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(path).append(File.separator).append(str);
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append(File.separator).append(str2);
        }
        return new File(stringBuilder.toString());
    }

    public static String c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("utf-8"));
            return a(instance.digest());
        } catch (Throwable th) {
            return String.valueOf(str.hashCode());
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    public static int a(Bitmap bitmap) {
        if (en.d()) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static boolean d() {
        if (en.b()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    public static File a(Context context) {
        try {
            if (en.a()) {
                return context.getExternalCacheDir();
            }
            return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/cache/"));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static long a(File file) {
        if (en.b()) {
            return file.getUsableSpace();
        }
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }
}
