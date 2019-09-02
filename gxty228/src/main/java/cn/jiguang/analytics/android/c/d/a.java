package cn.jiguang.analytics.android.c.d;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.jiguang.analytics.android.c.a.b;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Locale;
import java.util.regex.Pattern;

public final class a {
    private static final String[] z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 20;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "s@#R\u000b\u0005@#W~u7";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x00fa;
            case 1: goto L_0x00fe;
            case 2: goto L_0x0102;
            case 3: goto L_0x0106;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 59;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "Oy/@VI{\u0006IGq{.^\\k4\u0012Par/\u0005IN}8\u0005\u001bN}2\f^L";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "Ir?\u0012TAxu\u0010^Zq2\u0013HAs5Nzk_\u001e3hwK\u0012&rwO\u000f!om";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "E}8@ZLx)\u0005H[<=\u0012TE<\u0015\u0005O_s)\u000brFh>\u0012]I>Z";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\r,i\u0018\u0001";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "_p:\u000e\u000b";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "_u=\t";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "ir?\u0012TAx\u000e\u0014RD";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "@}(0^Zq2\u0013HAs5@^Zn4\u0012\u0001";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "K}52^Ix\u000b\bTFy\b\u0014Z\\y{\u0005IZs)Z";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "Ir?\u0012TAxu\u0010^Zq2\u0013HAs5Nim]\u001f?k`S\u0015%d{H\u001a4~";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "Oy/@VI{\u0006IGq{\u0017RNu\u0016\u0001UI{>\u0012\u001bN}2\f^L<";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "Ir?\u0012TAx{\rZK<:\u0004_Zy(\u0013\u0001";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "eXn";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "oy/@vl){\u0005IZs)";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\bu(@RFj:\fRL<";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "aq>\t\u0001";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "aq>\t\u001b";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "Xt4\u000e^";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "Ds(\u0014\u001bXy)\rR[o2\u000fRF<a@ZFx)\u000fRL2+\u0005IEu(\u0013RGru2~iX\u00040sgR\u001e?h|]\u000f%";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        z = r4;
        return;
    L_0x00fa:
        r9 = 40;
        goto L_0x0020;
    L_0x00fe:
        r9 = 28;
        goto L_0x0020;
    L_0x0102:
        r9 = 91;
        goto L_0x0020;
    L_0x0106:
        r9 = 96;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jiguang.analytics.android.c.d.a.<clinit>():void");
    }

    public static String a(Context context) {
        if (d(context)) {
            CharSequence deviceId = ((TelephonyManager) context.getSystemService(z[18])).getDeviceId();
            if (a(deviceId)) {
                b.b(z[7], new StringBuilder(z[16]).append(deviceId).toString());
                return deviceId;
            }
            b.b(z[7], new StringBuilder(z[17]).append(deviceId).append(z[15]).toString());
            return "";
        }
        b.b(z[7], z[19]);
        return "";
    }

    public static String a(Context context, String str) {
        CharSequence b = b(context);
        if (TextUtils.isEmpty(b)) {
            b = c(context);
        }
        return TextUtils.isEmpty(b) ? str : b;
    }

    public static String a(String str) {
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance(z[13]);
            char[] toCharArray = str.toCharArray();
            byte[] bArr = new byte[toCharArray.length];
            for (int i2 = 0; i2 < toCharArray.length; i2++) {
                bArr[i2] = (byte) toCharArray[i2];
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            int length = digest.length;
            while (i < length) {
                int i3 = digest[i] & 255;
                if (i3 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i3));
                i++;
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            b.b(z[7], z[14]);
            return "";
        }
    }

    private static boolean a(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        try {
            return Pattern.compile(z[0]).matcher(charSequence).matches();
        } catch (Throwable th) {
            return true;
        }
    }

    private static String b(Context context) {
        Throwable e;
        String str = "";
        if (VERSION.SDK_INT >= 23 || !b(context, z[2])) {
            return str;
        }
        String macAddress;
        try {
            macAddress = ((WifiManager) context.getApplicationContext().getSystemService(z[6])).getConnectionInfo().getMacAddress();
            try {
                b.b(z[7], new StringBuilder(z[12]).append(macAddress).toString());
                return macAddress;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            macAddress = str;
            e = th;
            b.a(z[7], z[11], e);
            return macAddress;
        }
    }

    public static boolean b(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            b.c(z[7], new StringBuilder(z[8]).append(th.getMessage()).toString());
            return false;
        }
    }

    private static String c(Context context) {
        Throwable e;
        String str = "";
        if (!(b(context, z[2]) ? ((WifiManager) context.getApplicationContext().getSystemService(z[6])).isWifiEnabled() : false)) {
            return str;
        }
        String stringBuilder;
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                if (z[5].equalsIgnoreCase(networkInterface.getName())) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (!(hardwareAddress == null || hardwareAddress.length == 0)) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        for (byte b : hardwareAddress) {
                            stringBuilder2.append(String.format(Locale.ENGLISH, z[4], new Object[]{Byte.valueOf(b)}));
                        }
                        if (stringBuilder2.length() > 0) {
                            stringBuilder2.deleteCharAt(stringBuilder2.length() - 1);
                        }
                        stringBuilder = stringBuilder2.toString();
                        try {
                            b.b(z[7], new StringBuilder(z[3]).append(stringBuilder).toString());
                            return stringBuilder;
                        } catch (Exception e2) {
                            e = e2;
                        }
                    }
                }
            }
            return str;
        } catch (Throwable e3) {
            Throwable th = e3;
            stringBuilder = str;
            e = th;
            b.a(z[7], z[1], e);
            return stringBuilder;
        }
    }

    private static boolean d(Context context) {
        try {
            return context.getPackageManager().checkPermission(z[10], context.getPackageName()) == 0;
        } catch (Throwable th) {
            b.c(z[7], new StringBuilder(z[9]).append(th.getMessage()).toString());
            return false;
        }
    }
}
