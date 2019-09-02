package com.bumptech.glide.manager;

import com.bumptech.glide.g.h;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: ActivityFragmentLifecycle */
class a implements g {
    private final Set<h> a = Collections.newSetFromMap(new WeakHashMap());
    private boolean b;
    private boolean c;

    a() {
    }

    public void a(h hVar) {
        this.a.add(hVar);
        if (this.c) {
            hVar.f();
        } else if (this.b) {
            hVar.d();
        } else {
            hVar.e();
        }
    }

    void a() {
        this.b = true;
        for (h d : h.a(this.a)) {
            d.d();
        }
    }

    void b() {
        this.b = false;
        for (h e : h.a(this.a)) {
            e.e();
        }
    }

    void c() {
        this.c = true;
        for (h f : h.a(this.a)) {
            f.f();
        }
    }
}
