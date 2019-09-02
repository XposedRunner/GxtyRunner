package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.fy.a;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OfflineUpdateCityHandlerAbstract */
public class as extends bl<String, List<OfflineMapProvince>> {
    private Context d;

    protected /* synthetic */ Object b(JSONObject jSONObject) throws AMapException {
        return a(jSONObject);
    }

    public as(Context context, String str) {
        super(context, str);
    }

    public void a(Context context) {
        this.d = context;
    }

    protected List<OfflineMapProvince> a(JSONObject jSONObject) throws AMapException {
        List<OfflineMapProvince> list = null;
        try {
            if (this.d != null) {
                bk.c(jSONObject.toString(), this.d);
            }
        } catch (Throwable th) {
            gz.c(th, "OfflineUpdateCityHandlerAbstract", "loadData jsonInit");
            th.printStackTrace();
        }
        try {
            if (this.d != null) {
                list = bk.a(jSONObject, this.d);
            }
        } catch (Throwable th2) {
            gz.c(th2, "OfflineUpdateCityHandlerAbstract", "loadData parseJson");
            th2.printStackTrace();
        }
        return list;
    }

    protected JSONObject a(a aVar) {
        if (aVar == null || aVar.w == null) {
            return null;
        }
        JSONObject optJSONObject = aVar.w.optJSONObject("015");
        if (!optJSONObject.has("result")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", new JSONObject().put("offlinemap_with_province_vfour", optJSONObject));
                return jSONObject;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return optJSONObject;
    }

    protected String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("015");
        return stringBuilder.toString();
    }

    protected Map<String, String> b() {
        Map<String, String> hashtable = new Hashtable(16);
        hashtable.put("mapver", this.a);
        return hashtable;
    }
}
