package com.autonavi.ae.gmap.style;

import java.util.HashMap;
import java.util.Map;

public class StyleItem {
    public int mainKey;
    private Map<Integer, StyleElement> styleElements = new HashMap();
    private int styleTypeId;
    public int[] subKey;

    public StyleItem(int i) {
        this.styleTypeId = i;
    }

    public StyleElement[] getStyleElements() {
        if (this.styleElements == null || this.styleElements.size() <= 0) {
            return null;
        }
        return (StyleElement[]) this.styleElements.values().toArray(new StyleElement[this.styleElements.size()]);
    }

    public StyleElement get(int i) {
        return (StyleElement) this.styleElements.get(Integer.valueOf(i));
    }

    public void put(int i, StyleElement styleElement) {
        this.styleElements.put(Integer.valueOf(i), styleElement);
    }

    public boolean isValid() {
        if (this.styleElements.size() <= 0 || this.styleTypeId < 0) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("styleTypeId:").append(this.styleTypeId).append("\n");
        stringBuilder.append("styleElements.size :").append(this.styleElements.size());
        return stringBuilder.toString();
    }
}
