package com.example.gita.gxty_runner.hook.map;

import java.util.Objects;

public class LatLng implements Cloneable {
    public double latitude;
    public double longitude;

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public LatLng() {
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public LatLng clone() {
        LatLng clone;
        try {
            clone = (LatLng) super.clone();
        } catch (CloneNotSupportedException ignored) {
            clone = new LatLng(latitude, longitude);
        }
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LatLng latLng = (LatLng) o;
        return Double.compare(latLng.latitude, latitude) == 0 &&
                Double.compare(latLng.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "LatLng{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
