package com.sina.weibo.sdk.auth;

import android.text.TextUtils;
import java.util.ArrayList;

public class WeiboParameters {
    private ArrayList<String> mKeys = new ArrayList();
    private ArrayList<String> mValues = new ArrayList();

    public void add(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.mKeys.add(str);
            this.mValues.add(str2);
        }
    }

    public void add(String str, int i) {
        this.mKeys.add(str);
        this.mValues.add(String.valueOf(i));
    }

    public void add(String str, long j) {
        this.mKeys.add(str);
        this.mValues.add(String.valueOf(j));
    }

    public void remove(String str) {
        int indexOf = this.mKeys.indexOf(str);
        if (indexOf >= 0) {
            this.mKeys.remove(indexOf);
            this.mValues.remove(indexOf);
        }
    }

    public void remove(int i) {
        if (i < this.mKeys.size()) {
            this.mKeys.remove(i);
            this.mValues.remove(i);
        }
    }

    private int getLocation(String str) {
        if (this.mKeys.contains(str)) {
            return this.mKeys.indexOf(str);
        }
        return -1;
    }

    public String getKey(int i) {
        if (i < 0 || i >= this.mKeys.size()) {
            return "";
        }
        return (String) this.mKeys.get(i);
    }

    public String getValue(String str) {
        int location = getLocation(str);
        if (location < 0 || location >= this.mKeys.size()) {
            return null;
        }
        return (String) this.mValues.get(location);
    }

    public String getValue(int i) {
        if (i < 0 || i >= this.mKeys.size()) {
            return null;
        }
        return (String) this.mValues.get(i);
    }

    public int size() {
        return this.mKeys.size();
    }

    public void addAll(WeiboParameters weiboParameters) {
        for (int i = 0; i < weiboParameters.size(); i++) {
            add(weiboParameters.getKey(i), weiboParameters.getValue(i));
        }
    }

    public void clear() {
        this.mKeys.clear();
        this.mValues.clear();
    }
}
