package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DistanceItem implements Parcelable {
    public static final Creator<DistanceItem> CREATOR = new Creator<DistanceItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public DistanceItem a(Parcel parcel) {
            return new DistanceItem(parcel);
        }

        public DistanceItem[] a(int i) {
            return new DistanceItem[i];
        }
    };
    public final int a = 1;
    public final int b = 2;
    public final int c = 3;
    private int d = 1;
    private int e = 1;
    private float f = 0.0f;
    private float g = 0.0f;
    private String h;
    private int i;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeFloat(this.f);
        parcel.writeFloat(this.g);
        parcel.writeString(this.h);
        parcel.writeInt(this.i);
    }

    protected DistanceItem(Parcel parcel) {
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readFloat();
        this.g = parcel.readFloat();
        this.h = parcel.readString();
        this.i = parcel.readInt();
    }
}
