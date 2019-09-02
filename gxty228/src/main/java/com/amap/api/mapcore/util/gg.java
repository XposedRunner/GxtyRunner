package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build.VERSION;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

/* compiled from: ProxyUtil */
public class gg {
    public static Proxy a(Context context) {
        try {
            if (VERSION.SDK_INT >= 11) {
                return a(context, new URI("http://restapi.amap.com"));
            }
            return b(context);
        } catch (Throwable th) {
            gz.c(th, "pu", "gp");
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.net.Proxy b(android.content.Context r12) {
        /*
        r10 = 0;
        r6 = 80;
        r9 = 1;
        r8 = -1;
        r7 = 0;
        r0 = c(r12);
        if (r0 == 0) goto L_0x0146;
    L_0x000c:
        r0 = "content://telephony/carriers/preferapn";
        r1 = android.net.Uri.parse(r0);
        r0 = r12.getContentResolver();
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ SecurityException -> 0x00ab, Throwable -> 0x0115, all -> 0x0133 }
        if (r2 == 0) goto L_0x01b0;
    L_0x0020:
        r0 = r2.moveToFirst();	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
        if (r0 == 0) goto L_0x01b0;
    L_0x0026:
        r0 = "apn";
        r0 = r2.getColumnIndex(r0);	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
        r0 = r2.getString(r0);	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
        if (r0 == 0) goto L_0x0038;
    L_0x0032:
        r1 = java.util.Locale.US;	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
        r0 = r0.toLowerCase(r1);	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
    L_0x0038:
        if (r0 == 0) goto L_0x007e;
    L_0x003a:
        r1 = "ctwap";
        r1 = r0.contains(r1);	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
        if (r1 == 0) goto L_0x007e;
    L_0x0042:
        r3 = a();	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
        r0 = b();	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
        r1 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SecurityException -> 0x0183, Throwable -> 0x016d }
        if (r1 != 0) goto L_0x01b6;
    L_0x0050:
        r1 = "null";
        r1 = r3.equals(r1);	 Catch:{ SecurityException -> 0x0183, Throwable -> 0x016d }
        if (r1 != 0) goto L_0x01b6;
    L_0x0058:
        r1 = r9;
    L_0x0059:
        if (r1 != 0) goto L_0x01b3;
    L_0x005b:
        r1 = "QMTAuMC4wLjIwMA==";
        r1 = a(r1);	 Catch:{ SecurityException -> 0x018b, Throwable -> 0x0172 }
    L_0x0061:
        if (r0 != r8) goto L_0x0064;
    L_0x0063:
        r0 = r6;
    L_0x0064:
        r8 = r0;
        r0 = r1;
    L_0x0066:
        if (r2 == 0) goto L_0x006b;
    L_0x0068:
        r2.close();	 Catch:{ Throwable -> 0x015b }
    L_0x006b:
        r3 = r0;
    L_0x006c:
        r0 = a(r3, r8);	 Catch:{ Throwable -> 0x013b }
        if (r0 == 0) goto L_0x0146;
    L_0x0072:
        r0 = new java.net.Proxy;	 Catch:{ Throwable -> 0x013b }
        r1 = java.net.Proxy.Type.HTTP;	 Catch:{ Throwable -> 0x013b }
        r2 = java.net.InetSocketAddress.createUnresolved(r3, r8);	 Catch:{ Throwable -> 0x013b }
        r0.<init>(r1, r2);	 Catch:{ Throwable -> 0x013b }
    L_0x007d:
        return r0;
    L_0x007e:
        if (r0 == 0) goto L_0x01b0;
    L_0x0080:
        r1 = "wap";
        r0 = r0.contains(r1);	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
        if (r0 == 0) goto L_0x01b0;
    L_0x0088:
        r3 = a();	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
        r1 = b();	 Catch:{ SecurityException -> 0x017d, Throwable -> 0x016a }
        r0 = android.text.TextUtils.isEmpty(r3);	 Catch:{ SecurityException -> 0x0192, Throwable -> 0x0176 }
        if (r0 != 0) goto L_0x01ac;
    L_0x0096:
        r0 = "null";
        r0 = r3.equals(r0);	 Catch:{ SecurityException -> 0x0192, Throwable -> 0x0176 }
        if (r0 != 0) goto L_0x01ac;
    L_0x009e:
        r0 = r9;
    L_0x009f:
        if (r0 != 0) goto L_0x01a9;
    L_0x00a1:
        r0 = "QMTAuMC4wLjE3Mg==";
        r0 = a(r0);	 Catch:{ SecurityException -> 0x0199, Throwable -> 0x017a }
    L_0x00a7:
        if (r1 != r8) goto L_0x01a6;
    L_0x00a9:
        r8 = r6;
        goto L_0x0066;
    L_0x00ab:
        r0 = move-exception;
        r1 = r7;
        r2 = r8;
        r3 = r7;
    L_0x00af:
        r4 = "pu";
        r5 = "ghp";
        com.amap.api.mapcore.util.gz.c(r0, r4, r5);	 Catch:{ all -> 0x0167 }
        r0 = com.amap.api.mapcore.util.gd.s(r12);	 Catch:{ all -> 0x0167 }
        if (r0 == 0) goto L_0x01a3;
    L_0x00bc:
        r2 = java.util.Locale.US;	 Catch:{ all -> 0x0167 }
        r4 = r0.toLowerCase(r2);	 Catch:{ all -> 0x0167 }
        r0 = a();	 Catch:{ all -> 0x0167 }
        r2 = b();	 Catch:{ all -> 0x0167 }
        r5 = "ctwap";
        r5 = r4.indexOf(r5);	 Catch:{ all -> 0x0167 }
        if (r5 == r8) goto L_0x00f4;
    L_0x00d2:
        r4 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0167 }
        if (r4 != 0) goto L_0x00e2;
    L_0x00d8:
        r4 = "null";
        r4 = r0.equals(r4);	 Catch:{ all -> 0x0167 }
        if (r4 != 0) goto L_0x00e2;
    L_0x00e0:
        r10 = r9;
        r3 = r0;
    L_0x00e2:
        if (r10 != 0) goto L_0x00ea;
    L_0x00e4:
        r0 = "QMTAuMC4wLjIwMA==";
        r3 = a(r0);	 Catch:{ all -> 0x0167 }
    L_0x00ea:
        if (r2 != r8) goto L_0x01a3;
    L_0x00ec:
        if (r1 == 0) goto L_0x00f1;
    L_0x00ee:
        r1.close();	 Catch:{ Throwable -> 0x0152 }
    L_0x00f1:
        r8 = r6;
        goto L_0x006c;
    L_0x00f4:
        r5 = "wap";
        r4 = r4.indexOf(r5);	 Catch:{ all -> 0x0167 }
        if (r4 == r8) goto L_0x01a3;
    L_0x00fc:
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x0167 }
        if (r2 != 0) goto L_0x019f;
    L_0x0102:
        r2 = "null";
        r2 = r0.equals(r2);	 Catch:{ all -> 0x0167 }
        if (r2 != 0) goto L_0x019f;
    L_0x010a:
        r2 = r9;
    L_0x010b:
        if (r2 != 0) goto L_0x0113;
    L_0x010d:
        r0 = "QMTAuMC4wLjE3Mg==";
        r0 = a(r0);	 Catch:{ all -> 0x0167 }
    L_0x0113:
        r3 = r0;
        goto L_0x00ec;
    L_0x0115:
        r0 = move-exception;
        r2 = r7;
        r3 = r7;
    L_0x0118:
        r1 = "pu";
        r4 = "gPx1";
        com.amap.api.mapcore.util.gz.c(r0, r1, r4);	 Catch:{ all -> 0x0165 }
        r0.printStackTrace();	 Catch:{ all -> 0x0165 }
        if (r2 == 0) goto L_0x006c;
    L_0x0124:
        r2.close();	 Catch:{ Throwable -> 0x0129 }
        goto L_0x006c;
    L_0x0129:
        r0 = move-exception;
        r1 = "pu";
        r2 = "gPx2";
        com.amap.api.mapcore.util.gz.c(r0, r1, r2);
        goto L_0x006c;
    L_0x0133:
        r0 = move-exception;
        r2 = r7;
    L_0x0135:
        if (r2 == 0) goto L_0x013a;
    L_0x0137:
        r2.close();	 Catch:{ Throwable -> 0x0149 }
    L_0x013a:
        throw r0;
    L_0x013b:
        r0 = move-exception;
        r1 = "pu";
        r2 = "gp2";
        com.amap.api.mapcore.util.gw.a(r0, r1, r2);
        r0.printStackTrace();
    L_0x0146:
        r0 = r7;
        goto L_0x007d;
    L_0x0149:
        r1 = move-exception;
        r2 = "pu";
        r3 = "gPx2";
        com.amap.api.mapcore.util.gz.c(r1, r2, r3);
        goto L_0x013a;
    L_0x0152:
        r0 = move-exception;
        r1 = "pu";
        r2 = "gPx2";
        com.amap.api.mapcore.util.gz.c(r0, r1, r2);
        goto L_0x00f1;
    L_0x015b:
        r1 = move-exception;
        r2 = "pu";
        r3 = "gPx2";
        com.amap.api.mapcore.util.gz.c(r1, r2, r3);
        goto L_0x006b;
    L_0x0165:
        r0 = move-exception;
        goto L_0x0135;
    L_0x0167:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0135;
    L_0x016a:
        r0 = move-exception;
        r3 = r7;
        goto L_0x0118;
    L_0x016d:
        r1 = move-exception;
        r8 = r0;
        r3 = r7;
        r0 = r1;
        goto L_0x0118;
    L_0x0172:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        goto L_0x0118;
    L_0x0176:
        r0 = move-exception;
        r8 = r1;
        r3 = r7;
        goto L_0x0118;
    L_0x017a:
        r0 = move-exception;
        r8 = r1;
        goto L_0x0118;
    L_0x017d:
        r0 = move-exception;
        r1 = r2;
        r3 = r7;
        r2 = r8;
        goto L_0x00af;
    L_0x0183:
        r1 = move-exception;
        r3 = r7;
        r11 = r2;
        r2 = r0;
        r0 = r1;
        r1 = r11;
        goto L_0x00af;
    L_0x018b:
        r1 = move-exception;
        r11 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r11;
        goto L_0x00af;
    L_0x0192:
        r0 = move-exception;
        r3 = r7;
        r11 = r1;
        r1 = r2;
        r2 = r11;
        goto L_0x00af;
    L_0x0199:
        r0 = move-exception;
        r11 = r2;
        r2 = r1;
        r1 = r11;
        goto L_0x00af;
    L_0x019f:
        r2 = r10;
        r0 = r3;
        goto L_0x010b;
    L_0x01a3:
        r6 = r2;
        goto L_0x00ec;
    L_0x01a6:
        r8 = r1;
        goto L_0x0066;
    L_0x01a9:
        r0 = r3;
        goto L_0x00a7;
    L_0x01ac:
        r0 = r10;
        r3 = r7;
        goto L_0x009f;
    L_0x01b0:
        r0 = r7;
        goto L_0x0066;
    L_0x01b3:
        r1 = r3;
        goto L_0x0061;
    L_0x01b6:
        r1 = r10;
        r3 = r7;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.gg.b(android.content.Context):java.net.Proxy");
    }

    public static String a(String str) {
        return gl.c(str);
    }

    private static boolean a(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    private static String a() {
        String defaultHost;
        try {
            defaultHost = android.net.Proxy.getDefaultHost();
        } catch (Throwable th) {
            gz.c(th, "pu", "gdh");
            defaultHost = null;
        }
        if (defaultHost == null) {
            return "null";
        }
        return defaultHost;
    }

    private static Proxy a(Context context, URI uri) {
        if (c(context)) {
            try {
                List select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty()) {
                    return null;
                }
                Proxy proxy = (Proxy) select.get(0);
                return (proxy == null || proxy.type() == Type.DIRECT) ? null : proxy;
            } catch (Throwable th) {
                gz.c(th, "pu", "gpsc");
            }
        }
        return null;
    }

    private static boolean c(Context context) {
        return gd.q(context) == 0;
    }

    private static int b() {
        int i = -1;
        try {
            i = android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            gz.c(th, "pu", "gdp");
        }
        return i;
    }
}
