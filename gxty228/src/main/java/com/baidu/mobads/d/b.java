package com.baidu.mobads.d;

import android.graphics.Bitmap;
import android.util.LruCache;

class b extends LruCache<String, Bitmap> {
    final /* synthetic */ a a;

    b(a aVar, int i) {
        this.a = aVar;
        super(i);
    }

    protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
        return a((String) obj, (Bitmap) obj2);
    }

    protected int a(String str, Bitmap bitmap) {
        return bitmap == null ? 0 : bitmap.getByteCount();
    }
}
