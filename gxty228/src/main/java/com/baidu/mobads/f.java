package com.baidu.mobads;

import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import org.json.JSONObject;

class f implements Runnable {
    final /* synthetic */ IOAdEvent a;
    final /* synthetic */ e b;

    f(e eVar, IOAdEvent iOAdEvent) {
        this.b = eVar;
        this.a = iOAdEvent;
    }

    public void run() {
        if (IXAdEvent.AD_LOADED.equals(this.a.getType())) {
            this.b.a.d.a(this.b.a);
        } else if (IXAdEvent.AD_STARTED.equals(this.a.getType())) {
            this.b.a.d.a();
            this.b.a.d.a(new JSONObject());
        } else if (IXAdEvent.AD_ERROR.equals(this.a.getType())) {
            this.b.a.d.a(XAdSDKFoundationFacade.getInstance().getErrorCode().getMessage(this.a.getData()));
        } else if ("AdUserClick".equals(this.a.getType())) {
            this.b.a.d.b(new JSONObject());
        } else if (IXAdEvent.AD_USER_CLOSE.equals(this.a.getType())) {
            XAdSDKFoundationFacade.getInstance().getCommonUtils().a(this.b.a);
            this.b.a.d.c(new JSONObject());
        }
    }
}
