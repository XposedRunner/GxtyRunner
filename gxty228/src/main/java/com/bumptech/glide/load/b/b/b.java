package com.bumptech.glide.load.b.b;

import android.content.Context;
import com.bumptech.glide.load.b.c;
import com.bumptech.glide.load.b.l;
import com.bumptech.glide.load.b.m;
import java.io.InputStream;

/* compiled from: StreamByteArrayLoader */
public class b implements d<byte[]> {
    private final String a;

    /* compiled from: StreamByteArrayLoader */
    public static class a implements m<byte[], InputStream> {
        public l<byte[], InputStream> a(Context context, c cVar) {
            return new b();
        }

        public void a() {
        }
    }

    public b() {
        this("");
    }

    @Deprecated
    public b(String str) {
        this.a = str;
    }

    public com.bumptech.glide.load.a.c<InputStream> a(byte[] bArr, int i, int i2) {
        return new com.bumptech.glide.load.a.b(bArr, this.a);
    }
}
