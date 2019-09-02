package com.amap.api.mapcore.util;

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
public class fy {
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
        public f G;
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
        public e z;

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
        }

        /* compiled from: AuthConfigManager */
        public static class f {
            public boolean a;
            public boolean b;
            public boolean c;
            public String d;
            public String e;
            public String f;
        }
    }

    /* compiled from: AuthConfigManager */
    static class b extends iu {
        private String c;
        private Map<String, String> d;
        private boolean e;

        b(Context context, gk gkVar, String str, Map<String, String> map) {
            super(context, gkVar);
            this.c = str;
            this.d = map;
            this.e = VERSION.SDK_INT != 19;
        }

        public boolean d() {
            return this.e;
        }

        public Map<String, String> a() {
            return null;
        }

        public String c() {
            return this.e ? "https://restapi.amap.com/v3/iasdkauth" : "http://restapi.amap.com/v3/iasdkauth";
        }

        public byte[] e() {
            return null;
        }

        public byte[] f() {
            return gl.a(gl.b(o()));
        }

        protected String g() {
            return "3.0";
        }

        private Map<String, String> o() {
            Object u = gd.u(this.a);
            if (TextUtils.isEmpty(u)) {
                u = gd.c();
            }
            if (!TextUtils.isEmpty(u)) {
                u = gf.b(new StringBuilder(u).reverse().toString());
            }
            Map<String, String> hashMap = new HashMap();
            hashMap.put("authkey", this.c);
            hashMap.put("plattype", "android");
            hashMap.put("product", this.b.a());
            hashMap.put("version", this.b.b());
            hashMap.put("output", "json");
            hashMap.put("androidversion", VERSION.SDK_INT + "");
            hashMap.put("deviceId", u);
            hashMap.put("manufacture", Build.MANUFACTURER);
            if (!(this.d == null || this.d.isEmpty())) {
                hashMap.putAll(this.d);
            }
            hashMap.put("abitype", gl.a(this.a));
            hashMap.put("ext", this.b.e());
            return hashMap;
        }
    }

    public static void a(Context context, String str) {
        fx.a(context, str);
    }

    public static boolean a(String str, boolean z) {
        boolean z2 = true;
        try {
            if (TextUtils.isEmpty(str)) {
                return z;
            }
            String[] split = URLDecoder.decode(str).split(HttpUtils.PATHS_SEPARATOR);
            if (split[split.length - 1].charAt(4) % 2 != 1) {
                z2 = false;
            }
            return z2;
        } catch (Throwable th) {
            return z;
        }
    }

    public static a a(Context context, gk gkVar, String str, Map<String, String> map) {
        return a(context, gkVar, str, map, false);
    }

    public static a a(Context context, gk gkVar, String str, Map<String, String> map, boolean z) {
        gp e;
        Object obj;
        Object obj2 = null;
        jb jbVar = null;
        a aVar = new a();
        aVar.w = new JSONObject();
        try {
            it itVar = new it();
            iy bVar = new b(context, gkVar, str + ";14N", map);
            jbVar = itVar.a(bVar, bVar.d());
            if (jbVar != null) {
                obj2 = jbVar.a;
            }
            Object obj3 = new byte[16];
            Object obj4 = new byte[(obj2.length - 16)];
            System.arraycopy(obj2, 0, obj3, 0, 16);
            System.arraycopy(obj2, 16, obj4, 0, obj2.length - 16);
            Key secretKeySpec = new SecretKeySpec(obj3, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(gl.b()));
            String a = gl.a(instance.doFinal(obj4));
            jb jbVar2 = jbVar;
            byte[] bArr = obj2;
            jb jbVar3 = jbVar2;
        } catch (gp e2) {
            throw e2;
        } catch (IllegalBlockSizeException e3) {
            a = null;
            jbVar3 = jbVar;
            obj = null;
        } catch (gp e22) {
            aVar.a = e22.a();
            gz.a(gkVar, "/v3/iasdkauth", e22);
            a = null;
            jbVar3 = jbVar;
            obj = null;
        } catch (Throwable th) {
            gz.c(th, IXAdRequestInfo.AD_TYPE, "lc");
            a = null;
            jbVar3 = jbVar;
            obj = null;
        }
        if (bArr == null) {
            return aVar;
        }
        if (TextUtils.isEmpty(a)) {
            a = gl.a(bArr);
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            if (jSONObject.has("status")) {
                int i = jSONObject.getInt("status");
                if (i == 1) {
                    a = 1;
                } else if (i == 0) {
                    String str2 = "authcsid";
                    a = "authgsid";
                    if (jbVar3 != null) {
                        a = jbVar3.c;
                        str2 = jbVar3.d;
                    } else {
                        String str3 = a;
                        a = str2;
                        str2 = str3;
                    }
                    gl.a(context, a, str2, jSONObject);
                    a = 0;
                    if (jSONObject.has("info")) {
                        b = jSONObject.getString("info");
                    }
                    a = "";
                    if (jSONObject.has("infocode")) {
                        a = jSONObject.getString("infocode");
                    }
                    gz.a(gkVar, "/v3/iasdkauth", b, str2, a);
                    if (a == 0) {
                        aVar.a = b;
                        return aVar;
                    }
                }
                if (jSONObject.has("ver")) {
                    aVar.b = jSONObject.getInt("ver");
                }
                if (gl.a(jSONObject, "result")) {
                    JSONObject jSONObject2;
                    c cVar;
                    a aVar2 = new a();
                    aVar2.a = false;
                    aVar2.b = false;
                    aVar.x = aVar2;
                    JSONObject jSONObject3 = jSONObject.getJSONObject("result");
                    try {
                        String[] split = str.split(";");
                        if (split != null && split.length > 0) {
                            for (String str4 : split) {
                                if (jSONObject3.has(str4)) {
                                    aVar.w.putOpt(str4, jSONObject3.get(str4));
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        gw.a(th2, IXAdRequestInfo.AD_TYPE, "co");
                    }
                    if (gl.a(jSONObject3, "11K")) {
                        try {
                            jSONObject2 = jSONObject3.getJSONObject("11K");
                            aVar2.a = a(jSONObject2.getString("able"), false);
                            if (jSONObject2.has("off")) {
                                aVar2.c = jSONObject2.getJSONObject("off");
                            }
                        } catch (Throwable th22) {
                            gw.a(th22, "AuthConfigManager", "loadException");
                        }
                    }
                    if (gl.a(jSONObject3, "001")) {
                        jSONObject2 = jSONObject3.getJSONObject("001");
                        d dVar = new d();
                        a(jSONObject2, dVar);
                        aVar.y = dVar;
                    }
                    if (gl.a(jSONObject3, "002")) {
                        jSONObject2 = jSONObject3.getJSONObject("002");
                        cVar = new c();
                        a(jSONObject2, cVar);
                        aVar.A = cVar;
                    }
                    if (gl.a(jSONObject3, "14S")) {
                        jSONObject2 = jSONObject3.getJSONObject("14S");
                        cVar = new c();
                        a(jSONObject2, cVar);
                        aVar.B = cVar;
                    }
                    a(aVar, jSONObject3);
                    if (gl.a(jSONObject3, "14Z")) {
                        jSONObject2 = jSONObject3.getJSONObject("14Z");
                        f fVar = new f();
                        a(jSONObject2, fVar);
                        aVar.G = fVar;
                    }
                    if (gl.a(jSONObject3, "151")) {
                        jSONObject2 = jSONObject3.getJSONObject("151");
                        e eVar = new e();
                        a(jSONObject2, eVar);
                        aVar.z = eVar;
                    }
                    a(aVar, jSONObject3);
                    if (gl.a(jSONObject3, "14N")) {
                        jSONObject2 = jSONObject3.getJSONObject("14N");
                        b bVar2 = new b();
                        bVar2.a = a(jSONObject2.optString("able"), false);
                        bVar2.b = jSONObject2.optString("url");
                        bVar2.c = jSONObject2.optString("md5");
                        if (bVar2.a) {
                            gk a2 = gu.a();
                            if (a2 != null) {
                                hm hmVar = new hm(bVar2.b, bVar2.c, "");
                                hmVar.a(z);
                                new hk(context, hmVar, a2).a();
                            }
                        } else {
                            hn.a(context, "aiu");
                        }
                    }
                }
            }
        } catch (Throwable th222) {
            gw.a(th222, IXAdRequestInfo.AD_TYPE, "lc");
        }
        return aVar;
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            return "";
        }
        String str2 = "";
        if (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) {
            return str2;
        }
        return jSONObject.optString(str);
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
                gw.a(th, IXAdRequestInfo.AD_TYPE, "pe");
            }
        }
    }

    private static void a(JSONObject jSONObject, f fVar) {
        if (fVar != null) {
            try {
                String a = a(jSONObject, "md5");
                String a2 = a(jSONObject, "md5info");
                String a3 = a(jSONObject, "url");
                String a4 = a(jSONObject, "able");
                String a5 = a(jSONObject, "on");
                String a6 = a(jSONObject, "mobileable");
                fVar.e = a;
                fVar.f = a2;
                fVar.d = a3;
                fVar.a = a(a4, false);
                fVar.b = a(a5, false);
                fVar.c = a(a6, false);
            } catch (Throwable th) {
                gw.a(th, IXAdRequestInfo.AD_TYPE, "pes");
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
                gw.a(th, IXAdRequestInfo.AD_TYPE, "psc");
            }
        }
    }

    private static void a(JSONObject jSONObject, d dVar) {
        if (jSONObject != null) {
            try {
                Object a = a(jSONObject, "md5");
                Object a2 = a(jSONObject, "url");
                Object a3 = a(jSONObject, "sdkversion");
                if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3)) {
                    dVar.a = a2;
                    dVar.b = a;
                    dVar.c = a3;
                }
            } catch (Throwable th) {
                gw.a(th, IXAdRequestInfo.AD_TYPE, "psu");
            }
        }
    }

    private static void a(JSONObject jSONObject, e eVar) {
        if (eVar != null && jSONObject != null) {
            eVar.a = a(jSONObject.optString("able"), false);
        }
    }

    private static void a(a aVar, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2;
            b bVar;
            if (gl.a(jSONObject, "11B")) {
                aVar.h = jSONObject.getJSONObject("11B");
            }
            if (gl.a(jSONObject, "11C")) {
                aVar.k = jSONObject.getJSONObject("11C");
            }
            if (gl.a(jSONObject, "11I")) {
                aVar.l = jSONObject.getJSONObject("11I");
            }
            if (gl.a(jSONObject, "11H")) {
                aVar.m = jSONObject.getJSONObject("11H");
            }
            if (gl.a(jSONObject, "11E")) {
                aVar.n = jSONObject.getJSONObject("11E");
            }
            if (gl.a(jSONObject, "11F")) {
                aVar.o = jSONObject.getJSONObject("11F");
            }
            if (gl.a(jSONObject, "13A")) {
                aVar.q = jSONObject.getJSONObject("13A");
            }
            if (gl.a(jSONObject, "13J")) {
                aVar.i = jSONObject.getJSONObject("13J");
            }
            if (gl.a(jSONObject, "11G")) {
                aVar.p = jSONObject.getJSONObject("11G");
            }
            if (gl.a(jSONObject, "006")) {
                aVar.r = jSONObject.getJSONObject("006");
            }
            if (gl.a(jSONObject, "010")) {
                aVar.s = jSONObject.getJSONObject("010");
            }
            if (gl.a(jSONObject, "11Z")) {
                jSONObject2 = jSONObject.getJSONObject("11Z");
                bVar = new b();
                a(jSONObject2, bVar);
                aVar.C = bVar;
            }
            if (gl.a(jSONObject, "135")) {
                aVar.j = jSONObject.getJSONObject("135");
            }
            if (gl.a(jSONObject, "13S")) {
                aVar.g = jSONObject.getJSONObject("13S");
            }
            if (gl.a(jSONObject, "121")) {
                jSONObject2 = jSONObject.getJSONObject("121");
                bVar = new b();
                a(jSONObject2, bVar);
                aVar.D = bVar;
            }
            if (gl.a(jSONObject, "122")) {
                jSONObject2 = jSONObject.getJSONObject("122");
                bVar = new b();
                a(jSONObject2, bVar);
                aVar.E = bVar;
            }
            if (gl.a(jSONObject, "123")) {
                jSONObject2 = jSONObject.getJSONObject("123");
                bVar = new b();
                a(jSONObject2, bVar);
                aVar.F = bVar;
            }
            if (gl.a(jSONObject, "011")) {
                aVar.c = jSONObject.getJSONObject("011");
            }
            if (gl.a(jSONObject, "012")) {
                aVar.d = jSONObject.getJSONObject("012");
            }
            if (gl.a(jSONObject, "013")) {
                aVar.e = jSONObject.getJSONObject("013");
            }
            if (gl.a(jSONObject, "014")) {
                aVar.f = jSONObject.getJSONObject("014");
            }
            if (gl.a(jSONObject, "145")) {
                aVar.t = jSONObject.getJSONObject("145");
            }
            if (gl.a(jSONObject, "14B")) {
                aVar.u = jSONObject.getJSONObject("14B");
            }
            if (gl.a(jSONObject, "14D")) {
                aVar.v = jSONObject.getJSONObject("14D");
            }
        } catch (Throwable th) {
            gz.c(th, IXAdRequestInfo.AD_TYPE, "pe");
        }
    }
}
