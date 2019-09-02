package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class DistanceResult implements Parcelable {
    public static final Creator<DistanceResult> CREATOR = new Creator<DistanceResult>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public DistanceResult a(Parcel parcel) {
            return new DistanceResult(parcel);
        }

        public DistanceResult[] a(int i) {
            return new DistanceResult[i];
        }
    };
    private List<DistanceItem> a = null;

    protected DistanceResult(Parcel parcel) {
        this.a = parcel.createTypedArrayList(DistanceItem.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.a);
    }
}
