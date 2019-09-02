package com.baidu.mobad.feeds;

import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class a implements IXAdFeedsRequestParameters {
    protected String a;
    private final String b;
    private int c;
    private boolean d;
    private Map<String, String> e;
    private int f;
    private int g;
    private int h;

    public final String getKeywords() {
        return this.b;
    }

    public int a() {
        return this.f;
    }

    public int b() {
        return this.g;
    }

    public int getAdsType() {
        return this.c;
    }

    public boolean isConfirmDownloading() {
        return this.d;
    }

    public Map<String, String> getExtras() {
        return this.e;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("mKeywords", this.b);
        hashMap.put("adsType", Integer.valueOf(this.c));
        hashMap.put("confirmDownloading", Boolean.valueOf(this.d));
        HashMap hashMap2 = new HashMap();
        if (this.e != null) {
            for (Entry entry : this.e.entrySet()) {
                hashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        hashMap.put("extras", hashMap2);
        return hashMap;
    }

    public String getAdPlacementId() {
        return this.a;
    }

    public int getAPPConfirmPolicy() {
        return this.h;
    }
}
