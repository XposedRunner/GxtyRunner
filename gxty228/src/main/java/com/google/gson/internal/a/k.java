package com.google.gson.internal.a;

import com.google.gson.JsonSyntaxException;
import com.google.gson.b.a;
import com.google.gson.e;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: TimeTypeAdapter */
public final class k extends q<Time> {
    public static final r a = new r() {
        public <T> q<T> a(e eVar, a<T> aVar) {
            return aVar.a() == Time.class ? new k() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    public /* synthetic */ Object b(com.google.gson.stream.a aVar) throws IOException {
        return a(aVar);
    }

    public synchronized Time a(com.google.gson.stream.a aVar) throws IOException {
        Time time;
        if (aVar.f() == JsonToken.NULL) {
            aVar.j();
            time = null;
        } else {
            try {
                time = new Time(this.b.parse(aVar.h()).getTime());
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            }
        }
        return time;
    }

    public synchronized void a(b bVar, Time time) throws IOException {
        bVar.b(time == null ? null : this.b.format(time));
    }
}
