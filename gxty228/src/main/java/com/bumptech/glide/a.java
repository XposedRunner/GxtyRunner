package com.bumptech.glide;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.manager.m;
import com.bumptech.glide.request.b.j;

/* compiled from: DrawableRequestBuilder */
public class a<ModelType> extends c<ModelType, g, com.bumptech.glide.load.resource.d.a, b> {
    public /* synthetic */ c b(int i, int i2) {
        return a(i, i2);
    }

    public /* synthetic */ c b(com.bumptech.glide.load.a aVar) {
        return a(aVar);
    }

    public /* synthetic */ c b(com.bumptech.glide.load.b bVar) {
        return a(bVar);
    }

    public /* synthetic */ c b(d dVar) {
        return a(dVar);
    }

    public /* synthetic */ c b(DiskCacheStrategy diskCacheStrategy) {
        return a(diskCacheStrategy);
    }

    public /* synthetic */ c b(com.bumptech.glide.request.a.d dVar) {
        return a(dVar);
    }

    public /* synthetic */ c b(Object obj) {
        return a(obj);
    }

    public /* synthetic */ c b(boolean z) {
        return a(z);
    }

    public /* synthetic */ c b(f[] fVarArr) {
        return a(fVarArr);
    }

    public /* synthetic */ c c(int i) {
        return b(i);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public /* synthetic */ c d(int i) {
        return a(i);
    }

    public /* synthetic */ c g() {
        return d();
    }

    a(Context context, Class<ModelType> cls, com.bumptech.glide.e.f<ModelType, g, com.bumptech.glide.load.resource.d.a, b> fVar, e eVar, m mVar, com.bumptech.glide.manager.g gVar) {
        super(context, cls, fVar, b.class, eVar, mVar, gVar);
        c();
    }

    public a<ModelType> a(d<g, com.bumptech.glide.load.resource.d.a> dVar) {
        super.b((d) dVar);
        return this;
    }

    public a<ModelType> a() {
        return a(this.c.c());
    }

    public a<ModelType> b() {
        return a(this.c.d());
    }

    public a<ModelType> a(f<com.bumptech.glide.load.resource.d.a>... fVarArr) {
        super.b((f[]) fVarArr);
        return this;
    }

    public final a<ModelType> c() {
        super.b(new com.bumptech.glide.request.a.a());
        return this;
    }

    public a<ModelType> a(com.bumptech.glide.request.a.d<b> dVar) {
        super.b((com.bumptech.glide.request.a.d) dVar);
        return this;
    }

    public a<ModelType> a(int i) {
        super.d(i);
        return this;
    }

    public a<ModelType> b(int i) {
        super.c(i);
        return this;
    }

    public a<ModelType> a(DiskCacheStrategy diskCacheStrategy) {
        super.b(diskCacheStrategy);
        return this;
    }

    public a<ModelType> a(boolean z) {
        super.b(z);
        return this;
    }

    public a<ModelType> a(int i, int i2) {
        super.b(i, i2);
        return this;
    }

    public a<ModelType> a(com.bumptech.glide.load.a<g> aVar) {
        super.b((com.bumptech.glide.load.a) aVar);
        return this;
    }

    public a<ModelType> a(com.bumptech.glide.load.b bVar) {
        super.b(bVar);
        return this;
    }

    public a<ModelType> a(ModelType modelType) {
        super.b((Object) modelType);
        return this;
    }

    public a<ModelType> d() {
        return (a) super.g();
    }

    public j<b> a(ImageView imageView) {
        return super.a(imageView);
    }

    void e() {
        b();
    }

    void f() {
        a();
    }
}
