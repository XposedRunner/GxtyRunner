package com.google.gson.internal.a;

import com.google.gson.JsonSyntaxException;
import com.google.gson.b.a;
import com.google.gson.e;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

/* compiled from: DateTypeAdapter */
public final class c extends q<Date> {
    public static final r a = new r() {
        public <T> q<T> a(e eVar, a<T> aVar) {
            return aVar.a() == Date.class ? new c() : null;
        }
    };
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);

    public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
        return a(aVar);
    }

    public Date a(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.f() != JsonToken.NULL) {
            return a(aVar.h());
        }
        aVar.j();
        return null;
    }

    private synchronized Date a(String str) {
        Date parse;
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
        return parse;
    }

    public synchronized void a(b bVar, Date date) throws IOException {
        if (date == null) {
            bVar.f();
        } else {
            bVar.b(this.b.format(date));
        }
    }
}
