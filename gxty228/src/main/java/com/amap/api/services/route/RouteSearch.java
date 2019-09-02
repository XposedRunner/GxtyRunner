package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.a.af;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class RouteSearch {
    private com.amap.api.services.b.b a;

    public static class BusRouteQuery implements Parcelable, Cloneable {
        public static final Creator<BusRouteQuery> CREATOR = new Creator<BusRouteQuery>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public BusRouteQuery a(Parcel parcel) {
                return new BusRouteQuery(parcel);
            }

            public BusRouteQuery[] a(int i) {
                return new BusRouteQuery[i];
            }
        };
        private FromAndTo a;
        private int b;
        private String c;
        private String d;
        private int e;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        public BusRouteQuery(FromAndTo fromAndTo, int i, String str, int i2) {
            this.a = fromAndTo;
            this.b = i;
            this.c = str;
            this.e = i2;
        }

        public void a(String str) {
            this.d = str;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeInt(this.b);
            parcel.writeString(this.c);
            parcel.writeInt(this.e);
            parcel.writeString(this.d);
        }

        public BusRouteQuery(Parcel parcel) {
            this.a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.readString();
            this.e = parcel.readInt();
            this.d = parcel.readString();
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((this.a == null ? 0 : this.a.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + 31) * 31)) * 31) + this.b) * 31) + this.e) * 31;
            if (this.d != null) {
                i = this.d.hashCode();
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            BusRouteQuery busRouteQuery = (BusRouteQuery) obj;
            if (this.c == null) {
                if (busRouteQuery.c != null) {
                    return false;
                }
            } else if (!this.c.equals(busRouteQuery.c)) {
                return false;
            }
            if (this.d == null) {
                if (busRouteQuery.d != null) {
                    return false;
                }
            } else if (!this.d.equals(busRouteQuery.d)) {
                return false;
            }
            if (this.a == null) {
                if (busRouteQuery.a != null) {
                    return false;
                }
            } else if (!this.a.equals(busRouteQuery.a)) {
                return false;
            }
            if (this.b != busRouteQuery.b) {
                return false;
            }
            if (this.e != busRouteQuery.e) {
                return false;
            }
            return true;
        }

        public BusRouteQuery a() {
            try {
                super.clone();
            } catch (Throwable e) {
                af.a(e, "RouteSearch", "BusRouteQueryclone");
            }
            BusRouteQuery busRouteQuery = new BusRouteQuery(this.a, this.b, this.c, this.e);
            busRouteQuery.a(this.d);
            return busRouteQuery;
        }
    }

    public static class DriveRouteQuery implements Parcelable, Cloneable {
        public static final Creator<DriveRouteQuery> CREATOR = new Creator<DriveRouteQuery>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public DriveRouteQuery a(Parcel parcel) {
                return new DriveRouteQuery(parcel);
            }

            public DriveRouteQuery[] a(int i) {
                return new DriveRouteQuery[i];
            }
        };
        private FromAndTo a;
        private int b;
        private List<LatLonPoint> c;
        private List<List<LatLonPoint>> d;
        private String e;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        public DriveRouteQuery(FromAndTo fromAndTo, int i, List<LatLonPoint> list, List<List<LatLonPoint>> list2, String str) {
            this.a = fromAndTo;
            this.b = i;
            this.c = list;
            this.d = list2;
            this.e = str;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeInt(this.b);
            parcel.writeTypedList(this.c);
            if (this.d == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(this.d.size());
                for (List writeTypedList : this.d) {
                    parcel.writeTypedList(writeTypedList);
                }
            }
            parcel.writeString(this.e);
        }

        public DriveRouteQuery(Parcel parcel) {
            this.a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
            int readInt = parcel.readInt();
            if (readInt == 0) {
                this.d = null;
            } else {
                this.d = new ArrayList();
            }
            for (int i = 0; i < readInt; i++) {
                this.d.add(parcel.createTypedArrayList(LatLonPoint.CREATOR));
            }
            this.e = parcel.readString();
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.a == null ? 0 : this.a.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + 31) * 31)) * 31)) * 31) + this.b) * 31;
            if (this.c != null) {
                i = this.c.hashCode();
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            DriveRouteQuery driveRouteQuery = (DriveRouteQuery) obj;
            if (this.e == null) {
                if (driveRouteQuery.e != null) {
                    return false;
                }
            } else if (!this.e.equals(driveRouteQuery.e)) {
                return false;
            }
            if (this.d == null) {
                if (driveRouteQuery.d != null) {
                    return false;
                }
            } else if (!this.d.equals(driveRouteQuery.d)) {
                return false;
            }
            if (this.a == null) {
                if (driveRouteQuery.a != null) {
                    return false;
                }
            } else if (!this.a.equals(driveRouteQuery.a)) {
                return false;
            }
            if (this.b != driveRouteQuery.b) {
                return false;
            }
            if (this.c == null) {
                if (driveRouteQuery.c != null) {
                    return false;
                }
                return true;
            } else if (this.c.equals(driveRouteQuery.c)) {
                return true;
            } else {
                return false;
            }
        }

        public DriveRouteQuery a() {
            try {
                super.clone();
            } catch (Throwable e) {
                af.a(e, "RouteSearch", "DriveRouteQueryclone");
            }
            return new DriveRouteQuery(this.a, this.b, this.c, this.d, this.e);
        }
    }

    public static class FromAndTo implements Parcelable, Cloneable {
        public static final Creator<FromAndTo> CREATOR = new Creator<FromAndTo>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public FromAndTo a(Parcel parcel) {
                return new FromAndTo(parcel);
            }

            public FromAndTo[] a(int i) {
                return new FromAndTo[i];
            }
        };
        private LatLonPoint a;
        private LatLonPoint b;
        private String c;
        private String d;
        private String e;
        private String f;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        public FromAndTo(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.a = latLonPoint;
            this.b = latLonPoint2;
        }

        public void a(String str) {
            this.c = str;
        }

        public void b(String str) {
            this.d = str;
        }

        public void c(String str) {
            this.e = str;
        }

        public void d(String str) {
            this.f = str;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeParcelable(this.b, i);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
            parcel.writeString(this.f);
        }

        public FromAndTo(Parcel parcel) {
            this.a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
            this.f = parcel.readString();
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.e == null ? 0 : this.e.hashCode()) + (((this.b == null ? 0 : this.b.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + (((this.a == null ? 0 : this.a.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.f != null) {
                i = this.f.hashCode();
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            FromAndTo fromAndTo = (FromAndTo) obj;
            if (this.d == null) {
                if (fromAndTo.d != null) {
                    return false;
                }
            } else if (!this.d.equals(fromAndTo.d)) {
                return false;
            }
            if (this.a == null) {
                if (fromAndTo.a != null) {
                    return false;
                }
            } else if (!this.a.equals(fromAndTo.a)) {
                return false;
            }
            if (this.c == null) {
                if (fromAndTo.c != null) {
                    return false;
                }
            } else if (!this.c.equals(fromAndTo.c)) {
                return false;
            }
            if (this.b == null) {
                if (fromAndTo.b != null) {
                    return false;
                }
            } else if (!this.b.equals(fromAndTo.b)) {
                return false;
            }
            if (this.e == null) {
                if (fromAndTo.e != null) {
                    return false;
                }
            } else if (!this.e.equals(fromAndTo.e)) {
                return false;
            }
            if (this.f == null) {
                if (fromAndTo.f != null) {
                    return false;
                }
                return true;
            } else if (this.f.equals(fromAndTo.f)) {
                return true;
            } else {
                return false;
            }
        }

        public FromAndTo a() {
            try {
                super.clone();
            } catch (Throwable e) {
                af.a(e, "RouteSearch", "FromAndToclone");
            }
            FromAndTo fromAndTo = new FromAndTo(this.a, this.b);
            fromAndTo.a(this.c);
            fromAndTo.b(this.d);
            fromAndTo.c(this.e);
            fromAndTo.d(this.f);
            return fromAndTo;
        }
    }

    public static class RideRouteQuery implements Parcelable, Cloneable {
        public static final Creator<RideRouteQuery> CREATOR = new Creator<RideRouteQuery>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public RideRouteQuery a(Parcel parcel) {
                return new RideRouteQuery(parcel);
            }

            public RideRouteQuery[] a(int i) {
                return new RideRouteQuery[i];
            }
        };
        private FromAndTo a;
        private int b;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        public RideRouteQuery(FromAndTo fromAndTo) {
            this.a = fromAndTo;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeInt(this.b);
        }

        public RideRouteQuery(Parcel parcel) {
            this.a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
        }

        public int hashCode() {
            return (((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            WalkRouteQuery walkRouteQuery = (WalkRouteQuery) obj;
            if (this.a == null) {
                if (walkRouteQuery.a != null) {
                    return false;
                }
            } else if (!this.a.equals(walkRouteQuery.a)) {
                return false;
            }
            if (this.b != walkRouteQuery.b) {
                return false;
            }
            return true;
        }

        public RideRouteQuery a() {
            try {
                super.clone();
            } catch (Throwable e) {
                af.a(e, "RouteSearch", "RideRouteQueryclone");
            }
            return new RideRouteQuery(this.a);
        }
    }

    public static class TruckRouteQuery implements Parcelable, Cloneable {
        public static final Creator<TruckRouteQuery> CREATOR = new Creator<TruckRouteQuery>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public TruckRouteQuery a(Parcel parcel) {
                return new TruckRouteQuery(parcel);
            }

            public TruckRouteQuery[] a(int i) {
                return new TruckRouteQuery[i];
            }
        };
        private FromAndTo a;
        private int b = 2;
        private int c;
        private List<LatLonPoint> d;
        private float e;
        private float f;
        private float g;
        private float h;
        private float i;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        public TruckRouteQuery(FromAndTo fromAndTo, int i, List<LatLonPoint> list, int i2) {
            this.a = fromAndTo;
            this.c = i;
            this.d = list;
            this.b = i2;
        }

        protected TruckRouteQuery(Parcel parcel) {
            this.a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.createTypedArrayList(LatLonPoint.CREATOR);
            this.e = parcel.readFloat();
            this.f = parcel.readFloat();
            this.g = parcel.readFloat();
            this.h = parcel.readFloat();
            this.i = parcel.readFloat();
        }

        public TruckRouteQuery a() {
            try {
                super.clone();
            } catch (Throwable e) {
                af.a(e, "RouteSearch", "TruckRouteQueryclone");
            }
            return new TruckRouteQuery(this.a, this.c, this.d, this.b);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeTypedList(this.d);
            parcel.writeFloat(this.e);
            parcel.writeFloat(this.f);
            parcel.writeFloat(this.g);
            parcel.writeFloat(this.h);
            parcel.writeFloat(this.i);
        }
    }

    public static class WalkRouteQuery implements Parcelable, Cloneable {
        public static final Creator<WalkRouteQuery> CREATOR = new Creator<WalkRouteQuery>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public WalkRouteQuery a(Parcel parcel) {
                return new WalkRouteQuery(parcel);
            }

            public WalkRouteQuery[] a(int i) {
                return new WalkRouteQuery[i];
            }
        };
        private FromAndTo a;
        private int b;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        public WalkRouteQuery(FromAndTo fromAndTo) {
            this.a = fromAndTo;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeInt(this.b);
        }

        public WalkRouteQuery(Parcel parcel) {
            this.a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
        }

        public int hashCode() {
            return (((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            WalkRouteQuery walkRouteQuery = (WalkRouteQuery) obj;
            if (this.a == null) {
                if (walkRouteQuery.a != null) {
                    return false;
                }
            } else if (!this.a.equals(walkRouteQuery.a)) {
                return false;
            }
            if (this.b != walkRouteQuery.b) {
                return false;
            }
            return true;
        }

        public WalkRouteQuery a() {
            try {
                super.clone();
            } catch (Throwable e) {
                af.a(e, "RouteSearch", "WalkRouteQueryclone");
            }
            return new WalkRouteQuery(this.a);
        }
    }

    public interface a {
    }

    public interface b {
    }

    public void setRouteSearchListener(a aVar) {
        if (this.a != null) {
            this.a.setRouteSearchListener(aVar);
        }
    }

    public void setOnTruckRouteSearchListener(b bVar) {
        if (this.a != null) {
            this.a.setOnTruckRouteSearchListener(bVar);
        }
    }
}
