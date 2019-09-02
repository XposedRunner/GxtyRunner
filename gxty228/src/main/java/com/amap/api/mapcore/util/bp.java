package com.amap.api.mapcore.util;

/* compiled from: AbstractCityStateImp */
public abstract class bp implements bt {
    protected int a;
    protected be b;

    public bp(int i, be beVar) {
        this.a = i;
        this.b = beVar;
    }

    public int b() {
        return this.a;
    }

    public boolean a(bp bpVar) {
        return bpVar.b() == b();
    }

    public void b(bp bpVar) {
        bk.a(b() + " ==> " + bpVar.b() + "   " + getClass() + "==>" + bpVar.getClass());
    }

    public void c() {
        bk.a("Wrong call start()  State: " + b() + "  " + getClass());
    }

    public void d() {
        bk.a("Wrong call continueDownload()  State: " + b() + "  " + getClass());
    }

    public void e() {
        bk.a("Wrong call pause()  State: " + b() + "  " + getClass());
    }

    public void a() {
        bk.a("Wrong call delete()  State: " + b() + "  " + getClass());
    }

    public void a(int i) {
        bk.a("Wrong call fail()  State: " + b() + "  " + getClass());
    }

    public void f() {
        bk.a("Wrong call hasNew()  State: " + b() + "  " + getClass());
    }

    public void g() {
        bk.a("Wrong call complete()  State: " + b() + "  " + getClass());
    }
}
