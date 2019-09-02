package cn.jiguang.a.a.a;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import cn.jiguang.e.d;
import cn.jiguang.g.a;
import cn.jiguang.g.k;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public final class c {
    private static AtomicInteger a = new AtomicInteger(2);

    static /* synthetic */ String a(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i & 255);
        stringBuffer.append('.');
        stringBuffer.append((i >> 8) & 255);
        stringBuffer.append('.');
        stringBuffer.append((i >> 16) & 255);
        stringBuffer.append('.');
        stringBuffer.append((i >> 24) & 255);
        return stringBuffer.toString();
    }

    static /* synthetic */ String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(bArr[0] & 255);
        stringBuffer.append('.');
        stringBuffer.append(bArr[1] & 255);
        stringBuffer.append('.');
        stringBuffer.append(bArr[2] & 255);
        stringBuffer.append('.');
        stringBuffer.append(bArr[3] & 255);
        return stringBuffer.toString();
    }

    public static void a(Context context) {
        if (a.get() != 2) {
            d.c("ArpUtil", "task is running, please wait for finish.");
            return;
        }
        c(1);
        if (context == null) {
            d.c("ArpUtil", "context is null, give up report MacList.");
            c(2);
        } else if (!a.d(context).toUpperCase().startsWith("WIFI")) {
            d.c("ArpUtil", "current connection type is not wifi, give up report MacList.");
            c(2);
        } else if (!((Boolean) cn.jiguang.d.a.d.b(context, "arpinfo_report_enable", Boolean.valueOf(false))).booleanValue()) {
            d.c("ArpUtil", "enable is false, give up report MacList.");
            c(2);
        } else if (cn.jiguang.d.a.d.e(context)) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(IXAdSystemUtils.NT_WIFI);
            if (wifiManager == null) {
                d.c("ArpUtil", "WifiManager is null, give up report MacList.");
                c(2);
            } else if (a.a(context, "android.permission.ACCESS_WIFI_STATE")) {
                String str;
                String str2;
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                CharSequence charSequence = "";
                CharSequence charSequence2 = "";
                if (connectionInfo != null) {
                    charSequence2 = k.c(connectionInfo.getSSID());
                    charSequence = connectionInfo.getBSSID();
                    d.e("ArpUtil", "ssid: " + charSequence2);
                    d.e("ArpUtil", "bssid: " + charSequence);
                }
                if (TextUtils.isEmpty(charSequence)) {
                    str = "";
                } else {
                    CharSequence charSequence3 = charSequence;
                }
                if (TextUtils.isEmpty(charSequence2)) {
                    str2 = "";
                } else {
                    CharSequence charSequence4 = charSequence2;
                }
                String str3 = TextUtils.isEmpty(str) ? str2 : str;
                boolean b = a.a().b(context, str3);
                a.a();
                int a = (int) (a.a(context) / 1000);
                if (b) {
                    d.e("ArpUtil", "report Frequency: " + a + " seconds");
                    new h(wifiManager, str3, str, str2, context).start();
                    return;
                }
                d.e("ArpUtil", "this network has reported, not repeat in " + a + " seconds");
                c(2);
            } else {
                d.g("ArpUtil", "ACCESS_WIFI_STATE not get");
                c(2);
            }
        } else {
            d.c("ArpUtil", "without uid, give up report MacList.");
            c(2);
        }
    }

    private static Process b(String str) {
        Process process = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                process = Runtime.getRuntime().exec(str);
                try {
                    process.waitFor();
                } catch (InterruptedException e) {
                }
            } catch (IOException e2) {
                d.i("ArpUtil", "do_exec :" + e2.toString());
            }
        }
        return process;
    }

    private static void c(int i) {
        a.getAndSet(i);
    }
}
