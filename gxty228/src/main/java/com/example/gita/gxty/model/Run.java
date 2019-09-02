package com.example.gita.gxty.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Run implements Serializable {
    public int beaconcount = 1;
    public String dayTarget;
    public int distance;
    public int gpscount = 2;
    public ArrayList<MyLatLng> gpsinfo;
    public ArrayList<Ibeacon> ibeacon;
    public String length;
    public int maxSeconds;
    public int peisu;
    public String runPageId;

    public String toString() {
        return "Run{length='" + this.length + '\'' + ", dayTarget='" + this.dayTarget + '\'' + ", ibeacon=" + this.ibeacon + ", beaconcount=" + this.beaconcount + ", gpscount=" + this.gpscount + ", distance=" + this.distance + ", peisu=" + this.peisu + ", runPageId='" + this.runPageId + '\'' + ", gpsinfo=" + this.gpsinfo + '}';
    }
}
