package com.bigkoo.pickerview.a;

import java.util.ArrayList;

/* compiled from: ArrayWheelAdapter */
public class a<T> implements b {
    private ArrayList<T> a;
    private int b;

    public a(ArrayList<T> arrayList, int i) {
        this.a = arrayList;
        this.b = i;
    }

    public a(ArrayList<T> arrayList) {
        this(arrayList, 4);
    }

    public Object a(int i) {
        if (i < 0 || i >= this.a.size()) {
            return "";
        }
        return this.a.get(i);
    }

    public int a() {
        return this.a.size();
    }

    public int a(Object obj) {
        return this.a.indexOf(obj);
    }
}
