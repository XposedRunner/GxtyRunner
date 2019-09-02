package com.amap.api.services.routepoisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class RoutePOIItem implements Parcelable {
    public static final Creator<RoutePOIItem> CREATOR = new Creator<RoutePOIItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RoutePOIItem a(Parcel parcel) {
            return new RoutePOIItem(parcel);
        }

        public RoutePOIItem[] a(int i) {
            return new RoutePOIItem[i];
        }
    };
    private String a;
    private String b;
    private LatLonPoint c;
    private float d;
    private float e;

    protected RoutePOIItem(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.d = parcel.readFloat();
        this.e = parcel.readFloat();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeParcelable(this.c, i);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
    }
}
