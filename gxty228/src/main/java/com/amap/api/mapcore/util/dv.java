package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.model.LatLng;
import java.lang.ref.WeakReference;
import java.util.Hashtable;

/* compiled from: InfoCollectUtils */
public class dv {
    private static boolean a = false;
    private static volatile dv d;
    private Hashtable<String, String> b = new Hashtable();
    private WeakReference<Context> c = null;

    private dv() {
    }

    public static dv a() {
        if (d == null) {
            synchronized (dv.class) {
                if (d == null) {
                    d = new dv();
                }
            }
        }
        return d;
    }

    public static void b() {
        if (d != null) {
            if (d.b != null && d.b.size() > 0) {
                synchronized (d.b) {
                    d.c();
                    if (d.c != null) {
                        d.c.clear();
                    }
                }
            }
            d = null;
        }
        a(false);
    }

    public static void a(boolean z) {
        a = z;
    }

    public static void a(int i) {
        if (a) {
            a(i < 1000);
        }
    }

    public void a(Context context) {
        if (context != null) {
            this.c = new WeakReference(context);
        }
    }

    public void a(LatLng latLng, String str, String str2) {
        if (!a) {
            this.b.clear();
        } else if (latLng != null && !TextUtils.isEmpty(str)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("{");
            stringBuffer.append("\"lon\":").append(latLng.longitude).append(",");
            stringBuffer.append("\"lat\":").append(latLng.latitude).append(",");
            stringBuffer.append("\"title\":").append("\"").append(str).append("\"").append(",");
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            stringBuffer.append("\"snippet\":").append("\"").append(str2).append("\"");
            stringBuffer.append("}");
            a(stringBuffer.toString());
        }
    }

    private void a(String str) {
        if (str != null && this.b != null) {
            synchronized (this.b) {
                String b = gf.b(str);
                if (!(this.b == null || this.b.contains(b))) {
                    this.b.put(b, str);
                }
                if (d()) {
                    c();
                }
            }
        }
    }

    private void c() {
        if (!a) {
            this.b.clear();
        } else if (this.b != null) {
            StringBuffer stringBuffer = new StringBuffer();
            int size = this.b.size();
            if (size > 0) {
                stringBuffer.append("[");
                int i = 0;
                for (String append : this.b.values()) {
                    i++;
                    stringBuffer.append(append);
                    if (i < size) {
                        stringBuffer.append(",");
                    }
                }
                stringBuffer.append("]");
                Object stringBuffer2 = stringBuffer.toString();
                if (!(TextUtils.isEmpty(stringBuffer2) || this.c == null || this.c.get() == null)) {
                    jg.a(stringBuffer2, (Context) this.c.get());
                }
            }
            this.b.clear();
        }
    }

    private boolean d() {
        if (this.b == null || this.b.size() <= 20) {
            return false;
        }
        return true;
    }
}
