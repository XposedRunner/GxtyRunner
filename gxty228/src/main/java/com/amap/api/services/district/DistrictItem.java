package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DistrictItem implements Parcelable {
    public static final Creator<DistrictItem> CREATOR = new Creator<DistrictItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public DistrictItem a(Parcel parcel) {
            return new DistrictItem(parcel);
        }

        public DistrictItem[] a(int i) {
            return new DistrictItem[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private LatLonPoint d;
    private String e;
    private List<DistrictItem> f;
    private String[] g;

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeParcelable(this.d, i);
        parcel.writeString(this.e);
        parcel.writeTypedList(this.f);
        parcel.writeInt(this.g.length);
        parcel.writeStringArray(this.g);
    }

    public DistrictItem() {
        this.f = new ArrayList();
        this.g = new String[0];
    }

    public int describeContents() {
        return 0;
    }

    public DistrictItem(Parcel parcel) {
        this.f = new ArrayList();
        this.g = new String[0];
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.e = parcel.readString();
        this.f = parcel.createTypedArrayList(CREATOR);
        this.g = new String[parcel.readInt()];
        parcel.readStringArray(this.g);
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((this.d == null ? 0 : this.d.hashCode()) + (((this.b == null ? 0 : this.b.hashCode()) + 31) * 31)) * 31;
        if (this.a == null) {
            i = 0;
        } else {
            i = this.a.hashCode();
        }
        hashCode = (((i + hashCode) * 31) + Arrays.hashCode(this.g)) * 31;
        if (this.f == null) {
            i = 0;
        } else {
            i = this.f.hashCode();
        }
        i = ((this.e == null ? 0 : this.e.hashCode()) + ((i + hashCode) * 31)) * 31;
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
        DistrictItem districtItem = (DistrictItem) obj;
        if (this.b == null) {
            if (districtItem.b != null) {
                return false;
            }
        } else if (!this.b.equals(districtItem.b)) {
            return false;
        }
        if (this.d == null) {
            if (districtItem.d != null) {
                return false;
            }
        } else if (!this.d.equals(districtItem.d)) {
            return false;
        }
        if (this.a == null) {
            if (districtItem.a != null) {
                return false;
            }
        } else if (!this.a.equals(districtItem.a)) {
            return false;
        }
        if (!Arrays.equals(this.g, districtItem.g)) {
            return false;
        }
        if (this.f == null) {
            if (districtItem.f != null) {
                return false;
            }
        } else if (!this.f.equals(districtItem.f)) {
            return false;
        }
        if (this.e == null) {
            if (districtItem.e != null) {
                return false;
            }
        } else if (!this.e.equals(districtItem.e)) {
            return false;
        }
        if (this.c == null) {
            if (districtItem.c != null) {
                return false;
            }
            return true;
        } else if (this.c.equals(districtItem.c)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "DistrictItem [mCitycode=" + this.a + ", mAdcode=" + this.b + ", mName=" + this.c + ", mCenter=" + this.d + ", mLevel=" + this.e + ", mDistricts=" + this.f + "]";
    }
}
