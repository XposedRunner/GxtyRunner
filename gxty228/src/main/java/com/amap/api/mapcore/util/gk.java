package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

@hf(a = "a")
/* compiled from: SDKInfo */
public class gk {
    @hg(a = "a1", b = 6)
    private String a;
    @hg(a = "a2", b = 6)
    private String b;
    @hg(a = "a6", b = 2)
    private int c;
    @hg(a = "a3", b = 6)
    private String d;
    @hg(a = "a4", b = 6)
    private String e;
    @hg(a = "a5", b = 6)
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String[] l;

    /* compiled from: SDKInfo */
    public static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private boolean e = true;
        private String f = "standard";
        private String[] g = null;

        public a(String str, String str2, String str3) {
            this.a = str2;
            this.b = str2;
            this.d = str3;
            this.c = str;
        }

        public a a(String[] strArr) {
            this.g = (String[]) strArr.clone();
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public gk a() throws gp {
            if (this.g != null) {
                return new gk();
            }
            throw new gp("sdk packages is null");
        }
    }

    private gk() {
        this.c = 1;
        this.l = null;
    }

    private gk(a aVar) {
        int i = 1;
        this.c = 1;
        this.l = null;
        this.g = aVar.a;
        this.h = aVar.b;
        this.j = aVar.c;
        this.i = aVar.d;
        if (!aVar.e) {
            i = 0;
        }
        this.c = i;
        this.k = aVar.f;
        this.l = aVar.g;
        this.b = gl.b(this.h);
        this.a = gl.b(this.j);
        this.d = gl.b(this.i);
        this.e = gl.b(a(this.l));
        this.f = gl.b(this.k);
    }

    public void a(boolean z) {
        this.c = z ? 1 : 0;
    }

    public String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.a)) {
            this.j = gl.c(this.a);
        }
        return this.j;
    }

    public String b() {
        return this.g;
    }

    public String c() {
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.b)) {
            this.h = gl.c(this.b);
        }
        return this.h;
    }

    public String d() {
        if (TextUtils.isEmpty(this.i) && !TextUtils.isEmpty(this.d)) {
            this.i = gl.c(this.d);
        }
        return this.i;
    }

    public String e() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f)) {
            this.k = gl.c(this.f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "standard";
        }
        return this.k;
    }

    public boolean f() {
        return this.c == 1;
    }

    public String[] g() {
        if ((this.l == null || this.l.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.l = b(gl.c(this.e));
        }
        return (String[]) this.l.clone();
    }

    private String[] b(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private String a(String[] strArr) {
        String str = null;
        if (strArr != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : strArr) {
                    stringBuilder.append(append).append(";");
                }
                str = stringBuilder.toString();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return str;
    }

    public static String a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("a1", gl.b(str));
        return he.a(hashMap);
    }

    public static String h() {
        return "a6=1";
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (hashCode() != ((gk) obj).hashCode()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        gv gvVar = new gv();
        gvVar.a(this.j).a(this.g).a(this.h).a(this.l);
        return gvVar.a();
    }
}
