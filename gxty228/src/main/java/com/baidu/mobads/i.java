package com.baidu.mobads;

import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;

class i implements Runnable {
    final /* synthetic */ IOAdEvent a;
    final /* synthetic */ h b;

    i(h hVar, IOAdEvent iOAdEvent) {
        this.b = hVar;
        this.a = iOAdEvent;
    }

    public void run() {
        if (IXAdEvent.AD_LOADED.equals(this.a.getType())) {
            this.b.a.b.a();
        }
        if (IXAdEvent.AD_STARTED.equals(this.a.getType())) {
            this.b.a.b.b();
        }
        if (IXAdEvent.AD_CLICK_THRU.equals(this.a.getType())) {
            this.b.a.b.e();
        }
        if (IXAdEvent.AD_STOPPED.equals(this.a.getType())) {
            this.b.a.b.c();
        }
        if (IXAdEvent.AD_ERROR.equals(this.a.getType())) {
            String str = (String) this.a.getData().get("message");
            this.b.a.b.d();
        }
    }
}
