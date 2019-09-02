package com.example.gita.gxty.model;

import com.example.gita.gxty.utils.h;
import com.example.gita.gxty.weiget.sortme.a;
import com.example.gita.gxty.weiget.sortme.b;
import java.io.Serializable;

public class School extends b implements Serializable {
    public double distance;
    public String id;
    public String name;

    public String toString() {
        return this.name;
    }

    public void filledData(a aVar) {
        setSortName(this.name);
        try {
            String toUpperCase = aVar.b(this.name).substring(0, 1).toUpperCase();
            if (toUpperCase.matches("[A-Z]")) {
                setSortLetters(toUpperCase.toUpperCase());
            } else {
                setSortLetters("#");
            }
        } catch (Exception e) {
            h.a(e);
            setSortLetters("#");
        }
    }
}
