package com.google.gson.internal.a;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.a.c;
import com.google.gson.e;
import com.google.gson.h;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.k;
import com.google.gson.l;
import com.google.gson.m;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: TypeAdapters */
public final class n {
    public static final q<String> A = new q<String>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public String a(com.google.gson.stream.a aVar) throws IOException {
            JsonToken f = aVar.f();
            if (f == JsonToken.NULL) {
                aVar.j();
                return null;
            } else if (f == JsonToken.BOOLEAN) {
                return Boolean.toString(aVar.i());
            } else {
                return aVar.h();
            }
        }

        public void a(b bVar, String str) throws IOException {
            bVar.b(str);
        }
    };
    public static final q<BigDecimal> B = new q<BigDecimal>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public BigDecimal a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return new BigDecimal(aVar.h());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void a(b bVar, BigDecimal bigDecimal) throws IOException {
            bVar.a((Number) bigDecimal);
        }
    };
    public static final q<BigInteger> C = new q<BigInteger>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public BigInteger a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return new BigInteger(aVar.h());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void a(b bVar, BigInteger bigInteger) throws IOException {
            bVar.a((Number) bigInteger);
        }
    };
    public static final r D = a(String.class, A);
    public static final q<StringBuilder> E = new q<StringBuilder>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public StringBuilder a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return new StringBuilder(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, StringBuilder stringBuilder) throws IOException {
            bVar.b(stringBuilder == null ? null : stringBuilder.toString());
        }
    };
    public static final r F = a(StringBuilder.class, E);
    public static final q<StringBuffer> G = new q<StringBuffer>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public StringBuffer a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return new StringBuffer(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, StringBuffer stringBuffer) throws IOException {
            bVar.b(stringBuffer == null ? null : stringBuffer.toString());
        }
    };
    public static final r H = a(StringBuffer.class, G);
    public static final q<URL> I = new q<URL>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public URL a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if ("null".equals(h)) {
                return null;
            }
            return new URL(h);
        }

        public void a(b bVar, URL url) throws IOException {
            bVar.b(url == null ? null : url.toExternalForm());
        }
    };
    public static final r J = a(URL.class, I);
    public static final q<URI> K = new q<URI>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public URI a(com.google.gson.stream.a aVar) throws IOException {
            URI uri = null;
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
            } else {
                try {
                    String h = aVar.h();
                    if (!"null".equals(h)) {
                        uri = new URI(h);
                    }
                } catch (Throwable e) {
                    throw new JsonIOException(e);
                }
            }
            return uri;
        }

        public void a(b bVar, URI uri) throws IOException {
            bVar.b(uri == null ? null : uri.toASCIIString());
        }
    };
    public static final r L = a(URI.class, K);
    public static final q<InetAddress> M = new q<InetAddress>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public InetAddress a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return InetAddress.getByName(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, InetAddress inetAddress) throws IOException {
            bVar.b(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };
    public static final r N = b(InetAddress.class, M);
    public static final q<UUID> O = new q<UUID>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public UUID a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return UUID.fromString(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, UUID uuid) throws IOException {
            bVar.b(uuid == null ? null : uuid.toString());
        }
    };
    public static final r P = a(UUID.class, O);
    public static final q<Currency> Q = new q<Currency>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Currency a(com.google.gson.stream.a aVar) throws IOException {
            return Currency.getInstance(aVar.h());
        }

        public void a(b bVar, Currency currency) throws IOException {
            bVar.b(currency.getCurrencyCode());
        }
    }.a();
    public static final r R = a(Currency.class, Q);
    public static final r S = new r() {
        public <T> q<T> a(e eVar, com.google.gson.b.a<T> aVar) {
            if (aVar.a() != Timestamp.class) {
                return null;
            }
            final q a = eVar.a(Date.class);
            return new q<Timestamp>(this) {
                final /* synthetic */ AnonymousClass19 b;

                public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
                    return a(aVar);
                }

                public Timestamp a(com.google.gson.stream.a aVar) throws IOException {
                    Date date = (Date) a.b(aVar);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                public void a(b bVar, Timestamp timestamp) throws IOException {
                    a.a(bVar, timestamp);
                }
            };
        }
    };
    public static final q<Calendar> T = new q<Calendar>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Calendar a(com.google.gson.stream.a aVar) throws IOException {
            int i = 0;
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            aVar.c();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.f() != JsonToken.END_OBJECT) {
                String g = aVar.g();
                int m = aVar.m();
                if ("year".equals(g)) {
                    i6 = m;
                } else if ("month".equals(g)) {
                    i5 = m;
                } else if ("dayOfMonth".equals(g)) {
                    i4 = m;
                } else if ("hourOfDay".equals(g)) {
                    i3 = m;
                } else if ("minute".equals(g)) {
                    i2 = m;
                } else if ("second".equals(g)) {
                    i = m;
                }
            }
            aVar.d();
            return new GregorianCalendar(i6, i5, i4, i3, i2, i);
        }

        public void a(b bVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                bVar.f();
                return;
            }
            bVar.d();
            bVar.a("year");
            bVar.a((long) calendar.get(1));
            bVar.a("month");
            bVar.a((long) calendar.get(2));
            bVar.a("dayOfMonth");
            bVar.a((long) calendar.get(5));
            bVar.a("hourOfDay");
            bVar.a((long) calendar.get(11));
            bVar.a("minute");
            bVar.a((long) calendar.get(12));
            bVar.a("second");
            bVar.a((long) calendar.get(13));
            bVar.e();
        }
    };
    public static final r U = b(Calendar.class, GregorianCalendar.class, T);
    public static final q<Locale> V = new q<Locale>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Locale a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            String nextToken;
            String nextToken2;
            String nextToken3;
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.h(), "_");
            if (stringTokenizer.hasMoreElements()) {
                nextToken = stringTokenizer.nextToken();
            } else {
                nextToken = null;
            }
            if (stringTokenizer.hasMoreElements()) {
                nextToken2 = stringTokenizer.nextToken();
            } else {
                nextToken2 = null;
            }
            if (stringTokenizer.hasMoreElements()) {
                nextToken3 = stringTokenizer.nextToken();
            } else {
                nextToken3 = null;
            }
            if (nextToken2 == null && nextToken3 == null) {
                return new Locale(nextToken);
            }
            if (nextToken3 == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, nextToken3);
        }

        public void a(b bVar, Locale locale) throws IOException {
            bVar.b(locale == null ? null : locale.toString());
        }
    };
    public static final r W = a(Locale.class, V);
    public static final q<k> X = new q<k>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public k a(com.google.gson.stream.a aVar) throws IOException {
            k hVar;
            switch (aVar.f()) {
                case NUMBER:
                    return new com.google.gson.n(new LazilyParsedNumber(aVar.h()));
                case BOOLEAN:
                    return new com.google.gson.n(Boolean.valueOf(aVar.i()));
                case STRING:
                    return new com.google.gson.n(aVar.h());
                case NULL:
                    aVar.j();
                    return l.a;
                case BEGIN_ARRAY:
                    hVar = new h();
                    aVar.a();
                    while (aVar.e()) {
                        hVar.a(a(aVar));
                    }
                    aVar.b();
                    return hVar;
                case BEGIN_OBJECT:
                    hVar = new m();
                    aVar.c();
                    while (aVar.e()) {
                        hVar.a(aVar.g(), a(aVar));
                    }
                    aVar.d();
                    return hVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public void a(b bVar, k kVar) throws IOException {
            if (kVar == null || kVar.j()) {
                bVar.f();
            } else if (kVar.i()) {
                com.google.gson.n m = kVar.m();
                if (m.p()) {
                    bVar.a(m.a());
                } else if (m.o()) {
                    bVar.a(m.f());
                } else {
                    bVar.b(m.b());
                }
            } else if (kVar.g()) {
                bVar.b();
                Iterator it = kVar.l().iterator();
                while (it.hasNext()) {
                    a(bVar, (k) it.next());
                }
                bVar.c();
            } else if (kVar.h()) {
                bVar.d();
                for (Entry entry : kVar.k().o()) {
                    bVar.a((String) entry.getKey());
                    a(bVar, (k) entry.getValue());
                }
                bVar.e();
            } else {
                throw new IllegalArgumentException("Couldn't write " + kVar.getClass());
            }
        }
    };
    public static final r Y = b(k.class, X);
    public static final r Z = new r() {
        public <T> q<T> a(e eVar, com.google.gson.b.a<T> aVar) {
            Class a = aVar.a();
            if (!Enum.class.isAssignableFrom(a) || a == Enum.class) {
                return null;
            }
            if (!a.isEnum()) {
                a = a.getSuperclass();
            }
            return new a(a);
        }
    };
    public static final q<Class> a = new q<Class>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public void a(b bVar, Class cls) throws IOException {
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        public Class a(com.google.gson.stream.a aVar) throws IOException {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }.a();
    public static final r b = a(Class.class, a);
    public static final q<BitSet> c = new q<BitSet>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public BitSet a(com.google.gson.stream.a aVar) throws IOException {
            BitSet bitSet = new BitSet();
            aVar.a();
            JsonToken f = aVar.f();
            int i = 0;
            while (f != JsonToken.END_ARRAY) {
                boolean z;
                switch (f) {
                    case NUMBER:
                        if (aVar.m() == 0) {
                            z = false;
                            break;
                        }
                        z = true;
                        break;
                    case BOOLEAN:
                        z = aVar.i();
                        break;
                    case STRING:
                        String h = aVar.h();
                        try {
                            if (Integer.parseInt(h) == 0) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        } catch (NumberFormatException e) {
                            throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + h);
                        }
                    default:
                        throw new JsonSyntaxException("Invalid bitset value type: " + f);
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                f = aVar.f();
            }
            aVar.b();
            return bitSet;
        }

        public void a(b bVar, BitSet bitSet) throws IOException {
            bVar.b();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                int i2;
                if (bitSet.get(i)) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                bVar.a((long) i2);
            }
            bVar.c();
        }
    }.a();
    public static final r d = a(BitSet.class, c);
    public static final q<Boolean> e = new q<Boolean>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Boolean a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            } else if (aVar.f() == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(aVar.h()));
            } else {
                return Boolean.valueOf(aVar.i());
            }
        }

        public void a(b bVar, Boolean bool) throws IOException {
            bVar.a(bool);
        }
    };
    public static final q<Boolean> f = new q<Boolean>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Boolean a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return Boolean.valueOf(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, Boolean bool) throws IOException {
            bVar.b(bool == null ? "null" : bool.toString());
        }
    };
    public static final r g = a(Boolean.TYPE, Boolean.class, e);
    public static final q<Number> h = new q<Number>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.m());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final r i = a(Byte.TYPE, Byte.class, h);
    public static final q<Number> j = new q<Number>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.m());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final r k = a(Short.TYPE, Short.class, j);
    public static final q<Number> l = new q<Number>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Integer.valueOf(aVar.m());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final r m = a(Integer.TYPE, Integer.class, l);
    public static final q<AtomicInteger> n = new q<AtomicInteger>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public AtomicInteger a(com.google.gson.stream.a aVar) throws IOException {
            try {
                return new AtomicInteger(aVar.m());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void a(b bVar, AtomicInteger atomicInteger) throws IOException {
            bVar.a((long) atomicInteger.get());
        }
    }.a();
    public static final r o = a(AtomicInteger.class, n);
    public static final q<AtomicBoolean> p = new q<AtomicBoolean>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public AtomicBoolean a(com.google.gson.stream.a aVar) throws IOException {
            return new AtomicBoolean(aVar.i());
        }

        public void a(b bVar, AtomicBoolean atomicBoolean) throws IOException {
            bVar.a(atomicBoolean.get());
        }
    }.a();
    public static final r q = a(AtomicBoolean.class, p);
    public static final q<AtomicIntegerArray> r = new q<AtomicIntegerArray>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public AtomicIntegerArray a(com.google.gson.stream.a aVar) throws IOException {
            List arrayList = new ArrayList();
            aVar.a();
            while (aVar.e()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.m()));
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }
            aVar.b();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        public void a(b bVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            bVar.b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                bVar.a((long) atomicIntegerArray.get(i));
            }
            bVar.c();
        }
    }.a();
    public static final r s = a(AtomicIntegerArray.class, r);
    public static final q<Number> t = new q<Number>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Long.valueOf(aVar.l());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final q<Number> u = new q<Number>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.k());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final q<Number> v = new q<Number>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return Double.valueOf(aVar.k());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final q<Number> w = new q<Number>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.google.gson.stream.a aVar) throws IOException {
            JsonToken f = aVar.f();
            switch (f) {
                case NUMBER:
                case STRING:
                    return new LazilyParsedNumber(aVar.h());
                case NULL:
                    aVar.j();
                    return null;
                default:
                    throw new JsonSyntaxException("Expecting number, got: " + f);
            }
        }

        public void a(b bVar, Number number) throws IOException {
            bVar.a(number);
        }
    };
    public static final r x = a(Number.class, w);
    public static final q<Character> y = new q<Character>() {
        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public Character a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + h);
        }

        public void a(b bVar, Character ch) throws IOException {
            bVar.b(ch == null ? null : String.valueOf(ch));
        }
    };
    public static final r z = a(Character.TYPE, Character.class, y);

    /* compiled from: TypeAdapters */
    private static final class a<T extends Enum<T>> extends q<T> {
        private final Map<String, T> a = new HashMap();
        private final Map<T, String> b = new HashMap();

        public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
            return a(aVar);
        }

        public a(Class<T> cls) {
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    String name = enumR.name();
                    c cVar = (c) cls.getField(name).getAnnotation(c.class);
                    if (cVar != null) {
                        name = cVar.a();
                        for (Object put : cVar.b()) {
                            this.a.put(put, enumR);
                        }
                    }
                    String str = name;
                    this.a.put(str, enumR);
                    this.b.put(enumR, str);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        public T a(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() != JsonToken.NULL) {
                return (Enum) this.a.get(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(b bVar, T t) throws IOException {
            bVar.b(t == null ? null : (String) this.b.get(t));
        }
    }

    public static <TT> r a(final Class<TT> cls, final q<TT> qVar) {
        return new r() {
            public <T> q<T> a(e eVar, com.google.gson.b.a<T> aVar) {
                return aVar.a() == cls ? qVar : null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + ",adapter=" + qVar + "]";
            }
        };
    }

    public static <TT> r a(final Class<TT> cls, final Class<TT> cls2, final q<? super TT> qVar) {
        return new r() {
            public <T> q<T> a(e eVar, com.google.gson.b.a<T> aVar) {
                Class a = aVar.a();
                return (a == cls || a == cls2) ? qVar : null;
            }

            public String toString() {
                return "Factory[type=" + cls2.getName() + "+" + cls.getName() + ",adapter=" + qVar + "]";
            }
        };
    }

    public static <TT> r b(final Class<TT> cls, final Class<? extends TT> cls2, final q<? super TT> qVar) {
        return new r() {
            public <T> q<T> a(e eVar, com.google.gson.b.a<T> aVar) {
                Class a = aVar.a();
                return (a == cls || a == cls2) ? qVar : null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + "+" + cls2.getName() + ",adapter=" + qVar + "]";
            }
        };
    }

    public static <T1> r b(final Class<T1> cls, final q<T1> qVar) {
        return new r() {
            public <T2> q<T2> a(e eVar, com.google.gson.b.a<T2> aVar) {
                final Class a = aVar.a();
                if (cls.isAssignableFrom(a)) {
                    return new q<T1>(this) {
                        final /* synthetic */ AnonymousClass28 b;

                        public void a(b bVar, T1 t1) throws IOException {
                            qVar.a(bVar, t1);
                        }

                        public T1 b(com.google.gson.stream.a aVar) throws IOException {
                            T1 b = qVar.b(aVar);
                            if (b == null || a.isInstance(b)) {
                                return b;
                            }
                            throw new JsonSyntaxException("Expected a " + a.getName() + " but was " + b.getClass().getName());
                        }
                    };
                }
                return null;
            }

            public String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + qVar + "]";
            }
        };
    }
}
