package com.tencent.bugly.proguard;

import android.text.TextUtils;
import com.tencent.bugly.beta.download.BetaReceiver;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.global.d;
import com.tencent.bugly.beta.ui.c;
import com.tencent.bugly.beta.utils.e;
import java.io.File;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: BUGLY */
public class t extends DownloadTask implements Runnable {
    public long k = 0;
    private File l;
    private long m = 0;

    public t(String str, String str2, long j, long j2, String str3) {
        super(str, "", "", str3);
        this.l = new File(str2);
        this.b = this.l.getParent();
        this.c = this.l.getName();
        this.e = j;
        this.f = j2;
        getStatus();
    }

    public t(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
        getStatus();
    }

    public File getSaveFile() {
        return this.l;
    }

    public void download() {
        if (getStatus() == 1) {
            b();
        } else if (getStatus() != 2) {
            if (getSaveFile() == null || !getSaveFile().exists()) {
                this.e = 0;
                this.f = 0;
                this.k = 0;
            } else {
                this.e = getSaveFile().length();
            }
            if (this.g) {
                c.a.a(this);
            }
            this.m = System.currentTimeMillis();
            this.i = 2;
            s.a.b.put(getDownloadUrl(), this);
            s.a.a(this);
        }
    }

    public void delete(boolean z) {
        stop();
        if (z) {
            if (!(getSaveFile() == null || !getSaveFile().exists() || getSaveFile().isDirectory())) {
                getSaveFile().delete();
            }
            p.a.b((DownloadTask) this);
        }
        BetaReceiver.netListeners.remove(getDownloadUrl());
        this.c = null;
        this.e = 0;
        this.f = 0;
        this.i = 4;
    }

    public void stop() {
        if (this.i != 5) {
            this.i = 3;
        }
    }

    public int getStatus() {
        if (getSaveFile() != null && getSaveFile().exists() && getSaveFile().length() == this.f && !s.a.b.contains(this)) {
            this.e = this.f;
            this.i = 1;
        }
        if (getSaveFile() != null && getSaveFile().exists() && getSaveFile().length() > 0 && getSaveFile().length() < this.f && !s.a.b.contains(this)) {
            this.e = getSaveFile().length();
            this.i = 3;
        }
        if ((getSaveFile() == null || !getSaveFile().exists()) && !s.a.b.contains(this)) {
            this.i = 0;
        }
        return this.i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r12 = this;
        r5 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x00a3 }
        r0 = r12.getDownloadUrl();	 Catch:{ MalformedURLException -> 0x00a3 }
        r5.<init>(r0);	 Catch:{ MalformedURLException -> 0x00a3 }
        r2 = 0;
        r1 = 0;
        r0 = 0;
    L_0x000c:
        r3 = 3;
        if (r0 >= r3) goto L_0x0159;
    L_0x000f:
        r4 = r0 + 1;
        r0 = r5.openConnection();	 Catch:{ IOException -> 0x01d6 }
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ IOException -> 0x01d6 }
        r3 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        r0.setConnectTimeout(r3);	 Catch:{ IOException -> 0x01d6 }
        r3 = "GET";
        r0.setRequestMethod(r3);	 Catch:{ IOException -> 0x01d6 }
        r3 = "Referer";
        r6 = r5.toString();	 Catch:{ IOException -> 0x01d6 }
        r0.setRequestProperty(r3, r6);	 Catch:{ IOException -> 0x01d6 }
        r3 = "Charset";
        r6 = "UTF-8";
        r0.setRequestProperty(r3, r6);	 Catch:{ IOException -> 0x01d6 }
        r3 = "Range";
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01d6 }
        r6.<init>();	 Catch:{ IOException -> 0x01d6 }
        r7 = "bytes=";
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x01d6 }
        r8 = r12.e;	 Catch:{ IOException -> 0x01d6 }
        r6 = r6.append(r8);	 Catch:{ IOException -> 0x01d6 }
        r7 = "-";
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x01d6 }
        r6 = r6.toString();	 Catch:{ IOException -> 0x01d6 }
        r0.setRequestProperty(r3, r6);	 Catch:{ IOException -> 0x01d6 }
        r3 = "Connection";
        r6 = "Keep-Alive";
        r0.setRequestProperty(r3, r6);	 Catch:{ IOException -> 0x01d6 }
        r0.connect();	 Catch:{ IOException -> 0x01d6 }
        r3 = r12.a(r0);	 Catch:{ IOException -> 0x01d6 }
        r12.c = r3;	 Catch:{ IOException -> 0x01d6 }
        r3 = new java.io.File;	 Catch:{ IOException -> 0x01d6 }
        r6 = r12.b;	 Catch:{ IOException -> 0x01d6 }
        r3.<init>(r6);	 Catch:{ IOException -> 0x01d6 }
        r6 = r3.exists();	 Catch:{ IOException -> 0x01d6 }
        if (r6 != 0) goto L_0x0071;
    L_0x006e:
        r3.mkdirs();	 Catch:{ IOException -> 0x01d6 }
    L_0x0071:
        r6 = new java.io.File;	 Catch:{ IOException -> 0x01d6 }
        r7 = r12.c;	 Catch:{ IOException -> 0x01d6 }
        r6.<init>(r3, r7);	 Catch:{ IOException -> 0x01d6 }
        r12.l = r6;	 Catch:{ IOException -> 0x01d6 }
        r6 = r12.f;	 Catch:{ IOException -> 0x01d6 }
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 != 0) goto L_0x00bd;
    L_0x0082:
        r3 = r0.getContentLength();	 Catch:{ IOException -> 0x01d6 }
        r6 = (long) r3;	 Catch:{ IOException -> 0x01d6 }
        r12.f = r6;	 Catch:{ IOException -> 0x01d6 }
        r6 = r12.f;	 Catch:{ IOException -> 0x01d6 }
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 > 0) goto L_0x00bd;
    L_0x0091:
        r0 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r3 = "tLen <= 0 ";
        r12.a(r0, r3);	 Catch:{ IOException -> 0x01d6 }
        if (r1 == 0) goto L_0x009d;
    L_0x009a:
        r1.close();	 Catch:{ Exception -> 0x01a5 }
    L_0x009d:
        if (r2 == 0) goto L_0x00a2;
    L_0x009f:
        r2.close();	 Catch:{ Exception -> 0x00b8 }
    L_0x00a2:
        return;
    L_0x00a3:
        r0 = move-exception;
        r1 = r0.getMessage();
        r2 = 0;
        r2 = new java.lang.Object[r2];
        com.tencent.bugly.proguard.an.a(r1, r2);
        r1 = 2010; // 0x7da float:2.817E-42 double:9.93E-321;
        r0 = r0.getMessage();
        r12.a(r1, r0);
        goto L_0x00a2;
    L_0x00b8:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00a2;
    L_0x00bd:
        r3 = com.tencent.bugly.proguard.p.a;	 Catch:{ IOException -> 0x01d6 }
        r3.a(r12);	 Catch:{ IOException -> 0x01d6 }
        r3 = r0.getInputStream();	 Catch:{ IOException -> 0x01d6 }
        r0 = 307200; // 0x4b000 float:4.30479E-40 double:1.51777E-318;
        r6 = new byte[r0];	 Catch:{ IOException -> 0x01d8, all -> 0x01d3 }
        r2 = new java.io.RandomAccessFile;	 Catch:{ IOException -> 0x01d8, all -> 0x01d3 }
        r0 = r12.l;	 Catch:{ IOException -> 0x01d8, all -> 0x01d3 }
        r7 = "rwd";
        r2.<init>(r0, r7);	 Catch:{ IOException -> 0x01d8, all -> 0x01d3 }
        r0 = r12.e;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r2.seek(r0);	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r1 = 0;
    L_0x00da:
        r7 = r3.read(r6);	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r0 = -1;
        if (r7 == r0) goto L_0x014f;
    L_0x00e1:
        r8 = r12.e;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r10 = (long) r7;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r8 = r8 + r10;
        r12.e = r8;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r8 = r12.e;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r10 = r12.f;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r0 <= 0) goto L_0x010a;
    L_0x00ef:
        r12.b();	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r0 = "mSavedLength > mTotalLength,重新下载!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        com.tencent.bugly.proguard.an.e(r0, r1);	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        if (r2 == 0) goto L_0x00ff;
    L_0x00fc:
        r2.close();	 Catch:{ Exception -> 0x01ce, all -> 0x01bf }
    L_0x00ff:
        if (r3 == 0) goto L_0x00a2;
    L_0x0101:
        r3.close();	 Catch:{ Exception -> 0x0105 }
        goto L_0x00a2;
    L_0x0105:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00a2;
    L_0x010a:
        r0 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r8 = r12.e;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r8 = (float) r8;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r10 = r12.f;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r9 = (float) r10;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r8 = r8 / r9;
        r0 = r0 * r8;
        r8 = r0 - r1;
        r8 = (double) r8;	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r10 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r8 < 0) goto L_0x01df;
    L_0x011d:
        r12.a();	 Catch:{ IOException -> 0x017b, all -> 0x019c }
    L_0x0120:
        r1 = 0;
        r2.write(r6, r1, r7);	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r1 = r12.getSaveFile();	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        if (r1 == 0) goto L_0x013b;
    L_0x012a:
        r1 = r12.getSaveFile();	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r1 = r1.exists();	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        if (r1 == 0) goto L_0x013b;
    L_0x0134:
        r1 = r12.getStatus();	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        r7 = 3;
        if (r1 != r7) goto L_0x014d;
    L_0x013b:
        if (r2 == 0) goto L_0x0140;
    L_0x013d:
        r2.close();	 Catch:{ Exception -> 0x01ce, all -> 0x01bf }
    L_0x0140:
        if (r3 == 0) goto L_0x00a2;
    L_0x0142:
        r3.close();	 Catch:{ Exception -> 0x0147 }
        goto L_0x00a2;
    L_0x0147:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00a2;
    L_0x014d:
        r1 = r0;
        goto L_0x00da;
    L_0x014f:
        r12.b();	 Catch:{ IOException -> 0x017b, all -> 0x019c }
        if (r2 == 0) goto L_0x01db;
    L_0x0154:
        r2.close();	 Catch:{ Exception -> 0x01ce, all -> 0x01bf }
        r0 = r4;
        r2 = r3;
    L_0x0159:
        r1 = 3;
        if (r0 < r1) goto L_0x016e;
    L_0x015c:
        r0 = com.tencent.bugly.proguard.t.class;
        r1 = "have retry %d times";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x01a5 }
        r4 = 0;
        r5 = 3;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x01a5 }
        r3[r4] = r5;	 Catch:{ Exception -> 0x01a5 }
        com.tencent.bugly.proguard.an.b(r0, r1, r3);	 Catch:{ Exception -> 0x01a5 }
    L_0x016e:
        if (r2 == 0) goto L_0x00a2;
    L_0x0170:
        r2.close();	 Catch:{ Exception -> 0x0175 }
        goto L_0x00a2;
    L_0x0175:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00a2;
    L_0x017b:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
    L_0x017e:
        r0.printStackTrace();	 Catch:{ all -> 0x01d1 }
        r3 = 2020; // 0x7e4 float:2.83E-42 double:9.98E-321;
        r0 = r0.getMessage();	 Catch:{ all -> 0x01d1 }
        r12.a(r3, r0);	 Catch:{ all -> 0x01d1 }
        r0 = com.tencent.bugly.proguard.t.class;
        r3 = "IOException,stop download!";
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x01d1 }
        com.tencent.bugly.proguard.an.b(r0, r3, r6);	 Catch:{ all -> 0x01d1 }
        if (r1 == 0) goto L_0x0199;
    L_0x0196:
        r1.close();	 Catch:{ Exception -> 0x01a5 }
    L_0x0199:
        r0 = r4;
        goto L_0x000c;
    L_0x019c:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
    L_0x019f:
        if (r1 == 0) goto L_0x01a4;
    L_0x01a1:
        r1.close();	 Catch:{ Exception -> 0x01a5 }
    L_0x01a4:
        throw r0;	 Catch:{ Exception -> 0x01a5 }
    L_0x01a5:
        r0 = move-exception;
    L_0x01a6:
        r1 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r3 = r0.getMessage();	 Catch:{ all -> 0x01cc }
        r12.a(r1, r3);	 Catch:{ all -> 0x01cc }
        r0.printStackTrace();	 Catch:{ all -> 0x01cc }
        if (r2 == 0) goto L_0x00a2;
    L_0x01b4:
        r2.close();	 Catch:{ Exception -> 0x01b9 }
        goto L_0x00a2;
    L_0x01b9:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00a2;
    L_0x01bf:
        r0 = move-exception;
        r2 = r3;
    L_0x01c1:
        if (r2 == 0) goto L_0x01c6;
    L_0x01c3:
        r2.close();	 Catch:{ Exception -> 0x01c7 }
    L_0x01c6:
        throw r0;
    L_0x01c7:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x01c6;
    L_0x01cc:
        r0 = move-exception;
        goto L_0x01c1;
    L_0x01ce:
        r0 = move-exception;
        r2 = r3;
        goto L_0x01a6;
    L_0x01d1:
        r0 = move-exception;
        goto L_0x019f;
    L_0x01d3:
        r0 = move-exception;
        r2 = r3;
        goto L_0x019f;
    L_0x01d6:
        r0 = move-exception;
        goto L_0x017e;
    L_0x01d8:
        r0 = move-exception;
        r2 = r3;
        goto L_0x017e;
    L_0x01db:
        r0 = r4;
        r2 = r3;
        goto L_0x0159;
    L_0x01df:
        r0 = r1;
        goto L_0x0120;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.t.run():void");
    }

    private String a(HttpURLConnection httpURLConnection) {
        try {
            if (!TextUtils.isEmpty(this.c)) {
                return this.c;
            }
            Map headerFields = httpURLConnection.getHeaderFields();
            if (headerFields != null) {
                for (String str : headerFields.keySet()) {
                    if (str != null) {
                        List<String> list = (List) headerFields.get(str);
                        if (list != null) {
                            for (String str2 : list) {
                                if (str2 != null && "content-disposition".equals(str.toLowerCase())) {
                                    Matcher matcher = Pattern.compile(".*filename=(.*)").matcher(str2.toLowerCase());
                                    if (matcher.find()) {
                                        return matcher.group(1);
                                    }
                                }
                            }
                            continue;
                        } else {
                            continue;
                        }
                    }
                }
            }
            String str3 = getDownloadUrl().substring(getDownloadUrl().lastIndexOf(47) + 1);
            if (!TextUtils.isEmpty(str3)) {
                return str3;
            }
            return UUID.randomUUID() + ".apk";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a(int i, String str) {
        this.i = 5;
        c.a.a();
        s.a.b.remove(getDownloadUrl());
        e.a(new d(10, this.d, this, Integer.valueOf(i), str));
    }

    protected void a() {
        this.k += System.currentTimeMillis() - this.m;
        p.a.a((DownloadTask) this);
        this.m = System.currentTimeMillis();
        c.a.a();
        e.a(new d(9, this.d, this));
    }

    protected void b() {
        this.i = 1;
        a();
        s.a.b.remove(getDownloadUrl());
        BetaReceiver.netListeners.remove(getDownloadUrl());
        e.a(new d(8, this.d, this));
    }

    public long getCostTime() {
        return this.k;
    }
}
