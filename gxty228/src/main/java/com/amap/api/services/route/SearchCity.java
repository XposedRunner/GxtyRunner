package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SearchCity implements Parcelable {
    public static final Creator<SearchCity> CREATOR = new Creator<SearchCity>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public SearchCity a(Parcel parcel) {
            return new SearchCity(parcel);
        }

        public SearchCity[] a(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private String c;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    public SearchCity(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }
}
