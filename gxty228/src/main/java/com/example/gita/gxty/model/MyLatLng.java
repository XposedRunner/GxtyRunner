package com.example.gita.gxty.model;

import com.amap.api.maps.model.LatLng;
import java.io.Serializable;

public class MyLatLng implements Serializable {
    public double latitude;
    public double longitude;
    public double speed;

    public MyLatLng(double d, double d2, double d3) {
        this.latitude = d;
        this.longitude = d2;
        this.speed = d3;
    }

    public String toString() {
        return "Latlng{latitude=" + this.latitude + ", longitude=" + this.longitude + '}';
    }

    public LatLng getMapLatLng() {
        return new LatLng(this.latitude, this.longitude);
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MyLatLng myLatLng = (MyLatLng) obj;
        if (this.latitude == myLatLng.latitude && this.longitude == myLatLng.longitude) {
            return true;
        }
        return false;
    }
}
