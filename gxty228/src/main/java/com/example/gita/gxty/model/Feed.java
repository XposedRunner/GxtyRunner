package com.example.gita.gxty.model;

import com.qq.e.ads.nativ.NativeExpressADView;
import java.io.Serializable;
import java.util.List;

public class Feed implements Serializable {
    public NativeExpressADView adView;
    public List<String> content_pic;
    public String content_word;
    public String create_time;
    public int feed_count;
    public String feed_id;
    public int isNeAd = 0;
    public int like_count;
    public Long myabcdtime;
    public String user_head;
    public String user_name;
    public int view_count;
    public XingKongAds xkAds;

    public long getMyabcdTime() {
        if (this.myabcdtime == null) {
            try {
                this.myabcdtime = Long.valueOf(Long.parseLong(this.create_time));
            } catch (Exception e) {
                this.myabcdtime = Long.valueOf(0);
            }
        }
        return this.myabcdtime.longValue();
    }
}
