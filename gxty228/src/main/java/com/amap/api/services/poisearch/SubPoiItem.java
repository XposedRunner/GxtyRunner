package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class SubPoiItem implements Parcelable {
    public static final Creator<SubPoiItem> CREATOR = new Creator<SubPoiItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public SubPoiItem a(Parcel parcel) {
            return new SubPoiItem(parcel);
        }

        public SubPoiItem[] a(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private String c;
    private int d;
    private LatLonPoint e;
    private String f;
    private String g;

    public SubPoiItem(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f = parcel.readString();
        this.g = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeValue(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }
}
