package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.i;
import java.util.Arrays;
import java.util.Collection;

/* compiled from: MultiTransformation */
public class c<T> implements f<T> {
    private final Collection<? extends f<T>> a;
    private String b;

    @SafeVarargs
    public c(f<T>... fVarArr) {
        if (fVarArr.length < 1) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.a = Arrays.asList(fVarArr);
    }

    public i<T> a(i<T> iVar, int i, int i2) {
        i<T> iVar2 = iVar;
        for (f a : this.a) {
            i<T> a2 = a.a(iVar2, i, i2);
            if (!(iVar2 == null || iVar2.equals(iVar) || iVar2.equals(a2))) {
                iVar2.d();
            }
            iVar2 = a2;
        }
        return iVar2;
    }

    public String a() {
        if (this.b == null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (f a : this.a) {
                stringBuilder.append(a.a());
            }
            this.b = stringBuilder.toString();
        }
        return this.b;
    }
}
