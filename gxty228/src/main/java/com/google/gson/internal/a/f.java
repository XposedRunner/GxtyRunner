package com.google.gson.internal.a;

import com.google.gson.h;
import com.google.gson.k;
import com.google.gson.l;
import com.google.gson.m;
import com.google.gson.n;
import com.google.gson.stream.b;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: JsonTreeWriter */
public final class f extends b {
    private static final Writer a = new Writer() {
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void flush() throws IOException {
            throw new AssertionError();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final n b = new n("closed");
    private final List<k> c = new ArrayList();
    private String d;
    private k e = l.a;

    public f() {
        super(a);
    }

    public k a() {
        if (this.c.isEmpty()) {
            return this.e;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.c);
    }

    private k j() {
        return (k) this.c.get(this.c.size() - 1);
    }

    private void a(k kVar) {
        if (this.d != null) {
            if (!kVar.j() || i()) {
                ((m) j()).a(this.d, kVar);
            }
            this.d = null;
        } else if (this.c.isEmpty()) {
            this.e = kVar;
        } else {
            k j = j();
            if (j instanceof h) {
                ((h) j).a(kVar);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public b b() throws IOException {
        k hVar = new h();
        a(hVar);
        this.c.add(hVar);
        return this;
    }

    public b c() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof h) {
            this.c.remove(this.c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public b d() throws IOException {
        k mVar = new m();
        a(mVar);
        this.c.add(mVar);
        return this;
    }

    public b e() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof m) {
            this.c.remove(this.c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public b a(String str) throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof m) {
            this.d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public b b(String str) throws IOException {
        if (str == null) {
            return f();
        }
        a(new n(str));
        return this;
    }

    public b f() throws IOException {
        a(l.a);
        return this;
    }

    public b a(boolean z) throws IOException {
        a(new n(Boolean.valueOf(z)));
        return this;
    }

    public b a(Boolean bool) throws IOException {
        if (bool == null) {
            return f();
        }
        a(new n(bool));
        return this;
    }

    public b a(long j) throws IOException {
        a(new n(Long.valueOf(j)));
        return this;
    }

    public b a(Number number) throws IOException {
        if (number == null) {
            return f();
        }
        if (!g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        a(new n(number));
        return this;
    }

    public void flush() throws IOException {
    }

    public void close() throws IOException {
        if (this.c.isEmpty()) {
            this.c.add(b);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
