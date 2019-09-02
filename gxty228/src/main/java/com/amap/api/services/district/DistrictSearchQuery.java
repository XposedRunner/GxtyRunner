package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.a.af;

public class DistrictSearchQuery implements Parcelable, Cloneable {
    public static final Creator<DistrictSearchQuery> CREATOR = new Creator<DistrictSearchQuery>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public DistrictSearchQuery a(Parcel parcel) {
            boolean z;
            boolean z2 = true;
            DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery();
            districtSearchQuery.a(parcel.readString());
            districtSearchQuery.b(parcel.readString());
            districtSearchQuery.a(parcel.readInt());
            districtSearchQuery.b(parcel.readInt());
            if (parcel.readByte() == (byte) 1) {
                z = true;
            } else {
                z = false;
            }
            districtSearchQuery.b(z);
            if (parcel.readByte() == (byte) 1) {
                z = true;
            } else {
                z = false;
            }
            districtSearchQuery.a(z);
            if (parcel.readByte() != (byte) 1) {
                z2 = false;
            }
            districtSearchQuery.c(z2);
            return districtSearchQuery;
        }

        public DistrictSearchQuery[] a(int i) {
            return new DistrictSearchQuery[i];
        }
    };
    private int a = 1;
    private int b = 20;
    private String c;
    private String d;
    private boolean e = true;
    private boolean f = false;
    private boolean g = false;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void a(int i) {
        this.a = i;
    }

    public void b(int i) {
        this.b = i;
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(String str) {
        this.d = str;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public void c(boolean z) {
        this.f = z;
    }

    public int hashCode() {
        int i;
        int i2 = 1231;
        int i3 = 0;
        int i4 = ((this.g ? 1231 : 1237) + 31) * 31;
        if (this.c == null) {
            i = 0;
        } else {
            i = this.c.hashCode();
        }
        i = (i + i4) * 31;
        if (this.d != null) {
            i3 = this.d.hashCode();
        }
        i = (((((i + i3) * 31) + this.a) * 31) + this.b) * 31;
        if (!this.e) {
            i2 = 1237;
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
        DistrictSearchQuery districtSearchQuery = (DistrictSearchQuery) obj;
        if (this.g != districtSearchQuery.g) {
            return false;
        }
        if (this.c == null) {
            if (districtSearchQuery.c != null) {
                return false;
            }
        } else if (!this.c.equals(districtSearchQuery.c)) {
            return false;
        }
        if (this.a != districtSearchQuery.a) {
            return false;
        }
        if (this.b != districtSearchQuery.b) {
            return false;
        }
        if (this.e != districtSearchQuery.e) {
            return false;
        }
        return true;
    }

    public DistrictSearchQuery a() {
        try {
            super.clone();
        } catch (Throwable e) {
            af.a(e, "DistrictSearchQuery", "clone");
        }
        DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery();
        districtSearchQuery.a(this.c);
        districtSearchQuery.b(this.d);
        districtSearchQuery.a(this.a);
        districtSearchQuery.b(this.b);
        districtSearchQuery.b(this.e);
        districtSearchQuery.a(this.g);
        districtSearchQuery.c(this.f);
        return districtSearchQuery;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        if (this.e) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.g) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.f) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
    }
}
