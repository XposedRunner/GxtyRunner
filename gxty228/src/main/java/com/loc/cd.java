package com.loc;

import android.content.Context;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.lzy.okgo.cookie.SerializableCookie;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* compiled from: DnsManager */
public final class cd {
    private static cd c = null;
    ch a = null;
    int b = 0;
    private Object d = null;
    private Context e = null;
    private ExecutorService f = null;
    private boolean g = false;
    private boolean h = true;
    private final int i = 2;
    private final String j = "https";
    private String k = "";
    private String l = "";
    private String[] m = null;
    private final int n = 5;
    private final int o = 2;

    /* compiled from: DnsManager */
    class a implements Runnable {
        ch a = null;
        final /* synthetic */ cd b;

        a(cd cdVar, ch chVar) {
            this.b = cdVar;
            this.a = chVar;
        }

        public final void run() {
            cd cdVar = this.b;
            cdVar.b++;
            this.b.b(this.a);
            cdVar = this.b;
            cdVar.b--;
        }
    }

    private cd() {
    }

    private cd(Context context) {
        this.e = context;
        Context context2 = this.e;
        try {
            if (ck.v()) {
                dk a = cl.a("HttpDNS", "1.0.0");
                if (cq.a(context2, a)) {
                    try {
                        this.d = x.a(context2, a, "com.autonavi.httpdns.HttpDnsManager", null, new Class[]{Context.class}, new Object[]{context2});
                    } catch (Throwable th) {
                    }
                    cq.a(context2, "HttpDns", this.d == null ? 0 : 1);
                }
            }
        } catch (Throwable th2) {
            cl.a(th2, "DNSManager", "initHttpDns");
        }
    }

    public static cd a(Context context) {
        if (c == null) {
            c = new cd(context);
        }
        return c;
    }

    private String a(String str) {
        String str2 = null;
        if (e()) {
            int i;
            try {
                String str3;
                String[] strArr = (String[]) co.a(this.d, "getIpsByHostAsync", str);
                if (strArr == null || strArr.length <= 0) {
                    str3 = null;
                } else {
                    if (this.m == null) {
                        this.m = strArr;
                        str2 = strArr[0];
                        i = 1;
                    } else if (a(strArr, this.m)) {
                        str2 = this.m[0];
                        i = 1;
                    } else {
                        this.m = strArr;
                        str3 = strArr[0];
                    }
                    cq.b(this.e, "HttpDns", i);
                }
                str2 = str3;
                i = 1;
            } catch (Throwable th) {
                i = 0;
            }
            cq.b(this.e, "HttpDns", i);
        }
        return str2;
    }

    private static boolean a(String[] strArr, String[] strArr2) {
        if (strArr != null && strArr2 == null) {
            return false;
        }
        if (strArr == null && strArr2 != null) {
            return false;
        }
        if (strArr == null && strArr2 == null) {
            return true;
        }
        try {
            if (strArr.length != strArr2.length) {
                return false;
            }
            List arrayList = new ArrayList(12);
            List arrayList2 = new ArrayList(12);
            arrayList.addAll(Arrays.asList(strArr));
            arrayList2.addAll(Arrays.asList(strArr2));
            Collections.sort(arrayList);
            Collections.sort(arrayList2);
            for (int i = 0; i < arrayList.size(); i++) {
                if (!((String) arrayList.get(i)).equals(arrayList2.get(i))) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static void d() {
        c = null;
    }

    private boolean e() {
        return ck.v() && this.d != null && !f() && cs.b(this.e, "pref", "dns_faile_count_total", 0) < 2;
    }

    private boolean f() {
        int parseInt;
        String str = null;
        try {
            if (VERSION.SDK_INT >= 14) {
                str = System.getProperty("http.proxyHost");
                String property = System.getProperty("http.proxyPort");
                if (property == null) {
                    property = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
                }
                parseInt = Integer.parseInt(property);
            } else {
                str = Proxy.getHost(this.e);
                parseInt = Proxy.getPort(this.e);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            parseInt = -1;
        }
        return (str == null || parseInt == -1) ? false : true;
    }

    public final void a() {
        if (!TextUtils.isEmpty(this.l)) {
            if (TextUtils.isEmpty(this.k) || !this.l.equals(this.k)) {
                this.k = this.l;
                cs.a(this.e, "ip", "last_ip", this.l);
            }
        }
    }

    public final void a(ch chVar) {
        try {
            this.g = false;
            if (e() && chVar != null) {
                this.a = chVar;
                String c = chVar.c();
                if (!c.substring(0, c.indexOf(":")).equalsIgnoreCase("https") && !"http://abroad.apilocate.amap.com/mobile/binary".equals(c)) {
                    String str;
                    if (c.contains("http://apilocate.amap.com/mobile/binary")) {
                        str = "http://apilocatesrc.amap.com/mobile/binary";
                        c = "apilocatesrc.amap.com";
                    } else {
                        str = c;
                        c = new URL(c).getHost();
                    }
                    CharSequence a = a(c);
                    if (this.h && TextUtils.isEmpty(a)) {
                        this.h = false;
                        a = cs.b(this.e, "ip", "last_ip", "");
                        if (!TextUtils.isEmpty(a)) {
                            this.k = a;
                        }
                    }
                    if (!TextUtils.isEmpty(a)) {
                        this.l = a;
                        chVar.g = str.replace(c, a);
                        chVar.a().put(SerializableCookie.HOST, c);
                        this.g = true;
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public final void b() {
        if (this.g) {
            cs.a(this.e, "pref", "dns_faile_count_total", 0);
        }
    }

    final synchronized void b(ch chVar) {
        try {
            chVar.g = cl.a();
            long b = cs.b(this.e, "pref", "dns_faile_count_total", 0);
            if (b < 2) {
                an.a();
                an.a(chVar, false);
                b++;
                if (b >= 2) {
                    cr.a(this.e, "HttpDNS", "dns failed too much");
                }
                cs.a(this.e, "pref", "dns_faile_count_total", b);
            }
        } catch (Throwable th) {
            cs.a(this.e, "pref", "dns_faile_count_total", 0);
        }
    }

    public final void c() {
        try {
            if (e()) {
                if (this.g && this.m != null) {
                    String[] strArr = this.m;
                    if (strArr != null) {
                        try {
                            if (strArr.length > 1) {
                                List arrayList = new ArrayList(12);
                                arrayList.addAll(Arrays.asList(strArr));
                                Iterator it = arrayList.iterator();
                                String str = (String) it.next();
                                it.remove();
                                arrayList.add(str);
                                arrayList.toArray(strArr);
                            }
                        } catch (Throwable th) {
                        }
                    }
                }
                if (this.b <= 5 && this.g) {
                    if (this.f == null) {
                        this.f = j.d();
                    }
                    if (!this.f.isShutdown()) {
                        this.f.submit(new a(this, this.a));
                    }
                }
            }
        } catch (Throwable th2) {
        }
    }
}
