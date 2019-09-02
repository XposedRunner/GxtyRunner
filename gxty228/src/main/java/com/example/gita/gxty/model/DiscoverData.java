package com.example.gita.gxty.model;

import com.example.gita.gxty.weiget.vtext.Notice;
import java.io.Serializable;
import java.util.List;

public class DiscoverData implements Serializable {
    public List<Banner> banner;
    public List<Feed> feed;
    public List<Notice> notice;
    public List<Topic> topic;

    public class Banner implements Serializable {
        public String href;
        public String pic;
        public String type;
    }
}
