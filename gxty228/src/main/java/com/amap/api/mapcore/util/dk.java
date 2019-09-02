package com.amap.api.mapcore.util;

import com.autonavi.ae.gmap.style.StyleItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: StyleParserResult */
public class dk {
    private Map<Integer, StyleItem> a = new ConcurrentHashMap();
    private Object b = null;
    private StyleItem[] c;

    public Map<Integer, StyleItem> a() {
        return this.a;
    }

    public StyleItem[] b() {
        if (this.a == null || this.a.size() == 0) {
            return null;
        }
        List arrayList = new ArrayList();
        for (StyleItem styleItem : this.a.values()) {
            if (styleItem.isValid()) {
                arrayList.add(styleItem);
            }
        }
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        this.c = (StyleItem[]) arrayList.toArray(new StyleItem[size]);
        return this.c;
    }

    public StyleItem[] c() {
        return this.c;
    }

    public Object d() {
        return this.b;
    }
}
