package com.lzy.okgo.cookie;

import android.content.ContentValues;
import android.database.Cursor;
import com.lzy.okgo.f.d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Locale;
import okhttp3.Cookie;
import okhttp3.Cookie.Builder;

public class SerializableCookie implements Serializable {
    public static final String COOKIE = "cookie";
    public static final String DOMAIN = "domain";
    public static final String HOST = "host";
    public static final String NAME = "name";
    private static final long serialVersionUID = 6374381323722046732L;
    private transient Cookie a;
    private transient Cookie b;
    public String domain;
    public String host;
    public String name;

    public SerializableCookie(String str, Cookie cookie) {
        this.a = cookie;
        this.host = str;
        this.name = cookie.name();
        this.domain = cookie.domain();
    }

    public Cookie getCookie() {
        Cookie cookie = this.a;
        if (this.b != null) {
            return this.b;
        }
        return cookie;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.a.name());
        objectOutputStream.writeObject(this.a.value());
        objectOutputStream.writeLong(this.a.expiresAt());
        objectOutputStream.writeObject(this.a.domain());
        objectOutputStream.writeObject(this.a.path());
        objectOutputStream.writeBoolean(this.a.secure());
        objectOutputStream.writeBoolean(this.a.httpOnly());
        objectOutputStream.writeBoolean(this.a.hostOnly());
        objectOutputStream.writeBoolean(this.a.persistent());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        String str2 = (String) objectInputStream.readObject();
        long readLong = objectInputStream.readLong();
        String str3 = (String) objectInputStream.readObject();
        String str4 = (String) objectInputStream.readObject();
        boolean readBoolean = objectInputStream.readBoolean();
        boolean readBoolean2 = objectInputStream.readBoolean();
        boolean readBoolean3 = objectInputStream.readBoolean();
        objectInputStream.readBoolean();
        Builder expiresAt = new Builder().name(str).value(str2).expiresAt(readLong);
        expiresAt = (readBoolean3 ? expiresAt.hostOnlyDomain(str3) : expiresAt.domain(str3)).path(str4);
        if (readBoolean) {
            expiresAt = expiresAt.secure();
        }
        if (readBoolean2) {
            expiresAt = expiresAt.httpOnly();
        }
        this.b = expiresAt.build();
    }

    public static SerializableCookie parseCursorToBean(Cursor cursor) {
        return new SerializableCookie(cursor.getString(cursor.getColumnIndex(HOST)), bytesToCookie(cursor.getBlob(cursor.getColumnIndex(COOKIE))));
    }

    public static ContentValues getContentValues(SerializableCookie serializableCookie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HOST, serializableCookie.host);
        contentValues.put("name", serializableCookie.name);
        contentValues.put(DOMAIN, serializableCookie.domain);
        contentValues.put(COOKIE, cookieToBytes(serializableCookie.host, serializableCookie.getCookie()));
        return contentValues;
    }

    public static String encodeCookie(String str, Cookie cookie) {
        if (cookie == null) {
            return null;
        }
        return a(cookieToBytes(str, cookie));
    }

    public static byte[] cookieToBytes(String str, Cookie cookie) {
        SerializableCookie serializableCookie = new SerializableCookie(str, cookie);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(serializableCookie);
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            d.a(e);
            return null;
        }
    }

    public static Cookie decodeCookie(String str) {
        return bytesToCookie(a(str));
    }

    public static Cookie bytesToCookie(byte[] bArr) {
        try {
            return ((SerializableCookie) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject()).getCookie();
        } catch (Throwable e) {
            d.a(e);
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(i));
        }
        return stringBuilder.toString().toUpperCase(Locale.US);
    }

    private static byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SerializableCookie serializableCookie = (SerializableCookie) obj;
        if (this.host != null) {
            if (!this.host.equals(serializableCookie.host)) {
                return false;
            }
        } else if (serializableCookie.host != null) {
            return false;
        }
        if (this.name != null) {
            if (!this.name.equals(serializableCookie.name)) {
                return false;
            }
        } else if (serializableCookie.name != null) {
            return false;
        }
        if (this.domain != null) {
            z = this.domain.equals(serializableCookie.domain);
        } else if (serializableCookie.domain != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.host != null) {
            hashCode = this.host.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.domain != null) {
            i = this.domain.hashCode();
        }
        return hashCode + i;
    }
}
