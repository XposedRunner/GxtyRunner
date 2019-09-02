package cn.jiguang.d.f;

public final class d {
    private static d a;
    private static final Object c = new Object();
    private a b;

    private d() {
    }

    public static d a() {
        if (a == null) {
            synchronized (c) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    public final a b() {
        if (this.b == null) {
            this.b = new c();
        }
        return this.b;
    }
}
