package com.example.gita.gxty.ram.adapt;

public class ImageItem implements IImageUrl {
    public int childCount;
    public String name;
    public String onlineId;
    public int sort;
    public String url;

    public String getImageUrl() {
        return this.url;
    }

    public ImageItem(String str, String str2, int i) {
        this.url = str;
        this.name = str2;
        this.sort = i;
    }
}
