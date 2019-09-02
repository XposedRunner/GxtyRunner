package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.a.af;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class DistanceSearch {
    private com.amap.api.services.b.a a;

    public static class DistanceQuery implements Parcelable, Cloneable {
        public static final Creator<DistanceQuery> CREATOR = new Creator<DistanceQuery>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public DistanceQuery a(Parcel parcel) {
                return new DistanceQuery(parcel);
            }

            public DistanceQuery[] a(int i) {
                return new DistanceQuery[i];
            }
        };
        private int a = 1;
        private List<LatLonPoint> b = new ArrayList();
        private LatLonPoint c;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        protected DistanceQuery(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.createTypedArrayList(LatLonPoint.CREATOR);
            this.c = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        }

        public DistanceQuery a() {
            try {
                super.clone();
            } catch (Throwable e) {
                af.a(e, "DistanceSearch", "DistanceQueryclone");
            }
            DistanceQuery distanceQuery = new DistanceQuery();
            distanceQuery.a(this.a);
            distanceQuery.a(this.b);
            distanceQuery.a(this.c);
            return distanceQuery;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeTypedList(this.b);
            parcel.writeParcelable(this.c, i);
        }

        public void a(int i) {
            this.a = i;
        }

        public void a(List<LatLonPoint> list) {
            if (list != null) {
                this.b = list;
            }
        }

        public void a(LatLonPoint latLonPoint) {
            this.c = latLonPoint;
        }
    }

    public interface a {
    }

    public void setDistanceSearchListener(a aVar) {
        if (this.a != null) {
            this.a.setDistanceSearchListener(aVar);
        }
    }
}
