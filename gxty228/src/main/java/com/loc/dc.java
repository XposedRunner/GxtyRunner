package com.loc;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.net.URLDecoder;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AuthConfigManager */
public final class dc {
    public static int a = -1;
    public static String b = "";

    /* compiled from: AuthConfigManager */
    public static class a {
        @Deprecated
        public c A;
        public c B;
        public b C;
        public b D;
        public b E;
        public b F;
        public e G;
        public String a;
        public int b = -1;
        @Deprecated
        public JSONObject c;
        @Deprecated
        public JSONObject d;
        @Deprecated
        public JSONObject e;
        @Deprecated
        public JSONObject f;
        @Deprecated
        public JSONObject g;
        @Deprecated
        public JSONObject h;
        @Deprecated
        public JSONObject i;
        @Deprecated
        public JSONObject j;
        @Deprecated
        public JSONObject k;
        @Deprecated
        public JSONObject l;
        @Deprecated
        public JSONObject m;
        @Deprecated
        public JSONObject n;
        @Deprecated
        public JSONObject o;
        @Deprecated
        public JSONObject p;
        @Deprecated
        public JSONObject q;
        @Deprecated
        public JSONObject r;
        @Deprecated
        public JSONObject s;
        @Deprecated
        public JSONObject t;
        @Deprecated
        public JSONObject u;
        @Deprecated
        public JSONObject v;
        public JSONObject w;
        public a x;
        public d y;
        public f z;

        /* compiled from: AuthConfigManager */
        public static class a {
            public boolean a;
            public boolean b;
            public JSONObject c;
        }

        /* compiled from: AuthConfigManager */
        public static class b {
            public boolean a;
            public String b;
            public String c;
            public String d;
            public boolean e;
        }

        /* compiled from: AuthConfigManager */
        public static class c {
            public String a;
            public String b;
        }

        /* compiled from: AuthConfigManager */
        public static class d {
            public String a;
            public String b;
            public String c;
        }

        /* compiled from: AuthConfigManager */
        public static class e {
            public boolean a;
            public boolean b;
            public boolean c;
            public String d;
            public String e;
            public String f;
        }

        /* compiled from: AuthConfigManager */
        public static class f {
            public boolean a;
        }
    }

    /* compiled from: AuthConfigManager */
    static class b extends ao {
        private String f;
        private Map<String, String> g = null;
        private boolean h;

        b(Context context, dk dkVar, String str) {
            super(context, dkVar);
            this.f = str;
            this.h = VERSION.SDK_INT != 19;
        }

        public final Map<String, String> a() {
            return null;
        }

        public final String c() {
            return this.h ? "https://restapi.amap.com/v3/iasdkauth" : "http://restapi.amap.com/v3/iasdkauth";
        }

        protected final String g() {
            return "3.0";
        }

        public final byte[] h() {
            return null;
        }

        public final byte[] i() {
            Object u = df.u(this.a);
            if (TextUtils.isEmpty(u)) {
                u = df.c();
            }
            if (!TextUtils.isEmpty(u)) {
                u = dh.b(new StringBuilder(u).reverse().toString());
            }
            Map hashMap = new HashMap();
            hashMap.put("authkey", this.f);
            hashMap.put("plattype", "android");
            hashMap.put("product", this.b.a());
            hashMap.put("version", this.b.b());
            hashMap.put("output", "json");
            hashMap.put("androidversion", VERSION.SDK_INT);
            hashMap.put("deviceId", u);
            hashMap.put("manufacture", Build.MANUFACTURER);
            if (!(this.g == null || this.g.isEmpty())) {
                hashMap.putAll(this.g);
            }
            hashMap.put("abitype", dl.a(this.a));
            hashMap.put("ext", this.b.d());
            return dl.a(dl.a(hashMap));
        }

        public final boolean k() {
            return this.h;
        }
    }

    public static a a(Context context, dk dkVar, String str, boolean z) {
        as a;
        byte[] bArr;
        k e;
        Object obj;
        String a2;
        JSONObject jSONObject;
        int i;
        String str2;
        a aVar;
        JSONObject jSONObject2;
        String[] split;
        String a3;
        Throwable th;
        JSONObject jSONObject3;
        d dVar;
        Object a4;
        Object a5;
        c cVar;
        e eVar;
        String a6;
        String a7;
        String a8;
        String a9;
        f fVar;
        b bVar;
        dk a10;
        w wVar;
        as asVar = null;
        a aVar2 = new a();
        aVar2.w = new JSONObject();
        try {
            an anVar = new an();
            ar bVar2 = new b(context, dkVar, str + ";14N");
            a = an.a(bVar2, bVar2.k());
            if (a != null) {
                try {
                    bArr = a.a;
                } catch (k e2) {
                    e = e2;
                    bArr = asVar;
                    aVar2.a = e.a();
                    j.a(dkVar, "/v3/iasdkauth", e);
                    obj = asVar;
                    asVar = a;
                    if (bArr != null) {
                        if (TextUtils.isEmpty(a2)) {
                            a2 = dl.a(bArr);
                        }
                        try {
                            jSONObject = new JSONObject(a2);
                            if (jSONObject.has("status")) {
                                i = jSONObject.getInt("status");
                                if (i == 1) {
                                    a = 1;
                                } else if (i == 0) {
                                    a2 = "authcsid";
                                    str2 = "authgsid";
                                    if (asVar != null) {
                                        a2 = asVar.c;
                                        str2 = asVar.d;
                                    }
                                    dl.a(context, a2, str2, jSONObject);
                                    a = 0;
                                    if (jSONObject.has("info")) {
                                        b = jSONObject.getString("info");
                                    }
                                    a2 = "";
                                    if (jSONObject.has("infocode")) {
                                        a2 = jSONObject.getString("infocode");
                                    }
                                    j.a(dkVar, "/v3/iasdkauth", b, str2, a2);
                                    if (a == 0) {
                                        aVar2.a = b;
                                    }
                                }
                                if (jSONObject.has("ver")) {
                                    aVar2.b = jSONObject.getInt("ver");
                                }
                                if (dl.a(jSONObject, "result")) {
                                    aVar = new a();
                                    aVar.a = false;
                                    aVar.b = false;
                                    aVar2.x = aVar;
                                    jSONObject2 = jSONObject.getJSONObject("result");
                                    try {
                                        split = str.split(";");
                                        for (String a32 : split) {
                                            if (jSONObject2.has(a32)) {
                                                aVar2.w.putOpt(a32, jSONObject2.get(a32));
                                            }
                                        }
                                    } catch (Throwable th2) {
                                        g.a(th2, IXAdRequestInfo.AD_TYPE, "co");
                                    }
                                    if (dl.a(jSONObject2, "11K")) {
                                        try {
                                            jSONObject3 = jSONObject2.getJSONObject("11K");
                                            aVar.a = a(jSONObject3.getString("able"), false);
                                            if (jSONObject3.has("off")) {
                                                aVar.c = jSONObject3.getJSONObject("off");
                                            }
                                        } catch (Throwable th22) {
                                            g.a(th22, "AuthConfigManager", "loadException");
                                        }
                                    }
                                    if (dl.a(jSONObject2, "001")) {
                                        jSONObject3 = jSONObject2.getJSONObject("001");
                                        dVar = new d();
                                        if (jSONObject3 != null) {
                                            try {
                                                a4 = a(jSONObject3, "md5");
                                                a5 = a(jSONObject3, "url");
                                                obj = a(jSONObject3, "sdkversion");
                                                dVar.a = a5;
                                                dVar.b = a4;
                                                dVar.c = obj;
                                            } catch (Throwable th222) {
                                                g.a(th222, IXAdRequestInfo.AD_TYPE, "psu");
                                            }
                                        }
                                        aVar2.y = dVar;
                                    }
                                    if (dl.a(jSONObject2, "002")) {
                                        jSONObject3 = jSONObject2.getJSONObject("002");
                                        cVar = new c();
                                        a(jSONObject3, cVar);
                                        aVar2.A = cVar;
                                    }
                                    if (dl.a(jSONObject2, "14S")) {
                                        jSONObject3 = jSONObject2.getJSONObject("14S");
                                        cVar = new c();
                                        a(jSONObject3, cVar);
                                        aVar2.B = cVar;
                                    }
                                    a(aVar2, jSONObject2);
                                    if (dl.a(jSONObject2, "14Z")) {
                                        jSONObject3 = jSONObject2.getJSONObject("14Z");
                                        eVar = new e();
                                        try {
                                            a6 = a(jSONObject3, "md5");
                                            a32 = a(jSONObject3, "md5info");
                                            a7 = a(jSONObject3, "url");
                                            a8 = a(jSONObject3, "able");
                                            a9 = a(jSONObject3, "on");
                                            a2 = a(jSONObject3, "mobileable");
                                            eVar.e = a6;
                                            eVar.f = a32;
                                            eVar.d = a7;
                                            eVar.a = a(a8, false);
                                            eVar.b = a(a9, false);
                                            eVar.c = a(a2, false);
                                        } catch (Throwable th2222) {
                                            g.a(th2222, IXAdRequestInfo.AD_TYPE, "pes");
                                        }
                                        aVar2.G = eVar;
                                    }
                                    if (dl.a(jSONObject2, "151")) {
                                        jSONObject3 = jSONObject2.getJSONObject("151");
                                        fVar = new f();
                                        if (jSONObject3 != null) {
                                            fVar.a = a(jSONObject3.optString("able"), false);
                                        }
                                        aVar2.z = fVar;
                                    }
                                    a(aVar2, jSONObject2);
                                    if (dl.a(jSONObject2, "14N")) {
                                        jSONObject3 = jSONObject2.getJSONObject("14N");
                                        bVar = new b();
                                        bVar.a = a(jSONObject3.optString("able"), false);
                                        bVar.b = jSONObject3.optString("url");
                                        bVar.c = jSONObject3.optString("md5");
                                        if (bVar.a) {
                                            a10 = e.a();
                                            if (a10 != null) {
                                                wVar = new w(bVar.b, bVar.c);
                                                wVar.a(z);
                                                new v(context, wVar, a10).a();
                                            }
                                        } else {
                                            x.a(context, "aiu");
                                        }
                                    }
                                }
                            }
                        } catch (Throwable th22222) {
                            g.a(th22222, IXAdRequestInfo.AD_TYPE, "lc");
                        }
                    }
                    return aVar2;
                } catch (IllegalBlockSizeException e3) {
                    bArr = asVar;
                    obj = asVar;
                    asVar = a;
                    if (bArr != null) {
                        if (TextUtils.isEmpty(a2)) {
                            a2 = dl.a(bArr);
                        }
                        jSONObject = new JSONObject(a2);
                        if (jSONObject.has("status")) {
                            i = jSONObject.getInt("status");
                            if (i == 1) {
                                a = 1;
                            } else if (i == 0) {
                                a2 = "authcsid";
                                str2 = "authgsid";
                                if (asVar != null) {
                                    a2 = asVar.c;
                                    str2 = asVar.d;
                                }
                                dl.a(context, a2, str2, jSONObject);
                                a = 0;
                                if (jSONObject.has("info")) {
                                    b = jSONObject.getString("info");
                                }
                                a2 = "";
                                if (jSONObject.has("infocode")) {
                                    a2 = jSONObject.getString("infocode");
                                }
                                j.a(dkVar, "/v3/iasdkauth", b, str2, a2);
                                if (a == 0) {
                                    aVar2.a = b;
                                }
                            }
                            if (jSONObject.has("ver")) {
                                aVar2.b = jSONObject.getInt("ver");
                            }
                            if (dl.a(jSONObject, "result")) {
                                aVar = new a();
                                aVar.a = false;
                                aVar.b = false;
                                aVar2.x = aVar;
                                jSONObject2 = jSONObject.getJSONObject("result");
                                split = str.split(";");
                                for (String a322 : split) {
                                    if (jSONObject2.has(a322)) {
                                        aVar2.w.putOpt(a322, jSONObject2.get(a322));
                                    }
                                }
                                if (dl.a(jSONObject2, "11K")) {
                                    jSONObject3 = jSONObject2.getJSONObject("11K");
                                    aVar.a = a(jSONObject3.getString("able"), false);
                                    if (jSONObject3.has("off")) {
                                        aVar.c = jSONObject3.getJSONObject("off");
                                    }
                                }
                                if (dl.a(jSONObject2, "001")) {
                                    jSONObject3 = jSONObject2.getJSONObject("001");
                                    dVar = new d();
                                    if (jSONObject3 != null) {
                                        a4 = a(jSONObject3, "md5");
                                        a5 = a(jSONObject3, "url");
                                        obj = a(jSONObject3, "sdkversion");
                                        dVar.a = a5;
                                        dVar.b = a4;
                                        dVar.c = obj;
                                    }
                                    aVar2.y = dVar;
                                }
                                if (dl.a(jSONObject2, "002")) {
                                    jSONObject3 = jSONObject2.getJSONObject("002");
                                    cVar = new c();
                                    a(jSONObject3, cVar);
                                    aVar2.A = cVar;
                                }
                                if (dl.a(jSONObject2, "14S")) {
                                    jSONObject3 = jSONObject2.getJSONObject("14S");
                                    cVar = new c();
                                    a(jSONObject3, cVar);
                                    aVar2.B = cVar;
                                }
                                a(aVar2, jSONObject2);
                                if (dl.a(jSONObject2, "14Z")) {
                                    jSONObject3 = jSONObject2.getJSONObject("14Z");
                                    eVar = new e();
                                    a6 = a(jSONObject3, "md5");
                                    a322 = a(jSONObject3, "md5info");
                                    a7 = a(jSONObject3, "url");
                                    a8 = a(jSONObject3, "able");
                                    a9 = a(jSONObject3, "on");
                                    a2 = a(jSONObject3, "mobileable");
                                    eVar.e = a6;
                                    eVar.f = a322;
                                    eVar.d = a7;
                                    eVar.a = a(a8, false);
                                    eVar.b = a(a9, false);
                                    eVar.c = a(a2, false);
                                    aVar2.G = eVar;
                                }
                                if (dl.a(jSONObject2, "151")) {
                                    jSONObject3 = jSONObject2.getJSONObject("151");
                                    fVar = new f();
                                    if (jSONObject3 != null) {
                                        fVar.a = a(jSONObject3.optString("able"), false);
                                    }
                                    aVar2.z = fVar;
                                }
                                a(aVar2, jSONObject2);
                                if (dl.a(jSONObject2, "14N")) {
                                    jSONObject3 = jSONObject2.getJSONObject("14N");
                                    bVar = new b();
                                    bVar.a = a(jSONObject3.optString("able"), false);
                                    bVar.b = jSONObject3.optString("url");
                                    bVar.c = jSONObject3.optString("md5");
                                    if (bVar.a) {
                                        a10 = e.a();
                                        if (a10 != null) {
                                            wVar = new w(bVar.b, bVar.c);
                                            wVar.a(z);
                                            new v(context, wVar, a10).a();
                                        }
                                    } else {
                                        x.a(context, "aiu");
                                    }
                                }
                            }
                        }
                    }
                    return aVar2;
                } catch (Throwable th3) {
                    th22222 = th3;
                    bArr = asVar;
                    j.b(th22222, IXAdRequestInfo.AD_TYPE, "lc");
                    obj = asVar;
                    asVar = a;
                    if (bArr != null) {
                        if (TextUtils.isEmpty(a2)) {
                            a2 = dl.a(bArr);
                        }
                        jSONObject = new JSONObject(a2);
                        if (jSONObject.has("status")) {
                            i = jSONObject.getInt("status");
                            if (i == 1) {
                                a = 1;
                            } else if (i == 0) {
                                a2 = "authcsid";
                                str2 = "authgsid";
                                if (asVar != null) {
                                    a2 = asVar.c;
                                    str2 = asVar.d;
                                }
                                dl.a(context, a2, str2, jSONObject);
                                a = 0;
                                if (jSONObject.has("info")) {
                                    b = jSONObject.getString("info");
                                }
                                a2 = "";
                                if (jSONObject.has("infocode")) {
                                    a2 = jSONObject.getString("infocode");
                                }
                                j.a(dkVar, "/v3/iasdkauth", b, str2, a2);
                                if (a == 0) {
                                    aVar2.a = b;
                                }
                            }
                            if (jSONObject.has("ver")) {
                                aVar2.b = jSONObject.getInt("ver");
                            }
                            if (dl.a(jSONObject, "result")) {
                                aVar = new a();
                                aVar.a = false;
                                aVar.b = false;
                                aVar2.x = aVar;
                                jSONObject2 = jSONObject.getJSONObject("result");
                                split = str.split(";");
                                for (String a3222 : split) {
                                    if (jSONObject2.has(a3222)) {
                                        aVar2.w.putOpt(a3222, jSONObject2.get(a3222));
                                    }
                                }
                                if (dl.a(jSONObject2, "11K")) {
                                    jSONObject3 = jSONObject2.getJSONObject("11K");
                                    aVar.a = a(jSONObject3.getString("able"), false);
                                    if (jSONObject3.has("off")) {
                                        aVar.c = jSONObject3.getJSONObject("off");
                                    }
                                }
                                if (dl.a(jSONObject2, "001")) {
                                    jSONObject3 = jSONObject2.getJSONObject("001");
                                    dVar = new d();
                                    if (jSONObject3 != null) {
                                        a4 = a(jSONObject3, "md5");
                                        a5 = a(jSONObject3, "url");
                                        obj = a(jSONObject3, "sdkversion");
                                        dVar.a = a5;
                                        dVar.b = a4;
                                        dVar.c = obj;
                                    }
                                    aVar2.y = dVar;
                                }
                                if (dl.a(jSONObject2, "002")) {
                                    jSONObject3 = jSONObject2.getJSONObject("002");
                                    cVar = new c();
                                    a(jSONObject3, cVar);
                                    aVar2.A = cVar;
                                }
                                if (dl.a(jSONObject2, "14S")) {
                                    jSONObject3 = jSONObject2.getJSONObject("14S");
                                    cVar = new c();
                                    a(jSONObject3, cVar);
                                    aVar2.B = cVar;
                                }
                                a(aVar2, jSONObject2);
                                if (dl.a(jSONObject2, "14Z")) {
                                    jSONObject3 = jSONObject2.getJSONObject("14Z");
                                    eVar = new e();
                                    a6 = a(jSONObject3, "md5");
                                    a3222 = a(jSONObject3, "md5info");
                                    a7 = a(jSONObject3, "url");
                                    a8 = a(jSONObject3, "able");
                                    a9 = a(jSONObject3, "on");
                                    a2 = a(jSONObject3, "mobileable");
                                    eVar.e = a6;
                                    eVar.f = a3222;
                                    eVar.d = a7;
                                    eVar.a = a(a8, false);
                                    eVar.b = a(a9, false);
                                    eVar.c = a(a2, false);
                                    aVar2.G = eVar;
                                }
                                if (dl.a(jSONObject2, "151")) {
                                    jSONObject3 = jSONObject2.getJSONObject("151");
                                    fVar = new f();
                                    if (jSONObject3 != null) {
                                        fVar.a = a(jSONObject3.optString("able"), false);
                                    }
                                    aVar2.z = fVar;
                                }
                                a(aVar2, jSONObject2);
                                if (dl.a(jSONObject2, "14N")) {
                                    jSONObject3 = jSONObject2.getJSONObject("14N");
                                    bVar = new b();
                                    bVar.a = a(jSONObject3.optString("able"), false);
                                    bVar.b = jSONObject3.optString("url");
                                    bVar.c = jSONObject3.optString("md5");
                                    if (bVar.a) {
                                        x.a(context, "aiu");
                                    } else {
                                        a10 = e.a();
                                        if (a10 != null) {
                                            wVar = new w(bVar.b, bVar.c);
                                            wVar.a(z);
                                            new v(context, wVar, a10).a();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return aVar2;
                }
            }
            bArr = asVar;
            try {
                obj = new byte[16];
                Object obj2 = new byte[(bArr.length - 16)];
                System.arraycopy(bArr, 0, obj, 0, 16);
                System.arraycopy(bArr, 16, obj2, 0, bArr.length - 16);
                Key secretKeySpec = new SecretKeySpec(obj, "AES");
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, secretKeySpec, new IvParameterSpec(dl.b()));
                a2 = dl.a(instance.doFinal(obj2));
                asVar = a;
            } catch (k e4) {
                e = e4;
                aVar2.a = e.a();
                j.a(dkVar, "/v3/iasdkauth", e);
                obj = asVar;
                asVar = a;
                if (bArr != null) {
                    if (TextUtils.isEmpty(a2)) {
                        a2 = dl.a(bArr);
                    }
                    jSONObject = new JSONObject(a2);
                    if (jSONObject.has("status")) {
                        i = jSONObject.getInt("status");
                        if (i == 1) {
                            a = 1;
                        } else if (i == 0) {
                            a2 = "authcsid";
                            str2 = "authgsid";
                            if (asVar != null) {
                                a2 = asVar.c;
                                str2 = asVar.d;
                            }
                            dl.a(context, a2, str2, jSONObject);
                            a = 0;
                            if (jSONObject.has("info")) {
                                b = jSONObject.getString("info");
                            }
                            a2 = "";
                            if (jSONObject.has("infocode")) {
                                a2 = jSONObject.getString("infocode");
                            }
                            j.a(dkVar, "/v3/iasdkauth", b, str2, a2);
                            if (a == 0) {
                                aVar2.a = b;
                            }
                        }
                        if (jSONObject.has("ver")) {
                            aVar2.b = jSONObject.getInt("ver");
                        }
                        if (dl.a(jSONObject, "result")) {
                            aVar = new a();
                            aVar.a = false;
                            aVar.b = false;
                            aVar2.x = aVar;
                            jSONObject2 = jSONObject.getJSONObject("result");
                            split = str.split(";");
                            for (String a32222 : split) {
                                if (jSONObject2.has(a32222)) {
                                    aVar2.w.putOpt(a32222, jSONObject2.get(a32222));
                                }
                            }
                            if (dl.a(jSONObject2, "11K")) {
                                jSONObject3 = jSONObject2.getJSONObject("11K");
                                aVar.a = a(jSONObject3.getString("able"), false);
                                if (jSONObject3.has("off")) {
                                    aVar.c = jSONObject3.getJSONObject("off");
                                }
                            }
                            if (dl.a(jSONObject2, "001")) {
                                jSONObject3 = jSONObject2.getJSONObject("001");
                                dVar = new d();
                                if (jSONObject3 != null) {
                                    a4 = a(jSONObject3, "md5");
                                    a5 = a(jSONObject3, "url");
                                    obj = a(jSONObject3, "sdkversion");
                                    dVar.a = a5;
                                    dVar.b = a4;
                                    dVar.c = obj;
                                }
                                aVar2.y = dVar;
                            }
                            if (dl.a(jSONObject2, "002")) {
                                jSONObject3 = jSONObject2.getJSONObject("002");
                                cVar = new c();
                                a(jSONObject3, cVar);
                                aVar2.A = cVar;
                            }
                            if (dl.a(jSONObject2, "14S")) {
                                jSONObject3 = jSONObject2.getJSONObject("14S");
                                cVar = new c();
                                a(jSONObject3, cVar);
                                aVar2.B = cVar;
                            }
                            a(aVar2, jSONObject2);
                            if (dl.a(jSONObject2, "14Z")) {
                                jSONObject3 = jSONObject2.getJSONObject("14Z");
                                eVar = new e();
                                a6 = a(jSONObject3, "md5");
                                a32222 = a(jSONObject3, "md5info");
                                a7 = a(jSONObject3, "url");
                                a8 = a(jSONObject3, "able");
                                a9 = a(jSONObject3, "on");
                                a2 = a(jSONObject3, "mobileable");
                                eVar.e = a6;
                                eVar.f = a32222;
                                eVar.d = a7;
                                eVar.a = a(a8, false);
                                eVar.b = a(a9, false);
                                eVar.c = a(a2, false);
                                aVar2.G = eVar;
                            }
                            if (dl.a(jSONObject2, "151")) {
                                jSONObject3 = jSONObject2.getJSONObject("151");
                                fVar = new f();
                                if (jSONObject3 != null) {
                                    fVar.a = a(jSONObject3.optString("able"), false);
                                }
                                aVar2.z = fVar;
                            }
                            a(aVar2, jSONObject2);
                            if (dl.a(jSONObject2, "14N")) {
                                jSONObject3 = jSONObject2.getJSONObject("14N");
                                bVar = new b();
                                bVar.a = a(jSONObject3.optString("able"), false);
                                bVar.b = jSONObject3.optString("url");
                                bVar.c = jSONObject3.optString("md5");
                                if (bVar.a) {
                                    x.a(context, "aiu");
                                } else {
                                    a10 = e.a();
                                    if (a10 != null) {
                                        wVar = new w(bVar.b, bVar.c);
                                        wVar.a(z);
                                        new v(context, wVar, a10).a();
                                    }
                                }
                            }
                        }
                    }
                }
                return aVar2;
            } catch (IllegalBlockSizeException e5) {
                obj = asVar;
                asVar = a;
                if (bArr != null) {
                    if (TextUtils.isEmpty(a2)) {
                        a2 = dl.a(bArr);
                    }
                    jSONObject = new JSONObject(a2);
                    if (jSONObject.has("status")) {
                        i = jSONObject.getInt("status");
                        if (i == 1) {
                            a = 1;
                        } else if (i == 0) {
                            a2 = "authcsid";
                            str2 = "authgsid";
                            if (asVar != null) {
                                a2 = asVar.c;
                                str2 = asVar.d;
                            }
                            dl.a(context, a2, str2, jSONObject);
                            a = 0;
                            if (jSONObject.has("info")) {
                                b = jSONObject.getString("info");
                            }
                            a2 = "";
                            if (jSONObject.has("infocode")) {
                                a2 = jSONObject.getString("infocode");
                            }
                            j.a(dkVar, "/v3/iasdkauth", b, str2, a2);
                            if (a == 0) {
                                aVar2.a = b;
                            }
                        }
                        if (jSONObject.has("ver")) {
                            aVar2.b = jSONObject.getInt("ver");
                        }
                        if (dl.a(jSONObject, "result")) {
                            aVar = new a();
                            aVar.a = false;
                            aVar.b = false;
                            aVar2.x = aVar;
                            jSONObject2 = jSONObject.getJSONObject("result");
                            split = str.split(";");
                            for (String a322222 : split) {
                                if (jSONObject2.has(a322222)) {
                                    aVar2.w.putOpt(a322222, jSONObject2.get(a322222));
                                }
                            }
                            if (dl.a(jSONObject2, "11K")) {
                                jSONObject3 = jSONObject2.getJSONObject("11K");
                                aVar.a = a(jSONObject3.getString("able"), false);
                                if (jSONObject3.has("off")) {
                                    aVar.c = jSONObject3.getJSONObject("off");
                                }
                            }
                            if (dl.a(jSONObject2, "001")) {
                                jSONObject3 = jSONObject2.getJSONObject("001");
                                dVar = new d();
                                if (jSONObject3 != null) {
                                    a4 = a(jSONObject3, "md5");
                                    a5 = a(jSONObject3, "url");
                                    obj = a(jSONObject3, "sdkversion");
                                    dVar.a = a5;
                                    dVar.b = a4;
                                    dVar.c = obj;
                                }
                                aVar2.y = dVar;
                            }
                            if (dl.a(jSONObject2, "002")) {
                                jSONObject3 = jSONObject2.getJSONObject("002");
                                cVar = new c();
                                a(jSONObject3, cVar);
                                aVar2.A = cVar;
                            }
                            if (dl.a(jSONObject2, "14S")) {
                                jSONObject3 = jSONObject2.getJSONObject("14S");
                                cVar = new c();
                                a(jSONObject3, cVar);
                                aVar2.B = cVar;
                            }
                            a(aVar2, jSONObject2);
                            if (dl.a(jSONObject2, "14Z")) {
                                jSONObject3 = jSONObject2.getJSONObject("14Z");
                                eVar = new e();
                                a6 = a(jSONObject3, "md5");
                                a322222 = a(jSONObject3, "md5info");
                                a7 = a(jSONObject3, "url");
                                a8 = a(jSONObject3, "able");
                                a9 = a(jSONObject3, "on");
                                a2 = a(jSONObject3, "mobileable");
                                eVar.e = a6;
                                eVar.f = a322222;
                                eVar.d = a7;
                                eVar.a = a(a8, false);
                                eVar.b = a(a9, false);
                                eVar.c = a(a2, false);
                                aVar2.G = eVar;
                            }
                            if (dl.a(jSONObject2, "151")) {
                                jSONObject3 = jSONObject2.getJSONObject("151");
                                fVar = new f();
                                if (jSONObject3 != null) {
                                    fVar.a = a(jSONObject3.optString("able"), false);
                                }
                                aVar2.z = fVar;
                            }
                            a(aVar2, jSONObject2);
                            if (dl.a(jSONObject2, "14N")) {
                                jSONObject3 = jSONObject2.getJSONObject("14N");
                                bVar = new b();
                                bVar.a = a(jSONObject3.optString("able"), false);
                                bVar.b = jSONObject3.optString("url");
                                bVar.c = jSONObject3.optString("md5");
                                if (bVar.a) {
                                    a10 = e.a();
                                    if (a10 != null) {
                                        wVar = new w(bVar.b, bVar.c);
                                        wVar.a(z);
                                        new v(context, wVar, a10).a();
                                    }
                                } else {
                                    x.a(context, "aiu");
                                }
                            }
                        }
                    }
                }
                return aVar2;
            } catch (Throwable th4) {
                th22222 = th4;
                j.b(th22222, IXAdRequestInfo.AD_TYPE, "lc");
                obj = asVar;
                asVar = a;
                if (bArr != null) {
                    if (TextUtils.isEmpty(a2)) {
                        a2 = dl.a(bArr);
                    }
                    jSONObject = new JSONObject(a2);
                    if (jSONObject.has("status")) {
                        i = jSONObject.getInt("status");
                        if (i == 1) {
                            a = 1;
                        } else if (i == 0) {
                            a2 = "authcsid";
                            str2 = "authgsid";
                            if (asVar != null) {
                                a2 = asVar.c;
                                str2 = asVar.d;
                            }
                            dl.a(context, a2, str2, jSONObject);
                            a = 0;
                            if (jSONObject.has("info")) {
                                b = jSONObject.getString("info");
                            }
                            a2 = "";
                            if (jSONObject.has("infocode")) {
                                a2 = jSONObject.getString("infocode");
                            }
                            j.a(dkVar, "/v3/iasdkauth", b, str2, a2);
                            if (a == 0) {
                                aVar2.a = b;
                            }
                        }
                        if (jSONObject.has("ver")) {
                            aVar2.b = jSONObject.getInt("ver");
                        }
                        if (dl.a(jSONObject, "result")) {
                            aVar = new a();
                            aVar.a = false;
                            aVar.b = false;
                            aVar2.x = aVar;
                            jSONObject2 = jSONObject.getJSONObject("result");
                            split = str.split(";");
                            for (String a3222222 : split) {
                                if (jSONObject2.has(a3222222)) {
                                    aVar2.w.putOpt(a3222222, jSONObject2.get(a3222222));
                                }
                            }
                            if (dl.a(jSONObject2, "11K")) {
                                jSONObject3 = jSONObject2.getJSONObject("11K");
                                aVar.a = a(jSONObject3.getString("able"), false);
                                if (jSONObject3.has("off")) {
                                    aVar.c = jSONObject3.getJSONObject("off");
                                }
                            }
                            if (dl.a(jSONObject2, "001")) {
                                jSONObject3 = jSONObject2.getJSONObject("001");
                                dVar = new d();
                                if (jSONObject3 != null) {
                                    a4 = a(jSONObject3, "md5");
                                    a5 = a(jSONObject3, "url");
                                    obj = a(jSONObject3, "sdkversion");
                                    dVar.a = a5;
                                    dVar.b = a4;
                                    dVar.c = obj;
                                }
                                aVar2.y = dVar;
                            }
                            if (dl.a(jSONObject2, "002")) {
                                jSONObject3 = jSONObject2.getJSONObject("002");
                                cVar = new c();
                                a(jSONObject3, cVar);
                                aVar2.A = cVar;
                            }
                            if (dl.a(jSONObject2, "14S")) {
                                jSONObject3 = jSONObject2.getJSONObject("14S");
                                cVar = new c();
                                a(jSONObject3, cVar);
                                aVar2.B = cVar;
                            }
                            a(aVar2, jSONObject2);
                            if (dl.a(jSONObject2, "14Z")) {
                                jSONObject3 = jSONObject2.getJSONObject("14Z");
                                eVar = new e();
                                a6 = a(jSONObject3, "md5");
                                a3222222 = a(jSONObject3, "md5info");
                                a7 = a(jSONObject3, "url");
                                a8 = a(jSONObject3, "able");
                                a9 = a(jSONObject3, "on");
                                a2 = a(jSONObject3, "mobileable");
                                eVar.e = a6;
                                eVar.f = a3222222;
                                eVar.d = a7;
                                eVar.a = a(a8, false);
                                eVar.b = a(a9, false);
                                eVar.c = a(a2, false);
                                aVar2.G = eVar;
                            }
                            if (dl.a(jSONObject2, "151")) {
                                jSONObject3 = jSONObject2.getJSONObject("151");
                                fVar = new f();
                                if (jSONObject3 != null) {
                                    fVar.a = a(jSONObject3.optString("able"), false);
                                }
                                aVar2.z = fVar;
                            }
                            a(aVar2, jSONObject2);
                            if (dl.a(jSONObject2, "14N")) {
                                jSONObject3 = jSONObject2.getJSONObject("14N");
                                bVar = new b();
                                bVar.a = a(jSONObject3.optString("able"), false);
                                bVar.b = jSONObject3.optString("url");
                                bVar.c = jSONObject3.optString("md5");
                                if (bVar.a) {
                                    x.a(context, "aiu");
                                } else {
                                    a10 = e.a();
                                    if (a10 != null) {
                                        wVar = new w(bVar.b, bVar.c);
                                        wVar.a(z);
                                        new v(context, wVar, a10).a();
                                    }
                                }
                            }
                        }
                    }
                }
                return aVar2;
            }
        } catch (k e6) {
            throw e6;
        } catch (IllegalBlockSizeException e7) {
            a = asVar;
            bArr = asVar;
            obj = asVar;
            asVar = a;
            if (bArr != null) {
                if (TextUtils.isEmpty(a2)) {
                    a2 = dl.a(bArr);
                }
                jSONObject = new JSONObject(a2);
                if (jSONObject.has("status")) {
                    i = jSONObject.getInt("status");
                    if (i == 1) {
                        a = 1;
                    } else if (i == 0) {
                        a2 = "authcsid";
                        str2 = "authgsid";
                        if (asVar != null) {
                            a2 = asVar.c;
                            str2 = asVar.d;
                        }
                        dl.a(context, a2, str2, jSONObject);
                        a = 0;
                        if (jSONObject.has("info")) {
                            b = jSONObject.getString("info");
                        }
                        a2 = "";
                        if (jSONObject.has("infocode")) {
                            a2 = jSONObject.getString("infocode");
                        }
                        j.a(dkVar, "/v3/iasdkauth", b, str2, a2);
                        if (a == 0) {
                            aVar2.a = b;
                        }
                    }
                    if (jSONObject.has("ver")) {
                        aVar2.b = jSONObject.getInt("ver");
                    }
                    if (dl.a(jSONObject, "result")) {
                        aVar = new a();
                        aVar.a = false;
                        aVar.b = false;
                        aVar2.x = aVar;
                        jSONObject2 = jSONObject.getJSONObject("result");
                        split = str.split(";");
                        for (String a32222222 : split) {
                            if (jSONObject2.has(a32222222)) {
                                aVar2.w.putOpt(a32222222, jSONObject2.get(a32222222));
                            }
                        }
                        if (dl.a(jSONObject2, "11K")) {
                            jSONObject3 = jSONObject2.getJSONObject("11K");
                            aVar.a = a(jSONObject3.getString("able"), false);
                            if (jSONObject3.has("off")) {
                                aVar.c = jSONObject3.getJSONObject("off");
                            }
                        }
                        if (dl.a(jSONObject2, "001")) {
                            jSONObject3 = jSONObject2.getJSONObject("001");
                            dVar = new d();
                            if (jSONObject3 != null) {
                                a4 = a(jSONObject3, "md5");
                                a5 = a(jSONObject3, "url");
                                obj = a(jSONObject3, "sdkversion");
                                dVar.a = a5;
                                dVar.b = a4;
                                dVar.c = obj;
                            }
                            aVar2.y = dVar;
                        }
                        if (dl.a(jSONObject2, "002")) {
                            jSONObject3 = jSONObject2.getJSONObject("002");
                            cVar = new c();
                            a(jSONObject3, cVar);
                            aVar2.A = cVar;
                        }
                        if (dl.a(jSONObject2, "14S")) {
                            jSONObject3 = jSONObject2.getJSONObject("14S");
                            cVar = new c();
                            a(jSONObject3, cVar);
                            aVar2.B = cVar;
                        }
                        a(aVar2, jSONObject2);
                        if (dl.a(jSONObject2, "14Z")) {
                            jSONObject3 = jSONObject2.getJSONObject("14Z");
                            eVar = new e();
                            a6 = a(jSONObject3, "md5");
                            a32222222 = a(jSONObject3, "md5info");
                            a7 = a(jSONObject3, "url");
                            a8 = a(jSONObject3, "able");
                            a9 = a(jSONObject3, "on");
                            a2 = a(jSONObject3, "mobileable");
                            eVar.e = a6;
                            eVar.f = a32222222;
                            eVar.d = a7;
                            eVar.a = a(a8, false);
                            eVar.b = a(a9, false);
                            eVar.c = a(a2, false);
                            aVar2.G = eVar;
                        }
                        if (dl.a(jSONObject2, "151")) {
                            jSONObject3 = jSONObject2.getJSONObject("151");
                            fVar = new f();
                            if (jSONObject3 != null) {
                                fVar.a = a(jSONObject3.optString("able"), false);
                            }
                            aVar2.z = fVar;
                        }
                        a(aVar2, jSONObject2);
                        if (dl.a(jSONObject2, "14N")) {
                            jSONObject3 = jSONObject2.getJSONObject("14N");
                            bVar = new b();
                            bVar.a = a(jSONObject3.optString("able"), false);
                            bVar.b = jSONObject3.optString("url");
                            bVar.c = jSONObject3.optString("md5");
                            if (bVar.a) {
                                x.a(context, "aiu");
                            } else {
                                a10 = e.a();
                                if (a10 != null) {
                                    wVar = new w(bVar.b, bVar.c);
                                    wVar.a(z);
                                    new v(context, wVar, a10).a();
                                }
                            }
                        }
                    }
                }
            }
            return aVar2;
        } catch (k e8) {
            e6 = e8;
            a = asVar;
            bArr = asVar;
        } catch (Throwable th5) {
            th22222 = th5;
            a = asVar;
            bArr = asVar;
            j.b(th22222, IXAdRequestInfo.AD_TYPE, "lc");
            obj = asVar;
            asVar = a;
            if (bArr != null) {
                if (TextUtils.isEmpty(a2)) {
                    a2 = dl.a(bArr);
                }
                jSONObject = new JSONObject(a2);
                if (jSONObject.has("status")) {
                    i = jSONObject.getInt("status");
                    if (i == 1) {
                        a = 1;
                    } else if (i == 0) {
                        a2 = "authcsid";
                        str2 = "authgsid";
                        if (asVar != null) {
                            a2 = asVar.c;
                            str2 = asVar.d;
                        }
                        dl.a(context, a2, str2, jSONObject);
                        a = 0;
                        if (jSONObject.has("info")) {
                            b = jSONObject.getString("info");
                        }
                        a2 = "";
                        if (jSONObject.has("infocode")) {
                            a2 = jSONObject.getString("infocode");
                        }
                        j.a(dkVar, "/v3/iasdkauth", b, str2, a2);
                        if (a == 0) {
                            aVar2.a = b;
                        }
                    }
                    if (jSONObject.has("ver")) {
                        aVar2.b = jSONObject.getInt("ver");
                    }
                    if (dl.a(jSONObject, "result")) {
                        aVar = new a();
                        aVar.a = false;
                        aVar.b = false;
                        aVar2.x = aVar;
                        jSONObject2 = jSONObject.getJSONObject("result");
                        split = str.split(";");
                        for (String a322222222 : split) {
                            if (jSONObject2.has(a322222222)) {
                                aVar2.w.putOpt(a322222222, jSONObject2.get(a322222222));
                            }
                        }
                        if (dl.a(jSONObject2, "11K")) {
                            jSONObject3 = jSONObject2.getJSONObject("11K");
                            aVar.a = a(jSONObject3.getString("able"), false);
                            if (jSONObject3.has("off")) {
                                aVar.c = jSONObject3.getJSONObject("off");
                            }
                        }
                        if (dl.a(jSONObject2, "001")) {
                            jSONObject3 = jSONObject2.getJSONObject("001");
                            dVar = new d();
                            if (jSONObject3 != null) {
                                a4 = a(jSONObject3, "md5");
                                a5 = a(jSONObject3, "url");
                                obj = a(jSONObject3, "sdkversion");
                                dVar.a = a5;
                                dVar.b = a4;
                                dVar.c = obj;
                            }
                            aVar2.y = dVar;
                        }
                        if (dl.a(jSONObject2, "002")) {
                            jSONObject3 = jSONObject2.getJSONObject("002");
                            cVar = new c();
                            a(jSONObject3, cVar);
                            aVar2.A = cVar;
                        }
                        if (dl.a(jSONObject2, "14S")) {
                            jSONObject3 = jSONObject2.getJSONObject("14S");
                            cVar = new c();
                            a(jSONObject3, cVar);
                            aVar2.B = cVar;
                        }
                        a(aVar2, jSONObject2);
                        if (dl.a(jSONObject2, "14Z")) {
                            jSONObject3 = jSONObject2.getJSONObject("14Z");
                            eVar = new e();
                            a6 = a(jSONObject3, "md5");
                            a322222222 = a(jSONObject3, "md5info");
                            a7 = a(jSONObject3, "url");
                            a8 = a(jSONObject3, "able");
                            a9 = a(jSONObject3, "on");
                            a2 = a(jSONObject3, "mobileable");
                            eVar.e = a6;
                            eVar.f = a322222222;
                            eVar.d = a7;
                            eVar.a = a(a8, false);
                            eVar.b = a(a9, false);
                            eVar.c = a(a2, false);
                            aVar2.G = eVar;
                        }
                        if (dl.a(jSONObject2, "151")) {
                            jSONObject3 = jSONObject2.getJSONObject("151");
                            fVar = new f();
                            if (jSONObject3 != null) {
                                fVar.a = a(jSONObject3.optString("able"), false);
                            }
                            aVar2.z = fVar;
                        }
                        a(aVar2, jSONObject2);
                        if (dl.a(jSONObject2, "14N")) {
                            jSONObject3 = jSONObject2.getJSONObject("14N");
                            bVar = new b();
                            bVar.a = a(jSONObject3.optString("able"), false);
                            bVar.b = jSONObject3.optString("url");
                            bVar.c = jSONObject3.optString("md5");
                            if (bVar.a) {
                                a10 = e.a();
                                if (a10 != null) {
                                    wVar = new w(bVar.b, bVar.c);
                                    wVar.a(z);
                                    new v(context, wVar, a10).a();
                                }
                            } else {
                                x.a(context, "aiu");
                            }
                        }
                    }
                }
            }
            return aVar2;
        }
        if (bArr != null) {
            if (TextUtils.isEmpty(a2)) {
                a2 = dl.a(bArr);
            }
            jSONObject = new JSONObject(a2);
            if (jSONObject.has("status")) {
                i = jSONObject.getInt("status");
                if (i == 1) {
                    a = 1;
                } else if (i == 0) {
                    a2 = "authcsid";
                    str2 = "authgsid";
                    if (asVar != null) {
                        a2 = asVar.c;
                        str2 = asVar.d;
                    }
                    dl.a(context, a2, str2, jSONObject);
                    a = 0;
                    if (jSONObject.has("info")) {
                        b = jSONObject.getString("info");
                    }
                    a2 = "";
                    if (jSONObject.has("infocode")) {
                        a2 = jSONObject.getString("infocode");
                    }
                    j.a(dkVar, "/v3/iasdkauth", b, str2, a2);
                    if (a == 0) {
                        aVar2.a = b;
                    }
                }
                if (jSONObject.has("ver")) {
                    aVar2.b = jSONObject.getInt("ver");
                }
                if (dl.a(jSONObject, "result")) {
                    aVar = new a();
                    aVar.a = false;
                    aVar.b = false;
                    aVar2.x = aVar;
                    jSONObject2 = jSONObject.getJSONObject("result");
                    split = str.split(";");
                    if (split != null && split.length > 0) {
                        for (String a3222222222 : split) {
                            if (jSONObject2.has(a3222222222)) {
                                aVar2.w.putOpt(a3222222222, jSONObject2.get(a3222222222));
                            }
                        }
                    }
                    if (dl.a(jSONObject2, "11K")) {
                        jSONObject3 = jSONObject2.getJSONObject("11K");
                        aVar.a = a(jSONObject3.getString("able"), false);
                        if (jSONObject3.has("off")) {
                            aVar.c = jSONObject3.getJSONObject("off");
                        }
                    }
                    if (dl.a(jSONObject2, "001")) {
                        jSONObject3 = jSONObject2.getJSONObject("001");
                        dVar = new d();
                        if (jSONObject3 != null) {
                            a4 = a(jSONObject3, "md5");
                            a5 = a(jSONObject3, "url");
                            obj = a(jSONObject3, "sdkversion");
                            if (!(TextUtils.isEmpty(a4) || TextUtils.isEmpty(a5) || TextUtils.isEmpty(obj))) {
                                dVar.a = a5;
                                dVar.b = a4;
                                dVar.c = obj;
                            }
                        }
                        aVar2.y = dVar;
                    }
                    if (dl.a(jSONObject2, "002")) {
                        jSONObject3 = jSONObject2.getJSONObject("002");
                        cVar = new c();
                        a(jSONObject3, cVar);
                        aVar2.A = cVar;
                    }
                    if (dl.a(jSONObject2, "14S")) {
                        jSONObject3 = jSONObject2.getJSONObject("14S");
                        cVar = new c();
                        a(jSONObject3, cVar);
                        aVar2.B = cVar;
                    }
                    a(aVar2, jSONObject2);
                    if (dl.a(jSONObject2, "14Z")) {
                        jSONObject3 = jSONObject2.getJSONObject("14Z");
                        eVar = new e();
                        a6 = a(jSONObject3, "md5");
                        a3222222222 = a(jSONObject3, "md5info");
                        a7 = a(jSONObject3, "url");
                        a8 = a(jSONObject3, "able");
                        a9 = a(jSONObject3, "on");
                        a2 = a(jSONObject3, "mobileable");
                        eVar.e = a6;
                        eVar.f = a3222222222;
                        eVar.d = a7;
                        eVar.a = a(a8, false);
                        eVar.b = a(a9, false);
                        eVar.c = a(a2, false);
                        aVar2.G = eVar;
                    }
                    if (dl.a(jSONObject2, "151")) {
                        jSONObject3 = jSONObject2.getJSONObject("151");
                        fVar = new f();
                        if (jSONObject3 != null) {
                            fVar.a = a(jSONObject3.optString("able"), false);
                        }
                        aVar2.z = fVar;
                    }
                    a(aVar2, jSONObject2);
                    if (dl.a(jSONObject2, "14N")) {
                        jSONObject3 = jSONObject2.getJSONObject("14N");
                        bVar = new b();
                        bVar.a = a(jSONObject3.optString("able"), false);
                        bVar.b = jSONObject3.optString("url");
                        bVar.c = jSONObject3.optString("md5");
                        if (bVar.a) {
                            a10 = e.a();
                            if (a10 != null) {
                                wVar = new w(bVar.b, bVar.c);
                                wVar.a(z);
                                new v(context, wVar, a10).a();
                            }
                        } else {
                            x.a(context, "aiu");
                        }
                    }
                }
            }
        }
        return aVar2;
    }

    private static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            return "";
        }
        String str2 = "";
        return (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) ? str2 : jSONObject.optString(str);
    }

    public static void a(Context context, String str) {
        db.a(context, str);
    }

    private static void a(a aVar, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2;
            b bVar;
            if (dl.a(jSONObject, "11B")) {
                aVar.h = jSONObject.getJSONObject("11B");
            }
            if (dl.a(jSONObject, "11C")) {
                aVar.k = jSONObject.getJSONObject("11C");
            }
            if (dl.a(jSONObject, "11I")) {
                aVar.l = jSONObject.getJSONObject("11I");
            }
            if (dl.a(jSONObject, "11H")) {
                aVar.m = jSONObject.getJSONObject("11H");
            }
            if (dl.a(jSONObject, "11E")) {
                aVar.n = jSONObject.getJSONObject("11E");
            }
            if (dl.a(jSONObject, "11F")) {
                aVar.o = jSONObject.getJSONObject("11F");
            }
            if (dl.a(jSONObject, "13A")) {
                aVar.q = jSONObject.getJSONObject("13A");
            }
            if (dl.a(jSONObject, "13J")) {
                aVar.i = jSONObject.getJSONObject("13J");
            }
            if (dl.a(jSONObject, "11G")) {
                aVar.p = jSONObject.getJSONObject("11G");
            }
            if (dl.a(jSONObject, "006")) {
                aVar.r = jSONObject.getJSONObject("006");
            }
            if (dl.a(jSONObject, "010")) {
                aVar.s = jSONObject.getJSONObject("010");
            }
            if (dl.a(jSONObject, "11Z")) {
                jSONObject2 = jSONObject.getJSONObject("11Z");
                bVar = new b();
                a(jSONObject2, bVar);
                aVar.C = bVar;
            }
            if (dl.a(jSONObject, "135")) {
                aVar.j = jSONObject.getJSONObject("135");
            }
            if (dl.a(jSONObject, "13S")) {
                aVar.g = jSONObject.getJSONObject("13S");
            }
            if (dl.a(jSONObject, "121")) {
                jSONObject2 = jSONObject.getJSONObject("121");
                bVar = new b();
                a(jSONObject2, bVar);
                aVar.D = bVar;
            }
            if (dl.a(jSONObject, "122")) {
                jSONObject2 = jSONObject.getJSONObject("122");
                bVar = new b();
                a(jSONObject2, bVar);
                aVar.E = bVar;
            }
            if (dl.a(jSONObject, "123")) {
                jSONObject2 = jSONObject.getJSONObject("123");
                bVar = new b();
                a(jSONObject2, bVar);
                aVar.F = bVar;
            }
            if (dl.a(jSONObject, "011")) {
                aVar.c = jSONObject.getJSONObject("011");
            }
            if (dl.a(jSONObject, "012")) {
                aVar.d = jSONObject.getJSONObject("012");
            }
            if (dl.a(jSONObject, "013")) {
                aVar.e = jSONObject.getJSONObject("013");
            }
            if (dl.a(jSONObject, "014")) {
                aVar.f = jSONObject.getJSONObject("014");
            }
            if (dl.a(jSONObject, "145")) {
                aVar.t = jSONObject.getJSONObject("145");
            }
            if (dl.a(jSONObject, "14B")) {
                aVar.u = jSONObject.getJSONObject("14B");
            }
            if (dl.a(jSONObject, "14D")) {
                aVar.v = jSONObject.getJSONObject("14D");
            }
        } catch (Throwable th) {
            j.b(th, IXAdRequestInfo.AD_TYPE, "pe");
        }
    }

    private static void a(JSONObject jSONObject, b bVar) {
        if (bVar != null) {
            try {
                String a = a(jSONObject, "m");
                String a2 = a(jSONObject, "u");
                String a3 = a(jSONObject, IXAdRequestInfo.V);
                String a4 = a(jSONObject, "able");
                String a5 = a(jSONObject, "on");
                bVar.c = a;
                bVar.b = a2;
                bVar.d = a3;
                bVar.a = a(a4, false);
                bVar.e = a(a5, true);
            } catch (Throwable th) {
                g.a(th, IXAdRequestInfo.AD_TYPE, "pe");
            }
        }
    }

    private static void a(JSONObject jSONObject, c cVar) {
        if (jSONObject != null) {
            try {
                String a = a(jSONObject, "md5");
                String a2 = a(jSONObject, "url");
                cVar.b = a;
                cVar.a = a2;
            } catch (Throwable th) {
                g.a(th, IXAdRequestInfo.AD_TYPE, "psc");
            }
        }
    }

    public static boolean a(String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return z;
            }
            String[] split = URLDecoder.decode(str).split(HttpUtils.PATHS_SEPARATOR);
            return split[split.length + -1].charAt(4) % 2 == 1;
        } catch (Throwable th) {
            return z;
        }
    }
}
