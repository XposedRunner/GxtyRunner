package com.amap.api.services.traffic;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.a.af;
import com.amap.api.services.core.LatLonPoint;

public class CircleTrafficQuery extends a implements Parcelable, Cloneable {
    public static final Creator<CircleTrafficQuery> CREATOR = new Creator<CircleTrafficQuery>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public CircleTrafficQuery a(Parcel parcel) {
            return new CircleTrafficQuery(parcel);
        }

        public CircleTrafficQuery[] a(int i) {
            return new CircleTrafficQuery[i];
        }
    };
    private LatLonPoint b;
    private int c = 1000;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return a();
    }

    public CircleTrafficQuery(LatLonPoint latLonPoint, int i, int i2) {
        this.b = latLonPoint;
        this.c = i;
        this.a = i2;
    }

    protected CircleTrafficQuery(Parcel parcel) {
        this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.c = parcel.readInt();
        this.a = parcel.readInt();
    }

    public CircleTrafficQuery a() {
        try {
            super.clone();
        } catch (Throwable e) {
            af.a(e, "CircleTrafficQuery", "CircleTrafficQueryClone");
        }
        return new CircleTrafficQuery(this.b, this.c, this.a);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.b, i);
        parcel.writeInt(this.c);
        parcel.writeInt(this.a);
    }
}
