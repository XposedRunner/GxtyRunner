package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class StreetNumber implements Parcelable {
    public static final Creator<StreetNumber> CREATOR = new Creator<StreetNumber>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public StreetNumber a(Parcel parcel) {
            return new StreetNumber(parcel);
        }

        public StreetNumber[] a(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private LatLonPoint c;
    private String d;
    private float e;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeValue(this.c);
        parcel.writeString(this.d);
        parcel.writeFloat(this.e);
    }

    private StreetNumber(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.readFloat();
    }
}
