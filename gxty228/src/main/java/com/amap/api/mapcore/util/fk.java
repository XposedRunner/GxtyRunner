package com.amap.api.mapcore.util;

import cn.jiguang.api.utils.ByteBufferUtils;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.tencent.bugly.BuglyStrategy.a;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CoreUtil */
public class fk {
    private static String[] a = new String[]{"com.amap.api.trace", "com.amap.api.trace.core"};

    public static void a(String str, String str2) throws gh {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != null && jSONObject.has("errcode")) {
                a(jSONObject.getInt("errcode"), jSONObject.getString("errmsg"), str2);
            } else if (jSONObject.has("status") && jSONObject.has("infocode")) {
                String string = jSONObject.getString("status");
                int i = jSONObject.getInt("infocode");
                if (!"1".equals(string)) {
                    String string2 = jSONObject.getString("info");
                    if ("0".equals(string)) {
                        a(i, string2, str2);
                    }
                }
            }
        } catch (JSONException e) {
            throw new gh(AMapException.ERROR_PROTOCOL);
        }
    }

    protected static void a(int i, String str, String str2) throws gh {
        switch (i) {
            case 0:
            case ByteBufferUtils.ERROR_CODE /*10000*/:
                return;
            case 10001:
                throw new gh("用户key不正确或过期");
            case GLMapStaticValue.AM_CALLBACK_NEED_NEXTFRAME /*10002*/:
                throw new gh("请求服务不存在");
            case GLMapStaticValue.AM_CALLBACK_INDOOR_NETWORK_ERR /*10003*/:
                throw new gh("访问已超出日访问量");
            case 10004:
                throw new gh("用户访问过于频繁");
            case 10005:
                throw new gh("用户IP无效");
            case 10006:
                throw new gh("用户域名无效");
            case 10007:
                throw new gh("用户签名未通过");
            case 10008:
                throw new gh("用户MD5安全码未通过");
            case 10009:
                throw new gh("请求key与绑定平台不符");
            case 10010:
                throw new gh("IP访问超限");
            case 10011:
                throw new gh("服务不支持https请求");
            case 10012:
                throw new gh("权限不足，服务请求被拒绝");
            case 10013:
                throw new gh("开发者删除了key，key被删除后无法正常使用");
            case 20000:
                throw new gh("请求参数非法");
            case 20001:
                throw new gh("缺少必填参数");
            case 20002:
                throw new gh("请求协议非法");
            case 20003:
                throw new gh("其他未知错误");
            case a.MAX_USERDATA_VALUE_LENGTH /*30000*/:
                throw new gh("请求服务响应错误");
            case 30001:
                throw new gh("引擎返回数据异常");
            case 30002:
                throw new gh("服务端请求链接超时");
            case 30003:
                throw new gh("读取服务结果超时");
            default:
                throw new gh(str);
        }
    }

    public static int a(List<LatLng> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        int i = 0;
        int i2 = 0;
        while (i < list.size() - 1) {
            LatLng latLng = (LatLng) list.get(i);
            LatLng latLng2 = (LatLng) list.get(i + 1);
            if (latLng == null || latLng2 == null) {
                return i2;
            }
            i++;
            i2 = (int) (AMapUtils.calculateLineDistance(latLng, latLng2) + ((float) i2));
        }
        return i2;
    }
}
