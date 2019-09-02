package com.amap.api.services.traffic;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.a.af;

public class RoadTrafficQuery extends a implements Parcelable, Cloneable {
    public static final Creator<RoadTrafficQuery> CREATOR = new Creator<RoadTrafficQuery>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public RoadTrafficQuery a(Parcel parcel) {
            return new RoadTrafficQuery(parcel);
        }

        public RoadTrafficQuery[] a(int i) {
            return new RoadTrafficQuery[i];
        }
    };
    private String b;
    private String c;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    public RoadTrafficQuery(String str, String str2, int i) {
        this.b = str;
        this.c = str2;
        this.a = i;
    }

    protected RoadTrafficQuery(Parcel parcel) {
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.a = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.a);
    }

    public RoadTrafficQuery a() {
        try {
            super.clone();
        } catch (Throwable e) {
            af.a(e, "RoadTrafficQuery", "RoadTrafficQueryClone");
        }
        return new RoadTrafficQuery(this.b, this.c, this.a);
    }
}
