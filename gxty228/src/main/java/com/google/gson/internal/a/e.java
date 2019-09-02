package com.google.gson.internal.a;

import com.google.gson.h;
import com.google.gson.l;
import com.google.gson.m;
import com.google.gson.n;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.a;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;

/* compiled from: JsonTreeReader */
public final class e extends a {
    private static final Reader b = new Reader() {
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final Object c = new Object();
    private Object[] d;
    private int e;
    private String[] f;
    private int[] g;

    public void a() throws IOException {
        a(JsonToken.BEGIN_ARRAY);
        a(((h) t()).iterator());
        this.g[this.e - 1] = 0;
    }

    public void b() throws IOException {
        a(JsonToken.END_ARRAY);
        u();
        u();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public void c() throws IOException {
        a(JsonToken.BEGIN_OBJECT);
        a(((m) t()).o().iterator());
    }

    public void d() throws IOException {
        a(JsonToken.END_OBJECT);
        u();
        u();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public boolean e() throws IOException {
        JsonToken f = f();
        return (f == JsonToken.END_OBJECT || f == JsonToken.END_ARRAY) ? false : true;
    }

    public JsonToken f() throws IOException {
        if (this.e == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object t = t();
        if (t instanceof Iterator) {
            boolean z = this.d[this.e - 2] instanceof m;
            Iterator it = (Iterator) t;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            } else {
                if (z) {
                    return JsonToken.NAME;
                }
                a(it.next());
                return f();
            }
        } else if (t instanceof m) {
            return JsonToken.BEGIN_OBJECT;
        } else {
            if (t instanceof h) {
                return JsonToken.BEGIN_ARRAY;
            }
            if (t instanceof n) {
                n nVar = (n) t;
                if (nVar.q()) {
                    return JsonToken.STRING;
                }
                if (nVar.o()) {
                    return JsonToken.BOOLEAN;
                }
                if (nVar.p()) {
                    return JsonToken.NUMBER;
                }
                throw new AssertionError();
            } else if (t instanceof l) {
                return JsonToken.NULL;
            } else {
                if (t == c) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    private Object t() {
        return this.d[this.e - 1];
    }

    private Object u() {
        Object[] objArr = this.d;
        int i = this.e - 1;
        this.e = i;
        Object obj = objArr[i];
        this.d[this.e] = null;
        return obj;
    }

    private void a(JsonToken jsonToken) throws IOException {
        if (f() != jsonToken) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + f() + v());
        }
    }

    public String g() throws IOException {
        a(JsonToken.NAME);
        Entry entry = (Entry) ((Iterator) t()).next();
        String str = (String) entry.getKey();
        this.f[this.e - 1] = str;
        a(entry.getValue());
        return str;
    }

    public String h() throws IOException {
        JsonToken f = f();
        if (f == JsonToken.STRING || f == JsonToken.NUMBER) {
            String b = ((n) u()).b();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return b;
        }
        throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + f + v());
    }

    public boolean i() throws IOException {
        a(JsonToken.BOOLEAN);
        boolean f = ((n) u()).f();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
        return f;
    }

    public void j() throws IOException {
        a(JsonToken.NULL);
        u();
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public double k() throws IOException {
        JsonToken f = f();
        if (f == JsonToken.NUMBER || f == JsonToken.STRING) {
            double c = ((n) t()).c();
            if (q() || !(Double.isNaN(c) || Double.isInfinite(c))) {
                u();
                if (this.e > 0) {
                    int[] iArr = this.g;
                    int i = this.e - 1;
                    iArr[i] = iArr[i] + 1;
                }
                return c;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + c);
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + f + v());
    }

    public long l() throws IOException {
        JsonToken f = f();
        if (f == JsonToken.NUMBER || f == JsonToken.STRING) {
            long d = ((n) t()).d();
            u();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return d;
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + f + v());
    }

    public int m() throws IOException {
        JsonToken f = f();
        if (f == JsonToken.NUMBER || f == JsonToken.STRING) {
            int e = ((n) t()).e();
            u();
            if (this.e > 0) {
                int[] iArr = this.g;
                int i = this.e - 1;
                iArr[i] = iArr[i] + 1;
            }
            return e;
        }
        throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + f + v());
    }

    public void close() throws IOException {
        this.d = new Object[]{c};
        this.e = 1;
    }

    public void n() throws IOException {
        if (f() == JsonToken.NAME) {
            g();
            this.f[this.e - 2] = "null";
        } else {
            u();
            if (this.e > 0) {
                this.f[this.e - 1] = "null";
            }
        }
        if (this.e > 0) {
            int[] iArr = this.g;
            int i = this.e - 1;
            iArr[i] = iArr[i] + 1;
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void o() throws IOException {
        a(JsonToken.NAME);
        Entry entry = (Entry) ((Iterator) t()).next();
        a(entry.getValue());
        a(new n((String) entry.getKey()));
    }

    private void a(Object obj) {
        if (this.e == this.d.length) {
            Object obj2 = new Object[(this.e * 2)];
            Object obj3 = new int[(this.e * 2)];
            Object obj4 = new String[(this.e * 2)];
            System.arraycopy(this.d, 0, obj2, 0, this.e);
            System.arraycopy(this.g, 0, obj3, 0, this.e);
            System.arraycopy(this.f, 0, obj4, 0, this.e);
            this.d = obj2;
            this.g = obj3;
            this.f = obj4;
        }
        Object[] objArr = this.d;
        int i = this.e;
        this.e = i + 1;
        objArr[i] = obj;
    }

    public String p() {
        StringBuilder append = new StringBuilder().append('$');
        int i = 0;
        while (i < this.e) {
            if (this.d[i] instanceof h) {
                i++;
                if (this.d[i] instanceof Iterator) {
                    append.append('[').append(this.g[i]).append(']');
                }
            } else if (this.d[i] instanceof m) {
                i++;
                if (this.d[i] instanceof Iterator) {
                    append.append('.');
                    if (this.f[i] != null) {
                        append.append(this.f[i]);
                    }
                }
            }
            i++;
        }
        return append.toString();
    }

    private String v() {
        return " at path " + p();
    }
}
