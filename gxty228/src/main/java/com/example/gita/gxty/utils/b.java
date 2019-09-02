package com.example.gita.gxty.utils;

import android.app.Activity;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.example.gita.gxty.MyApplication;
import com.example.gita.gxty.model.DataBean;
import com.google.gson.e;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/* compiled from: CommonUtils */
public class b {
    public static String a = null;
    private static String b = "lpKK*TJE8WaIg%93O0pfn0#xS0i3xE$z";

    public static String a(float f) {
        return String.format("%.2f", new Object[]{Float.valueOf(Float.parseFloat(String.valueOf(f / 1000.0f)))});
    }

    public static void a(Activity activity) {
        try {
            activity.startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(AMapEngineUtils.MAX_P20_WIDTH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String a() {
        String str = Build.MODEL;
        if (str != null) {
            return str.trim().replaceAll("\\s*", "");
        }
        return "";
    }

    public static String b() {
        if (a == null) {
            a = a(MyApplication.e());
        }
        h.b("UUID:" + a);
        return a;
    }

    private static String a(Application application) {
        String string;
        String str = "";
        try {
            str = ((TelephonyManager) application.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String c = c();
        String str2 = "";
        try {
            string = Secure.getString(application.getContentResolver(), "android_id");
        } catch (Exception e2) {
            e2.printStackTrace();
            string = str2;
        }
        String str3 = "";
        try {
            str2 = ((WifiManager) application.getSystemService(IXAdSystemUtils.NT_WIFI)).getConnectionInfo().getMacAddress();
        } catch (Exception e3) {
            e3.printStackTrace();
            str2 = str3;
        }
        str3 = "";
        try {
            str3 = BluetoothAdapter.getDefaultAdapter().getAddress();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        string = str + c + string + str2 + str3;
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e5) {
            e5.printStackTrace();
        }
        messageDigest.update(string.getBytes(), 0, string.length());
        byte[] digest = messageDigest.digest();
        str = new String();
        for (byte b : digest) {
            int i = b & 255;
            if (i <= 15) {
                str = str + "0";
            }
            str = str + Integer.toHexString(i);
        }
        str2 = str.toUpperCase();
        h.b("myid:" + str2);
        return str2;
    }

    private static String c() {
        String str = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10);
        try {
            return new UUID((long) str.hashCode(), (long) Build.class.getField("SERIAL").get(null).toString().hashCode()).toString();
        } catch (Exception e) {
            return new UUID((long) str.hashCode(), (long) "serial".hashCode()).toString();
        }
    }

    public static void a(Activity activity, String str) throws Exception {
        a(activity.getWindow().getDecorView(), str);
    }

    public static void a(View view, String str) throws Exception {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        if (createBitmap != null) {
            File file = new File(str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            a(createBitmap, str, false, 80);
            return;
        }
        throw new Exception("图片生成失败！！！");
    }

    public static void a(Bitmap bitmap, String str, boolean z, int i) throws Exception {
        if (z) {
            OutputStream fileOutputStream = new FileOutputStream(str);
            bitmap.compress(CompressFormat.JPEG, i, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return;
        }
        fileOutputStream = new FileOutputStream(str);
        bitmap.compress(CompressFormat.PNG, i, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            int length = digest.length;
            int i = 0;
            String str2 = "";
            while (i < length) {
                String toHexString = Integer.toHexString(digest[i] & 255);
                if (toHexString.length() == 1) {
                    toHexString = "0" + toHexString;
                }
                i++;
                str2 = str2 + toHexString;
            }
            return str2;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> DataBean a(T t) {
        DataBean dataBean = new DataBean();
        String str;
        if (t == null) {
            try {
                str = b + "data{}";
                dataBean.data = "{}";
                dataBean.sign = a(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            str = new e().a((Object) t);
            String str2 = b + "data" + str;
            dataBean.data = str;
            dataBean.sign = a(str2);
        }
        h.a(dataBean.data);
        return dataBean;
    }

    public static <T> String b(T t) {
        String str = "";
        if (t != null) {
            return new e().a((Object) t);
        }
        try {
            return "{}";
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static final boolean a(Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            boolean isProviderEnabled = locationManager.isProviderEnabled("gps");
            locationManager.isProviderEnabled("network");
            if (isProviderEnabled) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
