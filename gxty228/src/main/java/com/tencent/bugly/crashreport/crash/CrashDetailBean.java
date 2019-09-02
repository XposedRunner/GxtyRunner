package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.proguard.ap;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
public class CrashDetailBean implements Parcelable, Comparable<CrashDetailBean> {
    public static final Creator<CrashDetailBean> CREATOR = new Creator<CrashDetailBean>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public CrashDetailBean a(Parcel parcel) {
            return new CrashDetailBean(parcel);
        }

        public CrashDetailBean[] a(int i) {
            return new CrashDetailBean[i];
        }
    };
    public String A = "";
    public String B = "";
    public long C = -1;
    public long D = -1;
    public long E = -1;
    public long F = -1;
    public long G = -1;
    public long H = -1;
    public String I = "";
    public String J = "";
    public String K = "";
    public String L = "";
    public String M = "";
    public long N = -1;
    public boolean O = false;
    public Map<String, String> P = null;
    public int Q = -1;
    public int R = -1;
    public Map<String, String> S = null;
    public Map<String, String> T = null;
    public byte[] U = null;
    public String V = null;
    public String W = null;
    public long a = -1;
    public int b = 0;
    public String c = UUID.randomUUID().toString();
    public boolean d = false;
    public String e = "";
    public String f = "";
    public String g = "";
    public Map<String, PlugInBean> h = null;
    public Map<String, PlugInBean> i = null;
    public boolean j = false;
    public boolean k = false;
    public int l = 0;
    public String m = "";
    public String n = "";
    public String o = "";
    public String p = "";
    public String q = "";
    public long r = -1;
    public String s = null;
    public int t = 0;
    public String u = "";
    public String v = "";
    public String w = null;
    public String x = null;
    public byte[] y = null;
    public Map<String, String> z = null;

    public /* synthetic */ int compareTo(Object obj) {
        return a((CrashDetailBean) obj);
    }

    public CrashDetailBean(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.b = parcel.readInt();
        this.c = parcel.readString();
        this.d = parcel.readByte() == (byte) 1;
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.j = z;
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.k = z;
        this.l = parcel.readInt();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readLong();
        this.s = parcel.readString();
        this.t = parcel.readInt();
        this.u = parcel.readString();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.z = ap.b(parcel);
        this.A = parcel.readString();
        this.B = parcel.readString();
        this.C = parcel.readLong();
        this.D = parcel.readLong();
        this.E = parcel.readLong();
        this.F = parcel.readLong();
        this.G = parcel.readLong();
        this.H = parcel.readLong();
        this.I = parcel.readString();
        this.J = parcel.readString();
        this.K = parcel.readString();
        this.L = parcel.readString();
        this.M = parcel.readString();
        this.N = parcel.readLong();
        if (parcel.readByte() != (byte) 1) {
            z2 = false;
        }
        this.O = z2;
        this.P = ap.b(parcel);
        this.h = ap.a(parcel);
        this.i = ap.a(parcel);
        this.Q = parcel.readInt();
        this.R = parcel.readInt();
        this.S = ap.b(parcel);
        this.T = ap.b(parcel);
        this.U = parcel.createByteArray();
        this.y = parcel.createByteArray();
        this.V = parcel.readString();
        this.W = parcel.readString();
        this.x = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
        parcel.writeByte((byte) (this.d ? 1 : 0));
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        if (this.j) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.k) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeLong(this.r);
        parcel.writeString(this.s);
        parcel.writeInt(this.t);
        parcel.writeString(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        ap.b(parcel, this.z);
        parcel.writeString(this.A);
        parcel.writeString(this.B);
        parcel.writeLong(this.C);
        parcel.writeLong(this.D);
        parcel.writeLong(this.E);
        parcel.writeLong(this.F);
        parcel.writeLong(this.G);
        parcel.writeLong(this.H);
        parcel.writeString(this.I);
        parcel.writeString(this.J);
        parcel.writeString(this.K);
        parcel.writeString(this.L);
        parcel.writeString(this.M);
        parcel.writeLong(this.N);
        if (!this.O) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        ap.b(parcel, this.P);
        ap.a(parcel, this.h);
        ap.a(parcel, this.i);
        parcel.writeInt(this.Q);
        parcel.writeInt(this.R);
        ap.b(parcel, this.S);
        ap.b(parcel, this.T);
        parcel.writeByteArray(this.U);
        parcel.writeByteArray(this.y);
        parcel.writeString(this.V);
        parcel.writeString(this.W);
        parcel.writeString(this.x);
    }

    public int a(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return 1;
        }
        long j = this.r - crashDetailBean.r;
        if (j > 0) {
            return 1;
        }
        if (j < 0) {
            return -1;
        }
        return 0;
    }
}
