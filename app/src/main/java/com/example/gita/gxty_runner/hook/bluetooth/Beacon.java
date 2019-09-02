package com.example.gita.gxty_runner.hook.bluetooth;

import java.util.Objects;

public class Beacon {
    public String uuid;
    public String major;
    public String minor;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beacon)) return false;
        Beacon beacon = (Beacon) o;
        return Objects.equals(major, beacon.major) &&
                Objects.equals(minor, beacon.minor) &&
                Objects.equals(uuid, beacon.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(major, minor, uuid);
    }

    @Override
    public String toString() {
        return "Beacon{" +
                "major='" + major + '\'' +
                ", minor='" + minor + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public Beacon(String uuid,String major, String minor ) {
        this.uuid = uuid;
        this.major = major;
        this.minor = minor;
    }

    public Beacon() {
    }
}
