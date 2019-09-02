package cn.jpush.android.service;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import cn.jiguang.e.d;
import cn.jiguang.g.b.b;
import cn.jiguang.g.b.e;
import com.lzy.okgo.cache.CacheEntity;
import java.io.Serializable;

public class DataProvider extends ContentProvider {
    private static final String TAG = "DataProvider";

    private String getName(Uri uri) {
        if (uri != null) {
            try {
                CharSequence path = uri.getPath();
            } catch (Throwable th) {
                d.c(TAG, "getName failed:" + th.getMessage());
                return null;
            }
        }
        path = null;
        if (path == null) {
            path = null;
        } else if (path.charAt(0) == '/') {
            path = path.substring(1);
        }
        if (!TextUtils.isEmpty(path)) {
            return path;
        }
        d.i(TAG, "URI is illegal");
        return null;
    }

    private Serializable getWithCache(e eVar, b bVar, String str, int i) {
        Serializable a = bVar.a(str, i);
        if (a != null || eVar == null) {
            return a;
        }
        Object a2 = eVar.a(str, i);
        bVar.a(str, a2);
        return a2;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        String name = getName(uri);
        if (name == null) {
            return 0;
        }
        b.a(name).a();
        e a = e.a(getContext(), name);
        return (a == null || !a.a()) ? 0 : 1;
    }

    public String getType(Uri uri) {
        String queryParameter;
        String str;
        int intValue;
        int i;
        String name = getName(uri);
        if (name == null) {
            return null;
        }
        int i2 = -1;
        try {
            queryParameter = uri.getQueryParameter(CacheEntity.KEY);
            try {
                str = queryParameter;
                intValue = Integer.valueOf(uri.getQueryParameter("type")).intValue();
            } catch (Throwable th) {
                i = i2;
                str = queryParameter;
                intValue = i;
                if (intValue >= 0) {
                }
                d.g(TAG, str + "'s type is invalid : " + intValue);
                return null;
            }
        } catch (Throwable th2) {
            queryParameter = null;
            i = i2;
            str = queryParameter;
            intValue = i;
            if (intValue >= 0) {
            }
            d.g(TAG, str + "'s type is invalid : " + intValue);
            return null;
        }
        if (intValue >= 0 || intValue > 6) {
            d.g(TAG, str + "'s type is invalid : " + intValue);
            return null;
        }
        Serializable withCache = getWithCache(e.a(getContext(), name), b.a(name), str, intValue);
        return withCache != null ? withCache.toString() : null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return uri;
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (strArr == null || strArr.length == 0 || strArr2 == null || strArr2.length == 0 || strArr.length != strArr2.length) {
            return null;
        }
        String name = getName(uri);
        if (name == null) {
            return null;
        }
        try {
            e a = e.a(getContext(), name);
            b a2 = b.a(name);
            int length = strArr.length;
            MatrixCursor matrixCursor = new MatrixCursor(strArr, 1);
            Object[] objArr = new Object[length];
            for (int i = 0; i < length; i++) {
                objArr[i] = getWithCache(a, a2, strArr[i], Integer.valueOf(strArr2[i]).intValue());
                if (objArr[i] != null) {
                    if (objArr[i].equals(Boolean.valueOf(false))) {
                        objArr[i] = Integer.valueOf(0);
                    } else if (objArr[i].equals(Boolean.valueOf(true))) {
                        objArr[i] = Integer.valueOf(1);
                    }
                }
            }
            matrixCursor.addRow(objArr);
            return matrixCursor;
        } catch (NumberFormatException e) {
            Log.e(TAG, "selectionArgs should be int");
        } catch (Throwable th) {
            Log.e(TAG, th.getMessage());
            return null;
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (contentValues == null || contentValues.size() == 0) {
            return 0;
        }
        String name = getName(uri);
        if (name == null) {
            return 0;
        }
        b.a(name).a(contentValues);
        e a = e.a(getContext(), name);
        return (a == null || !a.a(contentValues)) ? 0 : contentValues.size();
    }
}
