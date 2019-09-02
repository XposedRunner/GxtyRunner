package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class RailwayStationItem implements Parcelable {
    public static final Creator<RailwayStationItem> CREATOR = new Creator<RailwayStationItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RailwayStationItem a(Parcel parcel) {
            return new RailwayStationItem(parcel);
        }

        public RailwayStationItem[] a(int i) {
            return new RailwayStationItem[i];
        }
    };
    private String a;
    private String b;
    private LatLonPoint c;
    private String d;
    private String e;
    private boolean f = false;
    private boolean g = false;
    private float h;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeParcelable(this.c, i);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeBooleanArray(new boolean[]{this.f, this.g});
        parcel.writeFloat(this.h);
    }

    protected RailwayStationItem(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.readString();
        boolean[] zArr = new boolean[2];
        parcel.readBooleanArray(zArr);
        this.f = zArr[0];
        this.g = zArr[1];
        this.h = parcel.readFloat();
    }
}
