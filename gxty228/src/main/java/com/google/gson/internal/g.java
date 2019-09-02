package com.google.gson.internal;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.a.n;
import com.google.gson.k;
import com.google.gson.l;
import com.google.gson.stream.b;
import java.io.IOException;
import java.io.Writer;

/* compiled from: Streams */
public final class g {

    /* compiled from: Streams */
    private static final class a extends Writer {
        private final Appendable a;
        private final a b = new a();

        /* compiled from: Streams */
        static class a implements CharSequence {
            char[] a;

            a() {
            }

            public int length() {
                return this.a.length;
            }

            public char charAt(int i) {
                return this.a[i];
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.a, i, i2 - i);
            }
        }

        a(Appendable appendable) {
            this.a = appendable;
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            this.b.a = cArr;
            this.a.append(this.b, i, i + i2);
        }

        public void write(int i) throws IOException {
            this.a.append((char) i);
        }

        public void flush() {
        }

        public void close() {
        }
    }

    public static k a(com.google.gson.stream.a aVar) throws JsonParseException {
        Object obj = 1;
        try {
            aVar.f();
            obj = null;
            return (k) n.X.b(aVar);
        } catch (Throwable e) {
            if (obj != null) {
                return l.a;
            }
            throw new JsonSyntaxException(e);
        } catch (Throwable e2) {
            throw new JsonSyntaxException(e2);
        } catch (Throwable e22) {
            throw new JsonIOException(e22);
        } catch (Throwable e222) {
            throw new JsonSyntaxException(e222);
        }
    }

    public static void a(k kVar, b bVar) throws IOException {
        n.X.a(bVar, kVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }
}
