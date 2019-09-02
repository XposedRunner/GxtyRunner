package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import cn.jiguang.net.HttpUtils;
import com.amap.api.mapcore.util.cf.a;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import java.io.File;

/* compiled from: CityObject */
public class be extends OfflineMapCity implements at, bm {
    public static final Creator<be> o = new Creator<be>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public be a(Parcel parcel) {
            return new be(parcel);
        }

        public be[] a(int i) {
            return new be[i];
        }
    };
    public final bp a;
    public final bp b;
    public final bp c;
    public final bp d;
    public final bp e;
    public final bp f;
    public final bp g;
    public final bp h;
    public final bp i;
    public final bp j;
    public final bp k;
    bp l;
    Context m;
    boolean n;
    private String p;
    private String q;
    private long r;

    public void a(String str) {
        this.q = str;
    }

    public String a() {
        return this.q;
    }

    public String b() {
        return getUrl();
    }

    public be(Context context, OfflineMapCity offlineMapCity) {
        this(context, offlineMapCity.getState());
        setCity(offlineMapCity.getCity());
        setUrl(offlineMapCity.getUrl());
        setState(offlineMapCity.getState());
        setCompleteCode(offlineMapCity.getcompleteCode());
        setAdcode(offlineMapCity.getAdcode());
        setVersion(offlineMapCity.getVersion());
        setSize(offlineMapCity.getSize());
        setCode(offlineMapCity.getCode());
        setJianpin(offlineMapCity.getJianpin());
        setPinyin(offlineMapCity.getPinyin());
        t();
    }

    public be(Context context, int i) {
        this.a = new br(6, this);
        this.b = new by(2, this);
        this.c = new bu(0, this);
        this.d = new bw(3, this);
        this.e = new bx(1, this);
        this.f = new bq(4, this);
        this.g = new bv(7, this);
        this.h = new bs(-1, this);
        this.i = new bs(101, this);
        this.j = new bs(102, this);
        this.k = new bs(103, this);
        this.p = null;
        this.q = "";
        this.n = false;
        this.r = 0;
        this.m = context;
        a(i);
    }

    public void a(int i) {
        switch (i) {
            case -1:
                this.l = this.h;
                break;
            case 0:
                this.l = this.c;
                break;
            case 1:
                this.l = this.e;
                break;
            case 2:
                this.l = this.b;
                break;
            case 3:
                this.l = this.d;
                break;
            case 4:
                this.l = this.f;
                break;
            case 6:
                this.l = this.a;
                break;
            case 7:
                this.l = this.g;
                break;
            case 101:
                this.l = this.i;
                break;
            case 102:
                this.l = this.j;
                break;
            case 103:
                this.l = this.k;
                break;
            default:
                if (i < 0) {
                    this.l = this.h;
                    break;
                }
                break;
        }
        setState(i);
    }

    public void a(bp bpVar) {
        this.l = bpVar;
        setState(bpVar.b());
    }

    public bp c() {
        return this.l;
    }

    public void d() {
        al a = al.a(this.m);
        if (a != null) {
            a.c(this);
        }
    }

    public void e() {
        al a = al.a(this.m);
        if (a != null) {
            a.e(this);
            d();
        }
    }

    public void f() {
        bk.a("CityOperation current State==>" + c().b());
        if (this.l.equals(this.d)) {
            this.l.d();
        } else if (this.l.equals(this.c)) {
            this.l.e();
        } else if (this.l.equals(this.g) || this.l.equals(this.h)) {
            k();
            this.n = true;
        } else if (this.l.equals(this.j) || this.l.equals(this.i) || this.l.a(this.k)) {
            this.l.c();
        } else {
            c().h();
        }
    }

    public void g() {
        this.l.e();
    }

    public void h() {
        this.l.a(this.k.b());
    }

    public void i() {
        this.l.a();
        if (this.n) {
            this.l.h();
        }
        this.n = false;
    }

    public void j() {
        if (this.l.equals(this.f)) {
            this.l.f();
        } else {
            this.l.f();
        }
    }

    public void k() {
        al a = al.a(this.m);
        if (a != null) {
            a.a(this);
        }
    }

    public void l() {
        al a = al.a(this.m);
        if (a != null) {
            a.b(this);
        }
    }

    public void m() {
        al a = al.a(this.m);
        if (a != null) {
            a.d(this);
        }
    }

    public void n() {
        this.r = 0;
        if (!this.l.equals(this.b)) {
            bk.a("state must be waiting when download onStart");
        }
        this.l.c();
    }

    public void a(long j, long j2) {
        long j3 = (100 * j2) / j;
        if (((int) j3) != getcompleteCode()) {
            setCompleteCode((int) j3);
            d();
        }
    }

    public void o() {
        if (!this.l.equals(this.c)) {
            bk.a("state must be Loading when download onFinish");
        }
        this.l.g();
    }

    public void a(a aVar) {
        int i = 6;
        switch (aVar) {
            case amap_exception:
                i = this.j.b();
                break;
            case file_io_exception:
                i = this.k.b();
                break;
            case network_exception:
                i = this.i.b();
                break;
        }
        if (this.l.equals(this.c) || this.l.equals(this.b)) {
            this.l.a(i);
        }
    }

    public void p() {
        e();
    }

    public void q() {
        this.r = 0;
        setCompleteCode(0);
        if (this.l.equals(this.e)) {
            this.l.c();
        } else {
            this.l.c();
        }
    }

    public void r() {
        if (this.l.equals(this.e)) {
            this.l.a(this.h.b());
        } else {
            this.l.a(this.h.b());
        }
    }

    public void a(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.r > 500) {
            if (((int) j) > getcompleteCode()) {
                setCompleteCode((int) j);
                d();
            }
            this.r = currentTimeMillis;
        }
    }

    public void b(String str) {
        Object u;
        Object v;
        if (this.l.equals(this.e)) {
            this.q = str;
            u = u();
            v = v();
        } else {
            this.q = str;
            u = u();
            v = v();
        }
        if (TextUtils.isEmpty(u) || TextUtils.isEmpty(v)) {
            r();
            return;
        }
        File file = new File(v + HttpUtils.PATHS_SEPARATOR);
        File file2 = new File(en.a(this.m) + File.separator + "map/");
        File file3 = new File(en.a(this.m));
        if (!file3.exists() && !file3.mkdir()) {
            return;
        }
        if (file2.exists() || file2.mkdir()) {
            a(file, file2, u);
        }
    }

    public void s() {
        e();
    }

    protected void t() {
        String str = al.a;
        String c = bk.c(getUrl());
        if (c != null) {
            this.p = str + c + ".zip" + ".tmp";
        } else {
            this.p = str + getPinyin() + ".zip" + ".tmp";
        }
    }

    public String u() {
        if (TextUtils.isEmpty(this.p)) {
            return null;
        }
        return this.p.substring(0, this.p.lastIndexOf("."));
    }

    public String v() {
        if (TextUtils.isEmpty(this.p)) {
            return null;
        }
        String u = u();
        return u.substring(0, u.lastIndexOf(46));
    }

    private void a(final File file, File file2, final String str) {
        new bc().a(file, file2, -1, bk.a(file), new bc.a(this) {
            final /* synthetic */ be c;

            public void a(String str, String str2, float f) {
                int i = (int) (60.0d + (((double) f) * 0.39d));
                if (i - this.c.getcompleteCode() > 0 && System.currentTimeMillis() - this.c.r > 1000) {
                    this.c.setCompleteCode(i);
                    this.c.r = System.currentTimeMillis();
                }
            }

            public void a(String str, String str2) {
            }

            public void b(String str, String str2) {
                try {
                    if (new File(str).delete()) {
                        bk.b(file);
                        this.c.setCompleteCode(100);
                        this.c.l.g();
                    }
                } catch (Exception e) {
                    this.c.l.a(this.c.k.b());
                }
            }

            public void a(String str, String str2, int i) {
                this.c.l.a(this.c.k.b());
            }
        });
    }

    public boolean w() {
        return ((double) bk.a()) < (((double) getSize()) * 2.5d) - ((double) (((long) getcompleteCode()) * getSize())) ? false : false;
    }

    public av x() {
        setState(this.l.b());
        av avVar = new av((OfflineMapCity) this, this.m);
        avVar.a(a());
        bk.a("vMapFileNames: " + a());
        return avVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.q);
    }

    public be(Parcel parcel) {
        super(parcel);
        this.a = new br(6, this);
        this.b = new by(2, this);
        this.c = new bu(0, this);
        this.d = new bw(3, this);
        this.e = new bx(1, this);
        this.f = new bq(4, this);
        this.g = new bv(7, this);
        this.h = new bs(-1, this);
        this.i = new bs(101, this);
        this.j = new bs(102, this);
        this.k = new bs(103, this);
        this.p = null;
        this.q = "";
        this.n = false;
        this.r = 0;
        this.q = parcel.readString();
    }

    public boolean y() {
        return w();
    }

    public String z() {
        StringBuffer stringBuffer = new StringBuffer();
        String c = bk.c(getUrl());
        if (c != null) {
            stringBuffer.append(c);
        } else {
            stringBuffer.append(getPinyin());
        }
        stringBuffer.append(".zip");
        return stringBuffer.toString();
    }

    public String A() {
        return getAdcode();
    }

    public String B() {
        return u();
    }

    public String C() {
        return v();
    }

    public bp b(int i) {
        switch (i) {
            case 101:
                return this.i;
            case 102:
                return this.j;
            case 103:
                return this.k;
            default:
                return this.h;
        }
    }
}
