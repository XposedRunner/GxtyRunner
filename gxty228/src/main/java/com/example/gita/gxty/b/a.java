package com.example.gita.gxty.b;

/* compiled from: StepCount */
public class a implements b {
    private int a = 0;
    private int b = 0;
    private d c;
    private long d = 0;
    private long e = 0;
    private c f = new c();

    public a() {
        this.f.a((b) this);
    }

    public c a() {
        return this.f;
    }

    public void b() {
        this.d = this.e;
        this.e = System.currentTimeMillis();
        if (this.e - this.d > 3000) {
            this.a = 1;
        } else if (this.a < 9) {
            this.a++;
        } else if (this.a == 9) {
            this.a++;
            this.b += this.a;
            c();
        } else {
            this.b++;
            c();
        }
    }

    public void a(d dVar) {
        this.c = dVar;
    }

    public void c() {
        if (this.c != null) {
            this.c.a(this.b);
        }
    }

    public void a(int i) {
        this.b = i;
        this.a = 0;
        this.d = 0;
        this.e = 0;
        c();
    }
}
