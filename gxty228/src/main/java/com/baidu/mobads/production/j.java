package com.baidu.mobads.production;

import com.baidu.mobads.f.a;
import com.baidu.mobads.g.g.c;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.utils.l;
import java.util.HashMap;

class j implements c {
    final /* synthetic */ b a;

    j(b bVar) {
        this.a = bVar;
    }

    public void a(boolean z) {
        if (z) {
            try {
                if (BaiduXAdSDKContext.mApkLoader != null) {
                    b.a = BaiduXAdSDKContext.mApkLoader.h();
                    if (b.a != null) {
                        this.a.r();
                        return;
                    }
                }
            } catch (Throwable e) {
                HashMap hashMap = new HashMap();
                hashMap.put("error_message", "async apk on load exception: " + e.toString());
                this.a.dispatchEvent(new a(IXAdEvent.AD_ERROR, hashMap));
                l.a().e(e);
                com.baidu.mobads.c.a.a().a("async apk on load exception: " + e.toString());
                return;
            }
        }
        BaiduXAdSDKContext.mApkLoader = null;
        HashMap hashMap2 = new HashMap();
        hashMap2.put("error_message", "回调onLoad,succcess=" + z);
        this.a.dispatchEvent(new a(IXAdEvent.AD_ERROR, hashMap2));
    }
}
