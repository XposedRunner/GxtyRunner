package com.lzy.okgo.interceptor;

import com.lzy.okgo.f.c;
import com.lzy.okgo.f.d;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Interceptor$Chain;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;

public class HttpLoggingInterceptor implements Interceptor {
    private static final Charset a = Charset.forName("UTF-8");
    private volatile Level b = Level.NONE;
    private java.util.logging.Level c;
    private Logger d;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public HttpLoggingInterceptor(String str) {
        this.d = Logger.getLogger(str);
    }

    public void a(Level level) {
        if (this.b == null) {
            throw new NullPointerException("printLevel == null. Use Level.NONE instead.");
        }
        this.b = level;
    }

    public void a(java.util.logging.Level level) {
        this.c = level;
    }

    private void a(String str) {
        this.d.log(this.c, str);
    }

    public Response intercept(Interceptor$Chain interceptor$Chain) throws IOException {
        Request request = interceptor$Chain.request();
        if (this.b == Level.NONE) {
            return interceptor$Chain.proceed(request);
        }
        a(request, interceptor$Chain.connection());
        long nanoTime = System.nanoTime();
        try {
            return a(interceptor$Chain.proceed(request), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
        } catch (Exception e) {
            a("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(okhttp3.Request r11, okhttp3.Connection r12) throws java.io.IOException {
        /*
        r10 = this;
        r1 = 1;
        r2 = 0;
        r0 = r10.b;
        r3 = com.lzy.okgo.interceptor.HttpLoggingInterceptor.Level.BODY;
        if (r0 != r3) goto L_0x00e2;
    L_0x0008:
        r0 = r1;
    L_0x0009:
        r3 = r10.b;
        r4 = com.lzy.okgo.interceptor.HttpLoggingInterceptor.Level.BODY;
        if (r3 == r4) goto L_0x0015;
    L_0x000f:
        r3 = r10.b;
        r4 = com.lzy.okgo.interceptor.HttpLoggingInterceptor.Level.HEADERS;
        if (r3 != r4) goto L_0x00e5;
    L_0x0015:
        r3 = r1;
    L_0x0016:
        r5 = r11.body();
        if (r5 == 0) goto L_0x00e8;
    L_0x001c:
        r4 = r1;
    L_0x001d:
        if (r12 == 0) goto L_0x00eb;
    L_0x001f:
        r1 = r12.protocol();
    L_0x0023:
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0126 }
        r6.<init>();	 Catch:{ Exception -> 0x0126 }
        r7 = "--> ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0126 }
        r7 = r11.method();	 Catch:{ Exception -> 0x0126 }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0126 }
        r7 = 32;
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0126 }
        r7 = r11.url();	 Catch:{ Exception -> 0x0126 }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0126 }
        r7 = 32;
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0126 }
        r1 = r6.append(r1);	 Catch:{ Exception -> 0x0126 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0126 }
        r10.a(r1);	 Catch:{ Exception -> 0x0126 }
        if (r3 == 0) goto L_0x0105;
    L_0x0057:
        if (r4 == 0) goto L_0x009d;
    L_0x0059:
        r1 = r5.contentType();	 Catch:{ Exception -> 0x0126 }
        if (r1 == 0) goto L_0x0079;
    L_0x005f:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0126 }
        r1.<init>();	 Catch:{ Exception -> 0x0126 }
        r3 = "\tContent-Type: ";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x0126 }
        r3 = r5.contentType();	 Catch:{ Exception -> 0x0126 }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x0126 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0126 }
        r10.a(r1);	 Catch:{ Exception -> 0x0126 }
    L_0x0079:
        r6 = r5.contentLength();	 Catch:{ Exception -> 0x0126 }
        r8 = -1;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 == 0) goto L_0x009d;
    L_0x0083:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0126 }
        r1.<init>();	 Catch:{ Exception -> 0x0126 }
        r3 = "\tContent-Length: ";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x0126 }
        r6 = r5.contentLength();	 Catch:{ Exception -> 0x0126 }
        r1 = r1.append(r6);	 Catch:{ Exception -> 0x0126 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0126 }
        r10.a(r1);	 Catch:{ Exception -> 0x0126 }
    L_0x009d:
        r1 = r11.headers();	 Catch:{ Exception -> 0x0126 }
        r3 = r1.size();	 Catch:{ Exception -> 0x0126 }
    L_0x00a5:
        if (r2 >= r3) goto L_0x00ef;
    L_0x00a7:
        r6 = r1.name(r2);	 Catch:{ Exception -> 0x0126 }
        r7 = "Content-Type";
        r7 = r7.equalsIgnoreCase(r6);	 Catch:{ Exception -> 0x0126 }
        if (r7 != 0) goto L_0x00df;
    L_0x00b3:
        r7 = "Content-Length";
        r7 = r7.equalsIgnoreCase(r6);	 Catch:{ Exception -> 0x0126 }
        if (r7 != 0) goto L_0x00df;
    L_0x00bb:
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0126 }
        r7.<init>();	 Catch:{ Exception -> 0x0126 }
        r8 = "\t";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0126 }
        r6 = r7.append(r6);	 Catch:{ Exception -> 0x0126 }
        r7 = ": ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0126 }
        r7 = r1.value(r2);	 Catch:{ Exception -> 0x0126 }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0126 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0126 }
        r10.a(r6);	 Catch:{ Exception -> 0x0126 }
    L_0x00df:
        r2 = r2 + 1;
        goto L_0x00a5;
    L_0x00e2:
        r0 = r2;
        goto L_0x0009;
    L_0x00e5:
        r3 = r2;
        goto L_0x0016;
    L_0x00e8:
        r4 = r2;
        goto L_0x001d;
    L_0x00eb:
        r1 = okhttp3.Protocol.HTTP_1_1;
        goto L_0x0023;
    L_0x00ef:
        r1 = " ";
        r10.a(r1);	 Catch:{ Exception -> 0x0126 }
        if (r0 == 0) goto L_0x0105;
    L_0x00f6:
        if (r4 == 0) goto L_0x0105;
    L_0x00f8:
        r0 = r5.contentType();	 Catch:{ Exception -> 0x0126 }
        r0 = b(r0);	 Catch:{ Exception -> 0x0126 }
        if (r0 == 0) goto L_0x0120;
    L_0x0102:
        r10.a(r11);	 Catch:{ Exception -> 0x0126 }
    L_0x0105:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "--> END ";
        r0 = r0.append(r1);
        r1 = r11.method();
        r0 = r0.append(r1);
        r0 = r0.toString();
        r10.a(r0);
    L_0x011f:
        return;
    L_0x0120:
        r0 = "\tbody: maybe [binary body], omitted!";
        r10.a(r0);	 Catch:{ Exception -> 0x0126 }
        goto L_0x0105;
    L_0x0126:
        r0 = move-exception;
        com.lzy.okgo.f.d.a(r0);	 Catch:{ all -> 0x0145 }
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "--> END ";
        r0 = r0.append(r1);
        r1 = r11.method();
        r0 = r0.append(r1);
        r0 = r0.toString();
        r10.a(r0);
        goto L_0x011f;
    L_0x0145:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "--> END ";
        r1 = r1.append(r2);
        r2 = r11.method();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r10.a(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lzy.okgo.interceptor.HttpLoggingInterceptor.a(okhttp3.Request, okhttp3.Connection):void");
    }

    private Response a(Response response, long j) {
        int i = 1;
        int i2 = 0;
        Response build = response.newBuilder().build();
        ResponseBody body = build.body();
        int i3 = this.b == Level.BODY ? 1 : 0;
        if (!(this.b == Level.BODY || this.b == Level.HEADERS)) {
            i = 0;
        }
        try {
            a("<-- " + build.code() + ' ' + build.message() + ' ' + build.request().url() + " (" + j + "ms）");
            if (i != 0) {
                Headers headers = build.headers();
                int size = headers.size();
                while (i2 < size) {
                    a("\t" + headers.name(i2) + ": " + headers.value(i2));
                    i2++;
                }
                a(" ");
                if (i3 != 0 && HttpHeaders.hasBody(build)) {
                    if (body != null) {
                        if (b(body.contentType())) {
                            byte[] a = c.a(body.byteStream());
                            a("\tbody:" + new String(a, a(body.contentType())));
                            response = response.newBuilder().body(ResponseBody.create(body.contentType(), a)).build();
                            a("<-- END HTTP");
                        } else {
                            a("\tbody: maybe [binary body], omitted!");
                        }
                    }
                    return response;
                }
            }
            a("<-- END HTTP");
        } catch (Throwable e) {
            d.a(e);
        } finally {
            a("<-- END HTTP");
        }
        return response;
    }

    private static Charset a(MediaType mediaType) {
        Charset charset = mediaType != null ? mediaType.charset(a) : a;
        if (charset == null) {
            return a;
        }
        return charset;
    }

    private static boolean b(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        String subtype = mediaType.subtype();
        if (subtype == null) {
            return false;
        }
        subtype = subtype.toLowerCase();
        if (subtype.contains("x-www-form-urlencoded") || subtype.contains("json") || subtype.contains("xml") || subtype.contains("html")) {
            return true;
        }
        return false;
    }

    private void a(Request request) {
        try {
            RequestBody body = request.newBuilder().build().body();
            if (body != null) {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                a("\tbody:" + buffer.readString(a(body.contentType())));
            }
        } catch (Throwable e) {
            d.a(e);
        }
    }
}
