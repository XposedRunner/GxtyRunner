package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.fy.a;
import com.amap.api.maps.AMapException;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: AbstractProtocalHandler */
public abstract class bl<T, V> {
    protected T a;
    protected int b = 3;
    protected Context c;

    protected abstract String a();

    protected abstract JSONObject a(a aVar);

    protected abstract V b(JSONObject jSONObject) throws AMapException;

    protected abstract Map<String, String> b();

    public bl(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.c = context;
        this.a = t;
    }

    public V c() throws AMapException {
        if (this.a != null) {
            return d();
        }
        return null;
    }

    protected V d() throws AMapException {
        int i = 0;
        V v = null;
        a aVar = null;
        while (i < this.b) {
            try {
                aVar = fy.a(this.c, en.e(), a(), b());
                v = b(a(aVar));
                i = this.b;
            } catch (Throwable th) {
                gz.c(th, "AbstractProtocalHandler", "getDataMayThrow AMapException");
                th.printStackTrace();
                i++;
                if (i < this.b) {
                    continue;
                } else if (aVar == null || aVar.a == null) {
                    v = null;
                } else {
                    AMapException aMapException = new AMapException(aVar.a);
                }
            }
        }
        return v;
    }
}
