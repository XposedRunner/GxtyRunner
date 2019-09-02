package com.loc;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

@q(a = "a")
/* compiled from: SDKInfo */
public class dk {
    @r(a = "a1", b = 6)
    private String a;
    @r(a = "a2", b = 6)
    private String b;
    @r(a = "a6", b = 2)
    private int c;
    @r(a = "a3", b = 6)
    private String d;
    @r(a = "a4", b = 6)
    private String e;
    @r(a = "a5", b = 6)
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

        public final a a(String str) {
            this.b = str;
            return this;
        }

        public final a a(String[] strArr) {
            this.g = (String[]) strArr.clone();
            return this;
        }

        public final dk a() throws k {
            if (this.g != null) {
                return new dk();
            }
            throw new k("sdk packages is null");
        }
    }

    private dk() {
        this.c = 1;
        this.l = null;
    }

    private dk(a aVar) {
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
        this.b = dl.b(this.h);
        this.a = dl.b(this.j);
        this.d = dl.b(this.i);
        this.e = dl.b(a(this.l));
        this.f = dl.b(this.k);
    }

    public static String a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("a1", dl.b(str));
        return p.a(hashMap);
    }

    private static String a(String[] strArr) {
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

    private static String[] b(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String g() {
        return "a6=1";
    }

    public final String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.a)) {
            this.j = dl.c(this.a);
        }
        return this.j;
    }

    public final void a(boolean z) {
        this.c = z ? 1 : 0;
    }

    public final String b() {
        return this.g;
    }

    public final String c() {
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.b)) {
            this.h = dl.c(this.b);
        }
        return this.h;
    }

    public final String d() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f)) {
            this.k = dl.c(this.f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "standard";
        }
        return this.k;
    }

    public final boolean e() {
        return this.c == 1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return hashCode() == ((dk) obj).hashCode();
    }

    public final String[] f() {
        if ((this.l == null || this.l.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.l = b(dl.c(this.e));
        }
        return (String[]) this.l.clone();
    }

    public int hashCode() {
        f fVar = new f();
        fVar.a(this.j).a(this.g).a(this.h).a(this.l);
        return fVar.a();
    }
}
