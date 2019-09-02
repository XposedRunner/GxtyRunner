package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.poisearch.IndoorData;
import com.amap.api.services.poisearch.Photo;
import com.amap.api.services.poisearch.PoiItemExtension;
import com.amap.api.services.poisearch.SubPoiItem;
import java.util.ArrayList;
import java.util.List;

public class PoiItem implements Parcelable {
    public static final Creator<PoiItem> CREATOR = new Creator<PoiItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public PoiItem a(Parcel parcel) {
            return new PoiItem(parcel);
        }

        public PoiItem[] a(int i) {
            return new PoiItem[i];
        }
    };
    private String A;
    private String B;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e = "";
    private int f = -1;
    private final LatLonPoint g;
    private final String h;
    private final String i;
    private LatLonPoint j;
    private LatLonPoint k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private boolean s;
    private IndoorData t;
    private String u;
    private String v;
    private String w;
    private List<SubPoiItem> x = new ArrayList();
    private List<Photo> y = new ArrayList();
    private PoiItemExtension z;

    protected PoiItem(Parcel parcel) {
        this.a = parcel.readString();
        this.c = parcel.readString();
        this.b = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readInt();
        this.g = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.d = parcel.readString();
        this.j = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.k = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.s = zArr[0];
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readString();
        this.u = parcel.readString();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.x = parcel.readArrayList(SubPoiItem.class.getClassLoader());
        this.t = (IndoorData) parcel.readValue(IndoorData.class.getClassLoader());
        this.y = parcel.createTypedArrayList(Photo.CREATOR);
        this.z = (PoiItemExtension) parcel.readParcelable(PoiItemExtension.class.getClassLoader());
        this.A = parcel.readString();
        this.B = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.c);
        parcel.writeString(this.b);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
        parcel.writeValue(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.d);
        parcel.writeValue(this.j);
        parcel.writeValue(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeBooleanArray(new boolean[]{this.s});
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        parcel.writeString(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        parcel.writeList(this.x);
        parcel.writeValue(this.t);
        parcel.writeTypedList(this.y);
        parcel.writeParcelable(this.z, i);
        parcel.writeString(this.A);
        parcel.writeString(this.B);
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
        PoiItem poiItem = (PoiItem) obj;
        if (this.a == null) {
            if (poiItem.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(poiItem.a)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public String toString() {
        return this.h;
    }
}
