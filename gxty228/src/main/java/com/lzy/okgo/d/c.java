package com.lzy.okgo.d;

/* compiled from: ColumnEntity */
public class c {
    public String a;
    public String b;
    public String[] c;
    public boolean d;
    public boolean e;
    public boolean f;

    public c(String... strArr) {
        this.c = strArr;
    }

    public c(String str, String str2) {
        this(str, str2, false, false, false);
    }

    public c(String str, String str2, boolean z, boolean z2) {
        this(str, str2, z, z2, false);
    }

    public c(String str, String str2, boolean z, boolean z2, boolean z3) {
        this.a = str;
        this.b = str2;
        this.d = z;
        this.e = z2;
        this.f = z3;
    }
}
