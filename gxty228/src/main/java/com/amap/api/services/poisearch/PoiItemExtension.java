package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PoiItemExtension implements Parcelable {
    public static final Creator<PoiItemExtension> CREATOR = new Creator<PoiItemExtension>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public PoiItemExtension a(Parcel parcel) {
            return new PoiItemExtension(parcel);
        }

        public PoiItemExtension[] a(int i) {
            return new PoiItemExtension[i];
        }
    };
    private String a;
    private String b;

    protected PoiItemExtension(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }

    public int describeContents() {
        return 0;
    }
}
