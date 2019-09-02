package com.example.gita.gxty.ram.db;

import android.net.Uri;
import cn.jiguang.net.HttpUtils;

/* compiled from: IResolver */
public class b {
    private Uri a;

    public Uri a(String str) {
        if (this.a == null) {
            this.a = Uri.parse("content://" + a.a().c() + HttpUtils.PATHS_SEPARATOR + str);
        }
        return this.a;
    }
}
