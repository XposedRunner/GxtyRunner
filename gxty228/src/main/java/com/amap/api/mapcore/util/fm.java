package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import cn.jiguang.net.HttpUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.trace.TraceLocation;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TraceHandlerAbstract */
public class fm extends fj<List<TraceLocation>, List<LatLng>> implements Runnable {
    private List<TraceLocation> i;
    private Handler j = null;
    private int k = 0;
    private int l = 0;
    private String m;

    protected /* synthetic */ Object c(String str) throws gh {
        return a(str);
    }

    public fm(Context context, Handler handler, List<TraceLocation> list, int i, String str, int i2, int i3) {
        super(context, list);
        this.i = list;
        this.j = handler;
        this.l = i2;
        this.k = i3;
        this.m = str;
    }

    protected String g() {
        JSONArray jSONArray = new JSONArray();
        long j = 0;
        for (int i = 0; i < this.i.size(); i++) {
            TraceLocation traceLocation = (TraceLocation) this.i.get(i);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("x", traceLocation.getLongitude());
                jSONObject.put("y", traceLocation.getLatitude());
                jSONObject.put("ag", (int) traceLocation.getBearing());
                long time = traceLocation.getTime();
                if (i == 0) {
                    if (time == 0) {
                        time = (System.currentTimeMillis() - 10000) / 1000;
                    }
                    jSONObject.put(IXAdRequestInfo.MAX_TITLE_LENGTH, time / 1000);
                    j = time;
                } else if (time == 0 || time - j < 1000) {
                    jSONObject.put(IXAdRequestInfo.MAX_TITLE_LENGTH, 1);
                    j = time;
                } else {
                    jSONObject.put(IXAdRequestInfo.MAX_TITLE_LENGTH, (time - j) / 1000);
                    j = time;
                }
                jSONObject.put("sp", (int) traceLocation.getSpeed());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        this.e = c() + HttpUtils.PARAMETERS_SEPARATOR + jSONArray.toString();
        return jSONArray.toString();
    }

    protected List<LatLng> a(String str) throws gh {
        List<LatLng> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("data")) {
                JSONArray optJSONArray = jSONObject.optJSONObject("data").optJSONArray("points");
                if (!(optJSONArray == null || optJSONArray.length() == 0)) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        arrayList.add(new LatLng(Double.parseDouble(optJSONObject.optString("y")), Double.parseDouble(optJSONObject.optString("x"))));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return arrayList;
    }

    public void run() {
        ArrayList arrayList = new ArrayList();
        try {
            fv.a().a(this.m, this.k, (List) e());
            fv.a().a(this.m).a(this.j);
        } catch (gh e) {
            fv.a().a(this.j, this.l, e.a());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String c() {
        String str = "key=" + fx.f(this.d);
        String a = gb.a();
        String str2 = "&ts=" + a;
        return "http://restapi.amap.com/v4/grasproad/driving?" + str + str2 + ("&scode=" + gb.a(this.d, a, str));
    }
}
