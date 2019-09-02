package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/* compiled from: SoDownloadItem */
public final class id {
    private iz a;
    private hz b;
    private RandomAccessFile c;

    public id(iz izVar, hz hzVar) {
        this.a = izVar;
        this.b = hzVar;
    }

    public final String a() {
        if (this.a == null) {
            return "";
        }
        return this.a.c;
    }

    public final void a(byte[] bArr, long j) {
        try {
            if (this.c == null) {
                File file = new File(this.b.b(this.a.a()));
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                try {
                    this.c = new RandomAccessFile(file, "rw");
                } catch (FileNotFoundException e) {
                }
            }
            try {
                this.c.seek(j);
                this.c.write(bArr);
            } catch (IOException e2) {
            }
        } catch (Throwable th) {
        }
    }

    public static void a(Context context, hz hzVar) {
        int i = 0;
        if (context != null) {
            iz b = ik.b(context);
            if (b != null && iz.a(b)) {
                if (hz.c(b.d, hzVar.b(b.a()))) {
                    String b2 = hzVar.b(b.a());
                    b.a();
                    hz.b(b2, hzVar.b());
                    if (ie.a(hzVar.c(b.a()))) {
                        int i2;
                        ij ijVar;
                        b2 = hzVar.c(b.a());
                        b.a();
                        hz.a(context, b2, hzVar.b());
                        b.a();
                        Object b3 = hzVar.b();
                        if (TextUtils.isEmpty(b3)) {
                            i2 = 0;
                        } else if (b.g == null) {
                            i2 = 0;
                        } else {
                            for (ij ijVar2 : b.g) {
                                String f = ijVar2.f();
                                Object obj = ijVar2.a;
                                if (!TextUtils.isEmpty(f) && !TextUtils.isEmpty(obj)) {
                                    if (!hz.c(obj, b3 + File.separator + hz.a(context, f))) {
                                        i2 = 0;
                                        break;
                                    }
                                }
                                i2 = 0;
                                break;
                            }
                            i2 = 1;
                        }
                        if (i2 != 0) {
                            iz a = ik.a(context);
                            List arrayList = new ArrayList();
                            Collection arrayList2 = new ArrayList();
                            a(b, a, arrayList, arrayList2);
                            b.d().addAll(arrayList2);
                            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                                ijVar2 = (ij) arrayList.get(i3);
                                a.d().remove(ijVar2);
                                hz.f(hzVar.a() + File.separator + hz.a(context, ijVar2.f()));
                            }
                            while (i < b.d().size()) {
                                b2 = ((ij) b.d().get(i)).f();
                                StringBuilder stringBuilder = new StringBuilder();
                                b.a();
                                hz.a(stringBuilder.append(hzVar.b()).append(File.separator).append(hz.a(context, b2)).toString(), hzVar.a() + File.separator + hz.a(context, b2));
                                i++;
                            }
                            ik.a(context, b);
                            ik.d(context);
                            b.a();
                            hz.e(hzVar.b());
                            hz.f(hzVar.b(b.a()));
                            return;
                        }
                        ik.d(context);
                        hz.e(hzVar.a());
                        hz.e(hzVar.a(b.a()));
                        return;
                    }
                    ik.d(context);
                    b.a();
                    hz.e(hzVar.b());
                    return;
                }
                hz.f(hzVar.b(b.a()));
                ik.d(context);
            }
        }
    }

    private static void a(iz izVar, iz izVar2, List<ij> list, List<ij> list2) {
        int i = 0;
        List d = izVar2.d();
        List d2 = izVar.d();
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < d.size(); i2++) {
            ij ijVar = (ij) d.get(i2);
            for (int i3 = 0; i3 < d2.size(); i3++) {
                if (((ij) d2.get(i3)).f().equals(ijVar.f())) {
                    hashSet.add(ijVar.b());
                }
            }
        }
        while (i < d.size()) {
            if (hashSet.contains(((ij) d.get(i)).b())) {
                list.add(d.get(i));
            } else {
                list2.add(d.get(i));
            }
            i++;
        }
    }

    public final void a(Context context) {
        try {
            if (this.c != null) {
                try {
                    this.c.close();
                } catch (IOException e) {
                }
                if (hz.c(this.a.d, this.b.b(this.a.a()))) {
                    ik.b(context, this.a);
                    return;
                }
                hz.f(this.b.b(this.a.a()));
                ik.d(context);
            }
        } catch (Throwable th) {
            hz.e(this.b.a());
            hz.e(this.b.a(this.a.a()));
        }
    }

    public final void b() {
        try {
            if (this.c != null) {
                try {
                    this.c.close();
                } catch (IOException e) {
                }
            }
        } catch (Throwable th) {
        }
    }
}
