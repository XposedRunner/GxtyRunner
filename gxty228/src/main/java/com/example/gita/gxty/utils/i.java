package com.example.gita.gxty.utils;

import android.content.Context;
import android.provider.Settings.Secure;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.example.gita.gxty.utils.a.a;
import com.example.gita.gxty.utils.a.b;
import com.example.gita.gxty.utils.a.c;
import com.example.gita.gxty.utils.a.d;

/* compiled from: MuGuaUtil */
public class i {
    private static String a = null;
    private static String b = null;

    public static String a() {
        if (a == null) {
            a = EncryptUtils.encryptMD5ToString("system32");
        }
        return a;
    }

    public static String a(Context context) {
        if (b == null) {
            b = c(context);
        }
        return b;
    }

    public static void b(Context context) {
        b = c(context);
    }

    public static String c(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        try {
            if (b.a()) {
                h.b("a1");
                stringBuffer.append("1");
            } else {
                h.b("a2");
                stringBuffer.append("0");
            }
            stringBuffer.append(",");
            if (d(context)) {
                h.b("a3");
                stringBuffer.append("1");
            } else {
                h.b("a4");
                stringBuffer.append("0");
            }
            stringBuffer.append(",");
            String appSignatureSHA1 = AppUtils.getAppSignatureSHA1();
            if ("D9:84:2E:92:51:4B:09:0A:BE:51:39:BB:77:9E:7E:65:5E:5A:1B:DA".equals(appSignatureSHA1)) {
                h.b("a5");
                stringBuffer.append("0");
            } else if ("DD:BB:8E:EF:99:3D:58:C2:2B:A8:31:BC:AA:13:58:C7:A1:E5:6D:68".equals(appSignatureSHA1)) {
                h.b("a6");
                stringBuffer.append("0");
            } else {
                h.b("a7");
                stringBuffer.append("1");
            }
            stringBuffer.append(",");
            if (a.a(context)) {
                h.b("a8");
                stringBuffer.append("1");
            } else {
                h.b("a9");
                stringBuffer.append("0");
            }
            stringBuffer.append(",");
            if (d.b(context) || c.a()) {
                h.b("a10");
                stringBuffer.append("1");
            } else {
                h.b("a11");
                stringBuffer.append("0");
            }
            h.b("MuGua Time:" + (System.currentTimeMillis() - currentTimeMillis) + ":result:" + stringBuffer.toString());
        } catch (Exception e) {
        }
        return stringBuffer.toString();
    }

    public static boolean d(Context context) {
        if (Secure.getString(context.getContentResolver(), "mock_location").equals("0")) {
            return false;
        }
        return true;
    }
}
