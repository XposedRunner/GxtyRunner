package com.amap.api.maps.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.github.mikephil.charting.utils.Utils;

public class CircleHoleOptions extends BaseHoleOptions implements Parcelable {
    public static final Creator<CircleHoleOptions> CREATOR = new Creator<CircleHoleOptions>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public CircleHoleOptions a(Parcel parcel) {
            return new CircleHoleOptions(parcel);
        }

        public CircleHoleOptions[] a(int i) {
            return new CircleHoleOptions[i];
        }
    };
    private LatLng a = null;
    private double b = Utils.DOUBLE_EPSILON;

    public CircleHoleOptions center(LatLng latLng) {
        this.a = latLng;
        return this;
    }

    public CircleHoleOptions radius(double d) {
        this.b = d;
        return this;
    }

    public LatLng getCenter() {
        return this.a;
    }

    public double getRadius() {
        return this.b;
    }

    protected CircleHoleOptions(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        this.a = new LatLng(readBundle.getDouble("lat"), readBundle.getDouble("lng"));
        this.b = parcel.readDouble();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        if (this.a != null) {
            bundle.putDouble("lat", this.a.latitude);
            bundle.putDouble("lng", this.a.longitude);
        }
        parcel.writeBundle(bundle);
        parcel.writeDouble(this.b);
    }
}
