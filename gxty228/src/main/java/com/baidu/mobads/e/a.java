package com.baidu.mobads.e;

import java.util.HashMap;
import java.util.Map;

public class a {
    private static Map<String, String> a = new HashMap();

    static {
        a.put("100000", "请求格式错误");
        a.put("101000", "请求ID信息缺失");
        a.put("101001", "请求ID不符合约定格式");
        a.put("101002", "请求的trftp信息缺失");
        a.put("101003", "请求的sdk版本信息有误");
        a.put("101004", "请求的referer信息有误");
        a.put("101005", "请求的appid不合法");
        a.put("103000", "应用信息缺失");
        a.put("103010", "应用ID信息缺失");
        a.put("103011", "应用ID信息错误，MSSP未收录");
        a.put("103012", "应用ID无效，MSSP上未生效");
        a.put("103020", "应用ID无效，渠道ID信息错误");
        a.put("103030", "应用版本信息缺失");
        a.put("103040", "应用主版本信息缺失");
        a.put("103050", "应用操作系统信息缺失");
        a.put("103060", "应用包名信息错误，请保证注册包名和实际请求包名一致");
        a.put("104000", "设备信息缺失");
        a.put("104010", "设备类型信息缺失");
        a.put("104011", "设备类型信息错误");
        a.put("104020", "操作系统信息缺失");
        a.put("104021", "操作系统信息错误");
        a.put("104030", "操作系统版本信息缺失");
        a.put("104040", "操作系统主版本信息缺失");
        a.put("104050", "厂商信息缺失");
        a.put("104060", "设备型号信息缺失");
        a.put("104070", "设备唯一标识符缺失");
        a.put("104071", "设备唯一标识符错误");
        a.put("104080", "android id 缺失");
        a.put("104081", "android id 格式错误");
        a.put("104090", "设备屏幕尺寸信息缺失");
        a.put("104100", "设备屏幕尺寸宽度缺失");
        a.put("104110", "设备屏幕尺寸高度缺失");
        a.put("105000", "网络环境信息缺失");
        a.put("105010", "网络地址信息缺失");
        a.put("105011", "网络地址信息格式错误");
        a.put("105020", "网络连接类型缺失");
        a.put("105021", "网络连接类型错误");
        a.put("105030", "网络运营商类型缺失");
        a.put("105031", "网络运营商类型错误");
        a.put("105040", "Wi-Fi热点地址信息缺失");
        a.put("105041", "Wi-Fi热点地址信息格式错误");
        a.put("105050", "Wi-Fi热点信号强度信息缺失");
        a.put("105060", "Wi-Fi热点名称缺失");
        a.put("105070", "Wi-Fi连接状态信息缺失");
        a.put("106000", "坐标类型信息缺失");
        a.put("106001", "坐标类型信息错误");
        a.put("106010", "经度信息缺失");
        a.put("106020", "纬度信息缺失");
        a.put("106030", "定位时间戳信息缺失");
        a.put("107000", "广告位ID缺失");
        a.put("107001", "广告位ID未收录");
        a.put("107002", "广告位ID未启用");
        a.put("107003", "广告位ID与应用ID不匹配");
        a.put("107010", "广告位尺寸信息缺失");
        a.put("107020", "广告位尺寸宽度缺失");
        a.put("107030", "广告位尺寸高度缺失");
        a.put("107040", "广告位信息缺失");
        a.put("107050", "视频广告的网络条件无法满足");
        a.put("107051", "视频标题名称过长");
        a.put("107052", "SDK传递的广告位比例与MSSP的广告位比例不一致");
        a.put("200000", "无广告返回");
        a.put("201000", "无广告数据");
        a.put("201010", "广告无签名");
        a.put("201020", "广告创意类型信息缺失");
        a.put("201021", "广告创意类型信息无法识别");
        a.put("201030", "广告动作类型信息缺失");
        a.put("201031", "广告动作类型信息无法识别");
        a.put("201040", "曝光汇报地址丢失");
        a.put("201050", "点击响应地址丢失");
        a.put("201060", "推广标题丢失");
        a.put("201070", "推广描述丢失");
        a.put("201080", "推广应用包名丢失");
        a.put("201090", "推广应用包大小丢失");
        a.put("201100", "推广图标丢失");
        a.put("201110", "推广图片丢失");
    }

    public static String a(String str) {
        String str2 = (String) a.get(str);
        if (str2 == null) {
            return "";
        }
        return str2;
    }
}
