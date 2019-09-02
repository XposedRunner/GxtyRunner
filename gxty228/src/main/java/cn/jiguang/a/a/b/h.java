package cn.jiguang.a.a.b;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import cn.jiguang.e.d;
import cn.jiguang.g.a;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.lzy.okgo.model.Progress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class h {
    private WifiManager a;
    private Context b = null;
    private JSONArray c;

    public h(Context context) {
        this.a = (WifiManager) context.getApplicationContext().getSystemService(IXAdSystemUtils.NT_WIFI);
        this.b = context;
    }

    private List<i> a(JSONArray jSONArray) {
        d.a("WifiInfoManager", "wifi dump");
        if (!d()) {
            return null;
        }
        i iVar;
        ArrayList arrayList;
        WifiInfo connectionInfo = this.a.getConnectionInfo();
        if (connectionInfo != null) {
            i iVar2 = new i(this, connectionInfo.getBSSID(), connectionInfo.getRssi(), connectionInfo.getSSID());
            d.a("WifiInfoManager", "currentWifi:" + iVar2.toString());
            iVar = iVar2;
        } else {
            iVar = null;
        }
        ArrayList arrayList2 = new ArrayList();
        if (iVar != null) {
            JSONObject a = iVar.a();
            a.put(Progress.TAG, "connect");
            jSONArray.put(a);
        }
        List scanResults = VERSION.SDK_INT < 23 ? this.a.getScanResults() : (this.b == null || !a.a(this.b, "android.permission.ACCESS_COARSE_LOCATION")) ? null : this.a.getScanResults();
        if (r0 != null && r0.size() > 0) {
            int i = -200;
            i iVar3 = null;
            for (ScanResult iVar4 : r0) {
                i iVar5 = new i(this, iVar4);
                d.a("WifiInfoManager", "wifi:" + iVar5.toString());
                if (iVar == null) {
                    d.a("WifiInfoManager", "currentWIFI is null");
                } else if (iVar.equals(iVar5)) {
                    d.a("WifiInfoManager", "this wifiInfo same with connect wifiInfo");
                } else {
                    int i2;
                    arrayList2.add(iVar5);
                    if (iVar5.c.equals(iVar.c) || iVar5.b <= i) {
                        iVar2 = iVar3;
                        i2 = i;
                    } else {
                        i iVar6 = iVar5;
                        i2 = iVar5.b;
                        iVar2 = iVar6;
                    }
                    iVar3 = iVar2;
                    i = i2;
                }
            }
            Collections.sort(arrayList2);
            int i3 = 10;
            if (iVar3 != null) {
                a = iVar3.a();
                a.put(Progress.TAG, "strongest");
                jSONArray.put(a);
                arrayList2.remove(iVar3);
                i3 = 9;
            }
            if (iVar != null) {
                arrayList2.remove(iVar);
                i3--;
            }
            if (arrayList2.size() > i3) {
                Collection subList = arrayList2.subList(0, i3);
                arrayList = new ArrayList();
                arrayList.addAll(subList);
                return arrayList;
            }
        }
        arrayList = arrayList2;
        return arrayList;
    }

    private boolean d() {
        try {
            return this.a.isWifiEnabled();
        } catch (Throwable e) {
            d.e("WifiInfoManager", "Wifi Error", e);
            return false;
        }
    }

    public final void a() {
        this.c = null;
    }

    public final void b() {
        int i = 0;
        if (a.a(this.b, "android.permission.ACCESS_WIFI_STATE")) {
            Context context = this.b;
            int i2 = (a.a(context, "android.permission.ACCESS_COARSE_LOCATION") && a.a(context, "android.permission.ACCESS_WIFI_STATE") && a.a(context, "android.permission.CHANGE_WIFI_STATE") && a.a(context, "android.permission.ACCESS_FINE_LOCATION")) ? 1 : 0;
            if (i2 == 0 && !a.n(this.b)) {
                return;
            }
            if (this.a.isWifiEnabled()) {
                JSONArray jSONArray = new JSONArray();
                try {
                    List<i> a = a(jSONArray);
                    if (a != null) {
                        i = a.size();
                    }
                    d.a("WifiInfoManager", "wifi count:" + i);
                    if (a != null) {
                        for (i a2 : a) {
                            jSONArray.put(a2.a());
                        }
                    }
                } catch (Throwable th) {
                    d.i("WifiInfoManager", "unexpected!" + th.getMessage());
                }
                d.a("WifiInfoManager", "report wifi info:" + jSONArray.toString());
                this.c = jSONArray;
                return;
            }
            d.a("WifiInfoManager", "wifi was disabled");
            return;
        }
        d.g("WifiInfoManager", "Require the permissionandroid.permission.ACCESS_WIFI_STATE");
    }

    public final JSONArray c() {
        return this.c;
    }
}
