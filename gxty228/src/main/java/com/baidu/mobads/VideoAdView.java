package com.baidu.mobads;

import android.content.Context;
import android.widget.RelativeLayout;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;

public class VideoAdView extends RelativeLayout {
    private IOAdEventListener a = new h(this);
    private d b;

    public VideoAdView(Context context) {
        super(context);
    }

    public void setListener(d dVar) {
        this.b = dVar;
    }
}
