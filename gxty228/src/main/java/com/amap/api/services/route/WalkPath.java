package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class WalkPath extends Path implements Parcelable {
    public static final Creator<WalkPath> CREATOR = new Creator<WalkPath>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public WalkPath a(Parcel parcel) {
            return new WalkPath(parcel);
        }

        public WalkPath[] a(int i) {
            return null;
        }
    };
    private List<WalkStep> a = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.a);
    }

    public WalkPath(Parcel parcel) {
        super(parcel);
        this.a = parcel.createTypedArrayList(WalkStep.CREATOR);
    }
}
