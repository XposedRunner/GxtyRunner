package com.example.gita.gxty.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* compiled from: NetConnectUtil */
public class k {
    private static boolean a = false;
    private static String b = "0";

    public static synchronized boolean a() {
        boolean z;
        synchronized (k.class) {
            z = a;
        }
        return z;
    }

    public static synchronized void a(boolean z) {
        synchronized (k.class) {
            a = z;
        }
    }

    public static synchronized void a(String str) {
        synchronized (k.class) {
            b = str;
        }
    }

    public static void a(Context context) {
        String str;
        String str2 = "2";
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            str = "0";
            a(false);
        } else {
            a(true);
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                str = "1";
            } else if (type == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                        str = "2";
                        break;
                    case 13:
                        str = "4";
                        break;
                    default:
                        str = "3";
                        break;
                }
            } else {
                str = str2;
            }
        }
        a(str);
    }
}
