package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.maps.AMapException;

public final class LatLng implements Parcelable, Cloneable {
    public static final LatLngCreator CREATOR = new LatLngCreator();
    public final double latitude;
    public final double longitude;

    public LatLng(double d, double d2) {
        this(d, d2, true);
    }

    public LatLng(double d, double d2, boolean z) {
        if (z) {
            if (-180.0d > d2 || d2 >= 180.0d) {
                this.longitude = ((((d2 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
            } else {
                this.longitude = d2;
            }
            if (d < -90.0d || d > 90.0d) {
                try {
                    throw new AMapException(AMapException.ERROR_ILLEGAL_VALUE);
                } catch (AMapException e) {
                    e.printStackTrace();
                }
            }
            this.latitude = Math.max(-90.0d, Math.min(90.0d, d));
            return;
        }
        this.latitude = d;
        this.longitude = d2;
    }

    public LatLng clone() {
        return new LatLng(this.latitude, this.longitude);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        doubleToLongBits = Double.doubleToLongBits(this.longitude);
        return (31 * i) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        if (Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(latLng.latitude) && Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(latLng.longitude)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "lat/lng: (" + this.latitude + "," + this.longitude + ")";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.latitude);
    }
}
