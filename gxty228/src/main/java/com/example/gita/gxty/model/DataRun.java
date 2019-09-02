package com.example.gita.gxty.model;

import com.github.mikephil.charting.data.Entry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataRun implements Serializable {
    public ArrayList<Ibeacon> bNode;
    public String buPin;
    public String duration;
    public String endTime;
    public String frombp;
    public String goal;
    public String real;
    public String runPageId;
    public String speed;
    public String startTime;
    public ArrayList<MyLatLng> tNode;
    public String totalNum;
    public List<MyLatLng> track;
    public ArrayList<Entry> trend;
    public String type;
    public String userid;
}
