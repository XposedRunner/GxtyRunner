package com.example.gita.gxty.model;

import java.io.Serializable;
import java.util.List;

public class TopicData implements Serializable {
    public Detail detail;
    public List<Feed> feed;
    public String id;

    public class Detail implements Serializable {
        public String desc;
        public String icon;
        public String id;
        public String title;
    }
}
