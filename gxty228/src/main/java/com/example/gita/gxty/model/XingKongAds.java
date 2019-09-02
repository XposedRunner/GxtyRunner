package com.example.gita.gxty.model;

import java.io.Serializable;
import java.util.List;

public class XingKongAds implements Serializable {
    public int ad_type;
    public String ad_url;
    public List<String> app;
    public List<String> click_urls;
    public String desc;
    public String download_url;
    public List<String> download_urls;
    public List<String> downloaded_urls;
    public List<String> image_urls;
    public List<String> installed_urls;
    public List<String> show_urls;
    public String title;

    public String toString() {
        return "XingKongAds{ad_type=" + this.ad_type + ", title='" + this.title + '\'' + ", desc='" + this.desc + '\'' + ", image_urls=" + this.image_urls + ", ad_url=" + this.ad_url + ", show_urls=" + this.show_urls + ", click_urls=" + this.click_urls + ", download_url='" + this.download_url + '\'' + ", download_urls=" + this.download_urls + ", downloaded_urls=" + this.downloaded_urls + ", installed_urls=" + this.installed_urls + ", app=" + this.app + '}';
    }
}
