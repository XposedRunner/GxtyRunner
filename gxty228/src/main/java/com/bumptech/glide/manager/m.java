package com.bumptech.glide.manager;

import com.bumptech.glide.g.h;
import com.bumptech.glide.request.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: RequestTracker */
public class m {
    private final Set<a> a = Collections.newSetFromMap(new WeakHashMap());
    private final List<a> b = new ArrayList();
    private boolean c;

    public void a(a aVar) {
        this.a.add(aVar);
        if (this.c) {
            this.b.add(aVar);
        } else {
            aVar.b();
        }
    }

    public void b(a aVar) {
        this.a.remove(aVar);
        this.b.remove(aVar);
    }

    public void a() {
        this.c = true;
        for (a aVar : h.a(this.a)) {
            if (aVar.f()) {
                aVar.e();
                this.b.add(aVar);
            }
        }
    }

    public void b() {
        this.c = false;
        for (a aVar : h.a(this.a)) {
            if (!(aVar.g() || aVar.i() || aVar.f())) {
                aVar.b();
            }
        }
        this.b.clear();
    }

    public void c() {
        for (a d : h.a(this.a)) {
            d.d();
        }
        this.b.clear();
    }

    public void d() {
        for (a aVar : h.a(this.a)) {
            if (!(aVar.g() || aVar.i())) {
                aVar.e();
                if (this.c) {
                    this.b.add(aVar);
                } else {
                    aVar.b();
                }
            }
        }
    }
}
