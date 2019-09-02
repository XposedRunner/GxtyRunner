package com.lzy.okgo.d;

import android.content.ContentValues;
import android.database.Cursor;
import com.lzy.okgo.cache.CacheEntity;
import java.util.List;

/* compiled from: CacheManager */
public class b extends a<CacheEntity<?>> {

    /* compiled from: CacheManager */
    private static class a {
        private static final b a = new b();
    }

    public /* synthetic */ Object a(Cursor cursor) {
        return b(cursor);
    }

    public /* synthetic */ ContentValues b(Object obj) {
        return a((CacheEntity) obj);
    }

    public static b c() {
        return a.a;
    }

    private b() {
        super(new d());
    }

    public CacheEntity<?> b(Cursor cursor) {
        return CacheEntity.parseCursorToBean(cursor);
    }

    public ContentValues a(CacheEntity<?> cacheEntity) {
        return CacheEntity.getContentValues(cacheEntity);
    }

    public String b() {
        return "cache";
    }

    public CacheEntity<?> a(String str) {
        if (str == null) {
            return null;
        }
        List b = b("key=?", new String[]{str});
        if (b.size() > 0) {
            return (CacheEntity) b.get(0);
        }
        return null;
    }

    public boolean b(String str) {
        if (str == null) {
            return false;
        }
        return a("key=?", new String[]{str});
    }

    public <T> CacheEntity<T> a(String str, CacheEntity<T> cacheEntity) {
        cacheEntity.setKey(str);
        a((Object) cacheEntity);
        return cacheEntity;
    }
}
