package com.bumptech.glide.load.resource.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.bumptech.glide.e;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.b.g;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

/* compiled from: GifFrameLoader */
class f {
    private final b a;
    private final com.bumptech.glide.b.a b;
    private final Handler c;
    private boolean d;
    private boolean e;
    private com.bumptech.glide.c<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> f;
    private a g;
    private boolean h;

    /* compiled from: GifFrameLoader */
    public interface b {
        void b(int i);
    }

    /* compiled from: GifFrameLoader */
    static class a extends g<Bitmap> {
        private final Handler a;
        private final int b;
        private final long c;
        private Bitmap d;

        public a(Handler handler, int i, long j) {
            this.a = handler;
            this.b = i;
            this.c = j;
        }

        public Bitmap a() {
            return this.d;
        }

        public void a(Bitmap bitmap, com.bumptech.glide.request.a.c<? super Bitmap> cVar) {
            this.d = bitmap;
            this.a.sendMessageAtTime(this.a.obtainMessage(1, this), this.c);
        }
    }

    /* compiled from: GifFrameLoader */
    private class c implements Callback {
        final /* synthetic */ f a;

        private c(f fVar) {
            this.a = fVar;
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                this.a.a((a) message.obj);
                return true;
            }
            if (message.what == 2) {
                e.a((a) message.obj);
            }
            return false;
        }
    }

    /* compiled from: GifFrameLoader */
    static class d implements com.bumptech.glide.load.b {
        private final UUID a;

        public d() {
            this(UUID.randomUUID());
        }

        d(UUID uuid) {
            this.a = uuid;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return ((d) obj).a.equals(this.a);
            }
            return false;
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    public f(Context context, b bVar, com.bumptech.glide.b.a aVar, int i, int i2) {
        this(bVar, aVar, null, a(context, aVar, i, i2, e.a(context).a()));
    }

    f(b bVar, com.bumptech.glide.b.a aVar, Handler handler, com.bumptech.glide.c<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> cVar) {
        this.d = false;
        this.e = false;
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper(), new c());
        }
        this.a = bVar;
        this.b = aVar;
        this.c = handler;
        this.f = cVar;
    }

    public void a(com.bumptech.glide.load.f<Bitmap> fVar) {
        if (fVar == null) {
            throw new NullPointerException("Transformation must not be null");
        }
        this.f = this.f.b(fVar);
    }

    public void a() {
        if (!this.d) {
            this.d = true;
            this.h = false;
            e();
        }
    }

    public void b() {
        this.d = false;
    }

    public void c() {
        b();
        if (this.g != null) {
            e.a(this.g);
            this.g = null;
        }
        this.h = true;
    }

    public Bitmap d() {
        return this.g != null ? this.g.a() : null;
    }

    private void e() {
        if (this.d && !this.e) {
            this.e = true;
            long uptimeMillis = SystemClock.uptimeMillis() + ((long) this.b.b());
            this.b.a();
            this.f.b(new d()).a(new a(this.c, this.b.d(), uptimeMillis));
        }
    }

    void a(a aVar) {
        if (this.h) {
            this.c.obtainMessage(2, aVar).sendToTarget();
            return;
        }
        a aVar2 = this.g;
        this.g = aVar;
        this.a.b(aVar.b);
        if (aVar2 != null) {
            this.c.obtainMessage(2, aVar2).sendToTarget();
        }
        this.e = false;
        e();
    }

    private static com.bumptech.glide.c<com.bumptech.glide.b.a, com.bumptech.glide.b.a, Bitmap, Bitmap> a(Context context, com.bumptech.glide.b.a aVar, int i, int i2, com.bumptech.glide.load.engine.a.c cVar) {
        com.bumptech.glide.load.d hVar = new h(cVar);
        l gVar = new g();
        return e.b(context).a(gVar, com.bumptech.glide.b.a.class).a((Object) aVar).a(Bitmap.class).b(com.bumptech.glide.load.resource.a.b()).b(hVar).b(true).b(DiskCacheStrategy.NONE).b(i, i2);
    }
}
