package com.loc;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.io.File;
import java.util.List;
import org.json.JSONObject;

/* compiled from: CoManager */
public final class bx {
    boolean a = false;
    boolean b = false;
    private String c = "com.amap.opensdk.co.CoManager";
    private Context d;
    private Object e = null;
    private int f = -1;
    private boolean g = false;

    public bx(Context context) {
        this.d = context;
    }

    private static String a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sv", "4.2.0");
            jSONObject.put("als", "S128DF1572465B890OE3F7A13167KLEI");
            jSONObject.put("pn", db.c(context));
            jSONObject.put("ak", db.f(context));
            jSONObject.put("ud", df.h(context));
            jSONObject.put("au", df.b(context));
            return jSONObject.toString();
        } catch (Throwable th) {
            return null;
        }
    }

    private static String a(bw bwVar) {
        String str = null;
        try {
            JSONObject jSONObject = new JSONObject();
            if (bwVar != null) {
                bv c = bwVar.c();
                bv d = bwVar.d();
                if (c != null) {
                    jSONObject.put("mainCgi", c.a());
                }
                if (d != null) {
                    jSONObject.put("mainCgi2", d.a());
                }
                str = jSONObject.toString();
            }
        } catch (Throwable th) {
            cl.a(th, "APSCoManager", "buildCgiJsonStr");
        }
        return str;
    }

    private void a(bw bwVar, List<ScanResult> list, AMapLocationServer aMapLocationServer, int i) {
        try {
            if (d() && ct.a(aMapLocationServer)) {
                e();
                if (this.e != null) {
                    String a = a(bwVar);
                    ScanResult[] a2 = a((List) list);
                    if (i == 1) {
                        co.a(this.e, "trainingFps", new Class[]{String.class, ScanResult[].class}, new Object[]{a, a2});
                    } else if (i == 2) {
                        co.a(this.e, "correctOfflineLocation", new Class[]{String.class, ScanResult[].class, Double.TYPE, Double.TYPE}, new Object[]{a, a2, Double.valueOf(aMapLocationServer.getLatitude()), Double.valueOf(aMapLocationServer.getLongitude())});
                    } else {
                        return;
                    }
                    this.b = true;
                }
            }
        } catch (Throwable th) {
            cl.a(th, "APSCoManager", "action-" + (1 == i ? "training" : "correct"));
        }
    }

    private static void a(String str, String str2, String str3) {
        if (!str2.endsWith(File.separator)) {
            str2 = str2 + File.separator;
        }
        String str4 = str2 + str3;
        ct.e(str2);
        ct.b(str, str4);
    }

    private static ScanResult[] a(List<ScanResult> list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    ScanResult[] scanResultArr = new ScanResult[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        scanResultArr[i] = (ScanResult) list.get(i);
                    }
                    return scanResultArr;
                }
            } catch (Throwable th) {
                cl.a(th, "APSCoManager", "buildScanResults");
            }
        }
        return null;
    }

    private boolean d() {
        if (!ck.C()) {
            c();
            return false;
        } else if (ck.D()) {
            return true;
        } else {
            if (!this.b) {
                return false;
            }
            try {
                if (this.e != null) {
                    co.a(this.e, "destroyOfflineLoc", new Object[0]);
                }
            } catch (Throwable th) {
                cl.a(th, "APSCoManager", "destroyOffline");
            }
            this.b = false;
            return false;
        }
    }

    private void e() {
        try {
            if (this.e == null && !this.g && ck.G()) {
                dk a = cl.a("co", "1.0.0");
                this.g = cq.a(this.d, a);
                if (this.g) {
                    String a2;
                    try {
                        this.e = x.a(this.d, a, this.c, null, new Class[]{Context.class}, new Object[]{this.d});
                        if (this.d != null) {
                            a2 = a(this.d);
                            if (this.e != null) {
                                co.a(this.e, "init", a2);
                            }
                        }
                    } catch (Throwable th) {
                    }
                    a2 = x.a(this.d, a);
                    if (!TextUtils.isEmpty(a2)) {
                        StringBuffer stringBuffer = new StringBuffer();
                        String str = this.d.getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + "loc_cozip";
                        String substring = a2.substring(a2.lastIndexOf(File.separator) + 1, a2.lastIndexOf("."));
                        boolean c = ct.c(str, substring);
                        boolean b = cs.b(this.d, "pref", "ok4", false);
                        if (!c || b) {
                            cs.a(this.d, "pref", "ok4", false);
                            a(a2, str, substring);
                        }
                        Object a3 = dl.a(this.d);
                        if (!TextUtils.isEmpty(a3)) {
                            stringBuffer.append(str).append(File.separator).append(substring).append(File.separator).append("libs").append(File.separator).append(a3).append(File.separator).append("libapssdk.so");
                            File file = new File(stringBuffer.toString());
                            if (!file.exists()) {
                                a(a2, str, substring);
                            }
                            if (file.exists() && this.e != null) {
                                co.a(this.e, "loadSo", r1);
                            }
                        }
                    }
                } else {
                    this.g = true;
                }
            }
            int F = ck.F();
            if (this.f != F) {
                this.f = F;
                if (this.e != null) {
                    co.a(this.e, "setCloudConfigVersion", Integer.valueOf(F));
                }
            }
        } catch (Throwable th2) {
            cl.a(th2, "APSCoManager", "initOffLocation");
        }
    }

    public final AMapLocationServer a(bw bwVar, List<ScanResult> list, AMapLocationServer aMapLocationServer) {
        try {
            if (!d()) {
                return aMapLocationServer;
            }
            if (aMapLocationServer != null && aMapLocationServer.getErrorCode() == 7) {
                return aMapLocationServer;
            }
            e();
            if (this.e == null) {
                return aMapLocationServer;
            }
            this.b = true;
            String a = a(bwVar);
            ScanResult[] a2 = a((List) list);
            Object a3 = co.a(this.e, "getOfflineLoc", new Class[]{String.class, ScanResult[].class, Boolean.TYPE}, new Object[]{a, a2, Boolean.valueOf(false)});
            if (a3 == null) {
                return aMapLocationServer;
            }
            JSONObject jSONObject = new JSONObject((String) a3);
            AMapLocationServer aMapLocationServer2 = new AMapLocationServer("lbs");
            aMapLocationServer2.b(jSONObject);
            if (ct.a(aMapLocationServer2)) {
                StringBuffer stringBuffer = new StringBuffer();
                if (aMapLocationServer2.e().equals("file")) {
                    stringBuffer.append("基站离线定位");
                } else if (aMapLocationServer2.e().equals("wifioff")) {
                    stringBuffer.append("WIFI离线定位");
                } else {
                    stringBuffer.append("离线定位，").append(aMapLocationServer2.e());
                }
                stringBuffer.append("，在线定位失败原因:" + aMapLocationServer.getErrorInfo());
                aMapLocationServer2.setLocationDetail(stringBuffer.toString());
                aMapLocationServer2.setLocationType(8);
            }
            return aMapLocationServer2;
        } catch (Throwable th) {
            cl.a(th, "APSCoManager", "getOffLoc");
            return aMapLocationServer;
        }
    }

    public final void a() {
        try {
            if (!ck.C()) {
                c();
            } else if (ck.E()) {
                if (!this.a) {
                    e();
                    if (this.e != null) {
                        co.a(this.e, "startCollect", new Object[0]);
                        this.a = true;
                    }
                }
            } else if (this.a) {
                if (this.e != null) {
                    co.a(this.e, "destroyCollect", new Object[0]);
                }
                this.a = false;
            }
        } catch (Throwable th) {
            cl.a(th, "APSCoManager", "startCollection");
        }
    }

    public final String b() {
        try {
            if (ck.C()) {
                String str;
                if (this.e != null) {
                    str = (String) co.a(this.e, "getCollectVersion", new Object[0]);
                    return str;
                }
                str = null;
                return str;
            }
            c();
            return null;
        } catch (Throwable th) {
            cl.a(th, "APSCoManager", "getCollectionVersion");
        }
    }

    public final void b(bw bwVar, List<ScanResult> list, AMapLocationServer aMapLocationServer) {
        try {
            a(bwVar, list, aMapLocationServer, 1);
        } catch (Throwable th) {
            cl.a(th, "APSCoManager", "trainingFps");
        }
    }

    public final void c() {
        try {
            if (this.e != null) {
                co.a(this.e, "destroy", new Object[0]);
            }
            this.a = false;
            this.b = false;
            this.e = null;
        } catch (Throwable th) {
            cl.a(th, "APSCoManager", "destroy");
        }
    }

    public final void c(bw bwVar, List<ScanResult> list, AMapLocationServer aMapLocationServer) {
        try {
            a(bwVar, list, aMapLocationServer, 2);
        } catch (Throwable th) {
            cl.a(th, "APSCoManager", "correctOffLoc");
        }
    }
}
