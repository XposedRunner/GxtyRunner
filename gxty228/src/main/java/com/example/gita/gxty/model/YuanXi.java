package com.example.gita.gxty.model;

import java.io.Serializable;

public class YuanXi implements Serializable {
    public String department_id;
    public String department_name;
    public String school_id;

    public String toString() {
        return this.department_name;
    }
}
