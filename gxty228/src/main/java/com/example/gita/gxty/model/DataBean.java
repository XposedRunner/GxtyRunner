package com.example.gita.gxty.model;

import android.net.Uri;
import java.io.Serializable;

public class DataBean implements Serializable {
    public String data;
    public String sign;

    public String getEndString() {
        return "&sign=" + this.sign + "&data=" + Uri.encode(this.data);
    }
}
