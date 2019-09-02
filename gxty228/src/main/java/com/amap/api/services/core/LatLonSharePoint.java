package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LatLonSharePoint extends LatLonPoint implements Parcelable {
    public static final Creator<LatLonSharePoint> CREATOR = new Creator<LatLonSharePoint>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public LatLonSharePoint a(Parcel parcel) {
            return new LatLonSharePoint(parcel);
        }

        public LatLonSharePoint[] a(int i) {
            return new LatLonSharePoint[i];
        }
    };
    private String a;

    protected LatLonSharePoint(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
    }

    public int hashCode() {
        int i;
        int hashCode = super.hashCode() * 31;
        if (this.a == null) {
            i = 0;
        } else {
            i = this.a.hashCode();
        }
        return i + hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LatLonSharePoint latLonSharePoint = (LatLonSharePoint) obj;
        if (this.a == null) {
            if (latLonSharePoint.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(latLonSharePoint.a)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return super.toString() + "," + this.a;
    }
}
