package com.bumptech.glide.load.engine.b;

import android.content.Context;
import com.bumptech.glide.load.engine.b.d.a;
import java.io.File;

/* compiled from: InternalCacheDiskCacheFactory */
public final class f extends d {

    /* compiled from: InternalCacheDiskCacheFactory */
    /* renamed from: com.bumptech.glide.load.engine.b.f$1 */
    class AnonymousClass1 implements a {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;

        AnonymousClass1(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        public File a() {
            File cacheDir = this.a.getCacheDir();
            if (cacheDir == null) {
                return null;
            }
            return this.b != null ? new File(cacheDir, this.b) : cacheDir;
        }
    }

    public f(Context context) {
        this(context, "image_manager_disk_cache", 262144000);
    }

    public f(Context context, String str, int i) {
        super(new AnonymousClass1(context, str), i);
    }
}
