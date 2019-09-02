package com.baidu.mobads.production.a;

import android.content.Context;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import cn.jiguang.api.utils.ByteBufferUtils;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.production.b;
import com.baidu.mobads.production.v;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.vo.c;
import com.baidu.mobads.vo.d;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends b implements IXNonLinearAdSlot {
    private c w;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return q();
    }

    public a(Context context, RelativeLayout relativeLayout, String str, boolean z) {
        JSONObject jSONObject;
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(relativeLayout);
        this.o = SlotType.SLOT_TYPE_BANNER;
        XAdSDKFoundationFacade.getInstance().getAdConstants();
        this.w = new c(getApplicationContext(), getActivity(), this.o);
        this.w.f(AdSize.Banner.getValue());
        this.w.d(str);
        com.baidu.mobads.vo.b bVar = (com.baidu.mobads.vo.b) this.w.d();
        bVar.a(z);
        JSONObject attribute = bVar.getAttribute();
        if (attribute == null) {
            jSONObject = new JSONObject();
        } else {
            jSONObject = attribute;
        }
        try {
            jSONObject.put("ABILITY", "BANNER_CLOSE,PAUSE,UNLIMITED_BANNER_SIZE,");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bVar.a(jSONObject);
        e(str);
    }

    public void g() {
        load();
    }

    protected void h() {
        this.m = ByteBufferUtils.ERROR_CODE;
    }

    public void request() {
        a(this.w);
        try {
            WebView webView = new WebView(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
            new HashMap().put("error_message", "init webview,exception");
            dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR));
        } catch (Error e2) {
            e2.printStackTrace();
            new HashMap().put("error_message", "init webview,error");
            dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR));
        }
    }

    protected void b(d dVar) {
        this.k = dVar;
        k();
        a(null, null, GLMapStaticValue.TMC_REFRESH_TIMELIMIT);
    }

    protected void a(com.baidu.mobads.openad.d.b bVar, v vVar, int i) {
        try {
            setAdResponseInfo(new c("{'ad':[{'id':99999999,'url':'" + this.w.b() + "', type='" + CreativeType.HTML.getValue() + "'}],'n':1}"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        b("XAdMouldeLoader ad-server requesting success");
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        start();
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
    }

    public d q() {
        return this.w;
    }

    protected void c() {
        new Thread(new b(this)).start();
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.p();
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_USER_CLOSE));
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }
}
