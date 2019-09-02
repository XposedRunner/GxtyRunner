package com.blankj.utilcode.util;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.blankj.utilcode.util.ShellUtils.CommandResult;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class NetworkUtils {

    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void openWirelessSettings() {
        Utils.getApp().startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(AMapEngineUtils.MAX_P20_WIDTH));
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByPing() {
        return isAvailableByPing(null);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByPing(String str) {
        boolean z = true;
        if (str == null || str.length() <= 0) {
            str = "223.5.5.5";
        }
        CommandResult execCmd = ShellUtils.execCmd(String.format("ping -c 1 %s", new Object[]{str}), false);
        if (execCmd.result != 0) {
            z = false;
        }
        if (execCmd.errorMsg != null) {
            Log.d("NetworkUtils", "isAvailableByPing() called" + execCmd.errorMsg);
        }
        if (execCmd.successMsg != null) {
            Log.d("NetworkUtils", "isAvailableByPing() called" + execCmd.successMsg);
        }
        return z;
    }

    public static boolean getMobileDataEnabled() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
            if (VERSION.SDK_INT >= 26) {
                return telephonyManager.isDataEnabled();
            }
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
            if (declaredMethod != null) {
                return ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresPermission("android.permission.MODIFY_PHONE_STATE")
    public static void setMobileDataEnabled(boolean z) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("setDataEnabled", new Class[]{Boolean.TYPE});
            if (declaredMethod != null) {
                declaredMethod.invoke(telephonyManager, new Object[]{Boolean.valueOf(z)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isMobileData() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 0;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean is4G() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 13;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static boolean getWifiEnabled() {
        return ((WifiManager) Utils.getApp().getSystemService(IXAdSystemUtils.NT_WIFI)).isWifiEnabled();
    }

    @RequiresPermission("android.permission.CHANGE_WIFI_STATE")
    public static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(IXAdSystemUtils.NT_WIFI);
        if (z != wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(z);
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().getType() == 1;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static boolean isWifiAvailable() {
        return getWifiEnabled() && isAvailableByPing();
    }

    public static String getNetworkOperatorName() {
        return ((TelephonyManager) Utils.getApp().getSystemService("phone")).getNetworkOperatorName();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static NetworkType getNetworkType() {
        NetworkType networkType = NetworkType.NETWORK_NO;
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return networkType;
        }
        if (activeNetworkInfo.getType() == 9) {
            return NetworkType.NETWORK_ETHERNET;
        }
        if (activeNetworkInfo.getType() == 1) {
            return NetworkType.NETWORK_WIFI;
        }
        if (activeNetworkInfo.getType() != 0) {
            return NetworkType.NETWORK_UNKNOWN;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkType.NETWORK_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetworkType.NETWORK_3G;
            case 13:
            case 18:
                return NetworkType.NETWORK_4G;
            default:
                String subtypeName = activeNetworkInfo.getSubtypeName();
                if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                    return NetworkType.NETWORK_3G;
                }
                return NetworkType.NETWORK_UNKNOWN;
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static NetworkInfo getActiveNetworkInfo() {
        return ((ConnectivityManager) Utils.getApp().getSystemService("connectivity")).getActiveNetworkInfo();
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String getIPAddress(boolean z) {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            LinkedList linkedList = new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                    Enumeration inetAddresses = networkInterface.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        linkedList.addFirst(inetAddresses.nextElement());
                    }
                }
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                InetAddress inetAddress = (InetAddress) it.next();
                if (!inetAddress.isLoopbackAddress()) {
                    Object obj;
                    String hostAddress = inetAddress.getHostAddress();
                    if (hostAddress.indexOf(58) < 0) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (z) {
                        if (obj != null) {
                            return hostAddress;
                        }
                    } else if (obj == null) {
                        int indexOf = hostAddress.indexOf(37);
                        if (indexOf < 0) {
                            return hostAddress.toUpperCase();
                        }
                        return hostAddress.substring(0, indexOf).toUpperCase();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getBroadcastIpAddress() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            LinkedList linkedList = new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                    List interfaceAddresses = networkInterface.getInterfaceAddresses();
                    for (int i = 0; i < interfaceAddresses.size(); i++) {
                        InetAddress broadcast = ((InterfaceAddress) interfaceAddresses.get(i)).getBroadcast();
                        if (broadcast != null) {
                            return broadcast.getHostAddress();
                        }
                    }
                    continue;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String getDomainAddress(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getIpAddressByWifi() {
        return Formatter.formatIpAddress(((WifiManager) Utils.getApp().getSystemService(IXAdSystemUtils.NT_WIFI)).getDhcpInfo().ipAddress);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getGatewayByWifi() {
        return Formatter.formatIpAddress(((WifiManager) Utils.getApp().getSystemService(IXAdSystemUtils.NT_WIFI)).getDhcpInfo().gateway);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getNetMaskByWifi() {
        return Formatter.formatIpAddress(((WifiManager) Utils.getApp().getSystemService(IXAdSystemUtils.NT_WIFI)).getDhcpInfo().netmask);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getServerAddressByWifi() {
        return Formatter.formatIpAddress(((WifiManager) Utils.getApp().getSystemService(IXAdSystemUtils.NT_WIFI)).getDhcpInfo().serverAddress);
    }
}
