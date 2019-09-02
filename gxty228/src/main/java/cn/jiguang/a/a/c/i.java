package cn.jiguang.a.a.c;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import cn.jiguang.a.c.c;
import cn.jiguang.d.d.o;
import cn.jiguang.e.d;
import cn.jiguang.g.a;
import cn.jiguang.g.f;
import cn.jiguang.g.k;
import cn.jiguang.net.HttpUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.connect.common.Constants;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

public final class i extends Thread {
    private static ExecutorService a = Executors.newSingleThreadExecutor();
    private static final Object b = new Object();
    private static AtomicInteger c = new AtomicInteger();
    private static CookieManager j;
    private String d;
    private String e;
    private String f;
    private Context g;
    private int h = 0;
    private String i;

    private i() {
    }

    private i(Context context) {
        String d = a.d(context, this.d);
        String c = a.c(context, this.e);
        String e = a.e(context, this.f);
        CookieManager cookieManager = new CookieManager();
        j = cookieManager;
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(j);
        this.g = context;
        this.d = d;
        this.e = c;
        this.f = e;
    }

    public static int a(String str) {
        return k.a(str) ? -1 : str.equalsIgnoreCase("ChinaTelecom") ? 2 : str.equalsIgnoreCase("ChinaMobile") ? 0 : str.equalsIgnoreCase("ChinaUnicom") ? 1 : -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private cn.jiguang.a.a.c.j a(android.content.Context r23, java.lang.String r24, int r25, long r26, boolean r28, java.io.File r29, java.lang.String r30) {
        /*
        r22 = this;
        r2 = "PhoneNumberUtil";
        r3 = "Action - httpPostFile ";
        cn.jiguang.e.d.a(r2, r3);
        r3 = "PhoneNumberUtil";
        r2 = new java.lang.StringBuilder;
        r4 = "urls:";
        r2.<init>(r4);
        r0 = r24;
        r2 = r2.append(r0);
        r4 = "\n file:";
        r4 = r2.append(r4);
        if (r29 == 0) goto L_0x01c3;
    L_0x001e:
        r2 = r29.getAbsolutePath();
    L_0x0022:
        r2 = r4.append(r2);
        r4 = " keyset:";
        r2 = r2.append(r4);
        r0 = r30;
        r2 = r2.append(r0);
        r4 = "  filename:";
        r4 = r2.append(r4);
        if (r29 == 0) goto L_0x01c7;
    L_0x003a:
        r2 = r29.getName();
    L_0x003e:
        r2 = r4.append(r2);
        r4 = " isTemporaryRedirect:";
        r2 = r2.append(r4);
        r0 = r28;
        r2 = r2.append(r0);
        r2 = r2.toString();
        cn.jiguang.e.d.a(r3, r2);
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r2 = (r26 > r2 ? 1 : (r26 == r2 ? 0 : -1));
        if (r2 < 0) goto L_0x0062;
    L_0x005b:
        r2 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r2 = (r26 > r2 ? 1 : (r26 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0064;
    L_0x0062:
        r26 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
    L_0x0064:
        r2 = java.util.UUID.randomUUID();
        r17 = r2.toString();
        r18 = "--";
        r19 = "\r\n";
        r20 = "multipart/form-data";
        r4 = 0;
        r14 = 0;
        r13 = 0;
        r11 = 0;
        r3 = 0;
        r2 = -1;
        r5 = j;
        if (r5 != 0) goto L_0x0083;
    L_0x007c:
        r5 = new java.net.CookieManager;
        r5.<init>();
        j = r5;
    L_0x0083:
        r16 = r3;
        r3 = r4;
    L_0x0086:
        r15 = cn.jiguang.net.HttpUtils.getHttpURLConnectionWithProxy(r23, r24);	 Catch:{ SSLPeerUnverifiedException -> 0x049e, Exception -> 0x0482, AssertionError -> 0x0470, all -> 0x046c }
        r3 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r15.setConnectTimeout(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r15.setReadTimeout(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = 1;
        r15.setDoInput(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = 1;
        r15.setDoOutput(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = 0;
        r15.setUseCaches(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = "POST";
        r15.setRequestMethod(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = "Charset";
        r4 = "UTF-8";
        r15.setRequestProperty(r3, r4);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = "User-Agent";
        r4 = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36";
        r15.addRequestProperty(r3, r4);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = j;	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = r3.getCookieStore();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = r3.getCookies();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = r3.size();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        if (r3 <= 0) goto L_0x00d8;
    L_0x00c3:
        r3 = "Cookie";
        r4 = ";";
        r5 = j;	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r5 = r5.getCookieStore();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r5 = r5.getCookies();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4 = android.text.TextUtils.join(r4, r5);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r15.setRequestProperty(r3, r4);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
    L_0x00d8:
        r0 = r28;
        r15.setInstanceFollowRedirects(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = "PhoneNumberUtil";
        r4 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r5 = "file:";
        r4.<init>(r5);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r29;
        r4 = r4.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4 = r4.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        cn.jiguang.e.d.a(r3, r4);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        if (r29 == 0) goto L_0x0203;
    L_0x00f5:
        r3 = "Content-Type";
        r4 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4.<init>();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r20;
        r4 = r4.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r5 = ";boundary=";
        r4 = r4.append(r5);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r17;
        r4 = r4.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4 = r4.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r15.setRequestProperty(r3, r4);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = r15.getOutputStream();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4 = new java.io.DataOutputStream;	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4.<init>(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = new java.lang.StringBuffer;	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3.<init>();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r18;
        r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r17;
        r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r19;
        r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r6 = "Content-Disposition: form-data; name=\"";
        r5.<init>(r6);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r30;
        r5 = r5.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r6 = "\"; filename=\"";
        r5 = r5.append(r6);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r6 = r29.getName();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r5 = r5.append(r6);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r6 = "\"";
        r5 = r5.append(r6);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r19;
        r5 = r5.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r5 = r5.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3.append(r5);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r6 = "Content-Type: application/octet-stream; charset=UTF-8";
        r5.<init>(r6);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r19;
        r5 = r5.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r5 = r5.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3.append(r5);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r19;
        r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = r3.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = r3.getBytes();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4.write(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = new java.io.FileInputStream;	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r29;
        r3.<init>(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r5 = new byte[r5];	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
    L_0x018f:
        r6 = r3.read(r5);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r7 = -1;
        if (r6 == r7) goto L_0x01cb;
    L_0x0196:
        r7 = 0;
        r4.write(r5, r7, r6);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        goto L_0x018f;
    L_0x019b:
        r3 = move-exception;
        r3 = r13;
        r4 = r14;
        r5 = r15;
    L_0x019f:
        r6 = "PhoneNumberUtil";
        r7 = "Catch SSLPeerUnverifiedException, http client execute error!";
        cn.jiguang.e.d.i(r6, r7);	 Catch:{ all -> 0x0464 }
        if (r4 == 0) goto L_0x01ab;
    L_0x01a8:
        r4.close();	 Catch:{ IOException -> 0x02ca }
    L_0x01ab:
        if (r5 == 0) goto L_0x04b9;
    L_0x01ad:
        r5.disconnect();
        r13 = r3;
        r14 = r4;
        r4 = r5;
    L_0x01b3:
        r3 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r2 == r3) goto L_0x01c1;
    L_0x01b7:
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r3) goto L_0x032d;
    L_0x01bb:
        r3 = cn.jiguang.g.a.c(r23);
        if (r3 != 0) goto L_0x032d;
    L_0x01c1:
        r2 = 0;
    L_0x01c2:
        return r2;
    L_0x01c3:
        r2 = r29;
        goto L_0x0022;
    L_0x01c7:
        r2 = r29;
        goto L_0x003e;
    L_0x01cb:
        r3.close();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = r19.getBytes();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4.write(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3.<init>();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r18;
        r3 = r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r17;
        r3 = r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r18;
        r3 = r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r0 = r19;
        r3 = r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = r3.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r3 = r3.getBytes();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4.write(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4.flush();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r4.close();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
    L_0x0203:
        r12 = r15.getResponseCode();	 Catch:{ SSLPeerUnverifiedException -> 0x019b, Exception -> 0x0488, AssertionError -> 0x0476 }
        r2 = r15.getHeaderFields();	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        a(r2);	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        r2 = "PhoneNumberUtil";
        r3 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        r4 = "rspCode:";
        r3.<init>(r4);	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        r3 = r3.append(r12);	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        r3 = r3.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        cn.jiguang.e.d.a(r2, r3);	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        r2 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r12 != r2) goto L_0x0275;
    L_0x0226:
        r2 = "Location";
        r4 = r15.getHeaderField(r2);	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        r2 = "PhoneNumberUtil";
        r3 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        r5 = "location:";
        r3.<init>(r5);	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        r3 = r3.append(r4);	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        r3 = r3.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        cn.jiguang.e.d.a(r2, r3);	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        if (r25 < 0) goto L_0x0263;
    L_0x0242:
        r5 = r25 + -1;
        r6 = 0;
        r9 = 0;
        r10 = 0;
        r2 = r22;
        r3 = r23;
        r8 = r28;
        r2 = r2.a(r3, r4, r5, r6, r8, r9, r10);	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        if (r14 == 0) goto L_0x0257;
    L_0x0254:
        r14.close();	 Catch:{ IOException -> 0x025e }
    L_0x0257:
        if (r15 == 0) goto L_0x01c2;
    L_0x0259:
        r15.disconnect();
        goto L_0x01c2;
    L_0x025e:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0257;
    L_0x0263:
        if (r14 == 0) goto L_0x0268;
    L_0x0265:
        r14.close();	 Catch:{ IOException -> 0x0270 }
    L_0x0268:
        if (r15 == 0) goto L_0x026d;
    L_0x026a:
        r15.disconnect();
    L_0x026d:
        r2 = 0;
        goto L_0x01c2;
    L_0x0270:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0268;
    L_0x0275:
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r12 != r2) goto L_0x02b7;
    L_0x0279:
        r3 = r15.getInputStream();	 Catch:{ SSLPeerUnverifiedException -> 0x04a4, Exception -> 0x048d, AssertionError -> 0x047b }
        r4 = r15.getHeaderFields();	 Catch:{ SSLPeerUnverifiedException -> 0x04ab, Exception -> 0x0490, AssertionError -> 0x047e, all -> 0x031b }
        r2 = new java.lang.String;	 Catch:{ SSLPeerUnverifiedException -> 0x0494, Exception -> 0x02d0, AssertionError -> 0x02ed, all -> 0x031b }
        r5 = cn.jiguang.g.j.a(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x0494, Exception -> 0x02d0, AssertionError -> 0x02ed, all -> 0x031b }
        r6 = "UTF-8";
        r2.<init>(r5, r6);	 Catch:{ SSLPeerUnverifiedException -> 0x0494, Exception -> 0x02d0, AssertionError -> 0x02ed, all -> 0x031b }
        if (r3 == 0) goto L_0x0291;
    L_0x028e:
        r3.close();	 Catch:{ IOException -> 0x02b2 }
    L_0x0291:
        if (r15 == 0) goto L_0x0296;
    L_0x0293:
        r15.disconnect();
    L_0x0296:
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r12 < r3) goto L_0x0358;
    L_0x029a:
        r3 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r12 >= r3) goto L_0x0358;
    L_0x029e:
        if (r2 != 0) goto L_0x02aa;
    L_0x02a0:
        r2 = "PhoneNumberUtil";
        r3 = "Unexpected: server responsed NULL";
        r5 = 0;
        cn.jiguang.e.d.b(r2, r3, r5);	 Catch:{ Exception -> 0x034c }
        r2 = "<<error>>";
    L_0x02aa:
        r3 = new cn.jiguang.a.a.c.j;
        r3.<init>(r12, r4, r2);
        r2 = r3;
        goto L_0x01c2;
    L_0x02b2:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0291;
    L_0x02b7:
        if (r14 == 0) goto L_0x02bc;
    L_0x02b9:
        r14.close();	 Catch:{ IOException -> 0x02c5 }
    L_0x02bc:
        if (r15 == 0) goto L_0x04b5;
    L_0x02be:
        r15.disconnect();
        r2 = r12;
        r4 = r15;
        goto L_0x01b3;
    L_0x02c5:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x02bc;
    L_0x02ca:
        r6 = move-exception;
        r6.printStackTrace();
        goto L_0x01ab;
    L_0x02d0:
        r2 = move-exception;
        r13 = r4;
        r14 = r3;
    L_0x02d3:
        r3 = "PhoneNumberUtil";
        r4 = "http client execute error";
        cn.jiguang.e.d.b(r3, r4, r2);	 Catch:{ all -> 0x0469 }
        if (r14 == 0) goto L_0x02df;
    L_0x02dc:
        r14.close();	 Catch:{ IOException -> 0x02e8 }
    L_0x02df:
        if (r15 == 0) goto L_0x04b5;
    L_0x02e1:
        r15.disconnect();
        r2 = r12;
        r4 = r15;
        goto L_0x01b3;
    L_0x02e8:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x02df;
    L_0x02ed:
        r2 = move-exception;
        r13 = r4;
        r14 = r3;
    L_0x02f0:
        r3 = "PhoneNumberUtil";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0469 }
        r5 = "Catch AssertionError to avoid http close crash - ";
        r4.<init>(r5);	 Catch:{ all -> 0x0469 }
        r2 = r2.toString();	 Catch:{ all -> 0x0469 }
        r2 = r4.append(r2);	 Catch:{ all -> 0x0469 }
        r2 = r2.toString();	 Catch:{ all -> 0x0469 }
        cn.jiguang.e.d.i(r3, r2);	 Catch:{ all -> 0x0469 }
        if (r14 == 0) goto L_0x030d;
    L_0x030a:
        r14.close();	 Catch:{ IOException -> 0x0316 }
    L_0x030d:
        if (r15 == 0) goto L_0x04b5;
    L_0x030f:
        r15.disconnect();
        r2 = r12;
        r4 = r15;
        goto L_0x01b3;
    L_0x0316:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x030d;
    L_0x031b:
        r2 = move-exception;
        r14 = r3;
    L_0x031d:
        if (r14 == 0) goto L_0x0322;
    L_0x031f:
        r14.close();	 Catch:{ IOException -> 0x0328 }
    L_0x0322:
        if (r15 == 0) goto L_0x0327;
    L_0x0324:
        r15.disconnect();
    L_0x0327:
        throw r2;
    L_0x0328:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0322;
    L_0x032d:
        r3 = 3;
        r0 = r16;
        if (r0 < r3) goto L_0x033c;
    L_0x0332:
        r2 = new cn.jiguang.a.a.c.j;
        r3 = -1;
        r4 = "<<failed_with_retries>>";
        r2.<init>(r3, r13, r4);
        goto L_0x01c2;
    L_0x033c:
        r3 = r16 + 1;
        java.lang.Thread.sleep(r26);	 Catch:{ InterruptedException -> 0x0346 }
        r16 = r3;
        r3 = r4;
        goto L_0x0086;
    L_0x0346:
        r5 = move-exception;
        r16 = r3;
        r3 = r4;
        goto L_0x0086;
    L_0x034c:
        r2 = move-exception;
        r3 = "PhoneNumberUtil";
        r5 = "parse entity error";
        cn.jiguang.e.d.a(r3, r5, r2);
        r2 = "<<error>>";
        goto L_0x02aa;
    L_0x0358:
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r12 < r2) goto L_0x0414;
    L_0x035c:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r12 >= r2) goto L_0x0414;
    L_0x0360:
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r2 != r12) goto L_0x037e;
    L_0x0364:
        r2 = "PhoneNumberUtil";
        r3 = new java.lang.StringBuilder;
        r5 = "Server response failure:400 - ";
        r3.<init>(r5);
        r0 = r24;
        r3 = r3.append(r0);
        r3 = r3.toString();
        cn.jiguang.e.d.c(r2, r3);
        r2 = "server fail";
        goto L_0x02aa;
    L_0x037e:
        r2 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r2 != r12) goto L_0x039c;
    L_0x0382:
        r2 = "PhoneNumberUtil";
        r3 = new java.lang.StringBuilder;
        r5 = "Request not authorized:401 - ";
        r3.<init>(r5);
        r0 = r24;
        r3 = r3.append(r0);
        r3 = r3.toString();
        cn.jiguang.e.d.c(r2, r3);
        r2 = "<<error>>";
        goto L_0x02aa;
    L_0x039c:
        r2 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r2 != r12) goto L_0x03ba;
    L_0x03a0:
        r2 = "PhoneNumberUtil";
        r3 = new java.lang.StringBuilder;
        r5 = "Request path does not exist: 404 - ";
        r3.<init>(r5);
        r0 = r24;
        r3 = r3.append(r0);
        r3 = r3.toString();
        cn.jiguang.e.d.c(r2, r3);
        r2 = "<<error>>";
        goto L_0x02aa;
    L_0x03ba:
        r2 = 406; // 0x196 float:5.69E-43 double:2.006E-321;
        if (r2 != r12) goto L_0x03d8;
    L_0x03be:
        r2 = "PhoneNumberUtil";
        r3 = new java.lang.StringBuilder;
        r5 = "not acceptable:406 - ";
        r3.<init>(r5);
        r0 = r24;
        r3 = r3.append(r0);
        r3 = r3.toString();
        cn.jiguang.e.d.c(r2, r3);
        r2 = "<<error>>";
        goto L_0x02aa;
    L_0x03d8:
        r2 = 408; // 0x198 float:5.72E-43 double:2.016E-321;
        if (r2 != r12) goto L_0x03f6;
    L_0x03dc:
        r2 = "PhoneNumberUtil";
        r3 = new java.lang.StringBuilder;
        r5 = "request timeout:408 - ";
        r3.<init>(r5);
        r0 = r24;
        r3 = r3.append(r0);
        r3 = r3.toString();
        cn.jiguang.e.d.c(r2, r3);
        r2 = "<<error>>";
        goto L_0x02aa;
    L_0x03f6:
        r2 = 409; // 0x199 float:5.73E-43 double:2.02E-321;
        if (r2 != r12) goto L_0x04b2;
    L_0x03fa:
        r2 = "PhoneNumberUtil";
        r3 = new java.lang.StringBuilder;
        r5 = "conflict:409 - ";
        r3.<init>(r5);
        r0 = r24;
        r3 = r3.append(r0);
        r3 = r3.toString();
        cn.jiguang.e.d.c(r2, r3);
        r2 = "<<error>>";
        goto L_0x02aa;
    L_0x0414:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r12 < r2) goto L_0x0440;
    L_0x0418:
        r2 = 600; // 0x258 float:8.41E-43 double:2.964E-321;
        if (r12 >= r2) goto L_0x0440;
    L_0x041c:
        r2 = "PhoneNumberUtil";
        r3 = new java.lang.StringBuilder;
        r5 = "Server error - ";
        r3.<init>(r5);
        r3 = r3.append(r12);
        r5 = ", url:";
        r3 = r3.append(r5);
        r0 = r24;
        r3 = r3.append(r0);
        r3 = r3.toString();
        cn.jiguang.e.d.c(r2, r3);
        r2 = "<<error>>";
        goto L_0x02aa;
    L_0x0440:
        r2 = "PhoneNumberUtil";
        r3 = new java.lang.StringBuilder;
        r5 = "Other wrong response status - ";
        r3.<init>(r5);
        r3 = r3.append(r12);
        r5 = ", url:";
        r3 = r3.append(r5);
        r0 = r24;
        r3 = r3.append(r0);
        r3 = r3.toString();
        cn.jiguang.e.d.c(r2, r3);
        r2 = "<<error>>";
        goto L_0x02aa;
    L_0x0464:
        r2 = move-exception;
        r14 = r4;
        r15 = r5;
        goto L_0x031d;
    L_0x0469:
        r2 = move-exception;
        goto L_0x031d;
    L_0x046c:
        r2 = move-exception;
        r15 = r3;
        goto L_0x031d;
    L_0x0470:
        r4 = move-exception;
        r12 = r2;
        r15 = r3;
        r2 = r4;
        goto L_0x02f0;
    L_0x0476:
        r3 = move-exception;
        r12 = r2;
        r2 = r3;
        goto L_0x02f0;
    L_0x047b:
        r2 = move-exception;
        goto L_0x02f0;
    L_0x047e:
        r2 = move-exception;
        r14 = r3;
        goto L_0x02f0;
    L_0x0482:
        r4 = move-exception;
        r12 = r2;
        r15 = r3;
        r2 = r4;
        goto L_0x02d3;
    L_0x0488:
        r3 = move-exception;
        r12 = r2;
        r2 = r3;
        goto L_0x02d3;
    L_0x048d:
        r2 = move-exception;
        goto L_0x02d3;
    L_0x0490:
        r2 = move-exception;
        r14 = r3;
        goto L_0x02d3;
    L_0x0494:
        r2 = move-exception;
        r2 = r12;
        r5 = r15;
        r21 = r3;
        r3 = r4;
        r4 = r21;
        goto L_0x019f;
    L_0x049e:
        r4 = move-exception;
        r4 = r14;
        r5 = r3;
        r3 = r13;
        goto L_0x019f;
    L_0x04a4:
        r2 = move-exception;
        r2 = r12;
        r3 = r13;
        r4 = r14;
        r5 = r15;
        goto L_0x019f;
    L_0x04ab:
        r2 = move-exception;
        r2 = r12;
        r4 = r3;
        r5 = r15;
        r3 = r13;
        goto L_0x019f;
    L_0x04b2:
        r2 = r11;
        goto L_0x02aa;
    L_0x04b5:
        r2 = r12;
        r4 = r15;
        goto L_0x01b3;
    L_0x04b9:
        r13 = r3;
        r14 = r4;
        r4 = r5;
        goto L_0x01b3;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.a.a.c.i.a(android.content.Context, java.lang.String, int, long, boolean, java.io.File, java.lang.String):cn.jiguang.a.a.c.j");
    }

    private String a(int i) {
        String b = (i < 0 || i >= 3) ? "http://182.92.20.189:9099/" : cn.jiguang.d.a.a.b(this.g, "number_url" + i, "http://182.92.20.189:9099/");
        this.i = b;
        d.a("PhoneNumberUtil", "base url:" + this.i);
        return this.i;
    }

    private String a(String str, j jVar) {
        return a(this.g, jVar) ? d(str) : null;
    }

    private String a(TreeMap<String, String> treeMap) {
        if (treeMap.size() == 0) {
            d.g("PhoneNumberUtil", "treeMap is null");
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry value : treeMap.entrySet()) {
            stringBuilder.append(value.getValue());
        }
        String b = cn.jiguang.a.b.a.b(this.g);
        d.a("PhoneNumberUtil", "appSecret:" + b);
        b = a.a(stringBuilder.toString() + b).toUpperCase();
        d.a("PhoneNumberUtil", "treeMapValue:" + stringBuilder);
        d.a("PhoneNumberUtil", "sign:" + b);
        return b;
    }

    public static void a(Context context) {
        d.a("PhoneNumberUtil", "Action - loadPhoneNumber");
        if (c.get() >= 2) {
            d.a("PhoneNumberUtil", "more than one load phonenumber thread is running");
        } else {
            a.execute(new i(context));
        }
    }

    private static void a(Map<String, List<String>> map) {
        List<String> list = (List) map.get(HttpHeaders.HEAD_KEY_SET_COOKIE);
        if (list != null) {
            for (String parse : list) {
                j.getCookieStore().add(null, (HttpCookie) HttpCookie.parse(parse).get(0));
            }
        }
    }

    private static boolean a(Context context, j jVar) {
        Throwable e;
        Closeable openFileOutput;
        IOException e2;
        FileNotFoundException e3;
        UnsupportedEncodingException e4;
        NullPointerException e5;
        if (context == null) {
            d.g("PhoneNumberUtil", "context did not init, return");
            return false;
        } else if (jVar == null) {
            return false;
        } else {
            try {
                d.a("PhoneNumberUtil", "save log in writeHistoryLog:\n" + jVar);
            } catch (Throwable e6) {
                d.c("PhoneNumberUtil", "save log in writeHistoryLog", e6);
            }
            String str = "resp.raw";
            StringBuilder stringBuilder = new StringBuilder("");
            if (jVar.c != null && jVar.c.size() > 0) {
                for (Entry entry : jVar.c.entrySet()) {
                    if (entry.getKey() != null) {
                        stringBuilder.append((String) entry.getKey()).append(": ");
                    }
                    Iterator it = ((List) entry.getValue()).iterator();
                    if (it.hasNext()) {
                        stringBuilder.append((String) it.next());
                        while (it.hasNext()) {
                            stringBuilder.append(", ").append((String) it.next());
                        }
                    }
                    stringBuilder.append("\n");
                }
            }
            stringBuilder.append("\r\n\r\n");
            if (!TextUtils.isEmpty(jVar.b)) {
                stringBuilder.append(jVar.b);
            }
            try {
                context.deleteFile(str);
                openFileOutput = context.openFileOutput(str, 0);
                try {
                    boolean z;
                    openFileOutput.write(stringBuilder.toString().getBytes("UTF-8"));
                    o.a(openFileOutput);
                    try {
                        String str2 = "resp.zip";
                        context.deleteFile(str2);
                        String str3 = context.getFilesDir() + File.separator;
                        Collection arrayList = new ArrayList();
                        arrayList.add(new File(str3 + "resp.raw"));
                        f.a(arrayList, new File(str3 + str2));
                        z = true;
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        z = false;
                    }
                    return z;
                } catch (FileNotFoundException e7) {
                    e3 = e7;
                    try {
                        d.c("PhoneNumberUtil", "can't open " + str + " outputStream, give up save :" + e3.getMessage());
                        o.a(openFileOutput);
                        return false;
                    } catch (Throwable th) {
                        e6 = th;
                        o.a(openFileOutput);
                        throw e6;
                    }
                } catch (UnsupportedEncodingException e8) {
                    e4 = e8;
                    d.c("PhoneNumberUtil", "can't encoding " + str + " , give up save :" + e4.getMessage());
                    o.a(openFileOutput);
                    return false;
                } catch (IOException e9) {
                    e22 = e9;
                    d.c("PhoneNumberUtil", "can't write " + str + " , give up save :" + e22.getMessage());
                    o.a(openFileOutput);
                    return false;
                } catch (NullPointerException e10) {
                    e5 = e10;
                    d.c("PhoneNumberUtil", "Filepath error of [" + str + "] , give up save :" + e5.getMessage());
                    o.a(openFileOutput);
                    return false;
                }
            } catch (FileNotFoundException e11) {
                e3 = e11;
                openFileOutput = null;
                d.c("PhoneNumberUtil", "can't open " + str + " outputStream, give up save :" + e3.getMessage());
                o.a(openFileOutput);
                return false;
            } catch (UnsupportedEncodingException e12) {
                e4 = e12;
                openFileOutput = null;
                d.c("PhoneNumberUtil", "can't encoding " + str + " , give up save :" + e4.getMessage());
                o.a(openFileOutput);
                return false;
            } catch (IOException e13) {
                e22 = e13;
                openFileOutput = null;
                d.c("PhoneNumberUtil", "can't write " + str + " , give up save :" + e22.getMessage());
                o.a(openFileOutput);
                return false;
            } catch (NullPointerException e14) {
                e5 = e14;
                openFileOutput = null;
                d.c("PhoneNumberUtil", "Filepath error of [" + str + "] , give up save :" + e5.getMessage());
                o.a(openFileOutput);
                return false;
            } catch (Throwable th2) {
                e6 = th2;
                openFileOutput = null;
                o.a(openFileOutput);
                throw e6;
            }
        }
    }

    private boolean a(String str, String str2, String str3) {
        d.a("PhoneNumberUtil", "Action - getPhoneNumber imei:" + str + " iccid:" + str2 + " imsi:" + str3);
        TreeMap treeMap = new TreeMap();
        if (!k.a(str)) {
            treeMap.put("imei", str);
        }
        if (!k.a(str2)) {
            treeMap.put("iccid", str2);
        }
        if (!k.a(str3)) {
            treeMap.put("imsi", str3);
        }
        treeMap.put("version", cn.jiguang.d.a.a.b(this.g, "number_version", "1.3.0"));
        treeMap.put("app_id", cn.jiguang.d.a.a.b(this.g, "number_appid", Constants.VIA_SHARE_TYPE_PUBLISHMOOD));
        treeMap.put("req_time", cn.jiguang.d.g.a.b());
        treeMap.put("sign", a(treeMap));
        String str4 = "";
        for (Entry entry : treeMap.entrySet()) {
            try {
                str4 = str4 + HttpUtils.PARAMETERS_SEPARATOR + entry.getKey() + HttpUtils.EQUAL_SIGN + URLEncoder.encode((String) entry.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
        d.a("PhoneNumberUtil", "url:" + str4);
        try {
            j a = a(this.g, this.i + "statistic/query?" + str4, 10, StatisticConfig.MIN_UPLOAD_INTERVAL, false, null, null);
            d.a("PhoneNumberUtil", " statusCode:" + a.a + " rspData:" + a.b);
            if (a.a != 200) {
                return false;
            }
            Object b;
            JSONObject c = c(a.b);
            if (c != null) {
                if (c.optInt("code", -1) != 200) {
                    return false;
                }
                b = b(c.optString("num"));
            } else if (a.c == null && TextUtils.isEmpty(a.b)) {
                b = null;
            } else {
                synchronized (b) {
                    this.h = 0;
                    try {
                        b = a(str4, a);
                    } catch (Throwable th) {
                        b = null;
                    }
                    this.g.deleteFile("resp.raw");
                    this.g.deleteFile("resp.zip");
                }
            }
            if (TextUtils.isEmpty(b)) {
                return false;
            }
            e(b);
            return true;
        } catch (Throwable th2) {
            return false;
        }
    }

    public static String b(Context context) {
        if (context == null) {
            d.g("PhoneNumberUtil", "context is null");
            return "";
        }
        String d = a.d(context, "");
        String c = a.c(context, "");
        d = a.a(d + c + a.e(context, ""));
        d.a("PhoneNumberUtil", "ret:" + d);
        return d;
    }

    private String b(String str) {
        String f = f(str);
        d.a("PhoneNumberUtil", "decodePhoneNumber decrypt data:" + f);
        if (k.a(f)) {
            d.g("PhoneNumberUtil", "after decrypted, phoneNumber is null");
            return null;
        } else if (Patterns.PHONE.matcher(f).matches()) {
            return f;
        } else {
            d.g("PhoneNumberUtil", "invalide phone number");
            return null;
        }
    }

    private static JSONObject c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return new JSONObject(str);
            }
        } catch (Exception e) {
        }
        return null;
    }

    private String d(String str) {
        d.c("PhoneNumberUtil", "Action - getPhoneNumberWithAnalysisUrl url:" + str);
        if (TextUtils.isEmpty(str)) {
            d.g("PhoneNumberUtil", "url is empty");
            return null;
        }
        try {
            j a = a(this.g, this.i + "statistic/query?" + str, 10, StatisticConfig.MIN_UPLOAD_INTERVAL, false, new File(this.g.getFilesDir() + File.separator + "resp.zip"), "resp_data");
            d.a("PhoneNumberUtil", " statusCode:" + a.a + " rspData:" + a.b);
            if (a.a != 200) {
                return null;
            }
            String a2;
            JSONObject c = c(a.b);
            if (c == null) {
                if (!(a.c == null && TextUtils.isEmpty(a.b))) {
                    if (this.h > 4) {
                        return null;
                    }
                    this.h++;
                    try {
                        a2 = a(str, a);
                    } catch (Throwable th) {
                    }
                }
                a2 = null;
            } else if (c.optInt("code", -1) != 200) {
                return null;
            } else {
                a2 = b(c.optString("num"));
            }
            return a2;
        } catch (Throwable th2) {
            return null;
        }
    }

    private void e(String str) {
        d.a("PhoneNumberUtil", "Acton - onDetchPhoneNumber phoneNumber:" + str);
        Context context = this.g;
        String b = b(context);
        if (k.a(b)) {
            b = "number_num";
        }
        cn.jiguang.d.a.a.a(context, b, str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("num", str);
            if (!k.a(this.d)) {
                jSONObject.put("imei", this.d);
            }
            if (!k.a(this.f)) {
                jSONObject.put("imsi", this.f);
            }
            if (!k.a(this.e)) {
                jSONObject.put("iccid", this.e);
            }
            try {
                b = cn.jiguang.d.g.a.a.a(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!k.a(b)) {
                JSONObject jSONObject2 = new JSONObject();
                o.b(this.g, jSONObject2, "nb");
                jSONObject2.put("content", b);
                d.a("PhoneNumberUtil", "enCrptyContent:" + b);
                d.a("PhoneNumberUtil", "reprot content:" + jSONObject2.toString());
                c.a(this.g, jSONObject2);
                cn.jiguang.a.b.a.c(this.g, false);
            }
        } catch (Throwable e2) {
            d.e("PhoneNumberUtil", "unexception", e2);
        }
    }

    private String f(String str) {
        try {
            return cn.jiguang.d.g.a.a.a(str, cn.jiguang.a.b.a.b(this.g).substring(0, 16));
        } catch (Exception e) {
            d.g("PhoneNumberUtil", "Unexpected - failed to AES decrypt.e:" + e.getMessage());
            return null;
        }
    }

    public final void run() {
        int i = 0;
        c.incrementAndGet();
        try {
            if (k.a(this.d) && k.a(this.e) && k.a(this.f)) {
                d.g("PhoneNumberUtil", "all param is invalide");
                return;
            }
            Context context = this.g;
            String b = b(context);
            if (k.a(b)) {
                b = "number_num";
            }
            String b2 = cn.jiguang.d.a.a.b(context, b, "");
            if (k.a(b2) || ((Boolean) cn.jiguang.d.a.d.b(this.g, "nb_upload", Boolean.valueOf(false))).booleanValue()) {
                int i2;
                cn.jiguang.d.a.d.a(this.g, "nb_lasttime", Long.valueOf(System.currentTimeMillis()));
                b = this.f;
                if (!k.a(b)) {
                    if (b.startsWith("46000") || b.startsWith("46002") || b.startsWith("46007") || b.startsWith("46008")) {
                        i2 = 0;
                        d.a("PhoneNumberUtil", "providersIndex:" + i2);
                        if (i2 == -1) {
                            a(i2);
                            if (!k.a(this.i)) {
                                a(this.d, this.e, this.f);
                            }
                        } else {
                            b = "";
                            while (i < 3) {
                                a(i);
                                d.a("PhoneNumberUtil", "providersIndex:" + i);
                                i++;
                                if (!k.a(this.i) || r0.equals(this.i)) {
                                    d.a("PhoneNumberUtil", "same with lasted access url");
                                } else {
                                    b = this.i;
                                    if (a(this.d, this.e, this.f)) {
                                        break;
                                    }
                                }
                            }
                        }
                        c.decrementAndGet();
                    } else if (b.startsWith("46001") || b.startsWith("46006") || b.startsWith("46009")) {
                        i2 = 1;
                        d.a("PhoneNumberUtil", "providersIndex:" + i2);
                        if (i2 == -1) {
                            b = "";
                            while (i < 3) {
                                a(i);
                                d.a("PhoneNumberUtil", "providersIndex:" + i);
                                i++;
                                if (k.a(this.i)) {
                                }
                                d.a("PhoneNumberUtil", "same with lasted access url");
                            }
                        } else {
                            a(i2);
                            if (k.a(this.i)) {
                                a(this.d, this.e, this.f);
                            }
                        }
                        c.decrementAndGet();
                    } else if (b.startsWith("46003") || b.startsWith("46005") || b.startsWith("46011")) {
                        i2 = 2;
                        d.a("PhoneNumberUtil", "providersIndex:" + i2);
                        if (i2 == -1) {
                            a(i2);
                            if (k.a(this.i)) {
                                a(this.d, this.e, this.f);
                            }
                        } else {
                            b = "";
                            while (i < 3) {
                                a(i);
                                d.a("PhoneNumberUtil", "providersIndex:" + i);
                                i++;
                                if (k.a(this.i)) {
                                }
                                d.a("PhoneNumberUtil", "same with lasted access url");
                            }
                        }
                        c.decrementAndGet();
                    }
                }
                i2 = -1;
                d.a("PhoneNumberUtil", "providersIndex:" + i2);
                if (i2 == -1) {
                    b = "";
                    while (i < 3) {
                        a(i);
                        d.a("PhoneNumberUtil", "providersIndex:" + i);
                        i++;
                        if (k.a(this.i)) {
                        }
                        d.a("PhoneNumberUtil", "same with lasted access url");
                    }
                } else {
                    a(i2);
                    if (k.a(this.i)) {
                        a(this.d, this.e, this.f);
                    }
                }
                c.decrementAndGet();
            }
            e(b2);
            c.decrementAndGet();
        } catch (Exception e) {
        }
    }
}
