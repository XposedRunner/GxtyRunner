package com.amap.api.mapcore.util;

import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;

/* compiled from: EngineStyleKeyItem */
public class dg {
    int a;
    int[] b;
    int c;
    int d;
    String e;
    String f;
    String g;

    public dg(int i, int[] iArr, String str, String str2, String str3) {
        this.a = i;
        this.b = iArr;
        this.e = str;
        this.f = str2;
        this.g = str3;
        if (!TextUtils.isEmpty(str)) {
            str2 = str;
        }
        this.c = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
        if ("regions".equals(str2)) {
            this.c = 1001;
        } else if ("water".equals(str2)) {
            this.c = 1002;
        } else if ("buildings".equals(str2)) {
            this.c = PointerIconCompat.TYPE_HELP;
        } else if ("roads".equals(str2)) {
            this.c = 1004;
        } else if ("labels".equals(str2)) {
            this.c = 1005;
        } else if ("borders".equals(str2)) {
            this.c = PointerIconCompat.TYPE_CELL;
        }
        this.d = (i * 1000) + iArr.hashCode();
    }
}
