package com.amap.api.mapcore.util;

import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.maps.AMapException;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.connect.common.Constants;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/* compiled from: HttpUrlUtil */
public class iw {
    private int a;
    private int b;
    private boolean c;
    private SSLContext d;
    private Proxy e;
    private volatile boolean f;
    private long g;
    private long h;
    private String i;
    private com.amap.api.mapcore.util.it.a j;
    private a k;

    /* compiled from: HttpUrlUtil */
    private static class a implements HostnameVerifier {
        private String a;

        private a() {
        }

        public void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.a = str;
            }
        }

        public boolean verify(String str, SSLSession sSLSession) {
            HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            if (!TextUtils.isEmpty(this.a)) {
                String str2 = this.a;
                this.a = null;
                if (str2.equals(str)) {
                    return true;
                }
            }
            if (defaultHostnameVerifier.verify(str, sSLSession) || defaultHostnameVerifier.verify(str, sSLSession)) {
                return true;
            }
            return false;
        }
    }

    private void b() {
        try {
            this.i = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            gw.a(th, "ht", "ic");
        }
    }

    iw(int i, int i2, Proxy proxy, boolean z) {
        this(i, i2, proxy, z, null);
    }

    iw(int i, int i2, Proxy proxy, boolean z, com.amap.api.mapcore.util.it.a aVar) {
        this.f = false;
        this.g = -1;
        this.h = 0;
        this.k = new a();
        this.a = i;
        this.b = i2;
        this.e = proxy;
        this.c = z;
        this.j = aVar;
        b();
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                this.d = instance;
            } catch (Throwable th) {
                gw.a(th, "ht", "ne");
            }
        }
    }

    iw(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    void a() {
        this.f = true;
    }

    void a(long j) {
        this.h = j;
    }

    void b(long j) {
        this.g = j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void a(java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, java.util.Map<java.lang.String, java.lang.String> r13, com.amap.api.mapcore.util.iv.a r14) {
        /*
        r10 = this;
        r1 = 0;
        r0 = 1;
        r8 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = 0;
        r2 = 0;
        r4 = 0;
        if (r14 != 0) goto L_0x002f;
    L_0x0009:
        if (r1 == 0) goto L_0x000e;
    L_0x000b:
        r2.close();	 Catch:{ IOException -> 0x0014, Throwable -> 0x001d }
    L_0x000e:
        if (r1 == 0) goto L_0x0013;
    L_0x0010:
        r4.disconnect();	 Catch:{ Throwable -> 0x0026 }
    L_0x0013:
        return;
    L_0x0014:
        r0 = move-exception;
        r2 = "ht";
        r3 = "mdr";
        com.amap.api.mapcore.util.gw.a(r0, r2, r3);
        goto L_0x000e;
    L_0x001d:
        r0 = move-exception;
        r2 = "ht";
        r3 = "mdr";
        com.amap.api.mapcore.util.gw.a(r0, r2, r3);
        goto L_0x000e;
    L_0x0026:
        r0 = move-exception;
        r1 = "ht";
        r2 = "mdr";
        com.amap.api.mapcore.util.gw.a(r0, r1, r2);
        goto L_0x0013;
    L_0x002f:
        r2 = a(r13);	 Catch:{ Throwable -> 0x0188, all -> 0x0180 }
        r4 = new java.lang.StringBuffer;	 Catch:{ Throwable -> 0x0188, all -> 0x0180 }
        r4.<init>();	 Catch:{ Throwable -> 0x0188, all -> 0x0180 }
        r4.append(r11);	 Catch:{ Throwable -> 0x0188, all -> 0x0180 }
        if (r2 == 0) goto L_0x0046;
    L_0x003d:
        r5 = "?";
        r5 = r4.append(r5);	 Catch:{ Throwable -> 0x0188, all -> 0x0180 }
        r5.append(r2);	 Catch:{ Throwable -> 0x0188, all -> 0x0180 }
    L_0x0046:
        r2 = r4.toString();	 Catch:{ Throwable -> 0x0188, all -> 0x0180 }
        r4 = 0;
        r2 = r10.a(r2, r12, r4);	 Catch:{ Throwable -> 0x0188, all -> 0x0180 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r4.<init>();	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r5 = "bytes=";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r6 = r10.h;	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r4 = r4.append(r6);	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r5 = "-";
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r5 = "RANGE";
        r2.setRequestProperty(r5, r4);	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r2.connect();	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r5 = r2.getResponseCode();	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 == r4) goto L_0x0101;
    L_0x007a:
        r4 = r0;
    L_0x007b:
        r6 = 206; // 0xce float:2.89E-43 double:1.02E-321;
        if (r5 == r6) goto L_0x0104;
    L_0x007f:
        r0 = r0 & r4;
        if (r0 == 0) goto L_0x00ab;
    L_0x0082:
        r0 = new com.amap.api.mapcore.util.gp;	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r3.<init>();	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r4 = "网络异常原因：";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r4 = r2.getResponseMessage();	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r4 = " 网络异常状态码：";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r3 = r3.append(r5);	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r14.a(r0);	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
    L_0x00ab:
        r1 = r2.getInputStream();	 Catch:{ Throwable -> 0x018c, all -> 0x0114 }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
    L_0x00b3:
        r3 = java.lang.Thread.interrupted();	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        if (r3 != 0) goto L_0x0120;
    L_0x00b9:
        r3 = r10.f;	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        if (r3 != 0) goto L_0x0120;
    L_0x00bd:
        r3 = 0;
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = r1.read(r0, r3, r4);	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        if (r3 <= 0) goto L_0x0120;
    L_0x00c6:
        r4 = r10.g;	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        r6 = -1;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 == 0) goto L_0x00d6;
    L_0x00ce:
        r4 = r10.h;	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        r6 = r10.g;	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 >= 0) goto L_0x0120;
    L_0x00d6:
        if (r3 != r8) goto L_0x0107;
    L_0x00d8:
        r4 = r10.h;	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        r14.a(r0, r4);	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
    L_0x00dd:
        r4 = r10.h;	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        r6 = (long) r3;	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        r4 = r4 + r6;
        r10.h = r4;	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        goto L_0x00b3;
    L_0x00e4:
        r0 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r9;
    L_0x00e8:
        r14.a(r0);	 Catch:{ all -> 0x0183 }
        if (r2 == 0) goto L_0x00f0;
    L_0x00ed:
        r2.close();	 Catch:{ IOException -> 0x0153, Throwable -> 0x015c }
    L_0x00f0:
        if (r1 == 0) goto L_0x0013;
    L_0x00f2:
        r1.disconnect();	 Catch:{ Throwable -> 0x00f7 }
        goto L_0x0013;
    L_0x00f7:
        r0 = move-exception;
        r1 = "ht";
        r2 = "mdr";
        com.amap.api.mapcore.util.gw.a(r0, r1, r2);
        goto L_0x0013;
    L_0x0101:
        r4 = r3;
        goto L_0x007b;
    L_0x0104:
        r0 = r3;
        goto L_0x007f;
    L_0x0107:
        r4 = new byte[r3];	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        r5 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r0, r5, r4, r6, r3);	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        r6 = r10.h;	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        r14.a(r4, r6);	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        goto L_0x00dd;
    L_0x0114:
        r0 = move-exception;
    L_0x0115:
        if (r1 == 0) goto L_0x011a;
    L_0x0117:
        r1.close();	 Catch:{ IOException -> 0x0165, Throwable -> 0x016e }
    L_0x011a:
        if (r2 == 0) goto L_0x011f;
    L_0x011c:
        r2.disconnect();	 Catch:{ Throwable -> 0x0177 }
    L_0x011f:
        throw r0;
    L_0x0120:
        r0 = r10.f;	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        if (r0 == 0) goto L_0x013d;
    L_0x0124:
        r14.d();	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
    L_0x0127:
        if (r1 == 0) goto L_0x012c;
    L_0x0129:
        r1.close();	 Catch:{ IOException -> 0x0141, Throwable -> 0x014a }
    L_0x012c:
        if (r2 == 0) goto L_0x0013;
    L_0x012e:
        r2.disconnect();	 Catch:{ Throwable -> 0x0133 }
        goto L_0x0013;
    L_0x0133:
        r0 = move-exception;
        r1 = "ht";
        r2 = "mdr";
        com.amap.api.mapcore.util.gw.a(r0, r1, r2);
        goto L_0x0013;
    L_0x013d:
        r14.e();	 Catch:{ Throwable -> 0x00e4, all -> 0x0114 }
        goto L_0x0127;
    L_0x0141:
        r0 = move-exception;
        r1 = "ht";
        r3 = "mdr";
        com.amap.api.mapcore.util.gw.a(r0, r1, r3);
        goto L_0x012c;
    L_0x014a:
        r0 = move-exception;
        r1 = "ht";
        r3 = "mdr";
        com.amap.api.mapcore.util.gw.a(r0, r1, r3);
        goto L_0x012c;
    L_0x0153:
        r0 = move-exception;
        r2 = "ht";
        r3 = "mdr";
        com.amap.api.mapcore.util.gw.a(r0, r2, r3);
        goto L_0x00f0;
    L_0x015c:
        r0 = move-exception;
        r2 = "ht";
        r3 = "mdr";
        com.amap.api.mapcore.util.gw.a(r0, r2, r3);
        goto L_0x00f0;
    L_0x0165:
        r1 = move-exception;
        r3 = "ht";
        r4 = "mdr";
        com.amap.api.mapcore.util.gw.a(r1, r3, r4);
        goto L_0x011a;
    L_0x016e:
        r1 = move-exception;
        r3 = "ht";
        r4 = "mdr";
        com.amap.api.mapcore.util.gw.a(r1, r3, r4);
        goto L_0x011a;
    L_0x0177:
        r1 = move-exception;
        r2 = "ht";
        r3 = "mdr";
        com.amap.api.mapcore.util.gw.a(r1, r2, r3);
        goto L_0x011f;
    L_0x0180:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0115;
    L_0x0183:
        r0 = move-exception;
        r9 = r1;
        r1 = r2;
        r2 = r9;
        goto L_0x0115;
    L_0x0188:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00e8;
    L_0x018c:
        r0 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00e8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.iw.a(java.lang.String, java.util.Map, java.util.Map, com.amap.api.mapcore.util.iv$a):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    com.amap.api.mapcore.util.jb a(java.lang.String r6, java.util.Map<java.lang.String, java.lang.String> r7, java.util.Map<java.lang.String, java.lang.String> r8) throws com.amap.api.mapcore.util.gp {
        /*
        r5 = this;
        r0 = 0;
        r1 = a(r8);	 Catch:{ ConnectException -> 0x0034, MalformedURLException -> 0x0047, UnknownHostException -> 0x0050, SocketException -> 0x0059, SocketTimeoutException -> 0x0062, InterruptedIOException -> 0x006b, IOException -> 0x0074, gp -> 0x007d, Throwable -> 0x0084, all -> 0x009c }
        r2 = new java.lang.StringBuffer;	 Catch:{ ConnectException -> 0x0034, MalformedURLException -> 0x0047, UnknownHostException -> 0x0050, SocketException -> 0x0059, SocketTimeoutException -> 0x0062, InterruptedIOException -> 0x006b, IOException -> 0x0074, gp -> 0x007d, Throwable -> 0x0084, all -> 0x009c }
        r2.<init>();	 Catch:{ ConnectException -> 0x0034, MalformedURLException -> 0x0047, UnknownHostException -> 0x0050, SocketException -> 0x0059, SocketTimeoutException -> 0x0062, InterruptedIOException -> 0x006b, IOException -> 0x0074, gp -> 0x007d, Throwable -> 0x0084, all -> 0x009c }
        r2.append(r6);	 Catch:{ ConnectException -> 0x0034, MalformedURLException -> 0x0047, UnknownHostException -> 0x0050, SocketException -> 0x0059, SocketTimeoutException -> 0x0062, InterruptedIOException -> 0x006b, IOException -> 0x0074, gp -> 0x007d, Throwable -> 0x0084, all -> 0x009c }
        if (r1 == 0) goto L_0x0018;
    L_0x000f:
        r3 = "?";
        r3 = r2.append(r3);	 Catch:{ ConnectException -> 0x0034, MalformedURLException -> 0x0047, UnknownHostException -> 0x0050, SocketException -> 0x0059, SocketTimeoutException -> 0x0062, InterruptedIOException -> 0x006b, IOException -> 0x0074, gp -> 0x007d, Throwable -> 0x0084, all -> 0x009c }
        r3.append(r1);	 Catch:{ ConnectException -> 0x0034, MalformedURLException -> 0x0047, UnknownHostException -> 0x0050, SocketException -> 0x0059, SocketTimeoutException -> 0x0062, InterruptedIOException -> 0x006b, IOException -> 0x0074, gp -> 0x007d, Throwable -> 0x0084, all -> 0x009c }
    L_0x0018:
        r1 = r2.toString();	 Catch:{ ConnectException -> 0x0034, MalformedURLException -> 0x0047, UnknownHostException -> 0x0050, SocketException -> 0x0059, SocketTimeoutException -> 0x0062, InterruptedIOException -> 0x006b, IOException -> 0x0074, gp -> 0x007d, Throwable -> 0x0084, all -> 0x009c }
        r2 = 0;
        r0 = r5.a(r1, r7, r2);	 Catch:{ ConnectException -> 0x0034, MalformedURLException -> 0x0047, UnknownHostException -> 0x0050, SocketException -> 0x0059, SocketTimeoutException -> 0x0062, InterruptedIOException -> 0x006b, IOException -> 0x0074, gp -> 0x007d, Throwable -> 0x0084, all -> 0x009c }
        r1 = r5.a(r0);	 Catch:{ ConnectException -> 0x0034, MalformedURLException -> 0x0047, UnknownHostException -> 0x0050, SocketException -> 0x0059, SocketTimeoutException -> 0x0062, InterruptedIOException -> 0x006b, IOException -> 0x0074, gp -> 0x00a6, Throwable -> 0x00a1 }
        if (r0 == 0) goto L_0x002a;
    L_0x0027:
        r0.disconnect();	 Catch:{ Throwable -> 0x002b }
    L_0x002a:
        return r1;
    L_0x002b:
        r0 = move-exception;
        r2 = "ht";
        r3 = "mgr";
        com.amap.api.mapcore.util.gw.a(r0, r2, r3);
        goto L_0x002a;
    L_0x0034:
        r1 = move-exception;
        r1 = new com.amap.api.mapcore.util.gp;	 Catch:{ all -> 0x003d }
        r2 = "http连接失败 - ConnectionException";
        r1.<init>(r2);	 Catch:{ all -> 0x003d }
        throw r1;	 Catch:{ all -> 0x003d }
    L_0x003d:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0041:
        if (r1 == 0) goto L_0x0046;
    L_0x0043:
        r1.disconnect();	 Catch:{ Throwable -> 0x0093 }
    L_0x0046:
        throw r0;
    L_0x0047:
        r1 = move-exception;
        r1 = new com.amap.api.mapcore.util.gp;	 Catch:{ all -> 0x003d }
        r2 = "url异常 - MalformedURLException";
        r1.<init>(r2);	 Catch:{ all -> 0x003d }
        throw r1;	 Catch:{ all -> 0x003d }
    L_0x0050:
        r1 = move-exception;
        r1 = new com.amap.api.mapcore.util.gp;	 Catch:{ all -> 0x003d }
        r2 = "未知主机 - UnKnowHostException";
        r1.<init>(r2);	 Catch:{ all -> 0x003d }
        throw r1;	 Catch:{ all -> 0x003d }
    L_0x0059:
        r1 = move-exception;
        r1 = new com.amap.api.mapcore.util.gp;	 Catch:{ all -> 0x003d }
        r2 = "socket 连接异常 - SocketException";
        r1.<init>(r2);	 Catch:{ all -> 0x003d }
        throw r1;	 Catch:{ all -> 0x003d }
    L_0x0062:
        r1 = move-exception;
        r1 = new com.amap.api.mapcore.util.gp;	 Catch:{ all -> 0x003d }
        r2 = "socket 连接超时 - SocketTimeoutException";
        r1.<init>(r2);	 Catch:{ all -> 0x003d }
        throw r1;	 Catch:{ all -> 0x003d }
    L_0x006b:
        r1 = move-exception;
        r1 = new com.amap.api.mapcore.util.gp;	 Catch:{ all -> 0x003d }
        r2 = "未知的错误";
        r1.<init>(r2);	 Catch:{ all -> 0x003d }
        throw r1;	 Catch:{ all -> 0x003d }
    L_0x0074:
        r1 = move-exception;
        r1 = new com.amap.api.mapcore.util.gp;	 Catch:{ all -> 0x003d }
        r2 = "IO 操作异常 - IOException";
        r1.<init>(r2);	 Catch:{ all -> 0x003d }
        throw r1;	 Catch:{ all -> 0x003d }
    L_0x007d:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0081:
        throw r0;	 Catch:{ all -> 0x0082 }
    L_0x0082:
        r0 = move-exception;
        goto L_0x0041;
    L_0x0084:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0088:
        r0.printStackTrace();	 Catch:{ all -> 0x0082 }
        r0 = new com.amap.api.mapcore.util.gp;	 Catch:{ all -> 0x0082 }
        r2 = "未知的错误";
        r0.<init>(r2);	 Catch:{ all -> 0x0082 }
        throw r0;	 Catch:{ all -> 0x0082 }
    L_0x0093:
        r1 = move-exception;
        r2 = "ht";
        r3 = "mgr";
        com.amap.api.mapcore.util.gw.a(r1, r2, r3);
        goto L_0x0046;
    L_0x009c:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0041;
    L_0x00a1:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0088;
    L_0x00a6:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0081;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.iw.a(java.lang.String, java.util.Map, java.util.Map):com.amap.api.mapcore.util.jb");
    }

    jb a(String str, Map<String, String> map, byte[] bArr) throws gp {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = a(str, (Map) map, true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            jb a = a(httpURLConnection);
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th) {
                    gw.a(th, "ht", "mPt");
                }
            }
            return a;
        } catch (ConnectException e) {
            e.printStackTrace();
            throw new gp(AMapException.ERROR_CONNECTION);
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            throw new gp(AMapException.ERROR_URL);
        } catch (UnknownHostException e3) {
            e3.printStackTrace();
            throw new gp(AMapException.ERROR_UNKNOW_HOST);
        } catch (SocketException e4) {
            e4.printStackTrace();
            throw new gp(AMapException.ERROR_SOCKET);
        } catch (SocketTimeoutException e5) {
            e5.printStackTrace();
            throw new gp(AMapException.ERROR_SOCKE_TIME_OUT);
        } catch (InterruptedIOException e6) {
            throw new gp(AMapException.ERROR_UNKNOWN);
        } catch (IOException e7) {
            e7.printStackTrace();
            throw new gp(AMapException.ERROR_IO);
        } catch (Throwable e8) {
            gw.a(e8, "ht", "mPt");
            throw e8;
        } catch (Throwable th2) {
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th3) {
                    gw.a(th3, "ht", "mPt");
                }
            }
        }
    }

    private String a(int i, String str, Map<String, String> map) {
        String str2 = "";
        switch (i) {
            case 1:
                str2 = it.b;
                break;
        }
        if (!TextUtils.isEmpty(str2)) {
            Uri parse = Uri.parse(str);
            String host = parse.getHost();
            str = parse.buildUpon().encodedAuthority(str2).build().toString();
            if (map != null) {
                map.put("targetHost", host);
            }
            if (this.c && this.k != null) {
                this.k.a(str2);
            }
        }
        return str;
    }

    HttpURLConnection a(String str, Map<String, String> map, boolean z) throws IOException {
        Map hashMap;
        HttpURLConnection httpURLConnection;
        gd.b();
        URLConnection uRLConnection = null;
        if (map == null) {
            hashMap = new HashMap();
        }
        URL url = new URL(a(it.a, str, hashMap));
        if (this.j != null) {
            uRLConnection = this.j.a(this.e, url);
        }
        if (uRLConnection == null) {
            if (this.e != null) {
                uRLConnection = url.openConnection(this.e);
            } else {
                uRLConnection = url.openConnection();
            }
        }
        if (this.c) {
            httpURLConnection = (HttpsURLConnection) uRLConnection;
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.d.getSocketFactory());
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(this.k);
        } else {
            httpURLConnection = (HttpURLConnection) uRLConnection;
        }
        if (VERSION.SDK != null && VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, HttpHeaders.HEAD_VALUE_CONNECTION_CLOSE);
        }
        a(hashMap, httpURLConnection);
        if (z) {
            httpURLConnection.setRequestMethod(Constants.HTTP_POST);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setRequestMethod(Constants.HTTP_GET);
            httpURLConnection.setDoInput(true);
        }
        return httpURLConnection;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.amap.api.mapcore.util.jb a(java.net.HttpURLConnection r11) throws com.amap.api.mapcore.util.gp, java.io.IOException {
        /*
        r10 = this;
        r2 = 0;
        r1 = "";
        r11.connect();	 Catch:{ IOException -> 0x015c, all -> 0x0147 }
        r6 = r11.getHeaderFields();	 Catch:{ IOException -> 0x015c, all -> 0x0147 }
        r3 = r11.getResponseCode();	 Catch:{ IOException -> 0x015c, all -> 0x0147 }
        if (r6 == 0) goto L_0x016c;
    L_0x0010:
        r0 = "gsid";
        r0 = r6.get(r0);	 Catch:{ IOException -> 0x015c, all -> 0x0147 }
        r0 = (java.util.List) r0;	 Catch:{ IOException -> 0x015c, all -> 0x0147 }
        if (r0 == 0) goto L_0x016c;
    L_0x001a:
        r4 = r0.size();	 Catch:{ IOException -> 0x015c, all -> 0x0147 }
        if (r4 <= 0) goto L_0x016c;
    L_0x0020:
        r4 = 0;
        r0 = r0.get(r4);	 Catch:{ IOException -> 0x015c, all -> 0x0147 }
        r0 = (java.lang.String) r0;	 Catch:{ IOException -> 0x015c, all -> 0x0147 }
    L_0x0027:
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 == r1) goto L_0x0090;
    L_0x002b:
        r1 = new com.amap.api.mapcore.util.gp;	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r4.<init>();	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r5 = "网络异常原因：";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r5 = r11.getResponseMessage();	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r5 = " 网络异常状态码：";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r4 = r4.append(r3);	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r5 = "  ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r4 = r4.append(r0);	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r5 = r10.i;	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r1.<init>(r4, r0);	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r1.a(r3);	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        throw r1;	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
    L_0x006b:
        r1 = move-exception;
        r1 = r2;
        r3 = r2;
        r4 = r2;
    L_0x006f:
        r5 = new com.amap.api.mapcore.util.gp;	 Catch:{ all -> 0x0077 }
        r6 = "IO 操作异常 - IOException";
        r5.<init>(r6, r0);	 Catch:{ all -> 0x0077 }
        throw r5;	 Catch:{ all -> 0x0077 }
    L_0x0077:
        r0 = move-exception;
        r9 = r1;
        r1 = r2;
        r2 = r9;
    L_0x007b:
        if (r4 == 0) goto L_0x0080;
    L_0x007d:
        r4.close();	 Catch:{ Throwable -> 0x00fb }
    L_0x0080:
        if (r3 == 0) goto L_0x0085;
    L_0x0082:
        r3.close();	 Catch:{ Throwable -> 0x0105 }
    L_0x0085:
        if (r2 == 0) goto L_0x008a;
    L_0x0087:
        r2.close();	 Catch:{ Throwable -> 0x010f }
    L_0x008a:
        if (r1 == 0) goto L_0x008f;
    L_0x008c:
        r1.close();	 Catch:{ Throwable -> 0x0119 }
    L_0x008f:
        throw r0;
    L_0x0090:
        r4 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r4.<init>();	 Catch:{ IOException -> 0x006b, all -> 0x0147 }
        r3 = r11.getInputStream();	 Catch:{ IOException -> 0x0163, all -> 0x014d }
        r1 = new java.io.PushbackInputStream;	 Catch:{ IOException -> 0x0168, all -> 0x0152 }
        r5 = 2;
        r1.<init>(r3, r5);	 Catch:{ IOException -> 0x0168, all -> 0x0152 }
        r5 = 2;
        r5 = new byte[r5];	 Catch:{ all -> 0x0156 }
        r1.read(r5);	 Catch:{ all -> 0x0156 }
        r1.unread(r5);	 Catch:{ all -> 0x0156 }
        r7 = 0;
        r7 = r5[r7];	 Catch:{ all -> 0x0156 }
        r8 = 31;
        if (r7 != r8) goto L_0x00ce;
    L_0x00af:
        r7 = 1;
        r5 = r5[r7];	 Catch:{ all -> 0x0156 }
        r7 = -117; // 0xffffffffffffff8b float:NaN double:NaN;
        if (r5 != r7) goto L_0x00ce;
    L_0x00b6:
        r5 = new java.util.zip.GZIPInputStream;	 Catch:{ all -> 0x0156 }
        r5.<init>(r1);	 Catch:{ all -> 0x0156 }
        r2 = r5;
    L_0x00bc:
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r5 = new byte[r5];	 Catch:{ IOException -> 0x00cc }
    L_0x00c0:
        r7 = r2.read(r5);	 Catch:{ IOException -> 0x00cc }
        r8 = -1;
        if (r7 == r8) goto L_0x00d0;
    L_0x00c7:
        r8 = 0;
        r4.write(r5, r8, r7);	 Catch:{ IOException -> 0x00cc }
        goto L_0x00c0;
    L_0x00cc:
        r5 = move-exception;
        goto L_0x006f;
    L_0x00ce:
        r2 = r1;
        goto L_0x00bc;
    L_0x00d0:
        com.amap.api.mapcore.util.gz.c();	 Catch:{ IOException -> 0x00cc }
        r5 = new com.amap.api.mapcore.util.jb;	 Catch:{ IOException -> 0x00cc }
        r5.<init>();	 Catch:{ IOException -> 0x00cc }
        r7 = r4.toByteArray();	 Catch:{ IOException -> 0x00cc }
        r5.a = r7;	 Catch:{ IOException -> 0x00cc }
        r5.b = r6;	 Catch:{ IOException -> 0x00cc }
        r6 = r10.i;	 Catch:{ IOException -> 0x00cc }
        r5.c = r6;	 Catch:{ IOException -> 0x00cc }
        r5.d = r0;	 Catch:{ IOException -> 0x00cc }
        if (r4 == 0) goto L_0x00eb;
    L_0x00e8:
        r4.close();	 Catch:{ Throwable -> 0x0123 }
    L_0x00eb:
        if (r3 == 0) goto L_0x00f0;
    L_0x00ed:
        r3.close();	 Catch:{ Throwable -> 0x012c }
    L_0x00f0:
        if (r1 == 0) goto L_0x00f5;
    L_0x00f2:
        r1.close();	 Catch:{ Throwable -> 0x0135 }
    L_0x00f5:
        if (r2 == 0) goto L_0x00fa;
    L_0x00f7:
        r2.close();	 Catch:{ Throwable -> 0x013e }
    L_0x00fa:
        return r5;
    L_0x00fb:
        r4 = move-exception;
        r5 = "ht";
        r6 = "par";
        com.amap.api.mapcore.util.gw.a(r4, r5, r6);
        goto L_0x0080;
    L_0x0105:
        r3 = move-exception;
        r4 = "ht";
        r5 = "par";
        com.amap.api.mapcore.util.gw.a(r3, r4, r5);
        goto L_0x0085;
    L_0x010f:
        r2 = move-exception;
        r3 = "ht";
        r4 = "par";
        com.amap.api.mapcore.util.gw.a(r2, r3, r4);
        goto L_0x008a;
    L_0x0119:
        r1 = move-exception;
        r2 = "ht";
        r3 = "par";
        com.amap.api.mapcore.util.gw.a(r1, r2, r3);
        goto L_0x008f;
    L_0x0123:
        r0 = move-exception;
        r4 = "ht";
        r6 = "par";
        com.amap.api.mapcore.util.gw.a(r0, r4, r6);
        goto L_0x00eb;
    L_0x012c:
        r0 = move-exception;
        r3 = "ht";
        r4 = "par";
        com.amap.api.mapcore.util.gw.a(r0, r3, r4);
        goto L_0x00f0;
    L_0x0135:
        r0 = move-exception;
        r1 = "ht";
        r3 = "par";
        com.amap.api.mapcore.util.gw.a(r0, r1, r3);
        goto L_0x00f5;
    L_0x013e:
        r0 = move-exception;
        r1 = "ht";
        r2 = "par";
        com.amap.api.mapcore.util.gw.a(r0, r1, r2);
        goto L_0x00fa;
    L_0x0147:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        r4 = r2;
        goto L_0x007b;
    L_0x014d:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x007b;
    L_0x0152:
        r0 = move-exception;
        r1 = r2;
        goto L_0x007b;
    L_0x0156:
        r0 = move-exception;
        r9 = r1;
        r1 = r2;
        r2 = r9;
        goto L_0x007b;
    L_0x015c:
        r0 = move-exception;
        r0 = r1;
        r3 = r2;
        r4 = r2;
        r1 = r2;
        goto L_0x006f;
    L_0x0163:
        r1 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x006f;
    L_0x0168:
        r1 = move-exception;
        r1 = r2;
        goto L_0x006f;
    L_0x016c:
        r0 = r1;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.iw.a(java.net.HttpURLConnection):com.amap.api.mapcore.util.jb");
    }

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, (String) map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", this.i);
        } catch (Throwable th) {
            gw.a(th, "ht", "adh");
        }
        httpURLConnection.setConnectTimeout(this.a);
        httpURLConnection.setReadTimeout(this.b);
    }

    static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str2 == null) {
                str2 = "";
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.append(HttpUtils.PARAMETERS_SEPARATOR);
            }
            stringBuilder.append(URLEncoder.encode(str));
            stringBuilder.append(HttpUtils.EQUAL_SIGN);
            stringBuilder.append(URLEncoder.encode(str2));
        }
        return stringBuilder.toString();
    }
}
