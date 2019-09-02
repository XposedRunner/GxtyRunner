package com.baidu.mobads;

import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;

class e implements IOAdEventListener {
    final /* synthetic */ AdView a;

    e(AdView adView) {
        this.a = adView;
    }

    public void run(IOAdEvent iOAdEvent) {
        if (this.a.d != null) {
            XAdSDKFoundationFacade.getInstance().getCommonUtils().a(new f(this, iOAdEvent));
        }
    }
}
