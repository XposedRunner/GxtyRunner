package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.b.c;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.f;
import com.bumptech.glide.request.a.d;
import com.bumptech.glide.request.b.h;
import com.bumptech.glide.request.b.j;
import java.util.Queue;

public final class GenericRequest<A, T, Z, R> implements a, h, d {
    private static final Queue<GenericRequest<?, ?, ?, ?>> a = com.bumptech.glide.g.h.a(0);
    private i<?> A;
    private c B;
    private long C;
    private Status D;
    private final String b = String.valueOf(hashCode());
    private b c;
    private Drawable d;
    private int e;
    private int f;
    private int g;
    private Context h;
    private f<Z> i;
    private com.bumptech.glide.e.f<A, T, Z, R> j;
    private b k;
    private A l;
    private Class<R> m;
    private boolean n;
    private Priority o;
    private j<R> p;
    private c<? super A, R> q;
    private float r;
    private com.bumptech.glide.load.engine.b s;
    private d<R> t;
    private int u;
    private int v;
    private DiskCacheStrategy w;
    private Drawable x;
    private Drawable y;
    private boolean z;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CANCELLED,
        CLEARED,
        PAUSED
    }

    public static <A, T, Z, R> GenericRequest<A, T, Z, R> a(com.bumptech.glide.e.f<A, T, Z, R> fVar, A a, b bVar, Context context, Priority priority, j<R> jVar, float f, Drawable drawable, int i, Drawable drawable2, int i2, Drawable drawable3, int i3, c<? super A, R> cVar, b bVar2, com.bumptech.glide.load.engine.b bVar3, f<Z> fVar2, Class<R> cls, boolean z, d<R> dVar, int i4, int i5, DiskCacheStrategy diskCacheStrategy) {
        GenericRequest<A, T, Z, R> genericRequest = (GenericRequest) a.poll();
        if (genericRequest == null) {
            genericRequest = new GenericRequest();
        }
        genericRequest.b(fVar, a, bVar, context, priority, jVar, f, drawable, i, drawable2, i2, drawable3, i3, cVar, bVar2, bVar3, fVar2, cls, z, dVar, i4, i5, diskCacheStrategy);
        return genericRequest;
    }

    private GenericRequest() {
    }

    public void a() {
        this.j = null;
        this.l = null;
        this.h = null;
        this.p = null;
        this.x = null;
        this.y = null;
        this.d = null;
        this.q = null;
        this.k = null;
        this.i = null;
        this.t = null;
        this.z = false;
        this.B = null;
        a.offer(this);
    }

    private void b(com.bumptech.glide.e.f<A, T, Z, R> fVar, A a, b bVar, Context context, Priority priority, j<R> jVar, float f, Drawable drawable, int i, Drawable drawable2, int i2, Drawable drawable3, int i3, c<? super A, R> cVar, b bVar2, com.bumptech.glide.load.engine.b bVar3, f<Z> fVar2, Class<R> cls, boolean z, d<R> dVar, int i4, int i5, DiskCacheStrategy diskCacheStrategy) {
        this.j = fVar;
        this.l = a;
        this.c = bVar;
        this.d = drawable3;
        this.e = i3;
        this.h = context.getApplicationContext();
        this.o = priority;
        this.p = jVar;
        this.r = f;
        this.x = drawable;
        this.f = i;
        this.y = drawable2;
        this.g = i2;
        this.q = cVar;
        this.k = bVar2;
        this.s = bVar3;
        this.i = fVar2;
        this.m = cls;
        this.n = z;
        this.t = dVar;
        this.u = i4;
        this.v = i5;
        this.w = diskCacheStrategy;
        this.D = Status.PENDING;
        if (a != null) {
            a("ModelLoader", fVar.e(), "try .using(ModelLoader)");
            a("Transcoder", fVar.f(), "try .as*(Class).transcode(ResourceTranscoder)");
            a("Transformation", fVar2, "try .transform(UnitTransformation.get())");
            if (diskCacheStrategy.cacheSource()) {
                a("SourceEncoder", fVar.c(), "try .sourceEncoder(Encoder) or .diskCacheStrategy(NONE/RESULT)");
            } else {
                a("SourceDecoder", fVar.b(), "try .decoder/.imageDecoder/.videoDecoder(ResourceDecoder) or .diskCacheStrategy(ALL/SOURCE)");
            }
            if (diskCacheStrategy.cacheSource() || diskCacheStrategy.cacheResult()) {
                a("CacheDecoder", fVar.a(), "try .cacheDecoder(ResouceDecoder) or .diskCacheStrategy(NONE)");
            }
            if (diskCacheStrategy.cacheResult()) {
                a("Encoder", fVar.d(), "try .encode(ResourceEncoder) or .diskCacheStrategy(NONE/SOURCE)");
            }
        }
    }

    private static void a(String str, Object obj, String str2) {
        if (obj == null) {
            StringBuilder stringBuilder = new StringBuilder(str);
            stringBuilder.append(" must not be null");
            if (str2 != null) {
                stringBuilder.append(", ");
                stringBuilder.append(str2);
            }
            throw new NullPointerException(stringBuilder.toString());
        }
    }

    public void b() {
        this.C = com.bumptech.glide.g.d.a();
        if (this.l == null) {
            a(null);
            return;
        }
        this.D = Status.WAITING_FOR_SIZE;
        if (com.bumptech.glide.g.h.a(this.u, this.v)) {
            a(this.u, this.v);
        } else {
            this.p.a((h) this);
        }
        if (!(g() || j() || !o())) {
            this.p.c(m());
        }
        if (Log.isLoggable("GenericRequest", 2)) {
            a("finished run method in " + com.bumptech.glide.g.d.a(this.C));
        }
    }

    void c() {
        this.D = Status.CANCELLED;
        if (this.B != null) {
            this.B.a();
            this.B = null;
        }
    }

    public void d() {
        com.bumptech.glide.g.h.a();
        if (this.D != Status.CLEARED) {
            c();
            if (this.A != null) {
                b(this.A);
            }
            if (o()) {
                this.p.b(m());
            }
            this.D = Status.CLEARED;
        }
    }

    public void e() {
        d();
        this.D = Status.PAUSED;
    }

    private void b(i iVar) {
        this.s.a(iVar);
        this.A = null;
    }

    public boolean f() {
        return this.D == Status.RUNNING || this.D == Status.WAITING_FOR_SIZE;
    }

    public boolean g() {
        return this.D == Status.COMPLETE;
    }

    public boolean h() {
        return g();
    }

    public boolean i() {
        return this.D == Status.CANCELLED || this.D == Status.CLEARED;
    }

    public boolean j() {
        return this.D == Status.FAILED;
    }

    private Drawable k() {
        if (this.d == null && this.e > 0) {
            this.d = this.h.getResources().getDrawable(this.e);
        }
        return this.d;
    }

    private void b(Exception exception) {
        if (o()) {
            Drawable k = this.l == null ? k() : null;
            if (k == null) {
                k = l();
            }
            if (k == null) {
                k = m();
            }
            this.p.a(exception, k);
        }
    }

    private Drawable l() {
        if (this.y == null && this.g > 0) {
            this.y = this.h.getResources().getDrawable(this.g);
        }
        return this.y;
    }

    private Drawable m() {
        if (this.x == null && this.f > 0) {
            this.x = this.h.getResources().getDrawable(this.f);
        }
        return this.x;
    }

    public void a(int i, int i2) {
        if (Log.isLoggable("GenericRequest", 2)) {
            a("Got onSizeReady in " + com.bumptech.glide.g.d.a(this.C));
        }
        if (this.D == Status.WAITING_FOR_SIZE) {
            this.D = Status.RUNNING;
            int round = Math.round(this.r * ((float) i));
            int round2 = Math.round(this.r * ((float) i2));
            com.bumptech.glide.load.a.c a = this.j.e().a(this.l, round, round2);
            if (a == null) {
                a(new Exception("Failed to load model: '" + this.l + "'"));
                return;
            }
            com.bumptech.glide.load.resource.e.c f = this.j.f();
            if (Log.isLoggable("GenericRequest", 2)) {
                a("finished setup for calling load in " + com.bumptech.glide.g.d.a(this.C));
            }
            this.z = true;
            this.B = this.s.a(this.c, round, round2, a, this.j, this.i, f, this.o, this.n, this.w, this);
            this.z = this.A != null;
            if (Log.isLoggable("GenericRequest", 2)) {
                a("finished onSizeReady in " + com.bumptech.glide.g.d.a(this.C));
            }
        }
    }

    private boolean n() {
        return this.k == null || this.k.a(this);
    }

    private boolean o() {
        return this.k == null || this.k.b(this);
    }

    private boolean p() {
        return this.k == null || !this.k.c();
    }

    private void q() {
        if (this.k != null) {
            this.k.c(this);
        }
    }

    public void a(i<?> iVar) {
        if (iVar == null) {
            a(new Exception("Expected to receive a Resource<R> with an object of " + this.m + " inside, but instead got null."));
            return;
        }
        Object b = iVar.b();
        if (b == null || !this.m.isAssignableFrom(b.getClass())) {
            b((i) iVar);
            a(new Exception("Expected to receive an object of " + this.m + " but instead got " + (b != null ? b.getClass() : "") + "{" + b + "}" + " inside Resource{" + iVar + "}." + (b != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.")));
        } else if (n()) {
            a((i) iVar, b);
        } else {
            b((i) iVar);
            this.D = Status.COMPLETE;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.bumptech.glide.load.engine.i<?> r7, R r8) {
        /*
        r6 = this;
        r5 = r6.p();
        r0 = com.bumptech.glide.request.GenericRequest.Status.COMPLETE;
        r6.D = r0;
        r6.A = r7;
        r0 = r6.q;
        if (r0 == 0) goto L_0x001d;
    L_0x000e:
        r0 = r6.q;
        r2 = r6.l;
        r3 = r6.p;
        r4 = r6.z;
        r1 = r8;
        r0 = r0.a(r1, r2, r3, r4, r5);
        if (r0 != 0) goto L_0x002a;
    L_0x001d:
        r0 = r6.t;
        r1 = r6.z;
        r0 = r0.a(r1, r5);
        r1 = r6.p;
        r1.a(r8, r0);
    L_0x002a:
        r6.q();
        r0 = "GenericRequest";
        r1 = 2;
        r0 = android.util.Log.isLoggable(r0, r1);
        if (r0 == 0) goto L_0x0070;
    L_0x0036:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Resource ready in ";
        r0 = r0.append(r1);
        r2 = r6.C;
        r2 = com.bumptech.glide.g.d.a(r2);
        r0 = r0.append(r2);
        r1 = " size: ";
        r0 = r0.append(r1);
        r1 = r7.c();
        r2 = (double) r1;
        r4 = 4517110426252607488; // 0x3eb0000000000000 float:0.0 double:9.5367431640625E-7;
        r2 = r2 * r4;
        r0 = r0.append(r2);
        r1 = " fromCache: ";
        r0 = r0.append(r1);
        r1 = r6.z;
        r0 = r0.append(r1);
        r0 = r0.toString();
        r6.a(r0);
    L_0x0070:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.GenericRequest.a(com.bumptech.glide.load.engine.i, java.lang.Object):void");
    }

    public void a(Exception exception) {
        if (Log.isLoggable("GenericRequest", 3)) {
            Log.d("GenericRequest", "load failed", exception);
        }
        this.D = Status.FAILED;
        if (this.q == null || !this.q.a(exception, this.l, this.p, p())) {
            b(exception);
        }
    }

    private void a(String str) {
        Log.v("GenericRequest", str + " this: " + this.b);
    }
}
