package com.bumptech.glide.request;

/* compiled from: ThumbnailRequestCoordinator */
public class e implements a, b {
    private a a;
    private a b;
    private b c;

    public e() {
        this(null);
    }

    public e(b bVar) {
        this.c = bVar;
    }

    public void a(a aVar, a aVar2) {
        this.a = aVar;
        this.b = aVar2;
    }

    public boolean a(a aVar) {
        return j() && (aVar.equals(this.a) || !this.a.h());
    }

    private boolean j() {
        return this.c == null || this.c.a(this);
    }

    public boolean b(a aVar) {
        return k() && aVar.equals(this.a) && !c();
    }

    private boolean k() {
        return this.c == null || this.c.b(this);
    }

    public boolean c() {
        return l() || h();
    }

    public void c(a aVar) {
        if (!aVar.equals(this.b)) {
            if (this.c != null) {
                this.c.c(this);
            }
            if (!this.b.g()) {
                this.b.d();
            }
        }
    }

    private boolean l() {
        return this.c != null && this.c.c();
    }

    public void b() {
        if (!this.b.f()) {
            this.b.b();
        }
        if (!this.a.f()) {
            this.a.b();
        }
    }

    public void e() {
        this.a.e();
        this.b.e();
    }

    public void d() {
        this.b.d();
        this.a.d();
    }

    public boolean f() {
        return this.a.f();
    }

    public boolean g() {
        return this.a.g() || this.b.g();
    }

    public boolean h() {
        return this.a.h() || this.b.h();
    }

    public boolean i() {
        return this.a.i();
    }

    public void a() {
        this.a.a();
        this.b.a();
    }
}
