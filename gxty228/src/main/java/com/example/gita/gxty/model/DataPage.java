package com.example.gita.gxty.model;

import java.io.Serializable;
import java.util.List;

public class DataPage implements Serializable {
    public String cycle;
    public List<String> feedIds;
    public String keyword;
    public String limit;
    public String page;
    public String term_id;
    public String type;
    public String userid;
}
