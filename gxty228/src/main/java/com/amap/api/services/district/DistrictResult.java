package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class DistrictResult implements Parcelable {
    public Creator<DistrictResult> a = new Creator<DistrictResult>(this) {
        final /* synthetic */ DistrictResult a;

        {
            this.a = r1;
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public DistrictResult a(Parcel parcel) {
            return new DistrictResult(parcel);
        }

        public DistrictResult[] a(int i) {
            return new DistrictResult[i];
        }
    };
    private DistrictSearchQuery b;
    private ArrayList<DistrictItem> c = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.b, i);
        parcel.writeTypedList(this.c);
    }

    protected DistrictResult(Parcel parcel) {
        this.b = (DistrictSearchQuery) parcel.readParcelable(DistrictSearchQuery.class.getClassLoader());
        this.c = parcel.createTypedArrayList(DistrictItem.CREATOR);
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.b == null) {
            i = 0;
        } else {
            i = this.b.hashCode();
        }
        i = (i + 31) * 31;
        if (this.c != null) {
            i2 = this.c.hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DistrictResult districtResult = (DistrictResult) obj;
        if (this.b == null) {
            if (districtResult.b != null) {
                return false;
            }
        } else if (!this.b.equals(districtResult.b)) {
            return false;
        }
        if (this.c == null) {
            if (districtResult.c != null) {
                return false;
            }
            return true;
        } else if (this.c.equals(districtResult.c)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "DistrictResult [mDisQuery=" + this.b + ", mDistricts=" + this.c + "]";
    }
}
