package com.loc;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.text.TextUtils;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.blankj.utilcode.constant.CacheConstants;
import com.blankj.utilcode.constant.TimeConstants;
import com.loc.dc.a.b;
import com.loc.dc.a.c;
import com.loc.dc.a.d;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.bugly.beta.tinker.TinkerReport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.altbeacon.beacon.BeaconManager;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AuthUtil */
public final class ck {
    private static int A = 0;
    private static int B = 0;
    private static boolean C = true;
    private static int D = 1000;
    private static int E = 200;
    private static boolean F = false;
    private static int G = 20;
    private static boolean H = true;
    private static boolean I = true;
    private static int J = -1;
    private static long K = 0;
    private static ArrayList<String> L = new ArrayList();
    private static boolean M = false;
    private static int N = -1;
    private static long O = 0;
    private static ArrayList<String> P = new ArrayList();
    private static String Q;
    private static String R;
    private static boolean S = false;
    private static boolean T = false;
    private static int U = PathInterpolatorCompat.MAX_NUM_POINTS;
    private static int V = PathInterpolatorCompat.MAX_NUM_POINTS;
    private static boolean W = true;
    private static long X = BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD;
    private static boolean Y = false;
    private static List<cn> Z = new ArrayList();
    public static boolean a = true;
    private static boolean aa = false;
    private static long ab = 0;
    private static int ac = 0;
    private static int ad = 0;
    private static List<String> ae = new ArrayList();
    private static boolean af = true;
    private static int ag = 80;
    private static boolean ah = false;
    private static boolean ai = true;
    private static boolean aj = false;
    private static boolean ak = true;
    private static boolean al = false;
    private static int am = -1;
    static boolean b = false;
    static boolean c = false;
    static int d = TimeConstants.HOUR;
    static long e = 0;
    static long f = 0;
    static boolean g = false;
    static boolean h = true;
    static boolean i = false;
    private static boolean j = false;
    private static String k = "提示信息";
    private static String l = "确认";
    private static String m = "取消";
    private static String n = "";
    private static String o = "";
    private static String p = "";
    private static boolean q = false;
    private static long r = 0;
    private static long s = 0;
    private static long t = 5000;
    private static boolean u = false;
    private static int v = 0;
    private static boolean w = false;
    private static int x = 0;
    private static boolean y = false;
    private static int z = TimeConstants.HOUR;

    /* compiled from: AuthUtil */
    static class a {
        boolean a = false;
        String b = "0";
        boolean c = false;
        int d = 5;

        a() {
        }
    }

    public static boolean A() {
        if (!g) {
            return g;
        }
        g = false;
        return true;
    }

    public static boolean B() {
        return h;
    }

    public static boolean C() {
        return aj;
    }

    public static boolean D() {
        return al;
    }

    public static boolean E() {
        return ak;
    }

    public static int F() {
        return am;
    }

    public static boolean G() {
        return i;
    }

    private static a a(JSONObject jSONObject, String str) {
        Throwable th;
        if (jSONObject != null) {
            a aVar;
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                if (jSONObject2 != null) {
                    aVar = new a();
                    try {
                        aVar.a = dc.a(jSONObject2.optString("b"), false);
                        aVar.b = jSONObject2.optString("t");
                        aVar.c = dc.a(jSONObject2.optString("st"), false);
                        aVar.d = jSONObject2.optInt("i", 0);
                        return aVar;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                aVar = null;
                th = th4;
                cl.a(th, "AuthUtil", "getLocateObj");
                return aVar;
            }
        }
        return null;
    }

    public static boolean a() {
        return u;
    }

    public static boolean a(long j) {
        long b = ct.b();
        return q && b - s <= r && b - j >= t;
    }

    public static boolean a(Context context) {
        boolean z = false;
        H = true;
        try {
            j = cs.b(context, "pref", "oda", false);
            com.loc.dc.a a = dc.a(context, cl.b(), cl.c(), j);
            if (a != null) {
                z = a(context, a);
            }
        } catch (Throwable th) {
            cl.a(th, "AuthUtil", "getConfig");
        }
        f = ct.b();
        e = ct.b();
        return z;
    }

    public static boolean a(Context context, long j) {
        if (!T) {
            return false;
        }
        long a = ct.a();
        if (a - j < ((long) U)) {
            return false;
        }
        if (V == -1) {
            return true;
        }
        if (ct.c(a, cs.b(context, "pref", "ngpsTime", 0))) {
            int b = cs.b(context, "pref", "ngpsCount", 0);
            if (b >= V) {
                return false;
            }
            cs.a(context, "pref", "ngpsCount", b + 1);
            return true;
        }
        try {
            Editor edit = context.getSharedPreferences("pref", 0).edit();
            edit.putLong("ngpsTime", a);
            edit.putInt("ngpsCount", 0);
            cs.a(edit);
        } catch (Throwable th) {
            cl.a(th, "AuthUtil", "resetPrefsNGPS");
        }
        cs.a(context, "pref", "ngpsCount", 1);
        return true;
    }

    private static boolean a(Context context, b bVar, String str, String str2) {
        if (bVar == null) {
            return false;
        }
        try {
            boolean z = bVar.a;
            Object obj = bVar.b;
            Object obj2 = bVar.c;
            CharSequence charSequence = bVar.d;
            boolean z2 = bVar.e;
            dk a = cl.a(str, str2);
            if (z) {
                if (!(!z2 || TextUtils.isEmpty(obj2) || TextUtils.isEmpty(obj) || TextUtils.isEmpty(charSequence))) {
                    w wVar = new w(obj, obj2);
                    wVar.a(j);
                    x.b(context, wVar, a);
                }
            } else if (cq.a(context, a)) {
                cr.a(context, str, "config|get dex able is false");
            }
            return z && z2;
        } catch (Throwable th) {
            cl.a(th, "AuthUtil", "downLoadPluginDex");
            return false;
        }
    }

    private static boolean a(Context context, com.loc.dc.a aVar) {
        JSONObject jSONObject;
        boolean a;
        JSONArray optJSONArray;
        int i;
        JSONObject jSONObject2;
        boolean a2;
        int optInt;
        try {
            jSONObject = aVar.g;
            if (jSONObject != null) {
                d = (jSONObject.optInt(IXAdRequestInfo.AD_TYPE, TinkerReport.KEY_APPLIED_DEXOPT_FORMAT) * 60) * 1000;
                try {
                    ah = dc.a(jSONObject.optString("ila"), ah);
                } catch (Throwable th) {
                    cl.a(th, "AuthUtil", "loadConfigData_indoor");
                }
                try {
                    a = dc.a(jSONObject.optString("icbd"), c);
                    c = a;
                    if (a) {
                        x.a(context, "loc");
                    }
                } catch (Throwable th2) {
                    cl.a(th2, "AuthUtil", "loadConfigData_CallBackDex");
                }
                if (!(context == null || jSONObject == null)) {
                    try {
                        h = dc.a(jSONObject.optString("re"), h);
                        cs.a(context, "pref", "fr", h);
                    } catch (Throwable th22) {
                        cl.a(th22, "AuthUtil", "checkReLocationAble");
                    }
                }
                try {
                    ai = dc.a(jSONObject.optString("nla"), ai);
                } catch (Throwable th3) {
                }
                try {
                    j = dc.a(jSONObject.optString("oda"), j);
                    cs.a(context, "pref", "oda", j);
                } catch (Throwable th4) {
                }
            }
            try {
                jSONObject = aVar.h;
                if (jSONObject != null) {
                    I = dc.a(jSONObject.optString("callamapflag"), I);
                    a = dc.a(jSONObject.optString("co"), false) && I;
                    b = a;
                    if (I) {
                        J = jSONObject.optInt(ParamKey.COUNT, J);
                        K = jSONObject.optLong("sysTime", K);
                        optJSONArray = jSONObject.optJSONArray(IXAdRequestInfo.SN);
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            for (i = 0; i < optJSONArray.length(); i++) {
                                L.add(optJSONArray.getString(i));
                            }
                        }
                        if (!(J == -1 || K == 0)) {
                            if (!ct.b(K, cs.b(context, "pref", "nowtime", 0))) {
                                i(context);
                            }
                        }
                    }
                }
            } catch (Throwable th5) {
                return false;
            }
        } catch (Throwable th222) {
            cl.a(th222, "AuthUtil", "loadConfigAbleStatus");
        }
        try {
            jSONObject2 = aVar.k;
            if (jSONObject2 != null) {
                a2 = dc.a(jSONObject2.optString("amappushflag"), M);
                M = a2;
                if (a2) {
                    N = jSONObject2.optInt(ParamKey.COUNT, N);
                    O = jSONObject2.optLong("sysTime", O);
                    optJSONArray = jSONObject2.optJSONArray(IXAdRequestInfo.SN);
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (i = 0; i < optJSONArray.length(); i++) {
                            P.add(optJSONArray.getString(i));
                        }
                    }
                    if (!(N == -1 || O == 0)) {
                        if (!ct.b(O, cs.b(context, "pref", "pushSerTime", 0))) {
                            j(context);
                        }
                    }
                }
            }
        } catch (Throwable th2222) {
            cl.a(th2222, "AuthUtil", "loadConfigDataCallAMapPush");
        }
        try {
            jSONObject2 = aVar.l;
            if (jSONObject2 != null) {
                S = dc.a(jSONObject2.optString("f"), S);
                A = jSONObject2.optInt("mco", 0);
                B = jSONObject2.optInt("co", 0);
                optInt = jSONObject2.optInt("it", CacheConstants.HOUR) * 1000;
                z = optInt;
                if (optInt < TimeConstants.HOUR) {
                    z = TimeConstants.HOUR;
                }
                k = jSONObject2.optString("a", "提示信息");
                l = jSONObject2.optString("o", "确认");
                m = jSONObject2.optString("c", "取消");
                n = jSONObject2.optString("i", "");
                o = jSONObject2.optString("u", "");
                p = jSONObject2.optString("t", "");
                a2 = (TextUtils.isEmpty(n) || "null".equals(n)) && (TextUtils.isEmpty(o) || "null".equals(o));
                a = B > A;
                if (a2 || a) {
                    S = false;
                }
            }
        } catch (Throwable th22222) {
            cl.a(th22222, "AuthUtil", "loadConfigDataOpenAMap");
        }
        try {
            dk b = cl.b();
            d dVar = aVar.y;
            Object obj;
            c cVar;
            de deVar;
            com.loc.dc.a.a aVar2;
            a a3;
            b bVar;
            JSONObject jSONObject3;
            JSONArray optJSONArray2;
            JSONObject optJSONObject;
            cn cnVar;
            JSONArray optJSONArray3;
            List arrayList;
            JSONObject optJSONObject2;
            Map hashMap;
            CharSequence optString;
            dk a4;
            Object optString2;
            w wVar;
            boolean a5;
            if (dVar != null) {
                Object obj2 = dVar.b;
                obj = dVar.a;
                CharSequence charSequence = dVar.c;
                if (TextUtils.isEmpty(obj2) || TextUtils.isEmpty(obj) || TextUtils.isEmpty(charSequence)) {
                    x.b(context, null, b);
                    if (a) {
                        try {
                            cVar = aVar.B;
                            if (cVar != null) {
                                Q = cVar.a;
                                R = cVar.b;
                                if (!(TextUtils.isEmpty(Q) || TextUtils.isEmpty(R))) {
                                    deVar = new de(context, "loc", Q, R);
                                    deVar.a(j);
                                    deVar.a();
                                }
                            }
                        } catch (Throwable th222222) {
                            cl.a(th222222, "AuthUtil", "loadConfigDataGroupOffset");
                        }
                    }
                    try {
                        aVar2 = aVar.x;
                        if (aVar2 != null) {
                            C = aVar2.a;
                            cs.a(context, "pref", "exception", C);
                            jSONObject2 = aVar2.c;
                            D = jSONObject2.optInt("fn", D);
                            optInt = jSONObject2.optInt("mpn", E);
                            E = optInt;
                            if (optInt > 500) {
                                E = 500;
                            }
                            if (E < 30) {
                                E = 30;
                            }
                            F = dc.a(jSONObject2.optString("igu"), F);
                            G = jSONObject2.optInt("ms", G);
                            ax.a(D, F, G);
                            cs.a(context, "pref", "fn", D);
                            cs.a(context, "pref", "mpn", E);
                            cs.a(context, "pref", "igu", F);
                            cs.a(context, "pref", "ms", G);
                        }
                    } catch (Throwable th2222222) {
                        cl.a(th2222222, "AuthUtil", "loadConfigDataUploadException");
                    }
                    try {
                        jSONObject = aVar.m;
                        if (jSONObject != null && dc.a(jSONObject.optString("able"), false)) {
                            a3 = a(jSONObject, "fs");
                            if (a3 != null) {
                                u = a3.c;
                                v = Integer.parseInt(a3.b);
                            }
                            a3 = a(jSONObject, "us");
                            if (a3 != null) {
                                w = a3.c;
                                y = a3.a;
                                try {
                                    x = Integer.parseInt(a3.b);
                                } catch (Throwable th22222222) {
                                    cl.a(th22222222, "AuthUtil", "loadconfig part1");
                                }
                                if (x < 2) {
                                    w = false;
                                }
                            }
                            a3 = a(jSONObject, "rs");
                            if (a3 != null) {
                                a2 = a3.c;
                                q = a2;
                                if (a2) {
                                    s = ct.b();
                                    t = (long) (a3.d * 1000);
                                }
                                try {
                                    r = (long) (Integer.parseInt(a3.b) * 1000);
                                } catch (Throwable th222222222) {
                                    cl.a(th222222222, "AuthUtil", "loadconfig part");
                                }
                            }
                        }
                    } catch (Throwable th2222222222) {
                        cl.a(th2222222222, "AuthUtil", "loadConfigDataLocate");
                    }
                    try {
                        jSONObject2 = aVar.o;
                        if (jSONObject2 != null) {
                            a2 = dc.a(jSONObject2.optString("able"), T);
                            T = a2;
                            if (a2) {
                                optInt = jSONObject2.optInt("c", 0);
                                if (optInt == 0) {
                                    U = PathInterpolatorCompat.MAX_NUM_POINTS;
                                } else {
                                    U = optInt * 1000;
                                }
                                V = jSONObject2.getInt("t") / 2;
                            }
                        }
                    } catch (Throwable th22222222222) {
                        cl.a(th22222222222, "AuthUtil", "loadConfigDataNgps");
                    }
                    try {
                        jSONObject2 = aVar.p;
                        if (jSONObject2 != null) {
                            a2 = dc.a(jSONObject2.optString("able"), W);
                            W = a2;
                            if (a2) {
                                X = (long) (jSONObject2.optInt("c", 300) * 1000);
                            }
                            cs.a(context, "pref", "ca", W);
                            cs.a(context, "pref", "ct", X);
                        }
                    } catch (Throwable th222222222222) {
                        cl.a(th222222222222, "AuthUtil", "loadConfigDataCacheAble");
                    }
                    try {
                        bVar = aVar.E;
                        if (bVar != null) {
                            Y = a(context, bVar, "HttpDNS", "1.0.0");
                        }
                    } catch (Throwable th2222222222222) {
                        cl.a(th2222222222222, "AuthUtil", "loadConfigDataDnsDex");
                    }
                    try {
                        jSONObject3 = aVar.j;
                        if (jSONObject3 != null) {
                            aa = dc.a(jSONObject3.optString("able"), aa);
                            ab = jSONObject3.optLong("sysTime", ct.a());
                            ac = jSONObject3.optInt(IXAdRequestInfo.AD_COUNT, 1);
                            ad = jSONObject3.optInt("nh", 1);
                            a = ac != -1 || ac >= ad;
                            if (aa && a) {
                                optJSONArray2 = jSONObject3.optJSONArray("l");
                                for (i = 0; i < optJSONArray2.length(); i++) {
                                    try {
                                        optJSONObject = optJSONArray2.optJSONObject(i);
                                        cnVar = new cn();
                                        a2 = dc.a(optJSONObject.optString("able", "false"), false);
                                        cnVar.a = a2;
                                        if (!a2) {
                                            cnVar.b = optJSONObject.optString("pn", "");
                                            cnVar.c = optJSONObject.optString("cn", "");
                                            cnVar.e = optJSONObject.optString("a", "");
                                            optJSONArray3 = optJSONObject.optJSONArray("b");
                                            if (optJSONArray3 != null) {
                                                arrayList = new ArrayList();
                                                for (optInt = 0; optInt < optJSONArray3.length(); optInt++) {
                                                    optJSONObject2 = optJSONArray3.optJSONObject(optInt);
                                                    hashMap = new HashMap(16);
                                                    try {
                                                        hashMap.put(optJSONObject2.optString("k"), optJSONObject2.optString(IXAdRequestInfo.V));
                                                        arrayList.add(hashMap);
                                                    } catch (Throwable th6) {
                                                    }
                                                }
                                                cnVar.d = arrayList;
                                            }
                                            cnVar.f = dc.a(optJSONObject.optString("is", "false"), false);
                                            Z.add(cnVar);
                                        }
                                    } catch (Throwable th7) {
                                    }
                                }
                                optJSONArray = jSONObject3.optJSONArray("sl");
                                if (optJSONArray != null) {
                                    for (i = 0; i < optJSONArray.length(); i++) {
                                        optString = optJSONArray.optJSONObject(i).optString("pan");
                                        if (TextUtils.isEmpty(optString)) {
                                            ae.add(optString);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Throwable th22222222222222) {
                        cl.a(th22222222222222, "AuthUtil", "loadConfigData_otherServiceList");
                    }
                    try {
                        jSONObject2 = aVar.i;
                        if (jSONObject2 != null) {
                            a2 = dc.a(jSONObject2.optString("able"), af);
                            af = a2;
                            if (a2) {
                                ag = jSONObject2.optInt("c", ag);
                            }
                        }
                    } catch (Throwable th222222222222222) {
                        cl.a(th222222222222222, "AuthUtil", "loadConfigDataGpsGeoAble");
                    }
                    jSONObject2 = aVar.w;
                    if (jSONObject2 != null) {
                        return true;
                    }
                    try {
                        jSONObject2 = jSONObject2.optJSONObject("157");
                        if (jSONObject2 != null) {
                            return true;
                        }
                        aj = dc.a(jSONObject2.optString("able"), aj);
                        a4 = cl.a("co", "1.0.0");
                        if (aj) {
                            am = jSONObject2.optInt("cv", -1);
                            ak = dc.a(jSONObject2.optString("co"), ak);
                            al = dc.a(jSONObject2.optString("oo"), al);
                            optString = jSONObject2.optString(IXAdRequestInfo.V);
                            obj = jSONObject2.optString("u");
                            optString2 = jSONObject2.optString("m");
                            if (!(TextUtils.isEmpty(optString2) || TextUtils.isEmpty(obj) || TextUtils.isEmpty(optString))) {
                                wVar = new w(obj, optString2);
                                wVar.a(j);
                                a5 = x.a(context, wVar, a4);
                                i = a5;
                                if (a5) {
                                    cs.a(context, "pref", "ok4", true);
                                    x.b(context, wVar, a4);
                                }
                            }
                        } else {
                            i = false;
                            ak = false;
                            al = false;
                            if (cq.a(context, a4)) {
                                cr.a(context, "co", "config|coDex able is false");
                            }
                        }
                        cs.a(context, "pref", "ok0", aj);
                        cs.a(context, "pref", "ok1", i);
                        cs.a(context, "pref", "ok2", ak);
                        cs.a(context, "pref", "ok3", al);
                        return true;
                    } catch (Throwable th2222222222222222) {
                        cl.a(th2222222222222222, "AuthUtil", "loadConfigDataNewCollectionOffline");
                        return true;
                    }
                }
                w wVar2 = new w(obj, obj2);
                wVar2.a(true);
                x.b(context, wVar2, b);
                if (a) {
                    cVar = aVar.B;
                    if (cVar != null) {
                        Q = cVar.a;
                        R = cVar.b;
                        deVar = new de(context, "loc", Q, R);
                        deVar.a(j);
                        deVar.a();
                    }
                }
                aVar2 = aVar.x;
                if (aVar2 != null) {
                    C = aVar2.a;
                    cs.a(context, "pref", "exception", C);
                    jSONObject2 = aVar2.c;
                    D = jSONObject2.optInt("fn", D);
                    optInt = jSONObject2.optInt("mpn", E);
                    E = optInt;
                    if (optInt > 500) {
                        E = 500;
                    }
                    if (E < 30) {
                        E = 30;
                    }
                    F = dc.a(jSONObject2.optString("igu"), F);
                    G = jSONObject2.optInt("ms", G);
                    ax.a(D, F, G);
                    cs.a(context, "pref", "fn", D);
                    cs.a(context, "pref", "mpn", E);
                    cs.a(context, "pref", "igu", F);
                    cs.a(context, "pref", "ms", G);
                }
                jSONObject = aVar.m;
                a3 = a(jSONObject, "fs");
                if (a3 != null) {
                    u = a3.c;
                    v = Integer.parseInt(a3.b);
                }
                a3 = a(jSONObject, "us");
                if (a3 != null) {
                    w = a3.c;
                    y = a3.a;
                    x = Integer.parseInt(a3.b);
                    if (x < 2) {
                        w = false;
                    }
                }
                a3 = a(jSONObject, "rs");
                if (a3 != null) {
                    a2 = a3.c;
                    q = a2;
                    if (a2) {
                        s = ct.b();
                        t = (long) (a3.d * 1000);
                    }
                    r = (long) (Integer.parseInt(a3.b) * 1000);
                }
                jSONObject2 = aVar.o;
                if (jSONObject2 != null) {
                    a2 = dc.a(jSONObject2.optString("able"), T);
                    T = a2;
                    if (a2) {
                        optInt = jSONObject2.optInt("c", 0);
                        if (optInt == 0) {
                            U = optInt * 1000;
                        } else {
                            U = PathInterpolatorCompat.MAX_NUM_POINTS;
                        }
                        V = jSONObject2.getInt("t") / 2;
                    }
                }
                jSONObject2 = aVar.p;
                if (jSONObject2 != null) {
                    a2 = dc.a(jSONObject2.optString("able"), W);
                    W = a2;
                    if (a2) {
                        X = (long) (jSONObject2.optInt("c", 300) * 1000);
                    }
                    cs.a(context, "pref", "ca", W);
                    cs.a(context, "pref", "ct", X);
                }
                bVar = aVar.E;
                if (bVar != null) {
                    Y = a(context, bVar, "HttpDNS", "1.0.0");
                }
                jSONObject3 = aVar.j;
                if (jSONObject3 != null) {
                    aa = dc.a(jSONObject3.optString("able"), aa);
                    ab = jSONObject3.optLong("sysTime", ct.a());
                    ac = jSONObject3.optInt(IXAdRequestInfo.AD_COUNT, 1);
                    ad = jSONObject3.optInt("nh", 1);
                    if (ac != -1) {
                    }
                    optJSONArray2 = jSONObject3.optJSONArray("l");
                    for (i = 0; i < optJSONArray2.length(); i++) {
                        optJSONObject = optJSONArray2.optJSONObject(i);
                        cnVar = new cn();
                        a2 = dc.a(optJSONObject.optString("able", "false"), false);
                        cnVar.a = a2;
                        if (!a2) {
                            cnVar.b = optJSONObject.optString("pn", "");
                            cnVar.c = optJSONObject.optString("cn", "");
                            cnVar.e = optJSONObject.optString("a", "");
                            optJSONArray3 = optJSONObject.optJSONArray("b");
                            if (optJSONArray3 != null) {
                                arrayList = new ArrayList();
                                for (optInt = 0; optInt < optJSONArray3.length(); optInt++) {
                                    optJSONObject2 = optJSONArray3.optJSONObject(optInt);
                                    hashMap = new HashMap(16);
                                    hashMap.put(optJSONObject2.optString("k"), optJSONObject2.optString(IXAdRequestInfo.V));
                                    arrayList.add(hashMap);
                                }
                                cnVar.d = arrayList;
                            }
                            cnVar.f = dc.a(optJSONObject.optString("is", "false"), false);
                            Z.add(cnVar);
                        }
                    }
                    optJSONArray = jSONObject3.optJSONArray("sl");
                    if (optJSONArray != null) {
                        for (i = 0; i < optJSONArray.length(); i++) {
                            optString = optJSONArray.optJSONObject(i).optString("pan");
                            if (TextUtils.isEmpty(optString)) {
                                ae.add(optString);
                            }
                        }
                    }
                }
                jSONObject2 = aVar.i;
                if (jSONObject2 != null) {
                    a2 = dc.a(jSONObject2.optString("able"), af);
                    af = a2;
                    if (a2) {
                        ag = jSONObject2.optInt("c", ag);
                    }
                }
                jSONObject2 = aVar.w;
                if (jSONObject2 != null) {
                    return true;
                }
                jSONObject2 = jSONObject2.optJSONObject("157");
                if (jSONObject2 != null) {
                    return true;
                }
                aj = dc.a(jSONObject2.optString("able"), aj);
                a4 = cl.a("co", "1.0.0");
                if (aj) {
                    i = false;
                    ak = false;
                    al = false;
                    if (cq.a(context, a4)) {
                        cr.a(context, "co", "config|coDex able is false");
                    }
                } else {
                    am = jSONObject2.optInt("cv", -1);
                    ak = dc.a(jSONObject2.optString("co"), ak);
                    al = dc.a(jSONObject2.optString("oo"), al);
                    optString = jSONObject2.optString(IXAdRequestInfo.V);
                    obj = jSONObject2.optString("u");
                    optString2 = jSONObject2.optString("m");
                    wVar = new w(obj, optString2);
                    wVar.a(j);
                    a5 = x.a(context, wVar, a4);
                    if (a5) {
                    }
                    i = a5;
                    if (a5) {
                        cs.a(context, "pref", "ok4", true);
                        x.b(context, wVar, a4);
                    }
                }
                cs.a(context, "pref", "ok0", aj);
                cs.a(context, "pref", "ok1", i);
                cs.a(context, "pref", "ok2", ak);
                cs.a(context, "pref", "ok3", al);
                return true;
            }
            x.b(context, null, b);
            if (a) {
                cVar = aVar.B;
                if (cVar != null) {
                    Q = cVar.a;
                    R = cVar.b;
                    deVar = new de(context, "loc", Q, R);
                    deVar.a(j);
                    deVar.a();
                }
            }
            aVar2 = aVar.x;
            if (aVar2 != null) {
                C = aVar2.a;
                cs.a(context, "pref", "exception", C);
                jSONObject2 = aVar2.c;
                D = jSONObject2.optInt("fn", D);
                optInt = jSONObject2.optInt("mpn", E);
                E = optInt;
                if (optInt > 500) {
                    E = 500;
                }
                if (E < 30) {
                    E = 30;
                }
                F = dc.a(jSONObject2.optString("igu"), F);
                G = jSONObject2.optInt("ms", G);
                ax.a(D, F, G);
                cs.a(context, "pref", "fn", D);
                cs.a(context, "pref", "mpn", E);
                cs.a(context, "pref", "igu", F);
                cs.a(context, "pref", "ms", G);
            }
            jSONObject = aVar.m;
            a3 = a(jSONObject, "fs");
            if (a3 != null) {
                u = a3.c;
                v = Integer.parseInt(a3.b);
            }
            a3 = a(jSONObject, "us");
            if (a3 != null) {
                w = a3.c;
                y = a3.a;
                x = Integer.parseInt(a3.b);
                if (x < 2) {
                    w = false;
                }
            }
            a3 = a(jSONObject, "rs");
            if (a3 != null) {
                a2 = a3.c;
                q = a2;
                if (a2) {
                    s = ct.b();
                    t = (long) (a3.d * 1000);
                }
                r = (long) (Integer.parseInt(a3.b) * 1000);
            }
            jSONObject2 = aVar.o;
            if (jSONObject2 != null) {
                a2 = dc.a(jSONObject2.optString("able"), T);
                T = a2;
                if (a2) {
                    optInt = jSONObject2.optInt("c", 0);
                    if (optInt == 0) {
                        U = PathInterpolatorCompat.MAX_NUM_POINTS;
                    } else {
                        U = optInt * 1000;
                    }
                    V = jSONObject2.getInt("t") / 2;
                }
            }
            jSONObject2 = aVar.p;
            if (jSONObject2 != null) {
                a2 = dc.a(jSONObject2.optString("able"), W);
                W = a2;
                if (a2) {
                    X = (long) (jSONObject2.optInt("c", 300) * 1000);
                }
                cs.a(context, "pref", "ca", W);
                cs.a(context, "pref", "ct", X);
            }
            bVar = aVar.E;
            if (bVar != null) {
                Y = a(context, bVar, "HttpDNS", "1.0.0");
            }
            jSONObject3 = aVar.j;
            if (jSONObject3 != null) {
                aa = dc.a(jSONObject3.optString("able"), aa);
                ab = jSONObject3.optLong("sysTime", ct.a());
                ac = jSONObject3.optInt(IXAdRequestInfo.AD_COUNT, 1);
                ad = jSONObject3.optInt("nh", 1);
                if (ac != -1) {
                }
                optJSONArray2 = jSONObject3.optJSONArray("l");
                for (i = 0; i < optJSONArray2.length(); i++) {
                    optJSONObject = optJSONArray2.optJSONObject(i);
                    cnVar = new cn();
                    a2 = dc.a(optJSONObject.optString("able", "false"), false);
                    cnVar.a = a2;
                    if (!a2) {
                        cnVar.b = optJSONObject.optString("pn", "");
                        cnVar.c = optJSONObject.optString("cn", "");
                        cnVar.e = optJSONObject.optString("a", "");
                        optJSONArray3 = optJSONObject.optJSONArray("b");
                        if (optJSONArray3 != null) {
                            arrayList = new ArrayList();
                            for (optInt = 0; optInt < optJSONArray3.length(); optInt++) {
                                optJSONObject2 = optJSONArray3.optJSONObject(optInt);
                                hashMap = new HashMap(16);
                                hashMap.put(optJSONObject2.optString("k"), optJSONObject2.optString(IXAdRequestInfo.V));
                                arrayList.add(hashMap);
                            }
                            cnVar.d = arrayList;
                        }
                        cnVar.f = dc.a(optJSONObject.optString("is", "false"), false);
                        Z.add(cnVar);
                    }
                }
                optJSONArray = jSONObject3.optJSONArray("sl");
                if (optJSONArray != null) {
                    for (i = 0; i < optJSONArray.length(); i++) {
                        optString = optJSONArray.optJSONObject(i).optString("pan");
                        if (TextUtils.isEmpty(optString)) {
                            ae.add(optString);
                        }
                    }
                }
            }
            jSONObject2 = aVar.i;
            if (jSONObject2 != null) {
                a2 = dc.a(jSONObject2.optString("able"), af);
                af = a2;
                if (a2) {
                    ag = jSONObject2.optInt("c", ag);
                }
            }
            jSONObject2 = aVar.w;
            if (jSONObject2 != null) {
                return true;
            }
            jSONObject2 = jSONObject2.optJSONObject("157");
            if (jSONObject2 != null) {
                return true;
            }
            aj = dc.a(jSONObject2.optString("able"), aj);
            a4 = cl.a("co", "1.0.0");
            if (aj) {
                am = jSONObject2.optInt("cv", -1);
                ak = dc.a(jSONObject2.optString("co"), ak);
                al = dc.a(jSONObject2.optString("oo"), al);
                optString = jSONObject2.optString(IXAdRequestInfo.V);
                obj = jSONObject2.optString("u");
                optString2 = jSONObject2.optString("m");
                wVar = new w(obj, optString2);
                wVar.a(j);
                a5 = x.a(context, wVar, a4);
                if (a5) {
                }
                i = a5;
                if (a5) {
                    cs.a(context, "pref", "ok4", true);
                    x.b(context, wVar, a4);
                }
            } else {
                i = false;
                ak = false;
                al = false;
                if (cq.a(context, a4)) {
                    cr.a(context, "co", "config|coDex able is false");
                }
            }
            cs.a(context, "pref", "ok0", aj);
            cs.a(context, "pref", "ok1", i);
            cs.a(context, "pref", "ok2", ak);
            cs.a(context, "pref", "ok3", al);
            return true;
        } catch (Throwable th22222222222222222) {
            cl.a(th22222222222222222, "AuthUtil", "loadConfigDataSdkUpdate");
        }
    }

    public static int b() {
        return v;
    }

    public static boolean b(long j) {
        if (!W) {
            return false;
        }
        return X < 0 || ct.a() - j < X;
    }

    public static boolean b(Context context) {
        if (!I) {
            return false;
        }
        if (J == -1 || K == 0) {
            return true;
        }
        if (ct.b(K, cs.b(context, "pref", "nowtime", 0))) {
            int b = cs.b(context, "pref", ParamKey.COUNT, 0);
            if (b >= J) {
                return false;
            }
            cs.a(context, "pref", ParamKey.COUNT, b + 1);
            return true;
        }
        i(context);
        cs.a(context, "pref", ParamKey.COUNT, 1);
        return true;
    }

    public static boolean c() {
        return w;
    }

    public static boolean c(Context context) {
        if (!M) {
            return false;
        }
        if (N == -1 || O == 0) {
            return true;
        }
        if (ct.b(O, cs.b(context, "pref", "pushSerTime", 0))) {
            int b = cs.b(context, "pref", "pushCount", 0);
            if (b >= N) {
                return false;
            }
            cs.a(context, "pref", "pushCount", b + 1);
            return true;
        }
        j(context);
        cs.a(context, "pref", "pushCount", 1);
        return true;
    }

    public static int d() {
        return x;
    }

    public static boolean d(Context context) {
        if (!S || B <= 0 || A <= 0 || B > A) {
            return false;
        }
        long b = cs.b(context, "abcd", "lct", 0);
        long b2 = cs.b(context, "abcd", "lst", 0);
        long b3 = ct.b();
        if (b3 < b) {
            cs.a(context, "abcd", "lct", b3);
            return false;
        }
        if (b3 - b > LogBuilder.MAX_INTERVAL) {
            cs.a(context, "abcd", "lct", b3);
            cs.a(context, "abcd", "t", 0);
        }
        if (b3 - b2 < ((long) z)) {
            return false;
        }
        int b4 = cs.b(context, "abcd", "t", 0) + 1;
        if (b4 > A) {
            return false;
        }
        cs.a(context, "abcd", "lst", b3);
        cs.a(context, "abcd", "t", b4);
        return true;
    }

    public static void e(Context context) {
        try {
            C = cs.b(context, "pref", "exception", C);
            f(context);
        } catch (Throwable th) {
            cl.a(th, "AuthUtil", "loadLastAbleState p1");
        }
        try {
            D = cs.b(context, "pref", "fn", D);
            E = cs.b(context, "pref", "mpn", E);
            F = cs.b(context, "pref", "igu", F);
            G = cs.b(context, "pref", "ms", G);
            ax.a(D, F, G);
        } catch (Throwable th2) {
        }
        try {
            W = cs.b(context, "pref", "ca", W);
            X = cs.b(context, "pref", "ct", X);
        } catch (Throwable th3) {
        }
        try {
            h = cs.b(context, "pref", "fr", h);
        } catch (Throwable th4) {
        }
        try {
            aj = cs.b(context, "pref", "ok0", aj);
            i = cs.b(context, "pref", "ok1", i);
            ak = cs.b(context, "pref", "ok2", ak);
            al = cs.b(context, "pref", "ok3", al);
        } catch (Throwable th5) {
        }
    }

    public static boolean e() {
        return y;
    }

    public static String f() {
        return k;
    }

    public static void f(Context context) {
        try {
            dk b = cl.b();
            b.a(C);
            j.a(context, b);
        } catch (Throwable th) {
        }
    }

    public static String g() {
        return l;
    }

    public static boolean g(Context context) {
        boolean z = ac != -1 && ac < ad;
        if (!aa || ac == 0 || ad == 0 || ab == 0 || z) {
            return false;
        }
        if (ae != null && ae.size() > 0) {
            for (String b : ae) {
                if (ct.b(context, b)) {
                    return false;
                }
            }
        }
        if (ac == -1 && ad == -1) {
            return true;
        }
        long b2 = cs.b(context, "pref", "ots", 0);
        long b3 = cs.b(context, "pref", "otsh", 0);
        int b4 = cs.b(context, "pref", "otn", 0);
        int b5 = cs.b(context, "pref", "otnh", 0);
        if (ac != -1) {
            if (!ct.b(ab, b2)) {
                try {
                    Editor edit = context.getSharedPreferences("pref", 0).edit();
                    edit.putLong("ots", ab);
                    edit.putLong("otsh", ab);
                    edit.putInt("otn", 0);
                    edit.putInt("otnh", 0);
                    cs.a(edit);
                } catch (Throwable th) {
                    cl.a(th, "AuthUtil", "resetPrefsBind");
                }
                cs.a(context, "pref", "otn", 1);
                cs.a(context, "pref", "otnh", 1);
                return true;
            } else if (b4 < ac) {
                if (ad == -1) {
                    cs.a(context, "pref", "otn", b4 + 1);
                    cs.a(context, "pref", "otnh", 0);
                    return true;
                } else if (!ct.a(ab, b3)) {
                    cs.a(context, "pref", "otsh", ab);
                    cs.a(context, "pref", "otn", b4 + 1);
                    cs.a(context, "pref", "otnh", 1);
                    return true;
                } else if (b5 < ad) {
                    int i = b5 + 1;
                    cs.a(context, "pref", "otn", b4 + 1);
                    cs.a(context, "pref", "otnh", i);
                    return true;
                }
            }
        }
        if (ac == -1) {
            cs.a(context, "pref", "otn", 0);
            if (ad == -1) {
                cs.a(context, "pref", "otnh", 0);
                return true;
            } else if (!ct.a(ab, b3)) {
                cs.a(context, "pref", "otsh", ab);
                cs.a(context, "pref", "otnh", 1);
                return true;
            } else if (b5 < ad) {
                cs.a(context, "pref", "otnh", b5 + 1);
                return true;
            }
        }
        return false;
    }

    public static String h() {
        return m;
    }

    public static boolean h(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (ct.b() - f < ((long) d)) {
                return false;
            }
            g = true;
            return true;
        } catch (Throwable th) {
            cl.a(th, "Aps", "isConfigNeedUpdate");
            return false;
        }
    }

    public static String i() {
        return n;
    }

    private static void i(Context context) {
        try {
            Editor edit = context.getSharedPreferences("pref", 0).edit();
            edit.putLong("nowtime", K);
            edit.putInt(ParamKey.COUNT, 0);
            cs.a(edit);
        } catch (Throwable th) {
            cl.a(th, "AuthUtil", "resetPrefsBind");
        }
    }

    public static String j() {
        return o;
    }

    private static void j(Context context) {
        try {
            Editor edit = context.getSharedPreferences("pref", 0).edit();
            edit.putLong("pushSerTime", O);
            edit.putInt("pushCount", 0);
            cs.a(edit);
        } catch (Throwable th) {
            cl.a(th, "AuthUtil", "resetPrefsBind");
        }
    }

    public static String k() {
        return p;
    }

    public static boolean l() {
        return b;
    }

    public static ArrayList<String> m() {
        return L;
    }

    public static ArrayList<String> n() {
        return P;
    }

    public static boolean o() {
        return C;
    }

    public static int p() {
        return E;
    }

    public static boolean q() {
        return H;
    }

    public static void r() {
        H = false;
    }

    public static boolean s() {
        return T;
    }

    public static long t() {
        return X;
    }

    public static boolean u() {
        return W;
    }

    public static boolean v() {
        return Y;
    }

    public static List<cn> w() {
        return Z;
    }

    public static boolean x() {
        return af;
    }

    public static int y() {
        return ag;
    }

    public static boolean z() {
        return ai;
    }
}
