package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.b;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/* compiled from: OriginalKey */
class h implements b {
    private final String a;
    private final b b;

    public h(String str, b bVar) {
        this.a = str;
        this.b = bVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        if (!this.a.equals(hVar.a)) {
            return false;
        }
        if (this.b.equals(hVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }

    public void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
        messageDigest.update(this.a.getBytes("UTF-8"));
        this.b.a(messageDigest);
    }
}
