package com.baidu.mobads.production.c;

import android.content.Context;
import android.view.View;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.BaiduNativeH5AdView;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.error.XAdErrorCode;
import com.baidu.mobads.interfaces.feeds.IXAdDummyContainer;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import com.baidu.mobads.production.b;
import com.baidu.mobads.production.v;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.g;
import com.baidu.mobads.utils.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a extends b {
    private b w = null;
    private BaiduNativeH5AdView x;

    public a(Context context, BaiduNativeH5AdView baiduNativeH5AdView) {
        super(context);
        this.x = baiduNativeH5AdView;
        setId(this.x.getAdPlacement().a());
        setActivity(context);
        setAdSlotBase(this.x);
        this.o = SlotType.SLOT_TYPE_FEEDS;
        this.w = new b(getApplicationContext(), getActivity(), this.o);
        this.w.d(this.x.getAdPlacement().a());
        g adConstants = XAdSDKFoundationFacade.getInstance().getAdConstants();
        List arrayList = new ArrayList();
        arrayList.add(adConstants.getSupportedActionType4RequestingLandingPage());
        arrayList.add(adConstants.a());
        XAdSDKFoundationFacade.getInstance().getPackageUtils();
        if (m.b(this.f)) {
            arrayList.add(adConstants.getSupportedActionType4RequestingDownload());
        }
        this.w.b(XAdSDKFoundationFacade.getInstance().getCommonUtils().a(arrayList));
        this.w.h(0);
        this.w.f(AdSize.FeedH5TemplateNative.getValue());
        this.w.g(1);
    }

    public void b(int i) {
        this.w.a(i);
    }

    public void c(int i) {
        this.w.b(i);
    }

    public void d(int i) {
        this.w.c(i);
    }

    public void a(com.baidu.mobad.feeds.a aVar) {
        int a = aVar.a();
        int b = aVar.b();
        if (a > 0 && b > 0) {
            this.w.d(a);
            this.w.e(b);
        }
    }

    public void request() {
        m();
        a(this.w);
    }

    public IXAdRequestInfo getAdRequestInfo() {
        return this.w;
    }

    protected void a(com.baidu.mobads.openad.d.b bVar, v vVar, int i) {
        if (getAdResponseInfo() != null) {
            b("XAdMouldeLoader ad-server requesting success");
        } else {
            vVar.a(bVar, (double) i);
        }
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        iXAdContainer.start();
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
    }

    public void g() {
        if (this.h != null) {
            this.h.load();
        } else {
            this.s.e("container is null");
        }
    }

    protected void h() {
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
        if (iXAdResponseInfo.getAdInstanceList().size() <= 0) {
            return;
        }
        if (iXAdResponseInfo.getPrimaryAdInstanceInfo().getHtmlSnippet() == null || iXAdResponseInfo.getPrimaryAdInstanceInfo().getHtmlSnippet().length() <= 0) {
            a(XAdErrorCode.REQUEST_PARAM_ERROR, "代码位错误，请检查代码位是否是信息流模板");
            return;
        }
        this.x.getAdPlacement().a(iXAdResponseInfo);
        dispatchEvent(new com.baidu.mobads.f.a("AdLoadData"));
    }

    public void q() {
    }

    public void a(View view, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onImpression(view, iXAdInstanceInfo, iXAdFeedsRequestParameters, new HashMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
