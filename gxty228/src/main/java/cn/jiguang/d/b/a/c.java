package cn.jiguang.d.b.a;

import android.text.TextUtils;
import java.io.Serializable;

public final class c implements Serializable {
    public String a;
    public final int b;

    public c(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public static c a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length != 2) {
            return null;
        }
        try {
            return new c(split[0], Integer.decode(split[1]).intValue());
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean a(String str, int i) {
        return !TextUtils.isEmpty(str) && i > 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (this.b == cVar.b) {
            if (this.a != null) {
                if (this.a.equals(cVar.a)) {
                    return true;
                }
            } else if (cVar.a == null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.a != null ? this.a.hashCode() : 0) * 31) + this.b;
    }

    public final String toString() {
        return this.a + ":" + this.b;
    }
}
