package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: OfflineMapDownloadList */
public class ap {
    public ArrayList<OfflineMapProvince> a = new ArrayList();
    private ba b;
    private Context c;

    public ap(Context context, Handler handler) {
        this.c = context;
        this.b = ba.a(context);
    }

    private void a(av avVar) {
        if (this.b != null && avVar != null) {
            this.b.a(avVar);
        }
    }

    private void b(av avVar) {
        if (this.b != null) {
            this.b.b(avVar);
        }
    }

    private boolean a(int i, int i2) {
        return i2 != 1 || i <= 2 || i >= 98;
    }

    private boolean b(int i) {
        if (i == 4) {
            return true;
        }
        return false;
    }

    private boolean a(OfflineMapProvince offlineMapProvince) {
        if (offlineMapProvince == null) {
            return false;
        }
        Iterator it = offlineMapProvince.getCityList().iterator();
        while (it.hasNext()) {
            if (((OfflineMapCity) it.next()).getState() != 4) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<OfflineMapProvince> a() {
        ArrayList<OfflineMapProvince> arrayList = new ArrayList();
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                arrayList.add((OfflineMapProvince) it.next());
            }
        }
        return arrayList;
    }

    public OfflineMapCity a(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
                while (it2.hasNext()) {
                    OfflineMapCity offlineMapCity = (OfflineMapCity) it2.next();
                    if (offlineMapCity.getCode().equals(str)) {
                        return offlineMapCity;
                    }
                }
            }
            return null;
        }
    }

    public OfflineMapCity b(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
                while (it2.hasNext()) {
                    OfflineMapCity offlineMapCity = (OfflineMapCity) it2.next();
                    if (offlineMapCity.getCity().trim().equalsIgnoreCase(str.trim())) {
                        return offlineMapCity;
                    }
                }
            }
            return null;
        }
    }

    public OfflineMapProvince c(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (offlineMapProvince.getProvinceName().trim().equalsIgnoreCase(str.trim())) {
                    return offlineMapProvince;
                }
            }
            return null;
        }
    }

    public ArrayList<OfflineMapCity> b() {
        ArrayList<OfflineMapCity> arrayList = new ArrayList();
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
                while (it2.hasNext()) {
                    arrayList.add((OfflineMapCity) it2.next());
                }
            }
        }
        return arrayList;
    }

    public void a(List<OfflineMapProvince> list) {
        synchronized (this.a) {
            OfflineMapProvince offlineMapProvince;
            if (this.a.size() > 0) {
                for (int i = 0; i < this.a.size(); i++) {
                    offlineMapProvince = (OfflineMapProvince) this.a.get(i);
                    for (OfflineMapProvince offlineMapProvince2 : list) {
                        if (offlineMapProvince.getPinyin().equals(offlineMapProvince2.getPinyin())) {
                            break;
                        } else if ((offlineMapProvince.getPinyin().equals("quanguogaiyaotu") || offlineMapProvince.getProvinceCode().equals("000001") || offlineMapProvince.getProvinceCode().equals("100000")) && offlineMapProvince2.getPinyin().equals("quanguogaiyaotu")) {
                            break;
                        }
                    }
                    OfflineMapProvince offlineMapProvince22 = null;
                    if (offlineMapProvince22 != null) {
                        a(offlineMapProvince, offlineMapProvince22);
                        ArrayList cityList = offlineMapProvince.getCityList();
                        ArrayList cityList2 = offlineMapProvince22.getCityList();
                        for (int i2 = 0; i2 < cityList.size(); i2++) {
                            OfflineMapCity offlineMapCity;
                            OfflineMapCity offlineMapCity2 = (OfflineMapCity) cityList.get(i2);
                            Iterator it = cityList2.iterator();
                            while (it.hasNext()) {
                                offlineMapCity = (OfflineMapCity) it.next();
                                if (offlineMapCity2.getPinyin().equals(offlineMapCity.getPinyin())) {
                                    break;
                                }
                            }
                            offlineMapCity = null;
                            if (offlineMapCity != null) {
                                a(offlineMapCity2, offlineMapCity);
                            }
                        }
                    }
                }
            } else {
                for (OfflineMapProvince offlineMapProvince3 : list) {
                    this.a.add(offlineMapProvince3);
                }
            }
        }
    }

    private void a(OfflineMapCity offlineMapCity, OfflineMapCity offlineMapCity2) {
        offlineMapCity.setUrl(offlineMapCity2.getUrl());
        offlineMapCity.setVersion(offlineMapCity2.getVersion());
        offlineMapCity.setSize(offlineMapCity2.getSize());
        offlineMapCity.setCode(offlineMapCity2.getCode());
        offlineMapCity.setPinyin(offlineMapCity2.getPinyin());
        offlineMapCity.setJianpin(offlineMapCity2.getJianpin());
    }

    private void a(OfflineMapProvince offlineMapProvince, OfflineMapProvince offlineMapProvince2) {
        offlineMapProvince.setUrl(offlineMapProvince2.getUrl());
        offlineMapProvince.setVersion(offlineMapProvince2.getVersion());
        offlineMapProvince.setSize(offlineMapProvince2.getSize());
        offlineMapProvince.setPinyin(offlineMapProvince2.getPinyin());
        offlineMapProvince.setJianpin(offlineMapProvince2.getJianpin());
    }

    public ArrayList<OfflineMapCity> c() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (offlineMapProvince != null) {
                    for (OfflineMapCity offlineMapCity : offlineMapProvince.getCityList()) {
                        if (offlineMapCity.getState() == 4 || offlineMapCity.getState() == 7) {
                            arrayList.add(offlineMapCity);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapProvince> d() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (offlineMapProvince != null && (offlineMapProvince.getState() == 4 || offlineMapProvince.getState() == 7)) {
                    arrayList.add(offlineMapProvince);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapCity> e() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (offlineMapProvince != null) {
                    for (OfflineMapCity offlineMapCity : offlineMapProvince.getCityList()) {
                        if (a(offlineMapCity.getState())) {
                            arrayList.add(offlineMapCity);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapProvince> f() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (offlineMapProvince != null && a(offlineMapProvince.getState())) {
                    arrayList.add(offlineMapProvince);
                }
            }
        }
        return arrayList;
    }

    public boolean a(int i) {
        return i == 0 || i == 2 || i == 3 || i == 1 || i == 102 || i == 101 || i == 103 || i == -1;
    }

    public void a(be beVar) {
        String pinyin = beVar.getPinyin();
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            loop0:
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (offlineMapProvince != null) {
                    for (OfflineMapCity offlineMapCity : offlineMapProvince.getCityList()) {
                        if (offlineMapCity.getPinyin().trim().equals(pinyin.trim())) {
                            a(beVar, offlineMapCity);
                            a(beVar, offlineMapProvince);
                            break loop0;
                        }
                    }
                    continue;
                }
            }
        }
    }

    private void a(be beVar, OfflineMapCity offlineMapCity) {
        int b = beVar.c().b();
        if (beVar.c().equals(beVar.a)) {
            b(beVar.x());
        } else {
            if (beVar.c().equals(beVar.f)) {
                bk.a("saveJSONObjectToFile  CITY " + beVar.getCity());
                b(beVar);
                beVar.x().c();
            }
            if (a(beVar.getcompleteCode(), beVar.c().b())) {
                a(beVar.x());
            }
        }
        offlineMapCity.setState(b);
        offlineMapCity.setCompleteCode(beVar.getcompleteCode());
    }

    private void b(be beVar) {
        File[] listFiles = new File(en.c(this.c)).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile() && file.exists() && file.getName().contains(beVar.getAdcode()) && file.getName().endsWith(".zip.tmp.dt")) {
                    file.delete();
                }
            }
        }
    }

    private void a(be beVar, OfflineMapProvince offlineMapProvince) {
        int b = beVar.c().b();
        if (b == 6) {
            offlineMapProvince.setState(b);
            offlineMapProvince.setCompleteCode(0);
            b(new av(offlineMapProvince, this.c));
            try {
                bk.b(offlineMapProvince.getProvinceCode(), this.c);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (b(b) && a(offlineMapProvince)) {
            av avVar;
            if (beVar.getPinyin().equals(offlineMapProvince.getPinyin())) {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(beVar.getcompleteCode());
                offlineMapProvince.setVersion(beVar.getVersion());
                offlineMapProvince.setUrl(beVar.getUrl());
                avVar = new av(offlineMapProvince, this.c);
                avVar.a(beVar.a());
                avVar.d(beVar.getCode());
            } else {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(100);
                avVar = new av(offlineMapProvince, this.c);
            }
            avVar.c();
            a(avVar);
            bk.a("saveJSONObjectToFile  province " + avVar.d());
        }
    }

    public void g() {
        h();
        this.b = null;
        this.c = null;
    }

    public void h() {
        if (this.a != null) {
            synchronized (this.a) {
                this.a.clear();
            }
        }
    }
}
