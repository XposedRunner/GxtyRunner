package com.amap.api.mapcore.util;

import android.content.Context;
import cn.jiguang.api.utils.ByteBufferUtils;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.model.HttpHeaders;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: AuthRequest */
public class hl extends fi<String, a> {
    private int[] i;

    /* compiled from: AuthRequest */
    public static class a {
        public int a = -1;
        public String b;
        public String c;
        public boolean d = false;
    }

    protected /* synthetic */ Object c(String str) throws gh {
        return a(str);
    }

    public hl(Context context, String str) {
        super(context, str);
        this.i = new int[]{ByteBufferUtils.ERROR_CODE, 0, 10018, 10019, 10020, 10021, 10022, 10023};
        this.e = "/feedback";
        this.a = false;
    }

    public Map<String, String> a() {
        gk e = en.e();
        String str = null;
        if (e != null) {
            str = e.b();
        }
        Map<String, String> hashtable = new Hashtable(16);
        hashtable.put(HttpHeaders.HEAD_KEY_USER_AGENT, le.c);
        hashtable.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", new Object[]{str, "3dmap"}));
        hashtable.put("x-INFO", gb.a(this.d));
        hashtable.put(CacheEntity.KEY, fx.f(this.d));
        hashtable.put("logversion", "2.1");
        return hashtable;
    }

    protected a a(String str) throws gh {
        try {
            int optInt;
            int i;
            String str2;
            JSONObject jSONObject = new JSONObject(str);
            String str3 = "";
            String str4 = "";
            if (jSONObject.has("errcode")) {
                optInt = jSONObject.optInt("errcode");
                str3 = jSONObject.optString("errmsg");
                str4 = jSONObject.optString("errdetail");
                i = optInt;
                str2 = str3;
                str3 = str4;
            } else {
                i = -1;
                str2 = str3;
                str3 = str4;
            }
            a aVar = new a();
            aVar.a = i;
            aVar.b = str2;
            aVar.c = str3;
            aVar.d = false;
            for (int i2 : this.i) {
                if (i2 == i) {
                    aVar.d = true;
                    return aVar;
                }
            }
            return aVar;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Map<String, String> b() {
        Map hashtable = new Hashtable(16);
        hashtable.put(CacheEntity.KEY, fx.f(this.d));
        hashtable.put("pname", "3dmap");
        String a = gb.a();
        String a2 = gb.a(this.d, a, gl.c(hashtable));
        hashtable.put("ts", a);
        hashtable.put("scode", a2);
        return hashtable;
    }

    public String c() {
        return "http://restapi.amap.com/v4" + this.e;
    }
}
