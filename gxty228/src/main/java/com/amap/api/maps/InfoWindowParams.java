package com.amap.api.maps;

import android.view.View;

public class InfoWindowParams {
    public static final int INFOWINDOW_TYPE_IMAGE = 1;
    public static final int INFOWINDOW_TYPE_VIEW = 2;
    private int a = 2;
    private int b;
    private View c;
    private View d;

    public void setInfoWindowUpdateTime(int i) {
        this.b = i;
    }

    public long getInfoWindowUpdateTime() {
        return (long) this.b;
    }

    public void setInfoWindowType(int i) {
        this.a = i;
    }

    public int getInfoWindowType() {
        return this.a;
    }

    public View getInfoWindow() {
        return this.c;
    }

    public void setInfoContent(View view) {
        this.d = view;
    }

    public void setInfoWindow(View view) {
        this.c = view;
    }

    public View getInfoContents() {
        return this.d;
    }
}
