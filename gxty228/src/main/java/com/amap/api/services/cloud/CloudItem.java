package com.amap.api.services.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloudItem implements Parcelable {
    public static final Creator<CloudItem> CREATOR = new Creator<CloudItem>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public CloudItem a(Parcel parcel) {
            return new CloudItem(parcel);
        }

        public CloudItem[] a(int i) {
            return new CloudItem[i];
        }
    };
    protected final LatLonPoint a;
    protected final String b;
    protected final String c;
    private String d;
    private int e = -1;
    private String f;
    private String g;
    private HashMap<String, String> h;
    private List<CloudImage> i;

    protected CloudItem(Parcel parcel) {
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.a = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = new HashMap();
        parcel.readMap(this.h, HashMap.class.getClassLoader());
        this.i = new ArrayList();
        parcel.readList(this.i, getClass().getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeValue(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeMap(this.h);
        parcel.writeList(this.i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CloudItem)) {
            return false;
        }
        CloudItem cloudItem = (CloudItem) obj;
        if (this.d == null) {
            if (cloudItem.d != null) {
                return false;
            }
            return true;
        } else if (this.d.equals(cloudItem.d)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (this.d == null ? 0 : this.d.hashCode()) + 31;
    }

    public String toString() {
        return this.b;
    }
}
