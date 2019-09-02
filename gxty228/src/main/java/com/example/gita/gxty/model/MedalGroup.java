package com.example.gita.gxty.model;

import java.io.Serializable;
import java.util.List;

public class MedalGroup implements Serializable {
    public String cate_id;
    public List<MedalData> child;
    public String display;
    public String key;
    public String sort;
    public String value;
}
