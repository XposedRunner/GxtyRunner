package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class RidePath extends Path implements Parcelable {
    public static final Creator<RidePath> CREATOR = new Creator<RidePath>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RidePath a(Parcel parcel) {
            return new RidePath(parcel);
        }

        public RidePath[] a(int i) {
            return null;
        }
    };
    private List<RideStep> a = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.a);
    }

    public RidePath(Parcel parcel) {
        super(parcel);
        this.a = parcel.createTypedArrayList(RideStep.CREATOR);
    }
}
