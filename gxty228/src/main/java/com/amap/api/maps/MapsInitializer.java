package com.amap.api.maps;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.mapcore.util.fy;
import com.amap.api.mapcore.util.it;
import com.amap.api.mapcore.util.lo;

public final class MapsInitializer {
    public static final int HTTP = 1;
    public static final int HTTPS = 2;
    private static boolean a = true;
    private static boolean b = true;
    private static boolean c = false;
    private static int d = 1;
    public static String sdcardDir = "";

    public static void initialize(Context context) throws RemoteException {
        if (context != null) {
            lo.a = context.getApplicationContext();
        } else {
            Log.w("MapsInitializer", "the context is null");
        }
    }

    public static void setNetWorkEnable(boolean z) {
        a = z;
    }

    public static boolean getNetWorkEnable() {
        return a;
    }

    public static void setApiKey(String str) {
        if (str != null && str.trim().length() > 0) {
            fy.a(lo.a, str);
        }
    }

    public static String getVersion() {
        return "6.4.1";
    }

    public static void loadWorldGridMap(boolean z) {
        c = z;
    }

    public static boolean isLoadWorldGridMap() {
        return c;
    }

    public static void setBuildingHeight(int i) {
    }

    public static void setDownloadCoordinateConvertLibrary(boolean z) {
        b = z;
    }

    public static boolean isDownloadCoordinateConvertLibrary() {
        return b;
    }

    public static void setHost(String str) {
        if (TextUtils.isEmpty(str)) {
            it.a = -1;
            it.b = "";
            return;
        }
        it.a = 1;
        it.b = str;
    }

    public static void setProtocol(int i) {
        d = i;
    }

    public static int getProtocol() {
        return d;
    }
}
