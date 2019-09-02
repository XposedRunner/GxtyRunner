package com.lzy.okgo.cache;

import android.content.ContentValues;
import android.database.Cursor;
import com.lzy.okgo.f.c;
import com.lzy.okgo.model.HttpHeaders;
import java.io.Serializable;

public class CacheEntity<T> implements Serializable {
    public static final long CACHE_NEVER_EXPIRE = -1;
    public static final String DATA = "data";
    public static final String HEAD = "head";
    public static final String KEY = "key";
    public static final String LOCAL_EXPIRE = "localExpire";
    private static final long serialVersionUID = -4337711009801627866L;
    private T data;
    private boolean isExpire;
    private String key;
    private long localExpire;
    private HttpHeaders responseHeaders;

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public void setResponseHeaders(HttpHeaders httpHeaders) {
        this.responseHeaders = httpHeaders;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public long getLocalExpire() {
        return this.localExpire;
    }

    public void setLocalExpire(long j) {
        this.localExpire = j;
    }

    public boolean isExpire() {
        return this.isExpire;
    }

    public void setExpire(boolean z) {
        this.isExpire = z;
    }

    public boolean checkExpire(CacheMode cacheMode, long j, long j2) {
        if (cacheMode == CacheMode.DEFAULT) {
            if (getLocalExpire() < j2) {
                return true;
            }
            return false;
        } else if (j == -1) {
            return false;
        } else {
            if (getLocalExpire() + j >= j2) {
                return false;
            }
            return true;
        }
    }

    public static <T> ContentValues getContentValues(CacheEntity<T> cacheEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY, cacheEntity.getKey());
        contentValues.put(LOCAL_EXPIRE, Long.valueOf(cacheEntity.getLocalExpire()));
        contentValues.put(HEAD, c.a(cacheEntity.getResponseHeaders()));
        contentValues.put("data", c.a(cacheEntity.getData()));
        return contentValues;
    }

    public static <T> CacheEntity<T> parseCursorToBean(Cursor cursor) {
        CacheEntity<T> cacheEntity = new CacheEntity();
        cacheEntity.setKey(cursor.getString(cursor.getColumnIndex(KEY)));
        cacheEntity.setLocalExpire(cursor.getLong(cursor.getColumnIndex(LOCAL_EXPIRE)));
        cacheEntity.setResponseHeaders((HttpHeaders) c.a(cursor.getBlob(cursor.getColumnIndex(HEAD))));
        cacheEntity.setData(c.a(cursor.getBlob(cursor.getColumnIndex("data"))));
        return cacheEntity;
    }

    public String toString() {
        return "CacheEntity{key='" + this.key + '\'' + ", responseHeaders=" + this.responseHeaders + ", data=" + this.data + ", localExpire=" + this.localExpire + '}';
    }
}
