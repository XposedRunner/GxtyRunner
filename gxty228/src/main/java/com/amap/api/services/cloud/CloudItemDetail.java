package com.amap.api.services.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CloudItemDetail extends CloudItem implements Parcelable {
    public static final Creator<CloudItemDetail> CREATOR = new Creator<CloudItemDetail>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public CloudItemDetail a(Parcel parcel) {
            return new CloudItemDetail(parcel);
        }

        public CloudItemDetail[] a(int i) {
            return new CloudItemDetail[i];
        }
    };

    protected CloudItemDetail(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
