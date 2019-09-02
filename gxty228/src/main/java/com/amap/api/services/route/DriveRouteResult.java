package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;
import java.util.ArrayList;
import java.util.List;

public class DriveRouteResult extends RouteResult implements Parcelable {
    public static final Creator<DriveRouteResult> CREATOR = new Creator<DriveRouteResult>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public DriveRouteResult a(Parcel parcel) {
            return new DriveRouteResult(parcel);
        }

        public DriveRouteResult[] a(int i) {
            return new DriveRouteResult[i];
        }
    };
    private float a;
    private List<DrivePath> b = new ArrayList();
    private DriveRouteQuery c;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.a);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.c, i);
    }

    public DriveRouteResult(Parcel parcel) {
        super(parcel);
        this.a = parcel.readFloat();
        this.b = parcel.createTypedArrayList(DrivePath.CREATOR);
        this.c = (DriveRouteQuery) parcel.readParcelable(DriveRouteQuery.class.getClassLoader());
    }
}
