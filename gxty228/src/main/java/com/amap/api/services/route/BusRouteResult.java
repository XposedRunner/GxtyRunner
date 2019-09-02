package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;
import java.util.ArrayList;
import java.util.List;

public class BusRouteResult extends RouteResult implements Parcelable {
    public static final Creator<BusRouteResult> CREATOR = new Creator<BusRouteResult>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public BusRouteResult a(Parcel parcel) {
            return new BusRouteResult(parcel);
        }

        public BusRouteResult[] a(int i) {
            return new BusRouteResult[i];
        }
    };
    private float a;
    private List<BusPath> b = new ArrayList();
    private BusRouteQuery c;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.a);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.c, i);
    }

    public BusRouteResult(Parcel parcel) {
        super(parcel);
        this.a = parcel.readFloat();
        this.b = parcel.createTypedArrayList(BusPath.CREATOR);
        this.c = (BusRouteQuery) parcel.readParcelable(BusRouteQuery.class.getClassLoader());
    }
}
