package com.amap.api.services.a;

import android.text.TextUtils;

@t(a = "a")
/* compiled from: SDKInfo */
public class e {
    @u(a = "a1", b = 6)
    private String a;
    @u(a = "a2", b = 6)
    private String b;
    @u(a = "a6", b = 2)
    private int c;
    @u(a = "a3", b = 6)
    private String d;
    @u(a = "a4", b = 6)
    private String e;
    @u(a = "a5", b = 6)
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

        public e a() throws be {
            if (this.g != null) {
                return new e();
            }
            throw new be("sdk packages is null");
        }
    }

    private e() {
        this.c = 1;
        this.l = null;
    }

    private e(a aVar) {
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
        this.b = f.b(this.h);
        this.a = f.b(this.j);
        this.d = f.b(this.i);
        this.e = f.b(a(this.l));
        this.f = f.b(this.k);
    }

    public String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.a)) {
            this.j = f.c(this.a);
        }
        return this.j;
    }

    public String b() {
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.b)) {
            this.h = f.c(this.b);
        }
        return this.h;
    }

    public String c() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f)) {
            this.k = f.c(this.f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "standard";
        }
        return this.k;
    }

    public boolean d() {
        return this.c == 1;
    }

    public String[] e() {
        if ((this.l == null || this.l.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.l = a(f.c(this.e));
        }
        return (String[]) this.l.clone();
    }

    private String[] a(String str) {
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

    public static String f() {
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
        if (hashCode() != ((e) obj).hashCode()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        m mVar = new m();
        mVar.a(this.j).a(this.g).a(this.h).a(this.l);
        return mVar.a();
    }
}
