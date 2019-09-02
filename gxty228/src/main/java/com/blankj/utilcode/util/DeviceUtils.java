package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.provider.Settings.Secure;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.blankj.utilcode.util.ShellUtils.CommandResult;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public final class DeviceUtils {
    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isDeviceRooted() {
        String str = "su";
        for (String str2 : new String[]{"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/"}) {
            if (new File(str2 + str).exists()) {
                return true;
            }
        }
        return false;
    }

    public static String getSDKVersionName() {
        return VERSION.RELEASE;
    }

    public static int getSDKVersionCode() {
        return VERSION.SDK_INT;
    }

    @SuppressLint({"HardwareIds"})
    public static String getAndroidID() {
        String string = Secure.getString(Utils.getApp().getContentResolver(), "android_id");
        return string == null ? "" : string;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static String getMacAddress() {
        return getMacAddress((String[]) null);
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static String getMacAddress(String... strArr) {
        String macAddressByWifiInfo = getMacAddressByWifiInfo();
        if (isAddressNotInExcepts(macAddressByWifiInfo, strArr)) {
            return macAddressByWifiInfo;
        }
        macAddressByWifiInfo = getMacAddressByNetworkInterface();
        if (isAddressNotInExcepts(macAddressByWifiInfo, strArr)) {
            return macAddressByWifiInfo;
        }
        macAddressByWifiInfo = getMacAddressByInetAddress();
        if (isAddressNotInExcepts(macAddressByWifiInfo, strArr)) {
            return macAddressByWifiInfo;
        }
        macAddressByWifiInfo = getMacAddressByFile();
        return !isAddressNotInExcepts(macAddressByWifiInfo, strArr) ? "" : macAddressByWifiInfo;
    }

    private static boolean isAddressNotInExcepts(String str, String... strArr) {
        if (strArr != null && strArr.length != 0) {
            for (Object equals : strArr) {
                if (str.equals(equals)) {
                    return false;
                }
            }
            return true;
        } else if ("02:00:00:00:00:00".equals(str)) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static String getMacAddressByWifiInfo() {
        try {
            WifiManager wifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService(IXAdSystemUtils.NT_WIFI);
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    return connectionInfo.getMacAddress();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByNetworkInterface() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                if (networkInterface != null && networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress != null && hardwareAddress.length > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                        }
                        return stringBuilder.substring(0, stringBuilder.length() - 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByInetAddress() {
        try {
            InetAddress inetAddress = getInetAddress();
            if (inetAddress != null) {
                NetworkInterface byInetAddress = NetworkInterface.getByInetAddress(inetAddress);
                if (byInetAddress != null) {
                    byte[] hardwareAddress = byInetAddress.getHardwareAddress();
                    if (hardwareAddress != null && hardwareAddress.length > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                        }
                        return stringBuilder.substring(0, stringBuilder.length() - 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static InetAddress getInetAddress() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                if (networkInterface.isUp()) {
                    Enumeration inetAddresses = networkInterface.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(58) < 0) {
                            return inetAddress;
                        }
                    }
                    continue;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMacAddressByFile() {
        CommandResult execCmd = ShellUtils.execCmd("getprop wifi.interface", false);
        if (execCmd.result == 0) {
            String str = execCmd.successMsg;
            if (str != null) {
                execCmd = ShellUtils.execCmd("cat /sys/class/net/" + str + "/address", false);
                if (execCmd.result == 0) {
                    str = execCmd.successMsg;
                    if (str != null && str.length() > 0) {
                        return str;
                    }
                }
            }
        }
        return "02:00:00:00:00:00";
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        String str = Build.MODEL;
        if (str != null) {
            return str.trim().replaceAll("\\s*", "");
        }
        return "";
    }

    public static String[] getABIs() {
        if (VERSION.SDK_INT >= 21) {
            return Build.SUPPORTED_ABIS;
        }
        if (TextUtils.isEmpty(Build.CPU_ABI2)) {
            return new String[]{Build.CPU_ABI};
        }
        return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    public static void shutdown() {
        ShellUtils.execCmd("reboot -p", true);
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        Utils.getApp().startActivity(intent.addFlags(AMapEngineUtils.MAX_P20_WIDTH));
    }

    public static void reboot() {
        ShellUtils.execCmd("reboot", true);
        Intent intent = new Intent("android.intent.action.REBOOT");
        intent.putExtra("nowait", 1);
        intent.putExtra("interval", 1);
        intent.putExtra("window", 0);
        Utils.getApp().sendBroadcast(intent);
    }

    public static void reboot(String str) {
        ((PowerManager) Utils.getApp().getSystemService("power")).reboot(str);
    }

    public static void reboot2Recovery() {
        ShellUtils.execCmd("reboot recovery", true);
    }

    public static void reboot2Bootloader() {
        ShellUtils.execCmd("reboot bootloader", true);
    }
}
