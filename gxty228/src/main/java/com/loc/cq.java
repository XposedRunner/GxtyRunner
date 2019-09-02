package com.loc;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ReportUtil */
public final class cq {
    static AMapLocation g = null;
    private static List<ay> h = new ArrayList();
    private static JSONArray i = null;
    public SparseArray<Long> a = new SparseArray();
    public int b = -1;
    public long c = 0;
    String[] d = new String[]{"ol", "cl", "gl", "ha", "bs", "ds"};
    public int e = -1;
    public long f = -1;

    public static void a(Context context) {
        if (context != null) {
            try {
                if (ck.o()) {
                    if (h != null && h.size() > 0) {
                        List arrayList = new ArrayList();
                        arrayList.addAll(h);
                        az.a(arrayList, context);
                        h.clear();
                    }
                    f(context);
                }
            } catch (Throwable th) {
                cl.a(th, "ReportUtil", "destroy");
            }
        }
    }

    public static void a(Context context, int i, int i2, long j, long j2) {
        if (i != -1 && i2 != -1) {
            try {
                String str = "O012";
                if (context == null) {
                    return;
                }
                if (ck.o()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("param_int_first", i);
                    jSONObject.put("param_int_second", i2);
                    jSONObject.put("param_long_first", j);
                    jSONObject.put("param_long_second", j2);
                    a(context, str, jSONObject);
                }
            } catch (Throwable th) {
                cl.a(th, "ReportUtil", "reportServiceAliveTime");
            }
        }
    }

    public static void a(Context context, long j, boolean z) {
        if (context != null) {
            try {
                if (ck.o()) {
                    int intValue = Long.valueOf(j).intValue();
                    String str = "domestic";
                    if (!z) {
                        str = "abroad";
                    }
                    a(context, "O015", str, null, intValue, Integer.MAX_VALUE);
                }
            } catch (Throwable th) {
                cl.a(th, "ReportUtil", "reportGPSLocUseTime");
            }
        }
    }

    public static void a(Context context, AMapLocation aMapLocation) {
        int i = 0;
        if (aMapLocation != null) {
            try {
                if (!"GPS".equalsIgnoreCase(aMapLocation.getProvider()) && aMapLocation.getLocationType() != 1) {
                    String str;
                    String str2 = "domestic";
                    if (a(aMapLocation)) {
                        str2 = "abroad";
                    }
                    if (aMapLocation.getErrorCode() == 0) {
                        switch (aMapLocation.getLocationType()) {
                            case 5:
                            case 6:
                                str = "net";
                                i = 1;
                                break;
                            default:
                                str = "cache";
                                i = 1;
                                break;
                        }
                    }
                    switch (aMapLocation.getErrorCode()) {
                        case 4:
                        case 5:
                        case 6:
                        case 11:
                            str = "net";
                            break;
                        default:
                            str = "cache";
                            break;
                    }
                    a(context, "O016", str, str2, i, Integer.MAX_VALUE);
                }
            } catch (Throwable th) {
                cl.a(th, "ReportUtil", "reportBatting");
            }
        }
    }

    public static void a(Context context, cp cpVar) {
        if (context != null) {
            try {
                if (ck.o()) {
                    AMapLocation c = cpVar.c();
                    if (ct.a((AMapLocationServer) c) && !"GPS".equalsIgnoreCase(c.getProvider()) && c.getLocationType() != 1) {
                        String str;
                        int intValue = Long.valueOf(cpVar.b() - cpVar.a()).intValue();
                        Object obj = null;
                        int intValue2 = Long.valueOf(c.k()).intValue();
                        switch (c.getLocationType()) {
                            case 5:
                            case 6:
                                str = "net";
                                break;
                            default:
                                str = "cache";
                                obj = 1;
                                break;
                        }
                        String str2 = "domestic";
                        if (a(c)) {
                            str2 = "abroad";
                        }
                        if (obj == null) {
                            a(context, "O014", str2, null, intValue2, intValue);
                        }
                        a(context, "O013", str, str2, intValue, Integer.MAX_VALUE);
                    }
                }
            } catch (Throwable th) {
                cl.a(th, "ReportUtil", "reportLBSLocUseTime");
            }
        }
    }

    public static void a(Context context, String str, int i) {
        try {
            a(context, "O009", i, str);
        } catch (Throwable th) {
            cl.a(th, "ReportUtil", "reportDexLoadDexClass");
        }
    }

    private static void a(Context context, String str, int i, String str2) {
        if (context != null) {
            try {
                if (ck.o()) {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("param_string_first", str2);
                    }
                    if (i != Integer.MAX_VALUE) {
                        jSONObject.put("param_int_first", i);
                    }
                    a(context, str, jSONObject);
                }
            } catch (Throwable th) {
                cl.a(th, "ReportUtil", "applyStatisticsEx");
            }
        }
    }

    private static void a(Context context, String str, String str2, String str3, int i, int i2) {
        if (context != null) {
            try {
                if (ck.o()) {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("param_string_first", str2);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        jSONObject.put("param_string_second", str3);
                    }
                    if (i != Integer.MAX_VALUE) {
                        jSONObject.put("param_int_first", i);
                    }
                    if (i2 != Integer.MAX_VALUE) {
                        jSONObject.put("param_int_second", i2);
                    }
                    a(context, str, jSONObject);
                }
            } catch (Throwable th) {
                cl.a(th, "ReportUtil", "applyStatisticsEx");
            }
        }
    }

    private static void a(Context context, String str, JSONObject jSONObject) {
        if (context != null) {
            try {
                if (ck.o()) {
                    ay ayVar = new ay(context, "loc", "4.2.0", str);
                    ayVar.a(jSONObject.toString());
                    h.add(ayVar);
                    if (h.size() >= 100) {
                        List arrayList = new ArrayList();
                        arrayList.addAll(h);
                        az.a(arrayList, context);
                        h.clear();
                    }
                }
            } catch (Throwable th) {
                cl.a(th, "ReportUtil", "applyStatistics");
            }
        }
    }

    public static void a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        try {
            if (g == null) {
                if (ct.a(aMapLocation)) {
                    g = aMapLocation.clone();
                } else {
                    g = aMapLocation2;
                    return;
                }
            }
            if (ct.a(g) && ct.a(aMapLocation2)) {
                AMapLocation clone = aMapLocation2.clone();
                if (!(g.getLocationType() == 1 || g.getLocationType() == 9 || "gps".equalsIgnoreCase(g.getProvider()) || g.getLocationType() == 7 || clone.getLocationType() == 1 || clone.getLocationType() == 9 || "gps".equalsIgnoreCase(clone.getProvider()) || clone.getLocationType() == 7)) {
                    long abs = Math.abs(clone.getTime() - g.getTime()) / 1000;
                    if (abs <= 0) {
                        abs = 1;
                    }
                    if (abs <= 1800) {
                        float a = ct.a(g, clone);
                        float f = a / ((float) abs);
                        if (a > 30000.0f && f > 1000.0f) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(g.getLatitude()).append(",");
                            stringBuilder.append(g.getLongitude()).append(",");
                            stringBuilder.append(g.getAccuracy()).append(",");
                            stringBuilder.append(g.getLocationType()).append(",");
                            if (aMapLocation.getTime() != 0) {
                                stringBuilder.append(ct.a(g.getTime(), "yyyyMMdd_HH:mm:ss:SS"));
                            } else {
                                stringBuilder.append(g.getTime());
                            }
                            stringBuilder.append("#");
                            stringBuilder.append(clone.getLatitude()).append(",");
                            stringBuilder.append(clone.getLongitude()).append(",");
                            stringBuilder.append(clone.getAccuracy()).append(",");
                            stringBuilder.append(clone.getLocationType()).append(",");
                            if (clone.getTime() != 0) {
                                stringBuilder.append(ct.a(clone.getTime(), "yyyyMMdd_HH:mm:ss:SS"));
                            } else {
                                stringBuilder.append(clone.getTime());
                            }
                            a("bigshiftstatistics", stringBuilder.toString());
                            stringBuilder.delete(0, stringBuilder.length());
                        }
                    }
                }
                g = clone;
            }
        } catch (Throwable th) {
        }
    }

    public static void a(String str, int i) {
        String valueOf = String.valueOf(i);
        String str2 = "";
        switch (i) {
            case GLMapStaticValue.MAP_PARAMETERNAME_SATELLITE /*2011*/:
                str2 = "ContextIsNull";
                break;
            case 2021:
                str2 = "OnlyMainWifi";
                break;
            case GLMapStaticValue.MAP_PARAMETERNAME_CLEAR_INDOORBUILDING_LAST /*2022*/:
                str2 = "OnlyOneWifiButNotMain";
                break;
            case 2031:
                str2 = "CreateApsReqException";
                break;
            case 2041:
                str2 = "ResponseResultIsNull";
                break;
            case 2051:
                str2 = "NeedLoginNetWork\t";
                break;
            case 2052:
                str2 = "MaybeIntercepted";
                break;
            case 2053:
                str2 = "DecryptResponseException";
                break;
            case 2054:
                str2 = "ParserDataException";
                break;
            case 2061:
                str2 = "ServerRetypeError";
                break;
            case 2062:
                str2 = "ServerLocFail";
                break;
            case 2081:
                str2 = "LocalLocException";
                break;
            case 2091:
                str2 = "InitException";
                break;
            case 2101:
                str2 = "BindAPSServiceException";
                break;
            case 2102:
                str2 = "AuthClientScodeFail";
                break;
            case 2111:
                str2 = "ErrorCgiInfo";
                break;
            case 2121:
                str2 = "NotLocPermission";
                break;
            case 2131:
                str2 = "NoCgiOAndWifiInfo";
                break;
            case 2132:
                str2 = "AirPlaneModeAndWifiOff";
                break;
            case 2133:
                str2 = "NoCgiAndWifiOff";
                break;
            case 2141:
                str2 = "NoEnoughStatellites";
                break;
            case 2151:
                str2 = "MaybeMockNetLoc";
                break;
            case 2152:
                str2 = "MaybeMockGPSLoc";
                break;
        }
        a(str, valueOf, str2);
    }

    public static void a(String str, String str2) {
        try {
            j.b(cl.b(), str2, str);
        } catch (Throwable th) {
            cl.a(th, "ReportUtil", "reportLog");
        }
    }

    public static void a(String str, String str2, String str3) {
        try {
            j.a(cl.b(), "/mobile/binary", str3, str, str2);
        } catch (Throwable th) {
        }
    }

    public static void a(String str, Throwable th) {
        try {
            if (th instanceof k) {
                j.a(cl.b(), str, (k) th);
            }
        } catch (Throwable th2) {
        }
    }

    public static boolean a(Context context, dk dkVar) {
        boolean z = false;
        try {
            z = x.b(context, dkVar);
        } catch (Throwable th) {
        }
        return z;
    }

    private static boolean a(AMapLocation aMapLocation) {
        return ct.a(aMapLocation) ? !cl.a(aMapLocation.getLatitude(), aMapLocation.getLongitude()) : "http://abroad.apilocate.amap.com/mobile/binary".equals(cl.a);
    }

    public static void b(Context context, AMapLocation aMapLocation) {
        int i = 0;
        int i2 = 1;
        try {
            if (ct.a(aMapLocation)) {
                switch (aMapLocation.getLocationType()) {
                    case 1:
                        break;
                    case 2:
                    case 4:
                        i = 1;
                        break;
                    case 8:
                        i = 3;
                        break;
                    case 9:
                        i = 2;
                        break;
                    default:
                        i2 = 0;
                        break;
                }
                if (i2 != 0) {
                    if (i == null) {
                        i = new JSONArray();
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("lon", ct.c(aMapLocation.getLongitude()));
                    jSONObject.put("lat", ct.c(aMapLocation.getLatitude()));
                    jSONObject.put("type", i);
                    jSONObject.put("timestamp", ct.a());
                    if (i == 0) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("accuracy", ct.b((double) aMapLocation.getAccuracy()));
                        jSONObject2.put("altitude", ct.b(aMapLocation.getAltitude()));
                        jSONObject2.put("bearing", ct.b((double) aMapLocation.getBearing()));
                        jSONObject2.put("speed", ct.b((double) aMapLocation.getSpeed()));
                        jSONObject.put("extension", jSONObject2);
                    }
                    JSONArray put = i.put(jSONObject);
                    i = put;
                    if (put.length() >= ck.p()) {
                        f(context);
                    }
                }
            }
        } catch (Throwable th) {
            cl.a(th, "ReportUtil", "recordOfflineLocLog");
        }
    }

    public static void b(Context context, String str, int i) {
        try {
            a(context, "O010", i, str);
        } catch (Throwable th) {
            cl.a(th, "ReportUtil", "reportDexFunction");
        }
    }

    private static void f(Context context) {
        try {
            if (i != null && i.length() > 0) {
                ax.a(new aw(context, cl.b(), i.toString()), context);
                i = null;
            }
        } catch (Throwable th) {
            cl.a(th, "ReportUtil", "writeOfflineLocLog");
        }
    }

    public final void a(Context context, int i) {
        try {
            if (this.b != i) {
                if (!(this.b == -1 || this.b == i)) {
                    long b = ct.b() - this.c;
                    this.a.append(this.b, Long.valueOf(((Long) this.a.get(this.b, Long.valueOf(0))).longValue() + b));
                }
                this.c = ct.b() - cs.b(context, "pref", this.d[i], 0);
                this.b = i;
            }
        } catch (Throwable th) {
            cl.a(th, "ReportUtil", "setLocationType");
        }
    }

    public final void a(Context context, AMapLocationClientOption aMapLocationClientOption) {
        try {
            int i;
            switch (aMapLocationClientOption.getLocationMode()) {
                case Battery_Saving:
                    i = 4;
                    break;
                case Device_Sensors:
                    i = 5;
                    break;
                case Hight_Accuracy:
                    i = 3;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (this.e != i) {
                if (!(this.e == -1 || this.e == i)) {
                    this.a.append(this.e, Long.valueOf((ct.b() - this.f) + ((Long) this.a.get(this.e, Long.valueOf(0))).longValue()));
                }
                this.f = ct.b() - cs.b(context, "pref", this.d[i], 0);
                this.e = i;
            }
        } catch (Throwable th) {
            cl.a(th, "ReportUtil", "setLocationMode");
        }
    }

    public final void b(Context context) {
        try {
            long b = ct.b() - this.c;
            if (this.b != -1) {
                this.a.append(this.b, Long.valueOf(((Long) this.a.get(this.b, Long.valueOf(0))).longValue() + b));
            }
            b = ct.b() - this.f;
            if (this.e != -1) {
                this.a.append(this.e, Long.valueOf(((Long) this.a.get(this.e, Long.valueOf(0))).longValue() + b));
            }
            int i = 0;
            while (i < this.d.length) {
                b = ((Long) this.a.get(i, Long.valueOf(0))).longValue();
                if (b > 0 && b > cs.b(context, "pref", this.d[i], 0)) {
                    cs.a(context, "pref", this.d[i], b);
                }
                i++;
            }
        } catch (Throwable th) {
            cl.a(th, "ReportUtil", "saveLocationTypeAndMode");
        }
    }

    public final int c(Context context) {
        try {
            long b = cs.b(context, "pref", this.d[2], 0);
            long b2 = cs.b(context, "pref", this.d[0], 0);
            long b3 = cs.b(context, "pref", this.d[1], 0);
            if (b == 0 && b2 == 0 && b3 == 0) {
                return -1;
            }
            b2 -= b;
            b3 -= b;
            return b > b2 ? b > b3 ? 2 : 1 : b2 > b3 ? 0 : 1;
        } catch (Throwable th) {
            return -1;
        }
    }

    public final int d(Context context) {
        try {
            long b = cs.b(context, "pref", this.d[3], 0);
            long b2 = cs.b(context, "pref", this.d[4], 0);
            long b3 = cs.b(context, "pref", this.d[5], 0);
            return (b == 0 && b2 == 0 && b3 == 0) ? -1 : b > b2 ? b > b3 ? 3 : 5 : b2 > b3 ? 4 : 5;
        } catch (Throwable th) {
            return -1;
        }
    }

    public final void e(Context context) {
        int i = 0;
        while (i < this.d.length) {
            try {
                cs.a(context, "pref", this.d[i], 0);
                i++;
            } catch (Throwable th) {
                return;
            }
        }
    }
}
