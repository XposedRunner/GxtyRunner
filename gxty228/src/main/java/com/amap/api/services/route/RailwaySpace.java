package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RailwaySpace implements Parcelable {
    public static final Creator<RailwaySpace> CREATOR = new Creator<RailwaySpace>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RailwaySpace a(Parcel parcel) {
            return new RailwaySpace(parcel);
        }

        public RailwaySpace[] a(int i) {
            return new RailwaySpace[i];
        }
    };
    private String a;
    private float b;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeFloat(this.b);
    }

    protected RailwaySpace(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readFloat();
    }
}
