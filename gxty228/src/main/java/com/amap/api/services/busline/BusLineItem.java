package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.a.af;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusLineItem implements Parcelable {
    public static final Creator<BusLineItem> CREATOR = new Creator<BusLineItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public BusLineItem a(Parcel parcel) {
            return new BusLineItem(parcel);
        }

        public BusLineItem[] a(int i) {
            return null;
        }
    };
    private float a;
    private String b;
    private String c;
    private String d;
    private List<LatLonPoint> e = new ArrayList();
    private List<LatLonPoint> f = new ArrayList();
    private String g;
    private String h;
    private String i;
    private Date j;
    private Date k;
    private String l;
    private float m;
    private float n;
    private List<BusStationItem> o = new ArrayList();

    public String a() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BusLineItem busLineItem = (BusLineItem) obj;
        if (this.g == null) {
            if (busLineItem.g != null) {
                return false;
            }
            return true;
        } else if (this.g.equals(busLineItem.g)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int i;
        if (this.g == null) {
            i = 0;
        } else {
            i = this.g.hashCode();
        }
        return i + 31;
    }

    public String toString() {
        return this.b + " " + af.a(this.j) + "-" + af.a(this.k);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeList(this.e);
        parcel.writeList(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(af.a(this.j));
        parcel.writeString(af.a(this.k));
        parcel.writeString(this.l);
        parcel.writeFloat(this.m);
        parcel.writeFloat(this.n);
        parcel.writeList(this.o);
    }

    public BusLineItem(Parcel parcel) {
        this.a = parcel.readFloat();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readArrayList(LatLonPoint.class.getClassLoader());
        this.f = parcel.readArrayList(LatLonPoint.class.getClassLoader());
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = af.a(parcel.readString());
        this.k = af.a(parcel.readString());
        this.l = parcel.readString();
        this.m = parcel.readFloat();
        this.n = parcel.readFloat();
        this.o = parcel.readArrayList(BusStationItem.class.getClassLoader());
    }
}
