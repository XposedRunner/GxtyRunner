package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class District implements Parcelable {
    public static final Creator<District> CREATOR = new Creator<District>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public District a(Parcel parcel) {
            return new District(parcel);
        }

        public District[] a(int i) {
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

    public District(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }
}
