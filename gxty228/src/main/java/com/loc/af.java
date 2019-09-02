package com.loc;

import java.util.HashMap;
import java.util.Map;

@q(a = "file")
/* compiled from: DynamicPlugin */
public class af {
    @r(a = "fname", b = 6)
    private String a;
    @r(a = "md", b = 6)
    private String b;
    @r(a = "sname", b = 6)
    private String c;
    @r(a = "version", b = 6)
    private String d;
    @r(a = "dversion", b = 6)
    private String e;
    @r(a = "status", b = 6)
    private String f;

    /* compiled from: DynamicPlugin */
    public static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f = "copy";

        public a(String str, String str2, String str3, String str4, String str5) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
        }

        public final a a(String str) {
            this.f = str;
            return this;
        }

        public final af a() {
            return new af(this);
        }
    }

    private af() {
    }

    public af(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
    }

    public static String a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("sname", str);
        return p.a(hashMap);
    }

    public static String a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("dversion", str2);
        return p.a(hashMap);
    }

    public static String a(String str, String str2, String str3, String str4) {
        Map hashMap = new HashMap();
        hashMap.put("fname", str);
        hashMap.put("sname", str2);
        hashMap.put("dversion", str4);
        hashMap.put("version", str3);
        return p.a(hashMap);
    }

    public static String b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("fname", str);
        return p.a(hashMap);
    }

    public static String b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("status", str2);
        return p.a(hashMap);
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final void c(String str) {
        this.f = str;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public final String f() {
        return this.f;
    }
}
