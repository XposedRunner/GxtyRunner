package com.baidu.mobads;

import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdSettings {
    private static HashSet<String> a = new HashSet();
    private static JSONArray b = new JSONArray();
    private static String c = (b.HTTP_PROTOCOL_TYPE.a() + "");
    private static HashSet<String> d = new HashSet();
    private static JSONArray e = new JSONArray();
    private static JSONObject f = new JSONObject();

    @Deprecated
    public enum b {
        UNKNOWN_PROTOCOL_TYPE(0),
        HTTP_PROTOCOL_TYPE(1),
        HTTPS_PROTOCOL_TYPE(2);
        
        private int d;

        private b(int i) {
            this.d = i;
        }

        public String a() {
            return this.d + "";
        }
    }

    public static String a() {
        return c;
    }

    public static JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        Iterator it = a.iterator();
        b = new JSONArray();
        while (it.hasNext()) {
            b.put(it.next());
        }
        try {
            jSONObject.putOpt("KEY", b);
            jSONObject.putOpt("RPT", c);
        } catch (Exception e) {
        }
        return jSONObject;
    }
}
