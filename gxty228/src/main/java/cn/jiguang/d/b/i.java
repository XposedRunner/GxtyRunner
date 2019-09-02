package cn.jiguang.d.b;

import cn.jiguang.d.a.a;
import cn.jiguang.d.d.o;
import cn.jiguang.e.d;
import cn.jiguang.g.k;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public final class i {
    private static i l = null;
    private static Pattern m = Pattern.compile("(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))");
    List<String> a = new ArrayList();
    List<String> b = new ArrayList();
    List<String> c = new ArrayList();
    boolean d = false;
    String e;
    String f;
    int g;
    List<String> h = new ArrayList();
    List<Integer> i = new ArrayList();
    String j;
    boolean k = false;

    public static boolean c(String str) {
        return m.matcher(str).matches();
    }

    public final String a() {
        return this.f;
    }

    public final void a(String str) {
        int i = 0;
        if (k.a(str)) {
            str = a.m();
        }
        if (!k.a(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArray = jSONObject.getJSONArray("ips");
                if (jSONArray == null || jSONArray.length() == 0) {
                    d.g("SisInfo", "ips is null");
                    return;
                }
                int i2;
                d.a("SisInfo", "ips:" + jSONArray.toString());
                for (i2 = 0; i2 < jSONArray.length(); i2++) {
                    this.a.add(jSONArray.optString(i2));
                }
                jSONArray = jSONObject.getJSONArray("op_conns");
                if (jSONArray == null || jSONArray.length() == 0) {
                    d.g("SisInfo", "op_conns is null");
                }
                if (jSONArray != null) {
                    d.a("SisInfo", "op_conns:" + jSONArray.toString());
                    for (i2 = 0; i2 < jSONArray.length(); i2++) {
                        this.b.add(jSONArray.optString(i2));
                    }
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("sis_ips");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    d.a("SisInfo", "sis_ips:" + optJSONArray.toString());
                    while (i < optJSONArray.length()) {
                        this.c.add(optJSONArray.optString(i));
                        i++;
                    }
                }
                this.d = jSONObject.optBoolean("data_report");
            } catch (Throwable th) {
                d.c("SisInfo", "parse sis :", th);
            }
        }
    }

    public final int b() {
        return this.g;
    }

    public final void b(String str) {
        this.j = str;
        if (this.a == null) {
            d.j("SisInfo", "Unexpected - Invalid sis - no ips key.");
            this.k = true;
        } else if (this.a.size() == 0) {
            d.j("SisInfo", "Unexpected - invalid sis - ips array len is 0");
            this.k = true;
        } else {
            try {
                j jVar = new j((String) this.a.get(0));
                this.f = jVar.a;
                this.g = jVar.b;
                if (this.b == null) {
                    d.c("SisInfo", "No op conn.");
                    return;
                }
                for (String str2 : this.b) {
                    try {
                        jVar = new j(str2);
                        this.h.add(jVar.a);
                        this.i.add(Integer.valueOf(jVar.b));
                    } catch (Throwable e) {
                        d.e("SisInfo", "Failed to parse op_conn - " + str2, e);
                    }
                }
            } catch (Throwable e2) {
                d.d("SisInfo", "Failed to parse ips-1 - main ip.", e2);
                this.k = true;
            }
        }
    }

    public final List<String> c() {
        return this.h;
    }

    public final List<Integer> d() {
        return this.i;
    }

    public final boolean e() {
        return this.k;
    }

    public final List<String> f() {
        return this.c;
    }

    public final boolean g() {
        return this.d;
    }

    public final void h() {
        int size = this.a.size();
        if (size != 0) {
            a.c(this.j);
            if (size > 1) {
                try {
                    j jVar = new j((String) this.a.get(1));
                    a.b(jVar.a, jVar.b);
                } catch (Throwable e) {
                    d.d("SisInfo", "Failed to parse ips-2 - default ip.", e);
                }
            } else {
                d.h("SisInfo", "Only main ip in sis.");
            }
            if (size > 2) {
                o.a((String) this.a.get(2));
                cn.jiguang.d.a.d.a((String) this.a.get(2));
            } else {
                d.h("SisInfo", "No report backup ip.");
            }
            if (this.e != null) {
                a.b(this.e);
            }
        }
    }
}
