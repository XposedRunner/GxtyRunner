package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.RequiresPermission;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.AMapEngineUtils;

public final class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isPhone() {
        return ((TelephonyManager) Utils.getApp().getSystemService("phone")).getPhoneType() != 0;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        if (VERSION.SDK_INT < 26) {
            return telephonyManager.getDeviceId();
        }
        CharSequence imei = telephonyManager.getImei();
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        }
        String meid = telephonyManager.getMeid();
        if (TextUtils.isEmpty(meid)) {
            return "";
        }
        return meid;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getSerial() {
        return VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        if (VERSION.SDK_INT >= 26) {
            return telephonyManager.getImei();
        }
        return telephonyManager.getDeviceId();
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getMEID() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        if (VERSION.SDK_INT >= 26) {
            return telephonyManager.getMeid();
        }
        return telephonyManager.getDeviceId();
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getIMSI() {
        return ((TelephonyManager) Utils.getApp().getSystemService("phone")).getSubscriberId();
    }

    public static int getPhoneType() {
        return ((TelephonyManager) Utils.getApp().getSystemService("phone")).getPhoneType();
    }

    public static boolean isSimCardReady() {
        return ((TelephonyManager) Utils.getApp().getSystemService("phone")).getSimState() == 5;
    }

    public static String getSimOperatorName() {
        return ((TelephonyManager) Utils.getApp().getSystemService("phone")).getSimOperatorName();
    }

    public static String getSimOperatorByMnc() {
        String simOperator = ((TelephonyManager) Utils.getApp().getSystemService("phone")).getSimOperator();
        if (simOperator == null) {
            return "";
        }
        Object obj = -1;
        switch (simOperator.hashCode()) {
            case 49679470:
                if (simOperator.equals("46000")) {
                    obj = null;
                    break;
                }
                break;
            case 49679471:
                if (simOperator.equals("46001")) {
                    obj = 4;
                    break;
                }
                break;
            case 49679472:
                if (simOperator.equals("46002")) {
                    obj = 1;
                    break;
                }
                break;
            case 49679473:
                if (simOperator.equals("46003")) {
                    obj = 7;
                    break;
                }
                break;
            case 49679475:
                if (simOperator.equals("46005")) {
                    obj = 8;
                    break;
                }
                break;
            case 49679476:
                if (simOperator.equals("46006")) {
                    obj = 5;
                    break;
                }
                break;
            case 49679477:
                if (simOperator.equals("46007")) {
                    obj = 2;
                    break;
                }
                break;
            case 49679479:
                if (simOperator.equals("46009")) {
                    obj = 6;
                    break;
                }
                break;
            case 49679502:
                if (simOperator.equals("46011")) {
                    obj = 9;
                    break;
                }
                break;
            case 49679532:
                if (simOperator.equals("46020")) {
                    obj = 3;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
            case 1:
            case 2:
            case 3:
                return "中国移动";
            case 4:
            case 5:
            case 6:
                return "中国联通";
            case 7:
            case 8:
            case 9:
                return "中国电信";
            default:
                return simOperator;
        }
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getPhoneStatus() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        return (((((((((((((("" + "DeviceId(IMEI) = " + telephonyManager.getDeviceId() + "\n") + "DeviceSoftwareVersion = " + telephonyManager.getDeviceSoftwareVersion() + "\n") + "Line1Number = " + telephonyManager.getLine1Number() + "\n") + "NetworkCountryIso = " + telephonyManager.getNetworkCountryIso() + "\n") + "NetworkOperator = " + telephonyManager.getNetworkOperator() + "\n") + "NetworkOperatorName = " + telephonyManager.getNetworkOperatorName() + "\n") + "NetworkType = " + telephonyManager.getNetworkType() + "\n") + "PhoneType = " + telephonyManager.getPhoneType() + "\n") + "SimCountryIso = " + telephonyManager.getSimCountryIso() + "\n") + "SimOperator = " + telephonyManager.getSimOperator() + "\n") + "SimOperatorName = " + telephonyManager.getSimOperatorName() + "\n") + "SimSerialNumber = " + telephonyManager.getSimSerialNumber() + "\n") + "SimState = " + telephonyManager.getSimState() + "\n") + "SubscriberId(IMSI) = " + telephonyManager.getSubscriberId() + "\n") + "VoiceMailNumber = " + telephonyManager.getVoiceMailNumber() + "\n";
    }

    public static void dial(String str) {
        Utils.getApp().startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)).addFlags(AMapEngineUtils.MAX_P20_WIDTH));
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    public static void call(String str) {
        Utils.getApp().startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + str)).addFlags(AMapEngineUtils.MAX_P20_WIDTH));
    }

    public static void sendSms(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", str2);
        Utils.getApp().startActivity(intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH));
    }

    @RequiresPermission("android.permission.SEND_SMS")
    public static void sendSmsSilent(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            PendingIntent broadcast = PendingIntent.getBroadcast(Utils.getApp(), 0, new Intent("send"), 0);
            SmsManager smsManager = SmsManager.getDefault();
            if (str2.length() >= 70) {
                for (String sendTextMessage : smsManager.divideMessage(str2)) {
                    smsManager.sendTextMessage(str, null, sendTextMessage, broadcast, null);
                }
                return;
            }
            smsManager.sendTextMessage(str, null, str2, broadcast, null);
        }
    }
}
