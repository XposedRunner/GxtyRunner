package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Path implements Parcelable {
    public static final Creator<Path> CREATOR = new Creator<Path>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public Path a(Parcel parcel) {
            return new Path(parcel);
        }

        public Path[] a(int i) {
            return null;
        }
    };
    private float a;
    private long b;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.a);
        parcel.writeLong(this.b);
    }

    public Path(Parcel parcel) {
        this.a = parcel.readFloat();
        this.b = parcel.readLong();
    }
}
