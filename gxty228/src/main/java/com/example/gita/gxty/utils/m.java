package com.example.gita.gxty.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.example.gita.gxty.model.DataRun;
import com.example.gita.gxty.model.Ibeacon;
import com.example.gita.gxty.model.MyLatLng;
import com.example.gita.gxty.ram.service.RuningService;
import com.github.mikephil.charting.data.Entry;
import com.google.gson.b.a;
import com.google.gson.e;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PreferRunUtils */
public class m {
    private static m a = null;
    private static m b = null;
    private int c = 1;
    private SharedPreferences d;
    private SharedPreferences e;

    private m(Context context, int i) {
        this.c = i;
        if (i == 1) {
            this.d = context.getSharedPreferences("gxty_run_prefer", 0);
        } else {
            this.e = context.getSharedPreferences("gxty_run_prefer_simple", 0);
        }
    }

    public static m a(Context context, int i) {
        if (i == 1) {
            if (a == null) {
                a = new m(context, i);
            }
            return a;
        }
        if (b == null) {
            b = new m(context, i);
        }
        return b;
    }

    private SharedPreferences e() {
        if (this.c == 1) {
            return this.d;
        }
        return this.e;
    }

    public void a() {
        Editor edit = e().edit();
        edit.clear();
        edit.commit();
    }

    public synchronized void a(String str) {
        Editor edit = e().edit();
        edit.putString("run_server", str);
        edit.commit();
    }

    public String b() {
        return e().getString("run_server", "");
    }

    public long c() {
        return e().getLong("8endTimeLong", 0);
    }

    public void a(int i) {
        Editor edit = e().edit();
        edit.putInt("8Run8Status", i);
        edit.commit();
    }

    public void a(String str, String str2, String str3, String str4) {
        h.b("******缓存不变参数******");
        Editor edit = e().edit();
        edit.putString("8userid", str);
        edit.putString("8goal", str2);
        edit.putString("8startTime", str3);
        edit.putString("8runPageId", str4);
        edit.commit();
    }

    public void a(float f, long j, long j2, long j3, String str) {
        h.b("******缓存可变参数******");
        Editor edit = e().edit();
        if (f > 0.0f) {
            edit.putFloat("8totalLength", f);
        }
        if (j > 0) {
            edit.putLong("8endTimeLong", j);
        }
        if (j2 > 0) {
            edit.putLong("8duration", j2);
        }
        if (j3 > 0) {
            edit.putLong("8curentStep", j3);
        }
        edit.putString("8bupin", str);
        edit.commit();
    }

    public void a(ArrayList<MyLatLng> arrayList) {
        Editor edit = e().edit();
        if (!(arrayList == null || arrayList.isEmpty())) {
            h.b("******缓存途径点******");
            edit.putString("8tnodeList", b.b(arrayList));
        }
        edit.commit();
    }

    public void b(ArrayList<Ibeacon> arrayList) {
        Editor edit = e().edit();
        if (!(arrayList == null || arrayList.isEmpty())) {
            h.b("******缓存必径点******");
            edit.putString("8bnodeList", b.b(arrayList));
        }
        edit.commit();
    }

    public void a(List<MyLatLng> list, ArrayList<Entry> arrayList) {
        Editor edit = e().edit();
        if (!(list == null || list.isEmpty())) {
            h.b("******缓存轨迹******");
            edit.putString("8track", b.b(list));
        }
        if (!(arrayList == null || arrayList.isEmpty())) {
            h.b("******缓存折线图******");
            edit.putString("8trend", b.b(arrayList));
        }
        edit.commit();
    }

    public boolean d() {
        SharedPreferences e = e();
        float f = e.getFloat("8totalLength", 0.0f);
        long j = e.getLong("8duration", 0);
        if (f <= 50.0f || j <= 60) {
            return false;
        }
        return true;
    }

    public DataRun a(long j) {
        SharedPreferences e = e();
        DataRun dataRun = new DataRun();
        dataRun.type = this.c + "";
        dataRun.userid = e.getString("8userid", "");
        dataRun.goal = e.getString("8goal", "");
        dataRun.startTime = e.getString("8startTime", "");
        dataRun.runPageId = e.getString("8runPageId", "");
        float f = e.getFloat("8totalLength", 0.0f);
        long j2 = e.getLong("8endTimeLong", 0);
        long j3 = e.getLong("8duration", 0);
        long j4 = e.getLong("8curentStep", 0);
        dataRun.real = f + "";
        dataRun.endTime = d.a(j2);
        if (j > j3) {
            dataRun.duration = j + "";
        } else {
            dataRun.duration = j3 + "";
        }
        dataRun.speed = RuningService.a(RuningService.a(f, j3));
        dataRun.totalNum = j4 + "";
        dataRun.buPin = e.getString("8bupin", "0.0");
        dataRun.frombp = e.getInt("8Run8Status", 0) + "";
        e eVar = new e();
        try {
            dataRun.track = (List) eVar.a(e.getString("8track", "[]"), new a<ArrayList<MyLatLng>>(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }
            }.b());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            dataRun.bNode = (ArrayList) eVar.a(e.getString("8bnodeList", "[]"), new a<ArrayList<Ibeacon>>(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }
            }.b());
        } catch (Exception e22) {
            e22.printStackTrace();
        }
        try {
            dataRun.tNode = (ArrayList) eVar.a(e.getString("8tnodeList", "[]"), new a<ArrayList<MyLatLng>>(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }
            }.b());
        } catch (Exception e222) {
            e222.printStackTrace();
        }
        try {
            dataRun.trend = (ArrayList) eVar.a(e.getString("8trend", "[]"), new a<ArrayList<Entry>>(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }
            }.b());
        } catch (Exception e2222) {
            e2222.printStackTrace();
        }
        return dataRun;
    }
}
