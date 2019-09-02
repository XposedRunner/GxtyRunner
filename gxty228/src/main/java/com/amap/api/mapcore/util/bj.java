package com.amap.api.mapcore.util;

import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* compiled from: UnZipFile */
public class bj {
    private b a;

    /* compiled from: UnZipFile */
    public interface c {
        void a();

        void a(long j);
    }

    /* compiled from: UnZipFile */
    public static class a {
        public boolean a = false;
    }

    /* compiled from: UnZipFile */
    private static class b {
        private String a;
        private String b;
        private bd c = null;
        private a d = new a();
        private String e;

        public b(bf bfVar, bd bdVar) {
            this.a = bfVar.B();
            this.b = bfVar.C();
            this.c = bdVar;
        }

        public void a(String str) {
            if (str.length() > 1) {
                this.e = str;
            }
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.e;
        }

        public bd d() {
            return this.c;
        }

        public a e() {
            return this.d;
        }

        public void f() {
            this.d.a = true;
        }
    }

    public bj(bf bfVar, bd bdVar) {
        this.a = new b(bfVar, bdVar);
    }

    public void a() {
        if (this.a != null) {
            this.a.f();
        }
    }

    public void b() {
        if (this.a != null) {
            a(this.a);
        }
    }

    private static void a(b bVar) {
        if (bVar != null) {
            final bd d = bVar.d();
            if (d != null) {
                d.q();
            }
            Object a = bVar.a();
            Object b = bVar.b();
            if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(b)) {
                File file = new File(a);
                if (file.exists()) {
                    c anonymousClass1;
                    File file2 = new File(b);
                    if (file2.exists() || !file2.mkdirs()) {
                        anonymousClass1 = new c() {
                            public void a(long j) {
                                try {
                                    if (d != null) {
                                        d.a(j);
                                    }
                                } catch (Exception e) {
                                }
                            }

                            public void a() {
                                if (d != null) {
                                    d.r();
                                }
                            }
                        };
                    } else {
                        anonymousClass1 = /* anonymous class already generated */;
                    }
                    try {
                        if (bVar.e().a && d != null) {
                            d.s();
                        }
                        a(file, file2, anonymousClass1, bVar);
                        if (bVar.e().a) {
                            if (d != null) {
                                d.s();
                            }
                        } else if (d != null) {
                            d.b(bVar.c());
                        }
                    } catch (Throwable th) {
                        if (bVar.e().a) {
                            if (d != null) {
                                d.s();
                            }
                        } else if (d != null) {
                            d.r();
                        }
                    }
                } else if (bVar.e().a) {
                    if (d != null) {
                        d.s();
                    }
                } else if (d != null) {
                    d.r();
                }
            } else if (bVar.e().a) {
                if (d != null) {
                    d.s();
                }
            } else if (d != null) {
                d.r();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.io.File r10, java.io.File r11, com.amap.api.mapcore.util.bj.c r12, com.amap.api.mapcore.util.bj.b r13) throws java.lang.Exception {
        /*
        r0 = new java.lang.StringBuffer;
        r0.<init>();
        r5 = r13.e();
        r2 = 0;
        if (r12 == 0) goto L_0x0049;
    L_0x000d:
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0080 }
        r1.<init>(r10);	 Catch:{ Exception -> 0x0080 }
        r4 = new java.util.zip.CheckedInputStream;	 Catch:{ Exception -> 0x0080 }
        r6 = new java.util.zip.CRC32;	 Catch:{ Exception -> 0x0080 }
        r6.<init>();	 Catch:{ Exception -> 0x0080 }
        r4.<init>(r1, r6);	 Catch:{ Exception -> 0x0080 }
        r6 = new java.util.zip.ZipInputStream;	 Catch:{ Exception -> 0x0080 }
        r6.<init>(r4);	 Catch:{ Exception -> 0x0080 }
    L_0x0021:
        r7 = r6.getNextEntry();	 Catch:{ Exception -> 0x0080 }
        if (r7 == 0) goto L_0x0039;
    L_0x0027:
        if (r5 == 0) goto L_0x006c;
    L_0x0029:
        r8 = r5.a;	 Catch:{ Exception -> 0x0080 }
        if (r8 == 0) goto L_0x006c;
    L_0x002d:
        r6.closeEntry();	 Catch:{ Exception -> 0x0080 }
        r6.close();	 Catch:{ Exception -> 0x0080 }
        r4.close();	 Catch:{ Exception -> 0x0080 }
        r1.close();	 Catch:{ Exception -> 0x0080 }
    L_0x0039:
        r0 = r0.toString();	 Catch:{ Exception -> 0x0080 }
        r13.a(r0);	 Catch:{ Exception -> 0x0080 }
        r6.close();	 Catch:{ Exception -> 0x0080 }
        r4.close();	 Catch:{ Exception -> 0x0080 }
        r1.close();	 Catch:{ Exception -> 0x0080 }
    L_0x0049:
        r6 = new java.io.FileInputStream;
        r6.<init>(r10);
        r7 = new java.util.zip.CheckedInputStream;
        r0 = new java.util.zip.CRC32;
        r0.<init>();
        r7.<init>(r6, r0);
        r1 = new java.util.zip.ZipInputStream;
        r1.<init>(r7);
        r0 = r11;
        r4 = r12;
        a(r0, r1, r2, r4, r5);
        r1.close();
        r7.close();
        r6.close();
        return;
    L_0x006c:
        r8 = r7.isDirectory();	 Catch:{ Exception -> 0x0080 }
        if (r8 != 0) goto L_0x0092;
    L_0x0072:
        r8 = r7.getName();	 Catch:{ Exception -> 0x0080 }
        r8 = a(r8);	 Catch:{ Exception -> 0x0080 }
        if (r8 != 0) goto L_0x0085;
    L_0x007c:
        r12.a();	 Catch:{ Exception -> 0x0080 }
        goto L_0x0039;
    L_0x0080:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0049;
    L_0x0085:
        r8 = r7.getName();	 Catch:{ Exception -> 0x0080 }
        r8 = r0.append(r8);	 Catch:{ Exception -> 0x0080 }
        r9 = ";";
        r8.append(r9);	 Catch:{ Exception -> 0x0080 }
    L_0x0092:
        r8 = r7.getSize();	 Catch:{ Exception -> 0x0080 }
        r2 = r2 + r8;
        r6.closeEntry();	 Catch:{ Exception -> 0x0080 }
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.bj.a(java.io.File, java.io.File, com.amap.api.mapcore.util.bj$c, com.amap.api.mapcore.util.bj$b):void");
    }

    private static void a(File file, ZipInputStream zipInputStream, long j, c cVar, a aVar) throws Exception {
        int i = 0;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            if (aVar == null || !aVar.a) {
                String str = file.getPath() + File.separator + nextEntry.getName();
                if (!a(nextEntry.getName())) {
                    break;
                }
                File file2 = new File(str);
                a(file2);
                int a = nextEntry.isDirectory() ? !file2.mkdirs() ? i : i : a(file2, zipInputStream, (long) i, j, cVar, aVar) + i;
                zipInputStream.closeEntry();
                i = a;
            } else {
                zipInputStream.closeEntry();
                return;
            }
        }
        if (cVar != null) {
            cVar.a();
        }
    }

    private static boolean a(String str) {
        if (str.contains("..") || str.contains(HttpUtils.PATHS_SEPARATOR) || str.contains("\\") || str.contains("%")) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.io.File r8, java.util.zip.ZipInputStream r9, long r10, long r12, com.amap.api.mapcore.util.bj.c r14, com.amap.api.mapcore.util.bj.a r15) throws java.lang.Exception {
        /*
        r0 = 0;
        r1 = new java.io.BufferedOutputStream;
        r2 = new java.io.FileOutputStream;
        r2.<init>(r8);
        r1.<init>(r2);
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = new byte[r2];
    L_0x000f:
        r3 = 0;
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = r9.read(r2, r3, r4);
        r4 = -1;
        if (r3 == r4) goto L_0x0040;
    L_0x0019:
        if (r15 == 0) goto L_0x0023;
    L_0x001b:
        r4 = r15.a;
        if (r4 == 0) goto L_0x0023;
    L_0x001f:
        r1.close();
    L_0x0022:
        return r0;
    L_0x0023:
        r4 = 0;
        r1.write(r2, r4, r3);
        r0 = r0 + r3;
        r4 = 0;
        r3 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1));
        if (r3 <= 0) goto L_0x000f;
    L_0x002e:
        if (r14 == 0) goto L_0x000f;
    L_0x0030:
        r4 = (long) r0;
        r4 = r4 + r10;
        r6 = 100;
        r4 = r4 * r6;
        r4 = r4 / r12;
        if (r15 == 0) goto L_0x003c;
    L_0x0038:
        r3 = r15.a;
        if (r3 != 0) goto L_0x000f;
    L_0x003c:
        r14.a(r4);
        goto L_0x000f;
    L_0x0040:
        r1.close();
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.bj.a(java.io.File, java.util.zip.ZipInputStream, long, long, com.amap.api.mapcore.util.bj$c, com.amap.api.mapcore.util.bj$a):int");
    }

    private static void a(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            a(parentFile);
            if (!parentFile.mkdir()) {
            }
        }
    }
}
