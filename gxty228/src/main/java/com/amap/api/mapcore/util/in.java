package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.NativeConfigInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SoManagerCore */
public final class in {
    private hz a;
    private ic b;
    private iz c;
    private ii d;
    private List<ij> e = new ArrayList();

    static /* synthetic */ boolean a(in inVar, Context context, iz izVar, gk gkVar) {
        if (izVar == null) {
            return false;
        }
        if (TextUtils.isEmpty(izVar.a()) || TextUtils.isEmpty(izVar.b())) {
            return false;
        }
        if (!gkVar.a().equals(izVar.a()) || !gkVar.b().equals(izVar.b()) || if.a(gkVar.b(), izVar.c()) >= 0) {
            return false;
        }
        if (izVar.d() == null) {
            return false;
        }
        for (ij b : izVar.d()) {
            if (ik.b(context, b)) {
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ boolean a(in inVar, iz izVar, iz izVar2) {
        if (izVar2 == null || TextUtils.isEmpty(izVar2.c())) {
            return false;
        }
        return izVar == null || TextUtils.isEmpty(izVar.c()) || if.a(izVar.c(), izVar2.c()) <= 0;
    }

    public in(Context context) {
        this.a = new hz(context);
        this.b = new ic();
        this.d = new ii(this.b, this.a, context);
    }

    public final void a(Context context, gk gkVar, String str, boolean z, boolean z2, String str2, String str3, boolean z3) {
        this.c = ik.a(context);
        iz izVar = new iz(str, str2, str3, z2, z3);
        final iz b = ik.b(context);
        final boolean z4 = z;
        final Context context2 = context;
        final gk gkVar2 = gkVar;
        final boolean z5 = z2;
        final iz izVar2 = izVar;
        this.b.a(new Runnable(this) {
            private /* synthetic */ in g;

            public final void run() {
                if (z4) {
                    this.g.a(context2, gkVar2.a(), z5);
                    if (z5 && iz.a(izVar2)) {
                        this.g.a(context2, gkVar2, izVar2, this.g.c, b);
                        return;
                    }
                    return;
                }
                this.g.a(context2, gkVar2.a());
            }
        });
    }

    public final void a(Context context, gk gkVar, iz izVar, iz izVar2, iz izVar3) {
        id idVar = new id(izVar, this.a);
        if (izVar != null && iz.a(izVar) && izVar2 != null && iz.a(izVar2)) {
            List d = izVar.d();
            List d2 = izVar2.d();
            if (!(d == null || d2 == null)) {
                ij ijVar;
                int i;
                HashSet hashSet = new HashSet();
                HashSet hashSet2 = new HashSet();
                for (int i2 = 0; i2 < d.size(); i2++) {
                    ijVar = (ij) d.get(i2);
                    for (i = 0; i < d2.size(); i++) {
                        ij ijVar2 = (ij) d2.get(i);
                        if (ijVar2.a().equals(izVar.a()) && ij.a(ijVar2, ijVar) && ijVar.b && !ijVar2.b) {
                            hashSet2.add(ijVar2.b());
                            hashSet.add(ijVar2.f());
                        }
                    }
                }
                Object obj = null;
                Iterator it = hashSet2.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    i = 0;
                    while (i < d2.size()) {
                        ijVar = (ij) d2.get(i);
                        if (!hashSet.contains(ijVar.f())) {
                            obj = null;
                            break;
                        }
                        Object obj2;
                        if (ijVar.b().equals(str)) {
                            ijVar.b = true;
                            obj2 = 1;
                        } else {
                            obj2 = obj;
                        }
                        i++;
                        obj = obj2;
                    }
                }
                if (obj != null) {
                    ik.a(context, izVar2);
                }
            }
        }
        new ih(context, idVar, gkVar, izVar, izVar2, izVar3, this.a).a();
    }

    public final boolean a(Context context, gk gkVar, String str, boolean z) {
        boolean z2 = false;
        if (gkVar != null) {
            try {
                z2 = b(context, gkVar, str, z);
            } catch (Throwable th) {
            }
        }
        return z2;
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (!str.startsWith("lib")) {
            str = "lib" + str;
        }
        if (str.endsWith(".so")) {
            return str;
        }
        return str + ".so";
    }

    private boolean b(final Context context, final gk gkVar, final String str, boolean z) {
        try {
            boolean z2;
            gl.a(context);
            int a = a(context, str, gkVar);
            if (this.b != null) {
                this.b.a();
            }
            if ((a & 4) > 0) {
                this.b.a(new Runnable(this) {
                    private /* synthetic */ in b;

                    public final void run() {
                        if (context != null) {
                            ik.c(context);
                        }
                        hz.e(this.b.a.a());
                    }
                });
            }
            if ((a & 8) > 0) {
                this.b.a(new Runnable(this) {
                    private /* synthetic */ in c;

                    public final void run() {
                        String a = in.a(str);
                        this.c.c = ik.a(context);
                        ij a2 = this.c.c.a(a);
                        if (a2 != null && a2.g()) {
                            String b = a2.b();
                            List arrayList = new ArrayList();
                            List<ij> arrayList2 = new ArrayList();
                            for (ij a22 : this.c.c.d()) {
                                if (a22.b().equals(b)) {
                                    hz.f(this.c.a.a() + File.separator + hz.a(context, a));
                                } else {
                                    arrayList.add(a22);
                                }
                            }
                            this.c.c.g = arrayList;
                            ik.a(context, this.c.c);
                            for (ij a222 : arrayList2) {
                                ik.a(context, a222);
                            }
                        }
                    }
                });
            }
            if ((a & 2) > 0) {
                this.b.a(new Runnable(this) {
                    private /* synthetic */ in c;

                    public final void run() {
                        iz b = ik.b(context);
                        if (!gkVar.a().equals(b.a())) {
                            return;
                        }
                        if (in.a(this.c, context, b, gkVar)) {
                            if (in.a(this.c, ik.a(context), b)) {
                                id.a(context, this.c.a);
                                return;
                            } else {
                                this.c.d.a(b.a());
                                return;
                            }
                        }
                        this.c.d.a(gkVar.a());
                    }
                });
            }
            if ((a & 1) > 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                return true;
            }
        } catch (Throwable th) {
        }
        try {
            System.loadLibrary(str);
            if (z) {
                return false;
            }
            return true;
        } catch (Throwable th2) {
            return false;
        }
    }

    private int a(Context context, String str, gk gkVar) {
        String str2;
        gl.a(context);
        String a = a(str);
        hz hzVar = this.a;
        if (hzVar == null || TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = hzVar.a() + File.separator + hz.a(context, a(str));
        }
        this.c = ik.a(context);
        ij a2 = this.c.a(a);
        if (a2 == null || !a2.g()) {
            return 2;
        }
        if (a2 != null && a2.g() && !b(str2)) {
            return 6;
        }
        if (!b(str2)) {
            return 2;
        }
        if (ik.b(context, a2)) {
            return 10;
        }
        a = hz.a(context, a);
        if (a2.b && a(context, a2, a, gkVar)) {
            this.e.add(a2);
            System.load(str2);
            try {
                a();
            } catch (Throwable th) {
            }
            return 3;
        } else if (a(context, a2, a, gkVar)) {
            return 2;
        } else {
            return 6;
        }
    }

    public final void a() {
        if (this.e != null && this.e.size() != 0 && im.a) {
            NativeConfigInfo.setConfigInfo(a(im.b, this.e, im.c));
        }
    }

    private String a(gk gkVar, List<ij> list, List<String> list2) {
        JSONObject jSONObject = new JSONObject();
        String a = ij.a((List) list);
        String a2 = a((List) list2);
        String a3 = a(gkVar);
        try {
            jSONObject.put("ik", a);
            jSONObject.put("jk", a2);
            jSONObject.put("lk", a3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private static String a(gk gkVar) {
        if (gkVar == null) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(IXAdRequestInfo.SN, gkVar.a());
            jSONObject.put("sv", gkVar.b());
            return jSONObject.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    private static String a(List<String> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                stringBuffer.append(str);
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }

    private boolean a(Context context, ij ijVar, String str, gk gkVar) {
        boolean z;
        if (ijVar == null) {
            z = false;
        } else if (TextUtils.isEmpty(ijVar.c()) || TextUtils.isEmpty(ijVar.d())) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            z = false;
        } else if (!gkVar.a().equals(ijVar.c()) || !gkVar.b().equals(ijVar.d()) || if.a(gkVar.b(), ijVar.e()) >= 0) {
            z = false;
        } else if (ik.b(context, ijVar)) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            if (hz.c(ijVar.a, this.a.a() + File.separator + str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean b(String str) {
        File file = new File(str);
        return file.exists() && !file.isDirectory();
    }

    private static List<ij> a(iz izVar, String str) {
        if (izVar == null || izVar.d() == null || TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        List d = izVar.d();
        List<ij> arrayList = new ArrayList();
        for (int i = 0; i < d.size(); i++) {
            ij ijVar = (ij) d.get(i);
            if (str.equals(ijVar.a())) {
                arrayList.add(ijVar);
            }
        }
        return arrayList;
    }

    public final void a(Context context, String str, boolean z) {
        try {
            iz a = ik.a(context);
            List a2 = a(a, str);
            int i = 0;
            Object obj = null;
            while (i < a2.size()) {
                ij ijVar = (ij) a2.get(i);
                if (ijVar.b != z) {
                    obj = 1;
                    ijVar.b = z;
                }
                i++;
                obj = obj;
            }
            if (obj != null) {
                ik.a(context, a);
            }
        } catch (Throwable th) {
        }
    }

    public final void a(Context context, String str) {
        List a = a(ik.a(context), str);
        for (int i = 0; i < a.size(); i++) {
            ik.a(context, (ij) a.get(i));
        }
    }
}
