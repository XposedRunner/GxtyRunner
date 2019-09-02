package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amap.api.maps.AMap.CommonInfoWindowAdapter;
import com.amap.api.maps.AMap.ImageInfoWindowAdapter;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.MultiPositionInfoWindowAdapter;
import com.amap.api.maps.InfoWindowParams;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.Marker;

/* compiled from: InfoWindowDelegate */
public class ac {
    InfoWindowAdapter a = null;
    CommonInfoWindowAdapter b = null;
    Context c;
    private boolean d = true;
    private View e;
    private TextView f;
    private TextView g;
    private Drawable h = null;
    private ab i;
    private ab j;
    private InfoWindowAdapter k = new InfoWindowAdapter(this) {
        final /* synthetic */ ac a;

        {
            this.a = r1;
        }

        public View getInfoWindow(Marker marker) {
            try {
                if (this.a.h == null) {
                    this.a.h = eb.a(this.a.c, "infowindow_bg.9.png");
                }
                if (this.a.e == null) {
                    this.a.e = new LinearLayout(this.a.c);
                    this.a.e.setBackground(this.a.h);
                    this.a.f = new TextView(this.a.c);
                    this.a.f.setText(marker.getTitle());
                    this.a.f.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    this.a.g = new TextView(this.a.c);
                    this.a.g.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    this.a.g.setText(marker.getSnippet());
                    ((LinearLayout) this.a.e).setOrientation(1);
                    ((LinearLayout) this.a.e).addView(this.a.f);
                    ((LinearLayout) this.a.e).addView(this.a.g);
                }
            } catch (Throwable th) {
                gz.c(th, "InfoWindowDelegate", "showInfoWindow decodeDrawableFromAsset");
                th.printStackTrace();
            }
            return this.a.e;
        }

        public View getInfoContents(Marker marker) {
            return null;
        }
    };
    private CommonInfoWindowAdapter l = new CommonInfoWindowAdapter(this) {
        final /* synthetic */ ac a;

        {
            this.a = r1;
        }

        public InfoWindowParams getInfoWindowParams(BasePointOverlay basePointOverlay) {
            try {
                InfoWindowParams infoWindowParams = new InfoWindowParams();
                if (this.a.h == null) {
                    this.a.h = eb.a(this.a.c, "infowindow_bg.9.png");
                }
                this.a.e = new LinearLayout(this.a.c);
                this.a.e.setBackground(this.a.h);
                this.a.f = new TextView(this.a.c);
                this.a.f.setText("标题");
                this.a.f.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.a.g = new TextView(this.a.c);
                this.a.g.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.a.g.setText("内容");
                ((LinearLayout) this.a.e).setOrientation(1);
                ((LinearLayout) this.a.e).addView(this.a.f);
                ((LinearLayout) this.a.e).addView(this.a.g);
                infoWindowParams.setInfoWindowType(2);
                infoWindowParams.setInfoWindow(this.a.e);
                return infoWindowParams;
            } catch (Throwable th) {
                gz.c(th, "InfoWindowDelegate", "showInfoWindow decodeDrawableFromAsset");
                th.printStackTrace();
                return null;
            }
        }
    };

    public ac(Context context) {
        this.c = context;
    }

    public void a(ab abVar) {
        synchronized (this) {
            this.i = abVar;
            if (this.i != null) {
                this.i.a(this);
            }
        }
    }

    public void b(ab abVar) {
        synchronized (this) {
            this.j = abVar;
            if (this.j != null) {
                this.j.a(this);
            }
        }
    }

    public synchronized boolean a() {
        return this.d;
    }

    public void a(String str, String str2) {
        if (this.f != null) {
            this.f.requestLayout();
            this.f.setText(str);
        }
        if (this.g != null) {
            this.g.requestLayout();
            this.g.setText(str2);
        }
        if (this.e != null) {
            this.e.requestLayout();
        }
    }

    public synchronized void a(InfoWindowAdapter infoWindowAdapter) {
        this.a = infoWindowAdapter;
        this.b = null;
        if (this.a == null) {
            this.a = this.k;
            this.d = true;
        } else {
            this.d = false;
        }
        if (this.j != null) {
            this.j.a_();
        }
        if (this.i != null) {
            this.i.a_();
        }
    }

    public synchronized void a(CommonInfoWindowAdapter commonInfoWindowAdapter) {
        this.b = commonInfoWindowAdapter;
        this.a = null;
        if (this.b == null) {
            this.b = this.l;
            this.d = true;
        } else {
            this.d = false;
        }
        if (this.j != null) {
            this.j.a_();
        }
        if (this.i != null) {
            this.i.a_();
        }
    }

    public void b() {
        this.c = null;
        this.e = null;
        this.f = null;
        this.g = null;
        synchronized (this) {
            en.a(this.h);
            this.h = null;
            this.k = null;
            this.a = null;
        }
        this.b = null;
    }

    public View a(BasePointOverlay basePointOverlay) {
        if (this.a != null) {
            return this.a.getInfoWindow((Marker) basePointOverlay);
        }
        InfoWindowParams infoWindowParams;
        if (this.b != null) {
            infoWindowParams = this.b.getInfoWindowParams(basePointOverlay);
            if (infoWindowParams != null) {
                return infoWindowParams.getInfoWindow();
            }
        }
        infoWindowParams = this.l.getInfoWindowParams(basePointOverlay);
        if (infoWindowParams != null) {
            return infoWindowParams.getInfoWindow();
        }
        return null;
    }

    public View b(BasePointOverlay basePointOverlay) {
        if (this.a != null) {
            return this.a.getInfoContents((Marker) basePointOverlay);
        }
        InfoWindowParams infoWindowParams;
        if (this.b != null) {
            infoWindowParams = this.b.getInfoWindowParams(basePointOverlay);
            if (infoWindowParams != null) {
                return infoWindowParams.getInfoContents();
            }
        }
        infoWindowParams = this.l.getInfoWindowParams(basePointOverlay);
        if (infoWindowParams != null) {
            return infoWindowParams.getInfoContents();
        }
        return null;
    }

    public View a(Marker marker) {
        if (this.a == null || !(this.a instanceof MultiPositionInfoWindowAdapter)) {
            return null;
        }
        return ((MultiPositionInfoWindowAdapter) this.a).getInfoWindowClick(marker);
    }

    public View b(Marker marker) {
        if (this.a == null || !(this.a instanceof MultiPositionInfoWindowAdapter)) {
            return null;
        }
        return ((MultiPositionInfoWindowAdapter) this.a).getOverturnInfoWindow(marker);
    }

    public View c(Marker marker) {
        if (this.a == null || !(this.a instanceof MultiPositionInfoWindowAdapter)) {
            return null;
        }
        return ((MultiPositionInfoWindowAdapter) this.a).getOverturnInfoWindowClick(marker);
    }

    public long c(BasePointOverlay basePointOverlay) {
        if (this.a != null && (this.a instanceof ImageInfoWindowAdapter)) {
            return ((ImageInfoWindowAdapter) this.a).getInfoWindowUpdateTime();
        }
        if (this.b != null) {
            InfoWindowParams infoWindowParams = this.b.getInfoWindowParams(basePointOverlay);
            if (infoWindowParams != null) {
                return infoWindowParams.getInfoWindowUpdateTime();
            }
        }
        return 0;
    }

    public void c() {
        ab d = d();
        if (d != null) {
            d.b();
        }
    }

    public synchronized ab d() {
        ab abVar;
        if (this.a != null) {
            if (this.a instanceof ImageInfoWindowAdapter) {
                abVar = this.j;
            } else if (this.a instanceof MultiPositionInfoWindowAdapter) {
                abVar = this.j;
            }
        }
        if (this.b == null || this.b.getInfoWindowParams(null).getInfoWindowType() != 1) {
            abVar = this.i;
        } else {
            abVar = this.j;
        }
        return abVar;
    }

    public boolean a(MotionEvent motionEvent) {
        ab d = d();
        if (d != null) {
            return d.a(motionEvent);
        }
        return false;
    }

    public void e() {
        ab d = d();
        if (d != null) {
            d.a_();
        }
    }

    public void a(kn knVar) throws RemoteException {
        ab d = d();
        if (d != null) {
            d.a(knVar);
        }
    }

    public Drawable f() {
        if (this.h == null) {
            try {
                this.h = eb.a(this.c, "infowindow_bg.9.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.h;
    }
}
