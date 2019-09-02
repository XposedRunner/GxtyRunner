package com.baidu.mobads.production.b;

import android.content.Context;
import android.widget.RelativeLayout;
import cn.jiguang.api.utils.ByteBufferUtils;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.baidu.mobads.f.a;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.production.v;
import com.baidu.mobads.vo.c;
import com.baidu.mobads.vo.d;
import java.util.HashMap;

public class b extends com.baidu.mobads.production.b implements IXNonLinearAdSlot {
    private d w;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return q();
    }

    public b(Context context, RelativeLayout relativeLayout, String str, String str2) {
        super(context);
        setActivity(context);
        setAdSlotBase(relativeLayout);
        this.o = SlotType.SLOT_TYPE_CPU;
        this.w = new d(getApplicationContext(), getActivity(), this.o, str, str2);
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
            setAdResponseInfo(new c("{'ad':[{'id':99999999,'html':'" + this.w.c() + "', type='" + CreativeType.HTML.getValue() + "'}],'n':1}"));
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

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.p();
        dispatchEvent(new a(IXAdEvent.AD_USER_CLOSE));
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }
}
