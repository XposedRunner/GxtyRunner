package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.List;

public class TruckRouteRestult implements Parcelable {
    public static final Creator<TruckRouteRestult> CREATOR = new Creator<TruckRouteRestult>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public TruckRouteRestult a(Parcel parcel) {
            return new TruckRouteRestult(parcel);
        }

        public TruckRouteRestult[] a(int i) {
            return new TruckRouteRestult[i];
        }
    };
    private List<TruckPath> a;
    private LatLonPoint b;
    private LatLonPoint c;

    protected TruckRouteRestult(Parcel parcel) {
        this.a = parcel.createTypedArrayList(TruckPath.CREATOR);
        this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.c = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.a);
        parcel.writeParcelable(this.b, i);
        parcel.writeParcelable(this.c, i);
    }
}
