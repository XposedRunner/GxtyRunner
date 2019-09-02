package com.amap.api.services.help;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class Tip implements Parcelable {
    public static final Creator<Tip> CREATOR = new Creator<Tip>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public Tip a(Parcel parcel) {
            return new Tip(parcel);
        }

        public Tip[] a(int i) {
            return null;
        }
    };
    private String a;
    private LatLonPoint b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public String toString() {
        return "name:" + this.c + " district:" + this.d + " adcode:" + this.e;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeString(this.d);
        parcel.writeString(this.a);
        parcel.writeValue(this.b);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }

    private Tip(Parcel parcel) {
        this.c = parcel.readString();
        this.e = parcel.readString();
        this.d = parcel.readString();
        this.a = parcel.readString();
        this.b = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f = parcel.readString();
        this.g = parcel.readString();
    }
}
