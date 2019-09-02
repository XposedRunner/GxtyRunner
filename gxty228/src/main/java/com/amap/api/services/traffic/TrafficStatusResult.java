package com.amap.api.services.traffic;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class TrafficStatusResult implements Parcelable {
    public static final Creator<TrafficStatusResult> CREATOR = new Creator<TrafficStatusResult>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public TrafficStatusResult a(Parcel parcel) {
            return new TrafficStatusResult(parcel);
        }

        public TrafficStatusResult[] a(int i) {
            return new TrafficStatusResult[i];
        }
    };
    private String a;
    private TrafficStatusEvaluation b;
    private List<TrafficStatusInfo> c;

    protected TrafficStatusResult(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (TrafficStatusEvaluation) parcel.readParcelable(TrafficStatusEvaluation.class.getClassLoader());
        this.c = parcel.createTypedArrayList(TrafficStatusInfo.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeParcelable(this.b, i);
        parcel.writeTypedList(this.c);
    }
}
