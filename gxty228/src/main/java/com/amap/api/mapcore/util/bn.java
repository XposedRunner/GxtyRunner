package com.amap.api.mapcore.util;

import com.lzy.okgo.model.HttpHeaders;
import java.util.Hashtable;
import java.util.Map;

/* compiled from: OfflineDownloadRequest */
public class bn extends dl {
    private String b;

    public bn(String str) {
        this.b = str;
    }

    public Map<String, String> a() {
        Map<String, String> hashtable = new Hashtable(32);
        hashtable.put(HttpHeaders.HEAD_KEY_USER_AGENT, "MAC=channel:amapapi");
        return hashtable;
    }

    public Map<String, String> b() {
        return null;
    }

    public String c() {
        return this.b;
    }
}
