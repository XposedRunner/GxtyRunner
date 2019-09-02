package com.baidu.mobads.vo.a;

import android.content.Context;
import cn.jiguang.net.HttpUtils;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.l;
import com.baidu.mobads.vo.a.a.a;
import java.util.HashMap;
import java.util.Map;

public class d extends a {
    private String o = "";
    private HashMap<String, String> p = null;

    public d(String str, IXAdInstanceInfo iXAdInstanceInfo, IXAdProdInfo iXAdProdInfo, HashMap<String, String> hashMap) {
        super(new a(iXAdInstanceInfo, iXAdProdInfo));
        this.o = str;
        this.p = hashMap;
    }

    protected HashMap<String, String> b() {
        if (this.p == null) {
            this.p = new HashMap();
        }
        return this.p;
    }

    public String a(Context context) {
        Map c = c();
        try {
            StringBuilder stringBuilder = new StringBuilder("type=" + this.o + HttpUtils.PARAMETERS_SEPARATOR);
            StringBuilder stringBuilder2 = new StringBuilder();
            IXAdCommonUtils commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
            for (String str : c.keySet()) {
                String str2;
                String str3 = (String) c.get(str2);
                if (!(str2 == null || str3 == null)) {
                    str2 = commonUtils.encodeURIComponent(str2);
                    str3 = commonUtils.encodeURIComponent(str3);
                    stringBuilder.append(str2);
                    stringBuilder.append(HttpUtils.EQUAL_SIGN);
                    stringBuilder.append(str3);
                    stringBuilder.append(HttpUtils.PARAMETERS_SEPARATOR);
                    stringBuilder2.append(str3);
                    stringBuilder2.append(",");
                }
            }
            return "https://mobads-logs.baidu.com/dz.zb" + HttpUtils.URL_AND_PARA_SEPARATOR + stringBuilder.toString();
        } catch (Throwable e) {
            l.a().d(e);
            return "";
        }
    }
}
