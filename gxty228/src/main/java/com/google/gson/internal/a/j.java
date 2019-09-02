package com.google.gson.internal.a;

import com.google.gson.JsonSyntaxException;
import com.google.gson.b.a;
import com.google.gson.e;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: SqlDateTypeAdapter */
public final class j extends q<Date> {
    public static final r a = new r() {
        public <T> q<T> a(e eVar, a<T> aVar) {
            return aVar.a() == Date.class ? new j() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
        return a(aVar);
    }

    public synchronized Date a(com.google.gson.stream.a aVar) throws IOException {
        Date date;
        if (aVar.f() == JsonToken.NULL) {
            aVar.j();
            date = null;
        } else {
            try {
                date = new Date(this.b.parse(aVar.h()).getTime());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }
        return date;
    }

    public synchronized void a(b bVar, Date date) throws IOException {
        bVar.b(date == null ? null : this.b.format(date));
    }
}
