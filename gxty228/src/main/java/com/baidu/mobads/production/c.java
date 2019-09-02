package com.baidu.mobads.production;

import com.baidu.mobads.c.a;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import org.json.JSONObject;

class c implements IOAdEventListener {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void run(IOAdEvent iOAdEvent) {
        String str;
        this.a.k();
        if ("URLLoader.Load.Complete".equals(iOAdEvent.getType())) {
            try {
                this.a.setAdResponseInfo(new com.baidu.mobads.vo.c((String) iOAdEvent.getData().get("message")));
                if (this.a.y == null || this.a.y.getAdInstanceList().size() <= 0) {
                    XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage(this.a.y.getErrorCode(), this.a.y.getErrorMessage(), "");
                    this.a.d(this.a.y.getErrorMessage());
                    return;
                }
                this.a.d = this.a.y.getPrimaryAdInstanceInfo();
                JSONObject originJsonObject = this.a.d.getOriginJsonObject();
                this.a.r = originJsonObject.optString("mimetype");
                this.a.q();
                this.a.a();
                return;
            } catch (Exception e) {
                str = "response json parsing error";
                XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage("", str, "");
                this.a.d(str);
                a.a().a(str);
                return;
            }
        }
        str = "request ad-server error, io_err/timeout";
        XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage("", str, "");
        this.a.d(str);
        a.a().a(str);
    }
}
