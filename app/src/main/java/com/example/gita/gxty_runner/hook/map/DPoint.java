package com.example.gita.gxty_runner.hook.map;


import com.example.gita.gxty_runner.hook.bluetooth.Beacon;

import java.util.Objects;

public class DPoint {

    public LatLng latLng;
    public Beacon beacon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DPoint dPoint = (DPoint) o;
        return Objects.equals(latLng, dPoint.latLng) &&
                Objects.equals(beacon, dPoint.beacon);
    }

    @Override
    public int hashCode() {

        return Objects.hash(latLng, beacon);
    }

    @Override
    public String toString() {
        return "DPoint{" +
                "latLng=" + latLng +
                ", beacon=" + beacon +
                '}';
    }

    public DPoint(LatLng latLng, Beacon beacon) {
        this.latLng = latLng;
        this.beacon = beacon;
    }

    public DPoint(LatLng latLng) {
        this.latLng = latLng;
        this.beacon = null;
    }

    public DPoint() {
        this.latLng = null;
        this.beacon = null;
    }
}
