package com.google.gson;

import com.google.gson.internal.a;
import java.lang.reflect.Field;

/* compiled from: FieldAttributes */
public final class c {
    private final Field a;

    public c(Field field) {
        a.a((Object) field);
        this.a = field;
    }

    public String a() {
        return this.a.getName();
    }
}
