package com.amap.api.mapcore.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.maps.model.LatLng;
import com.amap.api.trace.LBSTraceClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: TraceResultPool */
public class fv {
    private static volatile fv b = null;
    private Map<String, a> a;

    /* compiled from: TraceResultPool */
    class a {
        final /* synthetic */ fv a;
        private int b = 0;
        private int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = 0;
        private HashMap<Integer, List<LatLng>> g;
        private List<LatLng> h = new ArrayList();

        public a(fv fvVar, int i, int i2, int i3, HashMap<Integer, List<LatLng>> hashMap) {
            this.a = fvVar;
            this.b = i2;
            this.g = hashMap;
            this.c = i;
            this.e = i3;
        }

        public HashMap<Integer, List<LatLng>> a() {
            return this.g;
        }

        public void a(Handler handler) {
            for (int i = this.d; i <= this.b; i++) {
                List list = (List) this.g.get(Integer.valueOf(i));
                if (list == null) {
                    break;
                }
                this.h.addAll(list);
                a(handler, list);
            }
            if (this.d == this.b + 1) {
                b(handler);
            }
        }

        private void a(Handler handler, List<LatLng> list) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.obj = list;
            obtainMessage.what = 100;
            obtainMessage.arg1 = this.d;
            Bundle bundle = new Bundle();
            bundle.putInt("lineID", this.c);
            obtainMessage.setData(bundle);
            handler.sendMessage(obtainMessage);
            this.d++;
            this.f++;
        }

        private void b(Handler handler) {
            if (this.f > 0) {
                int a = fk.a(this.h);
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.obj = this.h;
                obtainMessage.what = 101;
                obtainMessage.arg1 = a;
                obtainMessage.arg2 = this.e;
                Bundle bundle = new Bundle();
                bundle.putInt("lineID", this.c);
                obtainMessage.setData(bundle);
                handler.sendMessage(obtainMessage);
                return;
            }
            this.a.a(handler, this.c, LBSTraceClient.MIN_GRASP_POINT_ERROR);
        }
    }

    public fv() {
        this.a = null;
        this.a = Collections.synchronizedMap(new HashMap());
    }

    public static fv a() {
        if (b == null) {
            synchronized (fv.class) {
                if (b == null) {
                    b = new fv();
                }
            }
        }
        return b;
    }

    public synchronized void a(String str, int i, List<LatLng> list) {
        if (this.a != null) {
            ((a) this.a.get(str)).a().put(Integer.valueOf(i), list);
        }
    }

    public synchronized void a(String str, int i, int i2, int i3) {
        if (this.a != null) {
            this.a.put(str, new a(this, i, i2, i3, new HashMap(16)));
        }
    }

    public synchronized a a(String str) {
        a aVar;
        if (this.a != null) {
            aVar = (a) this.a.get(str);
        } else {
            aVar = null;
        }
        return aVar;
    }

    public void a(Handler handler, int i, String str) {
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.obj = str;
        obtainMessage.what = 102;
        Bundle bundle = new Bundle();
        bundle.putInt("lineID", i);
        obtainMessage.setData(bundle);
        handler.sendMessage(obtainMessage);
    }
}
