package com.example.gita.gxty.model;

import java.io.Serializable;
import java.util.HashSet;

public class SignPoint implements Serializable {
    public HashSet<Ibeacon> ibeacons;
    public boolean inBeacon;
    public MyLatLng latLng;
    public String time;

    public String toString() {
        return "SignPoint{time='" + this.time + '\'' + ", inBeacon=" + this.inBeacon + ", ibeacons=" + this.ibeacons + ", latLng=" + this.latLng + '}';
    }
}
