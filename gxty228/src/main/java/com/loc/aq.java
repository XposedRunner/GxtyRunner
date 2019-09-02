package com.loc;

import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.maps.AMapException;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.bugly.beta.tinker.TinkerReport;
import com.tencent.connect.common.Constants;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
public final class aq {
    private int a;
    private int b;
    private boolean c;
    private SSLContext d;
    private Proxy e;
    private volatile boolean f;
    private long g;
    private long h;
    private String i;
    private com.loc.an.a j;
    private a k;

    /* compiled from: HttpUrlUtil */
    private static class a implements HostnameVerifier {
        private String a;

        private a() {
        }

        public final void a(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.a = str;
            }
        }

        public final boolean verify(String str, SSLSession sSLSession) {
            HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            if (!TextUtils.isEmpty(this.a)) {
                String str2 = this.a;
                this.a = null;
                if (str2.equals(str)) {
                    return true;
                }
            }
            return defaultHostnameVerifier.verify(str, sSLSession) || defaultHostnameVerifier.verify(str, sSLSession);
        }
    }

    aq(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    aq(int i, int i2, Proxy proxy, boolean z) {
        this(i, i2, proxy, z, (byte) 0);
    }

    private aq(int i, int i2, Proxy proxy, boolean z, byte b) {
        this.f = false;
        this.g = -1;
        this.h = 0;
        this.k = new a();
        this.a = i;
        this.b = i2;
        this.e = proxy;
        this.c = z;
        this.j = null;
        try {
            this.i = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            g.a(th, "ht", "ic");
        }
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                this.d = instance;
            } catch (Throwable th2) {
                g.a(th2, "ht", "ne");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.loc.as a(java.net.HttpURLConnection r11) throws com.loc.k, java.io.IOException {
        /*
        r10 = this;
        r2 = 0;
        r1 = "";
        r11.connect();	 Catch:{ IOException -> 0x0158, all -> 0x0143 }
        r6 = r11.getHeaderFields();	 Catch:{ IOException -> 0x0158, all -> 0x0143 }
        r3 = r11.getResponseCode();	 Catch:{ IOException -> 0x0158, all -> 0x0143 }
        if (r6 == 0) goto L_0x0168;
    L_0x0010:
        r0 = "gsid";
        r0 = r6.get(r0);	 Catch:{ IOException -> 0x0158, all -> 0x0143 }
        r0 = (java.util.List) r0;	 Catch:{ IOException -> 0x0158, all -> 0x0143 }
        if (r0 == 0) goto L_0x0168;
    L_0x001a:
        r4 = r0.size();	 Catch:{ IOException -> 0x0158, all -> 0x0143 }
        if (r4 <= 0) goto L_0x0168;
    L_0x0020:
        r4 = 0;
        r0 = r0.get(r4);	 Catch:{ IOException -> 0x0158, all -> 0x0143 }
        r0 = (java.lang.String) r0;	 Catch:{ IOException -> 0x0158, all -> 0x0143 }
    L_0x0027:
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 == r1) goto L_0x008c;
    L_0x002b:
        r1 = new com.loc.k;	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r5 = "网络异常原因：";
        r4.<init>(r5);	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r5 = r11.getResponseMessage();	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r5 = " 网络异常状态码：";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r4 = r4.append(r3);	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r5 = "  ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r4 = r4.append(r0);	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r5 = r10.i;	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r1.<init>(r4, r0);	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r1.a(r3);	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        throw r1;	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
    L_0x0067:
        r1 = move-exception;
        r1 = r2;
        r3 = r2;
        r4 = r2;
    L_0x006b:
        r5 = new com.loc.k;	 Catch:{ all -> 0x0073 }
        r6 = "IO 操作异常 - IOException";
        r5.<init>(r6, r0);	 Catch:{ all -> 0x0073 }
        throw r5;	 Catch:{ all -> 0x0073 }
    L_0x0073:
        r0 = move-exception;
        r9 = r1;
        r1 = r2;
        r2 = r9;
    L_0x0077:
        if (r4 == 0) goto L_0x007c;
    L_0x0079:
        r4.close();	 Catch:{ Throwable -> 0x00f7 }
    L_0x007c:
        if (r3 == 0) goto L_0x0081;
    L_0x007e:
        r3.close();	 Catch:{ Throwable -> 0x0101 }
    L_0x0081:
        if (r2 == 0) goto L_0x0086;
    L_0x0083:
        r2.close();	 Catch:{ Throwable -> 0x010b }
    L_0x0086:
        if (r1 == 0) goto L_0x008b;
    L_0x0088:
        r1.close();	 Catch:{ Throwable -> 0x0115 }
    L_0x008b:
        throw r0;
    L_0x008c:
        r4 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r4.<init>();	 Catch:{ IOException -> 0x0067, all -> 0x0143 }
        r3 = r11.getInputStream();	 Catch:{ IOException -> 0x015f, all -> 0x0149 }
        r1 = new java.io.PushbackInputStream;	 Catch:{ IOException -> 0x0164, all -> 0x014e }
        r5 = 2;
        r1.<init>(r3, r5);	 Catch:{ IOException -> 0x0164, all -> 0x014e }
        r5 = 2;
        r5 = new byte[r5];	 Catch:{ all -> 0x0152 }
        r1.read(r5);	 Catch:{ all -> 0x0152 }
        r1.unread(r5);	 Catch:{ all -> 0x0152 }
        r7 = 0;
        r7 = r5[r7];	 Catch:{ all -> 0x0152 }
        r8 = 31;
        if (r7 != r8) goto L_0x00ca;
    L_0x00ab:
        r7 = 1;
        r5 = r5[r7];	 Catch:{ all -> 0x0152 }
        r7 = -117; // 0xffffffffffffff8b float:NaN double:NaN;
        if (r5 != r7) goto L_0x00ca;
    L_0x00b2:
        r5 = new java.util.zip.GZIPInputStream;	 Catch:{ all -> 0x0152 }
        r5.<init>(r1);	 Catch:{ all -> 0x0152 }
        r2 = r5;
    L_0x00b8:
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r5 = new byte[r5];	 Catch:{ IOException -> 0x00c8 }
    L_0x00bc:
        r7 = r2.read(r5);	 Catch:{ IOException -> 0x00c8 }
        r8 = -1;
        if (r7 == r8) goto L_0x00cc;
    L_0x00c3:
        r8 = 0;
        r4.write(r5, r8, r7);	 Catch:{ IOException -> 0x00c8 }
        goto L_0x00bc;
    L_0x00c8:
        r5 = move-exception;
        goto L_0x006b;
    L_0x00ca:
        r2 = r1;
        goto L_0x00b8;
    L_0x00cc:
        com.loc.j.c();	 Catch:{ IOException -> 0x00c8 }
        r5 = new com.loc.as;	 Catch:{ IOException -> 0x00c8 }
        r5.<init>();	 Catch:{ IOException -> 0x00c8 }
        r7 = r4.toByteArray();	 Catch:{ IOException -> 0x00c8 }
        r5.a = r7;	 Catch:{ IOException -> 0x00c8 }
        r5.b = r6;	 Catch:{ IOException -> 0x00c8 }
        r6 = r10.i;	 Catch:{ IOException -> 0x00c8 }
        r5.c = r6;	 Catch:{ IOException -> 0x00c8 }
        r5.d = r0;	 Catch:{ IOException -> 0x00c8 }
        if (r4 == 0) goto L_0x00e7;
    L_0x00e4:
        r4.close();	 Catch:{ Throwable -> 0x011f }
    L_0x00e7:
        if (r3 == 0) goto L_0x00ec;
    L_0x00e9:
        r3.close();	 Catch:{ Throwable -> 0x0128 }
    L_0x00ec:
        if (r1 == 0) goto L_0x00f1;
    L_0x00ee:
        r1.close();	 Catch:{ Throwable -> 0x0131 }
    L_0x00f1:
        if (r2 == 0) goto L_0x00f6;
    L_0x00f3:
        r2.close();	 Catch:{ Throwable -> 0x013a }
    L_0x00f6:
        return r5;
    L_0x00f7:
        r4 = move-exception;
        r5 = "ht";
        r6 = "par";
        com.loc.g.a(r4, r5, r6);
        goto L_0x007c;
    L_0x0101:
        r3 = move-exception;
        r4 = "ht";
        r5 = "par";
        com.loc.g.a(r3, r4, r5);
        goto L_0x0081;
    L_0x010b:
        r2 = move-exception;
        r3 = "ht";
        r4 = "par";
        com.loc.g.a(r2, r3, r4);
        goto L_0x0086;
    L_0x0115:
        r1 = move-exception;
        r2 = "ht";
        r3 = "par";
        com.loc.g.a(r1, r2, r3);
        goto L_0x008b;
    L_0x011f:
        r0 = move-exception;
        r4 = "ht";
        r6 = "par";
        com.loc.g.a(r0, r4, r6);
        goto L_0x00e7;
    L_0x0128:
        r0 = move-exception;
        r3 = "ht";
        r4 = "par";
        com.loc.g.a(r0, r3, r4);
        goto L_0x00ec;
    L_0x0131:
        r0 = move-exception;
        r1 = "ht";
        r3 = "par";
        com.loc.g.a(r0, r1, r3);
        goto L_0x00f1;
    L_0x013a:
        r0 = move-exception;
        r1 = "ht";
        r2 = "par";
        com.loc.g.a(r0, r1, r2);
        goto L_0x00f6;
    L_0x0143:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        r4 = r2;
        goto L_0x0077;
    L_0x0149:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x0077;
    L_0x014e:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0077;
    L_0x0152:
        r0 = move-exception;
        r9 = r1;
        r1 = r2;
        r2 = r9;
        goto L_0x0077;
    L_0x0158:
        r0 = move-exception;
        r0 = r1;
        r3 = r2;
        r4 = r2;
        r1 = r2;
        goto L_0x006b;
    L_0x015f:
        r1 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x006b;
    L_0x0164:
        r1 = move-exception;
        r1 = r2;
        goto L_0x006b;
    L_0x0168:
        r0 = r1;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.aq.a(java.net.HttpURLConnection):com.loc.as");
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

    private HttpURLConnection a(String str, Map<String, String> map, boolean z) throws IOException {
        Map hashMap;
        URLConnection a;
        HttpURLConnection httpURLConnection;
        df.b();
        if (map == null) {
            hashMap = new HashMap();
        }
        String str2 = "";
        switch (an.a) {
            case 1:
                str2 = an.b;
                break;
        }
        if (!TextUtils.isEmpty(str2)) {
            Uri parse = Uri.parse(str);
            String host = parse.getHost();
            str = parse.buildUpon().encodedAuthority(str2).build().toString();
            if (hashMap != null) {
                hashMap.put("targetHost", host);
            }
            if (this.c && this.k != null) {
                this.k.a(str2);
            }
        }
        URL url = new URL(str);
        if (this.j != null) {
            com.loc.an.a aVar = this.j;
            Proxy proxy = this.e;
            a = aVar.a();
        } else {
            a = null;
        }
        if (a == null) {
            a = this.e != null ? url.openConnection(this.e) : url.openConnection();
        }
        if (this.c) {
            httpURLConnection = (HttpsURLConnection) a;
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.d.getSocketFactory());
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(this.k);
        } else {
            httpURLConnection = (HttpURLConnection) a;
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

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, (String) map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", this.i);
        } catch (Throwable th) {
            g.a(th, "ht", "adh");
        }
        httpURLConnection.setConnectTimeout(this.a);
        httpURLConnection.setReadTimeout(this.b);
    }

    final as a(String str, Map<String, String> map, byte[] bArr) throws k {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = a(str, (Map) map, true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            as a = a(httpURLConnection);
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th) {
                    g.a(th, "ht", "mPt");
                }
            }
            return a;
        } catch (ConnectException e) {
            e.printStackTrace();
            throw new k(AMapException.ERROR_CONNECTION);
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            throw new k(AMapException.ERROR_URL);
        } catch (UnknownHostException e3) {
            e3.printStackTrace();
            throw new k(AMapException.ERROR_UNKNOW_HOST);
        } catch (SocketException e4) {
            e4.printStackTrace();
            throw new k(AMapException.ERROR_SOCKET);
        } catch (SocketTimeoutException e5) {
            e5.printStackTrace();
            throw new k(AMapException.ERROR_SOCKE_TIME_OUT);
        } catch (InterruptedIOException e6) {
            throw new k(AMapException.ERROR_UNKNOWN);
        } catch (IOException e7) {
            e7.printStackTrace();
            throw new k(AMapException.ERROR_IO);
        } catch (Throwable e8) {
            g.a(e8, "ht", "mPt");
            throw e8;
        } catch (Throwable th2) {
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th3) {
                    g.a(th3, "ht", "mPt");
                }
            }
        }
    }

    final void a() {
        this.h = 0;
    }

    final void a(String str, Map<String, String> map, Map<String, String> map2, com.loc.ap.a aVar) {
        Throwable e;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        Throwable th;
        InputStream inputStream2;
        HttpURLConnection httpURLConnection2 = null;
        int i = 1;
        if (aVar != null) {
            HttpURLConnection a;
            try {
                String a2 = a((Map) map2);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str);
                if (a2 != null) {
                    stringBuffer.append(HttpUtils.URL_AND_PARA_SEPARATOR).append(a2);
                }
                a = a(stringBuffer.toString(), (Map) map, false);
                try {
                    a.setRequestProperty("RANGE", "bytes=" + this.h + "-");
                    a.connect();
                    int responseCode = a.getResponseCode();
                    int i2 = responseCode != 200 ? 1 : 0;
                    if (responseCode == TinkerReport.KEY_APPLIED_FAIL_COST_10S_LESS) {
                        i = 0;
                    }
                    if ((i & i2) != 0) {
                        k kVar = new k("网络异常原因：" + a.getResponseMessage() + " 网络异常状态码：" + responseCode);
                        aVar.c();
                    }
                    InputStream inputStream3 = a.getInputStream();
                    try {
                        Object obj = new byte[1024];
                        while (!Thread.interrupted() && !this.f) {
                            int read = inputStream3.read(obj, 0, 1024);
                            if (read > 0 && (this.g == -1 || this.h < this.g)) {
                                if (read == 1024) {
                                    aVar.a(obj, this.h);
                                } else {
                                    Object obj2 = new byte[read];
                                    System.arraycopy(obj, 0, obj2, 0, read);
                                    aVar.a(obj2, this.h);
                                }
                                this.h += (long) read;
                            }
                        }
                        if (this.f) {
                            aVar.e();
                        } else {
                            aVar.d();
                        }
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (Throwable e2) {
                                g.a(e2, "ht", "mdr");
                            } catch (Throwable e22) {
                                g.a(e22, "ht", "mdr");
                            }
                        }
                        if (a != null) {
                            try {
                                a.disconnect();
                            } catch (Throwable e222) {
                                g.a(e222, "ht", "mdr");
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream2 = inputStream3;
                        e222 = th;
                    }
                } catch (Throwable th22) {
                    th = th22;
                    inputStream2 = null;
                    e222 = th;
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (a != null) {
                        a.disconnect();
                    }
                    throw e222;
                }
            } catch (Throwable e3) {
                inputStream2 = null;
                e222 = e3;
                a = null;
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (a != null) {
                    a.disconnect();
                }
                throw e222;
            }
        }
    }

    final void b() {
        this.g = -1;
    }
}
