package com.example.gita.gxty.model;

import java.io.Serializable;
import org.altbeacon.beacon.Beacon;

public class Ibeacon implements Serializable {
    public String id;
    public String major;
    public String minor;
    public String name;
    public String number;
    public MyLatLng position;
    public String type;
    public String uuid;

    public String getMyKey() {
        return "" + this.uuid.toUpperCase() + this.major.toUpperCase() + this.minor.toUpperCase();
    }

    public static Ibeacon createIBeacon(Beacon beacon) {
        Ibeacon ibeacon = new Ibeacon();
        ibeacon.uuid = beacon.getId1() + "";
        ibeacon.major = beacon.getId2() + "";
        ibeacon.minor = beacon.getId3() + "";
        return ibeacon;
    }

    public String toString() {
        return "Ibeacon{uuid='" + this.uuid + '\'' + ", major='" + this.major + '\'' + ", minor='" + this.minor + '}';
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ibeacon ibeacon = (Ibeacon) obj;
        if (this.uuid.equalsIgnoreCase(ibeacon.uuid) && this.minor.equals(ibeacon.minor) && this.major.equals(ibeacon.major)) {
            return true;
        }
        return false;
    }
}
