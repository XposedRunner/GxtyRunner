package com.example.gita.gxty.model;

import android.content.Context;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.NetworkUtils.NetworkType;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.utils.r;
import java.io.Serializable;

public class DataXHAds implements Serializable {
    public String appKey;
    public DataXHDevice device;
    public String slotId;

    public void initDevice(double d, double d2, String str) {
        DataXHDevice dataXHDevice = new DataXHDevice();
        dataXHDevice.app_version = AppUtils.getAppVersionName();
        dataXHDevice.device_id = DeviceUtils.getAndroidID();
        dataXHDevice.imei = PhoneUtils.getIMEI();
        dataXHDevice.idfa = "";
        dataXHDevice.idfv = "";
        dataXHDevice.imsi = PhoneUtils.getIMSI();
        if (r.b(str)) {
            dataXHDevice.ip = str;
        } else {
            dataXHDevice.ip = NetworkUtils.getIPAddress(true);
        }
        dataXHDevice.latitude = d + "";
        dataXHDevice.longitude = d2 + "";
        dataXHDevice.mac = DeviceUtils.getMacAddress();
        dataXHDevice.model = DeviceUtils.getModel();
        NetworkType networkType = NetworkUtils.getNetworkType();
        if (networkType == NetworkType.NETWORK_WIFI) {
            dataXHDevice.net_type = 1;
        } else if (networkType == NetworkType.NETWORK_2G) {
            dataXHDevice.net_type = 2;
        } else if (networkType == NetworkType.NETWORK_3G) {
            dataXHDevice.net_type = 3;
        } else if (networkType == NetworkType.NETWORK_4G) {
            dataXHDevice.net_type = 4;
        } else {
            dataXHDevice.net_type = 1;
        }
        dataXHDevice.operator = getOperator(MyApplication.e());
        dataXHDevice.os = 1;
        dataXHDevice.os_version = VERSION.RELEASE;
        dataXHDevice.package_name = AppUtils.getAppPackageName();
        dataXHDevice.screen_height = ScreenUtils.getScreenHeight() + "";
        dataXHDevice.screen_width = ScreenUtils.getScreenWidth() + "";
        dataXHDevice.user_agent = MyApplication.e().f();
        dataXHDevice.vendor = DeviceUtils.getManufacturer();
        dataXHDevice.height = "600";
        dataXHDevice.width = "200";
        this.device = dataXHDevice;
    }

    public static int getOperator(Context context) {
        Object obj;
        int i;
        String str = "";
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            if (subscriberId == null) {
                h.b("没有获取到sim卡信息");
            } else if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002") || subscriberId.startsWith("46007")) {
                obj = "中国移动";
                i = 1;
                h.b(obj);
                return i;
            } else if (subscriberId.startsWith("46001") || subscriberId.startsWith("46006")) {
                obj = "中国联通";
                i = 2;
                h.b(obj);
                return i;
            } else if (subscriberId.startsWith("46003")) {
                obj = "中国电信";
                i = 3;
                h.b(obj);
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        i = 0;
        String str2 = str;
        h.b(obj);
        return i;
    }
}
