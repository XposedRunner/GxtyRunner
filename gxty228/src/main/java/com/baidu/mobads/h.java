package com.baidu.mobads;

import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;

class h implements IOAdEventListener {
    final /* synthetic */ VideoAdView a;

    h(VideoAdView videoAdView) {
        this.a = videoAdView;
    }

    public void run(IOAdEvent iOAdEvent) {
        XAdSDKFoundationFacade.getInstance().getCommonUtils().a(new i(this, iOAdEvent));
    }
}
