package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.a;
import java.math.BigInteger;

/* compiled from: JsonPrimitive */
public final class n extends k {
    private static final Class<?>[] a = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object b;

    public n(Boolean bool) {
        a((Object) bool);
    }

    public n(Number number) {
        a((Object) number);
    }

    public n(String str) {
        a((Object) str);
    }

    void a(Object obj) {
        if (obj instanceof Character) {
            this.b = String.valueOf(((Character) obj).charValue());
            return;
        }
        boolean z = (obj instanceof Number) || b(obj);
        a.a(z);
        this.b = obj;
    }

    public boolean o() {
        return this.b instanceof Boolean;
    }

    Boolean n() {
        return (Boolean) this.b;
    }

    public boolean f() {
        if (o()) {
            return n().booleanValue();
        }
        return Boolean.parseBoolean(b());
    }

    public boolean p() {
        return this.b instanceof Number;
    }

    public Number a() {
        return this.b instanceof String ? new LazilyParsedNumber((String) this.b) : (Number) this.b;
    }

    public boolean q() {
        return this.b instanceof String;
    }

    public String b() {
        if (p()) {
            return a().toString();
        }
        if (o()) {
            return n().toString();
        }
        return (String) this.b;
    }

    public double c() {
        return p() ? a().doubleValue() : Double.parseDouble(b());
    }

    public long d() {
        return p() ? a().longValue() : Long.parseLong(b());
    }

    public int e() {
        return p() ? a().intValue() : Integer.parseInt(b());
    }

    private static boolean b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class cls = obj.getClass();
        for (Class isAssignableFrom : a) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.b == null) {
            return 31;
        }
        long longValue;
        if (a(this)) {
            longValue = a().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.b instanceof Number)) {
            return this.b.hashCode();
        } else {
            longValue = Double.doubleToLongBits(a().doubleValue());
            return (int) (longValue ^ (longValue >>> 32));
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        n nVar = (n) obj;
        if (this.b == null) {
            if (nVar.b != null) {
                return false;
            }
            return true;
        } else if (a(this) && a(nVar)) {
            if (a().longValue() != nVar.a().longValue()) {
                return false;
            }
            return true;
        } else if (!(this.b instanceof Number) || !(nVar.b instanceof Number)) {
            return this.b.equals(nVar.b);
        } else {
            double doubleValue = a().doubleValue();
            double doubleValue2 = nVar.a().doubleValue();
            if (doubleValue == doubleValue2 || (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2))) {
                z = true;
            }
            return z;
        }
    }

    private static boolean a(n nVar) {
        if (!(nVar.b instanceof Number)) {
            return false;
        }
        Number number = (Number) nVar.b;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            return true;
        }
        return false;
    }
}
