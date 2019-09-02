package com.example.gita.gxty.model;

import com.amap.api.maps.model.LatLng;
import com.github.mikephil.charting.data.Entry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataRunResult implements Serializable {
    public ArrayList<Ibeacon> bNodeV2;
    public ArrayList<Ibeacon> beacon;
    public String bupin;
    public String busu;
    public String cal;
    public String duration;
    public String duration2;
    public String endTime;
    public String goal;
    public List<LatLng> gpsinfo;
    public String length;
    public String real;
    public String realKm;
    public String runDesc;
    public String runEndTime;
    public String runStartTime;
    public String runid;
    public String speed;
    public String startTime;
    public String state;
    public ArrayList<LatLng> tNodeV2;
    public String totalNum;
    public List<MyLatLng> track;
    public ArrayList<Entry> trend;
    public String userid;
}
