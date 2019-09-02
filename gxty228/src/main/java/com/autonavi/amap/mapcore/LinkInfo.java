package com.autonavi.amap.mapcore;

public class LinkInfo {
    private int length = 0;
    private int state = 0;
    private int time = 0;

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int i) {
        this.time = i;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("状态：").append(this.state).append("|");
        stringBuilder.append("时间：").append(this.time).append("|");
        stringBuilder.append("长度：").append(this.length);
        return stringBuilder.toString();
    }
}
