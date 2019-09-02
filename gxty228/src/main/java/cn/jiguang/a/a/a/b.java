package cn.jiguang.a.a.a;

public final class b {
    private String a;
    private String b;
    private String c;
    private String d;

    public final String a() {
        return this.a;
    }

    public final void a(String str) {
        this.a = str;
    }

    public final String b() {
        return this.c;
    }

    public final void b(String str) {
        this.b = str;
    }

    public final String c() {
        return this.d;
    }

    public final void c(String str) {
        this.c = str;
    }

    public final void d(String str) {
        this.d = str;
    }

    public final String toString() {
        return "ArpInfo{ip_Addr='" + this.a + '\'' + ", hw_type='" + this.b + '\'' + ", flag='" + this.c + '\'' + ", hw_addr='" + this.d + '\'' + '}';
    }
}
