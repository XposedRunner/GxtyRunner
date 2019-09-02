package cn.jiguang.a.a.b;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenu;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import cn.jiguang.e.d;
import cn.jiguang.g.m;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private int a = -1;
    private int b = -1;
    private String c = "";
    private String d = "";
    private String e = "";
    private TelephonyManager f = null;
    private Context g = null;
    private int h = 0;
    private b i;
    private f j;
    private JSONArray k = null;

    public a(Context context, f fVar) {
        this.g = context;
        try {
            this.f = (TelephonyManager) context.getSystemService("phone");
            this.j = fVar;
        } catch (Throwable e) {
            d.c("CellInfoManager", "The permission of ACCESS_COARSE_LOCATION is denied!", e);
        }
    }

    private static int a(String str) {
        int i = -1;
        try {
            if (str.length() <= 6) {
                i = Integer.parseInt(str.substring(3, str.length()));
            }
        } catch (Throwable e) {
            d.e("CellInfoManager", "unexpected!", e);
        }
        return i;
    }

    private JSONObject a(int i, int i2, int i3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mobile_network_code", this.b);
            jSONObject.put("cell_id", i2);
            jSONObject.put("radio_type", this.c);
            jSONObject.put("signal_strength", i);
            jSONObject.put("mobile_country_code", this.a);
            jSONObject.put("carrier", this.e);
            jSONObject.put("location_area_code", i3);
            jSONObject.put("generation", this.d);
            long t = cn.jiguang.d.a.a.t();
            d.c("CellInfoManager", "getCellInfoTime cellinfo time:" + t);
            jSONObject.put("itime", t);
            d.c("CellInfoManager", "radioTypeJason:" + jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject a(int i, int i2, int i3, int i4) {
        if (i2 < 268435455 && (i4 == 0 || i4 == 3)) {
            return a(i, i2, i3);
        }
        if (i2 < SupportMenu.USER_MASK && (i4 == 1 || i4 == 2)) {
            return a(i, i2, i3);
        }
        d.c("CellInfoManager", "getJasonObject error:" + i4 + ",cid:" + i2);
        return null;
    }

    static /* synthetic */ void a(a aVar, JSONArray jSONArray) {
        CellLocation cellLocation;
        try {
            cellLocation = aVar.f.getCellLocation();
        } catch (Exception e) {
            e.printStackTrace();
            cellLocation = null;
        }
        if (cellLocation != null) {
            try {
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    jSONArray.put(aVar.a(aVar.h, gsmCellLocation.getCid(), gsmCellLocation.getLac()));
                } else if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    jSONArray.put(aVar.a(aVar.h, cdmaCellLocation.getBaseStationId(), cdmaCellLocation.getNetworkId()));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            List<NeighboringCellInfo> neighboringCellInfo = aVar.f.getNeighboringCellInfo();
            if (neighboringCellInfo != null) {
                for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                    int rssi = (neighboringCellInfo2.getRssi() * 2) - 113;
                    int cid = neighboringCellInfo2.getCid();
                    int lac = neighboringCellInfo2.getLac();
                    if (cid < SupportMenu.USER_MASK) {
                        jSONArray.put(aVar.a(rssi, cid, lac));
                    }
                }
            }
            aVar.a(jSONArray);
            aVar.e();
        }
    }

    private void a(JSONArray jSONArray) {
        if (jSONArray != null) {
            JSONArray jSONArray2 = new JSONArray();
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (!jSONArray2.toString().contains(jSONObject.toString())) {
                        jSONArray2.put(jSONObject);
                    }
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
            this.k = jSONArray2;
        }
    }

    private void e() {
        d.c("CellInfoManager", "Action - notifyCollectCellDoneAction");
        if (this.j != null) {
            this.j.a();
        } else {
            d.g("CellInfoManager", "MyLocationManager instance was null");
        }
    }

    private void f() {
        try {
            this.i = new b(this);
            this.f.listen(this.i, 256);
        } catch (Exception e) {
            d.g("CellInfoManager", "unexcepted - initCellLister e:" + e.getMessage());
            e();
        }
    }

    public final void a() {
        List allCellInfo;
        if (this.j == null) {
            d.g("CellInfoManager", "cellLocationManager was null- skip this action");
            e();
            return;
        }
        CellLocation cellLocation;
        try {
            cellLocation = this.f.getCellLocation();
        } catch (Throwable e) {
            d.e("CellInfoManager", "Unexpected!", e);
            cellLocation = null;
        }
        if (cellLocation == null) {
            d.g("CellInfoManager", "Unexpected! cellLocation is null, give up report cell-info");
            e();
            return;
        }
        this.a = -1;
        this.b = -1;
        this.c = "";
        this.d = "";
        this.e = "";
        this.e = this.f.getNetworkOperatorName();
        int networkType = this.f.getNetworkType();
        d.a("TeleonyManagerUtils", "getRadioType - networkType:" + networkType);
        String str = (networkType == 4 || networkType == 7 || networkType == 5 || networkType == 6 || networkType == 12 || networkType == 14) ? "cdma" : networkType == 13 ? "lte" : "gsm";
        d.a("TeleonyManagerUtils", "getRadioType - radioType:" + str);
        this.c = str;
        this.d = m.a(this.g, this.f.getNetworkType());
        d.c("CellInfoManager", "radioType:" + this.c + ", carrier:" + this.e + ", generation:" + this.d);
        try {
            str = this.f.getNetworkOperator();
            if (str.length() > 3) {
                this.a = Integer.parseInt(str.substring(0, 3));
                this.b = a(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JSONArray jSONArray = new JSONArray();
        if (VERSION.SDK_INT > 17) {
            try {
                allCellInfo = this.f.getAllCellInfo();
            } catch (Exception e22) {
                d.i("CellInfoManager", "#unexcepted - get all cellinfo error:" + e22);
                allCellInfo = null;
            }
            d.c("CellInfoManager", "infoLists:" + (r0 != null ? r0.size() : 0));
            if (r0 == null || r0.size() == 0) {
                f();
                return;
            }
            for (CellInfo cellInfo : r0) {
                if (cellInfo == null) {
                    d.g("CellInfoManager", "cellinfo was null, avoid it");
                } else if (cellInfo instanceof CellInfoLte) {
                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                    if (VERSION.SDK_INT > 17) {
                        CellSignalStrengthLte cellSignalStrength = cellInfoLte.getCellSignalStrength();
                        CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
                        r0 = a(cellSignalStrength.getDbm(), cellIdentity.getCi(), cellIdentity.getTac(), 0);
                        if (r0 != null) {
                            jSONArray.put(r0);
                        }
                    }
                } else if (cellInfo instanceof CellInfoGsm) {
                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                    if (VERSION.SDK_INT > 17) {
                        CellSignalStrengthGsm cellSignalStrength2 = cellInfoGsm.getCellSignalStrength();
                        CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                        r0 = a(cellSignalStrength2.getDbm(), cellIdentity2.getCid(), cellIdentity2.getLac(), 1);
                        if (r0 != null) {
                            jSONArray.put(r0);
                        }
                    }
                } else if (cellInfo instanceof CellInfoCdma) {
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                    if (VERSION.SDK_INT > 17) {
                        CellSignalStrengthCdma cellSignalStrength3 = cellInfoCdma.getCellSignalStrength();
                        CellIdentityCdma cellIdentity3 = cellInfoCdma.getCellIdentity();
                        r0 = a(cellSignalStrength3.getDbm(), cellIdentity3.getBasestationId(), cellIdentity3.getNetworkId(), 2);
                        if (r0 != null) {
                            jSONArray.put(r0);
                        }
                    }
                } else if (cellInfo instanceof CellInfoWcdma) {
                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                    if (VERSION.SDK_INT > 17) {
                        CellSignalStrengthWcdma cellSignalStrength4 = cellInfoWcdma.getCellSignalStrength();
                        CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                        r0 = a(cellSignalStrength4.getDbm(), cellIdentity4.getCid(), cellIdentity4.getLac(), 3);
                        if (r0 != null) {
                            jSONArray.put(r0);
                        }
                    }
                }
            }
            a(jSONArray);
            e();
            return;
        }
        f();
    }

    public final JSONArray b() {
        return this.k;
    }

    public final void c() {
        this.k = null;
    }

    public final void d() {
        d.c("CellInfoManager", "stop lister");
        try {
            if (this.f != null && this.i != null) {
                this.f.listen(this.i, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
