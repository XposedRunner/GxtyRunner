package com.bumptech.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.e.a;
import com.bumptech.glide.g.h;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.f;
import com.bumptech.glide.manager.g;
import com.bumptech.glide.manager.m;
import com.bumptech.glide.request.GenericRequest;
import com.bumptech.glide.request.a.d;
import com.bumptech.glide.request.a.e;
import com.bumptech.glide.request.b.j;

/* compiled from: GenericRequestBuilder */
public class c<ModelType, DataType, ResourceType, TranscodeType> implements Cloneable {
    private boolean A;
    private Drawable B;
    private int C;
    protected final Class<ModelType> a;
    protected final Context b;
    protected final e c;
    protected final Class<TranscodeType> d;
    protected final m e;
    protected final g f;
    private a<ModelType, DataType, ResourceType, TranscodeType> g;
    private ModelType h;
    private b i = com.bumptech.glide.f.a.a();
    private boolean j;
    private int k;
    private int l;
    private com.bumptech.glide.request.c<? super ModelType, TranscodeType> m;
    private Float n;
    private c<?, ?, ?, TranscodeType> o;
    private Float p = Float.valueOf(1.0f);
    private Drawable q;
    private Drawable r;
    private Priority s = null;
    private boolean t = true;
    private d<TranscodeType> u = e.a();
    private int v = -1;
    private int w = -1;
    private DiskCacheStrategy x = DiskCacheStrategy.RESULT;
    private f<ResourceType> y = com.bumptech.glide.load.resource.d.b();
    private boolean z;

    /* compiled from: GenericRequestBuilder */
    /* renamed from: com.bumptech.glide.c$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.FIT_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return g();
    }

    c(Context context, Class<ModelType> cls, com.bumptech.glide.e.f<ModelType, DataType, ResourceType, TranscodeType> fVar, Class<TranscodeType> cls2, e eVar, m mVar, g gVar) {
        a aVar = null;
        this.b = context;
        this.a = cls;
        this.d = cls2;
        this.c = eVar;
        this.e = mVar;
        this.f = gVar;
        if (fVar != null) {
            aVar = new a(fVar);
        }
        this.g = aVar;
        if (context == null) {
            throw new NullPointerException("Context can't be null");
        } else if (cls != null && fVar == null) {
            throw new NullPointerException("LoadProvider must not be null");
        }
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.load.d<DataType, ResourceType> dVar) {
        if (this.g != null) {
            this.g.a((com.bumptech.glide.load.d) dVar);
        }
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(com.bumptech.glide.load.a<DataType> aVar) {
        if (this.g != null) {
            this.g.a((com.bumptech.glide.load.a) aVar);
        }
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(DiskCacheStrategy diskCacheStrategy) {
        this.x = diskCacheStrategy;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(f<ResourceType>... fVarArr) {
        this.z = true;
        if (fVarArr.length == 1) {
            this.y = fVarArr[0];
        } else {
            this.y = new com.bumptech.glide.load.c(fVarArr);
        }
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(d<TranscodeType> dVar) {
        if (dVar == null) {
            throw new NullPointerException("Animation factory must not be null!");
        }
        this.u = dVar;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> d(int i) {
        this.k = i;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> c(int i) {
        this.l = i;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(boolean z) {
        this.t = !z;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(int i, int i2) {
        if (h.a(i, i2)) {
            this.w = i;
            this.v = i2;
            return this;
        }
        throw new IllegalArgumentException("Width and height must be Target#SIZE_ORIGINAL or > 0");
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Signature must not be null");
        }
        this.i = bVar;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> b(ModelType modelType) {
        this.h = modelType;
        this.j = true;
        return this;
    }

    public c<ModelType, DataType, ResourceType, TranscodeType> g() {
        try {
            c<ModelType, DataType, ResourceType, TranscodeType> cVar = (c) super.clone();
            cVar.g = this.g != null ? this.g.g() : null;
            return cVar;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public <Y extends j<TranscodeType>> Y a(Y y) {
        h.a();
        if (y == null) {
            throw new IllegalArgumentException("You must pass in a non null Target");
        } else if (this.j) {
            com.bumptech.glide.request.a c = y.c();
            if (c != null) {
                c.d();
                this.e.b(c);
                c.a();
            }
            c = b((j) y);
            y.a(c);
            this.f.a(y);
            this.e.a(c);
            return y;
        } else {
            throw new IllegalArgumentException("You must first set a model (try #load())");
        }
    }

    public j<TranscodeType> a(ImageView imageView) {
        h.a();
        if (imageView == null) {
            throw new IllegalArgumentException("You must pass in a non null View");
        }
        if (!(this.z || imageView.getScaleType() == null)) {
            switch (AnonymousClass1.a[imageView.getScaleType().ordinal()]) {
                case 1:
                    f();
                    break;
                case 2:
                case 3:
                case 4:
                    e();
                    break;
            }
        }
        return a(this.c.a(imageView, this.d));
    }

    void f() {
    }

    void e() {
    }

    private Priority a() {
        if (this.s == Priority.LOW) {
            return Priority.NORMAL;
        }
        if (this.s == Priority.NORMAL) {
            return Priority.HIGH;
        }
        return Priority.IMMEDIATE;
    }

    private com.bumptech.glide.request.a b(j<TranscodeType> jVar) {
        if (this.s == null) {
            this.s = Priority.NORMAL;
        }
        return a(jVar, null);
    }

    private com.bumptech.glide.request.a a(j<TranscodeType> jVar, com.bumptech.glide.request.e eVar) {
        com.bumptech.glide.request.a eVar2;
        if (this.o != null) {
            if (this.A) {
                throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
            }
            if (this.o.u.equals(e.a())) {
                this.o.u = this.u;
            }
            if (this.o.s == null) {
                this.o.s = a();
            }
            if (h.a(this.w, this.v) && !h.a(this.o.w, this.o.v)) {
                this.o.b(this.w, this.v);
            }
            eVar2 = new com.bumptech.glide.request.e(eVar);
            com.bumptech.glide.request.a a = a(jVar, this.p.floatValue(), this.s, eVar2);
            this.A = true;
            com.bumptech.glide.request.a a2 = this.o.a(jVar, eVar2);
            this.A = false;
            eVar2.a(a, a2);
            return eVar2;
        } else if (this.n == null) {
            return a(jVar, this.p.floatValue(), this.s, eVar);
        } else {
            eVar2 = new com.bumptech.glide.request.e(eVar);
            eVar2.a(a(jVar, this.p.floatValue(), this.s, eVar2), a(jVar, this.n.floatValue(), a(), eVar2));
            return eVar2;
        }
    }

    private com.bumptech.glide.request.a a(j<TranscodeType> jVar, float f, Priority priority, com.bumptech.glide.request.b bVar) {
        return GenericRequest.a(this.g, this.h, this.i, this.b, priority, jVar, f, this.q, this.k, this.r, this.l, this.B, this.C, this.m, bVar, this.c.b(), this.y, this.d, this.t, this.u, this.w, this.v, this.x);
    }
}
