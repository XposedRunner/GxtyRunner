package com.example.gita.gxty.model;

import java.io.Serializable;
import java.util.List;

public class SignOverData implements Serializable {
    public String duration;
    public String endTime;
    public List<SignPoint> points;
    public String startTime;
}
