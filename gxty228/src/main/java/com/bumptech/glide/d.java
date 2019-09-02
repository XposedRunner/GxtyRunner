package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.e.e;
import com.bumptech.glide.e.f;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.resource.e.c;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.m;

/* compiled from: GenericTranscodeRequest */
public class d<ModelType, DataType, ResourceType> extends c<ModelType, DataType, ResourceType, ResourceType> {
    private final l<ModelType, DataType> g;
    private final Class<DataType> h;
    private final Class<ResourceType> i;
    private final c j;

    private static <A, T, Z, R> f<A, T, Z, R> a(e eVar, l<A, T> lVar, Class<T> cls, Class<Z> cls2, c<Z, R> cVar) {
        return new e(lVar, cVar, eVar.b((Class) cls, (Class) cls2));
    }

    d(Context context, e eVar, Class<ModelType> cls, l<ModelType, DataType> lVar, Class<DataType> cls2, Class<ResourceType> cls3, m mVar, g gVar, c cVar) {
        super(context, cls, a(eVar, lVar, cls2, cls3, com.bumptech.glide.load.resource.e.e.b()), cls3, eVar, mVar, gVar);
        this.g = lVar;
        this.h = cls2;
        this.i = cls3;
        this.j = cVar;
    }
}
