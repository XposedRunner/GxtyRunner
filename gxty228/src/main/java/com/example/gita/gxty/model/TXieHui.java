package com.example.gita.gxty.model;

import java.io.Serializable;
import java.util.List;

public class TXieHui implements Serializable {
    public String add_time;
    public String admin_id;
    public String ass_id;
    public List<Ibeacon> beacon;
    public String is_del;
    public String joined;
    public String limited;
    public String name;
    public String open_time;
    public String school_id;
    public String upd_time;

    public String toString() {
        return "TXieHui{ass_id='" + this.ass_id + '\'' + ", name='" + this.name + '\'' + ", open_time='" + this.open_time + '\'' + ", limited='" + this.limited + '\'' + ", joined='" + this.joined + '\'' + ", beacon=" + this.beacon + ", school_id='" + this.school_id + '\'' + ", admin_id='" + this.admin_id + '\'' + ", add_time='" + this.add_time + '\'' + ", upd_time='" + this.upd_time + '\'' + ", is_del='" + this.is_del + '\'' + '}';
    }
}
