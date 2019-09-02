package com.google.gson;

import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: DefaultDateTypeAdapter */
final class a extends q<Date> {
    private final Class<? extends Date> a;
    private final DateFormat b;
    private final DateFormat c;

    public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
        return a(aVar);
    }

    a(Class<? extends Date> cls, String str) {
        this((Class) cls, new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    public a(Class<? extends Date> cls, int i, int i2) {
        this((Class) cls, DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    a(Class<? extends Date> cls, DateFormat dateFormat, DateFormat dateFormat2) {
        if (cls == Date.class || cls == java.sql.Date.class || cls == Timestamp.class) {
            this.a = cls;
            this.b = dateFormat;
            this.c = dateFormat2;
            return;
        }
        throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class + ", or " + java.sql.Date.class + " but was " + cls);
    }

    public void a(b bVar, Date date) throws IOException {
        synchronized (this.c) {
            bVar.b(this.b.format(date));
        }
    }

    public Date a(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.f() != JsonToken.STRING) {
            throw new JsonParseException("The date should be a string value");
        }
        Date a = a(aVar.h());
        if (this.a == Date.class) {
            return a;
        }
        if (this.a == Timestamp.class) {
            return new Timestamp(a.getTime());
        }
        if (this.a == java.sql.Date.class) {
            return new java.sql.Date(a.getTime());
        }
        throw new AssertionError();
    }

    private Date a(String str) {
        Date parse;
        synchronized (this.c) {
            try {
                parse = this.c.parse(str);
            } catch (ParseException e) {
                try {
                    parse = this.b.parse(str);
                } catch (ParseException e2) {
                    try {
                        parse = com.google.gson.internal.a.a.a.a(str, new ParsePosition(0));
                    } catch (Throwable e3) {
                        throw new JsonSyntaxException(str, e3);
                    }
                }
            }
        }
        return parse;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DefaultDateTypeAdapter");
        stringBuilder.append('(').append(this.c.getClass().getSimpleName()).append(')');
        return stringBuilder.toString();
    }
}
