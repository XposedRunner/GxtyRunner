package com.example.gita.gxty.ram.db;

import com.example.gita.gxty.ram.db.a.c;
import java.util.HashMap;
import java.util.Map;

/* compiled from: DBModel */
public class a {
    private static a a = null;
    private String b;
    private String c;
    private int d;
    private Map<Integer, c> e = new HashMap();

    public static a a() {
        if (a == null) {
            a = new a();
            a.a("gxty_db");
            a.b("com.example.gita.gxty.ram.db.gxty");
            a.a(13);
            a.e().put(Integer.valueOf(1), new c());
        }
        return a;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public int d() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public Map<Integer, c> e() {
        return this.e;
    }
}
