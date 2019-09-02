package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.road.Road;
import java.util.ArrayList;
import java.util.List;

public final class RegeocodeAddress implements Parcelable {
    public static final Creator<RegeocodeAddress> CREATOR = new Creator<RegeocodeAddress>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RegeocodeAddress a(Parcel parcel) {
            return new RegeocodeAddress(parcel);
        }

        public RegeocodeAddress[] a(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private StreetNumber h;
    private String i;
    private String j;
    private String k;
    private List<RegeocodeRoad> l;
    private List<Crossroad> m;
    private List<PoiItem> n;
    private List<BusinessArea> o;
    private List<AoiItem> p;
    private String q;

    public RegeocodeAddress() {
        this.l = new ArrayList();
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeValue(this.h);
        parcel.writeList(this.l);
        parcel.writeList(this.m);
        parcel.writeList(this.n);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeList(this.o);
        parcel.writeList(this.p);
        parcel.writeString(this.k);
        parcel.writeString(this.q);
    }

    private RegeocodeAddress(Parcel parcel) {
        this.l = new ArrayList();
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.p = new ArrayList();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = (StreetNumber) parcel.readValue(StreetNumber.class.getClassLoader());
        this.l = parcel.readArrayList(Road.class.getClassLoader());
        this.m = parcel.readArrayList(Crossroad.class.getClassLoader());
        this.n = parcel.readArrayList(PoiItem.class.getClassLoader());
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.o = parcel.readArrayList(BusinessArea.class.getClassLoader());
        this.p = parcel.readArrayList(AoiItem.class.getClassLoader());
        this.k = parcel.readString();
        this.q = parcel.readString();
    }
}
