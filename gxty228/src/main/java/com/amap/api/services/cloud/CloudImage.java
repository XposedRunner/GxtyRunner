package com.amap.api.services.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CloudImage implements Parcelable {
    public static final Creator<CloudImage> CREATOR = new Creator<CloudImage>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public CloudImage a(Parcel parcel) {
            return new CloudImage(parcel);
        }

        public CloudImage[] a(int i) {
            return new CloudImage[i];
        }
    };
    private String a;
    private String b;
    private String c;

    public CloudImage(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }
}
