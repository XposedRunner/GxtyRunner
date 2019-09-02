package com.loc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol;
import com.amap.api.location.AMapLocationClientOption.GeoLanguage;
import com.autonavi.amap.mapcore.tools.GLMapStaticValue;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.github.mikephil.charting.utils.Utils;
import com.qq.e.comm.constants.ErrorCode.OtherError;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;

@SuppressLint({"NewApi"})
/* compiled from: Aps */
public final class bq {
    static int D = -1;
    public static boolean H = true;
    private static boolean L = false;
    private static int N = -1;
    int A = 12;
    bt B = null;
    boolean C = false;
    bs E = null;
    String F = null;
    bx G = null;
    IntentFilter I = null;
    private int J = 0;
    private String K = null;
    private String M = null;
    private boolean O = true;
    Context a = null;
    ConnectivityManager b = null;
    by c = null;
    bw d = null;
    ca e = null;
    br f = null;
    ci g = null;
    ArrayList<ScanResult> h = new ArrayList();
    a i = null;
    AMapLocationClientOption j = new AMapLocationClientOption();
    AMapLocationServer k = null;
    long l = 0;
    cj m = null;
    boolean n = false;
    cg o = null;
    StringBuilder p = new StringBuilder();
    boolean q = true;
    boolean r = true;
    GeoLanguage s = GeoLanguage.DEFAULT;
    boolean t = true;
    boolean u = false;
    WifiInfo v = null;
    boolean w = true;
    StringBuilder x = null;
    boolean y = false;
    public boolean z = false;

    /* compiled from: Aps */
    class a extends BroadcastReceiver {
        final /* synthetic */ bq a;

        a(bq bqVar) {
            this.a = bqVar;
        }

        public final void onReceive(Context context, Intent intent) {
            if (context != null && intent != null) {
                try {
                    String action = intent.getAction();
                    if (!TextUtils.isEmpty(action)) {
                        if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                            if (this.a.c != null) {
                                this.a.c.d();
                            }
                        } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED") && this.a.c != null) {
                            this.a.c.e();
                        }
                    }
                } catch (Throwable th) {
                    cl.a(th, "Aps", "onReceive");
                }
            }
        }
    }

    private static AMapLocationServer a(int i, String str) {
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        aMapLocationServer.setErrorCode(i);
        aMapLocationServer.setLocationDetail(str);
        if (i == 15) {
            cq.a(null, 2151);
        }
        return aMapLocationServer;
    }

    private AMapLocationServer a(AMapLocationServer aMapLocationServer, as asVar) {
        if (asVar != null) {
            try {
                if (!(asVar.a == null || asVar.a.length == 0)) {
                    ci ciVar = new ci();
                    String str = new String(asVar.a, "UTF-8");
                    if (str.contains("\"status\":\"0\"")) {
                        aMapLocationServer = ciVar.a(str, this.a, asVar);
                        aMapLocationServer.h(this.x.toString());
                        return aMapLocationServer;
                    } else if (!str.contains("</body></html>")) {
                        return null;
                    } else {
                        aMapLocationServer.setErrorCode(5);
                        if (this.c == null || !this.c.a(this.b)) {
                            this.p.append("请求可能被劫持了#0502");
                            cq.a(null, 2052);
                        } else {
                            this.p.append("您连接的是一个需要登录的网络，请确认已经登入网络#0501");
                            cq.a(null, 2051);
                        }
                        aMapLocationServer.setLocationDetail(this.p.toString());
                        return aMapLocationServer;
                    }
                }
            } catch (Throwable th) {
                aMapLocationServer.setErrorCode(4);
                cl.a(th, "Aps", "checkResponseEntity");
                this.p.append("check response exception ex is" + th.getMessage() + "#0403");
                aMapLocationServer.setLocationDetail(this.p.toString());
                return aMapLocationServer;
            }
        }
        aMapLocationServer.setErrorCode(4);
        this.p.append("网络异常,请求异常#0403");
        aMapLocationServer.h(this.x.toString());
        aMapLocationServer.setLocationDetail(this.p.toString());
        if (asVar == null) {
            return aMapLocationServer;
        }
        cq.a(asVar.d, 2041);
        return aMapLocationServer;
    }

    @SuppressLint({"NewApi"})
    private AMapLocationServer a(boolean z, boolean z2) {
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        try {
            if (this.m == null) {
                this.m = new cj();
            }
            if (this.j == null) {
                this.j = new AMapLocationClientOption();
            }
            this.m.a(this.a, this.j.isNeedAddress(), this.j.isOffset(), this.d, this.c, this.b, this.G != null ? this.G.b() : null, this.F);
            byte[] a = this.m.a();
            this.l = ct.b();
            try {
                AMapLocationServer aMapLocationServer2;
                cl.c(this.a);
                ch a2 = this.o.a(this.a, a, cl.a(), z2);
                cd.a(this.a).a(a2);
                as a3 = this.o.a(a2);
                cd.a(this.a).a();
                String str = "";
                if (a3 != null) {
                    cd.a(this.a).b();
                    aMapLocationServer.a((long) this.o.b());
                    if (!TextUtils.isEmpty(a3.c)) {
                        this.p.append("#csid:" + a3.c);
                    }
                    str = a3.d;
                    aMapLocationServer.h(this.x.toString());
                }
                if (z) {
                    aMapLocationServer2 = aMapLocationServer;
                } else {
                    AMapLocationServer a4 = a(aMapLocationServer, a3);
                    if (a4 != null) {
                        return a4;
                    }
                    byte[] a5 = bz.a(a3.a);
                    if (a5 == null) {
                        aMapLocationServer.setErrorCode(5);
                        this.p.append("解密数据失败#0503");
                        aMapLocationServer.setLocationDetail(this.p.toString());
                        cq.a(str, 2053);
                        return aMapLocationServer;
                    }
                    a4 = this.g.a(aMapLocationServer, a5);
                    if (ct.a(a4)) {
                        if (a4.getErrorCode() == 0 && a4.getLocationType() == 0) {
                            if ("-5".equals(a4.d()) || "1".equals(a4.d()) || "2".equals(a4.d()) || Constants.VIA_REPORT_TYPE_MAKE_FRIEND.equals(a4.d()) || "24".equals(a4.d()) || WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(a4.d())) {
                                a4.setLocationType(5);
                            } else {
                                a4.setLocationType(6);
                            }
                        }
                        a4.setOffset(this.r);
                        a4.a(this.q);
                        a4.f(String.valueOf(this.s));
                        aMapLocationServer2 = a4;
                    } else {
                        this.K = a4.b();
                        if (TextUtils.isEmpty(this.K)) {
                            cq.a(str, 2061);
                        } else {
                            cq.a(str, 2062);
                        }
                        a4.setErrorCode(6);
                        this.p.append("location faile retype:" + a4.d() + " rdesc:" + (TextUtils.isEmpty(this.K) ? "" : this.K) + "#0601");
                        a4.h(this.x.toString());
                        a4.setLocationDetail(this.p.toString());
                        return a4;
                    }
                }
                aMapLocationServer2.e("new");
                aMapLocationServer2.setLocationDetail(this.p.toString());
                this.F = aMapLocationServer2.a();
                return aMapLocationServer2;
            } catch (Throwable th) {
                cd.a(this.a).c();
                cl.a(th, "Aps", "getApsLoc req");
                cq.a("/mobile/binary", th);
                if (!ct.d(this.a)) {
                    this.p.append("网络异常，未连接到网络，请连接网络#0401");
                } else if (!(th instanceof k)) {
                    this.p.append("网络异常,请求异常#0403");
                } else if (((k) th).a().contains("网络异常状态码")) {
                    this.p.append("网络异常，状态码错误#0404").append(((k) th).e());
                } else if (((k) th).e() == 23 || Math.abs((ct.b() - this.l) - this.j.getHttpTimeOut()) < 500) {
                    this.p.append("网络异常，连接超时#0402");
                } else {
                    this.p.append("网络异常,请求异常#0403");
                }
                aMapLocationServer = a(4, this.p.toString());
                aMapLocationServer.h(this.x.toString());
                return aMapLocationServer;
            }
        } catch (Throwable th2) {
            this.p.append("get parames error:" + th2.getMessage() + "#0301");
            cq.a(null, 2031);
            aMapLocationServer = a(3, this.p.toString());
            aMapLocationServer.h(this.x.toString());
            return aMapLocationServer;
        }
    }

    private StringBuilder a(StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            stringBuilder = new StringBuilder(OtherError.VIDEO_DOWNLOAD_ERROR);
        } else {
            stringBuilder.delete(0, stringBuilder.length());
        }
        stringBuilder.append(this.d.l());
        stringBuilder.append(this.c.i());
        return stringBuilder;
    }

    public static void b(Context context) {
        try {
            if (N == -1 || ck.h(context)) {
                N = 1;
                ck.a(context);
            }
        } catch (Throwable th) {
            cl.a(th, "Aps", "initAuth");
        }
    }

    private void b(AMapLocationServer aMapLocationServer) {
        if (aMapLocationServer != null) {
            this.k = aMapLocationServer;
        }
    }

    private void l() {
        boolean z = true;
        if (this.o != null) {
            try {
                int i;
                if (this.j == null) {
                    this.j = new AMapLocationClientOption();
                }
                if (this.j.getGeoLanguage() != null) {
                    switch (this.j.getGeoLanguage()) {
                        case DEFAULT:
                            i = 0;
                            break;
                        case ZH:
                            i = 1;
                            break;
                        case EN:
                            i = 2;
                            break;
                        default:
                            i = 0;
                            break;
                    }
                }
                i = 0;
                cg cgVar = this.o;
                long httpTimeOut = this.j.getHttpTimeOut();
                if (!this.j.getLocationProtocol().equals(AMapLocationProtocol.HTTPS)) {
                    z = false;
                }
                cgVar.a(httpTimeOut, z, i);
            } catch (Throwable th) {
            }
        }
    }

    private void m() {
        try {
            if (this.i == null) {
                this.i = new a(this);
            }
            if (this.I == null) {
                this.I = new IntentFilter();
                this.I.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                this.I.addAction("android.net.wifi.SCAN_RESULTS");
            }
            this.a.registerReceiver(this.i, this.I);
        } catch (Throwable th) {
            cl.a(th, "Aps", "initBroadcastListener");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String n() {
        /*
        r10 = this;
        r9 = 2121; // 0x849 float:2.972E-42 double:1.048E-320;
        r8 = 12;
        r3 = 1;
        r2 = 0;
        r7 = 0;
        r4 = "";
        r5 = "network";
        r0 = r10.d;
        r1 = r0.f();
        r0 = r10.d;
        r6 = r0.c();
        r0 = r10.h;
        if (r0 == 0) goto L_0x0023;
    L_0x001b:
        r0 = r10.h;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x004d;
    L_0x0023:
        r0 = r3;
    L_0x0024:
        if (r6 != 0) goto L_0x012e;
    L_0x0026:
        if (r0 == 0) goto L_0x012e;
    L_0x0028:
        r0 = r10.b;
        if (r0 != 0) goto L_0x0038;
    L_0x002c:
        r0 = r10.a;
        r1 = "connectivity";
        r0 = com.loc.ct.a(r0, r1);
        r0 = (android.net.ConnectivityManager) r0;
        r10.b = r0;
    L_0x0038:
        r0 = r10.a;
        r0 = com.loc.ct.f(r0);
        if (r0 != 0) goto L_0x004f;
    L_0x0040:
        r10.A = r8;
        r0 = r10.p;
        r1 = "定位权限被禁用,请授予应用定位权限#1201";
        r0.append(r1);
        com.loc.cq.a(r7, r9);
    L_0x004c:
        return r4;
    L_0x004d:
        r0 = r2;
        goto L_0x0024;
    L_0x004f:
        r0 = com.loc.ct.c();
        r1 = 24;
        if (r0 < r1) goto L_0x0072;
    L_0x0057:
        r0 = r10.a;
        r0 = r0.getContentResolver();
        r1 = "location_mode";
        r0 = android.provider.Settings.Secure.getInt(r0, r1, r2);
        if (r0 != 0) goto L_0x0072;
    L_0x0065:
        r10.A = r8;
        r0 = r10.p;
        r1 = "定位服务没有开启，请在设置中打开定位服务开关#1206";
        r0.append(r1);
        com.loc.cq.a(r7, r9);
        goto L_0x004c;
    L_0x0072:
        r0 = r10.d;
        r0 = r0.j();
        r1 = r10.c;
        r1 = r1.a();
        r2 = r10.c;
        r3 = r10.b;
        r2 = r2.a(r3);
        if (r2 != 0) goto L_0x0092;
    L_0x0088:
        r2 = r10.c;
        r2 = r2.l();
        if (r2 != 0) goto L_0x0092;
    L_0x0090:
        if (r1 == 0) goto L_0x00a9;
    L_0x0092:
        r10.A = r8;
        if (r0 == 0) goto L_0x00a1;
    L_0x0096:
        r0 = r10.p;
        r1 = "获取基站与获取WIFI的权限都被禁用，请在安全软件中打开应用的定位权限#1202";
        r0.append(r1);
    L_0x009d:
        com.loc.cq.a(r7, r9);
        goto L_0x004c;
    L_0x00a1:
        r0 = r10.p;
        r1 = "获取到的基站为空，并且获取WIFI权限被禁用,请在安全软件中打开应用的定位权限#1203";
        r0.append(r1);
        goto L_0x009d;
    L_0x00a9:
        if (r0 == 0) goto L_0x00c8;
    L_0x00ab:
        r10.A = r8;
        r0 = r10.c;
        r0 = r0.f();
        if (r0 != 0) goto L_0x00c0;
    L_0x00b5:
        r0 = r10.p;
        r1 = "WIFI开关关闭，并且获取基站权限被禁用，请在安全软件中打开应用的定位权限或者打开WIFI开关#1204";
        r0.append(r1);
    L_0x00bc:
        com.loc.cq.a(r7, r9);
        goto L_0x004c;
    L_0x00c0:
        r0 = r10.p;
        r1 = "获取的WIFI列表为空，并且获取基站权限被禁用，请在安全软件中打开应用的定位权限#1205";
        r0.append(r1);
        goto L_0x00bc;
    L_0x00c8:
        r0 = r10.a;
        r0 = com.loc.ct.a(r0);
        if (r0 == 0) goto L_0x00ea;
    L_0x00d0:
        r0 = r10.c;
        r0 = r0.f();
        if (r0 != 0) goto L_0x00ea;
    L_0x00d8:
        r0 = 18;
        r10.A = r0;
        r0 = r10.p;
        r1 = "飞行模式下关闭了WIFI开关，请关闭飞行模式或者打开WIFI开关#1801";
        r0.append(r1);
        r0 = 2132; // 0x854 float:2.988E-42 double:1.0533E-320;
        com.loc.cq.a(r7, r0);
        goto L_0x004c;
    L_0x00ea:
        r0 = r10.c;
        r0 = r0.f();
        if (r0 != 0) goto L_0x010c;
    L_0x00f2:
        r0 = r10.d;
        r0 = r0.m();
        if (r0 != 0) goto L_0x010c;
    L_0x00fa:
        r0 = 19;
        r10.A = r0;
        r0 = r10.p;
        r1 = "没有检查到SIM卡，并且WIFI开关关闭，请打开WIFI开关或者插入SIM卡#1901";
        r0.append(r1);
        r0 = 2133; // 0x855 float:2.989E-42 double:1.054E-320;
        com.loc.cq.a(r7, r0);
        goto L_0x004c;
    L_0x010c:
        r0 = r10.c;
        r0 = r0.f();
        if (r0 != 0) goto L_0x0126;
    L_0x0114:
        r0 = r10.p;
        r1 = "获取到的基站为空，并且关闭了WIFI开关，请您打开WIFI开关在发起定位#1301";
        r0.append(r1);
    L_0x011b:
        r0 = 13;
        r10.A = r0;
        r0 = 2131; // 0x853 float:2.986E-42 double:1.053E-320;
        com.loc.cq.a(r7, r0);
        goto L_0x004c;
    L_0x0126:
        r0 = r10.p;
        r1 = "获取到的基站与WIFI为空，请移动到有WIFI的区域，若确定当前区域有WIFI，请检查是否授予APP定位权限#1302";
        r0.append(r1);
        goto L_0x011b;
    L_0x012e:
        r0 = r10.c;
        r0 = r0.g();
        r10.v = r0;
        r0 = r10.c;
        r0 = r10.v;
        r0 = com.loc.by.a(r0);
        r10.w = r0;
        switch(r1) {
            case 0: goto L_0x0242;
            case 1: goto L_0x018b;
            case 2: goto L_0x01e1;
            default: goto L_0x0143;
        };
    L_0x0143:
        r0 = 11;
        r10.A = r0;
        r0 = 2111; // 0x83f float:2.958E-42 double:1.043E-320;
        com.loc.cq.a(r7, r0);
        r0 = r10.p;
        r1 = "get cgi failure#1101";
        r0.append(r1);
    L_0x0153:
        r0 = r4;
    L_0x0154:
        r1 = "#";
        r2 = android.text.TextUtils.isEmpty(r0);
        if (r2 != 0) goto L_0x0188;
    L_0x015c:
        r2 = r0.startsWith(r1);
        if (r2 != 0) goto L_0x0173;
    L_0x0162:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = r2.append(r1);
        r0 = r1.append(r0);
        r0 = r0.toString();
    L_0x0173:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = com.loc.ct.h();
        r1 = r1.append(r2);
        r0 = r1.append(r0);
        r0 = r0.toString();
    L_0x0188:
        r4 = r0;
        goto L_0x004c;
    L_0x018b:
        if (r6 == 0) goto L_0x0153;
    L_0x018d:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r6.a;
        r0 = r1.append(r0);
        r2 = "#";
        r0.append(r2);
        r0 = r6.b;
        r0 = r1.append(r0);
        r2 = "#";
        r0.append(r2);
        r0 = r6.c;
        r0 = r1.append(r0);
        r2 = "#";
        r0.append(r2);
        r0 = r6.d;
        r0 = r1.append(r0);
        r2 = "#";
        r0.append(r2);
        r0 = r1.append(r5);
        r2 = "#";
        r0.append(r2);
        r0 = r10.h;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x01d3;
    L_0x01cf:
        r0 = r10.w;
        if (r0 == 0) goto L_0x01de;
    L_0x01d3:
        r0 = "cgiwifi";
    L_0x01d5:
        r1.append(r0);
        r0 = r1.toString();
        goto L_0x0154;
    L_0x01de:
        r0 = "cgi";
        goto L_0x01d5;
    L_0x01e1:
        if (r6 == 0) goto L_0x0153;
    L_0x01e3:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r6.a;
        r0 = r1.append(r0);
        r2 = "#";
        r0.append(r2);
        r0 = r6.b;
        r0 = r1.append(r0);
        r2 = "#";
        r0.append(r2);
        r0 = r6.g;
        r0 = r1.append(r0);
        r2 = "#";
        r0.append(r2);
        r0 = r6.h;
        r0 = r1.append(r0);
        r2 = "#";
        r0.append(r2);
        r0 = r6.i;
        r0 = r1.append(r0);
        r2 = "#";
        r0.append(r2);
        r0 = r1.append(r5);
        r2 = "#";
        r0.append(r2);
        r0 = r10.h;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x0234;
    L_0x0230:
        r0 = r10.w;
        if (r0 == 0) goto L_0x023f;
    L_0x0234:
        r0 = "cgiwifi";
    L_0x0236:
        r1.append(r0);
        r0 = r1.toString();
        goto L_0x0154;
    L_0x023f:
        r0 = "cgi";
        goto L_0x0236;
    L_0x0242:
        r0 = r10.h;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x024e;
    L_0x024a:
        r0 = r10.w;
        if (r0 == 0) goto L_0x02ff;
    L_0x024e:
        r1 = r3;
    L_0x024f:
        r0 = r10.w;
        if (r0 == 0) goto L_0x026c;
    L_0x0253:
        r0 = r10.h;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x026c;
    L_0x025b:
        r0 = 2;
        r10.A = r0;
        r0 = r10.p;
        r1 = "当前基站为伪基站，并且WIFI权限被禁用，请在安全软件中打开应用的定位权限#0201";
        r0.append(r1);
        r0 = 2021; // 0x7e5 float:2.832E-42 double:9.985E-321;
        com.loc.cq.a(r7, r0);
        goto L_0x004c;
    L_0x026c:
        r0 = r10.h;
        r0 = r0.size();
        if (r0 != r3) goto L_0x02b1;
    L_0x0274:
        r0 = 2;
        r10.A = r0;
        r0 = r10.w;
        if (r0 != 0) goto L_0x0289;
    L_0x027b:
        r0 = r10.p;
        r1 = "当前基站为伪基站，并且搜到的WIFI数量不足，请移动到WIFI比较丰富的区域#0202";
        r0.append(r1);
        r0 = 2022; // 0x7e6 float:2.833E-42 double:9.99E-321;
        com.loc.cq.a(r7, r0);
        goto L_0x004c;
    L_0x0289:
        r0 = r10.h;
        r0 = r0.get(r2);
        r0 = (android.net.wifi.ScanResult) r0;
        r0 = r0.BSSID;
        r6 = r10.c;
        r6 = r6.g();
        r6 = r6.getBSSID();
        r0 = r6.equals(r0);
        if (r0 == 0) goto L_0x02b1;
    L_0x02a3:
        r0 = r10.p;
        r1 = "当前基站为伪基站，并且搜到的WIFI数量不足，请移动到WIFI比较丰富的区域#0202";
        r0.append(r1);
        r0 = 2021; // 0x7e5 float:2.832E-42 double:9.985E-321;
        com.loc.cq.a(r7, r0);
        goto L_0x004c;
    L_0x02b1:
        r0 = java.util.Locale.US;
        r4 = "#%s#";
        r3 = new java.lang.Object[r3];
        r3[r2] = r5;
        r0 = java.lang.String.format(r0, r4, r3);
        if (r1 == 0) goto L_0x02d4;
    L_0x02bf:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = "wifi";
        r0 = r0.append(r1);
        r0 = r0.toString();
        goto L_0x0154;
    L_0x02d4:
        r1 = "network";
        r1 = r5.equals(r1);
        if (r1 == 0) goto L_0x0154;
    L_0x02dc:
        r0 = "";
        r1 = 2;
        r10.A = r1;
        r1 = r10.c;
        r1 = r1.f();
        if (r1 != 0) goto L_0x02f7;
    L_0x02e9:
        r1 = r10.p;
        r2 = "当前基站为伪基站,并且关闭了WIFI开关，请在设置中打开WIFI开关#0203";
        r1.append(r2);
    L_0x02f0:
        r1 = 2022; // 0x7e6 float:2.833E-42 double:9.99E-321;
        com.loc.cq.a(r7, r1);
        goto L_0x0154;
    L_0x02f7:
        r1 = r10.p;
        r2 = "当前基站为伪基站,并且没有搜索到WIFI，请移动到WIFI比较丰富的区域#0204";
        r1.append(r2);
        goto L_0x02f0;
    L_0x02ff:
        r1 = r2;
        goto L_0x024f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bq.n():java.lang.String");
    }

    private boolean o() {
        this.h = this.c.b();
        return this.h == null || this.h.size() <= 0;
    }

    public final AMapLocationServer a(double d, double d2) {
        try {
            String a = this.o.a(this.a, d, d2);
            if (a.contains("\"status\":\"1\"")) {
                AMapLocationServer a2 = this.g.a(a);
                a2.setLatitude(d);
                a2.setLongitude(d2);
                return a2;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public final AMapLocationServer a(AMapLocationServer aMapLocationServer, String... strArr) {
        this.E.a(this.t);
        if (strArr == null || strArr.length == 0) {
            return this.E.a(aMapLocationServer);
        }
        if (strArr[0].equals("shake")) {
            return this.E.a(aMapLocationServer);
        }
        if (!strArr[0].equals("fusion")) {
            return aMapLocationServer;
        }
        bs bsVar = this.E;
        return aMapLocationServer;
    }

    public final AMapLocationServer a(boolean z) {
        if (this.a == null) {
            this.p.append("context is null#0101");
            cq.a(null, (int) GLMapStaticValue.MAP_PARAMETERNAME_SATELLITE);
            return a(1, this.p.toString());
        } else if (this.c.h()) {
            return a(15, "networkLocation has been mocked!#1502");
        } else {
            a();
            if (TextUtils.isEmpty(this.M)) {
                return a(this.A, this.p.toString());
            }
            AMapLocationServer a = a(false, z);
            if (!ct.a(a)) {
                return a;
            }
            this.e.a(this.x.toString());
            this.e.a(this.d.c());
            b(a);
            return a;
        }
    }

    public final void a() {
        Context context = this.a;
        this.o = cg.a();
        l();
        if (this.b == null) {
            this.b = (ConnectivityManager) ct.a(this.a, "connectivity");
        }
        if (this.m == null) {
            this.m = new cj();
        }
    }

    public final void a(Context context) {
        try {
            if (this.a == null) {
                this.E = new bs();
                this.a = context.getApplicationContext();
                ck.e(this.a);
                ct.b(this.a);
                if (this.c == null) {
                    this.c = new by(this.a, (WifiManager) ct.a(this.a, IXAdSystemUtils.NT_WIFI));
                }
                if (this.d == null) {
                    this.d = new bw(this.a);
                }
                if (this.e == null) {
                    this.e = new ca();
                }
                if (this.g == null) {
                    this.g = new ci();
                }
                if (this.G == null) {
                    this.G = new bx(this.a);
                }
            }
        } catch (Throwable th) {
            cl.a(th, "Aps", "initBase");
        }
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        boolean isNeedAddress;
        boolean isOffset;
        boolean z = true;
        this.j = aMapLocationClientOption;
        if (this.j == null) {
            this.j = new AMapLocationClientOption();
        }
        if (this.c != null) {
            by byVar = this.c;
            this.j.isWifiActiveScan();
            byVar.a(this.j.isWifiScan(), this.j.isMockEnable());
        }
        l();
        if (this.e != null) {
            this.e.a(this.j);
        }
        if (this.g != null) {
            this.g.a(this.j);
        }
        GeoLanguage geoLanguage = GeoLanguage.DEFAULT;
        try {
            geoLanguage = this.j.getGeoLanguage();
            isNeedAddress = this.j.isNeedAddress();
            try {
                isOffset = this.j.isOffset();
                try {
                    z = this.j.isLocationCacheEnable();
                    this.u = this.j.isOnceLocationLatest();
                    this.C = this.j.isSensorEnable();
                    if (!(isOffset == this.r && isNeedAddress == this.q && z == this.t && geoLanguage == this.s)) {
                        if (this.e != null) {
                            this.e.a();
                        }
                        b(null);
                        this.O = false;
                        if (this.E != null) {
                            this.E.a();
                        }
                    }
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
                isOffset = true;
            }
        } catch (Throwable th3) {
            isNeedAddress = true;
            isOffset = true;
        }
        this.r = isOffset;
        this.q = isNeedAddress;
        this.t = z;
        this.s = geoLanguage;
    }

    public final void a(AMapLocationServer aMapLocationServer) {
        if (ct.a(aMapLocationServer)) {
            this.e.a(this.M, this.x, aMapLocationServer, this.a, true);
        }
    }

    public final void b() {
        if (this.B == null) {
            this.B = new bt(this.a);
        }
        if (this.f == null) {
            this.f = new br(this.a);
        }
        m();
        this.c.b(false);
        this.h = this.c.b();
        this.d.a(false, o());
        this.e.a(this.a);
        this.f.b();
        try {
            if (this.a.checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS") == 0) {
                this.n = true;
            }
        } catch (Throwable th) {
        }
        this.z = true;
    }

    public final void c() {
        if (this.p.length() > 0) {
            this.p.delete(0, this.p.length());
        }
    }

    public final AMapLocationServer d() throws Throwable {
        c();
        if (this.a == null) {
            this.p.append("context is null#0101");
            return a(1, this.p.toString());
        }
        boolean z;
        this.J++;
        if (this.J == 1 && this.c != null) {
            this.c.a(this.n);
        }
        long j = this.l;
        if (this.O) {
            if (ct.b() - j < 800) {
                if ((ct.a(this.k) ? ct.a() - this.k.getTime() : 0) <= 10000) {
                    z = true;
                }
            }
            z = false;
        } else {
            this.O = true;
            z = false;
        }
        if (z && ct.a(this.k)) {
            if (this.t && ck.b(this.k.getTime())) {
                this.k.setLocationType(2);
            }
            return this.k;
        }
        if (this.B != null) {
            if (this.C) {
                this.B.a();
            } else {
                this.B.b();
            }
        }
        try {
            z = this.j.isOnceLocationLatest() || !this.j.isOnceLocation();
            this.c.b(z);
            this.h = this.c.b();
        } catch (Throwable th) {
            cl.a(th, "Aps", "getLocation getScanResultsParam");
        }
        try {
            this.d.a(false, o());
        } catch (Throwable th2) {
            cl.a(th2, "Aps", "getLocation getCgiListParam");
        }
        this.M = n();
        if (TextUtils.isEmpty(this.M)) {
            this.k = this.f.e();
            if (this.k == null) {
                return a(this.A, this.p.toString());
            }
            this.k.setLocationDetail(this.p.toString());
            return this.k;
        }
        this.x = a(this.x);
        if (this.c.h()) {
            AMapLocationServer a = a(15, "networkLocation has been mocked!#1502");
            a.setMock(true);
            return a;
        }
        boolean z2 = this.l == 0 ? true : ct.b() - this.l > 20000;
        a = this.e.a(this.d, z2, this.k, this.c, this.x, this.M, this.a);
        if (ct.a(a)) {
            b(a);
        } else {
            this.k = a(false, true);
            if (ct.a(this.k)) {
                this.k.e("new");
                this.e.a(this.x.toString());
                this.e.a(this.d.c());
                b(this.k);
                if (this.G != null) {
                    this.G.c(this.d, this.h, this.k);
                }
            }
        }
        if (this.G != null) {
            this.G.b(this.d, this.h, this.k);
        }
        this.e.a(this.M, this.x, this.k, this.a, true);
        if (!(ct.a(this.k) || this.G == null)) {
            bx bxVar = this.G;
            Context context = this.a;
            this.k = bxVar.a(this.d, this.h, this.k);
        }
        this.x.delete(0, this.x.length());
        if (!this.C || this.B == null) {
            this.k.setAltitude(Utils.DOUBLE_EPSILON);
            this.k.setBearing(0.0f);
            this.k.setSpeed(0.0f);
        } else {
            this.k.setAltitude(this.B.c());
            this.k.setBearing(this.B.d());
            this.k.setSpeed((float) this.B.e());
        }
        return this.k;
    }

    public final void e() {
        try {
            a(this.a);
            a(this.j);
            Context context = this.a;
            i();
            a(a(true, true));
        } catch (Throwable th) {
            cl.a(th, "Aps", "doFusionLocation");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"NewApi"})
    public final void f() {
        /*
        r4 = this;
        r0 = 0;
        r3 = 0;
        r4.F = r3;
        r4.y = r0;
        r4.z = r0;
        r0 = r4.G;
        if (r0 == 0) goto L_0x0011;
    L_0x000c:
        r0 = r4.G;
        r0.c();
    L_0x0011:
        r0 = r4.f;
        if (r0 == 0) goto L_0x001a;
    L_0x0015:
        r0 = r4.f;
        r0.a();
    L_0x001a:
        r0 = r4.e;
        if (r0 == 0) goto L_0x0025;
    L_0x001e:
        r0 = r4.e;
        r1 = r4.a;
        r0.b(r1);
    L_0x0025:
        r0 = r4.E;
        if (r0 == 0) goto L_0x002e;
    L_0x0029:
        r0 = r4.E;
        r0.a();
    L_0x002e:
        r0 = r4.g;
        if (r0 == 0) goto L_0x0034;
    L_0x0032:
        r4.g = r3;
    L_0x0034:
        com.loc.ct.g();
        r0 = r4.a;	 Catch:{ Throwable -> 0x0076 }
        if (r0 == 0) goto L_0x0046;
    L_0x003b:
        r0 = r4.i;	 Catch:{ Throwable -> 0x0076 }
        if (r0 == 0) goto L_0x0046;
    L_0x003f:
        r0 = r4.a;	 Catch:{ Throwable -> 0x0076 }
        r1 = r4.i;	 Catch:{ Throwable -> 0x0076 }
        r0.unregisterReceiver(r1);	 Catch:{ Throwable -> 0x0076 }
    L_0x0046:
        r4.i = r3;
    L_0x0048:
        r0 = r4.d;
        if (r0 == 0) goto L_0x0051;
    L_0x004c:
        r0 = r4.d;
        r0.h();
    L_0x0051:
        r0 = r4.c;
        if (r0 == 0) goto L_0x005a;
    L_0x0055:
        r0 = r4.c;
        r0.j();
    L_0x005a:
        r0 = r4.h;
        if (r0 == 0) goto L_0x0063;
    L_0x005e:
        r0 = r4.h;
        r0.clear();
    L_0x0063:
        r0 = r4.B;
        if (r0 == 0) goto L_0x006c;
    L_0x0067:
        r0 = r4.B;
        r0.f();
    L_0x006c:
        com.loc.cd.d();
        r4.k = r3;
        r4.a = r3;
        r4.x = r3;
        return;
    L_0x0076:
        r0 = move-exception;
        r1 = "Aps";
        r2 = "destroy";
        com.loc.cl.a(r0, r1, r2);	 Catch:{ all -> 0x0081 }
        r4.i = r3;
        goto L_0x0048;
    L_0x0081:
        r0 = move-exception;
        r4.i = r3;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bq.f():void");
    }

    public final void g() {
        try {
            if (this.f != null) {
                this.f.c();
            }
        } catch (Throwable th) {
            cl.a(th, "Aps", "bindAMapService");
        }
    }

    public final void h() {
        try {
            if (this.f != null) {
                this.f.d();
            }
        } catch (Throwable th) {
            cl.a(th, "Aps", "bindOtherService");
        }
    }

    public final void i() {
        try {
            if (!this.y) {
                if (this.M != null) {
                    this.M = null;
                }
                if (this.x != null) {
                    this.x.delete(0, this.x.length());
                }
                if (this.u) {
                    m();
                }
                this.c.b(this.u);
                this.h = this.c.b();
                this.d.a(true, o());
                this.M = n();
                if (!TextUtils.isEmpty(this.M)) {
                    this.x = a(this.x);
                }
                this.y = true;
            }
        } catch (Throwable th) {
            cl.a(th, "Aps", "initFirstLocateParam");
        }
    }

    public final AMapLocationServer j() {
        if (this.c.h()) {
            return a(15, "networkLocation has been mocked!#1502");
        }
        if (TextUtils.isEmpty(this.M)) {
            return a(this.A, this.p.toString());
        }
        AMapLocationServer a = this.e.a(this.a, this.M, this.x, true);
        if (!ct.a(a)) {
            return a;
        }
        b(a);
        return a;
    }

    public final void k() {
        if (this.G != null) {
            this.G.a();
        }
    }
}
