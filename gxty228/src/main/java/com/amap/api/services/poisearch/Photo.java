package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Photo implements Parcelable {
    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public Photo a(Parcel parcel) {
            return new Photo(parcel);
        }

        public Photo[] a(int i) {
            return null;
        }
    };
    private String a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }

    public Photo(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }
}
