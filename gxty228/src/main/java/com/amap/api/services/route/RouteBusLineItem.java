package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class RouteBusLineItem extends BusLineItem implements Parcelable {
    public static final Creator<RouteBusLineItem> CREATOR = new Creator<RouteBusLineItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RouteBusLineItem a(Parcel parcel) {
            return new RouteBusLineItem(parcel);
        }

        public RouteBusLineItem[] a(int i) {
            return null;
        }
    };
    private BusStationItem a;
    private BusStationItem b;
    private List<LatLonPoint> c = new ArrayList();
    private int d;
    private List<BusStationItem> e = new ArrayList();
    private float f;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, i);
        parcel.writeParcelable(this.b, i);
        parcel.writeTypedList(this.c);
        parcel.writeInt(this.d);
        parcel.writeTypedList(this.e);
        parcel.writeFloat(this.f);
    }

    public RouteBusLineItem(Parcel parcel) {
        super(parcel);
        this.a = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.b = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.d = parcel.readInt();
        this.e = parcel.createTypedArrayList(BusStationItem.CREATOR);
        this.f = parcel.readFloat();
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = super.hashCode() * 31;
        if (this.b == null) {
            i = 0;
        } else {
            i = this.b.hashCode();
        }
        i = (i + hashCode) * 31;
        if (this.a != null) {
            i2 = this.a.hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RouteBusLineItem routeBusLineItem = (RouteBusLineItem) obj;
        if (this.b == null) {
            if (routeBusLineItem.b != null) {
                return false;
            }
        } else if (!this.b.equals(routeBusLineItem.b)) {
            return false;
        }
        if (this.a == null) {
            if (routeBusLineItem.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(routeBusLineItem.a)) {
            return true;
        } else {
            return false;
        }
    }
}
