package com.bumptech.glide;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.bumptech.glide.e.e;
import com.bumptech.glide.load.b.f;
import com.bumptech.glide.load.b.g;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.resource.d.a;
import com.bumptech.glide.load.resource.e.c;
import com.bumptech.glide.manager.m;
import java.io.InputStream;

/* compiled from: DrawableTypeRequest */
public class b<ModelType> extends a<ModelType> {
    private final l<ModelType, InputStream> g;
    private final l<ModelType, ParcelFileDescriptor> h;
    private final c i;

    private static <A, Z, R> e<A, g, Z, R> a(e eVar, l<A, InputStream> lVar, l<A, ParcelFileDescriptor> lVar2, Class<Z> cls, Class<R> cls2, c<Z, R> cVar) {
        if (lVar == null && lVar2 == null) {
            return null;
        }
        if (cVar == null) {
            cVar = eVar.a((Class) cls, (Class) cls2);
        }
        return new e(new f(lVar, lVar2), cVar, eVar.b(g.class, (Class) cls));
    }

    b(Class<ModelType> cls, l<ModelType, InputStream> lVar, l<ModelType, ParcelFileDescriptor> lVar2, Context context, e eVar, m mVar, com.bumptech.glide.manager.g gVar, c cVar) {
        super(context, cls, a(eVar, lVar, lVar2, a.class, com.bumptech.glide.load.resource.a.b.class, null), eVar, mVar, gVar);
        this.g = lVar;
        this.h = lVar2;
        this.i = cVar;
    }
}
