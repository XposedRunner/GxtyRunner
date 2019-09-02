package com.bumptech.glide.load.engine.b;

import android.annotation.SuppressLint;
import com.bumptech.glide.g.e;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.engine.b.h.a;
import com.bumptech.glide.load.engine.i;

/* compiled from: LruResourceCache */
public class g extends e<b, i<?>> implements h {
    private a a;

    public /* synthetic */ i a(b bVar) {
        return (i) super.c(bVar);
    }

    public /* bridge */ /* synthetic */ i b(b bVar, i iVar) {
        return (i) super.b(bVar, iVar);
    }

    public g(int i) {
        super(i);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    protected void a(b bVar, i<?> iVar) {
        if (this.a != null) {
            this.a.b(iVar);
        }
    }

    protected int a(i<?> iVar) {
        return iVar.c();
    }

    @SuppressLint({"InlinedApi"})
    public void a(int i) {
        if (i >= 60) {
            a();
        } else if (i >= 40) {
            b(b() / 2);
        }
    }
}
