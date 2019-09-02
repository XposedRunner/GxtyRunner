package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.fy.a;
import com.amap.api.maps.AMapException;
import java.util.Hashtable;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: OfflineInitHandlerAbstract */
public class an extends bl<String, am> {
    protected /* synthetic */ Object b(JSONObject jSONObject) throws AMapException {
        return a(jSONObject);
    }

    public an(Context context, String str) {
        super(context, str);
    }

    protected JSONObject a(a aVar) {
        if (aVar == null || aVar.w == null) {
            return null;
        }
        return aVar.w.optJSONObject("016");
    }

    protected String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("016");
        return stringBuilder.toString();
    }

    protected Map<String, String> b() {
        Map<String, String> hashtable = new Hashtable(16);
        hashtable.put("mapver", this.a);
        return hashtable;
    }

    protected am a(JSONObject jSONObject) throws AMapException {
        am amVar = new am();
        try {
            String optString = jSONObject.optString("update", "");
            if (optString.equals("0")) {
                amVar.a(false);
            } else if (optString.equals("1")) {
                amVar.a(true);
            }
            amVar.a(jSONObject.optString("version", ""));
        } catch (Throwable th) {
            gz.c(th, "OfflineInitHandlerAbstract", "loadData parseJson");
        }
        return amVar;
    }
}
