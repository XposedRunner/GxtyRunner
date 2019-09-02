package cn.jiguang.d.c;

import java.util.HashMap;

final class i {
    private static Integer[] a = new Integer[64];
    private HashMap b = new HashMap();
    private HashMap c = new HashMap();
    private String d;
    private int e = 3;
    private String f;
    private int g = Integer.MAX_VALUE;
    private boolean h;

    static {
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.valueOf(i);
        }
    }

    public i(String str, int i) {
        this.d = str;
    }

    private static Integer c(int i) {
        return (i < 0 || i >= a.length) ? Integer.valueOf(i) : a[i];
    }

    private void d(int i) {
        if (i < 0 || i > this.g) {
            throw new IllegalArgumentException(this.d + " " + i + "is out of range");
        }
    }

    public final void a(int i) {
        this.g = 3;
    }

    public final void a(int i, String str) {
        Object toUpperCase;
        d(i);
        Integer c = c(i);
        if (this.e == 2) {
            toUpperCase = str.toUpperCase();
        } else if (this.e == 3) {
            toUpperCase = str.toLowerCase();
        }
        this.b.put(toUpperCase, c);
        this.c.put(c, toUpperCase);
    }

    public final void a(boolean z) {
        this.h = true;
    }

    public final String b(int i) {
        d(i);
        String str = (String) this.c.get(c(i));
        if (str != null) {
            return str;
        }
        str = Integer.toString(i);
        return this.f != null ? this.f + str : str;
    }
}
