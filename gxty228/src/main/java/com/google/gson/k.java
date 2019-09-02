package com.google.gson;

import com.google.gson.internal.g;
import com.google.gson.stream.b;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/* compiled from: JsonElement */
public abstract class k {
    public boolean g() {
        return this instanceof h;
    }

    public boolean h() {
        return this instanceof m;
    }

    public boolean i() {
        return this instanceof n;
    }

    public boolean j() {
        return this instanceof l;
    }

    public m k() {
        if (h()) {
            return (m) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public h l() {
        if (g()) {
            return (h) this;
        }
        throw new IllegalStateException("Not a JSON Array: " + this);
    }

    public n m() {
        if (i()) {
            return (n) this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    public boolean f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    Boolean n() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            Writer stringWriter = new StringWriter();
            b bVar = new b(stringWriter);
            bVar.b(true);
            g.a(this, bVar);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
