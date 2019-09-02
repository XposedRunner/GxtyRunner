package com.amap.api.mapcore.util;

import android.support.v4.view.PointerIconCompat;
import com.amap.api.maps.AMapException;

/* compiled from: AMapException */
public class gh extends Exception {
    private String a = "";
    private int b = 1000;

    public gh(String str) {
        super(str);
        this.a = str;
        a(str);
    }

    public String a() {
        return this.a;
    }

    private void a(String str) {
        if ("用户签名未通过".equals(str)) {
            this.b = 1001;
        } else if ("用户key不正确或过期".equals(str)) {
            this.b = 1002;
        } else if ("请求服务不存在".equals(str)) {
            this.b = PointerIconCompat.TYPE_HELP;
        } else if ("访问已超出日访问量".equals(str)) {
            this.b = 1004;
        } else if ("用户访问过于频繁".equals(str)) {
            this.b = 1005;
        } else if ("用户IP无效".equals(str)) {
            this.b = PointerIconCompat.TYPE_CELL;
        } else if ("用户域名无效".equals(str)) {
            this.b = PointerIconCompat.TYPE_CROSSHAIR;
        } else if ("用户MD5安全码未通过".equals(str)) {
            this.b = PointerIconCompat.TYPE_TEXT;
        } else if ("请求key与绑定平台不符".equals(str)) {
            this.b = PointerIconCompat.TYPE_VERTICAL_TEXT;
        } else if ("IP访问超限".equals(str)) {
            this.b = PointerIconCompat.TYPE_ALIAS;
        } else if ("服务不支持https请求".equals(str)) {
            this.b = 1011;
        } else if ("权限不足，服务请求被拒绝".equals(str)) {
            this.b = PointerIconCompat.TYPE_NO_DROP;
        } else if ("开发者删除了key，key被删除后无法正常使用".equals(str)) {
            this.b = PointerIconCompat.TYPE_ALL_SCROLL;
        } else if ("请求服务响应错误".equals(str)) {
            this.b = 1100;
        } else if ("引擎返回数据异常".equals(str)) {
            this.b = 1101;
        } else if ("服务端请求链接超时".equals(str)) {
            this.b = 1102;
        } else if ("读取服务结果超时".equals(str)) {
            this.b = 1103;
        } else if ("请求参数非法".equals(str)) {
            this.b = 1200;
        } else if ("缺少必填参数".equals(str)) {
            this.b = 1201;
        } else if ("请求协议非法".equals(str)) {
            this.b = 1202;
        } else if ("其他未知错误".equals(str)) {
            this.b = 1203;
        } else if (AMapException.ERROR_PROTOCOL.equals(str)) {
            this.b = 1801;
        } else if (AMapException.ERROR_SOCKE_TIME_OUT.equals(str)) {
            this.b = 1802;
        } else if (AMapException.ERROR_URL.equals(str)) {
            this.b = 1803;
        } else if (AMapException.ERROR_UNKNOW_HOST.equals(str)) {
            this.b = 1804;
        } else if ("未知错误".equals(str)) {
            this.b = 1900;
        } else if (AMapException.ERROR_INVALID_PARAMETER.equals(str)) {
            this.b = 1901;
        } else if ("http或socket连接失败 - ConnectionException".equals(str)) {
            this.b = 1806;
        } else if (AMapException.ERROR_IO.equals(str)) {
            this.b = 1902;
        } else if (AMapException.ERROR_NULL_PARAMETER.equals(str)) {
            this.b = 1903;
        } else {
            this.b = 1800;
        }
    }
}
