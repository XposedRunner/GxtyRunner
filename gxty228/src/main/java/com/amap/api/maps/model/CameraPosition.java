package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.amap.api.mapcore.util.ee;
import com.amap.api.mapcore.util.en;
import com.amap.api.mapcore.util.gz;
import com.github.mikephil.charting.utils.Utils;

public final class CameraPosition implements Parcelable {
    public static final CameraPositionCreator CREATOR = new CameraPositionCreator();
    public final float bearing;
    public final boolean isAbroad;
    public final LatLng target;
    public final float tilt;
    public final float zoom;

    public static final class Builder {
        private LatLng a;
        private float b;
        private float c;
        private float d;

        public Builder(CameraPosition cameraPosition) {
            target(cameraPosition.target).bearing(cameraPosition.bearing).tilt(cameraPosition.tilt).zoom(cameraPosition.zoom);
        }

        public Builder target(LatLng latLng) {
            this.a = latLng;
            return this;
        }

        public Builder zoom(float f) {
            this.b = f;
            return this;
        }

        public Builder tilt(float f) {
            this.c = f;
            return this;
        }

        public Builder bearing(float f) {
            this.d = f;
            return this;
        }

        public CameraPosition build() {
            String str = "build";
            try {
                if (this.a != null) {
                    return new CameraPosition(this.a, this.b, this.c, this.d);
                }
                Log.w("CameraPosition", "target is null");
                return null;
            } catch (Throwable th) {
                gz.c(th, "CameraPosition", str);
                return null;
            }
        }
    }

    public CameraPosition(LatLng latLng, float f, float f2, float f3) {
        boolean z = false;
        if (latLng == null) {
            Log.w("CameraPosition", "构建CameraPosition时,位置(target)不能为null");
        }
        this.target = latLng;
        this.zoom = f;
        this.tilt = f2;
        if (((double) f3) <= Utils.DOUBLE_EPSILON) {
            f3 = (f3 % 360.0f) + 360.0f;
        }
        this.bearing = f3 % 360.0f;
        if (latLng != null) {
            if (!ee.a(latLng.latitude, latLng.longitude)) {
                z = true;
            }
            this.isAbroad = z;
            return;
        }
        this.isAbroad = false;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.bearing);
        parcel.writeFloat((float) this.target.latitude);
        parcel.writeFloat((float) this.target.longitude);
        parcel.writeFloat(this.tilt);
        parcel.writeFloat(this.zoom);
    }

    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public static final CameraPosition fromLatLngZoom(LatLng latLng, float f) {
        return new CameraPosition(latLng, f, 0.0f, 0.0f);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(CameraPosition cameraPosition) {
        return new Builder(cameraPosition);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        if (this.target.equals(cameraPosition.target) && Float.floatToIntBits(this.zoom) == Float.floatToIntBits(cameraPosition.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(cameraPosition.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(cameraPosition.bearing)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return en.a(en.a("target", this.target), en.a("zoom", Float.valueOf(this.zoom)), en.a("tilt", Float.valueOf(this.tilt)), en.a("bearing", Float.valueOf(this.bearing)));
    }
}
