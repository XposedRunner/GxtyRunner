package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class BusStationItem implements Parcelable {
    public static final Creator<BusStationItem> CREATOR = new Creator<BusStationItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public BusStationItem a(Parcel parcel) {
            return new BusStationItem(parcel);
        }

        public BusStationItem[] a(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private LatLonPoint c;
    private String d;
    private String e;
    private List<BusLineItem> f;

    public BusStationItem() {
        this.f = new ArrayList();
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
        BusStationItem busStationItem = (BusStationItem) obj;
        if (this.a == null) {
            if (busStationItem.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(busStationItem.a)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        int i;
        if (this.a == null) {
            i = 0;
        } else {
            i = this.a.hashCode();
        }
        return i + 31;
    }

    public String toString() {
        return "BusStationName: " + this.b + " LatLonPoint: " + this.c.toString() + " BusLines: " + a(this.f) + " CityCode: " + this.d + " AdCode: " + this.e;
    }

    private String a(List<BusLineItem> list) {
        StringBuffer stringBuffer = new StringBuffer();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append(((BusLineItem) list.get(i)).a());
                if (i < list.size() - 1) {
                    stringBuffer.append("|");
                }
            }
        }
        return stringBuffer.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.a);
        parcel.writeValue(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeList(this.f);
    }

    private BusStationItem(Parcel parcel) {
        this.f = new ArrayList();
        this.b = parcel.readString();
        this.a = parcel.readString();
        this.c = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readArrayList(BusLineItem.class.getClassLoader());
    }
}
