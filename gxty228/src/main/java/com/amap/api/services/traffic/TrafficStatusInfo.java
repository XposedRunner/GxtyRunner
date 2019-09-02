package com.amap.api.services.traffic;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.List;

public class TrafficStatusInfo implements Parcelable {
    public static final Creator<TrafficStatusInfo> CREATOR = new Creator<TrafficStatusInfo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public TrafficStatusInfo a(Parcel parcel) {
            return new TrafficStatusInfo(parcel);
        }

        public TrafficStatusInfo[] a(int i) {
            return new TrafficStatusInfo[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private int d;
    private float e;
    private String f;
    private List<LatLonPoint> g;

    protected TrafficStatusInfo(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readFloat();
        this.f = parcel.readString();
        this.g = parcel.readArrayList(TrafficStatusInfo.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeFloat(this.e);
        parcel.writeString(this.f);
        parcel.writeTypedList(this.g);
    }
}
