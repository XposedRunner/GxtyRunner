package com.example.gita.gxty.model;

import java.io.Serializable;

public class Sex implements Serializable {
    public String id;
    public String name;

    public Sex(String str, String str2) {
        this.id = str;
        this.name = str2;
    }

    public String toString() {
        return this.name;
    }
}
