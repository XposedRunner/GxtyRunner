package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;
import java.util.ArrayList;
import java.util.List;

public class WalkRouteResult extends RouteResult implements Parcelable {
    public static final Creator<WalkRouteResult> CREATOR = new Creator<WalkRouteResult>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public WalkRouteResult a(Parcel parcel) {
            return new WalkRouteResult(parcel);
        }

        public WalkRouteResult[] a(int i) {
            return new WalkRouteResult[i];
        }
    };
    private List<WalkPath> a = new ArrayList();
    private WalkRouteQuery b;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.a);
        parcel.writeParcelable(this.b, i);
    }

    public WalkRouteResult(Parcel parcel) {
        super(parcel);
        this.a = parcel.createTypedArrayList(WalkPath.CREATOR);
        this.b = (WalkRouteQuery) parcel.readParcelable(WalkRouteQuery.class.getClassLoader());
    }
}
