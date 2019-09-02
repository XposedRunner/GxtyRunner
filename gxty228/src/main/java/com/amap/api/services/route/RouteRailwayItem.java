package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class RouteRailwayItem extends Railway implements Parcelable {
    public static final Creator<RouteRailwayItem> CREATOR = new Creator<RouteRailwayItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RouteRailwayItem a(Parcel parcel) {
            return new RouteRailwayItem(parcel);
        }

        public RouteRailwayItem[] a(int i) {
            return new RouteRailwayItem[i];
        }
    };
    private String a;
    private String b;
    private float c;
    private String d;
    private RailwayStationItem e;
    private RailwayStationItem f;
    private List<RailwayStationItem> g = new ArrayList();
    private List<Railway> h = new ArrayList();
    private List<RailwaySpace> i = new ArrayList();

    protected RouteRailwayItem(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readFloat();
        this.d = parcel.readString();
        this.e = (RailwayStationItem) parcel.readParcelable(RailwayStationItem.class.getClassLoader());
        this.f = (RailwayStationItem) parcel.readParcelable(RailwayStationItem.class.getClassLoader());
        this.g = parcel.createTypedArrayList(RailwayStationItem.CREATOR);
        this.h = parcel.createTypedArrayList(Railway.CREATOR);
        this.i = parcel.createTypedArrayList(RailwaySpace.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeFloat(this.c);
        parcel.writeString(this.d);
        parcel.writeParcelable(this.e, i);
        parcel.writeParcelable(this.f, i);
        parcel.writeTypedList(this.g);
        parcel.writeTypedList(this.h);
        parcel.writeTypedList(this.i);
    }

    public int describeContents() {
        return 0;
    }
}
