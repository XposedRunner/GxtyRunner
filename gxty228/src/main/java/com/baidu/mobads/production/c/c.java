package com.baidu.mobads.production.c;

import android.content.Context;
import android.view.View;
import com.baidu.mobad.feeds.a;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.g.q;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.feeds.IXAdDummyContainer;
import com.baidu.mobads.interfaces.feeds.IXAdFeedsRequestParameters;
import com.baidu.mobads.production.b;
import com.baidu.mobads.production.v;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.g;
import com.baidu.mobads.utils.m;
import com.baidu.mobads.vo.d;
import com.qq.e.comm.constants.ErrorCode.OtherError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class c extends b {
    private d w;
    private ArrayList<IXAdInstanceInfo> x;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return r();
    }

    public c(Context context) {
        super(context);
    }

    public c(Context context, String str) {
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(null);
        this.o = SlotType.SLOT_TYPE_FEEDS;
        this.w = new d(getApplicationContext(), getActivity(), this.o);
        g adConstants = XAdSDKFoundationFacade.getInstance().getAdConstants();
        List arrayList = new ArrayList();
        arrayList.add(adConstants.getSupportedActionType4RequestingNone());
        arrayList.add(adConstants.getSupportedActionType4RequestingLandingPage());
        arrayList.add(adConstants.a());
        XAdSDKFoundationFacade.getInstance().getPackageUtils();
        if (m.b(context)) {
            arrayList.add(adConstants.getSupportedActionType4RequestingDownload());
        }
        this.w.b(XAdSDKFoundationFacade.getInstance().getCommonUtils().a(arrayList));
        this.w.d((int) OtherError.CONTAINER_INVISIBLE_ERROR);
        this.w.e(500);
        this.w.h(0);
        this.w.d(str);
        this.w.f(AdSize.FeedNative.getValue());
        this.w.g(1);
        this.w.i(XAdSDKFoundationFacade.getInstance().getAdConstants().getAdCreativeTypeImage());
    }

    public void a(a aVar) {
        int a = aVar.a();
        int b = aVar.b();
        if (a > 0 && b > 0) {
            this.w.d(a);
            this.w.e(b);
        }
    }

    protected void h() {
        this.m = 8000;
    }

    public void request() {
        super.a(this.w);
    }

    protected void a(com.baidu.mobads.openad.d.b bVar, v vVar, int i) {
        vVar.a(bVar, (double) i);
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        iXAdContainer.start();
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        this.x = iXAdContainer.getAdContainerContext().getAdResponseInfo().getAdInstanceList();
    }

    public ArrayList<IXAdInstanceInfo> q() {
        return this.x;
    }

    public d r() {
        return this.w;
    }

    public void a(View view, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onImpression(view, iXAdInstanceInfo, iXAdFeedsRequestParameters, a(-1, iXAdInstanceInfo.getThirdImpressionTrackingUrls()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(iXAdInstanceInfo.getHtmlSnippet());
            stringBuffer.append("_&_");
            stringBuffer.append(iXAdInstanceInfo.getQueryKey());
            stringBuffer.append("_&_");
            stringBuffer.append(iXAdInstanceInfo.getAdId());
            stringBuffer.append("_&_");
            stringBuffer.append(iXAdInstanceInfo.getMainPictureUrl());
            stringBuffer.append("_&_");
            stringBuffer.append(iXAdInstanceInfo.getTitle());
            stringBuffer.append("_&_");
            q.a = stringBuffer.toString();
        } catch (Exception e) {
        }
        try {
            return ((IXAdDummyContainer) this.h).isAdAvailable(context, iXAdInstanceInfo, iXAdFeedsRequestParameters);
        } catch (Exception e2) {
            return false;
        }
    }

    public void b(View view, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        a(view, iXAdInstanceInfo, -1, iXAdFeedsRequestParameters);
    }

    public void a(View view, IXAdInstanceInfo iXAdInstanceInfo, int i, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onClick(view, iXAdInstanceInfo, i, iXAdFeedsRequestParameters, a(i, iXAdInstanceInfo.getThirdClickTrackingUrls()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onStart(context, iXAdInstanceInfo, iXAdFeedsRequestParameters, a(0, iXAdInstanceInfo.getStartTrackers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Context context, int i, int i2, IXAdInstanceInfo iXAdInstanceInfo) {
    }

    public void c(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onComplete(context, iXAdInstanceInfo, iXAdFeedsRequestParameters, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Context context, int i, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onClose(context, iXAdInstanceInfo, iXAdFeedsRequestParameters, a(i, iXAdInstanceInfo.getCloseTrackers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void d(Context context, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onCstartcard(context, iXAdInstanceInfo, iXAdFeedsRequestParameters, a(0, iXAdInstanceInfo.getCstartcardTrackers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(Context context, int i, IXAdInstanceInfo iXAdInstanceInfo, IXAdFeedsRequestParameters iXAdFeedsRequestParameters) {
        try {
            ((IXAdDummyContainer) this.h).onFullScreen(context, i, iXAdInstanceInfo, iXAdFeedsRequestParameters, a(i, iXAdInstanceInfo.getFullScreenTrackers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> a(int i, List<String> list) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(XAdSDKFoundationFacade.getInstance().getAdConstants().feedsTrackerParameterKeyProgress(), Integer.valueOf(i));
        hashMap.put(XAdSDKFoundationFacade.getInstance().getAdConstants().feedsTrackerParameterKeyList(), list);
        return hashMap;
    }

    public void g() {
        this.h.load();
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }
}
