package com.nostra13.universalimageloader.a.b.a;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.Comparator;

/* compiled from: FuzzyKeyMemoryCache */
public class a implements com.nostra13.universalimageloader.a.b.a {
    private final com.nostra13.universalimageloader.a.b.a a;
    private final Comparator<String> b;

    public a(com.nostra13.universalimageloader.a.b.a aVar, Comparator<String> comparator) {
        this.a = aVar;
        this.b = comparator;
    }

    public boolean a(String str, Bitmap bitmap) {
        synchronized (this.a) {
            for (String str2 : this.a.a()) {
                if (this.b.compare(str, str2) == 0) {
                    break;
                }
            }
            String str22 = null;
            if (str22 != null) {
                this.a.b(str22);
            }
        }
        return this.a.a(str, bitmap);
    }

    public Bitmap a(String str) {
        return this.a.a(str);
    }

    public Bitmap b(String str) {
        return this.a.b(str);
    }

    public Collection<String> a() {
        return this.a.a();
    }
}
