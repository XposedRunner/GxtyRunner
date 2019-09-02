package com.amap.api.maps.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class CircleOptionsCreator implements Creator<CircleOptions> {
    public CircleOptions createFromParcel(Parcel parcel) {
        boolean z = true;
        CircleOptions circleOptions = new CircleOptions();
        Bundle readBundle = parcel.readBundle();
        circleOptions.center(new LatLng(readBundle.getDouble("lat"), readBundle.getDouble("lng")));
        circleOptions.radius(parcel.readDouble());
        circleOptions.strokeWidth(parcel.readFloat());
        circleOptions.strokeColor(parcel.readInt());
        circleOptions.fillColor(parcel.readInt());
        circleOptions.zIndex(parcel.readFloat());
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        circleOptions.visible(z);
        circleOptions.a = parcel.readString();
        Iterable arrayList = new ArrayList();
        parcel.readList(arrayList, BaseHoleOptions.class.getClassLoader());
        circleOptions.addHoles(arrayList);
        circleOptions.setStrokeDottedLineType(parcel.readInt());
        return circleOptions;
    }

    public CircleOptions[] newArray(int i) {
        return new CircleOptions[i];
    }
}
