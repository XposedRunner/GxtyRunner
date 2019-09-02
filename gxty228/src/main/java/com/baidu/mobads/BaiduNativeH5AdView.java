package com.baidu.mobads;

import android.widget.RelativeLayout;

public class BaiduNativeH5AdView extends RelativeLayout {
    private c a;
    private a b;

    public interface a {
    }

    public c getAdPlacement() {
        return this.a;
    }

    protected void setAdPlacement(c cVar) {
        this.a = cVar;
    }

    public void setEventListener(a aVar) {
        this.b = aVar;
    }
}
