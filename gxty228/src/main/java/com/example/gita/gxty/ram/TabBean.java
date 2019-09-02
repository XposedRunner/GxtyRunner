package com.example.gita.gxty.ram;

import android.os.Bundle;
import com.example.gita.gxty.utils.h;
import java.io.Serializable;

public class TabBean implements Serializable, Comparable<TabBean> {
    private static final long serialVersionUID = -8321961610124540326L;
    public String action;
    public Bundle bundle;
    public int countType;
    public String icon;
    public int index;
    public String tabId;
    public int tabImageId;
    public int tabItemBgSelectId;
    public int tabItemBgUnSelectId;
    public String text;

    public int compareTo(TabBean tabBean) {
        if (tabBean == null) {
            return 1;
        }
        try {
            if (tabBean.index > this.index) {
                return -1;
            }
            return 1;
        } catch (Exception e) {
            h.a(e);
            return 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            if (((TabBean) obj).tabId.equals(this.tabId)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            h.a(e);
            return false;
        }
    }
}
