package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class IndoorData implements Parcelable {
    public static final Creator<IndoorData> CREATOR = new Creator<IndoorData>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public IndoorData a(Parcel parcel) {
            return new IndoorData(parcel);
        }

        public IndoorData[] a(int i) {
            return new IndoorData[i];
        }
    };
    private String a;
    private int b;
    private String c;

    protected IndoorData(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readInt();
        this.c = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
    }
}
