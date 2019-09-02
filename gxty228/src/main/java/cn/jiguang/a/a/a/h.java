package cn.jiguang.a.a.a;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import cn.jiguang.d.d.o;
import cn.jiguang.e.d;
import cn.jiguang.g.a;
import cn.jiguang.g.n;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class h extends Thread {
    private static final Object i = new Object();
    private int a;
    private Context b;
    private WifiManager c;
    private String d;
    private String e;
    private String f;
    private int g;
    private boolean[] h;

    private h(WifiManager wifiManager, String str, String str2, String str3, Context context, int i, int i2) {
        this.g = 2;
        this.c = wifiManager;
        this.a = i;
        this.b = context;
        this.g = i2;
        this.d = str2;
        this.e = str3;
        this.f = str;
        if (i2 == 2) {
            this.h = new boolean[3];
        }
    }

    private ArrayList<b> a(String str) {
        BufferedReader bufferedReader;
        IOException e;
        IOException iOException;
        Throwable th;
        Process a;
        ArrayList<b> arrayList;
        try {
            a = c.b("cat /proc/net/arp");
            if (a == null) {
                if (a != null) {
                    a.destroy();
                }
                return null;
            }
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(a.getInputStream()));
                try {
                    bufferedReader.readLine();
                    arrayList = new ArrayList();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            try {
                                b b = b(readLine);
                                if (!(b == null || !b.b().equals("0x2") || str.equals(b.a()) || b.c().equals("00:00:00:00:00:00"))) {
                                    d.e("ArpUtil", "arpInfo:" + b.toString());
                                    arrayList.add(b);
                                }
                            } catch (Exception e2) {
                                try {
                                    d.i("ArpUtil", "getArpInfo parseArp :" + e2.toString());
                                } catch (IOException e3) {
                                    e = e3;
                                }
                            }
                        } else {
                            try {
                                break;
                            } catch (IOException e4) {
                                d.i("ArpUtil", "getArpInfo  close inputstream" + e4.toString());
                            }
                        }
                    }
                    bufferedReader.close();
                    if (a == null) {
                        return arrayList;
                    }
                    a.destroy();
                    return arrayList;
                } catch (IOException e5) {
                    iOException = e5;
                    arrayList = null;
                    e4 = iOException;
                    try {
                        d.i("ArpUtil", "getArpInfo " + e4.toString());
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e42) {
                                d.i("ArpUtil", "getArpInfo  close inputstream" + e42.toString());
                            }
                        }
                        if (a != null) {
                            return arrayList;
                        }
                        a.destroy();
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e422) {
                                d.i("ArpUtil", "getArpInfo  close inputstream" + e422.toString());
                            }
                        }
                        if (a != null) {
                            a.destroy();
                        }
                        throw th;
                    }
                }
            } catch (IOException e52) {
                bufferedReader = null;
                iOException = e52;
                arrayList = null;
                e422 = iOException;
                d.i("ArpUtil", "getArpInfo " + e422.toString());
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (a != null) {
                    return arrayList;
                }
                a.destroy();
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (a != null) {
                    a.destroy();
                }
                throw th;
            }
        } catch (IOException e522) {
            a = null;
            bufferedReader = null;
            iOException = e522;
            arrayList = null;
            e422 = iOException;
            d.i("ArpUtil", "getArpInfo " + e422.toString());
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (a != null) {
                return arrayList;
            }
            a.destroy();
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            a = null;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (a != null) {
                a.destroy();
            }
            throw th;
        }
    }

    private JSONObject a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, ArrayList<b> arrayList) {
        JSONObject jSONObject = new JSONObject();
        try {
            o.b(this.b, jSONObject, "mac_list");
            jSONObject.put("ssid", str);
            jSONObject.put("bssid", str2);
            jSONObject.put("local_ip", str3);
            jSONObject.put("local_mac", str4);
            jSONObject.put("netmask", str5);
            JSONArray jSONArray = new JSONArray();
            if (!TextUtils.isEmpty(str6)) {
                jSONArray.put(str6);
            }
            if (!TextUtils.isEmpty(str7)) {
                jSONArray.put(str7);
            }
            jSONObject.put("dns", jSONArray);
            jSONObject.put("gateway", str8);
            jSONObject.put("dhcp", str9);
            JSONArray jSONArray2 = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("ip", bVar.a());
                jSONObject2.put("mac", bVar.c());
                jSONArray2.put(jSONObject2);
            }
            jSONObject.put("data", jSONArray2);
        } catch (JSONException e) {
            d.i("ArpUtil", "formatReportData :" + e.toString());
        }
        return jSONObject;
    }

    private void a(String str, byte[] bArr) {
        d.e("ArpUtil", "PING............");
        if (this.g == 2) {
            for (int i = 0; i < 3; i++) {
                this.h[i] = false;
                int i2 = (i * 85) + 0;
                byte[] bArr2 = bArr;
                new Thread(new f(new d(str, this.a), bArr2, i2, i2 + 85, new j(this, new n(), i))).start();
            }
            synchronized (i) {
                while (b()) {
                    try {
                        i.wait();
                    } catch (InterruptedException e) {
                        d.i("ArpUtil", "doPing :" + e.toString());
                    }
                }
            }
        } else {
            new d(str, this.a).a(bArr, 0, 255);
        }
        d.e("ArpUtil", "finish PING");
    }

    private static b b(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            d.e("ArpUtil", "arpinfo line is empty, give up parse.");
            return null;
        }
        byte[] bytes = str.getBytes();
        b bVar = new b();
        int i2 = 0;
        int i3 = 0;
        while (i3 < bytes.length - 1) {
            i3++;
            if (bytes[i3] == (byte) 32) {
                if (i3 - i2 > 1) {
                    String str2 = new String(bytes, i2, i3 - i2);
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i == 3) {
                                    bVar.d(str2);
                                    break;
                                }
                            }
                            bVar.c(str2);
                        } else {
                            bVar.b(str2);
                        }
                    } else {
                        bVar.a(str2);
                    }
                    i++;
                }
                i2 = i3 + 1;
            }
        }
        return bVar;
    }

    private boolean b() {
        for (boolean z : this.h) {
            if (!z) {
                return true;
            }
        }
        return false;
    }

    public final void run() {
        DhcpInfo dhcpInfo = this.c.getDhcpInfo();
        if (dhcpInfo == null) {
            d.c("ArpUtil", "DhcpInfo is null");
            return;
        }
        d.e("ArpUtil", "DhcpInfo is :" + dhcpInfo.toString());
        byte[] a = new byte[]{(byte) ((int) (255 & ((long) dhcpInfo.ipAddress))), (byte) ((int) ((((long) dhcpInfo.ipAddress) >> 8) & 255)), (byte) ((int) ((((long) dhcpInfo.ipAddress) >> 16) & 255)), (byte) ((int) ((((long) dhcpInfo.ipAddress) >> 24) & 255))};
        String a2 = c.a(dhcpInfo.ipAddress);
        if (TextUtils.equals(a2, "0.0.0.0")) {
            a2 = "";
        }
        String b = a.b(this.b, "");
        String a3 = c.a(dhcpInfo.netmask);
        if (TextUtils.equals(a3, "0.0.0.0")) {
            a3 = "";
        }
        String a4 = c.a(dhcpInfo.dns1);
        if (TextUtils.equals(a4, "0.0.0.0")) {
            a4 = "";
        }
        String a5 = c.a(dhcpInfo.dns2);
        if (TextUtils.equals(a5, "0.0.0.0")) {
            a5 = "";
        }
        String a6 = c.a(dhcpInfo.gateway);
        if (TextUtils.equals(a6, "0.0.0.0")) {
            a6 = "";
        }
        String a7 = c.a(dhcpInfo.serverAddress);
        if (TextUtils.equals(a7, "0.0.0.0")) {
            a7 = "";
        }
        n nVar = new n();
        try {
            a(a7, a);
        } catch (Exception e) {
            d.i("ArpUtil", "doPing :" + e.toString());
        }
        nVar.a("ArpUtil", "ping");
        ArrayList arrayList = null;
        try {
            arrayList = a(a7);
        } catch (Exception e2) {
            d.i("ArpUtil", "getArpInfo :" + e2.toString());
        }
        if (arrayList == null || arrayList.isEmpty()) {
            d.c("ArpUtil", "arp info is empty, give up report.");
        } else {
            JSONObject a8 = a(this.e, this.d, a2, b, a3, a4, a5, a6, a7, arrayList);
            d.c("ArpUtil", a8.toString());
            o.a(this.b, new JSONArray().put(a8), new i(this));
        }
        c.c(2);
    }
}
