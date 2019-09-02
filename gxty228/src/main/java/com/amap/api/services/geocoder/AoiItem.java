package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class AoiItem implements Parcelable {
    public static final Creator<AoiItem> CREATOR = new Creator<AoiItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public AoiItem a(Parcel parcel) {
            return new AoiItem(parcel);
        }

        public AoiItem[] a(int i) {
            return new AoiItem[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private LatLonPoint d;
    private Float e;

    public AoiItem(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.e = Float.valueOf(parcel.readFloat());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeParcelable(this.d, i);
        parcel.writeFloat(this.e.floatValue());
    }
}
