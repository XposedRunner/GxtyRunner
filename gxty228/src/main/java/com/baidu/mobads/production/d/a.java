package com.baidu.mobads.production.d;

import android.webkit.WebView;
import cn.jiguang.api.utils.ByteBufferUtils;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.IXHybridAdRenderer;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.production.b;
import com.baidu.mobads.production.v;
import com.baidu.mobads.vo.c;
import com.baidu.mobads.vo.d;
import java.util.HashMap;
import org.json.JSONException;

public class a extends b implements IXNonLinearAdSlot {
    private c w;
    private IXHybridAdRenderer x;
    private WebView y;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return q();
    }

    public a(WebView webView) {
        super(webView.getContext());
        this.y = webView;
        setActivity(webView.getContext());
        this.o = SlotType.SLOT_TYPE_JSSDK;
        this.w = new c(getApplicationContext(), getActivity(), this.o);
    }

    public void g() {
        load();
    }

    protected void h() {
        this.m = ByteBufferUtils.ERROR_CODE;
    }

    public void request() {
        a(this.w);
    }

    protected void b(d dVar) {
        this.k = dVar;
        k();
        a(null, null, GLMapStaticValue.TMC_REFRESH_TIMELIMIT);
    }

    protected void a(com.baidu.mobads.openad.d.b bVar, v vVar, int i) {
        try {
            setAdResponseInfo(new c("{'ad':[{'id':99999999,'url':'" + this.w.b() + "', type='" + CreativeType.HYBRID.getValue() + "'}],'n':1}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        b("XAdMouldeLoader ad-server requesting success");
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        try {
            this.x = (IXHybridAdRenderer) this.h;
            this.x.setCustomerWebView(this.y);
        } catch (Exception e) {
            this.x = null;
        }
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

    public boolean a(WebView webView, String str) {
        return this.x == null ? false : this.x.shouldOverrideUrlLoading(webView, str);
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }
}
