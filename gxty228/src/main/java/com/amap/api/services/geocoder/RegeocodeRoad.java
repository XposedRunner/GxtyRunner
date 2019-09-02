package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class RegeocodeRoad implements Parcelable {
    public static final Creator<RegeocodeRoad> CREATOR = new Creator<RegeocodeRoad>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RegeocodeRoad a(Parcel parcel) {
            return new RegeocodeRoad(parcel);
        }

        public RegeocodeRoad[] a(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private float c;
    private String d;
    private LatLonPoint e;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeFloat(this.c);
        parcel.writeString(this.d);
        parcel.writeValue(this.e);
    }

    private RegeocodeRoad(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readFloat();
        this.d = parcel.readString();
        this.e = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
    }
}
