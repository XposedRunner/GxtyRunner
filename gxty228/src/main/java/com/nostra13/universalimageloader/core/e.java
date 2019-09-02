package com.nostra13.universalimageloader.core;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.nostra13.universalimageloader.b.d;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* compiled from: ImageLoaderConfiguration */
public final class e {
    final Resources a;
    final int b;
    final int c;
    final int d;
    final int e;
    final com.nostra13.universalimageloader.core.e.a f;
    final Executor g;
    final Executor h;
    final boolean i;
    final boolean j;
    final int k;
    final int l;
    final QueueProcessingType m;
    final com.nostra13.universalimageloader.a.b.a n;
    final com.nostra13.universalimageloader.a.a.a o;
    final ImageDownloader p;
    final com.nostra13.universalimageloader.core.a.b q;
    final c r;
    final ImageDownloader s;
    final ImageDownloader t;

    /* compiled from: ImageLoaderConfiguration */
    public static class a {
        public static final QueueProcessingType a = QueueProcessingType.FIFO;
        private Context b;
        private int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = 0;
        private com.nostra13.universalimageloader.core.e.a g = null;
        private Executor h = null;
        private Executor i = null;
        private boolean j = false;
        private boolean k = false;
        private int l = 3;
        private int m = 3;
        private boolean n = false;
        private QueueProcessingType o = a;
        private int p = 0;
        private long q = 0;
        private int r = 0;
        private com.nostra13.universalimageloader.a.b.a s = null;
        private com.nostra13.universalimageloader.a.a.a t = null;
        private com.nostra13.universalimageloader.a.a.b.a u = null;
        private ImageDownloader v = null;
        private com.nostra13.universalimageloader.core.a.b w;
        private c x = null;
        private boolean y = false;

        public a(Context context) {
            this.b = context.getApplicationContext();
        }

        public e a() {
            b();
            return new e();
        }

        private void b() {
            if (this.h == null) {
                this.h = a.a(this.l, this.m, this.o);
            } else {
                this.j = true;
            }
            if (this.i == null) {
                this.i = a.a(this.l, this.m, this.o);
            } else {
                this.k = true;
            }
            if (this.t == null) {
                if (this.u == null) {
                    this.u = a.b();
                }
                this.t = a.a(this.b, this.u, this.q, this.r);
            }
            if (this.s == null) {
                this.s = a.a(this.b, this.p);
            }
            if (this.n) {
                this.s = new com.nostra13.universalimageloader.a.b.a.a(this.s, d.a());
            }
            if (this.v == null) {
                this.v = a.a(this.b);
            }
            if (this.w == null) {
                this.w = a.a(this.y);
            }
            if (this.x == null) {
                this.x = c.t();
            }
        }
    }

    /* compiled from: ImageLoaderConfiguration */
    private static class b implements ImageDownloader {
        private final ImageDownloader a;

        public b(ImageDownloader imageDownloader) {
            this.a = imageDownloader;
        }

        public InputStream a(String str, Object obj) throws IOException {
            switch (Scheme.ofUri(str)) {
                case HTTP:
                case HTTPS:
                    throw new IllegalStateException();
                default:
                    return this.a.a(str, obj);
            }
        }
    }

    /* compiled from: ImageLoaderConfiguration */
    private static class c implements ImageDownloader {
        private final ImageDownloader a;

        public c(ImageDownloader imageDownloader) {
            this.a = imageDownloader;
        }

        public InputStream a(String str, Object obj) throws IOException {
            InputStream a = this.a.a(str, obj);
            switch (Scheme.ofUri(str)) {
                case HTTP:
                case HTTPS:
                    return new com.nostra13.universalimageloader.core.assist.b(a);
                default:
                    return a;
            }
        }
    }

    private e(a aVar) {
        this.a = aVar.b.getResources();
        this.b = aVar.c;
        this.c = aVar.d;
        this.d = aVar.e;
        this.e = aVar.f;
        this.f = aVar.g;
        this.g = aVar.h;
        this.h = aVar.i;
        this.k = aVar.l;
        this.l = aVar.m;
        this.m = aVar.o;
        this.o = aVar.t;
        this.n = aVar.s;
        this.r = aVar.x;
        this.p = aVar.v;
        this.q = aVar.w;
        this.i = aVar.j;
        this.j = aVar.k;
        this.s = new b(this.p);
        this.t = new c(this.p);
        com.nostra13.universalimageloader.b.c.a(aVar.y);
    }

    public static e a(Context context) {
        return new a(context).a();
    }

    com.nostra13.universalimageloader.core.assist.c a() {
        DisplayMetrics displayMetrics = this.a.getDisplayMetrics();
        int i = this.b;
        if (i <= 0) {
            i = displayMetrics.widthPixels;
        }
        int i2 = this.c;
        if (i2 <= 0) {
            i2 = displayMetrics.heightPixels;
        }
        return new com.nostra13.universalimageloader.core.assist.c(i, i2);
    }
}
