package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.amap.api.mapcore.util.en;

public final class LatLngBounds implements Parcelable {
    public static final LatLngBoundsCreator CREATOR = new LatLngBoundsCreator();
    private final int a;
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        private double a = Double.POSITIVE_INFINITY;
        private double b = Double.NEGATIVE_INFINITY;
        private double c = Double.NaN;
        private double d = Double.NaN;

        public Builder include(LatLng latLng) {
            if (latLng != null) {
                this.a = Math.min(this.a, latLng.latitude);
                this.b = Math.max(this.b, latLng.latitude);
                double d = latLng.longitude;
                if (Double.isNaN(this.c)) {
                    this.c = d;
                    this.d = d;
                } else if (!a(d)) {
                    if (LatLngBounds.c(this.c, d) < LatLngBounds.d(this.d, d)) {
                        this.c = d;
                    } else {
                        this.d = d;
                    }
                }
            }
            return this;
        }

        private boolean a(double d) {
            boolean z = false;
            if (this.c > this.d) {
                if (this.c <= d || d <= this.d) {
                    z = true;
                }
                return z;
            } else if (this.c > d || d > this.d) {
                return false;
            } else {
                return true;
            }
        }

        public LatLngBounds build() {
            if (Double.isNaN(this.c)) {
                Log.w("LatLngBounds", "no included points");
                return null;
            }
            if (this.c > this.d) {
                double d = this.c;
                this.c = this.d;
                this.d = d;
            }
            if (this.a > this.b) {
                d = this.a;
                this.a = this.b;
                this.b = d;
            }
            return new LatLngBounds(new LatLng(this.a, this.c, false), new LatLng(this.b, this.d, false));
        }
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        Object obj = 1;
        if (latLng == null) {
            try {
                throw new RuntimeRemoteException("null southwest");
            } catch (Throwable th) {
                Log.e("LatLngBounds", "the structure parameters are illegal!");
                th.printStackTrace();
                obj = null;
            }
        } else if (latLng2 == null) {
            throw new RuntimeRemoteException("null northeast");
        } else {
            if (latLng2.latitude < latLng.latitude) {
                throw new RuntimeRemoteException("southern latitude exceeds northern latitude (" + latLng.latitude + " > " + latLng2.latitude + ")");
            }
            if (obj == null) {
                i = 0;
            }
            this.a = i;
            if (obj == null) {
                latLng = null;
            }
            this.southwest = latLng;
            if (obj == null) {
                latLng2 = null;
            }
            this.northeast = latLng2;
        }
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    int a() {
        return this.a;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean contains(LatLng latLng) {
        if (latLng == null) {
            return false;
        }
        if (this.northeast == null || this.southwest == null) {
            Log.e("LatLngBounds", "current LatLngBounds is invalid, please check the structure parameters are legal");
            return false;
        } else if (a(latLng.latitude) && b(latLng.longitude)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(LatLngBounds latLngBounds) {
        if (latLngBounds != null && contains(latLngBounds.southwest) && contains(latLngBounds.northeast)) {
            return true;
        }
        return false;
    }

    public boolean intersects(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            return false;
        }
        if (this.northeast == null || this.southwest == null) {
            Log.e("LatLngBounds", "current LatLngBounds is invalid, please check the structure parameters are legal");
            return false;
        } else if (a(latLngBounds) || latLngBounds.a(this)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean a(LatLngBounds latLngBounds) {
        if (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null) {
            return false;
        }
        double d = ((latLngBounds.northeast.latitude + latLngBounds.southwest.latitude) - this.northeast.latitude) - this.southwest.latitude;
        double d2 = ((this.northeast.latitude - this.southwest.latitude) + latLngBounds.northeast.latitude) - latLngBounds.southwest.latitude;
        if (Math.abs(((latLngBounds.northeast.longitude + latLngBounds.southwest.longitude) - this.northeast.longitude) - this.southwest.longitude) >= ((this.northeast.longitude - this.southwest.longitude) + latLngBounds.northeast.longitude) - this.southwest.longitude || Math.abs(d) >= d2) {
            return false;
        }
        return true;
    }

    public LatLngBounds including(LatLng latLng) {
        if (latLng == null) {
            return this;
        }
        if (this.northeast == null || this.southwest == null) {
            Log.e("LatLngBounds", "current LatLngBounds is invalid, please check the structure parameters are legal");
            return this;
        }
        double d;
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        double d4 = latLng.longitude;
        if (b(d4)) {
            d4 = d3;
            d = d2;
        } else if (c(d3, d4) < d(d2, d4)) {
            d = d2;
        } else {
            d = d4;
            d4 = d3;
        }
        try {
            return new LatLngBounds(new LatLng(min, d4, false), new LatLng(max, d, false));
        } catch (Throwable th) {
            th.printStackTrace();
            return this;
        }
    }

    private static double c(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    private static double d(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    private boolean a(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    private boolean b(double d) {
        boolean z = false;
        if (this.southwest.longitude > this.northeast.longitude) {
            if (this.southwest.longitude <= d || d <= this.northeast.longitude) {
                z = true;
            }
            return z;
        } else if (this.southwest.longitude > d || d > this.northeast.longitude) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        return en.a(new Object[]{this.southwest, this.northeast});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        if (this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return en.a(en.a("southwest", this.southwest), en.a("northeast", this.northeast));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        LatLngBoundsCreator.a(this, parcel, i);
    }
}
