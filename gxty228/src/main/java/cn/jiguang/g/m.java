package cn.jiguang.g;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.jiguang.e.d;
import cn.jiguang.f.b;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import java.lang.reflect.InvocationTargetException;

public final class m {
    public static int a(Context context) {
        CharSequence c = c(context);
        if (!TextUtils.isEmpty(c)) {
            if (IXAdSystemUtils.NT_WIFI.equals(c)) {
                return 1;
            }
            if ("2g".equals(c)) {
                return 2;
            }
            if ("3g".equals(c)) {
                return 3;
            }
            if ("4g".equals(c)) {
                return 4;
            }
        }
        return 0;
    }

    public static String a(Context context, int i) {
        Object obj;
        CharSequence charSequence;
        CharSequence c = c(context);
        d.a("TeleonyManagerUtils", "getCurrentNetType - type:" + c);
        if (TextUtils.isEmpty(c)) {
            try {
                Object a = b.a(TelephonyManager.class, "getNetworkClass", Integer.valueOf(i));
                if (((Integer) a).intValue() == 0) {
                    obj = "unknown";
                } else if (((Integer) a).intValue() == 1) {
                    obj = "2g";
                } else if (((Integer) a).intValue() == 2) {
                    obj = "3g";
                } else if (((Integer) a).intValue() == 3) {
                    obj = "4g";
                } else {
                    charSequence = c;
                }
                try {
                    d.c("TeleonyManagerUtils", "step2 - type:" + obj);
                } catch (NoSuchMethodException e) {
                } catch (IllegalAccessException e2) {
                } catch (InvocationTargetException e3) {
                } catch (Exception e4) {
                }
            } catch (NoSuchMethodException e5) {
                charSequence = c;
            } catch (IllegalAccessException e6) {
                charSequence = c;
            } catch (InvocationTargetException e7) {
                charSequence = c;
            } catch (Exception e8) {
                charSequence = c;
            }
        } else {
            charSequence = c;
        }
        return TextUtils.isEmpty(obj) ? "unknown" : obj;
    }

    public static String b(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
        } catch (Exception e) {
            return "";
        }
    }

    public static String c(Context context) {
        String str;
        String str2 = "";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                str = "unknown";
            } else if (activeNetworkInfo.getType() == 1) {
                str = IXAdSystemUtils.NT_WIFI;
            } else {
                if (activeNetworkInfo.getType() == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    d.a("TeleonyManagerUtils", "getNetworkClass networkType:" + subtype);
                    switch (subtype) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            str = "2g";
                            break;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            str = "3g";
                            break;
                        case 13:
                            str = "4g";
                            break;
                        default:
                            if (subtype != 16) {
                                if (subtype != 17) {
                                    if (subtype != 18) {
                                        str = "unknown";
                                        break;
                                    }
                                    str = "4g";
                                    break;
                                }
                                str = "3g";
                                break;
                            }
                            str = "2g";
                            break;
                    }
                }
                str = str2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        d.a("TeleonyManagerUtils", "getCurrentNetType - type:" + str);
        return str;
    }
}
